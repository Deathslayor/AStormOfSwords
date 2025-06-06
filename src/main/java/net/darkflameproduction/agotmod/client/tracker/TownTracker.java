package net.darkflameproduction.agotmod.client.tracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.darkflameproduction.agotmod.client.overlay.TownNotificationOverlay;
import net.darkflameproduction.agotmod.client.renderer.TownHallDebugRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.darkflameproduction.agotmod.AGoTMod;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class TownTracker {

    private static final int SCAN_HEIGHT = 64; // Same as debug renderer
    private static final int CHECK_INTERVAL = 20; // Check every second (20 ticks)
    private static final String TOWN_NAMES_FILE = "townhall_names.json";

    private static int tickCounter = 0;
    private static BlockPos currentTownPos = null;
    private static String currentTownName = "";
    private static final Map<BlockPos, String> townNames = new HashMap<>();
    private static final Map<BlockPos, Integer> townPopulations = new HashMap<>(); // Store population data

    // Gson instance for JSON serialization
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Serializable version for JSON storage
    public static class SerializableTownName {
        public int x, y, z; // BlockPos coordinates
        public String name;
        public int population; // Add population storage

        public SerializableTownName(BlockPos pos, String name, int population) {
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            this.name = name;
            this.population = population;
        }

        public BlockPos getBlockPos() {
            return new BlockPos(x, y, z);
        }
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        tickCounter++;

        // Check more frequently for better responsiveness (every 10 ticks = 0.5 seconds)
        if (tickCounter % 10 != 0) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) {
            return;
        }

        Player player = mc.player;
        BlockPos playerPos = player.blockPosition();

        // Check which town (if any) the player is currently in
        BlockPos townInside = null;
        String townNameInside = "";

        // Get town hall data from the debug renderer
        Map<BlockPos, TownHallDebugRenderer.TownHallData> townData = getTownHallData();

        // Debug: Log player position and towns being checked
        if (tickCounter % 40 == 0) { // Every 2 seconds
            System.out.println("DEBUG: Player at " + playerPos + ", checking " + townData.size() + " towns");
        }

        for (Map.Entry<BlockPos, TownHallDebugRenderer.TownHallData> entry : townData.entrySet()) {
            BlockPos townHallPos = entry.getKey();
            TownHallDebugRenderer.TownHallData data = entry.getValue();

            if (isPlayerInTown(playerPos, townHallPos, data.radius)) {
                townInside = townHallPos;
                townNameInside = townNames.getOrDefault(townHallPos, "Unknown Town");

                // Debug: Log when player is detected in town
                if (tickCounter % 40 == 0) {
                    System.out.println("DEBUG: Player detected in town: " + townNameInside + " at " + townHallPos + " (radius: " + data.radius + ")");
                }
                break; // Player can only be in one town at a time (first one found)
            }
        }

        // Check if town status changed
        boolean wasInTown = currentTownPos != null;
        boolean isInTown = townInside != null;
        boolean townChanged = false;

        if (wasInTown && isInTown) {
            // Player was in a town and is still in a town - check if it's the same town
            townChanged = !currentTownPos.equals(townInside);
        } else if (wasInTown != isInTown) {
            // Player entered or left a town
            townChanged = true;
        }

        // Debug: Log town status changes
        if (townChanged) {
            System.out.println("DEBUG: Town status changed - WasInTown: " + wasInTown + ", IsInTown: " + isInTown);
            System.out.println("DEBUG: Previous town: " + (currentTownPos != null ? currentTownPos + " (" + currentTownName + ")" : "none"));
            System.out.println("DEBUG: Current town: " + (townInside != null ? townInside + " (" + townNameInside + ")" : "none"));
        }

        if (townChanged) {
            if (wasInTown && !isInTown) {
                // Player left a town - always show exit message immediately
                System.out.println("DEBUG: TRIGGERING EXIT MESSAGE for: " + currentTownName);
                TownNotificationOverlay.showExitMessage(currentTownName);
                System.out.println("DEBUG: Player left town: " + currentTownName + " at " + currentTownPos);
            }

            if (!wasInTown && isInTown) {
                // Player entered a town from outside - show entry message with population
                int population = townPopulations.getOrDefault(townInside, 0);
                System.out.println("DEBUG: TRIGGERING ENTRY MESSAGE for: " + townNameInside + " (Population: " + population + ")");
                TownNotificationOverlay.showEntryMessage(townNameInside, population);
                System.out.println("DEBUG: Player entered town: " + townNameInside + " at " + townInside);
            }

            if (wasInTown && isInTown && townChanged) {
                // Player moved from one town directly to another - show both messages with delay
                System.out.println("DEBUG: TRIGGERING TOWN-TO-TOWN MESSAGES: " + currentTownName + " -> " + townNameInside);
                TownNotificationOverlay.showExitMessage(currentTownName);
                System.out.println("DEBUG: Player left town: " + currentTownName + " at " + currentTownPos);

                // Schedule entry message after a delay with population
                int population = townPopulations.getOrDefault(townInside, 0);
                scheduleDelayedEntryMessage(townNameInside, population);
            }

            // Update current town status
            currentTownPos = townInside;
            currentTownName = townNameInside;
        }
    }

    /**
     * Schedule an entry message after a delay to avoid overlapping with exit messages
     */
    private static void scheduleDelayedEntryMessage(String townName, int population) {
        // Use a simple timer to delay the entry message
        new Thread(() -> {
            try {
                Thread.sleep(1000); // 1 second delay
                if (!TownNotificationOverlay.isShowingMessage()) {
                    TownNotificationOverlay.showEntryMessage(townName, population);
                    System.out.println("DEBUG: Player entered town (delayed): " + townName + " (Population: " + population + ")");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * Schedule an entry message after a delay (backwards compatibility)
     */
    private static void scheduleDelayedEntryMessage(String townName) {
        scheduleDelayedEntryMessage(townName, 0);
    }

    /**
     * Check if player is within town boundaries
     */
    private static boolean isPlayerInTown(BlockPos playerPos, BlockPos townHallPos, int radius) {
        int dx = Math.abs(playerPos.getX() - townHallPos.getX());
        int dy = Math.abs(playerPos.getY() - townHallPos.getY());
        int dz = Math.abs(playerPos.getZ() - townHallPos.getZ());

        boolean inTown = dx <= radius && dy <= SCAN_HEIGHT && dz <= radius;

        // Debug boundary checking occasionally
        if (tickCounter % 100 == 0 && (dx <= radius + 5 && dz <= radius + 5)) { // Log when close to boundary
            System.out.println("DEBUG: Boundary check - Player: " + playerPos + ", TownHall: " + townHallPos);
            System.out.println("DEBUG: Distance - dx: " + dx + ", dy: " + dy + ", dz: " + dz + " (radius: " + radius + ", height: " + SCAN_HEIGHT + ")");
            System.out.println("DEBUG: Result: " + (inTown ? "INSIDE" : "OUTSIDE") + " town");
        }

        return inTown;
    }

    /**
     * Get town hall data from the debug renderer
     */
    private static Map<BlockPos, TownHallDebugRenderer.TownHallData> getTownHallData() {
        Map<BlockPos, TownHallDebugRenderer.TownHallData> data = TownHallDebugRenderer.getTownHallDataMap();

        // Debug: Log if no town data is available
        if (data.isEmpty() && tickCounter % 100 == 0) {
            System.out.println("DEBUG: No town hall data available from debug renderer");
        }

        return data;
    }

    /**
     * Update the stored town name for a specific town hall
     */
    public static void updateTownName(BlockPos pos, String name) {
        String oldName = townNames.get(pos);
        townNames.put(pos, name);

        // If this is a new name or changed name, save immediately
        if (!name.equals(oldName)) {
            saveTownNames();
        }

        System.out.println("DEBUG: Updated town name for " + pos + " to '" + name + "'");
    }

    /**
     * Update the stored population for a specific town hall
     */
    public static void updateTownPopulation(BlockPos pos, int population) {
        Integer oldPopulation = townPopulations.get(pos);
        townPopulations.put(pos, population);

        // If this is a new population or changed population, save immediately
        if (oldPopulation == null || !oldPopulation.equals(population)) {
            saveTownNames(); // Save both names and populations
        }

        System.out.println("DEBUG: Updated town population for " + pos + " to " + population);
    }

    /**
     * Update both town name and population (called from packet handler)
     */
    public static void updateTownData(BlockPos pos, String name, int population) {
        String oldName = townNames.get(pos);
        Integer oldPopulation = townPopulations.get(pos);

        townNames.put(pos, name);
        townPopulations.put(pos, population);

        // Save if anything changed
        if (!name.equals(oldName) || oldPopulation == null || !oldPopulation.equals(population)) {
            saveTownNames();
        }

        System.out.println("DEBUG: Updated town data for " + pos + " - Name: '" + name + "', Population: " + population);
    }

    /**
     * Remove a town from tracking
     */
    public static void removeTown(BlockPos pos) {
        townNames.remove(pos);
        townPopulations.remove(pos); // Also remove population data
        saveTownNames(); // Save immediately when town is removed

        // If player was in this town, clear current town status
        if (pos.equals(currentTownPos)) {
            currentTownPos = null;
            currentTownName = "";
        }
    }

    /**
     * Get the name of the town the player is currently in (if any)
     */
    public static String getCurrentTownName() {
        return currentTownName;
    }

    /**
     * Check if player is currently in any town
     */
    public static boolean isInTown() {
        return currentTownPos != null;
    }

    /**
     * Save town names and populations to file
     */
    private static void saveTownNames() {
        try {
            Path gameDir = Paths.get(".");
            Path saveFile = gameDir.resolve(TOWN_NAMES_FILE);

            // Convert to serializable format
            Map<String, SerializableTownName> serializableData = new HashMap<>();
            for (Map.Entry<BlockPos, String> entry : townNames.entrySet()) {
                BlockPos pos = entry.getKey();
                String name = entry.getValue();
                int population = townPopulations.getOrDefault(pos, 0);
                String key = pos.getX() + "," + pos.getY() + "," + pos.getZ();
                serializableData.put(key, new SerializableTownName(pos, name, population));
            }

            // Write to file
            try (FileWriter writer = new FileWriter(saveFile.toFile())) {
                gson.toJson(serializableData, writer);
            }

            System.out.println("DEBUG: Saved " + townNames.size() + " town entries (names and populations) to " + TOWN_NAMES_FILE);

        } catch (IOException e) {
            System.err.println("DEBUG: Failed to save town names: " + e.getMessage());
        }
    }

    /**
     * Load town names and populations from file
     */
    private static void loadTownNames() {
        try {
            Path gameDir = Paths.get(".");
            Path saveFile = gameDir.resolve(TOWN_NAMES_FILE);

            if (!Files.exists(saveFile)) {
                System.out.println("DEBUG: No town names save file found, starting fresh");
                return;
            }

            // Read from file
            try (FileReader reader = new FileReader(saveFile.toFile())) {
                Type type = new TypeToken<Map<String, SerializableTownName>>(){}.getType();
                Map<String, SerializableTownName> serializableData = gson.fromJson(reader, type);

                if (serializableData != null) {
                    // Convert back to runtime format
                    townNames.clear();
                    townPopulations.clear();
                    for (SerializableTownName data : serializableData.values()) {
                        townNames.put(data.getBlockPos(), data.name);
                        townPopulations.put(data.getBlockPos(), data.population);
                    }

                    System.out.println("DEBUG: Loaded " + townNames.size() + " town entries (names and populations) from " + TOWN_NAMES_FILE);
                }
            }

        } catch (Exception e) {
            System.err.println("DEBUG: Failed to load town names: " + e.getMessage());
            System.err.println("DEBUG: Starting with empty town names");
        }
    }

    /**
     * Event handler for when a world loads - restore saved town names
     */
    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel().isClientSide()) {
            System.out.println("DEBUG: World loaded on client, loading town names");
            loadTownNames();
        }
    }

    /**
     * Event handler for when a world unloads - save current town names
     */
    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) {
            System.out.println("DEBUG: World unloading on client, saving town names");
            saveTownNames();
        }
    }
}
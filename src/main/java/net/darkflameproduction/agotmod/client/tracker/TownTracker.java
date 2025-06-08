package net.darkflameproduction.agotmod.client.tracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

        for (Map.Entry<BlockPos, TownHallDebugRenderer.TownHallData> entry : townData.entrySet()) {
            BlockPos townHallPos = entry.getKey();
            TownHallDebugRenderer.TownHallData data = entry.getValue();

            if (isPlayerInTown(playerPos, townHallPos, data.radius)) {
                townInside = townHallPos;
                townNameInside = townNames.getOrDefault(townHallPos, "Unknown Town");
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

        if (townChanged) {
            // Update current town status but don't show notifications
            // The ClientTownAreaManager handles notifications now
            currentTownPos = townInside;
            currentTownName = townNameInside;

            // Optional: Log town changes for debugging
            if (wasInTown && !isInTown) {
                System.out.println("DEBUG: TownTracker - Player left town: " + currentTownName);
            } else if (!wasInTown && isInTown) {
                System.out.println("DEBUG: TownTracker - Player entered town: " + townNameInside);
            } else if (wasInTown && isInTown && townChanged) {
                System.out.println("DEBUG: TownTracker - Player moved from " + currentTownName + " to " + townNameInside);
            }
        }
    }

    /**
     * Check if player is within town boundaries
     */
    private static boolean isPlayerInTown(BlockPos playerPos, BlockPos townHallPos, int radius) {
        int dx = Math.abs(playerPos.getX() - townHallPos.getX());
        int dy = Math.abs(playerPos.getY() - townHallPos.getY());
        int dz = Math.abs(playerPos.getZ() - townHallPos.getZ());

        return dx <= radius && dy <= SCAN_HEIGHT && dz <= radius;
    }

    /**
     * Get town hall data from the debug renderer
     */
    private static Map<BlockPos, TownHallDebugRenderer.TownHallData> getTownHallData() {
        return TownHallDebugRenderer.getTownHallDataMap();
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
     * Get the current town name (for external access)
     */
    public static String getCurrentTownName() {
        return currentTownName;
    }

    /**
     * Check if player is currently in a town (for external access)
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

            System.out.println("DEBUG: TownTracker saved " + townNames.size() + " towns to file");

        } catch (IOException e) {
            System.err.println("ERROR: TownTracker failed to save town data: " + e.getMessage());
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
                System.out.println("DEBUG: TownTracker - No saved town names file found");
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

                    System.out.println("DEBUG: TownTracker loaded " + townNames.size() + " towns from file");
                }
            }

        } catch (Exception e) {
            System.err.println("ERROR: TownTracker failed to load town data: " + e.getMessage());
        }
    }

    /**
     * Event handler for when a world loads - restore saved town names
     */
    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel().isClientSide()) {
            loadTownNames();
            // Reset current state
            currentTownPos = null;
            currentTownName = "";
        }
    }

    /**
     * Event handler for when a world unloads - save current town names
     */
    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) {
            saveTownNames();
            // Reset state
            currentTownPos = null;
            currentTownName = "";
        }
    }
}
package net.darkflameproduction.agotmod.client.town;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.darkflameproduction.agotmod.client.overlay.TownNotificationOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = "agotmod", bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientTownAreaManager {

    private static final String SAVE_FILE = "client_town_areas.json";
    private static final int CHECK_INTERVAL = 20; // Check every 20 ticks (1 second)
    private static final int SCAN_HEIGHT = 64; // Same as server

    // Town data storage
    private static final Map<BlockPos, TownAreaData> townAreas = new ConcurrentHashMap<>();

    // Current player state
    private static BlockPos currentTownPos = null;
    private static String currentTownName = null;
    private static int tickCounter = 0;

    // Gson for serialization
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Data class for town areas
    public static class TownAreaData {
        public final String townName;
        public final boolean isClaimed;
        public final String claimedByHouse;
        public final int population;
        public final int radius;
        public final long lastUpdated;

        public TownAreaData(String townName, boolean isClaimed, String claimedByHouse, int population, int radius) {
            this.townName = townName;
            this.isClaimed = isClaimed;
            this.claimedByHouse = claimedByHouse;
            this.population = population;
            this.radius = radius;
            this.lastUpdated = System.currentTimeMillis();
        }

        public TownAreaData(String townName, boolean isClaimed, String claimedByHouse, int population, int radius, long lastUpdated) {
            this.townName = townName;
            this.isClaimed = isClaimed;
            this.claimedByHouse = claimedByHouse;
            this.population = population;
            this.radius = radius;
            this.lastUpdated = lastUpdated;
        }
    }

    // Serializable version for JSON
    public static class SerializableTownData {
        public int x, y, z;
        public String townName;
        public boolean isClaimed;
        public String claimedByHouse;
        public int population;
        public int radius;
        public long lastUpdated;

        public SerializableTownData(BlockPos pos, TownAreaData data) {
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            this.townName = data.townName;
            this.isClaimed = data.isClaimed;
            this.claimedByHouse = data.claimedByHouse;
            this.population = data.population;
            this.radius = data.radius;
            this.lastUpdated = data.lastUpdated;
        }

        public BlockPos getBlockPos() {
            return new BlockPos(x, y, z);
        }

        public TownAreaData getTownAreaData() {
            return new TownAreaData(townName, isClaimed, claimedByHouse, population, radius, lastUpdated);
        }
    }

    /**
     * Update town data from server packet
     */
    public static void updateTownArea(BlockPos pos, String townName, boolean isClaimed, String claimedByHouse, int population, int radius) {
        TownAreaData newData = new TownAreaData(townName, isClaimed, claimedByHouse, population, radius);
        townAreas.put(pos, newData);

        System.out.println("DEBUG: ClientTownAreaManager updated town: " + townName +
                " at " + pos + ", claimed: " + isClaimed + ", house: " + claimedByHouse +
                ", population: " + population + ", radius: " + radius);

        saveTownData();
    }

    /**
     * Remove town area
     */
    public static void removeTownArea(BlockPos pos) {
        TownAreaData removed = townAreas.get(pos);

        if (removed != null) {
            // Check if the player is currently in this town that's being deleted
            if (pos.equals(currentTownPos)) {
                // Player is in the town being deleted - show exit message
                TownNotificationOverlay.showExitMessage(removed.townName);
                System.out.println("DEBUG: Player was in deleted town " + removed.townName + ", showing exit message");

                // Clear current town status
                currentTownPos = null;
                currentTownName = null;
            }

            // Remove the town from our data
            townAreas.remove(pos);
            System.out.println("DEBUG: ClientTownAreaManager removed town: " + removed.townName + " at " + pos);
            saveTownData();
        } else {
            System.out.println("DEBUG: Attempted to remove town at " + pos + " but it wasn't found in ClientTownAreaManager");
        }
    }

    /**
     * Force clear current town status (useful for debugging or cleanup)
     */
    public static void clearCurrentTownStatus() {
        if (currentTownPos != null && currentTownName != null) {
            TownNotificationOverlay.showExitMessage(currentTownName);
            System.out.println("DEBUG: Force clearing current town status: " + currentTownName);
        }

        currentTownPos = null;
        currentTownName = null;
        TownNotificationOverlay.clearMessage();
    }

    /**
     * Check if a specific town position exists in our data
     */
    public static boolean hasTownAt(BlockPos pos) {
        return townAreas.containsKey(pos);
    }

    /**
     * Get town data for a specific position
     */
    public static TownAreaData getTownDataAt(BlockPos pos) {
        return townAreas.get(pos);
    }

    /**
     * Validate current player status against known towns (for debugging)
     */
    public static void validatePlayerStatus() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null) return;

        BlockPos playerPos = player.blockPosition();

        // Check if current town position is still valid
        if (currentTownPos != null) {
            TownAreaData currentTownData = townAreas.get(currentTownPos);

            if (currentTownData == null) {
                // Current town no longer exists
                System.out.println("DEBUG: Current town " + currentTownName + " no longer exists, clearing status");
                TownNotificationOverlay.showExitMessage(currentTownName != null ? currentTownName : "Unknown Town");
                currentTownPos = null;
                currentTownName = null;
            } else if (!isPlayerInTownArea(playerPos, currentTownPos, currentTownData.radius)) {
                // Player is no longer in the current town area
                System.out.println("DEBUG: Player no longer in current town " + currentTownName + ", clearing status");
                TownNotificationOverlay.showExitMessage(currentTownName);
                currentTownPos = null;
                currentTownName = null;
            }
        }
    }

    /**
     * Get all town areas (for debugging)
     */
    public static Map<BlockPos, TownAreaData> getAllTownAreas() {
        return new HashMap<>(townAreas);
    }

    /**
     * Client tick event - check for town entry/exit
     */
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        tickCounter++;
        if (tickCounter >= CHECK_INTERVAL) {
            tickCounter = 0;

            // Every 5 seconds, run a validation check
            if ((tickCounter / CHECK_INTERVAL) % 5 == 0) {
                validatePlayerStatus();
            }

            checkPlayerTownStatus();
        }
    }

    /**
     * Check if player has entered or left a town
     */
    private static void checkPlayerTownStatus() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || townAreas.isEmpty()) {
            return;
        }

        BlockPos playerPos = player.blockPosition();
        BlockPos foundTownPos = null;
        TownAreaData foundTownData = null;

        // Check if player is in any town area
        for (Map.Entry<BlockPos, TownAreaData> entry : townAreas.entrySet()) {
            BlockPos townHallPos = entry.getKey();
            TownAreaData townData = entry.getValue();

            if (isPlayerInTownArea(playerPos, townHallPos, townData.radius)) {
                foundTownPos = townHallPos;
                foundTownData = townData;
                break; // Found a town, no need to check others
            }
        }

        // Check for state changes
        if (foundTownPos != null && !foundTownPos.equals(currentTownPos)) {
            // Player entered a new town
            if (currentTownPos != null) {
                // Player left previous town and entered new one
                TownAreaData oldTownData = townAreas.get(currentTownPos);
                if (oldTownData != null) {
                    TownNotificationOverlay.showExitMessage(oldTownData.townName);
                }
            }

            // Show entry message for new town
            String houseName = foundTownData.isClaimed ? foundTownData.claimedByHouse : null;
            TownNotificationOverlay.showEntryMessage(foundTownData.townName, houseName, foundTownData.population);

            currentTownPos = foundTownPos;
            currentTownName = foundTownData.townName;

            System.out.println("DEBUG: Player entered town: " + foundTownData.townName +
                    " (House: " + (houseName != null ? houseName : "Unclaimed") +
                    ", Population: " + foundTownData.population + ")");

        } else if (foundTownPos == null && currentTownPos != null) {
            // Player left town
            TownNotificationOverlay.showExitMessage(currentTownName);

            System.out.println("DEBUG: Player left town: " + currentTownName);

            currentTownPos = null;
            currentTownName = null;
        }
    }

    /**
     * Check if player is within a town's area
     */
    private static boolean isPlayerInTownArea(BlockPos playerPos, BlockPos townHallPos, int radius) {
        int dx = Math.abs(playerPos.getX() - townHallPos.getX());
        int dy = Math.abs(playerPos.getY() - townHallPos.getY());
        int dz = Math.abs(playerPos.getZ() - townHallPos.getZ());

        return dx <= radius && dy <= SCAN_HEIGHT && dz <= radius;
    }

    /**
     * Save town data to file
     */
    private static void saveTownData() {
        try {
            Path gameDir = Paths.get(".");
            Path saveFile = gameDir.resolve(SAVE_FILE);

            Map<String, SerializableTownData> serializableData = new HashMap<>();
            for (Map.Entry<BlockPos, TownAreaData> entry : townAreas.entrySet()) {
                String key = entry.getKey().getX() + "," + entry.getKey().getY() + "," + entry.getKey().getZ();
                serializableData.put(key, new SerializableTownData(entry.getKey(), entry.getValue()));
            }

            try (FileWriter writer = new FileWriter(saveFile.toFile())) {
                gson.toJson(serializableData, writer);
            }

            System.out.println("DEBUG: Saved " + townAreas.size() + " town areas to file");

        } catch (IOException e) {
            System.err.println("ERROR: Failed to save town data: " + e.getMessage());
        }
    }

    /**
     * Load town data from file
     */
    private static void loadTownData() {
        try {
            Path gameDir = Paths.get(".");
            Path saveFile = gameDir.resolve(SAVE_FILE);

            if (!Files.exists(saveFile)) {
                System.out.println("DEBUG: No saved town data file found");
                return;
            }

            try (FileReader reader = new FileReader(saveFile.toFile())) {
                Type type = new TypeToken<Map<String, SerializableTownData>>(){}.getType();
                Map<String, SerializableTownData> serializableData = gson.fromJson(reader, type);

                if (serializableData != null) {
                    townAreas.clear();
                    for (SerializableTownData data : serializableData.values()) {
                        townAreas.put(data.getBlockPos(), data.getTownAreaData());
                    }

                    System.out.println("DEBUG: Loaded " + townAreas.size() + " town areas from file");
                }
            }

        } catch (Exception e) {
            System.err.println("ERROR: Failed to load town data: " + e.getMessage());
        }
    }

    /**
     * World load event - load saved data
     */
    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel().isClientSide()) {
            loadTownData();
            // Reset current state
            currentTownPos = null;
            currentTownName = null;
        }
    }

    /**
     * World unload event - save data and reset state
     */
    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) {
            saveTownData();
            // Reset state
            currentTownPos = null;
            currentTownName = null;
            TownNotificationOverlay.clearMessage();
        }
    }

    /**
     * Get current town info (for debugging)
     */
    public static String getCurrentTownInfo() {
        if (currentTownPos == null) {
            return "Not in any town";
        }
        TownAreaData data = townAreas.get(currentTownPos);
        if (data == null) {
            return "In unknown town";
        }
        return "In " + data.townName + " (House: " + (data.isClaimed ? data.claimedByHouse : "Unclaimed") + ")";
    }
}
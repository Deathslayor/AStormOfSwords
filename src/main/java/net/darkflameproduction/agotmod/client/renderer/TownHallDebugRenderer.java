package net.darkflameproduction.agotmod.client.renderer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.darkflameproduction.agotmod.AGoTMod;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class TownHallDebugRenderer {

    private static final int SCAN_HEIGHT = 384; // Updated height
    private static final String SAVE_FILE = "townhall_debug_data.json";

    // Thread-safe map to store Town Hall positions with their current radius
    private static final Map<BlockPos, TownHallData> townHallDataMap = new ConcurrentHashMap<>();

    // Add a pending updates queue to handle rapid updates more smoothly
    private static final Map<BlockPos, TownHallData> pendingUpdates = new ConcurrentHashMap<>();
    private static int updateTick = 0;

    // Gson instance for JSON serialization
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Data class to store Town Hall information with timestamp
    public static class TownHallData {
        public final int radius;
        public final int citizenCount;
        public final int bedCount;
        public final long timestamp; // Add timestamp to track freshness

        public TownHallData(int radius, int citizenCount, int bedCount) {
            this.radius = radius;
            this.citizenCount = citizenCount;
            this.bedCount = bedCount;
            this.timestamp = System.currentTimeMillis();
        }

        public TownHallData(int radius, int citizenCount, int bedCount, long timestamp) {
            this.radius = radius;
            this.citizenCount = citizenCount;
            this.bedCount = bedCount;
            this.timestamp = timestamp;
        }
    }

    // Serializable version for JSON storage
    public static class SerializableTownHallData {
        public int x, y, z; // BlockPos coordinates
        public int radius;
        public int citizenCount;
        public int bedCount;
        public long timestamp;

        public SerializableTownHallData(BlockPos pos, TownHallData data) {
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            this.radius = data.radius;
            this.citizenCount = data.citizenCount;
            this.bedCount = data.bedCount;
            this.timestamp = data.timestamp;
        }

        public BlockPos getBlockPos() {
            return new BlockPos(x, y, z);
        }

        public TownHallData getTownHallData() {
            return new TownHallData(radius, citizenCount, bedCount, timestamp);
        }
    }

    // Static methods for Town Hall blocks to register/unregister themselves
    public static void addTownHall(BlockPos pos) {
        // Add with default values - but don't overwrite existing data if it exists
        if (!townHallDataMap.containsKey(pos)) {
            TownHallData defaultData = new TownHallData(16, 0, 0);
            townHallDataMap.put(pos, defaultData);
            saveTownHallData(); // Save immediately when new town hall is added
        }
    }

    public static void removeTownHall(BlockPos pos) {
        townHallDataMap.remove(pos);
        pendingUpdates.remove(pos); // Also remove from pending updates
        saveTownHallData(); // Save immediately when town hall is removed
    }

    // Method to update Town Hall data from network packets - now with timestamp checking
    public static void updateTownHallData(BlockPos pos, int bedCount, int citizenCount, int currentRadius) {
        long currentTime = System.currentTimeMillis();
        TownHallData existingData = townHallDataMap.get(pos);

        // Only accept updates that are newer than existing data or if no data exists
        if (existingData == null || currentTime > existingData.timestamp) {
            TownHallData newData = new TownHallData(currentRadius, citizenCount, bedCount, currentTime);
            // Store in pending updates with timestamp
            pendingUpdates.put(pos, newData);
        }
    }

    // Process pending updates gradually to avoid flicker
    private static void processPendingUpdates() {
        updateTick++;

        // Process updates every 2 ticks (10 times per second) for more responsive updates
        if (updateTick % 2 == 0 && !pendingUpdates.isEmpty()) {
            boolean dataChanged = false;

            for (Map.Entry<BlockPos, TownHallData> entry : pendingUpdates.entrySet()) {
                BlockPos pos = entry.getKey();
                TownHallData newData = entry.getValue();

                TownHallData oldData = townHallDataMap.get(pos);

                // Only update if this is newer data and actually different
                if (oldData == null ||
                        newData.timestamp >= oldData.timestamp &&
                                (oldData.radius != newData.radius ||
                                        oldData.citizenCount != newData.citizenCount ||
                                        oldData.bedCount != newData.bedCount)) {

                    townHallDataMap.put(pos, newData);
                    dataChanged = true;
                }
            }
            pendingUpdates.clear();

            // Save data if anything changed
            if (dataChanged) {
                saveTownHallData();
            }
        }
    }

    /**
     * Save town hall data to file
     */
    private static void saveTownHallData() {
        try {
            Path gameDir = Paths.get(".");
            Path saveFile = gameDir.resolve(SAVE_FILE);

            // Convert to serializable format
            Map<String, SerializableTownHallData> serializableData = new HashMap<>();
            for (Map.Entry<BlockPos, TownHallData> entry : townHallDataMap.entrySet()) {
                String key = entry.getKey().getX() + "," + entry.getKey().getY() + "," + entry.getKey().getZ();
                serializableData.put(key, new SerializableTownHallData(entry.getKey(), entry.getValue()));
            }

            // Write to file
            try (FileWriter writer = new FileWriter(saveFile.toFile())) {
                gson.toJson(serializableData, writer);
            }

        } catch (IOException e) {
            // Failed to save data
        }
    }

    /**
     * Load town hall data from file
     */
    private static void loadTownHallData() {
        try {
            Path gameDir = Paths.get(".");
            Path saveFile = gameDir.resolve(SAVE_FILE);

            if (!Files.exists(saveFile)) {
                return;
            }

            // Read from file
            try (FileReader reader = new FileReader(saveFile.toFile())) {
                Type type = new TypeToken<Map<String, SerializableTownHallData>>(){}.getType();
                Map<String, SerializableTownHallData> serializableData = gson.fromJson(reader, type);

                if (serializableData != null) {
                    // Convert back to runtime format
                    townHallDataMap.clear();
                    for (SerializableTownHallData data : serializableData.values()) {
                        townHallDataMap.put(data.getBlockPos(), data.getTownHallData());
                    }
                }
            }

        } catch (Exception e) {
            // Failed to load data
        }
    }

    /**
     * Get a copy of the current town hall data map (for external access)
     */
    public static Map<BlockPos, TownHallData> getTownHallDataMap() {
        return new HashMap<>(townHallDataMap);
    }

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel().isClientSide()) {
            loadTownHallData();
        }
    }

    /**
     * Event handler for when a world unloads - save current data
     */
    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) {
            saveTownHallData();
        }
    }

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        // Only render when F3 debug info is shown
        if (!mc.getDebugOverlay().showDebugScreen()) {
            return;
        }

        if (mc.player == null || mc.player.level() == null) {
            return;
        }

        // Process pending updates to smooth out changes
        processPendingUpdates();

        Vec3 cameraPos = event.getCamera().getPosition();
        PoseStack poseStack = event.getPoseStack();

        // Get buffer source from Minecraft's render buffers
        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();

        // Render all registered Town Hall bounds with their dynamic radius
        for (Map.Entry<BlockPos, TownHallData> entry : townHallDataMap.entrySet()) {
            BlockPos townHallPos = entry.getKey();
            TownHallData data = entry.getValue();

            // Only render if within reasonable distance to avoid performance issues
            double distance = cameraPos.distanceToSqr(townHallPos.getX(), townHallPos.getY(), townHallPos.getZ());
            if (distance < 512 * 512) { // Render within 512 blocks
                renderTownHallBounds(poseStack, bufferSource, townHallPos, data, cameraPos);
            }
        }

        // End the batch rendering
        bufferSource.endBatch();
    }

    private static void renderTownHallBounds(PoseStack poseStack, MultiBufferSource bufferSource,
                                             BlockPos townHallPos, TownHallData data, Vec3 cameraPos) {

        poseStack.pushPose();

        // Translate to world position relative to camera
        poseStack.translate(
                townHallPos.getX() - cameraPos.x,
                townHallPos.getY() - cameraPos.y,
                townHallPos.getZ() - cameraPos.z
        );

        // Use the dynamic radius from the data
        int currentRadius = data.radius;

        // Calculate the scan bounds using dynamic radius
        double minX = -currentRadius;
        double maxX = currentRadius + 1; // +1 to include the edge blocks
        double minY = -SCAN_HEIGHT;
        double maxY = SCAN_HEIGHT + 1;
        double minZ = -currentRadius;
        double maxZ = currentRadius + 1;

        // Check if player is inside the scan area
        Minecraft mc = Minecraft.getInstance();
        boolean isInside = false;
        if (mc.player != null) {
            double playerRelX = mc.player.getX() - townHallPos.getX();
            double playerRelY = mc.player.getY() - townHallPos.getY();
            double playerRelZ = mc.player.getZ() - townHallPos.getZ();

            isInside = playerRelX >= minX && playerRelX <= maxX &&
                    playerRelY >= minY && playerRelY <= maxY &&
                    playerRelZ >= minZ && playerRelZ <= maxZ;
        }

        // Use debug quad renderer which handles visibility better
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.debugQuads());

        // Simple green/red coloring based on player position
        float red, green, blue;
        if (isInside) {
            // Inside - Green
            red = 0.0f; green = 1.0f; blue = 0.0f;
        } else {
            // Outside - Red
            red = 1.0f; green = 0.0f; blue = 0.0f;
        }

        // Render grid pattern instead of wireframe
        renderGridBounds(poseStack, consumer, minX, maxX, minY, maxY, minZ, maxZ, red, green, blue, 0.8f);

        poseStack.popPose();
    }

    private static void renderGridBounds(PoseStack poseStack, VertexConsumer consumer,
                                         double minX, double maxX, double minY, double maxY, double minZ, double maxZ,
                                         float red, float green, float blue, float alpha) {

        float lineWidth = 0.04f;
        int gridSpacing = 16; // Grid lines every 16 blocks

        // Render horizontal grid lines on bottom face
        for (double x = Math.ceil(minX / gridSpacing) * gridSpacing; x <= maxX; x += gridSpacing) {
            addQuadLine(poseStack, consumer, (float)x, (float)minY, (float)minZ, (float)x, (float)minY, (float)maxZ, lineWidth, red, green, blue, alpha);
        }
        for (double z = Math.ceil(minZ / gridSpacing) * gridSpacing; z <= maxZ; z += gridSpacing) {
            addQuadLine(poseStack, consumer, (float)minX, (float)minY, (float)z, (float)maxX, (float)minY, (float)z, lineWidth, red, green, blue, alpha);
        }

        // Render horizontal grid lines on top face
        for (double x = Math.ceil(minX / gridSpacing) * gridSpacing; x <= maxX; x += gridSpacing) {
            addQuadLine(poseStack, consumer, (float)x, (float)maxY, (float)minZ, (float)x, (float)maxY, (float)maxZ, lineWidth, red, green, blue, alpha);
        }
        for (double z = Math.ceil(minZ / gridSpacing) * gridSpacing; z <= maxZ; z += gridSpacing) {
            addQuadLine(poseStack, consumer, (float)minX, (float)maxY, (float)z, (float)maxX, (float)maxY, (float)z, lineWidth, red, green, blue, alpha);
        }

        // Render vertical grid lines on X faces
        for (double y = Math.ceil(minY / gridSpacing) * gridSpacing; y <= maxY; y += gridSpacing) {
            addQuadLine(poseStack, consumer, (float)minX, (float)y, (float)minZ, (float)minX, (float)y, (float)maxZ, lineWidth, red, green, blue, alpha);
            addQuadLine(poseStack, consumer, (float)maxX, (float)y, (float)minZ, (float)maxX, (float)y, (float)maxZ, lineWidth, red, green, blue, alpha);
        }
        for (double z = Math.ceil(minZ / gridSpacing) * gridSpacing; z <= maxZ; z += gridSpacing) {
            addQuadLine(poseStack, consumer, (float)minX, (float)minY, (float)z, (float)minX, (float)maxY, (float)z, lineWidth, red, green, blue, alpha);
            addQuadLine(poseStack, consumer, (float)maxX, (float)minY, (float)z, (float)maxX, (float)maxY, (float)z, lineWidth, red, green, blue, alpha);
        }

        // Render vertical grid lines on Z faces
        for (double y = Math.ceil(minY / gridSpacing) * gridSpacing; y <= maxY; y += gridSpacing) {
            addQuadLine(poseStack, consumer, (float)minX, (float)y, (float)minZ, (float)maxX, (float)y, (float)minZ, lineWidth, red, green, blue, alpha);
            addQuadLine(poseStack, consumer, (float)minX, (float)y, (float)maxZ, (float)maxX, (float)y, (float)maxZ, lineWidth, red, green, blue, alpha);
        }
        for (double x = Math.ceil(minX / gridSpacing) * gridSpacing; x <= maxX; x += gridSpacing) {
            addQuadLine(poseStack, consumer, (float)x, (float)minY, (float)minZ, (float)x, (float)maxY, (float)minZ, lineWidth, red, green, blue, alpha);
            addQuadLine(poseStack, consumer, (float)x, (float)minY, (float)maxZ, (float)x, (float)maxY, (float)maxZ, lineWidth, red, green, blue, alpha);
        }

        // Render boundary edges to clearly define the area
        // Bottom boundary
        addQuadLine(poseStack, consumer, (float)minX, (float)minY, (float)minZ, (float)maxX, (float)minY, (float)minZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)maxX, (float)minY, (float)minZ, (float)maxX, (float)minY, (float)maxZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)maxX, (float)minY, (float)maxZ, (float)minX, (float)minY, (float)maxZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)minX, (float)minY, (float)maxZ, (float)minX, (float)minY, (float)minZ, lineWidth * 2, red, green, blue, alpha);

        // Top boundary
        addQuadLine(poseStack, consumer, (float)minX, (float)maxY, (float)minZ, (float)maxX, (float)maxY, (float)minZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)maxX, (float)maxY, (float)minZ, (float)maxX, (float)maxY, (float)maxZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)maxX, (float)maxY, (float)maxZ, (float)minX, (float)maxY, (float)maxZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)minX, (float)maxY, (float)maxZ, (float)minX, (float)maxY, (float)minZ, lineWidth * 2, red, green, blue, alpha);

        // Vertical boundary edges
        addQuadLine(poseStack, consumer, (float)minX, (float)minY, (float)minZ, (float)minX, (float)maxY, (float)minZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)maxX, (float)minY, (float)minZ, (float)maxX, (float)maxY, (float)minZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)maxX, (float)minY, (float)maxZ, (float)maxX, (float)maxY, (float)maxZ, lineWidth * 2, red, green, blue, alpha);
        addQuadLine(poseStack, consumer, (float)minX, (float)minY, (float)maxZ, (float)minX, (float)maxY, (float)maxZ, lineWidth * 2, red, green, blue, alpha);
    }

    private static void addQuadLine(PoseStack poseStack, VertexConsumer consumer,
                                    float x1, float y1, float z1, float x2, float y2, float z2,
                                    float width, float red, float green, float blue, float alpha) {

        // Calculate direction vector
        float dx = x2 - x1;
        float dy = y2 - y1;
        float dz = z2 - z1;

        // Calculate two perpendicular vectors to create a cube cross-section
        float px1, py1, pz1; // First perpendicular
        float px2, py2, pz2; // Second perpendicular

        // Choose first perpendicular based on direction
        if (Math.abs(dx) > Math.abs(dy) && Math.abs(dx) > Math.abs(dz)) {
            // Line is mostly along X axis
            px1 = 0; py1 = width; pz1 = 0;
            px2 = 0; py2 = 0; pz2 = width;
        } else if (Math.abs(dy) > Math.abs(dz)) {
            // Line is mostly along Y axis
            px1 = width; py1 = 0; pz1 = 0;
            px2 = 0; py2 = 0; pz2 = width;
        } else {
            // Line is mostly along Z axis
            px1 = width; py1 = 0; pz1 = 0;
            px2 = 0; py2 = width; pz2 = 0;
        }

        // Create 4 faces of the cube "pipe"
        // Face 1: +px1, +px2
        addQuad(poseStack, consumer,
                x1 + px1 + px2, y1 + py1 + py2, z1 + pz1 + pz2,
                x2 + px1 + px2, y2 + py1 + py2, z2 + pz1 + pz2,
                x2 + px1 - px2, y2 + py1 - py2, z2 + pz1 - pz2,
                x1 + px1 - px2, y1 + py1 - py2, z1 + pz1 - pz2,
                red, green, blue, alpha);

        // Face 2: -px1, +px2
        addQuad(poseStack, consumer,
                x1 - px1 - px2, y1 - py1 - py2, z1 - pz1 - pz2,
                x1 - px1 + px2, y1 - py1 + py2, z1 - pz1 + pz2,
                x2 - px1 + px2, y2 - py1 + py2, z2 - pz1 + pz2,
                x2 - px1 - px2, y2 - py1 - py2, z2 - pz1 - pz2,
                red, green, blue, alpha);

        // Face 3: +px1 to -px1 (top)
        addQuad(poseStack, consumer,
                x1 + px1 + px2, y1 + py1 + py2, z1 + pz1 + pz2,
                x1 - px1 + px2, y1 - py1 + py2, z1 - pz1 + pz2,
                x2 - px1 + px2, y2 - py1 + py2, z2 - pz1 + pz2,
                x2 + px1 + px2, y2 + py1 + py2, z2 + pz1 + pz2,
                red, green, blue, alpha);

        // Face 4: +px1 to -px1 (bottom)
        addQuad(poseStack, consumer,
                x1 - px1 - px2, y1 - py1 - py2, z1 - pz1 - pz2,
                x1 + px1 - px2, y1 + py1 - py2, z1 + pz1 - pz2,
                x2 + px1 - px2, y2 + py1 - py2, z2 + pz1 - pz2,
                x2 - px1 - px2, y2 - py1 - py2, z2 - pz1 - pz2,
                red, green, blue, alpha);
    }

    private static void addQuad(PoseStack poseStack, VertexConsumer consumer,
                                float x1, float y1, float z1,
                                float x2, float y2, float z2,
                                float x3, float y3, float z3,
                                float x4, float y4, float z4,
                                float red, float green, float blue, float alpha) {
        consumer.addVertex(poseStack.last().pose(), x1, y1, z1).setColor(red, green, blue, alpha);
        consumer.addVertex(poseStack.last().pose(), x2, y2, z2).setColor(red, green, blue, alpha);
        consumer.addVertex(poseStack.last().pose(), x3, y3, z3).setColor(red, green, blue, alpha);
        consumer.addVertex(poseStack.last().pose(), x4, y4, z4).setColor(red, green, blue, alpha);
    }
}
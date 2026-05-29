package net.darkflameproduction.agotmod.client.town;

import net.darkflameproduction.agotmod.client.overlay.TownNotificationOverlay;
import net.darkflameproduction.agotmod.network.EnterTownPacket;
import net.darkflameproduction.agotmod.network.LeaveTownPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = "agotmod", bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientTownAreaManager {

    private static final int CHECK_INTERVAL = 20;
    private static final int SCAN_HEIGHT    = 64;

    private static final Map<BlockPos, TownAreaData> townAreas = new ConcurrentHashMap<>();

    private static BlockPos currentTownPos  = null;
    private static String   currentTownName = null;
    private static int      tickCounter     = 0;

    private static BlockPos     pendingEntryTownPos  = null;
    private static TownAreaData pendingEntryTownData = null;

    public static class TownAreaData {
        public final String  townName;
        public final boolean isClaimed;
        public final String  claimedByHouse;
        public final int     population;
        public final int     radius;

        public TownAreaData(String townName, boolean isClaimed, String claimedByHouse,
                            int population, int radius) {
            this.townName      = townName;
            this.isClaimed     = isClaimed;
            this.claimedByHouse = claimedByHouse;
            this.population    = population;
            this.radius        = radius;
        }
    }

    public static void updateTownArea(BlockPos pos, String townName, boolean isClaimed,
                                      String claimedByHouse, int population, int radius) {
        townAreas.put(pos, new TownAreaData(townName, isClaimed, claimedByHouse, population, radius));
        checkPlayerTownStatus();
    }

    public static void removeTownArea(BlockPos pos) {
        TownAreaData removed = townAreas.remove(pos);
        if (removed != null && pos.equals(currentTownPos)) {
            TownNotificationOverlay.showExitMessage(removed.townName);
            sendLeaveTownPacket();
            currentTownPos  = null;
            currentTownName = null;
        }
    }

    public static boolean      hasTownAt(BlockPos pos)    { return townAreas.containsKey(pos); }
    public static TownAreaData getTownDataAt(BlockPos pos) { return townAreas.get(pos); }
    public static Map<BlockPos, TownAreaData> getAllTownAreas() { return new HashMap<>(townAreas); }

    public static String getCurrentTownInfo() {
        if (currentTownPos == null) return "Not in any town";
        TownAreaData data = townAreas.get(currentTownPos);
        if (data == null) return "In unknown town";
        return "In " + data.townName + " (House: " + (data.isClaimed ? data.claimedByHouse : "Unclaimed") + ")";
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        tickCounter++;
        if (tickCounter >= CHECK_INTERVAL) {
            tickCounter = 0;
            checkPlayerTownStatus();
        }
    }

    private static void checkPlayerTownStatus() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        BlockPos playerPos    = player.blockPosition();
        BlockPos foundTownPos = null;
        TownAreaData foundTownData = null;

        for (Map.Entry<BlockPos, TownAreaData> entry : townAreas.entrySet()) {
            if (isPlayerInTownArea(playerPos, entry.getKey(), entry.getValue().radius)) {
                foundTownPos  = entry.getKey();
                foundTownData = entry.getValue();
                break;
            }
        }

        if (foundTownPos != null && !foundTownPos.equals(currentTownPos)) {
            // Leaving old town (if any)
            if (currentTownPos != null) {
                TownAreaData oldData = townAreas.get(currentTownPos);
                TownNotificationOverlay.showExitMessage(oldData != null ? oldData.townName : currentTownName);
                sendLeaveTownPacket();

                pendingEntryTownPos  = foundTownPos;
                pendingEntryTownData = foundTownData;
                currentTownPos  = foundTownPos;
                currentTownName = foundTownData.townName;
                return;
            }

            // Entering new town
            String ownerDisplay = foundTownData.isClaimed ? foundTownData.claimedByHouse : null;
            TownNotificationOverlay.showEntryMessage(foundTownData.townName, ownerDisplay, foundTownData.population);
            sendEnterTownPacket(foundTownData);
            currentTownPos  = foundTownPos;
            currentTownName = foundTownData.townName;

        } else if (foundTownPos == null && currentTownPos != null) {
            // Left all towns
            TownNotificationOverlay.showExitMessage(currentTownName);
            sendLeaveTownPacket();
            currentTownPos  = null;
            currentTownName = null;
        }

        // Deferred entry after town-to-town transition
        if (pendingEntryTownPos != null && pendingEntryTownData != null) {
            String ownerDisplay = pendingEntryTownData.isClaimed ? pendingEntryTownData.claimedByHouse : null;
            TownNotificationOverlay.showEntryMessage(pendingEntryTownData.townName, ownerDisplay, pendingEntryTownData.population);
            sendEnterTownPacket(pendingEntryTownData);
            pendingEntryTownPos  = null;
            pendingEntryTownData = null;
        }
    }

    private static void sendEnterTownPacket(TownAreaData data) {
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                new EnterTownPacket(data.isClaimed, data.claimedByHouse != null ? data.claimedByHouse : ""));
    }

    private static void sendLeaveTownPacket() {
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new LeaveTownPacket());
    }

    private static boolean isPlayerInTownArea(BlockPos playerPos, BlockPos townHallPos, int radius) {
        return Math.abs(playerPos.getX() - townHallPos.getX()) <= radius
                && Math.abs(playerPos.getY() - townHallPos.getY()) <= SCAN_HEIGHT
                && Math.abs(playerPos.getZ() - townHallPos.getZ()) <= radius;
    }

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if (event.getLevel().isClientSide()) {
            townAreas.clear();
            currentTownPos       = null;
            currentTownName      = null;
            pendingEntryTownPos  = null;
            pendingEntryTownData = null;
            TownNotificationOverlay.clearMessage();
        }
    }

    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) {
            townAreas.clear();
            currentTownPos       = null;
            currentTownName      = null;
            pendingEntryTownPos  = null;
            pendingEntryTownData = null;
            TownNotificationOverlay.clearMessage();
        }
    }
}

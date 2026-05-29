package net.darkflameproduction.agotmod.entity.custom.npc.system.culture;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class TownCultureZone {

    public static class ZoneEntry {
        public final BlockPos           townHallPos;
        public final int                radius;
        public final Culture            culture;
        public final ResourceKey<Level> dimension;

        public ZoneEntry(BlockPos pos, int radius, Culture culture, ResourceKey<Level> dimension) {
            this.townHallPos = pos;
            this.radius      = radius;
            this.culture     = culture;
            this.dimension   = dimension;
        }

        public boolean contains(BlockPos pos, ResourceKey<Level> dim) {
            if (!this.dimension.equals(dim)) return false;
            int dx = Math.abs(pos.getX() - townHallPos.getX());
            int dz = Math.abs(pos.getZ() - townHallPos.getZ());
            return dx <= radius && dz <= radius;
        }
    }

    private static final Map<String, ZoneEntry> ZONES = new ConcurrentHashMap<>();

    // ── Registration ──────────────────────────────────────────────────────────

    public static void registerZone(BlockPos pos, int radius, Culture culture, ResourceKey<Level> dimension) {
        if (culture == Culture.NONE) { removeZone(pos, dimension); return; }
        String key = dimension.location() + ":" + pos.asLong();
        ZONES.put(key, new ZoneEntry(pos.immutable(), radius, culture, dimension));
        AGoTMod.LOGGER.info("[TownCultureZone] Registered zone at {} radius={} culture={} zones={}",
                pos, radius, culture.name(), ZONES.size());
    }

    public static void removeZone(BlockPos pos, ResourceKey<Level> dimension) {
        String key = dimension.location() + ":" + pos.asLong();
        ZONES.remove(key);
        AGoTMod.LOGGER.info("[TownCultureZone] Removed zone at {}", pos);
    }

    public static void clearAll() { ZONES.clear(); }

    // ── Lookup ────────────────────────────────────────────────────────────────

    public static Culture findCultureAtPos(BlockPos pos, ResourceKey<Level> dimension) {
        for (ZoneEntry zone : ZONES.values()) {
            if (zone.contains(pos, dimension)) return zone.culture;
        }
        return Culture.NONE;
    }

    // ── Spawn intercept ───────────────────────────────────────────────────────

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof Peasant_Entity peasant)) return;
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (peasant.getCultureSystem().hasCulture()) {
            // Already cultured (e.g. loaded from NBT) — just sync to clients
            peasant.syncCultureToClients();
            return;
        }

        Culture culture = findCultureAtPos(peasant.blockPosition(), level.dimension());
        if (culture == Culture.NONE) culture = Culture.GENERIC_NORTH;

        peasant.getCultureSystem().assignCulture(
                culture,
                level.getRandom(),
                peasant.isFemale(),
                peasant.isChild()
        );
        peasant.getNameSystem().generateRandomName(level.getRandom());

        // Push all culture data to clients via SynchedEntityData
        peasant.syncCultureToClients();

        AGoTMod.LOGGER.info("[TownCultureZone] Assigned {} to {} | body={} hair={} | name={}",
                culture.name(), peasant.getUUID(),
                peasant.getCultureSystem().getBodyVariant(),
                peasant.getCultureSystem().getHairVariant(),
                peasant.hasCustomName() ? peasant.getCustomName().getString() : "none");
    }
}

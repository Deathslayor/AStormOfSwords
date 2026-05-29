package net.darkflameproduction.agotmod.entity.custom.npc.system.protection;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TownProtectionZone {

    public static class ZoneEntry {
        public final BlockPos           townHallPos;
        public final int                radius;
        /** null = unclaimed */
        public final UUID               ownerHouseUUID;
        public final ResourceKey<Level> dimension;

        ZoneEntry(BlockPos pos, int radius, UUID ownerHouseUUID, ResourceKey<Level> dimension) {
            this.townHallPos    = pos.immutable();
            this.radius         = radius;
            this.ownerHouseUUID = ownerHouseUUID;
            this.dimension      = dimension;
        }

        public boolean contains(BlockPos pos, ResourceKey<Level> dim) {
            if (!this.dimension.equals(dim)) return false;
            return Math.abs(pos.getX() - townHallPos.getX()) <= radius
                    && Math.abs(pos.getZ() - townHallPos.getZ()) <= radius;
        }
    }

    private static final Map<String, ZoneEntry> ZONES = new ConcurrentHashMap<>();

    private static String key(BlockPos pos, ResourceKey<Level> dim) {
        return dim.location() + ":" + pos.asLong();
    }

    public static void registerZone(BlockPos townHallPos, int radius,
                                    UUID ownerHouseUUID, ResourceKey<Level> dimension) {
        ZONES.put(key(townHallPos, dimension),
                new ZoneEntry(townHallPos, radius, ownerHouseUUID, dimension));
    }

    public static void removeZone(BlockPos townHallPos, ResourceKey<Level> dimension) {
        ZONES.remove(key(townHallPos, dimension));
    }

    public static void clearAll() { ZONES.clear(); }

    public static ZoneEntry getZoneAt(BlockPos pos, ResourceKey<Level> dimension) {
        for (ZoneEntry zone : ZONES.values()) {
            if (zone.contains(pos, dimension)) return zone;
        }
        return null;
    }

    /** Exposed so EnterTownPacket can iterate all zones to find owner match. */
    public static Collection<ZoneEntry> getAllZones() {
        return ZONES.values();
    }
}

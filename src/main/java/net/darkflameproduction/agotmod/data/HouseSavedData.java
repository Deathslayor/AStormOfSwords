package net.darkflameproduction.agotmod.data;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.*;

public class HouseSavedData extends SavedData {

    private static final String DATA_NAME = AGoTMod.MOD_ID + "_houses";

    private final Map<UUID, HouseRecord> houses = new LinkedHashMap<>();

    public HouseSavedData() {}

    public static HouseSavedData get(MinecraftServer server) {
        DimensionDataStorage storage = server.overworld().getDataStorage();
        return storage.computeIfAbsent(
                new SavedData.Factory<>(
                        HouseSavedData::new,
                        (tag, provider) -> HouseSavedData.load(tag, provider)
                ),
                DATA_NAME
        );
    }

    private static HouseSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
        HouseSavedData data = new HouseSavedData();
        ListTag houseList = tag.getList("Houses", Tag.TAG_COMPOUND);
        for (int i = 0; i < houseList.size(); i++) {
            CompoundTag houseTag = houseList.getCompound(i);
            UUID houseUUID   = UUID.fromString(houseTag.getString("HouseUUID"));
            UUID founderUUID = houseTag.contains("FounderUUID")
                    ? UUID.fromString(houseTag.getString("FounderUUID")) : null;
            HouseRecord record = new HouseRecord(houseUUID, houseTag.getString("Name"), founderUUID);

            if (houseTag.contains("Banner")) {
                record.banner = houseTag.getCompound("Banner");
            }

            ListTag memberList = houseTag.getList("Members", Tag.TAG_COMPOUND);
            for (int j = 0; j < memberList.size(); j++) {
                CompoundTag memberTag = memberList.getCompound(j);
                record.members.add(new MemberRecord(
                        UUID.fromString(memberTag.getString("UUID")),
                        memberTag.getString("Username"),
                        memberTag.getLong("JoinTime")
                ));
            }
            data.houses.put(houseUUID, record);
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag houseList = new ListTag();
        for (HouseRecord record : houses.values()) {
            CompoundTag houseTag = new CompoundTag();
            houseTag.putString("HouseUUID", record.houseUUID.toString());
            houseTag.putString("Name",      record.name);
            if (record.founderUUID != null) {
                houseTag.putString("FounderUUID", record.founderUUID.toString());
            }
            if (record.banner != null) {
                houseTag.put("Banner", record.banner);
            }
            ListTag memberList = new ListTag();
            for (MemberRecord member : record.members) {
                CompoundTag memberTag = new CompoundTag();
                memberTag.putString("UUID",     member.uuid.toString());
                memberTag.putString("Username", member.username);
                memberTag.putLong("JoinTime",   member.joinTime);
                memberList.add(memberTag);
            }
            houseTag.put("Members", memberList);
            houseList.add(houseTag);
        }
        tag.put("Houses", houseList);
        return tag;
    }

    // ── Lookup ────────────────────────────────────────────────────────────────

    public HouseRecord getByUUID(UUID houseUUID) { return houses.get(houseUUID); }

    public HouseRecord getByName(String name) {
        for (HouseRecord r : houses.values()) {
            if (r.name.equalsIgnoreCase(name)) return r;
        }
        return null;
    }

    // ── Operations ────────────────────────────────────────────────────────────

    /** Creates a new house. Returns the new house UUID. */
    public UUID createHouse(String name, UUID founderUUID) {
        HouseRecord existing = getByName(name);
        if (existing != null) return existing.houseUUID;
        UUID id = UUID.randomUUID();
        houses.put(id, new HouseRecord(id, name, founderUUID));
        setDirty();
        return id;
    }

    public void renameHouse(UUID houseUUID, String newName) {
        HouseRecord r = houses.get(houseUUID);
        if (r == null) return;
        r.name = newName;
        setDirty();
    }

    public void addMember(UUID houseUUID, UUID playerUUID, String username) {
        HouseRecord r = houses.get(houseUUID);
        if (r == null) return;
        for (MemberRecord m : r.members) {
            if (m.uuid.equals(playerUUID)) { m.username = username; setDirty(); return; }
        }
        r.members.add(new MemberRecord(playerUUID, username, System.currentTimeMillis()));
        setDirty();
    }

    public void removeMember(UUID houseUUID, UUID playerUUID) {
        HouseRecord r = houses.get(houseUUID);
        if (r == null) return;
        r.members.removeIf(m -> m.uuid.equals(playerUUID));
        setDirty();
    }

    public void setBanner(UUID houseUUID, CompoundTag banner) {
        HouseRecord r = houses.get(houseUUID);
        if (r == null) return;
        r.banner = banner;
        setDirty();
    }

    public CompoundTag getBanner(UUID houseUUID) {
        HouseRecord r = houses.get(houseUUID);
        return r != null ? r.banner : null;
    }

    public List<MemberRecord> getMembers(UUID houseUUID) {
        HouseRecord r = houses.get(houseUUID);
        if (r == null) return Collections.emptyList();
        return Collections.unmodifiableList(r.members);
    }

    public String getHouseName(UUID houseUUID) {
        HouseRecord r = houses.get(houseUUID);
        return r != null ? r.name : "";
    }

    public UUID getFounderUUID(UUID houseUUID) {
        HouseRecord r = houses.get(houseUUID);
        return r != null ? r.founderUUID : null;
    }

    // ── Data classes ──────────────────────────────────────────────────────────

    public static class HouseRecord {
        public final UUID   houseUUID;
        public String       name;
        public final UUID   founderUUID;
        public CompoundTag  banner  = null;
        public List<MemberRecord> members = new ArrayList<>();

        HouseRecord(UUID houseUUID, String name, UUID founderUUID) {
            this.houseUUID   = houseUUID;
            this.name        = name;
            this.founderUUID = founderUUID;
        }
    }

    public static class MemberRecord {
        public UUID   uuid;
        public String username;
        public long   joinTime;

        MemberRecord(UUID uuid, String username, long joinTime) {
            this.uuid     = uuid;
            this.username = username;
            this.joinTime = joinTime;
        }
    }
}
package net.darkflameproduction.agotmod.gui;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.network.*;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class HouseData {

    // ── Static server-synced state ────────────────────────────────────────────
    private static String      syncedHouseName   = "";
    private static CompoundTag syncedHouseBanner = null;
    private static List<SyncOwnedTownsPacket.TownInfo> ownedTowns = new ArrayList<>();
    private static List<String> houseMembers     = new ArrayList<>();
    private static String      pendingInviteFrom = "";
    private static String      pendingInviteBy   = "";

    // ── Instance state (per screen open) ─────────────────────────────────────
    public String  houseName             = "";
    public boolean isEditingHouseName    = false;
    public boolean isValidatingHouseName = false;
    public boolean hasRequestedTowns     = false;

    // ── Static setters (called by ClientPacketHandler) ────────────────────────

    public static void setSyncedHouseName(String name)          { syncedHouseName = name != null ? name : ""; }
    public static void setSyncedHouseBanner(CompoundTag banner) { syncedHouseBanner = banner; }
    public static void setOwnedTowns(List<SyncOwnedTownsPacket.TownInfo> towns) { ownedTowns = new ArrayList<>(towns); }
    public static void setHouseMembers(List<String> members)    { houseMembers = new ArrayList<>(members); }

    public static void setPendingInvite(String houseName, String inviterName) {
        pendingInviteFrom = houseName  != null ? houseName  : "";
        pendingInviteBy   = inviterName != null ? inviterName : "";
    }

    // ── Static getters ────────────────────────────────────────────────────────

    public static String      getSyncedHouseName()   { return syncedHouseName; }
    public static CompoundTag getSyncedHouseBanner() { return syncedHouseBanner; }
    public static List<SyncOwnedTownsPacket.TownInfo> getOwnedTowns()  { return ownedTowns; }
    public static List<String> getHouseMembers()     { return houseMembers; }
    public static String      getPendingInviteFrom() { return pendingInviteFrom; }
    public static String      getPendingInviteBy()   { return pendingInviteBy; }
    public static boolean     hasPendingInvite()     { return !pendingInviteFrom.isEmpty(); }

    // ── Instance lifecycle ────────────────────────────────────────────────────

    public void load() {
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new RequestHouseNamePacket());
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new RequestHouseBannerPacket());
        houseName         = syncedHouseName;
        hasRequestedTowns = false;
    }

    public void tickPersist(Minecraft minecraft, PlayerSkillData skills) {
        if (minecraft == null || minecraft.player == null) return;
        CompoundTag data = minecraft.player.getPersistentData();

        CompoundTag skillsTag = new CompoundTag();
        skillsTag.putInt("one_handed_level",  skills.oneHandedLevel);
        skillsTag.putInt("two_handed_level",  skills.twoHandedLevel);
        skillsTag.putInt("polearm_level",     skills.polearmLevel);
        skillsTag.putInt("short_blade_level", skills.shortBladeLevel);
        skillsTag.putInt("ranged_level",      skills.rangedLevel);
        data.put(AGoTMod.MOD_ID + ".skills", skillsTag);

        CompoundTag houseTag = new CompoundTag();
        houseTag.putString("house_name", houseName);
        data.put(AGoTMod.MOD_ID + ".house", houseTag);
    }

    public void save(Minecraft minecraft) {
        if (minecraft == null || minecraft.player == null || isValidatingHouseName) return;
        String newName = houseName.trim();
        if (newName.isEmpty()) return;

        String currentName = "";
        CompoundTag data = minecraft.player.getPersistentData();
        if (data.contains(AGoTMod.MOD_ID + ".house")) {
            currentName = data.getCompound(AGoTMod.MOD_ID + ".house").getString("house_name");
        }

        CompoundTag houseTag = new CompoundTag();
        houseTag.putString("house_name", newName);
        data.put(AGoTMod.MOD_ID + ".house", houseTag);

        if (!newName.equals(currentName)) {
            isValidatingHouseName = true;
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new CheckHouseNamePacket(newName));
        }
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new SaveHouseNamePacket(newName));
    }

    public void onValidationResult(boolean isAvailable, String message) {
        isValidatingHouseName = false;
        if (!isAvailable) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                mc.player.displayClientMessage(
                        net.minecraft.network.chat.Component.literal(message), false);
            }
        }
    }
}
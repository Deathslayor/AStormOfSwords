package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.data.HouseSavedData;
import net.darkflameproduction.agotmod.data.LoreHouses;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerHouseHandler {

    private static final Map<UUID, String[]> pendingInvites = new ConcurrentHashMap<>();

    // ── Player house data helpers ─────────────────────────────────────────────

    public static UUID getPlayerHouseUUID(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();
        if (data.contains(AGoTMod.MOD_ID + ".house")) {
            CompoundTag tag = data.getCompound(AGoTMod.MOD_ID + ".house");
            if (tag.contains("house_uuid")) {
                try { return UUID.fromString(tag.getString("house_uuid")); }
                catch (Exception ignored) {}
            }
        }
        return null;
    }

    public static String getOnlinePlayerHouseName(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();
        if (data.contains(AGoTMod.MOD_ID + ".house")) {
            return data.getCompound(AGoTMod.MOD_ID + ".house").getString("house_name");
        }
        return "";
    }

    private static void setPlayerHouse(ServerPlayer player, UUID houseUUID, String houseName) {
        CompoundTag tag = new CompoundTag();
        tag.putString("house_name", houseName);
        tag.putString("house_uuid", houseUUID.toString());
        player.getPersistentData().put(AGoTMod.MOD_ID + ".house", tag);
    }

    private static void syncRole(ServerPlayer player, UUID houseUUID, HouseSavedData saved) {
        boolean isFounder = player.getUUID().equals(saved.getFounderUUID(houseUUID));
        PacketDistributor.sendToPlayer(player, new SyncHouseRolePacket(isFounder));
    }

    // ── Packet handlers ───────────────────────────────────────────────────────

    public static void handleSendHouseInvite(SendHouseInvitePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer inviter)) return;

            UUID houseUUID = getPlayerHouseUUID(inviter);
            if (houseUUID == null) {
                inviter.sendSystemMessage(Component.literal("You are not in a noble house."));
                return;
            }

            // Only the founder can send invites
            HouseSavedData saved = HouseSavedData.get(inviter.getServer());
            if (!inviter.getUUID().equals(saved.getFounderUUID(houseUUID))) {
                inviter.sendSystemMessage(Component.literal("Only the house founder can invite players."));
                return;
            }

            String houseName = getOnlinePlayerHouseName(inviter);
            String targetUsername = packet.targetUsername().trim();
            if (targetUsername.isEmpty()) return;

            ServerPlayer target = inviter.getServer().getPlayerList().getPlayerByName(targetUsername);
            if (target == null) {
                inviter.sendSystemMessage(Component.literal("Player '" + targetUsername + "' is not online."));
                return;
            }
            if (target.getUUID().equals(inviter.getUUID())) {
                inviter.sendSystemMessage(Component.literal("You cannot invite yourself."));
                return;
            }
            if (getPlayerHouseUUID(target) != null) {
                inviter.sendSystemMessage(Component.literal(targetUsername + " is already in a noble house."));
                return;
            }
            if (pendingInvites.containsKey(target.getUUID())) {
                inviter.sendSystemMessage(Component.literal(targetUsername + " already has a pending house invite."));
                return;
            }

            pendingInvites.put(target.getUUID(),
                    new String[]{ houseUUID.toString(), houseName, inviter.getGameProfile().getName() });
            PacketDistributor.sendToPlayer(target,
                    new SyncHouseInvitePacket(houseName, inviter.getGameProfile().getName()));
            target.sendSystemMessage(Component.literal(
                    "You have been invited to join House " + houseName
                            + " by " + inviter.getGameProfile().getName()
                            + ". Open your House tab to respond."));
            inviter.sendSystemMessage(Component.literal("Invite sent to " + targetUsername + "."));
        });
    }

    public static void handleRespondHouseInvite(RespondHouseInvitePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer invitee)) return;

            String[] invite = pendingInvites.remove(invitee.getUUID());
            if (invite == null) {
                invitee.sendSystemMessage(Component.literal("You have no pending house invite."));
                return;
            }

            UUID   houseUUID   = UUID.fromString(invite[0]);
            String houseName   = invite[1];
            String inviterName = invite[2];

            PacketDistributor.sendToPlayer(invitee, new SyncHouseInvitePacket("", ""));

            if (packet.accepted()) {
                setPlayerHouse(invitee, houseUUID, houseName);

                HouseSavedData saved = HouseSavedData.get(invitee.getServer());
                saved.addMember(houseUUID, invitee.getUUID(), invitee.getGameProfile().getName());

                invitee.sendSystemMessage(Component.literal("You have joined House " + houseName + "!"));

                CompoundTag banner = saved.getBanner(houseUUID);
                PacketDistributor.sendToPlayer(invitee, new SyncHouseBannerPacket(banner));

                // New members are never founders
                PacketDistributor.sendToPlayer(invitee, new SyncHouseRolePacket(false));

                ServerPlayer inviter = invitee.getServer().getPlayerList().getPlayerByName(inviterName);
                if (inviter != null) {
                    inviter.sendSystemMessage(Component.literal(
                            invitee.getGameProfile().getName() + " has joined House " + houseName + "."));
                }
                syncMembersToAllHouseMembers(invitee.getServer(), houseUUID);
            } else {
                invitee.sendSystemMessage(Component.literal(
                        "You declined the invitation to House " + houseName + "."));
                ServerPlayer inviter = invitee.getServer().getPlayerList().getPlayerByName(inviterName);
                if (inviter != null) {
                    inviter.sendSystemMessage(Component.literal(
                            invitee.getGameProfile().getName() + " declined your house invitation."));
                }
            }
        });
    }

    // ── Member sync ───────────────────────────────────────────────────────────

    public static void syncMembersToAllHouseMembers(MinecraftServer server, UUID houseUUID) {
        HouseSavedData saved = HouseSavedData.get(server);
        List<HouseSavedData.MemberRecord> records = saved.getMembers(houseUUID);

        List<SyncHouseMembersPacket.MemberEntry> entries = new ArrayList<>();
        for (HouseSavedData.MemberRecord r : records) {
            entries.add(new SyncHouseMembersPacket.MemberEntry(r.username, r.uuid));
        }

        SyncHouseMembersPacket packet = new SyncHouseMembersPacket(entries);
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (houseUUID.equals(getPlayerHouseUUID(player))) {
                PacketDistributor.sendToPlayer(player, packet);
            }
        }
    }

    // ── Banner ────────────────────────────────────────────────────────────────

    public static void saveBannerForHouse(MinecraftServer server, UUID houseUUID,
                                          CompoundTag bannerData) {
        HouseSavedData saved = HouseSavedData.get(server);
        saved.setBanner(houseUUID, bannerData);

        SyncHouseBannerPacket packet = new SyncHouseBannerPacket(bannerData);
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (houseUUID.equals(getPlayerHouseUUID(player))) {
                PacketDistributor.sendToPlayer(player, packet);
            }
        }
    }

    // ── Player join / leave ───────────────────────────────────────────────────

    public static void onPlayerJoin(ServerPlayer player) {
        String[] invite = pendingInvites.get(player.getUUID());
        if (invite != null) {
            PacketDistributor.sendToPlayer(player,
                    new SyncHouseInvitePacket(invite[1], invite[2]));
        }

        UUID houseUUID = getPlayerHouseUUID(player);
        if (houseUUID != null) {
            HouseSavedData saved = HouseSavedData.get(player.getServer());
            saved.addMember(houseUUID, player.getUUID(), player.getGameProfile().getName());

            CompoundTag banner = saved.getBanner(houseUUID);
            PacketDistributor.sendToPlayer(player, new SyncHouseBannerPacket(banner));

            syncMembersToAllHouseMembers(player.getServer(), houseUUID);

            // Sync role so client knows if it's founder or member
            syncRole(player, houseUUID, saved);
        }
    }

    public static void onPlayerLeave(ServerPlayer player) {
        pendingInvites.remove(player.getUUID());
        UUID houseUUID = getPlayerHouseUUID(player);
        if (houseUUID != null && player.getServer() != null) {
            MinecraftServer server = player.getServer();
            HouseSavedData saved = HouseSavedData.get(server);
            List<HouseSavedData.MemberRecord> records = saved.getMembers(houseUUID);
            List<SyncHouseMembersPacket.MemberEntry> entries = new ArrayList<>();
            for (HouseSavedData.MemberRecord r : records) {
                entries.add(new SyncHouseMembersPacket.MemberEntry(r.username, r.uuid));
            }
            SyncHouseMembersPacket packet = new SyncHouseMembersPacket(entries);
            for (ServerPlayer online : server.getPlayerList().getPlayers()) {
                if (online.getUUID().equals(player.getUUID())) continue;
                if (houseUUID.equals(getPlayerHouseUUID(online))) {
                    PacketDistributor.sendToPlayer(online, packet);
                }
            }
        }
    }

    // ── Validation ────────────────────────────────────────────────────────────

    public static String validateHouseName(String name) {
        if (name == null || name.isBlank())  return "House name cannot be empty.";
        if (name.trim().length() > 30)       return "House name is too long (max 30 characters).";
        if (LoreHouses.isLoreHouse(name))
            return "House " + name.trim() + " already exists in lore and cannot be claimed.";
        return null;
    }

    public static boolean isHouseNameAvailable(String houseName, ServerPlayer requestingPlayer) {
        if (houseName == null || houseName.trim().isEmpty()) return false;
        String trimmed = houseName.trim();

        UUID ownHouseUUID = getPlayerHouseUUID(requestingPlayer);
        for (ServerPlayer player : requestingPlayer.getServer().getPlayerList().getPlayers()) {
            if (player.getUUID().equals(requestingPlayer.getUUID())) continue;
            UUID otherUUID = getPlayerHouseUUID(player);
            if (otherUUID != null && otherUUID.equals(ownHouseUUID)) continue;
            if (trimmed.equalsIgnoreCase(getOnlinePlayerHouseName(player))) return false;
        }

        try {
            java.nio.file.Path playersDir = requestingPlayer.getServer()
                    .getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT)
                    .resolve("playerdata");
            if (java.nio.file.Files.exists(playersDir) && java.nio.file.Files.isDirectory(playersDir)) {
                for (java.nio.file.Path file : java.nio.file.Files.list(playersDir)
                        .collect(java.util.stream.Collectors.toList())) {
                    if (!file.toString().endsWith(".dat")) continue;
                    try {
                        UUID uuid = UUID.fromString(
                                file.getFileName().toString().replace(".dat", ""));
                        if (uuid.equals(requestingPlayer.getUUID())) continue;
                        net.minecraft.nbt.CompoundTag root = net.minecraft.nbt.NbtIo.readCompressed(
                                file, net.minecraft.nbt.NbtAccounter.unlimitedHeap());
                        String[] dat = readHouseFromDat(root);
                        if (ownHouseUUID != null && dat[1] != null
                                && ownHouseUUID.toString().equals(dat[1])) continue;
                        if (trimmed.equalsIgnoreCase(dat[0])) return false;
                    } catch (Exception ignored) {}
                }
            }
        } catch (Exception ignored) {}

        return true;
    }

    // ── Dat file reading ──────────────────────────────────────────────────────

    public static String[] readHouseFromDat(CompoundTag root) {
        CompoundTag houseTag = null;
        if (root.contains("ForgeCaps")) {
            CompoundTag caps = root.getCompound("ForgeCaps");
            if (caps.contains(AGoTMod.MOD_ID + ".house")) {
                houseTag = caps.getCompound(AGoTMod.MOD_ID + ".house");
            }
        }
        if (houseTag == null && root.contains(AGoTMod.MOD_ID + ".house")) {
            houseTag = root.getCompound(AGoTMod.MOD_ID + ".house");
        }
        if (houseTag == null) return new String[]{ "", null };
        return new String[]{
                houseTag.getString("house_name"),
                houseTag.contains("house_uuid") ? houseTag.getString("house_uuid") : null
        };
    }

    public static String readHouseNameFromDat(CompoundTag root) {
        return readHouseFromDat(root)[0];
    }
}

package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.data.LoreHouses;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ServerHouseHandler {

    /** invitee UUID -> { houseName, inviterUsername } */
    private static final Map<UUID, String[]> pendingInvites = new ConcurrentHashMap<>();

    // ── Packet handlers (register these in ModNetworking) ────────────────────

    public static void handleSendHouseInvite(SendHouseInvitePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer inviter)) return;

            String houseName = getPlayerHouseName(inviter);
            if (houseName.isEmpty()) {
                inviter.sendSystemMessage(Component.literal("You are not in a noble house."));
                return;
            }

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
            if (!getPlayerHouseName(target).isEmpty()) {
                inviter.sendSystemMessage(Component.literal(targetUsername + " is already in a noble house."));
                return;
            }
            if (pendingInvites.containsKey(target.getUUID())) {
                inviter.sendSystemMessage(Component.literal(targetUsername + " already has a pending house invite."));
                return;
            }

            pendingInvites.put(target.getUUID(), new String[]{ houseName, inviter.getGameProfile().getName() });
            PacketDistributor.sendToPlayer(target, new SyncHouseInvitePacket(houseName, inviter.getGameProfile().getName()));
            target.sendSystemMessage(Component.literal(
                    "You have been invited to join House " + houseName + " by " + inviter.getGameProfile().getName() + ". Open your House tab to respond."));
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

            String houseName   = invite[0];
            String inviterName = invite[1];

            // Always clear the invite on client
            PacketDistributor.sendToPlayer(invitee, new SyncHouseInvitePacket("", ""));

            if (packet.accepted()) {
                CompoundTag houseTag = new CompoundTag();
                houseTag.putString("house_name", houseName);
                invitee.getPersistentData().put("agotmod.house", houseTag);

                invitee.sendSystemMessage(Component.literal("You have joined House " + houseName + "!"));

                // Notify inviter and refresh member list
                ServerPlayer inviter = invitee.getServer().getPlayerList().getPlayerByName(inviterName);
                if (inviter != null) {
                    inviter.sendSystemMessage(Component.literal(
                            invitee.getGameProfile().getName() + " has joined House " + houseName + "."));
                    syncMembersToAllHouseMembers(inviter.getServer(), houseName);
                }
            } else {
                invitee.sendSystemMessage(Component.literal("You declined the invitation to House " + houseName + "."));
                ServerPlayer inviter = invitee.getServer().getPlayerList().getPlayerByName(inviterName);
                if (inviter != null) {
                    inviter.sendSystemMessage(Component.literal(
                            invitee.getGameProfile().getName() + " declined your house invitation."));
                }
            }
        });
    }

    // ── Member sync ───────────────────────────────────────────────────────────

    public static void syncMembersToAllHouseMembers(net.minecraft.server.MinecraftServer server, String houseName) {
        List<String> members = new ArrayList<>();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (houseName.equalsIgnoreCase(getPlayerHouseName(player))) {
                members.add(player.getGameProfile().getName());
            }
        }
        SyncHouseMembersPacket packet = new SyncHouseMembersPacket(members);
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (houseName.equalsIgnoreCase(getPlayerHouseName(player))) {
                PacketDistributor.sendToPlayer(player, packet);
            }
        }
    }

    // ── House name validation ─────────────────────────────────────────────────

    /**
     * Call this from the existing SaveHouseNamePacket / CheckHouseNamePacket handler.
     * Returns an error string if invalid, null if OK.
     */
    public static String validateHouseName(String name) {
        if (name == null || name.isBlank())  return "House name cannot be empty.";
        if (name.trim().length() > 30)       return "House name is too long (max 30 characters).";
        if (LoreHouses.isLoreHouse(name))    return "House " + name.trim() + " already exists in lore and cannot be claimed.";
        return null;
    }

    // ── Player join / leave hooks ─────────────────────────────────────────────

    /** Call from your server player login event. */
    public static void onPlayerJoin(ServerPlayer player) {
        // Restore any pending invite
        String[] invite = pendingInvites.get(player.getUUID());
        if (invite != null) {
            PacketDistributor.sendToPlayer(player, new SyncHouseInvitePacket(invite[0], invite[1]));
        }
        // Sync member list if they are in a house
        String houseName = getPlayerHouseName(player);
        if (!houseName.isEmpty()) {
            syncMembersToAllHouseMembers(player.getServer(), houseName);
        }
    }

    /** Call from your server player logout event. */
    public static void onPlayerLeave(ServerPlayer player) {
        pendingInvites.remove(player.getUUID());
        // Notify remaining online house members of updated list
        String houseName = getPlayerHouseName(player);
        if (!houseName.isEmpty() && player.getServer() != null) {
            syncMembersToAllHouseMembers(player.getServer(), houseName);
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static String getPlayerHouseName(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();
        if (data.contains("agotmod.house")) {
            return data.getCompound("agotmod.house").getString("house_name");
        }
        return "";
    }
}

// Updated SetHouseBannerCommand.java
package net.darkflameproduction.agotmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;

public class SetHouseBannerCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("set")
                .then(Commands.literal("house")
                        .then(Commands.literal("banner")
                                .executes(SetHouseBannerCommand::execute))));
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        if (!(context.getSource().getEntity() instanceof ServerPlayer player)) {
            context.getSource().sendFailure(Component.literal("This command can only be used by players"));
            return 0;
        }

        ItemStack heldItem = player.getMainHandItem();

        // Check if player is holding a banner
        if (!(heldItem.getItem() instanceof BannerItem)) {
            player.sendSystemMessage(Component.literal("You must be holding a banner to set it as your house banner!"));
            return 0;
        }

        // Check if player has a house name
        CompoundTag persistentData = player.getPersistentData();
        String houseName = "";
        if (persistentData.contains(AGoTMod.MOD_ID + ".house")) {
            CompoundTag houseTag = persistentData.getCompound(AGoTMod.MOD_ID + ".house");
            if (houseTag.contains("house_name")) {
                houseName = houseTag.getString("house_name");
            }
        }

        if (houseName.isEmpty()) {
            player.sendSystemMessage(Component.literal("You must set a house name before you can set a house banner!"));
            return 0;
        }

        // Save banner data using the proper method
        Tag bannerTagRaw = heldItem.saveOptional(player.registryAccess());
        CompoundTag bannerTag;

        if (bannerTagRaw instanceof CompoundTag) {
            bannerTag = (CompoundTag) bannerTagRaw;
        } else {
            // Fallback to the old method if saveOptional doesn't return CompoundTag
            bannerTag = new CompoundTag();
            heldItem.save(player.registryAccess(), bannerTag);
        }

        // Debug logging
        AGoTMod.LOGGER.info("Saving banner for player {}: {}", player.getName().getString(), bannerTag.toString());

        // Save banner data to UUID-based storage instead of house
        String playerUUID = player.getUUID().toString();
        CompoundTag bannerStorage = new CompoundTag();
        bannerStorage.put("house_banner", bannerTag);
        persistentData.put(AGoTMod.MOD_ID + ".player_banner_" + playerUUID, bannerStorage);

        player.sendSystemMessage(Component.literal("House banner set successfully for House " + houseName + "!"));

        AGoTMod.LOGGER.info("Player {} (UUID: {}) set house banner for House {}",
                player.getName().getString(), playerUUID, houseName);

        return 1;
    }
}
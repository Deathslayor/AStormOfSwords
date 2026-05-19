package net.darkflameproduction.agotmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.data.HouseSavedData;
import net.darkflameproduction.agotmod.network.ServerHouseHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

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

        UUID houseUUID = ServerHouseHandler.getPlayerHouseUUID(player);
        if (houseUUID == null) {
            player.sendSystemMessage(Component.literal(
                    "You must set a house name before you can set a house banner!"));
            return 0;
        }

        // Only the founder can set the banner
        UUID founderUUID = HouseSavedData.get(player.getServer()).getFounderUUID(houseUUID);
        if (!player.getUUID().equals(founderUUID)) {
            player.sendSystemMessage(Component.literal(
                    "Only the head of the house can set the house banner!"));
            return 0;
        }

        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof BannerItem)) {
            player.sendSystemMessage(Component.literal(
                    "You must be holding a banner to set it as your house banner!"));
            return 0;
        }

        Tag bannerTagRaw = heldItem.saveOptional(player.registryAccess());
        CompoundTag bannerTag;
        if (bannerTagRaw instanceof CompoundTag ct) {
            bannerTag = ct;
        } else {
            bannerTag = new CompoundTag();
            heldItem.save(player.registryAccess(), bannerTag);
        }

        AGoTMod.LOGGER.info("Saving banner for house UUID {}: {}", houseUUID, bannerTag);

        ServerHouseHandler.saveBannerForHouse(player.getServer(), houseUUID, bannerTag);
        player.sendSystemMessage(Component.literal(
                "House banner set successfully for House "
                        + ServerHouseHandler.getOnlinePlayerHouseName(player) + "!"));
        return 1;
    }
}
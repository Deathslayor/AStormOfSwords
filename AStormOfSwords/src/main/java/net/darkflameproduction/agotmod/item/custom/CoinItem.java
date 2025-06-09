package net.darkflameproduction.agotmod.item.custom;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.client.PlayerCoinData;
import net.darkflameproduction.agotmod.network.CoinBalancePacket;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class CoinItem extends Item {
    private final long coinValue; // Value in halfpennies

    public CoinItem(Properties properties, long valueInHalfpennies) {
        super(properties);
        this.coinValue = valueInHalfpennies;
        AGoTMod.LOGGER.info("CoinItem created with value: {}", valueInHalfpennies);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        AGoTMod.LOGGER.info("CoinItem.use() called - Level: {}, Player: {}, Hand: {}, Item: {}",
                level.isClientSide() ? "CLIENT" : "SERVER",
                player.getName().getString(),
                hand,
                itemStack.getItem().toString());

        if (!level.isClientSide()) {
            AGoTMod.LOGGER.info("Processing coin on server side - adding {} halfpennies", coinValue);

            // Get current balance before
            var persistentData = player.getPersistentData();
            long currentBalance = persistentData.getLong("agotmod.coin_balance");
            AGoTMod.LOGGER.info("Current balance before: {}", currentBalance);

            // Add coin value
            long newBalance = currentBalance + coinValue;
            persistentData.putLong("agotmod.coin_balance", newBalance);
            AGoTMod.LOGGER.info("New balance after: {}", newBalance);

            // Send packet to client to update the balance
            if (player instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new CoinBalancePacket(newBalance));
                AGoTMod.LOGGER.info("Sent balance update packet to client: {}", newBalance);
            }

            // Also update attachment for consistency
            try {
                PlayerCoinData.addCoins(player, coinValue);
                AGoTMod.LOGGER.info("Successfully updated attachment data");
            } catch (Exception e) {
                AGoTMod.LOGGER.error("Failed to update attachment data: {}", e.getMessage());
            }

            // Play money sound
            try {
                player.playSound(ModSounds.MONEY.get(), 1.0F, 1.0F);
                AGoTMod.LOGGER.info("Played money sound");
            } catch (Exception e) {
                AGoTMod.LOGGER.error("Failed to play sound: {}", e.getMessage());
            }

            // Consume the item
            itemStack.shrink(1);
            AGoTMod.LOGGER.info("Item consumed, stack size now: {}", itemStack.getCount());

            return InteractionResult.SUCCESS;
        }

        AGoTMod.LOGGER.info("Client side - returning CONSUME");
        return InteractionResult.CONSUME;
    }
}
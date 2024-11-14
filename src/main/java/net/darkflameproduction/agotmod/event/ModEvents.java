// This code belongs to the package net.stormofsorts.agotmod.event
package net.darkflameproduction.agotmod.event;

// Importing necessary classes from other packages

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.magic.custom.PlayerManaProvider;
import net.darkflameproduction.agotmod.villager.ModVillagers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Annotating the class as a subscriber to the Forge Mod Event Bus
@EventBusSubscriber(modid = AGoTMod.MOD_ID)
public class ModEvents {

    // Annotation indicating that this method should subscribe to the specified event
    @SubscribeEvent
    // Adds custom trades for the SPECIFIC Villager Profession
    public static void addCustomTrades(VillagerTradesEvent event) {

        // Template for Future Usage STANDARD ITEMS trade
        if (event.getType() == ModVillagers.MINTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemCost(ModItems.COIN.get(), 10),
                    // Item Offered
                    new ItemStack(Items.IRON_INGOT, 2),
                    10, 6, 0.02f));
            // Level 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemCost(ModItems.COIN.get(), 18),
                    // Item Offered
                    new ItemStack(Items.GOLD_INGOT, 2),
                    5, 8, 0.042f));
            // Level 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemCost(ModItems.COIN.get(), 25),
                    // Item Offered
                    new ItemStack(Items.EMERALD, 1),
                    3, 10, 0.08f));
            // Level 4
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemCost(ModItems.COIN.get(), 28),
                    // Item Offered
                    new ItemStack(Items.IRON_BLOCK, 1),
                    8, 12, 0.06f));
            // Level 5
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemCost(ModItems.COIN.get(), 40),
                    // Item Offered
                    new ItemStack(Items.DIAMOND, 1),
                    5, 17, 0.1f));
        }
    }

    // Annotation indicating that this method should subscribe to the specified event
    @SubscribeEvent
    // Adds custom trades for WANDERING TRADERS
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        // For generic trades aka what trades most often happens
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        // For rare trades aka if you are lucky the villager might have some hella good trades
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();


        // This is a Template for adding GENERIC trades to the WANDERING TRADER
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                // Item the villager is going to request for trade
                new ItemCost(ModItems.COIN.get(), 20),
                // Item Offered
                new ItemStack(Items.DIAMOND, 1),
                3, 5, 0.2f));

        // This is a Template for adding RARE trades to the WANDERING TRADER
        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                // Item the villager is going to request for trade
                new ItemCost(ModItems.COIN.get(), 20),
                // Item Offered
                new ItemStack(Items.DIAMOND, 3),
                5, 5, 0.2f));

    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.@NotNull Clone event) {
        if (event.isWasDeath()) {
            var oldStore = event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA);
            var newStore = event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA);
            if (newStore != null && oldStore != null) {
                newStore.copyFrom(oldStore);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        var mana = event.getEntity().getCapability(PlayerManaProvider.PLAYER_MANA);
        if (mana != null && !event.getEntity().level().isClientSide && mana.getMana() > 0 && event.getEntity().getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
            mana.addMana(1);
            ((ServerPlayer) event.getEntity()).sendSystemMessage(Component.literal("Subtracted Mana"));
        }
    }
}

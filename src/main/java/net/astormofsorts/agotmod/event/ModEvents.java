// This code belongs to the package net.stormofsorts.agotmod.event
package net.astormofsorts.agotmod.event;

// Importing necessary classes from other packages
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.item.ModItems;
import net.astormofsorts.agotmod.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

// Annotating the class as a subscriber to the Forge Mod Event Bus
@Mod.EventBusSubscriber(modid = AGoTMod.MOD_ID)
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
                    new ItemStack(ModItems.COIN.get(), 10),
                    // Item Offered
                    new ItemStack(Items.IRON_INGOT, 2),
                    10, 6, 0.02f));
            // Level 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemStack(ModItems.COIN.get(), 18),
                    // Item Offered
                    new ItemStack(Items.GOLD_INGOT, 2),
                    5, 8, 0.042f));
            // Level 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemStack(ModItems.COIN.get(), 25),
                    // Item Offered
                    new ItemStack(Items.EMERALD, 1),
                    3, 10, 0.08f));
            // Level 4
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemStack(ModItems.COIN.get(), 28),
                    // Item Offered
                    new ItemStack(Items.IRON_BLOCK, 1),
                    8, 12, 0.06f));
            // Level 5
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    // Item the villager is going to request for trade
                    new ItemStack(ModItems.COIN.get(), 40),
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
                new ItemStack(ModItems.COIN.get(), 20),
                // Item Offered
                new ItemStack(Items.DIAMOND, 1),
                3, 5, 0.2f));

        // This is a Template for adding RARE trades to the WANDERING TRADER
        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                // Item the villager is going to request for trade
                new ItemStack(ModItems.COIN.get(), 20),
                // Item Offered
                new ItemStack(Items.DIAMOND, 3),
                5, 5, 0.2f));

    }
}

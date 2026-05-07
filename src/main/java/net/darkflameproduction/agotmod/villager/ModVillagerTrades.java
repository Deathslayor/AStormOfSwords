package net.darkflameproduction.agotmod.villager;

import java.util.Comparator;
import java.util.List;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public final class ModVillagerTrades {
    private static final int MAX_TRADES = 16;
    private static final int XP = 2;
    private static final float PRICE_MULTIPLIER = 0.05F;

    private ModVillagerTrades() {
    }

    public static void onVillagerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide()) {
            return;
        }

        if (!(event.getTarget() instanceof Villager villager)) {
            return;
        }

        if (villager.getVillagerData().getProfession() != ModVillagers.MINTER.get()) {
            return;
        }

        ensureAllMinterTrades(villager);
    }

    private static void ensureAllMinterTrades(Villager villager) {
        List<Item> nuggetItems = getAllNuggetItems();
        MerchantOffers currentOffers = villager.getOffers();

        if (hasAllNuggetTrades(currentOffers, nuggetItems)) {
            return;
        }

        MerchantOffers rebuiltOffers = new MerchantOffers();
        for (Item nuggetItem : nuggetItems) {
            MerchantOffer offer = createEmeraldForNuggetTrade(nuggetItem).getOffer(villager, villager.getRandom());
            if (offer != null) {
                rebuiltOffers.add(offer);
            }
        }

        villager.setOffers(rebuiltOffers);
    }

    private static boolean hasAllNuggetTrades(MerchantOffers offers, List<Item> nuggetItems) {
        if (offers.size() != nuggetItems.size()) {
            return false;
        }

        for (Item nuggetItem : nuggetItems) {
            boolean found = offers.stream().anyMatch(offer ->
                    offer.getBaseCostA().is(Items.EMERALD) && offer.getBaseCostA().getCount() == 1
                            && offer.getResult().is(nuggetItem));
            if (!found) {
                return false;
            }
        }

        return true;
    }

    private static List<Item> getAllNuggetItems() {
        return BuiltInRegistries.ITEM.stream()
                .filter(item -> item != Items.AIR)
                .filter(item -> {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
                    return key != null && key.getPath().contains("nugget");
                })
                .sorted(Comparator.comparing(item -> BuiltInRegistries.ITEM.getKey(item).toString()))
                .toList();
    }

    private static BasicItemListing createEmeraldForNuggetTrade(Item nuggetItem) {
        return new BasicItemListing(1, new ItemStack(nuggetItem), MAX_TRADES, XP, PRICE_MULTIPLIER);
    }
}

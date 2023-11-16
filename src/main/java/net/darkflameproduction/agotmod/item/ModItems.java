package net.darkflameproduction.agotmod.item;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // makes creating items possible
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AGoTMod.MOD_ID);

    // Here I am adding the items

    // Main currency Coin
    public static final RegistryObject<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties()));


    // tells the AGoTMod class to call the modded items into the game
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

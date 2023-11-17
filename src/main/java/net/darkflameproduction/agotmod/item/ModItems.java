package net.darkflameproduction.agotmod.item;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.custom.ModArmorMaterials;
import net.darkflameproduction.agotmod.custom.Stark1ArmorItem;
import net.minecraft.world.item.ArmorItem;
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

    // Adding tier 1 STARK Armour
    public static final RegistryObject<Item> STARK1_HELMET = ITEMS.register("stark1_helmet",
            () ->new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.HELMET,new Item.Properties()));

    public static final RegistryObject<Item> STARK1_CHESTPLATE = ITEMS.register("stark1_chestplate",
            () ->new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.CHESTPLATE,new Item.Properties()));

    public static final RegistryObject<Item> STARK1_LEGGINGS = ITEMS.register("stark1_leggings",
            () ->new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.LEGGINGS,new Item.Properties()));

    public static final RegistryObject<Item> STARK1_BOOTS = ITEMS.register("stark1_boots",
            () ->new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.BOOTS,new Item.Properties()));


    // tells the AGoTMod class to call the modded items into the game
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

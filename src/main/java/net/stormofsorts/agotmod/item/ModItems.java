package net.stormofsorts.agotmod.item;

import net.minecraft.world.item.*;
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.custom.ModArmorMaterials;
import net.stormofsorts.agotmod.custom.Stark1ArmorItem;
import net.stormofsorts.agotmod.entity.ModEntities;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // makes creating items possible
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AGoTMod.MOD_ID);

    // Here I am adding the items

    // ---------------------------(COINS)--------------------------- //
    // Main currency Coin
    public static final RegistryObject<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties()));
    // ---------------------------(COINS)--------------------------- //

    // ---------------------------(ARMOUR)--------------------------- //
    // Adding tier 1 STARK Armour
    public static final RegistryObject<Item> STARK1_HELMET = ITEMS.register("stark1_helmet",
            () ->new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.HELMET,new Item.Properties()));

    public static final RegistryObject<Item> STARK1_CHESTPLATE = ITEMS.register("stark1_chestplate",
            () ->new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.CHESTPLATE,new Item.Properties()));

    public static final RegistryObject<Item> STARK1_LEGGINGS = ITEMS.register("stark1_leggings",
            () ->new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.LEGGINGS,new Item.Properties()));

    public static final RegistryObject<Item> STARK1_BOOTS = ITEMS.register("stark1_boots",
            () ->new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.BOOTS,new Item.Properties()));
    // ---------------------------(ARMOUR)--------------------------- //

    // ---------------------------(INGOTS)--------------------------- //
    // TIN INGOT
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));
    // RAW TIN
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    // BRONZE INGOT
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties()));
    // ---------------------------(INGOTS)--------------------------- //

    // ---------------------------(ANIMAL SPAWN EGGS)--------------------------- //
    public static final RegistryObject<Item> RHINO_SPANW_EGG = ITEMS.register("rhino_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RHINO, 0x7e9680, 0xc5d1c5, new Item.Properties()));
    // ---------------------------(ANIMAL SPAWN EGGS)--------------------------- //

    // ---------------------------(TOOLS)--------------------------- //
    // Bronze TOOLS
    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
            () -> new SwordItem(ModToolTiers.BRONZE, 5, 1.4F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PICKAXE = ITEMS.register("bronze_pickaxe",
            () -> new PickaxeItem(ModToolTiers.BRONZE, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SHOVEL = ITEMS.register("bronze_shovel",
            () -> new ShovelItem(ModToolTiers.BRONZE, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_AXE = ITEMS.register("bronze_axe",
            () -> new AxeItem(ModToolTiers.BRONZE, 7, 0.9F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_HOE = ITEMS.register("bronze_hoe",
            () -> new HoeItem(ModToolTiers.BRONZE, 0, 0, new Item.Properties()));
    // ---------------------------(TOOLS)--------------------------- //




    // tells the AGoTMod class to call the modded items into the game
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

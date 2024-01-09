// This code belongs to the package net.stormofsorts.agotmod.item
package net.astormofsorts.agotmod.item;

// Importing necessary classes from other packages
import net.astormofsorts.agotmod.armor.custom.ModArmorMaterials;
import net.astormofsorts.agotmod.armor.custom.Stark1ArmorItem;
import net.minecraft.world.item.*;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.entity.ModEntities;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// A utility class for creating and registering modded items
public class ModItems {

    // Deferred register for items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AGoTMod.MOD_ID);

    // ---------------------------(COINS)--------------------------- //
    // Main currency Coin
    public static final RegistryObject<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties()));
    // ---------------------------(COINS)--------------------------- //

    // ---------------------------(ARMOUR)--------------------------- //
    // Adding tier 1 STARK Armour
    public static final RegistryObject<Item> STARK1_HELMET = ITEMS.register("stark1_helmet",
            () -> new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> STARK1_CHESTPLATE = ITEMS.register("stark1_chestplate",
            () -> new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> STARK1_LEGGINGS = ITEMS.register("stark1_leggings",
            () -> new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> STARK1_BOOTS = ITEMS.register("stark1_boots",
            () -> new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Adding Stark Levy Armour
    public static final RegistryObject<Item> STARK_LEVY_HELMET = ITEMS.register("stark_levy_helmet",
            () -> new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> STARK_LEVY_CHESTPLATE = ITEMS.register("stark_levy_chestplate",
            () -> new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> STARK_LEVY_LEGGINGS = ITEMS.register("stark_levy_leggings",
            () -> new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> STARK_LEVY_BOOTS = ITEMS.register("stark_levy_boots",
            () -> new Stark1ArmorItem(ModArmorMaterials.STARK1, ArmorItem.Type.BOOTS, new Item.Properties()));
    // ---------------------------(ARMOUR)--------------------------- //

    // ---------------------------(INGOTS/NUGGETS)--------------------------- //
    // TIN INGOT
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));
    // RAW TIN
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    // BRONZE INGOT
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties()));

    // STEEL INGOT
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));
    // ---------------------------(INGOTS)--------------------------- //

    // ---------------------------(ANIMAL SPAWN EGGS)--------------------------- //
    public static final RegistryObject<Item> RHINO_SPANW_EGG = ITEMS.register("rhino_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RHINO, 0x7e9680, 0xc5d1c5, new Item.Properties()));
    public static final RegistryObject<Item> BEAR_SPANW_EGG = ITEMS.register("bear_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BEAR, 0x7e9680, 0xc5d1c5, new Item.Properties()));
    public static final RegistryObject<Item> WIGHT_SPANW_EGG = ITEMS.register("wight_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.WIGHT, 0x7e9680, 0xc5d1c5, new Item.Properties()));
    // ---------------------------(ANIMAL SPAWN EGGS)--------------------------- //

    // ---------------------------(TOOLS)--------------------------- //
    // Bronze TOOLS
    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
            () -> new SwordItem(ModToolTiers.BRONZE, 3, 1, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PICKAXE = ITEMS.register("bronze_pickaxe",
            () -> new PickaxeItem(ModToolTiers.BRONZE, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SHOVEL = ITEMS.register("bronze_shovel",
            () -> new ShovelItem(ModToolTiers.BRONZE, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_AXE = ITEMS.register("bronze_axe",
            () -> new AxeItem(ModToolTiers.BRONZE, 5.5F, 0.9F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_HOE = ITEMS.register("bronze_hoe",
            () -> new HoeItem(ModToolTiers.BRONZE, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SPATHA = ITEMS.register("bronze_spatha",
            () -> new SwordItem(ModToolTiers.BRONZE, 5, .5F, new Item.Properties()));

    // IRON WEAPONS
    public static final RegistryObject<Item> IRON_LONGSWORD = ITEMS.register("iron_longsword",
            () -> new SwordItem(Tiers.IRON, 6, .5F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SPEAR = ITEMS.register("iron_spear",
            () -> new SwordItem(Tiers.IRON, 6, .2F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_PIKE = ITEMS.register("iron_pike",
            () -> new SwordItem(Tiers.IRON, 7, .2F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_MACE = ITEMS.register("iron_mace",
            () -> new SwordItem(Tiers.IRON, 7, .2F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_DAGGER = ITEMS.register("iron_dagger",
            () -> new SwordItem(Tiers.IRON, 7, .2F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_BATTLE_AXE = ITEMS.register("iron_battle_axe",
            () -> new SwordItem(Tiers.IRON, 7, .2F, new Item.Properties()));

    // STEEL EAPONS
    public static final RegistryObject<Item> STEEL_LONGSWORD = ITEMS.register("steel_longsword",
            () -> new SwordItem(ModToolTiers.STEEL, 6, .5F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SPEAR = ITEMS.register("steel_spear",
            () -> new SwordItem(ModToolTiers.STEEL, 6, .2F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PIKE = ITEMS.register("steel_pike",
            () -> new SwordItem(ModToolTiers.STEEL, 7, .2F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_MACE = ITEMS.register("steel_mace",
            () -> new SwordItem(ModToolTiers.STEEL, 7, .2F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_DAGGER = ITEMS.register("steel_dagger",
            () -> new SwordItem(ModToolTiers.STEEL, 7, .2F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_BATTLEAXE = ITEMS.register("steel_battleaxe",
            () -> new SwordItem(ModToolTiers.STEEL, 7, .2F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HALBERD = ITEMS.register("steel_halberd",
            () -> new SwordItem(ModToolTiers.STEEL, 7, .2F, new Item.Properties()));



    // Steel TOOLS
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModToolTiers.STEEL, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STEEL, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModToolTiers.STEEL, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModToolTiers.STEEL, 7, 1, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModToolTiers.STEEL, 0, 0, new Item.Properties()));
    // ---------------------------(TOOLS)--------------------------- //

    // ---------------------------(FOODS)--------------------------- //
    public static final RegistryObject<Item> RAW_BEAR_MEAT = ITEMS.register("raw_bear_meat",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_BEAR_MEAT = ITEMS.register("cooked_bear_meat",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_WHITE_SAUSAGE = ITEMS.register("cooked_white_sausage",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_WHITE_SAUSAGE = ITEMS.register("raw_white_sausage",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_BLOOD_SAUSAGE = ITEMS.register("cooked_blood_sausage",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_BLOOD_SAUSAGE = ITEMS.register("raw_blood_sausage",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_SAUSAGE = ITEMS.register("cooked_sausage",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_SAUSAGE = ITEMS.register("raw_sausage",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_BACON = ITEMS.register("cooked_bacon",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_BACON = ITEMS.register("raw_bacon",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_BOAR_VENISON = ITEMS.register("cooked_boar_venison",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_BOAR_VENISON = ITEMS.register("raw_boar_venison",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_CHICKEN_NUGGETS = ITEMS.register("cooked_chicken_nuggets",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_CHICKEN_NUGGETS = ITEMS.register("raw_chicken_nuggets",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_DEER_VENISON = ITEMS.register("cooked_deer_venison",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_DEER_VENISON  = ITEMS.register("raw_deer_venison",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_GOAT_MEAT = ITEMS.register("cooked_goat_meat",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_GOAT_MEAT  = ITEMS.register("raw_goat_meat",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_HARE_MEAT = ITEMS.register("cooked_hare_meat",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_HARE_MEAT  = ITEMS.register("raw_hare_meat",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_HORSE_MEAT = ITEMS.register("cooked_horse_meat",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_HORSE_MEAT  = ITEMS.register("raw_horse_meat",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));
    public static final RegistryObject<Item> COOKED_MAMMOTH_MEAT = ITEMS.register("cooked_mammoth_meat",
            () -> new Item(new Item.Properties().food(ModFoods.COOKED_BEAR_MEAT)));
    public static final RegistryObject<Item> RAW_MAMMOTH_MEAT  = ITEMS.register("raw_mammoth_meat",
            () -> new Item(new Item.Properties().food(ModFoods.RAW_BEAR_MEAT)));

    // ---------------------------(FOODS)--------------------------- //

    // ---------------------------(CRAFTING INGREDIENTS)--------------------------- //
    public static final RegistryObject<Item> BOAR_INTESTINES = ITEMS.register("boar_intestines",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOAR_TUSK = ITEMS.register("boar_tusk",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WEIRWOOD_STICK = ITEMS.register("weirwood_stick",
            () -> new Item(new Item.Properties()));

    // Tells the AGoTMod class to call the modded items into the game
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.item.custom.CoinItem;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

// A utility class for creating and registering modded items
public class ModItems {

    // Deferred register for items
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AGoTMod.MOD_ID);
    // ---------------------------(Magic)--------------------------- //
    // Blood
    public static final DeferredItem<Item> BLOOD_BOTTLED = ITEMS.registerItem("blood_bottled",
            PotionItem::new);
    // Magical Dagger
    public static final DeferredItem<Item> BLOOD_DAGGER = ITEMS.registerItem("blood_dagger",
            properties -> new SwordItem(ModToolTiers.STEEL, properties.attributes(SwordItem.createAttributes(ModToolTiers.STEEL, 3, 1.0F))));

    // ---------------------------(COINS)--------------------------- //
    // Main currency Coin
    public static final DeferredItem<Item> COIN = ITEMS.registerItem("coin",
            Item::new);
    // ---------------------------(COINS)--------------------------- //

    // ---------------------------(ARMOUR)--------------------------- //


    // 3D armour sets and their smithing scrolls are registered by ModArmorSets (see register())
    // ---------------------------(COINS)--------------------------- //
    public static final DeferredItem<Item> GOLD_DRAGON = ITEMS.registerItem("gold_dragon",
            properties -> new CoinItem(properties, 23520L)); // 1 dragon = 23520 halfpennies

    // Silver coins
    public static final DeferredItem<Item> SILVER_MOON = ITEMS.registerItem("silver_moon",
            properties -> new CoinItem(properties, 784L)); // 1 moon = 784 halfpennies

    public static final DeferredItem<Item> SILVER_STAG = ITEMS.registerItem("silver_stag",
            properties -> new CoinItem(properties, 112L)); // 1 stag = 112 halfpennies

    // Copper coins
    public static final DeferredItem<Item> COPPER_STAR = ITEMS.registerItem("copper_star",
            properties -> new CoinItem(properties, 16L)); // 1 star = 16 halfpennies

    public static final DeferredItem<Item> COPPER_GROAT = ITEMS.registerItem("copper_groat",
            properties -> new CoinItem(properties, 8L)); // 1 groat = 8 halfpennies

    public static final DeferredItem<Item> COPPER_HALFGROAT = ITEMS.registerItem("copper_halfgroat",
            properties -> new CoinItem(properties, 4L)); // 1 halfgroat = 4 halfpennies

    public static final DeferredItem<Item> COPPER_PENNY = ITEMS.registerItem("copper_penny",
            properties -> new CoinItem(properties, 2L)); // 1 penny = 2 halfpennies

    public static final DeferredItem<Item> COPPER_HALFPENNY = ITEMS.registerItem("copper_halfpenny",
            properties -> new CoinItem(properties, 1L));

    // ---------------------------(INGOTS/NUGGETS)--------------------------- //
    // TIN INGOT
    public static final DeferredItem<Item> SILVER_NUGGET = ITEMS.registerItem("silver_nugget",
            Item::new);
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.registerItem("silver_ingot",
            Item::new);
    // RAW TIN
    public static final DeferredItem<Item> RAW_SILVER = ITEMS.registerItem("raw_silver",
            Item::new);

    public static final DeferredItem<Item> TIN_INGOT = ITEMS.registerItem("tin_ingot",
            Item::new);
    // RAW TIN
    public static final DeferredItem<Item> RAW_TIN = ITEMS.registerItem("raw_tin",
            Item::new);
    // BRONZE INGOT
    public static final DeferredItem<Item> BRONZE_INGOT = ITEMS.registerItem("bronze_ingot",
            Item::new);

    public static final DeferredItem<Item> BRONZE_NUGGET = ITEMS.registerItem("bronze_nugget",
            Item::new);

    // STEEL INGOT
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerItem("steel_ingot",
            Item::new);
    public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.registerItem("steel_nugget",
            Item::new);

    // GEMS
    public static final DeferredItem<Item> YELLOW_DIAMOND = ITEMS.registerItem("yellow_diamond",
            Item::new);
    public static final DeferredItem<Item> TRANSPARENT_DIAMOND = ITEMS.registerItem("transparent_diamond",
            Item::new);
    public static final DeferredItem<Item> AMBER = ITEMS.registerItem("amber",
            Item::new);
    public static final DeferredItem<Item> AMETHYST = ITEMS.registerItem("amethyst",
            Item::new);
    public static final DeferredItem<Item> BLACK_DIAMOND = ITEMS.registerItem("black_diamond",
            Item::new);
    public static final DeferredItem<Item> BLOODSTONE = ITEMS.registerItem("bloodstone",
            Item::new);
    public static final DeferredItem<Item> CARNELIAN = ITEMS.registerItem("carnelian",
            Item::new);
    public static final DeferredItem<Item> CHALCEDONY = ITEMS.registerItem("chalcedony",
            Item::new);
    public static final DeferredItem<Item> GARNET = ITEMS.registerItem("garnet",
            Item::new);
    public static final DeferredItem<Item> JADE = ITEMS.registerItem("jade",
            Item::new);
    public static final DeferredItem<Item> JASPER = ITEMS.registerItem("jasper",
            Item::new);
    public static final DeferredItem<Item> MALACHITE = ITEMS.registerItem("malachite",
            Item::new);
    public static final DeferredItem<Item> MOONSTONE = ITEMS.registerItem("moonstone",
            Item::new);
    public static final DeferredItem<Item> ONYX = ITEMS.registerItem("onyx",
            Item::new);
    public static final DeferredItem<Item> OPAL = ITEMS.registerItem("opal",
            Item::new);
    public static final DeferredItem<Item> RUBY = ITEMS.registerItem("ruby",
            Item::new);
    public static final DeferredItem<Item> SAPPHIRE = ITEMS.registerItem("sapphire",
            Item::new);
    public static final DeferredItem<Item> TIGERS_EYE = ITEMS.registerItem("tigers_eye",
            Item::new);
    public static final DeferredItem<Item> TOPAZ = ITEMS.registerItem("topaz",
            Item::new);
    public static final DeferredItem<Item> TOURMALINE = ITEMS.registerItem("tourmaline",
            Item::new);


    // ---------------------------(BANNERS)--------------------------- //




    // ---------------------------(TOOLS)--------------------------- //
    // Bronze TOOLS
    public static final DeferredItem<Item> BRONZE_PICKAXE = ITEMS.registerItem("bronze_pickaxe",
            properties -> new PickaxeItem(ModToolTiers.BRONZE, properties.attributes(DiggerItem.createAttributes(ModToolTiers.BRONZE, 0, 0.0F))));
    public static final DeferredItem<Item> BRONZE_SHOVEL = ITEMS.registerItem("bronze_shovel",
            properties -> new ShovelItem(ModToolTiers.BRONZE, properties.attributes(DiggerItem.createAttributes(ModToolTiers.BRONZE, 0, 0.0F))));
    public static final DeferredItem<Item> BRONZE_AXE = ITEMS.registerItem("bronze_axe",
            properties -> new AxeItem(ModToolTiers.BRONZE, properties.attributes(DiggerItem.createAttributes(ModToolTiers.BRONZE, 6.0F, 0.9F))));
    public static final DeferredItem<Item> BRONZE_HOE = ITEMS.registerItem("bronze_hoe",
            properties -> new HoeItem(ModToolTiers.BRONZE, properties.attributes(DiggerItem.createAttributes(ModToolTiers.BRONZE, 0, 0.0F))));

    // DRAGONGLASS WEAPONS
    public static final DeferredItem<Item> DRAGONGLASS_SPEAR = ITEMS.registerItem("dragonglass_spear",
            properties -> new LevelRequiredSwordItem(ModToolTiers.DRAGONGLASS, 5, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> DRAGONGLASS_DAGGER = ITEMS.registerItem("dragonglass_dagger",
            properties -> new LevelRequiredSwordItem(ModToolTiers.DRAGONGLASS, 2, -1.5F, properties, WeaponRequirements.SHORT_BLADE));



    // BRONZE WEAPONS
    public static final DeferredItem<Item> BRONZE_SWORD = ITEMS.registerItem("bronze_sword",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 5, -2.4F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> BRONZE_SPATHA = ITEMS.registerItem("bronze_spatha",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 7, -2.6F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> BRONZE_SPEAR = ITEMS.registerItem("bronze_spear",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 5, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> BRONZE_PIKE = ITEMS.registerItem("bronze_pike",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 6, -3F, properties, WeaponRequirements.LONG_POLEARM));
    public static final DeferredItem<Item> BRONZE_DAGGER = ITEMS.registerItem("bronze_dagger",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 2, -1.5F, properties, WeaponRequirements.SHORT_BLADE));
    public static final DeferredItem<Item> BRONZE_BATTLEAXE = ITEMS.registerItem("bronze_battleaxe",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 8, -3F, properties, WeaponRequirements.ONE_HANDED));

    // IRON WEAPONS
    private static final int IRON_SWORD_HILTS       = 6;
    private static final int IRON_SWORD_BLADES      = 3;
    private static final int IRON_SWORD_CROSSGUARDS = 4;

    public static final Map<String, DeferredItem<Item>> IRON_SWORDS = new HashMap<>();

    static {
        for (int h = 1; h <= IRON_SWORD_HILTS; h++) {
            for (int b = 1; b <= IRON_SWORD_BLADES; b++) {
                for (int c = 1; c <= IRON_SWORD_CROSSGUARDS; c++) {
                    String name = "iron_sword_h" + h + "_b" + b + "_c" + c;
                    IRON_SWORDS.put(name, ITEMS.registerItem(name,
                            properties -> new LevelRequiredSwordItem(
                                    Tiers.IRON, 9, -2.6F, properties,
                                    WeaponRequirements.TWO_HANDED)));
                }
            }
        }
    }
    public static final DeferredItem<Item> IRON_LONGSWORD = ITEMS.registerItem("iron_longsword",
            properties -> new LevelRequiredSwordItem(Tiers.IRON, 9, -2.6F, properties, WeaponRequirements.TWO_HANDED));
    public static final DeferredItem<Item> IRON_SPEAR = ITEMS.registerItem("iron_spear",
            properties -> new LevelRequiredSwordItem(Tiers.IRON, 7, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> IRON_PIKE = ITEMS.registerItem("iron_pike",
            properties -> new LevelRequiredSwordItem(Tiers.IRON, 8, -3F, properties, WeaponRequirements.LONG_POLEARM));
    public static final DeferredItem<Item> IRON_MACE = ITEMS.registerItem("iron_mace",
            properties -> new LevelRequiredSwordItem(Tiers.IRON, 7, -2.6F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> IRON_DAGGER = ITEMS.registerItem("iron_dagger",
            properties -> new LevelRequiredSwordItem(Tiers.IRON, 4, -1.5F, properties, WeaponRequirements.SHORT_BLADE));
    public static final DeferredItem<Item> IRON_BATTLEAXE = ITEMS.registerItem("iron_battleaxe",
            properties -> new LevelRequiredSwordItem(Tiers.IRON, 10, -3F, properties, WeaponRequirements.ONE_HANDED));


    // STEEL WEAPONS
    private static final int STEEL_SWORD_HILTS       = 6;
    private static final int STEEL_SWORD_BLADES      = 3;
    private static final int STEEL_SWORD_CROSSGUARDS = 12;

    public static final Map<String, DeferredItem<Item>> STEEL_SWORDS = new HashMap<>();

    static {
        for (int h = 1; h <= STEEL_SWORD_HILTS; h++) {
            for (int b = 1; b <= STEEL_SWORD_BLADES; b++) {
                for (int c = 1; c <= STEEL_SWORD_CROSSGUARDS; c++) {
                    String name = "steel_sword_h" + h + "_b" + b + "_c" + c;
                    STEEL_SWORDS.put(name, ITEMS.registerItem(name,
                            properties -> new LevelRequiredSwordItem(
                                    ModToolTiers.STEEL, 9, -2.6F, properties,
                                    WeaponRequirements.TWO_HANDED)));
                }
            }
        }
    }

    // STEEL WEAPONS
    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.registerItem("steel_sword",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 5, -2.4F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> STEEL_LONGSWORD = ITEMS.registerItem("steel_longsword",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 7, -2.6F, properties, WeaponRequirements.TWO_HANDED));
    public static final DeferredItem<Item> STEEL_SPEAR = ITEMS.registerItem("steel_spear",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 5, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> STEEL_PIKE = ITEMS.registerItem("steel_pike",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 6, -3F, properties, WeaponRequirements.LONG_POLEARM));
    public static final DeferredItem<Item> STEEL_MACE = ITEMS.registerItem("steel_mace",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 5, -2.6F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> STEEL_DAGGER = ITEMS.registerItem("steel_dagger",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 2, -1.5F, properties, WeaponRequirements.SHORT_BLADE));
    public static final DeferredItem<Item> STEEL_BATTLEAXE = ITEMS.registerItem("steel_battleaxe",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 8, -3F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> STEEL_HALBERD = ITEMS.registerItem("steel_halberd",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 9, -3.2F, properties, WeaponRequirements.POLEARM));


    // NOBLE WEAPONS
    private static final int NOBLE_SWORD_HILTS       = 6;
    private static final int NOBLE_SWORD_BLADES      = 3;
    private static final int NOBLE_SWORD_CROSSGUARDS = 80;

    public static final Map<String, DeferredItem<Item>> NOBLE_SWORDS = new HashMap<>();

    static {
        for (int h = 1; h <= NOBLE_SWORD_HILTS; h++) {
            for (int b = 1; b <= NOBLE_SWORD_BLADES; b++) {
                for (int c = 1; c <= NOBLE_SWORD_CROSSGUARDS; c++) {
                    String name = "noble_sword_h" + h + "_b" + b + "_c" + c;
                    NOBLE_SWORDS.put(name, ITEMS.registerItem(name,
                            properties -> new LevelRequiredSwordItem(
                                    ModToolTiers.NOBLE, 9, -2.6F, properties,
                                    WeaponRequirements.TWO_HANDED)));
                }
            }
        }
    }
    // NOBLE WEAPONS
    public static final DeferredItem<Item> NOBLE_LONGSWORD = ITEMS.registerItem("noble_longsword",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 7, -2.6F, properties, WeaponRequirements.TWO_HANDED));
    public static final DeferredItem<Item> NOBLE_SPEAR = ITEMS.registerItem("noble_spear",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 5, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> NOBLE_PIKE = ITEMS.registerItem("noble_pike",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 6, -3F, properties, WeaponRequirements.LONG_POLEARM));
    public static final DeferredItem<Item> NOBLE_MACE = ITEMS.registerItem("noble_mace",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 5, -2.6F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> NOBLE_DAGGER = ITEMS.registerItem("noble_dagger",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 2, -1.5F, properties, WeaponRequirements.SHORT_BLADE));
    public static final DeferredItem<Item> NOBLE_BATTLEAXE = ITEMS.registerItem("noble_battleaxe",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 8, -3F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> NOBLE_HALBERD = ITEMS.registerItem("noble_halberd",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 9, -3.2F, properties, WeaponRequirements.POLEARM));

    // Steel TOOLS
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.registerItem("steel_pickaxe",
            properties -> new PickaxeItem(ModToolTiers.STEEL, properties.attributes(DiggerItem.createAttributes(ModToolTiers.STEEL, 1, 1.0F))));
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.registerItem("steel_shovel",
            properties -> new ShovelItem(ModToolTiers.STEEL, properties.attributes(DiggerItem.createAttributes(ModToolTiers.STEEL, 0, 0.0F))));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.registerItem("steel_axe",
            properties -> new AxeItem(ModToolTiers.STEEL, properties.attributes(DiggerItem.createAttributes(ModToolTiers.STEEL, 7.0F, 1.0F))));
    public static final DeferredItem<Item> STEEL_HOE = ITEMS.registerItem("steel_hoe",
            properties -> new HoeItem(ModToolTiers.STEEL, properties.attributes(DiggerItem.createAttributes(ModToolTiers.STEEL, 0, 0.0F))));
    // ---------------------------(TOOLS)--------------------------- //
    // ---------------------------(BOW & ARROW)--------------------------- //
    //Bows
    public static final DeferredItem<Item> STEEL_BOW = ITEMS.registerItem("steel_bow",
            BowItem::new);


    //Arrows
    public static final DeferredItem<Item> ARROW_BRONZE = ITEMS.registerItem("arrow_bronze",
            ArrowItem::new);
    public static final DeferredItem<Item> ARROW_DRAGONGLASS = ITEMS.registerItem("arrow_dragon_glass",
            ArrowItem::new);
    public static final DeferredItem<Item> ARROW_STEEL = ITEMS.registerItem("arrow_steel",
            ArrowItem::new);
    public static final DeferredItem<Item> ARROW_IRON = ITEMS.registerItem("arrow_iron",
            ArrowItem::new);

    // ---------------------------(BOW & ARROW)--------------------------- //


    // ---------------------------(FOODS)--------------------------- //
    // Every food is registered from item/food/FoodCatalog by ModFoodItems - see ModFoodItems.get(id).


    // ---------------------------(CRAFTING INGREDIENTS)--------------------------- //
    public static final DeferredItem<Item> BOAR_INTESTINES = ITEMS.registerItem("boar_intestines",
            Item::new);
    public static final DeferredItem<Item> BOAR_TUSK = ITEMS.registerItem("boar_tusk",
            Item::new);
    public static final DeferredItem<Item> IVORY = ITEMS.registerItem("ivory",
            Item::new);
    public static final DeferredItem<Item> IVORY_SHARD = ITEMS.registerItem("ivory_shard",
            Item::new);
    public static final DeferredItem<Item> BOILED_LEATHER = ITEMS.registerItem("boiled_leather",
            Item::new);
    public static final DeferredItem<Item> FUR = ITEMS.registerItem("fur",
            Item::new);
    public static final DeferredItem<Item> WEIRWOOD_STICK = ITEMS.registerItem("weirwood_stick",
            Item::new);
    public static final DeferredItem<Item> BRONZE_CHAIN_LINK = ITEMS.registerItem("bronze_chain_link", Item::new);
    public static final DeferredItem<Item> BRONZE_CHAIN = ITEMS.registerItem("bronze_chain", Item::new);
    public static final DeferredItem<Item> BRONZE_PLATE = ITEMS.registerItem("bronze_plate", Item::new);
    public static final DeferredItem<Item> CLOTH = ITEMS.registerItem("cloth", Item::new);
    public static final DeferredItem<Item> NAIL = ITEMS.registerItem("nail", Item::new);
    public static final DeferredItem<Item> IRON_CHAIN_LINK = ITEMS.registerItem("iron_chain_link", Item::new);
    public static final DeferredItem<Item> IRON_CHAIN = ITEMS.registerItem("iron_chain", Item::new);
    public static final DeferredItem<Item> IRON_PLATE = ITEMS.registerItem("iron_plate", Item::new);
    public static final DeferredItem<Item> NOBLE_PLATE = ITEMS.registerItem("noble_plate", Item::new);
    public static final DeferredItem<Item> STEEL_BOOTS = ITEMS.registerItem("steel_boots", Item::new);
    public static final DeferredItem<Item> STEEL_CHAIN_LINK = ITEMS.registerItem("steel_chain_link", Item::new);
    public static final DeferredItem<Item> STEEL_CHAIN = ITEMS.registerItem("steel_chain", Item::new);
    public static final DeferredItem<Item> HAMMER = ITEMS.registerItem("hammer", properties ->
            new PickaxeItem(ModToolTiers.STEEL, properties.attributes(DiggerItem.createAttributes(ModToolTiers.STEEL, 1, 1.0F))) {
                {
                    try {
                        java.lang.reflect.Field craftingRemainingItemField = Item.class.getDeclaredField("craftingRemainingItem");
                        craftingRemainingItemField.setAccessible(true);
                        craftingRemainingItemField.set(this, this);
                    } catch (ReflectiveOperationException e) {
                        throw new IllegalStateException("Failed to configure hammer crafting remainder", e);
                    }
                }
            }
    );
    public static final DeferredItem<Item> STEEL_HELMET = ITEMS.registerItem("steel_helmet", Item::new);
    public static final DeferredItem<Item> STEEL_LEGGINGS = ITEMS.registerItem("steel_leggings", Item::new);
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.registerItem("steel_plate", Item::new);
    public static final DeferredItem<Item> STEEL_CHESTPLATE = ITEMS.registerItem("steel_chestplate", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_BRONZE = ITEMS.registerItem("upgrade_kit_bronze", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_IRON = ITEMS.registerItem("upgrade_kit_iron", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_NOBLE = ITEMS.registerItem("upgrade_kit_noble", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_STEEL = ITEMS.registerItem("upgrade_kit_steel", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_BRONZE_PLATE = ITEMS.registerItem("upgrade_kit_bronze_plate", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_CHIEF = ITEMS.registerItem("upgrade_kit_chief", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_FUR = ITEMS.registerItem("upgrade_kit_fur", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_LEATHER = ITEMS.registerItem("upgrade_kit_leather", Item::new);






    public static final DeferredItem<Item> WEIRWOOD_SIGN = ITEMS.registerItem("weirwood_sign",
            properties -> new SignItem(properties.stacksTo(16), ModBLocks.WEIRWOOD_SIGN.get(),
                    ModBLocks.WEIRWOOD_WALL_SIGN.get()));

    public static final DeferredItem<Item> WEIRWOOD_HANGING_SIGN = ITEMS.registerItem("weirwood_hanging_sign",
            properties -> new HangingSignItem(ModBLocks.WEIRWOOD_HANGING_SIGN.get(),
                    ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get(), properties.stacksTo(16)));

    public static final DeferredItem<Item> ROTTEN_SIGN = ITEMS.registerItem("rotten_sign",
            properties -> new SignItem(properties.stacksTo(16), ModBLocks.ROTTEN_SIGN.get(),
                    ModBLocks.ROTTEN_WALL_SIGN.get()));

    public static final DeferredItem<Item> ROTTEN_HANGING_SIGN = ITEMS.registerItem("rotten_hanging_sign",
            properties -> new HangingSignItem(ModBLocks.ROTTEN_HANGING_SIGN.get(),
                    ModBLocks.ROTTEN_WALL_HANGING_SIGN.get(), properties.stacksTo(16)));


    public static final DeferredItem<Item> CHARRED_SIGN = ITEMS.registerItem("charred_sign",
            properties -> new SignItem(properties.stacksTo(16), ModBLocks.CHARRED_SIGN.get(),
                    ModBLocks.CHARRED_WALL_SIGN.get()));

    public static final DeferredItem<Item> CHARRED_HANGING_SIGN = ITEMS.registerItem("charred_hanging_sign",
            properties -> new HangingSignItem(ModBLocks.CHARRED_HANGING_SIGN.get(),
                    ModBLocks.CHARRED_WALL_HANGING_SIGN.get(), properties.stacksTo(16)));

    // In your ModItems class
    public static final Map<String, DeferredItem<Item>> SIGN_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> HANGING_SIGN_ITEMS = new HashMap<>();

    // Define the wood types array
    private static final String[] WOOD_TYPES = {
            "sycamore",
            "pine",
            "ash",
            "beech",
            "cedar",
            "chestnut",
            "hawthorn",
            "ironwood",
            "sentinel",
            "blackbark",
            "aspen",
            "black_cherry",
            "black_olive",
            "crabapple",
            "olive",
            "white_cherry",
            "red_cherry",
            "fir",
            "willow",
            "wormtree",
            "alder",
            "almond",
            "apple",
            "apricot",
            "baobab",
            "black_cottonwood",
            "blackthorn",
            "blood_orange",
            "bloodwood",
            "blue_mahoe",
            "cottonwood",
            "datepalm",
            "ebony",
            "fig",
            "fireplum",
            "goldenheart",
            "lemon",
            "lime",
            "linden",
            "mahogany",
            "maple",
            "myrrh",
            "nightwood",
            "nutmeg",
            "orange",
            "peach",
            "pear",
            "pecan",
            "persimmon",
            "pink_ivory",
            "plum",
            "pomegranate",
            "purpleheart",
            "redwood",
            "sandalwood",
            "sandbeggar",
            "tigerwood",
            "yew",
            "blue_soldier_pine",
            "soldier_pine"
    };

    // Static initialization block to register all wood signs
    static {
        // Register all wood signs using a loop
        for (String woodType : WOOD_TYPES) {
            // Regular signs
            SIGN_ITEMS.put(woodType, ITEMS.registerItem(woodType + "_sign",
                    properties -> new SignItem(
                            properties.stacksTo(16),
                            ModBLocks.SIGNS.get(woodType).get(),
                            ModBLocks.WALL_SIGNS.get(woodType).get()
                    )
            ));

            // Hanging signs
            HANGING_SIGN_ITEMS.put(woodType, ITEMS.registerItem(woodType + "_hanging_sign",
                    properties -> new HangingSignItem(
                            ModBLocks.HANGING_SIGNS.get(woodType).get(),
                            ModBLocks.WALL_HANGING_SIGNS.get(woodType).get(),
                            properties.stacksTo(16)
                    )
            ));
        }
    }

// No need for the getBlockField method anymore since we access maps directly


    // ---------------------------(SEEDS)--------------------------- //

    public static final DeferredItem<Item> HORSERADISH_SEEDS = ITEMS.registerItem("horseradish_seeds",
            properties -> new BlockItem(ModBLocks.HORSERADISH_CROP.get(), properties));


    public static final DeferredItem<Item> LEEK_SEEDS = ITEMS.registerItem("leek_seeds",
            properties -> new BlockItem(ModBLocks.LEEK_CROP.get(), properties));

    public static final DeferredItem<Item> NEEP_SEEDS = ITEMS.registerItem("neep_seeds",
            properties -> new BlockItem(ModBLocks.NEEP_CROP.get(), properties));


    public static final DeferredItem<Item> PARSLEY_SEEDS = ITEMS.registerItem("parsley_seeds",
            properties -> new BlockItem(ModBLocks.PARSLEY_CROP.get(), properties));

    public static final DeferredItem<Item> RED_ONION_SEEDS = ITEMS.registerItem("red_onion_seeds",
            properties -> new BlockItem(ModBLocks.RED_ONION_CROP.get(), properties));

    public static final DeferredItem<Item> TURNIP_SEEDS = ITEMS.registerItem("turnip_seeds",
            properties -> new BlockItem(ModBLocks.TURNIP_CROP.get(), properties));

    public static final DeferredItem<Item> RYE_SEEDS = ITEMS.registerItem("rye_seeds",
            properties -> new BlockItem(ModBLocks.RYE_CROP.get(), properties));

    // Rye grain: like wheat, not eaten as it is but baked (rye bread, black bread)
    public static final DeferredItem<Item> RYE = ITEMS.registerItem("rye", Item::new);

    public static final DeferredItem<Item> BARLEY_SEEDS = ITEMS.registerItem("barley_seeds",
            properties -> new BlockItem(ModBLocks.BARLEY_CROP.get(), properties));
    public static final DeferredItem<Item> BARLEY = ITEMS.registerItem("barley", Item::new);

    public static final DeferredItem<Item> OAT_SEEDS = ITEMS.registerItem("oat_seeds",
            properties -> new BlockItem(ModBLocks.OAT_CROP.get(), properties));
    public static final DeferredItem<Item> OAT = ITEMS.registerItem("oat", Item::new);

    // Cotton spins into string (the tailor does it too) and is woven into cloth; hemp is a cash crop
    public static final DeferredItem<Item> COTTON_SEEDS = ITEMS.registerItem("cotton_seeds",
            properties -> new BlockItem(ModBLocks.COTTON_CROP.get(), properties));
    public static final DeferredItem<Item> COTTON = ITEMS.registerItem("cotton", Item::new);

    public static final DeferredItem<Item> HEMP_SEEDS = ITEMS.registerItem("hemp_seeds",
            properties -> new BlockItem(ModBLocks.HEMP_CROP.get(), properties));
    public static final DeferredItem<Item> HEMP = ITEMS.registerItem("hemp", Item::new);

    public static final DeferredItem<Item> WILD_ONION_SEEDS = ITEMS.registerItem("wild_onion_seeds",
            properties -> new BlockItem(ModBLocks.WILD_ONION_CROP.get(), properties));

    public static final DeferredItem<Item> ONION_SEEDS = ITEMS.registerItem("onion_seeds",
            properties -> new BlockItem(ModBLocks.ONION_CROP.get(), properties));

    public static final DeferredItem<Item> OPIUM_POPPY_SEEDS = ITEMS.registerItem("opium_poppy_seeds",
            properties -> new BlockItem(ModBLocks.OPIUM_POPPY_CROP.get(), properties));

    public static final DeferredItem<Item> CABBAGE_SEEDS = ITEMS.registerItem("cabbage_seeds",
            properties -> new BlockItem(ModBLocks.CABBAGE_CROP.get(), properties));
    public static final DeferredItem<Item> BEAN_SEEDS = ITEMS.registerItem("bean_seeds",
            properties -> new BlockItem(ModBLocks.BEAN_CROP.get(), properties));

    public static final DeferredItem<Item> CHICKPEA_SEEDS = ITEMS.registerItem("chickpea_seeds",
            properties -> new BlockItem(ModBLocks.CHICKPEA_CROP.get(), properties));

    public static final DeferredItem<Item> CUCUMBER_SEEDS = ITEMS.registerItem("cucumber_seeds",
            properties -> new BlockItem(ModBLocks.CUCUMBER_CROP.get(), properties));

    public static final DeferredItem<Item> GREEN_BEAN_SEEDS = ITEMS.registerItem("green_bean_seeds",
            properties -> new BlockItem(ModBLocks.GREEN_BEAN_CROP.get(), properties));

    public static final DeferredItem<Item> SPINACH_SEEDS = ITEMS.registerItem("spinach_seeds",
            properties -> new BlockItem(ModBLocks.SPINACH_CROP.get(), properties));

    public static final DeferredItem<Item> DRAGON_PEPPER_SEEDS = ITEMS.registerItem("dragon_pepper_seeds",
            properties -> new BlockItem(ModBLocks.DRAGON_PEPPER_CROP.get(), properties));

    public static final DeferredItem<Item> PEPPER_SEEDS = ITEMS.registerItem("pepper_seeds",
            properties -> new BlockItem(ModBLocks.PEPPER_CROP.get(), properties));

    public static final DeferredItem<Item> PEPPERCORN_SEEDS = ITEMS.registerItem("peppercorn_seeds",
            properties -> new BlockItem(ModBLocks.PEPPERCORN_CROP.get(), properties));



    public static final DeferredItem<Item> STRAWBERRY_SEEDS = ITEMS.registerItem("strawberry_seeds",
            properties -> new BlockItem(ModBLocks.STRAWBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> BLACKBERRY_SEEDS = ITEMS.registerItem("blackberry_seeds",
            properties -> new BlockItem(ModBLocks.BLACKBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> BLUEBERRY_SEEDS = ITEMS.registerItem("blueberry_seeds",
            properties -> new BlockItem(ModBLocks.BLUEBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> MULBERRY_SEEDS = ITEMS.registerItem("mulberry_seeds",
            properties -> new BlockItem(ModBLocks.MULBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> RASPBERRY_SEEDS = ITEMS.registerItem("raspberry_seeds",
            properties -> new BlockItem(ModBLocks.RASPBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> SMOKEBERRY_SEEDS = ITEMS.registerItem("smokeberry_seeds",
            properties -> new BlockItem(ModBLocks.SMOKEBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> CORN_SEEDS = ITEMS.registerItem("corn_seeds",
            properties -> new BlockItem(ModBLocks.CORN_CROP.get(), properties));

    public static final DeferredItem<Item> CORN_MIDDLE_SEEDS = ITEMS.registerItem("corn_middle_seeds",
            properties -> new BlockItem(ModBLocks.CORN_CROP_MIDDLE.get(), properties));

    public static final DeferredItem<Item> CORN_TOP_SEEDS = ITEMS.registerItem("corn_top_seeds",
            properties -> new BlockItem(ModBLocks.CORN_CROP_TOP.get(), properties));




    // ---------------------------(BOATS)--------------------------- //

    public static final DeferredItem<Item> FISHING_BOAT = ITEMS.registerItem("fishing_boat",
            properties -> new net.darkflameproduction.agotmod.item.custom.FishingBoatItem(properties.stacksTo(1)));

    // ---------------------------(SPAWN EGGS)--------------------------- //

    public static final DeferredItem<Item> MAMMOTH_SPAWN_EGG = ITEMS.registerItem("mammoth_spawn_egg",
            properties -> new SpawnEggItem(ModEntities.MAMMOTH_ENTITY.get(),
                    0x422a0d,
                    0xfffac2,
                    properties));

    public static final DeferredItem<Item> CROW_SPAWN_EGG = ITEMS.registerItem("crow_spawn_egg",
            properties -> new SpawnEggItem(ModEntities.CROW_ENTITY.get(),
                    0x0f0f0f,
                    0x333333,
                    properties));

    public static final DeferredItem<Item> DIREWOLF_SPAWN_EGG = ITEMS.registerItem("direwolf_spawn_egg",
            properties -> new SpawnEggItem(ModEntities.DIREWOLF_ENTITY.get(),
                    0x575757,
                    0x919191,
                    properties));

    public static final DeferredItem<Item> PEASANT_SPAWN_EGG = ITEMS.registerItem("peasant_spawn_egg",
            properties -> new SpawnEggItem(ModEntities.PEASANT_ENTITY.get(),
                    0x575757,
                    0x333333,
                    properties));


    // Town Hall (existing)
    public static final DeferredItem<Item> TOWN_HALL = ITEMS.registerItem("town_hall",
            properties -> new BlockItem(ModBLocks.TOWN_HALL.get(), properties));

    // All Job Barrel Item Registrations
    public static final DeferredItem<Item> ALEHOUSE_BARREL = ITEMS.registerItem("alehouse_barrel_icon",
            properties -> new BlockItem(ModBLocks.ALEHOUSE_BARREL.get(), properties));

    public static final DeferredItem<Item> CARPENTER_BARREL = ITEMS.registerItem("carpenter_barrel_icon",
            properties -> new BlockItem(ModBLocks.CARPENTER_BARREL.get(), properties));

    public static final DeferredItem<Item> ARMORSMITH_BARREL = ITEMS.registerItem("armorsmith_barrel_icon",
            properties -> new BlockItem(ModBLocks.ARMORSMITH_BARREL.get(), properties));

    public static final DeferredItem<Item> BAKER_BARREL = ITEMS.registerItem("baker_barrel_icon",
            properties -> new BlockItem(ModBLocks.BAKER_BARREL.get(), properties));

    public static final DeferredItem<Item> BANKER_BARREL = ITEMS.registerItem("banker_barrel_icon",
            properties -> new BlockItem(ModBLocks.BANKER_BARREL.get(), properties));

    public static final DeferredItem<Item> BARD_BARREL = ITEMS.registerItem("bard_barrel_icon",
            properties -> new BlockItem(ModBLocks.BARD_BARREL.get(), properties));

    public static final DeferredItem<Item> BLACKSMITH_BARREL = ITEMS.registerItem("blacksmith_barrel_icon",
            properties -> new BlockItem(ModBLocks.BLACKSMITH_BARREL.get(), properties));

    public static final DeferredItem<Item> BUILDER_BARREL = ITEMS.registerItem("builder_barrel_icon",
            properties -> new BlockItem(ModBLocks.BUILDER_BARREL.get(), properties));

    public static final DeferredItem<Item> BUTCHER_BARREL = ITEMS.registerItem("butcher_barrel_icon",
            properties -> new BlockItem(ModBLocks.BUTCHER_BARREL.get(), properties));

    public static final DeferredItem<Item> CARAVAN_MASTER_BARREL = ITEMS.registerItem("caravan_master_barrel_icon",
            properties -> new BlockItem(ModBLocks.CARAVAN_MASTER_BARREL.get(), properties));

    public static final DeferredItem<Item> CATTLE_HERDER_BARREL = ITEMS.registerItem("cattle_herder_barrel_icon",
            properties -> new BlockItem(ModBLocks.CATTLE_HERDER_BARREL.get(), properties));

    public static final DeferredItem<Item> CHARCOAL_BURNER_BARREL = ITEMS.registerItem("charcoal_burner_barrel_icon",
            properties -> new BlockItem(ModBLocks.CHARCOAL_BURNER_BARREL.get(), properties));

    public static final DeferredItem<Item> CHICKEN_BREEDER_BARREL = ITEMS.registerItem("chicken_breeder_barrel_icon",
            properties -> new BlockItem(ModBLocks.CHICKEN_BREEDER_BARREL.get(), properties));

    public static final DeferredItem<Item> FARMER_BARREL = ITEMS.registerItem("farmer_barrel_icon",
            properties -> new BlockItem(ModBLocks.FARMER_BARREL.get(), properties));

    public static final DeferredItem<Item> GOAT_HERDER_BARREL = ITEMS.registerItem("goat_herder_barrel_icon",
            properties -> new BlockItem(ModBLocks.GOAT_HERDER_BARREL.get(), properties));

    public static final DeferredItem<Item> GROCER_BARREL = ITEMS.registerItem("grocer_barrel_icon",
            properties -> new BlockItem(ModBLocks.GROCER_BARREL.get(), properties));

    public static final DeferredItem<Item> GUARD_BARREL = ITEMS.registerItem("guard_barrel_icon",
            properties -> new BlockItem(ModBLocks.GUARD_BARREL.get(), properties));

    public static final DeferredItem<Item> HERBALIST_BARREL = ITEMS.registerItem("herbalist_barrel_icon",
            properties -> new BlockItem(ModBLocks.HERBALIST_BARREL.get(), properties));

    public static final DeferredItem<Item> HORSE_BREEDER_BARREL = ITEMS.registerItem("horse_breeder_barrel_icon",
            properties -> new BlockItem(ModBLocks.HORSE_BREEDER_BARREL.get(), properties));

    public static final DeferredItem<Item> HUNTER_BARREL = ITEMS.registerItem("hunter_barrel_icon",
            properties -> new BlockItem(ModBLocks.HUNTER_BARREL.get(), properties));

    public static final DeferredItem<Item> INNKEEPER_BARREL = ITEMS.registerItem("innkeeper_barrel_icon",
            properties -> new BlockItem(ModBLocks.INNKEEPER_BARREL.get(), properties));

    public static final DeferredItem<Item> JEWELER_BARREL = ITEMS.registerItem("jeweler_barrel_icon",
            properties -> new BlockItem(ModBLocks.JEWELER_BARREL.get(), properties));

    public static final DeferredItem<Item> LUMBERJACK_BARREL = ITEMS.registerItem("lumberjack_barrel_icon",
            properties -> new BlockItem(ModBLocks.LUMBERJACK_BARREL.get(), properties));

    public static final DeferredItem<Item> MAESTER_BARREL = ITEMS.registerItem("maester_barrel_icon",
            properties -> new BlockItem(ModBLocks.MAESTER_BARREL.get(), properties));

    public static final DeferredItem<Item> MINER_BARREL = ITEMS.registerItem("miner_barrel_icon",
            properties -> new BlockItem(ModBLocks.MINER_BARREL.get(), properties));

    public static final DeferredItem<Item> PIG_BREEDER_BARREL = ITEMS.registerItem("pig_breeder_barrel_icon",
            properties -> new BlockItem(ModBLocks.PIG_BREEDER_BARREL.get(), properties));

    public static final DeferredItem<Item> PYROMANCER_BARREL = ITEMS.registerItem("pyromancer_barrel_icon",
            properties -> new BlockItem(ModBLocks.PYROMANCER_BARREL.get(), properties));

    public static final DeferredItem<Item> QUARRY_BARREL = ITEMS.registerItem("quarry_barrel_icon",
            properties -> new BlockItem(ModBLocks.QUARRY_BARREL.get(), properties));

    public static final DeferredItem<Item> SCRIBE_BARREL = ITEMS.registerItem("scribe_barrel_icon",
            properties -> new BlockItem(ModBLocks.SCRIBE_BARREL.get(), properties));

    public static final DeferredItem<Item> SEPTON_BARREL = ITEMS.registerItem("septon_barrel_icon",
            properties -> new BlockItem(ModBLocks.SEPTON_BARREL.get(), properties));

    public static final DeferredItem<Item> SHEEP_HERDER_BARREL = ITEMS.registerItem("sheep_herder_barrel_icon",
            properties -> new BlockItem(ModBLocks.SHEEP_HERDER_BARREL.get(), properties));

    public static final DeferredItem<Item> SHIPWRIGHT_BARREL = ITEMS.registerItem("shipwright_barrel_icon",
            properties -> new BlockItem(ModBLocks.SHIPWRIGHT_BARREL.get(), properties));

    public static final DeferredItem<Item> SLAVER_BARREL = ITEMS.registerItem("slaver_barrel_icon",
            properties -> new BlockItem(ModBLocks.SLAVER_BARREL.get(), properties));

    public static final DeferredItem<Item> SMELTER_BARREL = ITEMS.registerItem("smelter_barrel_icon",
            properties -> new BlockItem(ModBLocks.SMELTER_BARREL.get(), properties));

    public static final DeferredItem<Item> STONEMASON_BARREL = ITEMS.registerItem("stonemason_barrel_icon",
            properties -> new BlockItem(ModBLocks.STONEMASON_BARREL.get(), properties));

    public static final DeferredItem<Item> SWORDSMITH_BARREL = ITEMS.registerItem("swordsmith_barrel_icon",
            properties -> new BlockItem(ModBLocks.SWORDSMITH_BARREL.get(), properties));

    public static final DeferredItem<Item> TAILOR_BARREL = ITEMS.registerItem("tailor_barrel_icon",
            properties -> new BlockItem(ModBLocks.TAILOR_BARREL.get(), properties));

    public static final DeferredItem<Item> TANNER_BARREL = ITEMS.registerItem("tanner_barrel_icon",
            properties -> new BlockItem(ModBLocks.TANNER_BARREL.get(), properties));

    public static final DeferredItem<Item> TRADER_BARREL = ITEMS.registerItem("trader_barrel_icon",
            properties -> new BlockItem(ModBLocks.TRADER_BARREL.get(), properties));

    //Furniture
    public static final DeferredItem<Item> OAK_TABLE = ITEMS.registerItem("oak_table", p -> new BlockItem(ModBLocks.OAK_TABLE.get(), p));
    public static final DeferredItem<Item> DARK_OAK_TABLE = ITEMS.registerItem("dark_oak_table", p -> new BlockItem(ModBLocks.DARK_OAK_TABLE.get(), p));
    public static final DeferredItem<Item> SPRUCE_TABLE = ITEMS.registerItem("spruce_table", p -> new BlockItem(ModBLocks.SPRUCE_TABLE.get(), p));
    public static final DeferredItem<Item> BIRCH_TABLE = ITEMS.registerItem("birch_table", p -> new BlockItem(ModBLocks.BIRCH_TABLE.get(), p));
    public static final DeferredItem<Item> JUNGLE_TABLE = ITEMS.registerItem("jungle_table", p -> new BlockItem(ModBLocks.JUNGLE_TABLE.get(), p));
    public static final DeferredItem<Item> ACACIA_TABLE = ITEMS.registerItem("acacia_table", p -> new BlockItem(ModBLocks.ACACIA_TABLE.get(), p));
    public static final DeferredItem<Item> MANGROVE_TABLE = ITEMS.registerItem("mangrove_table", p -> new BlockItem(ModBLocks.MANGROVE_TABLE.get(), p));
    public static final DeferredItem<Item> CHERRY_TABLE = ITEMS.registerItem("cherry_table", p -> new BlockItem(ModBLocks.CHERRY_TABLE.get(), p));
    public static final DeferredItem<Item> BAMBOO_TABLE = ITEMS.registerItem("bamboo_table", p -> new BlockItem(ModBLocks.BAMBOO_TABLE.get(), p));
    public static final DeferredItem<Item> CRIMSON_TABLE = ITEMS.registerItem("crimson_table", p -> new BlockItem(ModBLocks.CRIMSON_TABLE.get(), p));
    public static final DeferredItem<Item> WARPED_TABLE = ITEMS.registerItem("warped_table", p -> new BlockItem(ModBLocks.WARPED_TABLE.get(), p));

    // MOD
    public static final DeferredItem<Item> WEIRWOOD_TABLE = ITEMS.registerItem("weirwood_table", p -> new BlockItem(ModBLocks.WEIRWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> CHARRED_TABLE = ITEMS.registerItem("charred_table", p -> new BlockItem(ModBLocks.CHARRED_TABLE.get(), p));
    public static final DeferredItem<Item> ROTTEN_TABLE = ITEMS.registerItem("rotten_table", p -> new BlockItem(ModBLocks.ROTTEN_TABLE.get(), p));
    public static final DeferredItem<Item> PINE_TABLE = ITEMS.registerItem("pine_table", p -> new BlockItem(ModBLocks.PINE_TABLE.get(), p));
    public static final DeferredItem<Item> ASH_TABLE = ITEMS.registerItem("ash_table", p -> new BlockItem(ModBLocks.ASH_TABLE.get(), p));
    public static final DeferredItem<Item> BEECH_TABLE = ITEMS.registerItem("beech_table", p -> new BlockItem(ModBLocks.BEECH_TABLE.get(), p));
    public static final DeferredItem<Item> CEDAR_TABLE = ITEMS.registerItem("cedar_table", p -> new BlockItem(ModBLocks.CEDAR_TABLE.get(), p));
    public static final DeferredItem<Item> CHESTNUT_TABLE = ITEMS.registerItem("chestnut_table", p -> new BlockItem(ModBLocks.CHESTNUT_TABLE.get(), p));
    public static final DeferredItem<Item> HAWTHORN_TABLE = ITEMS.registerItem("hawthorn_table", p -> new BlockItem(ModBLocks.HAWTHORN_TABLE.get(), p));
    public static final DeferredItem<Item> IRONWOOD_TABLE = ITEMS.registerItem("ironwood_table", p -> new BlockItem(ModBLocks.IRONWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> SENTINEL_TABLE = ITEMS.registerItem("sentinel_table", p -> new BlockItem(ModBLocks.SENTINEL_TABLE.get(), p));
    public static final DeferredItem<Item> SYCAMORE_TABLE = ITEMS.registerItem("sycamore_table", p -> new BlockItem(ModBLocks.SYCAMORE_TABLE.get(), p));
    public static final DeferredItem<Item> BLACKBARK_TABLE = ITEMS.registerItem("blackbark_table", p -> new BlockItem(ModBLocks.BLACKBARK_TABLE.get(), p));
    public static final DeferredItem<Item> ASPEN_TABLE = ITEMS.registerItem("aspen_table", p -> new BlockItem(ModBLocks.ASPEN_TABLE.get(), p));
    public static final DeferredItem<Item> ALDER_TABLE = ITEMS.registerItem("alder_table", p -> new BlockItem(ModBLocks.ALDER_TABLE.get(), p));
    public static final DeferredItem<Item> BLACK_CHERRY_TABLE = ITEMS.registerItem("black_cherry_table", p -> new BlockItem(ModBLocks.BLACK_CHERRY_TABLE.get(), p));
    public static final DeferredItem<Item> BLACK_OLIVE_TABLE = ITEMS.registerItem("black_olive_table", p -> new BlockItem(ModBLocks.BLACK_OLIVE_TABLE.get(), p));
    public static final DeferredItem<Item> CRABAPPLE_TABLE = ITEMS.registerItem("crabapple_table", p -> new BlockItem(ModBLocks.CRABAPPLE_TABLE.get(), p));
    public static final DeferredItem<Item> OLIVE_TABLE = ITEMS.registerItem("olive_table", p -> new BlockItem(ModBLocks.OLIVE_TABLE.get(), p));
    public static final DeferredItem<Item> WHITE_CHERRY_TABLE = ITEMS.registerItem("white_cherry_table", p -> new BlockItem(ModBLocks.WHITE_CHERRY_TABLE.get(), p));
    public static final DeferredItem<Item> RED_CHERRY_TABLE = ITEMS.registerItem("red_cherry_table", p -> new BlockItem(ModBLocks.RED_CHERRY_TABLE.get(), p));
    public static final DeferredItem<Item> FIR_TABLE = ITEMS.registerItem("fir_table", p -> new BlockItem(ModBLocks.FIR_TABLE.get(), p));
    public static final DeferredItem<Item> WILLOW_TABLE = ITEMS.registerItem("willow_table", p -> new BlockItem(ModBLocks.WILLOW_TABLE.get(), p));
    public static final DeferredItem<Item> WORMTREE_TABLE = ITEMS.registerItem("wormtree_table", p -> new BlockItem(ModBLocks.WORMTREE_TABLE.get(), p));
    public static final DeferredItem<Item> ALMOND_TABLE = ITEMS.registerItem("almond_table", p -> new BlockItem(ModBLocks.ALMOND_TABLE.get(), p));
    public static final DeferredItem<Item> APPLE_TABLE = ITEMS.registerItem("apple_table", p -> new BlockItem(ModBLocks.APPLE_TABLE.get(), p));
    public static final DeferredItem<Item> APRICOT_TABLE = ITEMS.registerItem("apricot_table", p -> new BlockItem(ModBLocks.APRICOT_TABLE.get(), p));
    public static final DeferredItem<Item> BAOBAB_TABLE = ITEMS.registerItem("baobab_table", p -> new BlockItem(ModBLocks.BAOBAB_TABLE.get(), p));
    public static final DeferredItem<Item> BLACK_COTTONWOOD_TABLE = ITEMS.registerItem("black_cottonwood_table", p -> new BlockItem(ModBLocks.BLACK_COTTONWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> BLACKTHORN_TABLE = ITEMS.registerItem("blackthorn_table", p -> new BlockItem(ModBLocks.BLACKTHORN_TABLE.get(), p));
    public static final DeferredItem<Item> BLOOD_ORANGE_TABLE = ITEMS.registerItem("blood_orange_table", p -> new BlockItem(ModBLocks.BLOOD_ORANGE_TABLE.get(), p));
    public static final DeferredItem<Item> BLOODWOOD_TABLE = ITEMS.registerItem("bloodwood_table", p -> new BlockItem(ModBLocks.BLOODWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> BLUE_MAHOE_TABLE = ITEMS.registerItem("blue_mahoe_table", p -> new BlockItem(ModBLocks.BLUE_MAHOE_TABLE.get(), p));
    public static final DeferredItem<Item> COTTONWOOD_TABLE = ITEMS.registerItem("cottonwood_table", p -> new BlockItem(ModBLocks.COTTONWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> DATEPALM_TABLE = ITEMS.registerItem("datepalm_table", p -> new BlockItem(ModBLocks.DATEPALM_TABLE.get(), p));
    public static final DeferredItem<Item> EBONY_TABLE = ITEMS.registerItem("ebony_table", p -> new BlockItem(ModBLocks.EBONY_TABLE.get(), p));
    public static final DeferredItem<Item> FIG_TABLE = ITEMS.registerItem("fig_table", p -> new BlockItem(ModBLocks.FIG_TABLE.get(), p));
    public static final DeferredItem<Item> FIREPLUM_TABLE = ITEMS.registerItem("fireplum_table", p -> new BlockItem(ModBLocks.FIREPLUM_TABLE.get(), p));
    public static final DeferredItem<Item> GOLDENHEART_TABLE = ITEMS.registerItem("goldenheart_table", p -> new BlockItem(ModBLocks.GOLDENHEART_TABLE.get(), p));
    public static final DeferredItem<Item> LEMON_TABLE = ITEMS.registerItem("lemon_table", p -> new BlockItem(ModBLocks.LEMON_TABLE.get(), p));
    public static final DeferredItem<Item> LIME_TABLE = ITEMS.registerItem("lime_table", p -> new BlockItem(ModBLocks.LIME_TABLE.get(), p));
    public static final DeferredItem<Item> LINDEN_TABLE = ITEMS.registerItem("linden_table", p -> new BlockItem(ModBLocks.LINDEN_TABLE.get(), p));
    public static final DeferredItem<Item> MAHOGANY_TABLE = ITEMS.registerItem("mahogany_table", p -> new BlockItem(ModBLocks.MAHOGANY_TABLE.get(), p));
    public static final DeferredItem<Item> MAPLE_TABLE = ITEMS.registerItem("maple_table", p -> new BlockItem(ModBLocks.MAPLE_TABLE.get(), p));
    public static final DeferredItem<Item> MYRRH_TABLE = ITEMS.registerItem("myrrh_table", p -> new BlockItem(ModBLocks.MYRRH_TABLE.get(), p));
    public static final DeferredItem<Item> NIGHTWOOD_TABLE = ITEMS.registerItem("nightwood_table", p -> new BlockItem(ModBLocks.NIGHTWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> NUTMEG_TABLE = ITEMS.registerItem("nutmeg_table", p -> new BlockItem(ModBLocks.NUTMEG_TABLE.get(), p));
    public static final DeferredItem<Item> ORANGE_TABLE = ITEMS.registerItem("orange_table", p -> new BlockItem(ModBLocks.ORANGE_TABLE.get(), p));
    public static final DeferredItem<Item> PEACH_TABLE = ITEMS.registerItem("peach_table", p -> new BlockItem(ModBLocks.PEACH_TABLE.get(), p));
    public static final DeferredItem<Item> PEAR_TABLE = ITEMS.registerItem("pear_table", p -> new BlockItem(ModBLocks.PEAR_TABLE.get(), p));
    public static final DeferredItem<Item> PECAN_TABLE = ITEMS.registerItem("pecan_table", p -> new BlockItem(ModBLocks.PECAN_TABLE.get(), p));
    public static final DeferredItem<Item> PERSIMMON_TABLE = ITEMS.registerItem("persimmon_table", p -> new BlockItem(ModBLocks.PERSIMMON_TABLE.get(), p));
    public static final DeferredItem<Item> PINK_IVORY_TABLE = ITEMS.registerItem("pink_ivory_table", p -> new BlockItem(ModBLocks.PINK_IVORY_TABLE.get(), p));
    public static final DeferredItem<Item> PLUM_TABLE = ITEMS.registerItem("plum_table", p -> new BlockItem(ModBLocks.PLUM_TABLE.get(), p));
    public static final DeferredItem<Item> POMEGRANATE_TABLE = ITEMS.registerItem("pomegranate_table", p -> new BlockItem(ModBLocks.POMEGRANATE_TABLE.get(), p));
    public static final DeferredItem<Item> PURPLEHEART_TABLE = ITEMS.registerItem("purpleheart_table", p -> new BlockItem(ModBLocks.PURPLEHEART_TABLE.get(), p));
    public static final DeferredItem<Item> REDWOOD_TABLE = ITEMS.registerItem("redwood_table", p -> new BlockItem(ModBLocks.REDWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> SANDALWOOD_TABLE = ITEMS.registerItem("sandalwood_table", p -> new BlockItem(ModBLocks.SANDALWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> SANDBEGGAR_TABLE = ITEMS.registerItem("sandbeggar_table", p -> new BlockItem(ModBLocks.SANDBEGGAR_TABLE.get(), p));
    public static final DeferredItem<Item> TIGERWOOD_TABLE = ITEMS.registerItem("tigerwood_table", p -> new BlockItem(ModBLocks.TIGERWOOD_TABLE.get(), p));
    public static final DeferredItem<Item> YEW_TABLE = ITEMS.registerItem("yew_table", p -> new BlockItem(ModBLocks.YEW_TABLE.get(), p));
    public static final DeferredItem<Item> SOLDIER_PINE_TABLE = ITEMS.registerItem("soldier_pine_table", p -> new BlockItem(ModBLocks.SOLDIER_PINE_TABLE.get(), p));
    public static final DeferredItem<Item> BLUE_SOLDIER_PINE_TABLE = ITEMS.registerItem("blue_soldier_pine_table", p -> new BlockItem(ModBLocks.BLUE_SOLDIER_PINE_TABLE.get(), p));


    public static final DeferredItem<Item> DARK_OAK_STOOL = ITEMS.registerItem("dark_oak_stool", p -> new BlockItem(ModBLocks.DARK_OAK_STOOL.get(), p));
    public static final DeferredItem<Item> OAK_STOOL = ITEMS.registerItem("oak_stool", p -> new BlockItem(ModBLocks.OAK_STOOL.get(), p));
    public static final DeferredItem<Item> SPRUCE_STOOL = ITEMS.registerItem("spruce_stool", p -> new BlockItem(ModBLocks.SPRUCE_STOOL.get(), p));
    public static final DeferredItem<Item> BIRCH_STOOL = ITEMS.registerItem("birch_stool", p -> new BlockItem(ModBLocks.BIRCH_STOOL.get(), p));
    public static final DeferredItem<Item> JUNGLE_STOOL = ITEMS.registerItem("jungle_stool", p -> new BlockItem(ModBLocks.JUNGLE_STOOL.get(), p));
    public static final DeferredItem<Item> ACACIA_STOOL = ITEMS.registerItem("acacia_stool", p -> new BlockItem(ModBLocks.ACACIA_STOOL.get(), p));
    public static final DeferredItem<Item> MANGROVE_STOOL = ITEMS.registerItem("mangrove_stool", p -> new BlockItem(ModBLocks.MANGROVE_STOOL.get(), p));
    public static final DeferredItem<Item> CHERRY_STOOL = ITEMS.registerItem("cherry_stool", p -> new BlockItem(ModBLocks.CHERRY_STOOL.get(), p));
    public static final DeferredItem<Item> BAMBOO_STOOL = ITEMS.registerItem("bamboo_stool", p -> new BlockItem(ModBLocks.BAMBOO_STOOL.get(), p));
    public static final DeferredItem<Item> CRIMSON_STOOL = ITEMS.registerItem("crimson_stool", p -> new BlockItem(ModBLocks.CRIMSON_STOOL.get(), p));
    public static final DeferredItem<Item> WARPED_STOOL = ITEMS.registerItem("warped_stool", p -> new BlockItem(ModBLocks.WARPED_STOOL.get(), p));
    public static final DeferredItem<Item> WEIRWOOD_STOOL = ITEMS.registerItem("weirwood_stool", p -> new BlockItem(ModBLocks.WEIRWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> CHARRED_STOOL = ITEMS.registerItem("charred_stool", p -> new BlockItem(ModBLocks.CHARRED_STOOL.get(), p));
    public static final DeferredItem<Item> ROTTEN_STOOL = ITEMS.registerItem("rotten_stool", p -> new BlockItem(ModBLocks.ROTTEN_STOOL.get(), p));
    public static final DeferredItem<Item> PINE_STOOL = ITEMS.registerItem("pine_stool", p -> new BlockItem(ModBLocks.PINE_STOOL.get(), p));
    public static final DeferredItem<Item> ASH_STOOL = ITEMS.registerItem("ash_stool", p -> new BlockItem(ModBLocks.ASH_STOOL.get(), p));
    public static final DeferredItem<Item> BEECH_STOOL = ITEMS.registerItem("beech_stool", p -> new BlockItem(ModBLocks.BEECH_STOOL.get(), p));
    public static final DeferredItem<Item> CEDAR_STOOL = ITEMS.registerItem("cedar_stool", p -> new BlockItem(ModBLocks.CEDAR_STOOL.get(), p));
    public static final DeferredItem<Item> CHESTNUT_STOOL = ITEMS.registerItem("chestnut_stool", p -> new BlockItem(ModBLocks.CHESTNUT_STOOL.get(), p));
    public static final DeferredItem<Item> HAWTHORN_STOOL = ITEMS.registerItem("hawthorn_stool", p -> new BlockItem(ModBLocks.HAWTHORN_STOOL.get(), p));
    public static final DeferredItem<Item> IRONWOOD_STOOL = ITEMS.registerItem("ironwood_stool", p -> new BlockItem(ModBLocks.IRONWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> SENTINEL_STOOL = ITEMS.registerItem("sentinel_stool", p -> new BlockItem(ModBLocks.SENTINEL_STOOL.get(), p));
    public static final DeferredItem<Item> SYCAMORE_STOOL = ITEMS.registerItem("sycamore_stool", p -> new BlockItem(ModBLocks.SYCAMORE_STOOL.get(), p));
    public static final DeferredItem<Item> BLACKBARK_STOOL = ITEMS.registerItem("blackbark_stool", p -> new BlockItem(ModBLocks.BLACKBARK_STOOL.get(), p));
    public static final DeferredItem<Item> ASPEN_STOOL = ITEMS.registerItem("aspen_stool", p -> new BlockItem(ModBLocks.ASPEN_STOOL.get(), p));
    public static final DeferredItem<Item> ALDER_STOOL = ITEMS.registerItem("alder_stool", p -> new BlockItem(ModBLocks.ALDER_STOOL.get(), p));
    public static final DeferredItem<Item> BLACK_CHERRY_STOOL = ITEMS.registerItem("black_cherry_stool", p -> new BlockItem(ModBLocks.BLACK_CHERRY_STOOL.get(), p));
    public static final DeferredItem<Item> BLACK_OLIVE_STOOL = ITEMS.registerItem("black_olive_stool", p -> new BlockItem(ModBLocks.BLACK_OLIVE_STOOL.get(), p));
    public static final DeferredItem<Item> CRABAPPLE_STOOL = ITEMS.registerItem("crabapple_stool", p -> new BlockItem(ModBLocks.CRABAPPLE_STOOL.get(), p));
    public static final DeferredItem<Item> OLIVE_STOOL = ITEMS.registerItem("olive_stool", p -> new BlockItem(ModBLocks.OLIVE_STOOL.get(), p));
    public static final DeferredItem<Item> WHITE_CHERRY_STOOL = ITEMS.registerItem("white_cherry_stool", p -> new BlockItem(ModBLocks.WHITE_CHERRY_STOOL.get(), p));
    public static final DeferredItem<Item> RED_CHERRY_STOOL = ITEMS.registerItem("red_cherry_stool", p -> new BlockItem(ModBLocks.RED_CHERRY_STOOL.get(), p));
    public static final DeferredItem<Item> FIR_STOOL = ITEMS.registerItem("fir_stool", p -> new BlockItem(ModBLocks.FIR_STOOL.get(), p));
    public static final DeferredItem<Item> WILLOW_STOOL = ITEMS.registerItem("willow_stool", p -> new BlockItem(ModBLocks.WILLOW_STOOL.get(), p));
    public static final DeferredItem<Item> WORMTREE_STOOL = ITEMS.registerItem("wormtree_stool", p -> new BlockItem(ModBLocks.WORMTREE_STOOL.get(), p));
    public static final DeferredItem<Item> ALMOND_STOOL = ITEMS.registerItem("almond_stool", p -> new BlockItem(ModBLocks.ALMOND_STOOL.get(), p));
    public static final DeferredItem<Item> APPLE_STOOL = ITEMS.registerItem("apple_stool", p -> new BlockItem(ModBLocks.APPLE_STOOL.get(), p));
    public static final DeferredItem<Item> APRICOT_STOOL = ITEMS.registerItem("apricot_stool", p -> new BlockItem(ModBLocks.APRICOT_STOOL.get(), p));
    public static final DeferredItem<Item> BAOBAB_STOOL = ITEMS.registerItem("baobab_stool", p -> new BlockItem(ModBLocks.BAOBAB_STOOL.get(), p));
    public static final DeferredItem<Item> BLACK_COTTONWOOD_STOOL = ITEMS.registerItem("black_cottonwood_stool", p -> new BlockItem(ModBLocks.BLACK_COTTONWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> BLACKTHORN_STOOL = ITEMS.registerItem("blackthorn_stool", p -> new BlockItem(ModBLocks.BLACKTHORN_STOOL.get(), p));
    public static final DeferredItem<Item> BLOOD_ORANGE_STOOL = ITEMS.registerItem("blood_orange_stool", p -> new BlockItem(ModBLocks.BLOOD_ORANGE_STOOL.get(), p));
    public static final DeferredItem<Item> BLOODWOOD_STOOL = ITEMS.registerItem("bloodwood_stool", p -> new BlockItem(ModBLocks.BLOODWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> BLUE_MAHOE_STOOL = ITEMS.registerItem("blue_mahoe_stool", p -> new BlockItem(ModBLocks.BLUE_MAHOE_STOOL.get(), p));
    public static final DeferredItem<Item> COTTONWOOD_STOOL = ITEMS.registerItem("cottonwood_stool", p -> new BlockItem(ModBLocks.COTTONWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> DATEPALM_STOOL = ITEMS.registerItem("datepalm_stool", p -> new BlockItem(ModBLocks.DATEPALM_STOOL.get(), p));
    public static final DeferredItem<Item> EBONY_STOOL = ITEMS.registerItem("ebony_stool", p -> new BlockItem(ModBLocks.EBONY_STOOL.get(), p));
    public static final DeferredItem<Item> FIG_STOOL = ITEMS.registerItem("fig_stool", p -> new BlockItem(ModBLocks.FIG_STOOL.get(), p));
    public static final DeferredItem<Item> FIREPLUM_STOOL = ITEMS.registerItem("fireplum_stool", p -> new BlockItem(ModBLocks.FIREPLUM_STOOL.get(), p));
    public static final DeferredItem<Item> GOLDENHEART_STOOL = ITEMS.registerItem("goldenheart_stool", p -> new BlockItem(ModBLocks.GOLDENHEART_STOOL.get(), p));
    public static final DeferredItem<Item> LEMON_STOOL = ITEMS.registerItem("lemon_stool", p -> new BlockItem(ModBLocks.LEMON_STOOL.get(), p));
    public static final DeferredItem<Item> LIME_STOOL = ITEMS.registerItem("lime_stool", p -> new BlockItem(ModBLocks.LIME_STOOL.get(), p));
    public static final DeferredItem<Item> LINDEN_STOOL = ITEMS.registerItem("linden_stool", p -> new BlockItem(ModBLocks.LINDEN_STOOL.get(), p));
    public static final DeferredItem<Item> MAHOGANY_STOOL = ITEMS.registerItem("mahogany_stool", p -> new BlockItem(ModBLocks.MAHOGANY_STOOL.get(), p));
    public static final DeferredItem<Item> MAPLE_STOOL = ITEMS.registerItem("maple_stool", p -> new BlockItem(ModBLocks.MAPLE_STOOL.get(), p));
    public static final DeferredItem<Item> MYRRH_STOOL = ITEMS.registerItem("myrrh_stool", p -> new BlockItem(ModBLocks.MYRRH_STOOL.get(), p));
    public static final DeferredItem<Item> NIGHTWOOD_STOOL = ITEMS.registerItem("nightwood_stool", p -> new BlockItem(ModBLocks.NIGHTWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> NUTMEG_STOOL = ITEMS.registerItem("nutmeg_stool", p -> new BlockItem(ModBLocks.NUTMEG_STOOL.get(), p));
    public static final DeferredItem<Item> ORANGE_STOOL = ITEMS.registerItem("orange_stool", p -> new BlockItem(ModBLocks.ORANGE_STOOL.get(), p));
    public static final DeferredItem<Item> PEACH_STOOL = ITEMS.registerItem("peach_stool", p -> new BlockItem(ModBLocks.PEACH_STOOL.get(), p));
    public static final DeferredItem<Item> PEAR_STOOL = ITEMS.registerItem("pear_stool", p -> new BlockItem(ModBLocks.PEAR_STOOL.get(), p));
    public static final DeferredItem<Item> PECAN_STOOL = ITEMS.registerItem("pecan_stool", p -> new BlockItem(ModBLocks.PECAN_STOOL.get(), p));
    public static final DeferredItem<Item> PERSIMMON_STOOL = ITEMS.registerItem("persimmon_stool", p -> new BlockItem(ModBLocks.PERSIMMON_STOOL.get(), p));
    public static final DeferredItem<Item> PINK_IVORY_STOOL = ITEMS.registerItem("pink_ivory_stool", p -> new BlockItem(ModBLocks.PINK_IVORY_STOOL.get(), p));
    public static final DeferredItem<Item> PLUM_STOOL = ITEMS.registerItem("plum_stool", p -> new BlockItem(ModBLocks.PLUM_STOOL.get(), p));
    public static final DeferredItem<Item> POMEGRANATE_STOOL = ITEMS.registerItem("pomegranate_stool", p -> new BlockItem(ModBLocks.POMEGRANATE_STOOL.get(), p));
    public static final DeferredItem<Item> PURPLEHEART_STOOL = ITEMS.registerItem("purpleheart_stool", p -> new BlockItem(ModBLocks.PURPLEHEART_STOOL.get(), p));
    public static final DeferredItem<Item> REDWOOD_STOOL = ITEMS.registerItem("redwood_stool", p -> new BlockItem(ModBLocks.REDWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> SANDALWOOD_STOOL = ITEMS.registerItem("sandalwood_stool", p -> new BlockItem(ModBLocks.SANDALWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> SANDBEGGAR_STOOL = ITEMS.registerItem("sandbeggar_stool", p -> new BlockItem(ModBLocks.SANDBEGGAR_STOOL.get(), p));
    public static final DeferredItem<Item> TIGERWOOD_STOOL = ITEMS.registerItem("tigerwood_stool", p -> new BlockItem(ModBLocks.TIGERWOOD_STOOL.get(), p));
    public static final DeferredItem<Item> YEW_STOOL = ITEMS.registerItem("yew_stool", p -> new BlockItem(ModBLocks.YEW_STOOL.get(), p));
    public static final DeferredItem<Item> SOLDIER_PINE_STOOL = ITEMS.registerItem("soldier_pine_stool", p -> new BlockItem(ModBLocks.SOLDIER_PINE_STOOL.get(), p));
    public static final DeferredItem<Item> BLUE_SOLDIER_PINE_STOOL = ITEMS.registerItem("blue_soldier_pine_stool", p -> new BlockItem(ModBLocks.BLUE_SOLDIER_PINE_STOOL.get(), p));

    public static final DeferredItem<Item> BARREL_LARGE  = ITEMS.registerItem("barrel_large",  p -> new net.darkflameproduction.agotmod.item.custom.BarrelBlockItem(ModBLocks.BARREL_LARGE.get(), p, "barrel_large"));
    public static final DeferredItem<Item> BARREL_MEDIUM = ITEMS.registerItem("barrel_medium", p -> new net.darkflameproduction.agotmod.item.custom.BarrelBlockItem(ModBLocks.BARREL_MEDIUM.get(), p, "barrel_medium"));
    public static final DeferredItem<Item> BARREL_SMALL  = ITEMS.registerItem("barrel_small",  p -> new net.darkflameproduction.agotmod.item.custom.BarrelBlockItem(ModBLocks.BARREL_SMALL.get(), p, "barrel_small"));

    public static final DeferredItem<Item> DARK_OAK_CHAIR = ITEMS.registerItem("dark_oak_chair", p -> new BlockItem(ModBLocks.DARK_OAK_CHAIR.get(), p));
    public static final DeferredItem<Item> OAK_CHAIR = ITEMS.registerItem("oak_chair", p -> new BlockItem(ModBLocks.OAK_CHAIR.get(), p));
    public static final DeferredItem<Item> SPRUCE_CHAIR = ITEMS.registerItem("spruce_chair", p -> new BlockItem(ModBLocks.SPRUCE_CHAIR.get(), p));
    public static final DeferredItem<Item> BIRCH_CHAIR = ITEMS.registerItem("birch_chair", p -> new BlockItem(ModBLocks.BIRCH_CHAIR.get(), p));
    public static final DeferredItem<Item> JUNGLE_CHAIR = ITEMS.registerItem("jungle_chair", p -> new BlockItem(ModBLocks.JUNGLE_CHAIR.get(), p));
    public static final DeferredItem<Item> ACACIA_CHAIR = ITEMS.registerItem("acacia_chair", p -> new BlockItem(ModBLocks.ACACIA_CHAIR.get(), p));
    public static final DeferredItem<Item> MANGROVE_CHAIR = ITEMS.registerItem("mangrove_chair", p -> new BlockItem(ModBLocks.MANGROVE_CHAIR.get(), p));
    public static final DeferredItem<Item> CHERRY_CHAIR = ITEMS.registerItem("cherry_chair", p -> new BlockItem(ModBLocks.CHERRY_CHAIR.get(), p));
    public static final DeferredItem<Item> BAMBOO_CHAIR = ITEMS.registerItem("bamboo_chair", p -> new BlockItem(ModBLocks.BAMBOO_CHAIR.get(), p));
    public static final DeferredItem<Item> CRIMSON_CHAIR = ITEMS.registerItem("crimson_chair", p -> new BlockItem(ModBLocks.CRIMSON_CHAIR.get(), p));
    public static final DeferredItem<Item> WARPED_CHAIR = ITEMS.registerItem("warped_chair", p -> new BlockItem(ModBLocks.WARPED_CHAIR.get(), p));
    public static final DeferredItem<Item> WEIRWOOD_CHAIR = ITEMS.registerItem("weirwood_chair", p -> new BlockItem(ModBLocks.WEIRWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> CHARRED_CHAIR = ITEMS.registerItem("charred_chair", p -> new BlockItem(ModBLocks.CHARRED_CHAIR.get(), p));
    public static final DeferredItem<Item> ROTTEN_CHAIR = ITEMS.registerItem("rotten_chair", p -> new BlockItem(ModBLocks.ROTTEN_CHAIR.get(), p));
    public static final DeferredItem<Item> PINE_CHAIR = ITEMS.registerItem("pine_chair", p -> new BlockItem(ModBLocks.PINE_CHAIR.get(), p));
    public static final DeferredItem<Item> ASH_CHAIR = ITEMS.registerItem("ash_chair", p -> new BlockItem(ModBLocks.ASH_CHAIR.get(), p));
    public static final DeferredItem<Item> BEECH_CHAIR = ITEMS.registerItem("beech_chair", p -> new BlockItem(ModBLocks.BEECH_CHAIR.get(), p));
    public static final DeferredItem<Item> CEDAR_CHAIR = ITEMS.registerItem("cedar_chair", p -> new BlockItem(ModBLocks.CEDAR_CHAIR.get(), p));
    public static final DeferredItem<Item> CHESTNUT_CHAIR = ITEMS.registerItem("chestnut_chair", p -> new BlockItem(ModBLocks.CHESTNUT_CHAIR.get(), p));
    public static final DeferredItem<Item> HAWTHORN_CHAIR = ITEMS.registerItem("hawthorn_chair", p -> new BlockItem(ModBLocks.HAWTHORN_CHAIR.get(), p));
    public static final DeferredItem<Item> IRONWOOD_CHAIR = ITEMS.registerItem("ironwood_chair", p -> new BlockItem(ModBLocks.IRONWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> SENTINEL_CHAIR = ITEMS.registerItem("sentinel_chair", p -> new BlockItem(ModBLocks.SENTINEL_CHAIR.get(), p));
    public static final DeferredItem<Item> SYCAMORE_CHAIR = ITEMS.registerItem("sycamore_chair", p -> new BlockItem(ModBLocks.SYCAMORE_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACKBARK_CHAIR = ITEMS.registerItem("blackbark_chair", p -> new BlockItem(ModBLocks.BLACKBARK_CHAIR.get(), p));
    public static final DeferredItem<Item> ASPEN_CHAIR = ITEMS.registerItem("aspen_chair", p -> new BlockItem(ModBLocks.ASPEN_CHAIR.get(), p));
    public static final DeferredItem<Item> ALDER_CHAIR = ITEMS.registerItem("alder_chair", p -> new BlockItem(ModBLocks.ALDER_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACK_CHERRY_CHAIR = ITEMS.registerItem("black_cherry_chair", p -> new BlockItem(ModBLocks.BLACK_CHERRY_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACK_OLIVE_CHAIR = ITEMS.registerItem("black_olive_chair", p -> new BlockItem(ModBLocks.BLACK_OLIVE_CHAIR.get(), p));
    public static final DeferredItem<Item> CRABAPPLE_CHAIR = ITEMS.registerItem("crabapple_chair", p -> new BlockItem(ModBLocks.CRABAPPLE_CHAIR.get(), p));
    public static final DeferredItem<Item> OLIVE_CHAIR = ITEMS.registerItem("olive_chair", p -> new BlockItem(ModBLocks.OLIVE_CHAIR.get(), p));
    public static final DeferredItem<Item> WHITE_CHERRY_CHAIR = ITEMS.registerItem("white_cherry_chair", p -> new BlockItem(ModBLocks.WHITE_CHERRY_CHAIR.get(), p));
    public static final DeferredItem<Item> RED_CHERRY_CHAIR = ITEMS.registerItem("red_cherry_chair", p -> new BlockItem(ModBLocks.RED_CHERRY_CHAIR.get(), p));
    public static final DeferredItem<Item> FIR_CHAIR = ITEMS.registerItem("fir_chair", p -> new BlockItem(ModBLocks.FIR_CHAIR.get(), p));
    public static final DeferredItem<Item> WILLOW_CHAIR = ITEMS.registerItem("willow_chair", p -> new BlockItem(ModBLocks.WILLOW_CHAIR.get(), p));
    public static final DeferredItem<Item> WORMTREE_CHAIR = ITEMS.registerItem("wormtree_chair", p -> new BlockItem(ModBLocks.WORMTREE_CHAIR.get(), p));
    public static final DeferredItem<Item> ALMOND_CHAIR = ITEMS.registerItem("almond_chair", p -> new BlockItem(ModBLocks.ALMOND_CHAIR.get(), p));
    public static final DeferredItem<Item> APPLE_CHAIR = ITEMS.registerItem("apple_chair", p -> new BlockItem(ModBLocks.APPLE_CHAIR.get(), p));
    public static final DeferredItem<Item> APRICOT_CHAIR = ITEMS.registerItem("apricot_chair", p -> new BlockItem(ModBLocks.APRICOT_CHAIR.get(), p));
    public static final DeferredItem<Item> BAOBAB_CHAIR = ITEMS.registerItem("baobab_chair", p -> new BlockItem(ModBLocks.BAOBAB_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACK_COTTONWOOD_CHAIR = ITEMS.registerItem("black_cottonwood_chair", p -> new BlockItem(ModBLocks.BLACK_COTTONWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACKTHORN_CHAIR = ITEMS.registerItem("blackthorn_chair", p -> new BlockItem(ModBLocks.BLACKTHORN_CHAIR.get(), p));
    public static final DeferredItem<Item> BLOOD_ORANGE_CHAIR = ITEMS.registerItem("blood_orange_chair", p -> new BlockItem(ModBLocks.BLOOD_ORANGE_CHAIR.get(), p));
    public static final DeferredItem<Item> BLOODWOOD_CHAIR = ITEMS.registerItem("bloodwood_chair", p -> new BlockItem(ModBLocks.BLOODWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> BLUE_MAHOE_CHAIR = ITEMS.registerItem("blue_mahoe_chair", p -> new BlockItem(ModBLocks.BLUE_MAHOE_CHAIR.get(), p));
    public static final DeferredItem<Item> COTTONWOOD_CHAIR = ITEMS.registerItem("cottonwood_chair", p -> new BlockItem(ModBLocks.COTTONWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> DATEPALM_CHAIR = ITEMS.registerItem("datepalm_chair", p -> new BlockItem(ModBLocks.DATEPALM_CHAIR.get(), p));
    public static final DeferredItem<Item> EBONY_CHAIR = ITEMS.registerItem("ebony_chair", p -> new BlockItem(ModBLocks.EBONY_CHAIR.get(), p));
    public static final DeferredItem<Item> FIG_CHAIR = ITEMS.registerItem("fig_chair", p -> new BlockItem(ModBLocks.FIG_CHAIR.get(), p));
    public static final DeferredItem<Item> FIREPLUM_CHAIR = ITEMS.registerItem("fireplum_chair", p -> new BlockItem(ModBLocks.FIREPLUM_CHAIR.get(), p));
    public static final DeferredItem<Item> GOLDENHEART_CHAIR = ITEMS.registerItem("goldenheart_chair", p -> new BlockItem(ModBLocks.GOLDENHEART_CHAIR.get(), p));
    public static final DeferredItem<Item> LEMON_CHAIR = ITEMS.registerItem("lemon_chair", p -> new BlockItem(ModBLocks.LEMON_CHAIR.get(), p));
    public static final DeferredItem<Item> LIME_CHAIR = ITEMS.registerItem("lime_chair", p -> new BlockItem(ModBLocks.LIME_CHAIR.get(), p));
    public static final DeferredItem<Item> LINDEN_CHAIR = ITEMS.registerItem("linden_chair", p -> new BlockItem(ModBLocks.LINDEN_CHAIR.get(), p));
    public static final DeferredItem<Item> MAHOGANY_CHAIR = ITEMS.registerItem("mahogany_chair", p -> new BlockItem(ModBLocks.MAHOGANY_CHAIR.get(), p));
    public static final DeferredItem<Item> MAPLE_CHAIR = ITEMS.registerItem("maple_chair", p -> new BlockItem(ModBLocks.MAPLE_CHAIR.get(), p));
    public static final DeferredItem<Item> MYRRH_CHAIR = ITEMS.registerItem("myrrh_chair", p -> new BlockItem(ModBLocks.MYRRH_CHAIR.get(), p));
    public static final DeferredItem<Item> NIGHTWOOD_CHAIR = ITEMS.registerItem("nightwood_chair", p -> new BlockItem(ModBLocks.NIGHTWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> NUTMEG_CHAIR = ITEMS.registerItem("nutmeg_chair", p -> new BlockItem(ModBLocks.NUTMEG_CHAIR.get(), p));
    public static final DeferredItem<Item> ORANGE_CHAIR = ITEMS.registerItem("orange_chair", p -> new BlockItem(ModBLocks.ORANGE_CHAIR.get(), p));
    public static final DeferredItem<Item> PEACH_CHAIR = ITEMS.registerItem("peach_chair", p -> new BlockItem(ModBLocks.PEACH_CHAIR.get(), p));
    public static final DeferredItem<Item> PEAR_CHAIR = ITEMS.registerItem("pear_chair", p -> new BlockItem(ModBLocks.PEAR_CHAIR.get(), p));
    public static final DeferredItem<Item> PECAN_CHAIR = ITEMS.registerItem("pecan_chair", p -> new BlockItem(ModBLocks.PECAN_CHAIR.get(), p));
    public static final DeferredItem<Item> PERSIMMON_CHAIR = ITEMS.registerItem("persimmon_chair", p -> new BlockItem(ModBLocks.PERSIMMON_CHAIR.get(), p));
    public static final DeferredItem<Item> PINK_IVORY_CHAIR = ITEMS.registerItem("pink_ivory_chair", p -> new BlockItem(ModBLocks.PINK_IVORY_CHAIR.get(), p));
    public static final DeferredItem<Item> PLUM_CHAIR = ITEMS.registerItem("plum_chair", p -> new BlockItem(ModBLocks.PLUM_CHAIR.get(), p));
    public static final DeferredItem<Item> POMEGRANATE_CHAIR = ITEMS.registerItem("pomegranate_chair", p -> new BlockItem(ModBLocks.POMEGRANATE_CHAIR.get(), p));
    public static final DeferredItem<Item> PURPLEHEART_CHAIR = ITEMS.registerItem("purpleheart_chair", p -> new BlockItem(ModBLocks.PURPLEHEART_CHAIR.get(), p));
    public static final DeferredItem<Item> REDWOOD_CHAIR = ITEMS.registerItem("redwood_chair", p -> new BlockItem(ModBLocks.REDWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> SANDALWOOD_CHAIR = ITEMS.registerItem("sandalwood_chair", p -> new BlockItem(ModBLocks.SANDALWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> SANDBEGGAR_CHAIR = ITEMS.registerItem("sandbeggar_chair", p -> new BlockItem(ModBLocks.SANDBEGGAR_CHAIR.get(), p));
    public static final DeferredItem<Item> TIGERWOOD_CHAIR = ITEMS.registerItem("tigerwood_chair", p -> new BlockItem(ModBLocks.TIGERWOOD_CHAIR.get(), p));
    public static final DeferredItem<Item> YEW_CHAIR = ITEMS.registerItem("yew_chair", p -> new BlockItem(ModBLocks.YEW_CHAIR.get(), p));
    public static final DeferredItem<Item> SOLDIER_PINE_CHAIR = ITEMS.registerItem("soldier_pine_chair", p -> new BlockItem(ModBLocks.SOLDIER_PINE_CHAIR.get(), p));
    public static final DeferredItem<Item> BLUE_SOLDIER_PINE_CHAIR = ITEMS.registerItem("blue_soldier_pine_chair", p -> new BlockItem(ModBLocks.BLUE_SOLDIER_PINE_CHAIR.get(), p));

    // ── 2b. ModItems.java — ARM CHAIRS ────────────────────────────────────────
    public static final DeferredItem<Item> DARK_OAK_ARM_CHAIR = ITEMS.registerItem("dark_oak_arm_chair", p -> new BlockItem(ModBLocks.DARK_OAK_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> OAK_ARM_CHAIR = ITEMS.registerItem("oak_arm_chair", p -> new BlockItem(ModBLocks.OAK_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> SPRUCE_ARM_CHAIR = ITEMS.registerItem("spruce_arm_chair", p -> new BlockItem(ModBLocks.SPRUCE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BIRCH_ARM_CHAIR = ITEMS.registerItem("birch_arm_chair", p -> new BlockItem(ModBLocks.BIRCH_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> JUNGLE_ARM_CHAIR = ITEMS.registerItem("jungle_arm_chair", p -> new BlockItem(ModBLocks.JUNGLE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> ACACIA_ARM_CHAIR = ITEMS.registerItem("acacia_arm_chair", p -> new BlockItem(ModBLocks.ACACIA_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> MANGROVE_ARM_CHAIR = ITEMS.registerItem("mangrove_arm_chair", p -> new BlockItem(ModBLocks.MANGROVE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> CHERRY_ARM_CHAIR = ITEMS.registerItem("cherry_arm_chair", p -> new BlockItem(ModBLocks.CHERRY_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BAMBOO_ARM_CHAIR = ITEMS.registerItem("bamboo_arm_chair", p -> new BlockItem(ModBLocks.BAMBOO_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> CRIMSON_ARM_CHAIR = ITEMS.registerItem("crimson_arm_chair", p -> new BlockItem(ModBLocks.CRIMSON_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> WARPED_ARM_CHAIR = ITEMS.registerItem("warped_arm_chair", p -> new BlockItem(ModBLocks.WARPED_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> WEIRWOOD_ARM_CHAIR = ITEMS.registerItem("weirwood_arm_chair", p -> new BlockItem(ModBLocks.WEIRWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> CHARRED_ARM_CHAIR = ITEMS.registerItem("charred_arm_chair", p -> new BlockItem(ModBLocks.CHARRED_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> ROTTEN_ARM_CHAIR = ITEMS.registerItem("rotten_arm_chair", p -> new BlockItem(ModBLocks.ROTTEN_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> PINE_ARM_CHAIR = ITEMS.registerItem("pine_arm_chair", p -> new BlockItem(ModBLocks.PINE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> ASH_ARM_CHAIR = ITEMS.registerItem("ash_arm_chair", p -> new BlockItem(ModBLocks.ASH_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BEECH_ARM_CHAIR = ITEMS.registerItem("beech_arm_chair", p -> new BlockItem(ModBLocks.BEECH_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> CEDAR_ARM_CHAIR = ITEMS.registerItem("cedar_arm_chair", p -> new BlockItem(ModBLocks.CEDAR_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> CHESTNUT_ARM_CHAIR = ITEMS.registerItem("chestnut_arm_chair", p -> new BlockItem(ModBLocks.CHESTNUT_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> HAWTHORN_ARM_CHAIR = ITEMS.registerItem("hawthorn_arm_chair", p -> new BlockItem(ModBLocks.HAWTHORN_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> IRONWOOD_ARM_CHAIR = ITEMS.registerItem("ironwood_arm_chair", p -> new BlockItem(ModBLocks.IRONWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> SENTINEL_ARM_CHAIR = ITEMS.registerItem("sentinel_arm_chair", p -> new BlockItem(ModBLocks.SENTINEL_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> SYCAMORE_ARM_CHAIR = ITEMS.registerItem("sycamore_arm_chair", p -> new BlockItem(ModBLocks.SYCAMORE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACKBARK_ARM_CHAIR = ITEMS.registerItem("blackbark_arm_chair", p -> new BlockItem(ModBLocks.BLACKBARK_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> ASPEN_ARM_CHAIR = ITEMS.registerItem("aspen_arm_chair", p -> new BlockItem(ModBLocks.ASPEN_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> ALDER_ARM_CHAIR = ITEMS.registerItem("alder_arm_chair", p -> new BlockItem(ModBLocks.ALDER_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACK_CHERRY_ARM_CHAIR = ITEMS.registerItem("black_cherry_arm_chair", p -> new BlockItem(ModBLocks.BLACK_CHERRY_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACK_OLIVE_ARM_CHAIR = ITEMS.registerItem("black_olive_arm_chair", p -> new BlockItem(ModBLocks.BLACK_OLIVE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> CRABAPPLE_ARM_CHAIR = ITEMS.registerItem("crabapple_arm_chair", p -> new BlockItem(ModBLocks.CRABAPPLE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> OLIVE_ARM_CHAIR = ITEMS.registerItem("olive_arm_chair", p -> new BlockItem(ModBLocks.OLIVE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> WHITE_CHERRY_ARM_CHAIR = ITEMS.registerItem("white_cherry_arm_chair", p -> new BlockItem(ModBLocks.WHITE_CHERRY_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> RED_CHERRY_ARM_CHAIR = ITEMS.registerItem("red_cherry_arm_chair", p -> new BlockItem(ModBLocks.RED_CHERRY_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> FIR_ARM_CHAIR = ITEMS.registerItem("fir_arm_chair", p -> new BlockItem(ModBLocks.FIR_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> WILLOW_ARM_CHAIR = ITEMS.registerItem("willow_arm_chair", p -> new BlockItem(ModBLocks.WILLOW_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> WORMTREE_ARM_CHAIR = ITEMS.registerItem("wormtree_arm_chair", p -> new BlockItem(ModBLocks.WORMTREE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> ALMOND_ARM_CHAIR = ITEMS.registerItem("almond_arm_chair", p -> new BlockItem(ModBLocks.ALMOND_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> APPLE_ARM_CHAIR = ITEMS.registerItem("apple_arm_chair", p -> new BlockItem(ModBLocks.APPLE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> APRICOT_ARM_CHAIR = ITEMS.registerItem("apricot_arm_chair", p -> new BlockItem(ModBLocks.APRICOT_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BAOBAB_ARM_CHAIR = ITEMS.registerItem("baobab_arm_chair", p -> new BlockItem(ModBLocks.BAOBAB_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACK_COTTONWOOD_ARM_CHAIR = ITEMS.registerItem("black_cottonwood_arm_chair", p -> new BlockItem(ModBLocks.BLACK_COTTONWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLACKTHORN_ARM_CHAIR = ITEMS.registerItem("blackthorn_arm_chair", p -> new BlockItem(ModBLocks.BLACKTHORN_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLOOD_ORANGE_ARM_CHAIR = ITEMS.registerItem("blood_orange_arm_chair", p -> new BlockItem(ModBLocks.BLOOD_ORANGE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLOODWOOD_ARM_CHAIR = ITEMS.registerItem("bloodwood_arm_chair", p -> new BlockItem(ModBLocks.BLOODWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLUE_MAHOE_ARM_CHAIR = ITEMS.registerItem("blue_mahoe_arm_chair", p -> new BlockItem(ModBLocks.BLUE_MAHOE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> COTTONWOOD_ARM_CHAIR = ITEMS.registerItem("cottonwood_arm_chair", p -> new BlockItem(ModBLocks.COTTONWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> DATEPALM_ARM_CHAIR = ITEMS.registerItem("datepalm_arm_chair", p -> new BlockItem(ModBLocks.DATEPALM_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> EBONY_ARM_CHAIR = ITEMS.registerItem("ebony_arm_chair", p -> new BlockItem(ModBLocks.EBONY_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> FIG_ARM_CHAIR = ITEMS.registerItem("fig_arm_chair", p -> new BlockItem(ModBLocks.FIG_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> FIREPLUM_ARM_CHAIR = ITEMS.registerItem("fireplum_arm_chair", p -> new BlockItem(ModBLocks.FIREPLUM_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> GOLDENHEART_ARM_CHAIR = ITEMS.registerItem("goldenheart_arm_chair", p -> new BlockItem(ModBLocks.GOLDENHEART_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> LEMON_ARM_CHAIR = ITEMS.registerItem("lemon_arm_chair", p -> new BlockItem(ModBLocks.LEMON_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> LIME_ARM_CHAIR = ITEMS.registerItem("lime_arm_chair", p -> new BlockItem(ModBLocks.LIME_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> LINDEN_ARM_CHAIR = ITEMS.registerItem("linden_arm_chair", p -> new BlockItem(ModBLocks.LINDEN_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> MAHOGANY_ARM_CHAIR = ITEMS.registerItem("mahogany_arm_chair", p -> new BlockItem(ModBLocks.MAHOGANY_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> MAPLE_ARM_CHAIR = ITEMS.registerItem("maple_arm_chair", p -> new BlockItem(ModBLocks.MAPLE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> MYRRH_ARM_CHAIR = ITEMS.registerItem("myrrh_arm_chair", p -> new BlockItem(ModBLocks.MYRRH_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> NIGHTWOOD_ARM_CHAIR = ITEMS.registerItem("nightwood_arm_chair", p -> new BlockItem(ModBLocks.NIGHTWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> NUTMEG_ARM_CHAIR = ITEMS.registerItem("nutmeg_arm_chair", p -> new BlockItem(ModBLocks.NUTMEG_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> ORANGE_ARM_CHAIR = ITEMS.registerItem("orange_arm_chair", p -> new BlockItem(ModBLocks.ORANGE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> PEACH_ARM_CHAIR = ITEMS.registerItem("peach_arm_chair", p -> new BlockItem(ModBLocks.PEACH_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> PEAR_ARM_CHAIR = ITEMS.registerItem("pear_arm_chair", p -> new BlockItem(ModBLocks.PEAR_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> PECAN_ARM_CHAIR = ITEMS.registerItem("pecan_arm_chair", p -> new BlockItem(ModBLocks.PECAN_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> PERSIMMON_ARM_CHAIR = ITEMS.registerItem("persimmon_arm_chair", p -> new BlockItem(ModBLocks.PERSIMMON_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> PINK_IVORY_ARM_CHAIR = ITEMS.registerItem("pink_ivory_arm_chair", p -> new BlockItem(ModBLocks.PINK_IVORY_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> PLUM_ARM_CHAIR = ITEMS.registerItem("plum_arm_chair", p -> new BlockItem(ModBLocks.PLUM_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> POMEGRANATE_ARM_CHAIR = ITEMS.registerItem("pomegranate_arm_chair", p -> new BlockItem(ModBLocks.POMEGRANATE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> PURPLEHEART_ARM_CHAIR = ITEMS.registerItem("purpleheart_arm_chair", p -> new BlockItem(ModBLocks.PURPLEHEART_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> REDWOOD_ARM_CHAIR = ITEMS.registerItem("redwood_arm_chair", p -> new BlockItem(ModBLocks.REDWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> SANDALWOOD_ARM_CHAIR = ITEMS.registerItem("sandalwood_arm_chair", p -> new BlockItem(ModBLocks.SANDALWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> SANDBEGGAR_ARM_CHAIR = ITEMS.registerItem("sandbeggar_arm_chair", p -> new BlockItem(ModBLocks.SANDBEGGAR_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> TIGERWOOD_ARM_CHAIR = ITEMS.registerItem("tigerwood_arm_chair", p -> new BlockItem(ModBLocks.TIGERWOOD_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> YEW_ARM_CHAIR = ITEMS.registerItem("yew_arm_chair", p -> new BlockItem(ModBLocks.YEW_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> SOLDIER_PINE_ARM_CHAIR = ITEMS.registerItem("soldier_pine_arm_chair", p -> new BlockItem(ModBLocks.SOLDIER_PINE_ARM_CHAIR.get(), p));
    public static final DeferredItem<Item> BLUE_SOLDIER_PINE_ARM_CHAIR = ITEMS.registerItem("blue_soldier_pine_arm_chair", p -> new BlockItem(ModBLocks.BLUE_SOLDIER_PINE_ARM_CHAIR.get(), p));


    // Tells the AGoTMod class to call the modded items into the game
    public static void register(IEventBus eventBus) {
        net.darkflameproduction.agotmod.armor.ModArmorSets.register();
        net.darkflameproduction.agotmod.item.food.ModFoodItems.registerAll();
        ITEMS.register(eventBus);
    }
}





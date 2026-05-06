package net.darkflameproduction.agotmod.util;

import java.util.HashMap;
import java.util.Map;

public class ItemPricing {

    private static final Map<String, Integer> ALLOWED_ITEMS_PRICING = new HashMap<>();

    static {
        // ── GROCER ITEMS ──────────────────────────────────────────────────────────────

        // Vanilla Crops and Seeds
        ALLOWED_ITEMS_PRICING.put("minecraft:wheat",             2);
        ALLOWED_ITEMS_PRICING.put("minecraft:wheat_seeds",       8);
        ALLOWED_ITEMS_PRICING.put("minecraft:pumpkin",           23);
        ALLOWED_ITEMS_PRICING.put("minecraft:pumpkin_seeds",     6);
        ALLOWED_ITEMS_PRICING.put("minecraft:melon_slice",       23);
        ALLOWED_ITEMS_PRICING.put("minecraft:melon_seeds",       6);
        ALLOWED_ITEMS_PRICING.put("minecraft:beetroot",          8);
        ALLOWED_ITEMS_PRICING.put("minecraft:beetroot_seeds",    31);
        ALLOWED_ITEMS_PRICING.put("minecraft:carrot",            3);
        ALLOWED_ITEMS_PRICING.put("minecraft:potato",            4);
        ALLOWED_ITEMS_PRICING.put("minecraft:poisonous_potato",  1);

        // Mod Vegetables & Seeds
        ALLOWED_ITEMS_PRICING.put("agotmod:horseradish",         7);
        ALLOWED_ITEMS_PRICING.put("agotmod:horseradish_seeds",   28);
        ALLOWED_ITEMS_PRICING.put("agotmod:onion",               4);
        ALLOWED_ITEMS_PRICING.put("agotmod:onion_seeds",         16);
        ALLOWED_ITEMS_PRICING.put("agotmod:red_onion",           5);
        ALLOWED_ITEMS_PRICING.put("agotmod:red_onion_seeds",     19);
        ALLOWED_ITEMS_PRICING.put("agotmod:wild_onion",          7);
        ALLOWED_ITEMS_PRICING.put("agotmod:wild_onion_seeds",    25);
        ALLOWED_ITEMS_PRICING.put("agotmod:leek",                6);
        ALLOWED_ITEMS_PRICING.put("agotmod:leek_seeds",          23);
        ALLOWED_ITEMS_PRICING.put("agotmod:neep",                8);
        ALLOWED_ITEMS_PRICING.put("agotmod:neep_seeds",          31);
        ALLOWED_ITEMS_PRICING.put("agotmod:turnip",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:turnip_seeds",        23);
        ALLOWED_ITEMS_PRICING.put("agotmod:parsley",             4);
        ALLOWED_ITEMS_PRICING.put("agotmod:parsley_seeds",       16);
        ALLOWED_ITEMS_PRICING.put("agotmod:bean",                4);
        ALLOWED_ITEMS_PRICING.put("agotmod:bean_seeds",          13);
        ALLOWED_ITEMS_PRICING.put("agotmod:green_bean",          4);
        ALLOWED_ITEMS_PRICING.put("agotmod:green_bean_seeds",    16);
        ALLOWED_ITEMS_PRICING.put("agotmod:chickpea",            6);
        ALLOWED_ITEMS_PRICING.put("agotmod:chickpea_seeds",      22);
        ALLOWED_ITEMS_PRICING.put("agotmod:cabbage",             8);
        ALLOWED_ITEMS_PRICING.put("agotmod:cabbage_seeds",       31);
        ALLOWED_ITEMS_PRICING.put("agotmod:spinach",             5);
        ALLOWED_ITEMS_PRICING.put("agotmod:spinach_seeds",       19);
        ALLOWED_ITEMS_PRICING.put("agotmod:cucumber",            8);
        ALLOWED_ITEMS_PRICING.put("agotmod:cucumber_seeds",      31);
        ALLOWED_ITEMS_PRICING.put("agotmod:dragon_pepper",       10);
        ALLOWED_ITEMS_PRICING.put("agotmod:dragon_pepper_seeds", 37);
        ALLOWED_ITEMS_PRICING.put("agotmod:pepper",              9);
        ALLOWED_ITEMS_PRICING.put("agotmod:pepper_seeds",        34);
        ALLOWED_ITEMS_PRICING.put("agotmod:peppercorn",          12);
        ALLOWED_ITEMS_PRICING.put("agotmod:peppercorn_seeds",    46);
        ALLOWED_ITEMS_PRICING.put("agotmod:barley",              2);
        ALLOWED_ITEMS_PRICING.put("agotmod:barley_seeds",        8);
        ALLOWED_ITEMS_PRICING.put("agotmod:oat",                 3);
        ALLOWED_ITEMS_PRICING.put("agotmod:oat_seeds",           10);
        ALLOWED_ITEMS_PRICING.put("agotmod:opium_poppy_seeds",   46);
        ALLOWED_ITEMS_PRICING.put("agotmod:cotton",              10);
        ALLOWED_ITEMS_PRICING.put("agotmod:cotton_seeds",        37);
        ALLOWED_ITEMS_PRICING.put("agotmod:hemp",                12);
        ALLOWED_ITEMS_PRICING.put("agotmod:hemp_seeds",          46);

        // Berries
        ALLOWED_ITEMS_PRICING.put("agotmod:strawberry",          17);
        ALLOWED_ITEMS_PRICING.put("agotmod:strawberry_seeds",    68);
        ALLOWED_ITEMS_PRICING.put("agotmod:blackberry",          17);
        ALLOWED_ITEMS_PRICING.put("agotmod:blackberry_seeds",    68);
        ALLOWED_ITEMS_PRICING.put("agotmod:blueberry",           17);
        ALLOWED_ITEMS_PRICING.put("agotmod:blueberry_seeds",     68);
        ALLOWED_ITEMS_PRICING.put("agotmod:mulberry",            17);
        ALLOWED_ITEMS_PRICING.put("agotmod:mulberry_seeds",      68);
        ALLOWED_ITEMS_PRICING.put("agotmod:raspberry",           17);
        ALLOWED_ITEMS_PRICING.put("agotmod:raspberry_seeds",     68);
        ALLOWED_ITEMS_PRICING.put("agotmod:smokeberry",          17);
        ALLOWED_ITEMS_PRICING.put("agotmod:smokeberry_seeds",    68);

        // Vegetables - Additional
        ALLOWED_ITEMS_PRICING.put("agotmod:garlic",              5);
        ALLOWED_ITEMS_PRICING.put("agotmod:olives",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:peas",                4);
        ALLOWED_ITEMS_PRICING.put("agotmod:radish",              4);
        ALLOWED_ITEMS_PRICING.put("agotmod:lentils",             5);
        ALLOWED_ITEMS_PRICING.put("agotmod:squash",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:corn",                4);

        // Fruits
        ALLOWED_ITEMS_PRICING.put("agotmod:cherry",              8);
        ALLOWED_ITEMS_PRICING.put("agotmod:crabapple",           7);
        ALLOWED_ITEMS_PRICING.put("agotmod:apricot",             8);
        ALLOWED_ITEMS_PRICING.put("agotmod:date",                9);
        ALLOWED_ITEMS_PRICING.put("agotmod:fig",                 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:grape",               8);
        ALLOWED_ITEMS_PRICING.put("agotmod:raisins",             9);
        ALLOWED_ITEMS_PRICING.put("agotmod:dried_apples",        8);
        ALLOWED_ITEMS_PRICING.put("agotmod:lemon",               7);
        ALLOWED_ITEMS_PRICING.put("agotmod:lime",                7);
        ALLOWED_ITEMS_PRICING.put("agotmod:orange",              9);
        ALLOWED_ITEMS_PRICING.put("agotmod:blood_orange",        10);
        ALLOWED_ITEMS_PRICING.put("agotmod:peach",               9);
        ALLOWED_ITEMS_PRICING.put("agotmod:winter_peach",        10);
        ALLOWED_ITEMS_PRICING.put("agotmod:pear",                9);
        ALLOWED_ITEMS_PRICING.put("agotmod:persimmon",           9);
        ALLOWED_ITEMS_PRICING.put("agotmod:plum",                8);
        ALLOWED_ITEMS_PRICING.put("agotmod:dornish_plum",        10);
        ALLOWED_ITEMS_PRICING.put("agotmod:fireplum",            10);
        ALLOWED_ITEMS_PRICING.put("agotmod:pomegranate",         10);

        // Nuts
        ALLOWED_ITEMS_PRICING.put("agotmod:almond",              8);
        ALLOWED_ITEMS_PRICING.put("agotmod:chestnut",            9);
        ALLOWED_ITEMS_PRICING.put("agotmod:pecan",               8);
        ALLOWED_ITEMS_PRICING.put("agotmod:pine_nut",            9);
        ALLOWED_ITEMS_PRICING.put("agotmod:walnut",              8);

        // Herbs
        ALLOWED_ITEMS_PRICING.put("agotmod:fennel",              4);
        ALLOWED_ITEMS_PRICING.put("agotmod:mint",                4);

        // Sweeteners
        ALLOWED_ITEMS_PRICING.put("agotmod:honey",               7);
        ALLOWED_ITEMS_PRICING.put("agotmod:molasses",            6);

        ALLOWED_ITEMS_PRICING.put("minecraft:egg", 5);

        // ── BUTCHER ITEMS ─────────────────────────────────────────────────────────────

        // Vanilla Meats (nutrition × 8)
        ALLOWED_ITEMS_PRICING.put("minecraft:beef",              24);  // nutrition 3
        ALLOWED_ITEMS_PRICING.put("minecraft:porkchop",          24);  // nutrition 3
        ALLOWED_ITEMS_PRICING.put("minecraft:chicken",           16);  // nutrition 2
        ALLOWED_ITEMS_PRICING.put("minecraft:mutton",            16);  // nutrition 2
        ALLOWED_ITEMS_PRICING.put("minecraft:rabbit",            24);  // nutrition 3

        // Raw Sausages (nutrition 2 → 16)
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_sausage",         16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_white_sausage",   16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_blood_sausage",   16);

        // Raw Poultry - Tiny (nutrition 1 → 8)
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_pigeon",          8);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_gull",            8);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_lark",            8);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_quail",           8);

        // Raw Poultry - Medium (nutrition 2 → 16)
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_duck",            16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_capon",           16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_heron",           16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_partridge",       16);

        // Raw Poultry - Large (nutrition 3 → 24)
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_goose",           24);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_peacock",         24);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_swan",            24);

        // Raw Meats - Tiny (nutrition 1 → 8)
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_chicken_nuggets", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_bacon",           8);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_frog",            8);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_squirrel",        8);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_rat",             8);

        // Raw Meats - Small (nutrition 2 → 16)
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_hare_meat",       16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_horse_meat",      16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_goat_meat",       16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_deer_venison",    16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_lamb",            16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_venison",         16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_dog",             16);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_snake",           16);

        // Raw Meats - Medium (nutrition 3 → 24)
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_boar_venison",    24);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_pork",            24);

        // Raw Meats - Large (nutrition 4 → 32)
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_bear_meat",       32);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_mammoth_meat",    32);
        ALLOWED_ITEMS_PRICING.put("agotmod:raw_aurochs",         32);

        // ── TANNER ITEMS ──────────────────────────────────────────────────────────────
        ALLOWED_ITEMS_PRICING.put("minecraft:leather",      12);
        ALLOWED_ITEMS_PRICING.put("minecraft:rabbit_hide",  4);
        ALLOWED_ITEMS_PRICING.put("minecraft:bundle",       18);
        ALLOWED_ITEMS_PRICING.put("agotmod:boiled_leather", 16);

        // ── TAILOR ITEMS ──────────────────────────────────────────────────────────────

// String and textile chain
        ALLOWED_ITEMS_PRICING.put("minecraft:string",               10);
        ALLOWED_ITEMS_PRICING.put("minecraft:white_wool",           40);
        ALLOWED_ITEMS_PRICING.put("minecraft:orange_wool",          40);
        ALLOWED_ITEMS_PRICING.put("minecraft:magenta_wool",         40);
        ALLOWED_ITEMS_PRICING.put("minecraft:light_blue_wool",      40);
        ALLOWED_ITEMS_PRICING.put("minecraft:yellow_wool",          40);
        ALLOWED_ITEMS_PRICING.put("minecraft:lime_wool",            40);
        ALLOWED_ITEMS_PRICING.put("minecraft:pink_wool",            40);
        ALLOWED_ITEMS_PRICING.put("minecraft:gray_wool",            40);
        ALLOWED_ITEMS_PRICING.put("minecraft:light_gray_wool",      40);
        ALLOWED_ITEMS_PRICING.put("minecraft:cyan_wool",            40);
        ALLOWED_ITEMS_PRICING.put("minecraft:purple_wool",          40);
        ALLOWED_ITEMS_PRICING.put("minecraft:blue_wool",            40);
        ALLOWED_ITEMS_PRICING.put("minecraft:brown_wool",           40);
        ALLOWED_ITEMS_PRICING.put("minecraft:green_wool",           40);
        ALLOWED_ITEMS_PRICING.put("minecraft:red_wool",             40);
        ALLOWED_ITEMS_PRICING.put("minecraft:black_wool",           40);
        ALLOWED_ITEMS_PRICING.put("agotmod:cloth",                  100);
        ALLOWED_ITEMS_PRICING.put("agotmod:fur",                    20);

// Leather armor
        ALLOWED_ITEMS_PRICING.put("minecraft:leather_helmet",       48);
        ALLOWED_ITEMS_PRICING.put("minecraft:leather_chestplate",   80);
        ALLOWED_ITEMS_PRICING.put("minecraft:leather_leggings",     64);
        ALLOWED_ITEMS_PRICING.put("minecraft:leather_boots",        48);

// Dyes
        ALLOWED_ITEMS_PRICING.put("minecraft:white_dye",            65);
        ALLOWED_ITEMS_PRICING.put("minecraft:orange_dye",           65);
        ALLOWED_ITEMS_PRICING.put("minecraft:magenta_dye",          65);
        ALLOWED_ITEMS_PRICING.put("minecraft:light_blue_dye",       65);
        ALLOWED_ITEMS_PRICING.put("minecraft:yellow_dye",           65);
        ALLOWED_ITEMS_PRICING.put("minecraft:lime_dye",             65);
        ALLOWED_ITEMS_PRICING.put("minecraft:pink_dye",             65);
        ALLOWED_ITEMS_PRICING.put("minecraft:gray_dye",             65);
        ALLOWED_ITEMS_PRICING.put("minecraft:light_gray_dye",       65);
        ALLOWED_ITEMS_PRICING.put("minecraft:cyan_dye",             65);
        ALLOWED_ITEMS_PRICING.put("minecraft:purple_dye",           65);
        ALLOWED_ITEMS_PRICING.put("minecraft:blue_dye",             65);
        ALLOWED_ITEMS_PRICING.put("minecraft:brown_dye",            65);
        ALLOWED_ITEMS_PRICING.put("minecraft:green_dye",            65);
        ALLOWED_ITEMS_PRICING.put("minecraft:red_dye",              65);
        ALLOWED_ITEMS_PRICING.put("minecraft:black_dye",            65);

// Flowers — mod flowers
        ALLOWED_ITEMS_PRICING.put("agotmod:winter_rose",            6);
        ALLOWED_ITEMS_PRICING.put("agotmod:wild_radish",            6);
        ALLOWED_ITEMS_PRICING.put("agotmod:white_rose",             6);
        ALLOWED_ITEMS_PRICING.put("agotmod:thorn_bush",             6);
        ALLOWED_ITEMS_PRICING.put("agotmod:thistle",                6);
        ALLOWED_ITEMS_PRICING.put("agotmod:tansy",                  6);
        ALLOWED_ITEMS_PRICING.put("agotmod:spiceflower",            6);
        ALLOWED_ITEMS_PRICING.put("agotmod:sedge",                  6);
        ALLOWED_ITEMS_PRICING.put("agotmod:saffron_crocus",         6);
        ALLOWED_ITEMS_PRICING.put("agotmod:rose",                   6);
        ALLOWED_ITEMS_PRICING.put("agotmod:poison_kisses",          6);
        ALLOWED_ITEMS_PRICING.put("agotmod:pennyroyal",             6);
        ALLOWED_ITEMS_PRICING.put("agotmod:opium_poppy_wild",       6);
        ALLOWED_ITEMS_PRICING.put("agotmod:nightshade",             6);
        ALLOWED_ITEMS_PRICING.put("agotmod:moonbloom",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:lungwort",               6);
        ALLOWED_ITEMS_PRICING.put("agotmod:liverwort",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:lavender",               6);
        ALLOWED_ITEMS_PRICING.put("agotmod:ladys_lace",             6);
        ALLOWED_ITEMS_PRICING.put("agotmod:gorse",                  6);
        ALLOWED_ITEMS_PRICING.put("agotmod:goldenrod",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:goldencup",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:goathead",               6);
        ALLOWED_ITEMS_PRICING.put("agotmod:ginger",                 6);
        ALLOWED_ITEMS_PRICING.put("agotmod:gillyflower",            6);
        ALLOWED_ITEMS_PRICING.put("agotmod:frostfire",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:forget_me_not",          6);
        ALLOWED_ITEMS_PRICING.put("agotmod:evening_star",           6);
        ALLOWED_ITEMS_PRICING.put("agotmod:dusky_rose",             6);
        ALLOWED_ITEMS_PRICING.put("agotmod:dragons_breath",         6);
        ALLOWED_ITEMS_PRICING.put("agotmod:coldsnap",               6);
        ALLOWED_ITEMS_PRICING.put("agotmod:blue_rose",              6);
        ALLOWED_ITEMS_PRICING.put("agotmod:bloodbloom",             6);
        ALLOWED_ITEMS_PRICING.put("agotmod:black_lotus",            6);
        ALLOWED_ITEMS_PRICING.put("agotmod:winter_rose_bush",       10);
        ALLOWED_ITEMS_PRICING.put("agotmod:white_rose_bush",        10);
        ALLOWED_ITEMS_PRICING.put("agotmod:dusky_rose_bush",        10);
        ALLOWED_ITEMS_PRICING.put("agotmod:blue_rose_bush",         10);
        ALLOWED_ITEMS_PRICING.put("agotmod:red_rose_bush",          10);

// Vanilla flowers
        ALLOWED_ITEMS_PRICING.put("minecraft:poppy",                6);
        ALLOWED_ITEMS_PRICING.put("minecraft:dandelion",            6);
        ALLOWED_ITEMS_PRICING.put("minecraft:blue_orchid",          6);
        ALLOWED_ITEMS_PRICING.put("minecraft:allium",               6);
        ALLOWED_ITEMS_PRICING.put("minecraft:azure_bluet",          6);
        ALLOWED_ITEMS_PRICING.put("minecraft:red_tulip",            6);
        ALLOWED_ITEMS_PRICING.put("minecraft:orange_tulip",         6);
        ALLOWED_ITEMS_PRICING.put("minecraft:white_tulip",          6);
        ALLOWED_ITEMS_PRICING.put("minecraft:pink_tulip",           6);
        ALLOWED_ITEMS_PRICING.put("minecraft:oxeye_daisy",          6);
        ALLOWED_ITEMS_PRICING.put("minecraft:cornflower",           6);
        ALLOWED_ITEMS_PRICING.put("minecraft:lily_of_the_valley",   6);
        ALLOWED_ITEMS_PRICING.put("minecraft:wither_rose",          6);
        ALLOWED_ITEMS_PRICING.put("minecraft:sunflower",            6);
        ALLOWED_ITEMS_PRICING.put("minecraft:lilac",                6);
        ALLOWED_ITEMS_PRICING.put("minecraft:rose_bush",            6);
        ALLOWED_ITEMS_PRICING.put("minecraft:peony",                6);
    }

    public static boolean isGrocerItem(String itemKey) {
        if (!ALLOWED_ITEMS_PRICING.containsKey(itemKey)) return false;
        return !isButcherItem(itemKey);
    }

    public static boolean isButcherItem(String itemKey) {
        if (!ALLOWED_ITEMS_PRICING.containsKey(itemKey)) return false;
        return itemKey.contains("raw_")
                || itemKey.equals("minecraft:beef")
                || itemKey.equals("minecraft:porkchop")
                || itemKey.equals("minecraft:chicken")
                || itemKey.equals("minecraft:mutton")
                || itemKey.equals("minecraft:rabbit");
    }

    public static boolean isItemAllowed(String itemKey) {
        return ALLOWED_ITEMS_PRICING.containsKey(itemKey);
    }

    public static int getItemPrice(String itemKey) {
        return ALLOWED_ITEMS_PRICING.getOrDefault(itemKey, 0);
    }

    public static int getItemSellPrice(String itemKey) {
        int buyPrice = getItemPrice(itemKey);

        if (itemKey.contains("_seeds")) {
            String baseItem = itemKey.replace("_seeds", "");
            if (baseItem.contains("pumpkin") || baseItem.contains("melon")) {
                return buyPrice / 2;
            }
            int baseItemPrice = getItemPrice(baseItem);
            if (baseItemPrice > 0) {
                return baseItemPrice / 2;
            }
        }

        if (itemKey.contains("pumpkin") || itemKey.contains("melon")) {
            return buyPrice / 2;
        }

        return buyPrice / 2;
    }

    public static boolean isTannerItem(String itemKey) {
        if (!ALLOWED_ITEMS_PRICING.containsKey(itemKey)) return false;
        return itemKey.equals("minecraft:leather")
                || itemKey.equals("minecraft:rabbit_hide")
                || itemKey.equals("minecraft:bundle")
                || itemKey.equals("agotmod:boiled_leather");
    }

    public static boolean isTailorItem(String itemKey) {
        if (!ALLOWED_ITEMS_PRICING.containsKey(itemKey)) return false;
        return itemKey.equals("minecraft:string")
                || itemKey.contains("_wool")
                || itemKey.equals("agotmod:cloth")
                || itemKey.equals("agotmod:fur")
                || itemKey.equals("minecraft:leather_helmet")
                || itemKey.equals("minecraft:leather_chestplate")
                || itemKey.equals("minecraft:leather_leggings")
                || itemKey.equals("minecraft:leather_boots")
                || itemKey.contains("_dye")
                || itemKey.startsWith("agotmod:") && isFlower(itemKey)
                || itemKey.startsWith("minecraft:") && isVanillaFlower(itemKey);
    }

    private static boolean isFlower(String itemKey) {
        return itemKey.equals("agotmod:winter_rose") || itemKey.equals("agotmod:wild_radish")
                || itemKey.equals("agotmod:white_rose") || itemKey.equals("agotmod:thorn_bush")
                || itemKey.equals("agotmod:thistle") || itemKey.equals("agotmod:tansy")
                || itemKey.equals("agotmod:spiceflower") || itemKey.equals("agotmod:sedge")
                || itemKey.equals("agotmod:saffron_crocus") || itemKey.equals("agotmod:rose")
                || itemKey.equals("agotmod:poison_kisses") || itemKey.equals("agotmod:pennyroyal")
                || itemKey.equals("agotmod:opium_poppy_wild") || itemKey.equals("agotmod:nightshade")
                || itemKey.equals("agotmod:moonbloom") || itemKey.equals("agotmod:lungwort")
                || itemKey.equals("agotmod:liverwort") || itemKey.equals("agotmod:lavender")
                || itemKey.equals("agotmod:ladys_lace") || itemKey.equals("agotmod:gorse")
                || itemKey.equals("agotmod:goldenrod") || itemKey.equals("agotmod:goldencup")
                || itemKey.equals("agotmod:goathead") || itemKey.equals("agotmod:ginger")
                || itemKey.equals("agotmod:gillyflower") || itemKey.equals("agotmod:frostfire")
                || itemKey.equals("agotmod:forget_me_not") || itemKey.equals("agotmod:evening_star")
                || itemKey.equals("agotmod:dusky_rose") || itemKey.equals("agotmod:dragons_breath")
                || itemKey.equals("agotmod:coldsnap") || itemKey.equals("agotmod:blue_rose")
                || itemKey.equals("agotmod:bloodbloom") || itemKey.equals("agotmod:black_lotus")
                || itemKey.equals("agotmod:winter_rose_bush") || itemKey.equals("agotmod:white_rose_bush")
                || itemKey.equals("agotmod:dusky_rose_bush") || itemKey.equals("agotmod:blue_rose_bush")
                || itemKey.equals("agotmod:red_rose_bush");
    }

    private static boolean isVanillaFlower(String itemKey) {
        return itemKey.equals("minecraft:poppy") || itemKey.equals("minecraft:dandelion")
                || itemKey.equals("minecraft:blue_orchid") || itemKey.equals("minecraft:allium")
                || itemKey.equals("minecraft:azure_bluet") || itemKey.equals("minecraft:red_tulip")
                || itemKey.equals("minecraft:orange_tulip") || itemKey.equals("minecraft:white_tulip")
                || itemKey.equals("minecraft:pink_tulip") || itemKey.equals("minecraft:oxeye_daisy")
                || itemKey.equals("minecraft:cornflower") || itemKey.equals("minecraft:lily_of_the_valley")
                || itemKey.equals("minecraft:wither_rose") || itemKey.equals("minecraft:sunflower")
                || itemKey.equals("minecraft:lilac") || itemKey.equals("minecraft:rose_bush")
                || itemKey.equals("minecraft:peony");
    }

    public static Map<String, Integer> getAllPricing() {
        return new HashMap<>(ALLOWED_ITEMS_PRICING);
    }
}
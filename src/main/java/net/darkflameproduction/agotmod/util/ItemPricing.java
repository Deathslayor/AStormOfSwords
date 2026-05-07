package net.darkflameproduction.agotmod.util;

import java.util.*;

public class ItemPricing {

    public static final String GROCER     = "grocer";
    public static final String BUTCHER    = "butcher";
    public static final String TANNER     = "tanner";
    public static final String TAILOR     = "tailor";
    public static final String BLACKSMITH = "blacksmith";

    private static class ItemData {
        final int price;
        final Set<String> jobs;

        ItemData(int price, String... jobs) {
            this.price = price;
            this.jobs  = new HashSet<>(Arrays.asList(jobs));
        }
    }

    private static final Map<String, ItemData> ITEMS = new LinkedHashMap<>();

    private static void reg(String key, int price, String... jobs) {
        ITEMS.put(key, new ItemData(price, jobs));
    }

    static {

        // ══════════════════════════════════════════════════════════════════════
        // GROCER
        // ══════════════════════════════════════════════════════════════════════

        // Vanilla Crops and Seeds
        reg("minecraft:wheat",               2,  GROCER);
        reg("minecraft:wheat_seeds",         8,  GROCER);
        reg("minecraft:pumpkin",            23,  GROCER);
        reg("minecraft:pumpkin_seeds",       6,  GROCER);
        reg("minecraft:melon_slice",        23,  GROCER);
        reg("minecraft:melon_seeds",         6,  GROCER);
        reg("minecraft:beetroot",            8,  GROCER);
        reg("minecraft:beetroot_seeds",     31,  GROCER);
        reg("minecraft:carrot",              3,  GROCER);
        reg("minecraft:potato",              4,  GROCER);
        reg("minecraft:poisonous_potato",    1,  GROCER);
        reg("minecraft:egg",                 5,  GROCER);

        // Mod Vegetables & Seeds
        reg("agotmod:horseradish",           7,  GROCER);
        reg("agotmod:horseradish_seeds",    28,  GROCER);
        reg("agotmod:onion",                 4,  GROCER);
        reg("agotmod:onion_seeds",          16,  GROCER);
        reg("agotmod:red_onion",             5,  GROCER);
        reg("agotmod:red_onion_seeds",      19,  GROCER);
        reg("agotmod:wild_onion",            7,  GROCER);
        reg("agotmod:wild_onion_seeds",     25,  GROCER);
        reg("agotmod:leek",                  6,  GROCER);
        reg("agotmod:leek_seeds",           23,  GROCER);
        reg("agotmod:neep",                  8,  GROCER);
        reg("agotmod:neep_seeds",           31,  GROCER);
        reg("agotmod:turnip",                6,  GROCER);
        reg("agotmod:turnip_seeds",         23,  GROCER);
        reg("agotmod:parsley",               4,  GROCER);
        reg("agotmod:parsley_seeds",        16,  GROCER);
        reg("agotmod:bean",                  4,  GROCER);
        reg("agotmod:bean_seeds",           13,  GROCER);
        reg("agotmod:green_bean",            4,  GROCER);
        reg("agotmod:green_bean_seeds",     16,  GROCER);
        reg("agotmod:chickpea",              6,  GROCER);
        reg("agotmod:chickpea_seeds",       22,  GROCER);
        reg("agotmod:cabbage",               8,  GROCER);
        reg("agotmod:cabbage_seeds",        31,  GROCER);
        reg("agotmod:spinach",               5,  GROCER);
        reg("agotmod:spinach_seeds",        19,  GROCER);
        reg("agotmod:cucumber",              8,  GROCER);
        reg("agotmod:cucumber_seeds",       31,  GROCER);
        reg("agotmod:dragon_pepper",        10,  GROCER);
        reg("agotmod:dragon_pepper_seeds",  37,  GROCER);
        reg("agotmod:pepper",                9,  GROCER);
        reg("agotmod:pepper_seeds",         34,  GROCER);
        reg("agotmod:peppercorn",           12,  GROCER);
        reg("agotmod:peppercorn_seeds",     46,  GROCER);
        reg("agotmod:barley",                2,  GROCER);
        reg("agotmod:barley_seeds",          8,  GROCER);
        reg("agotmod:oat",                   3,  GROCER);
        reg("agotmod:oat_seeds",            10,  GROCER);
        reg("agotmod:opium_poppy_seeds",    46,  GROCER);
        reg("agotmod:hemp",                 12,  GROCER);
        reg("agotmod:hemp_seeds",           46,  GROCER);

        // Cotton — grocer sells seeds, tailor uses raw cotton
        reg("agotmod:cotton",               10,  GROCER, TAILOR);
        reg("agotmod:cotton_seeds",         37,  GROCER);

        // Berries
        reg("agotmod:strawberry",           17,  GROCER);
        reg("agotmod:strawberry_seeds",     68,  GROCER);
        reg("agotmod:blackberry",           17,  GROCER);
        reg("agotmod:blackberry_seeds",     68,  GROCER);
        reg("agotmod:blueberry",            17,  GROCER);
        reg("agotmod:blueberry_seeds",      68,  GROCER);
        reg("agotmod:mulberry",             17,  GROCER);
        reg("agotmod:mulberry_seeds",       68,  GROCER);
        reg("agotmod:raspberry",            17,  GROCER);
        reg("agotmod:raspberry_seeds",      68,  GROCER);
        reg("agotmod:smokeberry",           17,  GROCER);
        reg("agotmod:smokeberry_seeds",     68,  GROCER);

        // Vegetables
        reg("agotmod:garlic",                5,  GROCER);
        reg("agotmod:olives",                6,  GROCER);
        reg("agotmod:peas",                  4,  GROCER);
        reg("agotmod:radish",                4,  GROCER);
        reg("agotmod:lentils",               5,  GROCER);
        reg("agotmod:squash",                6,  GROCER);
        reg("agotmod:corn",                  4,  GROCER);

        // Fruits
        reg("agotmod:cherry",                8,  GROCER);
        reg("agotmod:crabapple",             7,  GROCER);
        reg("agotmod:apricot",               8,  GROCER);
        reg("agotmod:date",                  9,  GROCER);
        reg("agotmod:fig",                   8,  GROCER);
        reg("agotmod:grape",                 8,  GROCER);
        reg("agotmod:raisins",               9,  GROCER);
        reg("agotmod:dried_apples",          8,  GROCER);
        reg("agotmod:lemon",                 7,  GROCER);
        reg("agotmod:lime",                  7,  GROCER);
        reg("agotmod:orange",                9,  GROCER);
        reg("agotmod:blood_orange",         10,  GROCER);
        reg("agotmod:peach",                 9,  GROCER);
        reg("agotmod:winter_peach",         10,  GROCER);
        reg("agotmod:pear",                  9,  GROCER);
        reg("agotmod:persimmon",             9,  GROCER);
        reg("agotmod:plum",                  8,  GROCER);
        reg("agotmod:dornish_plum",         10,  GROCER);
        reg("agotmod:fireplum",             10,  GROCER);
        reg("agotmod:pomegranate",          10,  GROCER);

        // Nuts
        reg("agotmod:almond",                8,  GROCER);
        reg("agotmod:chestnut",              9,  GROCER);
        reg("agotmod:pecan",                 8,  GROCER);
        reg("agotmod:pine_nut",              9,  GROCER);
        reg("agotmod:walnut",                8,  GROCER);

        // Herbs
        reg("agotmod:fennel",                4,  GROCER);
        reg("agotmod:mint",                  4,  GROCER);

        // Sweeteners
        reg("agotmod:honey",                 7,  GROCER);
        reg("agotmod:molasses",              6,  GROCER);

        // ══════════════════════════════════════════════════════════════════════
        // BUTCHER
        // ══════════════════════════════════════════════════════════════════════

        // Vanilla Meats
        reg("minecraft:beef",               24,  BUTCHER);
        reg("minecraft:porkchop",           24,  BUTCHER);
        reg("minecraft:chicken",            16,  BUTCHER);
        reg("minecraft:mutton",             16,  BUTCHER);
        reg("minecraft:rabbit",             24,  BUTCHER);

        // Raw Sausages
        reg("agotmod:raw_sausage",          16,  BUTCHER);
        reg("agotmod:raw_white_sausage",    16,  BUTCHER);
        reg("agotmod:raw_blood_sausage",    16,  BUTCHER);

        // Raw Poultry - Tiny (nutrition 1 → 8)
        reg("agotmod:raw_pigeon",            8,  BUTCHER);
        reg("agotmod:raw_gull",              8,  BUTCHER);
        reg("agotmod:raw_lark",              8,  BUTCHER);
        reg("agotmod:raw_quail",             8,  BUTCHER);

        // Raw Poultry - Medium (nutrition 2 → 16)
        reg("agotmod:raw_duck",             16,  BUTCHER);
        reg("agotmod:raw_capon",            16,  BUTCHER);
        reg("agotmod:raw_heron",            16,  BUTCHER);
        reg("agotmod:raw_partridge",        16,  BUTCHER);

        // Raw Poultry - Large (nutrition 3 → 24)
        reg("agotmod:raw_goose",            24,  BUTCHER);
        reg("agotmod:raw_peacock",          24,  BUTCHER);
        reg("agotmod:raw_swan",             24,  BUTCHER);

        // Raw Meats - Tiny (nutrition 1 → 8)
        reg("agotmod:raw_chicken_nuggets",   8,  BUTCHER);
        reg("agotmod:raw_bacon",             8,  BUTCHER);
        reg("agotmod:raw_frog",              8,  BUTCHER);
        reg("agotmod:raw_squirrel",          8,  BUTCHER);
        reg("agotmod:raw_rat",               8,  BUTCHER);

        // Raw Meats - Small (nutrition 2 → 16)
        reg("agotmod:raw_hare_meat",        16,  BUTCHER);
        reg("agotmod:raw_horse_meat",       16,  BUTCHER);
        reg("agotmod:raw_goat_meat",        16,  BUTCHER);
        reg("agotmod:raw_deer_venison",     16,  BUTCHER);
        reg("agotmod:raw_lamb",             16,  BUTCHER);
        reg("agotmod:raw_venison",          16,  BUTCHER);
        reg("agotmod:raw_dog",              16,  BUTCHER);
        reg("agotmod:raw_snake",            16,  BUTCHER);

        // Raw Meats - Medium (nutrition 3 → 24)
        reg("agotmod:raw_boar_venison",     24,  BUTCHER);
        reg("agotmod:raw_pork",             24,  BUTCHER);

        // Raw Meats - Large (nutrition 4 → 32)
        reg("agotmod:raw_bear_meat",        32,  BUTCHER);
        reg("agotmod:raw_mammoth_meat",     32,  BUTCHER);
        reg("agotmod:raw_aurochs",          32,  BUTCHER);

        // ══════════════════════════════════════════════════════════════════════
        // TANNER
        // ══════════════════════════════════════════════════════════════════════

        reg("minecraft:leather",            12,  TANNER);
        reg("minecraft:rabbit_hide",         4,  TANNER);
        reg("minecraft:bundle",             18,  TANNER);
        reg("agotmod:boiled_leather",       16,  TANNER);

        // ══════════════════════════════════════════════════════════════════════
        // TAILOR
        // ══════════════════════════════════════════════════════════════════════

        // Textile chain
        reg("minecraft:string",             10,  TAILOR);
        reg("minecraft:white_wool",         40,  TAILOR);
        reg("minecraft:orange_wool",        40,  TAILOR);
        reg("minecraft:magenta_wool",       40,  TAILOR);
        reg("minecraft:light_blue_wool",    40,  TAILOR);
        reg("minecraft:yellow_wool",        40,  TAILOR);
        reg("minecraft:lime_wool",          40,  TAILOR);
        reg("minecraft:pink_wool",          40,  TAILOR);
        reg("minecraft:gray_wool",          40,  TAILOR);
        reg("minecraft:light_gray_wool",    40,  TAILOR);
        reg("minecraft:cyan_wool",          40,  TAILOR);
        reg("minecraft:purple_wool",        40,  TAILOR);
        reg("minecraft:blue_wool",          40,  TAILOR);
        reg("minecraft:brown_wool",         40,  TAILOR);
        reg("minecraft:green_wool",         40,  TAILOR);
        reg("minecraft:red_wool",           40,  TAILOR);
        reg("minecraft:black_wool",         40,  TAILOR);
        reg("agotmod:cloth",               100,  TAILOR);
        reg("agotmod:fur",                  20,  TAILOR);

        // Leather armor
        reg("minecraft:leather_helmet",     48,  TAILOR);
        reg("minecraft:leather_chestplate", 80,  TAILOR);
        reg("minecraft:leather_leggings",   64,  TAILOR);
        reg("minecraft:leather_boots",      48,  TAILOR);

        // Dyes
        reg("minecraft:white_dye",          65,  TAILOR);
        reg("minecraft:orange_dye",         65,  TAILOR);
        reg("minecraft:magenta_dye",        65,  TAILOR);
        reg("minecraft:light_blue_dye",     65,  TAILOR);
        reg("minecraft:yellow_dye",         65,  TAILOR);
        reg("minecraft:lime_dye",           65,  TAILOR);
        reg("minecraft:pink_dye",           65,  TAILOR);
        reg("minecraft:gray_dye",           65,  TAILOR);
        reg("minecraft:light_gray_dye",     65,  TAILOR);
        reg("minecraft:cyan_dye",           65,  TAILOR);
        reg("minecraft:purple_dye",         65,  TAILOR);
        reg("minecraft:blue_dye",           65,  TAILOR);
        reg("minecraft:brown_dye",          65,  TAILOR);
        reg("minecraft:green_dye",          65,  TAILOR);
        reg("minecraft:red_dye",            65,  TAILOR);
        reg("minecraft:black_dye",          65,  TAILOR);

        // Mod flowers
        reg("agotmod:winter_rose",           6,  TAILOR);
        reg("agotmod:wild_radish",           6,  TAILOR);
        reg("agotmod:white_rose",            6,  TAILOR);
        reg("agotmod:thorn_bush",            6,  TAILOR);
        reg("agotmod:thistle",               6,  TAILOR);
        reg("agotmod:tansy",                 6,  TAILOR);
        reg("agotmod:spiceflower",           6,  TAILOR);
        reg("agotmod:sedge",                 6,  TAILOR);
        reg("agotmod:saffron_crocus",        6,  TAILOR);
        reg("agotmod:rose",                  6,  TAILOR);
        reg("agotmod:poison_kisses",         6,  TAILOR);
        reg("agotmod:pennyroyal",            6,  TAILOR);
        reg("agotmod:opium_poppy_wild",      6,  TAILOR);
        reg("agotmod:nightshade",            6,  TAILOR);
        reg("agotmod:moonbloom",             6,  TAILOR);
        reg("agotmod:lungwort",              6,  TAILOR);
        reg("agotmod:liverwort",             6,  TAILOR);
        reg("agotmod:lavender",              6,  TAILOR);
        reg("agotmod:ladys_lace",            6,  TAILOR);
        reg("agotmod:gorse",                 6,  TAILOR);
        reg("agotmod:goldenrod",             6,  TAILOR);
        reg("agotmod:goldencup",             6,  TAILOR);
        reg("agotmod:goathead",              6,  TAILOR);
        reg("agotmod:ginger",                6,  TAILOR);
        reg("agotmod:gillyflower",           6,  TAILOR);
        reg("agotmod:frostfire",             6,  TAILOR);
        reg("agotmod:forget_me_not",         6,  TAILOR);
        reg("agotmod:evening_star",          6,  TAILOR);
        reg("agotmod:dusky_rose",            6,  TAILOR);
        reg("agotmod:dragons_breath",        6,  TAILOR);
        reg("agotmod:coldsnap",              6,  TAILOR);
        reg("agotmod:blue_rose",             6,  TAILOR);
        reg("agotmod:bloodbloom",            6,  TAILOR);
        reg("agotmod:black_lotus",           6,  TAILOR);
        reg("agotmod:winter_rose_bush",     10,  TAILOR);
        reg("agotmod:white_rose_bush",      10,  TAILOR);
        reg("agotmod:dusky_rose_bush",      10,  TAILOR);
        reg("agotmod:blue_rose_bush",       10,  TAILOR);
        reg("agotmod:red_rose_bush",        10,  TAILOR);

        // Vanilla flowers
        reg("minecraft:poppy",               6,  TAILOR);
        reg("minecraft:dandelion",           6,  TAILOR);
        reg("minecraft:blue_orchid",         6,  TAILOR);
        reg("minecraft:allium",              6,  TAILOR);
        reg("minecraft:azure_bluet",         6,  TAILOR);
        reg("minecraft:red_tulip",           6,  TAILOR);
        reg("minecraft:orange_tulip",        6,  TAILOR);
        reg("minecraft:white_tulip",         6,  TAILOR);
        reg("minecraft:pink_tulip",          6,  TAILOR);
        reg("minecraft:oxeye_daisy",         6,  TAILOR);
        reg("minecraft:cornflower",          6,  TAILOR);
        reg("minecraft:lily_of_the_valley",  6,  TAILOR);
        reg("minecraft:wither_rose",         6,  TAILOR);
        reg("minecraft:sunflower",           6,  TAILOR);
        reg("minecraft:lilac",               6,  TAILOR);
        reg("minecraft:rose_bush",           6,  TAILOR);
        reg("minecraft:peony",               6,  TAILOR);

        // ══════════════════════════════════════════════════════════════════════
        // BLACKSMITH
        // ══════════════════════════════════════════════════════════════════════

        // ── Coal / Charcoal ──────────────────────────────────────────────────
        reg("minecraft:coal",               30,  BLACKSMITH);
        reg("minecraft:charcoal",           25,  BLACKSMITH);

        // ── Raw Ores (~70% of ingot) ─────────────────────────────────────────
        reg("minecraft:raw_copper",         98,  BLACKSMITH);
        reg("minecraft:raw_iron",          112,  BLACKSMITH);
        reg("agotmod:raw_tin",             105,  BLACKSMITH);
        reg("agotmod:raw_silver",          210,  BLACKSMITH);
        reg("minecraft:raw_gold",          700,  BLACKSMITH);  // 70% of 1000

        // ── Nuggets (ingot / 9) ──────────────────────────────────────────────
        reg("minecraft:iron_nugget",        18,  BLACKSMITH);  // 160/9
        reg("agotmod:bronze_nugget",        23,  BLACKSMITH);  // 200/9
        reg("agotmod:steel_nugget",         67,  BLACKSMITH);  // 600/9
        reg("agotmod:silver_nugget",        34,  BLACKSMITH);  // 300/9
        reg("minecraft:gold_nugget",       111,  BLACKSMITH);  // 1000/9

        // ── Ingots ───────────────────────────────────────────────────────────
        reg("minecraft:copper_ingot",      140,  BLACKSMITH);
        reg("agotmod:tin_ingot",           150,  BLACKSMITH);
        reg("minecraft:iron_ingot",        160,  BLACKSMITH);
        reg("agotmod:bronze_ingot",        200,  BLACKSMITH);
        reg("agotmod:silver_ingot",        300,  BLACKSMITH);
        // steel = (4×160 + 4×30) / 2 × 2.5 = (640+120)/2 × 2.5 = 950 → 600 rounded
        reg("agotmod:steel_ingot",         600,  BLACKSMITH);
        reg("minecraft:gold_ingot",       1000,  BLACKSMITH);

        // ── Chain Links, Chains, Plates ──────────────────────────────────────
        reg("agotmod:bronze_chain_link",   220,  BLACKSMITH);
        reg("agotmod:bronze_chain",        700,  BLACKSMITH);
        reg("agotmod:bronze_plate",        900,  BLACKSMITH);
        reg("agotmod:iron_chain_link",     180,  BLACKSMITH);
        reg("agotmod:iron_chain",          570,  BLACKSMITH);
        reg("agotmod:iron_plate",          730,  BLACKSMITH);
        reg("agotmod:steel_chain_link",    650,  BLACKSMITH);
        reg("agotmod:steel_chain",        2050,  BLACKSMITH);
        reg("agotmod:steel_plate",        2750,  BLACKSMITH);
        reg("agotmod:noble_plate",       15000,  BLACKSMITH);

        // ── Hammer ───────────────────────────────────────────────────────────
        reg("agotmod:hammer",             1500,  BLACKSMITH);

        // ── Steel Armor Components ───────────────────────────────────────────
        // helmet=5 steel ingots, chestplate=8, leggings=7, boots=4
        reg("agotmod:steel_helmet",       3500,  BLACKSMITH);
        reg("agotmod:steel_chestplate",   5600,  BLACKSMITH);
        reg("agotmod:steel_leggings",     4900,  BLACKSMITH);
        reg("agotmod:steel_boots",        2800,  BLACKSMITH);

        // ── Upgrade Kits ─────────────────────────────────────────────────────
        // Tier 1 set total = 2000+4000+3000+2000 = 11000 → kit = 2× = 22000
        reg("agotmod:upgrade_kit_fur",      22000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_leather",  22000,  BLACKSMITH);
        // Tier 2 set total = 10000+20000+15000+10000 = 55000 → kit = 110000
        reg("agotmod:upgrade_kit_bronze",       110000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_bronze_plate", 110000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_chief",        110000,  BLACKSMITH);
        // Tier 3 set total = 50000+100000+75000+50000 = 275000 → kit = 550000
        reg("agotmod:upgrade_kit_iron",     550000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_steel",    550000,  BLACKSMITH);
        // Tier 4 set total = 250000+500000+375000+250000 = 1375000 → kit = 2750000
        reg("agotmod:upgrade_kit_noble",   2750000,  BLACKSMITH);

        // ── Smithing Scrolls (2× full armor set price) ───────────────────────
        // Tier 1 = 22000
        reg("agotmod:nights_watch_ranger_smithing_scroll",             22000,  BLACKSMITH);
        reg("agotmod:wildling_fur_smithing_scroll",                    22000,  BLACKSMITH);
        reg("agotmod:thenn_levy_smithing_scroll",                      22000,  BLACKSMITH);
        // Tier 2 = 110000
        reg("agotmod:nights_watch_leather_smithing_scroll",           110000,  BLACKSMITH);
        reg("agotmod:wildling_leather_smithing_scroll",               110000,  BLACKSMITH);
        reg("agotmod:wildling_chief_smithing_scroll",                 110000,  BLACKSMITH);
        reg("agotmod:thenn_plate_smithing_scroll",                    110000,  BLACKSMITH);
        reg("agotmod:bolton_levy_smithing_scroll",                    110000,  BLACKSMITH);
        reg("agotmod:manderly_levy_smithing_scroll",                  110000,  BLACKSMITH);
        reg("agotmod:stark_levy_smithing_scroll",                     110000,  BLACKSMITH);
        reg("agotmod:ironborn_levy_smithing_scroll",                  110000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_leather_smithing_scroll", 110000,  BLACKSMITH);
        // Tier 3 = 550000
        reg("agotmod:nights_watch_elite_smithing_scroll",             550000,  BLACKSMITH);
        reg("agotmod:thenn_noble_smithing_scroll",                    550000,  BLACKSMITH);
        reg("agotmod:bolton_plate_smithing_scroll",                   550000,  BLACKSMITH);
        reg("agotmod:manderly_plate_smithing_scroll",                 550000,  BLACKSMITH);
        reg("agotmod:stark_plate_smithing_scroll",                    550000,  BLACKSMITH);
        reg("agotmod:ironborn_plate_smithing_scroll",                 550000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_chain_smithing_scroll",   550000,  BLACKSMITH);
        // Tier 4 = 2750000
        reg("agotmod:bolton_noble_smithing_scroll",                  2750000,  BLACKSMITH);
        reg("agotmod:manderly_noble_smithing_scroll",                2750000,  BLACKSMITH);
        reg("agotmod:stark_noble_smithing_scroll",                   2750000,  BLACKSMITH);
        reg("agotmod:ironborn_noble_smithing_scroll",                2750000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_noble_smithing_scroll",  2750000,  BLACKSMITH);

        // ── Vanilla Tools (ingot cost × pieces) ──────────────────────────────
        reg("minecraft:iron_sword",         320,  BLACKSMITH);  // 2×160
        reg("minecraft:iron_pickaxe",       480,  BLACKSMITH);  // 3×160
        reg("minecraft:iron_axe",           480,  BLACKSMITH);
        reg("minecraft:iron_shovel",        160,  BLACKSMITH);  // 1×160
        reg("minecraft:iron_hoe",           320,  BLACKSMITH);  // 2×160
        reg("minecraft:golden_sword",      2000,  BLACKSMITH);  // 2×1000
        reg("minecraft:golden_pickaxe",    3000,  BLACKSMITH);  // 3×1000
        reg("minecraft:golden_axe",        3000,  BLACKSMITH);
        reg("minecraft:golden_shovel",     1000,  BLACKSMITH);
        reg("minecraft:golden_hoe",        2000,  BLACKSMITH);

        // ── Vanilla Armor (ingot cost × pieces) ──────────────────────────────
        // iron: helmet=5, chest=8, legs=7, boots=4
        reg("minecraft:iron_helmet",        800,  BLACKSMITH);
        reg("minecraft:iron_chestplate",   1280,  BLACKSMITH);
        reg("minecraft:iron_leggings",     1120,  BLACKSMITH);
        reg("minecraft:iron_boots",         640,  BLACKSMITH);
        // chainmail ~2× iron
        reg("minecraft:chainmail_helmet",  1600,  BLACKSMITH);
        reg("minecraft:chainmail_chestplate",2560,BLACKSMITH);
        reg("minecraft:chainmail_leggings",2240,  BLACKSMITH);
        reg("minecraft:chainmail_boots",   1280,  BLACKSMITH);
        // gold: helmet=5, chest=8, legs=7, boots=4
        reg("minecraft:golden_helmet",     5000,  BLACKSMITH);
        reg("minecraft:golden_chestplate", 8000,  BLACKSMITH);
        reg("minecraft:golden_leggings",   7000,  BLACKSMITH);
        reg("minecraft:golden_boots",      4000,  BLACKSMITH);

        // ── Bronze Tools ─────────────────────────────────────────────────────
        reg("agotmod:bronze_pickaxe",       600,  BLACKSMITH);  // 3×200
        reg("agotmod:bronze_shovel",        200,  BLACKSMITH);  // 1×200
        reg("agotmod:bronze_axe",           600,  BLACKSMITH);
        reg("agotmod:bronze_hoe",           400,  BLACKSMITH);

        // ── Steel Tools ──────────────────────────────────────────────────────
        reg("agotmod:steel_pickaxe",       1800,  BLACKSMITH);  // 3×600
        reg("agotmod:steel_shovel",         600,  BLACKSMITH);  // 1×600
        reg("agotmod:steel_axe",           1800,  BLACKSMITH);
        reg("agotmod:steel_hoe",           1200,  BLACKSMITH);
        reg("agotmod:steel_bow",           2500,  BLACKSMITH);

        // ── Arrows ───────────────────────────────────────────────────────────
        reg("agotmod:arrow_bronze",          50,  BLACKSMITH);
        reg("agotmod:arrow_iron",            70,  BLACKSMITH);
        reg("agotmod:arrow_steel",          200,  BLACKSMITH);
        reg("agotmod:arrow_dragon_glass",   800,  BLACKSMITH);

        // ── Bronze Weapons ───────────────────────────────────────────────────
        reg("agotmod:bronze_sword",         600,  BLACKSMITH);
        reg("agotmod:bronze_spatha",        800,  BLACKSMITH);
        reg("agotmod:bronze_spear",         600,  BLACKSMITH);
        reg("agotmod:bronze_pike",          750,  BLACKSMITH);
        reg("agotmod:bronze_dagger",        400,  BLACKSMITH);
        reg("agotmod:bronze_battleaxe",     900,  BLACKSMITH);

        // ── Iron Weapons ─────────────────────────────────────────────────────
        reg("agotmod:iron_longsword",      1400,  BLACKSMITH);
        reg("agotmod:iron_spear",          1200,  BLACKSMITH);
        reg("agotmod:iron_pike",           1400,  BLACKSMITH);
        reg("agotmod:iron_mace",           1200,  BLACKSMITH);
        reg("agotmod:iron_dagger",          800,  BLACKSMITH);
        reg("agotmod:iron_battleaxe",      1600,  BLACKSMITH);

        // ── Steel Weapons (2×600 base + craftsmanship) ───────────────────────
        reg("agotmod:steel_sword",         1600,  BLACKSMITH);
        reg("agotmod:steel_longsword",     2200,  BLACKSMITH);
        reg("agotmod:steel_spear",         1600,  BLACKSMITH);
        reg("agotmod:steel_pike",          2000,  BLACKSMITH);
        reg("agotmod:steel_mace",          1600,  BLACKSMITH);
        reg("agotmod:steel_dagger",        1200,  BLACKSMITH);
        reg("agotmod:steel_battleaxe",     2200,  BLACKSMITH);
        reg("agotmod:steel_halberd",       2500,  BLACKSMITH);

        // ── Noble Weapons ────────────────────────────────────────────────────
        reg("agotmod:noble_longsword",    60000,  BLACKSMITH);
        reg("agotmod:noble_spear",        50000,  BLACKSMITH);
        reg("agotmod:noble_pike",         55000,  BLACKSMITH);
        reg("agotmod:noble_mace",         50000,  BLACKSMITH);
        reg("agotmod:noble_dagger",       35000,  BLACKSMITH);
        reg("agotmod:noble_battleaxe",    60000,  BLACKSMITH);
        reg("agotmod:noble_halberd",      65000,  BLACKSMITH);

        // ── Dragonglass Weapons ──────────────────────────────────────────────
        reg("agotmod:dragonglass_spear",  100000,  BLACKSMITH);
        reg("agotmod:dragonglass_dagger",  80000,  BLACKSMITH);

        // ── Tier 1 Armor — NW Ranger, Wildling Fur, Thenn Leather ────────────
        // helmet=2000, chest=4000, legs=3000, boots=2000
        reg("agotmod:night_watch_ranger_helmet",     2000,  BLACKSMITH);
        reg("agotmod:night_watch_ranger_chestplate", 4000,  BLACKSMITH);
        reg("agotmod:night_watch_ranger_leggings",   3000,  BLACKSMITH);
        reg("agotmod:night_watch_ranger_boots",      2000,  BLACKSMITH);

        reg("agotmod:wildling_fur_helmet",           2000,  BLACKSMITH);
        reg("agotmod:wildling_fur_chestplate",       4000,  BLACKSMITH);
        reg("agotmod:wildling_fur_leggings",         3000,  BLACKSMITH);
        reg("agotmod:wildling_fur_boots",            2000,  BLACKSMITH);

        reg("agotmod:thenn_leather_helmet",          2000,  BLACKSMITH);
        reg("agotmod:thenn_leather_chestplate",      4000,  BLACKSMITH);
        reg("agotmod:thenn_leather_leggings",        3000,  BLACKSMITH);
        reg("agotmod:thenn_leather_boots",           2000,  BLACKSMITH);

        // ── Tier 2 Armor — NW Leather, Wildling Leather+Chief, Thenn Bronze,
        //                   all Levy sets, Mountain Clan Leather ──────────────
        // helmet=10000, chest=20000, legs=15000, boots=10000
        reg("agotmod:night_watch_leather_helmet",     10000,  BLACKSMITH);
        reg("agotmod:night_watch_leather_chestplate", 20000,  BLACKSMITH);
        reg("agotmod:night_watch_leather_leggings",   15000,  BLACKSMITH);
        reg("agotmod:night_watch_leather_boots",      10000,  BLACKSMITH);

        reg("agotmod:wildling_leather_helmet",        10000,  BLACKSMITH);
        reg("agotmod:wildling_leather_chestplate",    20000,  BLACKSMITH);
        reg("agotmod:wildling_leather_leggings",      15000,  BLACKSMITH);
        reg("agotmod:wildling_leather_boots",         10000,  BLACKSMITH);

        reg("agotmod:wildling_chief_helmet",          10000,  BLACKSMITH);
        reg("agotmod:wildling_chief_chestplate",      20000,  BLACKSMITH);
        reg("agotmod:wildling_chief_leggings",        15000,  BLACKSMITH);
        reg("agotmod:wildling_chief_boots",           10000,  BLACKSMITH);

        reg("agotmod:thenn_bronze_helmet",            10000,  BLACKSMITH);
        reg("agotmod:thenn_bronze_chestplate",        20000,  BLACKSMITH);
        reg("agotmod:thenn_bronze_leggings",          15000,  BLACKSMITH);
        reg("agotmod:thenn_bronze_boots",             10000,  BLACKSMITH);

        reg("agotmod:bolton_levy_helmet",             10000,  BLACKSMITH);
        reg("agotmod:bolton_levy_chestplate",         20000,  BLACKSMITH);
        reg("agotmod:bolton_levy_leggings",           15000,  BLACKSMITH);
        reg("agotmod:bolton_levy_boots",              10000,  BLACKSMITH);

        reg("agotmod:manderly_levy_helmet",           10000,  BLACKSMITH);
        reg("agotmod:manderly_levy_chestplate",       20000,  BLACKSMITH);
        reg("agotmod:manderly_levy_leggings",         15000,  BLACKSMITH);
        reg("agotmod:manderly_levy_boots",            10000,  BLACKSMITH);

        reg("agotmod:stark_levy_helmet",              10000,  BLACKSMITH);
        reg("agotmod:stark_levy_chestplate",          20000,  BLACKSMITH);
        reg("agotmod:stark_levy_leggings",            15000,  BLACKSMITH);
        reg("agotmod:stark_levy_boots",               10000,  BLACKSMITH);

        reg("agotmod:ironborn_levy_helmet",           10000,  BLACKSMITH);
        reg("agotmod:ironborn_levy_chestplate",       20000,  BLACKSMITH);
        reg("agotmod:ironborn_levy_leggings",         15000,  BLACKSMITH);
        reg("agotmod:ironborn_levy_boots",            10000,  BLACKSMITH);

        reg("agotmod:northern_mountain_clan_leather_helmet",      10000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_leather_chestplate",  20000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_leather_leggings",    15000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_leather_boots",       10000,  BLACKSMITH);

        // ── Tier 3 Armor — NW Elite, Thenn Chief, all Plate,
        //                   Mountain Clan Chain ─────────────────────────────
        // helmet=50000, chest=100000, legs=75000, boots=50000
        reg("agotmod:night_watch_elite_helmet",        50000,  BLACKSMITH);
        reg("agotmod:night_watch_elite_chestplate",   100000,  BLACKSMITH);
        reg("agotmod:night_watch_elite_leggings",      75000,  BLACKSMITH);
        reg("agotmod:night_watch_elite_boots",         50000,  BLACKSMITH);

        reg("agotmod:thenn_chief_helmet",              50000,  BLACKSMITH);
        reg("agotmod:thenn_chief_chestplate",         100000,  BLACKSMITH);
        reg("agotmod:thenn_chief_leggings",            75000,  BLACKSMITH);
        reg("agotmod:thenn_chief_boots",               50000,  BLACKSMITH);

        reg("agotmod:bolton_plate_helmet",             50000,  BLACKSMITH);
        reg("agotmod:bolton_plate_chestplate",        100000,  BLACKSMITH);
        reg("agotmod:bolton_plate_leggings",           75000,  BLACKSMITH);
        reg("agotmod:bolton_plate_boots",              50000,  BLACKSMITH);

        reg("agotmod:manderly_plate_helmet",           50000,  BLACKSMITH);
        reg("agotmod:manderly_plate_chestplate",      100000,  BLACKSMITH);
        reg("agotmod:manderly_plate_leggings",         75000,  BLACKSMITH);
        reg("agotmod:manderly_plate_boots",            50000,  BLACKSMITH);

        reg("agotmod:stark_plate_helmet",              50000,  BLACKSMITH);
        reg("agotmod:stark_plate_chestplate",         100000,  BLACKSMITH);
        reg("agotmod:stark_plate_leggings",            75000,  BLACKSMITH);
        reg("agotmod:stark_plate_boots",               50000,  BLACKSMITH);

        reg("agotmod:ironborn_plate_helmet",           50000,  BLACKSMITH);
        reg("agotmod:ironborn_plate_chestplate",      100000,  BLACKSMITH);
        reg("agotmod:ironborn_plate_leggings",         75000,  BLACKSMITH);
        reg("agotmod:ironborn_plate_boots",            50000,  BLACKSMITH);

        reg("agotmod:northern_mountain_clan_chain_helmet",      50000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_chain_chestplate", 100000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_chain_leggings",    75000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_chain_boots",       50000,  BLACKSMITH);

        // ── Tier 4 Armor — all Noble sets, Mountain Clan Noble ───────────────
        // helmet=250000, chest=500000, legs=375000, boots=250000
        reg("agotmod:bolton_noble_helmet",            250000,  BLACKSMITH);
        reg("agotmod:bolton_noble_chestplate",        500000,  BLACKSMITH);
        reg("agotmod:bolton_noble_leggings",          375000,  BLACKSMITH);
        reg("agotmod:bolton_noble_boots",             250000,  BLACKSMITH);

        reg("agotmod:manderly_noble_helmet",          250000,  BLACKSMITH);
        reg("agotmod:manderly_noble_chestplate",      500000,  BLACKSMITH);
        reg("agotmod:manderly_noble_leggings",        375000,  BLACKSMITH);
        reg("agotmod:manderly_noble_boots",           250000,  BLACKSMITH);

        reg("agotmod:stark_noble_plate_helmet",       250000,  BLACKSMITH);
        reg("agotmod:stark_noble_plate_chestplate",   500000,  BLACKSMITH);
        reg("agotmod:stark_noble_plate_leggings",     375000,  BLACKSMITH);
        reg("agotmod:stark_noble_plate_boots",        250000,  BLACKSMITH);

        reg("agotmod:ironborn_noble_helmet",          250000,  BLACKSMITH);
        reg("agotmod:ironborn_noble_chestplate",      500000,  BLACKSMITH);
        reg("agotmod:ironborn_noble_leggings",        375000,  BLACKSMITH);
        reg("agotmod:ironborn_noble_boots",           250000,  BLACKSMITH);

        reg("agotmod:northern_mountain_clan_noble_helmet",      250000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_noble_chestplate",  500000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_noble_leggings",    375000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_noble_boots",       250000,  BLACKSMITH);
    }

    // ── Public API ────────────────────────────────────────────────────────────

    public static boolean isItemAllowed(String itemKey) {
        return ITEMS.containsKey(itemKey);
    }

    public static boolean isJobItem(String itemKey, String job) {
        ItemData data = ITEMS.get(itemKey);
        return data != null && data.jobs.contains(job);
    }

    public static boolean isGrocerItem(String itemKey)     { return isJobItem(itemKey, GROCER);     }
    public static boolean isButcherItem(String itemKey)    { return isJobItem(itemKey, BUTCHER);    }
    public static boolean isTannerItem(String itemKey)     { return isJobItem(itemKey, TANNER);     }
    public static boolean isTailorItem(String itemKey)     { return isJobItem(itemKey, TAILOR);     }
    public static boolean isBlacksmithItem(String itemKey) { return isJobItem(itemKey, BLACKSMITH); }

    public static int getItemPrice(String itemKey) {
        ItemData data = ITEMS.get(itemKey);
        return data != null ? data.price : 0;
    }

    public static int getItemSellPrice(String itemKey) {
        return getItemPrice(itemKey) / 2;
    }

    public static java.util.Map<String, Integer> getAllPricing() {
        java.util.Map<String, Integer> result = new LinkedHashMap<>();
        ITEMS.forEach((key, data) -> result.put(key, data.price));
        return result;
    }
}
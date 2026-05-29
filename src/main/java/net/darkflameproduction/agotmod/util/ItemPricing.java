package net.darkflameproduction.agotmod.util;

import java.util.*;

public class ItemPricing {

    public static final String GROCER     = "grocer";
    public static final String BUTCHER    = "butcher";
    public static final String TANNER     = "tanner";
    public static final String TAILOR     = "tailor";
    public static final String BLACKSMITH = "blacksmith";
    public static final String CARPENTER  = "carpenter";
    public static final String TRADER = "trader";


    private static class ItemData {
        final int price;
        final Set<String> jobs;

        ItemData(int price, String... jobs) {
            this.price = price;
            this.jobs  = new HashSet<>(Arrays.asList(jobs));
        }
    }

    private static final Map<String, ItemData> ITEMS = new LinkedHashMap<>();

    // ── Wood type lists — declared before static{} so the initialiser can use them ──

    private static final Set<String> VANILLA_WOODS = new HashSet<>(Arrays.asList(
            "oak", "spruce", "birch", "jungle", "acacia", "dark_oak",
            "mangrove", "cherry", "bamboo", "crimson", "warped"
    ));

    private static final List<String> MOD_WOODS = Arrays.asList(
            "weirwood", "charred", "rotten",
            "sycamore", "pine", "ash", "beech", "cedar", "chestnut", "hawthorn",
            "ironwood", "sentinel", "blackbark", "aspen", "black_cherry", "black_olive",
            "crabapple", "olive", "white_cherry", "red_cherry", "fir", "willow",
            "wormtree", "alder", "almond", "apple", "apricot", "baobab",
            "black_cottonwood", "blackthorn", "blood_orange", "bloodwood", "blue_mahoe",
            "cottonwood", "datepalm", "ebony", "fig", "fireplum", "goldenheart",
            "lemon", "lime", "linden", "mahogany", "maple", "myrrh", "nightwood",
            "nutmeg", "orange", "peach", "pear", "pecan", "persimmon", "pink_ivory",
            "plum", "pomegranate", "purpleheart", "redwood", "sandalwood", "sandbeggar",
            "tigerwood", "yew", "blue_soldier_pine", "soldier_pine"
    );

    private static final List<String> WOOD_TYPES;
    static {
        WOOD_TYPES = new ArrayList<>();
        WOOD_TYPES.addAll(VANILLA_WOODS);
        WOOD_TYPES.addAll(MOD_WOODS);
    }

    private static void reg(String key, int price, String... jobs) {
        ITEMS.put(key, new ItemData(price, jobs));
    }

    static {

        // ══════════════════════════════════════════════════════════════════════
        // GROCER
        // ══════════════════════════════════════════════════════════════════════

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
        reg("agotmod:cotton",               10,  GROCER, TAILOR);
        reg("agotmod:cotton_seeds",         37,  GROCER);

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

        reg("agotmod:garlic",                5,  GROCER);
        reg("agotmod:olives",                6,  GROCER);
        reg("agotmod:peas",                  4,  GROCER);
        reg("agotmod:radish",                4,  GROCER);
        reg("agotmod:lentils",               5,  GROCER);
        reg("agotmod:squash",                6,  GROCER);
        reg("agotmod:corn",                  4,  GROCER);

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

        reg("agotmod:almond",                8,  GROCER);
        reg("agotmod:chestnut",              9,  GROCER);
        reg("agotmod:pecan",                 8,  GROCER);
        reg("agotmod:pine_nut",              9,  GROCER);
        reg("agotmod:walnut",                8,  GROCER);

        reg("agotmod:fennel",                4,  GROCER);
        reg("agotmod:mint",                  4,  GROCER);
        reg("agotmod:honey",                 7,  GROCER);
        reg("agotmod:molasses",              6,  GROCER);

        // ══════════════════════════════════════════════════════════════════════
        // BUTCHER
        // ══════════════════════════════════════════════════════════════════════

        reg("minecraft:beef",               24,  BUTCHER);
        reg("minecraft:porkchop",           24,  BUTCHER);
        reg("minecraft:chicken",            16,  BUTCHER);
        reg("minecraft:mutton",             16,  BUTCHER);
        reg("minecraft:rabbit",             24,  BUTCHER);

        reg("agotmod:raw_sausage",          16,  BUTCHER);
        reg("agotmod:raw_white_sausage",    16,  BUTCHER);
        reg("agotmod:raw_blood_sausage",    16,  BUTCHER);
        reg("agotmod:raw_pigeon",            8,  BUTCHER);
        reg("agotmod:raw_gull",              8,  BUTCHER);
        reg("agotmod:raw_lark",              8,  BUTCHER);
        reg("agotmod:raw_quail",             8,  BUTCHER);
        reg("agotmod:raw_duck",             16,  BUTCHER);
        reg("agotmod:raw_capon",            16,  BUTCHER);
        reg("agotmod:raw_heron",            16,  BUTCHER);
        reg("agotmod:raw_partridge",        16,  BUTCHER);
        reg("agotmod:raw_goose",            24,  BUTCHER);
        reg("agotmod:raw_peacock",          24,  BUTCHER);
        reg("agotmod:raw_swan",             24,  BUTCHER);
        reg("agotmod:raw_chicken_nuggets",   8,  BUTCHER);
        reg("agotmod:raw_bacon",             8,  BUTCHER);
        reg("agotmod:raw_frog",              8,  BUTCHER);
        reg("agotmod:raw_squirrel",          8,  BUTCHER);
        reg("agotmod:raw_rat",               8,  BUTCHER);
        reg("agotmod:raw_hare_meat",        16,  BUTCHER);
        reg("agotmod:raw_horse_meat",       16,  BUTCHER);
        reg("agotmod:raw_goat_meat",        16,  BUTCHER);
        reg("agotmod:raw_deer_venison",     16,  BUTCHER);
        reg("agotmod:raw_lamb",             16,  BUTCHER);
        reg("agotmod:raw_venison",          16,  BUTCHER);
        reg("agotmod:raw_dog",              16,  BUTCHER);
        reg("agotmod:raw_snake",            16,  BUTCHER);
        reg("agotmod:raw_boar_venison",     24,  BUTCHER);
        reg("agotmod:raw_pork",             24,  BUTCHER);
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
        reg("minecraft:leather_helmet",     48,  TAILOR);
        reg("minecraft:leather_chestplate", 80,  TAILOR);
        reg("minecraft:leather_leggings",   64,  TAILOR);
        reg("minecraft:leather_boots",      48,  TAILOR);
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
        // CARPENTER
        // ══════════════════════════════════════════════════════════════════════

        // Raw materials (also bought from town)
        reg("agotmod:nail",                  5,  CARPENTER);
        reg("minecraft:stick",               2,  CARPENTER);

        // Planks — all wood types
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            String plankKey = ns + ":" + wood + "_planks";
            reg(plankKey, 8, CARPENTER);
        }

        // Stairs
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            reg(ns + ":" + wood + "_stairs", 10, CARPENTER);
        }

        // Slabs
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            // vanilla uses _slab, mod uses _slabs
            String suffix = VANILLA_WOODS.contains(wood) ? "_slab" : "_slabs";
            reg(ns + ":" + wood + suffix, 5, CARPENTER);
        }

        // Fences
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            reg(ns + ":" + wood + "_fence", 12, CARPENTER);
        }

        // Fence gates
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            reg(ns + ":" + wood + "_fence_gate", 18, CARPENTER);
        }

        // Walls (mod only — vanilla has no wood walls)
        for (String wood : MOD_WOODS) {
            reg("agotmod:" + wood + "_wall", 10, CARPENTER);
        }
        // vanilla walls added by AGoT mod
        for (String wood : VANILLA_WOODS) {
            reg("agotmod:" + wood + "_wall", 10, CARPENTER);
        }

        // Doors
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            reg(ns + ":" + wood + "_door", 20, CARPENTER);
        }

        // Trapdoors
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            reg(ns + ":" + wood + "_trapdoor", 20, CARPENTER);
        }

        // Signs
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            reg(ns + ":" + wood + "_sign", 15, CARPENTER);
            reg(ns + ":" + wood + "_hanging_sign", 18, CARPENTER);
        }

        // Saplings
        for (String wood : WOOD_TYPES) {
            String ns = VANILLA_WOODS.contains(wood) ? "minecraft" : "agotmod";
            reg(ns + ":" + wood + "_sapling", 12, CARPENTER);
        }

        // Furniture — all wood types
        for (String wood : WOOD_TYPES) {
            String prefix = "agotmod:" + wood;
            reg(prefix + "_stool",     40,  CARPENTER);
            reg(prefix + "_chair",     60,  CARPENTER);
            reg(prefix + "_arm_chair", 80,  CARPENTER);
            reg(prefix + "_table",     70,  CARPENTER);
        }

        for (String wood : MOD_WOODS) {
            // weirwood, charred, rotten are individual fields; map-based woods use _log suffix
            String logKey = switch (wood) {
                case "weirwood" -> "agotmod:weirwood_log";
                case "charred"  -> "agotmod:charred_log";
                case "rotten"   -> "agotmod:rotten_log";
                default         -> "agotmod:" + wood + "_log";
            };
            reg(logKey, 6, CARPENTER);
        }

        reg("minecraft:oak_log",      6,  CARPENTER);
        reg("minecraft:spruce_log",   6,  CARPENTER);
        reg("minecraft:birch_log",    6,  CARPENTER);
        reg("minecraft:jungle_log",   6,  CARPENTER);
        reg("minecraft:acacia_log",   6,  CARPENTER);
        reg("minecraft:dark_oak_log", 6,  CARPENTER);
        reg("minecraft:mangrove_log", 6,  CARPENTER);
        reg("minecraft:cherry_log",   6,  CARPENTER);
        reg("minecraft:bamboo_block", 4,  CARPENTER);
        reg("minecraft:crimson_stem", 6,  CARPENTER);
        reg("minecraft:warped_stem",  6,  CARPENTER);

        // ══════════════════════════════════════════════════════════════════════
        // BLACKSMITH
        // ══════════════════════════════════════════════════════════════════════

        reg("minecraft:coal",               30,  BLACKSMITH);
        reg("minecraft:charcoal",           25,  BLACKSMITH);
        reg("minecraft:raw_copper",         98,  BLACKSMITH);
        reg("minecraft:raw_iron",          112,  BLACKSMITH);
        reg("agotmod:raw_tin",             105,  BLACKSMITH);
        reg("agotmod:raw_silver",          210,  BLACKSMITH);
        reg("minecraft:raw_gold",          700,  BLACKSMITH);
        reg("minecraft:iron_nugget",        18,  BLACKSMITH);
        reg("agotmod:bronze_nugget",        23,  BLACKSMITH);
        reg("agotmod:steel_nugget",         67,  BLACKSMITH);
        reg("agotmod:silver_nugget",        34,  BLACKSMITH);
        reg("minecraft:gold_nugget",       111,  BLACKSMITH);
        reg("minecraft:copper_ingot",      140,  BLACKSMITH);
        reg("agotmod:tin_ingot",           150,  BLACKSMITH);
        reg("minecraft:iron_ingot",        160,  BLACKSMITH);
        reg("agotmod:bronze_ingot",        200,  BLACKSMITH);
        reg("agotmod:silver_ingot",        300,  BLACKSMITH);
        reg("agotmod:steel_ingot",         600,  BLACKSMITH);
        reg("minecraft:gold_ingot",       1000,  BLACKSMITH);
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
        reg("agotmod:hammer",             1500,  BLACKSMITH);
        reg("agotmod:steel_helmet",       3500,  BLACKSMITH);
        reg("agotmod:steel_chestplate",   5600,  BLACKSMITH);
        reg("agotmod:steel_leggings",     4900,  BLACKSMITH);
        reg("agotmod:steel_boots",        2800,  BLACKSMITH);
        reg("agotmod:upgrade_kit_fur",      22000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_leather",  22000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_bronze",       110000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_bronze_plate", 110000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_chief",        110000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_iron",     550000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_steel",    550000,  BLACKSMITH);
        reg("agotmod:upgrade_kit_noble",   2750000,  BLACKSMITH);
        reg("agotmod:nights_watch_ranger_smithing_scroll",             22000,  BLACKSMITH);
        reg("agotmod:wildling_fur_smithing_scroll",                    22000,  BLACKSMITH);
        reg("agotmod:thenn_levy_smithing_scroll",                      22000,  BLACKSMITH);
        reg("agotmod:nights_watch_leather_smithing_scroll",           110000,  BLACKSMITH);
        reg("agotmod:wildling_leather_smithing_scroll",               110000,  BLACKSMITH);
        reg("agotmod:wildling_chief_smithing_scroll",                 110000,  BLACKSMITH);
        reg("agotmod:thenn_plate_smithing_scroll",                    110000,  BLACKSMITH);
        reg("agotmod:bolton_levy_smithing_scroll",                    110000,  BLACKSMITH);
        reg("agotmod:manderly_levy_smithing_scroll",                  110000,  BLACKSMITH);
        reg("agotmod:stark_levy_smithing_scroll",                     110000,  BLACKSMITH);
        reg("agotmod:ironborn_levy_smithing_scroll",                  110000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_leather_smithing_scroll", 110000,  BLACKSMITH);
        reg("agotmod:nights_watch_elite_smithing_scroll",             550000,  BLACKSMITH);
        reg("agotmod:thenn_noble_smithing_scroll",                    550000,  BLACKSMITH);
        reg("agotmod:bolton_plate_smithing_scroll",                   550000,  BLACKSMITH);
        reg("agotmod:manderly_plate_smithing_scroll",                 550000,  BLACKSMITH);
        reg("agotmod:stark_plate_smithing_scroll",                    550000,  BLACKSMITH);
        reg("agotmod:ironborn_plate_smithing_scroll",                 550000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_chain_smithing_scroll",   550000,  BLACKSMITH);
        reg("agotmod:bolton_noble_smithing_scroll",                  2750000,  BLACKSMITH);
        reg("agotmod:manderly_noble_smithing_scroll",                2750000,  BLACKSMITH);
        reg("agotmod:stark_noble_smithing_scroll",                   2750000,  BLACKSMITH);
        reg("agotmod:ironborn_noble_smithing_scroll",                2750000,  BLACKSMITH);
        reg("agotmod:northern_mountain_clan_noble_smithing_scroll",  2750000,  BLACKSMITH);
        reg("minecraft:iron_sword",         320,  BLACKSMITH);
        reg("minecraft:iron_pickaxe",       480,  BLACKSMITH);
        reg("minecraft:iron_axe",           480,  BLACKSMITH);
        reg("minecraft:iron_shovel",        160,  BLACKSMITH);
        reg("minecraft:iron_hoe",           320,  BLACKSMITH);
        reg("minecraft:golden_sword",      2000,  BLACKSMITH);
        reg("minecraft:golden_pickaxe",    3000,  BLACKSMITH);
        reg("minecraft:golden_axe",        3000,  BLACKSMITH);
        reg("minecraft:golden_shovel",     1000,  BLACKSMITH);
        reg("minecraft:golden_hoe",        2000,  BLACKSMITH);
        reg("minecraft:iron_helmet",        800,  BLACKSMITH);
        reg("minecraft:iron_chestplate",   1280,  BLACKSMITH);
        reg("minecraft:iron_leggings",     1120,  BLACKSMITH);
        reg("minecraft:iron_boots",         640,  BLACKSMITH);
        reg("minecraft:chainmail_helmet",  1600,  BLACKSMITH);
        reg("minecraft:chainmail_chestplate",2560,BLACKSMITH);
        reg("minecraft:chainmail_leggings",2240,  BLACKSMITH);
        reg("minecraft:chainmail_boots",   1280,  BLACKSMITH);
        reg("minecraft:golden_helmet",     5000,  BLACKSMITH);
        reg("minecraft:golden_chestplate", 8000,  BLACKSMITH);
        reg("minecraft:golden_leggings",   7000,  BLACKSMITH);
        reg("minecraft:golden_boots",      4000,  BLACKSMITH);
        reg("agotmod:bronze_pickaxe",       600,  BLACKSMITH);
        reg("agotmod:bronze_shovel",        200,  BLACKSMITH);
        reg("agotmod:bronze_axe",           600,  BLACKSMITH);
        reg("agotmod:bronze_hoe",           400,  BLACKSMITH);
        reg("agotmod:steel_pickaxe",       1800,  BLACKSMITH);
        reg("agotmod:steel_shovel",         600,  BLACKSMITH);
        reg("agotmod:steel_axe",           1800,  BLACKSMITH);
        reg("agotmod:steel_hoe",           1200,  BLACKSMITH);
        reg("agotmod:steel_bow",           2500,  BLACKSMITH);
        reg("agotmod:arrow_bronze",          50,  BLACKSMITH);
        reg("agotmod:arrow_iron",            70,  BLACKSMITH);
        reg("agotmod:arrow_steel",          200,  BLACKSMITH);
        reg("agotmod:arrow_dragon_glass",   800,  BLACKSMITH);
        reg("agotmod:bronze_sword",         600,  BLACKSMITH);
        reg("agotmod:bronze_spatha",        800,  BLACKSMITH);
        reg("agotmod:bronze_spear",         600,  BLACKSMITH);
        reg("agotmod:bronze_pike",          750,  BLACKSMITH);
        reg("agotmod:bronze_dagger",        400,  BLACKSMITH);
        reg("agotmod:bronze_battleaxe",     900,  BLACKSMITH);
        reg("agotmod:iron_longsword",      1400,  BLACKSMITH);
        reg("agotmod:iron_spear",          1200,  BLACKSMITH);
        reg("agotmod:iron_pike",           1400,  BLACKSMITH);
        reg("agotmod:iron_mace",           1200,  BLACKSMITH);
        reg("agotmod:iron_dagger",          800,  BLACKSMITH);
        reg("agotmod:iron_battleaxe",      1600,  BLACKSMITH);
        reg("agotmod:steel_sword",         1600,  BLACKSMITH);
        reg("agotmod:steel_longsword",     2200,  BLACKSMITH);
        reg("agotmod:steel_spear",         1600,  BLACKSMITH);
        reg("agotmod:steel_pike",          2000,  BLACKSMITH);
        reg("agotmod:steel_mace",          1600,  BLACKSMITH);
        reg("agotmod:steel_dagger",        1200,  BLACKSMITH);
        reg("agotmod:steel_battleaxe",     2200,  BLACKSMITH);
        reg("agotmod:steel_halberd",       2500,  BLACKSMITH);
        reg("agotmod:noble_longsword",    60000,  BLACKSMITH);
        reg("agotmod:noble_spear",        50000,  BLACKSMITH);
        reg("agotmod:noble_pike",         55000,  BLACKSMITH);
        reg("agotmod:noble_mace",         50000,  BLACKSMITH);
        reg("agotmod:noble_dagger",       35000,  BLACKSMITH);
        reg("agotmod:noble_battleaxe",    60000,  BLACKSMITH);
        reg("agotmod:noble_halberd",      65000,  BLACKSMITH);
        reg("agotmod:dragonglass_spear",  100000,  BLACKSMITH);
        reg("agotmod:dragonglass_dagger",  80000,  BLACKSMITH);
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

    public static boolean isTraderItem(String itemKey) {
        // Trader buys/sells everything that any other trader handles
        return isGrocerItem(itemKey)
                || isButcherItem(itemKey)
                || isTannerItem(itemKey)
                || isTailorItem(itemKey)
                || isBlacksmithItem(itemKey)
                || isCarpenterItem(itemKey);
    }

    /** Trader sells at 125% of base price */
    public static int getTraderSellPrice(String itemKey) {
        return (int) Math.ceil(getItemPrice(itemKey) * 1.25);
    }

    /** Trader buys (from player) at 75% of base price */
    public static int getTraderBuyPrice(String itemKey) {
        return (int) Math.ceil(getItemPrice(itemKey) * 0.75);
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
    public static boolean isCarpenterItem(String itemKey)  { return isJobItem(itemKey, CARPENTER);  }

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

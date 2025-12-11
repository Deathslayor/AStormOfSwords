package net.darkflameproduction.agotmod.item.custom;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BannerPatterns {

    public static final DeferredRegister<Item> BANNER_PATTERN_ITEMS =
            DeferredRegister.create(Registries.ITEM, AGoTMod.MOD_ID);

    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS =
            DeferredRegister.create(Registries.BANNER_PATTERN, AGoTMod.MOD_ID);

    // Define tag keys for all banner patterns
    public static final TagKey<BannerPattern> ARRYN_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/arryn"));
    public static final TagKey<BannerPattern> BAELISH_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/baelish"));
    public static final TagKey<BannerPattern> BARATHEON_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/baratheon"));
    public static final TagKey<BannerPattern> BLACKWOOD_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/blackwood"));
    public static final TagKey<BannerPattern> BOLTON_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/bolton"));
    public static final TagKey<BannerPattern> BRACKEN_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/bracken"));
    public static final TagKey<BannerPattern> CLEGANE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/clegane"));
    public static final TagKey<BannerPattern> DAYNE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/dayne"));
    public static final TagKey<BannerPattern> FREY_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/frey"));
    public static final TagKey<BannerPattern> GREYJOY_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/greyjoy"));
    public static final TagKey<BannerPattern> HARLAW_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/harlaw"));
    public static final TagKey<BannerPattern> HIGHTOWER_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/hightower"));
    public static final TagKey<BannerPattern> KARSTARK_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/karstark"));
    public static final TagKey<BannerPattern> LANNISTER_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/lannister"));
    public static final TagKey<BannerPattern> MANDERLY_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/manderly"));
    public static final TagKey<BannerPattern> MORMONT_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/mormont"));
    public static final TagKey<BannerPattern> REDWYNE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/redwyne"));
    public static final TagKey<BannerPattern> REED_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/reed"));
    public static final TagKey<BannerPattern> REYNE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/reyne"));
    public static final TagKey<BannerPattern> ROYCE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/royce"));
    public static final TagKey<BannerPattern> STARK_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/stark"));
    public static final TagKey<BannerPattern> TARGARYEN_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/targaryen"));
    public static final TagKey<BannerPattern> TARLY_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/tarly"));
    public static final TagKey<BannerPattern> TULLY_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/tully"));
    public static final TagKey<BannerPattern> TYRELL_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/tyrell"));
    public static final TagKey<BannerPattern> UMBER_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/umber"));
    public static final TagKey<BannerPattern> VELARYON_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/velaryon"));

    // Register banner patterns for runtime
    public static final DeferredHolder<BannerPattern, BannerPattern> ARRYN_PATTERN =
            BANNER_PATTERNS.register("arryn", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn"), "arryn"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BAELISH_PATTERN =
            BANNER_PATTERNS.register("baelish", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baelish"), "baelish"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BARATHEON_PATTERN =
            BANNER_PATTERNS.register("baratheon", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baratheon"), "baratheon"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BLACKWOOD_PATTERN =
            BANNER_PATTERNS.register("blackwood", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "blackwood"), "blackwood"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BOLTON_PATTERN =
            BANNER_PATTERNS.register("bolton", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton"), "bolton"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BRACKEN_PATTERN =
            BANNER_PATTERNS.register("bracken", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bracken"), "bracken"));
    public static final DeferredHolder<BannerPattern, BannerPattern> CLEGANE_PATTERN =
            BANNER_PATTERNS.register("clegane", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "clegane"), "clegane"));
    public static final DeferredHolder<BannerPattern, BannerPattern> DAYNE_PATTERN =
            BANNER_PATTERNS.register("dayne", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dayne"), "dayne"));
    public static final DeferredHolder<BannerPattern, BannerPattern> FREY_PATTERN =
            BANNER_PATTERNS.register("frey", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "frey"), "frey"));
    public static final DeferredHolder<BannerPattern, BannerPattern> GREYJOY_PATTERN =
            BANNER_PATTERNS.register("greyjoy", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "greyjoy"), "greyjoy"));
    public static final DeferredHolder<BannerPattern, BannerPattern> HARLAW_PATTERN =
            BANNER_PATTERNS.register("harlaw", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "harlaw"), "harlaw"));
    public static final DeferredHolder<BannerPattern, BannerPattern> HIGHTOWER_PATTERN =
            BANNER_PATTERNS.register("hightower", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hightower"), "hightower"));
    public static final DeferredHolder<BannerPattern, BannerPattern> KARSTARK_PATTERN =
            BANNER_PATTERNS.register("karstark", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "karstark"), "karstark"));
    public static final DeferredHolder<BannerPattern, BannerPattern> LANNISTER_PATTERN =
            BANNER_PATTERNS.register("lannister", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "lannister"), "lannister"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MANDERLY_PATTERN =
            BANNER_PATTERNS.register("manderly", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly"), "manderly"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MORMONT_PATTERN =
            BANNER_PATTERNS.register("mormont", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mormont"), "mormont"));
    public static final DeferredHolder<BannerPattern, BannerPattern> REDWYNE_PATTERN =
            BANNER_PATTERNS.register("redwyne", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "redwyne"), "redwyne"));
    public static final DeferredHolder<BannerPattern, BannerPattern> REED_PATTERN =
            BANNER_PATTERNS.register("reed", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reed"), "reed"));
    public static final DeferredHolder<BannerPattern, BannerPattern> REYNE_PATTERN =
            BANNER_PATTERNS.register("reyne", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reyne"), "reyne"));
    public static final DeferredHolder<BannerPattern, BannerPattern> ROYCE_PATTERN =
            BANNER_PATTERNS.register("royce", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "royce"), "royce"));
    public static final DeferredHolder<BannerPattern, BannerPattern> STARK_PATTERN =
            BANNER_PATTERNS.register("stark", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stark"), "stark"));
    public static final DeferredHolder<BannerPattern, BannerPattern> TARGARYEN_PATTERN =
            BANNER_PATTERNS.register("targaryen", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen"), "targaryen"));
    public static final DeferredHolder<BannerPattern, BannerPattern> TARLY_PATTERN =
            BANNER_PATTERNS.register("tarly", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tarly"), "tarly"));
    public static final DeferredHolder<BannerPattern, BannerPattern> TULLY_PATTERN =
            BANNER_PATTERNS.register("tully", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tully"), "tully"));
    public static final DeferredHolder<BannerPattern, BannerPattern> TYRELL_PATTERN =
            BANNER_PATTERNS.register("tyrell", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tyrell"), "tyrell"));
    public static final DeferredHolder<BannerPattern, BannerPattern> UMBER_PATTERN =
            BANNER_PATTERNS.register("umber", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber"), "umber"));
    public static final DeferredHolder<BannerPattern, BannerPattern> VELARYON_PATTERN =
            BANNER_PATTERNS.register("velaryon", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "velaryon"), "velaryon"));

    // Register banner pattern items
    public static final DeferredHolder<Item, Item> ARRYN_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("arryn_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn_banner_pattern"));
                return new Item(ARRYN_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> BAELISH_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("baelish_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baelish_banner_pattern"));
                return new Item(BAELISH_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> BARATHEON_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("baratheon_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baratheon_banner_pattern"));
                return new Item(BARATHEON_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> BLACKWOOD_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("blackwood_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "blackwood_banner_pattern"));
                return new Item(BLACKWOOD_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> BOLTON_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("bolton_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton_banner_pattern"));
                return new Item(BOLTON_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> BRACKEN_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("bracken_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bracken_banner_pattern"));
                return new Item(BRACKEN_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> CLEGANE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("clegane_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "clegane_banner_pattern"));
                return new Item(CLEGANE_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> DAYNE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("dayne_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dayne_banner_pattern"));
                return new Item(DAYNE_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> FREY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("frey_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "frey_banner_pattern"));
                return new Item(FREY_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> GREYJOY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("greyjoy_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "greyjoy_banner_pattern"));
                return new Item(GREYJOY_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> HARLAW_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("harlaw_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "harlaw_banner_pattern"));
                return new Item(HARLAW_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> HIGHTOWER_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("hightower_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hightower_banner_pattern"));
                return new Item(HIGHTOWER_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> KARSTARK_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("karstark_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "karstark_banner_pattern"));
                return new Item(KARSTARK_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> LANNISTER_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("lannister_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "lannister_banner_pattern"));
                return new Item(LANNISTER_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> MANDERLY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("manderly_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly_banner_pattern"));
                return new Item(MANDERLY_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> MORMONT_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("mormont_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mormont_banner_pattern"));
                return new Item(MORMONT_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> REDWYNE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("redwyne_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "redwyne_banner_pattern"));
                return new Item(REDWYNE_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> REED_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("reed_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reed_banner_pattern"));
                return new Item(REED_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> REYNE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("reyne_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reyne_banner_pattern"));
                return new Item(REYNE_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> ROYCE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("royce_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "royce_banner_pattern"));
                return new Item(ROYCE_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> STARK_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("stark_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stark_banner_pattern"));
                return new Item(STARK_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> TARGARYEN_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("targaryen_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen_banner_pattern"));
                return new Item(TARGARYEN_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> TARLY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("tarly_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tarly_banner_pattern"));
                return new Item(TARLY_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> TULLY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("tully_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tully_banner_pattern"));
                return new Item(TULLY_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> TYRELL_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("tyrell_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tyrell_banner_pattern"));
                return new Item(TYRELL_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> UMBER_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("umber_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber_banner_pattern"));
                return new Item(UMBER_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, Item> VELARYON_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("velaryon_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "velaryon_banner_pattern"));
                return new Item(VELARYON_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey).stacksTo(1).rarity(Rarity.EPIC));
            });

    public static void register(IEventBus eventBus) {
        BANNER_PATTERNS.register(eventBus);
        BANNER_PATTERN_ITEMS.register(eventBus);
    }
}
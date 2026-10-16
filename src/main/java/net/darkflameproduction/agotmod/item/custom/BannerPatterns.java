package net.darkflameproduction.agotmod.item.custom;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
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
    public static final TagKey<BannerPattern> BLACKWOOD2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/blackwood2"));
    public static final TagKey<BannerPattern> BOLTON1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/bolton1"));
    public static final TagKey<BannerPattern> BOLTON2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/bolton2"));
    public static final TagKey<BannerPattern> BOOK_STARK_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/book_stark"));
    public static final TagKey<BannerPattern> BURLEY1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/burley1"));
    public static final TagKey<BannerPattern> CASSEL_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/cassel"));
    public static final TagKey<BannerPattern> CELTIGAR_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/celtigar"));
    public static final TagKey<BannerPattern> CERWYN_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/cerwyn"));
    public static final TagKey<BannerPattern> CONDON_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/condon"));
    public static final TagKey<BannerPattern> DAYNE2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/dayne2"));
    public static final TagKey<BannerPattern> DUSTIN1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/dustin1"));
    public static final TagKey<BannerPattern> DUSTIN2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/dustin2"));
    public static final TagKey<BannerPattern> FLINT1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/flint1"));
    public static final TagKey<BannerPattern> FLINT2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/flint2"));
    public static final TagKey<BannerPattern> FORRESTER1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/forrester1"));
    public static final TagKey<BannerPattern> FORRESTER2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/forrester2"));
    public static final TagKey<BannerPattern> FREY2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/frey2"));
    public static final TagKey<BannerPattern> GLOVER_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/glover"));
    public static final TagKey<BannerPattern> HARCLAY_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/harclay"));
    public static final TagKey<BannerPattern> HIGHTOWER1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/hightower1"));
    public static final TagKey<BannerPattern> HORNWOOD1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/hornwood1"));
    public static final TagKey<BannerPattern> HORNWOOD2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/hornwood2"));
    public static final TagKey<BannerPattern> KARSTARK2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/karstark2"));
    public static final TagKey<BannerPattern> KNOTT_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/knott"));
    public static final TagKey<BannerPattern> LIDDLE1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/liddle1"));
    public static final TagKey<BannerPattern> LIDDLE2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/liddle2"));
    public static final TagKey<BannerPattern> LOCKE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/locke"));
    public static final TagKey<BannerPattern> MAGNAR1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/magnar1"));
    public static final TagKey<BannerPattern> MAGNAR2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/magnar2"));
    public static final TagKey<BannerPattern> MANDERLY1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/manderly1"));
    public static final TagKey<BannerPattern> MANDERLY2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/manderly2"));
    public static final TagKey<BannerPattern> MANDERLY3_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/manderly3"));
    public static final TagKey<BannerPattern> MARSH_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/marsh"));
    public static final TagKey<BannerPattern> MOLLEN1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/mollen1"));
    public static final TagKey<BannerPattern> MOLLEN2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/mollen2"));
    public static final TagKey<BannerPattern> NORREY_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/norrey"));
    public static final TagKey<BannerPattern> ROYCE2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/royce2"));
    public static final TagKey<BannerPattern> RYSWELL1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/ryswell1"));
    public static final TagKey<BannerPattern> RYSWELL2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/ryswell2"));
    public static final TagKey<BannerPattern> RYSWELL3_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/ryswell3"));
    public static final TagKey<BannerPattern> STONEY_FISHER1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/stoney_fisher1"));
    public static final TagKey<BannerPattern> STONEY_FISHER2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/stoney_fisher2"));
    public static final TagKey<BannerPattern> STOUT_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/stout"));
    public static final TagKey<BannerPattern> TALLHART_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/tallhart"));
    public static final TagKey<BannerPattern> TARGARYEN2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/targaryen2"));
    public static final TagKey<BannerPattern> UMBER1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/umber1"));
    public static final TagKey<BannerPattern> UMBER2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/umber2"));
    public static final TagKey<BannerPattern> UMBER3_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/umber3"));
    public static final TagKey<BannerPattern> WHITEHILL_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/whitehill"));
    public static final TagKey<BannerPattern> WIDOWSWATCH1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/widowswatch1"));
    public static final TagKey<BannerPattern> WIDOWSWATCH2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/widowswatch2"));
    public static final TagKey<BannerPattern> WOOLFIELD_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/woolfield"));
    public static final TagKey<BannerPattern> WULL_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/wull"));
    public static final TagKey<BannerPattern> ARRYN1_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/arryn1"));
    public static final TagKey<BannerPattern> ARRYN2_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/arryn2"));
    public static final TagKey<BannerPattern> LIPPS_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/lipps"));
    public static final TagKey<BannerPattern> MOORE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/moore"));
    public static final TagKey<BannerPattern> UPCLIFF_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/upcliff"));
    public static final TagKey<BannerPattern> WAYNWOOD_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/waynwood"));
    public static final TagKey<BannerPattern> DOWNWARDS_WEDGE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/downwards_wedge"));
    public static final TagKey<BannerPattern> UPWARDS_WEDGE_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/upwards_wedge"));
    public static final TagKey<BannerPattern> SHIELD_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/shield"));

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
    public static final DeferredHolder<BannerPattern, BannerPattern> BLACKWOOD2_PATTERN =
            BANNER_PATTERNS.register("blackwood2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "blackwood2"), "blackwood2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BOLTON1_PATTERN =
            BANNER_PATTERNS.register("bolton1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton1"), "bolton1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BOLTON2_PATTERN =
            BANNER_PATTERNS.register("bolton2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton2"), "bolton2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BOOK_STARK_PATTERN =
            BANNER_PATTERNS.register("book_stark", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "book_stark"), "book_stark"));
    public static final DeferredHolder<BannerPattern, BannerPattern> BURLEY1_PATTERN =
            BANNER_PATTERNS.register("burley1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "burley1"), "burley1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> CASSEL_PATTERN =
            BANNER_PATTERNS.register("cassel", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "cassel"), "cassel"));
    public static final DeferredHolder<BannerPattern, BannerPattern> CELTIGAR_PATTERN =
            BANNER_PATTERNS.register("celtigar", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "celtigar"), "celtigar"));
    public static final DeferredHolder<BannerPattern, BannerPattern> CERWYN_PATTERN =
            BANNER_PATTERNS.register("cerwyn", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "cerwyn"), "cerwyn"));
    public static final DeferredHolder<BannerPattern, BannerPattern> CONDON_PATTERN =
            BANNER_PATTERNS.register("condon", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "condon"), "condon"));
    public static final DeferredHolder<BannerPattern, BannerPattern> DAYNE2_PATTERN =
            BANNER_PATTERNS.register("dayne2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dayne2"), "dayne2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> DUSTIN1_PATTERN =
            BANNER_PATTERNS.register("dustin1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dustin1"), "dustin1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> DUSTIN2_PATTERN =
            BANNER_PATTERNS.register("dustin2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dustin2"), "dustin2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> FLINT1_PATTERN =
            BANNER_PATTERNS.register("flint1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "flint1"), "flint1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> FLINT2_PATTERN =
            BANNER_PATTERNS.register("flint2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "flint2"), "flint2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> FORRESTER1_PATTERN =
            BANNER_PATTERNS.register("forrester1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "forrester1"), "forrester1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> FORRESTER2_PATTERN =
            BANNER_PATTERNS.register("forrester2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "forrester2"), "forrester2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> FREY2_PATTERN =
            BANNER_PATTERNS.register("frey2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "frey2"), "frey2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> GLOVER_PATTERN =
            BANNER_PATTERNS.register("glover", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "glover"), "glover"));
    public static final DeferredHolder<BannerPattern, BannerPattern> HARCLAY_PATTERN =
            BANNER_PATTERNS.register("harclay", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "harclay"), "harclay"));
    public static final DeferredHolder<BannerPattern, BannerPattern> HIGHTOWER1_PATTERN =
            BANNER_PATTERNS.register("hightower1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hightower1"), "hightower1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> HORNWOOD1_PATTERN =
            BANNER_PATTERNS.register("hornwood1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hornwood1"), "hornwood1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> HORNWOOD2_PATTERN =
            BANNER_PATTERNS.register("hornwood2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hornwood2"), "hornwood2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> KARSTARK2_PATTERN =
            BANNER_PATTERNS.register("karstark2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "karstark2"), "karstark2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> KNOTT_PATTERN =
            BANNER_PATTERNS.register("knott", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "knott"), "knott"));
    public static final DeferredHolder<BannerPattern, BannerPattern> LIDDLE1_PATTERN =
            BANNER_PATTERNS.register("liddle1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "liddle1"), "liddle1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> LIDDLE2_PATTERN =
            BANNER_PATTERNS.register("liddle2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "liddle2"), "liddle2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> LOCKE_PATTERN =
            BANNER_PATTERNS.register("locke", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "locke"), "locke"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MAGNAR1_PATTERN =
            BANNER_PATTERNS.register("magnar1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "magnar1"), "magnar1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MAGNAR2_PATTERN =
            BANNER_PATTERNS.register("magnar2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "magnar2"), "magnar2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MANDERLY1_PATTERN =
            BANNER_PATTERNS.register("manderly1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly1"), "manderly1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MANDERLY2_PATTERN =
            BANNER_PATTERNS.register("manderly2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly2"), "manderly2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MANDERLY3_PATTERN =
            BANNER_PATTERNS.register("manderly3", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly3"), "manderly3"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MARSH_PATTERN =
            BANNER_PATTERNS.register("marsh", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "marsh"), "marsh"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MOLLEN1_PATTERN =
            BANNER_PATTERNS.register("mollen1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mollen1"), "mollen1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MOLLEN2_PATTERN =
            BANNER_PATTERNS.register("mollen2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mollen2"), "mollen2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> NORREY_PATTERN =
            BANNER_PATTERNS.register("norrey", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "norrey"), "norrey"));
    public static final DeferredHolder<BannerPattern, BannerPattern> ROYCE2_PATTERN =
            BANNER_PATTERNS.register("royce2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "royce2"), "royce2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> RYSWELL1_PATTERN =
            BANNER_PATTERNS.register("ryswell1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell1"), "ryswell1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> RYSWELL2_PATTERN =
            BANNER_PATTERNS.register("ryswell2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell2"), "ryswell2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> RYSWELL3_PATTERN =
            BANNER_PATTERNS.register("ryswell3", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell3"), "ryswell3"));
    public static final DeferredHolder<BannerPattern, BannerPattern> STONEY_FISHER1_PATTERN =
            BANNER_PATTERNS.register("stoney_fisher1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stoney_fisher1"), "stoney_fisher1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> STONEY_FISHER2_PATTERN =
            BANNER_PATTERNS.register("stoney_fisher2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stoney_fisher2"), "stoney_fisher2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> STOUT_PATTERN =
            BANNER_PATTERNS.register("stout", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stout"), "stout"));
    public static final DeferredHolder<BannerPattern, BannerPattern> TALLHART_PATTERN =
            BANNER_PATTERNS.register("tallhart", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tallhart"), "tallhart"));
    public static final DeferredHolder<BannerPattern, BannerPattern> TARGARYEN2_PATTERN =
            BANNER_PATTERNS.register("targaryen2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen2"), "targaryen2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> UMBER1_PATTERN =
            BANNER_PATTERNS.register("umber1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber1"), "umber1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> UMBER2_PATTERN =
            BANNER_PATTERNS.register("umber2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber2"), "umber2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> UMBER3_PATTERN =
            BANNER_PATTERNS.register("umber3", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber3"), "umber3"));
    public static final DeferredHolder<BannerPattern, BannerPattern> WHITEHILL_PATTERN =
            BANNER_PATTERNS.register("whitehill", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "whitehill"), "whitehill"));
    public static final DeferredHolder<BannerPattern, BannerPattern> WIDOWSWATCH1_PATTERN =
            BANNER_PATTERNS.register("widowswatch1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "widowswatch1"), "widowswatch1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> WIDOWSWATCH2_PATTERN =
            BANNER_PATTERNS.register("widowswatch2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "widowswatch2"), "widowswatch2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> WOOLFIELD_PATTERN =
            BANNER_PATTERNS.register("woolfield", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "woolfield"), "woolfield"));
    public static final DeferredHolder<BannerPattern, BannerPattern> WULL_PATTERN =
            BANNER_PATTERNS.register("wull", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "wull"), "wull"));
    public static final DeferredHolder<BannerPattern, BannerPattern> ARRYN1_PATTERN =
            BANNER_PATTERNS.register("arryn1", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn1"), "arryn1"));
    public static final DeferredHolder<BannerPattern, BannerPattern> ARRYN2_PATTERN =
            BANNER_PATTERNS.register("arryn2", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn2"), "arryn2"));
    public static final DeferredHolder<BannerPattern, BannerPattern> LIPPS_PATTERN =
            BANNER_PATTERNS.register("lipps", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "lipps"), "lipps"));
    public static final DeferredHolder<BannerPattern, BannerPattern> MOORE_PATTERN =
            BANNER_PATTERNS.register("moore", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "moore"), "moore"));
    public static final DeferredHolder<BannerPattern, BannerPattern> UPCLIFF_PATTERN =
            BANNER_PATTERNS.register("upcliff", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "upcliff"), "upcliff"));
    public static final DeferredHolder<BannerPattern, BannerPattern> WAYNWOOD_PATTERN =
            BANNER_PATTERNS.register("waynwood", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "waynwood"), "waynwood"));
    public static final DeferredHolder<BannerPattern, BannerPattern> DOWNWARDS_WEDGE_PATTERN =
            BANNER_PATTERNS.register("downwards_wedge", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "downwards_wedge"), "downwards_wedge"));
    public static final DeferredHolder<BannerPattern, BannerPattern> UPWARDS_WEDGE_PATTERN =
            BANNER_PATTERNS.register("upwards_wedge", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "upwards_wedge"), "upwards_wedge"));
    public static final DeferredHolder<BannerPattern, BannerPattern> SHIELD_PATTERN =
            BANNER_PATTERNS.register("shield", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "shield"), "shield"));

    // Register banner pattern items
    public static final DeferredHolder<Item, BannerPatternItem> ARRYN_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("arryn_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn_banner_pattern"));
                return new BannerPatternItem(ARRYN_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BAELISH_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("baelish_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baelish_banner_pattern"));
                return new BannerPatternItem(BAELISH_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BARATHEON_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("baratheon_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baratheon_banner_pattern"));
                return new BannerPatternItem(BARATHEON_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BLACKWOOD_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("blackwood_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "blackwood_banner_pattern"));
                return new BannerPatternItem(BLACKWOOD_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BOLTON_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("bolton_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton_banner_pattern"));
                return new BannerPatternItem(BOLTON_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BRACKEN_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("bracken_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bracken_banner_pattern"));
                return new BannerPatternItem(BRACKEN_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> CLEGANE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("clegane_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "clegane_banner_pattern"));
                return new BannerPatternItem(CLEGANE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> DAYNE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("dayne_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dayne_banner_pattern"));
                return new BannerPatternItem(DAYNE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> FREY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("frey_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "frey_banner_pattern"));
                return new BannerPatternItem(FREY_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> GREYJOY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("greyjoy_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "greyjoy_banner_pattern"));
                return new BannerPatternItem(GREYJOY_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> HARLAW_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("harlaw_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "harlaw_banner_pattern"));
                return new BannerPatternItem(HARLAW_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> HIGHTOWER_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("hightower_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hightower_banner_pattern"));
                return new BannerPatternItem(HIGHTOWER_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> KARSTARK_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("karstark_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "karstark_banner_pattern"));
                return new BannerPatternItem(KARSTARK_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> LANNISTER_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("lannister_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "lannister_banner_pattern"));
                return new BannerPatternItem(LANNISTER_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MANDERLY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("manderly_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly_banner_pattern"));
                return new BannerPatternItem(MANDERLY_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MORMONT_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("mormont_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mormont_banner_pattern"));
                return new BannerPatternItem(MORMONT_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> REDWYNE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("redwyne_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "redwyne_banner_pattern"));
                return new BannerPatternItem(REDWYNE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> REED_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("reed_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reed_banner_pattern"));
                return new BannerPatternItem(REED_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> REYNE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("reyne_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reyne_banner_pattern"));
                return new BannerPatternItem(REYNE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> ROYCE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("royce_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "royce_banner_pattern"));
                return new BannerPatternItem(ROYCE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> STARK_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("stark_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stark_banner_pattern"));
                return new BannerPatternItem(STARK_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> TARGARYEN_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("targaryen_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen_banner_pattern"));
                return new BannerPatternItem(TARGARYEN_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> TARLY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("tarly_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tarly_banner_pattern"));
                return new BannerPatternItem(TARLY_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> TULLY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("tully_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tully_banner_pattern"));
                return new BannerPatternItem(TULLY_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> TYRELL_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("tyrell_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tyrell_banner_pattern"));
                return new BannerPatternItem(TYRELL_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> UMBER_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("umber_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber_banner_pattern"));
                return new BannerPatternItem(UMBER_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> VELARYON_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("velaryon_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "velaryon_banner_pattern"));
                return new BannerPatternItem(VELARYON_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });

    public static final DeferredHolder<Item, BannerPatternItem> BLACKWOOD2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("blackwood2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "blackwood2_banner_pattern"));
                return new BannerPatternItem(BLACKWOOD2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BOLTON1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("bolton1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton1_banner_pattern"));
                return new BannerPatternItem(BOLTON1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BOLTON2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("bolton2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton2_banner_pattern"));
                return new BannerPatternItem(BOLTON2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BOOK_STARK_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("book_stark_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "book_stark_banner_pattern"));
                return new BannerPatternItem(BOOK_STARK_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> BURLEY1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("burley1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "burley1_banner_pattern"));
                return new BannerPatternItem(BURLEY1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> CASSEL_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("cassel_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "cassel_banner_pattern"));
                return new BannerPatternItem(CASSEL_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> CELTIGAR_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("celtigar_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "celtigar_banner_pattern"));
                return new BannerPatternItem(CELTIGAR_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> CERWYN_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("cerwyn_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "cerwyn_banner_pattern"));
                return new BannerPatternItem(CERWYN_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> CONDON_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("condon_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "condon_banner_pattern"));
                return new BannerPatternItem(CONDON_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> DAYNE2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("dayne2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dayne2_banner_pattern"));
                return new BannerPatternItem(DAYNE2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> DUSTIN1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("dustin1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dustin1_banner_pattern"));
                return new BannerPatternItem(DUSTIN1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> DUSTIN2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("dustin2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dustin2_banner_pattern"));
                return new BannerPatternItem(DUSTIN2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> FLINT1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("flint1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "flint1_banner_pattern"));
                return new BannerPatternItem(FLINT1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> FLINT2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("flint2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "flint2_banner_pattern"));
                return new BannerPatternItem(FLINT2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> FORRESTER1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("forrester1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "forrester1_banner_pattern"));
                return new BannerPatternItem(FORRESTER1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> FORRESTER2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("forrester2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "forrester2_banner_pattern"));
                return new BannerPatternItem(FORRESTER2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> FREY2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("frey2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "frey2_banner_pattern"));
                return new BannerPatternItem(FREY2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> GLOVER_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("glover_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "glover_banner_pattern"));
                return new BannerPatternItem(GLOVER_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> HARCLAY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("harclay_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "harclay_banner_pattern"));
                return new BannerPatternItem(HARCLAY_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> HIGHTOWER1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("hightower1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hightower1_banner_pattern"));
                return new BannerPatternItem(HIGHTOWER1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> HORNWOOD1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("hornwood1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hornwood1_banner_pattern"));
                return new BannerPatternItem(HORNWOOD1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> HORNWOOD2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("hornwood2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hornwood2_banner_pattern"));
                return new BannerPatternItem(HORNWOOD2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> KARSTARK2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("karstark2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "karstark2_banner_pattern"));
                return new BannerPatternItem(KARSTARK2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> KNOTT_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("knott_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "knott_banner_pattern"));
                return new BannerPatternItem(KNOTT_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> LIDDLE1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("liddle1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "liddle1_banner_pattern"));
                return new BannerPatternItem(LIDDLE1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> LIDDLE2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("liddle2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "liddle2_banner_pattern"));
                return new BannerPatternItem(LIDDLE2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> LOCKE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("locke_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "locke_banner_pattern"));
                return new BannerPatternItem(LOCKE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MAGNAR1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("magnar1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "magnar1_banner_pattern"));
                return new BannerPatternItem(MAGNAR1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MAGNAR2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("magnar2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "magnar2_banner_pattern"));
                return new BannerPatternItem(MAGNAR2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MANDERLY1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("manderly1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly1_banner_pattern"));
                return new BannerPatternItem(MANDERLY1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MANDERLY2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("manderly2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly2_banner_pattern"));
                return new BannerPatternItem(MANDERLY2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MANDERLY3_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("manderly3_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly3_banner_pattern"));
                return new BannerPatternItem(MANDERLY3_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MARSH_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("marsh_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "marsh_banner_pattern"));
                return new BannerPatternItem(MARSH_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MOLLEN1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("mollen1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mollen1_banner_pattern"));
                return new BannerPatternItem(MOLLEN1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MOLLEN2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("mollen2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mollen2_banner_pattern"));
                return new BannerPatternItem(MOLLEN2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> NORREY_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("norrey_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "norrey_banner_pattern"));
                return new BannerPatternItem(NORREY_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> ROYCE2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("royce2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "royce2_banner_pattern"));
                return new BannerPatternItem(ROYCE2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> RYSWELL1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("ryswell1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell1_banner_pattern"));
                return new BannerPatternItem(RYSWELL1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> RYSWELL2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("ryswell2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell2_banner_pattern"));
                return new BannerPatternItem(RYSWELL2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> RYSWELL3_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("ryswell3_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell3_banner_pattern"));
                return new BannerPatternItem(RYSWELL3_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> STONEY_FISHER1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("stoney_fisher1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stoney_fisher1_banner_pattern"));
                return new BannerPatternItem(STONEY_FISHER1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> STONEY_FISHER2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("stoney_fisher2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stoney_fisher2_banner_pattern"));
                return new BannerPatternItem(STONEY_FISHER2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> STOUT_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("stout_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stout_banner_pattern"));
                return new BannerPatternItem(STOUT_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> TALLHART_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("tallhart_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tallhart_banner_pattern"));
                return new BannerPatternItem(TALLHART_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> TARGARYEN2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("targaryen2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen2_banner_pattern"));
                return new BannerPatternItem(TARGARYEN2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> UMBER1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("umber1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber1_banner_pattern"));
                return new BannerPatternItem(UMBER1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> UMBER2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("umber2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber2_banner_pattern"));
                return new BannerPatternItem(UMBER2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> UMBER3_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("umber3_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber3_banner_pattern"));
                return new BannerPatternItem(UMBER3_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> WHITEHILL_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("whitehill_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "whitehill_banner_pattern"));
                return new BannerPatternItem(WHITEHILL_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> WIDOWSWATCH1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("widowswatch1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "widowswatch1_banner_pattern"));
                return new BannerPatternItem(WIDOWSWATCH1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> WIDOWSWATCH2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("widowswatch2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "widowswatch2_banner_pattern"));
                return new BannerPatternItem(WIDOWSWATCH2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> WOOLFIELD_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("woolfield_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "woolfield_banner_pattern"));
                return new BannerPatternItem(WOOLFIELD_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> WULL_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("wull_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "wull_banner_pattern"));
                return new BannerPatternItem(WULL_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> ARRYN1_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("arryn1_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn1_banner_pattern"));
                return new BannerPatternItem(ARRYN1_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> ARRYN2_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("arryn2_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn2_banner_pattern"));
                return new BannerPatternItem(ARRYN2_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> LIPPS_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("lipps_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "lipps_banner_pattern"));
                return new BannerPatternItem(LIPPS_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> MOORE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("moore_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "moore_banner_pattern"));
                return new BannerPatternItem(MOORE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> UPCLIFF_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("upcliff_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "upcliff_banner_pattern"));
                return new BannerPatternItem(UPCLIFF_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> WAYNWOOD_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("waynwood_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "waynwood_banner_pattern"));
                return new BannerPatternItem(WAYNWOOD_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> DOWNWARDS_WEDGE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("downwards_wedge_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "downwards_wedge_banner_pattern"));
                return new BannerPatternItem(DOWNWARDS_WEDGE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> UPWARDS_WEDGE_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("upwards_wedge_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "upwards_wedge_banner_pattern"));
                return new BannerPatternItem(UPWARDS_WEDGE_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });
    public static final DeferredHolder<Item, BannerPatternItem> SHIELD_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("shield_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "shield_banner_pattern"));
                return new BannerPatternItem(SHIELD_PATTERN_TAG, new Item.Properties()
                        .stacksTo(1).rarity(Rarity.EPIC));
            });

    public static void register(IEventBus eventBus) {
        BANNER_PATTERNS.register(eventBus);
        BANNER_PATTERN_ITEMS.register(eventBus);
    }
}

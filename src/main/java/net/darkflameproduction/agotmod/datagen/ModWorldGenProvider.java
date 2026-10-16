package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.worldgen.ModBiomeModifiers;
import net.darkflameproduction.agotmod.worldgen.ModConfiguredFeatures;
import net.darkflameproduction.agotmod.worldgen.ModplacedFeatures;
import net.darkflameproduction.agotmod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    // All Banner Pattern Registry Keys
    public static final ResourceKey<BannerPattern> ARRYN = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn"));
    public static final ResourceKey<BannerPattern> BAELISH = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baelish"));
    public static final ResourceKey<BannerPattern> BARATHEON = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baratheon"));
    public static final ResourceKey<BannerPattern> BLACKWOOD = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "blackwood"));
    public static final ResourceKey<BannerPattern> BOLTON = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton"));
    public static final ResourceKey<BannerPattern> BRACKEN = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bracken"));
    public static final ResourceKey<BannerPattern> CLEGANE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "clegane"));
    public static final ResourceKey<BannerPattern> DAYNE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dayne"));
    public static final ResourceKey<BannerPattern> FREY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "frey"));
    public static final ResourceKey<BannerPattern> GREYJOY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "greyjoy"));
    public static final ResourceKey<BannerPattern> HARLAW = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "harlaw"));
    public static final ResourceKey<BannerPattern> HIGHTOWER = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hightower"));
    public static final ResourceKey<BannerPattern> KARSTARK = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "karstark"));
    public static final ResourceKey<BannerPattern> LANNISTER = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "lannister"));
    public static final ResourceKey<BannerPattern> MANDERLY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly"));
    public static final ResourceKey<BannerPattern> MORMONT = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mormont"));
    public static final ResourceKey<BannerPattern> REDWYNE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "redwyne"));
    public static final ResourceKey<BannerPattern> REED = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reed"));
    public static final ResourceKey<BannerPattern> REYNE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reyne"));
    public static final ResourceKey<BannerPattern> ROYCE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "royce"));
    public static final ResourceKey<BannerPattern> STARK = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stark"));
    public static final ResourceKey<BannerPattern> TARGARYEN = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen"));
    public static final ResourceKey<BannerPattern> TARLY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tarly"));
    public static final ResourceKey<BannerPattern> TULLY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tully"));
    public static final ResourceKey<BannerPattern> TYRELL = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tyrell"));
    public static final ResourceKey<BannerPattern> UMBER = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber"));
    public static final ResourceKey<BannerPattern> VELARYON = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "velaryon"));
    public static final ResourceKey<BannerPattern> BLACKWOOD2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "blackwood2"));
    public static final ResourceKey<BannerPattern> BOLTON1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton1"));
    public static final ResourceKey<BannerPattern> BOLTON2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton2"));
    public static final ResourceKey<BannerPattern> BOOK_STARK = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "book_stark"));
    public static final ResourceKey<BannerPattern> BURLEY1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "burley1"));
    public static final ResourceKey<BannerPattern> CASSEL = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "cassel"));
    public static final ResourceKey<BannerPattern> CELTIGAR = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "celtigar"));
    public static final ResourceKey<BannerPattern> CERWYN = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "cerwyn"));
    public static final ResourceKey<BannerPattern> CONDON = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "condon"));
    public static final ResourceKey<BannerPattern> DAYNE2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dayne2"));
    public static final ResourceKey<BannerPattern> DUSTIN1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dustin1"));
    public static final ResourceKey<BannerPattern> DUSTIN2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dustin2"));
    public static final ResourceKey<BannerPattern> FLINT1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "flint1"));
    public static final ResourceKey<BannerPattern> FLINT2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "flint2"));
    public static final ResourceKey<BannerPattern> FORRESTER1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "forrester1"));
    public static final ResourceKey<BannerPattern> FORRESTER2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "forrester2"));
    public static final ResourceKey<BannerPattern> FREY2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "frey2"));
    public static final ResourceKey<BannerPattern> GLOVER = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "glover"));
    public static final ResourceKey<BannerPattern> HARCLAY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "harclay"));
    public static final ResourceKey<BannerPattern> HIGHTOWER1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hightower1"));
    public static final ResourceKey<BannerPattern> HORNWOOD1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hornwood1"));
    public static final ResourceKey<BannerPattern> HORNWOOD2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hornwood2"));
    public static final ResourceKey<BannerPattern> KARSTARK2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "karstark2"));
    public static final ResourceKey<BannerPattern> KNOTT = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "knott"));
    public static final ResourceKey<BannerPattern> LIDDLE1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "liddle1"));
    public static final ResourceKey<BannerPattern> LIDDLE2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "liddle2"));
    public static final ResourceKey<BannerPattern> LOCKE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "locke"));
    public static final ResourceKey<BannerPattern> MAGNAR1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "magnar1"));
    public static final ResourceKey<BannerPattern> MAGNAR2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "magnar2"));
    public static final ResourceKey<BannerPattern> MANDERLY1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly1"));
    public static final ResourceKey<BannerPattern> MANDERLY2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly2"));
    public static final ResourceKey<BannerPattern> MANDERLY3 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly3"));
    public static final ResourceKey<BannerPattern> MARSH = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "marsh"));
    public static final ResourceKey<BannerPattern> MOLLEN1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mollen1"));
    public static final ResourceKey<BannerPattern> MOLLEN2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mollen2"));
    public static final ResourceKey<BannerPattern> NORREY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "norrey"));
    public static final ResourceKey<BannerPattern> ROYCE2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "royce2"));
    public static final ResourceKey<BannerPattern> RYSWELL1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell1"));
    public static final ResourceKey<BannerPattern> RYSWELL2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell2"));
    public static final ResourceKey<BannerPattern> RYSWELL3 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "ryswell3"));
    public static final ResourceKey<BannerPattern> STONEY_FISHER1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stoney_fisher1"));
    public static final ResourceKey<BannerPattern> STONEY_FISHER2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stoney_fisher2"));
    public static final ResourceKey<BannerPattern> STOUT = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stout"));
    public static final ResourceKey<BannerPattern> TALLHART = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tallhart"));
    public static final ResourceKey<BannerPattern> TARGARYEN2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen2"));
    public static final ResourceKey<BannerPattern> UMBER1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber1"));
    public static final ResourceKey<BannerPattern> UMBER2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber2"));
    public static final ResourceKey<BannerPattern> UMBER3 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber3"));
    public static final ResourceKey<BannerPattern> WHITEHILL = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "whitehill"));
    public static final ResourceKey<BannerPattern> WIDOWSWATCH1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "widowswatch1"));
    public static final ResourceKey<BannerPattern> WIDOWSWATCH2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "widowswatch2"));
    public static final ResourceKey<BannerPattern> WOOLFIELD = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "woolfield"));
    public static final ResourceKey<BannerPattern> WULL = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "wull"));
    public static final ResourceKey<BannerPattern> ARRYN1 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn1"));
    public static final ResourceKey<BannerPattern> ARRYN2 = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn2"));
    public static final ResourceKey<BannerPattern> LIPPS = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "lipps"));
    public static final ResourceKey<BannerPattern> MOORE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "moore"));
    public static final ResourceKey<BannerPattern> UPCLIFF = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "upcliff"));
    public static final ResourceKey<BannerPattern> WAYNWOOD = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "waynwood"));
    public static final ResourceKey<BannerPattern> DOWNWARDS_WEDGE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "downwards_wedge"));
    public static final ResourceKey<BannerPattern> UPWARDS_WEDGE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "upwards_wedge"));
    public static final ResourceKey<BannerPattern> SHIELD = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "shield"));

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModplacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.BIOME, ModBiomes::boostrap)
            .add(Registries.BANNER_PATTERN, ModWorldGenProvider::bootstrapBannerPatterns);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AGoTMod.MOD_ID));
    }

    private static void bootstrapBannerPatterns(BootstrapContext<BannerPattern> context) {
        context.register(ARRYN, new BannerPattern(ARRYN.location(), "arryn"));
        context.register(BAELISH, new BannerPattern(BAELISH.location(), "baelish"));
        context.register(BARATHEON, new BannerPattern(BARATHEON.location(), "baratheon"));
        context.register(BLACKWOOD, new BannerPattern(BLACKWOOD.location(), "blackwood"));
        context.register(BOLTON, new BannerPattern(BOLTON.location(), "bolton"));
        context.register(BRACKEN, new BannerPattern(BRACKEN.location(), "bracken"));
        context.register(CLEGANE, new BannerPattern(CLEGANE.location(), "clegane"));
        context.register(DAYNE, new BannerPattern(DAYNE.location(), "dayne"));
        context.register(FREY, new BannerPattern(FREY.location(), "frey"));
        context.register(GREYJOY, new BannerPattern(GREYJOY.location(), "greyjoy"));
        context.register(HARLAW, new BannerPattern(HARLAW.location(), "harlaw"));
        context.register(HIGHTOWER, new BannerPattern(HIGHTOWER.location(), "hightower"));
        context.register(KARSTARK, new BannerPattern(KARSTARK.location(), "karstark"));
        context.register(LANNISTER, new BannerPattern(LANNISTER.location(), "lannister"));
        context.register(MANDERLY, new BannerPattern(MANDERLY.location(), "manderly"));
        context.register(MORMONT, new BannerPattern(MORMONT.location(), "mormont"));
        context.register(REDWYNE, new BannerPattern(REDWYNE.location(), "redwyne"));
        context.register(REED, new BannerPattern(REED.location(), "reed"));
        context.register(REYNE, new BannerPattern(REYNE.location(), "reyne"));
        context.register(ROYCE, new BannerPattern(ROYCE.location(), "royce"));
        context.register(STARK, new BannerPattern(STARK.location(), "stark"));
        context.register(TARGARYEN, new BannerPattern(TARGARYEN.location(), "targaryen"));
        context.register(TARLY, new BannerPattern(TARLY.location(), "tarly"));
        context.register(TULLY, new BannerPattern(TULLY.location(), "tully"));
        context.register(TYRELL, new BannerPattern(TYRELL.location(), "tyrell"));
        context.register(UMBER, new BannerPattern(UMBER.location(), "umber"));
        context.register(VELARYON, new BannerPattern(VELARYON.location(), "velaryon"));
        context.register(BLACKWOOD2, new BannerPattern(BLACKWOOD2.location(), "blackwood2"));
        context.register(BOLTON1, new BannerPattern(BOLTON1.location(), "bolton1"));
        context.register(BOLTON2, new BannerPattern(BOLTON2.location(), "bolton2"));
        context.register(BOOK_STARK, new BannerPattern(BOOK_STARK.location(), "book_stark"));
        context.register(BURLEY1, new BannerPattern(BURLEY1.location(), "burley1"));
        context.register(CASSEL, new BannerPattern(CASSEL.location(), "cassel"));
        context.register(CELTIGAR, new BannerPattern(CELTIGAR.location(), "celtigar"));
        context.register(CERWYN, new BannerPattern(CERWYN.location(), "cerwyn"));
        context.register(CONDON, new BannerPattern(CONDON.location(), "condon"));
        context.register(DAYNE2, new BannerPattern(DAYNE2.location(), "dayne2"));
        context.register(DUSTIN1, new BannerPattern(DUSTIN1.location(), "dustin1"));
        context.register(DUSTIN2, new BannerPattern(DUSTIN2.location(), "dustin2"));
        context.register(FLINT1, new BannerPattern(FLINT1.location(), "flint1"));
        context.register(FLINT2, new BannerPattern(FLINT2.location(), "flint2"));
        context.register(FORRESTER1, new BannerPattern(FORRESTER1.location(), "forrester1"));
        context.register(FORRESTER2, new BannerPattern(FORRESTER2.location(), "forrester2"));
        context.register(FREY2, new BannerPattern(FREY2.location(), "frey2"));
        context.register(GLOVER, new BannerPattern(GLOVER.location(), "glover"));
        context.register(HARCLAY, new BannerPattern(HARCLAY.location(), "harclay"));
        context.register(HIGHTOWER1, new BannerPattern(HIGHTOWER1.location(), "hightower1"));
        context.register(HORNWOOD1, new BannerPattern(HORNWOOD1.location(), "hornwood1"));
        context.register(HORNWOOD2, new BannerPattern(HORNWOOD2.location(), "hornwood2"));
        context.register(KARSTARK2, new BannerPattern(KARSTARK2.location(), "karstark2"));
        context.register(KNOTT, new BannerPattern(KNOTT.location(), "knott"));
        context.register(LIDDLE1, new BannerPattern(LIDDLE1.location(), "liddle1"));
        context.register(LIDDLE2, new BannerPattern(LIDDLE2.location(), "liddle2"));
        context.register(LOCKE, new BannerPattern(LOCKE.location(), "locke"));
        context.register(MAGNAR1, new BannerPattern(MAGNAR1.location(), "magnar1"));
        context.register(MAGNAR2, new BannerPattern(MAGNAR2.location(), "magnar2"));
        context.register(MANDERLY1, new BannerPattern(MANDERLY1.location(), "manderly1"));
        context.register(MANDERLY2, new BannerPattern(MANDERLY2.location(), "manderly2"));
        context.register(MANDERLY3, new BannerPattern(MANDERLY3.location(), "manderly3"));
        context.register(MARSH, new BannerPattern(MARSH.location(), "marsh"));
        context.register(MOLLEN1, new BannerPattern(MOLLEN1.location(), "mollen1"));
        context.register(MOLLEN2, new BannerPattern(MOLLEN2.location(), "mollen2"));
        context.register(NORREY, new BannerPattern(NORREY.location(), "norrey"));
        context.register(ROYCE2, new BannerPattern(ROYCE2.location(), "royce2"));
        context.register(RYSWELL1, new BannerPattern(RYSWELL1.location(), "ryswell1"));
        context.register(RYSWELL2, new BannerPattern(RYSWELL2.location(), "ryswell2"));
        context.register(RYSWELL3, new BannerPattern(RYSWELL3.location(), "ryswell3"));
        context.register(STONEY_FISHER1, new BannerPattern(STONEY_FISHER1.location(), "stoney_fisher1"));
        context.register(STONEY_FISHER2, new BannerPattern(STONEY_FISHER2.location(), "stoney_fisher2"));
        context.register(STOUT, new BannerPattern(STOUT.location(), "stout"));
        context.register(TALLHART, new BannerPattern(TALLHART.location(), "tallhart"));
        context.register(TARGARYEN2, new BannerPattern(TARGARYEN2.location(), "targaryen2"));
        context.register(UMBER1, new BannerPattern(UMBER1.location(), "umber1"));
        context.register(UMBER2, new BannerPattern(UMBER2.location(), "umber2"));
        context.register(UMBER3, new BannerPattern(UMBER3.location(), "umber3"));
        context.register(WHITEHILL, new BannerPattern(WHITEHILL.location(), "whitehill"));
        context.register(WIDOWSWATCH1, new BannerPattern(WIDOWSWATCH1.location(), "widowswatch1"));
        context.register(WIDOWSWATCH2, new BannerPattern(WIDOWSWATCH2.location(), "widowswatch2"));
        context.register(WOOLFIELD, new BannerPattern(WOOLFIELD.location(), "woolfield"));
        context.register(WULL, new BannerPattern(WULL.location(), "wull"));
        context.register(ARRYN1, new BannerPattern(ARRYN1.location(), "arryn1"));
        context.register(ARRYN2, new BannerPattern(ARRYN2.location(), "arryn2"));
        context.register(LIPPS, new BannerPattern(LIPPS.location(), "lipps"));
        context.register(MOORE, new BannerPattern(MOORE.location(), "moore"));
        context.register(UPCLIFF, new BannerPattern(UPCLIFF.location(), "upcliff"));
        context.register(WAYNWOOD, new BannerPattern(WAYNWOOD.location(), "waynwood"));
        context.register(DOWNWARDS_WEDGE, new BannerPattern(DOWNWARDS_WEDGE.location(), "downwards_wedge"));
        context.register(UPWARDS_WEDGE, new BannerPattern(UPWARDS_WEDGE.location(), "upwards_wedge"));
        context.register(SHIELD, new BannerPattern(SHIELD.location(), "shield"));
    }
}

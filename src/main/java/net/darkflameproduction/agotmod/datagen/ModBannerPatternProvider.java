package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBannerPatternProvider extends TagsProvider<BannerPattern> {

    public ModBannerPatternProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.BANNER_PATTERN, lookupProvider, AGoTMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        System.out.println("DEBUG: Adding banner pattern tags");

        // Create banner pattern tags for all Game of Thrones houses
        createBannerPatternTag("arryn");
        createBannerPatternTag("baelish");
        createBannerPatternTag("baratheon");
        createBannerPatternTag("blackwood");
        createBannerPatternTag("bolton");
        createBannerPatternTag("bracken");
        createBannerPatternTag("clegane");
        createBannerPatternTag("dayne");
        createBannerPatternTag("frey");
        createBannerPatternTag("greyjoy");
        createBannerPatternTag("harlaw");
        createBannerPatternTag("hightower");
        createBannerPatternTag("karstark");
        createBannerPatternTag("lannister");
        createBannerPatternTag("manderly");
        createBannerPatternTag("mormont");
        createBannerPatternTag("redwyne");
        createBannerPatternTag("reed");
        createBannerPatternTag("reyne");
        createBannerPatternTag("royce");
        createBannerPatternTag("stark");
        createBannerPatternTag("targaryen");
        createBannerPatternTag("tarly");
        createBannerPatternTag("tully");
        createBannerPatternTag("tyrell");
        createBannerPatternTag("umber");
        createBannerPatternTag("velaryon");
        createBannerPatternTag("blackwood2");
        createBannerPatternTag("bolton1");
        createBannerPatternTag("bolton2");
        createBannerPatternTag("book_stark");
        createBannerPatternTag("burley1");
        createBannerPatternTag("cassel");
        createBannerPatternTag("celtigar");
        createBannerPatternTag("cerwyn");
        createBannerPatternTag("condon");
        createBannerPatternTag("dayne2");
        createBannerPatternTag("dustin1");
        createBannerPatternTag("dustin2");
        createBannerPatternTag("flint1");
        createBannerPatternTag("flint2");
        createBannerPatternTag("forrester1");
        createBannerPatternTag("forrester2");
        createBannerPatternTag("frey2");
        createBannerPatternTag("glover");
        createBannerPatternTag("harclay");
        createBannerPatternTag("hightower1");
        createBannerPatternTag("hornwood1");
        createBannerPatternTag("hornwood2");
        createBannerPatternTag("karstark2");
        createBannerPatternTag("knott");
        createBannerPatternTag("liddle1");
        createBannerPatternTag("liddle2");
        createBannerPatternTag("locke");
        createBannerPatternTag("magnar1");
        createBannerPatternTag("magnar2");
        createBannerPatternTag("manderly1");
        createBannerPatternTag("manderly2");
        createBannerPatternTag("manderly3");
        createBannerPatternTag("marsh");
        createBannerPatternTag("mollen1");
        createBannerPatternTag("mollen2");
        createBannerPatternTag("norrey");
        createBannerPatternTag("royce2");
        createBannerPatternTag("ryswell1");
        createBannerPatternTag("ryswell2");
        createBannerPatternTag("ryswell3");
        createBannerPatternTag("stoney_fisher1");
        createBannerPatternTag("stoney_fisher2");
        createBannerPatternTag("stout");
        createBannerPatternTag("tallhart");
        createBannerPatternTag("targaryen2");
        createBannerPatternTag("umber1");
        createBannerPatternTag("umber2");
        createBannerPatternTag("umber3");
        createBannerPatternTag("whitehill");
        createBannerPatternTag("widowswatch1");
        createBannerPatternTag("widowswatch2");
        createBannerPatternTag("woolfield");
        createBannerPatternTag("wull");
        createBannerPatternTag("arryn1");
        createBannerPatternTag("arryn2");
        createBannerPatternTag("lipps");
        createBannerPatternTag("moore");
        createBannerPatternTag("upcliff");
        createBannerPatternTag("waynwood");
        createBannerPatternTag("downwards_wedge");
        createBannerPatternTag("upwards_wedge");
        createBannerPatternTag("shield");

        System.out.println("DEBUG: Added all Game of Thrones house banner pattern tags");
    }

    private void createBannerPatternTag(String patternName) {
        TagKey<BannerPattern> bannerTag = TagKey.create(Registries.BANNER_PATTERN,
                ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/" + patternName));

        // Use addOptional() temporarily to allow the tag to be created even if the pattern doesn't exist during data gen
        this.tag(bannerTag).addOptional(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, patternName));
    }
}

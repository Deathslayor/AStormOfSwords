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

        // Create the banner pattern tags for linking items to patterns
        createBannerPatternTag("targaryen");

        System.out.println("DEBUG: Added targaryen banner pattern tag");

        // Add more banner patterns here as you create them
        // createBannerPatternTag("stark");
        // createBannerPatternTag("lannister");
        // etc.
    }

    private void createBannerPatternTag(String patternName) {
        TagKey<BannerPattern> bannerTag = TagKey.create(Registries.BANNER_PATTERN,
                ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/" + patternName));

        // Use addOptional() temporarily to allow the tag to be created even if the pattern doesn't exist during data gen
        this.tag(bannerTag).addOptional(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, patternName));
    }
}
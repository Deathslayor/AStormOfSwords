package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

// This class extends PoiTypeTagsProvider and is used for adding custom job site tags
// PoiTypeTags are used to associate points of interest (job sites) with specific jobs for villagers
public class ModPoiTypeTagsProvider extends PoiTypeTagsProvider {
    // Constructor for ModPoiTypeTagsProvider
    public ModPoiTypeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        // Call the constructor of the superclass (PoiTypeTagsProvider)
        super(pOutput, pProvider, AGoTMod.MOD_ID, existingFileHelper);
    }

    // Method to add tags to points of interest
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        // Add the custom job site tag to the ACQUIRABLE_JOB_SITE tag
        // This associates the "mint_poi" point of interest with jobs in Minecraft
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptional(AGoTMod.id("mint_poi"));
    }
}

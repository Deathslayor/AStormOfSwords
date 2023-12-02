package net.stormofsorts.agotmod.item;

import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier BRONZE = TierSortingRegistry.registerTier(
        new ForgeTier(2, 170, 7F, 2.5F, 14,
                ModTags.Blocks.NEEDS_BRONZE_TOOL, () -> Ingredient.of(ModItems.BRONZE_INGOT.get())),
                new ResourceLocation(AGoTMod.MOD_ID, "bronze_ingot"), List.of(Tiers.IRON), List.of());

    public static final Tier STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(4, 2031, 9.0F, 4.0F, 15,
                ModTags.Blocks.NEEDS_STEEL_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
                new ResourceLocation(AGoTMod.MOD_ID, "steel_ingot"), List.of(Tiers.NETHERITE), List.of());




}

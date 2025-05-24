package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

// Class for generating data pack recipes
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    // Constructor
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput pOutput) {
        super(provider, pOutput);
    }

    private void generateStonecutterRecipes() {
        // Define your block pattern numbers
        String[] patterns = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38"}; // Add all your pattern numbers

        // Loop through each pattern
        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.REDKEEP_VARIANTS.get(i); // Changed to REDKEEP_VARIANTS
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "redkeep_" + i; // Changed pattern prefix to redkeep

            // From vanilla Granite to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(ModBLocks.REDKEEP_STONE_BLOCK.get()), // Changed to Blocks.GRANITE
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(ModBLocks.REDKEEP_STONE_BLOCK.get()), has(ModBLocks.REDKEEP_STONE_BLOCK.get()))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(ModBLocks.REDKEEP_STONE_BLOCK.get()), // Changed to Blocks.GRANITE
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(ModBLocks.REDKEEP_STONE_BLOCK.get()), has(ModBLocks.REDKEEP_STONE_BLOCK.get()))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(ModBLocks.REDKEEP_STONE_BLOCK.get()), // Changed to Blocks.GRANITE
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(ModBLocks.REDKEEP_STONE_BLOCK.get()), has(ModBLocks.REDKEEP_STONE_BLOCK.get()))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(ModBLocks.REDKEEP_STONE_BLOCK.get()), // Changed to Blocks.GRANITE
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(ModBLocks.REDKEEP_STONE_BLOCK.get()), has(ModBLocks.REDKEEP_STONE_BLOCK.get()))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 16 || i == 17 || i == 18) continue;

            ModBLocks.BlockSet set = ModBLocks.RSANDSTONE_VARIANTS.get(i); // Changed to RSANDSTONE_VARIANTS
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "rsandstone_" + i; // Changed pattern prefix to rsandstone

            // From vanilla Red Sandstone to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.RED_SANDSTONE), // Changed to RED_SANDSTONE
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.RED_SANDSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.RED_SANDSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.RED_SANDSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 7, 9, and 15
            if (i == 7 || i == 9 || i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.MUD_BRICK_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "mud_" + i;

            // From vanilla Mud to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.MUD),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.MUD), has(Blocks.MUD))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.MUD),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.MUD), has(Blocks.MUD))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.MUD),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.MUD), has(Blocks.MUD))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.MUD),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.MUD), has(Blocks.MUD))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }


        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 16 || i == 17 || i == 18) continue;

            ModBLocks.BlockSet set = ModBLocks.SANDSTONE_VARIANTS.get(i); // Changed to SANDSTONE_VARIANTS
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "sandstone_" + i; // Changed pattern prefix to sandstone

            // From vanilla Sandstone to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.SANDSTONE), // Changed to SANDSTONE
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.SANDSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.SANDSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.SANDSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 16 || i == 17 || i == 18) continue;

            ModBLocks.BlockSet set = ModBLocks.SSTONE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "sstone_" + i;

            // From vanilla Smooth Stone to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.SMOOTH_STONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.SMOOTH_STONE), has(Blocks.SMOOTH_STONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.SMOOTH_STONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.SMOOTH_STONE), has(Blocks.SMOOTH_STONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.SMOOTH_STONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.SMOOTH_STONE), has(Blocks.SMOOTH_STONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.SMOOTH_STONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.SMOOTH_STONE), has(Blocks.SMOOTH_STONE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 5 || i == 15 || i == 19 || i == 23) continue;

            ModBLocks.BlockSet set = ModBLocks.STONE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "stone_" + i;

            // From vanilla Stone to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.STONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.STONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.STONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.STONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 1 || i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.BASALT_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "basalt_" + i;

            // From vanilla Basalt to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BASALT),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.BASALT), has(Blocks.BASALT))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BASALT),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.BASALT), has(Blocks.BASALT))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BASALT),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.BASALT), has(Blocks.BASALT))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BASALT),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.BASALT), has(Blocks.BASALT))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 2 || i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.BRICKS_VARIANTS.get(i); // Changed to BRICK_VARIANTS
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "bricks_" + i; // Changed pattern prefix to bricks

            // From vanilla Bricks to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BRICKS), // Changed to Blocks.BRICKS
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BRICKS), // Changed to Blocks.BRICKS
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BRICKS), // Changed to Blocks.BRICKS
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BRICKS), // Changed to Blocks.BRICKS
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.CALCITE_VARIANTS.get(i); // Changed to CALCITE_VARIANTS
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "calcite_" + i; // Changed pattern prefix to calcite

            // From vanilla Calcite to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.CALCITE), // Changed to Blocks.CALCITE
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.CALCITE), has(Blocks.CALCITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.CALCITE), // Changed to Blocks.CALCITE
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.CALCITE), has(Blocks.CALCITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.CALCITE), // Changed to Blocks.CALCITE
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.CALCITE), has(Blocks.CALCITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.CALCITE), // Changed to Blocks.CALCITE
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.CALCITE), has(Blocks.CALCITE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 4 || i == 15 || i == 19) continue;

            ModBLocks.BlockSet set = ModBLocks.CDEEPSLATE_VARIANTS.get(i); // Changed to CDEEPSLATE_VARIANTS
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "cdeepslate_" + i; // Changed pattern prefix to cdeepslate

            // From vanilla Cobbled Deepslate to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.COBBLED_DEEPSLATE), // Changed to COBBLED_DEEPSLATE
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.COBBLED_DEEPSLATE), has(Blocks.COBBLED_DEEPSLATE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.COBBLED_DEEPSLATE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.COBBLED_DEEPSLATE), has(Blocks.COBBLED_DEEPSLATE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.COBBLED_DEEPSLATE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.COBBLED_DEEPSLATE), has(Blocks.COBBLED_DEEPSLATE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.COBBLED_DEEPSLATE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.COBBLED_DEEPSLATE), has(Blocks.COBBLED_DEEPSLATE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.GRANITE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "granite_" + i;

            // From vanilla Granite to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.GRANITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.GRANITE), has(Blocks.GRANITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.GRANITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.GRANITE), has(Blocks.GRANITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.GRANITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.GRANITE), has(Blocks.GRANITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.GRANITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.GRANITE), has(Blocks.GRANITE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.ANDESITE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "andesite_" + i;

            // From vanilla Andesite to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.ANDESITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.ANDESITE), has(Blocks.ANDESITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.ANDESITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.ANDESITE), has(Blocks.ANDESITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.ANDESITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.ANDESITE), has(Blocks.ANDESITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.ANDESITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.ANDESITE), has(Blocks.ANDESITE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 10 || i == 15 || i == 20 || i == 21) continue;

            ModBLocks.BlockSet set = ModBLocks.TUFF_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "tuff_" + i;

            // From vanilla Tuff to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.TUFF),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.TUFF), has(Blocks.TUFF))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.TUFF),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.TUFF), has(Blocks.TUFF))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.TUFF),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.TUFF), has(Blocks.TUFF))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.TUFF),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.TUFF), has(Blocks.TUFF))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }


        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.DIORITE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "diorite_" + i;

            // From vanilla Diorite to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.DIORITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.DIORITE), has(Blocks.DIORITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.DIORITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.DIORITE), has(Blocks.DIORITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.DIORITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.DIORITE), has(Blocks.DIORITE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.DIORITE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.DIORITE), has(Blocks.DIORITE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }


        for (int i = 1; i <= 38; i++) {
            if (i == 14 || i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.QUARTZ_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "quartz_" + i;

            // From vanilla Quartz Block to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.QUARTZ_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.QUARTZ_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.QUARTZ_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.QUARTZ_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }



        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 22 || i == 31) continue;

            ModBLocks.BlockSet set = ModBLocks.BLACKSTONE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "blackstone_" + i;

            // From vanilla Blackstone to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BLACKSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.BLACKSTONE), has(Blocks.BLACKSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BLACKSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.BLACKSTONE), has(Blocks.BLACKSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BLACKSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.BLACKSTONE), has(Blocks.BLACKSTONE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BLACKSTONE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.BLACKSTONE), has(Blocks.BLACKSTONE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 35) continue;

            ModBLocks.BlockSet set = ModBLocks.BONE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "bone_" + i;

            // From vanilla Bone to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BONE_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.BONE_BLOCK), has(Blocks.BONE_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BONE_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.BONE_BLOCK), has(Blocks.BONE_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BONE_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.BONE_BLOCK), has(Blocks.BONE_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.BONE_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.BONE_BLOCK), has(Blocks.BONE_BLOCK))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.DRIPSTONE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "dripstone_" + i;

            // From vanilla Dripstone to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.DRIPSTONE_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.DRIPSTONE_BLOCK), has(Blocks.DRIPSTONE_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.DRIPSTONE_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.DRIPSTONE_BLOCK), has(Blocks.DRIPSTONE_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.DRIPSTONE_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.DRIPSTONE_BLOCK), has(Blocks.DRIPSTONE_BLOCK))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.DRIPSTONE_BLOCK),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.DRIPSTONE_BLOCK), has(Blocks.DRIPSTONE_BLOCK))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }
        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.PACKED_ICE_VARIANTS.get(i);
            if (set == null) {
                System.err.println("BlockSet missing for pattern index " + i);
                continue;
            }

            ItemLike base = set.base().get().asItem();
            ItemLike stairs = set.stairs().get().asItem();
            ItemLike slab = set.slab().get().asItem();
            ItemLike wall = set.wall().get().asItem();
            String pattern = "packed_ice_" + i;

            // From vanilla Packed Ice to each variant
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.PACKED_ICE),
                            RecipeCategory.BUILDING_BLOCKS,
                            base)
                    .unlockedBy(getHasName(Blocks.PACKED_ICE), has(Blocks.PACKED_ICE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.PACKED_ICE),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(Blocks.PACKED_ICE), has(Blocks.PACKED_ICE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.PACKED_ICE),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(Blocks.PACKED_ICE), has(Blocks.PACKED_ICE))
                    .save(this.output);

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(Blocks.PACKED_ICE),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(Blocks.PACKED_ICE), has(Blocks.PACKED_ICE))
                    .save(this.output);

            // From variant block to its shaped versions
            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            stairs)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_stairs_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            slab, 2)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_slab_from_" + pattern + "_block");

            SingleItemRecipeBuilder.stonecutting(
                            Ingredient.of(base),
                            RecipeCategory.BUILDING_BLOCKS,
                            wall)
                    .unlockedBy(getHasName(base), has(base))
                    .save(this.output, pattern + "_wall_from_" + pattern + "_block");
        }


    }

    // Main method for building recipes
    @Override
    protected void buildRecipes() {
        List<ItemLike> tinSmeltables = List.of(
                ModItems.RAW_TIN.get(),
                ModBLocks.TIN_ORE.get(),
                ModBLocks.DEEPSLATE_TIN_ORE.get());
        generateStonecutterRecipes();
        // -------------------------------------------------(TIN)------------------------------------------------- //
        // ---------------------------(SMELTING)--------------------------- //
        // Smelting recipes for tin
        oreSmelting(tinSmeltables, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 200, "tin");
        oreBlasting(tinSmeltables, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 100, "tin");
        // ---------------------------(SMELTING)--------------------------- //

        // ---------------------------(CRAFTING)--------------------------- //
        // Crafting recipes for tin-related items
        this.shaped(RecipeCategory.MISC, ModBLocks.TIN_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.TIN_INGOT.get())
                .unlockedBy(getHasName(ModItems.TIN_INGOT.get()), has(ModItems.TIN_INGOT.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 9)
                .requires(ModBLocks.TIN_BLOCK.get())
                .unlockedBy(getHasName(ModBLocks.TIN_BLOCK.get()), has(ModBLocks.TIN_BLOCK.get()))
                .save(this.output);
        this.shaped(RecipeCategory.MISC, ModBLocks.RAW_TIN_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RAW_TIN.get())
                .unlockedBy(getHasName(ModItems.RAW_TIN.get()), has(ModItems.RAW_TIN.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.RAW_TIN.get(), 9)
                .requires(ModBLocks.RAW_TIN_BLOCK.get())
                .unlockedBy(getHasName(ModBLocks.RAW_TIN_BLOCK.get()), has(ModBLocks.RAW_TIN_BLOCK.get()))
                .save(this.output);
        // ---------------------------(CRAFTING)--------------------------- //
        // -------------------------------------------------(TIN)------------------------------------------------- //

        // -------------------------------------------------(BRONZE)------------------------------------------------- //
        // ---------------------------(CRAFTING)--------------------------- //
        // Crafting recipes for bronze-related items
        this.shaped(RecipeCategory.MISC, ModBLocks.BRONZE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.MISC, ModItems.BRONZE_INGOT.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.BRONZE_NUGGET.get())
                .unlockedBy(getHasName(ModItems.BRONZE_NUGGET.get()), has(ModItems.BRONZE_NUGGET.get()))
                .save(this.output, "bronze_from_nuggets");
        this.shapeless(RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 9)
                .requires(ModBLocks.BRONZE_BLOCK.get())
                .unlockedBy(getHasName(ModBLocks.BRONZE_BLOCK.get()), has(ModBLocks.BRONZE_BLOCK.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 1)
                .requires(ModItems.TIN_INGOT.get())
                .requires(Items.COPPER_INGOT)
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(this.output, "bronze_ingot_from_tin_and_copper");
        this.shapeless(RecipeCategory.MISC, ModBLocks.BRONZE_BLOCK.get(), 1)
                .requires(ModBLocks.TIN_BLOCK.get())
                .requires(Blocks.COPPER_BLOCK)
                .unlockedBy(getHasName(ModBLocks.BRONZE_BLOCK.get()), has(ModBLocks.BRONZE_BLOCK.get()))
                .save(this.output, "bronze_block_from_tin_and_copper_block");

        // Crafting recipes for bronze tools
        this.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_PICKAXE.get())
                .pattern("BBB")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_AXE.get())
                .pattern("BB")
                .pattern("BS")
                .pattern(" S")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.BRONZE_NUGGET.get(), 9)
                .requires(ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(this.output, "nuggets_from_bronze");
        this.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_SHOVEL.get())
                .pattern("B")
                .pattern("S")
                .pattern("S")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_HOE.get())
                .pattern("BB")
                .pattern(" S")
                .pattern(" S")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_SWORD.get())
                .pattern("B")
                .pattern("B")
                .pattern("S")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(this.output);
        // ---------------------------(CRAFTING)--------------------------- //
        // -------------------------------------------------(BRONZE)------------------------------------------------- //

        // -------------------------------------------------(STEEL)------------------------------------------------- //
        // ---------------------------(CRAFTING)--------------------------- //
        // Crafting recipes for steel-related items
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 2)
                .pattern("III")
                .pattern("ICC")
                .pattern("CC ")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COAL)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(this.output, "steel_with_coal");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 2)
                .pattern("III")
                .pattern("ICC")
                .pattern("CC ")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.CHARCOAL)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(this.output, "steel_with_charcoal");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_INGOT.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STEEL_NUGGET.get())
                .unlockedBy(getHasName(ModItems.STEEL_NUGGET.get()), has(ModItems.STEEL_NUGGET.get()))
                .save(this.output, "steel_from_nuggets");
        this.shapeless(RecipeCategory.MISC, ModItems.STEEL_NUGGET.get(), 9)
                .requires(ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output, "nuggets_from_steel");

        // Crafting recipes for steel tools
        this.shaped(RecipeCategory.TOOLS, ModItems.STEEL_PICKAXE.get())
                .pattern("BBB")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ModItems.STEEL_AXE.get())
                .pattern("BB")
                .pattern("BS")
                .pattern(" S")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ModItems.STEEL_SHOVEL.get())
                .pattern("B")
                .pattern("S")
                .pattern("S")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ModItems.STEEL_HOE.get())
                .pattern("BB")
                .pattern(" S")
                .pattern(" S")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ModItems.STEEL_SWORD.get())
                .pattern("B")
                .pattern("B")
                .pattern("S")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output);
        // ---------------------------(CRAFTING)--------------------------- //
        // -------------------------------------------------(STEEL)------------------------------------------------- //

        // -------------------------------------------------(GEMS)------------------------------------------------- //
        this.shaped(RecipeCategory.MISC, ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TRANSPARENT_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TRANSPARENT_DIAMOND.get()), has(ModItems.TRANSPARENT_DIAMOND.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.TRANSPARENT_DIAMOND.get(), 9)
                .requires(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get()), has(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.YELLOW_DIAMOND_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.YELLOW_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.YELLOW_DIAMOND.get()), has(ModItems.YELLOW_DIAMOND.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.YELLOW_DIAMOND.get(), 9)
                .requires(ModBLocks.YELLOW_DIAMOND_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.YELLOW_DIAMOND_BLOCK.get()), has(ModBLocks.YELLOW_DIAMOND_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.AMBER_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.AMBER.get())
                .unlockedBy(getHasName(ModItems.AMBER.get()), has(ModItems.AMBER.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.AMBER.get(), 9)
                .requires(ModBLocks.AMBER_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.AMBER_BLOCK.get()), has(ModBLocks.AMBER_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.AMETHYST_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.AMETHYST.get())
                .unlockedBy(getHasName(ModItems.AMETHYST.get()), has(ModItems.AMETHYST.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.AMETHYST.get(), 9)
                .requires(ModBLocks.AMETHYST_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.AMETHYST_BLOCK.get()), has(ModBLocks.AMETHYST_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.BLACK_DIAMOND_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BLACK_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.BLACK_DIAMOND.get()), has(ModItems.BLACK_DIAMOND.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.BLACK_DIAMOND.get(), 9)
                .requires(ModBLocks.BLACK_DIAMOND_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.BLACK_DIAMOND_BLOCK.get()), has(ModBLocks.BLACK_DIAMOND_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.CARNELIAN_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.CARNELIAN.get())
                .unlockedBy(getHasName(ModItems.CARNELIAN.get()), has(ModItems.CARNELIAN.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.CARNELIAN.get(), 9)
                .requires(ModBLocks.CARNELIAN_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.CARNELIAN_BLOCK.get()), has(ModBLocks.CARNELIAN_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.BLOODSTONE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BLOODSTONE.get())
                .unlockedBy(getHasName(ModItems.BLOODSTONE.get()), has(ModItems.BLOODSTONE.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.BLOODSTONE.get(), 9)
                .requires(ModBLocks.BLOODSTONE_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.BLOODSTONE_BLOCK.get()), has(ModBLocks.BLOODSTONE_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.CHALCEDONY_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.CHALCEDONY.get())
                .unlockedBy(getHasName(ModItems.CHALCEDONY.get()), has(ModItems.CHALCEDONY.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.CHALCEDONY.get(), 9)
                .requires(ModBLocks.CHALCEDONY_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.CHALCEDONY_BLOCK.get()), has(ModBLocks.CHALCEDONY_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.GARNET_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.GARNET.get())
                .unlockedBy(getHasName(ModItems.GARNET.get()), has(ModItems.GARNET.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.GARNET.get(), 9)
                .requires(ModBLocks.GARNET_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.GARNET_BLOCK.get()), has(ModBLocks.GARNET_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.JADE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.JADE.get())
                .unlockedBy(getHasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.JADE.get(), 9)
                .requires(ModBLocks.JADE_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.JADE_BLOCK.get()), has(ModBLocks.JADE_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.JASPER_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.JASPER.get())
                .unlockedBy(getHasName(ModItems.JASPER.get()), has(ModItems.JASPER.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.JASPER.get(), 9)
                .requires(ModBLocks.JASPER_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.JASPER_BLOCK.get()), has(ModBLocks.JASPER_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.MALACHITE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MALACHITE.get())
                .unlockedBy(getHasName(ModItems.MALACHITE.get()), has(ModItems.MALACHITE.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.MALACHITE.get(), 9)
                .requires(ModBLocks.MALACHITE_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.MALACHITE_BLOCK.get()), has(ModBLocks.MALACHITE_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.MOONSTONE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.MOONSTONE.get())
                .unlockedBy(getHasName(ModItems.MOONSTONE.get()), has(ModItems.MOONSTONE.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.MOONSTONE.get(), 9)
                .requires(ModBLocks.MOONSTONE_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.MOONSTONE_BLOCK.get()), has(ModBLocks.MOONSTONE_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.ONYX_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.ONYX.get())
                .unlockedBy(getHasName(ModItems.ONYX.get()), has(ModItems.ONYX.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.ONYX.get(), 9)
                .requires(ModBLocks.ONYX_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.ONYX_BLOCK.get()), has(ModBLocks.ONYX_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.OPAL_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.OPAL.get())
                .unlockedBy(getHasName(ModItems.OPAL.get()), has(ModItems.OPAL.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.OPAL.get(), 9)
                .requires(ModBLocks.OPAL_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.OPAL_BLOCK.get()), has(ModBLocks.OPAL_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.RUBY_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.RUBY.get(), 9)
                .requires(ModBLocks.RUBY_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.RUBY_BLOCK.get()), has(ModBLocks.RUBY_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.SAPPHIRE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBLocks.SAPPHIRE_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.SAPPHIRE_BLOCK.get()), has(ModBLocks.SAPPHIRE_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.TIGERS_EYE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TIGERS_EYE.get())
                .unlockedBy(getHasName(ModItems.TIGERS_EYE.get()), has(ModItems.TIGERS_EYE.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.TIGERS_EYE.get(), 9)
                .requires(ModBLocks.TIGERS_EYE_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.TIGERS_EYE_BLOCK.get()), has(ModBLocks.TIGERS_EYE_BLOCK.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.TOPAZ_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TOPAZ.get())
                .unlockedBy(getHasName(ModItems.TOPAZ.get()), has(ModItems.TOPAZ.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.TOPAZ.get(), 9)
                .requires(ModBLocks.TOPAZ_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.TOPAZ_BLOCK.get()), has(ModBLocks.TOPAZ_BLOCK.get()))
                .save(this.output);
        // -------------------------------------------------(GEMS)------------------------------------------------- //

        // -------------------------------------------------(STONE CUTTER)------------------------------------------------- //
        // Stonecutting recipes
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, ModBLocks.DARK_STONE_BRICK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, ModBLocks.STONE_BRICK_BUT_COOLER.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        // -------------------------------------------------(STONE CUTTER)------------------------------------------------- //

        // -------------------------------------------------(WOOD)------------------------------------------------- //

// Weirwood Crafting Recipes

// Weirwood block recipes for doors, slabs, stairs, etc.

// Wood conversions
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_WOOD.get(), 3)
                .requires(ModBLocks.WEIRWOOD_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_LOG.get()), has(ModBLocks.WEIRWOOD_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_WEIRWOOD_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_WEIRWOOD_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), has(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()))
                .save(this.output);

// Planks
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.WEIRWOOD_LOG.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_LOG.get()), has(ModBLocks.WEIRWOOD_LOG.get()))
                .save(this.output, "planks_from_weirwood_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_WEIRWOOD_LOG.get())
                .unlockedBy(getHasName(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), has(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()))
                .save(this.output, "planks_from_weirwood_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.WEIRWOOD_WOOD.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_WOOD.get()), has(ModBLocks.WEIRWOOD_WOOD.get()))
                .save(this.output, "planks_from_weirwood_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get())
                .unlockedBy(getHasName(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get()), has(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get()))
                .save(this.output, "planks_from_weirwood_stripped_wood");

// Crafting table
        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output, "weirwood_plank_craftingtable");

// Vanilla Stick recipe
        this.shaped(RecipeCategory.MISC, ModItems.WEIRWOOD_STICK, 4)
                .pattern("B")
                .pattern("B")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output, "weirwood_plank_stick");

// Smelting to charcoal
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.WEIRWOOD_LOG.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_LOG.get()), has(ModBLocks.WEIRWOOD_LOG.get()))
                .save(this.output, "smelt_weirwood_log_to_charcoal");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), has(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()))
                .save(this.output, "smelt_stripped_weirwood_log_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.WEIRWOOD_WOOD.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_WOOD.get()), has(ModBLocks.WEIRWOOD_WOOD.get()))
                .save(this.output, "smelt_weirwood_wood_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get()), has(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get()))
                .save(this.output, "smelt_stripped_weirwood_wood_to_charcoal");

// Wood family blocks
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.WEIRWOOD_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.WEIRWOOD_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.WEIRWOOD_BUTTON.get())
                .pattern("B")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.WEIRWOOD_PRESSURE_PLATE.get())
                .pattern("BB")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.WEIRWOOD_DOOR.get(), 3)
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.WEIRWOOD_TRAPDOOR.get(), 2)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.DECORATIONS, ModBLocks.WEIRWOOD_FENCE.get(), 3)
                .pattern("B#B")
                .pattern("B#B")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .define('#', ModItems.WEIRWOOD_STICK)
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.DECORATIONS, ModBLocks.WEIRWOOD_FENCE_GATE.get())
                .pattern("#B#")
                .pattern("#B#")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .define('#', ModItems.WEIRWOOD_STICK)
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.WEIRWOOD_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output);


        //Sycamore


        String[] woodTypes = {
                "alder",
                "almond",
                "apple",
                "apricot",
                "ash",
                "aspen",
                "baobab",
                "beech",
                "black_cherry",
                "black_cottonwood",
                "black_olive",
                "blackbark",
                "blackthorn",
                "blood_orange",
                "bloodwood",
                "blue_mahoe",
                "cedar",
                "chestnut",
                "cottonwood",
                "crabapple",
                "datepalm",
                "ebony",
                "fig",
                "fir",
                "fireplum",
                "goldenheart",
                "hawthorn",
                "ironwood",
                "lemon",
                "lime",
                "linden",
                "mahogany",
                "maple",
                "myrrh",
                "nightwood",
                "nutmeg",
                "olive",
                "orange",
                "peach",
                "pear",
                "pecan",
                "persimmon",
                "pine",
                "pink_ivory",
                "plum",
                "pomegranate",
                "purpleheart",
                "redwood",
                "sandalwood",
                "sandbeggar",
                "sentinel",
                "sycamore",
                "tigerwood",
                "white_cherry",
                "willow",
                "wormtree",
                "yew"
        };

        // Generate recipes for all wood types
        for (String woodType : woodTypes) {
            generateWoodRecipes(woodType);
        }

        // -------------------------------------------------(BANNER PATTERNS)------------------------------------------------- //



        // -------------------------------------------------(FLOWERS)------------------------------------------------- //
        this.shaped(RecipeCategory.MISC, ModBLocks.RED_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.ROSE.get())
                .unlockedBy(getHasName(ModBLocks.ROSE.get()), has(ModBLocks.ROSE.get()))
                .save(this.output, "rose_bush");

        this.shaped(RecipeCategory.MISC, Items.RED_DYE, 2) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.RED_ROSE_BUSH.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.RED_ROSE_BUSH.get()), has(ModBLocks.RED_ROSE_BUSH.get()))
                .save(this.output, "rose_bush_to_red_dye");

        this.shaped(RecipeCategory.MISC, Items.RED_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.ROSE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.ROSE.get()), has(ModBLocks.ROSE.get()))
                .save(this.output, "rose_to_red_dye");

        this.shaped(RecipeCategory.MISC, ModBLocks.BLUE_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.BLUE_ROSE.get())
                .unlockedBy(getHasName(ModBLocks.BLUE_ROSE.get()), has(ModBLocks.BLUE_ROSE.get()))
                .save(this.output, "blue_rose_bush");

        this.shaped(RecipeCategory.MISC, Items.BLUE_DYE, 2) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.BLUE_ROSE_BUSH.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.BLUE_ROSE_BUSH.get()), has(ModBLocks.BLUE_ROSE_BUSH.get()))
                .save(this.output, "rose_bush_to_blue_dye");

        this.shaped(RecipeCategory.MISC, Items.BLUE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.BLUE_ROSE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.BLUE_ROSE.get()), has(ModBLocks.BLUE_ROSE.get()))
                .save(this.output, "rose_to_blue_dye");

        this.shaped(RecipeCategory.MISC, ModBLocks.WHITE_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WHITE_ROSE.get())
                .unlockedBy(getHasName(ModBLocks.WHITE_ROSE.get()), has(ModBLocks.WHITE_ROSE.get()))
                .save(this.output, "white_rose_bush");

        this.shaped(RecipeCategory.MISC, Items.WHITE_DYE, 2) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WHITE_ROSE_BUSH.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.WHITE_ROSE_BUSH.get()), has(ModBLocks.WHITE_ROSE_BUSH.get()))
                .save(this.output, "rose_bush_to_white_dye");

        this.shaped(RecipeCategory.MISC, Items.WHITE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WHITE_ROSE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.WHITE_ROSE.get()), has(ModBLocks.WHITE_ROSE.get()))
                .save(this.output, "rose_to_white_dye");

        this.shaped(RecipeCategory.MISC, ModBLocks.WINTER_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WINTER_ROSE.get())
                .unlockedBy(getHasName(ModBLocks.WINTER_ROSE.get()), has(ModBLocks.WINTER_ROSE.get()))
                .save(this.output, "winter_rose_bush");

        this.shaped(RecipeCategory.MISC, Items.LIGHT_BLUE_DYE, 2) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WINTER_ROSE_BUSH.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.WINTER_ROSE_BUSH.get()), has(ModBLocks.WINTER_ROSE_BUSH.get()))
                .save(this.output, "rose_bush_to_light_blue_dye");

        this.shaped(RecipeCategory.MISC, Items.LIGHT_BLUE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WINTER_ROSE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.WINTER_ROSE.get()), has(ModBLocks.WINTER_ROSE.get()))
                .save(this.output, "rose_to_light_blue_dye");

        this.shaped(RecipeCategory.MISC, ModBLocks.DUSKY_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.DUSKY_ROSE.get())
                .unlockedBy(getHasName(ModBLocks.DUSKY_ROSE.get()), has(ModBLocks.DUSKY_ROSE.get()))
                .save(this.output, "dusky_rose_bush");

        this.shaped(RecipeCategory.MISC, Items.RED_DYE, 2) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.DUSKY_ROSE_BUSH.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.DUSKY_ROSE_BUSH.get()), has(ModBLocks.DUSKY_ROSE_BUSH.get()))
                .save(this.output, "rose_bush_to_two_red_dye");

        this.shaped(RecipeCategory.MISC, Items.RED_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.DUSKY_ROSE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.DUSKY_ROSE.get()), has(ModBLocks.DUSKY_ROSE.get()))
                .save(this.output, "rose_to_two_red_dye");

        this.shaped(RecipeCategory.MISC, Items.MAGENTA_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.THISTLE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.THISTLE.get()), has(ModBLocks.THISTLE.get()))
                .save(this.output, "thistle_to_magenta_dye");

        this.shaped(RecipeCategory.MISC, Items.YELLOW_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.TANSY.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.TANSY.get()), has(ModBLocks.TANSY.get()))
                .save(this.output, "tansy_to_yellow_dye");

        this.shaped(RecipeCategory.MISC, Items.ORANGE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.SPICEFLOWER.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.SPICEFLOWER.get()), has(ModBLocks.SPICEFLOWER.get()))
                .save(this.output, "spiceflower_to_orange_dye");

        this.shaped(RecipeCategory.MISC, Items.YELLOW_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.SEDGE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.SEDGE.get()), has(ModBLocks.SEDGE.get()))
                .save(this.output, "sedge_to_yellow_dye");

        this.shaped(RecipeCategory.MISC, Items.MAGENTA_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.SAFFRON_CROCUS.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.SAFFRON_CROCUS.get()), has(ModBLocks.SAFFRON_CROCUS.get()))
                .save(this.output, "saffron_to_magenta_dye");

        this.shaped(RecipeCategory.MISC, Items.PURPLE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.POISON_KISSES.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.POISON_KISSES.get()), has(ModBLocks.POISON_KISSES.get()))
                .save(this.output, "poison_kisses_to_purple_dye");

        this.shaped(RecipeCategory.MISC, Items.PURPLE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.PENNYROYAL.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.PENNYROYAL.get()), has(ModBLocks.PENNYROYAL.get()))
                .save(this.output, "penny_royal_to_purple_dye");

        this.shaped(RecipeCategory.MISC, ModItems.OPIUM_POPPY_SEEDS.get()) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.OPIUM_POPPY_WILD.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.OPIUM_POPPY_WILD.get()), has(ModBLocks.OPIUM_POPPY_WILD.get()))
                .save(this.output, "opium_poppy_to_poppy_seed");

        this.shaped(RecipeCategory.MISC, Items.PURPLE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.NIGHTSHADE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.NIGHTSHADE.get()), has(ModBLocks.NIGHTSHADE.get()))
                .save(this.output, "nightshade_to_purple_dye");

        this.shaped(RecipeCategory.MISC, Items.BLUE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.MOONBLOOM.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.MOONBLOOM.get()), has(ModBLocks.MOONBLOOM.get()))
                .save(this.output, "moonbloom_to_blue_dye");

        this.shaped(RecipeCategory.MISC, Items.MAGENTA_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.LUNGWORT.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.LUNGWORT.get()), has(ModBLocks.LUNGWORT.get()))
                .save(this.output, "lungwort_to_magenta_dye");

        this.shaped(RecipeCategory.MISC, Items.PINK_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.LIVERWORT.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.LIVERWORT.get()), has(ModBLocks.LIVERWORT.get()))
                .save(this.output, "liverwort_to_pink_dye");

        this.shaped(RecipeCategory.MISC, Items.PURPLE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.LAVENDER.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.LAVENDER.get()), has(ModBLocks.LAVENDER.get()))
                .save(this.output, "lavender_to_purple_dye");

        this.shaped(RecipeCategory.MISC, Items.WHITE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.LADYS_LACE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.LADYS_LACE.get()), has(ModBLocks.LADYS_LACE.get()))
                .save(this.output, "ladyslace_to_white_dye");

        this.shaped(RecipeCategory.MISC, Items.YELLOW_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.GORSE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.GORSE.get()), has(ModBLocks.GORSE.get()))
                .save(this.output, "gorse_to_yellow_dye");

        this.shaped(RecipeCategory.MISC, Items.YELLOW_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.GOLDENROD.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.GOLDENROD.get()), has(ModBLocks.GOLDENROD.get()))
                .save(this.output, "goldenrod_to_yellow_dye");

        this.shaped(RecipeCategory.MISC, Items.YELLOW_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.GOLDENCUP.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.GOLDENCUP.get()), has(ModBLocks.GOLDENCUP.get()))
                .save(this.output, "goldencup_to_yellow_dye");

        this.shaped(RecipeCategory.MISC, Items.YELLOW_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.GOATHEAD.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.GOATHEAD.get()), has(ModBLocks.GOATHEAD.get()))
                .save(this.output, "goathead_to_yellow_dye");

        this.shaped(RecipeCategory.MISC, Items.RED_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.GINGER.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.GINGER.get()), has(ModBLocks.GINGER.get()))
                .save(this.output, "ginger_to_yellow_dye");


        this.shaped(RecipeCategory.MISC, Items.PINK_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.GILLYFLOWER.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.GILLYFLOWER.get()), has(ModBLocks.GILLYFLOWER.get()))
                .save(this.output, "gillyflower_to_yellow_dye");

        this.shaped(RecipeCategory.MISC, Items.RED_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.FROSTFIRE.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.FROSTFIRE.get()), has(ModBLocks.FROSTFIRE.get()))
                .save(this.output, "frostfire_to_red_dye");


        this.shaped(RecipeCategory.MISC, Items.LIGHT_BLUE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.FORGET_ME_NOT.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.FORGET_ME_NOT.get()), has(ModBLocks.FORGET_ME_NOT.get()))
                .save(this.output, "forget_me_not_to_light_blue_dye");

        this.shaped(RecipeCategory.MISC, Items.YELLOW_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.EVENING_STAR.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.EVENING_STAR.get()), has(ModBLocks.EVENING_STAR.get()))
                .save(this.output, "evening_star_to_yellow_dye");

        this.shaped(RecipeCategory.MISC, Items.RED_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.DRAGONS_BREATH.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.DRAGONS_BREATH.get()), has(ModBLocks.DRAGONS_BREATH.get()))
                .save(this.output, "dragons_breath_to_red_dye");

        this.shaped(RecipeCategory.MISC, Items.LIGHT_BLUE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.COLDSNAP.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.COLDSNAP.get()), has(ModBLocks.COLDSNAP.get()))
                .save(this.output, "coldsnap_to_light_blue_dye");

        this.shaped(RecipeCategory.MISC, Items.RED_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.BLOODBLOOM.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.BLOODBLOOM.get()), has(ModBLocks.BLOODBLOOM.get()))
                .save(this.output, "bloodbloom_to_red_dye");

        this.shaped(RecipeCategory.MISC, Items.BLACK_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.BLACK_LOTUS.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.BLACK_LOTUS.get()), has(ModBLocks.BLACK_LOTUS.get()))
                .save(this.output, "black_lotus_to_black_dye");

        this.shaped(RecipeCategory.MISC, Items.WHITE_DYE) // Crafting 2 red dye from a single rose bush
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WILD_RADISH.get()) // Using your rose bush block
                .unlockedBy(getHasName(ModBLocks.WILD_RADISH.get()), has(ModBLocks.WILD_RADISH.get()))
                .save(this.output, "wild_radish_to_white_dye");

        // -------------------------------------------------(FOODS)------------------------------------------------- //
        this.shaped(RecipeCategory.MISC, ModItems.STRAWBERRY_SEEDS.get())
                .pattern("S")
                .pattern(" ")
                .define('S', ModItems.STRAWBERRY.get())
                .unlockedBy(getHasName(ModItems.STRAWBERRY.get()), has(ModItems.STRAWBERRY.get()))
                .save(this.output, "strawberry_to_strawberry_seeds");

        this.shaped(RecipeCategory.MISC, ModItems.BLACKBERRY_SEEDS.get())
                .pattern("S")
                .pattern(" ")
                .define('S', ModItems.BLACKBERRY.get())
                .unlockedBy(getHasName(ModItems.BLACKBERRY.get()), has(ModItems.BLACKBERRY.get()))
                .save(this.output, "blackberry_to_blackberry_seeds");

        this.shaped(RecipeCategory.MISC, ModItems.BLUEBERRY_SEEDS.get())
                .pattern("S")
                .pattern(" ")
                .define('S', ModItems.BLUEBERRY.get())
                .unlockedBy(getHasName(ModItems.BLUEBERRY.get()), has(ModItems.BLUEBERRY.get()))
                .save(this.output, "blueberry_to_blueberry_seeds");

        this.shaped(RecipeCategory.MISC, ModItems.MULBERRY_SEEDS.get())
                .pattern("S")
                .pattern(" ")
                .define('S', ModItems.MULBERRY.get())
                .unlockedBy(getHasName(ModItems.MULBERRY.get()), has(ModItems.MULBERRY.get()))
                .save(this.output, "mulberry_to_mulberry_seeds");

        this.shaped(RecipeCategory.MISC, ModItems.RASPBERRY_SEEDS.get())
                .pattern("S")
                .pattern(" ")
                .define('S', ModItems.RASPBERRY.get())
                .unlockedBy(getHasName(ModItems.RASPBERRY.get()), has(ModItems.RASPBERRY.get()))
                .save(this.output, "raspberry_to_raspberry_seeds");

        this.shaped(RecipeCategory.MISC, ModItems.SMOKEBERRY_SEEDS.get())
                .pattern("S")
                .pattern(" ")
                .define('S', ModItems.SMOKEBERRY.get())
                .unlockedBy(getHasName(ModItems.SMOKEBERRY.get()), has(ModItems.SMOKEBERRY.get()))
                .save(this.output, "smokeberry_to_smokeberry_seeds");



        // -------------------------------------------------(FOODS)------------------------------------------------- //
        // Food smelting recipes Furnace
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_SAUSAGE.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_SAUSAGE.get()), has(ModItems.RAW_SAUSAGE.get())) // Unlock condition
                .save(this.output, "cooked_sausage_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_BEAR_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_BEAR_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_BEAR_MEAT.get()), has(ModItems.RAW_BEAR_MEAT.get())) // Unlock condition
                .save(this.output, "cooked_bear_meat_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_BLOOD_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_BLOOD_SAUSAGE.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_BLOOD_SAUSAGE.get()), has(ModItems.RAW_BLOOD_SAUSAGE.get())) // Unlock condition
                .save(this.output, "cooked_blood_sausage_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_WHITE_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_WHITE_SAUSAGE.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_WHITE_SAUSAGE.get()), has(ModItems.RAW_WHITE_SAUSAGE.get())) // Unlock condition
                .save(this.output, "cooked_white_sausage_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_BACON.get()), RecipeCategory.FOOD, ModItems.COOKED_BACON.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_BACON.get()), has(ModItems.RAW_BACON.get())) // Unlock condition
                .save(this.output, "cooked_bacon_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_BOAR_VENISON.get()), RecipeCategory.FOOD, ModItems.COOKED_BOAR_VENISON.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_BOAR_VENISON.get()), has(ModItems.RAW_BOAR_VENISON.get())) // Unlock condition
                .save(this.output, "cooked_boar_venison_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_CHICKEN_NUGGETS.get()), RecipeCategory.FOOD, ModItems.COOKED_CHICKEN_NUGGETS.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_CHICKEN_NUGGETS.get()), has(ModItems.RAW_CHICKEN_NUGGETS.get())) // Unlock condition
                .save(this.output, "cooked_chicken_nuggets_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_DEER_VENISON.get()), RecipeCategory.FOOD, ModItems.COOKED_DEER_VENISON.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_DEER_VENISON.get()), has(ModItems.RAW_DEER_VENISON.get())) // Unlock condition
                .save(this.output, "cooked_deer_venison_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_GOAT_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_GOAT_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_GOAT_MEAT.get()), has(ModItems.RAW_GOAT_MEAT.get())) // Unlock condition
                .save(this.output, "cooked_goat_meat_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_HARE_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_HARE_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_HARE_MEAT.get()), has(ModItems.RAW_HARE_MEAT.get())) // Unlock condition
                .save(this.output, "cooked_hare_meat_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_HORSE_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_HORSE_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_HORSE_MEAT.get()), has(ModItems.RAW_HORSE_MEAT.get())) // Unlock condition
                .save(this.output, "cooked_horse_meat_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_MAMMOTH_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_MAMMOTH_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_MAMMOTH_MEAT.get()), has(ModItems.RAW_MAMMOTH_MEAT.get())) // Unlock condition
                .save(this.output, "cooked_mammoth_meat_with_furnace");

        // Food Smelting recipes Smoker
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_SAUSAGE.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItems.RAW_SAUSAGE.get()), has(ModItems.RAW_SAUSAGE.get())) // Unlock condition
                .save(this.output, "cooked_sausage_with_smoker");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_BEAR_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_BEAR_MEAT.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItems.RAW_BEAR_MEAT.get()), has(ModItems.RAW_BEAR_MEAT.get())) // Unlock condition
                .save(this.output, "cooked_bear_meat_with_smoker");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_BLOOD_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_BLOOD_SAUSAGE.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItems.RAW_BLOOD_SAUSAGE.get()), has(ModItems.RAW_BLOOD_SAUSAGE.get())) // Unlock condition
                .save(this.output, "cooked_blood_sausage_with_smoker");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_WHITE_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_WHITE_SAUSAGE.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItems.RAW_WHITE_SAUSAGE.get()), has(ModItems.RAW_WHITE_SAUSAGE.get())) // Unlock condition
                .save(this.output, "cooked_white_sausage_with_smoker");


        // -------------------------------------------------(INGREDIENTS)------------------------------------------------- //
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.LEATHER), RecipeCategory.MISC, ModItems.BOILED_LEATHER.get(), 0.35f, 200)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(this.output, "boiled_leather_from_smelting");

        this.shapeless(RecipeCategory.MISC, ModItems.IVORY_SHARD.get(), 4)
                .requires(ModItems.IVORY.get())
                .unlockedBy(getHasName(ModItems.IVORY.get()), has(ModItems.IVORY.get()))
                .save(this.output, "ivory_to_shards");

        this.shaped(RecipeCategory.MISC, ModItems.IRON_CHAIN_LINK.get())
                .pattern(" H ")
                .pattern(" N ")
                .define('H', ModItems.HAMMER.get()) // Your custom hammer
                .define('N', Items.IRON_NUGGET)          // Vanilla iron nugget
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(this.output, "iron_chain_link_from_nugget_and_hammer");
        this.shaped(RecipeCategory.MISC, ModItems.IRON_CHAIN.get())
                .pattern(" L ")
                .pattern("LHL")
                .pattern(" L ")
                .define('L', ModItems.IRON_CHAIN_LINK.get())
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.IRON_CHAIN_LINK.get()), has(ModItems.IRON_CHAIN_LINK.get()))
                .save(this.output, "iron_chain_from_links");
        this.shaped(RecipeCategory.MISC, ModItems.BRONZE_CHAIN.get())
                .pattern(" L ")
                .pattern("LHL")
                .pattern(" L ")
                .define('L', ModItems.BRONZE_CHAIN_LINK.get())
                .define('H', ModItems.HAMMER.get()) // if bronze uses iron hammer
                .unlockedBy(getHasName(ModItems.BRONZE_CHAIN_LINK.get()), has(ModItems.BRONZE_CHAIN_LINK.get()))
                .save(this.output, "bronze_chain_from_links");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_CHAIN.get())
                .pattern(" L ")
                .pattern("LHL")
                .pattern(" L ")
                .define('L', ModItems.STEEL_CHAIN_LINK.get())
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.STEEL_CHAIN_LINK.get()), has(ModItems.STEEL_CHAIN_LINK.get()))
                .save(this.output, "steel_chain_from_links");
        this.shaped(RecipeCategory.MISC, ModItems.BRONZE_CHAIN_LINK.get())
                .pattern(" H ")
                .pattern("N  ")
                .define('H', ModItems.HAMMER.get())
                .define('N', ModItems.BRONZE_INGOT.get()) // assuming you use ingots instead of nuggets
                .unlockedBy(getHasName(ModItems.HAMMER.get()), has(ModItems.HAMMER.get()))
                .save(this.output, "bronze_chain_link");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_CHAIN_LINK.get())
                .pattern(" H ")
                .pattern("N  ")
                .define('H', ModItems.HAMMER.get())
                .define('N', ModItems.STEEL_NUGGET.get())
                .unlockedBy(getHasName(ModItems.HAMMER.get()), has(ModItems.HAMMER.get()))
                .save(this.output, "steel_chain_link");
        this.shaped(RecipeCategory.MISC, ModItems.IRON_PLATE.get())
                .pattern("HI ")
                .pattern(" I ")
                .define('H', ModItems.HAMMER.get())
                .define('I', Items.IRON_INGOT) // vanilla iron ingot
                .unlockedBy(getHasName(ModItems.HAMMER.get()), has(ModItems.HAMMER.get()))
                .save(this.output, "iron_plate");
        this.shaped(RecipeCategory.MISC, ModItems.BRONZE_PLATE.get())
                .pattern("HB ")
                .pattern(" B ")
                .define('H', ModItems.HAMMER.get())
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.HAMMER.get()), has(ModItems.HAMMER.get()))
                .save(this.output, "bronze_plate");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_PLATE.get())
                .pattern("HS ")
                .pattern(" S ")
                .define('H', ModItems.HAMMER.get())
                .define('S', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.HAMMER.get()), has(ModItems.HAMMER.get()))
                .save(this.output, "steel_plate");
        this.shaped(RecipeCategory.MISC, ModItems.NOBLE_PLATE.get())
                .pattern(" P ")
                .pattern("PHP")
                .pattern(" P ")
                .define('H', ModItems.HAMMER.get())
                .define('P', ModItems.STEEL_PLATE.get())
                .unlockedBy(getHasName(ModItems.HAMMER.get()), has(ModItems.HAMMER.get()))
                .save(this.output, "noble_plate");
        this.shaped(RecipeCategory.MISC, ModItems.CLOTH.get())
                .pattern("W")
                .pattern("W")
                .define('W', Items.WHITE_WOOL) // Vanilla white wool
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(this.output, "cloth");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_HELMET.get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output, "steel_helmet");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_CHESTPLATE.get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output, "steel_chestplate");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_LEGGINGS.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output, "steel_leggings");
        this.shaped(RecipeCategory.MISC, ModItems.STEEL_BOOTS.get())
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(this.output, "steel_boots");
        this.shaped(RecipeCategory.MISC, ModItems.UPGRADE_KIT_IRON.get())
                .pattern("PCP")
                .pattern("CBC")
                .pattern("IHI")
                .define('P', ModItems.IRON_PLATE.get())
                .define('C', ModItems.CLOTH.get())
                .define('B', Items.BUNDLE)
                .define('I', ModItems.IRON_CHAIN.get())
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.IRON_PLATE.get()), has(ModItems.IRON_PLATE.get()))
                .save(this.output, "upgrade_kit_iron");
        this.shaped(RecipeCategory.MISC, ModItems.UPGRADE_KIT_BRONZE.get())
                .pattern("PCP")
                .pattern("CBC")
                .pattern("IHI")
                .define('P', ModItems.BRONZE_PLATE.get())
                .define('C', ModItems.CLOTH.get())
                .define('B', Items.BUNDLE)
                .define('I', ModItems.BRONZE_CHAIN.get())
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.BRONZE_PLATE.get()), has(ModItems.BRONZE_PLATE.get()))
                .save(this.output, "upgrade_kit_bronze");
        this.shaped(RecipeCategory.MISC, ModItems.UPGRADE_KIT_BRONZE_PLATE.get())
                .pattern("PPP")
                .pattern("CBC")
                .pattern("PHP")
                .define('P', ModItems.BRONZE_PLATE.get())
                .define('C', ModItems.CLOTH.get())
                .define('B', Items.BUNDLE)
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.BRONZE_PLATE.get()), has(ModItems.BRONZE_PLATE.get()))
                .save(this.output, "upgrade_kit_bronze_plate");
        this.shaped(RecipeCategory.MISC, ModItems.UPGRADE_KIT_STEEL.get())
                .pattern("PCP")
                .pattern("CBC")
                .pattern("IHI")
                .define('P', ModItems.STEEL_PLATE.get())
                .define('C', ModItems.CLOTH.get())
                .define('B', Items.BUNDLE)
                .define('I', ModItems.STEEL_CHAIN.get())
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.STEEL_PLATE.get()), has(ModItems.STEEL_PLATE.get()))
                .save(this.output, "upgrade_kit_steel");
        this.shaped(RecipeCategory.MISC, ModItems.UPGRADE_KIT_NOBLE.get())
                .pattern("PCP")
                .pattern("CBC")
                .pattern("IHI")
                .define('P', ModItems.NOBLE_PLATE.get())
                .define('C', ModItems.CLOTH.get())
                .define('B', Items.BUNDLE)
                .define('I', ModItems.STEEL_CHAIN.get())
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.NOBLE_PLATE.get()), has(ModItems.NOBLE_PLATE.get()))
                .save(this.output, "upgrade_kit_noble");
        this.shaped(RecipeCategory.MISC, ModItems.UPGRADE_KIT_FUR.get())
                .pattern("PPP")
                .pattern("CBC")
                .pattern("PHP")
                .define('P', ModItems.FUR.get())
                .define('C', ModItems.CLOTH.get())
                .define('B', Items.BUNDLE)
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.FUR.get()), has(ModItems.FUR.get()))
                .save(this.output, "upgrade_kit_fur");
        this.shaped(RecipeCategory.MISC, ModItems.UPGRADE_KIT_LEATHER.get())
                .pattern("PCP")
                .pattern("CBC")
                .pattern("IHI")
                .define('P', ModItems.FUR.get())
                .define('C', ModItems.BOILED_LEATHER.get())
                .define('B', Items.BUNDLE)
                .define('I', ModItems.BRONZE_CHAIN.get())
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.BOILED_LEATHER.get()), has(ModItems.BOILED_LEATHER.get()))
                .save(this.output, "upgrade_kit_leather");
        this.shaped(RecipeCategory.MISC, ModItems.UPGRADE_KIT_CHIEF.get())
                .pattern("PCP")
                .pattern("CBC")
                .pattern("IHI")
                .define('P', ModItems.BOILED_LEATHER.get())
                .define('C', ModItems.BOILED_LEATHER.get())
                .define('B', Items.BUNDLE)
                .define('I', ModItems.IVORY_SHARD.get())
                .define('H', ModItems.HAMMER.get())
                .unlockedBy(getHasName(ModItems.IVORY_SHARD.get()), has(ModItems.IVORY_SHARD.get()))
                .save(this.output, "upgrade_kit_chief");

        this.shaped(RecipeCategory.MISC, ModItems.CLOTH.get())
                .pattern("AA")
                .pattern("AA")
                .define('A', ModItems.COTTON.get())
                .unlockedBy(getHasName(ModItems.COTTON.get()), has(ModItems.COTTON.get()))
                .save(this.output, "cloth_from_cotton");

        this.shaped(RecipeCategory.MISC, Items.STRING)
                .pattern("A")
                .define('A', ModItems.COTTON.get())
                .unlockedBy(getHasName(ModItems.COTTON.get()), has(ModItems.COTTON.get()))
                .save(this.output, "string_from_cotton");


        // Stark Levy Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_HELMET.get()),
                        Ingredient.of(ModItems.STARK_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_LEVY_HELMET.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "stark_levy_helmet_smithing");

// Stark Levy Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_CHESTPLATE.get()),
                        Ingredient.of(ModItems.STARK_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_LEVY_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "stark_levy_chestplate_smithing");

// Stark Levy Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_LEGGINGS.get()),
                        Ingredient.of(ModItems.STARK_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_LEVY_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "stark_levy_leggings_smithing");

// Stark Levy Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_BOOTS.get()),
                        Ingredient.of(ModItems.STARK_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_LEVY_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "stark_levy_boots_smithing");

        // Stark Plate Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.STARK_LEVY_HELMET.get()),
                        Ingredient.of(ModItems.STARK_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_PLATE_HELMET.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "stark_plate_helmet_smithing");

// Stark Plate Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.STARK_LEVY_CHESTPLATE.get()),
                        Ingredient.of(ModItems.STARK_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_PLATE_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "stark_plate_chestplate_smithing");

// Stark Plate Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.STARK_LEVY_LEGGINGS.get()),
                        Ingredient.of(ModItems.STARK_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_PLATE_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "stark_plate_leggings_smithing");

// Stark Plate Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.STARK_LEVY_BOOTS.get()),
                        Ingredient.of(ModItems.STARK_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_PLATE_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "stark_plate_boots_smithing");

        // Stark noble Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.STARK_PLATE_HELMET.get()),
                        Ingredient.of(ModItems.STARK_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_NOBLE_PLATE_HELMET.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "stark_noble_helmet_smithing");

// Stark Plate Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.STARK_PLATE_CHESTPLATE.get()),
                        Ingredient.of(ModItems.STARK_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_NOBLE_PLATE_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "stark_noble_chestplate_smithing");

// Stark Plate Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.STARK_PLATE_LEGGINGS.get()),
                        Ingredient.of(ModItems.STARK_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_NOBLE_PLATE_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "stark_noble_leggings_smithing");

// Stark Plate Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.STARK_PLATE_BOOTS.get()),
                        Ingredient.of(ModItems.STARK_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.STARK_NOBLE_PLATE_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "stark_noble_boots_smithing");

// Bolten Levy Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_HELMET.get()),
                        Ingredient.of(ModItems.BOLTON_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_LEVY_HELMET.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "bolton_levy_helmet_smithing");

// Bolten Levy Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_CHESTPLATE.get()),
                        Ingredient.of(ModItems.BOLTON_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_LEVY_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "bolton_levy_chestplate_smithing");

// Bolten Levy Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_LEGGINGS.get()),
                        Ingredient.of(ModItems.BOLTON_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_LEVY_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "bolton_levy_leggings_smithing");

// Bolten Levy Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_BOOTS.get()),
                        Ingredient.of(ModItems.BOLTON_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_LEVY_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "bolton_levy_boots_smithing");

// Bolten Plate Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.BOLTEN_LEVY_HELMET.get()),
                        Ingredient.of(ModItems.BOLTON_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_PLATE_HELMET.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "bolton_plate_helmet_smithing");

// Bolten Plate Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.BOLTEN_LEVY_CHESTPLATE.get()),
                        Ingredient.of(ModItems.BOLTON_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_PLATE_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "bolton_plate_chestplate_smithing");

// Bolten Plate Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.BOLTEN_LEVY_LEGGINGS.get()),
                        Ingredient.of(ModItems.BOLTON_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_PLATE_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "bolton_plate_leggings_smithing");

// Bolten Plate Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.BOLTEN_LEVY_BOOTS.get()),
                        Ingredient.of(ModItems.BOLTON_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_PLATE_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "bolton_plate_boots_smithing");

// Bolten Noble Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.BOLTEN_PLATE_HELMET.get()),
                        Ingredient.of(ModItems.BOLTON_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_NOBLE_HELMET.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "bolton_noble_helmet_smithing");

// Bolten Noble Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.BOLTEN_PLATE_CHESTPLATE.get()),
                        Ingredient.of(ModItems.BOLTON_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_NOBLE_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "bolton_noble_chestplate_smithing");

// Bolten Noble Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.BOLTEN_PLATE_LEGGINGS.get()),
                        Ingredient.of(ModItems.BOLTON_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_NOBLE_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "bolton_noble_leggings_smithing");

// Bolten Noble Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.BOLTEN_PLATE_BOOTS.get()),
                        Ingredient.of(ModItems.BOLTON_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.BOLTEN_NOBLE_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "bolton_noble_boots_smithing");

        // Manderly Levy Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_HELMET.get()),
                        Ingredient.of(ModItems.MANDERLY_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_LEVY_HELMET.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "manderly_levy_helmet_smithing");

// Manderly Levy Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_CHESTPLATE.get()),
                        Ingredient.of(ModItems.MANDERLY_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_LEVY_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "manderly_levy_chestplate_smithing");

// Manderly Levy Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_LEGGINGS.get()),
                        Ingredient.of(ModItems.MANDERLY_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_LEVY_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "manderly_levy_leggings_smithing");

// Manderly Levy Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_BOOTS.get()),
                        Ingredient.of(ModItems.MANDERLY_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_LEVY_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "manderly_levy_boots_smithing");

// Manderly Plate Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.MANDERLY_LEVY_HELMET.get()),
                        Ingredient.of(ModItems.MANDERLY_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_PLATE_HELMET.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "manderly_plate_helmet_smithing");

// Manderly Plate Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.MANDERLY_LEVY_CHESTPLATE.get()),
                        Ingredient.of(ModItems.MANDERLY_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_PLATE_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "manderly_plate_chestplate_smithing");

// Manderly Plate Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.MANDERLY_LEVY_LEGGINGS.get()),
                        Ingredient.of(ModItems.MANDERLY_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_PLATE_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "manderly_plate_leggings_smithing");

// Manderly Plate Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.MANDERLY_LEVY_BOOTS.get()),
                        Ingredient.of(ModItems.MANDERLY_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_PLATE_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "manderly_plate_boots_smithing");

// Manderly Noble Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.MANDERLY_PLATE_HELMET.get()),
                        Ingredient.of(ModItems.MANDERLY_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_NOBLE_HELMET.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "manderly_noble_helmet_smithing");

// Manderly Noble Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.MANDERLY_PLATE_CHESTPLATE.get()),
                        Ingredient.of(ModItems.MANDERLY_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_NOBLE_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "manderly_noble_chestplate_smithing");

// Manderly Noble Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.MANDERLY_PLATE_LEGGINGS.get()),
                        Ingredient.of(ModItems.MANDERLY_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_NOBLE_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "manderly_noble_leggings_smithing");

// Manderly Noble Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.MANDERLY_PLATE_BOOTS.get()),
                        Ingredient.of(ModItems.MANDERLY_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MANDERLY_PLATE_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "manderly_noble_boots_smithing");

        // Northern Mountain Clan Leather Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_LEVY_HELMET.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_LEVY_HELMET.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "mountain_clan_leather_helmet_smithing");

// Northern Mountain Clan Leather Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_LEVY_CHESTPLATE.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_LEVY_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "mountain_clan_leather_chestplate_smithing");

// Northern Mountain Clan Leather Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_LEVY_LEGGINGS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_LEVY_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "mountain_clan_leather_leggings_smithing");

// Northern Mountain Clan Leather Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_LEVY_BOOTS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_LEVY_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_irons", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "mountain_clan_leather_boots_smithing");

// Northern Mountain Clan Chain Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_PLATE_HELMET.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_PLATE_HELMET.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "mountain_clan_chain_helmet_smithing");

// Northern Mountain Clan Chain Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_PLATE_CHESTPLATE.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_PLATE_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "mountain_clan_chain_chestplate_smithing");

// Northern Mountain Clan Chain Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_PLATE_LEGGINGS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_PLATE_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "mountain_clan_chain_leggings_smithing");

// Northern Mountain Clan Chain Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_PLATE_BOOTS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_PLATE_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "mountain_clan_chain_boots_smithing");

// Northern Mountain Clan Noble Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_CHIEF_HELMET.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_CHIEF_HELMET.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "mountain_clan_noble_helmet_smithing");

// Northern Mountain Clan Noble Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_CHIEF_CHESTPLATE.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_CHIEF_CHESTPLATE.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "mountain_clan_noble_chestplate_smithing");

// Northern Mountain Clan Noble Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_CHIEF_LEGGINGS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_CHIEF_LEGGINGS.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "mountain_clan_noble_leggings_smithing");

// Northern Mountain Clan Noble Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.MOUNTAIN_CLAN_CHIEF_BOOTS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.MOUNTAIN_CLAN_CHIEF_BOOTS.get()
                )
                .unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "mountain_clan_noble_boots_smithing");
        // Ironborn Levy Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_HELMET.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_LEVY_HELMET.get()
                ).unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "ironborn_levy_helmet_smithing");

// Ironborn Levy Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_CHESTPLATE.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_LEVY_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "ironborn_levy_chestplate_smithing");

// Ironborn Levy Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_LEGGINGS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_LEVY_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "ironborn_levy_leggings_smithing");

// Ironborn Levy Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_IRON.get()),
                        Ingredient.of(ModItems.STEEL_BOOTS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_LEVY_BOOTS.get()
                ).unlocks("has_upgrade_kit_iron", has(ModItems.UPGRADE_KIT_IRON.get()))
                .save(this.output, "ironborn_levy_boots_smithing");

        // Ironborn Plate Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.IRONBORN_LEVY_HELMET.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_PLATE_HELMET.get()
                ).unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "ironborn_plate_helmet_smithing");

// Ironborn Plate Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.IRONBORN_LEVY_CHESTPLATE.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_PLATE_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "ironborn_plate_chestplate_smithing");

// Ironborn Plate Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.IRONBORN_LEVY_LEGGINGS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_PLATE_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "ironborn_plate_leggings_smithing");

// Ironborn Plate Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.IRONBORN_LEVY_BOOTS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_PLATE_BOOTS.get()
                ).unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "ironborn_plate_boots_smithing");

// Ironborn Noble Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.IRONBORN_PLATE_HELMET.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_NOBLE_HELMET.get()
                ).unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "ironborn_noble_helmet_smithing");

// Ironborn Noble Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.IRONBORN_PLATE_CHESTPLATE.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_NOBLE_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "ironborn_noble_chestplate_smithing");

// Ironborn Noble Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.IRONBORN_PLATE_LEGGINGS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_NOBLE_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "ironborn_noble_leggings_smithing");

// Ironborn Noble Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_NOBLE.get()),
                        Ingredient.of(ModItems.IRONBORN_PLATE_BOOTS.get()),
                        Ingredient.of(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.IRONBORN_NOBLE_BOOTS.get()
                ).unlocks("has_upgrade_kit_noble", has(ModItems.UPGRADE_KIT_NOBLE.get()))
                .save(this.output, "ironborn_noble_boots_smithing");

        // NIGHT'S WATCH RANGER ARMOR - Base tier
// Night's Watch Ranger Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_HELMET),
                        Ingredient.of(ModItems.NIGHTS_WATCH_RANGER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_RANGER_HELMET.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "night_watch_ranger_helmet_smithing");

// Night's Watch Ranger Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_CHESTPLATE),
                        Ingredient.of(ModItems.NIGHTS_WATCH_RANGER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_RANGER_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "night_watch_ranger_chestplate_smithing");

// Night's Watch Ranger Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_LEGGINGS),
                        Ingredient.of(ModItems.NIGHTS_WATCH_RANGER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_RANGER_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "night_watch_ranger_leggings_smithing");

// Night's Watch Ranger Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_BOOTS),
                        Ingredient.of(ModItems.NIGHTS_WATCH_RANGER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_RANGER_BOOTS.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "night_watch_ranger_boots_smithing");

// NIGHT'S WATCH LEATHER ARMOR - Mid tier, upgraded from Ranger
// Night's Watch Leather Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_LEATHER.get()),
                        Ingredient.of(ModItems.NIGHT_WATCH_RANGER_HELMET.get()),
                        Ingredient.of(ModItems.NIGHTS_WATCH_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_LEATHER_HELMET.get()
                ).unlocks("has_upgrade_kit_leather", has(ModItems.UPGRADE_KIT_LEATHER.get()))
                .save(this.output, "night_watch_leather_helmet_smithing");

// Night's Watch Leather Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_LEATHER.get()),
                        Ingredient.of(ModItems.NIGHT_WATCH_RANGER_CHESTPLATE.get()),
                        Ingredient.of(ModItems.NIGHTS_WATCH_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_LEATHER_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_leather", has(ModItems.UPGRADE_KIT_LEATHER.get()))
                .save(this.output, "night_watch_leather_chestplate_smithing");

// Night's Watch Leather Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_LEATHER.get()),
                        Ingredient.of(ModItems.NIGHT_WATCH_RANGER_LEGGINGS.get()),
                        Ingredient.of(ModItems.NIGHTS_WATCH_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_LEATHER_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_leather", has(ModItems.UPGRADE_KIT_LEATHER.get()))
                .save(this.output, "night_watch_leather_leggings_smithing");

// Night's Watch Leather Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_LEATHER.get()),
                        Ingredient.of(ModItems.NIGHT_WATCH_RANGER_BOOTS.get()),
                        Ingredient.of(ModItems.NIGHTS_WATCH_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_LEATHER_BOOTS.get()
                ).unlocks("has_upgrade_kit_leather", has(ModItems.UPGRADE_KIT_LEATHER.get()))
                .save(this.output, "night_watch_leather_boots_smithing");

// NIGHT'S WATCH ELITE ARMOR - Top tier, upgraded from Leather
// Night's Watch Elite Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.NIGHT_WATCH_LEATHER_HELMET.get()),
                        Ingredient.of(ModItems.NIGHTS_WATCH_ELITE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_ELITE_HELMET.get()
                ).unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "night_watch_elite_helmet_smithing");

// Night's Watch Elite Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.NIGHT_WATCH_LEATHER_CHESTPLATE.get()),
                        Ingredient.of(ModItems.NIGHTS_WATCH_ELITE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_ELITE_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "night_watch_elite_chestplate_smithing");

// Night's Watch Elite Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.NIGHT_WATCH_LEATHER_LEGGINGS.get()),
                        Ingredient.of(ModItems.NIGHTS_WATCH_ELITE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_ELITE_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "night_watch_elite_leggings_smithing");

// Night's Watch Elite Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_STEEL.get()),
                        Ingredient.of(ModItems.NIGHT_WATCH_LEATHER_BOOTS.get()),
                        Ingredient.of(ModItems.NIGHTS_WATCH_ELITE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.NIGHT_WATCH_ELITE_BOOTS.get()
                ).unlocks("has_upgrade_kit_steel", has(ModItems.UPGRADE_KIT_STEEL.get()))
                .save(this.output, "night_watch_elite_boots_smithing");

// WILDLING FUR ARMOR - Base tier
// Wildling Fur Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_HELMET),
                        Ingredient.of(ModItems.WILDLING_FUR_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_FUR_HELMET.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "wildling_fur_helmet_smithing");

// Wildling Fur Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_CHESTPLATE),
                        Ingredient.of(ModItems.WILDLING_FUR_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_FUR_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "wildling_fur_chestplate_smithing");

// Wildling Fur Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_LEGGINGS),
                        Ingredient.of(ModItems.WILDLING_FUR_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_FUR_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "wildling_fur_leggings_smithing");

// Wildling Fur Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_BOOTS),
                        Ingredient.of(ModItems.WILDLING_FUR_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_FUR_BOOTS.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "wildling_fur_boots_smithing");

// WILDLING LEATHER ARMOR - Mid tier, upgraded from Fur
// Wildling Leather Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_LEATHER.get()),
                        Ingredient.of(ModItems.WILDLING_FUR_HELMET.get()),
                        Ingredient.of(ModItems.WILDLING_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_LEATHER_HELMET.get()
                ).unlocks("has_upgrade_kit_leather", has(ModItems.UPGRADE_KIT_LEATHER.get()))
                .save(this.output, "wildling_leather_helmet_smithing");

// Wildling Leather Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_LEATHER.get()),
                        Ingredient.of(ModItems.WILDLING_FUR_CHESTPLATE.get()),
                        Ingredient.of(ModItems.WILDLING_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_LEATHER_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_leather", has(ModItems.UPGRADE_KIT_LEATHER.get()))
                .save(this.output, "wildling_leather_chestplate_smithing");

// Wildling Leather Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_LEATHER.get()),
                        Ingredient.of(ModItems.WILDLING_FUR_LEGGINGS.get()),
                        Ingredient.of(ModItems.WILDLING_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_LEATHER_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_leather", has(ModItems.UPGRADE_KIT_LEATHER.get()))
                .save(this.output, "wildling_leather_leggings_smithing");

// Wildling Leather Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_LEATHER.get()),
                        Ingredient.of(ModItems.WILDLING_FUR_BOOTS.get()),
                        Ingredient.of(ModItems.WILDLING_LEATHER_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_LEATHER_BOOTS.get()
                ).unlocks("has_upgrade_kit_leather", has(ModItems.UPGRADE_KIT_LEATHER.get()))
                .save(this.output, "wildling_leather_boots_smithing");

// WILDLING CHIEF ARMOR - Top tier, upgraded from Leather
// Wildling Chief Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_CHIEF.get()),
                        Ingredient.of(ModItems.WILDLING_LEATHER_HELMET.get()),
                        Ingredient.of(ModItems.WILDLING_CHIEF_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_CHIEF_HELMET.get()
                ).unlocks("has_upgrade_kit_chief", has(ModItems.UPGRADE_KIT_CHIEF.get()))
                .save(this.output, "wildling_chief_helmet_smithing");

// Wildling Chief Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_CHIEF.get()),
                        Ingredient.of(ModItems.WILDLING_LEATHER_CHESTPLATE.get()),
                        Ingredient.of(ModItems.WILDLING_CHIEF_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_CHIEF_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_chief", has(ModItems.UPGRADE_KIT_CHIEF.get()))
                .save(this.output, "wildling_chief_chestplate_smithing");

// Wildling Chief Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_CHIEF.get()),
                        Ingredient.of(ModItems.WILDLING_LEATHER_LEGGINGS.get()),
                        Ingredient.of(ModItems.WILDLING_CHIEF_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_CHIEF_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_chief", has(ModItems.UPGRADE_KIT_CHIEF.get()))
                .save(this.output, "wildling_chief_leggings_smithing");

// Wildling Chief Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_CHIEF.get()),
                        Ingredient.of(ModItems.WILDLING_LEATHER_BOOTS.get()),
                        Ingredient.of(ModItems.WILDLING_CHIEF_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.WILDLING_CHIEF_BOOTS.get()
                ).unlocks("has_upgrade_kit_chief", has(ModItems.UPGRADE_KIT_CHIEF.get()))
                .save(this.output, "wildling_chief_boots_smithing");

// THENN LEVY ARMOR - Base tier
// Thenn Levy Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_HELMET),
                        Ingredient.of(ModItems.THENN_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_LEVY_HELMET.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "thenn_levy_helmet_smithing");

// Thenn Levy Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_CHESTPLATE),
                        Ingredient.of(ModItems.THENN_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_LEVY_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "thenn_levy_chestplate_smithing");

// Thenn Levy Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_LEGGINGS),
                        Ingredient.of(ModItems.THENN_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_LEVY_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "thenn_levy_leggings_smithing");

// Thenn Levy Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_FUR.get()),
                        Ingredient.of(Items.LEATHER_BOOTS),
                        Ingredient.of(ModItems.THENN_LEVY_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_LEVY_BOOTS.get()
                ).unlocks("has_upgrade_kit_fur", has(ModItems.UPGRADE_KIT_FUR.get()))
                .save(this.output, "thenn_levy_boots_smithing");

// THENN PLATE/BRONZE ARMOR - Mid tier, upgraded from Levy
// Thenn Plate Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_BRONZE.get()),
                        Ingredient.of(ModItems.THENN_LEVY_HELMET.get()),
                        Ingredient.of(ModItems.THENN_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_PLATE_HELMET.get()
                ).unlocks("has_upgrade_kit_bronze", has(ModItems.UPGRADE_KIT_BRONZE.get()))
                .save(this.output, "thenn_plate_helmet_smithing");

// Thenn Plate Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_BRONZE.get()),
                        Ingredient.of(ModItems.THENN_LEVY_CHESTPLATE.get()),
                        Ingredient.of(ModItems.THENN_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_PLATE_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_bronze", has(ModItems.UPGRADE_KIT_BRONZE.get()))
                .save(this.output, "thenn_plate_chestplate_smithing");

// Thenn Plate Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_BRONZE.get()),
                        Ingredient.of(ModItems.THENN_LEVY_LEGGINGS.get()),
                        Ingredient.of(ModItems.THENN_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_PLATE_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_bronze", has(ModItems.UPGRADE_KIT_BRONZE.get()))
                .save(this.output, "thenn_plate_leggings_smithing");

// Thenn Plate Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_BRONZE.get()),
                        Ingredient.of(ModItems.THENN_LEVY_BOOTS.get()),
                        Ingredient.of(ModItems.THENN_PLATE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_PLATE_BOOTS.get()
                ).unlocks("has_upgrade_kit_bronze", has(ModItems.UPGRADE_KIT_BRONZE.get()))
                .save(this.output, "thenn_plate_boots_smithing");

// THENN NOBLE/CHIEF ARMOR - Top tier, upgraded from Plate
// Thenn Noble Helmet
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_BRONZE_PLATE.get()),
                        Ingredient.of(ModItems.THENN_PLATE_HELMET.get()),
                        Ingredient.of(ModItems.THENN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_NOBLE_HELMET.get()
                ).unlocks("has_upgrade_kit_bronze_plate", has(ModItems.UPGRADE_KIT_BRONZE_PLATE.get()))
                .save(this.output, "thenn_noble_helmet_smithing");

// Thenn Noble Chestplate
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_BRONZE_PLATE.get()),
                        Ingredient.of(ModItems.THENN_PLATE_CHESTPLATE.get()),
                        Ingredient.of(ModItems.THENN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_NOBLE_CHESTPLATE.get()
                ).unlocks("has_upgrade_kit_bronze_plate", has(ModItems.UPGRADE_KIT_BRONZE_PLATE.get()))
                .save(this.output, "thenn_noble_chestplate_smithing");

// Thenn Noble Leggings
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_BRONZE_PLATE.get()),
                        Ingredient.of(ModItems.THENN_PLATE_LEGGINGS.get()),
                        Ingredient.of(ModItems.THENN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_NOBLE_LEGGINGS.get()
                ).unlocks("has_upgrade_kit_bronze_plate", has(ModItems.UPGRADE_KIT_BRONZE_PLATE.get()))
                .save(this.output, "thenn_noble_leggings_smithing");

// Thenn Noble Boots
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.UPGRADE_KIT_BRONZE_PLATE.get()),
                        Ingredient.of(ModItems.THENN_PLATE_BOOTS.get()),
                        Ingredient.of(ModItems.THENN_NOBLE_SMITHING_SCROLL.get()),
                        RecipeCategory.COMBAT,
                        ModItems.THENN_NOBLE_BOOTS.get()
                ).unlocks("has_upgrade_kit_bronze_plate", has(ModItems.UPGRADE_KIT_BRONZE_PLATE.get()))
                .save(this.output, "thenn_noble_boots_smithing");




    }



    private void generateWoodRecipes(String woodType) {
        // Get all the blocks for this wood type
        Block logBlock = ModBLocks.LOGS.get(woodType).get();
        Block strippedLogBlock = ModBLocks.STRIPPED_LOGS.get(woodType).get();
        Block woodBlock = ModBLocks.WOODS.get(woodType).get();
        Block strippedWoodBlock = ModBLocks.STRIPPED_WOODS.get(woodType).get();
        Block planksBlock = ModBLocks.PLANKS.get(woodType).get();
        Block saplingBlock = ModBLocks.SAPLINGS.get(woodType).get();
        Block leavesBlock = ModBLocks.LEAVES.get(woodType).get();
        Block stairsBlock = ModBLocks.STAIRS.get(woodType).get();
        Block slabBlock = ModBLocks.SLABS.get(woodType).get();
        Block buttonBlock = ModBLocks.BUTTONS.get(woodType).get();
        Block pressurePlateBlock = ModBLocks.PRESSURE_PLATES.get(woodType).get();
        Block doorBlock = ModBLocks.DOORS.get(woodType).get();
        Block trapdoorBlock = ModBLocks.TRAPDOORS.get(woodType).get();
        Block fenceBlock = ModBLocks.FENCES.get(woodType).get();
        Block fenceGateBlock = ModBLocks.FENCE_GATES.get(woodType).get();
        Block wallBlock = ModBLocks.WALLS.get(woodType).get();
        Block signBlock = ModBLocks.SIGNS.get(woodType).get();
        Block wallSignBlock = ModBLocks.WALL_SIGNS.get(woodType).get();
        Block hangingSignBlock = ModBLocks.HANGING_SIGNS.get(woodType).get();
        Block wallHangingSignBlock = ModBLocks.WALL_HANGING_SIGNS.get(woodType).get();

        // Get sign and hanging sign items
        Item signItem = ModItems.SIGN_ITEMS.get(woodType).get();
        Item hangingSignItem = ModItems.HANGING_SIGN_ITEMS.get(woodType).get();

        // ===== Log to Wood recipes =====
        // Normal wood
        this.shapeless(RecipeCategory.MISC, woodBlock, 3)
                .requires(logBlock, 4)
                .unlockedBy(getHasName(logBlock), has(logBlock))
                .save(this.output);

        // Stripped wood
        this.shapeless(RecipeCategory.MISC, strippedWoodBlock, 3)
                .requires(strippedLogBlock, 4)
                .unlockedBy(getHasName(strippedLogBlock), has(strippedLogBlock))
                .save(this.output);

        // ===== Planks recipes =====
        // From log
        this.shapeless(RecipeCategory.MISC, planksBlock, 4)
                .requires(logBlock)
                .unlockedBy(getHasName(logBlock), has(logBlock))
                .save(this.output, "planks_from_" + woodType + "_log");

        // From stripped log
        this.shapeless(RecipeCategory.MISC, planksBlock, 4)
                .requires(strippedLogBlock)
                .unlockedBy(getHasName(strippedLogBlock), has(strippedLogBlock))
                .save(this.output, "planks_from_" + woodType + "_stripped_log");

        // From wood
        this.shapeless(RecipeCategory.MISC, planksBlock, 4)
                .requires(woodBlock)
                .unlockedBy(getHasName(woodBlock), has(woodBlock))
                .save(this.output, "planks_from_" + woodType + "_wood");

        // From stripped wood
        this.shapeless(RecipeCategory.MISC, planksBlock, 4)
                .requires(strippedWoodBlock)
                .unlockedBy(getHasName(strippedWoodBlock), has(strippedWoodBlock))
                .save(this.output, "planks_from_" + woodType + "_stripped_wood");

        // ===== Crafting table from planks =====
        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output, woodType + "_plank_craftingtable");

        // ===== Sticks from planks =====
        this.shaped(RecipeCategory.MISC, Items.STICK, 4)
                .pattern("B")
                .pattern("B")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output, woodType + "_plank_stick");

        // ===== Smelting recipes for charcoal =====
        // From log
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(logBlock), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(logBlock), has(logBlock))
                .save(this.output, "smelt_" + woodType + "_log_to_charcoal");

        // From stripped log
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(strippedLogBlock), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(strippedLogBlock), has(strippedLogBlock))
                .save(this.output, "smelt_stripped_" + woodType + "_log_to_charcoal");

        // From wood
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(woodBlock), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(woodBlock), has(woodBlock))
                .save(this.output, "smelt_" + woodType + "_wood_to_charcoal");

        // From stripped wood
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(strippedWoodBlock), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(strippedWoodBlock), has(strippedWoodBlock))
                .save(this.output, "smelt_stripped_" + woodType + "_wood_to_charcoal");

        // ===== Decorative wood blocks =====
        // Stairs
        this.shaped(RecipeCategory.BUILDING_BLOCKS, stairsBlock, 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // Slabs
        this.shaped(RecipeCategory.BUILDING_BLOCKS, slabBlock, 6)
                .pattern("BBB")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // Button
        this.shaped(RecipeCategory.REDSTONE, buttonBlock)
                .pattern("B")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // Pressure plate
        this.shaped(RecipeCategory.REDSTONE, pressurePlateBlock)
                .pattern("BB")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // Door
        this.shaped(RecipeCategory.REDSTONE, doorBlock, 3)
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // Trapdoor
        this.shaped(RecipeCategory.REDSTONE, trapdoorBlock, 2)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // Fence
        this.shaped(RecipeCategory.DECORATIONS, fenceBlock, 3)
                .pattern("B#B")
                .pattern("B#B")
                .define('B', planksBlock)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // Fence gate
        this.shaped(RecipeCategory.DECORATIONS, fenceGateBlock)
                .pattern("#B#")
                .pattern("#B#")
                .define('B', planksBlock)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // Wall
        this.shaped(RecipeCategory.BUILDING_BLOCKS, wallBlock, 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', planksBlock)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output);

        // ===== Sign recipes =====
        // Standing sign
        this.shaped(RecipeCategory.DECORATIONS, signItem, 3)
                .pattern("BBB")
                .pattern("BBB")
                .pattern(" # ")
                .define('B', planksBlock)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(planksBlock), has(planksBlock))
                .save(this.output, woodType + "_sign");

        // Hanging sign
        this.shaped(RecipeCategory.DECORATIONS, hangingSignItem, 6)
                .pattern("# #")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', strippedLogBlock)
                .define('#', Items.CHAIN)
                .unlockedBy(getHasName(strippedLogBlock), has(strippedLogBlock))
                .save(this.output, woodType + "_hanging_sign");
    }

    // Helper method for the criteria
    protected static String getHasName(ItemLike pItemLike) {
        return "has_" + getItemName(pItemLike);
    }

    protected static String getItemName(ItemLike pItemLike) {
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(pItemLike.asItem())).getPath();
    }




    // Helper method to invoke the get() method on supplier objects
    @SuppressWarnings("unchecked")
    private <T> T invokeGet(Object supplier) {
        try {
            java.lang.reflect.Method getMethod = supplier.getClass().getMethod("get");
            return (T) getMethod.invoke(supplier);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke get() method", e);
        }
    }



    private void registerStoneRecipes() {

    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput p_365442_, CompletableFuture<HolderLookup.Provider> p_362168_) {
            super(p_365442_, p_362168_);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider p_364945_, @NotNull RecipeOutput p_362956_) {
            return new ModRecipeProvider(p_364945_, p_362956_);
        }

        @Override
        public @NotNull String getName() {
            return "ASOS Recipes";
        }
    }
}

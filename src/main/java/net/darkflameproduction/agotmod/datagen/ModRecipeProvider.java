package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.item.custom.BannerPatterns;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
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

import java.util.*;
import java.util.concurrent.CompletableFuture;

// Class for generating data pack recipes
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private RecipeOutput output;

    // Constructor
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }



    private void generateStonecutterRecipes() {

        // Each entry: variant map â†’ vanilla source block (used for "from vanilla" recipes)
        // REDKEEP uses ModBLocks.REDKEEP_STONE_BLOCK instead of a vanilla block â€” handled separately below
        record VariantEntry(Map<Integer, ModBLocks.BlockSet> variants, ItemLike source, String prefix) {}

        List<VariantEntry> entries = List.of(
                new VariantEntry(ModBLocks.BLACKSTONE_VARIANTS,  Blocks.BLACKSTONE,       "blackstone"),
                new VariantEntry(ModBLocks.BASALT_VARIANTS,      Blocks.BASALT,           "basalt"),
                new VariantEntry(ModBLocks.BRICKS_VARIANTS,      Blocks.BRICKS,           "bricks"),
                new VariantEntry(ModBLocks.CALCITE_VARIANTS,     Blocks.CALCITE,          "calcite"),
                new VariantEntry(ModBLocks.CDEEPSLATE_VARIANTS,  Blocks.COBBLED_DEEPSLATE,"cdeepslate"),
                new VariantEntry(ModBLocks.GRANITE_VARIANTS,     Blocks.GRANITE,          "granite"),
                new VariantEntry(ModBLocks.ANDESITE_VARIANTS,    Blocks.ANDESITE,         "andesite"),
                new VariantEntry(ModBLocks.TUFF_VARIANTS,        Blocks.TUFF,             "tuff"),
                new VariantEntry(ModBLocks.DIORITE_VARIANTS,     Blocks.DIORITE,          "diorite"),
                new VariantEntry(ModBLocks.QUARTZ_VARIANTS,      Blocks.QUARTZ_BLOCK,     "quartz"),
                new VariantEntry(ModBLocks.RSANDSTONE_VARIANTS,  Blocks.RED_SANDSTONE,    "rsandstone"),
                new VariantEntry(ModBLocks.SANDSTONE_VARIANTS,   Blocks.SANDSTONE,        "sandstone"),
                new VariantEntry(ModBLocks.STONE_VARIANTS,       Blocks.STONE,            "stone"),
                new VariantEntry(ModBLocks.SSTONE_VARIANTS,      Blocks.SMOOTH_STONE,     "sstone"),
                new VariantEntry(ModBLocks.BONE_VARIANTS,        Blocks.BONE_BLOCK,       "bone"),
                new VariantEntry(ModBLocks.DRIPSTONE_VARIANTS,   Blocks.DRIPSTONE_BLOCK,  "dripstone"),
                new VariantEntry(ModBLocks.PACKED_ICE_VARIANTS,  Blocks.PACKED_ICE,       "packed_ice"),
                new VariantEntry(ModBLocks.MUD_BRICK_VARIANTS,   Blocks.MUD,              "mud")
        );

        for (VariantEntry entry : entries) {
            for (Map.Entry<Integer, ModBLocks.BlockSet> e : entry.variants().entrySet()) {
                ModBLocks.BlockSet set = e.getValue();
                String pattern = entry.prefix() + "_" + e.getKey();
                stonecutVariant(set, entry.source(), pattern);
            }
        }

        // REDKEEP uses its own source block rather than a vanilla block
        for (Map.Entry<Integer, ModBLocks.BlockSet> e : ModBLocks.REDKEEP_VARIANTS.entrySet()) {
            stonecutVariant(e.getValue(), ModBLocks.REDKEEP_STONE_BLOCK.get(), "redkeep_" + e.getKey());
        }
    }

// â”€â”€ Helper â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

    private void stonecutVariant(ModBLocks.BlockSet set, ItemLike source, String pattern) {
        ItemLike base   = set.base().get().asItem();
        ItemLike stairs = set.stairs().get().asItem();
        ItemLike slab   = set.slab().get().asItem();
        ItemLike wall   = set.wall().get().asItem();

        // From vanilla source â†’ each variant shape
        cut(source, base,   1);
        cut(source, stairs, 1);
        cut(source, slab,   2);
        cut(source, wall,   1);

        // From variant base â†’ its shaped versions (named to avoid recipe ID collision)
        cut(base, stairs, 1, pattern + "_stairs_from_" + pattern + "_block");
        cut(base, slab,   2, pattern + "_slab_from_"   + pattern + "_block");
        cut(base, wall,   1, pattern + "_wall_from_"   + pattern + "_block");
    }

    private void cut(ItemLike from, ItemLike to, int count) {
        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(from), RecipeCategory.BUILDING_BLOCKS, to, count)
                .unlockedBy(getHasName(from), has(from))
                .save(this.output);
    }

    private void cut(ItemLike from, ItemLike to, int count, String id) {
        SingleItemRecipeBuilder.stonecutting(
                        Ingredient.of(from), RecipeCategory.BUILDING_BLOCKS, to, count)
                .unlockedBy(getHasName(from), has(from))
                .save(this.output, id);
    }

    private void chandelierRecipe(ItemLike chandelier, ItemLike ingot, ItemLike candle) {
        this.shaped(RecipeCategory.DECORATIONS, chandelier)
                .pattern(" I ")
                .pattern("CIC")
                .pattern("C C")
                .define('I', ingot)
                .define('C', candle)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(this.output);
    }

    // Main method for building recipes
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
        this.output = recipeOutput;
        List<ItemLike> tinSmeltables = List.of(
                ModItems.RAW_TIN.get(),
                ModBLocks.TIN_ORE.get(),
                ModBLocks.DEEPSLATE_TIN_ORE.get());
        generateStonecutterRecipes();

        oreSmelting(this.output, tinSmeltables, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 200, "tin");
        oreBlasting(this.output, tinSmeltables, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 100, "tin");
        List<ItemLike> silverSmeltables = List.of(
                ModItems.RAW_SILVER.get(),
                ModBLocks.SILVER_ORE.get(),
                ModBLocks.DEEPSLATE_SILVER_ORE.get());
        oreSmelting(this.output, silverSmeltables, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.25f, 200, "silver");
        oreBlasting(this.output, silverSmeltables, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 0.25f, 100, "silver");
        this.shaped(RecipeCategory.MISC, ModItems.SILVER_INGOT.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SILVER_NUGGET.get())
                .unlockedBy(getHasName(ModItems.SILVER_NUGGET.get()), has(ModItems.SILVER_NUGGET.get()))
                .save(this.output, "silver_ingot_from_nuggets");
        this.shapeless(RecipeCategory.MISC, ModItems.SILVER_NUGGET.get(), 9)
                .requires(ModItems.SILVER_INGOT.get())
                .unlockedBy(getHasName(ModItems.SILVER_INGOT.get()), has(ModItems.SILVER_INGOT.get()))
                .save(this.output);

        this.shaped(RecipeCategory.MISC, ModBLocks.SILVER_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SILVER_INGOT.get())
                .unlockedBy(getHasName(ModItems.SILVER_INGOT.get()), has(ModItems.SILVER_INGOT.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 9)
                .requires(ModBLocks.SILVER_BLOCK.get())
                .unlockedBy(getHasName(ModBLocks.SILVER_BLOCK.get()), has(ModBLocks.SILVER_BLOCK.get()))
                .save(this.output, "silver_ingot_from_block");

        this.shaped(RecipeCategory.MISC, ModBLocks.RAW_SILVER_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RAW_SILVER.get())
                .unlockedBy(getHasName(ModItems.RAW_SILVER.get()), has(ModItems.RAW_SILVER.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.RAW_SILVER.get(), 9)
                .requires(ModBLocks.RAW_SILVER_BLOCK.get())
                .unlockedBy(getHasName(ModBLocks.RAW_SILVER_BLOCK.get()), has(ModBLocks.RAW_SILVER_BLOCK.get()))
                .save(this.output);


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
                .save(this.output, "tin_ingot_from_block");

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

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.DERPY_DRAGON_BLOCK.get())
                .pattern("XSX")
                .pattern("SXS")
                .pattern("XSX")
                .define('S', Blocks.DRAGON_EGG)
                .define('X', Blocks.DRAGON_HEAD)
                .unlockedBy(getHasName(Blocks.DRAGON_EGG), has(Blocks.DRAGON_HEAD))
                .save(this.output);

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
        stonecutterResultFromBase(this.output, RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, ModBLocks.DARK_STONE_BRICK.get());
        stonecutterResultFromBase(this.output, RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, ModBLocks.STONE_BRICK_BUT_COOLER.get());
        stonecutterResultFromBase(this.output, RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        // -------------------------------------------------(BLOCKS)------------------------------------------------- //
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.MUD_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', Blocks.MUD)
                .unlockedBy(getHasName(Blocks.MUD), has(Blocks.MUD))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.MUD_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', Blocks.MUD)
                .unlockedBy(getHasName(Blocks.MUD), has(Blocks.MUD))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.COARSE_DIRT_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', Blocks.COARSE_DIRT)
                .unlockedBy(getHasName(Blocks.COARSE_DIRT), has(Blocks.COARSE_DIRT))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.COARSE_DIRT_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', Blocks.COARSE_DIRT)
                .unlockedBy(getHasName(Blocks.COARSE_DIRT), has(Blocks.COARSE_DIRT))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.PODZOL_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', Blocks.PODZOL)
                .unlockedBy(getHasName(Blocks.PODZOL), has(Blocks.PODZOL))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.PODZOL_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', Blocks.PODZOL)
                .unlockedBy(getHasName(Blocks.PODZOL), has(Blocks.PODZOL))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.GRASS_BLOCK_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', Blocks.GRASS_BLOCK)
                .unlockedBy(getHasName(Blocks.GRASS_BLOCK), has(Blocks.GRASS_BLOCK))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.GRASS_BLOCK_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', Blocks.GRASS_BLOCK)
                .unlockedBy(getHasName(Blocks.GRASS_BLOCK), has(Blocks.GRASS_BLOCK))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.DIRT_PATH_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', Blocks.DIRT_PATH)
                .unlockedBy(getHasName(Blocks.DIRT_PATH), has(Blocks.DIRT_PATH))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.DIRT_PATH_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', Blocks.DIRT_PATH)
                .unlockedBy(getHasName(Blocks.DIRT_PATH), has(Blocks.DIRT_PATH))
                .save(this.output);


        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.THATCH_BLOCK.get())
                .pattern(" OOO ")
                .pattern(" OOO ")
                .pattern(" OOO ")
                .define('O', Ingredient.of(Items.STICK, Items.SHORT_GRASS, ModBLocks.REED)) // Your custom hammer
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(this.output, "thatch_from_sticks_and_grass");

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.THATCH_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', ModBLocks.THATCH_STAIRS.get())
                .unlockedBy(getHasName(ModBLocks.THATCH_BLOCK), has(ModBLocks.THATCH_BLOCK))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.THATCH_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', ModBLocks.THATCH_SLAB.get())
                .unlockedBy(getHasName(ModBLocks.THATCH_BLOCK), has(ModBLocks.THATCH_SLAB))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.HEARTH_BLOCK.get(), 4)
                .pattern("BB")
                .pattern("OO")
                .define('B', Items.CHARCOAL)
                .define('O', ModBLocks.PEAT.get())
                .unlockedBy(getHasName(ModBLocks.PEAT), has(ModBLocks.PEAT))
                .save(this.output);

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

        // Wood conversions

// Rotten
        this.shapeless(RecipeCategory.MISC, ModBLocks.ROTTEN_WOOD.get(), 3)
                .requires(ModBLocks.ROTTEN_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.ROTTEN_LOG.get()), has(ModBLocks.ROTTEN_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_ROTTEN_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_ROTTEN_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ROTTEN_LOG.get()), has(ModBLocks.STRIPPED_ROTTEN_LOG.get()))
                .save(this.output);

// Charred
        this.shapeless(RecipeCategory.MISC, ModBLocks.CHARRED_WOOD.get(), 3)
                .requires(ModBLocks.CHARRED_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.CHARRED_LOG.get()), has(ModBLocks.CHARRED_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_CHARRED_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_CHARRED_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CHARRED_LOG.get()), has(ModBLocks.STRIPPED_CHARRED_LOG.get()))
                .save(this.output);


// Planks

        this.shapeless(RecipeCategory.MISC, ModBLocks.ROTTEN_PLANKS.get(), 4)
                .requires(ModBLocks.ROTTEN_LOG.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_LOG.get()), has(ModBLocks.ROTTEN_LOG.get()))
                .save(this.output, "planks_from_rotten_log");

        this.shapeless(RecipeCategory.MISC, ModBLocks.ROTTEN_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_ROTTEN_LOG.get())
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ROTTEN_LOG.get()), has(ModBLocks.STRIPPED_ROTTEN_LOG.get()))
                .save(this.output, "planks_from_rotten_stripped_log");

        this.shapeless(RecipeCategory.MISC, ModBLocks.ROTTEN_PLANKS.get(), 4)
                .requires(ModBLocks.ROTTEN_WOOD.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_WOOD.get()), has(ModBLocks.ROTTEN_WOOD.get()))
                .save(this.output, "planks_from_rotten_wood");

        this.shapeless(RecipeCategory.MISC, ModBLocks.ROTTEN_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_ROTTEN_WOOD.get())
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ROTTEN_WOOD.get()), has(ModBLocks.STRIPPED_ROTTEN_WOOD.get()))
                .save(this.output, "planks_from_rotten_stripped_wood");

        this.shapeless(RecipeCategory.MISC, ModBLocks.CHARRED_PLANKS.get(), 4)
                .requires(ModBLocks.CHARRED_LOG.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_LOG.get()), has(ModBLocks.CHARRED_LOG.get()))
                .save(this.output, "planks_from_charred_log");

        this.shapeless(RecipeCategory.MISC, ModBLocks.CHARRED_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_CHARRED_LOG.get())
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CHARRED_LOG.get()), has(ModBLocks.STRIPPED_CHARRED_LOG.get()))
                .save(this.output, "planks_from_charred_stripped_log");

        this.shapeless(RecipeCategory.MISC, ModBLocks.CHARRED_PLANKS.get(), 4)
                .requires(ModBLocks.CHARRED_WOOD.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_WOOD.get()), has(ModBLocks.CHARRED_WOOD.get()))
                .save(this.output, "planks_from_charred_wood");

        this.shapeless(RecipeCategory.MISC, ModBLocks.CHARRED_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_CHARRED_WOOD.get())
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CHARRED_WOOD.get()), has(ModBLocks.STRIPPED_CHARRED_WOOD.get()))
                .save(this.output, "planks_from_charred_stripped_wood");


// Smelting to charcoal

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.ROTTEN_LOG.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.ROTTEN_LOG.get()), has(ModBLocks.ROTTEN_LOG.get()))
                .save(this.output, "smelt_rotten_log_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.STRIPPED_ROTTEN_LOG.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ROTTEN_LOG.get()), has(ModBLocks.STRIPPED_ROTTEN_LOG.get()))
                .save(this.output, "smelt_stripped_rotten_log_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.ROTTEN_WOOD.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.ROTTEN_WOOD.get()), has(ModBLocks.ROTTEN_WOOD.get()))
                .save(this.output, "smelt_rotten_wood_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.STRIPPED_ROTTEN_WOOD.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ROTTEN_WOOD.get()), has(ModBLocks.STRIPPED_ROTTEN_WOOD.get()))
                .save(this.output, "smelt_stripped_rotten_wood_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.CHARRED_LOG.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.CHARRED_LOG.get()), has(ModBLocks.CHARRED_LOG.get()))
                .save(this.output, "smelt_charred_log_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.STRIPPED_CHARRED_LOG.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CHARRED_LOG.get()), has(ModBLocks.STRIPPED_CHARRED_LOG.get()))
                .save(this.output, "smelt_stripped_charred_log_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.CHARRED_WOOD.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.CHARRED_WOOD.get()), has(ModBLocks.CHARRED_WOOD.get()))
                .save(this.output, "smelt_charred_wood_to_charcoal");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBLocks.STRIPPED_CHARRED_WOOD.get()), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 200)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CHARRED_WOOD.get()), has(ModBLocks.STRIPPED_CHARRED_WOOD.get()))
                .save(this.output, "smelt_stripped_charred_wood_to_charcoal");


// Wood family blocks

// Rotten
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.ROTTEN_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.ROTTEN_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.ROTTEN_BUTTON.get())
                .pattern("B")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.ROTTEN_PRESSURE_PLATE.get())
                .pattern("BB")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.ROTTEN_DOOR.get(), 3)
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.ROTTEN_TRAPDOOR.get(), 2)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.DECORATIONS, ModBLocks.ROTTEN_FENCE.get(), 3)
                .pattern("B#B")
                .pattern("B#B")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .define('#', Items.STICK) // assuming shared stick
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.DECORATIONS, ModBLocks.ROTTEN_FENCE_GATE.get())
                .pattern("#B#")
                .pattern("#B#")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .define('#', Items.STICK)
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.ROTTEN_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBLocks.ROTTEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ROTTEN_PLANKS.get()), has(ModBLocks.ROTTEN_PLANKS.get()))
                .save(this.output);


// Charred
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.CHARRED_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.CHARRED_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.CHARRED_BUTTON.get())
                .pattern("B")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.CHARRED_PRESSURE_PLATE.get())
                .pattern("BB")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.CHARRED_DOOR.get(), 3)
                .pattern("BB")
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.REDSTONE, ModBLocks.CHARRED_TRAPDOOR.get(), 2)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.DECORATIONS, ModBLocks.CHARRED_FENCE.get(), 3)
                .pattern("B#B")
                .pattern("B#B")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .define('#', Items.STICK)
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.DECORATIONS, ModBLocks.CHARRED_FENCE_GATE.get())
                .pattern("#B#")
                .pattern("#B#")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .define('#', Items.STICK)
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.CHARRED_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBLocks.CHARRED_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHARRED_PLANKS.get()), has(ModBLocks.CHARRED_PLANKS.get()))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.OAK_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.OAK_PLANKS)
                .unlockedBy(getHasName(Blocks.OAK_PLANKS), has(Blocks.OAK_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.SPRUCE_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.SPRUCE_PLANKS)
                .unlockedBy(getHasName(Blocks.SPRUCE_PLANKS), has(Blocks.SPRUCE_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.BIRCH_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.BIRCH_PLANKS)
                .unlockedBy(getHasName(Blocks.BIRCH_PLANKS), has(Blocks.BIRCH_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.JUNGLE_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.JUNGLE_PLANKS)
                .unlockedBy(getHasName(Blocks.JUNGLE_PLANKS), has(Blocks.JUNGLE_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.ACACIA_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.ACACIA_PLANKS)
                .unlockedBy(getHasName(Blocks.ACACIA_PLANKS), has(Blocks.ACACIA_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.DARK_OAK_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.DARK_OAK_PLANKS)
                .unlockedBy(getHasName(Blocks.DARK_OAK_PLANKS), has(Blocks.DARK_OAK_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.MANGROVE_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.MANGROVE_PLANKS)
                .unlockedBy(getHasName(Blocks.MANGROVE_PLANKS), has(Blocks.MANGROVE_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.CHERRY_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.CHERRY_PLANKS)
                .unlockedBy(getHasName(Blocks.CHERRY_PLANKS), has(Blocks.CHERRY_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.BAMBOO_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.BAMBOO_PLANKS)
                .unlockedBy(getHasName(Blocks.BAMBOO_PLANKS), has(Blocks.BAMBOO_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.CRIMSON_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.CRIMSON_PLANKS)
                .unlockedBy(getHasName(Blocks.CRIMSON_PLANKS), has(Blocks.CRIMSON_PLANKS))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBLocks.WARPED_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Blocks.WARPED_PLANKS)
                .unlockedBy(getHasName(Blocks.WARPED_PLANKS), has(Blocks.WARPED_PLANKS))
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
                "yew",
                "blue_soldier_pine",
                "soldier_pine"
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








        // -------------------------------------------------(FOODS)------------------------------------------------- //
        // Food smelting recipes Furnace
        // â”€â”€ Furnace â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

        // Meats

        // Offal & organ meats

        // Sausages

        // Fowl

        // Fish & Seafood

        // â”€â”€ Smoker â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

        // Meats

        // Offal & organ meats

        // Sausages

        // Fowl

        // Fish & Seafood

        // â”€â”€ Campfire â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€




        this.shaped(RecipeCategory.MISC, ModItems.NAIL.get())
                .pattern("O")
                .define('O', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(this.output, "nugget");

        // Every food recipe - cooking and crafting - is in FoodRecipes (from the FoodCatalog)
        FoodRecipes.build(this.output);

        // Cloth is woven from wool, or from cotton (cloth_from_cotton, with the other cloth recipes)
        this.shapeless(RecipeCategory.MISC, ModItems.CLOTH.get())
                .requires(Items.WHITE_WOOL, 2)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(this.output, "cloth_from_wool");

        // -------------------------------------------------(FURNITURE)------------------------------------------------- //

// â”€â”€ Vanilla wood furniture â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

        record WoodEntry(String name, Object planks, Object stick) {}

        List<WoodEntry> allWoods = new ArrayList<>();

// Vanilla
        allWoods.add(new WoodEntry("oak",      Blocks.OAK_PLANKS,      Items.STICK));
        allWoods.add(new WoodEntry("spruce",   Blocks.SPRUCE_PLANKS,   Items.STICK));
        allWoods.add(new WoodEntry("birch",    Blocks.BIRCH_PLANKS,    Items.STICK));
        allWoods.add(new WoodEntry("jungle",   Blocks.JUNGLE_PLANKS,   Items.STICK));
        allWoods.add(new WoodEntry("acacia",   Blocks.ACACIA_PLANKS,   Items.STICK));
        allWoods.add(new WoodEntry("dark_oak", Blocks.DARK_OAK_PLANKS, Items.STICK));
        allWoods.add(new WoodEntry("mangrove", Blocks.MANGROVE_PLANKS, Items.STICK));
        allWoods.add(new WoodEntry("cherry",   Blocks.CHERRY_PLANKS,   Items.STICK));
        allWoods.add(new WoodEntry("bamboo",   Blocks.BAMBOO_PLANKS,   Items.STICK));
        allWoods.add(new WoodEntry("crimson",  Blocks.CRIMSON_PLANKS,  Items.STICK));
        allWoods.add(new WoodEntry("warped",   Blocks.WARPED_PLANKS,   Items.STICK));

// Modded map-based woods
        for (String woodType : ModBLocks.WOOD_TYPES) {
            allWoods.add(new WoodEntry(woodType,
                    ModBLocks.PLANKS.get(woodType).get(),
                    Items.STICK));
        }

// Special woods
        allWoods.add(new WoodEntry("weirwood", ModBLocks.WEIRWOOD_PLANKS.get(), ModItems.WEIRWOOD_STICK.get()));
        allWoods.add(new WoodEntry("charred",  ModBLocks.CHARRED_PLANKS.get(),  Items.STICK));
        allWoods.add(new WoodEntry("rotten",   ModBLocks.ROTTEN_PLANKS.get(),   Items.STICK));

// Generate all furniture recipes
        for (WoodEntry wood : allWoods) {
            String n = wood.name();
            ItemLike planks = (ItemLike) wood.planks();
            ItemLike stick  = (ItemLike) wood.stick();

            ItemLike stoolItem    = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, n + "_stool"));
            ItemLike chairItem    = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, n + "_chair"));
            ItemLike armChairItem = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, n + "_arm_chair"));
            ItemLike tableItem    = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, n + "_table"));

            // Stool
            this.shaped(RecipeCategory.MISC, stoolItem)
                    .pattern("OO")
                    .pattern("AA")
                    .pattern("BB")
                    .define('O', ModItems.NAIL.get())
                    .define('A', planks)
                    .define('B', stick)
                    .unlockedBy(getHasName(planks), has(planks))
                    .save(this.output, n + "_stool");

            // Chair
            this.shaped(RecipeCategory.MISC, chairItem)
                    .pattern("AO")
                    .pattern("AO")
                    .pattern("B ")
                    .define('O', ModItems.NAIL.get())
                    .define('A', stick)
                    .define('B', stoolItem)
                    .unlockedBy(getHasName(planks), has(stoolItem))
                    .save(this.output, n + "_chair");

            // Arm chair
            this.shaped(RecipeCategory.MISC, armChairItem)
                    .pattern("O O")
                    .pattern("ABA")
                    .define('O', ModItems.NAIL.get())
                    .define('A', stick)
                    .define('B', chairItem)
                    .unlockedBy(getHasName(planks), has(chairItem))
                    .save(this.output, n + "_arm_chair");

            // Table
            this.shaped(RecipeCategory.MISC, tableItem)
                    .pattern("O O")
                    .pattern("BBB")
                    .pattern("A A")
                    .define('O', ModItems.NAIL.get())
                    .define('A', stick)
                    .define('B', planks)
                    .unlockedBy(getHasName(planks), has(planks))
                    .save(this.output, n + "_table");

            // Bench
            ItemLike benchItem = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, n + "_bench"));
            this.shaped(RecipeCategory.MISC, benchItem)
                    .pattern("BBB")
                    .pattern("AOA")
                    .define('O', ModItems.NAIL.get())
                    .define('A', stick)
                    .define('B', planks)
                    .unlockedBy(getHasName(planks), has(planks))
                    .save(this.output, n + "_bench");
        }


        // Chandeliers: two ingots of the metal and four candles of the colour
        Map<String, ItemLike> chandelierIngots = Map.of(
                "bronze", ModItems.BRONZE_INGOT.get(),
                "copper", Items.COPPER_INGOT,
                "gold",   Items.GOLD_INGOT,
                "iron",   Items.IRON_INGOT,
                "silver", ModItems.SILVER_INGOT.get());
        for (String metal : ModBLocks.CHANDELIER_METALS) {
            ItemLike ingot = chandelierIngots.get(metal);
            chandelierRecipe(ModBLocks.CHANDELIERS.get(metal + "_chandelier").get(), ingot, Items.CANDLE);
            for (DyeColor colour : DyeColor.values()) {
                ItemLike candle = BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(colour.getName() + "_candle"));
                chandelierRecipe(ModBLocks.CHANDELIERS.get(metal + "_chandelier_" + colour.getName()).get(), ingot, candle);
            }
        }

        // Pots with stand: a pot of five ingots hanging from a chain
        Map<String, ItemLike> potIngots = Map.of(
                "metal",  Items.IRON_INGOT,
                "bronze", ModItems.BRONZE_INGOT.get(),
                "copper", Items.COPPER_INGOT);
        for (String metal : ModBLocks.POT_STAND_METALS) {
            ItemLike ingot = potIngots.get(metal);
            this.shaped(RecipeCategory.DECORATIONS, ModBLocks.POT_STANDS.get(metal + "_pot_stand").get())
                    .pattern("ICI")
                    .pattern("I I")
                    .pattern(" I ")
                    .define('I', ingot)
                    .define('C', Items.CHAIN)
                    .unlockedBy(getHasName(ingot), has(ingot))
                    .save(this.output);
        }


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
        this.shaped(RecipeCategory.MISC, ModItems.CLOTH.get())
                .pattern("AA")
                .pattern("AA")
                .define('A', ModItems.COTTON.get())
                .unlockedBy(getHasName(ModItems.COTTON.get()), has(ModItems.COTTON.get()))
                .save(this.output, "cloth_from_cotton");
        this.shapeless(RecipeCategory.MISC, Items.STRING)
                .requires(ModItems.COTTON.get())
                .unlockedBy(getHasName(ModItems.COTTON.get()), has(ModItems.COTTON.get()))
                .save(this.output, "string_from_cotton");
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




        // ===== 3D ARMOUR SETS =====
        // Every piece is made at the smithing table: upgrade kit + the piece it upgrades + the set's scroll.
        // Tier 1 (regional) upgrades steel or leather armour; tier 2 upgrades the region's tier 1; tier 3
        // upgrades the faction's tier 2. Kits per tier come from the set's chain (see ModArmorSets.Chain).
        for (net.darkflameproduction.agotmod.armor.ModArmorSets.ArmorSet set : net.darkflameproduction.agotmod.armor.ModArmorSets.all()) {
            Item kit = set.kit();
            for (net.minecraft.world.item.ArmorItem.Type type : net.darkflameproduction.agotmod.armor.ModArmorSets.PIECES) {
                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(kit),
                                Ingredient.of(set.upgradeBase(type)),
                                Ingredient.of(set.scroll().get()),
                                RecipeCategory.COMBAT,
                                set.piece(type).get()
                        )
                        .unlocks(getHasName(kit), has(kit))
                        .save(this.output, set.itemId(type) + "_smithing");
            }
        }


        // ===== BANNERS =====


        this.shaped(RecipeCategory.DECORATIONS, Items.RED_BANNER) // Or create a custom banner item
                .pattern("ABA")
                .pattern("ACA")
                .pattern("ABA")
                .define('A', Items.RED_WOOL)
                .define('B', Items.BLACK_DYE)
                .define('C', BannerPatterns.TARGARYEN_BANNER_PATTERN.get())
                .unlockedBy("has_targaryen_pattern", has(BannerPatterns.TARGARYEN_BANNER_PATTERN.get()))
                .save(this.output, "targaryen_decorated_banner");




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

    private ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result) {
        return ShapedRecipeBuilder.shaped(category, result);
    }

    private ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result, int count) {
        return ShapedRecipeBuilder.shaped(category, result, count);
    }

    private ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result) {
        return ShapelessRecipeBuilder.shapeless(category, result);
    }

    private ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int count) {
        return ShapelessRecipeBuilder.shapeless(category, result, count);
    }

}


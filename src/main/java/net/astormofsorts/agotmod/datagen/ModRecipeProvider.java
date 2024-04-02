package net.astormofsorts.agotmod.datagen;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.block.ModBLocks;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

// Class for generating data pack recipes
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    // CREATES LIST OF TIN SMELTABLES
    private static final List<ItemLike> TIN_SMELTABLES = List.of(
            ModItems.RAW_TIN.get(),
            ModBLocks.TIN_ORE.get(),
            ModBLocks.DEEPSLATE_TIN_ORE.get());

    // Constructor
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    // Main method for building recipes
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        // -------------------------------------------------(TIN)------------------------------------------------- //
        // ---------------------------(SMELTING)--------------------------- //
        // Smelting recipes for tin
        oreSmelting(pWriter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 200, "tin");
        oreBlasting(pWriter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 100, "tin");
        // ---------------------------(SMELTING)--------------------------- //

        // ---------------------------(CRAFTING)--------------------------- //
        // Crafting recipes for tin-related items
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.TIN_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.TIN_INGOT.get())
                .unlockedBy(getHasName(ModItems.TIN_INGOT.get()), has(ModItems.TIN_INGOT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 9)
                .requires(ModBLocks.TIN_BLOCK.get())
                .unlockedBy(getHasName(ModBLocks.TIN_BLOCK.get()), has(ModBLocks.TIN_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.RAW_TIN_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RAW_TIN.get())
                .unlockedBy(getHasName(ModItems.RAW_TIN.get()), has(ModItems.RAW_TIN.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_TIN.get(), 9)
                .requires(ModBLocks.RAW_TIN_BLOCK.get())
                .unlockedBy(getHasName(ModBLocks.RAW_TIN_BLOCK.get()), has(ModBLocks.RAW_TIN_BLOCK.get()))
                .save(pWriter);
        // ---------------------------(CRAFTING)--------------------------- //
        // -------------------------------------------------(TIN)------------------------------------------------- //

        // -------------------------------------------------(BRONZE)------------------------------------------------- //
        // ---------------------------(CRAFTING)--------------------------- //
        // Crafting recipes for bronze-related items
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.BRONZE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 9)
                .requires(ModBLocks.BRONZE_BLOCK.get())
                .unlockedBy(getHasName(ModBLocks.BRONZE_BLOCK.get()), has(ModBLocks.BRONZE_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), 1)
                .requires(ModItems.TIN_INGOT.get())
                .requires(Items.COPPER_INGOT)
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(pWriter, "bronze_ingot_from_tin_and_copper");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBLocks.BRONZE_BLOCK.get(), 1)
                .requires(ModBLocks.TIN_BLOCK.get())
                .requires(Blocks.COPPER_BLOCK)
                .unlockedBy(getHasName(ModBLocks.BRONZE_BLOCK.get()), has(ModBLocks.BRONZE_BLOCK.get()))
                .save(pWriter, "bronze_block_from_tin_and_copper_block");

        // Crafting recipes for bronze tools
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_PICKAXE.get())
                .pattern("BBB")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_AXE.get())
                .pattern("BB")
                .pattern("BS")
                .pattern(" S")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_SHOVEL.get())
                .pattern("B")
                .pattern("S")
                .pattern("S")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_HOE.get())
                .pattern("BB")
                .pattern(" S")
                .pattern(" S")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BRONZE_SWORD.get())
                .pattern("B")
                .pattern("B")
                .pattern("S")
                .define('S', Items.STICK)
                .define('B', ModItems.BRONZE_INGOT.get())
                .unlockedBy(getHasName(ModItems.BRONZE_INGOT.get()), has(ModItems.BRONZE_INGOT.get()))
                .save(pWriter);
        // ---------------------------(CRAFTING)--------------------------- //
        // -------------------------------------------------(BRONZE)------------------------------------------------- //

        // -------------------------------------------------(STEEL)------------------------------------------------- //
        // ---------------------------(CRAFTING)--------------------------- //
        // Crafting recipes for steel-related items
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 2)
                .pattern("III")
                .pattern("ICC")
                .pattern("CC ")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COAL)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter, "steel_with_coal");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 2)
                .pattern("III")
                .pattern("ICC")
                .pattern("CC ")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.CHARCOAL)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter, "steel_with_charcoal");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_INGOT.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.STEEL_NUGGET.get())
                .unlockedBy(getHasName(ModItems.STEEL_NUGGET.get()), has(ModItems.STEEL_NUGGET.get()))
                .save(pWriter, "steel_from_nuggets");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_NUGGET.get(), 9)
                .requires(ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pWriter, "nuggets_from_steel");

        // Crafting recipes for steel tools
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_PICKAXE.get())
                .pattern("BBB")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_AXE.get())
                .pattern("BB")
                .pattern("BS")
                .pattern(" S")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_SHOVEL.get())
                .pattern("B")
                .pattern("S")
                .pattern("S")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_HOE.get())
                .pattern("BB")
                .pattern(" S")
                .pattern(" S")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_SWORD.get())
                .pattern("B")
                .pattern("B")
                .pattern("S")
                .define('S', Items.STICK)
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pWriter);
        // ---------------------------(CRAFTING)--------------------------- //
        // -------------------------------------------------(STEEL)------------------------------------------------- //

        // -------------------------------------------------(GEMS)------------------------------------------------- //
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TRANSPARENT_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TRANSPARENT_DIAMOND.get()), has(ModItems.TRANSPARENT_DIAMOND.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TRANSPARENT_DIAMOND.get(), 9)
                .requires(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get()), has(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.YELLOW_DIAMOND_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.YELLOW_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.YELLOW_DIAMOND.get()), has(ModItems.YELLOW_DIAMOND.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.YELLOW_DIAMOND.get(), 9)
                .requires(ModBLocks.YELLOW_DIAMOND_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.YELLOW_DIAMOND_BLOCK.get()), has(ModBLocks.YELLOW_DIAMOND_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.AMBER_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.AMBER.get())
                .unlockedBy(getHasName(ModItems.AMBER.get()), has(ModItems.AMBER.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AMBER.get(), 9)
                .requires(ModBLocks.AMBER_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.AMBER_BLOCK.get()), has(ModBLocks.AMBER_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.AMETHYST_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.AMETHYST.get())
                .unlockedBy(getHasName(ModItems.AMETHYST.get()), has(ModItems.AMETHYST.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AMETHYST.get(), 9)
                .requires(ModBLocks.AMETHYST_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.AMETHYST_BLOCK.get()), has(ModBLocks.AMETHYST_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.BLACK_DIAMOND_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BLACK_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.BLACK_DIAMOND.get()), has(ModItems.BLACK_DIAMOND.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACK_DIAMOND.get(), 9)
                .requires(ModBLocks.BLACK_DIAMOND_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.BLACK_DIAMOND_BLOCK.get()), has(ModBLocks.BLACK_DIAMOND_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.CARNELIAN_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.CARNELIAN.get())
                .unlockedBy(getHasName(ModItems.CARNELIAN.get()), has(ModItems.CARNELIAN.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CARNELIAN.get(), 9)
                .requires(ModBLocks.CARNELIAN_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.CARNELIAN_BLOCK.get()), has(ModBLocks.CARNELIAN_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBLocks.BLOODSTONE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BLOODSTONE.get())
                .unlockedBy(getHasName(ModItems.BLOODSTONE.get()), has(ModItems.BLOODSTONE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLOODSTONE.get(), 9)
                .requires(ModBLocks.BLOODSTONE_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBLocks.BLOODSTONE_BLOCK.get()), has(ModBLocks.BLOODSTONE_BLOCK.get()))
                .save(pWriter);
        // -------------------------------------------------(GEMS)------------------------------------------------- //

        // -------------------------------------------------(STONE CUTTER)------------------------------------------------- //
        // Stonecutting recipes
        stonecutting(Ingredient.of(Blocks.STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBLocks.DARK_STONE_BRICK.get())
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(pWriter);
        stonecutting(Ingredient.of(Blocks.STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBLocks.STONE_BRICK_BUT_COOLER.get())
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(pWriter);
        stonecutting(Ingredient.of(Blocks.STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBLocks.KINGS_LANDING_BRICK_LARGE.get())
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(pWriter);
        // -------------------------------------------------(STONE CUTTER)------------------------------------------------- //

        // -------------------------------------------------(WOOD)------------------------------------------------- //
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_WOOD.get(), 3)
                .requires(ModBLocks.SYCAMORE_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_LOG.get()), has(ModBLocks.SYCAMORE_LOG.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_SYCAMORE_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_SYCAMORE_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SYCAMORE_LOG.get()), has(ModBLocks.STRIPPED_SYCAMORE_LOG.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_SYCAMORE_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SYCAMORE_WOOD.get()), has(ModBLocks.STRIPPED_SYCAMORE_WOOD.get()))
                .save(pWriter, "planks_from_sycamore_stripped_wood");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_PLANKS.get(), 4)
                .requires(ModBLocks.SYCAMORE_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_WOOD.get()), has(ModBLocks.SYCAMORE_WOOD.get()))
                .save(pWriter, "planks_from_sycamore_wood");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_SYCAMORE_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SYCAMORE_LOG.get()), has(ModBLocks.STRIPPED_SYCAMORE_LOG.get()))
                .save(pWriter, "planks_from_sycamore_stripped_log");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_PLANKS.get(), 4)
                .requires(ModBLocks.SYCAMORE_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_LOG.get()), has(ModBLocks.SYCAMORE_LOG.get()))
                .save(pWriter, "planks_from_sycamore_log");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.SYCAMORE_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_PLANKS.get()), has(ModBLocks.SYCAMORE_PLANKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.SYCAMORE_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_PLANKS.get()), has(ModBLocks.SYCAMORE_PLANKS.get()))
                .save(pWriter);
        // -------------------------------------------------(WOOD)------------------------------------------------- //

        // -------------------------------------------------(FOODS)------------------------------------------------- //
        // Food smelting recipes Furnace
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_SAUSAGE.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_SAUSAGE.get()), has(ModItems.RAW_SAUSAGE.get())) // Unlock condition
                .save(pWriter, "cooked_sausage_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_BEAR_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_BEAR_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_BEAR_MEAT.get()), has(ModItems.RAW_BEAR_MEAT.get())) // Unlock condition
                .save(pWriter, "cooked_bear_meat_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_BLOOD_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_BLOOD_SAUSAGE.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_BLOOD_SAUSAGE.get()), has(ModItems.RAW_BLOOD_SAUSAGE.get())) // Unlock condition
                .save(pWriter, "cooked_blood_sausage_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_WHITE_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_WHITE_SAUSAGE.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_WHITE_SAUSAGE.get()), has(ModItems.RAW_WHITE_SAUSAGE.get())) // Unlock condition
                .save(pWriter, "cooked_white_sausage_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_BACON.get()), RecipeCategory.FOOD, ModItems.COOKED_BACON.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_BACON.get()), has(ModItems.RAW_BACON.get())) // Unlock condition
                .save(pWriter, "cooked_bacon_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_BOAR_VENISON.get()), RecipeCategory.FOOD, ModItems.COOKED_BOAR_VENISON.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_BOAR_VENISON.get()), has(ModItems.RAW_BOAR_VENISON.get())) // Unlock condition
                .save(pWriter, "cooked_boar_venison_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_CHICKEN_NUGGETS.get()), RecipeCategory.FOOD, ModItems.COOKED_CHICKEN_NUGGETS.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_CHICKEN_NUGGETS.get()), has(ModItems.RAW_CHICKEN_NUGGETS.get())) // Unlock condition
                .save(pWriter, "cooked_chicken_nuggets_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_DEER_VENISON.get()), RecipeCategory.FOOD, ModItems.COOKED_DEER_VENISON.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_DEER_VENISON.get()), has(ModItems.RAW_DEER_VENISON.get())) // Unlock condition
                .save(pWriter, "cooked_deer_venison_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_GOAT_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_GOAT_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_GOAT_MEAT.get()), has(ModItems.RAW_GOAT_MEAT.get())) // Unlock condition
                .save(pWriter, "cooked_goat_meat_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_HARE_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_HARE_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_HARE_MEAT.get()), has(ModItems.RAW_HARE_MEAT.get())) // Unlock condition
                .save(pWriter, "cooked_hare_meat_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_HORSE_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_HORSE_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_HORSE_MEAT.get()), has(ModItems.RAW_HORSE_MEAT.get())) // Unlock condition
                .save(pWriter, "cooked_horse_meat_with_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAW_MAMMOTH_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_MAMMOTH_MEAT.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItems.RAW_MAMMOTH_MEAT.get()), has(ModItems.RAW_MAMMOTH_MEAT.get())) // Unlock condition
                .save(pWriter, "cooked_mammoth_meat_with_furnace");

        // Food Smelting recipes Smoker
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_SAUSAGE.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItems.RAW_SAUSAGE.get()), has(ModItems.RAW_SAUSAGE.get())) // Unlock condition
                .save(pWriter, "cooked_sausage_with_smoker");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_BEAR_MEAT.get()), RecipeCategory.FOOD, ModItems.COOKED_BEAR_MEAT.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItems.RAW_BEAR_MEAT.get()), has(ModItems.RAW_BEAR_MEAT.get())) // Unlock condition
                .save(pWriter, "cooked_bear_meat_with_smoker");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_BLOOD_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_BLOOD_SAUSAGE.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItems.RAW_BLOOD_SAUSAGE.get()), has(ModItems.RAW_BLOOD_SAUSAGE.get())) // Unlock condition
                .save(pWriter, "cooked_blood_sausage_with_smoker");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.RAW_WHITE_SAUSAGE.get()), RecipeCategory.FOOD, ModItems.COOKED_WHITE_SAUSAGE.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItems.RAW_WHITE_SAUSAGE.get()), has(ModItems.RAW_WHITE_SAUSAGE.get())) // Unlock condition
                .save(pWriter, "cooked_white_sausage_with_smoker");


        // -------------------------------------------------(FOODS)------------------------------------------------- //

    }

    /**
     * Creates a stonecutting recipe builder for the given parameters.
     *
     * @param pIngredient The input ingredient for the recipe.
     * @param pCategory   The recipe category.
     * @param pResult     The resulting item.
     * @return A new SingleItemRecipeBuilder for stonecutting.
     */
    public static SingleItemRecipeBuilder stonecutting(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult) {
        return new SingleItemRecipeBuilder(pCategory, RecipeSerializer.STONECUTTER, pIngredient, pResult, 1);
    }

    /**
     * Creates smelting recipes for the provided ingredients and result.
     *
     * @param pFinishedRecipeConsumer The recipe consumer.
     * @param pIngredients            List of input ingredients for the recipe.
     * @param pCategory               The recipe category.
     * @param pResult                 The resulting item.
     * @param pExperience             The experience gained from the recipe.
     * @param pCookingTime            The cooking time.
     * @param pGroup                  The recipe group.
     */
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    /**
     * Creates blasting recipes for the provided ingredients and result.
     *
     * @param pFinishedRecipeConsumer The recipe consumer.
     * @param pIngredients            List of input ingredients for the recipe.
     * @param pCategory               The recipe category.
     * @param pResult                 The resulting item.
     * @param pExperience             The experience gained from the recipe.
     * @param pCookingTime            The cooking time.
     * @param pGroup                  The recipe group.
     */
    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    /**
     * Creates cooking recipes for the provided ingredients and result.
     *
     * @param pFinishedRecipeConsumer The recipe consumer.
     * @param pCookingSerializer      The cooking serializer for the recipe.
     * @param pIngredients            List of input ingredients for the recipe.
     * @param pCategory               The recipe category.
     * @param pResult                 The resulting item.
     * @param pExperience             The experience gained from the recipe.
     * @param pCookingTime            The cooking time.
     * @param pGroup                  The recipe group.
     * @param pRecipeName             The name of the recipe.
     */
    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory,
                            pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, AGoTMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

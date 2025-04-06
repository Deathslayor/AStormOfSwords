package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// Class for generating data pack recipes
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    // Constructor
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput pOutput) {
        super(provider, pOutput);
    }

    // Main method for building recipes
    @Override
    protected void buildRecipes() {
        List<ItemLike> tinSmeltables = List.of(
                ModItems.RAW_TIN.get(),
                ModBLocks.TIN_ORE.get(),
                ModBLocks.DEEPSLATE_TIN_ORE.get());

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

        //Weirwood
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_WOOD.get(), 3)
                .requires(ModBLocks.WEIRWOOD_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_LOG.get()), has(ModBLocks.WEIRWOOD_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_WEIRWOOD_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_WEIRWOOD_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), has(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get()), has(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get()))
                .save(this.output, "planks_from_weirwood_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.WEIRWOOD_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_WOOD.get()), has(ModBLocks.WEIRWOOD_WOOD.get()))
                .save(this.output, "planks_from_weirwood_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_WEIRWOOD_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), has(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()))
                .save(this.output, "planks_from_weirwood_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.WEIRWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.WEIRWOOD_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_LOG.get()), has(ModBLocks.WEIRWOOD_LOG.get()))
                .save(this.output, "planks_from_weirwood_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output, "weirwood_plank_craftingtable");
        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WEIRWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.WEIRWOOD_PLANKS.get()), has(ModBLocks.WEIRWOOD_PLANKS.get()))
                .save(this.output, "weirwood_plank_stick");

        //Sycamore


        this.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_WOOD.get(), 3)
                .requires(ModBLocks.SYCAMORE_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_LOG.get()), has(ModBLocks.SYCAMORE_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_SYCAMORE_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_SYCAMORE_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SYCAMORE_LOG.get()), has(ModBLocks.STRIPPED_SYCAMORE_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_SYCAMORE_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SYCAMORE_WOOD.get()), has(ModBLocks.STRIPPED_SYCAMORE_WOOD.get()))
                .save(this.output, "planks_from_sycamore_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_PLANKS.get(), 4)
                .requires(ModBLocks.SYCAMORE_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_WOOD.get()), has(ModBLocks.SYCAMORE_WOOD.get()))
                .save(this.output, "planks_from_sycamore_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_SYCAMORE_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SYCAMORE_LOG.get()), has(ModBLocks.STRIPPED_SYCAMORE_LOG.get()))
                .save(this.output, "planks_from_sycamore_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.SYCAMORE_PLANKS.get(), 4)
                .requires(ModBLocks.SYCAMORE_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_LOG.get()), has(ModBLocks.SYCAMORE_LOG.get()))
                .save(this.output, "planks_from_sycamore_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.SYCAMORE_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_PLANKS.get()), has(ModBLocks.SYCAMORE_PLANKS.get()))
                .save(this.output, "sycamore_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.SYCAMORE_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.SYCAMORE_PLANKS.get()), has(ModBLocks.SYCAMORE_PLANKS.get()))
                .save(this.output, "sycamore_plank_stick");

        //Sentinel


        this.shapeless(RecipeCategory.MISC, ModBLocks.SENTINEL_WOOD.get(), 3)
                .requires(ModBLocks.SENTINEL_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.SENTINEL_LOG.get()), has(ModBLocks.SENTINEL_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_SENTINEL_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_SENTINEL_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SENTINEL_LOG.get()), has(ModBLocks.STRIPPED_SENTINEL_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.SENTINEL_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_SENTINEL_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SENTINEL_WOOD.get()), has(ModBLocks.STRIPPED_SENTINEL_WOOD.get()))
                .save(this.output, "planks_from_sentinel_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.SENTINEL_PLANKS.get(), 4)
                .requires(ModBLocks.SENTINEL_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.SENTINEL_WOOD.get()), has(ModBLocks.SENTINEL_WOOD.get()))
                .save(this.output, "planks_from_sentinel_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.SENTINEL_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_SENTINEL_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_SENTINEL_LOG.get()), has(ModBLocks.STRIPPED_SENTINEL_LOG.get()))
                .save(this.output, "planks_from_sentinel_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.SENTINEL_PLANKS.get(), 4)
                .requires(ModBLocks.SENTINEL_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.SENTINEL_LOG.get()), has(ModBLocks.SENTINEL_LOG.get()))
                .save(this.output, "planks_from_sentinel_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.SENTINEL_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.SENTINEL_PLANKS.get()), has(ModBLocks.SENTINEL_PLANKS.get()))
                .save(this.output, "sentinel_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.SENTINEL_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.SENTINEL_PLANKS.get()), has(ModBLocks.SENTINEL_PLANKS.get()))
                .save(this.output, "sentinel_plank_stick");

        //Pine

        this.shapeless(RecipeCategory.MISC, ModBLocks.PINE_WOOD.get(), 3)
                .requires(ModBLocks.PINE_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.PINE_LOG.get()), has(ModBLocks.PINE_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_PINE_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_PINE_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_PINE_LOG.get()), has(ModBLocks.STRIPPED_PINE_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.PINE_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_PINE_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_PINE_WOOD.get()), has(ModBLocks.STRIPPED_PINE_WOOD.get()))
                .save(this.output, "planks_from_pine_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.PINE_PLANKS.get(), 4)
                .requires(ModBLocks.PINE_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.PINE_WOOD.get()), has(ModBLocks.PINE_WOOD.get()))
                .save(this.output, "planks_from_pine_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.PINE_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_PINE_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_PINE_LOG.get()), has(ModBLocks.STRIPPED_PINE_LOG.get()))
                .save(this.output, "planks_from_pine_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.PINE_PLANKS.get(), 4)
                .requires(ModBLocks.PINE_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.PINE_LOG.get()), has(ModBLocks.PINE_LOG.get()))
                .save(this.output, "planks_from_pine_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.PINE_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.PINE_PLANKS.get()), has(ModBLocks.PINE_PLANKS.get()))
                .save(this.output, "pine_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.PINE_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.PINE_PLANKS.get()), has(ModBLocks.PINE_PLANKS.get()))
                .save(this.output, "pine_plank_stick");

        //Ironwood

        this.shapeless(RecipeCategory.MISC, ModBLocks.IRONWOOD_WOOD.get(), 3)
                .requires(ModBLocks.IRONWOOD_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.IRONWOOD_LOG.get()), has(ModBLocks.IRONWOOD_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_IRONWOOD_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_IRONWOOD_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_IRONWOOD_LOG.get()), has(ModBLocks.STRIPPED_IRONWOOD_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.IRONWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_IRONWOOD_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_PINE_WOOD.get()), has(ModBLocks.STRIPPED_IRONWOOD_WOOD.get()))
                .save(this.output, "planks_from_ironwood_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.IRONWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.IRONWOOD_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.IRONWOOD_WOOD.get()), has(ModBLocks.IRONWOOD_WOOD.get()))
                .save(this.output, "planks_from_ironwood_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.IRONWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_IRONWOOD_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_IRONWOOD_LOG.get()), has(ModBLocks.STRIPPED_IRONWOOD_LOG.get()))
                .save(this.output, "planks_from_ironwood_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.IRONWOOD_PLANKS.get(), 4)
                .requires(ModBLocks.IRONWOOD_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.IRONWOOD_LOG.get()), has(ModBLocks.IRONWOOD_LOG.get()))
                .save(this.output, "planks_from_ironwood_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.IRONWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.IRONWOOD_PLANKS.get()), has(ModBLocks.IRONWOOD_PLANKS.get()))
                .save(this.output, "ironwood_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.IRONWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.IRONWOOD_PLANKS.get()), has(ModBLocks.IRONWOOD_PLANKS.get()))
                .save(this.output, "ironwood_plank_stick");

        //Hawhtorn

        this.shapeless(RecipeCategory.MISC, ModBLocks.HAWTHORN_WOOD.get(), 3)
                .requires(ModBLocks.HAWTHORN_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.HAWTHORN_LOG.get()), has(ModBLocks.HAWTHORN_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_HAWTHORN_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_HAWTHORN_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_HAWTHORN_LOG.get()), has(ModBLocks.STRIPPED_HAWTHORN_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.HAWTHORN_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_HAWTHORN_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_PINE_WOOD.get()), has(ModBLocks.STRIPPED_HAWTHORN_WOOD.get()))
                .save(this.output, "planks_from_hawthorn_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.HAWTHORN_PLANKS.get(), 4)
                .requires(ModBLocks.HAWTHORN_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.HAWTHORN_WOOD.get()), has(ModBLocks.HAWTHORN_WOOD.get()))
                .save(this.output, "planks_from_hawthorn_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.HAWTHORN_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_HAWTHORN_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_HAWTHORN_LOG.get()), has(ModBLocks.STRIPPED_HAWTHORN_LOG.get()))
                .save(this.output, "planks_from_hawthorn_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.HAWTHORN_PLANKS.get(), 4)
                .requires(ModBLocks.HAWTHORN_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.HAWTHORN_LOG.get()), has(ModBLocks.HAWTHORN_LOG.get()))
                .save(this.output, "planks_from_hawthorn_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.HAWTHORN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.HAWTHORN_PLANKS.get()), has(ModBLocks.HAWTHORN_PLANKS.get()))
                .save(this.output, "hawthorn_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.HAWTHORN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.HAWTHORN_PLANKS.get()), has(ModBLocks.HAWTHORN_PLANKS.get()))
                .save(this.output, "hawthorn_plank_stick");

        //Chestnut

        this.shapeless(RecipeCategory.MISC, ModBLocks.CHESTNUT_WOOD.get(), 3)
                .requires(ModBLocks.CHESTNUT_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.CHESTNUT_LOG.get()), has(ModBLocks.CHESTNUT_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_CHESTNUT_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_CHESTNUT_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CHESTNUT_LOG.get()), has(ModBLocks.STRIPPED_CHESTNUT_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.CHESTNUT_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_CHESTNUT_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_PINE_WOOD.get()), has(ModBLocks.STRIPPED_CHESTNUT_WOOD.get()))
                .save(this.output, "planks_from_chestnut_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.CHESTNUT_PLANKS.get(), 4)
                .requires(ModBLocks.CHESTNUT_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.CHESTNUT_WOOD.get()), has(ModBLocks.CHESTNUT_WOOD.get()))
                .save(this.output, "planks_from_chestnut_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.CHESTNUT_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_CHESTNUT_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CHESTNUT_LOG.get()), has(ModBLocks.STRIPPED_CHESTNUT_LOG.get()))
                .save(this.output, "planks_from_chestnut_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.CHESTNUT_PLANKS.get(), 4)
                .requires(ModBLocks.CHESTNUT_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.CHESTNUT_LOG.get()), has(ModBLocks.CHESTNUT_LOG.get()))
                .save(this.output, "planks_from_chestnut_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.CHESTNUT_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHESTNUT_PLANKS.get()), has(ModBLocks.CHESTNUT_PLANKS.get()))
                .save(this.output, "chestnut_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.CHESTNUT_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CHESTNUT_PLANKS.get()), has(ModBLocks.CHESTNUT_PLANKS.get()))
                .save(this.output, "chestnut_plank_stick");

        //Cedar

        this.shapeless(RecipeCategory.MISC, ModBLocks.CEDAR_WOOD.get(), 3)
                .requires(ModBLocks.CEDAR_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.CEDAR_LOG.get()), has(ModBLocks.CEDAR_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_CEDAR_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_CEDAR_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CEDAR_LOG.get()), has(ModBLocks.STRIPPED_CEDAR_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.CEDAR_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_CEDAR_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_PINE_WOOD.get()), has(ModBLocks.STRIPPED_CEDAR_WOOD.get()))
                .save(this.output, "planks_from_cedar_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.CEDAR_PLANKS.get(), 4)
                .requires(ModBLocks.CEDAR_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.CEDAR_WOOD.get()), has(ModBLocks.CEDAR_WOOD.get()))
                .save(this.output, "planks_from_cedar_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.CEDAR_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_CEDAR_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_CEDAR_LOG.get()), has(ModBLocks.STRIPPED_CEDAR_LOG.get()))
                .save(this.output, "planks_from_cedar_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.CEDAR_PLANKS.get(), 4)
                .requires(ModBLocks.CEDAR_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.CEDAR_LOG.get()), has(ModBLocks.CEDAR_LOG.get()))
                .save(this.output, "planks_from_cedar_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.CEDAR_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CEDAR_PLANKS.get()), has(ModBLocks.CEDAR_PLANKS.get()))
                .save(this.output, "cedar_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.CEDAR_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.CEDAR_PLANKS.get()), has(ModBLocks.CEDAR_PLANKS.get()))
                .save(this.output, "cedar_plank_stick");

        //beech

        this.shapeless(RecipeCategory.MISC, ModBLocks.BEECH_WOOD.get(), 3)
                .requires(ModBLocks.BEECH_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.BEECH_LOG.get()), has(ModBLocks.BEECH_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_BEECH_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_BEECH_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_BEECH_LOG.get()), has(ModBLocks.STRIPPED_BEECH_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.BEECH_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_BEECH_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_BEECH_WOOD.get()), has(ModBLocks.STRIPPED_BEECH_WOOD.get()))
                .save(this.output, "planks_from_beech_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.BEECH_PLANKS.get(), 4)
                .requires(ModBLocks.BEECH_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.BEECH_WOOD.get()), has(ModBLocks.BEECH_WOOD.get()))
                .save(this.output, "planks_from_beech_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.BEECH_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_BEECH_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_BEECH_LOG.get()), has(ModBLocks.STRIPPED_BEECH_LOG.get()))
                .save(this.output, "planks_from_beech_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.BEECH_PLANKS.get(), 4)
                .requires(ModBLocks.BEECH_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.BEECH_LOG.get()), has(ModBLocks.BEECH_LOG.get()))
                .save(this.output, "planks_from_beech_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.BEECH_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.BEECH_PLANKS.get()), has(ModBLocks.BEECH_PLANKS.get()))
                .save(this.output, "beech_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.BEECH_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.BEECH_PLANKS.get()), has(ModBLocks.BEECH_PLANKS.get()))
                .save(this.output, "beech_plank_stick");

        //ash

        this.shapeless(RecipeCategory.MISC, ModBLocks.ASH_WOOD.get(), 3)
                .requires(ModBLocks.ASH_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.ASH_LOG.get()), has(ModBLocks.ASH_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_ASH_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_ASH_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ASH_LOG.get()), has(ModBLocks.STRIPPED_ASH_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.ASH_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_ASH_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ASH_WOOD.get()), has(ModBLocks.STRIPPED_ASH_WOOD.get()))
                .save(this.output, "planks_from_ash_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ASH_PLANKS.get(), 4)
                .requires(ModBLocks.ASH_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.ASH_WOOD.get()), has(ModBLocks.ASH_WOOD.get()))
                .save(this.output, "planks_from_ash_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ASH_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_ASH_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ASH_LOG.get()), has(ModBLocks.STRIPPED_ASH_LOG.get()))
                .save(this.output, "planks_from_ash_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ASH_PLANKS.get(), 4)
                .requires(ModBLocks.ASH_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.ASH_LOG.get()), has(ModBLocks.ASH_LOG.get()))
                .save(this.output, "planks_from_ash_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.ASH_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ASH_PLANKS.get()), has(ModBLocks.ASH_PLANKS.get()))
                .save(this.output, "ash_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.ASH_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ASH_PLANKS.get()), has(ModBLocks.ASH_PLANKS.get()))
                .save(this.output, "ash_plank_stick");

        //Blackbark

        this.shapeless(RecipeCategory.MISC, ModBLocks.BLACKBARK_WOOD.get(), 3)
                .requires(ModBLocks.BLACKBARK_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.BLACKBARK_LOG.get()), has(ModBLocks.BLACKBARK_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_BLACKBARK_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_BLACKBARK_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_BLACKBARK_LOG.get()), has(ModBLocks.STRIPPED_BLACKBARK_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.BLACKBARK_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_BLACKBARK_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_BLACKBARK_WOOD.get()), has(ModBLocks.STRIPPED_BLACKBARK_WOOD.get()))
                .save(this.output, "planks_from_blackbark_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.BLACKBARK_PLANKS.get(), 4)
                .requires(ModBLocks.BLACKBARK_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.BLACKBARK_WOOD.get()), has(ModBLocks.BLACKBARK_WOOD.get()))
                .save(this.output, "planks_from_blackbark_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.BLACKBARK_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_BLACKBARK_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_BLACKBARK_LOG.get()), has(ModBLocks.STRIPPED_BLACKBARK_LOG.get()))
                .save(this.output, "planks_from_blackbark_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.BLACKBARK_PLANKS.get(), 4)
                .requires(ModBLocks.BLACKBARK_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.BLACKBARK_LOG.get()), has(ModBLocks.BLACKBARK_LOG.get()))
                .save(this.output, "planks_from_blackbark_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.BLACKBARK_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.BLACKBARK_PLANKS.get()), has(ModBLocks.BLACKBARK_PLANKS.get()))
                .save(this.output, "blackbark_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.BLACKBARK_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.BLACKBARK_PLANKS.get()), has(ModBLocks.BLACKBARK_PLANKS.get()))
                .save(this.output, "blackbark_plank_stick");

        //Aspen

        this.shapeless(RecipeCategory.MISC, ModBLocks.ASPEN_WOOD.get(), 3)
                .requires(ModBLocks.ASPEN_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.ASPEN_LOG.get()), has(ModBLocks.ASPEN_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_ASPEN_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_ASPEN_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ASPEN_LOG.get()), has(ModBLocks.STRIPPED_ASPEN_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.ASPEN_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_ASPEN_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ASPEN_WOOD.get()), has(ModBLocks.STRIPPED_ASPEN_WOOD.get()))
                .save(this.output, "planks_from_aspen_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ASPEN_PLANKS.get(), 4)
                .requires(ModBLocks.ASPEN_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.ASPEN_WOOD.get()), has(ModBLocks.ASPEN_WOOD.get()))
                .save(this.output, "planks_from_aspen_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ASPEN_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_ASPEN_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ASPEN_LOG.get()), has(ModBLocks.STRIPPED_ASPEN_LOG.get()))
                .save(this.output, "planks_from_aspen_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ASPEN_PLANKS.get(), 4)
                .requires(ModBLocks.ASPEN_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.ASPEN_LOG.get()), has(ModBLocks.ASPEN_LOG.get()))
                .save(this.output, "planks_from_aspen_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.ASPEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ASPEN_PLANKS.get()), has(ModBLocks.ASPEN_PLANKS.get()))
                .save(this.output, "aspen_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.ASPEN_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ASPEN_PLANKS.get()), has(ModBLocks.ASPEN_PLANKS.get()))
                .save(this.output, "aspen_plank_stick");

        //Alder

        this.shapeless(RecipeCategory.MISC, ModBLocks.ALDER_WOOD.get(), 3)
                .requires(ModBLocks.ALDER_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.ALDER_LOG.get()), has(ModBLocks.ALDER_LOG.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModBLocks.STRIPPED_ALDER_WOOD.get(), 3)
                .requires(ModBLocks.STRIPPED_ALDER_LOG.get(), 4)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ALDER_LOG.get()), has(ModBLocks.STRIPPED_ALDER_LOG.get()))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModBLocks.ALDER_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_ALDER_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ALDER_WOOD.get()), has(ModBLocks.STRIPPED_ALDER_WOOD.get()))
                .save(this.output, "planks_from_alder_stripped_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ALDER_PLANKS.get(), 4)
                .requires(ModBLocks.ALDER_WOOD.get(), 1)
                .unlockedBy(getHasName(ModBLocks.ALDER_WOOD.get()), has(ModBLocks.ALDER_WOOD.get()))
                .save(this.output, "planks_from_alder_wood");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ALDER_PLANKS.get(), 4)
                .requires(ModBLocks.STRIPPED_ALDER_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.STRIPPED_ALDER_LOG.get()), has(ModBLocks.STRIPPED_ALDER_LOG.get()))
                .save(this.output, "planks_from_alder_stripped_log");
        this.shapeless(RecipeCategory.MISC, ModBLocks.ALDER_PLANKS.get(), 4)
                .requires(ModBLocks.ALDER_LOG.get(), 1)
                .unlockedBy(getHasName(ModBLocks.ALDER_LOG.get()), has(ModBLocks.ALDER_LOG.get()))
                .save(this.output, "planks_from_alder_log");

        this.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("BB")
                .pattern("BB")
                .pattern("  ")
                .define('B', ModBLocks.ALDER_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ALDER_PLANKS.get()), has(ModBLocks.ALDER_PLANKS.get()))
                .save(this.output, "alder_plank_craftingtable");

        this.shaped(RecipeCategory.MISC, Items.STICK)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.ALDER_PLANKS.get())
                .unlockedBy(getHasName(ModBLocks.ALDER_PLANKS.get()), has(ModBLocks.ALDER_PLANKS.get()))
                .save(this.output, "alder_plank_stick");
        // -------------------------------------------------(FLOWERS)------------------------------------------------- //
        this.shaped(RecipeCategory.MISC, ModBLocks.RED_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.ROSE.get())
                .unlockedBy(getHasName(ModBLocks.ROSE.get()), has(ModBLocks.ROSE.get()))
                .save(this.output, "rose_bush");

        this.shaped(RecipeCategory.MISC, ModBLocks.BLUE_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.BLUE_ROSE.get())
                .unlockedBy(getHasName(ModBLocks.BLUE_ROSE.get()), has(ModBLocks.BLUE_ROSE.get()))
                .save(this.output, "blue_rose_bush");

        this.shaped(RecipeCategory.MISC, ModBLocks.WHITE_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WHITE_ROSE.get())
                .unlockedBy(getHasName(ModBLocks.WHITE_ROSE.get()), has(ModBLocks.WHITE_ROSE.get()))
                .save(this.output, "white_rose_bush");

        this.shaped(RecipeCategory.MISC, ModBLocks.WINTER_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.WINTER_ROSE.get())
                .unlockedBy(getHasName(ModBLocks.WINTER_ROSE.get()), has(ModBLocks.WINTER_ROSE.get()))
                .save(this.output, "winter_rose_bush");

        this.shaped(RecipeCategory.MISC, ModBLocks.DUSKY_ROSE_BUSH)
                .pattern("B")
                .pattern("B")
                .pattern(" ")
                .define('B', ModBLocks.DUSKY_ROSE.get())
                .unlockedBy(getHasName(ModBLocks.DUSKY_ROSE.get()), has(ModBLocks.DUSKY_ROSE.get()))
                .save(this.output, "dusky_rose_bush");


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


        // -------------------------------------------------(FOODS)------------------------------------------------- //

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

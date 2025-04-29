// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// A utility class for creating custom creative mode tabs
public class ModCreativeNaturalBlocks {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_NATURAL_BLOCKS = CREATIVE_MODE_TAB.register("agot_tab_natural_blocks", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBLocks.WEIRWOOD_SAPLING.get())) // Icon for the creative mode tab
            .title(Component.translatable("creativetab.agot_tab_natural_blocks")) // Title for the creative mode tab
            .displayItems((pParameters, pOutput) -> {

                // Roses
                pOutput.accept(ModBLocks.ROSE.get());
                pOutput.accept(ModBLocks.RED_ROSE_BUSH.get());
                pOutput.accept(ModBLocks.WHITE_ROSE.get());
                pOutput.accept(ModBLocks.WHITE_ROSE_BUSH.get());
                pOutput.accept(ModBLocks.DUSKY_ROSE.get());
                pOutput.accept(ModBLocks.DUSKY_ROSE_BUSH.get());
                pOutput.accept(ModBLocks.WINTER_ROSE.get());
                pOutput.accept(ModBLocks.WINTER_ROSE_BUSH.get());
                pOutput.accept(ModBLocks.BLUE_ROSE.get());
                pOutput.accept(ModBLocks.BLUE_ROSE_BUSH.get());
                pOutput.accept(ModBLocks.BLOODBLOOM.get());

// Other Wildflowers
                pOutput.accept(ModBLocks.WILD_RADISH.get());
                pOutput.accept(ModBLocks.THORN_BUSH.get());
                pOutput.accept(ModBLocks.THISTLE.get());
                pOutput.accept(ModBLocks.TANSY.get());
                pOutput.accept(ModBLocks.SPICEFLOWER.get());
                pOutput.accept(ModBLocks.SEDGE.get());
                pOutput.accept(ModBLocks.LAVENDER.get());
                pOutput.accept(ModBLocks.LADYS_LACE.get());
                pOutput.accept(ModBLocks.GORSE.get());
                pOutput.accept(ModBLocks.GOLDENROD.get());
                pOutput.accept(ModBLocks.GOLDENCUP.get());

// Exotic Flowers
                pOutput.accept(ModBLocks.SAFFRON_CROCUS.get());
                pOutput.accept(ModBLocks.POISON_KISSES.get());
                pOutput.accept(ModBLocks.PENNYROYAL.get());
                pOutput.accept(ModBLocks.OPIUM_POPPY_WILD.get());
                pOutput.accept(ModBLocks.NIGHTSHADE.get());
                pOutput.accept(ModBLocks.MOONBLOOM.get());
                pOutput.accept(ModBLocks.LUNGWORT.get());
                pOutput.accept(ModBLocks.LIVERWORT.get());
                pOutput.accept(ModBLocks.GINGER.get());
                pOutput.accept(ModBLocks.GILLYFLOWER.get());

// Rare and Magical Flowers
                pOutput.accept(ModBLocks.FROSTFIRE.get());
                pOutput.accept(ModBLocks.FORGET_ME_NOT.get());
                pOutput.accept(ModBLocks.EVENING_STAR.get());
                pOutput.accept(ModBLocks.DRAGONS_BREATH.get());
                pOutput.accept(ModBLocks.COLDSNAP.get());
                pOutput.accept(ModBLocks.BLACK_LOTUS.get());

// Miscellaneous
                pOutput.accept(ModBLocks.GOATHEAD.get());



                //Wood
                pOutput.accept(ModBLocks.WEIRWOOD_LOG.get());
                pOutput.accept(ModBLocks.WEIRWOOD_FACE_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_WEIRWOOD_LOG.get());
                pOutput.accept(ModBLocks.WEIRWOOD_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get());
                pOutput.accept(ModBLocks.WEIRWOOD_PLANKS.get());

                pOutput.accept(ModBLocks.WEIRWOOD_LEAVES.get());
                pOutput.accept(ModBLocks.WEIRWOOD_SAPLING.get());

                pOutput.accept(ModBLocks.SYCAMORE_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_SYCAMORE_LOG.get());
                pOutput.accept(ModBLocks.SYCAMORE_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_SYCAMORE_WOOD.get());
                pOutput.accept(ModBLocks.SYCAMORE_PLANKS.get());

                pOutput.accept(ModBLocks.SYCAMORE_LEAVES.get());
                pOutput.accept(ModBLocks.SYCAMORE_SAPLING.get());

                pOutput.accept(ModBLocks.SENTINEL_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_SENTINEL_LOG.get());
                pOutput.accept(ModBLocks.SENTINEL_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_SENTINEL_WOOD.get());
                pOutput.accept(ModBLocks.SENTINEL_PLANKS.get());

                pOutput.accept(ModBLocks.SENTINEL_LEAVES.get());
                pOutput.accept(ModBLocks.SENTINEL_SAPLING.get());

                pOutput.accept(ModBLocks.PINE_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_PINE_LOG.get());
                pOutput.accept(ModBLocks.PINE_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_PINE_WOOD.get());
                pOutput.accept(ModBLocks.PINE_PLANKS.get());

                pOutput.accept(ModBLocks.PINE_LEAVES.get());
                pOutput.accept(ModBLocks.PINE_SAPLING.get());

                pOutput.accept(ModBLocks.IRONWOOD_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_IRONWOOD_LOG.get());
                pOutput.accept(ModBLocks.IRONWOOD_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_IRONWOOD_WOOD.get());
                pOutput.accept(ModBLocks.IRONWOOD_PLANKS.get());

                pOutput.accept(ModBLocks.IRONWOOD_LEAVES.get());
                pOutput.accept(ModBLocks.IRONWOOD_SAPLING.get());

                pOutput.accept(ModBLocks.HAWTHORN_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_HAWTHORN_LOG.get());
                pOutput.accept(ModBLocks.HAWTHORN_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_HAWTHORN_WOOD.get());
                pOutput.accept(ModBLocks.HAWTHORN_PLANKS.get());

                pOutput.accept(ModBLocks.HAWTHORN_LEAVES.get());
                pOutput.accept(ModBLocks.HAWTHORN_SAPLING.get());

                pOutput.accept(ModBLocks.CHESTNUT_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_CHESTNUT_LOG.get());
                pOutput.accept(ModBLocks.CHESTNUT_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_CHESTNUT_WOOD.get());
                pOutput.accept(ModBLocks.CHESTNUT_PLANKS.get());

                pOutput.accept(ModBLocks.CHESTNUT_LEAVES.get());
                pOutput.accept(ModBLocks.CHESTNUT_SAPLING.get());

                pOutput.accept(ModBLocks.CEDAR_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_CEDAR_LOG.get());
                pOutput.accept(ModBLocks.CEDAR_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_CEDAR_WOOD.get());
                pOutput.accept(ModBLocks.CEDAR_PLANKS.get());

                pOutput.accept(ModBLocks.CEDAR_LEAVES.get());
                pOutput.accept(ModBLocks.CEDAR_SAPLING.get());

                pOutput.accept(ModBLocks.BEECH_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_BEECH_LOG.get());
                pOutput.accept(ModBLocks.BEECH_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_BEECH_WOOD.get());
                pOutput.accept(ModBLocks.BEECH_PLANKS.get());

                pOutput.accept(ModBLocks.BEECH_LEAVES.get());
                pOutput.accept(ModBLocks.BEECH_SAPLING.get());

                pOutput.accept(ModBLocks.ASH_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_ASH_LOG.get());
                pOutput.accept(ModBLocks.ASH_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_ASH_WOOD.get());
                pOutput.accept(ModBLocks.ASH_PLANKS.get());

                pOutput.accept(ModBLocks.ASH_LEAVES.get());
                pOutput.accept(ModBLocks.ASH_SAPLING.get());

                pOutput.accept(ModBLocks.BLACKBARK_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_BLACKBARK_LOG.get());
                pOutput.accept(ModBLocks.BLACKBARK_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_BLACKBARK_WOOD.get());
                pOutput.accept(ModBLocks.BLACKBARK_PLANKS.get());

                pOutput.accept(ModBLocks.BLACKBARK_LEAVES.get());
                pOutput.accept(ModBLocks.BLACKBARK_SAPLING.get());

                pOutput.accept(ModBLocks.ASPEN_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_ASPEN_LOG.get());
                pOutput.accept(ModBLocks.ASPEN_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_ASPEN_WOOD.get());
                pOutput.accept(ModBLocks.ASPEN_PLANKS.get());

                pOutput.accept(ModBLocks.ASPEN_LEAVES.get());
                pOutput.accept(ModBLocks.ASPEN_SAPLING.get());

                pOutput.accept(ModBLocks.ALDER_LOG.get());
                pOutput.accept(ModBLocks.STRIPPED_ALDER_LOG.get());
                pOutput.accept(ModBLocks.ALDER_WOOD.get());
                pOutput.accept(ModBLocks.STRIPPED_ALDER_WOOD.get());
                pOutput.accept(ModBLocks.ALDER_PLANKS.get());

                pOutput.accept(ModBLocks.ALDER_LEAVES.get());
                pOutput.accept(ModBLocks.ALDER_SAPLING.get());

                //Ores
                pOutput.accept(ModBLocks.TIN_ORE.get());
                pOutput.accept(ModBLocks.DEEPSLATE_TIN_ORE.get());
                pOutput.accept(ModBLocks.RAW_TIN_BLOCK.get());
                pOutput.accept(ModBLocks.DIAMONDS_ORE.get());
                pOutput.accept(ModBLocks.DIAMONDS_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.BLOODSTONE_ORE.get());
                pOutput.accept(ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.CHALCEDONY_ORE.get());
                pOutput.accept(ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.AMBER_ORE.get());
                pOutput.accept(ModBLocks.AMBER_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.AMETHYST_ORE.get());
                pOutput.accept(ModBLocks.AMETHYST_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.CARNELIAN_ORE.get());
                pOutput.accept(ModBLocks.CARNELIAN_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.GARNET_ORE.get());
                pOutput.accept(ModBLocks.GARNET_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.JADE_ORE.get());
                pOutput.accept(ModBLocks.JADE_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.JASPER_ORE.get());
                pOutput.accept(ModBLocks.JASPER_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.MALACHITE_ORE.get());
                pOutput.accept(ModBLocks.MALACHITE_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.RUBY_ORE.get());
                pOutput.accept(ModBLocks.RUBY_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.ONYX_ORE.get());
                pOutput.accept(ModBLocks.ONYX_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.OPAL_ORE.get());
                pOutput.accept(ModBLocks.OPAL_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.SAPPHIRE_ORE.get());
                pOutput.accept(ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.MOONSTONE_ORE.get());
                pOutput.accept(ModBLocks.MOONSTONE_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.TIGERS_EYE_ORE.get());
                pOutput.accept(ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get());
                pOutput.accept(ModBLocks.TOPAZ_ORE.get());
                pOutput.accept(ModBLocks.TOPAZ_DEEPSLATE_ORE.get());






            }).build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

package net.darkflameproduction.agotmod.item.creativetabs;

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

public class ModCreativeNaturalBlocks {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Array of wood types for consistency
    private static final String[] WOOD_TYPES = {
            "sycamore",
            "pine",
            "ash",
            "beech",
            "cedar",
            "chestnut",
            "hawthorn",
            "ironwood",
            "sentinel",
            "blackbark",
            "aspen",
            "black_cherry",
            "black_olive",
            "crabapple",
            "olive",
            "white_cherry",
            "red_cherry",
            "fir",
            "willow",
            "wormtree",
            "alder"
    };

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_NATURAL_BLOCKS =
            CREATIVE_MODE_TAB.register("agot_tab_natural_blocks",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModBLocks.WEIRWOOD_SAPLING.get()))
                            .title(Component.translatable("creativetab.agot_tab_natural_blocks"))
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
                                pOutput.accept(ModBLocks.GHOST_GRASS_BLOCK.get());

                                // Weirwood
                                pOutput.accept(ModBLocks.WEIRWOOD_LOG.get());
                                pOutput.accept(ModBLocks.WEIRWOOD_FACE_LOG.get());
                                pOutput.accept(ModBLocks.STRIPPED_WEIRWOOD_LOG.get());
                                pOutput.accept(ModBLocks.WEIRWOOD_WOOD.get());
                                pOutput.accept(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get());
                                pOutput.accept(ModBLocks.WEIRWOOD_PLANKS.get());
                                pOutput.accept(ModBLocks.WEIRWOOD_LEAVES.get());
                                pOutput.accept(ModBLocks.WEIRWOOD_SAPLING.get());

                                // All other wood types using maps
                                for (String woodType : WOOD_TYPES) {
                                    try {
                                        // Add logs, woods, planks, leaves and saplings for each wood type
                                        if (ModBLocks.LOGS.containsKey(woodType)) {
                                            pOutput.accept(ModBLocks.LOGS.get(woodType).get());
                                        }

                                        if (ModBLocks.STRIPPED_LOGS.containsKey(woodType)) {
                                            pOutput.accept(ModBLocks.STRIPPED_LOGS.get(woodType).get());
                                        }

                                        if (ModBLocks.WOODS.containsKey(woodType)) {
                                            pOutput.accept(ModBLocks.WOODS.get(woodType).get());
                                        }

                                        if (ModBLocks.STRIPPED_WOODS.containsKey(woodType)) {
                                            pOutput.accept(ModBLocks.STRIPPED_WOODS.get(woodType).get());
                                        }

                                        if (ModBLocks.PLANKS.containsKey(woodType)) {
                                            pOutput.accept(ModBLocks.PLANKS.get(woodType).get());
                                        }

                                        if (ModBLocks.LEAVES.containsKey(woodType)) {
                                            pOutput.accept(ModBLocks.LEAVES.get(woodType).get());
                                        }

                                        if (ModBLocks.SAPLINGS.containsKey(woodType)) {
                                            pOutput.accept(ModBLocks.SAPLINGS.get(woodType).get());
                                        }

                                    } catch (Exception e) {
                                        AGoTMod.LOGGER.error("Error adding wood type to natural blocks tab: " + woodType, e);
                                    }
                                }

                                // Ores
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
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
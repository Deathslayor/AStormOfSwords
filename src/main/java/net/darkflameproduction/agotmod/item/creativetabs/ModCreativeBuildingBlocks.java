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

public class ModCreativeBuildingBlocks {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Array of wood types (for iterating through maps)
    private static final String[] WOOD_TYPES = {
            "sycamore",
            "sentinel",
            "pine",
            "ironwood",
            "hawthorn",
            "chestnut",
            "cedar",
            "beech",
            "ash",
            "blackbark",
            "aspen",
            "alder"
    };

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_BUILDING_BLOCKS = CREATIVE_MODE_TAB.register("agot_tab_building_blocks",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBLocks.DARK_STONE_BRICK.get()))
                    .title(Component.translatable("creativetab.agot_tab_building_blocks"))
                    .displayItems((pParameters, pOutput) -> {

                        //Wood
                        // Weirwood items (direct field access)
                        pOutput.accept(ModBLocks.WEIRWOOD_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_WEIRWOOD_LOG.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_PLANKS.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_FACE_LOG.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_STAIRS.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_SLAB.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_BUTTON.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_FENCE.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_WALL.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_DOOR.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_TRAPDOOR.get());
                        pOutput.accept(ModItems.WEIRWOOD_SIGN.get());
                        pOutput.accept(ModItems.WEIRWOOD_HANGING_SIGN.get());

                        // Handle all other wood types with map-based access
                        for (String woodType : WOOD_TYPES) {
                            try {
                                // Block fields from maps
                                pOutput.accept(ModBLocks.LOGS.get(woodType).get());
                                pOutput.accept(ModBLocks.STRIPPED_LOGS.get(woodType).get());
                                pOutput.accept(ModBLocks.WOODS.get(woodType).get());
                                pOutput.accept(ModBLocks.STRIPPED_WOODS.get(woodType).get());
                                pOutput.accept(ModBLocks.PLANKS.get(woodType).get());
                                pOutput.accept(ModBLocks.STAIRS.get(woodType).get());
                                pOutput.accept(ModBLocks.SLABS.get(woodType).get());
                                pOutput.accept(ModBLocks.BUTTONS.get(woodType).get());
                                pOutput.accept(ModBLocks.PRESSURE_PLATES.get(woodType).get());
                                pOutput.accept(ModBLocks.FENCES.get(woodType).get());
                                pOutput.accept(ModBLocks.FENCE_GATES.get(woodType).get());
                                pOutput.accept(ModBLocks.WALLS.get(woodType).get());
                                pOutput.accept(ModBLocks.DOORS.get(woodType).get());
                                pOutput.accept(ModBLocks.TRAPDOORS.get(woodType).get());

                                // Item maps
                                pOutput.accept(ModItems.SIGN_ITEMS.get(woodType).get());
                                pOutput.accept(ModItems.HANGING_SIGN_ITEMS.get(woodType).get());

                            } catch (Exception e) {
                                AGoTMod.LOGGER.error("Error adding wood items to creative tab: " + woodType, e);
                            }
                        }

                        // Bricks
                        pOutput.accept(ModBLocks.STONE_BRICK_BUT_COOLER.get());
                        pOutput.accept(ModBLocks.DARK_STONE_BRICK.get());
                        // Kings LANDING BRICKS
                        pOutput.accept(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());

                        // Stone variants
                        for (int i = 1; i <= 38; i++) {
                            if (i == 5 || i == 15 || i == 19 || i == 23) continue;

                            ModBLocks.BlockSet blockSet = ModBLocks.STONE_VARIANTS.get(i);
                            if (blockSet != null) {
                                pOutput.accept(blockSet.base().get());
                                pOutput.accept(blockSet.stairs().get());
                                pOutput.accept(blockSet.slab().get());
                                pOutput.accept(blockSet.wall().get());
                            }
                        }

                        // Keep the rest of your code as is...
                        for (int i = 1; i <= 38; i++) {
                            if (i == 15 || i == 23) continue;

                            ModBLocks.BlockSet blockSet = ModBLocks.SSTONE_VARIANTS.get(i);
                            if (blockSet != null) {
                                pOutput.accept(blockSet.base().get());
                                pOutput.accept(blockSet.stairs().get());
                                pOutput.accept(blockSet.slab().get());
                                pOutput.accept(blockSet.wall().get());
                            }
                        }

                        // Continue with all your other variant loops and blocks...

                        // INGOTS = Blocks
                        pOutput.accept(ModBLocks.QUAGMIRE.get());

                        // TIN
                        pOutput.accept(ModBLocks.TIN_ORE.get());
                        pOutput.accept(ModBLocks.RAW_TIN_BLOCK.get());
                        pOutput.accept(ModBLocks.DEEPSLATE_TIN_ORE.get());
                        pOutput.accept(ModBLocks.TIN_BLOCK.get());

                        // BRONZE
                        pOutput.accept(ModBLocks.BRONZE_BLOCK.get());

                        // GEMSTONES
                        pOutput.accept(ModBLocks.YELLOW_DIAMOND_BLOCK.get());
                        pOutput.accept(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get());
                        pOutput.accept(ModBLocks.BLOODSTONE_BLOCK.get());
                        pOutput.accept(ModBLocks.BLACK_DIAMOND_BLOCK.get());
                        pOutput.accept(ModBLocks.CHALCEDONY_BLOCK.get());
                        pOutput.accept(ModBLocks.AMBER_BLOCK.get());
                        pOutput.accept(ModBLocks.AMETHYST_BLOCK.get());
                        pOutput.accept(ModBLocks.CARNELIAN_BLOCK.get());
                        pOutput.accept(ModBLocks.GARNET_BLOCK.get());
                        pOutput.accept(ModBLocks.JADE_BLOCK.get());
                        pOutput.accept(ModBLocks.JASPER_BLOCK.get());
                        pOutput.accept(ModBLocks.MALACHITE_BLOCK.get());
                        pOutput.accept(ModBLocks.RUBY_BLOCK.get());
                        pOutput.accept(ModBLocks.ONYX_BLOCK.get());
                        pOutput.accept(ModBLocks.OPAL_BLOCK.get());
                        pOutput.accept(ModBLocks.SAPPHIRE_BLOCK.get());
                        pOutput.accept(ModBLocks.MOONSTONE_BLOCK.get());
                        pOutput.accept(ModBLocks.TIGERS_EYE_BLOCK.get());
                        pOutput.accept(ModBLocks.TOPAZ_BLOCK.get());
                        //pOutput.accept(ModBLocks.TOURMALINE_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
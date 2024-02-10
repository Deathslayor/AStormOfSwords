// This code belongs to the package net.stormofsorts.agotmod.item
package net.astormofsorts.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.block.ModBLocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

// A utility class for creating custom creative mode tabs
public class ModCreativeNaturalBlocks {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final RegistryObject<CreativeModeTab> AGOT_TAB_NATURAL_BLOCKS = CREATIVE_MODE_TAB.register("agot_tab_natural_blocks", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBLocks.WEIRWOOD_SAPLING.get())) // Icon for the creative mode tab
            .title(Component.translatable("creativetab.agot_tab_natural_blocks")) // Title for the creative mode tab
            .displayItems((pParameters, pOutput) -> {

                //Ores
                pOutput.accept(ModBLocks.TIN_ORE.get());
                pOutput.accept(ModBLocks.DEEPSLATE_TIN_ORE.get());
                pOutput.accept(ModBLocks.RAW_TIN_BLOCK.get());

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


            }).build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

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
public class ModCreativeToolsUtilities {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_TOOLS = CREATIVE_MODE_TAB.register("agot_tab_tools_utilities",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STEEL_PICKAXE.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_tools_utilities")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {
                        /** TOOLS */
                        pOutput.accept(ModItems.BRONZE_AXE.get());
                        pOutput.accept(ModItems.BRONZE_HOE.get());
                        pOutput.accept(ModItems.BRONZE_PICKAXE.get());
                        pOutput.accept(ModItems.BRONZE_SHOVEL.get());

                        pOutput.accept(ModItems.STEEL_AXE.get());
                        pOutput.accept(ModItems.STEEL_HOE.get());
                        pOutput.accept(ModItems.STEEL_PICKAXE.get());
                        pOutput.accept(ModItems.STEEL_SHOVEL.get());
                        pOutput.accept(ModItems.FARMER_BARREL.get());

                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

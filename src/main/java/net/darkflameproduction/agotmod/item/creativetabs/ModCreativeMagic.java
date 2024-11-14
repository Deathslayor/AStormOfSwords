// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


// A utility class for creating custom creative mode tabs
public class ModCreativeMagic {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_MAGIC = CREATIVE_MODE_TAB.register("agot_tab_magic",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BLOOD_BOTTLED.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_magic")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {

                        /** Potions */
                        pOutput.accept(ModItems.BLOOD_BOTTLED.get());
                        pOutput.accept(ModItems.BLOOD_DAGGER.get());


                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
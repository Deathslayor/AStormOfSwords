// This code belongs to the package net.stormofsorts.agotmod.item
package net.stormofsorts.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.block.ModBLocks;
import net.stormofsorts.agotmod.item.ModItems;

// A utility class for creating custom creative mode tabs
public class ModCreativeArmour {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final RegistryObject<CreativeModeTab> AGOT_TAB_ARMOUR = CREATIVE_MODE_TAB.register("agot_tab_armour",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STARK1_CHESTPLATE.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_armour")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.STARK1_HELMET.get());
                        pOutput.accept(ModItems.STARK1_CHESTPLATE.get());
                        pOutput.accept(ModItems.STARK1_LEGGINGS.get());
                        pOutput.accept(ModItems.STARK1_BOOTS.get());

                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

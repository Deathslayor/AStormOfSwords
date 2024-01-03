// This code belongs to the package net.stormofsorts.agotmod.item
package net.astormofsorts.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.item.ModItems;

// A utility class for creating custom creative mode tabs
public class ModCreativeFoods {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final RegistryObject<CreativeModeTab> AGOT_TAB_FOODS = CREATIVE_MODE_TAB.register("agot_tab_foods",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.COOKED_BEAR_MEAT.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_foods")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {


                        pOutput.accept(ModItems.RAW_BEAR_MEAT.get());
                        pOutput.accept(ModItems.COOKED_BEAR_MEAT.get());
                        pOutput.accept(ModItems.RAW_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_BLOOD_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_WHITE_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_WHITE_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_BLOOD_SAUSAGE.get());

                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

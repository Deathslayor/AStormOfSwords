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
public class ModCreativeFoods {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_FOODS = CREATIVE_MODE_TAB.register("agot_tab_foods",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RAW_BACON.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_foods")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {


                        pOutput.accept(ModItems.RAW_BEAR_MEAT.get());
                        pOutput.accept(ModItems.COOKED_BEAR_MEAT.get());
                        pOutput.accept(ModItems.RAW_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_BLOOD_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_BLOOD_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_WHITE_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_WHITE_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_BACON.get());
                        pOutput.accept(ModItems.COOKED_BACON.get());
                        pOutput.accept(ModItems.RAW_BOAR_VENISON.get());
                        pOutput.accept(ModItems.COOKED_BOAR_VENISON.get());
                        pOutput.accept(ModItems.RAW_CHICKEN_NUGGETS.get());
                        pOutput.accept(ModItems.COOKED_CHICKEN_NUGGETS.get());
                        pOutput.accept(ModItems.RAW_DEER_VENISON.get());
                        pOutput.accept(ModItems.COOKED_DEER_VENISON.get());
                        pOutput.accept(ModItems.RAW_GOAT_MEAT.get());
                        pOutput.accept(ModItems.COOKED_GOAT_MEAT.get());
                        pOutput.accept(ModItems.RAW_HARE_MEAT.get());
                        pOutput.accept(ModItems.COOKED_HARE_MEAT.get());
                        pOutput.accept(ModItems.RAW_HORSE_MEAT.get());
                        pOutput.accept(ModItems.COOKED_HORSE_MEAT.get());
                        pOutput.accept(ModItems.RAW_MAMMOTH_MEAT.get());
                        pOutput.accept(ModItems.COOKED_MAMMOTH_MEAT.get());
                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

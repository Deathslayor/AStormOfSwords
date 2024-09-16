// This code belongs to the package net.stormofsorts.agotmod.item
package net.astormofsorts.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

// A utility class for creating custom creative mode tabs
public class ModCreativeIngredients {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final RegistryObject<CreativeModeTab> AGOT_TAB_INGREDIENTS = CREATIVE_MODE_TAB.register("agot_tab_ingredients",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STEEL_INGOT.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_ingredients")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {
                        /** Blocks */

                        /** Items */
                        //INGREDIENTS
                        pOutput.accept(ModItems.WEIRWOOD_STICK.get());
                        pOutput.accept(ModItems.BOAR_INTESTINES.get());
                        pOutput.accept(ModItems.BOAR_TUSK.get());

                        // INGOTS
                        pOutput.accept(ModItems.TIN_INGOT.get());
                        pOutput.accept(ModItems.RAW_TIN.get());
                        pOutput.accept(ModItems.BRONZE_INGOT.get());
                        pOutput.accept(ModItems.STEEL_INGOT.get());

                        pOutput.accept(ModItems.YELLOW_DIAMOND.get());
                        pOutput.accept(ModItems.TRANSPARENT_DIAMOND.get());
                        pOutput.accept(ModItems.BLOODSTONE.get());
                        pOutput.accept(ModItems.BLACK_DIAMOND.get());
                        pOutput.accept(ModItems.CHALCEDONY.get());
                        pOutput.accept(ModItems.AMBER.get());
                        pOutput.accept(ModItems.AMETHYST.get());
                        pOutput.accept(ModItems.CARNELIAN.get());
                        pOutput.accept(ModItems.GARNET.get());
                        pOutput.accept(ModItems.JADE.get());
                        pOutput.accept(ModItems.JASPER.get());
                        pOutput.accept(ModItems.MALACHITE.get());
                        pOutput.accept(ModItems.RUBY.get());
                        pOutput.accept(ModItems.ONYX.get());
                        pOutput.accept(ModItems.OPAL.get());
                        pOutput.accept(ModItems.SAPPHIRE.get());
                        pOutput.accept(ModItems.MOONSTONE.get());
                        pOutput.accept(ModItems.TIGERS_EYE.get());
                        pOutput.accept(ModItems.TOPAZ.get());
                        pOutput.accept(ModItems.TOURMALINE.get());


                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

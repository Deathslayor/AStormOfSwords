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
public class ModCreativeIngredients {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_INGREDIENTS = CREATIVE_MODE_TAB.register("agot_tab_ingredients",
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

                        //SEEDS
                        pOutput.accept(ModItems.BARLEY.get());
                        pOutput.accept(ModItems.OAT.get());
                        pOutput.accept(ModItems.HORSERADISH_SEEDS.get());
                        pOutput.accept(ModItems.BARLEY_SEEDS.get());
                        pOutput.accept(ModItems.GARLIC.get());
                        pOutput.accept(ModItems.LEEK_SEEDS.get());
                        pOutput.accept(ModItems.NEEP_SEEDS.get());
                        pOutput.accept(ModItems.OAT_SEEDS.get());
                        pOutput.accept(ModItems.PARSLEY_SEEDS.get());
                        pOutput.accept(ModItems.RED_ONION_SEEDS.get());
                        pOutput.accept(ModItems.TURNIP_SEEDS.get());
                        pOutput.accept(ModItems.WILD_ONION_SEEDS.get());
                        pOutput.accept(ModItems.ONION_SEEDS.get());
                        pOutput.accept(ModItems.OPIUM_POPPY_SEEDS.get());



                        pOutput.accept(ModItems.BRONZE_CHAIN_LINK.get());
                        pOutput.accept(ModItems.BRONZE_CHAIN.get());
                        pOutput.accept(ModItems.BRONZE_PLATE.get());
                        pOutput.accept(ModItems.CLOTH.get());
                        pOutput.accept(ModItems.IRON_CHAIN_LINK.get());
                        pOutput.accept(ModItems.IRON_CHAIN.get());
                        pOutput.accept(ModItems.IRON_PLATE.get());
                        pOutput.accept(ModItems.NOBLE_PLATE.get());

                        pOutput.accept(ModItems.STEEL_CHAIN_LINK.get());
                        pOutput.accept(ModItems.STEEL_CHAIN.get());
                        pOutput.accept(ModItems.STEEL_HELMET.get());
                        pOutput.accept(ModItems.STEEL_CHESTPLATE.get());
                        pOutput.accept(ModItems.STEEL_LEGGINGS.get());
                        pOutput.accept(ModItems.STEEL_BOOTS.get());
                        pOutput.accept(ModItems.HAMMER.get());
                        pOutput.accept(ModItems.STEEL_PLATE.get());
                        pOutput.accept(ModItems.UPGRADE_KIT_BRONZE.get());
                        pOutput.accept(ModItems.UPGRADE_KIT_IRON.get());
                        pOutput.accept(ModItems.UPGRADE_KIT_NOBLE.get());
                        pOutput.accept(ModItems.UPGRADE_KIT_STEEL.get());

                        pOutput.accept(ModItems.STARK_LEVY_SMITHING_SCROLL.get());
                        pOutput.accept(ModItems.STARK_PLATE_SMITHING_SCROLL.get());
                        pOutput.accept(ModItems.STARK_NOBLE_SMITHING_SCROLL.get());

                        pOutput.accept(ModItems.BOLTON_LEVY_SMITHING_SCROLL.get());
                        pOutput.accept(ModItems.BOLTON_PLATE_SMITHING_SCROLL.get());
                        pOutput.accept(ModItems.BOLTON_NOBLE_SMITHING_SCROLL.get());

                        pOutput.accept(ModItems.MANDERLY_LEVY_SMITHING_SCROLL.get());
                        pOutput.accept(ModItems.MANDERLY_PLATE_SMITHING_SCROLL.get());
                        pOutput.accept(ModItems.MANDERLY_NOBLE_SMITHING_SCROLL.get());

                        pOutput.accept(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL.get());
                        pOutput.accept(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL.get());
                        pOutput.accept(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL.get());




                        // INGOTS
                        pOutput.accept(ModItems.TIN_INGOT.get());
                        pOutput.accept(ModItems.RAW_TIN.get());
                        pOutput.accept(ModItems.BRONZE_INGOT.get());
                        pOutput.accept(ModItems.BRONZE_NUGGET.get());
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

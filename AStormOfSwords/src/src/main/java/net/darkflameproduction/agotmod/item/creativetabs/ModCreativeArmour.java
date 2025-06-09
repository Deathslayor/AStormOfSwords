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
public class ModCreativeArmour {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_ARMOUR = CREATIVE_MODE_TAB.register("agot_tab_armour",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STARK_LEVY_CHESTPLATE.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_armour")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.STARK_LEVY_HELMET.get());
                        pOutput.accept(ModItems.STARK_LEVY_CHESTPLATE.get());
                        pOutput.accept(ModItems.STARK_LEVY_LEGGINGS.get());
                        pOutput.accept(ModItems.STARK_LEVY_BOOTS.get());

                        pOutput.accept(ModItems.STARK_PLATE_HELMET.get());
                        pOutput.accept(ModItems.STARK_PLATE_CHESTPLATE.get());
                        pOutput.accept(ModItems.STARK_PLATE_LEGGINGS.get());
                        pOutput.accept(ModItems.STARK_PLATE_BOOTS.get());

                        pOutput.accept(ModItems.STARK_NOBLE_PLATE_HELMET.get());
                        pOutput.accept(ModItems.STARK_NOBLE_PLATE_CHESTPLATE.get());
                        pOutput.accept(ModItems.STARK_NOBLE_PLATE_LEGGINGS.get());
                        pOutput.accept(ModItems.STARK_NOBLE_PLATE_BOOTS.get());

                        pOutput.accept(ModItems.MANDERLY_LEVY_HELMET.get());
                        pOutput.accept(ModItems.MANDERLY_LEVY_CHESTPLATE.get());
                        pOutput.accept(ModItems.MANDERLY_LEVY_LEGGINGS.get());
                        pOutput.accept(ModItems.MANDERLY_LEVY_BOOTS.get());

                        pOutput.accept(ModItems.MANDERLY_PLATE_HELMET.get());
                        pOutput.accept(ModItems.MANDERLY_PLATE_CHESTPLATE.get());
                        pOutput.accept(ModItems.MANDERLY_PLATE_LEGGINGS.get());
                        pOutput.accept(ModItems.MANDERLY_PLATE_BOOTS.get());

                        pOutput.accept(ModItems.MANDERLY_NOBLE_HELMET.get());
                        pOutput.accept(ModItems.MANDERLY_NOBLE_CHESTPLATE.get());
                        pOutput.accept(ModItems.MANDERLY_NOBLE_LEGGINGS.get());
                        pOutput.accept(ModItems.MANDERLY_NOBLE_BOOTS.get());

                        pOutput.accept(ModItems.BOLTEN_LEVY_HELMET.get());
                        pOutput.accept(ModItems.BOLTEN_LEVY_CHESTPLATE.get());
                        pOutput.accept(ModItems.BOLTEN_LEVY_LEGGINGS.get());
                        pOutput.accept(ModItems.BOLTEN_LEVY_BOOTS.get());

                        pOutput.accept(ModItems.BOLTEN_PLATE_HELMET.get());
                        pOutput.accept(ModItems.BOLTEN_PLATE_CHESTPLATE.get());
                        pOutput.accept(ModItems.BOLTEN_PLATE_LEGGINGS.get());
                        pOutput.accept(ModItems.BOLTEN_PLATE_BOOTS.get());

                        pOutput.accept(ModItems.BOLTEN_NOBLE_HELMET.get());
                        pOutput.accept(ModItems.BOLTEN_NOBLE_CHESTPLATE.get());
                        pOutput.accept(ModItems.BOLTEN_NOBLE_LEGGINGS.get());
                        pOutput.accept(ModItems.BOLTEN_NOBLE_BOOTS.get());

                        pOutput.accept(ModItems.MOUNTAIN_CLAN_LEVY_HELMET.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_LEVY_CHESTPLATE.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_LEVY_LEGGINGS.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_LEVY_BOOTS.get());

                        pOutput.accept(ModItems.MOUNTAIN_CLAN_PLATE_HELMET.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_PLATE_CHESTPLATE.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_PLATE_LEGGINGS.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_PLATE_BOOTS.get());

                        pOutput.accept(ModItems.MOUNTAIN_CLAN_CHIEF_HELMET.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_CHIEF_CHESTPLATE.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_CHIEF_LEGGINGS.get());
                        pOutput.accept(ModItems.MOUNTAIN_CLAN_CHIEF_BOOTS.get());

                        pOutput.accept(ModItems.NIGHT_WATCH_RANGER_HELMET.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_RANGER_CHESTPLATE.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_RANGER_LEGGINGS.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_RANGER_BOOTS.get());

                        pOutput.accept(ModItems.NIGHT_WATCH_LEATHER_HELMET.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_LEATHER_CHESTPLATE.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_LEATHER_LEGGINGS.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_LEATHER_BOOTS.get());

                        pOutput.accept(ModItems.NIGHT_WATCH_ELITE_HELMET.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_ELITE_CHESTPLATE.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_ELITE_LEGGINGS.get());
                        pOutput.accept(ModItems.NIGHT_WATCH_ELITE_BOOTS.get());

                        pOutput.accept(ModItems.WILDLING_FUR_HELMET.get());
                        pOutput.accept(ModItems.WILDLING_FUR_CHESTPLATE.get());
                        pOutput.accept(ModItems.WILDLING_FUR_LEGGINGS.get());
                        pOutput.accept(ModItems.WILDLING_FUR_BOOTS.get());

                        pOutput.accept(ModItems.WILDLING_LEATHER_HELMET.get());
                        pOutput.accept(ModItems.WILDLING_LEATHER_CHESTPLATE.get());
                        pOutput.accept(ModItems.WILDLING_LEATHER_LEGGINGS.get());
                        pOutput.accept(ModItems.WILDLING_LEATHER_BOOTS.get());

                        pOutput.accept(ModItems.WILDLING_CHIEF_HELMET.get());
                        pOutput.accept(ModItems.WILDLING_CHIEF_CHESTPLATE.get());
                        pOutput.accept(ModItems.WILDLING_CHIEF_LEGGINGS.get());
                        pOutput.accept(ModItems.WILDLING_CHIEF_BOOTS.get());

                        pOutput.accept(ModItems.THENN_LEVY_HELMET.get());
                        pOutput.accept(ModItems.THENN_LEVY_CHESTPLATE.get());
                        pOutput.accept(ModItems.THENN_LEVY_LEGGINGS.get());
                        pOutput.accept(ModItems.THENN_LEVY_BOOTS.get());

                        pOutput.accept(ModItems.THENN_PLATE_HELMET.get());
                        pOutput.accept(ModItems.THENN_PLATE_CHESTPLATE.get());
                        pOutput.accept(ModItems.THENN_PLATE_LEGGINGS.get());
                        pOutput.accept(ModItems.THENN_PLATE_BOOTS.get());

                        pOutput.accept(ModItems.THENN_NOBLE_HELMET.get());
                        pOutput.accept(ModItems.THENN_NOBLE_CHESTPLATE.get());
                        pOutput.accept(ModItems.THENN_NOBLE_LEGGINGS.get());
                        pOutput.accept(ModItems.THENN_NOBLE_BOOTS.get());

                        pOutput.accept(ModItems.IRONBORN_LEVY_HELMET.get());
                        pOutput.accept(ModItems.IRONBORN_LEVY_CHESTPLATE.get());
                        pOutput.accept(ModItems.IRONBORN_LEVY_LEGGINGS.get());
                        pOutput.accept(ModItems.IRONBORN_LEVY_BOOTS.get());

                        pOutput.accept(ModItems.IRONBORN_PLATE_HELMET.get());
                        pOutput.accept(ModItems.IRONBORN_PLATE_CHESTPLATE.get());
                        pOutput.accept(ModItems.IRONBORN_PLATE_LEGGINGS.get());
                        pOutput.accept(ModItems.IRONBORN_PLATE_BOOTS.get());

                        pOutput.accept(ModItems.IRONBORN_NOBLE_HELMET.get());
                        pOutput.accept(ModItems.IRONBORN_NOBLE_CHESTPLATE.get());
                        pOutput.accept(ModItems.IRONBORN_NOBLE_LEGGINGS.get());
                        pOutput.accept(ModItems.IRONBORN_NOBLE_BOOTS.get());

                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

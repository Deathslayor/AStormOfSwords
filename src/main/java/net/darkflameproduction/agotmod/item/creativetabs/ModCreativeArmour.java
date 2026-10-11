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
                    .icon(() -> new ItemStack(net.darkflameproduction.agotmod.armor.ModArmorSets.get("stark_tier2").piece(net.minecraft.world.item.ArmorItem.Type.CHESTPLATE).get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_armour")) // Title for the creative mode tab
                    .withTabsBefore(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "agot_tab_weapons"))
                    .displayItems((pParameters, pOutput) -> {

                        // Every 3D armour set: regional tier 1 sets first, then each faction's tier 2 and 3
                        for (net.darkflameproduction.agotmod.armor.ModArmorSets.ArmorSet set : net.darkflameproduction.agotmod.armor.ModArmorSets.all()) {
                            for (net.minecraft.world.item.ArmorItem.Type type : net.darkflameproduction.agotmod.armor.ModArmorSets.PIECES) {
                                pOutput.accept(set.piece(type).get());
                            }
                        }

                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}


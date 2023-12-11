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
public class ModCreativeHandheld {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final RegistryObject<CreativeModeTab> AGOT_TAB_HANDHELD = CREATIVE_MODE_TAB.register("agot_tab_handheld",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STEEL_BATTLEAXE.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_handheld")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {
                        /** TOOLS */
                        pOutput.accept(ModItems.BRONZE_SWORD.get());
                        pOutput.accept(ModItems.BRONZE_AXE.get());
                        pOutput.accept(ModItems.BRONZE_HOE.get());
                        pOutput.accept(ModItems.BRONZE_PICKAXE.get());
                        pOutput.accept(ModItems.BRONZE_SHOVEL.get());
                        pOutput.accept(ModItems.BRONZE_SPATHA.get());

                        pOutput.accept(ModItems.IRON_LONGSWORD.get());
                        pOutput.accept(ModItems.IRON_SPEAR.get());
                        pOutput.accept(ModItems.IRON_PIKE.get());
                        pOutput.accept(ModItems.IRON_DAGGER.get());
                        pOutput.accept(ModItems.IRON_MACE.get());
                        pOutput.accept(ModItems.IRON_BATTLE_AXE.get());

                        pOutput.accept(ModItems.STEEL_LONGSWORD.get());
                        pOutput.accept(ModItems.STEEL_SPEAR.get());
                        pOutput.accept(ModItems.STEEL_PIKE.get());
                        pOutput.accept(ModItems.STEEL_DAGGER.get());
                        pOutput.accept(ModItems.STEEL_MACE.get());
                        pOutput.accept(ModItems.STEEL_BATTLEAXE.get());
                        pOutput.accept(ModItems.STEEL_HALBERD.get());


                        pOutput.accept(ModItems.STEEL_SWORD.get());
                        pOutput.accept(ModItems.STEEL_AXE.get());
                        pOutput.accept(ModItems.STEEL_HOE.get());
                        pOutput.accept(ModItems.STEEL_PICKAXE.get());
                        pOutput.accept(ModItems.STEEL_SHOVEL.get());
                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

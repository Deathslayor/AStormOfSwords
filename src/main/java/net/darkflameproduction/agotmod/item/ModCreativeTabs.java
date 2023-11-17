package net.darkflameproduction.agotmod.item;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    // makes creating modded tabs possible
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final RegistryObject<CreativeModeTab> AGOT_TAB = CREATIVE_MODE_TAB.register("agot_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBLocks.MINT_BLOCK.get()))
                    .title(Component.translatable("creativetab.agot_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        // Blocks
                        pOutput.accept(ModBLocks.MINT_BLOCK.get()); // Adds the mint Working station block for villagers

                        // Items
                        pOutput.accept(ModItems.COIN.get()); // Adds Default currency COIN used for trading with villagers
                        // Armour
                        pOutput.accept(ModItems.STARK1_HELMET.get());
                        pOutput.accept(ModItems.STARK1_CHESTPLATE.get());
                        pOutput.accept(ModItems.STARK1_LEGGINGS.get());
                        pOutput.accept(ModItems.STARK1_BOOTS.get());

                    })
                    .build());

    // tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

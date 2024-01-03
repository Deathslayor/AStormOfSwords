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
import net.astormofsorts.agotmod.block.ModBLocks;

// A utility class for creating custom creative mode tabs
public class ModCreativeBuildingBlocks {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final RegistryObject<CreativeModeTab> AGOT_TAB_BUILDING_BLOCKS = CREATIVE_MODE_TAB.register("agot_tab_building_blocks",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBLocks.DARK_STONE_BRICK.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_building_blocks")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {

                        // Bricks
                        pOutput.accept(ModBLocks.STONE_BRICK_BUT_COOLER.get());
                        pOutput.accept(ModBLocks.DARK_STONE_BRICK.get());
                        // Kings LANDING BRICKS
                        pOutput.accept(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());

                        /** INGOTS = Blocks */
                        // TIN
                        pOutput.accept(ModBLocks.TIN_ORE.get());
                        pOutput.accept(ModBLocks.RAW_TIN_BLOCK.get());
                        pOutput.accept(ModBLocks.DEEPSLATE_TIN_ORE.get());
                        pOutput.accept(ModBLocks.TIN_BLOCK.get());
                        // BRONZE
                        pOutput.accept(ModBLocks.BRONZE_BLOCK.get());

                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// A utility class for creating custom creative mode tabs
public class ModCreativeToolsUtilities {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_TOOLS = CREATIVE_MODE_TAB.register("agot_tab_tools_utilities",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STEEL_PICKAXE.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_tools_utilities")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {
                        /** TOOLS */
                        pOutput.accept(ModItems.BRONZE_AXE.get());
                        pOutput.accept(ModItems.BRONZE_HOE.get());
                        pOutput.accept(ModItems.BRONZE_PICKAXE.get());
                        pOutput.accept(ModItems.BRONZE_SHOVEL.get());

                        pOutput.accept(ModItems.STEEL_AXE.get());
                        pOutput.accept(ModItems.STEEL_HOE.get());
                        pOutput.accept(ModItems.STEEL_PICKAXE.get());
                        pOutput.accept(ModItems.STEEL_SHOVEL.get());
// Town Hall (existing)
                        pOutput.accept(ModItems.TOWN_HALL.get());

// All Job Barrel Creative Tab Entries
                        pOutput.accept(ModItems.ALEHOUSE_BARREL.get());
                        pOutput.accept(ModItems.ARMORSMITH_BARREL.get());
                        pOutput.accept(ModItems.BAKER_BARREL.get());
                        pOutput.accept(ModItems.BANKER_BARREL.get());
                        pOutput.accept(ModItems.BARD_BARREL.get());
                        pOutput.accept(ModItems.BUILDER_BARREL.get());
                        pOutput.accept(ModItems.BUTCHER_BARREL.get());
                        pOutput.accept(ModItems.CARAVAN_MASTER_BARREL.get());
                        pOutput.accept(ModItems.CATTLE_HERDER_BARREL.get());
                        pOutput.accept(ModItems.CHARCOAL_BURNER_BARREL.get());
                        pOutput.accept(ModItems.CHICKEN_BREEDER_BARREL.get());
                        pOutput.accept(ModItems.FARMER_BARREL.get());
                        pOutput.accept(ModItems.GOAT_HERDER_BARREL.get());
                        pOutput.accept(ModItems.GROCER_BARREL.get());
                        pOutput.accept(ModItems.GUARD_BARREL.get());
                        pOutput.accept(ModItems.HERBALIST_BARREL.get());
                        pOutput.accept(ModItems.HORSE_BREEDER_BARREL.get());
                        pOutput.accept(ModItems.HUNTER_BARREL.get());
                        pOutput.accept(ModItems.INNKEEPER_BARREL.get());
                        pOutput.accept(ModItems.JEWELER_BARREL.get());
                        pOutput.accept(ModItems.LUMBERJACK_BARREL.get());
                        pOutput.accept(ModItems.MAESTER_BARREL.get());
                        pOutput.accept(ModItems.MINER_BARREL.get());
                        pOutput.accept(ModItems.PIG_BREEDER_BARREL.get());
                        pOutput.accept(ModItems.PYROMANCER_BARREL.get());
                        pOutput.accept(ModItems.QUARRY_BARREL.get());
                        pOutput.accept(ModItems.SCRIBE_BARREL.get());
                        pOutput.accept(ModItems.SEPTON_BARREL.get());
                        pOutput.accept(ModItems.SHEEP_HERDER_BARREL.get());
                        pOutput.accept(ModItems.SHIPWRIGHT_BARREL.get());
                        pOutput.accept(ModItems.SLAVER_BARREL.get());
                        pOutput.accept(ModItems.SMELTER_BARREL.get());
                        pOutput.accept(ModItems.STONEMASON_BARREL.get());
                        pOutput.accept(ModItems.SWORDSMITH_BARREL.get());
                        pOutput.accept(ModItems.TAILOR_BARREL.get());
                        pOutput.accept(ModItems.TANNER_BARREL.get());
                        pOutput.accept(ModItems.TRADER_BARREL.get());

                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

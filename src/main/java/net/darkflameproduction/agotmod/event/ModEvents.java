// MOD BUS EVENTS ONLY - for capability registration
package net.darkflameproduction.agotmod.event;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.JobBarrelBlockEntity;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

// MOD event bus for mod loading events
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {

    // Register capabilities for mod event bus
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // Register item handler capability for all job barrels
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.JOB_BARREL.get(), // Your job barrel block entity type
                (blockEntity, side) -> {
                    if (blockEntity instanceof JobBarrelBlockEntity barrel) {
                        return barrel.getItemHandler();
                    }
                    return null;
                }
        );
    }
}
// MOD BUS EVENTS ONLY - for capability registration
package net.darkflameproduction.agotmod.event;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.FarmerBarrelBlockEntity;
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
        // Register item handler capability for farmer barrel
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.FARMER_BARREL.get(), // Your farmer barrel block entity type
                (blockEntity, side) -> {
                    if (blockEntity instanceof FarmerBarrelBlockEntity barrel) {
                        return barrel.getItemHandler();
                    }
                    return null;
                }
        );
    }
}
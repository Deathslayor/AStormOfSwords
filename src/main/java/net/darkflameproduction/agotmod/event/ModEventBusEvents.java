// This code belongs to the package net.stormofsorts.agotmod.event
package net.darkflameproduction.agotmod.event;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.darkflameproduction.agotmod.magic.custom.PlayerManaProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.jetbrains.annotations.NotNull;

// Changed bus to Bus.MOD for NeoForge (same as Forge)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // For NeoForge, we use .get() on the DeferredHolder
        event.put(ModEntities.MAMMOTH_ENTITY.get(), Mammoth_Entity.createAttributes().build());
        event.put(ModEntities.CROW_ENTITY.get(), Mammoth_Entity.createAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(@NotNull RegisterCapabilitiesEvent event) {
        event.registerEntity(PlayerManaProvider.PLAYER_MANA, EntityType.PLAYER, new PlayerManaProvider());
    }
}

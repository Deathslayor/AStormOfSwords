// This code belongs to the package net.stormofsorts.agotmod.event
package net.darkflameproduction.agotmod.event;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;

import net.darkflameproduction.agotmod.magic.custom.PlayerManaProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.jetbrains.annotations.NotNull;

// Annotating the class as a subscriber to the Forge Mod Event Bus
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {






    }

    @SubscribeEvent
    public static void onRegisterCapabilities(@NotNull RegisterCapabilitiesEvent event) {
        event.registerEntity(PlayerManaProvider.PLAYER_MANA, EntityType.PLAYER, new PlayerManaProvider());
    }
}

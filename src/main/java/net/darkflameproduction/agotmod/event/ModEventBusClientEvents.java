// This code belongs to the package net.stormofsorts.agotmod.event
package net.darkflameproduction.agotmod.event;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.client.MammothModel;
import net.darkflameproduction.agotmod.entity.client.ModModelLayers;
import net.darkflameproduction.agotmod.event.KeyMappings.KeyBindings;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.jetbrains.annotations.NotNull;

// Annotating the class as a subscriber to the Forge Mod Event Bus
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    // Annotation indicating that this method should subscribe to the specified event
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.@NotNull RegisterLayerDefinitions event) {
        // Registering a layer definition for a custom model (RhinoModel)
        // The layer definition is associated with the RhinoModel's createBodyLayer method
        event.registerLayerDefinition(ModModelLayers.MAMMOTH_LAYER, MammothModel::createBodyLayer);
    }

    //Registers Keys
    @SubscribeEvent
    public static void registerKeys(@NotNull RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.INSTANCE.UseSpellOne);
        event.register(KeyBindings.INSTANCE.UseSpellTwo);
        event.register(KeyBindings.INSTANCE.UseSpellThree);
        event.register(KeyBindings.INSTANCE.UseSpellFour);
        event.register(KeyBindings.INSTANCE.UseSpellFive);
        event.register(KeyBindings.INSTANCE.UseSpellSix);
        event.register(KeyBindings.INSTANCE.UseSpellSeven);
        event.register(KeyBindings.INSTANCE.UseSpellEight);
        event.register(KeyBindings.INSTANCE.UseSpellNine);
        event.register(KeyBindings.INSTANCE.OpenMagicMenu);
        event.register(KeyBindings.INSTANCE.OpenBasicMenu);
        event.register(KeyBindings.INSTANCE.OpenMap);
        event.register(KeyBindings.INSTANCE.SetWayPoint);
    }
}

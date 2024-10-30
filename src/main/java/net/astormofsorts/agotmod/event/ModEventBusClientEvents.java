// This code belongs to the package net.stormofsorts.agotmod.event
package net.astormofsorts.agotmod.event;

// Importing necessary classes from other packages

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.entity.client.BearModel;
import net.astormofsorts.agotmod.entity.client.MammothModel;
import net.astormofsorts.agotmod.entity.client.ModModelLayers;
import net.astormofsorts.agotmod.entity.client.norththewall.WightModel;
import net.astormofsorts.agotmod.event.KeyMappings.KeyBindings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Annotating the class as a subscriber to the Forge Mod Event Bus
@Mod.EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    // Annotation indicating that this method should subscribe to the specified event
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Registering a layer definition for a custom model (RhinoModel)
        // The layer definition is associated with the RhinoModel's createBodyLayer method
        event.registerLayerDefinition(ModModelLayers.BEAR_LAYER, BearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.WIGHT_LAYER, WightModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MAMMOTH_LAYER, MammothModel::createBodyLayer);
    }

    //Registers Keys
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
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

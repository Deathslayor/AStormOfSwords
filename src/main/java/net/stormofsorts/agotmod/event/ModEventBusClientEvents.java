// This code belongs to the package net.stormofsorts.agotmod.event
package net.stormofsorts.agotmod.event;

// Importing necessary classes from other packages
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.entity.client.ModModelLayers;
import net.stormofsorts.agotmod.entity.client.RhinoModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
        event.registerLayerDefinition(ModModelLayers.RHINO_LAYER, RhinoModel::createBodyLayer);
    }
}

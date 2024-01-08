// This code belongs to the package net.stormofsorts.agotmod.event
package net.astormofsorts.agotmod.event;

// Importing necessary classes from other packages
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.entity.ModEntities;
import net.astormofsorts.agotmod.entity.custom.BearEntity;
import net.astormofsorts.agotmod.entity.custom.RhinoEntity;
import net.astormofsorts.agotmod.entity.custom.norththewall.WightEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Annotating the class as a subscriber to the Forge Mod Event Bus
@Mod.EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    // Annotation indicating that this method should subscribe to the specified event
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // Registering attributes for the RhinoEntity when an EntityAttributeCreationEvent occurs
        // The attributes are created using the createAttributes() method of RhinoEntity
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
        event.put(ModEntities.BEAR.get(), BearEntity.createAttributes().build());
        event.put(ModEntities.WIGHT.get(), WightEntity.createAttributes().build());
    }
}

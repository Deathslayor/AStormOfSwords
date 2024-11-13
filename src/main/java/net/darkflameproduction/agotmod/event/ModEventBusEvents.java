// This code belongs to the package net.stormofsorts.agotmod.event
package net.darkflameproduction.agotmod.event;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.entity.custom.BearEntity;
import net.darkflameproduction.agotmod.entity.custom.norththewall.WightEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import org.jetbrains.annotations.NotNull;

// Annotating the class as a subscriber to the Forge Mod Event Bus
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    // Annotation indicating that this method should subscribe to the specified event
    @SubscribeEvent
    public static void registerAttributes(@NotNull EntityAttributeCreationEvent event) {
        // Registering attributes for the RhinoEntity when an EntityAttributeCreationEvent occurs
        // The attributes are created using the createAttributes() method of RhinoEntity
        event.put(ModEntities.BEAR.get(), BearEntity.createAttributes().build());
        event.put(ModEntities.WIGHT.get(), WightEntity.createAttributes().build());
        event.put(ModEntities.MAMMOTH.get(), WightEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(@NotNull RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.MAMMOTH.get(), SpawnPlacements.getPlacementType(ModEntities.MAMMOTH.get()), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

    }
}

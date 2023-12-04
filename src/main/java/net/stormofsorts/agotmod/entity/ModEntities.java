package net.stormofsorts.agotmod.entity;

import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.entity.custom.BearEntity;
import net.stormofsorts.agotmod.entity.custom.RhinoEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// ModEntities serves as a registry for custom entities in the mod
public class ModEntities {

    // Deferred register for entity types
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AGoTMod.MOD_ID);

    // Registry object for the Rhino entity type
    public static final RegistryObject<EntityType<RhinoEntity>> RHINO =
            ENTITY_TYPES.register("rhino", () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 2.5f).build("rhino"));

    // Registry object for the Bear entity type
    public static final RegistryObject<EntityType<BearEntity>> BEAR =
            ENTITY_TYPES.register("bear", () -> EntityType.Builder.of(BearEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("bear"));

    // Method for registering the deferred register with the event bus
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

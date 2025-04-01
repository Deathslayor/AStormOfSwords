package net.darkflameproduction.agotmod.entity;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.MammothEntity;
import net.darkflameproduction.agotmod.entity.custom.norththewall.WightEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// ModEntities serves as a registry for custom entities in the mod
public class ModEntities {

    // Deferred register for entity types
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AGoTMod.MOD_ID);

    // Registry object for the Mammoth entity type
    public static final DeferredHolder<EntityType<?>, EntityType<MammothEntity>> MAMMOTH =
            ENTITY_TYPES.register("mammoth", () -> EntityType.Builder.of(MammothEntity::new, MobCategory.CREATURE)
                    .sized(3f, 4f).build(ResourceKey.create(Registries.ENTITY_TYPE, AGoTMod.id("mammoth"))));


    // Method for registering the deferred register with the event bus
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

package net.darkflameproduction.agotmod.entity;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, AGoTMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Mammoth_Entity>> MAMMOTH_ENTITY =
            ENTITY_TYPES.register("mammoth",
                    () -> EntityType.Builder.of(Mammoth_Entity::new, MobCategory.MISC)
                            .sized(3.5f, 4f)
                            .clientTrackingRange(64)
                            .build(ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE,
                                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mammoth"))));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

package net.darkflameproduction.agotmod.entity;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.birds.Crow_Entity;
import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.wolves.Direwolf_Entity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, AGoTMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Mammoth_Entity>> MAMMOTH_ENTITY =
            ENTITY_TYPES.register("mammoth",
                    () -> EntityType.Builder.of(Mammoth_Entity::new, MobCategory.CREATURE)
                            .sized(3.5f, 4f)
                            .clientTrackingRange(256)
                            .build(ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE,
                                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mammoth"))));

    public static final DeferredHolder<EntityType<?>, EntityType<Crow_Entity>> CROW_ENTITY =
            ENTITY_TYPES.register("crow",
                    () -> EntityType.Builder.of(Crow_Entity::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.6f)
                            .clientTrackingRange(256)
                            .build(ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE,
                                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "crow"))));

    public static final DeferredHolder<EntityType<?>, EntityType<Direwolf_Entity>> DIREWOLF_ENTITY =
            ENTITY_TYPES.register("direwolf",
                    () -> EntityType.Builder.of(Direwolf_Entity::new, MobCategory.CREATURE)
                            .sized(2f, 2f)
                            .clientTrackingRange(256)
                            .build(ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE,
                                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "direwolf"))));

    // Fixed Northern_Peasant_Entity registration with explicit lambda
    public static final DeferredHolder<EntityType<?>, EntityType<Northern_Peasant_Entity>> NORTHERN_PEASANT_ENTITY =
            ENTITY_TYPES.register("northern_peasant",
                    () -> EntityType.Builder.of(
                                    (EntityType<Northern_Peasant_Entity> entityType, Level level) ->
                                            new Northern_Peasant_Entity(entityType, level),
                                    MobCategory.CREATURE)
                            .sized(0.6f, 1.95f) // Changed to more reasonable villager-like size
                            .clientTrackingRange(10) // Changed to match villager tracking range
                            .build(ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE,
                                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "northern_peasant"))));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(MAMMOTH_ENTITY.get(), Mammoth_Entity.createAttributes().build());
        event.put(CROW_ENTITY.get(), Crow_Entity.createAttributes().build());
        event.put(DIREWOLF_ENTITY.get(), Direwolf_Entity.createAttributes().build());
        event.put(NORTHERN_PEASANT_ENTITY.get(), Northern_Peasant_Entity.createAttributes().build());
    }
}
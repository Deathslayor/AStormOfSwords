package net.darkflameproduction.agotmod.entity;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.SeatEntity;
import net.darkflameproduction.agotmod.entity.custom.birds.Crow_Entity;
import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.wolves.Direwolf_Entity;
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
                            .build("mammoth"));

    public static final DeferredHolder<EntityType<?>, EntityType<Crow_Entity>> CROW_ENTITY =
            ENTITY_TYPES.register("crow",
                    () -> EntityType.Builder.of(Crow_Entity::new, MobCategory.CREATURE)
                            .sized(0.4f, 0.6f)
                            .clientTrackingRange(256)
                            .build("crow"));

    public static final DeferredHolder<EntityType<?>, EntityType<Direwolf_Entity>> DIREWOLF_ENTITY =
            ENTITY_TYPES.register("direwolf",
                    () -> EntityType.Builder.of(Direwolf_Entity::new, MobCategory.CREATURE)
                            .sized(2f, 2f)
                            .clientTrackingRange(256)
                            .build("direwolf"));

    public static final DeferredHolder<EntityType<?>, EntityType<Peasant_Entity>> PEASANT_ENTITY =
            ENTITY_TYPES.register("peasant",
                    () -> EntityType.Builder.of(
                                    (EntityType<Peasant_Entity> entityType, Level level) ->
                                            new Peasant_Entity(entityType, level),
                                    MobCategory.CREATURE)
                            // A player's size (they are drawn as one): at 1.95 tall, a jump onto a block with a carpet
                            // on it hit the ceiling of a three-high room just short of the top, so they never made it
                            .sized(0.6f, 1.8f)
                            .eyeHeight(1.62f)
                            // Sits in a seat (chair, boat, horse) as low as a player does - without it, 0.6 above them
                            .vehicleAttachment(net.minecraft.world.entity.player.Player.DEFAULT_VEHICLE_ATTACHMENT)
                            .clientTrackingRange(10)
                            .build("peasant"));

    public static final DeferredHolder<EntityType<?>, EntityType<SeatEntity>> SEAT_ENTITY =
            ENTITY_TYPES.register("seat",
                    () -> EntityType.Builder.of(
                                    (EntityType<SeatEntity> entityType, Level level) ->
                                            new SeatEntity(entityType, level),
                                    MobCategory.MISC)
                            .sized(0.001f, 0.001f)
                            .clientTrackingRange(10)
                            .build("seat"));

    public static final DeferredHolder<EntityType<?>, EntityType<net.darkflameproduction.agotmod.entity.custom.boat.FishingBoatEntity>> FISHING_BOAT =
            ENTITY_TYPES.register("fishing_boat",
                    () -> EntityType.Builder.<net.darkflameproduction.agotmod.entity.custom.boat.FishingBoatEntity>of(
                                    net.darkflameproduction.agotmod.entity.custom.boat.FishingBoatEntity::new, MobCategory.MISC)
                            .sized(1.40625F, 0.5625F) // as wide as the hull (15 px x MODEL_SCALE), as high as a vanilla boat (it floats by its height)
                            .clientTrackingRange(10)
                            .build("fishing_boat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(MAMMOTH_ENTITY.get(), Mammoth_Entity.createAttributes().build());
        event.put(CROW_ENTITY.get(), Crow_Entity.createAttributes().build());
        event.put(DIREWOLF_ENTITY.get(), Direwolf_Entity.createAttributes().build());
        event.put(PEASANT_ENTITY.get(), Peasant_Entity.createAttributes().build());
    }
}

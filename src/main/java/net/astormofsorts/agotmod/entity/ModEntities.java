package net.astormofsorts.agotmod.entity;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.entity.custom.BearEntity;
import net.astormofsorts.agotmod.entity.custom.MammothEntity;
import net.astormofsorts.agotmod.entity.custom.norththewall.WightEntity;
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


    // Registry object for the Bear entity type
    public static final RegistryObject<EntityType<BearEntity>> BEAR =
            ENTITY_TYPES.register("bear", () -> EntityType.Builder.of(BearEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("bear"));


    // Registry object for the Bear entity type
    public static final RegistryObject<EntityType<WightEntity>> WIGHT =
            ENTITY_TYPES.register("wight", () -> EntityType.Builder.of(WightEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("wight"));

    // Registry object for the Mammoth entity type
    public static final RegistryObject<EntityType<MammothEntity>> MAMMOTH =
            ENTITY_TYPES.register("mammoth", () -> EntityType.Builder.of(MammothEntity::new, MobCategory.CREATURE)
                    .sized(3f, 4f).build("mammoth"));


    // Method for registering the deferred register with the event bus
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

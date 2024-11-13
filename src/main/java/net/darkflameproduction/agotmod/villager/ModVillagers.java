// This code belongs to the package net.stormofsorts.agotmod.villager
package net.darkflameproduction.agotmod.villager;

// Importing necessary classes from other packages

import com.google.common.collect.ImmutableSet;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// A utility class for creating custom POI types and Villager professions
public class ModVillagers {

    // Deferred register for custom POI types
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, AGoTMod.MOD_ID);

    // Deferred register for custom Villager professions
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(Registries.VILLAGER_PROFESSION, AGoTMod.MOD_ID);

    // Custom POI type: MINT_POI
    public static final DeferredHolder<PoiType, PoiType> MINT_POI = POI_TYPES.register("mint_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.MAGENTA_GLAZED_TERRACOTTA.getStateDefinition().getPossibleStates()),
                    1, 1));

    // Custom Villager profession: MINTER
    public static final DeferredHolder<VillagerProfession, VillagerProfession> MINTER =
            // Registers the custom Villager profession
            VILLAGER_PROFESSIONS.register("minter", () -> new VillagerProfession("minter",
                    // Condition for villagers to become this profession
                    holder -> holder.get() == MINT_POI.get(),
                    // Condition for villagers to lose this profession
                    holder -> holder.get() == MINT_POI.get(),
                    // Sets of work blocks and POI types for the profession
                    ImmutableSet.of(), ImmutableSet.of(),
                    // Sound played when the villager works
                    SoundEvents.VILLAGER_WORK_ARMORER));

    // Registers the custom POI types and Villager professions into the game
    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}

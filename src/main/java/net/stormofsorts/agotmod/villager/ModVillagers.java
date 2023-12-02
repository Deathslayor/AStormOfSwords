// This code belongs to the package net.stormofsorts.agotmod.villager
package net.stormofsorts.agotmod.villager;

// Importing necessary classes from other packages
import com.google.common.collect.ImmutableSet;
import net.stormofsorts.agotmod.AGoTMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// A utility class for creating custom POI types and Villager professions
public class ModVillagers {

    // Deferred register for custom POI types
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, AGoTMod.MOD_ID);

    // Deferred register for custom Villager professions
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, AGoTMod.MOD_ID);

    // Custom POI type: MINT_POI
    public static final RegistryObject<PoiType> MINT_POI = POI_TYPES.register("mint_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.MAGENTA_GLAZED_TERRACOTTA.getStateDefinition().getPossibleStates()),
                    1, 1));

    // Custom Villager profession: MINTER
    public static final RegistryObject<VillagerProfession> MINTER =
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

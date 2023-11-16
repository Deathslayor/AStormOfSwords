package net.darkflameproduction.agotmod.villager;

import com.google.common.collect.ImmutableSet;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {

    // makes creating custom possible
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, AGoTMod.MOD_ID);
    // makes creating custom Professions possible
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, AGoTMod.MOD_ID);

    // Template for creating custom Villagers.
    public static final RegistryObject<PoiType> MINT_POI = POI_TYPES.register("mint_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.MAGENTA_GLAZED_TERRACOTTA.getStateDefinition().getPossibleStates()),
                    1,1));

    // Template creates the Villager itself
    public static final RegistryObject<VillagerProfession> MINTER =
            // Creates the name of the profession
            VILLAGER_PROFESSIONS.register("minter", () -> new VillagerProfession("minter",
                    holder -> holder.get() == MINT_POI.get(), holder -> holder.get() == MINT_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));




    // tells the AGoTMod class to call the CUSTOM VILLAGERS into the game
    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}

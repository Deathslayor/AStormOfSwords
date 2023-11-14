package net.deathslayor.agotmod.villager;

import net.deathslayor.agotmod.AGoTMod;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPE =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, AGoTMod.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONs =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, AGoTMod.MODID);



    public static void register(IEventBus eventBus) {
        POI_TYPE.register(eventBus);
        VILLAGER_PROFESSIONs.register(eventBus);
    }
}

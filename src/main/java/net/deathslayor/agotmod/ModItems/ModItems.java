package net.deathslayor.agotmod.ModItems;
import net.deathslayor.agotmod.AGoTMod;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AGoTMod.MODID);

    public static final RegistryObject<Item> BATTLEAXE = ITEMS.register("battleaxe",
            () ->new AxeItem(Tiers.NETHERITE,3,0,new Item.Properties()));
    public static final RegistryObject<Item> GOTSWORD = ITEMS.register("gotsword",
            () ->new AxeItem(Tiers.NETHERITE,3,0,new Item.Properties()));

    public static final RegistryObject<Item> NEDSWORD = ITEMS.register("nedsword",
            () ->new SwordItem(Tiers.NETHERITE,1000,3,new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

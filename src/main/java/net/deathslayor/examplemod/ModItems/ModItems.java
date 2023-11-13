package net.deathslayor.examplemod.ModItems;
import net.deathslayor.examplemod.ExampleMod;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);

    public static final RegistryObject<Item> BATTLEAXE = ITEMS.register("battleaxe",
            () ->new AxeItem(Tiers.NETHERITE,3,0,new Item.Properties()));
    public static final RegistryObject<Item> GOTSWORD = ITEMS.register("gotsword",
            () ->new AxeItem(Tiers.NETHERITE,3,0,new Item.Properties()));

    public static final RegistryObject<Item> NEDSWORD = ITEMS.register("nedsword",
            () ->new SwordItem(Tiers.NETHERITE,1000,3,new Item.Properties()));















    public static final RegistryObject<Item> STEEL = ITEMS.register("steel",
            () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANASHARD = ITEMS.register("manashard",
            () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELDERDRAGONFLESHB = ITEMS.register("elderdragonfleshb",
            () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELDERDRAGONFLESHW = ITEMS.register("elderdragonfleshw",
            () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELDERDRAGONHEARTB = ITEMS.register("elderdragonheartb",
            () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELDERDRAGONHEARTW = ITEMS.register("elderdragonheartw",
            () ->new Item(new Item.Properties()));








    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

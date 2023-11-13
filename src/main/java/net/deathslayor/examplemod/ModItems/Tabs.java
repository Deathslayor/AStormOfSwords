package net.deathslayor.examplemod.ModItems;
import net.deathslayor.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Tabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MODID);

    public static final RegistryObject<CreativeModeTab> MYSTIC_TAB = CREATIVE_MODE_TAB.register("got_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GOTSWORD.get()))
                    .title(Component.translatable("creativetab.got_tab"))
                    .displayItems((pParameters, pOutput) ->{


                        pOutput.accept(ModItems.BATTLEAXE.get());
                        pOutput.accept(ModItems.GOTSWORD.get());
                        pOutput.accept(ModItems.NEDSWORD.get());










                    })
                    .build());









    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);


    }
}

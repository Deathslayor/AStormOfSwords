package net.darkflameproduction.agotmod.item.creativetabs;


import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeAnimals {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_ANIMALS = CREATIVE_MODE_TAB.register("agot_tab_animals",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.agot_tab_animals"))
                    .icon(() -> new ItemStack(ModItems.MAMMOTH_SPAWN_EGG.get()))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.MAMMOTH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.DIREWOLF_SPAWN_EGG.get());
                        pOutput.accept(ModItems.CROW_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PEASANT_SPAWN_EGG.get());


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

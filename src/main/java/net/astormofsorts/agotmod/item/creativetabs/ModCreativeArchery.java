package net.astormofsorts.agotmod.item.creativetabs;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeArchery {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> AGOT_TAB_ARCHERY = CREATIVE_MODE_TAB.register("agot_tab_archery",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STEEL_BOW.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_archery")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {

                        // Bows
                        pOutput.accept(ModItems.STEEL_BOW.get());


                        // Arrows
                        pOutput.accept(ModItems.ARROW_BRONZE.get());
                        pOutput.accept(ModItems.ARROW_DRAGONGLASS.get());
                        pOutput.accept(ModItems.ARROW_STEEL.get());
                        pOutput.accept(ModItems.ARROW_IRON.get());


                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

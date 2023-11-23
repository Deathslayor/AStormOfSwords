package net.stormofsorts.agotmod;

import com.mojang.logging.LogUtils;
import net.stormofsorts.agotmod.block.ModBLocks;
import net.stormofsorts.agotmod.entity.ModEntities;
import net.stormofsorts.agotmod.entity.client.RhinoRenderer;
import net.stormofsorts.agotmod.item.ModCreativeTabs;
import net.stormofsorts.agotmod.item.ModItems;
import net.stormofsorts.agotmod.villager.ModVillagers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AGoTMod.MOD_ID)
public class AGoTMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "agotmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AGoTMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        // Adds Creative Mod Tabs
        ModCreativeTabs.register(modEventBus);

        // Adds Custom Villagers to the game
        ModVillagers.register(modEventBus);

        // Adds Custom Blocks to the game
        ModBLocks.register(modEventBus);

        // Adds Custom Items to the game
        ModItems.register(modEventBus);

        // Adds custom Entities to the game
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);
        }
    }
}

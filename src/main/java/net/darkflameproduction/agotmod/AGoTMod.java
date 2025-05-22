package net.darkflameproduction.agotmod;

import dev.tocraft.ctgen.impl.CTGClient;
import net.darkflameproduction.agotmod.client.ClientKeyInputEvents;
import net.darkflameproduction.agotmod.datagen.ModDimensionProvider;
import net.darkflameproduction.agotmod.gui.CustomGuiScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.item.ModItemProperties;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.item.creativetabs.*;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.darkflameproduction.agotmod.util.ModWoodTypes;
import net.darkflameproduction.agotmod.villager.ModVillagers;
import net.darkflameproduction.agotmod.worldgen.biome.ModTerrablender;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(AGoTMod.MOD_ID)
public class AGoTMod {
    public static final String MOD_ID = "agotmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public AGoTMod(IEventBus modEventBus) {
        ModCreativeBuildingBlocks.register(modEventBus);
        ModCreativeNaturalBlocks.register(modEventBus);
        ModCreativeToolsUtilities.register(modEventBus);
        ModCreativeWeapons.register(modEventBus);
        ModCreativeArmour.register(modEventBus);
        ModCreativeFoods.register(modEventBus);
        ModCreativeIngredients.register(modEventBus);
        ModCreativeAnimals.register(modEventBus);
        ModCreativeMagic.register(modEventBus);
        ModCreativeArchery.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModBLocks.register(modEventBus);
        ModItems.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModTerrablender.registerBiomes();
        ModEntities.register(modEventBus);
        LOGGER.info("AGOT Mod initialized");

        if (FMLEnvironment.dist == Dist.CLIENT) {
            registerClientEvents();
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void registerClientEvents() {
        NeoForge.EVENT_BUS.register(ClientKeyInputEvents.class);
        CTGClient.registerMenu(ModDimensionProvider.KNOWN_WORLD, CustomGuiScreen::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.WINTER_ROSE.getId(), ModBLocks.POTTED_WINTER_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.WILD_RADISH.getId(), ModBLocks.POTTED_WILD_RADISH);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.WHITE_ROSE.getId(), ModBLocks.POTTED_WHITE_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.THORN_BUSH.getId(), ModBLocks.POTTED_THORN_BUSH);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.THISTLE.getId(), ModBLocks.POTTED_THISTLE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.TANSY.getId(), ModBLocks.POTTED_TANSY);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.SPICEFLOWER.getId(), ModBLocks.POTTED_SPICEFLOWER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.SEDGE.getId(), ModBLocks.POTTED_SEDGE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.SAFFRON_CROCUS.getId(), ModBLocks.POTTED_SAFFRON_CROCUS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.ROSE.getId(), ModBLocks.POTTED_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.POISON_KISSES.getId(), ModBLocks.POTTED_POISON_KISSES);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.PENNYROYAL.getId(), ModBLocks.POTTED_PENNYROYAL);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.OPIUM_POPPY_WILD.getId(), ModBLocks.POTTED_OPIUM_POPPY_WILD);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.NIGHTSHADE.getId(), ModBLocks.POTTED_NIGHTSHADE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.MOONBLOOM.getId(), ModBLocks.POTTED_MOONBLOOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.LUNGWORT.getId(), ModBLocks.POTTED_LUNGWORT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.LIVERWORT.getId(), ModBLocks.POTTED_LIVERWORT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.LAVENDER.getId(), ModBLocks.POTTED_LAVENDER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.LADYS_LACE.getId(), ModBLocks.POTTED_LADYS_LACE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GORSE.getId(), ModBLocks.POTTED_GORSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GOLDENROD.getId(), ModBLocks.POTTED_GOLDENROD);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GOLDENCUP.getId(), ModBLocks.POTTED_GOLDENCUP);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GOATHEAD.getId(), ModBLocks.POTTED_GOATHEAD);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GINGER.getId(), ModBLocks.POTTED_GINGER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GILLYFLOWER.getId(), ModBLocks.POTTED_GILLYFLOWER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.FROSTFIRE.getId(), ModBLocks.POTTED_FROSTFIRE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.FORGET_ME_NOT.getId(), ModBLocks.POTTED_FORGET_ME_NOT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.EVENING_STAR.getId(), ModBLocks.POTTED_EVENING_STAR);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.DUSKY_ROSE.getId(), ModBLocks.POTTED_DUSKY_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.DRAGONS_BREATH.getId(), ModBLocks.POTTED_DRAGONS_BREATH);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.COLDSNAP.getId(), ModBLocks.POTTED_COLDSNAP);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.BLUE_ROSE.getId(), ModBLocks.POTTED_BLUE_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.BLOODBLOOM.getId(), ModBLocks.POTTED_BLOODBLOOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.BLACK_LOTUS.getId(), ModBLocks.POTTED_BLACK_LOTUS);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(@NotNull FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.WEIRWOOD);
            Sheets.addWoodType(ModWoodTypes.PINE);
            Sheets.addWoodType(ModWoodTypes.ASH);
            Sheets.addWoodType(ModWoodTypes.BEECH);
            Sheets.addWoodType(ModWoodTypes.CEDAR);
            Sheets.addWoodType(ModWoodTypes.CHESTNUT);
            Sheets.addWoodType(ModWoodTypes.HAWTHORN);
            Sheets.addWoodType(ModWoodTypes.IRONWOOD);
            Sheets.addWoodType(ModWoodTypes.SENTINEL);
            Sheets.addWoodType(ModWoodTypes.SYCAMORE);
            Sheets.addWoodType(ModWoodTypes.BLACKBARK);
            Sheets.addWoodType(ModWoodTypes.ASPEN);
            Sheets.addWoodType(ModWoodTypes.ALDER);
            Sheets.addWoodType(ModWoodTypes.BLACK_CHERRY);
            Sheets.addWoodType(ModWoodTypes.BLACK_OLIVE);
            Sheets.addWoodType(ModWoodTypes.CRABAPPLE);
            Sheets.addWoodType(ModWoodTypes.OLIVE);
            Sheets.addWoodType(ModWoodTypes.WHITE_CHERRY);
            Sheets.addWoodType(ModWoodTypes.RED_CHERRY);
            Sheets.addWoodType(ModWoodTypes.FIR);
            Sheets.addWoodType(ModWoodTypes.WILLOW);
            Sheets.addWoodType(ModWoodTypes.WORMTREE);
            event.enqueueWork(ModItemProperties::addCustomItemProperties);
        }
    }

    @Contract("_ -> new")
    @ApiStatus.Internal
    public static @NotNull ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
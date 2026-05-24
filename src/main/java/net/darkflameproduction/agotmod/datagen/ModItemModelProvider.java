package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanChiefArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingFurArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingLeatherArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingChiefArmorItem;
import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchRangerArmorItem;
import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchLeatherArmorItem;
import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchEliteArmorItem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.item.custom.BannerPatterns;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static net.darkflameproduction.agotmod.block.ModBLocks.BLACKSTONE_VARIANTS;

public class ModItemModelProvider extends ItemModelProvider {
    // Constructor for ModItemModelProvider
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        // Call the constructor of the superclass (ItemModelProvider)
        super(output, AGoTMod.MOD_ID, existingFileHelper);
    }



    // Method to register item models
    @Override
    protected void registerModels() {
        // ---------------------------(COINS)--------------------------- //
        // Register the item model for the COIN
        simpleItem(ModItems.COIN);
        // ---------------------------(SPAWN EGGS)--------------------------- //
        spawnEggItem(ModItems.MAMMOTH_SPAWN_EGG);
        spawnEggItem(ModItems.CROW_SPAWN_EGG);
        spawnEggItem(ModItems.DIREWOLF_SPAWN_EGG);
        spawnEggItem(ModItems.PEASANT_SPAWN_EGG);


        // ---------------------------(ARMOUR)--------------------------- //
        // Register item models for Armour
        //Stark
        simpleItemStarkLevy(ModItems.STARK_LEVY_HELMET);
        simpleItemStarkLevy(ModItems.STARK_LEVY_CHESTPLATE);
        simpleItemStarkLevy(ModItems.STARK_LEVY_LEGGINGS);
        simpleItemStarkLevy(ModItems.STARK_LEVY_BOOTS);
        //
        simpleItemStarkPlate(ModItems.STARK_PLATE_HELMET);
        simpleItemStarkPlate(ModItems.STARK_PLATE_CHESTPLATE);
        simpleItemStarkPlate(ModItems.STARK_PLATE_LEGGINGS);
        simpleItemStarkPlate(ModItems.STARK_PLATE_BOOTS);
        //
        simpleItemStarkNoblePlate(ModItems.STARK_NOBLE_PLATE_HELMET);
        simpleItemStarkNoblePlate(ModItems.STARK_NOBLE_PLATE_CHESTPLATE);
        simpleItemStarkNoblePlate(ModItems.STARK_NOBLE_PLATE_LEGGINGS);
        simpleItemStarkNoblePlate(ModItems.STARK_NOBLE_PLATE_BOOTS);
        //Bolten
        simpleItemBoltenLevy(ModItems.BOLTEN_LEVY_HELMET);
        simpleItemBoltenLevy(ModItems.BOLTEN_LEVY_CHESTPLATE);
        simpleItemBoltenLevy(ModItems.BOLTEN_LEVY_LEGGINGS);
        simpleItemBoltenLevy(ModItems.BOLTEN_LEVY_BOOTS);
        //
        simpleItemBoltenPlate(ModItems.BOLTEN_PLATE_HELMET);
        simpleItemBoltenPlate(ModItems.BOLTEN_PLATE_CHESTPLATE);
        simpleItemBoltenPlate(ModItems.BOLTEN_PLATE_LEGGINGS);
        simpleItemBoltenPlate(ModItems.BOLTEN_PLATE_BOOTS);
        //
        simpleItemBoltenNoblePlate(ModItems.BOLTEN_NOBLE_HELMET);
        simpleItemBoltenNoblePlate(ModItems.BOLTEN_NOBLE_CHESTPLATE);
        simpleItemBoltenNoblePlate(ModItems.BOLTEN_NOBLE_LEGGINGS);
        simpleItemBoltenNoblePlate(ModItems.BOLTEN_NOBLE_BOOTS);
        //Manderly
        simpleItemManderlyLevy(ModItems.MANDERLY_LEVY_HELMET);
        simpleItemManderlyLevy(ModItems.MANDERLY_LEVY_CHESTPLATE);
        simpleItemManderlyLevy(ModItems.MANDERLY_LEVY_LEGGINGS);
        simpleItemManderlyLevy(ModItems.MANDERLY_LEVY_BOOTS);
        //
        simpleItemManderlyPlate(ModItems.MANDERLY_PLATE_HELMET);
        simpleItemManderlyPlate(ModItems.MANDERLY_PLATE_CHESTPLATE);
        simpleItemManderlyPlate(ModItems.MANDERLY_PLATE_LEGGINGS);
        simpleItemManderlyPlate(ModItems.MANDERLY_PLATE_BOOTS);
        //
        simpleItemManderlyNoble(ModItems.MANDERLY_NOBLE_HELMET);
        simpleItemManderlyNoble(ModItems.MANDERLY_NOBLE_CHESTPLATE);
        simpleItemManderlyNoble(ModItems.MANDERLY_NOBLE_LEGGINGS);
        simpleItemManderlyNoble(ModItems.MANDERLY_NOBLE_BOOTS);
        //
        simpleItemThennLevy(ModItems.THENN_LEVY_HELMET);
        simpleItemThennLevy(ModItems.THENN_LEVY_CHESTPLATE);
        simpleItemThennLevy(ModItems.THENN_LEVY_LEGGINGS);
        simpleItemThennLevy(ModItems.THENN_LEVY_BOOTS);
        //
        simpleItemThennPlate(ModItems.THENN_PLATE_HELMET);
        simpleItemThennPlate(ModItems.THENN_PLATE_CHESTPLATE);
        simpleItemThennPlate(ModItems.THENN_PLATE_LEGGINGS);
        simpleItemThennPlate(ModItems.THENN_PLATE_BOOTS);
        //
        simpleItemThennNoble(ModItems.THENN_NOBLE_HELMET);
        simpleItemThennNoble(ModItems.THENN_NOBLE_CHESTPLATE);
        simpleItemThennNoble(ModItems.THENN_NOBLE_LEGGINGS);
        simpleItemThennNoble(ModItems.THENN_NOBLE_BOOTS);
        //
        simpleItemWildlingLeather(ModItems.WILDLING_LEATHER_HELMET);
        simpleItemWildlingLeather(ModItems.WILDLING_LEATHER_CHESTPLATE);
        simpleItemWildlingLeather(ModItems.WILDLING_LEATHER_LEGGINGS);
        simpleItemWildlingLeather(ModItems.WILDLING_LEATHER_BOOTS);
        //
        simpleItemWildlingFur(ModItems.WILDLING_FUR_HELMET);
        simpleItemWildlingFur(ModItems.WILDLING_FUR_CHESTPLATE);
        simpleItemWildlingFur(ModItems.WILDLING_FUR_LEGGINGS);
        simpleItemWildlingFur(ModItems.WILDLING_FUR_BOOTS);
        //
        simpleItemWildlingChief(ModItems.WILDLING_CHIEF_HELMET);
        simpleItemWildlingChief(ModItems.WILDLING_CHIEF_CHESTPLATE);
        simpleItemWildlingChief(ModItems.WILDLING_CHIEF_LEGGINGS);
        simpleItemWildlingChief(ModItems.WILDLING_CHIEF_BOOTS);
        //
        simpleItemIronbornLevy(ModItems.IRONBORN_LEVY_HELMET);
        simpleItemIronbornLevy(ModItems.IRONBORN_LEVY_CHESTPLATE);
        simpleItemIronbornLevy(ModItems.IRONBORN_LEVY_LEGGINGS);
        simpleItemIronbornLevy(ModItems.IRONBORN_LEVY_BOOTS);
        //
        simpleItemIronbornPlate(ModItems.IRONBORN_PLATE_HELMET);
        simpleItemIronbornPlate(ModItems.IRONBORN_PLATE_CHESTPLATE);
        simpleItemIronbornPlate(ModItems.IRONBORN_PLATE_LEGGINGS);
        simpleItemIronbornPlate(ModItems.IRONBORN_PLATE_BOOTS);
        //
        simpleItemIronbornNoble(ModItems.IRONBORN_NOBLE_HELMET);
        simpleItemIronbornNoble(ModItems.IRONBORN_NOBLE_CHESTPLATE);
        simpleItemIronbornNoble(ModItems.IRONBORN_NOBLE_LEGGINGS);
        simpleItemIronbornNoble(ModItems.IRONBORN_NOBLE_BOOTS);
        //
        simpleItemMountainClanLevy(ModItems.MOUNTAIN_CLAN_LEVY_HELMET);
        simpleItemMountainClanLevy(ModItems.MOUNTAIN_CLAN_LEVY_CHESTPLATE);
        simpleItemMountainClanLevy(ModItems.MOUNTAIN_CLAN_LEVY_LEGGINGS);
        simpleItemMountainClanLevy(ModItems.MOUNTAIN_CLAN_LEVY_BOOTS);
        //
        simpleItemMountainClanPlate(ModItems.MOUNTAIN_CLAN_PLATE_HELMET);
        simpleItemMountainClanPlate(ModItems.MOUNTAIN_CLAN_PLATE_CHESTPLATE);
        simpleItemMountainClanPlate(ModItems.MOUNTAIN_CLAN_PLATE_LEGGINGS);
        simpleItemMountainClanPlate(ModItems.MOUNTAIN_CLAN_PLATE_BOOTS);
        //
        simpleItemMountainClanChief(ModItems.MOUNTAIN_CLAN_CHIEF_HELMET);
        simpleItemMountainClanChief(ModItems.MOUNTAIN_CLAN_CHIEF_CHESTPLATE);
        simpleItemMountainClanChief(ModItems.MOUNTAIN_CLAN_CHIEF_LEGGINGS);
        simpleItemMountainClanChief(ModItems.MOUNTAIN_CLAN_CHIEF_BOOTS);
        //
        simpleItemNightWatchRanger(ModItems.NIGHT_WATCH_RANGER_HELMET);
        simpleItemNightWatchRanger(ModItems.NIGHT_WATCH_RANGER_CHESTPLATE);
        simpleItemNightWatchRanger(ModItems.NIGHT_WATCH_RANGER_LEGGINGS);
        simpleItemNightWatchRanger(ModItems.NIGHT_WATCH_RANGER_BOOTS);
        //
        simpleItemNightWatchLeather(ModItems.NIGHT_WATCH_LEATHER_HELMET);
        simpleItemNightWatchLeather(ModItems.NIGHT_WATCH_LEATHER_CHESTPLATE);
        simpleItemNightWatchLeather(ModItems.NIGHT_WATCH_LEATHER_LEGGINGS);
        simpleItemNightWatchLeather(ModItems.NIGHT_WATCH_LEATHER_BOOTS);
        //
        simpleItemNightWatchElite(ModItems.NIGHT_WATCH_ELITE_HELMET);
        simpleItemNightWatchElite(ModItems.NIGHT_WATCH_ELITE_CHESTPLATE);
        simpleItemNightWatchElite(ModItems.NIGHT_WATCH_ELITE_LEGGINGS);
        simpleItemNightWatchElite(ModItems.NIGHT_WATCH_ELITE_BOOTS);

        // ---------------------------(COINS)--------------------------- //
        simpleItem(ModItems.COPPER_STAR);
        simpleItem(ModItems.COPPER_GROAT);
        simpleItem(ModItems.COPPER_HALFPENNY);
        simpleItem(ModItems.COPPER_PENNY);
        simpleItem(ModItems.GOLD_DRAGON);
        simpleItem(ModItems.SILVER_MOON);
        simpleItem(ModItems.SILVER_STAG);
        simpleItem(ModItems.COPPER_HALFGROAT);


        // ---------------------------(INGOTS)--------------------------- //
        // Register item models for ingots and nuggets
        simpleItem(ModItems.RAW_SILVER);
        simpleItem(ModItems.SILVER_INGOT);
        simpleItem(ModItems.SILVER_NUGGET);
        simpleItem(ModItems.RAW_TIN);
        simpleItem(ModItems.TIN_INGOT);
        simpleItem(ModItems.BRONZE_INGOT);
        simpleItem(ModItems.BRONZE_NUGGET);
        simpleItem(ModItems.STEEL_INGOT);
        simpleItem(ModItems.STEEL_NUGGET);
        simpleItem(ModItems.YELLOW_DIAMOND);
        simpleItem(ModItems.TRANSPARENT_DIAMOND);
        simpleItem(ModItems.AMBER);
        simpleItem(ModItems.AMETHYST);
        simpleItem(ModItems.BLACK_DIAMOND);
        simpleItem(ModItems.BLOODSTONE);
        simpleItem(ModItems.CARNELIAN);
        simpleItem(ModItems.CHALCEDONY);
        simpleItem(ModItems.GARNET);
        simpleItem(ModItems.JADE);
        simpleItem(ModItems.JASPER);
        simpleItem(ModItems.MALACHITE);
        simpleItem(ModItems.MOONSTONE);
        simpleItem(ModItems.ONYX);
        simpleItem(ModItems.OPAL);
        simpleItem(ModItems.RUBY);
        simpleItem(ModItems.SAPPHIRE);
        simpleItem(ModItems.TIGERS_EYE);
        simpleItem(ModItems.TOPAZ);
        simpleItem(ModItems.TOURMALINE);

        // ---------------------------(INGOTS)--------------------------- //

        // ---------------------------(Weapons)--------------------------- //
        weaponItem(ModItems.DRAGONGLASS_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.DRAGONGLASS_DAGGER);
        weaponItem(ModItems.BRONZE_SPATHA,
                new float[]{0.0f, 6.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.BRONZE_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.BRONZE_PIKE,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{3f, 3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.4f, 1.4f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.BRONZE_DAGGER);
        weaponItem(ModItems.BRONZE_BATTLEAXE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.IRON_LONGSWORD,
                new float[]{0.0f, 6.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.IRON_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.IRON_PIKE,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{3f, 3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.4f, 1.4f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.IRON_DAGGER);
        weaponItem(ModItems.IRON_MACE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.IRON_BATTLEAXE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_LONGSWORD,
                new float[]{0.0f, 6.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_PIKE,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{3f, 3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.4f, 1.4f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.STEEL_DAGGER);
        weaponItem(ModItems.STEEL_MACE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_BATTLEAXE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_HALBERD,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_LONGSWORD,
                new float[]{0.0f, 6.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_PIKE,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{3f, 3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.4f, 1.4f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.NOBLE_DAGGER);
        weaponItem(ModItems.NOBLE_MACE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_BATTLEAXE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_HALBERD,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );


        // ---------------------------(TOOLS)--------------------------- //
        handheldItem(ModItems.BRONZE_AXE);
        handheldItem(ModItems.BRONZE_PICKAXE);
        handheldItem(ModItems.BRONZE_HOE);
        handheldItem(ModItems.BRONZE_SWORD);
        handheldItem(ModItems.BRONZE_SHOVEL);
        handheldItem(ModItems.STEEL_AXE);
        handheldItem(ModItems.STEEL_PICKAXE);
        handheldItem(ModItems.STEEL_HOE);
        handheldItem(ModItems.STEEL_SWORD);
        handheldItem(ModItems.STEEL_SHOVEL);
        handheldItem(ModItems.BLOOD_DAGGER);

        // ---------------------------(MAGIC)--------------------------- //
        handheldItem(ModItems.BLOOD_BOTTLED);




// ── Fowl ─────────────────────────────────────────────────────────────────
        simpleItem(ModItems.RAW_PIGEON);
        simpleItem(ModItems.COOKED_PIGEON);
        simpleItem(ModItems.RAW_DUCK);
        simpleItem(ModItems.COOKED_DUCK);
        simpleItem(ModItems.RAW_GOOSE);
        simpleItem(ModItems.COOKED_GOOSE);
        simpleItem(ModItems.RAW_GULL);
        simpleItem(ModItems.COOKED_GULL);
        simpleItem(ModItems.RAW_HERON);
        simpleItem(ModItems.COOKED_HERON);
        simpleItem(ModItems.RAW_LARK);
        simpleItem(ModItems.COOKED_LARK);
        simpleItem(ModItems.RAW_PARTRIDGE);
        simpleItem(ModItems.COOKED_PARTRIDGE);
        simpleItem(ModItems.RAW_PEACOCK);
        simpleItem(ModItems.COOKED_PEACOCK);
        simpleItem(ModItems.RAW_QUAIL);
        simpleItem(ModItems.COOKED_QUAIL);
        simpleItem(ModItems.RAW_SWAN);
        simpleItem(ModItems.COOKED_SWAN);
        simpleItem(ModItems.RAW_CHICKEN_NUGGETS);
        simpleItem(ModItems.COOKED_CHICKEN_NUGGETS);

// ── Meats ─────────────────────────────────────────────────────────────────
        simpleItem(ModItems.RAW_AUROCHS);
        simpleItem(ModItems.COOKED_AUROCHS);
        simpleItem(ModItems.RAW_BEAR_MEAT);
        simpleItem(ModItems.COOKED_BEAR_MEAT);
        simpleItem(ModItems.RAW_BOAR_VENISON);
        simpleItem(ModItems.COOKED_BOAR_VENISON);
        simpleItem(ModItems.RAW_DEER_VENISON);
        simpleItem(ModItems.COOKED_DEER_VENISON);
        simpleItem(ModItems.RAW_DOG);
        simpleItem(ModItems.COOKED_DOG);
        simpleItem(ModItems.RAW_DORMICE);
        simpleItem(ModItems.COOKED_DORMICE);
        simpleItem(ModItems.RAW_FROG);
        simpleItem(ModItems.COOKED_FROG);
        simpleItem(ModItems.RAW_GOAT_MEAT);
        simpleItem(ModItems.COOKED_GOAT_MEAT);
        simpleItem(ModItems.RAW_HARE_MEAT);
        simpleItem(ModItems.COOKED_HARE_MEAT);
        simpleItem(ModItems.RAW_HORSE_MEAT);
        simpleItem(ModItems.COOKED_HORSE_MEAT);
        simpleItem(ModItems.RAW_LAMB);
        simpleItem(ModItems.COOKED_LAMB);
        simpleItem(ModItems.RAW_LOCUSTS);
        simpleItem(ModItems.COOKED_LOCUSTS);
        simpleItem(ModItems.RAW_MAMMOTH_MEAT);
        simpleItem(ModItems.COOKED_MAMMOTH_MEAT);
        simpleItem(ModItems.RAW_MAN_FLESH);
        simpleItem(ModItems.COOKED_MAN_FLESH);
        simpleItem(ModItems.RAW_RAT);
        simpleItem(ModItems.COOKED_RAT);
        simpleItem(ModItems.RAW_SNAKE);
        simpleItem(ModItems.COOKED_SNAKE);
        simpleItem(ModItems.RAW_SNAIL);
        simpleItem(ModItems.COOKED_SNAIL);
        simpleItem(ModItems.RAW_SQUIRREL);
        simpleItem(ModItems.COOKED_SQUIRREL);
        simpleItem(ModItems.RAW_SUCKLING_PIG);
        simpleItem(ModItems.COOKED_SUCKLING_PIG);

// ── Offal & Organ Meats ───────────────────────────────────────────────────
        simpleItem(ModItems.RAW_BACON);
        simpleItem(ModItems.COOKED_BACON);
        simpleItem(ModItems.RAW_PIG_KIDNEYS);
        simpleItem(ModItems.COOKED_PIG_KIDNEYS);
        simpleItem(ModItems.RAW_PIG_LIVER);
        simpleItem(ModItems.COOKED_PIG_LIVER);
        simpleItem(ModItems.RAW_PIG_RIBS);
        simpleItem(ModItems.COOKED_PIG_RIBS);
        simpleItem(ModItems.RAW_GOOSE_LIVER);
        simpleItem(ModItems.COOKED_GOOSE_LIVER);
        simpleItem(ModItems.RAW_CALF_PANCREAS);
        simpleItem(ModItems.COOKED_CALF_PANCREAS);
        simpleItem(ModItems.HORSE_HEART);
        simpleItem(ModItems.JELLIED_CALVES_BRAIN);

// ── Sausages ─────────────────────────────────────────────────────────────
        simpleItem(ModItems.RAW_SAUSAGE);
        simpleItem(ModItems.COOKED_SAUSAGE);
        simpleItem(ModItems.RAW_BLOOD_SAUSAGE);
        simpleItem(ModItems.COOKED_BLOOD_SAUSAGE);
        simpleItem(ModItems.RAW_WHITE_SAUSAGE);
        simpleItem(ModItems.COOKED_WHITE_SAUSAGE);

// ── Other Ingredient Meats ────────────────────────────────────────────────
        simpleItem(ModItems.MINCED_MEAT);
        simpleItem(ModItems.PIG_INTESTINES);
        simpleItem(ModItems.CALF_BRAIN);

// ── Fish & Seafood ────────────────────────────────────────────────────────
        simpleItem(ModItems.RAW_HERRING);
        simpleItem(ModItems.COOKED_HERRING);
        simpleItem(ModItems.RAW_TROUT);
        simpleItem(ModItems.COOKED_TROUT);
        simpleItem(ModItems.RAW_EEL);
        simpleItem(ModItems.COOKED_EEL);
        simpleItem(ModItems.RAW_LAMPREY);
        simpleItem(ModItems.COOKED_LAMPREY);
        simpleItem(ModItems.RAW_PIKE);
        simpleItem(ModItems.COOKED_PIKE);
        simpleItem(ModItems.RAW_SARDINE);
        simpleItem(ModItems.COOKED_SARDINE);
        simpleItem(ModItems.RAW_MONKFISH);
        simpleItem(ModItems.COOKED_MONKFISH);
        simpleItem(ModItems.RAW_OCTOPUS);
        simpleItem(ModItems.COOKED_OCTOPUS);
        simpleItem(ModItems.RAW_WHITEFISH);
        simpleItem(ModItems.COOKED_WHITEFISH);
        simpleItem(ModItems.RAW_CRAB);
        simpleItem(ModItems.COOKED_CRAB);
        simpleItem(ModItems.RAW_LOBSTER);
        simpleItem(ModItems.COOKED_LOBSTER);
        simpleItem(ModItems.RAW_CLAM);
        simpleItem(ModItems.COOKED_CLAM);
        simpleItem(ModItems.RAW_MUSSELS);
        simpleItem(ModItems.COOKED_MUSSELS);
        simpleItem(ModItems.RAW_WINKLES);
        simpleItem(ModItems.COOKED_WINKLES);

// ── Prepared & Preserved ─────────────────────────────────────────────────
        simpleItem(ModItems.PICKLED_HERRING);
        simpleItem(ModItems.HONEYED_LOCUSTS);
        simpleItem(ModItems.BEEF_AND_BARLEY_STEW);
        simpleItem(ModItems.BEEF_STEW);
        simpleItem(ModItems.MUTTON_STEW);
        simpleItem(ModItems.SISTERS_STEW);
        simpleItem(ModItems.BOWL_OF_BROWN);
        simpleItem(ModItems.BREWIS);
        simpleItem(ModItems.JERKY);
        simpleItem(ModItems.HAM);

// ── Baked Goods & Prepared Dishes ───────────────────────────
        simpleItem(ModItems.BISCUITS);
        simpleItem(ModItems.BOWL_OF_BROWN);
        simpleItem(ModItems.BARLEY_BREAD);
        simpleItem(ModItems.BLACK_BREAD);
        simpleItem(ModItems.HARD_BREAD);
        simpleItem(ModItems.OATCAKE);
        simpleItem(ModItems.BLACKBERRY_OATCAKE);
        simpleItem(ModItems.PINENUT_OATCAKE);
        simpleItem(ModItems.COD_CAKE);
        simpleItem(ModItems.CREAM_CAKES);
        simpleItem(ModItems.HONEY_CAKE);
        simpleItem(ModItems.HONEYFINGERS);
        simpleItem(ModItems.LEMON_CAKE);
        simpleItem(ModItems.SPECIAL_CAKE);
        simpleItem(ModItems.STALE_CAKE);
        simpleItem(ModItems.SWEET_CAKE);
        simpleItem(ModItems.WINTERCAKE);
        simpleItem(ModItems.PIGEON_PIE);
        simpleItem(ModItems.VENISON_PIE);
        simpleItem(ModItems.LAMPREY_PIE);
        simpleItem(ModItems.LOCUST_PIE);
        simpleItem(ModItems.STRAWBERRY_PIE);
        simpleItem(ModItems.APPLE_TART);
        simpleItem(ModItems.BERRY_TARTS);
        simpleItem(ModItems.SISTERS_STEW);
        simpleItem(ModItems.PEA_SOUP);

// ── General ─────────────────────────────────────────────────
        simpleItem(ModItems.CHEESE);
        simpleItem(ModItems.GRAVY);
        simpleItem(ModItems.HONEYCOMB_FOOD);
        simpleItem(ModItems.JAMS);
        simpleItem(ModItems.JELLIES);
        simpleItem(ModItems.SHERBET);

// ── Cereals ─────────────────────────────────────────────────
        simpleItem(ModItems.BARLEY);
        simpleItem(ModItems.BLACK_RICE);
        simpleItem(ModItems.OAT);
        simpleItem(ModItems.RYE);
        simpleItem(ModItems.AUTUMN_WHEAT);
        simpleItem(ModItems.WINTER_WHEAT);
        simpleItem(ModItems.CORN);

// ── Vegetables ──────────────────────────────────────────────
        simpleItem(ModItems.BEAN);
        simpleItem(ModItems.CABBAGE);
        simpleItem(ModItems.CHICKPEA);
        simpleItem(ModItems.CUCUMBER);
        simpleItem(ModItems.DRAGON_PEPPER);
        simpleItem(ModItems.GARLIC);
        simpleItem(ModItems.GREEN_BEAN);
        simpleItem(ModItems.HORSERADISH);
        simpleItem(ModItems.LEEK);
        simpleItem(ModItems.LENTILS);
        simpleItem(ModItems.NEEP);
        simpleItem(ModItems.OLIVES);
        simpleItem(ModItems.OLIVE_OIL);
        simpleItem(ModItems.ONION);
        simpleItem(ModItems.PARSLEY);
        simpleItem(ModItems.PEAS);
        simpleItem(ModItems.PEPPER);
        simpleItem(ModItems.PEPPERCORN);
        simpleItem(ModItems.RADISH);
        simpleItem(ModItems.RED_ONION);
        simpleItem(ModItems.SPINACH);
        simpleItem(ModItems.SQUASH);
        simpleItem(ModItems.TURNIP);
        simpleItem(ModItems.WILD_ONION);

// ── Fruits ──────────────────────────────────────────────────
        simpleItem(ModItems.APRICOT);
        simpleItem(ModItems.BLACKBERRY);
        simpleItem(ModItems.BLOOD_ORANGE);
        simpleItem(ModItems.BLUEBERRY);
        simpleItem(ModItems.CHERRY);
        simpleItem(ModItems.CRABAPPLE);
        simpleItem(ModItems.DATE);
        simpleItem(ModItems.DORNISH_PLUM);
        simpleItem(ModItems.DRIED_APPLES);
        simpleItem(ModItems.FIG);
        simpleItem(ModItems.FIREPLUM);
        simpleItem(ModItems.GRAPE);
        simpleItem(ModItems.LEMON);
        simpleItem(ModItems.LIME);
        simpleItem(ModItems.MELON_SLICE_AGOT);
        simpleItem(ModItems.MULBERRY);
        simpleItem(ModItems.ORANGE);
        simpleItem(ModItems.PEACH);
        simpleItem(ModItems.PEAR);
        simpleItem(ModItems.PERSIMMON);
        simpleItem(ModItems.PLUM);
        simpleItem(ModItems.POMEGRANATE);
        simpleItem(ModItems.RAISINS);
        simpleItem(ModItems.RASPBERRY);
        simpleItem(ModItems.SMOKEBERRY);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.WINTER_PEACH);

// ── Nuts ────────────────────────────────────────────────────
        simpleItem(ModItems.ALMOND);
        simpleItem(ModItems.CHESTNUT);
        simpleItem(ModItems.PECAN);
        simpleItem(ModItems.PINE_NUT);
        simpleItem(ModItems.WALNUT);

// ── Herbs & Spices ──────────────────────────────────────────
        simpleItem(ModItems.CARDAMOM);
        simpleItem(ModItems.CINNAMON);
        simpleItem(ModItems.CLARY_SAGE);
        simpleItem(ModItems.CLOVES);
        simpleItem(ModItems.CORIANDER);
        simpleItem(ModItems.COTTON);
        simpleItem(ModItems.CURRY);
        simpleItem(ModItems.FENNEL);
        //simpleItem(ModItems.GINGER);
        simpleItem(ModItems.HEMP);
        simpleItem(ModItems.HONEY);
        simpleItem(ModItems.LEMONGRASS);
        simpleItem(ModItems.MINT);
        simpleItem(ModItems.MOLASSES);
        simpleItem(ModItems.MUSTARD_SEED);
        simpleItem(ModItems.NUTMEG);
        simpleItem(ModItems.PARSLEY);
        simpleItem(ModItems.SAFFRON);
        simpleItem(ModItems.SAGE);
        simpleItem(ModItems.SALT);

// ── Drinks — Beer ───────────────────────────────────────────
        simpleItem(ModItems.BEER);
        simpleItem(ModItems.ALE);
        simpleItem(ModItems.STOUT);

// ── Drinks — Liquor ─────────────────────────────────────────
        simpleItem(ModItems.RUM);
        simpleItem(ModItems.BLACKBELLY_RUM);
        simpleItem(ModItems.BLACK_TAR_RUM);
        simpleItem(ModItems.SPICED_RUM);
        simpleItem(ModItems.MYRISH_FIRE);
        simpleItem(ModItems.TYROSHI_PEAR_BRANDY);

// ── Drinks — Milk ───────────────────────────────────────────
        simpleItem(ModItems.ALMOND_MILK);
        simpleItem(ModItems.GOAT_MILK);
        simpleItem(ModItems.MARES_MILK);
        simpleItem(ModItems.FERMENTED_MILK);
        simpleItem(ModItems.NAHSA);
        simpleItem(ModItems.ICED_MILK);

// ── Drinks — Tea ────────────────────────────────────────────
        simpleItem(ModItems.TEA);
        simpleItem(ModItems.MINT_TEA);
        simpleItem(ModItems.NETTLE_TEA);
        simpleItem(ModItems.MOON_TEA);

// ── Drinks — Wine ───────────────────────────────────────────
        simpleItem(ModItems.WINE);
        simpleItem(ModItems.APPLE_WINE);
        simpleItem(ModItems.GREEN_APPLE_WINE);
        simpleItem(ModItems.ARBOR_GOLD);
        simpleItem(ModItems.ARBOR_RED);
        simpleItem(ModItems.DORNISH_RED);
        simpleItem(ModItems.DREAMWINE);
        simpleItem(ModItems.HONEYED_WINE);
        simpleItem(ModItems.SPICED_WINE);
        simpleItem(ModItems.STRONGWINE);
        simpleItem(ModItems.SUMMERWINE);
        simpleItem(ModItems.WINE_OF_COURAGE);

// ── Drinks — Other ──────────────────────────────────────────
        simpleItem(ModItems.CIDER);
        simpleItem(ModItems.HIPPOCRAS);
        simpleItem(ModItems.MEAD);
        simpleItem(ModItems.SHADE_OF_THE_EVENING);
        simpleItem(ModItems.SUGAR_WATER);




        // ---------------------------(SEEDS)--------------------------- //
        simpleItem(ModItems.HORSERADISH_SEEDS);
        simpleItem(ModItems.BARLEY_SEEDS);
        simpleItem(ModItems.GARLIC);
        simpleItem(ModItems.LEEK_SEEDS);
        simpleItem(ModItems.NEEP_SEEDS);
        simpleItem(ModItems.OAT_SEEDS);
        simpleItem(ModItems.PARSLEY_SEEDS);
        simpleItem(ModItems.RED_ONION_SEEDS);
        simpleItem(ModItems.TURNIP_SEEDS);
        simpleItem(ModItems.WILD_ONION_SEEDS);
        simpleItem(ModItems.ONION_SEEDS);
        simpleItem(ModItems.OPIUM_POPPY_SEEDS);
        simpleItem(ModItems.CABBAGE_SEEDS);
        simpleItem(ModItems.BEAN_SEEDS);
        simpleItem(ModItems.CHICKPEA_SEEDS);
        simpleItem(ModItems.CUCUMBER_SEEDS);
        simpleItem(ModItems.GREEN_BEAN_SEEDS);
        simpleItem(ModItems.SPINACH_SEEDS);
        simpleItem(ModItems.DRAGON_PEPPER_SEEDS);
        simpleItem(ModItems.PEPPER_SEEDS);
        simpleItem(ModItems.PEPPERCORN_SEEDS);
        simpleItem(ModItems.COTTON_SEEDS);
        simpleItem(ModItems.HEMP_SEEDS);
        simpleItem(ModItems.STRAWBERRY_SEEDS);
        simpleItem(ModItems.BLACKBERRY_SEEDS);
        simpleItem(ModItems.BLUEBERRY_SEEDS);
        simpleItem(ModItems.MULBERRY_SEEDS);
        simpleItem(ModItems.RASPBERRY_SEEDS);
        simpleItem(ModItems.SMOKEBERRY_SEEDS);
        simpleItem(ModItems.CORN_SEEDS);
        simpleItem(ModItems.CORN_MIDDLE_SEEDS);
        simpleItem(ModItems.CORN_TOP_SEEDS);

        // ---------------------------(JOBBLOCKS)--------------------------- //

// Town Hall (existing)
        simpleItem(ModItems.TOWN_HALL);

// All Job Barrel Item Model Generation
        simpleItem(ModItems.ALEHOUSE_BARREL);
        simpleItem(ModItems.ARMORSMITH_BARREL);
        simpleItem(ModItems.BAKER_BARREL);
        simpleItem(ModItems.BANKER_BARREL);
        simpleItem(ModItems.BARD_BARREL);
        simpleItem(ModItems.BLACKSMITH_BARREL);
        simpleItem(ModItems.BUILDER_BARREL);
        simpleItem(ModItems.BUTCHER_BARREL);
        simpleItem(ModItems.CARAVAN_MASTER_BARREL);
        simpleItem(ModItems.CARPENTER_BARREL);
        simpleItem(ModItems.CATTLE_HERDER_BARREL);
        simpleItem(ModItems.CHARCOAL_BURNER_BARREL);
        simpleItem(ModItems.CHICKEN_BREEDER_BARREL);
        simpleItem(ModItems.FARMER_BARREL);
        simpleItem(ModItems.GOAT_HERDER_BARREL);
        simpleItem(ModItems.GROCER_BARREL);
        simpleItem(ModItems.GUARD_BARREL);
        simpleItem(ModItems.HERBALIST_BARREL);
        simpleItem(ModItems.HORSE_BREEDER_BARREL);
        simpleItem(ModItems.HUNTER_BARREL);
        simpleItem(ModItems.INNKEEPER_BARREL);
        simpleItem(ModItems.JEWELER_BARREL);
        simpleItem(ModItems.LUMBERJACK_BARREL);
        simpleItem(ModItems.MAESTER_BARREL);
        simpleItem(ModItems.MINER_BARREL);
        simpleItem(ModItems.PIG_BREEDER_BARREL);
        simpleItem(ModItems.PYROMANCER_BARREL);
        simpleItem(ModItems.QUARRY_BARREL);
        simpleItem(ModItems.SCRIBE_BARREL);
        simpleItem(ModItems.SEPTON_BARREL);
        simpleItem(ModItems.SHEEP_HERDER_BARREL);
        simpleItem(ModItems.SHIPWRIGHT_BARREL);
        simpleItem(ModItems.SLAVER_BARREL);
        simpleItem(ModItems.SMELTER_BARREL);
        simpleItem(ModItems.STONEMASON_BARREL);
        simpleItem(ModItems.SWORDSMITH_BARREL);
        simpleItem(ModItems.TAILOR_BARREL);
        simpleItem(ModItems.TANNER_BARREL);
        simpleItem(ModItems.TRADER_BARREL);

        // ---------------------------(FURNITURE)--------------------------- //
        // Tables
        furnitureItem(ModItems.OAK_TABLE);
        furnitureItem(ModItems.DARK_OAK_TABLE);
        furnitureItem(ModItems.SPRUCE_TABLE);
        furnitureItem(ModItems.BIRCH_TABLE);
        furnitureItem(ModItems.JUNGLE_TABLE);
        furnitureItem(ModItems.ACACIA_TABLE);
        furnitureItem(ModItems.MANGROVE_TABLE);
        furnitureItem(ModItems.CHERRY_TABLE);
        furnitureItem(ModItems.BAMBOO_TABLE);
        furnitureItem(ModItems.CRIMSON_TABLE);
        furnitureItem(ModItems.WARPED_TABLE);
        furnitureItem(ModItems.WEIRWOOD_TABLE);
        furnitureItem(ModItems.CHARRED_TABLE);
        furnitureItem(ModItems.ROTTEN_TABLE);
        furnitureItem(ModItems.PINE_TABLE);
        furnitureItem(ModItems.ASH_TABLE);
        furnitureItem(ModItems.BEECH_TABLE);
        furnitureItem(ModItems.CEDAR_TABLE);
        furnitureItem(ModItems.CHESTNUT_TABLE);
        furnitureItem(ModItems.HAWTHORN_TABLE);
        furnitureItem(ModItems.IRONWOOD_TABLE);
        furnitureItem(ModItems.SENTINEL_TABLE);
        furnitureItem(ModItems.SYCAMORE_TABLE);
        furnitureItem(ModItems.BLACKBARK_TABLE);
        furnitureItem(ModItems.ASPEN_TABLE);
        furnitureItem(ModItems.ALDER_TABLE);
        furnitureItem(ModItems.BLACK_CHERRY_TABLE);
        furnitureItem(ModItems.BLACK_OLIVE_TABLE);
        furnitureItem(ModItems.CRABAPPLE_TABLE);
        furnitureItem(ModItems.OLIVE_TABLE);
        furnitureItem(ModItems.WHITE_CHERRY_TABLE);
        furnitureItem(ModItems.RED_CHERRY_TABLE);
        furnitureItem(ModItems.FIR_TABLE);
        furnitureItem(ModItems.WILLOW_TABLE);
        furnitureItem(ModItems.WORMTREE_TABLE);
        furnitureItem(ModItems.ALMOND_TABLE);
        furnitureItem(ModItems.APPLE_TABLE);
        furnitureItem(ModItems.APRICOT_TABLE);
        furnitureItem(ModItems.BAOBAB_TABLE);
        furnitureItem(ModItems.BLACK_COTTONWOOD_TABLE);
        furnitureItem(ModItems.BLACKTHORN_TABLE);
        furnitureItem(ModItems.BLOOD_ORANGE_TABLE);
        furnitureItem(ModItems.BLOODWOOD_TABLE);
        furnitureItem(ModItems.BLUE_MAHOE_TABLE);
        furnitureItem(ModItems.COTTONWOOD_TABLE);
        furnitureItem(ModItems.DATEPALM_TABLE);
        furnitureItem(ModItems.EBONY_TABLE);
        furnitureItem(ModItems.FIG_TABLE);
        furnitureItem(ModItems.FIREPLUM_TABLE);
        furnitureItem(ModItems.GOLDENHEART_TABLE);
        furnitureItem(ModItems.LEMON_TABLE);
        furnitureItem(ModItems.LIME_TABLE);
        furnitureItem(ModItems.LINDEN_TABLE);
        furnitureItem(ModItems.MAHOGANY_TABLE);
        furnitureItem(ModItems.MAPLE_TABLE);
        furnitureItem(ModItems.MYRRH_TABLE);
        furnitureItem(ModItems.NIGHTWOOD_TABLE);
        furnitureItem(ModItems.NUTMEG_TABLE);
        furnitureItem(ModItems.ORANGE_TABLE);
        furnitureItem(ModItems.PEACH_TABLE);
        furnitureItem(ModItems.PEAR_TABLE);
        furnitureItem(ModItems.PECAN_TABLE);
        furnitureItem(ModItems.PERSIMMON_TABLE);
        furnitureItem(ModItems.PINK_IVORY_TABLE);
        furnitureItem(ModItems.PLUM_TABLE);
        furnitureItem(ModItems.POMEGRANATE_TABLE);
        furnitureItem(ModItems.PURPLEHEART_TABLE);
        furnitureItem(ModItems.REDWOOD_TABLE);
        furnitureItem(ModItems.SANDALWOOD_TABLE);
        furnitureItem(ModItems.SANDBEGGAR_TABLE);
        furnitureItem(ModItems.TIGERWOOD_TABLE);
        furnitureItem(ModItems.YEW_TABLE);
        furnitureItem(ModItems.SOLDIER_PINE_TABLE);
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_TABLE);



// Stools
        furnitureItem(ModItems.DARK_OAK_STOOL);
        furnitureItem(ModItems.OAK_STOOL);
        furnitureItem(ModItems.SPRUCE_STOOL);
        furnitureItem(ModItems.BIRCH_STOOL);
        furnitureItem(ModItems.JUNGLE_STOOL);
        furnitureItem(ModItems.ACACIA_STOOL);
        furnitureItem(ModItems.MANGROVE_STOOL);
        furnitureItem(ModItems.CHERRY_STOOL);
        furnitureItem(ModItems.BAMBOO_STOOL);
        furnitureItem(ModItems.CRIMSON_STOOL);
        furnitureItem(ModItems.WARPED_STOOL);
        furnitureItem(ModItems.WEIRWOOD_STOOL);
        furnitureItem(ModItems.CHARRED_STOOL);
        furnitureItem(ModItems.ROTTEN_STOOL);
        furnitureItem(ModItems.PINE_STOOL);
        furnitureItem(ModItems.ASH_STOOL);
        furnitureItem(ModItems.BEECH_STOOL);
        furnitureItem(ModItems.CEDAR_STOOL);
        furnitureItem(ModItems.CHESTNUT_STOOL);
        furnitureItem(ModItems.HAWTHORN_STOOL);
        furnitureItem(ModItems.IRONWOOD_STOOL);
        furnitureItem(ModItems.SENTINEL_STOOL);
        furnitureItem(ModItems.SYCAMORE_STOOL);
        furnitureItem(ModItems.BLACKBARK_STOOL);
        furnitureItem(ModItems.ASPEN_STOOL);
        furnitureItem(ModItems.ALDER_STOOL);
        furnitureItem(ModItems.BLACK_CHERRY_STOOL);
        furnitureItem(ModItems.BLACK_OLIVE_STOOL);
        furnitureItem(ModItems.CRABAPPLE_STOOL);
        furnitureItem(ModItems.OLIVE_STOOL);
        furnitureItem(ModItems.WHITE_CHERRY_STOOL);
        furnitureItem(ModItems.RED_CHERRY_STOOL);
        furnitureItem(ModItems.FIR_STOOL);
        furnitureItem(ModItems.WILLOW_STOOL);
        furnitureItem(ModItems.WORMTREE_STOOL);
        furnitureItem(ModItems.ALMOND_STOOL);
        furnitureItem(ModItems.APPLE_STOOL);
        furnitureItem(ModItems.APRICOT_STOOL);
        furnitureItem(ModItems.BAOBAB_STOOL);
        furnitureItem(ModItems.BLACK_COTTONWOOD_STOOL);
        furnitureItem(ModItems.BLACKTHORN_STOOL);
        furnitureItem(ModItems.BLOOD_ORANGE_STOOL);
        furnitureItem(ModItems.BLOODWOOD_STOOL);
        furnitureItem(ModItems.BLUE_MAHOE_STOOL);
        furnitureItem(ModItems.COTTONWOOD_STOOL);
        furnitureItem(ModItems.DATEPALM_STOOL);
        furnitureItem(ModItems.EBONY_STOOL);
        furnitureItem(ModItems.FIG_STOOL);
        furnitureItem(ModItems.FIREPLUM_STOOL);
        furnitureItem(ModItems.GOLDENHEART_STOOL);
        furnitureItem(ModItems.LEMON_STOOL);
        furnitureItem(ModItems.LIME_STOOL);
        furnitureItem(ModItems.LINDEN_STOOL);
        furnitureItem(ModItems.MAHOGANY_STOOL);
        furnitureItem(ModItems.MAPLE_STOOL);
        furnitureItem(ModItems.MYRRH_STOOL);
        furnitureItem(ModItems.NIGHTWOOD_STOOL);
        furnitureItem(ModItems.NUTMEG_STOOL);
        furnitureItem(ModItems.ORANGE_STOOL);
        furnitureItem(ModItems.PEACH_STOOL);
        furnitureItem(ModItems.PEAR_STOOL);
        furnitureItem(ModItems.PECAN_STOOL);
        furnitureItem(ModItems.PERSIMMON_STOOL);
        furnitureItem(ModItems.PINK_IVORY_STOOL);
        furnitureItem(ModItems.PLUM_STOOL);
        furnitureItem(ModItems.POMEGRANATE_STOOL);
        furnitureItem(ModItems.PURPLEHEART_STOOL);
        furnitureItem(ModItems.REDWOOD_STOOL);
        furnitureItem(ModItems.SANDALWOOD_STOOL);
        furnitureItem(ModItems.SANDBEGGAR_STOOL);
        furnitureItem(ModItems.TIGERWOOD_STOOL);
        furnitureItem(ModItems.YEW_STOOL);
        furnitureItem(ModItems.SOLDIER_PINE_STOOL);
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_STOOL);



// Chairs
        furnitureItem(ModItems.DARK_OAK_CHAIR);
        furnitureItem(ModItems.OAK_CHAIR);
        furnitureItem(ModItems.SPRUCE_CHAIR);
        furnitureItem(ModItems.BIRCH_CHAIR);
        furnitureItem(ModItems.JUNGLE_CHAIR);
        furnitureItem(ModItems.ACACIA_CHAIR);
        furnitureItem(ModItems.MANGROVE_CHAIR);
        furnitureItem(ModItems.CHERRY_CHAIR);
        furnitureItem(ModItems.BAMBOO_CHAIR);
        furnitureItem(ModItems.CRIMSON_CHAIR);
        furnitureItem(ModItems.WARPED_CHAIR);
        furnitureItem(ModItems.WEIRWOOD_CHAIR);
        furnitureItem(ModItems.CHARRED_CHAIR);
        furnitureItem(ModItems.ROTTEN_CHAIR);
        furnitureItem(ModItems.PINE_CHAIR);
        furnitureItem(ModItems.ASH_CHAIR);
        furnitureItem(ModItems.BEECH_CHAIR);
        furnitureItem(ModItems.CEDAR_CHAIR);
        furnitureItem(ModItems.CHESTNUT_CHAIR);
        furnitureItem(ModItems.HAWTHORN_CHAIR);
        furnitureItem(ModItems.IRONWOOD_CHAIR);
        furnitureItem(ModItems.SENTINEL_CHAIR);
        furnitureItem(ModItems.SYCAMORE_CHAIR);
        furnitureItem(ModItems.BLACKBARK_CHAIR);
        furnitureItem(ModItems.ASPEN_CHAIR);
        furnitureItem(ModItems.ALDER_CHAIR);
        furnitureItem(ModItems.BLACK_CHERRY_CHAIR);
        furnitureItem(ModItems.BLACK_OLIVE_CHAIR);
        furnitureItem(ModItems.CRABAPPLE_CHAIR);
        furnitureItem(ModItems.OLIVE_CHAIR);
        furnitureItem(ModItems.WHITE_CHERRY_CHAIR);
        furnitureItem(ModItems.RED_CHERRY_CHAIR);
        furnitureItem(ModItems.FIR_CHAIR);
        furnitureItem(ModItems.WILLOW_CHAIR);
        furnitureItem(ModItems.WORMTREE_CHAIR);
        furnitureItem(ModItems.ALMOND_CHAIR);
        furnitureItem(ModItems.APPLE_CHAIR);
        furnitureItem(ModItems.APRICOT_CHAIR);
        furnitureItem(ModItems.BAOBAB_CHAIR);
        furnitureItem(ModItems.BLACK_COTTONWOOD_CHAIR);
        furnitureItem(ModItems.BLACKTHORN_CHAIR);
        furnitureItem(ModItems.BLOOD_ORANGE_CHAIR);
        furnitureItem(ModItems.BLOODWOOD_CHAIR);
        furnitureItem(ModItems.BLUE_MAHOE_CHAIR);
        furnitureItem(ModItems.COTTONWOOD_CHAIR);
        furnitureItem(ModItems.DATEPALM_CHAIR);
        furnitureItem(ModItems.EBONY_CHAIR);
        furnitureItem(ModItems.FIG_CHAIR);
        furnitureItem(ModItems.FIREPLUM_CHAIR);
        furnitureItem(ModItems.GOLDENHEART_CHAIR);
        furnitureItem(ModItems.LEMON_CHAIR);
        furnitureItem(ModItems.LIME_CHAIR);
        furnitureItem(ModItems.LINDEN_CHAIR);
        furnitureItem(ModItems.MAHOGANY_CHAIR);
        furnitureItem(ModItems.MAPLE_CHAIR);
        furnitureItem(ModItems.MYRRH_CHAIR);
        furnitureItem(ModItems.NIGHTWOOD_CHAIR);
        furnitureItem(ModItems.NUTMEG_CHAIR);
        furnitureItem(ModItems.ORANGE_CHAIR);
        furnitureItem(ModItems.PEACH_CHAIR);
        furnitureItem(ModItems.PEAR_CHAIR);
        furnitureItem(ModItems.PECAN_CHAIR);
        furnitureItem(ModItems.PERSIMMON_CHAIR);
        furnitureItem(ModItems.PINK_IVORY_CHAIR);
        furnitureItem(ModItems.PLUM_CHAIR);
        furnitureItem(ModItems.POMEGRANATE_CHAIR);
        furnitureItem(ModItems.PURPLEHEART_CHAIR);
        furnitureItem(ModItems.REDWOOD_CHAIR);
        furnitureItem(ModItems.SANDALWOOD_CHAIR);
        furnitureItem(ModItems.SANDBEGGAR_CHAIR);
        furnitureItem(ModItems.TIGERWOOD_CHAIR);
        furnitureItem(ModItems.YEW_CHAIR);
        furnitureItem(ModItems.SOLDIER_PINE_CHAIR);
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_CHAIR);

// Arm Chairs
        furnitureItem(ModItems.DARK_OAK_ARM_CHAIR);
        furnitureItem(ModItems.OAK_ARM_CHAIR);
        furnitureItem(ModItems.SPRUCE_ARM_CHAIR);
        furnitureItem(ModItems.BIRCH_ARM_CHAIR);
        furnitureItem(ModItems.JUNGLE_ARM_CHAIR);
        furnitureItem(ModItems.ACACIA_ARM_CHAIR);
        furnitureItem(ModItems.MANGROVE_ARM_CHAIR);
        furnitureItem(ModItems.CHERRY_ARM_CHAIR);
        furnitureItem(ModItems.BAMBOO_ARM_CHAIR);
        furnitureItem(ModItems.CRIMSON_ARM_CHAIR);
        furnitureItem(ModItems.WARPED_ARM_CHAIR);
        furnitureItem(ModItems.WEIRWOOD_ARM_CHAIR);
        furnitureItem(ModItems.CHARRED_ARM_CHAIR);
        furnitureItem(ModItems.ROTTEN_ARM_CHAIR);
        furnitureItem(ModItems.PINE_ARM_CHAIR);
        furnitureItem(ModItems.ASH_ARM_CHAIR);
        furnitureItem(ModItems.BEECH_ARM_CHAIR);
        furnitureItem(ModItems.CEDAR_ARM_CHAIR);
        furnitureItem(ModItems.CHESTNUT_ARM_CHAIR);
        furnitureItem(ModItems.HAWTHORN_ARM_CHAIR);
        furnitureItem(ModItems.IRONWOOD_ARM_CHAIR);
        furnitureItem(ModItems.SENTINEL_ARM_CHAIR);
        furnitureItem(ModItems.SYCAMORE_ARM_CHAIR);
        furnitureItem(ModItems.BLACKBARK_ARM_CHAIR);
        furnitureItem(ModItems.ASPEN_ARM_CHAIR);
        furnitureItem(ModItems.ALDER_ARM_CHAIR);
        furnitureItem(ModItems.BLACK_CHERRY_ARM_CHAIR);
        furnitureItem(ModItems.BLACK_OLIVE_ARM_CHAIR);
        furnitureItem(ModItems.CRABAPPLE_ARM_CHAIR);
        furnitureItem(ModItems.OLIVE_ARM_CHAIR);
        furnitureItem(ModItems.WHITE_CHERRY_ARM_CHAIR);
        furnitureItem(ModItems.RED_CHERRY_ARM_CHAIR);
        furnitureItem(ModItems.FIR_ARM_CHAIR);
        furnitureItem(ModItems.WILLOW_ARM_CHAIR);
        furnitureItem(ModItems.WORMTREE_ARM_CHAIR);
        furnitureItem(ModItems.ALMOND_ARM_CHAIR);
        furnitureItem(ModItems.APPLE_ARM_CHAIR);
        furnitureItem(ModItems.APRICOT_ARM_CHAIR);
        furnitureItem(ModItems.BAOBAB_ARM_CHAIR);
        furnitureItem(ModItems.BLACK_COTTONWOOD_ARM_CHAIR);
        furnitureItem(ModItems.BLACKTHORN_ARM_CHAIR);
        furnitureItem(ModItems.BLOOD_ORANGE_ARM_CHAIR);
        furnitureItem(ModItems.BLOODWOOD_ARM_CHAIR);
        furnitureItem(ModItems.BLUE_MAHOE_ARM_CHAIR);
        furnitureItem(ModItems.COTTONWOOD_ARM_CHAIR);
        furnitureItem(ModItems.DATEPALM_ARM_CHAIR);
        furnitureItem(ModItems.EBONY_ARM_CHAIR);
        furnitureItem(ModItems.FIG_ARM_CHAIR);
        furnitureItem(ModItems.FIREPLUM_ARM_CHAIR);
        furnitureItem(ModItems.GOLDENHEART_ARM_CHAIR);
        furnitureItem(ModItems.LEMON_ARM_CHAIR);
        furnitureItem(ModItems.LIME_ARM_CHAIR);
        furnitureItem(ModItems.LINDEN_ARM_CHAIR);
        furnitureItem(ModItems.MAHOGANY_ARM_CHAIR);
        furnitureItem(ModItems.MAPLE_ARM_CHAIR);
        furnitureItem(ModItems.MYRRH_ARM_CHAIR);
        furnitureItem(ModItems.NIGHTWOOD_ARM_CHAIR);
        furnitureItem(ModItems.NUTMEG_ARM_CHAIR);
        furnitureItem(ModItems.ORANGE_ARM_CHAIR);
        furnitureItem(ModItems.PEACH_ARM_CHAIR);
        furnitureItem(ModItems.PEAR_ARM_CHAIR);
        furnitureItem(ModItems.PECAN_ARM_CHAIR);
        furnitureItem(ModItems.PERSIMMON_ARM_CHAIR);
        furnitureItem(ModItems.PINK_IVORY_ARM_CHAIR);
        furnitureItem(ModItems.PLUM_ARM_CHAIR);
        furnitureItem(ModItems.POMEGRANATE_ARM_CHAIR);
        furnitureItem(ModItems.PURPLEHEART_ARM_CHAIR);
        furnitureItem(ModItems.REDWOOD_ARM_CHAIR);
        furnitureItem(ModItems.SANDALWOOD_ARM_CHAIR);
        furnitureItem(ModItems.SANDBEGGAR_ARM_CHAIR);
        furnitureItem(ModItems.TIGERWOOD_ARM_CHAIR);
        furnitureItem(ModItems.YEW_ARM_CHAIR);
        furnitureItem(ModItems.SOLDIER_PINE_ARM_CHAIR);
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_ARM_CHAIR);





        // ---------------------------(SAPLINGS)--------------------------- //
// Define the wood types
        String[] woodTypes = {
                "alder",
                "almond",
                "apple",
                "apricot",
                "ash",
                "aspen",
                "baobab",
                "beech",
                "black_cherry",
                "black_cottonwood",
                "black_olive",
                "blackbark",
                "blackthorn",
                "blood_orange",
                "bloodwood",
                "blue_mahoe",
                "cedar",
                "chestnut",
                "cottonwood",
                "crabapple",
                "datepalm",
                "ebony",
                "fig",
                "fir",
                "fireplum",
                "goldenheart",
                "hawthorn",
                "ironwood",
                "lemon",
                "lime",
                "linden",
                "mahogany",
                "maple",
                "myrrh",
                "nightwood",
                "nutmeg",
                "olive",
                "orange",
                "peach",
                "pear",
                "pecan",
                "persimmon",
                "pine",
                "pink_ivory",
                "plum",
                "pomegranate",
                "purpleheart",
                "redwood",
                "sandalwood",
                "sandbeggar",
                "sentinel",
                "sycamore",
                "tigerwood",
                "white_cherry",
                "willow",
                "wormtree",
                "yew",
                "blue_soldier_pine",
                "soldier_pine"
        };

// First register all saplings in one loop
        for (String woodType : woodTypes) {
            saplingItem(ModBLocks.SAPLINGS.get(woodType));
        }

// Register all wood items using a loop
        for (String woodType : woodTypes) {
            // Door items
            simpleBlockItem(ModBLocks.DOORS.get(woodType));

            // Fence items
            fenceItem(ModBLocks.FENCES.get(woodType), ModBLocks.PLANKS.get(woodType));

            // Button items
            buttonItem(ModBLocks.BUTTONS.get(woodType), ModBLocks.PLANKS.get(woodType));

            // Wall items
            wallItem(ModBLocks.WALLS.get(woodType), ModBLocks.PLANKS.get(woodType));

            // Simple block items
            evenSimplerBlockItem(ModBLocks.STAIRS.get(woodType));
            evenSimplerBlockItem(ModBLocks.SLABS.get(woodType));
            evenSimplerBlockItem(ModBLocks.PRESSURE_PLATES.get(woodType));
            evenSimplerBlockItem(ModBLocks.FENCE_GATES.get(woodType));

            // Trapdoor items
            trapdoorItem(ModBLocks.TRAPDOORS.get(woodType));

            // Sign items
            simpleItem(ModItems.SIGN_ITEMS.get(woodType));
            simpleItem(ModItems.HANGING_SIGN_ITEMS.get(woodType));
        }
        // ---------------------------(BLOCKS)--------------------------- //
        evenSimplerBlockItemVanilla(ModBLocks.DIRT_STAIRS, "dirt");
        evenSimplerBlockItemVanilla(ModBLocks.DIRT_SLAB, "dirt");

        evenSimplerBlockItemVanilla(ModBLocks.MUD_STAIRS, "mud");
        evenSimplerBlockItemVanilla(ModBLocks.MUD_SLAB, "mud");

        evenSimplerBlockItemVanilla(ModBLocks.COARSE_DIRT_STAIRS, "coarse_dirt");
        evenSimplerBlockItemVanilla(ModBLocks.COARSE_DIRT_SLAB, "coarse_dirt");

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PODZOL_STAIRS, "podzol_top", "podzol_side", "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PODZOL_SLAB,   "podzol_top", "podzol_side", "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PODZOL_STAIRS,       "podzol_top",       "podzol_side",       "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PODZOL_SLAB,         "podzol_top",       "podzol_side",       "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.GRASS_BLOCK_STAIRS,  "grass_block_top",  "grass_block_side",  "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.GRASS_BLOCK_SLAB,    "grass_block_top",  "grass_block_side",  "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.DIRT_PATH_STAIRS, "dirt_path_top", "dirt_path_side", "dirt", true, false, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.DIRT_PATH_SLAB,   "dirt_path_top", "dirt_path_side", "dirt", true, false, true);


        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.THATCH_SLAB,         "thatch_block_side",       "thatch_block_top",       "thatch_block_side", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.THATCH_STAIRS,         "thatch_block_side",       "thatch_block_top",       "thatch_block_side", false, false, false);



        // ---------------------------(WOODBLOCKS)--------------------------- //
        //Weirwood
        saplingItem(ModBLocks.WEIRWOOD_SAPLING);
        simpleBlockItem(ModBLocks.WEIRWOOD_DOOR);
        fenceItem(ModBLocks.WEIRWOOD_FENCE, ModBLocks.WEIRWOOD_PLANKS);
        buttonItem(ModBLocks.WEIRWOOD_BUTTON, ModBLocks.WEIRWOOD_PLANKS);
        wallItem(ModBLocks.WEIRWOOD_WALL, ModBLocks.WEIRWOOD_PLANKS);
        evenSimplerBlockItem(ModBLocks.WEIRWOOD_STAIRS);
        evenSimplerBlockItem(ModBLocks.WEIRWOOD_SLAB);
        evenSimplerBlockItem(ModBLocks.WEIRWOOD_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.WEIRWOOD_FENCE_GATE);
        trapdoorItem(ModBLocks.WEIRWOOD_TRAPDOOR);
        vanillaWallItem(ModBLocks.OAK_WALL, Blocks.OAK_PLANKS);
        vanillaWallItem(ModBLocks.SPRUCE_WALL, Blocks.SPRUCE_PLANKS);
        vanillaWallItem(ModBLocks.BIRCH_WALL, Blocks.BIRCH_PLANKS);
        vanillaWallItem(ModBLocks.JUNGLE_WALL, Blocks.JUNGLE_PLANKS);
        vanillaWallItem(ModBLocks.ACACIA_WALL, Blocks.ACACIA_PLANKS);
        vanillaWallItem(ModBLocks.DARK_OAK_WALL, Blocks.DARK_OAK_PLANKS);
        vanillaWallItem(ModBLocks.MANGROVE_WALL, Blocks.MANGROVE_PLANKS);
        vanillaWallItem(ModBLocks.CHERRY_WALL, Blocks.CHERRY_PLANKS);
        vanillaWallItem(ModBLocks.BAMBOO_WALL, Blocks.BAMBOO_PLANKS);
        vanillaWallItem(ModBLocks.CRIMSON_WALL, Blocks.CRIMSON_PLANKS);
        vanillaWallItem(ModBLocks.WARPED_WALL, Blocks.WARPED_PLANKS);
        simpleBlockItem(ModBLocks.ROTTEN_DOOR);
        fenceItem(ModBLocks.ROTTEN_FENCE, ModBLocks.ROTTEN_PLANKS);
        buttonItem(ModBLocks.ROTTEN_BUTTON, ModBLocks.ROTTEN_PLANKS);
        wallItem(ModBLocks.ROTTEN_WALL, ModBLocks.ROTTEN_PLANKS);
        evenSimplerBlockItem(ModBLocks.ROTTEN_STAIRS);
        evenSimplerBlockItem(ModBLocks.ROTTEN_SLAB);
        evenSimplerBlockItem(ModBLocks.ROTTEN_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.ROTTEN_FENCE_GATE);
        trapdoorItem(ModBLocks.ROTTEN_TRAPDOOR);

        simpleBlockItem(ModBLocks.CHARRED_DOOR);
        fenceItem(ModBLocks.CHARRED_FENCE, ModBLocks.CHARRED_PLANKS);
        buttonItem(ModBLocks.CHARRED_BUTTON, ModBLocks.CHARRED_PLANKS);
        wallItem(ModBLocks.CHARRED_WALL, ModBLocks.CHARRED_PLANKS);
        evenSimplerBlockItem(ModBLocks.CHARRED_STAIRS);
        evenSimplerBlockItem(ModBLocks.CHARRED_SLAB);
        evenSimplerBlockItem(ModBLocks.CHARRED_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.CHARRED_FENCE_GATE);
        trapdoorItem(ModBLocks.CHARRED_TRAPDOOR);


        // ---------------------------(FLOWERS)--------------------------- //
        simpleBlockItemBlockTexture(ModBLocks.WINTER_ROSE);
        simpleBlockItemBlockTexture(ModBLocks.WILD_RADISH);
        simpleBlockItemBlockTexture(ModBLocks.WHITE_ROSE);
        simpleBlockItemBlockTexture(ModBLocks.THORN_BUSH);
        simpleBlockItemBlockTexture(ModBLocks.THISTLE);
        simpleBlockItemBlockTexture(ModBLocks.TANSY);
        simpleBlockItemBlockTexture(ModBLocks.SPICEFLOWER);
        simpleBlockItemBlockTexture(ModBLocks.SEDGE);
        simpleBlockItemBlockTexture(ModBLocks.SAFFRON_CROCUS);
        simpleBlockItemBlockTexture(ModBLocks.ROSE);
        simpleBlockItemBlockTexture(ModBLocks.POISON_KISSES);
        simpleBlockItemBlockTexture(ModBLocks.PENNYROYAL);
        simpleBlockItemBlockTexture(ModBLocks.OPIUM_POPPY_WILD);
        simpleBlockItemBlockTexture(ModBLocks.NIGHTSHADE);
        simpleBlockItemBlockTexture(ModBLocks.MOONBLOOM);
        simpleBlockItemBlockTexture(ModBLocks.LUNGWORT);
        simpleBlockItemBlockTexture(ModBLocks.LIVERWORT);
        simpleBlockItemBlockTexture(ModBLocks.LAVENDER);
        simpleBlockItemBlockTexture(ModBLocks.LADYS_LACE);
        simpleBlockItemBlockTexture(ModBLocks.GORSE);
        simpleBlockItemBlockTexture(ModBLocks.GOLDENROD);
        simpleBlockItemBlockTexture(ModBLocks.GOLDENCUP);
        simpleBlockItemBlockTexture(ModBLocks.GOATHEAD);
        simpleBlockItemBlockTexture(ModBLocks.GINGER);
        simpleBlockItemBlockTexture(ModBLocks.GILLYFLOWER);
        simpleBlockItemBlockTexture(ModBLocks.FROSTFIRE);
        simpleBlockItemBlockTexture(ModBLocks.FORGET_ME_NOT);
        simpleBlockItemBlockTexture(ModBLocks.EVENING_STAR);
        simpleBlockItemBlockTexture(ModBLocks.DUSKY_ROSE);
        simpleBlockItemBlockTexture(ModBLocks.DRAGONS_BREATH);
        simpleBlockItemBlockTexture(ModBLocks.COLDSNAP);
        simpleBlockItemBlockTexture(ModBLocks.BLUE_ROSE);
        simpleBlockItemBlockTexture(ModBLocks.BLOODBLOOM);
        simpleBlockItemBlockTexture(ModBLocks.BLACK_LOTUS);

        // ---------------------------(STONE)--------------------------- //
        //REDKEEP
        wallItem(ModBLocks.REDKEEP_STONE_WALL, ModBLocks.REDKEEP_STONE_BLOCK);
        evenSimplerBlockItem(ModBLocks.REDKEEP_STONE_STAIRS);
        evenSimplerBlockItem(ModBLocks.REDKEEP_STONE_SLAB);


        // Replace all 19 variant model loops with this

        for (Map<Integer, ModBLocks.BlockSet> variants : List.of(
                ModBLocks.BLACKSTONE_VARIANTS,  ModBLocks.BASALT_VARIANTS,
                ModBLocks.BRICKS_VARIANTS,      ModBLocks.CALCITE_VARIANTS,
                ModBLocks.CDEEPSLATE_VARIANTS,  ModBLocks.GRANITE_VARIANTS,
                ModBLocks.REDKEEP_VARIANTS,     ModBLocks.ANDESITE_VARIANTS,
                ModBLocks.TUFF_VARIANTS,        ModBLocks.DIORITE_VARIANTS,
                ModBLocks.QUARTZ_VARIANTS,      ModBLocks.RSANDSTONE_VARIANTS,
                ModBLocks.MUD_BRICK_VARIANTS,   ModBLocks.SANDSTONE_VARIANTS,
                ModBLocks.STONE_VARIANTS,       ModBLocks.SSTONE_VARIANTS,
                ModBLocks.BONE_VARIANTS,        ModBLocks.DRIPSTONE_VARIANTS,
                ModBLocks.PACKED_ICE_VARIANTS
        )) {
            for (ModBLocks.BlockSet set : variants.values()) {
                wallItem(set.wall(), set.base());
                evenSimplerBlockItem(set.stairs());
                evenSimplerBlockItem(set.slab());
            }
        }


        // ---------------------------(CRAFTING ITEMS)--------------------------- //
        // Register item models for crafting items
        simpleItem(ModItems.BOAR_INTESTINES);
        simpleItem(ModItems.BOAR_TUSK);
        simpleItem(ModItems.IVORY);
        simpleItem(ModItems.IVORY_SHARD);
        simpleItem(ModItems.FUR);
        simpleItem(ModItems.BOILED_LEATHER);
        simpleItem(ModItems.WEIRWOOD_STICK);
        simpleItem(ModItems.BRONZE_CHAIN_LINK);
        simpleItem(ModItems.BRONZE_CHAIN);
        simpleItem(ModItems.BRONZE_PLATE);
        simpleItem(ModItems.CLOTH);
        simpleItem(ModItems.NAIL);
        simpleItem(ModItems.IRON_CHAIN_LINK);
        simpleItem(ModItems.IRON_CHAIN);
        simpleItem(ModItems.HAMMER);
        simpleItem(ModItems.IRON_PLATE);
        simpleItem(ModItems.NOBLE_PLATE);
        simpleItem(ModItems.STEEL_BOOTS);
        simpleItem(ModItems.STEEL_CHAIN_LINK);
        simpleItem(ModItems.STEEL_CHAIN);
        simpleItem(ModItems.STEEL_CHESTPLATE);
        simpleItem(ModItems.STEEL_HELMET);
        simpleItem(ModItems.STEEL_LEGGINGS);
        simpleItem(ModItems.STEEL_PLATE);
        simpleItem(ModItems.UPGRADE_KIT_BRONZE);
        simpleItem(ModItems.UPGRADE_KIT_IRON);
        simpleItem(ModItems.UPGRADE_KIT_NOBLE);
        simpleItem(ModItems.UPGRADE_KIT_STEEL);
        simpleItem(ModItems.UPGRADE_KIT_BRONZE_PLATE);
        simpleItem(ModItems.UPGRADE_KIT_CHIEF);
        simpleItem(ModItems.UPGRADE_KIT_FUR);
        simpleItem(ModItems.UPGRADE_KIT_LEATHER);

        simpleItem(ModItems.STARK_LEVY_SMITHING_SCROLL);
        simpleItem(ModItems.STARK_PLATE_SMITHING_SCROLL);
        simpleItem(ModItems.STARK_NOBLE_SMITHING_SCROLL);

        simpleItem(ModItems.BOLTON_LEVY_SMITHING_SCROLL);
        simpleItem(ModItems.BOLTON_PLATE_SMITHING_SCROLL);
        simpleItem(ModItems.BOLTON_NOBLE_SMITHING_SCROLL);

        simpleItem(ModItems.MANDERLY_LEVY_SMITHING_SCROLL);
        simpleItem(ModItems.MANDERLY_PLATE_SMITHING_SCROLL);
        simpleItem(ModItems.MANDERLY_NOBLE_SMITHING_SCROLL);

        simpleItem(ModItems.NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL);
        simpleItem(ModItems.NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL);
        simpleItem(ModItems.NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL);

        simpleItem(ModItems.NIGHTS_WATCH_RANGER_SMITHING_SCROLL);
        simpleItem(ModItems.NIGHTS_WATCH_LEATHER_SMITHING_SCROLL);
        simpleItem(ModItems.NIGHTS_WATCH_ELITE_SMITHING_SCROLL);

        simpleItem(ModItems.WILDLING_FUR_SMITHING_SCROLL);
        simpleItem(ModItems.WILDLING_LEATHER_SMITHING_SCROLL);
        simpleItem(ModItems.WILDLING_CHIEF_SMITHING_SCROLL);

        simpleItem(ModItems.THENN_LEVY_SMITHING_SCROLL);
        simpleItem(ModItems.THENN_PLATE_SMITHING_SCROLL);
        simpleItem(ModItems.THENN_NOBLE_SMITHING_SCROLL);

        simpleItem(ModItems.IRONBORN_LEVY_SMITHING_SCROLL);
        simpleItem(ModItems.IRONBORN_PLATE_SMITHING_SCROLL);
        simpleItem(ModItems.IRONBORN_NOBLE_SMITHING_SCROLL);



        // ---------------------------(SIGNS)--------------------------- //
        simpleItem(ModItems.WEIRWOOD_SIGN);
        simpleItem(ModItems.WEIRWOOD_HANGING_SIGN);
        simpleItem(ModItems.ROTTEN_SIGN);
        simpleItem(ModItems.ROTTEN_HANGING_SIGN);

        simpleItem(ModItems.CHARRED_SIGN);
        simpleItem(ModItems.CHARRED_HANGING_SIGN);


        // ---------------------------(BANNERS)--------------------------- //

        bannerPatternItem(BannerPatterns.ARRYN_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BAELISH_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BARATHEON_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BLACKWOOD_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BOLTON_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BRACKEN_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.CLEGANE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.DAYNE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.FREY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.GREYJOY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.HARLAW_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.HIGHTOWER_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.KARSTARK_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.LANNISTER_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MANDERLY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MORMONT_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.REDWYNE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.REED_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.REYNE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.ROYCE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.STARK_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TARGARYEN_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TARLY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TULLY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TYRELL_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.UMBER_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.VELARYON_BANNER_PATTERN);





    }

    private @NotNull ItemModelBuilder saplingItem(@NotNull DeferredHolder<Block, Block> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("block/" + item.getId().getPath()));
    }

    // Method to create a simple item model
    private @NotNull ItemModelBuilder simpleItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private @NotNull ItemModelBuilder simpleBannerItem(DeferredItem<BannerPatternItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    // Method to create a simple Armor item model
    //Stark
    private ItemModelBuilder simpleItemStarkLevy(DeferredHolder<Item, StarkLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemStarkPlate(DeferredHolder<Item, StarkPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemStarkNoblePlate(DeferredHolder<Item, StarkNoblePlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    //Bolten
    private ItemModelBuilder simpleItemBoltenLevy(DeferredHolder<Item, BoltenLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemBoltenPlate(DeferredHolder<Item, BoltenPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemBoltenNoblePlate(DeferredHolder<Item, BoltenNobleArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    //Manderly
    private ItemModelBuilder simpleItemManderlyLevy(DeferredHolder<Item, ManderlyLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemManderlyPlate(DeferredHolder<Item, ManderlyPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemManderlyNoble(DeferredHolder<Item, ManderlyNobleArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    // Thenn
    private ItemModelBuilder simpleItemThennLevy(DeferredHolder<Item, ThennLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemThennPlate(DeferredHolder<Item, ThennPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemThennNoble(DeferredHolder<Item, ThennNobleArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    // Nothern Mountain Clans
    private ItemModelBuilder simpleItemMountainClanLevy(DeferredHolder<Item, MountainClanLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemMountainClanPlate(DeferredHolder<Item, MountainClanPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemMountainClanChief(DeferredHolder<Item, MountainClanChiefArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    // Ironborn
    private ItemModelBuilder simpleItemIronbornLevy(DeferredHolder<Item, IronBornLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemIronbornPlate(DeferredHolder<Item, IronBornPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemIronbornNoble(DeferredHolder<Item, IronBornNobleArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    // Wildling
    private ItemModelBuilder simpleItemWildlingFur(DeferredHolder<Item, WildlingFurArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemWildlingLeather(DeferredHolder<Item, WildlingLeatherArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemWildlingChief(DeferredHolder<Item, WildlingChiefArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    // Night's Watch
    private ItemModelBuilder simpleItemNightWatchRanger(DeferredHolder<Item, NightsWatchRangerArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemNightWatchLeather(DeferredHolder<Item, NightsWatchLeatherArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemNightWatchElite(DeferredHolder<Item, NightsWatchEliteArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }


    // Method to create a handheld item model
    private ItemModelBuilder handheldItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/handheld")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }



    private ItemModelBuilder weaponItem(DeferredHolder<Item, Item> item,
                                        float[] thirdpersonTranslation,
                                        float[] thirdpersonScale,
                                        float[] thirdpersonRotation,
                                        float[] firstpersonTranslation,
                                        float[] firstpersonScale,
                                        float[] firstpersonRotation) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(thirdpersonRotation[0], -Math.abs(thirdpersonRotation[1]), thirdpersonRotation[2])
                .translation(thirdpersonTranslation[0], thirdpersonTranslation[1], thirdpersonTranslation[2])
                .scale(thirdpersonScale[0], thirdpersonScale[1], thirdpersonScale[2])
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(thirdpersonRotation[0], Math.abs(thirdpersonRotation[1]), -thirdpersonRotation[2])
                .translation(thirdpersonTranslation[0], thirdpersonTranslation[1], thirdpersonTranslation[2])
                .scale(thirdpersonScale[0], thirdpersonScale[1], thirdpersonScale[2])
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(firstpersonRotation[0], -Math.abs(firstpersonRotation[1]), firstpersonRotation[2])
                .translation(firstpersonTranslation[0], firstpersonTranslation[1], firstpersonTranslation[2])
                .scale(firstpersonScale[0], firstpersonScale[1], firstpersonScale[2])
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(firstpersonRotation[0], Math.abs(firstpersonRotation[1]), -firstpersonRotation[2])
                .translation(firstpersonTranslation[0], firstpersonTranslation[1], firstpersonTranslation[2])
                .scale(firstpersonScale[0], firstpersonScale[1], firstpersonScale[2])
                .end()
                .transform(ItemDisplayContext.GROUND)
                .scale(1.0f, 1.0f, 1.0f)
                .end()
                .transform(ItemDisplayContext.GUI)
                .rotation(0, 0, 0)
                .scale(1.2f, 1.2f, 1.2f)
                .end()
                .end();
    }

    public void trapdoorItem(DeferredHolder<Block, Block> block) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath() + "_bottom"));
    }

    private void fenceItem(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    private void buttonItem(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    private void wallItem(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    private void vanillaWallItem(DeferredHolder<Block, Block> block, Block baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.withDefaultNamespace("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(DeferredHolder<Block, Block> block) {
        return withExistingParent(block.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + block.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(DeferredHolder<Block, Block> block) {
        return withExistingParent(block.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("block/" + block.getId().getPath()));
    }

    public void evenSimplerBlockItem(DeferredHolder<Block, Block> block) {
        this.withExistingParent(AGoTMod.MOD_ID + ":" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath()));
    }

    public void evenSimplerBlockItemVanilla(DeferredHolder<Block, Block> block, String vanillaPath) {
        String blockPath = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        if (blockPath.endsWith("_stairs")) {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/stairs"))
                    .texture("top",  mcLoc("block/" + vanillaPath))
                    .texture("side", mcLoc("block/" + vanillaPath))
                    .texture("bottom", mcLoc("block/" + vanillaPath));
        } else if (blockPath.endsWith("_slab") || blockPath.endsWith("_slabs")) {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/slab"))
                    .texture("top",    mcLoc("block/" + vanillaPath))
                    .texture("side",   mcLoc("block/" + vanillaPath))
                    .texture("bottom", mcLoc("block/" + vanillaPath));
        } else {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/" + vanillaPath));
        }
    }

    public void evenSimplerBlockItemVanillaMultiTexture(DeferredHolder<Block, Block> block,
                                                        String topPath, String sidePath, String bottomPath, boolean topIsVanilla, boolean sideIsVanilla, boolean bottomIsVanilla) {
        String blockPath = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        net.minecraft.resources.ResourceLocation top    = topIsVanilla    ? mcLoc("block/" + topPath)    : modLoc("block/" + topPath);
        net.minecraft.resources.ResourceLocation side   = sideIsVanilla   ? mcLoc("block/" + sidePath)   : modLoc("block/" + sidePath);
        net.minecraft.resources.ResourceLocation bottom = bottomIsVanilla ? mcLoc("block/" + bottomPath) : modLoc("block/" + bottomPath);

        if (blockPath.endsWith("_stairs")) {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/stairs"))
                    .texture("top",    top)
                    .texture("side",   side)
                    .texture("bottom", bottom);
        } else if (blockPath.endsWith("_slab") || blockPath.endsWith("_slabs")) {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/slab"))
                    .texture("top",    top)
                    .texture("side",   side)
                    .texture("bottom", bottom);
        }
    }

    public void furnitureItem(DeferredHolder<Item, Item> item) {
        String itemPath = BuiltInRegistries.ITEM.getKey(item.get()).getPath();

        String texturePath;

        if (itemPath.endsWith("_table")) {
            texturePath = "item/" + itemPath + "_icon";
        } else if (itemPath.endsWith("_stool")) {
            String woodType = itemPath.replace("_stool", "");
            texturePath = "item/" + woodType + "_chair1_icon";
        } else if (itemPath.endsWith("_arm_chair")) {
            String woodType = itemPath.replace("_arm_chair", "");
            texturePath = "item/" + woodType + "_chair3_icon";
        } else if (itemPath.endsWith("_chair")) {
            String woodType = itemPath.replace("_chair", "");
            texturePath = "item/" + woodType + "_chair2_icon";
        } else {
            throw new IllegalArgumentException("Unknown furniture item type: " + itemPath);
        }

        this.withExistingParent(itemPath, mcLoc("item/generated"))
                .texture("layer0", modLoc(texturePath));
    }


    private ItemModelBuilder spawnEggItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/template_spawn_egg"))
                .texture("layer0", modLoc("item/spawn_egg"))
                .texture("layer1", modLoc("item/spawn_egg_overlay"));
    }
    private @NotNull ItemModelBuilder bannerPatternItem(DeferredHolder<Item, BannerPatternItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/targaryen_banner_pattern")); // Always uses targaryen texture
    }

}

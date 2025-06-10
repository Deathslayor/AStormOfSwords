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
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

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




        // ---------------------------(SAPLINGS)--------------------------- //
        // ---------------------------(FOODS)--------------------------- //
        simpleItem(ModItems.COOKED_SAUSAGE);
        simpleItem(ModItems.RAW_WHITE_SAUSAGE);
        simpleItem(ModItems.COOKED_WHITE_SAUSAGE);
        simpleItem(ModItems.RAW_BLOOD_SAUSAGE);
        simpleItem(ModItems.COOKED_BLOOD_SAUSAGE);
        simpleItem(ModItems.RAW_BACON);
        simpleItem(ModItems.COOKED_BACON);
        simpleItem(ModItems.COOKED_BOAR_VENISON);
        simpleItem(ModItems.RAW_BOAR_VENISON);
        simpleItem(ModItems.COOKED_CHICKEN_NUGGETS);
        simpleItem(ModItems.RAW_CHICKEN_NUGGETS);
        simpleItem(ModItems.COOKED_DEER_VENISON);
        simpleItem(ModItems.RAW_DEER_VENISON);
        simpleItem(ModItems.COOKED_GOAT_MEAT);
        simpleItem(ModItems.RAW_GOAT_MEAT);
        simpleItem(ModItems.COOKED_HARE_MEAT);
        simpleItem(ModItems.RAW_HARE_MEAT);
        simpleItem(ModItems.COOKED_HORSE_MEAT);
        simpleItem(ModItems.RAW_HORSE_MEAT);
        simpleItem(ModItems.COOKED_MAMMOTH_MEAT);
        simpleItem(ModItems.RAW_MAMMOTH_MEAT);
        simpleItem(ModItems.RAW_BEAR_MEAT);
        simpleItem(ModItems.COOKED_BEAR_MEAT);
        simpleItem(ModItems.RAW_SAUSAGE);
        simpleItem(ModItems.RAW_WHITE_SAUSAGE);
        simpleItem(ModItems.RAW_BLOOD_SAUSAGE);
        simpleItem(ModItems.COOKED_SAUSAGE);
        simpleItem(ModItems.COOKED_BLOOD_SAUSAGE);
        simpleItem(ModItems.COOKED_WHITE_SAUSAGE);
        simpleItem(ModItems.HORSERADISH);
        simpleItem(ModItems.BARLEY);
        simpleItem(ModItems.GARLIC);
        simpleItem(ModItems.LEEK);
        simpleItem(ModItems.NEEP);
        simpleItem(ModItems.OAT);
        simpleItem(ModItems.PARSLEY);
        simpleItem(ModItems.RED_ONION);
        simpleItem(ModItems.TURNIP);
        simpleItem(ModItems.WILD_ONION);
        simpleItem(ModItems.ONION);
        simpleItem(ModItems.CABBAGE);
        simpleItem(ModItems.BEAN);
        simpleItem(ModItems.CHICKPEA);
        simpleItem(ModItems.CUCUMBER);
        simpleItem(ModItems.GREEN_BEAN);
        simpleItem(ModItems.SPINACH);
        simpleItem(ModItems.DRAGON_PEPPER);
        simpleItem(ModItems.PEPPER);
        simpleItem(ModItems.PEPPERCORN);
        simpleItem(ModItems.COTTON);
        simpleItem(ModItems.HEMP);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.BLACKBERRY);
        simpleItem(ModItems.BLUEBERRY);
        simpleItem(ModItems.MULBERRY);
        simpleItem(ModItems.RASPBERRY);
        simpleItem(ModItems.SMOKEBERRY);
        simpleItem(ModItems.CORN);


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

        simpleItem(ModItems.FARMER_BARREL);
        simpleItem(ModItems.TOWN_HALL);



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
                "yew"
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
        //Sycamore


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


        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 as in your block registration
            if (i == 15 || i == 22 || i == 31) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.BLACKSTONE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }
        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 1 || i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.BASALT_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 2 || i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.BRICKS_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.CALCITE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 4 || i == 15 || i == 19) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.CDEEPSLATE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.GRANITE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.REDKEEP_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.ANDESITE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 10, 15, 20, and 21 as in your block registration
            if (i == 10 || i == 15 || i == 20 || i == 21) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.TUFF_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }


        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.DIORITE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 14 || i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.QUARTZ_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 as in your block registration
            if (i == 15 || i == 16 || i == 17 || i == 18) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.RSANDSTONE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 7, 9, and 15
            if (i == 7 || i == 9 || i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.MUD_BRICK_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }


        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 as in your block registration
            if (i == 15 || i == 16 || i == 17 || i == 18) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.SANDSTONE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 as in your block registration
            if (i == 5 || i == 15 || i == 19 || i == 23) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.STONE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 23
            if (i == 15 || i == 23) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.SSTONE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 15 || i == 35) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.BONE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.DRIPSTONE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
            }
        }

        for (int i = 1; i <= 38; i++) {
            // Skip variants 15 and 22 and 31 as in your block registration
            if (i == 15) continue;

            ModBLocks.BlockSet blockSet = ModBLocks.PACKED_ICE_VARIANTS.get(i);
            if (blockSet != null) {
                wallItem(blockSet.wall(), blockSet.base());
                evenSimplerBlockItem(blockSet.stairs());
                evenSimplerBlockItem(blockSet.slab());
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


        // ---------------------------(BANNERS)--------------------------- //

        bannerPatternItem(BannerPatterns.TARGARYEN_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.STARK_BANNER_PATTERN);





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

    private ItemModelBuilder spawnEggItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/template_spawn_egg"))
                .texture("layer0", modLoc("item/spawn_egg"))
                .texture("layer1", modLoc("item/spawn_egg_overlay"));
    }
    private @NotNull ItemModelBuilder bannerPatternItem(DeferredHolder<Item, BannerPatternItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath())); // Uses your custom texture
    }

}

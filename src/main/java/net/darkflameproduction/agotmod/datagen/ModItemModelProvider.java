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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

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
        // ---------------------------(COINS)--------------------------- //

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

        // ---------------------------(ARMOUR)--------------------------- //

        // ---------------------------(INGOTS)--------------------------- //
        // Register item models for ingots and nuggets
        simpleItem(ModItems.RAW_TIN);
        simpleItem(ModItems.TIN_INGOT);
        simpleItem(ModItems.BRONZE_INGOT);
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
        handheldItem(ModItems.DRAGONGLASS_SPEAR);
        handheldItem(ModItems.DRAGONGLASS_DAGGER);
        handheldItem(ModItems.BRONZE_SPATHA);
        handheldItem(ModItems.BRONZE_SPEAR);
        handheldItem(ModItems.BRONZE_PIKE);
        handheldItem(ModItems.BRONZE_DAGGER);
        handheldItem(ModItems.BRONZE_BATTLEAXE);
        handheldItem(ModItems.IRON_LONGSWORD);
        handheldItem(ModItems.IRON_SPEAR);
        handheldItem(ModItems.IRON_PIKE);
        handheldItem(ModItems.IRON_DAGGER);
        handheldItem(ModItems.IRON_MACE);
        handheldItem(ModItems.IRON_BATTLEAXE);
        handheldItem(ModItems.STEEL_LONGSWORD);
        handheldItem(ModItems.STEEL_SPEAR);
        handheldItem(ModItems.STEEL_PIKE);
        handheldItem(ModItems.STEEL_DAGGER);
        handheldItem(ModItems.STEEL_MACE);
        handheldItem(ModItems.STEEL_BATTLEAXE);
        handheldItem(ModItems.STEEL_HALBERD);
        handheldItem(ModItems.NOBLE_LONGSWORD);
        handheldItem(ModItems.NOBLE_SPEAR);
        handheldItem(ModItems.NOBLE_PIKE);
        handheldItem(ModItems.NOBLE_DAGGER);
        handheldItem(ModItems.NOBLE_MACE);
        handheldItem(ModItems.NOBLE_BATTLEAXE);
        handheldItem(ModItems.NOBLE_HALBERD);

        // ---------------------------(Weapons)--------------------------- //

        // ---------------------------(ANIMAL SPAWN EGGS)--------------------------- //
        // Register item model for the Rhino Spawn Egg
        withExistingParent(ModItems.MAMMOTH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        // ---------------------------(ANIMAL SPAWN EGGS)--------------------------- //

        // ---------------------------(TOOLS)--------------------------- //
        // Register item models for tools
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
        // ---------------------------(TOOLS)--------------------------- //

        // ---------------------------(TOOLS)--------------------------- //
        handheldItem(ModItems.BLOOD_BOTTLED);
        // ---------------------------(TOOLS)--------------------------- //

        // ---------------------------(FOODS)--------------------------- //
        // Register item models for food items


        // ---------------------------(SAPLINGS)--------------------------- //
        saplingItem(ModBLocks.SYCAMORE_SAPLING);
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
        // ---------------------------(SAPLINGS)--------------------------- //
        saplingItem(ModBLocks.WEIRWOOD_SAPLING);
        saplingItem(ModBLocks.SYCAMORE_SAPLING);
        saplingItem(ModBLocks.SENTINEL_SAPLING);
        saplingItem(ModBLocks.PINE_SAPLING);
        saplingItem(ModBLocks.IRONWOOD_SAPLING);
        saplingItem(ModBLocks.HAWTHORN_SAPLING);
        saplingItem(ModBLocks.CHESTNUT_SAPLING);
        saplingItem(ModBLocks.CEDAR_SAPLING);
        saplingItem(ModBLocks.BEECH_SAPLING);
        saplingItem(ModBLocks.ASH_SAPLING);
        saplingItem(ModBLocks.BLACKBARK_SAPLING);
        saplingItem(ModBLocks.ASPEN_SAPLING);
        saplingItem(ModBLocks.ALDER_SAPLING);
        // ---------------------------(WOODBLOCKS)--------------------------- //
        //Weirwood
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
        simpleBlockItem(ModBLocks.SYCAMORE_DOOR);
        fenceItem(ModBLocks.SYCAMORE_FENCE, ModBLocks.SYCAMORE_PLANKS);
        buttonItem(ModBLocks.SYCAMORE_BUTTON, ModBLocks.SYCAMORE_PLANKS);
        wallItem(ModBLocks.SYCAMORE_WALL, ModBLocks.SYCAMORE_PLANKS);
        evenSimplerBlockItem(ModBLocks.SYCAMORE_STAIRS);
        evenSimplerBlockItem(ModBLocks.SYCAMORE_SLAB);
        evenSimplerBlockItem(ModBLocks.SYCAMORE_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.SYCAMORE_FENCE_GATE);
        trapdoorItem(ModBLocks.SYCAMORE_TRAPDOOR);
        //Sentinel
        simpleBlockItem(ModBLocks.SENTINEL_DOOR);
        fenceItem(ModBLocks.SENTINEL_FENCE, ModBLocks.SENTINEL_PLANKS);
        buttonItem(ModBLocks.SENTINEL_BUTTON, ModBLocks.SENTINEL_PLANKS);
        wallItem(ModBLocks.SENTINEL_WALL, ModBLocks.SENTINEL_PLANKS);
        evenSimplerBlockItem(ModBLocks.SENTINEL_STAIRS);
        evenSimplerBlockItem(ModBLocks.SENTINEL_SLAB);
        evenSimplerBlockItem(ModBLocks.SENTINEL_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.SENTINEL_FENCE_GATE);
        trapdoorItem(ModBLocks.SENTINEL_TRAPDOOR);
        //Pine
        simpleBlockItem(ModBLocks.PINE_DOOR);
        fenceItem(ModBLocks.PINE_FENCE, ModBLocks.PINE_PLANKS);
        buttonItem(ModBLocks.PINE_BUTTON, ModBLocks.PINE_PLANKS);
        wallItem(ModBLocks.PINE_WALL, ModBLocks.PINE_PLANKS);
        evenSimplerBlockItem(ModBLocks.PINE_STAIRS);
        evenSimplerBlockItem(ModBLocks.PINE_SLAB);
        evenSimplerBlockItem(ModBLocks.PINE_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.PINE_FENCE_GATE);
        trapdoorItem(ModBLocks.PINE_TRAPDOOR);
        //Ironwood
        simpleBlockItem(ModBLocks.IRONWOOD_DOOR);
        fenceItem(ModBLocks.IRONWOOD_FENCE, ModBLocks.IRONWOOD_PLANKS);
        buttonItem(ModBLocks.IRONWOOD_BUTTON, ModBLocks.IRONWOOD_PLANKS);
        wallItem(ModBLocks.IRONWOOD_WALL, ModBLocks.IRONWOOD_PLANKS);
        evenSimplerBlockItem(ModBLocks.IRONWOOD_STAIRS);
        evenSimplerBlockItem(ModBLocks.IRONWOOD_SLAB);
        evenSimplerBlockItem(ModBLocks.IRONWOOD_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.IRONWOOD_FENCE_GATE);
        trapdoorItem(ModBLocks.IRONWOOD_TRAPDOOR);
        //Hawthorn
        simpleBlockItem(ModBLocks.HAWTHORN_DOOR);
        fenceItem(ModBLocks.HAWTHORN_FENCE, ModBLocks.HAWTHORN_PLANKS);
        buttonItem(ModBLocks.HAWTHORN_BUTTON, ModBLocks.HAWTHORN_PLANKS);
        wallItem(ModBLocks.HAWTHORN_WALL, ModBLocks.HAWTHORN_PLANKS);
        evenSimplerBlockItem(ModBLocks.HAWTHORN_STAIRS);
        evenSimplerBlockItem(ModBLocks.HAWTHORN_SLAB);
        evenSimplerBlockItem(ModBLocks.HAWTHORN_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.HAWTHORN_FENCE_GATE);
        trapdoorItem(ModBLocks.HAWTHORN_TRAPDOOR);
        //Chestnut
        simpleBlockItem(ModBLocks.CHESTNUT_DOOR);
        fenceItem(ModBLocks.CHESTNUT_FENCE, ModBLocks.CHESTNUT_PLANKS);
        buttonItem(ModBLocks.CHESTNUT_BUTTON, ModBLocks.CHESTNUT_PLANKS);
        wallItem(ModBLocks.CHESTNUT_WALL, ModBLocks.CHESTNUT_PLANKS);
        evenSimplerBlockItem(ModBLocks.CHESTNUT_STAIRS);
        evenSimplerBlockItem(ModBLocks.CHESTNUT_SLAB);
        evenSimplerBlockItem(ModBLocks.CHESTNUT_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.CHESTNUT_FENCE_GATE);
        trapdoorItem(ModBLocks.CHESTNUT_TRAPDOOR);
        //Cedar
        simpleBlockItem(ModBLocks.CEDAR_DOOR);
        fenceItem(ModBLocks.CEDAR_FENCE, ModBLocks.CEDAR_PLANKS);
        buttonItem(ModBLocks.CEDAR_BUTTON, ModBLocks.CEDAR_PLANKS);
        wallItem(ModBLocks.CEDAR_WALL, ModBLocks.CEDAR_PLANKS);
        evenSimplerBlockItem(ModBLocks.CEDAR_STAIRS);
        evenSimplerBlockItem(ModBLocks.CEDAR_SLAB);
        evenSimplerBlockItem(ModBLocks.CEDAR_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.CEDAR_FENCE_GATE);
        trapdoorItem(ModBLocks.CEDAR_TRAPDOOR);
        //Beech
        simpleBlockItem(ModBLocks.BEECH_DOOR);
        fenceItem(ModBLocks.BEECH_FENCE, ModBLocks.BEECH_PLANKS);
        buttonItem(ModBLocks.BEECH_BUTTON, ModBLocks.BEECH_PLANKS);
        wallItem(ModBLocks.BEECH_WALL, ModBLocks.BEECH_PLANKS);
        evenSimplerBlockItem(ModBLocks.BEECH_STAIRS);
        evenSimplerBlockItem(ModBLocks.BEECH_SLAB);
        evenSimplerBlockItem(ModBLocks.BEECH_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.BEECH_FENCE_GATE);
        trapdoorItem(ModBLocks.BEECH_TRAPDOOR);
        //Ash
        simpleBlockItem(ModBLocks.ASH_DOOR);
        fenceItem(ModBLocks.ASH_FENCE, ModBLocks.ASH_PLANKS);
        buttonItem(ModBLocks.ASH_BUTTON, ModBLocks.ASH_PLANKS);
        wallItem(ModBLocks.ASH_WALL, ModBLocks.ASH_PLANKS);
        evenSimplerBlockItem(ModBLocks.ASH_STAIRS);
        evenSimplerBlockItem(ModBLocks.ASH_SLAB);
        evenSimplerBlockItem(ModBLocks.ASH_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.ASH_FENCE_GATE);
        trapdoorItem(ModBLocks.ASH_TRAPDOOR);
        //Blackbark
        simpleBlockItem(ModBLocks.BLACKBARK_DOOR);
        fenceItem(ModBLocks.BLACKBARK_FENCE, ModBLocks.BLACKBARK_PLANKS);
        buttonItem(ModBLocks.BLACKBARK_BUTTON, ModBLocks.BLACKBARK_PLANKS);
        wallItem(ModBLocks.BLACKBARK_WALL, ModBLocks.BLACKBARK_PLANKS);
        evenSimplerBlockItem(ModBLocks.BLACKBARK_STAIRS);
        evenSimplerBlockItem(ModBLocks.BLACKBARK_SLAB);
        evenSimplerBlockItem(ModBLocks.BLACKBARK_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.BLACKBARK_FENCE_GATE);
        trapdoorItem(ModBLocks.BLACKBARK_TRAPDOOR);
        //Aspen
        simpleBlockItem(ModBLocks.ASPEN_DOOR);
        fenceItem(ModBLocks.ASPEN_FENCE, ModBLocks.ASPEN_PLANKS);
        buttonItem(ModBLocks.ASPEN_BUTTON, ModBLocks.ASPEN_PLANKS);
        wallItem(ModBLocks.ASPEN_WALL, ModBLocks.ASPEN_PLANKS);
        evenSimplerBlockItem(ModBLocks.ASPEN_STAIRS);
        evenSimplerBlockItem(ModBLocks.ASPEN_SLAB);
        evenSimplerBlockItem(ModBLocks.ASPEN_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.ASPEN_FENCE_GATE);
        trapdoorItem(ModBLocks.ASPEN_TRAPDOOR);
        //Alder
        simpleBlockItem(ModBLocks.ALDER_DOOR);
        fenceItem(ModBLocks.ALDER_FENCE, ModBLocks.ALDER_PLANKS);
        buttonItem(ModBLocks.ALDER_BUTTON, ModBLocks.ALDER_PLANKS);
        wallItem(ModBLocks.ALDER_WALL, ModBLocks.ALDER_PLANKS);
        evenSimplerBlockItem(ModBLocks.ALDER_STAIRS);
        evenSimplerBlockItem(ModBLocks.ALDER_SLAB);
        evenSimplerBlockItem(ModBLocks.ALDER_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.ALDER_FENCE_GATE);
        trapdoorItem(ModBLocks.ALDER_TRAPDOOR);

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
        simpleBlockItemBlockTexture(ModBLocks.OPIUM_POPPY);
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


        // ---------------------------(CRAFTING ITEMS)--------------------------- //
        // Register item models for crafting items
        simpleItem(ModItems.BOAR_INTESTINES);
        simpleItem(ModItems.BOAR_TUSK);
        simpleItem(ModItems.WEIRWOOD_STICK);
    }

    private @NotNull ItemModelBuilder saplingItem(@NotNull DeferredHolder<Block, Block> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("block/" + item.getId().getPath()));
    }

    // Method to create a simple item model
    private @NotNull ItemModelBuilder simpleItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                modLoc(item.getId().getPath()))
                .texture("layer0",
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

}

// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.ModArmorMaterials;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanChiefArmorItem;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchEliteArmorItem;
import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchLeatherArmorItem;
import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchRangerArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingChiefArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingFurArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingLeatherArmorItem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.item.custom.CoinItem;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

// A utility class for creating and registering modded items
public class ModItems {

    // Deferred register for items
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AGoTMod.MOD_ID);
    // ---------------------------(Magic)--------------------------- //
    // Blood
    public static final DeferredItem<Item> BLOOD_BOTTLED = ITEMS.registerItem("blood_bottled",
            PotionItem::new);
    // Magical Dagger
    public static final DeferredItem<Item> BLOOD_DAGGER = ITEMS.registerItem("blood_dagger",
            properties -> new SwordItem(ModToolTiers.STEEL, 3, 1, properties));

    // ---------------------------(COINS)--------------------------- //
    // Main currency Coin
    public static final DeferredItem<Item> COIN = ITEMS.registerItem("coin",
            Item::new);
    // ---------------------------(COINS)--------------------------- //

    // ---------------------------(ARMOUR)--------------------------- //


    //Bolten Levy
    public static final DeferredItem<BoltenLevyArmorItem> BOLTEN_LEVY_HELMET = ITEMS.registerItem("bolton_levy_helmet",
            properties -> new BoltenLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.HELMET, properties));

    public static final DeferredItem<BoltenLevyArmorItem> BOLTEN_LEVY_CHESTPLATE = ITEMS.registerItem("bolton_levy_chestplate",
            properties -> new BoltenLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<BoltenLevyArmorItem> BOLTEN_LEVY_LEGGINGS = ITEMS.registerItem("bolton_levy_leggings",
            properties -> new BoltenLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<BoltenLevyArmorItem> BOLTEN_LEVY_BOOTS = ITEMS.registerItem("bolton_levy_boots",
            properties -> new BoltenLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.BOOTS, properties));

    //Bolten Plate
    public static final DeferredItem<BoltenPlateArmorItem> BOLTEN_PLATE_HELMET = ITEMS.registerItem("bolton_plate_helmet",
            properties -> new BoltenPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.HELMET, properties));

    public static final DeferredItem<BoltenPlateArmorItem> BOLTEN_PLATE_CHESTPLATE = ITEMS.registerItem("bolton_plate_chestplate",
            properties -> new BoltenPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<BoltenPlateArmorItem> BOLTEN_PLATE_LEGGINGS = ITEMS.registerItem("bolton_plate_leggings",
            properties -> new BoltenPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<BoltenPlateArmorItem> BOLTEN_PLATE_BOOTS = ITEMS.registerItem("bolton_plate_boots",
            properties -> new BoltenPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.BOOTS, properties));

    //Bolten Noble
    public static final DeferredItem<BoltenNobleArmorItem> BOLTEN_NOBLE_HELMET = ITEMS.registerItem("bolton_noble_helmet",
            properties -> new BoltenNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.HELMET, properties));

    public static final DeferredItem<BoltenNobleArmorItem> BOLTEN_NOBLE_CHESTPLATE = ITEMS.registerItem("bolton_noble_chestplate",
            properties -> new BoltenNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<BoltenNobleArmorItem> BOLTEN_NOBLE_LEGGINGS = ITEMS.registerItem("bolton_noble_leggings",
            properties -> new BoltenNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<BoltenNobleArmorItem> BOLTEN_NOBLE_BOOTS = ITEMS.registerItem("bolton_noble_boots",
            properties -> new BoltenNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.BOOTS, properties));

    //Manderly Levy
    public static final DeferredItem<ManderlyLevyArmorItem> MANDERLY_LEVY_HELMET = ITEMS.registerItem("manderly_levy_helmet",
            properties -> new ManderlyLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.HELMET, properties));

    public static final DeferredItem<ManderlyLevyArmorItem> MANDERLY_LEVY_CHESTPLATE = ITEMS.registerItem("manderly_levy_chestplate",
            properties -> new ManderlyLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<ManderlyLevyArmorItem> MANDERLY_LEVY_LEGGINGS = ITEMS.registerItem("manderly_levy_leggings",
            properties -> new ManderlyLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<ManderlyLevyArmorItem> MANDERLY_LEVY_BOOTS = ITEMS.registerItem("manderly_levy_boots",
            properties -> new ManderlyLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.BOOTS, properties));

    //Manderly Plate
    public static final DeferredItem<ManderlyPlateArmorItem> MANDERLY_PLATE_HELMET = ITEMS.registerItem("manderly_plate_helmet",
            properties -> new ManderlyPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.HELMET, properties));

    public static final DeferredItem<ManderlyPlateArmorItem> MANDERLY_PLATE_CHESTPLATE = ITEMS.registerItem("manderly_plate_chestplate",
            properties -> new ManderlyPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<ManderlyPlateArmorItem> MANDERLY_PLATE_LEGGINGS = ITEMS.registerItem("manderly_plate_leggings",
            properties -> new ManderlyPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<ManderlyPlateArmorItem> MANDERLY_PLATE_BOOTS = ITEMS.registerItem("manderly_plate_boots",
            properties -> new ManderlyPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.BOOTS, properties));

    //Manderly Noble
    public static final DeferredItem<ManderlyNobleArmorItem> MANDERLY_NOBLE_HELMET = ITEMS.registerItem("manderly_noble_helmet",
            properties -> new ManderlyNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.HELMET, properties));

    public static final DeferredItem<ManderlyNobleArmorItem> MANDERLY_NOBLE_CHESTPLATE = ITEMS.registerItem("manderly_noble_chestplate",
            properties -> new ManderlyNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<ManderlyNobleArmorItem> MANDERLY_NOBLE_LEGGINGS = ITEMS.registerItem("manderly_noble_leggings",
            properties -> new ManderlyNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<ManderlyNobleArmorItem> MANDERLY_NOBLE_BOOTS = ITEMS.registerItem("manderly_noble_boots",
            properties -> new ManderlyNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.BOOTS, properties));

    // Adding Stark Armour

    //Stark Levy
    public static final DeferredItem<StarkLevyArmorItem> STARK_LEVY_HELMET = ITEMS.registerItem("stark_levy_helmet",
            properties -> new StarkLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.HELMET, properties));

    public static final DeferredItem<StarkLevyArmorItem> STARK_LEVY_CHESTPLATE = ITEMS.registerItem("stark_levy_chestplate",
            properties -> new StarkLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<StarkLevyArmorItem> STARK_LEVY_LEGGINGS = ITEMS.registerItem("stark_levy_leggings",
            properties -> new StarkLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<StarkLevyArmorItem> STARK_LEVY_BOOTS = ITEMS.registerItem("stark_levy_boots",
            properties -> new StarkLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.BOOTS, properties));

    //Stark PLate
    public static final DeferredItem<StarkPlateArmorItem> STARK_PLATE_HELMET = ITEMS.registerItem("stark_plate_helmet",
            properties -> new StarkPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.HELMET, properties));

    public static final DeferredItem<StarkPlateArmorItem> STARK_PLATE_CHESTPLATE = ITEMS.registerItem("stark_plate_chestplate",
            properties -> new StarkPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<StarkPlateArmorItem> STARK_PLATE_LEGGINGS = ITEMS.registerItem("stark_plate_leggings",
            properties -> new StarkPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<StarkPlateArmorItem> STARK_PLATE_BOOTS = ITEMS.registerItem("stark_plate_boots",
            properties -> new StarkPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.BOOTS, properties));

    //Stark Noble
    public static final DeferredItem<StarkNoblePlateArmorItem> STARK_NOBLE_PLATE_HELMET = ITEMS.registerItem("stark_noble_plate_helmet",
            properties -> new StarkNoblePlateArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.HELMET, properties));

    public static final DeferredItem<StarkNoblePlateArmorItem> STARK_NOBLE_PLATE_CHESTPLATE = ITEMS.registerItem("stark_noble_plate_chestplate",
            properties -> new StarkNoblePlateArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<StarkNoblePlateArmorItem> STARK_NOBLE_PLATE_LEGGINGS = ITEMS.registerItem("stark_noble_plate_leggings",
            properties -> new StarkNoblePlateArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<StarkNoblePlateArmorItem> STARK_NOBLE_PLATE_BOOTS = ITEMS.registerItem("stark_noble_plate_boots",
            properties -> new StarkNoblePlateArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.BOOTS, properties));

    //Stark Noble
    public static final DeferredItem<MountainClanLevyArmorItem> MOUNTAIN_CLAN_LEVY_HELMET = ITEMS.registerItem("northern_mountain_clan_leather_helmet",
            properties -> new MountainClanLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.HELMET, properties));

    public static final DeferredItem<MountainClanLevyArmorItem> MOUNTAIN_CLAN_LEVY_CHESTPLATE = ITEMS.registerItem("northern_mountain_clan_leather_chestplate",
            properties -> new MountainClanLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<MountainClanLevyArmorItem> MOUNTAIN_CLAN_LEVY_LEGGINGS = ITEMS.registerItem("northern_mountain_clan_leather_leggings",
            properties -> new MountainClanLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<MountainClanLevyArmorItem> MOUNTAIN_CLAN_LEVY_BOOTS = ITEMS.registerItem("northern_mountain_clan_leather_boots",
            properties -> new MountainClanLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.BOOTS, properties));

    //Stark Noble
    public static final DeferredItem<MountainClanPlateArmorItem> MOUNTAIN_CLAN_PLATE_HELMET = ITEMS.registerItem("northern_mountain_clan_chain_helmet",
            properties -> new MountainClanPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.HELMET, properties));

    public static final DeferredItem<MountainClanPlateArmorItem> MOUNTAIN_CLAN_PLATE_CHESTPLATE = ITEMS.registerItem("northern_mountain_clan_chain_chestplate",
            properties -> new MountainClanPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<MountainClanPlateArmorItem> MOUNTAIN_CLAN_PLATE_LEGGINGS = ITEMS.registerItem("northern_mountain_clan_chain_leggings",
            properties -> new MountainClanPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<MountainClanPlateArmorItem> MOUNTAIN_CLAN_PLATE_BOOTS = ITEMS.registerItem("northern_mountain_clan_chain_boots",
            properties -> new MountainClanPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.BOOTS, properties));

    //Stark Noble
    public static final DeferredItem<MountainClanChiefArmorItem> MOUNTAIN_CLAN_CHIEF_HELMET = ITEMS.registerItem("northern_mountain_clan_noble_helmet",
            properties -> new MountainClanChiefArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.HELMET, properties));

    public static final DeferredItem<MountainClanChiefArmorItem> MOUNTAIN_CLAN_CHIEF_CHESTPLATE = ITEMS.registerItem("northern_mountain_clan_noble_chestplate",
            properties -> new MountainClanChiefArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<MountainClanChiefArmorItem> MOUNTAIN_CLAN_CHIEF_LEGGINGS = ITEMS.registerItem("northern_mountain_clan_noble_leggings",
            properties -> new MountainClanChiefArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<MountainClanChiefArmorItem> MOUNTAIN_CLAN_CHIEF_BOOTS = ITEMS.registerItem("northern_mountain_clan_noble_boots",
            properties -> new MountainClanChiefArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.BOOTS, properties));

    //Stark Noble
    public static final DeferredItem<IronBornLevyArmorItem> IRONBORN_LEVY_HELMET = ITEMS.registerItem("ironborn_levy_helmet",
            properties -> new IronBornLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.HELMET, properties));

    public static final DeferredItem<IronBornLevyArmorItem> IRONBORN_LEVY_CHESTPLATE = ITEMS.registerItem("ironborn_levy_chestplate",
            properties -> new IronBornLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<IronBornLevyArmorItem> IRONBORN_LEVY_LEGGINGS = ITEMS.registerItem("ironborn_levy_leggings",
            properties -> new IronBornLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<IronBornLevyArmorItem> IRONBORN_LEVY_BOOTS = ITEMS.registerItem("ironborn_levy_boots",
            properties -> new IronBornLevyArmorItem(ModArmorMaterials.NORTHERN_LEVY, ArmorType.BOOTS, properties));

    //Stark Noble
    public static final DeferredItem<IronBornPlateArmorItem> IRONBORN_PLATE_HELMET = ITEMS.registerItem("ironborn_plate_helmet",
            properties -> new IronBornPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.HELMET, properties));

    public static final DeferredItem<IronBornPlateArmorItem> IRONBORN_PLATE_CHESTPLATE = ITEMS.registerItem("ironborn_plate_chestplate",
            properties -> new IronBornPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<IronBornPlateArmorItem> IRONBORN_PLATE_LEGGINGS = ITEMS.registerItem("ironborn_plate_leggings",
            properties -> new IronBornPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<IronBornPlateArmorItem> IRONBORN_PLATE_BOOTS = ITEMS.registerItem("ironborn_plate_boots",
            properties -> new IronBornPlateArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.BOOTS, properties));

    //Stark Noble
    public static final DeferredItem<IronBornNobleArmorItem> IRONBORN_NOBLE_HELMET = ITEMS.registerItem("ironborn_noble_helmet",
            properties -> new IronBornNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.HELMET, properties));

    public static final DeferredItem<IronBornNobleArmorItem> IRONBORN_NOBLE_CHESTPLATE = ITEMS.registerItem("ironborn_noble_chestplate",
            properties -> new IronBornNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<IronBornNobleArmorItem> IRONBORN_NOBLE_LEGGINGS = ITEMS.registerItem("ironborn_noble_leggings",
            properties -> new IronBornNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<IronBornNobleArmorItem> IRONBORN_NOBLE_BOOTS = ITEMS.registerItem("ironborn_noble_boots",
            properties -> new IronBornNobleArmorItem(ModArmorMaterials.NORTHERN_NOBLE, ArmorType.BOOTS, properties));

    //Stark Noble


    //Stark Noble
    public static final DeferredItem<WildlingFurArmorItem> WILDLING_FUR_HELMET = ITEMS.registerItem("wildling_fur_helmet",
            properties -> new WildlingFurArmorItem(ModArmorMaterials.FUR, ArmorType.HELMET, properties));

    public static final DeferredItem<WildlingFurArmorItem> WILDLING_FUR_CHESTPLATE = ITEMS.registerItem("wildling_fur_chestplate",
            properties -> new WildlingFurArmorItem(ModArmorMaterials.FUR, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<WildlingFurArmorItem> WILDLING_FUR_LEGGINGS = ITEMS.registerItem("wildling_fur_leggings",
            properties -> new WildlingFurArmorItem(ModArmorMaterials.FUR, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<WildlingFurArmorItem> WILDLING_FUR_BOOTS = ITEMS.registerItem("wildling_fur_boots",
            properties -> new WildlingFurArmorItem(ModArmorMaterials.FUR, ArmorType.BOOTS, properties));

    public static final DeferredItem<WildlingLeatherArmorItem> WILDLING_LEATHER_HELMET = ITEMS.registerItem("wildling_leather_helmet",
            properties -> new WildlingLeatherArmorItem(ModArmorMaterials.LEATHER, ArmorType.HELMET, properties));

    public static final DeferredItem<WildlingLeatherArmorItem> WILDLING_LEATHER_CHESTPLATE = ITEMS.registerItem("wildling_leather_chestplate",
            properties -> new WildlingLeatherArmorItem(ModArmorMaterials.LEATHER, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<WildlingLeatherArmorItem> WILDLING_LEATHER_LEGGINGS = ITEMS.registerItem("wildling_leather_leggings",
            properties -> new WildlingLeatherArmorItem(ModArmorMaterials.LEATHER, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<WildlingLeatherArmorItem> WILDLING_LEATHER_BOOTS = ITEMS.registerItem("wildling_leather_boots",
            properties -> new WildlingLeatherArmorItem(ModArmorMaterials.LEATHER, ArmorType.BOOTS, properties));
    //Stark Noble
    public static final DeferredItem<WildlingChiefArmorItem> WILDLING_CHIEF_HELMET = ITEMS.registerItem("wildling_chief_helmet",
            properties -> new WildlingChiefArmorItem(ModArmorMaterials.CHIEF, ArmorType.HELMET, properties));

    public static final DeferredItem<WildlingChiefArmorItem> WILDLING_CHIEF_CHESTPLATE = ITEMS.registerItem("wildling_chief_chestplate",
            properties -> new WildlingChiefArmorItem(ModArmorMaterials.CHIEF, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<WildlingChiefArmorItem> WILDLING_CHIEF_LEGGINGS = ITEMS.registerItem("wildling_chief_leggings",
            properties -> new WildlingChiefArmorItem(ModArmorMaterials.CHIEF, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<WildlingChiefArmorItem> WILDLING_CHIEF_BOOTS = ITEMS.registerItem("wildling_chief_boots",
            properties -> new WildlingChiefArmorItem(ModArmorMaterials.CHIEF, ArmorType.BOOTS, properties));

    //Thenn Leather
    public static final DeferredItem<ThennLevyArmorItem> THENN_LEVY_HELMET = ITEMS.registerItem("thenn_leather_helmet",
            properties -> new ThennLevyArmorItem(ModArmorMaterials.FUR, ArmorType.HELMET, properties));

    public static final DeferredItem<ThennLevyArmorItem> THENN_LEVY_CHESTPLATE = ITEMS.registerItem("thenn_leather_chestplate",
            properties -> new ThennLevyArmorItem(ModArmorMaterials.FUR, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<ThennLevyArmorItem> THENN_LEVY_LEGGINGS = ITEMS.registerItem("thenn_leather_leggings",
            properties -> new ThennLevyArmorItem(ModArmorMaterials.FUR, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<ThennLevyArmorItem> THENN_LEVY_BOOTS = ITEMS.registerItem("thenn_leather_boots",
            properties -> new ThennLevyArmorItem(ModArmorMaterials.FUR, ArmorType.BOOTS, properties));

    //Thenn Bronze
    public static final DeferredItem<ThennPlateArmorItem> THENN_PLATE_HELMET = ITEMS.registerItem("thenn_bronze_helmet",
            properties -> new ThennPlateArmorItem(ModArmorMaterials.LEATHER, ArmorType.HELMET, properties));

    public static final DeferredItem<ThennPlateArmorItem> THENN_PLATE_CHESTPLATE = ITEMS.registerItem("thenn_bronze_chestplate",
            properties -> new ThennPlateArmorItem(ModArmorMaterials.LEATHER, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<ThennPlateArmorItem> THENN_PLATE_LEGGINGS = ITEMS.registerItem("thenn_bronze_leggings",
            properties -> new ThennPlateArmorItem(ModArmorMaterials.LEATHER, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<ThennPlateArmorItem> THENN_PLATE_BOOTS = ITEMS.registerItem("thenn_bronze_boots",
            properties -> new ThennPlateArmorItem(ModArmorMaterials.LEATHER, ArmorType.BOOTS, properties));

    //Thenn Chieftain
    public static final DeferredItem<ThennNobleArmorItem> THENN_NOBLE_HELMET = ITEMS.registerItem("thenn_chief_helmet",
            properties -> new ThennNobleArmorItem(ModArmorMaterials.CHIEF, ArmorType.HELMET, properties));

    public static final DeferredItem<ThennNobleArmorItem> THENN_NOBLE_CHESTPLATE = ITEMS.registerItem("thenn_chief_chestplate",
            properties -> new ThennNobleArmorItem(ModArmorMaterials.CHIEF, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<ThennNobleArmorItem> THENN_NOBLE_LEGGINGS = ITEMS.registerItem("thenn_chief_leggings",
            properties -> new ThennNobleArmorItem(ModArmorMaterials.CHIEF, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<ThennNobleArmorItem> THENN_NOBLE_BOOTS = ITEMS.registerItem("thenn_chief_boots",
            properties -> new ThennNobleArmorItem(ModArmorMaterials.CHIEF, ArmorType.BOOTS, properties));

    //Stark Noble

    //Stark Noble
    public static final DeferredItem<NightsWatchRangerArmorItem> NIGHT_WATCH_RANGER_HELMET = ITEMS.registerItem("night_watch_ranger_helmet",
            properties -> new NightsWatchRangerArmorItem(ModArmorMaterials.LEATHER, ArmorType.HELMET, properties));

    public static final DeferredItem<NightsWatchRangerArmorItem> NIGHT_WATCH_RANGER_CHESTPLATE = ITEMS.registerItem("night_watch_ranger_chestplate",
            properties -> new NightsWatchRangerArmorItem(ModArmorMaterials.LEATHER, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<NightsWatchRangerArmorItem> NIGHT_WATCH_RANGER_LEGGINGS = ITEMS.registerItem("night_watch_ranger_leggings",
            properties -> new NightsWatchRangerArmorItem(ModArmorMaterials.LEATHER, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<NightsWatchRangerArmorItem> NIGHT_WATCH_RANGER_BOOTS = ITEMS.registerItem("night_watch_ranger_boots",
            properties -> new NightsWatchRangerArmorItem(ModArmorMaterials.LEATHER, ArmorType.BOOTS, properties));

    public static final DeferredItem<NightsWatchLeatherArmorItem> NIGHT_WATCH_LEATHER_HELMET = ITEMS.registerItem("night_watch_leather_helmet",
            properties -> new NightsWatchLeatherArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.HELMET, properties));

    public static final DeferredItem<NightsWatchLeatherArmorItem> NIGHT_WATCH_LEATHER_CHESTPLATE = ITEMS.registerItem("night_watch_leather_chestplate",
            properties -> new NightsWatchLeatherArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<NightsWatchLeatherArmorItem> NIGHT_WATCH_LEATHER_LEGGINGS = ITEMS.registerItem("night_watch_leather_leggings",
            properties -> new NightsWatchLeatherArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<NightsWatchLeatherArmorItem> NIGHT_WATCH_LEATHER_BOOTS = ITEMS.registerItem("night_watch_leather_boots",
            properties -> new NightsWatchLeatherArmorItem(ModArmorMaterials.NORTHERN_PLATE, ArmorType.BOOTS, properties));

    //Stark Noble
    public static final DeferredItem<NightsWatchEliteArmorItem> NIGHT_WATCH_ELITE_HELMET = ITEMS.registerItem("night_watch_elite_helmet",
            properties -> new NightsWatchEliteArmorItem(ModArmorMaterials.CHIEF, ArmorType.HELMET, properties));

    public static final DeferredItem<NightsWatchEliteArmorItem> NIGHT_WATCH_ELITE_CHESTPLATE = ITEMS.registerItem("night_watch_elite_chestplate",
            properties -> new NightsWatchEliteArmorItem(ModArmorMaterials.CHIEF, ArmorType.CHESTPLATE, properties));

    public static final DeferredItem<NightsWatchEliteArmorItem> NIGHT_WATCH_ELITE_LEGGINGS = ITEMS.registerItem("night_watch_elite_leggings",
            properties -> new NightsWatchEliteArmorItem(ModArmorMaterials.CHIEF, ArmorType.LEGGINGS, properties));

    public static final DeferredItem<NightsWatchEliteArmorItem> NIGHT_WATCH_ELITE_BOOTS = ITEMS.registerItem("night_watch_elite_boots",
            properties -> new NightsWatchEliteArmorItem(ModArmorMaterials.CHIEF, ArmorType.BOOTS, properties));
    // ---------------------------(COINS)--------------------------- //
    public static final DeferredItem<Item> GOLD_DRAGON = ITEMS.registerItem("gold_dragon",
            properties -> new CoinItem(properties, 23520L)); // 1 dragon = 23520 halfpennies

    // Silver coins
    public static final DeferredItem<Item> SILVER_MOON = ITEMS.registerItem("silver_moon",
            properties -> new CoinItem(properties, 784L)); // 1 moon = 784 halfpennies

    public static final DeferredItem<Item> SILVER_STAG = ITEMS.registerItem("silver_stag",
            properties -> new CoinItem(properties, 112L)); // 1 stag = 112 halfpennies

    // Copper coins
    public static final DeferredItem<Item> COPPER_STAR = ITEMS.registerItem("copper_star",
            properties -> new CoinItem(properties, 16L)); // 1 star = 16 halfpennies

    public static final DeferredItem<Item> COPPER_GROAT = ITEMS.registerItem("copper_groat",
            properties -> new CoinItem(properties, 8L)); // 1 groat = 8 halfpennies

    public static final DeferredItem<Item> COPPER_HALFGROAT = ITEMS.registerItem("copper_halfgroat",
            properties -> new CoinItem(properties, 4L)); // 1 halfgroat = 4 halfpennies

    public static final DeferredItem<Item> COPPER_PENNY = ITEMS.registerItem("copper_penny",
            properties -> new CoinItem(properties, 2L)); // 1 penny = 2 halfpennies

    public static final DeferredItem<Item> COPPER_HALFPENNY = ITEMS.registerItem("copper_halfpenny",
            properties -> new CoinItem(properties, 1L));

    // ---------------------------(INGOTS/NUGGETS)--------------------------- //
    // TIN INGOT
    public static final DeferredItem<Item> SILVER_NUGGET = ITEMS.registerItem("silver_nugget",
            Item::new);
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.registerItem("silver_ingot",
            Item::new);
    // RAW TIN
    public static final DeferredItem<Item> RAW_SILVER = ITEMS.registerItem("raw_silver",
            Item::new);

    public static final DeferredItem<Item> TIN_INGOT = ITEMS.registerItem("tin_ingot",
            Item::new);
    // RAW TIN
    public static final DeferredItem<Item> RAW_TIN = ITEMS.registerItem("raw_tin",
            Item::new);
    // BRONZE INGOT
    public static final DeferredItem<Item> BRONZE_INGOT = ITEMS.registerItem("bronze_ingot",
            Item::new);

    public static final DeferredItem<Item> BRONZE_NUGGET = ITEMS.registerItem("bronze_nugget",
            Item::new);

    // STEEL INGOT
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerItem("steel_ingot",
            Item::new);
    public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.registerItem("steel_nugget",
            Item::new);

    // GEMS
    public static final DeferredItem<Item> YELLOW_DIAMOND = ITEMS.registerItem("yellow_diamond",
            Item::new);
    public static final DeferredItem<Item> TRANSPARENT_DIAMOND = ITEMS.registerItem("transparent_diamond",
            Item::new);
    public static final DeferredItem<Item> AMBER = ITEMS.registerItem("amber",
            Item::new);
    public static final DeferredItem<Item> AMETHYST = ITEMS.registerItem("amethyst",
            Item::new);
    public static final DeferredItem<Item> BLACK_DIAMOND = ITEMS.registerItem("black_diamond",
            Item::new);
    public static final DeferredItem<Item> BLOODSTONE = ITEMS.registerItem("bloodstone",
            Item::new);
    public static final DeferredItem<Item> CARNELIAN = ITEMS.registerItem("carnelian",
            Item::new);
    public static final DeferredItem<Item> CHALCEDONY = ITEMS.registerItem("chalcedony",
            Item::new);
    public static final DeferredItem<Item> GARNET = ITEMS.registerItem("garnet",
            Item::new);
    public static final DeferredItem<Item> JADE = ITEMS.registerItem("jade",
            Item::new);
    public static final DeferredItem<Item> JASPER = ITEMS.registerItem("jasper",
            Item::new);
    public static final DeferredItem<Item> MALACHITE = ITEMS.registerItem("malachite",
            Item::new);
    public static final DeferredItem<Item> MOONSTONE = ITEMS.registerItem("moonstone",
            Item::new);
    public static final DeferredItem<Item> ONYX = ITEMS.registerItem("onyx",
            Item::new);
    public static final DeferredItem<Item> OPAL = ITEMS.registerItem("opal",
            Item::new);
    public static final DeferredItem<Item> RUBY = ITEMS.registerItem("ruby",
            Item::new);
    public static final DeferredItem<Item> SAPPHIRE = ITEMS.registerItem("sapphire",
            Item::new);
    public static final DeferredItem<Item> TIGERS_EYE = ITEMS.registerItem("tigers_eye",
            Item::new);
    public static final DeferredItem<Item> TOPAZ = ITEMS.registerItem("topaz",
            Item::new);
    public static final DeferredItem<Item> TOURMALINE = ITEMS.registerItem("tourmaline",
            Item::new);


    // ---------------------------(BANNERS)--------------------------- //




    // ---------------------------(TOOLS)--------------------------- //
    // Bronze TOOLS
    public static final DeferredItem<Item> BRONZE_PICKAXE = ITEMS.registerItem("bronze_pickaxe",
            properties -> new PickaxeItem(ModToolTiers.BRONZE, 0, 0, properties));
    public static final DeferredItem<Item> BRONZE_SHOVEL = ITEMS.registerItem("bronze_shovel",
            properties -> new ShovelItem(ModToolTiers.BRONZE, 0, 0, properties));
    public static final DeferredItem<Item> BRONZE_AXE = ITEMS.registerItem("bronze_axe",
            properties -> new AxeItem(ModToolTiers.BRONZE, 6F, 0.9F, properties));
    public static final DeferredItem<Item> BRONZE_HOE = ITEMS.registerItem("bronze_hoe",
            properties -> new HoeItem(ModToolTiers.BRONZE, 0, 0, properties));

    // DRAGONGLASS WEAPONS
    public static final DeferredItem<Item> DRAGONGLASS_SPEAR = ITEMS.registerItem("dragonglass_spear",
            properties -> new LevelRequiredSwordItem(ModToolTiers.DRAGONGLASS, 5, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> DRAGONGLASS_DAGGER = ITEMS.registerItem("dragonglass_dagger",
            properties -> new LevelRequiredSwordItem(ModToolTiers.DRAGONGLASS, 2, -1.5F, properties, WeaponRequirements.SHORT_BLADE));

    // BRONZE WEAPONS
    public static final DeferredItem<Item> BRONZE_SWORD = ITEMS.registerItem("bronze_sword",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 5, -2.4F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> BRONZE_SPATHA = ITEMS.registerItem("bronze_spatha",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 7, -2.6F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> BRONZE_SPEAR = ITEMS.registerItem("bronze_spear",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 5, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> BRONZE_PIKE = ITEMS.registerItem("bronze_pike",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 6, -3F, properties, WeaponRequirements.LONG_POLEARM));
    public static final DeferredItem<Item> BRONZE_DAGGER = ITEMS.registerItem("bronze_dagger",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 2, -1.5F, properties, WeaponRequirements.SHORT_BLADE));
    public static final DeferredItem<Item> BRONZE_BATTLEAXE = ITEMS.registerItem("bronze_battleaxe",
            properties -> new LevelRequiredSwordItem(ModToolTiers.BRONZE, 8, -3F, properties, WeaponRequirements.ONE_HANDED));

    // IRON WEAPONS
    public static final DeferredItem<Item> IRON_LONGSWORD = ITEMS.registerItem("iron_longsword",
            properties -> new LevelRequiredSwordItem(ToolMaterial.IRON, 9, -2.6F, properties, WeaponRequirements.TWO_HANDED));
    public static final DeferredItem<Item> IRON_SPEAR = ITEMS.registerItem("iron_spear",
            properties -> new LevelRequiredSwordItem(ToolMaterial.IRON, 7, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> IRON_PIKE = ITEMS.registerItem("iron_pike",
            properties -> new LevelRequiredSwordItem(ToolMaterial.IRON, 8, -3F, properties, WeaponRequirements.LONG_POLEARM));
    public static final DeferredItem<Item> IRON_MACE = ITEMS.registerItem("iron_mace",
            properties -> new LevelRequiredSwordItem(ToolMaterial.IRON, 7, -2.6F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> IRON_DAGGER = ITEMS.registerItem("iron_dagger",
            properties -> new LevelRequiredSwordItem(ToolMaterial.IRON, 4, -1.5F, properties, WeaponRequirements.SHORT_BLADE));
    public static final DeferredItem<Item> IRON_BATTLEAXE = ITEMS.registerItem("iron_battleaxe",
            properties -> new LevelRequiredSwordItem(ToolMaterial.IRON, 10, -3F, properties, WeaponRequirements.ONE_HANDED));

    // STEEL WEAPONS
    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.registerItem("steel_sword",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 5, -2.4F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> STEEL_LONGSWORD = ITEMS.registerItem("steel_longsword",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 7, -2.6F, properties, WeaponRequirements.TWO_HANDED));
    public static final DeferredItem<Item> STEEL_SPEAR = ITEMS.registerItem("steel_spear",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 5, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> STEEL_PIKE = ITEMS.registerItem("steel_pike",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 6, -3F, properties, WeaponRequirements.LONG_POLEARM));
    public static final DeferredItem<Item> STEEL_MACE = ITEMS.registerItem("steel_mace",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 5, -2.6F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> STEEL_DAGGER = ITEMS.registerItem("steel_dagger",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 2, -1.5F, properties, WeaponRequirements.SHORT_BLADE));
    public static final DeferredItem<Item> STEEL_BATTLEAXE = ITEMS.registerItem("steel_battleaxe",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 8, -3F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> STEEL_HALBERD = ITEMS.registerItem("steel_halberd",
            properties -> new LevelRequiredSwordItem(ModToolTiers.STEEL, 9, -3.2F, properties, WeaponRequirements.POLEARM));

    // NOBLE WEAPONS
    public static final DeferredItem<Item> NOBLE_LONGSWORD = ITEMS.registerItem("noble_longsword",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 7, -2.6F, properties, WeaponRequirements.TWO_HANDED));
    public static final DeferredItem<Item> NOBLE_SPEAR = ITEMS.registerItem("noble_spear",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 5, -2.2F, properties, WeaponRequirements.POLEARM));
    public static final DeferredItem<Item> NOBLE_PIKE = ITEMS.registerItem("noble_pike",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 6, -3F, properties, WeaponRequirements.LONG_POLEARM));
    public static final DeferredItem<Item> NOBLE_MACE = ITEMS.registerItem("noble_mace",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 5, -2.6F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> NOBLE_DAGGER = ITEMS.registerItem("noble_dagger",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 2, -1.5F, properties, WeaponRequirements.SHORT_BLADE));
    public static final DeferredItem<Item> NOBLE_BATTLEAXE = ITEMS.registerItem("noble_battleaxe",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 8, -3F, properties, WeaponRequirements.ONE_HANDED));
    public static final DeferredItem<Item> NOBLE_HALBERD = ITEMS.registerItem("noble_halberd",
            properties -> new LevelRequiredSwordItem(ModToolTiers.NOBLE, 9, -3.2F, properties, WeaponRequirements.POLEARM));

    // Steel TOOLS
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.registerItem("steel_pickaxe",
            properties -> new PickaxeItem(ModToolTiers.STEEL, 1, 1, properties));
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.registerItem("steel_shovel",
            properties -> new ShovelItem(ModToolTiers.STEEL, 0, 0, properties));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.registerItem("steel_axe",
            properties -> new AxeItem(ModToolTiers.STEEL, 7, 1, properties));
    public static final DeferredItem<Item> STEEL_HOE = ITEMS.registerItem("steel_hoe",
            properties -> new HoeItem(ModToolTiers.STEEL, 0, 0, properties));
    // ---------------------------(TOOLS)--------------------------- //
    // ---------------------------(BOW & ARROW)--------------------------- //
    //Bows
    public static final DeferredItem<Item> STEEL_BOW = ITEMS.registerItem("steel_bow",
            BowItem::new);


    //Arrows
    public static final DeferredItem<Item> ARROW_BRONZE = ITEMS.registerItem("arrow_bronze",
            ArrowItem::new);
    public static final DeferredItem<Item> ARROW_DRAGONGLASS = ITEMS.registerItem("arrow_dragon_glass",
            ArrowItem::new);
    public static final DeferredItem<Item> ARROW_STEEL = ITEMS.registerItem("arrow_steel",
            ArrowItem::new);
    public static final DeferredItem<Item> ARROW_IRON = ITEMS.registerItem("arrow_iron",
            ArrowItem::new);

    // ---------------------------(BOW & ARROW)--------------------------- //


    // ---------------------------(FOODS)--------------------------- //
    public static final DeferredItem<Item> RAW_BEAR_MEAT = ITEMS.registerItem("raw_bear_meat",
            properties -> new Item(properties.food(ModFoods.RAW_BEAR_MEAT, ModConsumables.RAW_BEAR_MEAT)));
    public static final DeferredItem<Item> COOKED_BEAR_MEAT = ITEMS.registerItem("cooked_bear_meat",
            properties -> new Item(properties.food(ModFoods.COOKED_BEAR_MEAT)));
    public static final DeferredItem<Item> COOKED_WHITE_SAUSAGE = ITEMS.registerItem("cooked_white_sausage",
            properties -> new Item(properties.food(ModFoods.COOKED_BEAR_MEAT)));
    public static final DeferredItem<Item> COOKED_BLOOD_SAUSAGE = ITEMS.registerItem("cooked_blood_sausage",
            properties -> new Item(properties.food(ModFoods.COOKED_BEAR_MEAT)));
    public static final DeferredItem<Item> COOKED_SAUSAGE = ITEMS.registerItem("cooked_sausage",
            properties -> new Item(properties.food(ModFoods.COOKED_BEAR_MEAT)));
    public static final DeferredItem<Item> RAW_WHITE_SAUSAGE = ITEMS.registerItem("raw_white_sausage",
            properties -> new Item(properties.food(Foods.HONEY_BOTTLE, ModConsumables.RAW_WHITE_SAUSAGE)));
    public static final DeferredItem<Item> RAW_BLOOD_SAUSAGE = ITEMS.registerItem("raw_blood_sausage",
            properties -> new Item(properties.food(ModFoods.RAW_BLOOD_SAUSAGE, ModConsumables.RAW_BLOOD_SAUSAGE)));
    public static final DeferredItem<Item> RAW_SAUSAGE = ITEMS.registerItem("raw_sausage",
            properties -> new Item(properties.food(ModFoods.RAW_SAUSAGE, ModConsumables.RAW_SAUSAGE)));
    public static final DeferredItem<Item> COOKED_BACON = ITEMS.registerItem("cooked_bacon",
            properties -> new Item(properties.food(ModFoods.COOKED_BACON)));
    public static final DeferredItem<Item> RAW_BACON = ITEMS.registerItem("raw_bacon",
            properties -> new Item(properties.food(ModFoods.RAW_BACON, ModConsumables.RAW_BACON)));
    public static final DeferredItem<Item> COOKED_BOAR_VENISON = ITEMS.registerItem("cooked_boar_venison",
            properties -> new Item(properties.food(ModFoods.COOKED_BOAR_VENISON)));
    public static final DeferredItem<Item> RAW_BOAR_VENISON = ITEMS.registerItem("raw_boar_venison",
            properties -> new Item(properties.food(ModFoods.RAW_BOAR_VENISON, ModConsumables.RAW_BOAR_VENISON)));
    public static final DeferredItem<Item> COOKED_CHICKEN_NUGGETS = ITEMS.registerItem("cooked_chicken_nuggets",
            properties -> new Item(properties.food(ModFoods.COOKED_CHICKEN_NUGGETS)));
    public static final DeferredItem<Item> RAW_CHICKEN_NUGGETS = ITEMS.registerItem("raw_chicken_nuggets",
            properties -> new Item(properties.food(ModFoods.RAW_CHICKEN_NUGGETS, ModConsumables.RAW_CHICKEN_NUGGETS)));
    public static final DeferredItem<Item> COOKED_DEER_VENISON = ITEMS.registerItem("cooked_deer_venison",
            properties -> new Item(properties.food(ModFoods.COOKED_DEER_VENISON)));
    public static final DeferredItem<Item> RAW_DEER_VENISON = ITEMS.registerItem("raw_deer_venison",
            properties -> new Item(properties.food(ModFoods.RAW_DEER_VENISON, ModConsumables.RAW_DEER_VENISON)));
    public static final DeferredItem<Item> COOKED_GOAT_MEAT = ITEMS.registerItem("cooked_goat_meat",
            properties -> new Item(properties.food(ModFoods.COOKED_GOAT_MEAT)));
    public static final DeferredItem<Item> RAW_GOAT_MEAT = ITEMS.registerItem("raw_goat_meat",
            properties -> new Item(properties.food(ModFoods.RAW_GOAT_MEAT, ModConsumables.RAW_GOAT_MEAT)));
    public static final DeferredItem<Item> COOKED_HARE_MEAT = ITEMS.registerItem("cooked_hare_meat",
            properties -> new Item(properties.food(ModFoods.COOKED_HARE_MEAT)));
    public static final DeferredItem<Item> RAW_HARE_MEAT = ITEMS.registerItem("raw_hare_meat",
            properties -> new Item(properties.food(ModFoods.RAW_HARE_MEAT, ModConsumables.RAW_HARE_MEAT)));
    public static final DeferredItem<Item> COOKED_HORSE_MEAT = ITEMS.registerItem("cooked_horse_meat",
            properties -> new Item(properties.food(ModFoods.COOKED_HORSE_MEAT)));
    public static final DeferredItem<Item> RAW_HORSE_MEAT = ITEMS.registerItem("raw_horse_meat",
            properties -> new Item(properties.food(ModFoods.RAW_HORSE_MEAT, ModConsumables.RAW_HORSE_MEAT)));
    public static final DeferredItem<Item> COOKED_MAMMOTH_MEAT = ITEMS.registerItem("cooked_mammoth_meat",
            properties -> new Item(properties.food(ModFoods.COOKED_MAMMOTH_MEAT)));
    public static final DeferredItem<Item> RAW_MAMMOTH_MEAT = ITEMS.registerItem("raw_mammoth_meat",
            properties -> new Item(properties.food(ModFoods.RAW_MAMMOTH_MEAT, ModConsumables.RAW_MAMMOTH_MEAT)));
    public static final DeferredItem<Item> HORSERADISH = ITEMS.registerItem("horseradish",
            properties -> new Item(properties.food(ModFoods.HORSERADISH)));
    public static final DeferredItem<Item> LEEK = ITEMS.registerItem("leek",
            properties -> new Item(properties.food(ModFoods.LEEK)));
    public static final DeferredItem<Item> NEEP = ITEMS.registerItem("neep",
            properties -> new Item(properties.food(ModFoods.NEEP)));
    public static final DeferredItem<Item> OAT = ITEMS.registerItem("oat",
            properties -> new Item(properties.food(ModFoods.OAT)));
    public static final DeferredItem<Item> PARSLEY = ITEMS.registerItem("parsley",
            properties -> new Item(properties.food(ModFoods.PARSLEY)));
    public static final DeferredItem<Item> RED_ONION = ITEMS.registerItem("red_onion",
            properties -> new Item(properties.food(ModFoods.RED_ONION)));
    public static final DeferredItem<Item> TURNIP = ITEMS.registerItem("turnip",
            properties -> new Item(properties.food(ModFoods.TURNIP)));
    public static final DeferredItem<Item> WILD_ONION = ITEMS.registerItem("wild_onion",
            properties -> new Item(properties.food(ModFoods.WILD_ONION)));
    public static final DeferredItem<Item> ONION = ITEMS.registerItem("onion",
            properties -> new Item(properties.food(ModFoods.ONION)));
    public static final DeferredItem<Item> BARLEY = ITEMS.registerItem("barley", Item::new);
    public static final DeferredItem<Item> CABBAGE = ITEMS.registerItem("cabbage",
            properties -> new Item(properties.food(ModFoods.CABBAGE)));
    public static final DeferredItem<Item> BEAN = ITEMS.registerItem("bean",
            properties -> new Item(properties.food(ModFoods.BEAN)));
    public static final DeferredItem<Item> CHICKPEA = ITEMS.registerItem("chickpea",
            properties -> new Item(properties.food(ModFoods.CHICKPEA)));
    public static final DeferredItem<Item> CUCUMBER = ITEMS.registerItem("cucumber",
            properties -> new Item(properties.food(ModFoods.CUCUMBER)));
    public static final DeferredItem<Item> GREEN_BEAN = ITEMS.registerItem("green_bean",
            properties -> new Item(properties.food(ModFoods.GREEN_BEAN)));
    public static final DeferredItem<Item> SPINACH = ITEMS.registerItem("spinach",
            properties -> new Item(properties.food(ModFoods.SPINACH)));
    public static final DeferredItem<Item> STRAWBERRY = ITEMS.registerItem("strawberry",
            properties -> new Item(properties.food(ModFoods.STRAWBERRY)));
    public static final DeferredItem<Item> BLACKBERRY = ITEMS.registerItem("blackberry",
            properties -> new Item(properties.food(ModFoods.BLACKBERRY)));
    public static final DeferredItem<Item> BLUEBERRY = ITEMS.registerItem("blueberry",
            properties -> new Item(properties.food(ModFoods.BLUEBERRY)));
    public static final DeferredItem<Item> MULBERRY = ITEMS.registerItem("mulberry",
            properties -> new Item(properties.food(ModFoods.MULBERRY)));
    public static final DeferredItem<Item> RASPBERRY = ITEMS.registerItem("raspberry",
            properties -> new Item(properties.food(ModFoods.RASPBERRY)));
    public static final DeferredItem<Item> SMOKEBERRY = ITEMS.registerItem("smokeberry",
            properties -> new Item(properties.food(ModFoods.SMOKEBERRY)));
    public static final DeferredItem<Item> CORN = ITEMS.registerItem("corn",
            properties -> new Item(properties.food(ModFoods.CORN)));
    public static final DeferredItem<Item> DRAGON_PEPPER = ITEMS.registerItem("dragon_pepper", Item::new);
    public static final DeferredItem<Item> PEPPER = ITEMS.registerItem("pepper", Item::new);
    public static final DeferredItem<Item> PEPPERCORN = ITEMS.registerItem("peppercorn", Item::new);
    public static final DeferredItem<Item> COTTON = ITEMS.registerItem("cotton", Item::new);
    public static final DeferredItem<Item> HEMP = ITEMS.registerItem("hemp", Item::new);




    // ---------------------------(FOODS)--------------------------- //

    // ---------------------------(CRAFTING INGREDIENTS)--------------------------- //
    public static final DeferredItem<Item> BOAR_INTESTINES = ITEMS.registerItem("boar_intestines",
            Item::new);
    public static final DeferredItem<Item> BOAR_TUSK = ITEMS.registerItem("boar_tusk",
            Item::new);
    public static final DeferredItem<Item> IVORY = ITEMS.registerItem("ivory",
            Item::new);
    public static final DeferredItem<Item> IVORY_SHARD = ITEMS.registerItem("ivory_shard",
            Item::new);
    public static final DeferredItem<Item> BOILED_LEATHER = ITEMS.registerItem("boiled_leather",
            Item::new);
    public static final DeferredItem<Item> FUR = ITEMS.registerItem("fur",
            Item::new);
    public static final DeferredItem<Item> WEIRWOOD_STICK = ITEMS.registerItem("weirwood_stick",
            Item::new);
    public static final DeferredItem<Item> BRONZE_CHAIN_LINK = ITEMS.registerItem("bronze_chain_link", Item::new);
    public static final DeferredItem<Item> BRONZE_CHAIN = ITEMS.registerItem("bronze_chain", Item::new);
    public static final DeferredItem<Item> BRONZE_PLATE = ITEMS.registerItem("bronze_plate", Item::new);
    public static final DeferredItem<Item> CLOTH = ITEMS.registerItem("cloth", Item::new);
    public static final DeferredItem<Item> IRON_CHAIN_LINK = ITEMS.registerItem("iron_chain_link", Item::new);
    public static final DeferredItem<Item> IRON_CHAIN = ITEMS.registerItem("iron_chain", Item::new);
    public static final DeferredItem<Item> IRON_PLATE = ITEMS.registerItem("iron_plate", Item::new);
    public static final DeferredItem<Item> NOBLE_PLATE = ITEMS.registerItem("noble_plate", Item::new);
    public static final DeferredItem<Item> STEEL_BOOTS = ITEMS.registerItem("steel_boots", Item::new);
    public static final DeferredItem<Item> STEEL_CHAIN_LINK = ITEMS.registerItem("steel_chain_link", Item::new);
    public static final DeferredItem<Item> STEEL_CHAIN = ITEMS.registerItem("steel_chain", Item::new);
    public static final DeferredItem<Item> HAMMER = ITEMS.registerItem("hammer", properties ->
            new PickaxeItem(ModToolTiers.STEEL, 1, 1, properties) {
                @Override
                public ItemStack getCraftingRemainder(ItemStack stack) {
                    return stack.copy();
                }
            }
    );
    public static final DeferredItem<Item> STEEL_HELMET = ITEMS.registerItem("steel_helmet", Item::new);
    public static final DeferredItem<Item> STEEL_LEGGINGS = ITEMS.registerItem("steel_leggings", Item::new);
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.registerItem("steel_plate", Item::new);
    public static final DeferredItem<Item> STEEL_CHESTPLATE = ITEMS.registerItem("steel_chestplate", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_BRONZE = ITEMS.registerItem("upgrade_kit_bronze", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_IRON = ITEMS.registerItem("upgrade_kit_iron", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_NOBLE = ITEMS.registerItem("upgrade_kit_noble", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_STEEL = ITEMS.registerItem("upgrade_kit_steel", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_BRONZE_PLATE = ITEMS.registerItem("upgrade_kit_bronze_plate", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_CHIEF = ITEMS.registerItem("upgrade_kit_chief", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_FUR = ITEMS.registerItem("upgrade_kit_fur", Item::new);
    public static final DeferredItem<Item> UPGRADE_KIT_LEATHER = ITEMS.registerItem("upgrade_kit_leather", Item::new);

    public static final DeferredItem<Item> STARK_LEVY_SMITHING_SCROLL = ITEMS.registerItem("stark_levy_smithing_scroll", Item::new);
    public static final DeferredItem<Item> STARK_PLATE_SMITHING_SCROLL = ITEMS.registerItem("stark_plate_smithing_scroll", Item::new);
    public static final DeferredItem<Item> STARK_NOBLE_SMITHING_SCROLL = ITEMS.registerItem("stark_noble_smithing_scroll", Item::new);
    public static final DeferredItem<Item> BOLTON_LEVY_SMITHING_SCROLL = ITEMS.registerItem("bolton_levy_smithing_scroll", Item::new);
    public static final DeferredItem<Item> BOLTON_PLATE_SMITHING_SCROLL = ITEMS.registerItem("bolton_plate_smithing_scroll", Item::new);
    public static final DeferredItem<Item> BOLTON_NOBLE_SMITHING_SCROLL = ITEMS.registerItem("bolton_noble_smithing_scroll", Item::new);
    public static final DeferredItem<Item> MANDERLY_LEVY_SMITHING_SCROLL = ITEMS.registerItem("manderly_levy_smithing_scroll", Item::new);
    public static final DeferredItem<Item> MANDERLY_PLATE_SMITHING_SCROLL = ITEMS.registerItem("manderly_plate_smithing_scroll", Item::new);
    public static final DeferredItem<Item> MANDERLY_NOBLE_SMITHING_SCROLL = ITEMS.registerItem("manderly_noble_smithing_scroll", Item::new);
    public static final DeferredItem<Item> NORTHERN_MOUNTAIN_CLAN_LEATHER_SMITHING_SCROLL = ITEMS.registerItem("northern_mountain_clan_leather_smithing_scroll", Item::new);
    public static final DeferredItem<Item> NORTHERN_MOUNTAIN_CLAN_CHAIN_SMITHING_SCROLL = ITEMS.registerItem("northern_mountain_clan_chain_smithing_scroll", Item::new);
    public static final DeferredItem<Item> NORTHERN_MOUNTAIN_CLAN_NOBLE_SMITHING_SCROLL = ITEMS.registerItem("northern_mountain_clan_noble_smithing_scroll", Item::new);
    public static final DeferredItem<Item> NIGHTS_WATCH_RANGER_SMITHING_SCROLL = ITEMS.registerItem("nights_watch_ranger_smithing_scroll", Item::new);
    public static final DeferredItem<Item> NIGHTS_WATCH_LEATHER_SMITHING_SCROLL = ITEMS.registerItem("nights_watch_leather_smithing_scroll", Item::new);
    public static final DeferredItem<Item> NIGHTS_WATCH_ELITE_SMITHING_SCROLL = ITEMS.registerItem("nights_watch_elite_smithing_scroll", Item::new);
    public static final DeferredItem<Item> WILDLING_FUR_SMITHING_SCROLL = ITEMS.registerItem("wildling_fur_smithing_scroll", Item::new);
    public static final DeferredItem<Item> WILDLING_LEATHER_SMITHING_SCROLL = ITEMS.registerItem("wildling_leather_smithing_scroll", Item::new);
    public static final DeferredItem<Item> WILDLING_CHIEF_SMITHING_SCROLL = ITEMS.registerItem("wildling_chief_smithing_scroll", Item::new);
    public static final DeferredItem<Item> THENN_LEVY_SMITHING_SCROLL = ITEMS.registerItem("thenn_levy_smithing_scroll", Item::new);
    public static final DeferredItem<Item> THENN_PLATE_SMITHING_SCROLL = ITEMS.registerItem("thenn_plate_smithing_scroll", Item::new);
    public static final DeferredItem<Item> THENN_NOBLE_SMITHING_SCROLL = ITEMS.registerItem("thenn_noble_smithing_scroll", Item::new);
    public static final DeferredItem<Item> IRONBORN_LEVY_SMITHING_SCROLL = ITEMS.registerItem("ironborn_levy_smithing_scroll", Item::new);
    public static final DeferredItem<Item> IRONBORN_PLATE_SMITHING_SCROLL = ITEMS.registerItem("ironborn_plate_smithing_scroll", Item::new);
    public static final DeferredItem<Item> IRONBORN_NOBLE_SMITHING_SCROLL = ITEMS.registerItem("ironborn_noble_smithing_scroll", Item::new);





    public static final DeferredItem<Item> WEIRWOOD_SIGN = ITEMS.registerItem("weirwood_sign",
            properties -> new SignItem(ModBLocks.WEIRWOOD_SIGN.get(),
                    ModBLocks.WEIRWOOD_WALL_SIGN.get(), properties.stacksTo(16)));

    public static final DeferredItem<Item> WEIRWOOD_HANGING_SIGN = ITEMS.registerItem("weirwood_hanging_sign",
            properties -> new HangingSignItem(ModBLocks.WEIRWOOD_HANGING_SIGN.get(),
                    ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get(), properties.stacksTo(16)));

    // In your ModItems class
    public static final Map<String, DeferredItem<Item>> SIGN_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> HANGING_SIGN_ITEMS = new HashMap<>();

    // Define the wood types array
    private static final String[] WOOD_TYPES = {
            "sycamore",
            "pine",
            "ash",
            "beech",
            "cedar",
            "chestnut",
            "hawthorn",
            "ironwood",
            "sentinel",
            "blackbark",
            "aspen",
            "black_cherry",
            "black_olive",
            "crabapple",
            "olive",
            "white_cherry",
            "red_cherry",
            "fir",
            "willow",
            "wormtree",
            "alder",
            "almond",
            "apple",
            "apricot",
            "baobab",
            "black_cottonwood",
            "blackthorn",
            "blood_orange",
            "bloodwood",
            "blue_mahoe",
            "cottonwood",
            "datepalm",
            "ebony",
            "fig",
            "fireplum",
            "goldenheart",
            "lemon",
            "lime",
            "linden",
            "mahogany",
            "maple",
            "myrrh",
            "nightwood",
            "nutmeg",
            "orange",
            "peach",
            "pear",
            "pecan",
            "persimmon",
            "pink_ivory",
            "plum",
            "pomegranate",
            "purpleheart",
            "redwood",
            "sandalwood",
            "sandbeggar",
            "tigerwood",
            "yew"
    };

    // Static initialization block to register all wood signs
    static {
        // Register all wood signs using a loop
        for (String woodType : WOOD_TYPES) {
            // Regular signs
            SIGN_ITEMS.put(woodType, ITEMS.registerItem(woodType + "_sign",
                    properties -> new SignItem(
                            ModBLocks.SIGNS.get(woodType).get(),
                            ModBLocks.WALL_SIGNS.get(woodType).get(),
                            properties.stacksTo(16)
                    )
            ));

            // Hanging signs
            HANGING_SIGN_ITEMS.put(woodType, ITEMS.registerItem(woodType + "_hanging_sign",
                    properties -> new HangingSignItem(
                            ModBLocks.HANGING_SIGNS.get(woodType).get(),
                            ModBLocks.WALL_HANGING_SIGNS.get(woodType).get(),
                            properties.stacksTo(16)
                    )
            ));
        }
    }

// No need for the getBlockField method anymore since we access maps directly


    // ---------------------------(SEEDS)--------------------------- //

    public static final DeferredItem<Item> HORSERADISH_SEEDS = ITEMS.registerItem("horseradish_seeds",
            properties -> new BlockItem(ModBLocks.HORSERADISH_CROP.get(), properties));

    public static final DeferredItem<Item> BARLEY_SEEDS = ITEMS.registerItem("barley_seeds",
            properties -> new BlockItem(ModBLocks.BARLEY_CROP.get(), properties));

    public static final DeferredItem<Item> GARLIC = ITEMS.registerItem("garlic",
            properties -> new BlockItem(ModBLocks.GARLIC_CROP.get(), properties));

    public static final DeferredItem<Item> LEEK_SEEDS = ITEMS.registerItem("leek_seeds",
            properties -> new BlockItem(ModBLocks.LEEK_CROP.get(), properties));

    public static final DeferredItem<Item> NEEP_SEEDS = ITEMS.registerItem("neep_seeds",
            properties -> new BlockItem(ModBLocks.NEEP_CROP.get(), properties));

    public static final DeferredItem<Item> OAT_SEEDS = ITEMS.registerItem("oat_seeds",
            properties -> new BlockItem(ModBLocks.OAT_CROP.get(), properties));

    public static final DeferredItem<Item> PARSLEY_SEEDS = ITEMS.registerItem("parsley_seeds",
            properties -> new BlockItem(ModBLocks.PARSLEY_CROP.get(), properties));

    public static final DeferredItem<Item> RED_ONION_SEEDS = ITEMS.registerItem("red_onion_seeds",
            properties -> new BlockItem(ModBLocks.RED_ONION_CROP.get(), properties));

    public static final DeferredItem<Item> TURNIP_SEEDS = ITEMS.registerItem("turnip_seeds",
            properties -> new BlockItem(ModBLocks.TURNIP_CROP.get(), properties));

    public static final DeferredItem<Item> WILD_ONION_SEEDS = ITEMS.registerItem("wild_onion_seeds",
            properties -> new BlockItem(ModBLocks.WILD_ONION_CROP.get(), properties));

    public static final DeferredItem<Item> ONION_SEEDS = ITEMS.registerItem("onion_seeds",
            properties -> new BlockItem(ModBLocks.ONION_CROP.get(), properties));

    public static final DeferredItem<Item> OPIUM_POPPY_SEEDS = ITEMS.registerItem("opium_poppy_seeds",
            properties -> new BlockItem(ModBLocks.OPIUM_POPPY_CROP.get(), properties));

    public static final DeferredItem<Item> CABBAGE_SEEDS = ITEMS.registerItem("cabbage_seeds",
            properties -> new BlockItem(ModBLocks.CABBAGE_CROP.get(), properties));
    public static final DeferredItem<Item> BEAN_SEEDS = ITEMS.registerItem("bean_seeds",
            properties -> new BlockItem(ModBLocks.BEAN_CROP.get(), properties));

    public static final DeferredItem<Item> CHICKPEA_SEEDS = ITEMS.registerItem("chickpea_seeds",
            properties -> new BlockItem(ModBLocks.CHICKPEA_CROP.get(), properties));

    public static final DeferredItem<Item> CUCUMBER_SEEDS = ITEMS.registerItem("cucumber_seeds",
            properties -> new BlockItem(ModBLocks.CUCUMBER_CROP.get(), properties));

    public static final DeferredItem<Item> GREEN_BEAN_SEEDS = ITEMS.registerItem("green_bean_seeds",
            properties -> new BlockItem(ModBLocks.GREEN_BEAN_CROP.get(), properties));

    public static final DeferredItem<Item> SPINACH_SEEDS = ITEMS.registerItem("spinach_seeds",
            properties -> new BlockItem(ModBLocks.SPINACH_CROP.get(), properties));

    public static final DeferredItem<Item> DRAGON_PEPPER_SEEDS = ITEMS.registerItem("dragon_pepper_seeds",
            properties -> new BlockItem(ModBLocks.DRAGON_PEPPER_CROP.get(), properties));

    public static final DeferredItem<Item> PEPPER_SEEDS = ITEMS.registerItem("pepper_seeds",
            properties -> new BlockItem(ModBLocks.PEPPER_CROP.get(), properties));

    public static final DeferredItem<Item> PEPPERCORN_SEEDS = ITEMS.registerItem("peppercorn_seeds",
            properties -> new BlockItem(ModBLocks.PEPPERCORN_CROP.get(), properties));

    public static final DeferredItem<Item> COTTON_SEEDS = ITEMS.registerItem("cotton_seeds",
            properties -> new BlockItem(ModBLocks.COTTON_CROP.get(), properties));

    public static final DeferredItem<Item> HEMP_SEEDS = ITEMS.registerItem("hemp_seeds",
            properties -> new BlockItem(ModBLocks.HEMP_CROP.get(), properties));

    public static final DeferredItem<Item> STRAWBERRY_SEEDS = ITEMS.registerItem("strawberry_seeds",
            properties -> new BlockItem(ModBLocks.STRAWBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> BLACKBERRY_SEEDS = ITEMS.registerItem("blackberry_seeds",
            properties -> new BlockItem(ModBLocks.BLACKBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> BLUEBERRY_SEEDS = ITEMS.registerItem("blueberry_seeds",
            properties -> new BlockItem(ModBLocks.BLUEBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> MULBERRY_SEEDS = ITEMS.registerItem("mulberry_seeds",
            properties -> new BlockItem(ModBLocks.MULBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> RASPBERRY_SEEDS = ITEMS.registerItem("raspberry_seeds",
            properties -> new BlockItem(ModBLocks.RASPBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> SMOKEBERRY_SEEDS = ITEMS.registerItem("smokeberry_seeds",
            properties -> new BlockItem(ModBLocks.SMOKEBERRY_BUSH.get(), properties));

    public static final DeferredItem<Item> CORN_SEEDS = ITEMS.registerItem("corn_seeds",
            properties -> new BlockItem(ModBLocks.CORN_CROP.get(), properties));

    public static final DeferredItem<Item> CORN_MIDDLE_SEEDS = ITEMS.registerItem("corn_middle_seeds",
            properties -> new BlockItem(ModBLocks.CORN_CROP_MIDDLE.get(), properties));

    public static final DeferredItem<Item> CORN_TOP_SEEDS = ITEMS.registerItem("corn_top_seeds",
            properties -> new BlockItem(ModBLocks.CORN_CROP_TOP.get(), properties));




    // ---------------------------(SPAWN EGGS)--------------------------- //

    public static final DeferredItem<Item> MAMMOTH_SPAWN_EGG = ITEMS.registerItem("mammoth_spawn_egg",
            properties -> new SpawnEggItem(ModEntities.MAMMOTH_ENTITY.get(),
                    0x422a0d,
                    0xfffac2,
                    properties));

    public static final DeferredItem<Item> CROW_SPAWN_EGG = ITEMS.registerItem("crow_spawn_egg",
            properties -> new SpawnEggItem(ModEntities.CROW_ENTITY.get(),
                    0x0f0f0f,
                    0x333333,
                    properties));

    public static final DeferredItem<Item> DIREWOLF_SPAWN_EGG = ITEMS.registerItem("direwolf_spawn_egg",
            properties -> new SpawnEggItem(ModEntities.DIREWOLF_ENTITY.get(),
                    0x575757,
                    0x919191,
                    properties));

    public static final DeferredItem<Item> PEASANT_SPAWN_EGG = ITEMS.registerItem("peasant_spawn_egg",
            properties -> new SpawnEggItem(ModEntities.PEASANT_ENTITY.get(),
                    0x575757,
                    0x333333,
                    properties));


    // Town Hall (existing)
    public static final DeferredItem<Item> TOWN_HALL = ITEMS.registerItem("town_hall",
            properties -> new BlockItem(ModBLocks.TOWN_HALL.get(), properties));

    // All Job Barrel Item Registrations
    public static final DeferredItem<Item> ALEHOUSE_BARREL = ITEMS.registerItem("alehouse_barrel_icon",
            properties -> new BlockItem(ModBLocks.ALEHOUSE_BARREL.get(), properties));

    public static final DeferredItem<Item> ARMORSMITH_BARREL = ITEMS.registerItem("armorsmith_barrel_icon",
            properties -> new BlockItem(ModBLocks.ARMORSMITH_BARREL.get(), properties));

    public static final DeferredItem<Item> BAKER_BARREL = ITEMS.registerItem("baker_barrel_icon",
            properties -> new BlockItem(ModBLocks.BAKER_BARREL.get(), properties));

    public static final DeferredItem<Item> BANKER_BARREL = ITEMS.registerItem("banker_barrel_icon",
            properties -> new BlockItem(ModBLocks.BANKER_BARREL.get(), properties));

    public static final DeferredItem<Item> BARD_BARREL = ITEMS.registerItem("bard_barrel_icon",
            properties -> new BlockItem(ModBLocks.BARD_BARREL.get(), properties));

    public static final DeferredItem<Item> BUILDER_BARREL = ITEMS.registerItem("builder_barrel_icon",
            properties -> new BlockItem(ModBLocks.BUILDER_BARREL.get(), properties));

    public static final DeferredItem<Item> BUTCHER_BARREL = ITEMS.registerItem("butcher_barrel_icon",
            properties -> new BlockItem(ModBLocks.BUTCHER_BARREL.get(), properties));

    public static final DeferredItem<Item> CARAVAN_MASTER_BARREL = ITEMS.registerItem("caravan_master_barrel_icon",
            properties -> new BlockItem(ModBLocks.CARAVAN_MASTER_BARREL.get(), properties));

    public static final DeferredItem<Item> CATTLE_HERDER_BARREL = ITEMS.registerItem("cattle_herder_barrel_icon",
            properties -> new BlockItem(ModBLocks.CATTLE_HERDER_BARREL.get(), properties));

    public static final DeferredItem<Item> CHARCOAL_BURNER_BARREL = ITEMS.registerItem("charcoal_burner_barrel_icon",
            properties -> new BlockItem(ModBLocks.CHARCOAL_BURNER_BARREL.get(), properties));

    public static final DeferredItem<Item> CHICKEN_BREEDER_BARREL = ITEMS.registerItem("chicken_breeder_barrel_icon",
            properties -> new BlockItem(ModBLocks.CHICKEN_BREEDER_BARREL.get(), properties));

    public static final DeferredItem<Item> FARMER_BARREL = ITEMS.registerItem("farmer_barrel_icon",
            properties -> new BlockItem(ModBLocks.FARMER_BARREL.get(), properties));

    public static final DeferredItem<Item> GOAT_HERDER_BARREL = ITEMS.registerItem("goat_herder_barrel_icon",
            properties -> new BlockItem(ModBLocks.GOAT_HERDER_BARREL.get(), properties));

    public static final DeferredItem<Item> GROCER_BARREL = ITEMS.registerItem("grocer_barrel_icon",
            properties -> new BlockItem(ModBLocks.GROCER_BARREL.get(), properties));

    public static final DeferredItem<Item> GUARD_BARREL = ITEMS.registerItem("guard_barrel_icon",
            properties -> new BlockItem(ModBLocks.GUARD_BARREL.get(), properties));

    public static final DeferredItem<Item> HERBALIST_BARREL = ITEMS.registerItem("herbalist_barrel_icon",
            properties -> new BlockItem(ModBLocks.HERBALIST_BARREL.get(), properties));

    public static final DeferredItem<Item> HORSE_BREEDER_BARREL = ITEMS.registerItem("horse_breeder_barrel_icon",
            properties -> new BlockItem(ModBLocks.HORSE_BREEDER_BARREL.get(), properties));

    public static final DeferredItem<Item> HUNTER_BARREL = ITEMS.registerItem("hunter_barrel_icon",
            properties -> new BlockItem(ModBLocks.HUNTER_BARREL.get(), properties));

    public static final DeferredItem<Item> INNKEEPER_BARREL = ITEMS.registerItem("innkeeper_barrel_icon",
            properties -> new BlockItem(ModBLocks.INNKEEPER_BARREL.get(), properties));

    public static final DeferredItem<Item> JEWELER_BARREL = ITEMS.registerItem("jeweler_barrel_icon",
            properties -> new BlockItem(ModBLocks.JEWELER_BARREL.get(), properties));

    public static final DeferredItem<Item> LUMBERJACK_BARREL = ITEMS.registerItem("lumberjack_barrel_icon",
            properties -> new BlockItem(ModBLocks.LUMBERJACK_BARREL.get(), properties));

    public static final DeferredItem<Item> MAESTER_BARREL = ITEMS.registerItem("maester_barrel_icon",
            properties -> new BlockItem(ModBLocks.MAESTER_BARREL.get(), properties));

    public static final DeferredItem<Item> MINER_BARREL = ITEMS.registerItem("miner_barrel_icon",
            properties -> new BlockItem(ModBLocks.MINER_BARREL.get(), properties));

    public static final DeferredItem<Item> PIG_BREEDER_BARREL = ITEMS.registerItem("pig_breeder_barrel_icon",
            properties -> new BlockItem(ModBLocks.PIG_BREEDER_BARREL.get(), properties));

    public static final DeferredItem<Item> PYROMANCER_BARREL = ITEMS.registerItem("pyromancer_barrel_icon",
            properties -> new BlockItem(ModBLocks.PYROMANCER_BARREL.get(), properties));

    public static final DeferredItem<Item> QUARRY_BARREL = ITEMS.registerItem("quarry_barrel_icon",
            properties -> new BlockItem(ModBLocks.QUARRY_BARREL.get(), properties));

    public static final DeferredItem<Item> SCRIBE_BARREL = ITEMS.registerItem("scribe_barrel_icon",
            properties -> new BlockItem(ModBLocks.SCRIBE_BARREL.get(), properties));

    public static final DeferredItem<Item> SEPTON_BARREL = ITEMS.registerItem("septon_barrel_icon",
            properties -> new BlockItem(ModBLocks.SEPTON_BARREL.get(), properties));

    public static final DeferredItem<Item> SHEEP_HERDER_BARREL = ITEMS.registerItem("sheep_herder_barrel_icon",
            properties -> new BlockItem(ModBLocks.SHEEP_HERDER_BARREL.get(), properties));

    public static final DeferredItem<Item> SHIPWRIGHT_BARREL = ITEMS.registerItem("shipwright_barrel_icon",
            properties -> new BlockItem(ModBLocks.SHIPWRIGHT_BARREL.get(), properties));

    public static final DeferredItem<Item> SLAVER_BARREL = ITEMS.registerItem("slaver_barrel_icon",
            properties -> new BlockItem(ModBLocks.SLAVER_BARREL.get(), properties));

    public static final DeferredItem<Item> SMELTER_BARREL = ITEMS.registerItem("smelter_barrel_icon",
            properties -> new BlockItem(ModBLocks.SMELTER_BARREL.get(), properties));

    public static final DeferredItem<Item> STONEMASON_BARREL = ITEMS.registerItem("stonemason_barrel_icon",
            properties -> new BlockItem(ModBLocks.STONEMASON_BARREL.get(), properties));

    public static final DeferredItem<Item> SWORDSMITH_BARREL = ITEMS.registerItem("swordsmith_barrel_icon",
            properties -> new BlockItem(ModBLocks.SWORDSMITH_BARREL.get(), properties));

    public static final DeferredItem<Item> TAILOR_BARREL = ITEMS.registerItem("tailor_barrel_icon",
            properties -> new BlockItem(ModBLocks.TAILOR_BARREL.get(), properties));

    public static final DeferredItem<Item> TANNER_BARREL = ITEMS.registerItem("tanner_barrel_icon",
            properties -> new BlockItem(ModBLocks.TANNER_BARREL.get(), properties));

    public static final DeferredItem<Item> TRADER_BARREL = ITEMS.registerItem("trader_barrel_icon",
            properties -> new BlockItem(ModBLocks.TRADER_BARREL.get(), properties));


    // Tells the AGoTMod class to call the modded items into the game
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
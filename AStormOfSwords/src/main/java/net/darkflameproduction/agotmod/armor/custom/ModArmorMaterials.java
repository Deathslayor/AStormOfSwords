// This code belongs to the package net.stormofsorts.agotmod.custom
package net.darkflameproduction.agotmod.armor.custom;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.HashMap;

// ModArmorMaterials enum implementing ArmorMaterial interface
public interface ModArmorMaterials {
    // Definition of the STARK1 armor material
    ArmorMaterial NORTHERN_LEVY = new ArmorMaterial(750, new HashMap<>() {
        {
            put(ArmorType.BOOTS, 4);
            put(ArmorType.LEGGINGS, 8);
            put(ArmorType.CHESTPLATE, 10);
            put(ArmorType.HELMET, 4);
        }
        // TODO: add repair ingredient
    }, 25, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.15F, TagKey.create(Registries.ITEM, AGoTMod.id("repair_stark_levy")), AGoTMod.id("stark_levy"));

    ArmorMaterial NORTHERN_PLATE = new ArmorMaterial(1000, new HashMap<>() {
        {
            put(ArmorType.BOOTS, 5);
            put(ArmorType.LEGGINGS, 9);
            put(ArmorType.CHESTPLATE, 11);
            put(ArmorType.HELMET, 5);
        }
        // TODO: add repair ingredient
    }, 30, SoundEvents.ARMOR_EQUIP_NETHERITE, 5.0F, 0.25F, TagKey.create(Registries.ITEM, AGoTMod.id("repair_stark_levy")), AGoTMod.id("stark_levy"));

    ArmorMaterial NORTHERN_NOBLE = new ArmorMaterial(1400, new HashMap<>() {
        {
            put(ArmorType.BOOTS, 6);
            put(ArmorType.LEGGINGS, 11);
            put(ArmorType.CHESTPLATE, 13);
            put(ArmorType.HELMET, 6);
        }
        // TODO: add repair ingredient
    }, 40, SoundEvents.ARMOR_EQUIP_NETHERITE, 6.0F, 0.40F, TagKey.create(Registries.ITEM, AGoTMod.id("repair_stark_levy")), AGoTMod.id("stark_levy"));

    ArmorMaterial FUR = new ArmorMaterial(650, new HashMap<>() {
        {
            put(ArmorType.BOOTS, 4);
            put(ArmorType.LEGGINGS, 7);
            put(ArmorType.CHESTPLATE, 9);
            put(ArmorType.HELMET, 4);
        }
        // TODO: add repair ingredient
    }, 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.10F, TagKey.create(Registries.ITEM, AGoTMod.id("repair_stark_levy")), AGoTMod.id("stark_levy"));

    ArmorMaterial LEATHER = new ArmorMaterial(1000, new HashMap<>() {
        {
            put(ArmorType.BOOTS, 5);
            put(ArmorType.LEGGINGS, 8);
            put(ArmorType.CHESTPLATE, 10);
            put(ArmorType.HELMET, 5);
        }
        // TODO: add repair ingredient
    }, 25, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.15F, TagKey.create(Registries.ITEM, AGoTMod.id("repair_stark_levy")), AGoTMod.id("stark_levy"));

    ArmorMaterial CHIEF = new ArmorMaterial(1000, new HashMap<>() {
        {
            put(ArmorType.BOOTS, 6);
            put(ArmorType.LEGGINGS, 9);
            put(ArmorType.CHESTPLATE, 11);
            put(ArmorType.HELMET, 6);
        }
        // TODO: add repair ingredient
    }, 30, SoundEvents.ARMOR_EQUIP_NETHERITE, 5.0F, 0.20F, TagKey.create(Registries.ITEM, AGoTMod.id("repair_stark_levy")), AGoTMod.id("stark_levy"));
}

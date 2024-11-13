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
    ArmorMaterial STARK_LEVY = new ArmorMaterial(500, new HashMap<>() {
        {
            put(ArmorType.BOOTS, 13);
            put(ArmorType.LEGGINGS, 15);
            put(ArmorType.CHESTPLATE, 16);
            put(ArmorType.HELMET, 11);
        }
        // TODO: add repair ingredient
    }, 500, SoundEvents.ARMOR_EQUIP_NETHERITE, 300.0F, 100F, TagKey.create(Registries.ITEM, AGoTMod.id("repair_stark_levy")), AGoTMod.id("stark_levy"));
}

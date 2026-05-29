package net.darkflameproduction.agotmod.armor.custom;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;

public interface ModArmorMaterials {
    Holder<ArmorMaterial> NORTHERN_LEVY = Holder.direct(createMaterial(
            Map.of(
                    ArmorItem.Type.BOOTS, 4,
                    ArmorItem.Type.LEGGINGS, 8,
                    ArmorItem.Type.CHESTPLATE, 10,
                    ArmorItem.Type.HELMET, 4
            ),
            25,
            4.0F,
            0.15F,
            "stark_levy"
    ));

    Holder<ArmorMaterial> NORTHERN_PLATE = Holder.direct(createMaterial(
            Map.of(
                    ArmorItem.Type.BOOTS, 5,
                    ArmorItem.Type.LEGGINGS, 9,
                    ArmorItem.Type.CHESTPLATE, 11,
                    ArmorItem.Type.HELMET, 5
            ),
            30,
            5.0F,
            0.25F,
            "stark_levy"
    ));

    Holder<ArmorMaterial> NORTHERN_NOBLE = Holder.direct(createMaterial(
            Map.of(
                    ArmorItem.Type.BOOTS, 6,
                    ArmorItem.Type.LEGGINGS, 11,
                    ArmorItem.Type.CHESTPLATE, 13,
                    ArmorItem.Type.HELMET, 6
            ),
            40,
            6.0F,
            0.40F,
            "stark_levy"
    ));

    Holder<ArmorMaterial> FUR = Holder.direct(createMaterial(
            Map.of(
                    ArmorItem.Type.BOOTS, 4,
                    ArmorItem.Type.LEGGINGS, 7,
                    ArmorItem.Type.CHESTPLATE, 9,
                    ArmorItem.Type.HELMET, 4
            ),
            20,
            3.0F,
            0.10F,
            "stark_levy"
    ));

    Holder<ArmorMaterial> LEATHER = Holder.direct(createMaterial(
            Map.of(
                    ArmorItem.Type.BOOTS, 5,
                    ArmorItem.Type.LEGGINGS, 8,
                    ArmorItem.Type.CHESTPLATE, 10,
                    ArmorItem.Type.HELMET, 5
            ),
            25,
            4.0F,
            0.15F,
            "stark_levy"
    ));

    Holder<ArmorMaterial> CHIEF = Holder.direct(createMaterial(
            Map.of(
                    ArmorItem.Type.BOOTS, 6,
                    ArmorItem.Type.LEGGINGS, 9,
                    ArmorItem.Type.CHESTPLATE, 11,
                    ArmorItem.Type.HELMET, 6
            ),
            30,
            5.0F,
            0.20F,
            "stark_levy"
    ));

    private static ArmorMaterial createMaterial(Map<ArmorItem.Type, Integer> defense, int enchantmentValue, float toughness, float knockbackResistance, String modelId) {
        return new ArmorMaterial(
                defense,
                enchantmentValue,
                SoundEvents.ARMOR_EQUIP_NETHERITE,
                () -> Ingredient.EMPTY,
                List.of(new ArmorMaterial.Layer(AGoTMod.id(modelId))),
                toughness,
                knockbackResistance
        );
    }
}


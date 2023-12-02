// This code belongs to the package net.stormofsorts.agotmod.custom
package net.stormofsorts.agotmod.custom;

// Importing necessary classes from other packages
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

// ModArmorMaterials enum implementing ArmorMaterial interface
public enum ModArmorMaterials implements ArmorMaterial {
    // Definition of the STARK1 armor material
    STARK1("stark1", 500, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 300);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 300);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 300);
        p_266655_.put(ArmorItem.Type.HELMET, 300);
    }), 500, SoundEvents.ARMOR_EQUIP_NETHERITE, 300.0F, 100F, () -> {
        return Ingredient.of(Items.NETHER_STAR);
    });

    // Codec to serialize/deserialize ArmorMaterials
    public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);

    // Map containing health values for different armor types
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });

    // Fields defining the armor material properties
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    // Constructor for ModArmorMaterials
    private ModArmorMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType,
                              int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionFunctionForType = pProtectionFunctionForType;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    // Method to get the durability for a specific armor type
    public int getDurabilityForType(ArmorItem.Type pType) {
        return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
    }

    // Method to get the defense value for a specific armor type
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunctionForType.get(pType);
    }

    // Method to get the enchantment value for the armor material
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    // Method to get the sound event for equipping the armor material
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    // Method to get the repair ingredient for the armor material
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    // Method to get the name of the armor material
    public String getName() {
        return this.name;
    }

    // Method to get the toughness of the armor material
    public float getToughness() {
        return this.toughness;
    }

    // Method to get the knockback resistance of the armor material
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    // Method to get the serialized name of the armor material
    public String getSerializedName() {
        return this.name;
    }
}

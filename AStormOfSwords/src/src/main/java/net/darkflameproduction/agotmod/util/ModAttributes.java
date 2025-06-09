package net.darkflameproduction.agotmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.UUID;

public class ModAttributes {
    // Unique UUIDs for attribute modifiers
    public static final UUID ATTACK_REACH_MODIFIER_UUID = UUID.fromString("f0b68cb5-6a60-4d5a-ba6c-4817ec7e88f9");

    // This is the vanilla attack reach attribute
    public static final Attribute ATTACK_REACH = Attributes.BLOCK_INTERACTION_RANGE.value();

    // Attack reach values for different weapon types
    public static final double SHORT_BLADE_REACH = -0.5; // Daggers have shorter reach
    public static final double ONE_HANDED_REACH = 0.0;   // Base reach (no modifier)
    public static final double TWO_HANDED_REACH = 1;   // Longswords have slightly more reach
    public static final double POLEARM_REACH = 1.5;      // Spears and pikes have significantly more reach
    public static final double LONG_POLEARM_REACH = 30;      // Spears and pikes have significantly more reach

}
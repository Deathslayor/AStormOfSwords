package net.darkflameproduction.agotmod.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.util.ModAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class LevelRequiredSwordItem extends Item {
    private final String weaponType;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    // Constructor for ToolMaterial
    public LevelRequiredSwordItem(Item.Properties properties, String weaponType) {
        super(properties);
        //super(material, attackDamage, attackSpeed, properties);
        this.weaponType = weaponType;

        // Create a builder based on the parent's default modifiers
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        // Add the parent's attribute modifiers for an empty ItemStack

        // Add our custom reach modifier if needed
        double reachModifier = switch (weaponType) {
            case WeaponRequirements.SHORT_BLADE -> ModAttributes.SHORT_BLADE_REACH;
            case WeaponRequirements.ONE_HANDED -> ModAttributes.ONE_HANDED_REACH;
            case WeaponRequirements.TWO_HANDED -> ModAttributes.TWO_HANDED_REACH;
            case WeaponRequirements.POLEARM -> ModAttributes.POLEARM_REACH;
            case WeaponRequirements.LONG_POLEARM -> ModAttributes.LONG_POLEARM_REACH;
            default -> 0.0;
        };

        // Only add modifier if it's not zero
        if (reachModifier != 0.0) {
            // Add the reach attribute modifier
            builder.put(
                    ModAttributes.ATTACK_REACH,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "weapon_reach_modifier"),
                            reachModifier,
                            AttributeModifier.Operation.ADD_VALUE
                    )
            );
        }

        // Store the combined modifiers
        this.defaultModifiers = builder.build();
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        // Add weapon requirements tooltip
        WeaponRequirements.appendTooltip(stack, context, tooltipAdder, flag);
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player && !WeaponRequirements.meetsRequirement(player, stack)) {
            // Notify player when using a weapon beyond their skill level
            player.displayClientMessage(
                    Component.translatable("message.agotmod.weapon_too_advanced").withStyle(ChatFormatting.RED),
                    true
            );
        }

        // Call the parent method
        super.hurtEnemy(stack, target, attacker);
    }

    /**
     * Gets a readable skill name from a category
     */
    private String getSkillNameFromCategory(String category) {
        return switch (category) {
            case WeaponRequirements.ONE_HANDED -> "One-Handed";
            case WeaponRequirements.TWO_HANDED -> "Two-Handed";
            case WeaponRequirements.POLEARM -> "Polearm";
            case WeaponRequirements.SHORT_BLADE -> "Short Blade";
            case WeaponRequirements.RANGED -> "Ranged";
            default -> "Unknown";
        };
    }
}
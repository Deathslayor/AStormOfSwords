package net.darkflameproduction.agotmod.item;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.util.ModAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;

import java.util.List;

public class LevelRequiredSwordItem extends SwordItem {
    private final String weaponType;
    public LevelRequiredSwordItem(net.minecraft.world.item.Tier material, int attackDamage, float attackSpeed, Item.Properties properties, String weaponType) {
        super(material, properties.attributes(buildAttributes(material, attackDamage, attackSpeed, weaponType)));
        this.weaponType = weaponType;
    }

    private static ItemAttributeModifiers buildAttributes(net.minecraft.world.item.Tier material, int attackDamage, float attackSpeed, String weaponType) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(SwordItem.BASE_ATTACK_DAMAGE_ID, attackDamage + material.getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(SwordItem.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                );

        double reachModifier = 0.0;

        switch (weaponType) {
            case WeaponRequirements.SHORT_BLADE:
                reachModifier = ModAttributes.SHORT_BLADE_REACH;
                break;
            case WeaponRequirements.ONE_HANDED:
                reachModifier = ModAttributes.ONE_HANDED_REACH;
                break;
            case WeaponRequirements.TWO_HANDED:
                reachModifier = ModAttributes.TWO_HANDED_REACH;
                break;
            case WeaponRequirements.POLEARM:
                reachModifier = ModAttributes.POLEARM_REACH;
                break;
            case WeaponRequirements.LONG_POLEARM:
                reachModifier = ModAttributes.LONG_POLEARM_REACH;
                break;
        }

        if (reachModifier != 0.0) {
            builder.add(
                    Attributes.BLOCK_INTERACTION_RANGE,
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "weapon_reach_modifier"),
                            reachModifier,
                            AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
            );
        }

        return builder.build();
    }

    // The correct signature for appendHoverText in 1.21.3
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        // Add weapon requirements tooltip
        WeaponRequirements.appendTooltip(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player && !WeaponRequirements.meetsRequirement(player, stack)) {
            // Notify player when using a weapon beyond their skill level
            player.displayClientMessage(
                    Component.translatable("message.agotmod.weapon_too_advanced").withStyle(ChatFormatting.RED),
                    true
            );
        }

        // Call the parent method
        super.hurtEnemy(stack, target, attacker);
        return true;
    }

    /**
     * Gets a readable skill name from a category
     */
    private String getSkillNameFromCategory(String category) {
        switch (category) {
            case WeaponRequirements.ONE_HANDED:
                return "One-Handed";
            case WeaponRequirements.TWO_HANDED:
                return "Two-Handed";
            case WeaponRequirements.POLEARM:
                return "Polearm";
            case WeaponRequirements.SHORT_BLADE:
                return "Short Blade";
            case WeaponRequirements.RANGED:
                return "Ranged";
            default:
                return "Unknown";
        }
    }
}


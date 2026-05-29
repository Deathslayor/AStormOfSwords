package net.darkflameproduction.agotmod.item.custom;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class EffectFoodItem extends Item {
    public record ChanceEffect(MobEffectInstance effect, float chance) {
    }

    private final List<ChanceEffect> effects;
    private final boolean drink;

    public EffectFoodItem(Properties properties, boolean drink, List<ChanceEffect> effects) {
        super(properties);
        this.drink = drink;
        this.effects = effects;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        if (!level.isClientSide) {
            for (ChanceEffect chanceEffect : this.effects) {
                if (level.random.nextFloat() <= chanceEffect.chance()) {
                    livingEntity.addEffect(new MobEffectInstance(chanceEffect.effect()));
                }
            }
        }

        if (livingEntity instanceof Player player) {
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return result;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return this.drink ? UseAnim.DRINK : super.getUseAnimation(stack);
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return this.drink ? SoundEvents.GENERIC_DRINK : super.getEatingSound();
    }
}

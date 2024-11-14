package net.darkflameproduction.agotmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModConsumables {
    public static final Consumable RAW_BEAR_MEAT = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_WHITE_SAUSAGE = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_BLOOD_SAUSAGE = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_SAUSAGE = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_BACON = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_BOAR_VENISON = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_CHICKEN_NUGGETS = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_DEER_VENISON = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_GOAT_MEAT = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_HARE_MEAT = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_HORSE_MEAT = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();

    public static final Consumable RAW_MAMMOTH_MEAT = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.POISON, 60)), 0.9F))
            .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 60)), 0.2F))
            .build();
}

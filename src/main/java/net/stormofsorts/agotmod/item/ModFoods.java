package net.stormofsorts.agotmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties RAW_BEAR_MEAT = new FoodProperties.Builder().meat().nutrition(3)
            .saturationMod(0.3F)
            .effect(() ->new MobEffectInstance(MobEffects.POISON, 60),0.90F)
            .effect(() ->new MobEffectInstance(MobEffects.HUNGER, 60),0.20F).build();
    public static final FoodProperties COOKED_BEAR_MEAT = new FoodProperties.Builder().meat().nutrition(8)
            .saturationMod(0.8F).build();

    public static final FoodProperties RAW_WHITE_SAUSAGE = new FoodProperties.Builder().meat().nutrition(1)
            .saturationMod(0.1F)
            .effect(() ->new MobEffectInstance(MobEffects.POISON, 60),0.20F)
            .effect(() ->new MobEffectInstance(MobEffects.HUNGER, 60),0.20F).build();
    public static final FoodProperties RAW_BLOOD_SAUSAGE = new FoodProperties.Builder().meat().nutrition(1)
            .saturationMod(0.1F)
            .effect(() ->new MobEffectInstance(MobEffects.POISON, 60),0.20F)
            .effect(() ->new MobEffectInstance(MobEffects.HUNGER, 60),0.20F).build();
    public static final FoodProperties RAW_SAUSAGE = new FoodProperties.Builder().meat().nutrition(1)
            .saturationMod(0.1F)
            .effect(() ->new MobEffectInstance(MobEffects.POISON, 60),0.20F)
            .effect(() ->new MobEffectInstance(MobEffects.HUNGER, 60),0.20F).build();
    public static final FoodProperties COOKED_WHITE_SAUSAGE = new FoodProperties.Builder().meat().nutrition(5)
            .saturationMod(0.8F).build();
    public static final FoodProperties COOKED_BLOOD_SAUSAGE = new FoodProperties.Builder().meat().nutrition(5)
            .saturationMod(0.8F).build();
    public static final FoodProperties COOKED_SAUSAGE = new FoodProperties.Builder().meat().nutrition(5)
            .saturationMod(0.8F).build();

}

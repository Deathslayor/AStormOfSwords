package net.darkflameproduction.agotmod.item;

import net.darkflameproduction.agotmod.item.custom.EffectFoodItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Function;

public final class ModConsumables {
    private static final List<EffectFoodItem.ChanceEffect> RAW_MEAT_EFFECTS = List.of(
            new EffectFoodItem.ChanceEffect(new MobEffectInstance(MobEffects.POISON, 60), 0.9F),
            new EffectFoodItem.ChanceEffect(new MobEffectInstance(MobEffects.HUNGER, 60), 0.2F)
    );
    private static final List<EffectFoodItem.ChanceEffect> RAW_FISH_EFFECTS = List.of(
            new EffectFoodItem.ChanceEffect(new MobEffectInstance(MobEffects.POISON, 60), 0.5F),
            new EffectFoodItem.ChanceEffect(new MobEffectInstance(MobEffects.HUNGER, 60), 0.1F)
    );
    private static final List<EffectFoodItem.ChanceEffect> ALCOHOL_EFFECTS = List.of(
            new EffectFoodItem.ChanceEffect(new MobEffectInstance(MobEffects.CONFUSION, 200), 0.8F)
    );
    private static final List<EffectFoodItem.ChanceEffect> SHADE_EFFECTS = List.of(
            new EffectFoodItem.ChanceEffect(new MobEffectInstance(MobEffects.CONFUSION, 600), 1.0F),
            new EffectFoodItem.ChanceEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200), 0.5F)
    );

    private ModConsumables() {
    }

    public static Function<Item.Properties, Item> rawMeat() {
        return properties -> new EffectFoodItem(properties, false, RAW_MEAT_EFFECTS);
    }

    public static Function<Item.Properties, Item> rawFish() {
        return properties -> new EffectFoodItem(properties, false, RAW_FISH_EFFECTS);
    }

    public static Function<Item.Properties, Item> alcohol() {
        return properties -> new EffectFoodItem(properties, true, ALCOHOL_EFFECTS);
    }

    public static Function<Item.Properties, Item> shadeOfTheEvening() {
        return properties -> new EffectFoodItem(properties, true, SHADE_EFFECTS);
    }

    public static Function<Item.Properties, Item> drink() {
        return properties -> new EffectFoodItem(properties, true, List.of());
    }
}

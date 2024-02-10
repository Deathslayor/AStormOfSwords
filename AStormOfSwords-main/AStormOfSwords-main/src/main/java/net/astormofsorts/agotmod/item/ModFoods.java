// This code belongs to the package net.stormofsorts.agotmod.item
package net.astormofsorts.agotmod.item;

// Importing necessary classes from other packages
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

// A utility class for defining custom food properties
public class ModFoods {

    // Food properties for raw bear meat
    public static final FoodProperties RAW_BEAR_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(4) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked bear meat
    public static final FoodProperties COOKED_BEAR_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(12) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw white sausage
    public static final FoodProperties RAW_WHITE_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(2) // Amount of hunger restored
            .saturationMod(0.1F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.20F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for raw blood sausage
    public static final FoodProperties RAW_BLOOD_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(2) // Amount of hunger restored
            .saturationMod(0.1F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.20F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for raw sausage
    public static final FoodProperties RAW_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(2) // Amount of hunger restored
            .saturationMod(0.1F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.20F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked white sausage
    public static final FoodProperties COOKED_WHITE_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(10) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for cooked blood sausage
    public static final FoodProperties COOKED_BLOOD_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(10) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for cooked sausage
    public static final FoodProperties COOKED_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(10) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw bacon
    public static final FoodProperties RAW_BACON = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(1) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked bacon
    public static final FoodProperties COOKED_BACON = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(6) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw boar venison
    public static final FoodProperties RAW_BOAR_VENISON = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(3) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked boar venison
    public static final FoodProperties COOKED_BOAR_VENISON = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(9) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw chicken nuggets
    public static final FoodProperties RAW_CHICKEN_NUGGETS = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(1) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked chicken nuggets
    public static final FoodProperties COOKED_CHICKEN_NUGGETS = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(4) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw deer venison
    public static final FoodProperties RAW_DEER_VENISON = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(2) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked deer venison
    public static final FoodProperties COOKED_DEER_VENISON = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(8) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw goat meat
    public static final FoodProperties RAW_GOAT_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(2) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked goat meat
    public static final FoodProperties COOKED_GOAT_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(7) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw hare meat
    public static final FoodProperties RAW_HARE_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(2) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked hare meat
    public static final FoodProperties COOKED_HARE_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(6) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw horse meat
    public static final FoodProperties RAW_HORSE_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(2) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked horse meat
    public static final FoodProperties COOKED_HORSE_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(8) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw mammoth meat
    public static final FoodProperties RAW_MAMMOTH_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(4) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked mammoth meat
    public static final FoodProperties COOKED_MAMMOTH_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(12) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();
}

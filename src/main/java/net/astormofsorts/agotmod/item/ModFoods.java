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
            .nutrition(3) // Amount of hunger restored
            .saturationMod(0.3F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.90F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked bear meat
    public static final FoodProperties COOKED_BEAR_MEAT = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(8) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for raw white sausage
    public static final FoodProperties RAW_WHITE_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(1) // Amount of hunger restored
            .saturationMod(0.1F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.20F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for raw blood sausage
    public static final FoodProperties RAW_BLOOD_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(1) // Amount of hunger restored
            .saturationMod(0.1F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.20F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for raw sausage
    public static final FoodProperties RAW_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(1) // Amount of hunger restored
            .saturationMod(0.1F) // Saturation modifier
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 0.20F) // Poison effect with a chance
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60), 0.20F) // Hunger effect with a chance
            .build();

    // Food properties for cooked white sausage
    public static final FoodProperties COOKED_WHITE_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(5) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for cooked blood sausage
    public static final FoodProperties COOKED_BLOOD_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(5) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();

    // Food properties for cooked sausage
    public static final FoodProperties COOKED_SAUSAGE = new FoodProperties.Builder()
            .meat() // Marking the food as meat
            .nutrition(5) // Amount of hunger restored
            .saturationMod(0.8F) // Saturation modifier
            .build();
}

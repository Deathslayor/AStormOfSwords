// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// A utility class for creating custom creative mode tabs
public class ModCreativeFoods {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_FOODS = CREATIVE_MODE_TAB.register("agot_tab_foods",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(net.darkflameproduction.agotmod.item.food.ModFoodItems.get("raw_bacon").get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_foods")) // Title for the creative mode tab
                    .withTabsBefore(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "agot_tab_animals"))
                    .displayItems((pParameters, pOutput) -> {
                        // Every food, in catalog order: meat, prepared food, baking, dairy, fruit, vegetables (FoodCatalog)
                        for (net.darkflameproduction.agotmod.item.food.FoodCatalog.Category category
                                : net.darkflameproduction.agotmod.item.food.FoodCatalog.Category.values()) {
                            for (net.darkflameproduction.agotmod.item.food.FoodCatalog.Food food
                                    : net.darkflameproduction.agotmod.item.food.FoodCatalog.of(category)) {
                                pOutput.accept(net.darkflameproduction.agotmod.item.food.ModFoodItems.get(food.id()).get());
                            }
                        }
                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}


package net.darkflameproduction.agotmod.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Shared utility class for item pricing that can be used by both server and client
 * This replaces the pricing logic that was in the client-only GrocerInventoryScreen
 */
public class ItemPricing {

    // Item pricing map - only items in this map can be stored by the grocer
    private static final Map<String, Integer> ALLOWED_ITEMS_PRICING = new HashMap<>();

    static {
        // Crops and Seeds
        ALLOWED_ITEMS_PRICING.put("minecraft:wheat", 2);
        ALLOWED_ITEMS_PRICING.put("minecraft:wheat_seeds", 8);
        ALLOWED_ITEMS_PRICING.put("minecraft:pumpkin", 23);
        ALLOWED_ITEMS_PRICING.put("minecraft:pumpkin_seeds", 6);
        ALLOWED_ITEMS_PRICING.put("minecraft:melon_slice", 23);
        ALLOWED_ITEMS_PRICING.put("minecraft:melon_seeds", 6);
        ALLOWED_ITEMS_PRICING.put("minecraft:beetroot", 8);
        ALLOWED_ITEMS_PRICING.put("minecraft:beetroot_seeds", 31);

        // Custom mod items
        ALLOWED_ITEMS_PRICING.put("agotmod:horseradish", 7);
        ALLOWED_ITEMS_PRICING.put("agotmod:horseradish_seeds", 28);
        ALLOWED_ITEMS_PRICING.put("agotmod:onion", 4);
        ALLOWED_ITEMS_PRICING.put("agotmod:onion_seeds", 16);
        ALLOWED_ITEMS_PRICING.put("agotmod:red_onion", 5);
        ALLOWED_ITEMS_PRICING.put("agotmod:red_onion_seeds", 19);
        ALLOWED_ITEMS_PRICING.put("agotmod:wild_onion", 7);
        ALLOWED_ITEMS_PRICING.put("agotmod:wild_onion_seeds", 25);
        ALLOWED_ITEMS_PRICING.put("agotmod:leek", 6);
        ALLOWED_ITEMS_PRICING.put("agotmod:leek_seeds", 23);
        ALLOWED_ITEMS_PRICING.put("agotmod:neep", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:neep_seeds", 31);
        ALLOWED_ITEMS_PRICING.put("agotmod:turnip", 6);
        ALLOWED_ITEMS_PRICING.put("agotmod:turnip_seeds", 23);
        ALLOWED_ITEMS_PRICING.put("agotmod:parsley", 4);
        ALLOWED_ITEMS_PRICING.put("agotmod:parsley_seeds", 16);
        ALLOWED_ITEMS_PRICING.put("agotmod:bean", 4);
        ALLOWED_ITEMS_PRICING.put("agotmod:bean_seeds", 13);
        ALLOWED_ITEMS_PRICING.put("agotmod:green_bean", 4);
        ALLOWED_ITEMS_PRICING.put("agotmod:green_bean_seeds", 16);
        ALLOWED_ITEMS_PRICING.put("agotmod:chickpea", 6);
        ALLOWED_ITEMS_PRICING.put("agotmod:chickpea_seeds", 22);
        ALLOWED_ITEMS_PRICING.put("agotmod:cabbage", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:cabbage_seeds", 31);
        ALLOWED_ITEMS_PRICING.put("agotmod:spinach", 5);
        ALLOWED_ITEMS_PRICING.put("agotmod:spinach_seeds", 19);
        ALLOWED_ITEMS_PRICING.put("agotmod:cucumber", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:cucumber_seeds", 31);
        ALLOWED_ITEMS_PRICING.put("agotmod:dragon_pepper", 10);
        ALLOWED_ITEMS_PRICING.put("agotmod:dragon_pepper_seeds", 37);
        ALLOWED_ITEMS_PRICING.put("agotmod:pepper", 9);
        ALLOWED_ITEMS_PRICING.put("agotmod:pepper_seeds", 34);
        ALLOWED_ITEMS_PRICING.put("agotmod:peppercorn", 12);
        ALLOWED_ITEMS_PRICING.put("agotmod:peppercorn_seeds", 46);
        ALLOWED_ITEMS_PRICING.put("agotmod:barley", 2);
        ALLOWED_ITEMS_PRICING.put("agotmod:barley_seeds", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:oat", 3);
        ALLOWED_ITEMS_PRICING.put("agotmod:oat_seeds", 10);
        ALLOWED_ITEMS_PRICING.put("agotmod:opium_poppy_seeds", 46);
        ALLOWED_ITEMS_PRICING.put("agotmod:cotton", 10);
        ALLOWED_ITEMS_PRICING.put("agotmod:cotton_seeds", 37);
        ALLOWED_ITEMS_PRICING.put("agotmod:hemp", 12);
        ALLOWED_ITEMS_PRICING.put("agotmod:hemp_seeds", 46);

        // Berries
        ALLOWED_ITEMS_PRICING.put("agotmod:strawberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:strawberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:blackberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:blackberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:blueberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:blueberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:mulberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:mulberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:raspberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:raspberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:smokeberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:smokeberry_seeds", 68);

        // Other
        ALLOWED_ITEMS_PRICING.put("agotmod:garlic", 5);
    }

    /**
     * Check if an item is allowed to be stored by the grocer
     */
    public static boolean isItemAllowed(String itemKey) {
        return ALLOWED_ITEMS_PRICING.containsKey(itemKey);
    }

    /**
     * Get the buy price for an item
     */
    public static int getItemPrice(String itemKey) {
        return ALLOWED_ITEMS_PRICING.getOrDefault(itemKey, 0);
    }

    /**
     * Get the sell price for an item with special rules
     */
    public static int getItemSellPrice(String itemKey) {
        int buyPrice = getItemPrice(itemKey);

        // Special rule: Seeds have same sell value as their non-seed counterparts
        // Exception: Pumpkin and melon seeds/items are half of normal value
        if (itemKey.contains("_seeds")) {
            String baseItem = itemKey.replace("_seeds", "");

            // For pumpkin and melon seeds, use half of the seed buy price
            if (baseItem.contains("pumpkin") || baseItem.contains("melon")) {
                return buyPrice / 2;
            }

            // For other seeds, use half of the non-seed item's price
            int baseItemPrice = getItemPrice(baseItem);
            if (baseItemPrice > 0) {
                return baseItemPrice / 2;
            }
        }

        // For pumpkin and melon (non-seeds), also half price
        if (itemKey.contains("pumpkin") || itemKey.contains("melon")) {
            return buyPrice / 2;
        }

        // Default: half of buy price rounded down
        return buyPrice / 2;
    }

    /**
     * Get all allowed items with their prices (for debugging/admin purposes)
     */
    public static Map<String, Integer> getAllPricing() {
        return new HashMap<>(ALLOWED_ITEMS_PRICING);
    }
}
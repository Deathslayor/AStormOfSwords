package net.darkflameproduction.agotmod.item;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.util.ModAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.TooltipFlag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WeaponRequirements {
    public static final String ONE_HANDED = "one_handed";
    public static final String TWO_HANDED = "two_handed";
    public static final String POLEARM = "polearm";
    public static final String LONG_POLEARM = "long_polearm";
    public static final String SHORT_BLADE = "short_blade";
    public static final String RANGED = "ranged";

    private static final Map<String, Map<String, Integer>> weaponRequirements = new HashMap<>();

    public static void addReachAttributes(ItemStack stack, Map<AttributeModifier.Operation, Map<EquipmentSlot, Map<UUID, AttributeModifier>>> modifiers) {
        String category = getWeaponCategory(stack);
        String itemId = stack.getItem().getDescriptionId().replace("item.agotmod.", "");
        double reachModifier = 0.0;

        if (itemId.contains("pike")) {
            reachModifier = ModAttributes.LONG_POLEARM_REACH;
        } else {
            switch (category) {
                case SHORT_BLADE:
                    reachModifier = ModAttributes.SHORT_BLADE_REACH;
                    break;
                case ONE_HANDED:
                    reachModifier = ModAttributes.ONE_HANDED_REACH;
                    break;
                case TWO_HANDED:
                    reachModifier = ModAttributes.TWO_HANDED_REACH;
                    break;
                case POLEARM:
                    reachModifier = ModAttributes.POLEARM_REACH;
                    break;
                case LONG_POLEARM:
                    reachModifier = ModAttributes.LONG_POLEARM_REACH;
                    break;
                default:
                    return;
            }
        }

        if (reachModifier != 0.0) {
            AttributeModifier reachAttributeModifier = new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "weapon_reach_modifier"),
                    reachModifier,
                    AttributeModifier.Operation.ADD_VALUE
            );

            // Initialize maps if they don't exist
            if (!modifiers.containsKey(AttributeModifier.Operation.ADD_VALUE)) {
                modifiers.put(AttributeModifier.Operation.ADD_VALUE, new HashMap<>());
            }

            Map<EquipmentSlot, Map<UUID, AttributeModifier>> slotMap = modifiers.get(AttributeModifier.Operation.ADD_VALUE);
            if (!slotMap.containsKey(EquipmentSlot.MAINHAND)) {
                slotMap.put(EquipmentSlot.MAINHAND, new HashMap<>());
            }

            slotMap.get(EquipmentSlot.MAINHAND).put(ModAttributes.ATTACK_REACH_MODIFIER_UUID, reachAttributeModifier);
        }
    }

    /**
     * Gets the reach modifier for a weapon based on its category
     */
    public static double getReachModifier(ItemStack stack) {
        String category = getWeaponCategory(stack);
        String itemId = stack.getItem().getDescriptionId().replace("item.agotmod.", "");

        // Check if the item is a pike (should have LONG_POLEARM reach)
        if (itemId.contains("pike")) {
            return ModAttributes.LONG_POLEARM_REACH;
        }

        switch (category) {
            case SHORT_BLADE:
                return ModAttributes.SHORT_BLADE_REACH;
            case ONE_HANDED:
                return ModAttributes.ONE_HANDED_REACH;
            case TWO_HANDED:
                return ModAttributes.TWO_HANDED_REACH;
            case POLEARM:
                return ModAttributes.POLEARM_REACH;
            case LONG_POLEARM:
                return ModAttributes.LONG_POLEARM_REACH;
            default:
                return 0.0;
        }
    }

    public static void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag flag) {
        String category = getWeaponCategory(stack);

        if (category.isEmpty()) {
            return; // Not a categorized weapon
        }

        int requiredLevel = getRequiredLevel(stack);

        if (requiredLevel > 0) {
            String skillName = getSkillNameFromCategory(category);
            tooltipComponents.add(Component.translatable("tooltip.agotmod.level_requirement", skillName, requiredLevel)
                    .withStyle(ChatFormatting.RED));
            tooltipComponents.add(Component.translatable("tooltip.agotmod.level_penalty")
                    .withStyle(ChatFormatting.DARK_RED));
        }

        // Add reach tooltip
        double reachModifier = getReachModifier(stack);
        if (reachModifier != 0.0) {
            String key = reachModifier > 0 ? "tooltip.agotmod.reach_bonus" : "tooltip.agotmod.reach_penalty";
            ChatFormatting color = reachModifier > 0 ? ChatFormatting.BLUE : ChatFormatting.RED;

            // Format the reach value with a + sign for positive values
            String formattedReach = reachModifier > 0 ? "+" + reachModifier : String.valueOf(reachModifier);

            tooltipComponents.add(Component.translatable(key, formattedReach)
                    .withStyle(color));
        }
    }

    static {

        Map<String, Integer> shortbladeReqs = new HashMap<>();
        shortbladeReqs.put("BRONZE", 0);
        shortbladeReqs.put("IRON", 25);
        shortbladeReqs.put("STEEL", 50);
        shortbladeReqs.put("NOBLE", 75);
        shortbladeReqs.put("DRAGONGLASS", 10);
        weaponRequirements.put(SHORT_BLADE, shortbladeReqs);

        Map<String, Integer> oneHandedReqs = new HashMap<>();
        oneHandedReqs.put("BRONZE", 0);
        oneHandedReqs.put("IRON", 25);
        oneHandedReqs.put("STEEL", 50);
        oneHandedReqs.put("NOBLE", 75);
        weaponRequirements.put(ONE_HANDED, oneHandedReqs);

        Map<String, Integer> twoHandedReqs = new HashMap<>();
        twoHandedReqs.put("BRONZE", 0);
        twoHandedReqs.put("IRON", 25);
        twoHandedReqs.put("STEEL", 50);
        twoHandedReqs.put("NOBLE", 75);
        weaponRequirements.put(TWO_HANDED, twoHandedReqs);

        Map<String, Integer> polearmReqs = new HashMap<>();
        polearmReqs.put("BRONZE", 0);
        polearmReqs.put("IRON", 25);
        polearmReqs.put("STEEL", 50);
        polearmReqs.put("NOBLE", 75);
        polearmReqs.put("DRAGONGLASS", 10);
        weaponRequirements.put(POLEARM, polearmReqs);

    }

    private static final Map<String, String> weaponCategories = new HashMap<>();

    static {
        mapOneHandedWeapons();

        mapTwoHandedWeapons();

        mapPolearmWeapons();

        mapShortBladeWeapons();

        mapRangedWeapons();
    }

    private static void mapOneHandedWeapons() {
        weaponCategories.put("bronze_sword", ONE_HANDED);
        weaponCategories.put("bronze_spatha", ONE_HANDED);
        weaponCategories.put("bronze_battleaxe", ONE_HANDED);

        weaponCategories.put("iron_sword", ONE_HANDED);
        weaponCategories.put("iron_mace", ONE_HANDED);
        weaponCategories.put("iron_battleaxe", ONE_HANDED);

        weaponCategories.put("steel_sword", ONE_HANDED);
        weaponCategories.put("steel_mace", ONE_HANDED);
        weaponCategories.put("steel_battleaxe", ONE_HANDED);

        weaponCategories.put("noble_mace", ONE_HANDED);
        weaponCategories.put("noble_battleaxe", ONE_HANDED);

    }

    private static void mapTwoHandedWeapons() {
        weaponCategories.put("iron_longsword", TWO_HANDED);

        weaponCategories.put("steel_longsword", TWO_HANDED);

        weaponCategories.put("noble_longsword", TWO_HANDED);
    }

    private static void mapPolearmWeapons() {
        weaponCategories.put("bronze_spear", POLEARM);
        weaponCategories.put("bronze_pike", POLEARM); // Changed to POLEARM (was in mapLongPolearmWeapons)

        weaponCategories.put("dragonglass_spear", POLEARM);

        weaponCategories.put("iron_spear", POLEARM);
        weaponCategories.put("iron_pike", POLEARM); // Changed to POLEARM (was in mapLongPolearmWeapons)

        weaponCategories.put("steel_spear", POLEARM);
        weaponCategories.put("steel_halberd", POLEARM);
        weaponCategories.put("steel_pike", POLEARM); // Changed to POLEARM (was in mapLongPolearmWeapons)

        weaponCategories.put("noble_spear", POLEARM);
        weaponCategories.put("noble_halberd", POLEARM);
        weaponCategories.put("noble_pike", POLEARM); // Changed to POLEARM (was in mapLongPolearmWeapons)
    }


    private static void mapShortBladeWeapons() {
        weaponCategories.put("bronze_dagger", SHORT_BLADE);

        weaponCategories.put("dragonglass_dagger", SHORT_BLADE);

        weaponCategories.put("iron_dagger", SHORT_BLADE);

        weaponCategories.put("steel_dagger", SHORT_BLADE);

        weaponCategories.put("noble_dagger", SHORT_BLADE);
    }

    private static void mapRangedWeapons() {
        weaponCategories.put("steel_bow", RANGED);

        weaponCategories.put("bow", RANGED);
        weaponCategories.put("crossbow", RANGED);
    }


    public static int getRequiredLevel(ItemStack stack) {
        String itemId = stack.getItem().getDescriptionId().replace("item.agotmod.", "");
        String category = weaponCategories.get(itemId);

        if (category == null) {
            return 0;
        }

        String tier = determineTier(itemId);

        Map<String, Integer> categoryReqs = weaponRequirements.get(category);
        if (categoryReqs == null || !categoryReqs.containsKey(tier)) {
            return 0;
        }

        return categoryReqs.get(tier);
    }


    private static String determineTier(String itemId) {
        if (itemId.contains("bronze_")) {
            return "BRONZE";
        } else if (itemId.contains("iron_")) {
            return "IRON";
        } else if (itemId.contains("steel_")) {
            return "STEEL";
        } else if (itemId.contains("noble_")) {
            return "NOBLE";
        } else if (itemId.contains("dragonglass_")) {
            return "DRAGONGLASS";
        }
        return "BRONZE";
    }


    public static String getWeaponCategory(ItemStack stack) {
        String itemId = stack.getItem().getDescriptionId().replace("item.agotmod.", "");
        return weaponCategories.getOrDefault(itemId, "");
    }


    public static boolean meetsRequirement(Player player, ItemStack stack) {
        String itemId = stack.getItem().getDescriptionId().replace("item.agotmod.", "");
        String category = weaponCategories.get(itemId);

        if (category == null) {
            return true;
        }

        int requiredLevel = getRequiredLevel(stack);

        int playerLevel = getPlayerSkillLevel(player, category);

        return playerLevel >= requiredLevel;
    }


    private static int getPlayerSkillLevel(Player player, String category) {
        CompoundTag persistentData = player.getPersistentData();
        CompoundTag skills = persistentData.getCompound(AGoTMod.MOD_ID + ".skills");

        switch (category) {
            case ONE_HANDED:
                return skills.getInt("one_handed_level");
            case TWO_HANDED:
                return skills.getInt("two_handed_level");
            case POLEARM:
                return skills.getInt("polearm_level");
            case SHORT_BLADE:
                return skills.getInt("short_blade_level");
            case RANGED:
                return skills.getInt("ranged_level");
            default:
                return 0;
        }
    }


    private static String getSkillNameFromCategory(String category) {
        switch (category) {
            case ONE_HANDED:
                return "One-Handed";
            case TWO_HANDED:
                return "Two-Handed";
            case POLEARM:
                return "Polearm";
            case SHORT_BLADE:
                return "Short Blade";
            case RANGED:
                return "Ranged";
            default:
                return "Unknown";
        }
    }


    public static float getAttackSpeedMultiplier(Player player, ItemStack stack) {
        if (meetsRequirement(player, stack)) {
            return 1.0f;
        } else {
            return 0.2f; // 5x slower
        }
    }
}
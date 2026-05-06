package net.darkflameproduction.agotmod.entity.custom.npc.goals.tailor;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorSystem;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class TailorGoal extends Goal {

    private static final int TICKS_PER_CRAFT = 16;

    // Flower → Dye mapping
    private static final Map<String, String> FLOWER_TO_DYE = new LinkedHashMap<>();
    static {
        // Red dye
        FLOWER_TO_DYE.put("agotmod:rose",           "minecraft:red_dye");
        FLOWER_TO_DYE.put("agotmod:dusky_rose",     "minecraft:red_dye");
        FLOWER_TO_DYE.put("agotmod:ginger",         "minecraft:red_dye");
        FLOWER_TO_DYE.put("agotmod:frostfire",      "minecraft:red_dye");
        FLOWER_TO_DYE.put("agotmod:dragons_breath", "minecraft:red_dye");
        FLOWER_TO_DYE.put("agotmod:bloodbloom",     "minecraft:red_dye");
        FLOWER_TO_DYE.put("agotmod:red_rose_bush",  "minecraft:red_dye");
        FLOWER_TO_DYE.put("minecraft:poppy",         "minecraft:red_dye");
        FLOWER_TO_DYE.put("minecraft:red_tulip",     "minecraft:red_dye");
        // Blue dye
        FLOWER_TO_DYE.put("agotmod:blue_rose",      "minecraft:blue_dye");
        FLOWER_TO_DYE.put("agotmod:moonbloom",      "minecraft:blue_dye");
        FLOWER_TO_DYE.put("agotmod:blue_rose_bush", "minecraft:blue_dye");
        FLOWER_TO_DYE.put("minecraft:cornflower",    "minecraft:blue_dye");
        FLOWER_TO_DYE.put("minecraft:blue_orchid",   "minecraft:blue_dye");
        // White dye
        FLOWER_TO_DYE.put("agotmod:white_rose",     "minecraft:white_dye");
        FLOWER_TO_DYE.put("agotmod:ladys_lace",     "minecraft:white_dye");
        FLOWER_TO_DYE.put("agotmod:wild_radish",    "minecraft:white_dye");
        FLOWER_TO_DYE.put("agotmod:white_rose_bush","minecraft:white_dye");
        FLOWER_TO_DYE.put("minecraft:lily_of_the_valley","minecraft:white_dye");
        FLOWER_TO_DYE.put("minecraft:oxeye_daisy",  "minecraft:white_dye");
        FLOWER_TO_DYE.put("minecraft:white_tulip",  "minecraft:white_dye");
        // Light blue dye
        FLOWER_TO_DYE.put("agotmod:winter_rose",        "minecraft:light_blue_dye");
        FLOWER_TO_DYE.put("agotmod:forget_me_not",      "minecraft:light_blue_dye");
        FLOWER_TO_DYE.put("agotmod:coldsnap",           "minecraft:light_blue_dye");
        FLOWER_TO_DYE.put("agotmod:winter_rose_bush",   "minecraft:light_blue_dye");
        FLOWER_TO_DYE.put("minecraft:azure_bluet",      "minecraft:light_blue_dye");
        // Magenta dye
        FLOWER_TO_DYE.put("agotmod:thistle",        "minecraft:magenta_dye");
        FLOWER_TO_DYE.put("agotmod:saffron_crocus", "minecraft:magenta_dye");
        FLOWER_TO_DYE.put("agotmod:lungwort",       "minecraft:magenta_dye");
        FLOWER_TO_DYE.put("minecraft:allium",        "minecraft:magenta_dye");
        // Yellow dye
        FLOWER_TO_DYE.put("agotmod:tansy",          "minecraft:yellow_dye");
        FLOWER_TO_DYE.put("agotmod:sedge",          "minecraft:yellow_dye");
        FLOWER_TO_DYE.put("agotmod:gorse",          "minecraft:yellow_dye");
        FLOWER_TO_DYE.put("agotmod:goldenrod",      "minecraft:yellow_dye");
        FLOWER_TO_DYE.put("agotmod:goldencup",      "minecraft:yellow_dye");
        FLOWER_TO_DYE.put("agotmod:goathead",       "minecraft:yellow_dye");
        FLOWER_TO_DYE.put("agotmod:evening_star",   "minecraft:yellow_dye");
        FLOWER_TO_DYE.put("minecraft:dandelion",     "minecraft:yellow_dye");
        FLOWER_TO_DYE.put("minecraft:sunflower",     "minecraft:yellow_dye");
        // Orange dye
        FLOWER_TO_DYE.put("agotmod:spiceflower",    "minecraft:orange_dye");
        FLOWER_TO_DYE.put("minecraft:orange_tulip",  "minecraft:orange_dye");
        // Purple dye
        FLOWER_TO_DYE.put("agotmod:poison_kisses",  "minecraft:purple_dye");
        FLOWER_TO_DYE.put("agotmod:pennyroyal",     "minecraft:purple_dye");
        FLOWER_TO_DYE.put("agotmod:nightshade",     "minecraft:purple_dye");
        FLOWER_TO_DYE.put("agotmod:lavender",       "minecraft:purple_dye");
        // Pink dye
        FLOWER_TO_DYE.put("agotmod:liverwort",      "minecraft:pink_dye");
        FLOWER_TO_DYE.put("agotmod:gillyflower",    "minecraft:pink_dye");
        FLOWER_TO_DYE.put("minecraft:pink_tulip",    "minecraft:pink_dye");
        FLOWER_TO_DYE.put("minecraft:peony",         "minecraft:pink_dye");
        // Black dye
        FLOWER_TO_DYE.put("agotmod:black_lotus",    "minecraft:black_dye");
        FLOWER_TO_DYE.put("minecraft:wither_rose",   "minecraft:black_dye");
        // Other vanilla
        FLOWER_TO_DYE.put("minecraft:lilac",         "minecraft:magenta_dye");
        FLOWER_TO_DYE.put("minecraft:rose_bush",     "minecraft:red_dye");
    }

    private final Peasant_Entity peasant;
    private int craftTick = 0;

    public TailorGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TAILOR)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (!isJobBlockValid()) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getTailorSystem().getLastCollectionDay() != currentDay) return false;

        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 12000) return false;

        return peasant.getTailorSystem().getCurrentState() == TailorSystem.TailorState.WORKING
                && hasAnythingToCraft();
    }

    @Override
    public boolean canContinueToUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TAILOR)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 12000) return false;
        return hasAnythingToCraft();
    }

    private boolean hasAnythingToCraft() {
        // Has flowers to turn into dye
        for (String flowerKey : FLOWER_TO_DYE.keySet()) {
            if (hasItemInInventory(flowerKey, 1)) return true;
        }
        // Has string to turn into wool
        if (hasItemInInventory("minecraft:string", 4)) return true;
        // Has wool to turn into cloth
        if (hasItemInInventory("minecraft:white_wool", 2)) return true;
        // Has leather to craft armor
        if (hasItemInInventory("minecraft:leather", 7)) return true;
        // Has cotton to turn into string
        if (hasItemInInventory("agotmod:cotton", 1)) return true;
        return false;
    }

    @Override
    public void start() {
        craftTick = 0;
        peasant.getTailorSystem().setCurrentState(TailorSystem.TailorState.WORKING);
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock != null) {
            peasant.getNavigation().moveTo(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 1.5, 0.6D);
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        craftTick = 0;
    }

    @Override
    public void tick() {
        if (peasant.level().isClientSide) return;

        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        double dist = peasant.distanceToSqr(
                jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);

        if (dist > 4.0D) {
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 1.5, 0.6D);
            }
            return;
        }

        peasant.getNavigation().stop();
        peasant.getLookControl().setLookAt(
                jobBlock.getX() + 0.5, jobBlock.getY() + 0.5, jobBlock.getZ() + 0.5);

        craftTick++;
        if (craftTick < TICKS_PER_CRAFT) return;
        craftTick = 0;

        tryCraft();
    }

    private void tryCraft() {
        // Step 1: Cotton → String (1:1)
        if (hasItemInInventory("agotmod:cotton", 1)) {
            removeItemFromInventory("agotmod:cotton", 1);
            addItemToInventory(new ItemStack(Items.STRING, 1));
            peasant.triggerInteractAnimation();
            return;
        }

        // Step 2: String → Wool (4 string = 1 white wool)
        if (hasItemInInventory("minecraft:string", 4)) {
            removeItemFromInventory("minecraft:string", 4);
            addItemToInventory(new ItemStack(Items.WHITE_WOOL, 1));
            peasant.triggerInteractAnimation();
            return;
        }

        // Step 3: Wool → Cloth (2 wool = 1 cloth)
        if (hasItemInInventory("minecraft:white_wool", 2)) {
            removeItemFromInventory("minecraft:white_wool", 2);
            addItemToInventory(new ItemStack(ModItems.CLOTH.get(), 1));
            peasant.triggerInteractAnimation();
            return;
        }

        // Step 4: Flowers → Dye (1 flower = 1 dye)
        for (Map.Entry<String, String> entry : FLOWER_TO_DYE.entrySet()) {
            String flowerKey = entry.getKey();
            String dyeKey    = entry.getValue();
            if (hasItemInInventory(flowerKey, 1)) {
                removeItemFromInventory(flowerKey, 1);
                spawnItem(dyeKey, 1);
                peasant.triggerInteractAnimation();
                return;
            }
        }

        // Step 5: Leather armor crafting
        // Helmet: 5 leather
        if (hasItemInInventory("minecraft:leather", 5) &&
                !hasItemInInventory("minecraft:leather_helmet", 1)) {
            removeItemFromInventory("minecraft:leather", 5);
            addItemToInventory(new ItemStack(Items.LEATHER_HELMET, 1));
            peasant.triggerInteractAnimation();
            return;
        }
        // Chestplate: 8 leather
        if (hasItemInInventory("minecraft:leather", 8) &&
                !hasItemInInventory("minecraft:leather_chestplate", 1)) {
            removeItemFromInventory("minecraft:leather", 8);
            addItemToInventory(new ItemStack(Items.LEATHER_CHESTPLATE, 1));
            peasant.triggerInteractAnimation();
            return;
        }
        // Leggings: 7 leather
        if (hasItemInInventory("minecraft:leather", 7) &&
                !hasItemInInventory("minecraft:leather_leggings", 1)) {
            removeItemFromInventory("minecraft:leather", 7);
            addItemToInventory(new ItemStack(Items.LEATHER_LEGGINGS, 1));
            peasant.triggerInteractAnimation();
            return;
        }
        // Boots: 4 leather
        if (hasItemInInventory("minecraft:leather", 4)) {
            removeItemFromInventory("minecraft:leather", 4);
            addItemToInventory(new ItemStack(Items.LEATHER_BOOTS, 1));
            peasant.triggerInteractAnimation();
        }
    }

    private boolean isJobBlockValid() {
        BlockPos pos = peasant.getJobBlockPos();
        if (pos == null) return false;
        BlockState state = peasant.level().getBlockState(pos);
        return state.is(ModBLocks.TAILOR_BARREL.get());
    }

    private boolean hasItemInInventory(String itemKey, int amount) {
        var inventory = peasant.getInventorySystem().getInventory();
        int count = 0;
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
                if (key.equals(itemKey)) count += stack.getCount();
            }
        }
        return count >= amount;
    }

    private void removeItemFromInventory(String itemKey, int amount) {
        var inventory = peasant.getInventorySystem().getInventory();
        int toRemove = amount;
        for (int i = 0; i < inventory.getContainerSize() && toRemove > 0; i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
                if (key.equals(itemKey)) {
                    int removed = Math.min(stack.getCount(), toRemove);
                    stack.shrink(removed);
                    toRemove -= removed;
                    if (stack.isEmpty()) inventory.setItem(i, ItemStack.EMPTY);
                }
            }
        }
    }

    private void addItemToInventory(ItemStack stack) {
        peasant.getInventorySystem().addItem(stack);
    }

    private void spawnItem(String itemKey, int count) {
        ResourceLocation loc = ResourceLocation.tryParse(itemKey);
        if (loc == null) return;
        net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.getValue(loc);
        if (item == null || item == Items.AIR) return;
        peasant.getInventorySystem().addItem(new ItemStack(item, count));
    }
}
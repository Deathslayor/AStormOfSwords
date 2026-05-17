package net.darkflameproduction.agotmod.entity.custom.npc.goals.carpenter;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class CarpenterGoal extends Goal {

    private static final int TICKS_PER_CRAFT = 20;

    // All craftable product types in rotation order (excluding planks)
    // The carpenter cycles through these in order, one per craft tick.
    // Planks are kept at 50% of total output by allowing one product per plank batch.
    private static final String[] PRODUCT_ROTATION = {
            "stool", "chair", "arm_chair", "table",
            "stairs", "fence", "fence_gate", "wall", "slab", "door", "trapdoor"
    };

    private final Peasant_Entity peasant;
    private int craftTick    = 0;
    private int rotationIndex = 0; // which product to attempt next
    private String woodType  = "oak";

    // Track how many planks and non-plank items produced this session
    // to maintain the 50/50 split
    private int planksProduced      = 0;
    private int nonPlanksProduced   = 0;

    public CarpenterGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_CARPENTER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (!isJobBlockValid()) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 12000) return false;

        woodType = detectWoodType();
        return hasAnythingToProcess();
    }

    @Override
    public boolean canContinueToUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_CARPENTER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 12000) return false;
        return hasAnythingToProcess();
    }

    @Override
    public void start() {
        craftTick         = 0;
        nonPlanksProduced = 0;
        woodType          = detectWoodType();
        // Seed planksProduced from actual inventory so the balance check works
        // even when planks were received directly rather than converted from logs
        planksProduced    = countInInventory(itemKey(woodType + "_planks"));
        peasant.getCarpenterSystem().setCurrentState(CarpenterSystem.CarpenterState.WORKING);

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

        woodType = detectWoodType();
        tryCraft();
    }

    // ── Crafting logic ────────────────────────────────────────────────────────

    private void tryCraft() {
        String logKey    = resolveLogKey(woodType);
        String planksKey = itemKey(woodType + "_planks");
        String nailKey   = "agotmod:nail";
        String stickKey  = "minecraft:stick";

        // Convert logs to planks first — always do this, planks are the base resource
        if (logKey != null && has(logKey, 1)) {
            remove(logKey, 1);
            give(woodType + "_planks", 4);
            planksProduced += 4;
            AGoTMod.LOGGER.debug("[Carpenter] log->planks, planks={} nonPlanks={}", planksProduced, nonPlanksProduced);
            peasant.triggerInteractAnimation();
            return;
        }

        int planksAvail = countInInventory(planksKey);

        // Reserve half the planks — only craft products if we have more planks
        // than what we've already set aside for selling
        // Rule: non-plank items produced should not exceed planks produced.
        // Also always keep at least 4 planks in reserve so we don't empty the supply.
        boolean canMakeProducts = nonPlanksProduced < planksProduced && planksAvail > 4;

        if (!canMakeProducts) {
            // Nothing to do this tick — we're either waiting for logs
            // or maintaining the 50/50 split
            AGoTMod.LOGGER.debug("[Carpenter] Waiting for balance. planks={} nonPlanks={} avail={}",
                    planksProduced, nonPlanksProduced, planksAvail);
            return;
        }

        // Try the next product in rotation, skipping ones we can't make
        int attempts = 0;
        while (attempts < PRODUCT_ROTATION.length) {
            String product = PRODUCT_ROTATION[rotationIndex];
            rotationIndex  = (rotationIndex + 1) % PRODUCT_ROTATION.length;
            attempts++;

            if (tryCraftProduct(product, planksKey, stickKey, nailKey)) {
                peasant.triggerInteractAnimation();
                return;
            }
        }

        // Couldn't craft any product — ran out of sticks/nails/planks
        AGoTMod.LOGGER.debug("[Carpenter] Could not craft any product this tick");
    }

    /**
     * Attempts to craft one unit of the given product type.
     * Returns true if successful.
     */
    private boolean tryCraftProduct(String product, String planksKey, String stickKey, String nailKey) {
        switch (product) {
            case "stool" -> {
                if (has(planksKey, 2) && has(stickKey, 2) && has(nailKey, 1)) {
                    remove(planksKey, 2); remove(stickKey, 2); remove(nailKey, 1);
                    give(woodType + "_stool", 1);
                    nonPlanksProduced++;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted stool");
                    return true;
                }
            }
            case "chair" -> {
                String stoolKey = itemKey(woodType + "_stool");
                if (has(stoolKey, 1) && has(stickKey, 2) && has(nailKey, 1)) {
                    remove(stoolKey, 1); remove(stickKey, 2); remove(nailKey, 1);
                    give(woodType + "_chair", 1);
                    nonPlanksProduced++;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted chair");
                    return true;
                }
            }
            case "arm_chair" -> {
                String chairKey = itemKey(woodType + "_chair");
                if (has(chairKey, 1) && has(stickKey, 2) && has(nailKey, 1)) {
                    remove(chairKey, 1); remove(stickKey, 2); remove(nailKey, 1);
                    give(woodType + "_arm_chair", 1);
                    nonPlanksProduced++;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted arm_chair");
                    return true;
                }
            }
            case "table" -> {
                if (has(planksKey, 3) && has(stickKey, 2) && has(nailKey, 1)) {
                    remove(planksKey, 3); remove(stickKey, 2); remove(nailKey, 1);
                    give(woodType + "_table", 1);
                    nonPlanksProduced += 3; // costs 3 planks worth
                    AGoTMod.LOGGER.debug("[Carpenter] crafted table");
                    return true;
                }
            }
            case "stairs" -> {
                if (has(planksKey, 6)) {
                    remove(planksKey, 6);
                    give(woodType + "_stairs", 4);
                    nonPlanksProduced += 6;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted stairs");
                    return true;
                }
            }
            case "fence" -> {
                if (has(planksKey, 4) && has(stickKey, 2)) {
                    remove(planksKey, 4); remove(stickKey, 2);
                    give(woodType + "_fence", 3);
                    nonPlanksProduced += 4;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted fence");
                    return true;
                }
            }
            case "fence_gate" -> {
                if (has(planksKey, 2) && has(stickKey, 4)) {
                    remove(planksKey, 2); remove(stickKey, 4);
                    give(woodType + "_fence_gate", 1);
                    nonPlanksProduced += 2;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted fence_gate");
                    return true;
                }
            }
            case "wall" -> {
                if (has(planksKey, 6)) {
                    remove(planksKey, 6);
                    give(woodType + "_wall", 6);
                    nonPlanksProduced += 6;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted wall");
                    return true;
                }
            }
            case "slab" -> {
                if (has(planksKey, 3)) {
                    remove(planksKey, 3);
                    String slabName = woodType + (isVanillaWood(woodType) ? "_slab" : "_slabs");
                    give(slabName, 6);
                    nonPlanksProduced += 3;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted slab");
                    return true;
                }
            }
            case "door" -> {
                if (has(planksKey, 6)) {
                    remove(planksKey, 6);
                    give(woodType + "_door", 3);
                    nonPlanksProduced += 6;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted door");
                    return true;
                }
            }
            case "trapdoor" -> {
                if (has(planksKey, 6)) {
                    remove(planksKey, 6);
                    give(woodType + "_trapdoor", 2);
                    nonPlanksProduced += 6;
                    AGoTMod.LOGGER.debug("[Carpenter] crafted trapdoor");
                    return true;
                }
            }
        }
        return false;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private boolean hasAnythingToProcess() {
        String logKey    = resolveLogKey(woodType);
        String planksKey = itemKey(woodType + "_planks");
        String stickKey  = "minecraft:stick";
        String nailKey   = "agotmod:nail";

        if (logKey != null && has(logKey, 1)) return true;
        // Has planks and room to make something
        int planksAvail = countInInventory(planksKey);
        if (planksAvail >= 3) return true;
        if (planksAvail >= 2 && has(stickKey, 2) && has(nailKey, 1)) return true;
        return false;
    }

    private String detectWoodType() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.isEmpty()) continue;
            String key  = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
            String path = key.contains(":") ? key.split(":")[1] : key;
            if (path.endsWith("_log"))    return path.replace("_log", "");
            if (path.endsWith("_stem"))   return path.replace("_stem", "");
            if (path.equals("bamboo_block")) return "bamboo";
            if (path.endsWith("_planks")) return path.replace("_planks", "");
        }
        return "oak";
    }

    private boolean isVanillaWood(String wood) {
        return switch (wood) {
            case "oak","spruce","birch","jungle","acacia","dark_oak",
                 "mangrove","cherry","bamboo","crimson","warped" -> true;
            default -> false;
        };
    }

    private String resolveLogKey(String wood) {
        if (ModBLocks.LOGS.containsKey(wood)) return "agotmod:" + wood + "_log";
        return switch (wood) {
            case "oak"      -> "minecraft:oak_log";
            case "spruce"   -> "minecraft:spruce_log";
            case "birch"    -> "minecraft:birch_log";
            case "jungle"   -> "minecraft:jungle_log";
            case "acacia"   -> "minecraft:acacia_log";
            case "dark_oak" -> "minecraft:dark_oak_log";
            case "mangrove" -> "minecraft:mangrove_log";
            case "cherry"   -> "minecraft:cherry_log";
            case "bamboo"   -> "minecraft:bamboo_block";
            case "crimson"  -> "minecraft:crimson_stem";
            case "warped"   -> "minecraft:warped_stem";
            default         -> "agotmod:" + wood + "_log";
        };
    }

    private String itemKey(String name) {
        ResourceLocation mod = ResourceLocation.fromNamespaceAndPath("agotmod", name);
        if (BuiltInRegistries.ITEM.getValue(mod) != Items.AIR) return mod.toString();
        ResourceLocation mc = ResourceLocation.fromNamespaceAndPath("minecraft", name);
        if (BuiltInRegistries.ITEM.getValue(mc) != Items.AIR) return mc.toString();
        return "agotmod:" + name;
    }

    private void give(String name, int count) {
        Item item = BuiltInRegistries.ITEM.getValue(ResourceLocation.fromNamespaceAndPath("agotmod", name));
        if (item == null || item == Items.AIR)
            item = BuiltInRegistries.ITEM.getValue(ResourceLocation.fromNamespaceAndPath("minecraft", name));
        if (item != null && item != Items.AIR)
            peasant.getInventorySystem().addItem(new ItemStack(item, count));
        else
            AGoTMod.LOGGER.warn("[Carpenter] Cannot resolve item: {}", name);
    }

    private boolean has(String key, int amount) { return countInInventory(key) >= amount; }

    private int countInInventory(String key) {
        var inventory = peasant.getInventorySystem().getInventory();
        int count = 0;
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && BuiltInRegistries.ITEM.getKey(stack.getItem()).toString().equals(key))
                count += stack.getCount();
        }
        return count;
    }

    private void remove(String key, int amount) {
        var inventory = peasant.getInventorySystem().getInventory();
        int rem = amount;
        for (int i = 0; i < inventory.getContainerSize() && rem > 0; i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && BuiltInRegistries.ITEM.getKey(stack.getItem()).toString().equals(key)) {
                int removed = Math.min(stack.getCount(), rem);
                stack.shrink(removed);
                rem -= removed;
                if (stack.isEmpty()) inventory.setItem(i, ItemStack.EMPTY);
            }
        }
    }

    private boolean isJobBlockValid() {
        BlockPos pos = peasant.getJobBlockPos();
        if (pos == null) return false;
        BlockState state = peasant.level().getBlockState(pos);
        return state.is(ModBLocks.CARPENTER_BARREL.get());
    }
}
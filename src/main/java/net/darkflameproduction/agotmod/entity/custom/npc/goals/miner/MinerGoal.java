package net.darkflameproduction.agotmod.entity.custom.npc.goals.miner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.miner.MinerSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;

public class MinerGoal extends Goal {

    private final Peasant_Entity peasant;
    private int swingTimer = 0;
    private static final int SWING_INTERVAL = 40; // 2 seconds

    private static final String[] RUBBLE = {
            "minecraft:cobblestone",
            "minecraft:cobblestone",
            "minecraft:cobblestone",
            "minecraft:cobblestone",
            "minecraft:cobblestone",
            "minecraft:cobblestone",
            "minecraft:cobblestone",
            "minecraft:cobblestone",
            "minecraft:cobblestone",
            "minecraft:cobbled_deepslate",
            "minecraft:cobbled_deepslate",
            "minecraft:cobbled_deepslate",
            "minecraft:cobbled_deepslate",
            "minecraft:cobbled_deepslate",
            "minecraft:cobbled_deepslate",
            "minecraft:cobbled_deepslate",
            "minecraft:cobbled_deepslate",
            "minecraft:cobbled_deepslate",
            "minecraft:gravel"
    };

    private static final String[] GEMS = {
            "minecraft:lapis_lazuli",
            "agotmod:amber",
            "agotmod:amethyst",
            "agotmod:bloodstone",
            "agotmod:carnelian",
            "agotmod:chalcedony",
            "agotmod:garnet",
            "agotmod:jade",
            "agotmod:jasper",
            "agotmod:malachite",
            "agotmod:moonstone",
            "agotmod:onyx",
            "agotmod:opal",
            "agotmod:ruby",
            "agotmod:sapphire",
            "agotmod:tigers_eye",
            "agotmod:topaz",
            "agotmod:tourmaline",
            "agotmod:yellow_diamond",
            "agotmod:transparent_diamond",
            "agotmod:black_diamond"
    };

    public MinerGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public boolean canContinueToUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public void start() {
        swingTimer = 0;
        peasant.getMinerSystem().setCurrentMinerState(MinerSystem.MinerState.GOING_TO_JOB_BLOCK);
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        swingTimer = 0;
    }

    @Override
    public void tick() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        MinerSystem.MinerState state = peasant.getMinerSystem().getCurrentMinerState();

        if (state == MinerSystem.MinerState.GOING_TO_JOB_BLOCK) {
            double dist = peasant.distanceToSqr(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);

            if (dist <= 4.0D) {
                peasant.getNavigation().stop();
                peasant.getMinerSystem().setCurrentMinerState(MinerSystem.MinerState.MINING);
            } else {
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(
                            jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.7D);
                }
            }
            return;
        }

        // MINING state
        peasant.getLookControl().setLookAt(
                jobBlock.getX() + 0.5, jobBlock.getY() + 0.5, jobBlock.getZ() + 0.5);

        swingTimer++;
        if (swingTimer >= SWING_INTERVAL) {
            swingTimer = 0;
            peasant.triggerInteractAnimation();
            rollForItems();
        }
    }

    private void rollForItems() {
        if (peasant.level().isClientSide) return;

        double roll = peasant.getRandom().nextDouble() * 100.0;

        if (roll < 90.0) {
            // 90% — rubble
            spawnItem(RUBBLE[peasant.getRandom().nextInt(RUBBLE.length)]);

        } else if (roll < 94.5) {
            // 4.5% — coal
            spawnItem("minecraft:coal");

        } else if (roll < 96.3) {
            // 1.8% — iron
            spawnItem("minecraft:raw_iron");

        } else if (roll < 97.65) {
            // 1.35% — copper
            spawnItem("minecraft:raw_copper");

        } else if (roll < 99.0) {
            // 1.35% — tin
            spawnItem("agotmod:raw_tin");

        } else if (roll < 99.6) {
            // 0.6% — silver
            spawnItem("agotmod:raw_silver");

        } else if (roll < 99.85) {
            // 0.25% — gold
            spawnItem("minecraft:raw_gold");

        } else {
            // 0.15% — random gem
            spawnItem(GEMS[peasant.getRandom().nextInt(GEMS.length)]);
        }
    }

    private void spawnItem(String itemKey) {
        ResourceLocation loc = ResourceLocation.tryParse(itemKey);
        if (loc == null) return;
        net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
        if (item == null || item == Items.AIR) return;
        peasant.getInventorySystem().addItem(new ItemStack(item, 1));
    }
}

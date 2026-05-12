package net.darkflameproduction.agotmod.entity.custom.npc.goals.lumberjack;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.lumberjack.LumberjackSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;

public class LumberjackGoal extends Goal {

    private static final int    COLLECT_RADIUS = 12;
    private static final double PICKUP_DIST_SQ = 2.5;
    private static final int    SWING_DURATION = 8;
    private static final int    COLLECT_TIMEOUT = 200; // 10 seconds max collecting

    private final Peasant_Entity peasant;
    private int swingProgress   = 0;
    private int collectTimer    = 0;

    public LumberjackGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    private LumberjackSystem getSystem() {
        return peasant.getLumberjackSystem();
    }

    @Override
    public boolean canUse() {
        return peasant.getJobType().equals(JobSystem.JOB_LUMBERJACK)
                && !peasant.shouldSleep()
                && !peasant.needsFoodCollection();
    }

    @Override
    public boolean canContinueToUse() {
        return canUse();
    }

    @Override
    public void start() {
        swingProgress = 0;
        collectTimer  = 0;
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        swingProgress = 0;
        collectTimer  = 0;
        getSystem().setCurrentState(LumberjackSystem.LumberjackState.GOING_TO_JOB_BLOCK);
    }

    @Override
    public void tick() {
        if (peasant.level().isClientSide) return;

        LumberjackSystem system = getSystem();
        if (system == null) return;

        system.tick();

        switch (system.getCurrentState()) {
            case GOING_TO_JOB_BLOCK -> navigateToJobBlock();
            case IDLING              -> stopMoving();
            case GOING_TO_TREE       -> navigateToTree();
            case CHOPPING            -> handleChopping();
            case COLLECTING          -> handleCollecting();
            case REPLANTING          -> stopMoving();
            case DEPOSITING          -> stopMoving();
        }
    }

    // ── Navigation ────────────────────────────────────────────────────────────

    private void navigateToJobBlock() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;
        double dx = peasant.getX() - (jobBlock.getX() + 0.5);
        double dz = peasant.getZ() - (jobBlock.getZ() + 0.5);
        if (dx * dx + dz * dz > 4) {
            peasant.getNavigation().moveTo(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6);
        }
    }

    private void navigateToTree() {
        BlockPos tree = getSystem().getTargetTreePos();
        if (tree == null) return;

        double dx = peasant.getX() - (tree.getX() + 0.5);
        double dz = peasant.getZ() - (tree.getZ() + 0.5);
        double horizontalDistSq = dx * dx + dz * dz;

        if (horizontalDistSq > 25) {
            // Navigate to the X/Z of the tree but at the peasant's own Y
            // so pathfinding stays on the ground
            peasant.getNavigation().moveTo(
                    tree.getX() + 0.5,
                    peasant.getY(),
                    tree.getZ() + 0.5,
                    0.6);
        }
    }

    // ── Chopping ──────────────────────────────────────────────────────────────

    private void handleChopping() {
        peasant.getNavigation().stop();

        BlockPos tree = getSystem().getTargetTreePos();
        if (tree != null) {
            peasant.getLookControl().setLookAt(
                    tree.getX() + 0.5, tree.getY() + 1.0, tree.getZ() + 0.5);
        }

        // Swing animation cycles like the miner
        swingProgress++;
        if (swingProgress >= SWING_DURATION) {
            peasant.triggerInteractAnimation();
            swingProgress = 0;
        }
    }

    // ── Collecting — mirrors AnimalHerderGoal.pickUpNearbyItems pattern ───────

    private void handleCollecting() {
        if (!(peasant.level() instanceof ServerLevel level)) return;

        collectTimer++;

        // Check every 20 ticks like the animal herder
        if (collectTimer % 20 != 0) return;

        // Search around the chopped tree position, or peasant if no tree pos
        BlockPos center = getSystem().getTargetTreePos();
        if (center == null) center = peasant.blockPosition();

        AABB searchArea = new AABB(
                center.getX() - 10, center.getY() - 10, center.getZ() - 10,
                center.getX() + 10, center.getY() + 10, center.getZ() + 10
        );

        List<ItemEntity> nearbyItems = level.getEntitiesOfClass(
                ItemEntity.class, searchArea,
                item -> !item.getItem().isEmpty()
        );

        // Pick up ALL items at once — no navigation needed
        for (ItemEntity item : nearbyItems) {
            peasant.getInventorySystem().addItem(item.getItem().copy());
            item.discard();
        }

        // Move to replanting regardless — items are all collected
        if (collectTimer > 40 || nearbyItems.isEmpty()) {
            collectTimer = 0;
            getSystem().setCurrentState(LumberjackSystem.LumberjackState.REPLANTING);
        }
    }

    // ── Misc ──────────────────────────────────────────────────────────────────

    private void stopMoving() {
        peasant.getNavigation().stop();
        swingProgress = 0;
    }
}
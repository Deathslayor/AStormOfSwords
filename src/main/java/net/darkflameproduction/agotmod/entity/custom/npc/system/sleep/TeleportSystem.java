package net.darkflameproduction.agotmod.entity.custom.npc.system.sleep;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;

/**
 * Gets NPCs into bed when walking there fails. At bedtime an NPC that hasn't come closer to its bed for
 * STUCK_TICKS (stuck behind a door, on furniture, in a pit), or still isn't in bed LATE_TICKS after bedtime
 * began, is put straight into its bed.
 *
 * The old check only fired after 30 seconds on exactly the same block with no path at all - an NPC wobbling
 * against a door, or re-planning its path every few seconds, never met both, so it never teleported.
 */
public class TeleportSystem {
    private final Peasant_Entity peasant;

    private static final int CHECK_INTERVAL = 20;          // ticks between checks
    private static final int STUCK_TICKS = 300;            // 15 s without getting any closer to the bed
    private static final int LATE_TICKS = 1800;            // 90 s of bedtime and still not in bed
    private static final int TELEPORT_COOLDOWN = 400;      // 20 s between teleports
    private static final double PROGRESS_SQR = 1.0D;       // getting a block closer counts as progress

    private int bedtimeTicks = 0;        // how long it has been bedtime without the NPC asleep
    private int ticksWithoutProgress = 0;
    private double closestDistanceSqr = Double.MAX_VALUE;
    private int teleportCooldown = 0;

    public TeleportSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (peasant.level().isClientSide) return;
        if (teleportCooldown > 0) teleportCooldown--;
        if ((peasant.tickCount + peasant.getId()) % CHECK_INTERVAL != 0) return;

        if (!peasant.shouldSleep() || peasant.isSleeping()) {
            reset();
            return;
        }
        // Eating, talking to a player or fighting come first - bedtime waits for them
        if (peasant.getHungerSystem().isEating() || peasant.isInteractingWithPlayer()
                || peasant.getGuardSystem().getCurrentTarget() != null) {
            return;
        }

        BlockPos bed = bedToReach();
        if (bed == null) {
            reset();
            return;
        }

        bedtimeTicks += CHECK_INTERVAL;
        double distanceSqr = peasant.distanceToSqr(bed.getX() + 0.5, bed.getY() + 0.5, bed.getZ() + 0.5);
        if (distanceSqr < closestDistanceSqr - PROGRESS_SQR) {
            closestDistanceSqr = distanceSqr;
            ticksWithoutProgress = 0;
        } else {
            ticksWithoutProgress += CHECK_INTERVAL;
        }

        if (ticksWithoutProgress >= STUCK_TICKS || bedtimeTicks >= LATE_TICKS) {
            teleportToBed();
        }
    }

    /** The bed the NPC is trying to get to - its home bed first - if it is there, loaded and free. */
    private BlockPos bedToReach() {
        BlockPos bed = peasant.getHomeSystem().hasHomeBed() ? peasant.getHomeSystem().getHomeBedPos() : peasant.getBedPos();
        if (bed == null || !peasant.level().isLoaded(bed)) return null;
        bed = BedAccess.head(peasant.level(), bed);
        if (bed == null || SleepSystem.isBedOccupied(peasant.level(), bed)) return null;
        return bed;
    }

    private void reset() {
        bedtimeTicks = 0;
        ticksWithoutProgress = 0;
        closestDistanceSqr = Double.MAX_VALUE;
    }

    /** Puts the NPC straight into its bed, asleep. */
    public boolean teleportToBed() {
        if (teleportCooldown > 0) return false;
        BlockPos bed = bedToReach();
        if (bed == null) return false;

        peasant.level().playSound(null, peasant.getX(), peasant.getY(), peasant.getZ(),
                SoundEvents.ENDERMAN_TELEPORT, SoundSource.NEUTRAL, 0.5F, 1.0F);
        peasant.getNavigation().stop();
        peasant.setBedPos(bed);
        // startSleeping lies the NPC down in the bed (LivingEntity.setPosToBed); SleepSystem wakes it in the morning
        peasant.startSleeping(bed);
        teleportCooldown = TELEPORT_COOLDOWN;
        reset();
        return true;
    }

    public void saveData(CompoundTag compound) {
        compound.putInt("TeleportCooldown", teleportCooldown);
    }

    public void loadData(CompoundTag compound) {
        teleportCooldown = compound.getInt("TeleportCooldown");
    }
}

package net.darkflameproduction.agotmod.entity.custom.npc.goals.guard;

import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumSet;
import java.util.List;

/**
 * On-duty guard behaviour. The guard picks objectives in a random weighted order and
 * marches between them at walking speed:
 *
 *  - PATROL:       walk a route between several job blocks, circling some of them
 *  - VISIT:        briefly visit a single NPC's job block
 *  - WANDER:       wander around town for a few stops
 *  - RETURN_POST:  return to their own guard barrel for a while
 *  - STAND_GUARD:  stand at a spot for 10 seconds to 1 minute
 *
 * Each objective is a queue of waypoints; the objective is complete when the queue is empty.
 */
public class GuardPatrolGoal extends Goal {

    private static final double WALK_SPEED = 0.6D;

    // Stand guard: 10 seconds to 1 minute
    private static final int STAND_GUARD_MIN_TICKS = 200;
    private static final int STAND_GUARD_MAX_TICKS = 1200;

    // Brief visit to a job block: 3 to 8 seconds
    private static final int VISIT_MIN_TICKS = 60;
    private static final int VISIT_MAX_TICKS = 160;

    // Back at own post: 5 to 15 seconds
    private static final int POST_MIN_TICKS = 100;
    private static final int POST_MAX_TICKS = 300;

    // Short look-around at a patrol stop: 1 to 3 seconds
    private static final int PATROL_STOP_MIN_TICKS = 20;
    private static final int PATROL_STOP_MAX_TICKS = 60;

    private static final int PATROL_MIN_STOPS = 3;
    private static final int PATROL_MAX_STOPS = 6;
    private static final int WANDER_MIN_STOPS = 2;
    private static final int WANDER_MAX_STOPS = 4;

    // Only go back to own post every now and then
    private static final int RETURN_POST_COOLDOWN_TICKS = 20 * 60 * 4; // 4 minutes
    private static final int MIN_WANDER_DIST_FROM_POST = 16;

    private static final double ARRIVAL_DIST_SQ = 2.25D;         // 1.5 blocks
    private static final double NAV_DONE_ARRIVAL_DIST_SQ = 9.0D; // 3 blocks, when the path ends short
    private static final int MAX_REPATH_ATTEMPTS = 3;            // legs in a row without progress before giving up
    private static final int LEG_LENGTH = 32;                    // navigation can't path past follow range, so long trips go in legs
    private static final int BASE_TRAVEL_TICKS = 20 * 20;        // timeout = 20s + 0.5s per block
    private static final int TRAVEL_TICKS_PER_BLOCK = 10;

    enum Objective {
        PATROL(50),
        VISIT(15),
        STAND_GUARD(15),
        WANDER(15),
        RETURN_POST(5);

        final int weight;

        Objective(int weight) {
            this.weight = weight;
        }
    }

    /** One stop in an objective. pos may be null for wander stops, which are resolved when reached. */
    private static class Waypoint {
        BlockPos pos;
        final int waitTicks;
        final BlockPos lookAt; // null = look around freely while waiting

        Waypoint(BlockPos pos, int waitTicks, BlockPos lookAt) {
            this.pos = pos;
            this.waitTicks = waitTicks;
            this.lookAt = lookAt;
        }
    }

    private final Peasant_Entity peasant;
    private final Deque<Waypoint> waypoints = new ArrayDeque<>();
    private Objective currentObjective = null;
    private Objective lastObjective = null;

    private Waypoint activeWaypoint = null;
    private boolean waiting = false;
    private int waitTicksLeft = 0;
    private int travelTicks = 0;
    private int maxTravelTicks = 0;
    private int repathAttempts = 0;
    private double lastLegDistSq = Double.MAX_VALUE;
    private long lastPostVisitTime = Long.MIN_VALUE / 2;
    private int lookAroundCooldown = 0;
    private double lookX, lookZ;

    public GuardPatrolGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    // ===== GOAL LIFECYCLE =====

    @Override
    public boolean canUse() {
        return isOnDuty();
    }

    @Override
    public boolean canContinueToUse() {
        return isOnDuty();
    }

    /** On shift, awake, and not eating, talking or fighting. */
    private boolean isOnDuty() {
        if (!peasant.getJobType().equals(JobSystem.JOB_GUARD) || peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() || peasant.needsFoodCollection()) return false;
        if (peasant.isInteractingWithPlayer() || peasant.isPlayingInteractAnimation()) return false;
        if (peasant.getTarget() != null || peasant.getGuardSystem().getCurrentTarget() != null) return false;
        return peasant.getGuardSystem().hasAssignedShift() && !peasant.getGuardSystem().shouldSleep();
    }

    @Override
    public void start() {
        // Always start fresh after an interruption (combat, eating, talking to a player)
        clearObjective();
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        clearObjective();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (activeWaypoint == null && !advanceToNextWaypoint()) {
            // Objective finished (or couldn't be planned) - pick the next one
            planNextObjective();
            if (!advanceToNextWaypoint()) {
                currentObjective = null;
                return;
            }
        }

        if (waiting) {
            tickWaiting();
        } else {
            tickTravelling();
        }
    }

    // ===== WAYPOINT EXECUTION =====

    private boolean advanceToNextWaypoint() {
        while (!waypoints.isEmpty()) {
            Waypoint next = waypoints.poll();
            if (next.pos == null) {
                next.pos = resolveWanderStop();
            }
            if (next.pos == null) continue; // no spot found, skip this stop

            activeWaypoint = next;
            waiting = false;
            travelTicks = 0;
            repathAttempts = 0;
            lastLegDistSq = Double.MAX_VALUE;
            int distance = (int) Math.sqrt(peasant.blockPosition().distSqr(next.pos));
            maxTravelTicks = BASE_TRAVEL_TICKS + distance * TRAVEL_TICKS_PER_BLOCK;
            startNextLeg();
            return true;
        }
        activeWaypoint = null;
        return false;
    }

    private void tickTravelling() {
        BlockPos target = activeWaypoint.pos;
        travelTicks++;

        double distSq = peasant.distanceToSqr(target.getX() + 0.5, target.getY(), target.getZ() + 0.5);
        boolean navDone = peasant.getNavigation().isDone();

        if (distSq <= ARRIVAL_DIST_SQ || (navDone && distSq <= NAV_DONE_ARRIVAL_DIST_SQ)) {
            arrive();
            return;
        }

        if (travelTicks > maxTravelTicks) {
            activeWaypoint = null; // taking too long, move on
            return;
        }

        if (navDone) {
            // Leg finished (or path failed). Only count it as a failed attempt if we didn't get closer.
            if (distSq < lastLegDistSq - 4.0D) {
                repathAttempts = 0;
            } else {
                repathAttempts++;
            }
            lastLegDistSq = distSq;

            if (repathAttempts >= MAX_REPATH_ATTEMPTS) {
                activeWaypoint = null; // unreachable, move on
                return;
            }
            startNextLeg();
        }
    }

    /**
     * Path toward the active waypoint. Navigation can't path further than the follow range,
     * so far targets are approached through intermediate points LEG_LENGTH blocks at a time.
     */
    private boolean startNextLeg() {
        BlockPos target = activeWaypoint.pos;
        double dx = target.getX() + 0.5 - peasant.getX();
        double dz = target.getZ() + 0.5 - peasant.getZ();
        double horizontal = Math.sqrt(dx * dx + dz * dz);

        if (horizontal <= LEG_LENGTH) {
            return moveTo(target);
        }

        // Walk straight towards the destination: the ground point LEG_LENGTH blocks along the line
        int lx = (int) Math.floor(peasant.getX() + dx / horizontal * LEG_LENGTH);
        int lz = (int) Math.floor(peasant.getZ() + dz / horizontal * LEG_LENGTH);
        BlockPos ground = findGround(lx, lz, peasant.blockPosition().getY());
        if (ground != null && moveTo(ground)) return true;

        // Fall back to a random reachable land position biased toward the target
        Vec3 targetVec = Vec3.atBottomCenterOf(target);
        Vec3 leg = LandRandomPos.getPosTowards(peasant, LEG_LENGTH, 8, targetVec);
        if (leg != null && leg.distanceToSqr(targetVec) < peasant.position().distanceToSqr(targetVec)) {
            if (moveTo(BlockPos.containing(leg))) return true;
        }

        // Last resort: let navigation get as close as it can
        return moveTo(target);
    }

    private void arrive() {
        peasant.getNavigation().stop();
        if (activeWaypoint.waitTicks <= 0) {
            activeWaypoint = null;
            return;
        }
        waiting = true;
        waitTicksLeft = activeWaypoint.waitTicks;
        lookAroundCooldown = 0;
    }

    private void tickWaiting() {
        if (--waitTicksLeft <= 0) {
            waiting = false;
            activeWaypoint = null;
            return;
        }

        if (activeWaypoint.lookAt != null) {
            BlockPos l = activeWaypoint.lookAt;
            peasant.getLookControl().setLookAt(l.getX() + 0.5, l.getY() + 0.5, l.getZ() + 0.5);
            return;
        }

        // Keep watch: glance in a new direction every few seconds
        if (--lookAroundCooldown <= 0) {
            RandomSource random = peasant.getRandom();
            double angle = random.nextDouble() * Math.PI * 2;
            lookX = peasant.getX() + Math.cos(angle) * 8;
            lookZ = peasant.getZ() + Math.sin(angle) * 8;
            lookAroundCooldown = 40 + random.nextInt(60);
        }
        peasant.getLookControl().setLookAt(lookX, peasant.getEyeY(), lookZ);
    }

    private boolean moveTo(BlockPos pos) {
        return peasant.getNavigation().moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, WALK_SPEED);
    }

    private void clearObjective() {
        waypoints.clear();
        activeWaypoint = null;
        currentObjective = null;
        waiting = false;
    }

    // ===== OBJECTIVE PLANNING =====

    private void planNextObjective() {
        List<BlockPos> jobBlocks = getOtherJobBlocks();
        currentObjective = pickObjective(!jobBlocks.isEmpty(), canReturnToPost());

        switch (currentObjective) {
            case PATROL -> planPatrol(jobBlocks);
            case VISIT -> planVisit(jobBlocks);
            case STAND_GUARD -> planStandGuard(jobBlocks);
            case WANDER -> planWander();
            case RETURN_POST -> planReturnToPost();
        }

        lastObjective = currentObjective;
    }

    private boolean canReturnToPost() {
        return peasant.level().getGameTime() - lastPostVisitTime >= RETURN_POST_COOLDOWN_TICKS;
    }

    /**
     * Weighted random pick. Never repeats the previous objective, except patrol (the main job).
     * Returning to post is only allowed every few minutes.
     */
    private Objective pickObjective(boolean hasJobBlocks, boolean postAllowed) {
        List<Objective> options = new ArrayList<>();
        int totalWeight = 0;
        for (Objective o : Objective.values()) {
            if (!hasJobBlocks && (o == Objective.PATROL || o == Objective.VISIT)) continue;
            if (o == lastObjective && o != Objective.PATROL) continue;
            if (o == Objective.RETURN_POST && !postAllowed) continue;
            options.add(o);
            totalWeight += o.weight;
        }

        int roll = peasant.getRandom().nextInt(totalWeight);
        for (Objective o : options) {
            roll -= o.weight;
            if (roll < 0) return o;
        }
        return Objective.STAND_GUARD;

    }

    /** Walk a route between several job blocks, circling some of them on the way. */
    private void planPatrol(List<BlockPos> jobBlocks) {
        RandomSource random = peasant.getRandom();
        int stops = Math.min(jobBlocks.size(), PATROL_MIN_STOPS + random.nextInt(PATROL_MAX_STOPS - PATROL_MIN_STOPS + 1));

        // Pick random job blocks, then order them nearest-neighbour so the route flows
        List<BlockPos> pool = new ArrayList<>(jobBlocks);
        List<BlockPos> picked = new ArrayList<>();
        for (int i = 0; i < stops; i++) {
            picked.add(pool.remove(random.nextInt(pool.size())));
        }
        List<BlockPos> route = orderNearestNeighbour(peasant.blockPosition(), picked);

        for (BlockPos jobBlock : route) {
            BlockPos stop = findStandSpot(jobBlock, 2, 4);
            if (stop == null) continue;

            int pause = randomBetween(PATROL_STOP_MIN_TICKS, PATROL_STOP_MAX_TICKS);
            waypoints.add(new Waypoint(stop, pause, null));

            // Half the time, walk around the job block before moving on
            if (random.nextBoolean()) {
                double startAngle = Math.atan2(stop.getZ() - jobBlock.getZ(), stop.getX() - jobBlock.getX());
                int laps = 2 + random.nextInt(2);
                for (int i = 1; i <= laps; i++) {
                    double angle = startAngle + i * (Math.PI * 2 / (laps + 1));
                    BlockPos around = findStandSpotAtAngle(jobBlock, angle, 3 + random.nextInt(2));
                    if (around != null) waypoints.add(new Waypoint(around, 0, null));
                }
            }
        }
    }

    /** Brief visit to one NPC's job block. */
    private void planVisit(List<BlockPos> jobBlocks) {
        BlockPos jobBlock = jobBlocks.get(peasant.getRandom().nextInt(jobBlocks.size()));
        BlockPos spot = findStandSpot(jobBlock, 1, 2);
        if (spot != null) {
            waypoints.add(new Waypoint(spot, randomBetween(VISIT_MIN_TICKS, VISIT_MAX_TICKS), jobBlock));
        }
    }

    /** Stand at a spot for 10 seconds to 1 minute - out in town, not at own post. */
    private void planStandGuard(List<BlockPos> jobBlocks) {
        RandomSource random = peasant.getRandom();
        BlockPos spot = null;

        // Near an NPC's job block, somewhere in town, or right here
        if (!jobBlocks.isEmpty() && random.nextBoolean()) {
            spot = findStandSpot(jobBlocks.get(random.nextInt(jobBlocks.size())), 3, 6);
        }
        if (spot == null) spot = findTownSpotAwayFromPost();
        if (spot == null) spot = peasant.blockPosition();

        waypoints.add(new Waypoint(spot, randomBetween(STAND_GUARD_MIN_TICKS, STAND_GUARD_MAX_TICKS), null));
    }

    /** A few wander stops spread through town; each is picked when the guard sets off for it. */
    private void planWander() {
        int stops = randomBetween(WANDER_MIN_STOPS, WANDER_MAX_STOPS);
        for (int i = 0; i < stops; i++) {
            waypoints.add(new Waypoint(null, randomBetween(0, 40), null));
        }
    }

    /** Walk back to own guard barrel and stay there a little while. */
    private void planReturnToPost() {
        lastPostVisitTime = peasant.level().getGameTime();
        BlockPos post = peasant.getJobBlockPos();
        BlockPos spot = findStandSpot(post, 1, 2);
        if (spot != null) {
            waypoints.add(new Waypoint(spot, randomBetween(POST_MIN_TICKS, POST_MAX_TICKS), null));
        }
    }

    // ===== LOCATION HELPERS =====

    private BlockPos resolveWanderStop() {
        return findTownSpotAwayFromPost();
    }

    /** A standable spot anywhere in town, at least MIN_WANDER_DIST_FROM_POST blocks from own post. */
    private BlockPos findTownSpotAwayFromPost() {
        BlockPos post = peasant.getJobBlockPos();
        for (int attempt = 0; attempt < 16; attempt++) {
            BlockPos point = randomTownPoint();
            if (post != null && horizontalDistSq(point, post) < MIN_WANDER_DIST_FROM_POST * MIN_WANDER_DIST_FROM_POST) continue;
            BlockPos ground = findGround(point.getX(), point.getZ(), peasant.blockPosition().getY());
            if (ground != null) return ground;
        }
        return null;
    }

    /** A random point inside the town area, or around the guard's post if not in a town. */
    private BlockPos randomTownPoint() {
        RandomSource random = peasant.getRandom();
        TownHallBlockEntity townHall = getTownHall();
        BlockPos center;
        int radius;
        if (townHall != null) {
            center = townHall.getBlockPos();
            radius = townHall.getCurrentScanRadius();
        } else {
            center = peasant.getJobBlockPos();
            radius = 48;
        }
        int x = center.getX() + random.nextInt(radius * 2 + 1) - radius;
        int z = center.getZ() + random.nextInt(radius * 2 + 1) - radius;
        return new BlockPos(x, peasant.blockPosition().getY(), z);
    }

    /**
     * A standable position at x/z: first near the given Y (so guards stay on streets, not rooftops),
     * then on the surface.
     */
    private BlockPos findGround(int x, int z, int nearY) {
        for (int dy : new int[]{0, 1, -1, 2, -2, 3, -3, 4, -4}) {
            BlockPos pos = new BlockPos(x, nearY + dy, z);
            if (isStandable(pos)) return pos;
        }
        BlockPos surface = new BlockPos(x, peasant.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z), z);
        return isStandable(surface) ? surface : null;
    }

    private static double horizontalDistSq(BlockPos a, BlockPos b) {
        double dx = a.getX() - b.getX();
        double dz = a.getZ() - b.getZ();
        return dx * dx + dz * dz;
    }

    /** Job blocks of other NPCs in this guard's town (excludes own post). */
    private List<BlockPos> getOtherJobBlocks() {
        TownHallBlockEntity townHall = getTownHall();
        if (townHall == null) return new ArrayList<>();
        List<BlockPos> blocks = townHall.getJobBlockPositions();
        blocks.remove(peasant.getJobBlockPos());
        return blocks;
    }

    private TownHallBlockEntity getTownHall() {
        return peasant.getTownHallPos().map(pos -> {
            if (!peasant.level().isLoaded(pos)) return null;
            BlockEntity be = peasant.level().getBlockEntity(pos);
            return be instanceof TownHallBlockEntity th ? th : null;
        }).orElse(null);
    }

    private static List<BlockPos> orderNearestNeighbour(BlockPos start, List<BlockPos> points) {
        List<BlockPos> remaining = new ArrayList<>(points);
        List<BlockPos> ordered = new ArrayList<>();
        BlockPos current = start;
        while (!remaining.isEmpty()) {
            BlockPos nearest = remaining.get(0);
            for (BlockPos p : remaining) {
                if (p.distSqr(current) < nearest.distSqr(current)) nearest = p;
            }
            remaining.remove(nearest);
            ordered.add(nearest);
            current = nearest;
        }
        return ordered;
    }

    /** A spot a guard can stand on, at a random direction and distance from center. */
    private BlockPos findStandSpot(BlockPos center, int minRadius, int maxRadius) {
        RandomSource random = peasant.getRandom();
        for (int attempt = 0; attempt < 12; attempt++) {
            double angle = random.nextDouble() * Math.PI * 2;
            int radius = randomBetween(minRadius, maxRadius);
            BlockPos spot = findStandSpotAtAngle(center, angle, radius);
            if (spot != null) return spot;
        }
        return null;
    }

    private BlockPos findStandSpotAtAngle(BlockPos center, double angle, int radius) {
        int x = center.getX() + (int) Math.round(Math.cos(angle) * radius);
        int z = center.getZ() + (int) Math.round(Math.sin(angle) * radius);
        for (int dy : new int[]{0, 1, -1, 2, -2, 3, -3}) {
            BlockPos pos = new BlockPos(x, center.getY() + dy, z);
            if (isStandable(pos)) return pos;
        }
        return null;
    }

    private boolean isStandable(BlockPos pos) {
        Level level = peasant.level();
        if (!level.isLoaded(pos)) return false;
        BlockPos below = pos.below();
        return level.getBlockState(below).isFaceSturdy(level, below, Direction.UP)
                && level.getBlockState(pos).getCollisionShape(level, pos).isEmpty()
                && level.getBlockState(pos.above()).getCollisionShape(level, pos.above()).isEmpty()
                && level.getFluidState(pos).isEmpty();
    }

    private int randomBetween(int min, int max) {
        return min + peasant.getRandom().nextInt(max - min + 1);
    }

}

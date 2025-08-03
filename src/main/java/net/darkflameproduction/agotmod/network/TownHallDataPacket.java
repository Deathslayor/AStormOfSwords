package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record TownHallDataPacket(
        BlockPos pos,
        int bedCount,
        int citizenCount,
        int currentRadius,
        String townName,
        boolean isClaimed,
        String claimedByHouse,
        int availableJobCount,
        int assignedJobCount,
        int totalJobCount,
        int joblessCount
) implements CustomPacketPayload {

    public static final Type<TownHallDataPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_hall_data")
    );

    // Custom StreamCodec implementation for more than 8 fields
    public static final StreamCodec<FriendlyByteBuf, TownHallDataPacket> STREAM_CODEC = StreamCodec.of(
            TownHallDataPacket::encode,
            TownHallDataPacket::decode
    );

    // Custom encode method
    private static void encode(FriendlyByteBuf buf, TownHallDataPacket packet) {
        // Write BlockPos
        buf.writeBlockPos(packet.pos);

        // Write integers
        buf.writeVarInt(packet.bedCount);
        buf.writeVarInt(packet.citizenCount);
        buf.writeVarInt(packet.currentRadius);

        // Write strings
        buf.writeUtf(packet.townName);
        buf.writeUtf(packet.claimedByHouse);

        // Write boolean
        buf.writeBoolean(packet.isClaimed);

        // Write job management data
        buf.writeVarInt(packet.availableJobCount);
        buf.writeVarInt(packet.assignedJobCount);
        buf.writeVarInt(packet.totalJobCount);
        buf.writeVarInt(packet.joblessCount);
    }

    // Custom decode method
    private static TownHallDataPacket decode(FriendlyByteBuf buf) {
        // Read BlockPos
        BlockPos pos = buf.readBlockPos();

        // Read integers
        int bedCount = buf.readVarInt();
        int citizenCount = buf.readVarInt();
        int currentRadius = buf.readVarInt();

        // Read strings
        String townName = buf.readUtf();
        String claimedByHouse = buf.readUtf();

        // Read boolean
        boolean isClaimed = buf.readBoolean();

        // Read job management data
        int availableJobCount = buf.readVarInt();
        int assignedJobCount = buf.readVarInt();
        int totalJobCount = buf.readVarInt();
        int joblessCount = buf.readVarInt();

        return new TownHallDataPacket(
                pos, bedCount, citizenCount, currentRadius,
                townName, isClaimed, claimedByHouse,
                availableJobCount, assignedJobCount, totalJobCount, joblessCount
        );
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // ===== CONVENIENCE CONSTRUCTORS =====

    /**
     * Constructor for backwards compatibility (without job data)
     */
    public static TownHallDataPacket createBasic(BlockPos pos, int bedCount, int citizenCount,
                                                 int currentRadius, String townName,
                                                 boolean isClaimed, String claimedByHouse) {
        return new TownHallDataPacket(pos, bedCount, citizenCount, currentRadius,
                townName, isClaimed, claimedByHouse,
                0, 0, 0, 0);
    }

    /**
     * Constructor with all job management data
     */
    public static TownHallDataPacket createComplete(BlockPos pos, int bedCount, int citizenCount,
                                                    int currentRadius, String townName,
                                                    boolean isClaimed, String claimedByHouse,
                                                    int availableJobCount, int assignedJobCount,
                                                    int totalJobCount, int joblessCount) {
        return new TownHallDataPacket(pos, bedCount, citizenCount, currentRadius,
                townName, isClaimed, claimedByHouse,
                availableJobCount, assignedJobCount, totalJobCount, joblessCount);
    }

    // ===== UTILITY METHODS =====

    /**
     * Calculates employment rate as a percentage
     */
    public int getEmploymentRate() {
        if (totalJobCount == 0) return 0;
        return (assignedJobCount * 100) / totalJobCount;
    }

    /**
     * Gets unemployment rate as a percentage of total citizens
     */
    public int getUnemploymentRate() {
        if (citizenCount == 0) return 0;
        return (joblessCount * 100) / citizenCount;
    }

    /**
     * Checks if there are available jobs for unemployed citizens
     */
    public boolean hasAvailableJobs() {
        return availableJobCount > 0;
    }

    /**
     * Checks if there are unemployed citizens
     */
    public boolean hasUnemployedCitizens() {
        return joblessCount > 0;
    }

    /**
     * Gets job utilization rate (how many jobs are being used)
     */
    public int getJobUtilizationRate() {
        if (totalJobCount == 0) return 0;
        return (assignedJobCount * 100) / totalJobCount;
    }

    /**
     * Checks if the job market is balanced (no unemployment and good job utilization)
     */
    public boolean isJobMarketBalanced() {
        return joblessCount == 0 && getJobUtilizationRate() >= 80;
    }

    /**
     * Gets a status string for the job market
     */
    public String getJobMarketStatus() {
        if (totalJobCount == 0) {
            return "No Jobs Available";
        }

        if (joblessCount == 0 && availableJobCount == 0) {
            return "Full Employment";
        }

        if (joblessCount > 0 && availableJobCount > 0) {
            return "Jobs Available";
        }

        if (joblessCount > 0 && availableJobCount == 0) {
            return "Job Shortage";
        }

        if (joblessCount == 0 && availableJobCount > 0) {
            return "Labor Shortage";
        }

        return "Stable";
    }

    // ===== DATA VALIDATION =====

    /**
     * Validates that the packet data is consistent
     */
    public boolean isDataValid() {
        // Basic validation checks
        if (bedCount < 0 || citizenCount < 0 || currentRadius < 0) {
            return false;
        }

        if (availableJobCount < 0 || assignedJobCount < 0 || totalJobCount < 0 || joblessCount < 0) {
            return false;
        }

        // Job data consistency checks
        if (availableJobCount + assignedJobCount != totalJobCount) {
            return false;
        }

        // Logical consistency checks
        if (joblessCount > citizenCount) {
            return false;
        }

        return true;
    }

    // ===== STRING REPRESENTATION =====

    @Override
    public String toString() {
        return String.format(
                "TownHallDataPacket{pos=%s, townName='%s', citizens=%d, beds=%d, radius=%d, " +
                        "claimed=%s, jobs=[total=%d, available=%d, assigned=%d], unemployed=%d}",
                pos, townName, citizenCount, bedCount, currentRadius,
                isClaimed ? claimedByHouse : "unclaimed",
                totalJobCount, availableJobCount, assignedJobCount, joblessCount
        );
    }
}
package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        int joblessCount,
        long townBalance,
        long townIncome,
        Map<String, Long> townInventory,
        String culture,
        List<CitizenEntry> citizens,
        List<CraftingNeed> craftingNeeds,
        Earnings earnings
) implements CustomPacketPayload {

    /** A citizen of the town: its name and job type (JobSystem.JOB_*, "" for none). */
    public record CitizenEntry(String name, String jobType) {}

    /** Something the town's blacksmith still has to make: have / want; label names requested tools. */
    /**
     * One row of the Crafting tab: {@code have} of {@code want} of an item in the town hall. {@code jobs} are the
     * jobs that make it ("tailor/blacksmith"), {@code hasWorker} whether the town has a citizen with one of them.
     * {@code trade}: a basic good the town's jobs need (TownDemand) that its trader buys rather than anyone crafts.
     * {@code sell}: on the sell list - the town holds {@code have} but may hold only {@code want} (TownSurplus).
     */
    public record CraftingNeed(String itemKey, String label, String jobs, int have, int want,
                               boolean toolRequest, boolean hasWorker, boolean trade, boolean sell) {
        public CraftingNeed(String itemKey, String label, String jobs, int have, int want,
                            boolean toolRequest, boolean hasWorker) {
            this(itemKey, label, jobs, have, want, toolRequest, hasWorker, false, false);
        }
    }

    /**
     * What the town has earned today and yesterday, what its trader may spend today (TraderPurchasing), and its
     * money in and out today and yesterday: "FLOW|job" (CashFlow and the citizen's job, empty if none) -> coins.
     */
    public record Earnings(long today, long yesterday, long traderBudget,
                           Map<String, Long> flowsToday, Map<String, Long> flowsYesterday) {}

    public static final Type<TownHallDataPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_hall_data")
    );

    public static final StreamCodec<FriendlyByteBuf, TownHallDataPacket> STREAM_CODEC = StreamCodec.of(
            TownHallDataPacket::encode,
            TownHallDataPacket::decode
    );

    private static void encode(FriendlyByteBuf buf, TownHallDataPacket packet) {
        buf.writeBlockPos(packet.pos);
        buf.writeVarInt(packet.bedCount);
        buf.writeVarInt(packet.citizenCount);
        buf.writeVarInt(packet.currentRadius);
        buf.writeUtf(packet.townName);
        buf.writeUtf(packet.claimedByHouse);
        buf.writeBoolean(packet.isClaimed);
        buf.writeVarInt(packet.availableJobCount);
        buf.writeVarInt(packet.assignedJobCount);
        buf.writeVarInt(packet.totalJobCount);
        buf.writeVarInt(packet.joblessCount);
        buf.writeLong(packet.townBalance);
        buf.writeLong(packet.townIncome);
        buf.writeVarInt(packet.townInventory.size());
        for (Map.Entry<String, Long> entry : packet.townInventory.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeLong(entry.getValue());
        }
        buf.writeUtf(packet.culture);
        buf.writeVarInt(packet.citizens.size());
        for (CitizenEntry citizen : packet.citizens) {
            buf.writeUtf(citizen.name());
            buf.writeUtf(citizen.jobType());
        }
        buf.writeVarInt(packet.craftingNeeds.size());
        for (CraftingNeed need : packet.craftingNeeds) {
            buf.writeUtf(need.itemKey());
            buf.writeUtf(need.label());
            buf.writeUtf(need.jobs());
            buf.writeVarInt(need.have());
            buf.writeVarInt(need.want());
            buf.writeBoolean(need.toolRequest());
            buf.writeBoolean(need.hasWorker());
            buf.writeBoolean(need.trade());
            buf.writeBoolean(need.sell());
        }
        buf.writeLong(packet.earnings.today());
        buf.writeLong(packet.earnings.yesterday());
        buf.writeLong(packet.earnings.traderBudget());
        writeFlows(buf, packet.earnings.flowsToday());
        writeFlows(buf, packet.earnings.flowsYesterday());
    }

    private static void writeFlows(FriendlyByteBuf buf, Map<String, Long> flows) {
        buf.writeVarInt(flows.size());
        for (Map.Entry<String, Long> entry : flows.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeLong(entry.getValue());
        }
    }

    private static Map<String, Long> readFlows(FriendlyByteBuf buf) {
        int size = buf.readVarInt();
        Map<String, Long> flows = new HashMap<>(size);
        for (int i = 0; i < size; i++) flows.put(buf.readUtf(), buf.readLong());
        return flows;
    }

    private static TownHallDataPacket decode(FriendlyByteBuf buf) {
        BlockPos pos          = buf.readBlockPos();
        int bedCount          = buf.readVarInt();
        int citizenCount      = buf.readVarInt();
        int currentRadius     = buf.readVarInt();
        String townName       = buf.readUtf();
        String claimedByHouse = buf.readUtf();
        boolean isClaimed     = buf.readBoolean();
        int availableJobCount = buf.readVarInt();
        int assignedJobCount  = buf.readVarInt();
        int totalJobCount     = buf.readVarInt();
        int joblessCount      = buf.readVarInt();
        long townBalance      = buf.readLong();
        long townIncome       = buf.readLong();
        int inventorySize     = buf.readVarInt();
        Map<String, Long> townInventory = new HashMap<>();
        for (int i = 0; i < inventorySize; i++) {
            townInventory.put(buf.readUtf(), buf.readLong());
        }
        String culture = buf.readUtf();
        int citizenListSize = buf.readVarInt();
        List<CitizenEntry> citizens = new ArrayList<>(citizenListSize);
        for (int i = 0; i < citizenListSize; i++) {
            citizens.add(new CitizenEntry(buf.readUtf(), buf.readUtf()));
        }
        int needCount = buf.readVarInt();
        List<CraftingNeed> craftingNeeds = new ArrayList<>(needCount);
        for (int i = 0; i < needCount; i++) {
            craftingNeeds.add(new CraftingNeed(buf.readUtf(), buf.readUtf(), buf.readUtf(), buf.readVarInt(),
                    buf.readVarInt(), buf.readBoolean(), buf.readBoolean(), buf.readBoolean(), buf.readBoolean()));
        }

        return new TownHallDataPacket(
                pos, bedCount, citizenCount, currentRadius,
                townName, isClaimed, claimedByHouse,
                availableJobCount, assignedJobCount, totalJobCount, joblessCount,
                townBalance, townIncome, townInventory, culture, citizens, craftingNeeds,
                new Earnings(buf.readLong(), buf.readLong(), buf.readLong(), readFlows(buf), readFlows(buf))
        );
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

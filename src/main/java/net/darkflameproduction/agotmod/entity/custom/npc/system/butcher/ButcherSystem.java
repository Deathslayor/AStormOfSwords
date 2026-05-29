package net.darkflameproduction.agotmod.entity.custom.npc.system.butcher;

import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.*;

public class ButcherSystem {
    private final Peasant_Entity peasant;

    public ButcherSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (peasant.level().isClientSide) return;
        if (!peasant.getJobType().equals(JobSystem.JOB_BUTCHER) ||
                peasant.getJobBlockPos() == null) return;
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (!peasant.level().getBlockState(jobBlockPos).is(ModBLocks.BUTCHER_BARREL.get())) return;
    }

    private TownHallBlockEntity getRegisteredTownHall() {
        if (peasant.isRegisteredToTownHall()) {
            Optional<BlockPos> townHallPos = peasant.getTownHallPos();
            if (townHallPos.isPresent()) {
                BlockEntity blockEntity = peasant.level().getBlockEntity(townHallPos.get());
                if (blockEntity instanceof TownHallBlockEntity townHall) return townHall;
            }
        }

        BlockPos myPos = peasant.blockPosition();
        int searchRadius = 128;
        for (int x = -searchRadius; x <= searchRadius; x += 16) {
            for (int y = -64; y <= 64; y += 16) {
                for (int z = -searchRadius; z <= searchRadius; z += 16) {
                    BlockPos checkPos = myPos.offset(x, y, z);
                    if (peasant.level().isLoaded(checkPos)) {
                        BlockEntity blockEntity = peasant.level().getBlockEntity(checkPos);
                        if (blockEntity instanceof TownHallBlockEntity townHall) {
                            if (townHall.isWithinStableScanArea(myPos, townHall.getCurrentScanRadius())) {
                                peasant.registerWithTownHall(checkPos);
                                return townHall;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public boolean hasItem(String itemKey, long amount) {
        TownHallBlockEntity townHall = getRegisteredTownHall();
        if (townHall == null) return false;
        return townHall.hasTownInventoryItem(itemKey, amount);
    }

    public boolean removeItem(String itemKey, long amount) {
        TownHallBlockEntity townHall = getRegisteredTownHall();
        if (townHall == null) return false;
        return townHall.removeFromTownInventory(itemKey, amount);
    }

    public long getItemAmount(String itemKey) {
        TownHallBlockEntity townHall = getRegisteredTownHall();
        if (townHall == null) return 0;
        return townHall.getTownInventoryAmount(itemKey);
    }

    public List<ButcherInventoryEntry> getSortedInventoryEntries() {
        TownHallBlockEntity townHall = getRegisteredTownHall();
        if (townHall == null) return Collections.emptyList();

        Map<String, Long> inventory = townHall.getTownInventory();
        List<ButcherInventoryEntry> entries = new ArrayList<>();

        for (Map.Entry<String, Long> entry : inventory.entrySet()) {
            String itemKey = entry.getKey();
            long amount = entry.getValue();
            if (amount <= 0) continue;
            if (!net.darkflameproduction.agotmod.util.ItemPricing.isButcherItem(itemKey)) continue;

            ResourceLocation itemLocation = ResourceLocation.tryParse(itemKey);
            if (itemLocation == null) continue;

            Item item = BuiltInRegistries.ITEM.get(itemLocation);
            if (item == null || item == net.minecraft.world.item.Items.AIR) continue;

            String displayName = new ItemStack(item).getHoverName().getString();
            entries.add(new ButcherInventoryEntry(displayName, amount, itemKey));
        }

        entries.sort(Comparator.comparing(e -> e.displayName));
        return entries;
    }

    public void refreshInventoryDisplay() {
        TownHallBlockEntity townHall = getRegisteredTownHall();
        if (townHall != null) townHall.cleanupTownInventory();
    }

    public static class ButcherInventoryEntry {
        public final String displayName;
        public final long amount;
        public final String itemKey;

        public ButcherInventoryEntry(String displayName, long amount, String itemKey) {
            this.displayName = displayName;
            this.amount = amount;
            this.itemKey = itemKey;
        }
    }

    public void onWakeUp() {}

    public void onRemove() {}

    public void saveData(CompoundTag compound) {}

    public void loadData(CompoundTag compound) {}
}

package net.darkflameproduction.agotmod.entity.custom.npc.system.grocer;

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

public class GrocerSystem {
    private final Peasant_Entity peasant;

    public GrocerSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (peasant.level().isClientSide) return;
        if (!peasant.getJobType().equals(JobSystem.JOB_GROCER) ||
                peasant.getJobBlockPos() == null) return;
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (!peasant.level().getBlockState(jobBlockPos).is(ModBLocks.GROCER_BARREL.get())) return;
    }

    // ===== INVENTORY ACCESS - delegates to town hall =====

    private TownHallBlockEntity getRegisteredTownHall() {
        // First try registered town hall
        if (peasant.isRegisteredToTownHall()) {
            Optional<BlockPos> townHallPos = peasant.getTownHallPos();
            if (townHallPos.isPresent()) {
                BlockEntity blockEntity = peasant.level().getBlockEntity(townHallPos.get());
                if (blockEntity instanceof TownHallBlockEntity townHall) return townHall;
            }
        }

        // Fallback: scan nearby chunks for a town hall
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
                                // Cache the result
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

    public List<GrocerInventoryEntry> getSortedInventoryEntries() {
        TownHallBlockEntity townHall = getRegisteredTownHall();
        if (townHall == null) return Collections.emptyList();

        Map<String, Long> inventory = townHall.getTownInventory();
        List<GrocerInventoryEntry> entries = new ArrayList<>();

        for (Map.Entry<String, Long> entry : inventory.entrySet()) {
            String itemKey = entry.getKey();
            long amount = entry.getValue();
            if (amount <= 0) continue;

            ResourceLocation itemLocation = ResourceLocation.tryParse(itemKey);
            if (itemLocation == null) continue;

            Item item = BuiltInRegistries.ITEM.get(itemLocation);
            if (item == null || item == net.minecraft.world.item.Items.AIR) continue;

            String displayName = new ItemStack(item).getHoverName().getString();
            entries.add(new GrocerInventoryEntry(displayName, amount, itemKey));
        }

        entries.sort(Comparator.comparing(e -> e.displayName));
        return entries;
    }

    public void refreshInventoryDisplay() {
        TownHallBlockEntity townHall = getRegisteredTownHall();
        if (townHall != null) townHall.cleanupTownInventory();
    }

    // ===== HELPER CLASS FOR GUI =====

    public static class GrocerInventoryEntry {
        public final String displayName;
        public final long amount;
        public final String itemKey;

        public GrocerInventoryEntry(String displayName, long amount, String itemKey) {
            this.displayName = displayName;
            this.amount = amount;
            this.itemKey = itemKey;
        }
    }

    // ===== LIFECYCLE =====

    public void onWakeUp() {}

    public void onRemove() {}

    // ===== SAVE / LOAD =====

    public void saveData(CompoundTag compound) {}

    public void loadData(CompoundTag compound) {}
}

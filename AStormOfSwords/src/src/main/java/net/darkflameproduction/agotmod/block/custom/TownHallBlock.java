package net.darkflameproduction.agotmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.network.PacketDistributor;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.network.OpenTownHallMenuPacket;
import org.jetbrains.annotations.Nullable;

public class TownHallBlock extends Block implements EntityBlock {

    public TownHallBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                            Player player, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof TownHallBlockEntity townHallBE) {
                if (player instanceof ServerPlayer serverPlayer) {
                    // Send packet to open Town Hall GUI on client side
                    PacketDistributor.sendToPlayer(serverPlayer,
                            new OpenTownHallMenuPacket(pos));

                    // Send current data to the player with dynamic radius, town name, and claim info
                    PacketDistributor.sendToPlayer(serverPlayer,
                            new TownHallDataPacket(
                                    pos,
                                    townHallBE.getBedCount(),
                                    townHallBE.getCitizenCount(),
                                    townHallBE.getCurrentScanRadius(),
                                    townHallBE.getTownName(),
                                    townHallBE.isClaimed(),
                                    townHallBE.getClaimedByHouse(),
                                    townHallBE.getClaimedByHouseBanner()  // NEW: Add banner
                            ));
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TownHallBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) {
            return null;
        }

        if (blockEntityType == ModBlockEntities.TOWN_HALL.get()) {
            return (level1, pos, state1, blockEntity) -> {
                if (blockEntity instanceof TownHallBlockEntity townHallBE) {
                    TownHallBlockEntity.tick(level1, pos, state1, townHallBE);
                }
            };
        }

        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
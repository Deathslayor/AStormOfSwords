package net.darkflameproduction.agotmod.client;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, AGoTMod.MOD_ID);

    public static final Supplier<AttachmentType<PlayerCoinData>> PLAYER_COIN_DATA =
            ATTACHMENT_TYPES.register("player_coin_data", () ->
                    AttachmentType.builder(() -> new PlayerCoinData())
                            .serialize(new PlayerCoinData.Serializer())
                            .build()
            );
}
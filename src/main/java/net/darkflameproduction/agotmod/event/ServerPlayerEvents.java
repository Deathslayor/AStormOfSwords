package net.darkflameproduction.agotmod.event;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.network.ServerHouseHandler;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ServerPlayerEvents {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            // Small delay via server tick to ensure persistent data is fully loaded
            serverPlayer.getServer().execute(() ->
                    ServerHouseHandler.onPlayerJoin(serverPlayer));
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            ServerHouseHandler.onPlayerLeave(serverPlayer);
        }
    }
}

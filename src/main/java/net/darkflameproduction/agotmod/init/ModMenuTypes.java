package net.darkflameproduction.agotmod.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.inventory.NPCInventoryMenu;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(BuiltInRegistries.MENU, AGoTMod.MOD_ID);

    public static final Supplier<MenuType<NPCInventoryMenu>> NPC_INVENTORY_MENU =
            MENU_TYPES.register("npc_inventory", () ->
                    IMenuTypeExtension.create((windowId, inv, data) -> {
                        // The data should contain the NPC entity ID
                        int entityId = data.readInt();
                        if (inv.player.level().getEntity(entityId) instanceof net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity npc) {
                            return new NPCInventoryMenu(windowId, inv, npc);
                        }
                        return null;
                    }));

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
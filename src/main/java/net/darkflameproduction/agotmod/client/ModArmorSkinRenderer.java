package net.darkflameproduction.agotmod.client;

import net.darkflameproduction.agotmod.armor.custom.GeoArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

/**
 * The 3D armour sets are modelled over the skin, so the player's outer skin layer (hat, jacket,
 * sleeves, pants) is hidden under them - see PlayerRendererMixin.
 */
public class ModArmorSkinRenderer {

    /** True if the player wears a piece of 3D armour (any GeoArmorItem) in this slot. */
    public static boolean isWearing3dArmor(Player player, EquipmentSlot slot) {
        return player.getItemBySlot(slot).getItem() instanceof GeoArmorItem;
    }
}

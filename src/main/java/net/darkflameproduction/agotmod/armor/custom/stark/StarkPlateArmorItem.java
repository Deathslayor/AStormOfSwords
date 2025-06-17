// This code belongs to the package net.stormofsorts.agotmod.custom
package net.darkflameproduction.agotmod.armor.custom.stark;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.armor.client.stark.StarkPlateArmorRenderer;
import net.darkflameproduction.agotmod.armor.custom.ASOSArmorItem;
import net.minecraft.world.item.Item;

// Stark1ArmorItem class extending ArmorItem and implementing GeoItem
public class StarkPlateArmorItem extends ASOSArmorItem {

    public StarkPlateArmorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    protected ASOSArmorRenderer<?> createArmorRenderer() {
        return new StarkPlateArmorRenderer<>();
    }
}
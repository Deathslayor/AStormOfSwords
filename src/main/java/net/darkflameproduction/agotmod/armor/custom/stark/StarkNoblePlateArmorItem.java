// This code belongs to the package net.stormofsorts.agotmod.custom
package net.darkflameproduction.agotmod.armor.custom.stark;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.armor.client.stark.StarkNoblePLateArmorRenderer;
import net.darkflameproduction.agotmod.armor.custom.ASOSArmorItem;

// Stark1ArmorItem class extending ArmorItem and implementing GeoItem
public class StarkNoblePlateArmorItem extends ASOSArmorItem {

    public StarkNoblePlateArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ASOSArmorRenderer<?> createArmorRenderer() {
        return new StarkNoblePLateArmorRenderer<>();
    }
}
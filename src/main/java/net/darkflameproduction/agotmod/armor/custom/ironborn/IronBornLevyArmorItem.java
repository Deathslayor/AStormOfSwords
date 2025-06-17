// This code belongs to the package net.stormofsorts.agotmod.custom
package net.darkflameproduction.agotmod.armor.custom.ironborn;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.armor.client.ironborn.IronBornLevyArmorRenderer;
import net.darkflameproduction.agotmod.armor.custom.ASOSArmorItem;

// Stark1ArmorItem class extending ArmorItem and implementing GeoItem
public class IronBornLevyArmorItem extends ASOSArmorItem {

    public IronBornLevyArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ASOSArmorRenderer<?> createArmorRenderer() {
        return new IronBornLevyArmorRenderer<>();
    }
}
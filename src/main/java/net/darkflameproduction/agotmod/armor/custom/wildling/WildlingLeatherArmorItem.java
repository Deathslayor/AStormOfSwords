// This code belongs to the package net.stormofsorts.agotmod.custom
package net.darkflameproduction.agotmod.armor.custom.wildling;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.armor.client.wildling.WildlingLeatherArmorRenderer;
import net.darkflameproduction.agotmod.armor.custom.ASOSArmorItem;

// Stark1ArmorItem class extending ArmorItem and implementing GeoItem
public class WildlingLeatherArmorItem extends ASOSArmorItem {

    public WildlingLeatherArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ASOSArmorRenderer<?> createArmorRenderer() {
        return new WildlingLeatherArmorRenderer<>();
    }
}
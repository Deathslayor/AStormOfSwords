// This code belongs to the package net.stormofsorts.agotmod.custom
package net.darkflameproduction.agotmod.armor.custom.night_watch;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.armor.client.night_watch.NightsWatchRangerArmorRenderer;
import net.darkflameproduction.agotmod.armor.custom.ASOSArmorItem;

// Stark1ArmorItem class extending ArmorItem and implementing GeoItem
public class NightsWatchRangerArmorItem extends ASOSArmorItem {

    public NightsWatchRangerArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ASOSArmorRenderer<?> createArmorRenderer() {
        return new NightsWatchRangerArmorRenderer<>();
    }
}
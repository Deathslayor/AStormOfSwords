package net.darkflameproduction.agotmod.armor.client.night_watch;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class NightsWatchLeatherArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public NightsWatchLeatherArmorRenderer() {
        super(new ASOSArmorModel("nights_watch_leather"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.NIGHT_WATCH_LEATHER_HELMET.asItem(), ModItems.NIGHT_WATCH_LEATHER_CHESTPLATE.asItem(), ModItems.NIGHT_WATCH_LEATHER_LEGGINGS.asItem(), ModItems.NIGHT_WATCH_LEATHER_BOOTS.asItem());
    }
}
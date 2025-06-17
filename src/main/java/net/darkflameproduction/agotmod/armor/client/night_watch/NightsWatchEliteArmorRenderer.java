package net.darkflameproduction.agotmod.armor.client.night_watch;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class NightsWatchEliteArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public NightsWatchEliteArmorRenderer() {
        super(new ASOSArmorModel("nights_watch_elite"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.NIGHT_WATCH_ELITE_HELMET.asItem(), ModItems.NIGHT_WATCH_ELITE_CHESTPLATE.asItem(), ModItems.NIGHT_WATCH_ELITE_LEGGINGS.asItem(), ModItems.NIGHT_WATCH_ELITE_BOOTS.asItem());
    }
}
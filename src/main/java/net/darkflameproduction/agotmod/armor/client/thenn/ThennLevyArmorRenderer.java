package net.darkflameproduction.agotmod.armor.client.thenn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ThennLevyArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public ThennLevyArmorRenderer() {
        super(new ASOSArmorModel("thenn_levy"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.THENN_LEVY_HELMET.asItem(), ModItems.THENN_LEVY_CHESTPLATE.asItem(), ModItems.THENN_LEVY_LEGGINGS.asItem(), ModItems.THENN_LEVY_BOOTS.asItem());
    }
}
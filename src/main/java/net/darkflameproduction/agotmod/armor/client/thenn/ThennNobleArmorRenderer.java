package net.darkflameproduction.agotmod.armor.client.thenn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ThennNobleArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public ThennNobleArmorRenderer() {
        super(new ASOSArmorModel("thenn_noble"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.THENN_NOBLE_HELMET.asItem(), ModItems.THENN_NOBLE_CHESTPLATE.asItem(), ModItems.THENN_NOBLE_LEGGINGS.asItem(), ModItems.THENN_NOBLE_BOOTS.asItem());
    }
}
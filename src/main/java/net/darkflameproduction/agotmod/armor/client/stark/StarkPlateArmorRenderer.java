package net.darkflameproduction.agotmod.armor.client.stark;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class StarkPlateArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public StarkPlateArmorRenderer() {
        super(new ASOSArmorModel("stark_plate"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.STARK_PLATE_HELMET.asItem(), ModItems.STARK_PLATE_CHESTPLATE.asItem(), ModItems.STARK_PLATE_LEGGINGS.asItem(), ModItems.STARK_PLATE_BOOTS.asItem());
    }
}
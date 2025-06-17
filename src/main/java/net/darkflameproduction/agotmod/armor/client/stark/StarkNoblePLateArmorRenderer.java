package net.darkflameproduction.agotmod.armor.client.stark;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class StarkNoblePLateArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public StarkNoblePLateArmorRenderer() {
        super(new ASOSArmorModel("stark_noble_plate"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.STARK_NOBLE_PLATE_HELMET.asItem(), ModItems.STARK_NOBLE_PLATE_HELMET.asItem(), ModItems.STARK_NOBLE_PLATE_HELMET.asItem(), ModItems.STARK_NOBLE_PLATE_HELMET.asItem());
    }
}
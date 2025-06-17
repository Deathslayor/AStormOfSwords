package net.darkflameproduction.agotmod.armor.client.ironborn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

public class IronBornPlateArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public IronBornPlateArmorRenderer() {
        super(new ASOSArmorModel("ironborn_plate"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.IRONBORN_PLATE_HELMET.asItem(), ModItems.IRONBORN_PLATE_CHESTPLATE.asItem(), ModItems.IRONBORN_PLATE_LEGGINGS.asItem(), ModItems.IRONBORN_PLATE_BOOTS.asItem());
    }
}

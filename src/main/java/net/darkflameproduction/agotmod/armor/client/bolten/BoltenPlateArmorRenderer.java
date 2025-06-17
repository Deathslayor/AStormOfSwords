package net.darkflameproduction.agotmod.armor.client.bolten;

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

public class BoltenPlateArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public BoltenPlateArmorRenderer() {
        super(new ASOSArmorModel("bolten_plate"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.BOLTEN_PLATE_HELMET.asItem(), ModItems.BOLTEN_PLATE_CHESTPLATE.asItem(), ModItems.BOLTEN_PLATE_LEGGINGS.asItem(), ModItems.BOLTEN_PLATE_BOOTS.asItem());
    }
}

package net.darkflameproduction.agotmod.armor.client.bolten;

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

public class BoltenNobleArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public BoltenNobleArmorRenderer() {
        super(new ASOSArmorModel("bolten_noble"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.BOLTEN_NOBLE_HELMET.asItem(), ModItems.BOLTEN_NOBLE_CHESTPLATE.asItem(), ModItems.BOLTEN_NOBLE_LEGGINGS.asItem(), ModItems.BOLTEN_NOBLE_BOOTS.asItem());
    }
}

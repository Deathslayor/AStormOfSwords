package net.darkflameproduction.agotmod.armor.client.bolten;

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

public class BoltenLevyArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public BoltenLevyArmorRenderer() {
        super(new ASOSArmorModel("bolten_levy"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.BOLTEN_LEVY_HELMET.asItem(), ModItems.BOLTEN_LEVY_CHESTPLATE.asItem(), ModItems.BOLTEN_LEVY_LEGGINGS.asItem(), ModItems.BOLTEN_LEVY_BOOTS.asItem());
    }
}

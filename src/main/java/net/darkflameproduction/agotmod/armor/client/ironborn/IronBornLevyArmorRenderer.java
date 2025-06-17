package net.darkflameproduction.agotmod.armor.client.ironborn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

public class IronBornLevyArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public IronBornLevyArmorRenderer() {
        super(new ASOSArmorModel("ironborn_levy"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.IRONBORN_LEVY_HELMET.asItem(), ModItems.IRONBORN_LEVY_CHESTPLATE.asItem(), ModItems.IRONBORN_LEVY_LEGGINGS.asItem(), ModItems.IRONBORN_LEVY_BOOTS.asItem());
    }
}

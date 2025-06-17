package net.darkflameproduction.agotmod.armor.client.manderly;

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ManderlyLevyArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public ManderlyLevyArmorRenderer() {
        super(new ASOSArmorModel("manderly_levy"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.MANDERLY_LEVY_HELMET.asItem(), ModItems.MANDERLY_LEVY_CHESTPLATE.asItem(), ModItems.MANDERLY_LEVY_LEGGINGS.asItem(), ModItems.MANDERLY_LEVY_BOOTS.asItem());
    }
}
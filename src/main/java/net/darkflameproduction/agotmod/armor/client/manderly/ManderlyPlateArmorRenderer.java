package net.darkflameproduction.agotmod.armor.client.manderly;

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ManderlyPlateArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public ManderlyPlateArmorRenderer() {
        super(new ASOSArmorModel("manderly_plate"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.MANDERLY_PLATE_HELMET.asItem(), ModItems.MANDERLY_PLATE_CHESTPLATE.asItem(), ModItems.MANDERLY_PLATE_LEGGINGS.asItem(), ModItems.MANDERLY_PLATE_BOOTS.asItem());
    }
}

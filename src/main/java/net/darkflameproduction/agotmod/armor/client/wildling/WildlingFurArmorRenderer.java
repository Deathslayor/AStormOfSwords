package net.darkflameproduction.agotmod.armor.client.wildling;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class WildlingFurArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public WildlingFurArmorRenderer() {
        super(new ASOSArmorModel("wildling_fur"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.WILDLING_FUR_HELMET.asItem(), ModItems.WILDLING_FUR_CHESTPLATE.asItem(), ModItems.WILDLING_FUR_LEGGINGS.asItem(), ModItems.WILDLING_FUR_BOOTS.asItem());
    }
}
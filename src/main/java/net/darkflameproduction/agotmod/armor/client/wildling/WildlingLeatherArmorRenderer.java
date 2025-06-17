package net.darkflameproduction.agotmod.armor.client.wildling;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class WildlingLeatherArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public WildlingLeatherArmorRenderer() {
        super(new ASOSArmorModel("wildling_leather"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.WILDLING_LEATHER_HELMET.asItem(), ModItems.WILDLING_LEATHER_CHESTPLATE.asItem(), ModItems.WILDLING_LEATHER_LEGGINGS.asItem(), ModItems.WILDLING_LEATHER_BOOTS.asItem());
    }
}
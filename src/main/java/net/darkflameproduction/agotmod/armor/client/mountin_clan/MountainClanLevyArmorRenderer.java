package net.darkflameproduction.agotmod.armor.client.mountin_clan;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class MountainClanLevyArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public MountainClanLevyArmorRenderer() {
        super(new ASOSArmorModel("mountain_clan_levy"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.MOUNTAIN_CLAN_LEVY_HELMET.asItem(), ModItems.MOUNTAIN_CLAN_LEVY_CHESTPLATE.asItem(), ModItems.MOUNTAIN_CLAN_LEVY_LEGGINGS.asItem(), ModItems.MOUNTAIN_CLAN_LEVY_BOOTS.asItem());
    }
}
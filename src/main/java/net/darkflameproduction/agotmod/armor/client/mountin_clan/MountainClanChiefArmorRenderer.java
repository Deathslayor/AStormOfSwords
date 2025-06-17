package net.darkflameproduction.agotmod.armor.client.mountin_clan;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.client.ASOSArmorModel;
import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class MountainClanChiefArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends ASOSArmorRenderer<R> {

    public MountainClanChiefArmorRenderer() {
        super(new ASOSArmorModel("mountain_clan_chief"));
    }

    @Override
    protected List<Item> fullArmorSet() {
        return List.of(ModItems.MOUNTAIN_CLAN_CHIEF_HELMET.asItem(), ModItems.MOUNTAIN_CLAN_CHIEF_CHESTPLATE.asItem(), ModItems.MOUNTAIN_CLAN_CHIEF_LEGGINGS.asItem(), ModItems.MOUNTAIN_CLAN_CHIEF_BOOTS.asItem());
    }
}
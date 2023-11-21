package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AGoTMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // ---------------------------(COINS)--------------------------- //
        simpleItem(ModItems.COIN);
        // ---------------------------(COINS)--------------------------- //

        // ---------------------------(ARMOUR)--------------------------- //
        simpleItem(ModItems.STARK1_BOOTS);
        simpleItem(ModItems.STARK1_LEGGINGS);
        simpleItem(ModItems.STARK1_CHESTPLATE);
        simpleItem(ModItems.STARK1_HELMET);
        // ---------------------------(ARMOUR)--------------------------- //

        // ---------------------------(INGOTS)--------------------------- //
        simpleItem(ModItems.RAW_TIN);
        simpleItem(ModItems.TIN_INGOT);
        simpleItem(ModItems.BRONZE_INGOT);
        // ---------------------------(INGOTS)--------------------------- //
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}

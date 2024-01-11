package net.astormofsorts.agotmod.datagen;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.astormofsorts.agotmod.block.ModBLocks;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    // Constructor for ModItemModelProvider
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        // Call the constructor of the superclass (ItemModelProvider)
        super(output, AGoTMod.MOD_ID, existingFileHelper);
    }

    // Method to register item models
    @Override
    protected void registerModels() {
        // ---------------------------(COINS)--------------------------- //
        // Register the item model for the COIN
        simpleItem(ModItems.COIN);
        // ---------------------------(COINS)--------------------------- //

        // ---------------------------(ARMOUR)--------------------------- //
        // Register item models for Stark Tier 1 Armour
        simpleItemStark(ModItems.STARK_LEVY_HELMET);
        simpleItemStark(ModItems.STARK_LEVY_CHESTPLATE);
        simpleItemStark(ModItems.STARK_LEVY_LEGGINGS);
        simpleItemStark(ModItems.STARK_LEVY_BOOTS);
        // ---------------------------(ARMOUR)--------------------------- //

        // ---------------------------(INGOTS)--------------------------- //
        // Register item models for ingots and nuggets
        simpleItem(ModItems.RAW_TIN);
        simpleItem(ModItems.TIN_INGOT);
        simpleItem(ModItems.BRONZE_INGOT);
        simpleItem(ModItems.STEEL_INGOT);
        simpleItem(ModItems.STEEL_NUGGET);
        // ---------------------------(INGOTS)--------------------------- //

        // ---------------------------(ANIMAL SPAWN EGGS)--------------------------- //
        // Register item model for the Rhino Spawn Egg
        withExistingParent(ModItems.RHINO_SPANW_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.BEAR_SPANW_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.WIGHT_SPANW_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        // ---------------------------(ANIMAL SPAWN EGGS)--------------------------- //

        // ---------------------------(TOOLS)--------------------------- //
        // Register item models for tools
        handheldItem(ModItems.BRONZE_AXE);
        handheldItem(ModItems.BRONZE_PICKAXE);
        handheldItem(ModItems.BRONZE_HOE);
        handheldItem(ModItems.BRONZE_SWORD);
        handheldItem(ModItems.BRONZE_SHOVEL);
        handheldItem(ModItems.STEEL_AXE);
        handheldItem(ModItems.STEEL_PICKAXE);
        handheldItem(ModItems.STEEL_HOE);
        handheldItem(ModItems.STEEL_SWORD);
        handheldItem(ModItems.STEEL_SHOVEL);
        // ---------------------------(TOOLS)--------------------------- //

        // ---------------------------(FOODS)--------------------------- //
        // Register item models for food items
        simpleItem(ModItems.RAW_BEAR_MEAT);
        simpleItem(ModItems.COOKED_BEAR_MEAT);
        simpleItem(ModItems.RAW_SAUSAGE);
        simpleItem(ModItems.RAW_WHITE_SAUSAGE);
        simpleItem(ModItems.RAW_BLOOD_SAUSAGE);
        simpleItem(ModItems.COOKED_SAUSAGE);
        simpleItem(ModItems.COOKED_BLOOD_SAUSAGE);
        simpleItem(ModItems.COOKED_WHITE_SAUSAGE);
        // ---------------------------(FOODS)--------------------------- //

        // ---------------------------(SAPLINGS)--------------------------- //
        saplingItem(ModBLocks.SYCAMORE_SAPLING);
        // ---------------------------(SAPLINGS)--------------------------- //
        simpleItem(ModItems.COOKED_SAUSAGE);
        simpleItem(ModItems.RAW_WHITE_SAUSAGE);
        simpleItem(ModItems.COOKED_WHITE_SAUSAGE);
        simpleItem(ModItems.RAW_BLOOD_SAUSAGE);
        simpleItem(ModItems.COOKED_BLOOD_SAUSAGE);
        simpleItem(ModItems.RAW_BACON);
        simpleItem(ModItems.COOKED_BACON);
        simpleItem(ModItems.COOKED_BOAR_VENISON);
        simpleItem(ModItems.RAW_BOAR_VENISON);
        simpleItem(ModItems.COOKED_CHICKEN_NUGGETS);
        simpleItem(ModItems.RAW_CHICKEN_NUGGETS);
        simpleItem(ModItems.COOKED_DEER_VENISON);
        simpleItem(ModItems.RAW_DEER_VENISON);
        simpleItem(ModItems.COOKED_GOAT_MEAT);
        simpleItem(ModItems.RAW_GOAT_MEAT);
        simpleItem(ModItems.COOKED_HARE_MEAT);
        simpleItem(ModItems.RAW_HARE_MEAT);
        simpleItem(ModItems.COOKED_HORSE_MEAT);
        simpleItem(ModItems.RAW_HORSE_MEAT);
        simpleItem(ModItems.COOKED_MAMMOTH_MEAT);
        simpleItem(ModItems.RAW_MAMMOTH_MEAT);
        // ---------------------------(FOODS)--------------------------- //

        // ---------------------------(SAPLINGS)--------------------------- //
        saplingItem(ModBLocks.WEIRWOOD_SAPLING);
        saplingItem(ModBLocks.SYCAMORE_SAPLING);
        // ---------------------------(SAPLINGS)--------------------------- //

        // ---------------------------(CRAFTING ITEMS)--------------------------- //
        // Register item models for crafting items
        simpleItem(ModItems.BOAR_INTESTINES);
        simpleItem(ModItems.BOAR_TUSK);
        simpleItem(ModItems.WEIRWOOD_STICK);
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID,"block/" + item.getId().getPath()));
    }

    // Method to create a simple item model
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }
    // Method to create a simple item model
    private ItemModelBuilder simpleItemStark(RegistryObject<StarkLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    // Method to create a handheld item model
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}

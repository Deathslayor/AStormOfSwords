package net.astormofsorts.agotmod.datagen;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.armor.custom.bolten.BoltenLevyArmorItem;
import net.astormofsorts.agotmod.armor.custom.bolten.BoltenNobleArmorItem;
import net.astormofsorts.agotmod.armor.custom.bolten.BoltenPlateArmorItem;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyLevyArmorItem;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyNobleArmorItem;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyPlateArmorItem;
import net.astormofsorts.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.astormofsorts.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import net.astormofsorts.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.astormofsorts.agotmod.block.ModBLocks;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
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
        // Register item models for Armour
        //Stark
        simpleItemStarkLevy(ModItems.STARK_LEVY_HELMET);
        simpleItemStarkLevy(ModItems.STARK_LEVY_CHESTPLATE);
        simpleItemStarkLevy(ModItems.STARK_LEVY_LEGGINGS);
        simpleItemStarkLevy(ModItems.STARK_LEVY_BOOTS);
        //
        simpleItemStarkPlate(ModItems.STARKPLATE_HELMET);
        simpleItemStarkPlate(ModItems.STARKPLATE_CHESTPLATE);
        simpleItemStarkPlate(ModItems.STARKPLATE_LEGGINGS);
        simpleItemStarkPlate(ModItems.STARKPLATE_BOOTS);
        //
        simpleItemStarkNoblePlate(ModItems.STARK_NOBLE_PLATE_HELMET);
        simpleItemStarkNoblePlate(ModItems.STARK_NOBLE_PLATE_CHESTPLATE);
        simpleItemStarkNoblePlate(ModItems.STARK_NOBLE_PLATE_LEGGINGS);
        simpleItemStarkNoblePlate(ModItems.STARK_NOBLE_PLATE_BOOTS);
        //Bolten
        simpleItemBoltenLevy(ModItems.BOLTEN_LEVY_HELMET);
        simpleItemBoltenLevy(ModItems.BOLTEN_LEVY_CHESTPLATE);
        simpleItemBoltenLevy(ModItems.BOLTEN_LEVY_LEGGINGS);
        simpleItemBoltenLevy(ModItems.BOLTEN_LEVY_BOOTS);
        //
        simpleItemBoltenPlate(ModItems.BOLTEN_PLATE_HELMET);
        simpleItemBoltenPlate(ModItems.BOLTEN_PLATE_CHESTPLATE);
        simpleItemBoltenPlate(ModItems.BOLTEN_PLATE_LEGGINGS);
        simpleItemBoltenPlate(ModItems.BOLTEN_PLATE_BOOTS);
        //
        simpleItemBoltenNoblePlate(ModItems.BOLTEN_NOBLE_HELMET);
        simpleItemBoltenNoblePlate(ModItems.BOLTEN_NOBLE_CHESTPLATE);
        simpleItemBoltenNoblePlate(ModItems.BOLTEN_NOBLE_LEGGINGS);
        simpleItemBoltenNoblePlate(ModItems.BOLTEN_NOBLE_BOOTS);
        //Manderly
        simpleItemManderlyLevy(ModItems.MANDERLY_LEVY_HELMET);
        simpleItemManderlyLevy(ModItems.MANDERLY_LEVY_CHESTPLATE);
        simpleItemManderlyLevy(ModItems.MANDERLY_LEVY_LEGGINGS);
        simpleItemManderlyLevy(ModItems.MANDERLY_LEVY_BOOTS);
        //
        simpleItemManderlyPlate(ModItems.MANDERLY_PLATE_HELMET);
        simpleItemManderlyPlate(ModItems.MANDERLY_PLATE_CHESTPLATE);
        simpleItemManderlyPlate(ModItems.MANDERLY_PLATE_LEGGINGS);
        simpleItemManderlyPlate(ModItems.MANDERLY_PLATE_BOOTS);
        //
        simpleItemManderlyNoble(ModItems.MANDERLY_NOBLE_HELMET);
        simpleItemManderlyNoble(ModItems.MANDERLY_NOBLE_CHESTPLATE);
        simpleItemManderlyNoble(ModItems.MANDERLY_NOBLE_LEGGINGS);
        simpleItemManderlyNoble(ModItems.MANDERLY_NOBLE_BOOTS);
        // ---------------------------(ARMOUR)--------------------------- //

        // ---------------------------(INGOTS)--------------------------- //
        // Register item models for ingots and nuggets
        simpleItem(ModItems.RAW_TIN);
        simpleItem(ModItems.TIN_INGOT);
        simpleItem(ModItems.BRONZE_INGOT);
        simpleItem(ModItems.STEEL_INGOT);
        simpleItem(ModItems.STEEL_NUGGET);
        simpleItem(ModItems.YELLOW_DIAMOND);
        simpleItem(ModItems.TRANSPARENT_DIAMOND);
        simpleItem(ModItems.AMBER);
        simpleItem(ModItems.AMETHYST);
        simpleItem(ModItems.BLACK_DIAMOND);
        simpleItem(ModItems.BLOODSTONE);
        simpleItem(ModItems.CARNELIAN);
        simpleItem(ModItems.CHALCEDONY);
        simpleItem(ModItems.GARNET);
        simpleItem(ModItems.JADE);
        simpleItem(ModItems.JASPER);
        simpleItem(ModItems.MALACHITE);
        simpleItem(ModItems.MOONSTONE);
        simpleItem(ModItems.ONYX);
        simpleItem(ModItems.OPAL);
        simpleItem(ModItems.RUBY);
        simpleItem(ModItems.SAPPHIRE);
        simpleItem(ModItems.TIGERS_EYE);
        simpleItem(ModItems.TOPAZ);
        simpleItem(ModItems.TOURMALINE);

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
                new ResourceLocation(AGoTMod.MOD_ID, "block/" + item.getId().getPath()));
    }

    // Method to create a simple item model
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    // Method to create a simple Armor item model
    //Stark
    private ItemModelBuilder simpleItemStarkLevy(RegistryObject<StarkLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemStarkPlate(RegistryObject<StarkPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemStarkNoblePlate(RegistryObject<StarkNoblePlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    //Bolten
    private ItemModelBuilder simpleItemBoltenLevy(RegistryObject<BoltenLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemBoltenPlate(RegistryObject<BoltenPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemBoltenNoblePlate(RegistryObject<BoltenNobleArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    //Manderly
    private ItemModelBuilder simpleItemManderlyLevy(RegistryObject<ManderlyLevyArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemManderlyPlate(RegistryObject<ManderlyPlateArmorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AGoTMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemManderlyNoble(RegistryObject<ManderlyNobleArmorItem> item) {
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

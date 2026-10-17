package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.item.custom.BannerPatterns;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static net.darkflameproduction.agotmod.block.ModBLocks.BLACKSTONE_VARIANTS;

public class ModItemModelProvider extends ItemModelProvider {
    // Constructor for ModItemModelProvider
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        // Call the constructor of the superclass (ItemModelProvider)
        super(output, AGoTMod.MOD_ID, existingFileHelper);
    }



    // Method to register item models
    @Override
    protected void registerModels() {
        foodModels();

        // ---------------------------(COINS)--------------------------- //
        // Register the item model for the COIN
        simpleItem(ModItems.COIN);
        // ---------------------------(SPAWN EGGS)--------------------------- //
        spawnEggItem(ModItems.MAMMOTH_SPAWN_EGG);
        spawnEggItem(ModItems.CROW_SPAWN_EGG);
        spawnEggItem(ModItems.DIREWOLF_SPAWN_EGG);
        spawnEggItem(ModItems.PEASANT_SPAWN_EGG);


        // ---------------------------(ARMOUR)--------------------------- //
        // Register item models for Armour
        // Every 3D armour set: flat inventory icons (textures/item/<item id>.png) and the set's smithing scroll
        for (net.darkflameproduction.agotmod.armor.ModArmorSets.ArmorSet set : net.darkflameproduction.agotmod.armor.ModArmorSets.all()) {
            for (net.minecraft.world.item.ArmorItem.Type type : net.darkflameproduction.agotmod.armor.ModArmorSets.PIECES) {
                basicItem(set.piece(type).get());
            }
            basicItem(set.scroll().get());
        }

        // ---------------------------(COINS)--------------------------- //
        simpleItem(ModItems.COPPER_STAR);
        simpleItem(ModItems.COPPER_GROAT);
        simpleItem(ModItems.COPPER_HALFPENNY);
        simpleItem(ModItems.COPPER_PENNY);
        simpleItem(ModItems.GOLD_DRAGON);
        simpleItem(ModItems.SILVER_MOON);
        simpleItem(ModItems.SILVER_STAG);
        simpleItem(ModItems.COPPER_HALFGROAT);


        // ---------------------------(INGOTS)--------------------------- //
        // Register item models for ingots and nuggets
        simpleItem(ModItems.RAW_SILVER);
        simpleItem(ModItems.SILVER_INGOT);
        simpleItem(ModItems.SILVER_NUGGET);
        simpleItem(ModItems.RAW_TIN);
        simpleItem(ModItems.TIN_INGOT);
        simpleItem(ModItems.BRONZE_INGOT);
        simpleItem(ModItems.BRONZE_NUGGET);
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

        // ---------------------------(Weapons)--------------------------- //
        weaponItem(ModItems.DRAGONGLASS_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.DRAGONGLASS_DAGGER);
        weaponItem(ModItems.BRONZE_SPATHA,
                new float[]{0.0f, 6.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.BRONZE_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.BRONZE_PIKE,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{3f, 3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.4f, 1.4f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.BRONZE_DAGGER);
        weaponItem(ModItems.BRONZE_BATTLEAXE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.IRON_LONGSWORD,
                new float[]{0.0f, 6.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.IRON_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.IRON_PIKE,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{3f, 3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.4f, 1.4f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.IRON_DAGGER);
        weaponItem(ModItems.IRON_MACE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.IRON_BATTLEAXE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_LONGSWORD,
                new float[]{0.0f, 6.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_PIKE,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{3f, 3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.4f, 1.4f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.STEEL_DAGGER);
        weaponItem(ModItems.STEEL_MACE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_BATTLEAXE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.STEEL_HALBERD,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_LONGSWORD,
                new float[]{0.0f, 6.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_SPEAR,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_PIKE,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{3f, 3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.4f, 1.4f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        handheldItem(ModItems.NOBLE_DAGGER);
        weaponItem(ModItems.NOBLE_MACE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_BATTLEAXE,
                new float[]{0.0f, 2.0f, 0.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{1.3f, 1.3f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );
        weaponItem(ModItems.NOBLE_HALBERD,
                new float[]{0.0f, 2.0f, 1.0f},   // thirdpersonTranslation [x, y, z]
                new float[]{2f, 2f, 1.0f},   // thirdpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 55.0f}, // thirdpersonRotation [x, y, z]
                new float[]{0.0f, 3.0f, 0.0f},   // firstpersonTranslation [x, y, z]
                new float[]{1.2f, 1.2f, 1.0f},   // firstpersonScale [x, y, z]
                new float[]{0.0f, 90.0f, 25.0f}  // firstpersonRotation [x, y, z]
        );

        for (Map.Entry<String, DeferredItem<Item>> entry : ModItems.IRON_SWORDS.entrySet()) {
            String[] parts = entry.getKey().split("_h|_b|_c");
            ironSwordItem(entry.getValue(),
                    Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                    new float[]{0.0f, 6.0f, 1.0f}, new float[]{1.3f, 1.3f, 1.0f}, new float[]{0.0f, 90.0f, 55.0f},
                    new float[]{0.0f, 3.0f, 0.0f}, new float[]{1.2f, 1.2f, 1.0f}, new float[]{0.0f, 90.0f, 25.0f});
        }

        for (Map.Entry<String, DeferredItem<Item>> entry : ModItems.STEEL_SWORDS.entrySet()) {
            String[] parts = entry.getKey().split("_h|_b|_c");
            steelSwordItem(entry.getValue(),
                    Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                    new float[]{0.0f, 6.0f, 1.0f}, new float[]{1.3f, 1.3f, 1.0f}, new float[]{0.0f, 90.0f, 55.0f},
                    new float[]{0.0f, 3.0f, 0.0f}, new float[]{1.2f, 1.2f, 1.0f}, new float[]{0.0f, 90.0f, 25.0f});
        }

        for (Map.Entry<String, DeferredItem<Item>> entry : ModItems.NOBLE_SWORDS.entrySet()) {
            String[] parts = entry.getKey().split("_h|_b|_c");
            nobleSwordItem(entry.getValue(),
                    Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                    new float[]{0.0f, 6.0f, 1.0f}, new float[]{1.3f, 1.3f, 1.0f}, new float[]{0.0f, 90.0f, 55.0f},
                    new float[]{0.0f, 3.0f, 0.0f}, new float[]{1.2f, 1.2f, 1.0f}, new float[]{0.0f, 90.0f, 25.0f});
        }


        // ---------------------------(TOOLS)--------------------------- //
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
        handheldItem(ModItems.BLOOD_DAGGER);

        // ---------------------------(MAGIC)--------------------------- //
        handheldItem(ModItems.BLOOD_BOTTLED);




// â”€â”€ Fowl â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Meats â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Offal & Organ Meats â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Sausages â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Other Ingredient Meats â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Fish & Seafood â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Prepared & Preserved â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Baked Goods & Prepared Dishes â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ General â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Cereals â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Vegetables â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Vegetable Pies â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€


// â”€â”€ Fruits â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Nuts â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Herbs & Spices â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Drinks â€” Beer â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Drinks â€” Liquor â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Drinks â€” Milk â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Drinks â€” Tea â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Drinks â€” Wine â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

// â”€â”€ Drinks â€” Other â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€




        // ---------------------------(SEEDS)--------------------------- //
        simpleItem(ModItems.HORSERADISH_SEEDS);
        simpleItem(ModItems.LEEK_SEEDS);
        simpleItem(ModItems.NEEP_SEEDS);
        simpleItem(ModItems.PARSLEY_SEEDS);
        simpleItem(ModItems.RED_ONION_SEEDS);
        simpleItem(ModItems.TURNIP_SEEDS);
        simpleItem(ModItems.RYE_SEEDS);
        simpleItem(ModItems.RYE);
        simpleItem(ModItems.BARLEY_SEEDS);
        simpleItem(ModItems.BARLEY);
        simpleItem(ModItems.OAT_SEEDS);
        simpleItem(ModItems.OAT);
        simpleItem(ModItems.COTTON_SEEDS);
        simpleItem(ModItems.COTTON);
        simpleItem(ModItems.HEMP_SEEDS);
        simpleItem(ModItems.HEMP);
        simpleItem(ModItems.WILD_ONION_SEEDS);
        simpleItem(ModItems.ONION_SEEDS);
        simpleItem(ModItems.OPIUM_POPPY_SEEDS);
        simpleItem(ModItems.CABBAGE_SEEDS);
        simpleItem(ModItems.BEAN_SEEDS);
        simpleItem(ModItems.CHICKPEA_SEEDS);
        simpleItem(ModItems.CUCUMBER_SEEDS);
        simpleItem(ModItems.GREEN_BEAN_SEEDS);
        simpleItem(ModItems.SPINACH_SEEDS);
        simpleItem(ModItems.DRAGON_PEPPER_SEEDS);
        simpleItem(ModItems.PEPPER_SEEDS);
        simpleItem(ModItems.PEPPERCORN_SEEDS);
        simpleItem(ModItems.STRAWBERRY_SEEDS);
        simpleItem(ModItems.BLACKBERRY_SEEDS);
        simpleItem(ModItems.BLUEBERRY_SEEDS);
        simpleItem(ModItems.MULBERRY_SEEDS);
        simpleItem(ModItems.RASPBERRY_SEEDS);
        simpleItem(ModItems.SMOKEBERRY_SEEDS);
        simpleItem(ModItems.CORN_SEEDS);
        simpleItem(ModItems.CORN_MIDDLE_SEEDS);
        simpleItem(ModItems.CORN_TOP_SEEDS);

        // ---------------------------(JOBBLOCKS)--------------------------- //

// Town Hall (existing)
        simpleItem(ModItems.TOWN_HALL);

// All Job Barrel Item Model Generation
        simpleItem(ModItems.ALEHOUSE_BARREL);
        simpleItem(ModItems.ARMORSMITH_BARREL);
        simpleItem(ModItems.BAKER_BARREL);
        simpleItem(ModItems.BANKER_BARREL);
        simpleItem(ModItems.BARD_BARREL);
        simpleItem(ModItems.BLACKSMITH_BARREL);
        simpleItem(ModItems.BUILDER_BARREL);
        simpleItem(ModItems.BUTCHER_BARREL);
        simpleItem(ModItems.CARAVAN_MASTER_BARREL);
        simpleItem(ModItems.CARPENTER_BARREL);
        simpleItem(ModItems.CATTLE_HERDER_BARREL);
        simpleItem(ModItems.CHARCOAL_BURNER_BARREL);
        simpleItem(ModItems.CHICKEN_BREEDER_BARREL);
        simpleItem(ModItems.FARMER_BARREL);
        simpleItem(ModItems.GOAT_HERDER_BARREL);
        simpleItem(ModItems.GROCER_BARREL);
        simpleItem(ModItems.GUARD_BARREL);
        simpleItem(ModItems.HERBALIST_BARREL);
        simpleItem(ModItems.HORSE_BREEDER_BARREL);
        simpleItem(ModItems.HUNTER_BARREL);
        simpleItem(ModItems.INNKEEPER_BARREL);
        simpleItem(ModItems.JEWELER_BARREL);
        simpleItem(ModItems.LUMBERJACK_BARREL);
        simpleItem(ModItems.MAESTER_BARREL);
        simpleItem(ModItems.MINER_BARREL);
        simpleItem(ModItems.PIG_BREEDER_BARREL);
        simpleItem(ModItems.PYROMANCER_BARREL);
        simpleItem(ModItems.QUARRY_BARREL);
        simpleItem(ModItems.SCRIBE_BARREL);
        simpleItem(ModItems.SEPTON_BARREL);
        simpleItem(ModItems.SHEEP_HERDER_BARREL);
        simpleItem(ModItems.SHIPWRIGHT_BARREL);
        simpleItem(ModItems.SLAVER_BARREL);
        simpleItem(ModItems.SMELTER_BARREL);
        simpleItem(ModItems.STONEMASON_BARREL);
        simpleItem(ModItems.SWORDSMITH_BARREL);
        simpleItem(ModItems.TAILOR_BARREL);
        simpleItem(ModItems.TANNER_BARREL);
        simpleItem(ModItems.TRADER_BARREL);

        // ---------------------------(FURNITURE)--------------------------- //
        // Tables
        furnitureItem(ModItems.OAK_TABLE);
        furnitureItem(ModItems.DARK_OAK_TABLE);
        furnitureItem(ModItems.SPRUCE_TABLE);
        furnitureItem(ModItems.BIRCH_TABLE);
        furnitureItem(ModItems.JUNGLE_TABLE);
        furnitureItem(ModItems.ACACIA_TABLE);
        furnitureItem(ModItems.MANGROVE_TABLE);
        furnitureItem(ModItems.CHERRY_TABLE);
        furnitureItem(ModItems.BAMBOO_TABLE);
        furnitureItem(ModItems.CRIMSON_TABLE);
        furnitureItem(ModItems.WARPED_TABLE);
        furnitureItem(ModItems.WEIRWOOD_TABLE);
        furnitureItem(ModItems.CHARRED_TABLE);
        furnitureItem(ModItems.ROTTEN_TABLE);
        furnitureItem(ModItems.PINE_TABLE);
        furnitureItem(ModItems.ASH_TABLE);
        furnitureItem(ModItems.BEECH_TABLE);
        furnitureItem(ModItems.CEDAR_TABLE);
        furnitureItem(ModItems.CHESTNUT_TABLE);
        furnitureItem(ModItems.HAWTHORN_TABLE);
        furnitureItem(ModItems.IRONWOOD_TABLE);
        furnitureItem(ModItems.SENTINEL_TABLE);
        furnitureItem(ModItems.SYCAMORE_TABLE);
        furnitureItem(ModItems.BLACKBARK_TABLE);
        furnitureItem(ModItems.ASPEN_TABLE);
        furnitureItem(ModItems.ALDER_TABLE);
        furnitureItem(ModItems.BLACK_CHERRY_TABLE);
        furnitureItem(ModItems.BLACK_OLIVE_TABLE);
        furnitureItem(ModItems.CRABAPPLE_TABLE);
        furnitureItem(ModItems.OLIVE_TABLE);
        furnitureItem(ModItems.WHITE_CHERRY_TABLE);
        furnitureItem(ModItems.RED_CHERRY_TABLE);
        furnitureItem(ModItems.FIR_TABLE);
        furnitureItem(ModItems.WILLOW_TABLE);
        furnitureItem(ModItems.WORMTREE_TABLE);
        furnitureItem(ModItems.ALMOND_TABLE);
        furnitureItem(ModItems.APPLE_TABLE);
        furnitureItem(ModItems.APRICOT_TABLE);
        furnitureItem(ModItems.BAOBAB_TABLE);
        furnitureItem(ModItems.BLACK_COTTONWOOD_TABLE);
        furnitureItem(ModItems.BLACKTHORN_TABLE);
        furnitureItem(ModItems.BLOOD_ORANGE_TABLE);
        furnitureItem(ModItems.BLOODWOOD_TABLE);
        furnitureItem(ModItems.BLUE_MAHOE_TABLE);
        furnitureItem(ModItems.COTTONWOOD_TABLE);
        furnitureItem(ModItems.DATEPALM_TABLE);
        furnitureItem(ModItems.EBONY_TABLE);
        furnitureItem(ModItems.FIG_TABLE);
        furnitureItem(ModItems.FIREPLUM_TABLE);
        furnitureItem(ModItems.GOLDENHEART_TABLE);
        furnitureItem(ModItems.LEMON_TABLE);
        furnitureItem(ModItems.LIME_TABLE);
        furnitureItem(ModItems.LINDEN_TABLE);
        furnitureItem(ModItems.MAHOGANY_TABLE);
        furnitureItem(ModItems.MAPLE_TABLE);
        furnitureItem(ModItems.MYRRH_TABLE);
        furnitureItem(ModItems.NIGHTWOOD_TABLE);
        furnitureItem(ModItems.NUTMEG_TABLE);
        furnitureItem(ModItems.ORANGE_TABLE);
        furnitureItem(ModItems.PEACH_TABLE);
        furnitureItem(ModItems.PEAR_TABLE);
        furnitureItem(ModItems.PECAN_TABLE);
        furnitureItem(ModItems.PERSIMMON_TABLE);
        furnitureItem(ModItems.PINK_IVORY_TABLE);
        furnitureItem(ModItems.PLUM_TABLE);
        furnitureItem(ModItems.POMEGRANATE_TABLE);
        furnitureItem(ModItems.PURPLEHEART_TABLE);
        furnitureItem(ModItems.REDWOOD_TABLE);
        furnitureItem(ModItems.SANDALWOOD_TABLE);
        furnitureItem(ModItems.SANDBEGGAR_TABLE);
        furnitureItem(ModItems.TIGERWOOD_TABLE);
        furnitureItem(ModItems.YEW_TABLE);
        furnitureItem(ModItems.SOLDIER_PINE_TABLE);
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_TABLE);



// Stools
        furnitureItem(ModItems.DARK_OAK_STOOL);
        furnitureItem(ModItems.OAK_STOOL);
        furnitureItem(ModItems.SPRUCE_STOOL);
        furnitureItem(ModItems.BIRCH_STOOL);
        furnitureItem(ModItems.JUNGLE_STOOL);
        furnitureItem(ModItems.ACACIA_STOOL);
        furnitureItem(ModItems.MANGROVE_STOOL);
        furnitureItem(ModItems.CHERRY_STOOL);
        furnitureItem(ModItems.BAMBOO_STOOL);
        furnitureItem(ModItems.CRIMSON_STOOL);
        furnitureItem(ModItems.WARPED_STOOL);
        furnitureItem(ModItems.WEIRWOOD_STOOL);
        furnitureItem(ModItems.CHARRED_STOOL);
        furnitureItem(ModItems.ROTTEN_STOOL);
        furnitureItem(ModItems.PINE_STOOL);
        furnitureItem(ModItems.ASH_STOOL);
        furnitureItem(ModItems.BEECH_STOOL);
        furnitureItem(ModItems.CEDAR_STOOL);
        furnitureItem(ModItems.CHESTNUT_STOOL);
        furnitureItem(ModItems.HAWTHORN_STOOL);
        furnitureItem(ModItems.IRONWOOD_STOOL);
        furnitureItem(ModItems.SENTINEL_STOOL);
        furnitureItem(ModItems.SYCAMORE_STOOL);
        furnitureItem(ModItems.BLACKBARK_STOOL);
        furnitureItem(ModItems.ASPEN_STOOL);
        furnitureItem(ModItems.ALDER_STOOL);
        furnitureItem(ModItems.BLACK_CHERRY_STOOL);
        furnitureItem(ModItems.BLACK_OLIVE_STOOL);
        furnitureItem(ModItems.CRABAPPLE_STOOL);
        furnitureItem(ModItems.OLIVE_STOOL);
        furnitureItem(ModItems.WHITE_CHERRY_STOOL);
        furnitureItem(ModItems.RED_CHERRY_STOOL);
        furnitureItem(ModItems.FIR_STOOL);
        furnitureItem(ModItems.WILLOW_STOOL);
        furnitureItem(ModItems.WORMTREE_STOOL);
        furnitureItem(ModItems.ALMOND_STOOL);
        furnitureItem(ModItems.APPLE_STOOL);
        furnitureItem(ModItems.APRICOT_STOOL);
        furnitureItem(ModItems.BAOBAB_STOOL);
        furnitureItem(ModItems.BLACK_COTTONWOOD_STOOL);
        furnitureItem(ModItems.BLACKTHORN_STOOL);
        furnitureItem(ModItems.BLOOD_ORANGE_STOOL);
        furnitureItem(ModItems.BLOODWOOD_STOOL);
        furnitureItem(ModItems.BLUE_MAHOE_STOOL);
        furnitureItem(ModItems.COTTONWOOD_STOOL);
        furnitureItem(ModItems.DATEPALM_STOOL);
        furnitureItem(ModItems.EBONY_STOOL);
        furnitureItem(ModItems.FIG_STOOL);
        furnitureItem(ModItems.FIREPLUM_STOOL);
        furnitureItem(ModItems.GOLDENHEART_STOOL);
        furnitureItem(ModItems.LEMON_STOOL);
        furnitureItem(ModItems.LIME_STOOL);
        furnitureItem(ModItems.LINDEN_STOOL);
        furnitureItem(ModItems.MAHOGANY_STOOL);
        furnitureItem(ModItems.MAPLE_STOOL);
        furnitureItem(ModItems.MYRRH_STOOL);
        furnitureItem(ModItems.NIGHTWOOD_STOOL);
        furnitureItem(ModItems.NUTMEG_STOOL);
        furnitureItem(ModItems.ORANGE_STOOL);
        furnitureItem(ModItems.PEACH_STOOL);
        furnitureItem(ModItems.PEAR_STOOL);
        furnitureItem(ModItems.PECAN_STOOL);
        furnitureItem(ModItems.PERSIMMON_STOOL);
        furnitureItem(ModItems.PINK_IVORY_STOOL);
        furnitureItem(ModItems.PLUM_STOOL);
        furnitureItem(ModItems.POMEGRANATE_STOOL);
        furnitureItem(ModItems.PURPLEHEART_STOOL);
        furnitureItem(ModItems.REDWOOD_STOOL);
        furnitureItem(ModItems.SANDALWOOD_STOOL);
        furnitureItem(ModItems.SANDBEGGAR_STOOL);
        furnitureItem(ModItems.TIGERWOOD_STOOL);
        furnitureItem(ModItems.YEW_STOOL);
        furnitureItem(ModItems.SOLDIER_PINE_STOOL);
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_STOOL);

// Benches
        furnitureItem(ModItems.DARK_OAK_BENCH);
        furnitureItem(ModItems.OAK_BENCH);
        furnitureItem(ModItems.SPRUCE_BENCH);
        furnitureItem(ModItems.BIRCH_BENCH);
        furnitureItem(ModItems.JUNGLE_BENCH);
        furnitureItem(ModItems.ACACIA_BENCH);
        furnitureItem(ModItems.MANGROVE_BENCH);
        furnitureItem(ModItems.CHERRY_BENCH);
        furnitureItem(ModItems.BAMBOO_BENCH);
        furnitureItem(ModItems.CRIMSON_BENCH);
        furnitureItem(ModItems.WARPED_BENCH);
        furnitureItem(ModItems.WEIRWOOD_BENCH);
        furnitureItem(ModItems.CHARRED_BENCH);
        furnitureItem(ModItems.ROTTEN_BENCH);
        furnitureItem(ModItems.PINE_BENCH);
        furnitureItem(ModItems.ASH_BENCH);
        furnitureItem(ModItems.BEECH_BENCH);
        furnitureItem(ModItems.CEDAR_BENCH);
        furnitureItem(ModItems.CHESTNUT_BENCH);
        furnitureItem(ModItems.HAWTHORN_BENCH);
        furnitureItem(ModItems.IRONWOOD_BENCH);
        furnitureItem(ModItems.SENTINEL_BENCH);
        furnitureItem(ModItems.SYCAMORE_BENCH);
        furnitureItem(ModItems.BLACKBARK_BENCH);
        furnitureItem(ModItems.ASPEN_BENCH);
        furnitureItem(ModItems.ALDER_BENCH);
        furnitureItem(ModItems.BLACK_CHERRY_BENCH);
        furnitureItem(ModItems.BLACK_OLIVE_BENCH);
        furnitureItem(ModItems.CRABAPPLE_BENCH);
        furnitureItem(ModItems.OLIVE_BENCH);
        furnitureItem(ModItems.WHITE_CHERRY_BENCH);
        furnitureItem(ModItems.RED_CHERRY_BENCH);
        furnitureItem(ModItems.FIR_BENCH);
        furnitureItem(ModItems.WILLOW_BENCH);
        furnitureItem(ModItems.WORMTREE_BENCH);
        furnitureItem(ModItems.ALMOND_BENCH);
        furnitureItem(ModItems.APPLE_BENCH);
        furnitureItem(ModItems.APRICOT_BENCH);
        furnitureItem(ModItems.BAOBAB_BENCH);
        furnitureItem(ModItems.BLACK_COTTONWOOD_BENCH);
        furnitureItem(ModItems.BLACKTHORN_BENCH);
        furnitureItem(ModItems.BLOOD_ORANGE_BENCH);
        furnitureItem(ModItems.BLOODWOOD_BENCH);
        furnitureItem(ModItems.BLUE_MAHOE_BENCH);
        furnitureItem(ModItems.COTTONWOOD_BENCH);
        furnitureItem(ModItems.DATEPALM_BENCH);
        furnitureItem(ModItems.EBONY_BENCH);
        furnitureItem(ModItems.FIG_BENCH);
        furnitureItem(ModItems.FIREPLUM_BENCH);
        furnitureItem(ModItems.GOLDENHEART_BENCH);
        furnitureItem(ModItems.LEMON_BENCH);
        furnitureItem(ModItems.LIME_BENCH);
        furnitureItem(ModItems.LINDEN_BENCH);
        furnitureItem(ModItems.MAHOGANY_BENCH);
        furnitureItem(ModItems.MAPLE_BENCH);
        furnitureItem(ModItems.MYRRH_BENCH);
        furnitureItem(ModItems.NIGHTWOOD_BENCH);
        furnitureItem(ModItems.NUTMEG_BENCH);
        furnitureItem(ModItems.ORANGE_BENCH);
        furnitureItem(ModItems.PEACH_BENCH);
        furnitureItem(ModItems.PEAR_BENCH);
        furnitureItem(ModItems.PECAN_BENCH);
        furnitureItem(ModItems.PERSIMMON_BENCH);
        furnitureItem(ModItems.PINK_IVORY_BENCH);
        furnitureItem(ModItems.PLUM_BENCH);
        furnitureItem(ModItems.POMEGRANATE_BENCH);
        furnitureItem(ModItems.PURPLEHEART_BENCH);
        furnitureItem(ModItems.REDWOOD_BENCH);
        furnitureItem(ModItems.SANDALWOOD_BENCH);
        furnitureItem(ModItems.SANDBEGGAR_BENCH);
        furnitureItem(ModItems.TIGERWOOD_BENCH);
        furnitureItem(ModItems.YEW_BENCH);
        // furnitureItem(ModItems.SOLDIER_PINE_BENCH); // needs item/soldier_pine_bench_icon.png
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_BENCH);



// Chairs
        furnitureItem(ModItems.DARK_OAK_CHAIR);
        furnitureItem(ModItems.OAK_CHAIR);
        furnitureItem(ModItems.SPRUCE_CHAIR);
        furnitureItem(ModItems.BIRCH_CHAIR);
        furnitureItem(ModItems.JUNGLE_CHAIR);
        furnitureItem(ModItems.ACACIA_CHAIR);
        furnitureItem(ModItems.MANGROVE_CHAIR);
        furnitureItem(ModItems.CHERRY_CHAIR);
        furnitureItem(ModItems.BAMBOO_CHAIR);
        furnitureItem(ModItems.CRIMSON_CHAIR);
        furnitureItem(ModItems.WARPED_CHAIR);
        furnitureItem(ModItems.WEIRWOOD_CHAIR);
        furnitureItem(ModItems.CHARRED_CHAIR);
        furnitureItem(ModItems.ROTTEN_CHAIR);
        furnitureItem(ModItems.PINE_CHAIR);
        furnitureItem(ModItems.ASH_CHAIR);
        furnitureItem(ModItems.BEECH_CHAIR);
        furnitureItem(ModItems.CEDAR_CHAIR);
        furnitureItem(ModItems.CHESTNUT_CHAIR);
        furnitureItem(ModItems.HAWTHORN_CHAIR);
        furnitureItem(ModItems.IRONWOOD_CHAIR);
        furnitureItem(ModItems.SENTINEL_CHAIR);
        furnitureItem(ModItems.SYCAMORE_CHAIR);
        furnitureItem(ModItems.BLACKBARK_CHAIR);
        furnitureItem(ModItems.ASPEN_CHAIR);
        furnitureItem(ModItems.ALDER_CHAIR);
        furnitureItem(ModItems.BLACK_CHERRY_CHAIR);
        furnitureItem(ModItems.BLACK_OLIVE_CHAIR);
        furnitureItem(ModItems.CRABAPPLE_CHAIR);
        furnitureItem(ModItems.OLIVE_CHAIR);
        furnitureItem(ModItems.WHITE_CHERRY_CHAIR);
        furnitureItem(ModItems.RED_CHERRY_CHAIR);
        furnitureItem(ModItems.FIR_CHAIR);
        furnitureItem(ModItems.WILLOW_CHAIR);
        furnitureItem(ModItems.WORMTREE_CHAIR);
        furnitureItem(ModItems.ALMOND_CHAIR);
        furnitureItem(ModItems.APPLE_CHAIR);
        furnitureItem(ModItems.APRICOT_CHAIR);
        furnitureItem(ModItems.BAOBAB_CHAIR);
        furnitureItem(ModItems.BLACK_COTTONWOOD_CHAIR);
        furnitureItem(ModItems.BLACKTHORN_CHAIR);
        furnitureItem(ModItems.BLOOD_ORANGE_CHAIR);
        furnitureItem(ModItems.BLOODWOOD_CHAIR);
        furnitureItem(ModItems.BLUE_MAHOE_CHAIR);
        furnitureItem(ModItems.COTTONWOOD_CHAIR);
        furnitureItem(ModItems.DATEPALM_CHAIR);
        furnitureItem(ModItems.EBONY_CHAIR);
        furnitureItem(ModItems.FIG_CHAIR);
        furnitureItem(ModItems.FIREPLUM_CHAIR);
        furnitureItem(ModItems.GOLDENHEART_CHAIR);
        furnitureItem(ModItems.LEMON_CHAIR);
        furnitureItem(ModItems.LIME_CHAIR);
        furnitureItem(ModItems.LINDEN_CHAIR);
        furnitureItem(ModItems.MAHOGANY_CHAIR);
        furnitureItem(ModItems.MAPLE_CHAIR);
        furnitureItem(ModItems.MYRRH_CHAIR);
        furnitureItem(ModItems.NIGHTWOOD_CHAIR);
        furnitureItem(ModItems.NUTMEG_CHAIR);
        furnitureItem(ModItems.ORANGE_CHAIR);
        furnitureItem(ModItems.PEACH_CHAIR);
        furnitureItem(ModItems.PEAR_CHAIR);
        furnitureItem(ModItems.PECAN_CHAIR);
        furnitureItem(ModItems.PERSIMMON_CHAIR);
        furnitureItem(ModItems.PINK_IVORY_CHAIR);
        furnitureItem(ModItems.PLUM_CHAIR);
        furnitureItem(ModItems.POMEGRANATE_CHAIR);
        furnitureItem(ModItems.PURPLEHEART_CHAIR);
        furnitureItem(ModItems.REDWOOD_CHAIR);
        furnitureItem(ModItems.SANDALWOOD_CHAIR);
        furnitureItem(ModItems.SANDBEGGAR_CHAIR);
        furnitureItem(ModItems.TIGERWOOD_CHAIR);
        furnitureItem(ModItems.YEW_CHAIR);
        furnitureItem(ModItems.SOLDIER_PINE_CHAIR);
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_CHAIR);

// Arm Chairs
        furnitureItem(ModItems.DARK_OAK_ARM_CHAIR);
        furnitureItem(ModItems.OAK_ARM_CHAIR);
        furnitureItem(ModItems.SPRUCE_ARM_CHAIR);
        furnitureItem(ModItems.BIRCH_ARM_CHAIR);
        furnitureItem(ModItems.JUNGLE_ARM_CHAIR);
        furnitureItem(ModItems.ACACIA_ARM_CHAIR);
        furnitureItem(ModItems.MANGROVE_ARM_CHAIR);
        furnitureItem(ModItems.CHERRY_ARM_CHAIR);
        furnitureItem(ModItems.BAMBOO_ARM_CHAIR);
        furnitureItem(ModItems.CRIMSON_ARM_CHAIR);
        furnitureItem(ModItems.WARPED_ARM_CHAIR);
        furnitureItem(ModItems.WEIRWOOD_ARM_CHAIR);
        furnitureItem(ModItems.CHARRED_ARM_CHAIR);
        furnitureItem(ModItems.ROTTEN_ARM_CHAIR);
        furnitureItem(ModItems.PINE_ARM_CHAIR);
        furnitureItem(ModItems.ASH_ARM_CHAIR);
        furnitureItem(ModItems.BEECH_ARM_CHAIR);
        furnitureItem(ModItems.CEDAR_ARM_CHAIR);
        furnitureItem(ModItems.CHESTNUT_ARM_CHAIR);
        furnitureItem(ModItems.HAWTHORN_ARM_CHAIR);
        furnitureItem(ModItems.IRONWOOD_ARM_CHAIR);
        furnitureItem(ModItems.SENTINEL_ARM_CHAIR);
        furnitureItem(ModItems.SYCAMORE_ARM_CHAIR);
        furnitureItem(ModItems.BLACKBARK_ARM_CHAIR);
        furnitureItem(ModItems.ASPEN_ARM_CHAIR);
        furnitureItem(ModItems.ALDER_ARM_CHAIR);
        furnitureItem(ModItems.BLACK_CHERRY_ARM_CHAIR);
        furnitureItem(ModItems.BLACK_OLIVE_ARM_CHAIR);
        furnitureItem(ModItems.CRABAPPLE_ARM_CHAIR);
        furnitureItem(ModItems.OLIVE_ARM_CHAIR);
        furnitureItem(ModItems.WHITE_CHERRY_ARM_CHAIR);
        furnitureItem(ModItems.RED_CHERRY_ARM_CHAIR);
        furnitureItem(ModItems.FIR_ARM_CHAIR);
        furnitureItem(ModItems.WILLOW_ARM_CHAIR);
        furnitureItem(ModItems.WORMTREE_ARM_CHAIR);
        furnitureItem(ModItems.ALMOND_ARM_CHAIR);
        furnitureItem(ModItems.APPLE_ARM_CHAIR);
        furnitureItem(ModItems.APRICOT_ARM_CHAIR);
        furnitureItem(ModItems.BAOBAB_ARM_CHAIR);
        furnitureItem(ModItems.BLACK_COTTONWOOD_ARM_CHAIR);
        furnitureItem(ModItems.BLACKTHORN_ARM_CHAIR);
        furnitureItem(ModItems.BLOOD_ORANGE_ARM_CHAIR);
        furnitureItem(ModItems.BLOODWOOD_ARM_CHAIR);
        furnitureItem(ModItems.BLUE_MAHOE_ARM_CHAIR);
        furnitureItem(ModItems.COTTONWOOD_ARM_CHAIR);
        furnitureItem(ModItems.DATEPALM_ARM_CHAIR);
        furnitureItem(ModItems.EBONY_ARM_CHAIR);
        furnitureItem(ModItems.FIG_ARM_CHAIR);
        furnitureItem(ModItems.FIREPLUM_ARM_CHAIR);
        furnitureItem(ModItems.GOLDENHEART_ARM_CHAIR);
        furnitureItem(ModItems.LEMON_ARM_CHAIR);
        furnitureItem(ModItems.LIME_ARM_CHAIR);
        furnitureItem(ModItems.LINDEN_ARM_CHAIR);
        furnitureItem(ModItems.MAHOGANY_ARM_CHAIR);
        furnitureItem(ModItems.MAPLE_ARM_CHAIR);
        furnitureItem(ModItems.MYRRH_ARM_CHAIR);
        furnitureItem(ModItems.NIGHTWOOD_ARM_CHAIR);
        furnitureItem(ModItems.NUTMEG_ARM_CHAIR);
        furnitureItem(ModItems.ORANGE_ARM_CHAIR);
        furnitureItem(ModItems.PEACH_ARM_CHAIR);
        furnitureItem(ModItems.PEAR_ARM_CHAIR);
        furnitureItem(ModItems.PECAN_ARM_CHAIR);
        furnitureItem(ModItems.PERSIMMON_ARM_CHAIR);
        furnitureItem(ModItems.PINK_IVORY_ARM_CHAIR);
        furnitureItem(ModItems.PLUM_ARM_CHAIR);
        furnitureItem(ModItems.POMEGRANATE_ARM_CHAIR);
        furnitureItem(ModItems.PURPLEHEART_ARM_CHAIR);
        furnitureItem(ModItems.REDWOOD_ARM_CHAIR);
        furnitureItem(ModItems.SANDALWOOD_ARM_CHAIR);
        furnitureItem(ModItems.SANDBEGGAR_ARM_CHAIR);
        furnitureItem(ModItems.TIGERWOOD_ARM_CHAIR);
        furnitureItem(ModItems.YEW_ARM_CHAIR);
        furnitureItem(ModItems.SOLDIER_PINE_ARM_CHAIR);
        furnitureItem(ModItems.BLUE_SOLDIER_PINE_ARM_CHAIR);





        // ---------------------------(SAPLINGS)--------------------------- //
// Define the wood types
        String[] woodTypes = {
                "alder",
                "almond",
                "apple",
                "apricot",
                "ash",
                "aspen",
                "baobab",
                "beech",
                "black_cherry",
                "black_cottonwood",
                "black_olive",
                "blackbark",
                "blackthorn",
                "blood_orange",
                "bloodwood",
                "blue_mahoe",
                "cedar",
                "chestnut",
                "cottonwood",
                "crabapple",
                "datepalm",
                "ebony",
                "fig",
                "fir",
                "fireplum",
                "goldenheart",
                "hawthorn",
                "ironwood",
                "lemon",
                "lime",
                "linden",
                "mahogany",
                "maple",
                "myrrh",
                "nightwood",
                "nutmeg",
                "olive",
                "orange",
                "peach",
                "pear",
                "pecan",
                "persimmon",
                "pine",
                "pink_ivory",
                "plum",
                "pomegranate",
                "purpleheart",
                "redwood",
                "sandalwood",
                "sandbeggar",
                "sentinel",
                "sycamore",
                "tigerwood",
                "white_cherry",
                "willow",
                "wormtree",
                "yew",
                "blue_soldier_pine",
                "soldier_pine"
        };

// First register all saplings in one loop
        for (String woodType : woodTypes) {
            saplingItem(ModBLocks.SAPLINGS.get(woodType));
        }

// Register all wood items using a loop
        for (String woodType : woodTypes) {
            // Door items
            simpleBlockItem(ModBLocks.DOORS.get(woodType));

            // Fence items
            fenceItem(ModBLocks.FENCES.get(woodType), ModBLocks.PLANKS.get(woodType));

            // Button items
            buttonItem(ModBLocks.BUTTONS.get(woodType), ModBLocks.PLANKS.get(woodType));

            // Wall items
            wallItem(ModBLocks.WALLS.get(woodType), ModBLocks.PLANKS.get(woodType));

            // Simple block items
            evenSimplerBlockItem(ModBLocks.STAIRS.get(woodType));
            evenSimplerBlockItem(ModBLocks.SLABS.get(woodType));
            evenSimplerBlockItem(ModBLocks.PRESSURE_PLATES.get(woodType));
            evenSimplerBlockItem(ModBLocks.FENCE_GATES.get(woodType));

            // Trapdoor items
            trapdoorItem(ModBLocks.TRAPDOORS.get(woodType));

            // Sign items
            simpleItem(ModItems.SIGN_ITEMS.get(woodType));
            simpleItem(ModItems.HANGING_SIGN_ITEMS.get(woodType));
        }
        // ---------------------------(BLOCKS)--------------------------- //
        evenSimplerBlockItemVanilla(ModBLocks.DIRT_STAIRS, "dirt");
        evenSimplerBlockItemVanilla(ModBLocks.DIRT_SLAB, "dirt");

        evenSimplerBlockItemVanilla(ModBLocks.MUD_STAIRS, "mud");
        evenSimplerBlockItemVanilla(ModBLocks.MUD_SLAB, "mud");

        evenSimplerBlockItemVanilla(ModBLocks.COARSE_DIRT_STAIRS, "coarse_dirt");
        evenSimplerBlockItemVanilla(ModBLocks.COARSE_DIRT_SLAB, "coarse_dirt");

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PODZOL_STAIRS, "podzol_top", "podzol_side", "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PODZOL_SLAB,   "podzol_top", "podzol_side", "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PODZOL_STAIRS,       "podzol_top",       "podzol_side",       "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PODZOL_SLAB,         "podzol_top",       "podzol_side",       "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.GRASS_BLOCK_STAIRS,  "grass_block_top",  "grass_block_side",  "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.GRASS_BLOCK_SLAB,    "grass_block_top",  "grass_block_side",  "dirt", true, true, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.DIRT_PATH_STAIRS, "dirt_path_top", "dirt_path_side", "dirt", true, false, true);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.DIRT_PATH_SLAB,   "dirt_path_top", "dirt_path_side", "dirt", true, false, true);


        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.THATCH_SLAB,         "thatch_block_side",       "thatch_block_top",       "thatch_block_side", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.THATCH_STAIRS,         "thatch_block_side",       "thatch_block_top",       "thatch_block_side", false, false, false);

        // ── Add to your item model provider ──────────────────────────────────

        // ── Add to your item model provider ──────────────────────────────────

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.OILY_STONE_STAIRS,
                "oily_stone_cycle", "oily_stone_cycle", "oily_stone_cycle", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.OILY_STONE_SLAB,
                "oily_stone_cycle", "oily_stone_cycle", "oily_stone_cycle", false, false, false);

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.OILY_STONE_EYE_CIRCLE_STAIRS,
                "oily_stone_eye_circle_cycle", "oily_stone_eye_circle_cycle", "oily_stone_eye_circle_cycle", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.OILY_STONE_EYE_CIRCLE_SLAB,
                "oily_stone_eye_circle_cycle", "oily_stone_eye_circle_cycle", "oily_stone_eye_circle_cycle", false, false, false);

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.POLISHED_OILY_STONE_STAIRS,
                "polished_oily_stone_cycle", "polished_oily_stone_cycle", "polished_oily_stone_cycle", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.POLISHED_OILY_STONE_SLAB,
                "polished_oily_stone_cycle", "polished_oily_stone_cycle", "polished_oily_stone_cycle", false, false, false);

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.OILY_BRICKS_STAIRS,
                "oily_bricks_cycle", "oily_bricks_cycle", "oily_bricks_cycle", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.OILY_BRICKS_SLAB,
                "oily_bricks_cycle", "oily_bricks_cycle", "oily_bricks_cycle", false, false, false);

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.CHISELED_OILY_STONE_STAIRS,
                "chiseled_oily_stone_cycle", "chiseled_oily_stone_cycle", "chiseled_oily_stone_cycle", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.CHISELED_OILY_STONE_SLAB,
                "chiseled_oily_stone_cycle", "chiseled_oily_stone_cycle", "chiseled_oily_stone_cycle", false, false, false);

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.CARVED_OILY_STONE_STAIRS,
                "carved_oily_stone_cycle", "carved_oily_stone_cycle", "carved_oily_stone_cycle", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.CARVED_OILY_STONE_SLAB,
                "carved_oily_stone_cycle", "carved_oily_stone_cycle", "carved_oily_stone_cycle", false, false, false);

        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PATTERNED_OILY_STONE_STAIRS,
                "patterned_oily_stone_cycle", "patterned_oily_stone_cycle", "patterned_oily_stone_cycle", false, false, false);
        evenSimplerBlockItemVanillaMultiTexture(ModBLocks.PATTERNED_OILY_STONE_SLAB,
                "patterned_oily_stone_cycle", "patterned_oily_stone_cycle", "patterned_oily_stone_cycle", false, false, false);

        modWallItem(ModBLocks.OILY_STONE_WALL, "oily_stone_cycle");

        modWallItem(ModBLocks.OILY_STONE_EYE_CIRCLE_WALL, "oily_stone_eye_circle_cycle");

        modWallItem(ModBLocks.POLISHED_OILY_STONE_WALL, "polished_oily_stone_cycle");

        modWallItem(ModBLocks.OILY_BRICKS_WALL, "oily_bricks_cycle");

        modWallItem(ModBLocks.CHISELED_OILY_STONE_WALL, "chiseled_oily_stone_cycle");

        modWallItem(ModBLocks.CARVED_OILY_STONE_WALL, "carved_oily_stone_cycle");

        modWallItem(ModBLocks.PATTERNED_OILY_STONE_WALL, "patterned_oily_stone_cycle");
        // ---------------------------(WOODBLOCKS)--------------------------- //
        //Weirwood
        saplingItem(ModBLocks.WEIRWOOD_SAPLING);
        simpleBlockItem(ModBLocks.WEIRWOOD_DOOR);
        fenceItem(ModBLocks.WEIRWOOD_FENCE, ModBLocks.WEIRWOOD_PLANKS);
        buttonItem(ModBLocks.WEIRWOOD_BUTTON, ModBLocks.WEIRWOOD_PLANKS);
        wallItem(ModBLocks.WEIRWOOD_WALL, ModBLocks.WEIRWOOD_PLANKS);
        evenSimplerBlockItem(ModBLocks.WEIRWOOD_STAIRS);
        evenSimplerBlockItem(ModBLocks.WEIRWOOD_SLAB);
        evenSimplerBlockItem(ModBLocks.WEIRWOOD_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.WEIRWOOD_FENCE_GATE);
        trapdoorItem(ModBLocks.WEIRWOOD_TRAPDOOR);
        vanillaWallItem(ModBLocks.OAK_WALL, Blocks.OAK_PLANKS);
        vanillaWallItem(ModBLocks.SPRUCE_WALL, Blocks.SPRUCE_PLANKS);
        vanillaWallItem(ModBLocks.BIRCH_WALL, Blocks.BIRCH_PLANKS);
        vanillaWallItem(ModBLocks.JUNGLE_WALL, Blocks.JUNGLE_PLANKS);
        vanillaWallItem(ModBLocks.ACACIA_WALL, Blocks.ACACIA_PLANKS);
        vanillaWallItem(ModBLocks.DARK_OAK_WALL, Blocks.DARK_OAK_PLANKS);
        vanillaWallItem(ModBLocks.MANGROVE_WALL, Blocks.MANGROVE_PLANKS);
        vanillaWallItem(ModBLocks.CHERRY_WALL, Blocks.CHERRY_PLANKS);
        vanillaWallItem(ModBLocks.BAMBOO_WALL, Blocks.BAMBOO_PLANKS);
        vanillaWallItem(ModBLocks.CRIMSON_WALL, Blocks.CRIMSON_PLANKS);
        vanillaWallItem(ModBLocks.WARPED_WALL, Blocks.WARPED_PLANKS);
        simpleBlockItem(ModBLocks.ROTTEN_DOOR);
        fenceItem(ModBLocks.ROTTEN_FENCE, ModBLocks.ROTTEN_PLANKS);
        buttonItem(ModBLocks.ROTTEN_BUTTON, ModBLocks.ROTTEN_PLANKS);
        wallItem(ModBLocks.ROTTEN_WALL, ModBLocks.ROTTEN_PLANKS);
        evenSimplerBlockItem(ModBLocks.ROTTEN_STAIRS);
        evenSimplerBlockItem(ModBLocks.ROTTEN_SLAB);
        evenSimplerBlockItem(ModBLocks.ROTTEN_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.ROTTEN_FENCE_GATE);
        trapdoorItem(ModBLocks.ROTTEN_TRAPDOOR);

        simpleBlockItem(ModBLocks.CHARRED_DOOR);
        fenceItem(ModBLocks.CHARRED_FENCE, ModBLocks.CHARRED_PLANKS);
        buttonItem(ModBLocks.CHARRED_BUTTON, ModBLocks.CHARRED_PLANKS);
        wallItem(ModBLocks.CHARRED_WALL, ModBLocks.CHARRED_PLANKS);
        evenSimplerBlockItem(ModBLocks.CHARRED_STAIRS);
        evenSimplerBlockItem(ModBLocks.CHARRED_SLAB);
        evenSimplerBlockItem(ModBLocks.CHARRED_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBLocks.CHARRED_FENCE_GATE);
        trapdoorItem(ModBLocks.CHARRED_TRAPDOOR);


        // ---------------------------(FLOWERS)--------------------------- //
        simpleBlockItemBlockTexture(ModBLocks.WINTER_ROSE);
        simpleBlockItemBlockTexture(ModBLocks.WILD_RADISH);
        simpleBlockItemBlockTexture(ModBLocks.WHITE_ROSE);
        simpleBlockItemBlockTexture(ModBLocks.THORN_BUSH);
        simpleBlockItemBlockTexture(ModBLocks.THISTLE);
        simpleBlockItemBlockTexture(ModBLocks.TANSY);
        simpleBlockItemBlockTexture(ModBLocks.SPICEFLOWER);
        simpleBlockItemBlockTexture(ModBLocks.SEDGE);
        simpleBlockItemBlockTexture(ModBLocks.SAFFRON_CROCUS);
        simpleBlockItemBlockTexture(ModBLocks.ROSE);
        simpleBlockItemBlockTexture(ModBLocks.POISON_KISSES);
        simpleBlockItemBlockTexture(ModBLocks.PENNYROYAL);
        simpleBlockItemBlockTexture(ModBLocks.OPIUM_POPPY_WILD);
        simpleBlockItemBlockTexture(ModBLocks.NIGHTSHADE);
        simpleBlockItemBlockTexture(ModBLocks.MOONBLOOM);
        simpleBlockItemBlockTexture(ModBLocks.LUNGWORT);
        simpleBlockItemBlockTexture(ModBLocks.LIVERWORT);
        simpleBlockItemBlockTexture(ModBLocks.LAVENDER);
        simpleBlockItemBlockTexture(ModBLocks.LADYS_LACE);
        simpleBlockItemBlockTexture(ModBLocks.GORSE);
        simpleBlockItemBlockTexture(ModBLocks.GOLDENROD);
        simpleBlockItemBlockTexture(ModBLocks.GOLDENCUP);
        simpleBlockItemBlockTexture(ModBLocks.GOATHEAD);
        simpleBlockItemBlockTexture(ModBLocks.GINGER);
        simpleBlockItemBlockTexture(ModBLocks.GILLYFLOWER);
        simpleBlockItemBlockTexture(ModBLocks.FROSTFIRE);
        simpleBlockItemBlockTexture(ModBLocks.FORGET_ME_NOT);
        simpleBlockItemBlockTexture(ModBLocks.EVENING_STAR);
        simpleBlockItemBlockTexture(ModBLocks.DUSKY_ROSE);
        simpleBlockItemBlockTexture(ModBLocks.DRAGONS_BREATH);
        simpleBlockItemBlockTexture(ModBLocks.COLDSNAP);
        simpleBlockItemBlockTexture(ModBLocks.BLUE_ROSE);
        simpleBlockItemBlockTexture(ModBLocks.BLOODBLOOM);
        simpleBlockItemBlockTexture(ModBLocks.BLACK_LOTUS);

        // ---------------------------(STONE)--------------------------- //
        //REDKEEP
        wallItem(ModBLocks.REDKEEP_STONE_WALL, ModBLocks.REDKEEP_STONE_BLOCK);
        evenSimplerBlockItem(ModBLocks.REDKEEP_STONE_STAIRS);
        evenSimplerBlockItem(ModBLocks.REDKEEP_STONE_SLAB);


        // Replace all 19 variant model loops with this

        for (Map<Integer, ModBLocks.BlockSet> variants : List.of(
                ModBLocks.BLACKSTONE_VARIANTS,  ModBLocks.BASALT_VARIANTS,
                ModBLocks.BRICKS_VARIANTS,      ModBLocks.CALCITE_VARIANTS,
                ModBLocks.CDEEPSLATE_VARIANTS,  ModBLocks.GRANITE_VARIANTS,
                ModBLocks.REDKEEP_VARIANTS,     ModBLocks.ANDESITE_VARIANTS,
                ModBLocks.TUFF_VARIANTS,        ModBLocks.DIORITE_VARIANTS,
                ModBLocks.QUARTZ_VARIANTS,      ModBLocks.RSANDSTONE_VARIANTS,
                ModBLocks.MUD_BRICK_VARIANTS,   ModBLocks.SANDSTONE_VARIANTS,
                ModBLocks.STONE_VARIANTS,       ModBLocks.SSTONE_VARIANTS,
                ModBLocks.BONE_VARIANTS,        ModBLocks.DRIPSTONE_VARIANTS,
                ModBLocks.PACKED_ICE_VARIANTS
        )) {
            for (ModBLocks.BlockSet set : variants.values()) {
                wallItem(set.wall(), set.base());
                evenSimplerBlockItem(set.stairs());
                evenSimplerBlockItem(set.slab());
            }
        }


        // ---------------------------(CRAFTING ITEMS)--------------------------- //
        // Register item models for crafting items
        simpleItem(ModItems.BOAR_INTESTINES);
        simpleItem(ModItems.BOAR_TUSK);
        simpleItem(ModItems.IVORY);
        simpleItem(ModItems.IVORY_SHARD);
        simpleItem(ModItems.FUR);
        simpleItem(ModItems.BOILED_LEATHER);
        simpleItem(ModItems.WEIRWOOD_STICK);
        simpleItem(ModItems.BRONZE_CHAIN_LINK);
        simpleItem(ModItems.BRONZE_CHAIN);
        simpleItem(ModItems.BRONZE_PLATE);
        simpleItem(ModItems.CLOTH);
        simpleItem(ModItems.NAIL);
        simpleItem(ModItems.IRON_CHAIN_LINK);
        simpleItem(ModItems.IRON_CHAIN);
        handheldItem(ModItems.HAMMER); // a tool: held like a pickaxe
        simpleItem(ModItems.IRON_PLATE);
        simpleItem(ModItems.NOBLE_PLATE);
        simpleItem(ModItems.STEEL_BOOTS);
        simpleItem(ModItems.STEEL_CHAIN_LINK);
        simpleItem(ModItems.STEEL_CHAIN);
        simpleItem(ModItems.STEEL_CHESTPLATE);
        simpleItem(ModItems.STEEL_HELMET);
        simpleItem(ModItems.STEEL_LEGGINGS);
        simpleItem(ModItems.STEEL_PLATE);
        simpleItem(ModItems.UPGRADE_KIT_BRONZE);
        simpleItem(ModItems.UPGRADE_KIT_IRON);
        simpleItem(ModItems.UPGRADE_KIT_NOBLE);
        simpleItem(ModItems.UPGRADE_KIT_STEEL);
        simpleItem(ModItems.UPGRADE_KIT_BRONZE_PLATE);
        simpleItem(ModItems.UPGRADE_KIT_CHIEF);
        simpleItem(ModItems.UPGRADE_KIT_FUR);
        simpleItem(ModItems.UPGRADE_KIT_LEATHER);











        // ---------------------------(SIGNS)--------------------------- //
        simpleItem(ModItems.WEIRWOOD_SIGN);
        simpleItem(ModItems.WEIRWOOD_HANGING_SIGN);
        simpleItem(ModItems.ROTTEN_SIGN);
        simpleItem(ModItems.ROTTEN_HANGING_SIGN);

        simpleItem(ModItems.CHARRED_SIGN);
        simpleItem(ModItems.CHARRED_HANGING_SIGN);


        // ---------------------------(BANNERS)--------------------------- //

        bannerPatternItem(BannerPatterns.ARRYN_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BAELISH_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BARATHEON_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BLACKWOOD_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BOLTON_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BRACKEN_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.CLEGANE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.DAYNE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.FREY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.GREYJOY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.HARLAW_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.HIGHTOWER_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.KARSTARK_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.LANNISTER_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MANDERLY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MORMONT_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.REDWYNE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.REED_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.REYNE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.ROYCE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.STARK_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TARGARYEN_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TARLY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TULLY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TYRELL_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.UMBER_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.VELARYON_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BLACKWOOD2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BOLTON1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BOLTON2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BOOK_STARK_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.BURLEY1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.CASSEL_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.CELTIGAR_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.CERWYN_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.CONDON_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.DAYNE2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.DUSTIN1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.DUSTIN2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.FLINT1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.FLINT2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.FORRESTER1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.FORRESTER2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.FREY2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.GLOVER_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.HARCLAY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.HIGHTOWER1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.HORNWOOD1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.HORNWOOD2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.KARSTARK2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.KNOTT_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.LIDDLE1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.LIDDLE2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.LOCKE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MAGNAR1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MAGNAR2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MANDERLY1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MANDERLY2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MANDERLY3_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MARSH_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MOLLEN1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MOLLEN2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.NORREY_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.ROYCE2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.RYSWELL1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.RYSWELL2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.RYSWELL3_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.STONEY_FISHER1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.STONEY_FISHER2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.STOUT_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TALLHART_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.TARGARYEN2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.UMBER1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.UMBER2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.UMBER3_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.WHITEHILL_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.WIDOWSWATCH1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.WIDOWSWATCH2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.WOOLFIELD_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.WULL_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.ARRYN1_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.ARRYN2_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.LIPPS_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.MOORE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.UPCLIFF_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.WAYNWOOD_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.DOWNWARDS_WEDGE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.UPWARDS_WEDGE_BANNER_PATTERN);
        bannerPatternItem(BannerPatterns.SHIELD_BANNER_PATTERN);





    }

    private @NotNull ItemModelBuilder saplingItem(@NotNull DeferredHolder<Block, Block> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("block/" + item.getId().getPath()));
    }

    // Method to create a simple item model
    private @NotNull ItemModelBuilder simpleItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private @NotNull ItemModelBuilder simpleBannerItem(DeferredItem<BannerPatternItem> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }



    // Method to create a handheld item model
    private ItemModelBuilder handheldItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/handheld")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }



    private ItemModelBuilder weaponItem(DeferredHolder<Item, Item> item,
                                        float[] thirdpersonTranslation,
                                        float[] thirdpersonScale,
                                        float[] thirdpersonRotation,
                                        float[] firstpersonTranslation,
                                        float[] firstpersonScale,
                                        float[] firstpersonRotation) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(thirdpersonRotation[0], -Math.abs(thirdpersonRotation[1]), thirdpersonRotation[2])
                .translation(thirdpersonTranslation[0], thirdpersonTranslation[1], thirdpersonTranslation[2])
                .scale(thirdpersonScale[0], thirdpersonScale[1], thirdpersonScale[2])
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(thirdpersonRotation[0], Math.abs(thirdpersonRotation[1]), -thirdpersonRotation[2])
                .translation(thirdpersonTranslation[0], thirdpersonTranslation[1], thirdpersonTranslation[2])
                .scale(thirdpersonScale[0], thirdpersonScale[1], thirdpersonScale[2])
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(firstpersonRotation[0], -Math.abs(firstpersonRotation[1]), firstpersonRotation[2])
                .translation(firstpersonTranslation[0], firstpersonTranslation[1], firstpersonTranslation[2])
                .scale(firstpersonScale[0], firstpersonScale[1], firstpersonScale[2])
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(firstpersonRotation[0], Math.abs(firstpersonRotation[1]), -firstpersonRotation[2])
                .translation(firstpersonTranslation[0], firstpersonTranslation[1], firstpersonTranslation[2])
                .scale(firstpersonScale[0], firstpersonScale[1], firstpersonScale[2])
                .end()
                .transform(ItemDisplayContext.GROUND)
                .scale(1.0f, 1.0f, 1.0f)
                .end()
                .transform(ItemDisplayContext.GUI)
                .rotation(0, 0, 0)
                .scale(1.2f, 1.2f, 1.2f)
                .end()
                .end();
    }

    public void trapdoorItem(DeferredHolder<Block, Block> block) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath() + "_bottom"));
    }

    private void fenceItem(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    private void buttonItem(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    private void wallItem(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    private void vanillaWallItem(DeferredHolder<Block, Block> block, Block baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.withDefaultNamespace("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock).getPath()));
    }

    private void modWallItem(DeferredHolder<Block, Block> block, String texture) {
        this.withExistingParent(
                        BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                        mcLoc("block/wall_inventory"))
                .texture("wall", modLoc("block/" + texture));
    }

    private ItemModelBuilder simpleBlockItem(DeferredHolder<Block, Block> block) {
        return withExistingParent(block.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + block.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(DeferredHolder<Block, Block> block) {
        return withExistingParent(block.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("block/" + block.getId().getPath()));
    }

    public void evenSimplerBlockItem(DeferredHolder<Block, Block> block) {
        this.withExistingParent(AGoTMod.MOD_ID + ":" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath()));
    }

    public void evenSimplerBlockItemVanilla(DeferredHolder<Block, Block> block, String vanillaPath) {
        String blockPath = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        if (blockPath.endsWith("_stairs")) {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/stairs"))
                    .texture("top",  mcLoc("block/" + vanillaPath))
                    .texture("side", mcLoc("block/" + vanillaPath))
                    .texture("bottom", mcLoc("block/" + vanillaPath));
        } else if (blockPath.endsWith("_slab") || blockPath.endsWith("_slabs")) {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/slab"))
                    .texture("top",    mcLoc("block/" + vanillaPath))
                    .texture("side",   mcLoc("block/" + vanillaPath))
                    .texture("bottom", mcLoc("block/" + vanillaPath));
        } else {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/" + vanillaPath));
        }
    }

    public void evenSimplerBlockItemVanillaMultiTexture(DeferredHolder<Block, Block> block,
                                                        String topPath, String sidePath, String bottomPath, boolean topIsVanilla, boolean sideIsVanilla, boolean bottomIsVanilla) {
        String blockPath = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        net.minecraft.resources.ResourceLocation top    = topIsVanilla    ? mcLoc("block/" + topPath)    : modLoc("block/" + topPath);
        net.minecraft.resources.ResourceLocation side   = sideIsVanilla   ? mcLoc("block/" + sidePath)   : modLoc("block/" + sidePath);
        net.minecraft.resources.ResourceLocation bottom = bottomIsVanilla ? mcLoc("block/" + bottomPath) : modLoc("block/" + bottomPath);

        if (blockPath.endsWith("_stairs")) {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/stairs"))
                    .texture("top",    top)
                    .texture("side",   side)
                    .texture("bottom", bottom);
        } else if (blockPath.endsWith("_slab") || blockPath.endsWith("_slabs")) {
            this.withExistingParent(AGoTMod.MOD_ID + ":" + blockPath, mcLoc("block/slab"))
                    .texture("top",    top)
                    .texture("side",   side)
                    .texture("bottom", bottom);
        }
    }

    public void furnitureItem(DeferredHolder<Item, Item> item) {
        String itemPath = BuiltInRegistries.ITEM.getKey(item.get()).getPath();

        String texturePath;

        if (itemPath.endsWith("_table") || itemPath.endsWith("_bench")) {
            texturePath = "item/" + itemPath + "_icon";
        } else if (itemPath.endsWith("_stool")) {
            String woodType = itemPath.replace("_stool", "");
            texturePath = "item/" + woodType + "_chair1_icon";
        } else if (itemPath.endsWith("_arm_chair")) {
            String woodType = itemPath.replace("_arm_chair", "");
            texturePath = "item/" + woodType + "_chair3_icon";
        } else if (itemPath.endsWith("_chair")) {
            String woodType = itemPath.replace("_chair", "");
            texturePath = "item/" + woodType + "_chair2_icon";
        } else {
            throw new IllegalArgumentException("Unknown furniture item type: " + itemPath);
        }

        this.withExistingParent(itemPath, mcLoc("item/generated"))
                .texture("layer0", modLoc(texturePath));
    }

    private ItemModelBuilder swordItem(DeferredHolder<Item, Item> item,
                                       int hilt, int blade, int crossguard,
                                       String hiltMaterial, String bladeMaterial, String crossguardMaterial,
                                       float[] thirdpersonTranslation,
                                       float[] thirdpersonScale,
                                       float[] thirdpersonRotation,
                                       float[] firstpersonTranslation,
                                       float[] firstpersonScale,
                                       float[] firstpersonRotation) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/sword_hilt_"       + hiltMaterial       + hilt))
                .texture("layer1", modLoc("item/sword_blade_"      + bladeMaterial      + blade))
                .texture("layer2", modLoc("item/sword_crossguard_" + crossguardMaterial + crossguard))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(thirdpersonRotation[0], -Math.abs(thirdpersonRotation[1]), thirdpersonRotation[2])
                .translation(thirdpersonTranslation[0], thirdpersonTranslation[1], thirdpersonTranslation[2])
                .scale(thirdpersonScale[0], thirdpersonScale[1], thirdpersonScale[2])
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(thirdpersonRotation[0], Math.abs(thirdpersonRotation[1]), -thirdpersonRotation[2])
                .translation(thirdpersonTranslation[0], thirdpersonTranslation[1], thirdpersonTranslation[2])
                .scale(thirdpersonScale[0], thirdpersonScale[1], thirdpersonScale[2])
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(firstpersonRotation[0], -Math.abs(firstpersonRotation[1]), firstpersonRotation[2])
                .translation(firstpersonTranslation[0], firstpersonTranslation[1], firstpersonTranslation[2])
                .scale(firstpersonScale[0], firstpersonScale[1], firstpersonScale[2])
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(firstpersonRotation[0], Math.abs(firstpersonRotation[1]), -firstpersonRotation[2])
                .translation(firstpersonTranslation[0], firstpersonTranslation[1], firstpersonTranslation[2])
                .scale(firstpersonScale[0], firstpersonScale[1], firstpersonScale[2])
                .end()
                .transform(ItemDisplayContext.GROUND)
                .scale(1.0f, 1.0f, 1.0f)
                .end()
                .transform(ItemDisplayContext.GUI)
                .rotation(0, 0, 0)
                .scale(1.2f, 1.2f, 1.2f)
                .end()
                .end();
    }

// â”€â”€ Three convenience wrappers â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€

    private ItemModelBuilder ironSwordItem(DeferredHolder<Item, Item> item,
                                           int hilt, int blade, int crossguard,
                                           float[] tpTrans, float[] tpScale, float[] tpRot,
                                           float[] fpTrans, float[] fpScale, float[] fpRot) {
        return swordItem(item, hilt, blade, crossguard, "iron", "iron", "iron",
                tpTrans, tpScale, tpRot, fpTrans, fpScale, fpRot);
    }

    private ItemModelBuilder steelSwordItem(DeferredHolder<Item, Item> item,
                                            int hilt, int blade, int crossguard,
                                            float[] tpTrans, float[] tpScale, float[] tpRot,
                                            float[] fpTrans, float[] fpScale, float[] fpRot) {
        return swordItem(item, hilt, blade, crossguard, "steel", "steel", "steel",
                tpTrans, tpScale, tpRot, fpTrans, fpScale, fpRot);
    }

    private ItemModelBuilder nobleSwordItem(DeferredHolder<Item, Item> item,
                                            int hilt, int blade, int crossguard,
                                            float[] tpTrans, float[] tpScale, float[] tpRot,
                                            float[] fpTrans, float[] fpScale, float[] fpRot) {
        // noble uses "jeweled" for crossguards
        return swordItem(item, hilt, blade, crossguard, "noble", "noble", "jeweled",
                tpTrans, tpScale, tpRot, fpTrans, fpScale, fpRot);
    }


    /**
     * Every food (FoodCatalog): its own texture, or for a procedural pie or cake the template's untinted
     * bottom and its top, which the client tints with the food's colour (layer1, ModEventBusClientEvents).
     */
    private void foodModels() {
        for (net.darkflameproduction.agotmod.item.food.FoodCatalog.Food food
                : net.darkflameproduction.agotmod.item.food.FoodCatalog.FOODS) {
            ItemModelBuilder model = withExistingParent(food.id(), mcLoc("item/generated"));
            if (food.procedural()) {
                model.texture("layer0", modLoc("item/" + food.template().prefix + "_bottom_template"))
                        .texture("layer1", modLoc("item/" + food.template().prefix + "_top_template"));
            } else {
                model.texture("layer0", modLoc("item/" + food.id()));
            }
        }
    }

    private ItemModelBuilder spawnEggItem(DeferredHolder<Item, Item> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/template_spawn_egg"))
                .texture("layer0", modLoc("item/spawn_egg"))
                .texture("layer1", modLoc("item/spawn_egg_overlay"));
    }
    private @NotNull ItemModelBuilder bannerPatternItem(DeferredHolder<Item, BannerPatternItem> item) {
        // Same as vanilla: flat item using its own item/<name>_banner_pattern texture
        return withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

}


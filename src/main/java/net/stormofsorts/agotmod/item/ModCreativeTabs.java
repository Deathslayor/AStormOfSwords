// This code belongs to the package net.stormofsorts.agotmod.item
package net.stormofsorts.agotmod.item;

// Importing necessary classes from other packages
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.block.ModBLocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

// A utility class for creating custom creative mode tabs
public class ModCreativeTabs {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final RegistryObject<CreativeModeTab> AGOT_TAB = CREATIVE_MODE_TAB.register("agot_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBLocks.MINT_BLOCK.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {
                        /** Blocks */
                        pOutput.accept(ModBLocks.MINT_BLOCK.get()); // Adds the mint Working station block for villagers
                        // TIN
                        pOutput.accept(ModBLocks.TIN_ORE.get());
                        pOutput.accept(ModBLocks.RAW_TIN_BLOCK.get());
                        pOutput.accept(ModBLocks.DEEPSLATE_TIN_ORE.get());
                        pOutput.accept(ModBLocks.TIN_BLOCK.get());
                        // BRONZE
                        pOutput.accept(ModBLocks.BRONZE_BLOCK.get());
                        // Bricks
                        pOutput.accept(ModBLocks.STONE_BRICK_BUT_COOLER.get());
                        pOutput.accept(ModBLocks.DARK_STONE_BRICK.get());
                        // Kings LANDING BRICKS
                        pOutput.accept(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());

                        /** Items */
                        // COINS
                        pOutput.accept(ModItems.COIN.get());

                        // INGOTS
                        pOutput.accept(ModItems.TIN_INGOT.get());
                        pOutput.accept(ModItems.RAW_TIN.get());
                        pOutput.accept(ModItems.BRONZE_INGOT.get());
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModItems.STEEL_NUGGET.get());

                        /** Armour */
                        pOutput.accept(ModItems.STARK1_HELMET.get());
                        pOutput.accept(ModItems.STARK1_CHESTPLATE.get());
                        pOutput.accept(ModItems.STARK1_LEGGINGS.get());
                        pOutput.accept(ModItems.STARK1_BOOTS.get());

                        /** SPAWN EGGS */
                        pOutput.accept(ModItems.RHINO_SPANW_EGG.get());
                        pOutput.accept(ModItems.BEAR_SPANW_EGG.get());

                        /** TOOLS */
                        pOutput.accept(ModItems.BRONZE_SWORD.get());
                        pOutput.accept(ModItems.BRONZE_AXE.get());
                        pOutput.accept(ModItems.BRONZE_HOE.get());
                        pOutput.accept(ModItems.BRONZE_PICKAXE.get());
                        pOutput.accept(ModItems.BRONZE_SHOVEL.get());
                        pOutput.accept(ModItems.BRONZE_SPATHA.get());

                        pOutput.accept(ModItems.IRON_LONGSWORD.get());
                        pOutput.accept(ModItems.IRON_SPEAR.get());
                        pOutput.accept(ModItems.IRON_PIKE.get());
                        pOutput.accept(ModItems.IRON_DAGGER.get());
                        pOutput.accept(ModItems.IRON_MACE.get());
                        pOutput.accept(ModItems.IRON_BATTLE_AXE.get());

                        pOutput.accept(ModItems.STEEL_LONGSWORD.get());
                        pOutput.accept(ModItems.STEEL_SPEAR.get());
                        pOutput.accept(ModItems.STEEL_PIKE.get());
                        pOutput.accept(ModItems.STEEL_DAGGER.get());
                        pOutput.accept(ModItems.STEEL_MACE.get());
                        pOutput.accept(ModItems.STEEL_BATTLEAXE.get());
                        pOutput.accept(ModItems.STEEL_HALBERD.get());


                        pOutput.accept(ModItems.STEEL_SWORD.get());
                        pOutput.accept(ModItems.STEEL_AXE.get());
                        pOutput.accept(ModItems.STEEL_HOE.get());
                        pOutput.accept(ModItems.STEEL_PICKAXE.get());
                        pOutput.accept(ModItems.STEEL_SHOVEL.get());

                        /** FOODS */
                        pOutput.accept(ModItems.RAW_BEAR_MEAT.get());
                        pOutput.accept(ModItems.COOKED_BEAR_MEAT.get());
                        pOutput.accept(ModItems.RAW_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_BLOOD_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_WHITE_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_WHITE_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_BLOOD_SAUSAGE.get());

                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}

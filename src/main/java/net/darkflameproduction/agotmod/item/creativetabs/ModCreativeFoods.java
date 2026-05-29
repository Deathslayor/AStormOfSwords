// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// A utility class for creating custom creative mode tabs
public class ModCreativeFoods {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_FOODS = CREATIVE_MODE_TAB.register("agot_tab_foods",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RAW_BACON.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_foods")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {


                        // ── Meats & Sausages ────────────────────────────────────────
                        pOutput.accept(ModItems.RAW_AUROCHS.get());
                        pOutput.accept(ModItems.COOKED_AUROCHS.get());
                        pOutput.accept(ModItems.RAW_BEAR_MEAT.get());
                        pOutput.accept(ModItems.COOKED_BEAR_MEAT.get());
                        pOutput.accept(ModItems.RAW_BOAR_VENISON.get());
                        pOutput.accept(ModItems.COOKED_BOAR_VENISON.get());
                        pOutput.accept(ModItems.RAW_DEER_VENISON.get());
                        pOutput.accept(ModItems.COOKED_DEER_VENISON.get());
                        pOutput.accept(ModItems.RAW_DOG.get());
                        pOutput.accept(ModItems.COOKED_DOG.get());
                        pOutput.accept(ModItems.RAW_DORMICE.get());
                        pOutput.accept(ModItems.COOKED_DORMICE.get());
                        pOutput.accept(ModItems.RAW_FROG.get());
                        pOutput.accept(ModItems.COOKED_FROG.get());
                        pOutput.accept(ModItems.RAW_GOAT_MEAT.get());
                        pOutput.accept(ModItems.COOKED_GOAT_MEAT.get());
                        pOutput.accept(ModItems.RAW_HARE_MEAT.get());
                        pOutput.accept(ModItems.COOKED_HARE_MEAT.get());
                        pOutput.accept(ModItems.RAW_HORSE_MEAT.get());
                        pOutput.accept(ModItems.COOKED_HORSE_MEAT.get());
                        pOutput.accept(ModItems.RAW_LAMB.get());
                        pOutput.accept(ModItems.COOKED_LAMB.get());
                        pOutput.accept(ModItems.RAW_LOCUSTS.get());
                        pOutput.accept(ModItems.COOKED_LOCUSTS.get());
                        pOutput.accept(ModItems.RAW_MAMMOTH_MEAT.get());
                        pOutput.accept(ModItems.COOKED_MAMMOTH_MEAT.get());
                        pOutput.accept(ModItems.RAW_MAN_FLESH.get());
                        pOutput.accept(ModItems.COOKED_MAN_FLESH.get());
                        pOutput.accept(ModItems.RAW_RAT.get());
                        pOutput.accept(ModItems.COOKED_RAT.get());
                        pOutput.accept(ModItems.RAW_SNAKE.get());
                        pOutput.accept(ModItems.COOKED_SNAKE.get());
                        pOutput.accept(ModItems.RAW_SNAIL.get());
                        pOutput.accept(ModItems.COOKED_SNAIL.get());
                        pOutput.accept(ModItems.RAW_SQUIRREL.get());
                        pOutput.accept(ModItems.COOKED_SQUIRREL.get());
                        pOutput.accept(ModItems.RAW_SUCKLING_PIG.get());
                        pOutput.accept(ModItems.COOKED_SUCKLING_PIG.get());

// ── Offal & Organ Meats ──────────────────────────────────────
                        pOutput.accept(ModItems.RAW_BACON.get());
                        pOutput.accept(ModItems.COOKED_BACON.get());
                        pOutput.accept(ModItems.RAW_PIG_KIDNEYS.get());
                        pOutput.accept(ModItems.COOKED_PIG_KIDNEYS.get());
                        pOutput.accept(ModItems.RAW_PIG_LIVER.get());
                        pOutput.accept(ModItems.COOKED_PIG_LIVER.get());
                        pOutput.accept(ModItems.RAW_PIG_RIBS.get());
                        pOutput.accept(ModItems.COOKED_PIG_RIBS.get());
                        pOutput.accept(ModItems.RAW_GOOSE_LIVER.get());
                        pOutput.accept(ModItems.COOKED_GOOSE_LIVER.get());
                        pOutput.accept(ModItems.RAW_CALF_PANCREAS.get());
                        pOutput.accept(ModItems.COOKED_CALF_PANCREAS.get());
                        pOutput.accept(ModItems.HORSE_HEART.get());
                        pOutput.accept(ModItems.JELLIED_CALVES_BRAIN.get());
                        pOutput.accept(ModItems.MINCED_MEAT.get());
                        pOutput.accept(ModItems.PIG_INTESTINES.get());
                        pOutput.accept(ModItems.CALF_BRAIN.get());

// ── Sausages ─────────────────────────────────────────────────
                        pOutput.accept(ModItems.RAW_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_BLOOD_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_BLOOD_SAUSAGE.get());
                        pOutput.accept(ModItems.RAW_WHITE_SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_WHITE_SAUSAGE.get());

// ── Fowl ────────────────────────────────────────────────────
                        pOutput.accept(ModItems.RAW_PIGEON.get());
                        pOutput.accept(ModItems.COOKED_PIGEON.get());
                        pOutput.accept(ModItems.RAW_DUCK.get());
                        pOutput.accept(ModItems.COOKED_DUCK.get());
                        pOutput.accept(ModItems.RAW_GOOSE.get());
                        pOutput.accept(ModItems.COOKED_GOOSE.get());
                        pOutput.accept(ModItems.RAW_GULL.get());
                        pOutput.accept(ModItems.COOKED_GULL.get());
                        pOutput.accept(ModItems.RAW_HERON.get());
                        pOutput.accept(ModItems.COOKED_HERON.get());
                        pOutput.accept(ModItems.RAW_LARK.get());
                        pOutput.accept(ModItems.COOKED_LARK.get());
                        pOutput.accept(ModItems.RAW_PARTRIDGE.get());
                        pOutput.accept(ModItems.COOKED_PARTRIDGE.get());
                        pOutput.accept(ModItems.RAW_PEACOCK.get());
                        pOutput.accept(ModItems.COOKED_PEACOCK.get());
                        pOutput.accept(ModItems.RAW_QUAIL.get());
                        pOutput.accept(ModItems.COOKED_QUAIL.get());
                        pOutput.accept(ModItems.RAW_SWAN.get());
                        pOutput.accept(ModItems.COOKED_SWAN.get());
                        pOutput.accept(ModItems.RAW_CHICKEN_NUGGETS.get());
                        pOutput.accept(ModItems.COOKED_CHICKEN_NUGGETS.get());

// ── Fish & Seafood ──────────────────────────────────────────
                        pOutput.accept(ModItems.RAW_HERRING.get());
                        pOutput.accept(ModItems.COOKED_HERRING.get());
                        pOutput.accept(ModItems.PICKLED_HERRING.get());
                        pOutput.accept(ModItems.RAW_TROUT.get());
                        pOutput.accept(ModItems.COOKED_TROUT.get());
                        pOutput.accept(ModItems.RAW_EEL.get());
                        pOutput.accept(ModItems.COOKED_EEL.get());
                        pOutput.accept(ModItems.RAW_LAMPREY.get());
                        pOutput.accept(ModItems.COOKED_LAMPREY.get());
                        pOutput.accept(ModItems.RAW_PIKE.get());
                        pOutput.accept(ModItems.COOKED_PIKE.get());
                        pOutput.accept(ModItems.RAW_SARDINE.get());
                        pOutput.accept(ModItems.COOKED_SARDINE.get());
                        pOutput.accept(ModItems.RAW_MONKFISH.get());
                        pOutput.accept(ModItems.COOKED_MONKFISH.get());
                        pOutput.accept(ModItems.RAW_OCTOPUS.get());
                        pOutput.accept(ModItems.COOKED_OCTOPUS.get());
                        pOutput.accept(ModItems.RAW_WHITEFISH.get());
                        pOutput.accept(ModItems.COOKED_WHITEFISH.get());
                        pOutput.accept(ModItems.RAW_CRAB.get());
                        pOutput.accept(ModItems.COOKED_CRAB.get());
                        pOutput.accept(ModItems.RAW_LOBSTER.get());
                        pOutput.accept(ModItems.COOKED_LOBSTER.get());
                        pOutput.accept(ModItems.RAW_CLAM.get());
                        pOutput.accept(ModItems.COOKED_CLAM.get());
                        pOutput.accept(ModItems.RAW_MUSSELS.get());
                        pOutput.accept(ModItems.COOKED_MUSSELS.get());
                        pOutput.accept(ModItems.RAW_WINKLES.get());
                        pOutput.accept(ModItems.COOKED_WINKLES.get());

// ── Prepared & Preserved ────────────────────────────────────
                        pOutput.accept(ModItems.HONEYED_LOCUSTS.get());
                        pOutput.accept(ModItems.BEEF_AND_BARLEY_STEW.get());
                        pOutput.accept(ModItems.BEEF_STEW.get());
                        pOutput.accept(ModItems.MUTTON_STEW.get());
                        pOutput.accept(ModItems.SISTERS_STEW.get());
                        pOutput.accept(ModItems.BOWL_OF_BROWN.get());
                        pOutput.accept(ModItems.BREWIS.get());
                        pOutput.accept(ModItems.JERKY.get());
                        pOutput.accept(ModItems.HAM.get());

// ── Baked Goods & Prepared Dishes ───────────────────────────
                        pOutput.accept(ModItems.BISCUITS.get());
                        pOutput.accept(ModItems.BOWL_OF_BROWN.get());
                        pOutput.accept(ModItems.BARLEY_BREAD.get());
                        pOutput.accept(ModItems.BLACK_BREAD.get());
                        pOutput.accept(ModItems.HARD_BREAD.get());
                        pOutput.accept(ModItems.OATCAKE.get());
                        pOutput.accept(ModItems.BLACKBERRY_OATCAKE.get());
                        pOutput.accept(ModItems.PINENUT_OATCAKE.get());
                        pOutput.accept(ModItems.COD_CAKE.get());
                        pOutput.accept(ModItems.CREAM_CAKES.get());
                        pOutput.accept(ModItems.HONEY_CAKE.get());
                        pOutput.accept(ModItems.HONEYFINGERS.get());
                        pOutput.accept(ModItems.LEMON_CAKE.get());
                        pOutput.accept(ModItems.SPECIAL_CAKE.get());
                        pOutput.accept(ModItems.STALE_CAKE.get());
                        pOutput.accept(ModItems.SWEET_CAKE.get());
                        pOutput.accept(ModItems.WINTERCAKE.get());
                        pOutput.accept(ModItems.PIGEON_PIE.get());
                        pOutput.accept(ModItems.VENISON_PIE.get());
                        pOutput.accept(ModItems.LAMPREY_PIE.get());
                        pOutput.accept(ModItems.LOCUST_PIE.get());
                        pOutput.accept(ModItems.STRAWBERRY_PIE.get());
                        pOutput.accept(ModItems.APPLE_TART.get());
                        pOutput.accept(ModItems.BERRY_TARTS.get());
                        pOutput.accept(ModItems.SISTERS_STEW.get());
                        pOutput.accept(ModItems.PEA_SOUP.get());

// ── General ─────────────────────────────────────────────────
                        pOutput.accept(ModItems.CHEESE.get());
                        pOutput.accept(ModItems.GRAVY.get());
                        pOutput.accept(ModItems.HONEYCOMB_FOOD.get());
                        pOutput.accept(ModItems.JAMS.get());
                        pOutput.accept(ModItems.JELLIES.get());
                        pOutput.accept(ModItems.SHERBET.get());

// ── Cereals ─────────────────────────────────────────────────
                        pOutput.accept(ModItems.BARLEY.get());
                        pOutput.accept(ModItems.BLACK_RICE.get());
                        pOutput.accept(ModItems.OAT.get());
                        pOutput.accept(ModItems.RYE.get());
                        pOutput.accept(ModItems.AUTUMN_WHEAT.get());
                        pOutput.accept(ModItems.WINTER_WHEAT.get());
                        pOutput.accept(ModItems.CORN.get());

// ── Vegetables ──────────────────────────────────────────────
                        pOutput.accept(ModItems.BEAN.get());
                        pOutput.accept(ModItems.CABBAGE.get());
                        pOutput.accept(ModItems.CHICKPEA.get());
                        pOutput.accept(ModItems.CUCUMBER.get());
                        pOutput.accept(ModItems.DRAGON_PEPPER.get());
                        pOutput.accept(ModItems.GARLIC.get());
                        pOutput.accept(ModItems.GREEN_BEAN.get());
                        pOutput.accept(ModItems.HORSERADISH.get());
                        pOutput.accept(ModItems.LEEK.get());
                        pOutput.accept(ModItems.LENTILS.get());
                        pOutput.accept(ModItems.NEEP.get());
                        pOutput.accept(ModItems.OLIVES.get());
                        pOutput.accept(ModItems.OLIVE_OIL.get());
                        pOutput.accept(ModItems.ONION.get());
                        pOutput.accept(ModItems.PARSLEY.get());
                        pOutput.accept(ModItems.PEAS.get());
                        pOutput.accept(ModItems.PEPPER.get());
                        pOutput.accept(ModItems.PEPPERCORN.get());
                        pOutput.accept(ModItems.RADISH.get());
                        pOutput.accept(ModItems.RED_ONION.get());
                        pOutput.accept(ModItems.SPINACH.get());
                        pOutput.accept(ModItems.SQUASH.get());
                        pOutput.accept(ModItems.TURNIP.get());
                        pOutput.accept(ModItems.WILD_ONION.get());


// ── Vegetables ──────────────────────────────────────────────
                        pOutput.accept(ModItems.SPINACH_PIE.get());



// ── Fruits ──────────────────────────────────────────────────
                        pOutput.accept(ModItems.APRICOT.get());
                        pOutput.accept(ModItems.BLACKBERRY.get());
                        pOutput.accept(ModItems.BLOOD_ORANGE.get());
                        pOutput.accept(ModItems.BLUEBERRY.get());
                        pOutput.accept(ModItems.CHERRY.get());
                        pOutput.accept(ModItems.CRABAPPLE.get());
                        pOutput.accept(ModItems.DATE.get());
                        pOutput.accept(ModItems.DORNISH_PLUM.get());
                        pOutput.accept(ModItems.DRIED_APPLES.get());
                        pOutput.accept(ModItems.FIG.get());
                        pOutput.accept(ModItems.FIREPLUM.get());
                        pOutput.accept(ModItems.GRAPE.get());
                        pOutput.accept(ModItems.LEMON.get());
                        pOutput.accept(ModItems.LIME.get());
                        pOutput.accept(ModItems.MELON_SLICE_AGOT.get());
                        pOutput.accept(ModItems.MULBERRY.get());
                        pOutput.accept(ModItems.ORANGE.get());
                        pOutput.accept(ModItems.PEACH.get());
                        pOutput.accept(ModItems.PEAR.get());
                        pOutput.accept(ModItems.PERSIMMON.get());
                        pOutput.accept(ModItems.PLUM.get());
                        pOutput.accept(ModItems.POMEGRANATE.get());
                        pOutput.accept(ModItems.RAISINS.get());
                        pOutput.accept(ModItems.RASPBERRY.get());
                        pOutput.accept(ModItems.SMOKEBERRY.get());
                        pOutput.accept(ModItems.STRAWBERRY.get());
                        pOutput.accept(ModItems.WINTER_PEACH.get());

// ── Nuts ────────────────────────────────────────────────────
                        pOutput.accept(ModItems.ALMOND.get());
                        pOutput.accept(ModItems.CHESTNUT.get());
                        pOutput.accept(ModItems.PECAN.get());
                        pOutput.accept(ModItems.PINE_NUT.get());
                        pOutput.accept(ModItems.WALNUT.get());

// ── Herbs & Spices ──────────────────────────────────────────
                        pOutput.accept(ModItems.CARDAMOM.get());
                        pOutput.accept(ModItems.CINNAMON.get());
                        pOutput.accept(ModItems.CLARY_SAGE.get());
                        pOutput.accept(ModItems.CLOVES.get());
                        pOutput.accept(ModItems.CORIANDER.get());
                        pOutput.accept(ModItems.COTTON.get());
                        pOutput.accept(ModItems.CURRY.get());
                        pOutput.accept(ModItems.FENNEL.get());
                        //pOutput.accept(ModItems.GINGER.get());
                        pOutput.accept(ModItems.HEMP.get());
                        pOutput.accept(ModItems.HONEY.get());
                        pOutput.accept(ModItems.LEMONGRASS.get());
                        pOutput.accept(ModItems.MINT.get());
                        pOutput.accept(ModItems.MOLASSES.get());
                        pOutput.accept(ModItems.MUSTARD_SEED.get());
                        pOutput.accept(ModItems.NUTMEG.get());
                        pOutput.accept(ModItems.SAFFRON.get());
                        pOutput.accept(ModItems.SAGE.get());
                        pOutput.accept(ModItems.SALT.get());

// ── Drinks — Beer ───────────────────────────────────────────
                        pOutput.accept(ModItems.BEER.get());
                        pOutput.accept(ModItems.ALE.get());
                        pOutput.accept(ModItems.STOUT.get());

// ── Drinks — Liquor ─────────────────────────────────────────
                        pOutput.accept(ModItems.RUM.get());
                        pOutput.accept(ModItems.BLACKBELLY_RUM.get());
                        pOutput.accept(ModItems.BLACK_TAR_RUM.get());
                        pOutput.accept(ModItems.SPICED_RUM.get());
                        pOutput.accept(ModItems.MYRISH_FIRE.get());
                        pOutput.accept(ModItems.TYROSHI_PEAR_BRANDY.get());

// ── Drinks — Milk ───────────────────────────────────────────
                        pOutput.accept(ModItems.ALMOND_MILK.get());
                        pOutput.accept(ModItems.GOAT_MILK.get());
                        pOutput.accept(ModItems.MARES_MILK.get());
                        pOutput.accept(ModItems.FERMENTED_MILK.get());
                        pOutput.accept(ModItems.NAHSA.get());
                        pOutput.accept(ModItems.ICED_MILK.get());

// ── Drinks — Tea ────────────────────────────────────────────
                        pOutput.accept(ModItems.TEA.get());
                        pOutput.accept(ModItems.MINT_TEA.get());
                        pOutput.accept(ModItems.NETTLE_TEA.get());
                        pOutput.accept(ModItems.MOON_TEA.get());

// ── Drinks — Wine ───────────────────────────────────────────
                        pOutput.accept(ModItems.WINE.get());
                        pOutput.accept(ModItems.APPLE_WINE.get());
                        pOutput.accept(ModItems.GREEN_APPLE_WINE.get());
                        pOutput.accept(ModItems.ARBOR_GOLD.get());
                        pOutput.accept(ModItems.ARBOR_RED.get());
                        pOutput.accept(ModItems.DORNISH_RED.get());
                        pOutput.accept(ModItems.DREAMWINE.get());
                        pOutput.accept(ModItems.HONEYED_WINE.get());
                        pOutput.accept(ModItems.SPICED_WINE.get());
                        pOutput.accept(ModItems.STRONGWINE.get());
                        pOutput.accept(ModItems.SUMMERWINE.get());
                        pOutput.accept(ModItems.WINE_OF_COURAGE.get());

// ── Drinks — Other ──────────────────────────────────────────
                        pOutput.accept(ModItems.CIDER.get());
                        pOutput.accept(ModItems.HIPPOCRAS.get());
                        pOutput.accept(ModItems.MEAD.get());
                        pOutput.accept(ModItems.SHADE_OF_THE_EVENING.get());
                        pOutput.accept(ModItems.SUGAR_WATER.get());




                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}


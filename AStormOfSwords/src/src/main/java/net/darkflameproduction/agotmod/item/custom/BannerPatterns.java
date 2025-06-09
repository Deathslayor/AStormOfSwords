package net.darkflameproduction.agotmod.item.custom;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BannerPatterns {

    public static final DeferredRegister<Item> BANNER_PATTERN_ITEMS =
            DeferredRegister.create(Registries.ITEM, AGoTMod.MOD_ID);

    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS =
            DeferredRegister.create(Registries.BANNER_PATTERN, AGoTMod.MOD_ID);

    // Define the tag key as a static constant for consistency
    public static final TagKey<BannerPattern> TARGARYEN_PATTERN_TAG = TagKey.create(
            Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "pattern_item/targaryen"));

    // Register the banner pattern for runtime
    public static final DeferredHolder<BannerPattern, BannerPattern> TARGARYEN_PATTERN =
            BANNER_PATTERNS.register("targaryen", () -> new BannerPattern(
                    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen"),
                    "targaryen"));

    // Register the banner pattern item using the static tag reference
    public static final DeferredHolder<Item, BannerPatternItem> TARGARYEN_BANNER_PATTERN =
            BANNER_PATTERN_ITEMS.register("targaryen_banner_pattern", () -> {
                net.minecraft.resources.ResourceKey<Item> itemKey = net.minecraft.resources.ResourceKey.create(
                        Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen_banner_pattern"));
                return new BannerPatternItem(TARGARYEN_PATTERN_TAG, new Item.Properties()
                        .setId(itemKey)
                        .stacksTo(1)
                        .rarity(Rarity.EPIC));
            });

    public static void register(IEventBus eventBus) {
        BANNER_PATTERNS.register(eventBus);
        BANNER_PATTERN_ITEMS.register(eventBus);
    }
}
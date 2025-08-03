package net.darkflameproduction.agotmod.client;

import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.function.Supplier;

public class ModArmorSkinRenderer {

    public static final Map<String, Supplier<Helmet>> HELMETS = Map.ofEntries(
    Map.entry("bolton_levy", () -> new Helmet(ModItems.BOLTEN_LEVY_HELMET.get())),
    Map.entry("bolton_plate", () -> new Helmet(ModItems.BOLTEN_PLATE_HELMET.get())),
    Map.entry("bolton_noble", () -> new Helmet(ModItems.BOLTEN_NOBLE_HELMET.get())),
    Map.entry("manderly_levy", () -> new Helmet(ModItems.MANDERLY_LEVY_HELMET.get())),
    Map.entry("manderly_plate", () -> new Helmet(ModItems.MANDERLY_PLATE_HELMET.get())),
    Map.entry("manderly_noble", () -> new Helmet(ModItems.MANDERLY_NOBLE_HELMET.get())),
    Map.entry("stark_levy", () -> new Helmet(ModItems.STARK_LEVY_HELMET.get())),
    Map.entry("stark_plate", () -> new Helmet(ModItems.STARK_PLATE_HELMET.get())),
    Map.entry("stark_noble", () -> new Helmet(ModItems.STARK_NOBLE_PLATE_HELMET.get())),
    Map.entry("mountain_clan_levy", () -> new Helmet(ModItems.MOUNTAIN_CLAN_LEVY_HELMET.get())),
    Map.entry("mountain_clan_plate", () -> new Helmet(ModItems.MOUNTAIN_CLAN_PLATE_HELMET.get())),
    Map.entry("mountain_clan_chief", () -> new Helmet(ModItems.MOUNTAIN_CLAN_CHIEF_HELMET.get())),
    Map.entry("ironborn_levy", () -> new Helmet(ModItems.IRONBORN_LEVY_HELMET.get())),
    Map.entry("ironborn_plate", () -> new Helmet(ModItems.IRONBORN_PLATE_HELMET.get())),
    Map.entry("ironborn_noble", () -> new Helmet(ModItems.IRONBORN_NOBLE_HELMET.get())),
    Map.entry("wildling_fur", () -> new Helmet(ModItems.WILDLING_FUR_HELMET.get())),
    Map.entry("wildling_leather", () -> new Helmet(ModItems.WILDLING_LEATHER_HELMET.get())),
    Map.entry("wildling_chief", () -> new Helmet(ModItems.WILDLING_CHIEF_HELMET.get())),
    Map.entry("thenn_levy", () -> new Helmet(ModItems.THENN_LEVY_HELMET.get())),
    Map.entry("thenn_plate", () -> new Helmet(ModItems.THENN_PLATE_HELMET.get())),
    Map.entry("thenn_noble", () -> new Helmet(ModItems.THENN_NOBLE_HELMET.get())),
    Map.entry("nights_watch_ranger", () -> new Helmet(ModItems.NIGHT_WATCH_RANGER_HELMET.get())),
    Map.entry("nights_watch_leather", () -> new Helmet(ModItems.NIGHT_WATCH_LEATHER_HELMET.get())),
    Map.entry("nights_watch_elite", () -> new Helmet(ModItems.NIGHT_WATCH_ELITE_HELMET.get()))
);

public static final Map<String, Supplier<Chestplate>> CHESTPLATES = Map.ofEntries(
    Map.entry("bolton_levy", () -> new Chestplate(ModItems.BOLTEN_LEVY_CHESTPLATE.get())),
    Map.entry("bolton_plate", () -> new Chestplate(ModItems.BOLTEN_PLATE_CHESTPLATE.get())),
    Map.entry("bolton_noble", () -> new Chestplate(ModItems.BOLTEN_NOBLE_CHESTPLATE.get())),
    Map.entry("manderly_levy", () -> new Chestplate(ModItems.MANDERLY_LEVY_CHESTPLATE.get())),
    Map.entry("manderly_plate", () -> new Chestplate(ModItems.MANDERLY_PLATE_CHESTPLATE.get())),
    Map.entry("manderly_noble", () -> new Chestplate(ModItems.MANDERLY_NOBLE_CHESTPLATE.get())),
    Map.entry("stark_levy", () -> new Chestplate(ModItems.STARK_LEVY_CHESTPLATE.get())),
    Map.entry("stark_plate", () -> new Chestplate(ModItems.STARK_PLATE_CHESTPLATE.get())),
    Map.entry("stark_noble", () -> new Chestplate(ModItems.STARK_NOBLE_PLATE_CHESTPLATE.get())),
    Map.entry("mountain_clan_levy", () -> new Chestplate(ModItems.MOUNTAIN_CLAN_LEVY_CHESTPLATE.get())),
    Map.entry("mountain_clan_plate", () -> new Chestplate(ModItems.MOUNTAIN_CLAN_PLATE_CHESTPLATE.get())),
    Map.entry("mountain_clan_chief", () -> new Chestplate(ModItems.MOUNTAIN_CLAN_CHIEF_CHESTPLATE.get())),
    Map.entry("ironborn_levy", () -> new Chestplate(ModItems.IRONBORN_LEVY_CHESTPLATE.get())),
    Map.entry("ironborn_plate", () -> new Chestplate(ModItems.IRONBORN_PLATE_CHESTPLATE.get())),
    Map.entry("ironborn_noble", () -> new Chestplate(ModItems.IRONBORN_NOBLE_CHESTPLATE.get())),
    Map.entry("wildling_fur", () -> new Chestplate(ModItems.WILDLING_FUR_CHESTPLATE.get())),
    Map.entry("wildling_leather", () -> new Chestplate(ModItems.WILDLING_LEATHER_CHESTPLATE.get())),
    Map.entry("wildling_chief", () -> new Chestplate(ModItems.WILDLING_CHIEF_CHESTPLATE.get())),
    Map.entry("thenn_levy", () -> new Chestplate(ModItems.THENN_LEVY_CHESTPLATE.get())),
    Map.entry("thenn_plate", () -> new Chestplate(ModItems.THENN_PLATE_CHESTPLATE.get())),
    Map.entry("thenn_noble", () -> new Chestplate(ModItems.THENN_NOBLE_CHESTPLATE.get())),
    Map.entry("nights_watch_ranger", () -> new Chestplate(ModItems.NIGHT_WATCH_RANGER_CHESTPLATE.get())),
    Map.entry("nights_watch_leather", () -> new Chestplate(ModItems.NIGHT_WATCH_LEATHER_CHESTPLATE.get())),
    Map.entry("nights_watch_elite", () -> new Chestplate(ModItems.NIGHT_WATCH_ELITE_CHESTPLATE.get()))
);

public static final Map<String, Supplier<Leggings>> LEGGINGS = Map.ofEntries(
    Map.entry("bolton_levy", () -> new Leggings(ModItems.BOLTEN_LEVY_LEGGINGS.get())),
    Map.entry("bolton_plate", () -> new Leggings(ModItems.BOLTEN_PLATE_LEGGINGS.get())),
    Map.entry("bolton_noble", () -> new Leggings(ModItems.BOLTEN_NOBLE_LEGGINGS.get())),
    Map.entry("manderly_levy", () -> new Leggings(ModItems.MANDERLY_LEVY_LEGGINGS.get())),
    Map.entry("manderly_plate", () -> new Leggings(ModItems.MANDERLY_PLATE_LEGGINGS.get())),
    Map.entry("manderly_noble", () -> new Leggings(ModItems.MANDERLY_NOBLE_LEGGINGS.get())),
    Map.entry("stark_levy", () -> new Leggings(ModItems.STARK_LEVY_LEGGINGS.get())),
    Map.entry("stark_plate", () -> new Leggings(ModItems.STARK_PLATE_LEGGINGS.get())),
    Map.entry("stark_noble", () -> new Leggings(ModItems.STARK_NOBLE_PLATE_LEGGINGS.get())),
    Map.entry("mountain_clan_levy", () -> new Leggings(ModItems.MOUNTAIN_CLAN_LEVY_LEGGINGS.get())),
    Map.entry("mountain_clan_plate", () -> new Leggings(ModItems.MOUNTAIN_CLAN_PLATE_LEGGINGS.get())),
    Map.entry("mountain_clan_chief", () -> new Leggings(ModItems.MOUNTAIN_CLAN_CHIEF_LEGGINGS.get())),
    Map.entry("ironborn_levy", () -> new Leggings(ModItems.IRONBORN_LEVY_LEGGINGS.get())),
    Map.entry("ironborn_plate", () -> new Leggings(ModItems.IRONBORN_PLATE_LEGGINGS.get())),
    Map.entry("ironborn_noble", () -> new Leggings(ModItems.IRONBORN_NOBLE_LEGGINGS.get())),
    Map.entry("wildling_fur", () -> new Leggings(ModItems.WILDLING_FUR_LEGGINGS.get())),
    Map.entry("wildling_leather", () -> new Leggings(ModItems.WILDLING_LEATHER_LEGGINGS.get())),
    Map.entry("wildling_chief", () -> new Leggings(ModItems.WILDLING_CHIEF_LEGGINGS.get())),
    Map.entry("thenn_levy", () -> new Leggings(ModItems.THENN_LEVY_LEGGINGS.get())),
    Map.entry("thenn_plate", () -> new Leggings(ModItems.THENN_PLATE_LEGGINGS.get())),
    Map.entry("thenn_noble", () -> new Leggings(ModItems.THENN_NOBLE_LEGGINGS.get())),
    Map.entry("nights_watch_ranger", () -> new Leggings(ModItems.NIGHT_WATCH_RANGER_LEGGINGS.get())),
    Map.entry("nights_watch_leather", () -> new Leggings(ModItems.NIGHT_WATCH_LEATHER_LEGGINGS.get())),
    Map.entry("nights_watch_elite", () -> new Leggings(ModItems.NIGHT_WATCH_ELITE_LEGGINGS.get()))
);

public static final Map<String, Supplier<Boots>> BOOTS = Map.ofEntries(
    Map.entry("bolton_levy", () -> new Boots(ModItems.BOLTEN_LEVY_BOOTS.get())),
    Map.entry("bolton_plate", () -> new Boots(ModItems.BOLTEN_PLATE_BOOTS.get())),
    Map.entry("bolton_noble", () -> new Boots(ModItems.BOLTEN_NOBLE_BOOTS.get())),
    Map.entry("manderly_levy", () -> new Boots(ModItems.MANDERLY_LEVY_BOOTS.get())),
    Map.entry("manderly_plate", () -> new Boots(ModItems.MANDERLY_PLATE_BOOTS.get())),
    Map.entry("manderly_noble", () -> new Boots(ModItems.MANDERLY_NOBLE_BOOTS.get())),
    Map.entry("stark_levy", () -> new Boots(ModItems.STARK_LEVY_BOOTS.get())),
    Map.entry("stark_plate", () -> new Boots(ModItems.STARK_PLATE_BOOTS.get())),
    Map.entry("stark_noble", () -> new Boots(ModItems.STARK_NOBLE_PLATE_BOOTS.get())),
    Map.entry("mountain_clan_levy", () -> new Boots(ModItems.MOUNTAIN_CLAN_LEVY_BOOTS.get())),
    Map.entry("mountain_clan_plate", () -> new Boots(ModItems.MOUNTAIN_CLAN_PLATE_BOOTS.get())),
    Map.entry("mountain_clan_chief", () -> new Boots(ModItems.MOUNTAIN_CLAN_CHIEF_BOOTS.get())),
    Map.entry("ironborn_levy", () -> new Boots(ModItems.IRONBORN_LEVY_BOOTS.get())),
    Map.entry("ironborn_plate", () -> new Boots(ModItems.IRONBORN_PLATE_BOOTS.get())),
    Map.entry("ironborn_noble", () -> new Boots(ModItems.IRONBORN_NOBLE_BOOTS.get())),
    Map.entry("wildling_fur", () -> new Boots(ModItems.WILDLING_FUR_BOOTS.get())),
    Map.entry("wildling_leather", () -> new Boots(ModItems.WILDLING_LEATHER_BOOTS.get())),
    Map.entry("wildling_chief", () -> new Boots(ModItems.WILDLING_CHIEF_BOOTS.get())),
    Map.entry("thenn_levy", () -> new Boots(ModItems.THENN_LEVY_BOOTS.get())),
    Map.entry("thenn_plate", () -> new Boots(ModItems.THENN_PLATE_BOOTS.get())),
    Map.entry("thenn_noble", () -> new Boots(ModItems.THENN_NOBLE_BOOTS.get())),
    Map.entry("nights_watch_ranger", () -> new Boots(ModItems.NIGHT_WATCH_RANGER_BOOTS.get())),
    Map.entry("nights_watch_leather", () -> new Boots(ModItems.NIGHT_WATCH_LEATHER_BOOTS.get())),
    Map.entry("nights_watch_elite", () -> new Boots(ModItems.NIGHT_WATCH_ELITE_BOOTS.get()))
);
    public static record Helmet(Item helmet) {
        public boolean isWearingHelmet(Player player) {
            return player.getItemBySlot(EquipmentSlot.HEAD).getItem() == helmet;
        }
    }
    public static record Chestplate(Item chestplate) {
        public boolean isWearingChestplate(Player player) {
            return player.getItemBySlot(EquipmentSlot.CHEST).getItem() == chestplate;
        }
    }
    public static record Leggings(Item leggings) {
        public boolean isWearingLeggings(Player player) {
            return player.getItemBySlot(EquipmentSlot.LEGS).getItem() == leggings;
        }
    }
    public static record Boots(Item boots) {
        public boolean isWearingBoots(Player player) {
            return player.getItemBySlot(EquipmentSlot.FEET).getItem() == boots;
        }
    }
}
package net.darkflameproduction.agotmod.event.KeyMappings;

import com.mojang.blaze3d.platform.InputConstants;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;


public final class KeyBindings {
    //Creats keys And Defines Usages And Abilitys For Magic Etc
    public static final KeyBindings INSTANCE = new KeyBindings();

    private KeyBindings() {
    }

    public static final String CATEGORY = "key.categoriesmagic." + AGoTMod.MOD_ID;

    public final KeyMapping UseSpellOne = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_one",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY
    );
    public final KeyMapping UseSpellTwo = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_two",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );
    public final KeyMapping UseSpellThree = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_three",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );
    public final KeyMapping UseSpellFour = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_four",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY
    );
    public final KeyMapping UseSpellFive = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_five",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );
    public final KeyMapping UseSpellSix = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_six",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );
    public final KeyMapping UseSpellSeven = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_seven",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY
    );
    public final KeyMapping UseSpellEight = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_eight",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );
    public final KeyMapping UseSpellNine = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".use_spell_nine",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );
    public final KeyMapping OpenMagicMenu = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".open_magic_menu",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );
    public final KeyMapping OpenBasicMenu = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".open_basic_menu",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );
    public final KeyMapping OpenMap = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".open_map",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );

    public final KeyMapping SetWayPoint = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".set_way_point",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );

    public final KeyMapping ToggleMiniMap = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".toggle_mini_map",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_0, -1),
            CATEGORY

    );


}
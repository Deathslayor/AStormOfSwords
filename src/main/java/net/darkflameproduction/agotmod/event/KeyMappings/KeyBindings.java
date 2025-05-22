package net.darkflameproduction.agotmod.event.KeyMappings;

import com.mojang.blaze3d.platform.InputConstants;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public final class KeyBindings {
    //Creats keys And Defines Usages And Abilitys For Magic Etc
    public static final KeyBindings INSTANCE = new KeyBindings();

    private KeyBindings() {
    }

    public static final String CATEGORY = "key.categoriesmagic." + AGoTMod.MOD_ID;

    public final KeyMapping OpenCustomGUI = new KeyMapping(
            "key." + AGoTMod.MOD_ID + ".open_custom_gui",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(GLFW.GLFW_KEY_M, -1),  // 'M' key
            CATEGORY
    );



}
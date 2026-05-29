package net.darkflameproduction.agotmod.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, value = Dist.CLIENT)
public final class TitleScreenIconOverlay {
    private static final ResourceLocation ICON_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/title_icon.png");
    private static final String INSTAGRAM_URL = "https://www.instagram.com/philip_guldberg/";
    private static final int ICON_SIZE = 72;
    private static final int ICON_X = 16;
    private static final int ICON_Y = 16;
    private static final int FRAME_PADDING = 6;

    private TitleScreenIconOverlay() {
    }

    @SubscribeEvent
    public static void onScreenRender(ScreenEvent.Render.Post event) {
        if (event.getScreen() instanceof TitleScreen) {
            renderOnTitleScreen(event.getGuiGraphics());
        }
    }

    @SubscribeEvent
    public static void onMouseClick(ScreenEvent.MouseButtonPressed.Pre event) {
        Screen screen = event.getScreen();
        if (!(screen instanceof TitleScreen) || event.getButton() != 0) {
            return;
        }

        if (isWithinIcon(event.getMouseX(), event.getMouseY())) {
            ConfirmLinkScreen.confirmLinkNow(screen, INSTAGRAM_URL);
            event.setCanceled(true);
        }
    }

    public static void renderOnTitleScreen(GuiGraphics guiGraphics) {
        guiGraphics.fill(
                ICON_X - FRAME_PADDING,
                ICON_Y - FRAME_PADDING,
                ICON_X + ICON_SIZE + FRAME_PADDING,
                ICON_Y + ICON_SIZE + FRAME_PADDING,
                0x7A000000
        );

        RenderSystem.enableBlend();
        guiGraphics.blit(
                
                ICON_TEXTURE,
                ICON_X,
                ICON_Y,
                0,
                0,
                ICON_SIZE,
                ICON_SIZE,
                ICON_SIZE,
                ICON_SIZE
        );
    }

    private static boolean isWithinIcon(double mouseX, double mouseY) {
        return mouseX >= ICON_X - FRAME_PADDING
                && mouseX <= ICON_X + ICON_SIZE + FRAME_PADDING
                && mouseY >= ICON_Y - FRAME_PADDING
                && mouseY <= ICON_Y + ICON_SIZE + FRAME_PADDING;
    }
}


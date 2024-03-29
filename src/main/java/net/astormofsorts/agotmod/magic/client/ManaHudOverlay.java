package net.astormofsorts.agotmod.magic.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.astormofsorts.agotmod.AGoTMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;


public class ManaHudOverlay implements IGuiOverlay {
    private static final ResourceLocation FULL_MANA = new ResourceLocation(AGoTMod.MOD_ID,
            "textures/gui/full_mana.png");
    private static final ResourceLocation EMPTY_MANA = new ResourceLocation(AGoTMod.MOD_ID,
            "textures/gui/empty_mana.png");


    public static final IGuiOverlay HUD_MANA = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_MANA);
        for
        (int i = 0; i < 10; i++) {

        }


        RenderSystem.setShaderTexture(0, FULL_MANA);
        for
        (int i = 0; i < 10; i++) {
            if (ClientManaData.getPlayerMana() > i) {

            } else {
                break;
            }
        }
    });

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {

    }
}


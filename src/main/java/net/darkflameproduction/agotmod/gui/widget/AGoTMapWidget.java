package net.darkflameproduction.agotmod.gui.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.tocraft.ctgen.impl.network.SyncMapPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

public class AGoTMapWidget extends AbstractWidget {
    private static final float ZOOM_FACTOR = 1.1F;
    private static final int MOVE_SPEED = 10;

    private final Minecraft minecraft;
    private final ResourceLocation mapId;
    private final boolean pixelsAreChunks;
    private final int pixelOffsetX;
    private final int pixelOffsetY;
    private final int mapWidth;
    private final int mapHeight;
    private final double ratio;

    private int zoomedWidth = 0;
    private int zoomedHeight = 0;

    private double textureOffsetX = 0;
    private double textureOffsetY = 0;
    private float minZoom;
    private double zoom;

    private boolean showCursorPos;
    private boolean showPlayer;

    @Nullable
    public static AGoTMapWidget ofPacket(Minecraft minecraft, int x, int y, int width, int height, @NotNull SyncMapPacket packet) {
        ResourceLocation packetMapId = packet.getMapId();
        if (packetMapId == null) {
            return null;
        }

        return new AGoTMapWidget(
                minecraft,
                x,
                y,
                width,
                height,
                ResourceLocation.fromNamespaceAndPath(packetMapId.getNamespace(), "textures/gui/" + packetMapId.getPath() + ".png"),
                packet.isPixelsAreChunks(),
                packet.getXOffset(),
                packet.getYOffset(),
                packet.getMapWidth(),
                packet.getMapHeight()
        );
    }

    public AGoTMapWidget(Minecraft minecraft, int x, int y, int width, int height, ResourceLocation mapId, boolean pixelsAreChunks, int xOffset, int yOffset, int mapWidth, int mapHeight) {
        this(minecraft, x, y, width, height, mapId, pixelsAreChunks, xOffset, yOffset, mapWidth, mapHeight, defaultZoom(width, height, mapWidth, mapHeight), true, true);
    }

    public AGoTMapWidget(Minecraft minecraft, int x, int y, int width, int height, ResourceLocation mapId, boolean pixelsAreChunks, int xOffset, int yOffset, int mapWidth, int mapHeight, float minZoom, boolean showCursorPos, boolean showPlayer) {
        super(x, y, width, height, Component.literal("Map Widget"));
        this.minecraft = minecraft;
        this.pixelOffsetX = xOffset;
        this.pixelOffsetY = yOffset;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.ratio = (double) mapWidth / mapHeight;
        this.mapId = mapId;
        this.pixelsAreChunks = pixelsAreChunks;
        this.minZoom = minZoom;
        this.zoom = minZoom;
        this.showCursorPos = showCursorPos;
        this.showPlayer = showPlayer;

        updateZoomedWidth();
        updateZoomedHeight();
        resetTextureOffsets();
    }

    public float defaultZoom() {
        return defaultZoom(width, height, mapWidth, mapHeight);
    }

    private static float defaultZoom(int width, int height, int mapWidth, int mapHeight) {
        return Math.max((float) width / mapWidth, (float) height / mapHeight);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMinZoom(float minZoom) {
        this.minZoom = minZoom;
        if (zoom < minZoom) {
            zoom = minZoom;
        }
        updateZoomedWidth();
        updateZoomedHeight();
    }

    public void setHeight(int height) {
        this.height = height;
        updateZoomedHeight();
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        updateZoomedWidth();
    }

    public ResourceLocation getMapId() {
        return mapId;
    }

    public double getRatio() {
        return ratio;
    }

    public void resetTextureOffsets() {
        int playerX;
        int playerY;
        if (minecraft.player != null) {
            BlockPos blockPos = minecraft.player.blockPosition();
            int scaleShift = pixelsAreChunks ? 4 : 2;
            int pixelX = (blockPos.getX() >> scaleShift) + pixelOffsetX;
            int pixelY = (blockPos.getZ() >> scaleShift) + pixelOffsetY;
            playerX = (int) ((double) pixelX / mapWidth * zoomedWidth);
            playerY = (int) ((double) pixelY / mapHeight * zoomedHeight);
        } else {
            playerX = zoomedWidth / 2;
            playerY = zoomedHeight / 2;
        }

        setTextureOffsetX(playerX - width / 2.0);
        setTextureOffsetY(playerY - height / 2.0);
    }

    public void setTextureOffsetX(double textureOffsetX) {
        this.textureOffsetX = textureOffsetX;
        clampTextureOffsets();
    }

    public void setTextureOffsetY(double textureOffsetY) {
        this.textureOffsetY = textureOffsetY;
        clampTextureOffsets();
    }

    public int getTextureY() {
        return (int) (getY() - textureOffsetY);
    }

    public int getTextureX() {
        return (int) (getX() - textureOffsetX);
    }

    public int getZoomedHeight() {
        return zoomedHeight;
    }

    public int getZoomedWidth() {
        return zoomedWidth;
    }

    public void setZoom(double zoom) {
        this.zoom = Math.max(minZoom, zoom);
    }

    public double getZoom() {
        return zoom;
    }

    private void updateZoomedWidth() {
        zoomedWidth = (int) (mapWidth * zoom);
        clampTextureOffsets();
    }

    private void updateZoomedHeight() {
        zoomedHeight = (int) (mapHeight * zoom);
        clampTextureOffsets();
    }

    private void clampTextureOffsets() {
        textureOffsetX = Mth.clamp(textureOffsetX, 0, Math.max(0, zoomedWidth - width));
        textureOffsetY = Mth.clamp(textureOffsetY, 0, Math.max(0, zoomedHeight - height));
    }

    public void setShowCursorPos(boolean showCursorPos) {
        this.showCursorPos = showCursorPos;
    }

    public void setShowPlayer(boolean showPlayer) {
        this.showPlayer = showPlayer;
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics context, int mouseX, int mouseY, float delta) {
        if (minecraft == null || minecraft.player == null) {
            return;
        }

        updateZoomedWidth();
        updateZoomedHeight();

        context.enableScissor(getX(), getY(), getX() + width, getY() + height);
        context.blit(RenderType::guiTextured, mapId, getTextureX(), getTextureY(), 0, 0, zoomedWidth, zoomedHeight, zoomedWidth, zoomedHeight);

        if (showPlayer) {
            BlockPos blockPos = minecraft.player.blockPosition();
            int scaleShift = pixelsAreChunks ? 4 : 2;
            int pixelX = (blockPos.getX() >> scaleShift) + pixelOffsetX;
            int pixelY = (blockPos.getZ() >> scaleShift) + pixelOffsetY;
            int playerX = (int) (getTextureX() + (double) pixelX / mapWidth * zoomedWidth);
            int playerY = (int) (getTextureY() + (double) pixelY / mapHeight * zoomedHeight);

            playerX = Mth.clamp(playerX, getTextureX() + 4, getTextureX() - 4 + zoomedWidth);
            playerY = Mth.clamp(playerY, getTextureY() + 4, getTextureY() - 4 + zoomedHeight);

            ResourceLocation skin = minecraft.player.getSkin().texture();
            context.blit(RenderType::guiTextured, skin, playerX - 4, playerY - 4, 8.0f, 8, 8, 8, 8, 8, 64, 64);
            context.blit(RenderType::guiTextured, skin, playerX - 4, playerY - 4, 40.0f, 8, 8, 8, 8, 8, 64, 64);
        }

        if (isHovered && showCursorPos) {
            int mousePixelX = mousePixelX(mouseX);
            int mousePixelY = mousePixelY(mouseY);
            Component text = Component.translatable("ctgen.screen.mouse_pos", Component.translatable("ctgen.coordinates", mousePixelX, mousePixelY));
            int textWidth = minecraft.font.width(text);

            PoseStack pose = context.pose();
            pose.pushPose();
            pose.scale(0.75f, 0.75f, 1.0f);
            context.drawString(
                    minecraft.font,
                    text,
                    (int) (getX() / 0.75f + width / 1.5f - (float) textWidth / 2),
                    (int) ((getY() + (height - (float) height / 8)) / 0.75f),
                    0xFFFFFF
            );
            pose.popPose();
        }

        context.disableScissor();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean handled = super.keyPressed(keyCode, scanCode, modifiers);
        if (handled) {
            return true;
        }

        if (keyCode == GLFW.GLFW_KEY_W || keyCode == GLFW.GLFW_KEY_UP) {
            textureOffsetY -= MOVE_SPEED;
            clampTextureOffsets();
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_S || keyCode == GLFW.GLFW_KEY_DOWN) {
            textureOffsetY += MOVE_SPEED;
            clampTextureOffsets();
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_A || keyCode == GLFW.GLFW_KEY_LEFT) {
            textureOffsetX -= MOVE_SPEED;
            clampTextureOffsets();
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_D || keyCode == GLFW.GLFW_KEY_RIGHT) {
            textureOffsetX += MOVE_SPEED;
            clampTextureOffsets();
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_RIGHT_BRACKET || keyCode == GLFW.GLFW_KEY_KP_ADD) {
            zoom(ZOOM_FACTOR, (double) width / 2, (double) height / 2);
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_SLASH || keyCode == GLFW.GLFW_KEY_KP_SUBTRACT) {
            zoom(1 / ZOOM_FACTOR, (double) width / 2, (double) height / 2);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (active && visible && button == 1 && minecraft != null && minecraft.player != null && clicked(mouseX, mouseY) && isHovered) {
            int mousePixelX = mousePixelX(mouseX);
            int mousePixelY = mousePixelY(mouseY);
            if (minecraft.player.hasPermissions(2)) {
                minecraft.player.connection.sendCommand("ctgen teleport " + mousePixelX + " " + mousePixelY);
                playDownSound(minecraft.getSoundManager());
                active = false;
                return true;
            }
        }
        return false;
    }

    public int mousePixelX(double mouseX) {
        return (int) ((mouseX - getTextureX()) / zoomedWidth * mapWidth);
    }

    public int mousePixelY(double mouseY) {
        return (int) ((mouseY - getTextureY()) / zoomedHeight * mapHeight);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        double factor = 1.0;
        if (deltaY > 0) {
            factor = ZOOM_FACTOR;
        } else if (deltaY < 0) {
            factor = 1 / ZOOM_FACTOR;
        }

        if (isHovered) {
            zoom(factor, mouseX - getX(), mouseY - getY());
        } else {
            zoom(factor, (double) width / 2, (double) height / 2);
        }
        return true;
    }

    private void zoom(double factor, double relX, double relY) {
        double oldZoom = zoom;
        setZoom(zoom * factor);

        if (zoom != oldZoom) {
            double zoomRatio = zoom / oldZoom;
            textureOffsetX = (textureOffsetX + relX) * zoomRatio - relX;
            textureOffsetY = (textureOffsetY + relY) * zoomRatio - relY;
            clampTextureOffsets();
            updateZoomedHeight();
            updateZoomedWidth();
        }
    }

    @Override
    public void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        textureOffsetX -= dragX;
        textureOffsetY -= dragY;
        clampTextureOffsets();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }
}

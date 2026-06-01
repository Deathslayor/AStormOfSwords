package net.darkflameproduction.agotmod.gui.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.tocraft.ctgen.data.MapOverlayTextLoader;
import dev.tocraft.ctgen.data.MapWaypoint;
import dev.tocraft.ctgen.data.MapWaypointLoader;
import dev.tocraft.ctgen.impl.network.SyncMapPacket;
import dev.tocraft.ctgen.impl.screen.MapText;
import dev.tocraft.ctgen.rivers.River;
import dev.tocraft.ctgen.rivers.RiverNetworkLoader;
import dev.tocraft.ctgen.roads.Road;
import dev.tocraft.ctgen.roads.RoadNetworkLoader;
import dev.tocraft.ctgen.roads.Waypoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class AGoTMapWidget extends AbstractWidget {

    private static final float ZOOM_FACTOR = 1.1F;
    private static final int   MOVE_SPEED  = 10;

    private final Minecraft        minecraft;
    private final ResourceLocation mapTextureId;
    private final ResourceLocation mapId;
    private final int              pixelOffsetX;
    private final int              pixelOffsetY;
    private final int              mapWidth;
    private final int              mapHeight;
    private final double           ratio;
    private final int              scale;

    private int    zoomedWidth  = 0;
    private int    zoomedHeight = 0;

    private double textureOffsetX = 0;
    private double textureOffsetY = 0;
    private float  minZoom;
    private double zoom;

    private boolean showCursorPos;
    private boolean showPlayer;
    private boolean showTexts;
    private final List<MapText> textOverlays = new ArrayList<>();

    // ── Factory ───────────────────────────────────────────────────────────────

    @Nullable
    public static AGoTMapWidget ofPacket(Minecraft minecraft, int x, int y,
                                         int width, int height,
                                         @NotNull SyncMapPacket packet) {
        ResourceLocation packetMapId = packet.getMapId();
        if (packetMapId == null) return null;
        return new AGoTMapWidget(
                minecraft, x, y, width, height,
                ResourceLocation.fromNamespaceAndPath(
                        packetMapId.getNamespace(),
                        "textures/gui/" + packetMapId.getPath() + ".png"),
                packetMapId,
                packet.getXOffset(), packet.getYOffset(),
                packet.getMapWidth(), packet.getMapHeight(),
                packet.getScale()
        );
    }

    // ── Constructors ──────────────────────────────────────────────────────────

    public AGoTMapWidget(Minecraft minecraft, int x, int y, int width, int height,
                         ResourceLocation mapTextureId, ResourceLocation mapId,
                         int xOffset, int yOffset, int mapWidth, int mapHeight, int scale) {
        this(minecraft, x, y, width, height, mapTextureId, mapId,
                xOffset, yOffset, mapWidth, mapHeight, scale,
                defaultZoom(width, height, mapWidth, mapHeight),
                true, true, true);
    }

    public AGoTMapWidget(Minecraft minecraft, int x, int y, int width, int height,
                         ResourceLocation mapTextureId, ResourceLocation mapId,
                         int xOffset, int yOffset, int mapWidth, int mapHeight, int scale,
                         float minZoom,
                         boolean showCursorPos, boolean showPlayer, boolean showTexts) {
        super(x, y, width, height, Component.literal("Map Widget"));
        this.minecraft     = minecraft;
        this.pixelOffsetX  = xOffset;
        this.pixelOffsetY  = yOffset;
        this.mapWidth      = mapWidth;
        this.mapHeight     = mapHeight;
        this.ratio         = (double) mapWidth / mapHeight;
        this.mapTextureId  = mapTextureId;
        this.mapId         = mapId;
        this.scale         = scale;
        this.minZoom       = minZoom;
        this.zoom          = minZoom;
        this.showCursorPos = showCursorPos;
        this.showPlayer    = showPlayer;
        this.showTexts     = showTexts;
        this.textOverlays.addAll(
                MapOverlayTextLoader.ENTRIES.getOrDefault(mapId, List.of()));

        updateZoomedWidth();
        updateZoomedHeight();
        resetTextureOffsets();
    }

    // ── Getters / setters ─────────────────────────────────────────────────────

    public float defaultZoom() {
        return defaultZoom(width, height, mapWidth, mapHeight);
    }

    private static float defaultZoom(int w, int h, int mw, int mh) {
        return Math.max((float) w / mw, (float) h / mh);
    }

    public int    getMapWidth()    { return mapWidth; }
    public int    getMapHeight()   { return mapHeight; }
    public double getRatio()       { return ratio; }
    public double getZoom()        { return zoom; }
    public ResourceLocation getMapId()        { return mapId; }
    public ResourceLocation getMapTextureId() { return mapTextureId; }
    public double getReadableZoom()           { return zoom * mapWidth / width; }

    public void setMinZoom(float minZoom) {
        this.minZoom = minZoom;
        if (zoom < minZoom) zoom = minZoom;
        updateZoomedWidth();
        updateZoomedHeight();
    }

    public void setHeight(int height) { this.height = height; updateZoomedHeight(); }

    @Override public void setWidth(int width)  { super.setWidth(width);  updateZoomedWidth(); }
    @Override public void setX(int x)          { super.setX(x); }
    @Override public void setY(int y)          { super.setY(y); }

    public void setZoom(double zoom)        { this.zoom = Math.max(minZoom, zoom); }
    public void setShowCursorPos(boolean v) { this.showCursorPos = v; }
    public void setShowPlayer(boolean v)    { this.showPlayer = v; }
    public void setShowTexts(boolean v)     { this.showTexts = v; }

    public int getTextureX()     { return (int) (getX() - textureOffsetX); }
    public int getTextureY()     { return (int) (getY() - textureOffsetY); }
    public int getZoomedWidth()  { return zoomedWidth; }
    public int getZoomedHeight() { return zoomedHeight; }

    // ── Texture offset helpers ────────────────────────────────────────────────

    public void resetTextureOffsets() {
        int playerX, playerY;
        if (minecraft.player != null) {
            BlockPos blockPos = minecraft.player.blockPosition();
            int pixelX = (blockPos.getX() / (scale * 4)) + pixelOffsetX;
            int pixelY = (blockPos.getZ() / (scale * 4)) + pixelOffsetY;
            playerX = (int) ((double) pixelX / mapWidth  * zoomedWidth);
            playerY = (int) ((double) pixelY / mapHeight * zoomedHeight);
        } else {
            playerX = zoomedWidth  / 2;
            playerY = zoomedHeight / 2;
        }
        setTextureOffsetX(playerX - width  / 2.0);
        setTextureOffsetY(playerY - height / 2.0);
    }

    public void setTextureOffsetX(double v) { this.textureOffsetX = v; updateZoomedWidth(); }
    public void setTextureOffsetY(double v) { this.textureOffsetY = v; updateZoomedHeight(); }

    private void updateZoomedWidth() {
        zoomedWidth = (int) (mapWidth * zoom);
        if (minZoom >= defaultZoom()) {
            textureOffsetX = Mth.clamp(textureOffsetX, 0, Math.max(0, zoomedWidth - width));
        }
    }

    private void updateZoomedHeight() {
        zoomedHeight = (int) (mapHeight * zoom);
        if (minZoom >= defaultZoom()) {
            textureOffsetY = Mth.clamp(textureOffsetY, 0, Math.max(0, zoomedHeight - height));
        }
    }

    // ── Render ────────────────────────────────────────────────────────────────

    @Override
    protected void renderWidget(@NotNull GuiGraphics context, int mouseX, int mouseY, float delta) {
        if (minecraft == null || minecraft.player == null) return;

        updateZoomedWidth();
        updateZoomedHeight();

        // Original scissor — widget is in real screen coords
        context.flush();
        final double guiScale = minecraft.getWindow().getGuiScale();
        context.enableScissor(getX(), getY(), getX() + width, getY() + height);

        context.blit(mapTextureId,
                getTextureX(), getTextureY(), 0, 0,
                zoomedWidth, zoomedHeight, zoomedWidth, zoomedHeight);

        if (showPlayer) {
            BlockPos blockPos = minecraft.player.blockPosition();
            int pixelX  = (blockPos.getX() / (scale * 4)) + pixelOffsetX;
            int pixelY  = (blockPos.getZ() / (scale * 4)) + pixelOffsetY;
            int playerX = (int) (getTextureX() + (double) pixelX / mapWidth  * zoomedWidth);
            int playerY = (int) (getTextureY() + (double) pixelY / mapHeight * zoomedHeight);

            if (playerX < getTextureX() + 4)              playerX = getTextureX() + 4;
            if (playerY < getTextureY() + 4)              playerY = getTextureY() + 4;
            if (playerX > getTextureX() - 4 + zoomedWidth)  playerX = getTextureX() - 4 + zoomedWidth;
            if (playerY > getTextureY() - 4 + zoomedHeight) playerY = getTextureY() - 4 + zoomedHeight;

            ResourceLocation skin = minecraft.player.getSkin().texture();
            context.blit(skin, playerX - 4, playerY - 4, 0,  8.0f, 8.0f, 8, 8, 64, 64);
            context.blit(skin, playerX - 4, playerY - 4, 0, 40.0f, 8.0f, 8, 8, 64, 64);
        }

        RiverNetworkLoader.getNetwork().ifPresent(network -> {
            for (River river : network.rivers()) {
                if (!river.type().visibleOnMap()) continue;
                if (river.waypoints().size() < 2) continue;
                drawRiverSpline(context, river);
            }
        });

        RoadNetworkLoader.getNetwork().ifPresent(network -> {
            for (Road road : network.roads()) {
                List<Waypoint> waypoints = road.waypoints();
                if (waypoints.size() < 2) continue;
                for (int i = 0; i < waypoints.size() - 1; i++) {
                    drawRoadSegment(context, waypoints.get(i), waypoints.get(i + 1),
                            road.minZoom(), road.maxZoom());
                }
            }
        });

        for (MapWaypoint waypoint : MapWaypointLoader.getWaypoints(mapId)) {
            renderWaypoint(context, waypoint);
        }

        if (isHovered && showCursorPos) {
            int mousePixelX = mousePixelX(mouseX);
            int mousePixelY = mousePixelY(mouseY);
            int worldX = (mousePixelX - pixelOffsetX) * scale * 4;
            int worldZ = (mousePixelY - pixelOffsetY) * scale * 4;

            Component textPos  = Component.translatable("ctgen.screen.mouse_pos",
                    Component.translatable("ctgen.coordinates", worldX, worldZ));
            Component textZoom = Component.translatable("ctgen.screen.zoom",
                    String.format("%.2f", getReadableZoom()));

            PoseStack pose = context.pose();
            pose.pushPose();
            pose.scale(0.5f, 0.5f, 1f);
            int posWidth = minecraft.font.width(textPos);
            context.drawString(minecraft.font, textPos,
                    (int) (getX() / 0.5f + width / 1.0f - (float) posWidth / 2),
                    (int) ((getY() + (height - (float) height / 8)) / 0.5f),
                    0xFFFFFF);
            int zoomWidth = minecraft.font.width(textZoom);
            context.drawString(minecraft.font, textZoom,
                    (int) ((getX() + width) / 0.5f - zoomWidth - 4),
                    (int) ((getY() + 4) / 0.5f),
                    0xFFFFFF);
            pose.popPose();
        }

        if (showTexts) {
            double readableZoom = getReadableZoom();
            for (MapText entry : textOverlays) {
                if (readableZoom > entry.minZoom()
                        && (readableZoom < entry.maxZoom() || entry.maxZoom() == -1)) {
                    float opacity = entry.getOpacity(readableZoom);
                    int alpha = (int) (opacity * 255);
                    if (alpha <= 0) continue;
                    int color = (alpha << 24) | 0xFFFFFF;
                    int px = getTextureX() + (int) (entry.x() * zoom);
                    int py = getTextureY() + (int) (entry.y() * zoom);
                    PoseStack pose = context.pose();
                    pose.pushPose();
                    pose.translate(px, py, 0);
                    pose.mulPose(Axis.ZP.rotationDegrees(entry.rotation()));
                    pose.scale((float) zoom * entry.size(), (float) zoom * entry.size(), 1f);
                    Component text = Component.translatable(entry.text().getString())
                            .withStyle(entry.text().getStyle());
                    context.drawString(minecraft.font, text, 0, 0, color);
                    pose.popPose();
                }
            }
        }

        context.flush();
        context.disableScissor();
    }

    // ── Input ─────────────────────────────────────────────────────────────────

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) return true;
        switch (keyCode) {
            case GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_UP    -> { textureOffsetY -= MOVE_SPEED; updateZoomedHeight(); return true; }
            case GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_DOWN  -> { textureOffsetY += MOVE_SPEED; updateZoomedHeight(); return true; }
            case GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_LEFT  -> { textureOffsetX -= MOVE_SPEED; updateZoomedWidth();  return true; }
            case GLFW.GLFW_KEY_D, GLFW.GLFW_KEY_RIGHT -> { textureOffsetX += MOVE_SPEED; updateZoomedWidth();  return true; }
            case GLFW.GLFW_KEY_RIGHT_BRACKET, GLFW.GLFW_KEY_KP_ADD       -> { zoom(ZOOM_FACTOR,     width / 2.0, height / 2.0); return true; }
            case GLFW.GLFW_KEY_SLASH,          GLFW.GLFW_KEY_KP_SUBTRACT -> { zoom(1 / ZOOM_FACTOR, width / 2.0, height / 2.0); return true; }
        }
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (active && visible && button == 1
                && minecraft != null && minecraft.player != null
                && isMouseOver(mouseX, mouseY) && isHovered) {
            if (minecraft.player.hasPermissions(2)) {
                int mousePixelX = mousePixelX(mouseX);
                int mousePixelY = mousePixelY(mouseY);
                int worldX = (mousePixelX - pixelOffsetX) * scale * 4;
                int worldZ = (mousePixelY - pixelOffsetY) * scale * 4;
                minecraft.player.connection.sendCommand("ctgen teleport " + worldX + " " + worldZ);
                playDownSound(minecraft.getSoundManager());
                active = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        double factor = deltaY > 0 ? ZOOM_FACTOR : deltaY < 0 ? 1 / ZOOM_FACTOR : 1.0;
        if (isHovered) {
            zoom(factor, mouseX - getX(), mouseY - getY());
        } else {
            zoom(factor, width / 2.0, height / 2.0);
        }
        return true;
    }

    @Override
    public void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        textureOffsetX -= dragX;
        textureOffsetY -= dragY;
        updateZoomedWidth();
        updateZoomedHeight();
    }

    // ── Coordinate helpers ────────────────────────────────────────────────────

    public int mousePixelX(double mouseX) {
        return (int) ((mouseX - getTextureX()) / zoomedWidth  * mapWidth);
    }

    public int mousePixelY(double mouseY) {
        return (int) ((mouseY - getTextureY()) / zoomedHeight * mapHeight);
    }

    private void zoom(double fac, double relX, double relY) {
        double oldZoom = zoom;
        setZoom(zoom * fac);
        if (zoom != oldZoom) {
            double ratio = zoom / oldZoom;
            textureOffsetX = (textureOffsetX + relX) * ratio - relX;
            textureOffsetY = (textureOffsetY + relY) * ratio - relY;
            updateZoomedHeight();
            updateZoomedWidth();
        }
    }

    // ── Roads ─────────────────────────────────────────────────────────────────

    private void drawRoadSegment(@NotNull GuiGraphics context,
                                 @NotNull Waypoint from, @NotNull Waypoint to,
                                 float minZoom, float maxZoom) {
        double readableZoom = getReadableZoom();
        if (readableZoom < minZoom) return;
        if (maxZoom != -1 && readableZoom > maxZoom) return;

        float opacity = 1.0f;
        if (minZoom > 0) {
            float fadeRange = minZoom * 0.3f;
            if (readableZoom < minZoom + fadeRange)
                opacity = (float) ((readableZoom - minZoom) / fadeRange);
        }
        opacity = Math.max(0, Math.min(1, opacity));
        if (opacity <= 0) return;

        int alpha      = (int) (opacity * 255);
        int outerColor = (alpha << 24) | 0xAAAAAA;
        int innerColor = (alpha << 24) | 0xFFFFFF;
        double dotSpacing = 6.0;
        double worldDist  = Math.sqrt(Math.pow(to.x() - from.x(), 2) + Math.pow(to.z() - from.z(), 2));
        int    samples    = Math.max(256, (int) (worldDist / 2));

        List<double[]> screenPoints = new ArrayList<>(samples + 1);
        for (int i = 0; i <= samples; i++) {
            double t     = (double) i / samples;
            double midX  = (from.x() + to.x()) / 2.0;
            double midZ  = (from.z() + to.z()) / 2.0;
            double dx    = to.x() - from.x(), dz = to.z() - from.z();
            double len   = Math.sqrt(dx * dx + dz * dz);
            double ctrlX = midX + (-dz / len) * len * to.curve();
            double ctrlZ = midZ + ( dx / len) * len * to.curve();
            double wx    = (1-t)*(1-t)*from.x() + 2*(1-t)*t*ctrlX + t*t*to.x();
            double wz    = (1-t)*(1-t)*from.z() + 2*(1-t)*t*ctrlZ + t*t*to.z();
            screenPoints.add(new double[]{
                    getTextureX() + (wx / (scale * 4) + pixelOffsetX) / mapWidth  * zoomedWidth,
                    getTextureY() + (wz / (scale * 4) + pixelOffsetY) / mapHeight * zoomedHeight
            });
        }
        drawDotted(context, screenPoints, dotSpacing, outerColor, innerColor);
    }

    private void drawRiverSpline(@NotNull GuiGraphics context, @NotNull River river) {
        List<dev.tocraft.ctgen.roads.Waypoint> wps = river.waypoints();
        double totalDist = 0;
        for (int i = 0; i < wps.size() - 1; i++) {
            double dx = wps.get(i+1).x() - wps.get(i).x(), dz = wps.get(i+1).z() - wps.get(i).z();
            totalDist += Math.sqrt(dx*dx + dz*dz);
        }
        int samples = Math.max(256, (int)(totalDist / 2));
        List<double[]> screenPoints = new ArrayList<>(samples + 1);
        for (int i = 0; i <= samples; i++) {
            double[] pos = river.evaluateSpline((double) i / samples);
            screenPoints.add(new double[]{
                    getTextureX() + (pos[0] / (scale * 4) + pixelOffsetX) / mapWidth  * zoomedWidth,
                    getTextureY() + (pos[1] / (scale * 4) + pixelOffsetY) / mapHeight * zoomedHeight
            });
        }
        drawDotted(context, screenPoints, 3.0, 0xFF004499, 0xFF0066FF);
    }

    private void drawDotted(@NotNull GuiGraphics context, List<double[]> pts,
                            double spacing, int outer, int inner) {
        double acc = 0; boolean draw = true;
        for (int i = 1; i < pts.size(); i++) {
            double[] p = pts.get(i-1), c = pts.get(i);
            double dx = c[0]-p[0], dz = c[1]-p[1], len = Math.sqrt(dx*dx+dz*dz);
            if (len < 0.0001) continue;
            double walked = 0;
            while (walked < len) {
                double rem = spacing - acc;
                if (walked + rem > len) { acc += len - walked; break; }
                walked += rem; acc = 0; draw = !draw;
                if (draw) {
                    int px = (int) Math.round(p[0] + dx * walked/len);
                    int py = (int) Math.round(p[1] + dz * walked/len);
                    if (px >= getX() && px < getX()+width && py >= getY() && py < getY()+height) {
                        context.fill(px-1, py-1, px+2, py+2, outer);
                        context.fill(px,   py,   px+1, py+1, inner);
                    }
                }
            }
        }
    }

    private void renderWaypoint(@NotNull GuiGraphics context, @NotNull MapWaypoint waypoint) {
        double readableZoom = getReadableZoom();
        int screenX = getTextureX() + (int)((waypoint.x() / (scale*4.0) + pixelOffsetX) / mapWidth  * zoomedWidth);
        int screenY = getTextureY() + (int)((waypoint.z() / (scale*4.0) + pixelOffsetY) / mapHeight * zoomedHeight);
        if (screenX < getX() || screenX >= getX()+width || screenY < getY() || screenY >= getY()+height) return;

        boolean showDot = readableZoom >= waypoint.minZoom() && (waypoint.maxZoom()==-1 || readableZoom<=waypoint.maxZoom());
        if (showDot) {
            double zs = Math.min(readableZoom / Math.max(waypoint.minZoom(), 0.01f), 2.0);
            int or_ = (int) Math.round((1 + zs*0.8)*0.7), ir_ = Math.max(1, or_-1);
            float op = 1f;
            float fr = Math.max(waypoint.minZoom()*0.3f, 0.01f);
            if (readableZoom < waypoint.minZoom()+fr) op = (float)((readableZoom-waypoint.minZoom())/fr);
            if (waypoint.maxZoom()!=-1) { float fs=waypoint.maxZoom()*0.9f; if(readableZoom>fs) op=Math.min(op,(float)((waypoint.maxZoom()-readableZoom)/(waypoint.maxZoom()-fs))); }
            op = Math.max(0, Math.min(1, op));
            if (op > 0) {
                int a=(int)(op*255);
                drawFilledCircle(context, screenX, screenY, or_, ir_, (a<<24)|(waypoint.outerColor()&0xFFFFFF), (a<<24)|(waypoint.innerColor()&0xFFFFFF));
            }
        }

        boolean showText = readableZoom >= waypoint.textMinZoom() && (waypoint.textMaxZoom()==-1 || readableZoom<=waypoint.textMaxZoom());
        if (showText) {
            float op = 1f;
            float fr = Math.max(waypoint.textMinZoom()*0.3f, 0.01f);
            if (readableZoom < waypoint.textMinZoom()+fr) op = (float)((readableZoom-waypoint.textMinZoom())/fr);
            if (waypoint.textMaxZoom()!=-1) { float fs=waypoint.textMaxZoom()*0.9f; if(readableZoom>fs) op=Math.min(op,(float)((waypoint.textMaxZoom()-readableZoom)/(waypoint.textMaxZoom()-fs))); }
            op = Math.max(0, Math.min(1, op));
            if (op > 0) {
                int a=(int)(op*255), bgA=(int)(op*80);
                float ns=0.5f; int tw=minecraft.font.width(waypoint.name());
                int dr = showDot ? (int)Math.round((1+Math.min(readableZoom/Math.max(waypoint.minZoom(),0.01f),2.0)*0.8)*0.7) : 1;
                PoseStack pose = context.pose(); pose.pushPose(); pose.scale(ns,ns,1f);
                int nx=(int)((screenX/ns)-tw/2f), ny=(int)((screenY+dr+3)/ns);
                context.fill(nx-2, ny-2, nx+tw+2, ny+minecraft.font.lineHeight+2, (bgA<<24)|0x000000);
                context.drawString(minecraft.font, waypoint.name(), nx, ny, (a<<24)|0xFFFFFF, false);
                pose.popPose();
            }
        }
    }

    private void drawFilledCircle(@NotNull GuiGraphics context, int cx, int cy, int or_, int ir_, int oc, int ic) {
        for (int px=-or_; px<=or_; px++) for (int py=-or_; py<=or_; py++) {
            double d=Math.sqrt(px*px+py*py);
            if (d<=ir_) context.fill(cx+px,cy+py,cx+px+1,cy+py+1,ic);
            else if (d<=or_) context.fill(cx+px,cy+py,cx+px+1,cy+py+1,oc);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput n) {}
}
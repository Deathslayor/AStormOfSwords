package net.darkflameproduction.agotmod.client.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.core.component.DataComponents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TownNotificationOverlay {

    private static String currentMessage = "";
    private static String currentOwnership = "";
    private static String currentPopulation = "";
    private static String currentHouseName = "";
    private static long messageStartTime = 0;
    private static final long DISPLAY_DURATION = 5000;
    private static final long FADE_DURATION = 500;

    private static CompoundTag syncedBanner = null;

    private static final int BANNER_WIDTH  = 30;
    private static final int BANNER_HEIGHT = 60;
    private static final int BANNER_GAP    = 6;

    private static final ResourceLocation BANNER_BASE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner_base.png");

    private static final Map<String, ResourceLocation> BANNER_PATTERN_TEXTURES = buildPatternMap();

    private static Map<String, ResourceLocation> buildPatternMap() {
        Map<String, ResourceLocation> map = new HashMap<>();
        for (String house : new String[]{
                "arryn","baelish","baratheon","blackwood","bolton","bracken",
                "clegane","dayne","frey","greyjoy","harlaw","hightower",
                "karstark","lannister","manderly","mormont","redwyne","reed",
                "reyne","royce","stark","targaryen","tarly","tully",
                "tyrell","umber","velaryon"
        }) {
            map.put(house, ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/banner/" + house + ".png"));
        }
        map.put("base", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner_base.png"));
        for (String p : new String[]{
                "stripe_bottom","stripe_top","stripe_left","stripe_right","stripe_center",
                "stripe_middle","cross","straight_cross","diagonal_left","diagonal_right",
                "half_vertical","half_horizontal","half_vertical_right","half_horizontal_bottom",
                "circle","border","triangle_top","triangle_bottom"
        }) {
            map.put(p, ResourceLocation.fromNamespaceAndPath("minecraft",
                    "textures/entity/banner/" + p + ".png"));
        }
        return map;
    }

    public static void setSyncedBanner(CompoundTag bannerData) {
        syncedBanner = bannerData;
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.HOTBAR,
                ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_notification"),
                (guiGraphics, deltaTracker) -> renderTownNotification(guiGraphics, deltaTracker));
    }

    public static void showEntryMessage(String townName, String houseName, int population) {
        currentMessage    = "You Have Entered " + townName;
        currentHouseName  = houseName != null ? houseName.toLowerCase().trim() : "";
        currentOwnership  = (houseName != null && !houseName.trim().isEmpty())
                ? "Owned By House " + houseName : "Unclaimed Territory";
        currentPopulation = "Population: " + population;
        messageStartTime  = System.currentTimeMillis();
    }

    public static void showExitMessage(String townName) {
        currentMessage    = "You Have Left " + townName;
        currentOwnership  = "";
        currentPopulation = "";
        currentHouseName  = "";
        messageStartTime  = System.currentTimeMillis();
    }

    public static void clearMessage() {
        currentMessage    = "";
        currentOwnership  = "";
        currentPopulation = "";
        currentHouseName  = "";
        messageStartTime  = 0;
    }

    public static boolean isShowingMessage() {
        if (currentMessage.isEmpty()) return false;
        return (System.currentTimeMillis() - messageStartTime) < DISPLAY_DURATION;
    }

    private static void renderTownNotification(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        if (currentMessage.isEmpty()) return;

        long elapsed = System.currentTimeMillis() - messageStartTime;

        if (elapsed >= DISPLAY_DURATION) {
            currentMessage = ""; currentOwnership = ""; currentPopulation = ""; currentHouseName = "";
            return;
        }

        float alpha;
        if (elapsed < FADE_DURATION) {
            alpha = (float) elapsed / FADE_DURATION;
        } else if (elapsed > DISPLAY_DURATION - FADE_DURATION) {
            alpha = 1.0f - ((float) (elapsed - (DISPLAY_DURATION - FADE_DURATION)) / FADE_DURATION);
        } else {
            alpha = 1.0f;
        }
        alpha = Math.max(0.02f, Math.min(1.0f, alpha));

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        Font font = mc.font;

        Component messageComponent    = Component.literal(currentMessage);
        Component ownershipComponent  = currentOwnership.isEmpty()  ? null : Component.literal(currentOwnership);
        Component populationComponent = currentPopulation.isEmpty() ? null : Component.literal(currentPopulation);

        int messageWidth    = font.width(messageComponent);
        int ownershipWidth  = ownershipComponent  != null ? font.width(ownershipComponent)  : 0;
        int populationWidth = populationComponent != null ? font.width(populationComponent) : 0;
        int maxTextWidth    = Math.max(messageWidth, Math.max(ownershipWidth, populationWidth));
        int textHeight      = font.lineHeight;
        int lineCount       = 1 + (ownershipComponent != null ? 1 : 0) + (populationComponent != null ? 1 : 0);
        int totalTextHeight = (textHeight * lineCount) + ((lineCount - 1) * 2);

        int padding = 10;
        int panelW  = maxTextWidth + (padding * 2);
        int panelH  = totalTextHeight + padding;
        int panelX  = (screenWidth - panelW) / 2;
        int panelY  = 20;

        boolean hasBanner = !currentHouseName.isEmpty() && syncedBanner != null;
        int bannerY      = panelY + (panelH - BANNER_HEIGHT) / 2;
        int leftBannerX  = panelX - BANNER_GAP - BANNER_WIDTH;
        int rightBannerX = panelX + panelW + BANNER_GAP;

        int a   = (int) (alpha * 255) & 0xFF;
        int aBg = (int) (alpha * 160) & 0xFF;

        int colorBg         = (aBg << 24);
        int colorBorder     = (aBg << 24) | 0x00FFFFFF;
        int colorWhite      = (a   << 24) | 0x00FFFFFF;
        int colorUnclaimed  = (a   << 24) | 0x00FF6666;
        int colorClaimed    = (a   << 24) | 0x0066AAFF;
        int colorPopulation = (a   << 24) | 0x0066FF66;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.fill(panelX, panelY, panelX + panelW, panelY + panelH, colorBg);
        guiGraphics.fill(panelX,              panelY,              panelX + panelW, panelY + 1,          colorBorder);
        guiGraphics.fill(panelX,              panelY + panelH - 1, panelX + panelW, panelY + panelH,     colorBorder);
        guiGraphics.fill(panelX,              panelY,              panelX + 1,      panelY + panelH,     colorBorder);
        guiGraphics.fill(panelX + panelW - 1, panelY,              panelX + panelW, panelY + panelH,     colorBorder);

        int textY    = panelY + (panelH - totalTextHeight) / 2;
        int currentY = textY;

        guiGraphics.drawString(font, messageComponent,
                panelX + padding + (maxTextWidth - messageWidth) / 2, currentY, colorWhite, false);
        currentY += textHeight + 2;

        if (ownershipComponent != null) {
            int ownerColor = currentOwnership.equals("Unclaimed Territory") ? colorUnclaimed : colorClaimed;
            guiGraphics.drawString(font, ownershipComponent,
                    panelX + padding + (maxTextWidth - ownershipWidth) / 2, currentY, ownerColor, false);
            currentY += textHeight + 2;
        }

        if (populationComponent != null) {
            guiGraphics.drawString(font, populationComponent,
                    panelX + padding + (maxTextWidth - populationWidth) / 2, currentY, colorPopulation, false);
        }

        if (hasBanner) {
            drawBannerFromNbt(guiGraphics, mc, leftBannerX,  bannerY, alpha);
            drawBannerFromNbt(guiGraphics, mc, rightBannerX, bannerY, alpha);
        }

        RenderSystem.disableBlend();
    }

    private static void drawBannerFromNbt(GuiGraphics guiGraphics, Minecraft mc,
                                          int x, int y, float alpha) {
        if (syncedBanner == null || mc.level == null) return;
        try {
            ItemStack bannerStack = ItemStack.parseOptional(mc.level.registryAccess(), syncedBanner);
            if (bannerStack.isEmpty() || !(bannerStack.getItem() instanceof BannerItem bannerItem)) return;

            BannerPatternLayers patterns = bannerStack.get(DataComponents.BANNER_PATTERNS);

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            guiGraphics.pose().pushPose();

            drawTextureLayer(guiGraphics, x, y, bannerItem.getColor(), BANNER_BASE_TEXTURE, alpha);

            if (patterns != null) {
                for (BannerPatternLayers.Layer layer : patterns.layers()) {
                    String patternName = layer.pattern().value().assetId().getPath();
                    ResourceLocation tex = BANNER_PATTERN_TEXTURES.getOrDefault(patternName,
                            ResourceLocation.fromNamespaceAndPath("minecraft",
                                    "textures/entity/banner/" + patternName + ".png"));
                    drawTextureLayer(guiGraphics, x, y, layer.color(), tex, alpha);
                }
            }

            guiGraphics.pose().popPose();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        } catch (Exception e) {
            // Banner render failed silently
        }
    }

    private static void drawTextureLayer(GuiGraphics guiGraphics, int x, int y,
                                         net.minecraft.world.item.DyeColor color,
                                         ResourceLocation texture, float alpha) {
        int dyeColor = color.getTextureDiffuseColor();
        int tint = ((int)(alpha * 255) & 0xFF) << 24
                | ((dyeColor >> 16) & 0xFF) << 16
                | ((dyeColor >>  8) & 0xFF) <<  8
                |  (dyeColor        & 0xFF);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(
                net.minecraft.client.renderer.RenderType::guiTextured,
                texture,
                x, y,
                1.0f, 0.0f,
                BANNER_WIDTH, BANNER_HEIGHT,
                20, 40,
                64, 64,
                tint
        );
    }
}
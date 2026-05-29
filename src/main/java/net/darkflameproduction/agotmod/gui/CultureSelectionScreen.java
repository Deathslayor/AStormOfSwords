package net.darkflameproduction.agotmod.gui;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.Culture;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureGroup;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class CultureSelectionScreen extends Screen {

    private static final int TEXT_COLOR    = 0xFF000000;
    private static final int COLOR_HEADER  = 0xFF1A237E;
    private static final int COLOR_GOLD    = 0xFFB8860B;
    private static final int COLOR_GRAY    = 0xFF757575;
    private static final int COLOR_GREEN   = 0xFF2E7D32;
    private static final int COLOR_RED     = 0xFFD32F2F;
    private static final int COLOR_BLUE    = 0xFF1565C0;
    private static final int COLOR_SELECTED= 0xFF4A148C;

    // Cultures grouped for display
    private static final Map<CultureGroup, List<Culture>> CULTURE_MAP = new LinkedHashMap<>();

    static {
        for (CultureGroup group : CultureGroup.values()) {
            if (group == CultureGroup.NONE) continue;
            List<Culture> list = new ArrayList<>();
            for (Culture c : Culture.values()) {
                if (c.group == group) list.add(c);
            }
            CULTURE_MAP.put(group, list);
        }
    }

    // ── State ─────────────────────────────────────────────────────────────────

    private final BlockPos townHallPos;
    private final Screen   parent;

    // Step: 0 = pick group, 1 = pick culture, 2 = confirm
    private int step = 0;

    private CultureGroup selectedGroup   = null;
    private Culture      selectedCulture = null;

    public CultureSelectionScreen(BlockPos townHallPos, Screen parent) {
        super(Component.literal("Choose Culture"));
        this.townHallPos = townHallPos;
        this.parent      = parent;
    }

    // ── Init ──────────────────────────────────────────────────────────────────

    @Override
    protected void init() {
        super.init();
        buildStep();
    }

    private void buildStep() {
        clearWidgets();
        switch (step) {
            case 0 -> buildGroupStep();
            case 1 -> buildCultureStep();
            case 2 -> buildConfirmStep();
        }
    }

    // ── Step 0: pick group ────────────────────────────────────────────────────

    private void buildGroupStep() {
        int cx = width / 2;
        int startY = height / 2 - (CULTURE_MAP.size() * 26) / 2;
        int idx = 0;
        for (CultureGroup group : CULTURE_MAP.keySet()) {
            final CultureGroup g = group;
            int y = startY + idx * 26;
            addRenderableWidget(Button.builder(
                    Component.literal(group.displayName),
                    btn -> { selectedGroup = g; step = 1; buildStep(); }
            ).pos(cx - 80, y).size(160, 20).build());
            idx++;
        }
        // Back button
        addRenderableWidget(Button.builder(
                Component.literal("Back"),
                btn -> minecraft.setScreen(parent)
        ).pos(cx - 40, height - 40).size(80, 20).build());
    }

    // ── Step 1: pick culture ──────────────────────────────────────────────────

    private void buildCultureStep() {
        List<Culture> cultures = CULTURE_MAP.get(selectedGroup);
        int cx = width / 2;
        int cols   = 2;
        int btnW   = 150;
        int btnH   = 20;
        int gapX   = 10;
        int gapY   = 6;
        int totalW = cols * btnW + (cols - 1) * gapX;
        int rows   = (int) Math.ceil((double) cultures.size() / cols);
        int startX = cx - totalW / 2;
        int startY = height / 2 - (rows * (btnH + gapY)) / 2;

        for (int i = 0; i < cultures.size(); i++) {
            final Culture c = cultures.get(i);
            int col = i % cols;
            int row = i / cols;
            int bx  = startX + col * (btnW + gapX);
            int by  = startY + row * (btnH + gapY);
            addRenderableWidget(Button.builder(
                    Component.literal(c.displayName),
                    btn -> { selectedCulture = c; step = 2; buildStep(); }
            ).pos(bx, by).size(btnW, btnH).build());
        }

        // Back
        addRenderableWidget(Button.builder(
                Component.literal("Back"),
                btn -> { selectedGroup = null; step = 0; buildStep(); }
        ).pos(cx - 40, height - 40).size(80, 20).build());
    }

    // ── Step 2: confirm ───────────────────────────────────────────────────────

    private void buildConfirmStep() {
        int cx = width / 2;
        int cy = height / 2;

        // Confirm
        addRenderableWidget(Button.builder(
                Component.literal("Confirm"),
                btn -> sendAndClose()
        ).pos(cx - 90, cy + 30).size(80, 20).build());

        // Cancel
        addRenderableWidget(Button.builder(
                Component.literal("Cancel"),
                btn -> { selectedCulture = null; step = 0; buildStep(); }
        ).pos(cx + 10, cy + 30).size(80, 20).build());
    }

    private void sendAndClose() {
        if (selectedCulture == null) return;
        minecraft.getConnection().send(
                new net.darkflameproduction.agotmod.network.SetTownHallCulturePacket(
                        townHallPos, selectedCulture)
        );
        minecraft.setScreen(parent);
    }

    // ── Render ────────────────────────────────────────────────────────────────

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        // Dim background
        g.fill(0, 0, width, height, 0x80000000);

        int cx = width / 2;

        switch (step) {
            case 0 -> {
                drawCenteredTitle(g, "Choose a Culture Group", cx, height / 2 - (CULTURE_MAP.size() * 26) / 2 - 30, COLOR_HEADER);
                drawCenteredSubtitle(g, "This cannot be changed later.", cx, height / 2 - (CULTURE_MAP.size() * 26) / 2 - 16, COLOR_RED);
            }
            case 1 -> {
                drawCenteredTitle(g, selectedGroup.displayName, cx, 30, COLOR_HEADER);
                drawCenteredSubtitle(g, "Select a specific culture for your town.", cx, 46, COLOR_GRAY);
            }
            case 2 -> {
                int cy = height / 2;
                drawCenteredTitle(g, "Are you sure?", cx, cy - 40, COLOR_RED);
                drawCenteredSubtitle(g, "You are setting your town's culture to:", cx, cy - 22, COLOR_GRAY);

                // Culture name in big gold text
                String cultureName = selectedCulture.displayName + " (" + selectedGroup.displayName + ")";
                g.pose().pushPose();
                float scale = 1.2f;
                g.pose().scale(scale, scale, 1f);
                int nameW = (int)(font.width(cultureName) * scale);
                g.drawString(font, cultureName,
                        (int)((cx - nameW / 2) / scale),
                        (int)((cy - 5) / scale),
                        COLOR_GOLD, false);
                g.pose().popPose();

                drawCenteredSubtitle(g, "This choice is permanent and cannot be undone.", cx, cy + 18, COLOR_RED);
            }
        }

        super.render(g, mouseX, mouseY, partialTick);
    }

    private void drawCenteredTitle(GuiGraphics g, String text, int cx, int y, int color) {
        g.pose().pushPose();
        float scale = 1.3f;
        g.pose().scale(scale, scale, 1f);
        int w = (int)(font.width(text) * scale);
        g.drawString(font, text, (int)((cx - w / 2) / scale), (int)(y / scale), color, false);
        g.pose().popPose();
    }

    private void drawCenteredSubtitle(GuiGraphics g, String text, int cx, int y, int color) {
        int w = font.width(text);
        g.drawString(font, text, cx - w / 2, y, color, false);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}

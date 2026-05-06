package net.darkflameproduction.agotmod.client.gui;

import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.network.CloseNpcConversationPacket;
import net.darkflameproduction.agotmod.network.OpenNpcInventoryPacket;
import net.darkflameproduction.agotmod.network.OpenNpcTradePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class NpcConversationScreen extends Screen {

    private static final int COLOR_NORMAL  = 0xFFAAAAAA;
    private static final int COLOR_HOVERED = 0xFFFFFFFF;
    private static final int COLOR_LINE    = 0xAAFFFFFF;
    private static final int COLOR_NAME    = 0xFFFFFFFF;
    private static final int OPTION_GAP    = 6;
    private static final int BOTTOM_MARGIN = 80;
    private static final int LINE_PADDING  = 6;
    private static final int LINE_GAP      = 8;
    private static final int NAME_GAP      = 8;

    private static final String KEY_INVENTORY = "agotmod.conversation.inventory";
    private static final String KEY_TRADE     = "agotmod.conversation.trade";
    private static final String KEY_GOODBYE   = "agotmod.conversation.goodbye";

    private final UUID npcUUID;
    private final String jobType;
    private final String npcName;
    private final List<String> options = new ArrayList<>();

    public NpcConversationScreen(UUID npcUUID, String jobType, String npcName) {
        super(Component.empty());
        this.npcUUID  = npcUUID;
        this.jobType  = jobType;
        this.npcName  = npcName;
    }

    private boolean hasTrade() {
        return jobType.equals(JobSystem.JOB_GROCER)
                || jobType.equals(JobSystem.JOB_BUTCHER)
                || jobType.equals(JobSystem.JOB_TANNER)
                || jobType.equals(JobSystem.JOB_TAILOR);
    }

    @Override
    protected void init() {
        options.clear();
        options.add(KEY_INVENTORY);
        if (hasTrade()) options.add(KEY_TRADE);
        options.add(KEY_GOODBYE);
    }

    @Override
    public void tick() {
        super.tick();
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        mc.level.getEntitiesOfClass(
                        net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity.class,
                        new net.minecraft.world.phys.AABB(
                                mc.player.getX() - 64, mc.player.getY() - 64, mc.player.getZ() - 64,
                                mc.player.getX() + 64, mc.player.getY() + 64, mc.player.getZ() + 64
                        )
                ).stream()
                .filter(p -> p.getUUID().equals(npcUUID))
                .findFirst()
                .ifPresent(peasant -> {
                    if (peasant.isSleeping()) {
                        net.darkflameproduction.agotmod.client.overlay.NpcSleepNotificationOverlay.show(npcName);
                        this.onClose();
                    }
                });
    }

    private List<String> wrapName(String name, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = name.split(" ");

        StringBuilder current = new StringBuilder();
        for (String word : words) {
            while (this.font.width(word) > maxWidth) {
                String partial = "";
                int cut = 0;
                for (int i = 1; i <= word.length(); i++) {
                    String candidate = word.substring(0, i) + "-";
                    if (this.font.width(candidate) <= maxWidth) {
                        partial = candidate;
                        cut = i;
                    } else {
                        break;
                    }
                }
                if (!current.isEmpty()) {
                    lines.add(current.toString().trim());
                    current = new StringBuilder();
                }
                lines.add(partial);
                word = word.substring(cut);
            }

            if (word.isEmpty()) continue;

            String test = current.isEmpty() ? word : current + " " + word;
            if (this.font.width(test) <= maxWidth) {
                current = new StringBuilder(test);
            } else {
                if (!current.isEmpty()) lines.add(current.toString().trim());
                current = new StringBuilder(word);
            }
        }
        if (!current.isEmpty()) lines.add(current.toString().trim());
        return lines;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int startX      = (int)(this.width * 0.6);
        int totalHeight = options.size() * (this.font.lineHeight + OPTION_GAP) - OPTION_GAP;
        int startY      = this.height - BOTTOM_MARGIN - totalHeight;

        int lineX      = startX - LINE_GAP;
        int lineTop    = startY - LINE_PADDING;
        int lineBottom = startY + totalHeight + LINE_PADDING;

        int maxTextW = 0;
        for (String key : options) {
            int w = this.font.width(Component.translatable(key).getString());
            if (w > maxTextW) maxTextW = w;
        }

        int boxLeft  = lineX;
        int boxRight = startX + maxTextW + 12;
        guiGraphics.fill(boxLeft, lineTop, boxRight, lineBottom, 0x88000000);
        guiGraphics.fill(lineX, lineTop, lineX + 1, lineBottom, COLOR_LINE);

        int maxNameWidth = lineX - NAME_GAP - 10;
        List<String> nameLines = wrapName(npcName, maxNameWidth);
        int nameTotalHeight = nameLines.size() * (this.font.lineHeight + 2) - 2;
        int nameStartY = startY + (totalHeight - nameTotalHeight) / 2;

        for (int i = 0; i < nameLines.size(); i++) {
            String line = nameLines.get(i);
            int nameX   = lineX - NAME_GAP - this.font.width(line);
            int nameY   = nameStartY + i * (this.font.lineHeight + 2);
            guiGraphics.drawString(this.font, line, nameX, nameY, COLOR_NAME, false);
        }

        for (int i = 0; i < options.size(); i++) {
            String key        = options.get(i);
            String translated = Component.translatable(key).getString();
            int x     = startX;
            int y     = startY + i * (this.font.lineHeight + OPTION_GAP);
            int textW = this.font.width(translated);

            boolean hovered = mouseX >= x && mouseX <= x + textW
                    && mouseY >= y && mouseY <= y + this.font.lineHeight;

            guiGraphics.drawString(this.font, translated, x, y,
                    hovered ? COLOR_HOVERED : COLOR_NORMAL, false);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int startX      = (int)(this.width * 0.6);
        int totalHeight = options.size() * (this.font.lineHeight + OPTION_GAP) - OPTION_GAP;
        int startY      = this.height - BOTTOM_MARGIN - totalHeight;

        for (int i = 0; i < options.size(); i++) {
            String key        = options.get(i);
            String translated = Component.translatable(key).getString();
            int x     = startX;
            int y     = startY + i * (this.font.lineHeight + OPTION_GAP);
            int textW = this.font.width(translated);

            boolean clicked = mouseX >= x && mouseX <= x + textW
                    && mouseY >= y && mouseY <= y + this.font.lineHeight;

            if (clicked) {
                switch (key) {
                    case KEY_INVENTORY -> {
                        this.onClose();
                        net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                                new OpenNpcInventoryPacket(npcUUID));
                    }
                    case KEY_TRADE -> {
                        this.onClose();
                        net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                                new OpenNpcTradePacket(npcUUID));
                    }
                    case KEY_GOODBYE -> this.onClose();
                }
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void onClose() {
        super.onClose();
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                new CloseNpcConversationPacket(npcUUID));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
package com.raspberry.client.gui.clickgui;

import com.raspberry.client.Raspberry;
import com.raspberry.client.modules.Category;
import com.raspberry.client.modules.Module;
import com.raspberry.client.utils.ColorUtils;
import com.raspberry.client.utils.RenderUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Click GUI for the Raspberry Client.
 * Provides a visual interface for toggling modules and configuring settings.
 */
public class ClickGUI extends GuiScreen {

    private final List<CategoryPanel> panels = new ArrayList<>();

    public ClickGUI() {
        int x = 10;
        int y = 10;

        // Create a panel for each category
        for (Category category : Category.values()) {
            List<Module> modules = Raspberry.getInstance().getModuleManager().getModulesByCategory(category);
            if (!modules.isEmpty()) {
                panels.add(new CategoryPanel(category, x, y, 120, modules));
                x += 130; // Spacing between panels
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw dark background
        drawDefaultBackground();

        // Draw all panels
        for (CategoryPanel panel : panels) {
            panel.render(mouseX, mouseY);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (CategoryPanel panel : panels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (CategoryPanel panel : panels) {
            panel.mouseReleased(mouseX, mouseY, state);
        }
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    /**
     * Category panel for organizing modules.
     */
    private static class CategoryPanel {
        private final Category category;
        private double x;
        private double y;
        private final double width;
        private final List<Module> modules;
        private boolean expanded = true;
        private boolean dragging = false;
        private double dragX, dragY;

        private static final double HEADER_HEIGHT = 16;
        private static final double MODULE_HEIGHT = 14;

        public CategoryPanel(Category category, double x, double y, double width, List<Module> modules) {
            this.category = category;
            this.x = x;
            this.y = y;
            this.width = width;
            this.modules = modules;
        }

        public void render(int mouseX, int mouseY) {
            // Update drag position
            if (dragging) {
                x = mouseX - dragX;
                y = mouseY - dragY;
            }

            double currentY = y;

            // Draw header
            int headerColor = getCategoryColor();
            RenderUtils.drawRect(x, currentY, width, HEADER_HEIGHT, headerColor);
            RenderUtils.drawRect(x, currentY + HEADER_HEIGHT, width, 1, ColorUtils.darken(headerColor, 0.8f));

            // Draw category name
            String categoryName = category.getName();
            double textX = x + (width - mc.fontRendererObj.getStringWidth(categoryName)) / 2;
            double textY = currentY + (HEADER_HEIGHT - mc.fontRendererObj.FONT_HEIGHT) / 2;
            mc.fontRendererObj.drawStringWithShadow(categoryName, (float) textX, (float) textY, 0xFFFFFFFF);

            currentY += HEADER_HEIGHT;

            // Draw modules if expanded
            if (expanded) {
                for (Module module : modules) {
                    int moduleColor = module.isEnabled() ?
                        ColorUtils.setAlpha(headerColor, 150) :
                        ColorUtils.rgba(40, 40, 40, 200);

                    RenderUtils.drawRect(x, currentY, width, MODULE_HEIGHT, moduleColor);

                    // Draw module name
                    String moduleName = module.getName();
                    mc.fontRendererObj.drawStringWithShadow(
                        moduleName,
                        (float) (x + 4),
                        (float) (currentY + (MODULE_HEIGHT - mc.fontRendererObj.FONT_HEIGHT) / 2),
                        module.isEnabled() ? 0xFFFFFFFF : 0xFFAAAAAA
                    );

                    currentY += MODULE_HEIGHT;
                }
            }
        }

        public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
            double currentY = y;

            // Check header click
            if (isHovering(mouseX, mouseY, x, currentY, width, HEADER_HEIGHT)) {
                if (mouseButton == 0) {
                    // Left click - start dragging
                    dragging = true;
                    dragX = mouseX - x;
                    dragY = mouseY - y;
                } else if (mouseButton == 1) {
                    // Right click - toggle expand
                    expanded = !expanded;
                }
                return;
            }

            currentY += HEADER_HEIGHT;

            // Check module clicks
            if (expanded) {
                for (Module module : modules) {
                    if (isHovering(mouseX, mouseY, x, currentY, width, MODULE_HEIGHT)) {
                        if (mouseButton == 0) {
                            // Left click - toggle module
                            module.toggle();
                        }
                        return;
                    }
                    currentY += MODULE_HEIGHT;
                }
            }
        }

        public void mouseReleased(int mouseX, int mouseY, int state) {
            dragging = false;
        }

        private boolean isHovering(int mouseX, int mouseY, double x, double y, double width, double height) {
            return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        }

        private int getCategoryColor() {
            switch (category) {
                case COMBAT: return ColorUtils.rgba(255, 85, 85, 200);
                case VISUAL: return ColorUtils.rgba(85, 170, 255, 200);
                case MOVEMENT: return ColorUtils.rgba(85, 255, 85, 200);
                case PLAYER: return ColorUtils.rgba(255, 255, 85, 200);
                case RENDER: return ColorUtils.rgba(255, 170, 255, 200);
                case MISC: return ColorUtils.rgba(170, 170, 170, 200);
                default: return ColorUtils.rgba(100, 100, 100, 200);
            }
        }

        // Make mc accessible
        private static final net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
    }
}

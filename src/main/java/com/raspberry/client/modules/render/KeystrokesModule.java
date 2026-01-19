package com.raspberry.client.modules.render;

import com.raspberry.client.modules.HudModule;
import com.raspberry.client.utils.ColorUtils;
import com.raspberry.client.utils.RenderUtils;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;

/**
 * Keystrokes display module.
 * Shows visual representation of WASD and mouse button presses.
 */
public class KeystrokesModule extends HudModule {

    private static final int KEY_SIZE = 25;
    private static final int SPACING = 2;

    public KeystrokesModule() {
        super("Keystrokes", "Displays keypresses visually", 0, 100, 5);
        updateDimensions(KEY_SIZE * 3 + SPACING * 2, KEY_SIZE * 3 + SPACING * 4);
    }

    @Override
    public void render(ScaledResolution sr, float partialTicks) {
        double x = getX();
        double y = getY();

        // W key (top center)
        renderKey(x + KEY_SIZE + SPACING, y, "W", mc.gameSettings.keyBindForward);

        // A, S, D keys (middle row)
        renderKey(x, y + KEY_SIZE + SPACING, "A", mc.gameSettings.keyBindLeft);
        renderKey(x + KEY_SIZE + SPACING, y + KEY_SIZE + SPACING, "S", mc.gameSettings.keyBindBack);
        renderKey(x + (KEY_SIZE + SPACING) * 2, y + KEY_SIZE + SPACING, "D", mc.gameSettings.keyBindRight);

        // Mouse buttons (bottom row)
        double mouseY = y + (KEY_SIZE + SPACING) * 2;
        double mouseWidth = (KEY_SIZE * 3 + SPACING * 2) / 2.0 - SPACING / 2.0;

        // LMB
        boolean lmbPressed = Mouse.isButtonDown(0);
        int lmbColor = lmbPressed ? ColorUtils.rgb(255, 255, 255) : ColorUtils.rgb(0, 0, 0);
        RenderUtils.drawBorderedRect(x, mouseY, mouseWidth, KEY_SIZE, 2,
            ColorUtils.setAlpha(lmbColor, 100), ColorUtils.rgb(255, 255, 255));

        String lmbText = "LMB";
        double lmbTextX = x + (mouseWidth - getStringWidth(lmbText)) / 2;
        double lmbTextY = mouseY + (KEY_SIZE - mc.fontRendererObj.FONT_HEIGHT) / 2;
        drawString(lmbText, lmbTextX, lmbTextY, 0xFFFFFFFF);

        // RMB
        boolean rmbPressed = Mouse.isButtonDown(1);
        int rmbColor = rmbPressed ? ColorUtils.rgb(255, 255, 255) : ColorUtils.rgb(0, 0, 0);
        RenderUtils.drawBorderedRect(x + mouseWidth + SPACING, mouseY, mouseWidth, KEY_SIZE, 2,
            ColorUtils.setAlpha(rmbColor, 100), ColorUtils.rgb(255, 255, 255));

        String rmbText = "RMB";
        double rmbTextX = x + mouseWidth + SPACING + (mouseWidth - getStringWidth(rmbText)) / 2;
        double rmbTextY = mouseY + (KEY_SIZE - mc.fontRendererObj.FONT_HEIGHT) / 2;
        drawString(rmbText, rmbTextX, rmbTextY, 0xFFFFFFFF);
    }

    /**
     * Renders a single key.
     * @param x the x position
     * @param y the y position
     * @param label the key label
     * @param keyBinding the key binding
     */
    private void renderKey(double x, double y, String label, KeyBinding keyBinding) {
        boolean pressed = keyBinding.isKeyDown();
        int backgroundColor = pressed ? ColorUtils.rgb(255, 255, 255) : ColorUtils.rgb(0, 0, 0);

        RenderUtils.drawBorderedRect(x, y, KEY_SIZE, KEY_SIZE, 2,
            ColorUtils.setAlpha(backgroundColor, 100), ColorUtils.rgb(255, 255, 255));

        // Center the text
        double textX = x + (KEY_SIZE - getStringWidth(label)) / 2;
        double textY = y + (KEY_SIZE - mc.fontRendererObj.FONT_HEIGHT) / 2;

        drawString(label, textX, textY, 0xFFFFFFFF);
    }
}

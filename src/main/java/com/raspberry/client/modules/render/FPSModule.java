package com.raspberry.client.modules.render;

import com.raspberry.client.modules.HudModule;
import com.raspberry.client.utils.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

/**
 * FPS display module.
 * Shows the current frames per second.
 */
public class FPSModule extends HudModule {

    public FPSModule() {
        super("FPS", "Displays current FPS", 0, 5, 5);
    }

    @Override
    public void render(ScaledResolution sr, float partialTicks) {
        int fps = Minecraft.getDebugFPS();
        String text = "FPS: " + fps;

        // Color code based on FPS (green = good, yellow = ok, red = bad)
        int color;
        if (fps >= 60) {
            color = ColorUtils.rgb(85, 255, 85); // Green
        } else if (fps >= 30) {
            color = ColorUtils.rgb(255, 255, 85); // Yellow
        } else {
            color = ColorUtils.rgb(255, 85, 85); // Red
        }

        drawString(text, getX(), getY(), color);

        // Update dimensions for HUD editor
        updateDimensions(getStringWidth(text), mc.fontRendererObj.FONT_HEIGHT);
    }
}

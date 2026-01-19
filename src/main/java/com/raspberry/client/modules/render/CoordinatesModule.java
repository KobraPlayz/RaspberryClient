package com.raspberry.client.modules.render;

import com.raspberry.client.modules.HudModule;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.BlockPos;

/**
 * Coordinates display module.
 * Shows the player's current X, Y, Z coordinates.
 */
public class CoordinatesModule extends HudModule {

    public CoordinatesModule() {
        super("Coordinates", "Displays current coordinates", 0, 5, 35);
    }

    @Override
    public void render(ScaledResolution sr, float partialTicks) {
        if (mc.thePlayer == null) {
            return;
        }

        BlockPos pos = mc.thePlayer.getPosition();
        String text = String.format("XYZ: %d, %d, %d", pos.getX(), pos.getY(), pos.getZ());

        drawString(text, getX(), getY(), 0xFFFFFFFF);

        // Update dimensions
        updateDimensions(getStringWidth(text), mc.fontRendererObj.FONT_HEIGHT);
    }
}

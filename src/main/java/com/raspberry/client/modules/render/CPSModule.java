package com.raspberry.client.modules.render;

import com.raspberry.client.modules.HudModule;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

/**
 * CPS (Clicks Per Second) display module.
 * Tracks and displays left and right mouse button clicks.
 */
public class CPSModule extends HudModule {

    private final List<Long> leftClicks = new ArrayList<>();
    private final List<Long> rightClicks = new ArrayList<>();

    private boolean leftMouseDown = false;
    private boolean rightMouseDown = false;

    public CPSModule() {
        super("CPS", "Displays clicks per second", 0, 5, 20);
    }

    @Override
    public void render(ScaledResolution sr, float partialTicks) {
        // Update click tracking
        updateClicks();

        // Remove old clicks (older than 1 second)
        long currentTime = System.currentTimeMillis();
        leftClicks.removeIf(time -> currentTime - time > 1000);
        rightClicks.removeIf(time -> currentTime - time > 1000);

        int leftCPS = leftClicks.size();
        int rightCPS = rightClicks.size();

        String text = "[" + leftCPS + " | " + rightCPS + "] CPS";

        drawString(text, getX(), getY(), 0xFFFFFFFF);

        // Update dimensions
        updateDimensions(getStringWidth(text), mc.fontRendererObj.FONT_HEIGHT);
    }

    /**
     * Updates click tracking by detecting new mouse clicks.
     */
    private void updateClicks() {
        // Track left clicks
        boolean leftDown = Mouse.isButtonDown(0);
        if (leftDown && !leftMouseDown) {
            leftClicks.add(System.currentTimeMillis());
        }
        leftMouseDown = leftDown;

        // Track right clicks
        boolean rightDown = Mouse.isButtonDown(1);
        if (rightDown && !rightMouseDown) {
            rightClicks.add(System.currentTimeMillis());
        }
        rightMouseDown = rightDown;
    }
}

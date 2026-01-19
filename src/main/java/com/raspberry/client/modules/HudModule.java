package com.raspberry.client.modules;

import com.raspberry.client.events.EventTarget;
import com.raspberry.client.events.types.EventRender2D;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Base class for HUD modules that render information on screen.
 * Provides positioning and rendering functionality.
 */
public abstract class HudModule extends Module {

    private double x;
    private double y;
    private double width;
    private double height;
    private boolean dragging = false;

    public HudModule(String name, String description, int keyBind, double defaultX, double defaultY) {
        super(name, description, Category.RENDER, keyBind);
        this.x = defaultX;
        this.y = defaultY;
        this.width = 100;
        this.height = 20;
        setEnabled(true); // HUD modules are enabled by default
    }

    @EventTarget
    public void onRender2D(EventRender2D event) {
        if (mc.currentScreen != null && !isInHudEditor()) {
            return; // Don't render HUD when in GUI screens (except HUD editor)
        }

        ScaledResolution sr = new ScaledResolution(mc);
        render(sr, event.getPartialTicks());
    }

    /**
     * Renders the HUD element.
     * @param sr scaled resolution
     * @param partialTicks partial ticks
     */
    public abstract void render(ScaledResolution sr, float partialTicks);

    /**
     * Updates the dimensions of this HUD element.
     * Should be called when the content size changes.
     * @param width the new width
     * @param height the new height
     */
    protected void updateDimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if we're currently in the HUD editor.
     * @return true if in HUD editor
     */
    protected boolean isInHudEditor() {
        // Will be implemented when HUD editor is created
        return false;
    }

    /**
     * Draws a string with shadow.
     * @param text the text to draw
     * @param x the x coordinate
     * @param y the y coordinate
     * @param color the color
     */
    protected void drawString(String text, double x, double y, int color) {
        FontRenderer fr = mc.fontRendererObj;
        fr.drawStringWithShadow(text, (float) x, (float) y, color);
    }

    /**
     * Gets the width of a string.
     * @param text the text
     * @return the width in pixels
     */
    protected int getStringWidth(String text) {
        return mc.fontRendererObj.getStringWidth(text);
    }

    // Getters and setters

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }
}

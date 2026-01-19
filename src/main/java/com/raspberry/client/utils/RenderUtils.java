package com.raspberry.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Utility class for rendering operations.
 */
public class RenderUtils {

    private static final Minecraft mc = Minecraft.getMinecraft();

    /**
     * Draws a rectangle with the specified color.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     * @param color the color
     */
    public static void drawRect(double x, double y, double width, double height, int color) {
        Gui.drawRect((int) x, (int) y, (int) (x + width), (int) (y + height), color);
    }

    /**
     * Draws a rectangle with gradient.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     * @param color1 the start color
     * @param color2 the end color
     */
    public static void drawGradientRect(double x, double y, double width, double height, int color1, int color2) {
        float f = (float) (color1 >> 24 & 255) / 255.0F;
        float f1 = (float) (color1 >> 16 & 255) / 255.0F;
        float f2 = (float) (color1 >> 8 & 255) / 255.0F;
        float f3 = (float) (color1 & 255) / 255.0F;
        float f4 = (float) (color2 >> 24 & 255) / 255.0F;
        float f5 = (float) (color2 >> 16 & 255) / 255.0F;
        float f6 = (float) (color2 >> 8 & 255) / 255.0F;
        float f7 = (float) (color2 & 255) / 255.0F;

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(x + width, y, 0.0D).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(x, y, 0.0D).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(x, y + height, 0.0D).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0D).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();

        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    /**
     * Draws a bordered rectangle.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     * @param borderWidth the border width
     * @param fillColor the fill color
     * @param borderColor the border color
     */
    public static void drawBorderedRect(double x, double y, double width, double height, double borderWidth, int fillColor, int borderColor) {
        drawRect(x, y, width, height, fillColor);
        drawRect(x, y, width, borderWidth, borderColor); // Top
        drawRect(x, y + height - borderWidth, width, borderWidth, borderColor); // Bottom
        drawRect(x, y, borderWidth, height, borderColor); // Left
        drawRect(x + width - borderWidth, y, borderWidth, height, borderColor); // Right
    }

    /**
     * Draws a rounded rectangle.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     * @param radius the corner radius
     * @param color the color
     */
    public static void drawRoundedRect(double x, double y, double width, double height, double radius, int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(red, green, blue, alpha);

        GL11.glBegin(GL11.GL_POLYGON);

        double i;
        double angle;
        double x2 = x + width;
        double y2 = y + height;

        // Top-left corner
        for (i = 0; i <= 90; i += 1) {
            angle = Math.toRadians(i + 180);
            GL11.glVertex2d(x + radius + Math.sin(angle) * radius, y + radius + Math.cos(angle) * radius);
        }

        // Top-right corner
        for (i = 0; i <= 90; i += 1) {
            angle = Math.toRadians(i + 270);
            GL11.glVertex2d(x2 - radius + Math.sin(angle) * radius, y + radius + Math.cos(angle) * radius);
        }

        // Bottom-right corner
        for (i = 0; i <= 90; i += 1) {
            angle = Math.toRadians(i);
            GL11.glVertex2d(x2 - radius + Math.sin(angle) * radius, y2 - radius + Math.cos(angle) * radius);
        }

        // Bottom-left corner
        for (i = 0; i <= 90; i += 1) {
            angle = Math.toRadians(i + 90);
            GL11.glVertex2d(x + radius + Math.sin(angle) * radius, y2 - radius + Math.cos(angle) * radius);
        }

        GL11.glEnd();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /**
     * Gets the scaled resolution for the current window.
     * @return the scaled resolution
     */
    public static ScaledResolution getScaledResolution() {
        return new ScaledResolution(mc);
    }

    /**
     * Prepares for 2D rendering.
     */
    public static void start2D() {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableCull();
    }

    /**
     * Stops 2D rendering.
     */
    public static void stop2D() {
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}

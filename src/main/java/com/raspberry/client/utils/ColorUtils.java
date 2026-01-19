package com.raspberry.client.utils;

import java.awt.Color;

/**
 * Utility class for color operations.
 */
public class ColorUtils {

    /**
     * Converts RGB values to an integer color.
     * @param r red (0-255)
     * @param g green (0-255)
     * @param b blue (0-255)
     * @return the color as an integer
     */
    public static int rgb(int r, int g, int b) {
        return rgba(r, g, b, 255);
    }

    /**
     * Converts RGBA values to an integer color.
     * @param r red (0-255)
     * @param g green (0-255)
     * @param b blue (0-255)
     * @param a alpha (0-255)
     * @return the color as an integer
     */
    public static int rgba(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    /**
     * Gets a rainbow color based on time.
     * @param offset the offset for color cycling
     * @param speed the speed of color change
     * @return the rainbow color
     */
    public static int rainbow(long offset, float speed) {
        float hue = (System.currentTimeMillis() + offset) % (long) (360.0f / speed) / (360.0f / speed);
        return Color.HSBtoRGB(hue, 0.8f, 1.0f);
    }

    /**
     * Blends two colors together.
     * @param color1 the first color
     * @param color2 the second color
     * @param ratio the blend ratio (0.0 to 1.0)
     * @return the blended color
     */
    public static int blend(int color1, int color2, float ratio) {
        ratio = Math.max(0, Math.min(1, ratio));

        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;
        int a1 = (color1 >> 24) & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;
        int a2 = (color2 >> 24) & 0xFF;

        int r = (int) (r1 + (r2 - r1) * ratio);
        int g = (int) (g1 + (g2 - g1) * ratio);
        int b = (int) (b1 + (b2 - b1) * ratio);
        int a = (int) (a1 + (a2 - a1) * ratio);

        return rgba(r, g, b, a);
    }

    /**
     * Darkens a color by a factor.
     * @param color the original color
     * @param factor the darkening factor (0.0 to 1.0)
     * @return the darkened color
     */
    public static int darken(int color, float factor) {
        int r = (int) (((color >> 16) & 0xFF) * factor);
        int g = (int) (((color >> 8) & 0xFF) * factor);
        int b = (int) ((color & 0xFF) * factor);
        int a = (color >> 24) & 0xFF;

        return rgba(r, g, b, a);
    }

    /**
     * Lightens a color by a factor.
     * @param color the original color
     * @param factor the lightening factor (0.0 to 1.0)
     * @return the lightened color
     */
    public static int lighten(int color, float factor) {
        int r = (int) (((color >> 16) & 0xFF) + (255 - ((color >> 16) & 0xFF)) * factor);
        int g = (int) (((color >> 8) & 0xFF) + (255 - ((color >> 8) & 0xFF)) * factor);
        int b = (int) ((color & 0xFF) + (255 - (color & 0xFF)) * factor);
        int a = (color >> 24) & 0xFF;

        return rgba(r, g, b, a);
    }

    /**
     * Sets the alpha value of a color.
     * @param color the original color
     * @param alpha the new alpha value (0-255)
     * @return the color with updated alpha
     */
    public static int setAlpha(int color, int alpha) {
        return rgba((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, alpha);
    }
}

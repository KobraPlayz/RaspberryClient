package com.raspberry.client.modules.visual;

import com.raspberry.client.modules.Category;
import com.raspberry.client.modules.Module;

/**
 * Fullbright module.
 * Provides maximum brightness for better visibility.
 */
public class FullbrightModule extends Module {

    private float previousGamma = 0;

    public FullbrightModule() {
        super("Fullbright", "Maximum brightness", Category.VISUAL, 0);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (mc.gameSettings != null) {
            previousGamma = mc.gameSettings.gammaSetting;
            mc.gameSettings.gammaSetting = 100.0F;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (mc.gameSettings != null) {
            mc.gameSettings.gammaSetting = previousGamma;
        }
    }
}

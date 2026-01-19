package com.raspberry.client.modules.misc;

import com.raspberry.client.gui.clickgui.ClickGUI;
import com.raspberry.client.modules.Category;
import com.raspberry.client.modules.Module;
import org.lwjgl.input.Keyboard;

/**
 * ClickGUI module.
 * Opens the Click GUI interface for managing modules.
 */
public class ClickGUIModule extends Module {

    public ClickGUIModule() {
        super("ClickGUI", "Opens the Click GUI", Category.MISC, Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        // Don't call super.onEnable() to avoid event registration
        if (mc.thePlayer != null) {
            mc.displayGuiScreen(new ClickGUI());
        }
        // Immediately disable after opening
        setEnabled(false);
    }
}

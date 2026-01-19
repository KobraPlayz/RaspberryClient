package com.raspberry.client.modules.movement;

import com.raspberry.client.events.EventTarget;
import com.raspberry.client.events.types.EventUpdate;
import com.raspberry.client.modules.Category;
import com.raspberry.client.modules.Module;
import org.lwjgl.input.Keyboard;

/**
 * Toggle Sneak module.
 * Toggles sneaking on/off instead of requiring holding the key.
 */
public class ToggleSneakModule extends Module {

    private boolean sneaking = false;
    private boolean wasKeyDown = false;

    public ToggleSneakModule() {
        super("Toggle Sneak", "Toggle crouch instead of hold", Category.MOVEMENT, 0);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (event.isPre() && mc.thePlayer != null) {
            boolean isKeyDown = Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode());

            // Toggle on key press
            if (isKeyDown && !wasKeyDown) {
                sneaking = !sneaking;
            }

            wasKeyDown = isKeyDown;

            // Apply sneaking state
            mc.gameSettings.keyBindSneak.pressed = sneaking;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        // Reset sneaking when module is disabled
        sneaking = false;
        if (mc.gameSettings != null) {
            mc.gameSettings.keyBindSneak.pressed = false;
        }
    }
}

package com.raspberry.client.modules.movement;

import com.raspberry.client.events.EventTarget;
import com.raspberry.client.events.types.EventUpdate;
import com.raspberry.client.modules.Category;
import com.raspberry.client.modules.Module;
import com.raspberry.client.settings.types.BooleanSetting;

/**
 * Toggle Sprint module.
 * Automatically sprints without needing to hold the sprint key.
 */
public class ToggleSprintModule extends Module {

    private final BooleanSetting keepSprint;

    public ToggleSprintModule() {
        super("Toggle Sprint", "Automatically sprint", Category.MOVEMENT, 0);

        // Add keep sprint setting
        keepSprint = new BooleanSetting("Keep Sprint", "Keep sprinting after hitting", true);
        addSetting(keepSprint);

        setEnabled(true); // Enable by default for convenience
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (event.isPre() && mc.thePlayer != null) {
            // Auto sprint when moving forward
            if (mc.thePlayer.movementInput.moveForward > 0 && !mc.thePlayer.isSneaking() &&
                !mc.thePlayer.isCollidedHorizontally && mc.thePlayer.getFoodStats().getFoodLevel() > 6) {
                mc.thePlayer.setSprinting(true);
            }
        }
    }
}

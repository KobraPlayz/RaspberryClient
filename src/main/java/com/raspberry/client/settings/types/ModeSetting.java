package com.raspberry.client.settings.types;

import com.raspberry.client.settings.Setting;

import java.util.Arrays;
import java.util.List;

/**
 * A mode setting that allows selection from multiple options.
 */
public class ModeSetting extends Setting {

    private final List<String> modes;
    private int currentIndex;

    public ModeSetting(String name, String description, String defaultMode, String... modes) {
        super(name, description);
        this.modes = Arrays.asList(modes);

        // Find default mode index
        this.currentIndex = this.modes.indexOf(defaultMode);
        if (this.currentIndex == -1) {
            this.currentIndex = 0;
        }
    }

    public String getMode() {
        return modes.get(currentIndex);
    }

    public void setMode(String mode) {
        int index = modes.indexOf(mode);
        if (index != -1) {
            currentIndex = index;
        }
    }

    public void cycle() {
        currentIndex = (currentIndex + 1) % modes.size();
    }

    public List<String> getModes() {
        return modes;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    @Override
    public Object getValue() {
        return getMode();
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof String) {
            setMode((String) value);
        }
    }
}

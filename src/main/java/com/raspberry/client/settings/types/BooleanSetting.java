package com.raspberry.client.settings.types;

import com.raspberry.client.settings.Setting;

/**
 * A boolean (true/false) setting.
 */
public class BooleanSetting extends Setting {

    private boolean value;

    public BooleanSetting(String name, String description, boolean defaultValue) {
        super(name, description);
        this.value = defaultValue;
    }

    public boolean isEnabled() {
        return value;
    }

    public void setEnabled(boolean value) {
        this.value = value;
    }

    public void toggle() {
        value = !value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Boolean) {
            this.value = (Boolean) value;
        }
    }
}

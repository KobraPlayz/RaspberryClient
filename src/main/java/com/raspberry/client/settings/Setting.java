package com.raspberry.client.settings;

/**
 * Base class for all settings in the Raspberry Client.
 * Settings allow modules to have customizable behavior.
 */
public abstract class Setting {

    private final String name;
    private final String description;

    public Setting(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Gets the value as an object for serialization.
     * @return the value object
     */
    public abstract Object getValue();

    /**
     * Sets the value from an object (for deserialization).
     * @param value the value object
     */
    public abstract void setValue(Object value);
}

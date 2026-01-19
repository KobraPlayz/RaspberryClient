package com.raspberry.client.modules;

import com.raspberry.client.events.EventManager;
import com.raspberry.client.settings.Setting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all modules in the Raspberry Client.
 * Modules are toggleable features with customizable settings.
 */
public abstract class Module {

    protected final Minecraft mc = Minecraft.getMinecraft();

    private final String name;
    private final String description;
    private final Category category;
    private int keyBind;
    private boolean enabled;

    private final List<Setting> settings = new ArrayList<>();

    /**
     * Creates a new module.
     * @param name the module name
     * @param description the module description
     * @param category the module category
     * @param keyBind the default keybind (0 for none)
     */
    public Module(String name, String description, Category category, int keyBind) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keyBind = keyBind;
        this.enabled = false;
    }

    /**
     * Called when the module is enabled.
     */
    public void onEnable() {
        EventManager.register(this);
    }

    /**
     * Called when the module is disabled.
     */
    public void onDisable() {
        EventManager.unregister(this);
    }

    /**
     * Toggles the module on/off.
     */
    public void toggle() {
        setEnabled(!enabled);
    }

    /**
     * Sets the enabled state of the module.
     * @param enabled true to enable, false to disable
     */
    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }

    /**
     * Adds a setting to this module.
     * @param setting the setting to add
     */
    public void addSetting(Setting setting) {
        settings.add(setting);
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<Setting> getSettings() {
        return settings;
    }
}

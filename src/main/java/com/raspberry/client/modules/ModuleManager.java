package com.raspberry.client.modules;

import com.raspberry.client.events.EventManager;
import com.raspberry.client.events.EventTarget;
import com.raspberry.client.events.types.EventKey;
import com.raspberry.client.modules.render.*;
import com.raspberry.client.modules.visual.*;
import com.raspberry.client.modules.movement.*;
import com.raspberry.client.modules.misc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manager for all modules in the Raspberry Client.
 * Handles module registration, toggling, and keybind processing.
 */
public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();

    /**
     * Initializes the module manager and registers all modules.
     */
    public void initialize() {
        System.out.println("[Raspberry] Initializing modules...");

        // Register this manager for key events
        EventManager.register(this);

        // Register HUD modules
        registerModule(new FPSModule());
        registerModule(new CPSModule());
        registerModule(new CoordinatesModule());
        registerModule(new KeystrokesModule());

        // Register visual modules
        registerModule(new FullbrightModule());
        registerModule(new ZoomModule());

        // Register movement modules
        registerModule(new ToggleSprintModule());
        registerModule(new ToggleSneakModule());

        // Register misc modules
        registerModule(new ClickGUIModule());

        System.out.println("[Raspberry] Loaded " + modules.size() + " modules!");
    }

    /**
     * Registers a module.
     * @param module the module to register
     */
    public void registerModule(Module module) {
        modules.add(module);
    }

    /**
     * Gets a module by name.
     * @param name the module name
     * @return the module, or null if not found
     */
    public Module getModule(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }

    /**
     * Gets a module by class.
     * @param clazz the module class
     * @param <T> the module type
     * @return the module, or null if not found
     */
    public <T extends Module> T getModule(Class<T> clazz) {
        for (Module module : modules) {
            if (module.getClass() == clazz) {
                return (T) module;
            }
        }
        return null;
    }

    /**
     * Gets all modules in a specific category.
     * @param category the category
     * @return list of modules in the category
     */
    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    /**
     * Gets all modules.
     * @return list of all modules
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * Event handler for key presses to toggle modules.
     * @param event the key event
     */
    @EventTarget
    public void onKey(EventKey event) {
        for (Module module : modules) {
            if (module.getKeyBind() == event.getKeyCode()) {
                module.toggle();
            }
        }
    }
}

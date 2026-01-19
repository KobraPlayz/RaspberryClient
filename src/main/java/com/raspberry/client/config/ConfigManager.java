package com.raspberry.client.config;

import com.google.gson.*;
import com.raspberry.client.Raspberry;
import com.raspberry.client.modules.Module;
import com.raspberry.client.settings.Setting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Configuration manager for the Raspberry Client.
 * Handles saving and loading of module states and settings.
 */
public class ConfigManager {

    private final File configDir;
    private final File configFile;
    private final Gson gson;

    public ConfigManager() {
        // Config directory: .minecraft/raspberry/
        this.configDir = new File("raspberry");
        this.configFile = new File(configDir, "config.json");
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        // Create directory if it doesn't exist
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
    }

    /**
     * Saves all module states and settings to the config file.
     */
    public void saveConfig() {
        try {
            JsonObject root = new JsonObject();
            JsonArray modulesArray = new JsonArray();

            // Save each module
            for (Module module : Raspberry.getInstance().getModuleManager().getModules()) {
                JsonObject moduleObj = new JsonObject();

                moduleObj.addProperty("name", module.getName());
                moduleObj.addProperty("enabled", module.isEnabled());
                moduleObj.addProperty("keybind", module.getKeyBind());

                // Save settings
                if (!module.getSettings().isEmpty()) {
                    JsonObject settingsObj = new JsonObject();
                    for (Setting setting : module.getSettings()) {
                        Object value = setting.getValue();
                        if (value instanceof Boolean) {
                            settingsObj.addProperty(setting.getName(), (Boolean) value);
                        } else if (value instanceof Number) {
                            settingsObj.addProperty(setting.getName(), (Number) value);
                        } else if (value instanceof String) {
                            settingsObj.addProperty(setting.getName(), (String) value);
                        }
                    }
                    moduleObj.add("settings", settingsObj);
                }

                modulesArray.add(moduleObj);
            }

            root.add("modules", modulesArray);

            // Write to file
            try (FileWriter writer = new FileWriter(configFile)) {
                gson.toJson(root, writer);
            }

            System.out.println("[Raspberry] Configuration saved successfully!");

        } catch (IOException e) {
            System.err.println("[Raspberry] Failed to save configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads module states and settings from the config file.
     */
    public void loadConfig() {
        if (!configFile.exists()) {
            System.out.println("[Raspberry] No configuration file found, using defaults.");
            return;
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(configFile.getPath())));
            JsonObject root = gson.fromJson(content, JsonObject.class);

            if (root.has("modules")) {
                JsonArray modulesArray = root.getAsJsonArray("modules");

                for (JsonElement element : modulesArray) {
                    JsonObject moduleObj = element.getAsJsonObject();

                    String name = moduleObj.get("name").getAsString();
                    Module module = Raspberry.getInstance().getModuleManager().getModule(name);

                    if (module != null) {
                        // Load enabled state
                        if (moduleObj.has("enabled")) {
                            boolean enabled = moduleObj.get("enabled").getAsBoolean();
                            if (enabled != module.isEnabled()) {
                                module.setEnabled(enabled);
                            }
                        }

                        // Load keybind
                        if (moduleObj.has("keybind")) {
                            module.setKeyBind(moduleObj.get("keybind").getAsInt());
                        }

                        // Load settings
                        if (moduleObj.has("settings")) {
                            JsonObject settingsObj = moduleObj.getAsJsonObject("settings");

                            for (Setting setting : module.getSettings()) {
                                if (settingsObj.has(setting.getName())) {
                                    JsonElement valueElement = settingsObj.get(setting.getName());

                                    if (valueElement.isJsonPrimitive()) {
                                        JsonPrimitive primitive = valueElement.getAsJsonPrimitive();

                                        if (primitive.isBoolean()) {
                                            setting.setValue(primitive.getAsBoolean());
                                        } else if (primitive.isNumber()) {
                                            setting.setValue(primitive.getAsDouble());
                                        } else if (primitive.isString()) {
                                            setting.setValue(primitive.getAsString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("[Raspberry] Configuration loaded successfully!");

        } catch (IOException | JsonSyntaxException e) {
            System.err.println("[Raspberry] Failed to load configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

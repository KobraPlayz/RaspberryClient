package com.raspberry.client.modules;

/**
 * Categories for organizing modules in the Raspberry Client.
 */
public enum Category {
    COMBAT("Combat"),
    VISUAL("Visual"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    MISC("Misc");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

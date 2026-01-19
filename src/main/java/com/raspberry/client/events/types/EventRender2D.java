package com.raspberry.client.events.types;

import com.raspberry.client.events.Event;

/**
 * Event called when rendering 2D elements (HUD, GUI, etc.).
 * @param partialTicks partial ticks for smooth interpolation
 */
public class EventRender2D extends Event {

    private final float partialTicks;

    public EventRender2D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}

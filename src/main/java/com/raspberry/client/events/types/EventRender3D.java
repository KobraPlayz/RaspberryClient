package com.raspberry.client.events.types;

import com.raspberry.client.events.Event;

/**
 * Event called when rendering 3D elements in the world.
 * @param partialTicks partial ticks for smooth interpolation
 */
public class EventRender3D extends Event {

    private final float partialTicks;

    public EventRender3D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}

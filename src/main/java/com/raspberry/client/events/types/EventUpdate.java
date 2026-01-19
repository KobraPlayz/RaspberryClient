package com.raspberry.client.events.types;

import com.raspberry.client.events.Event;

/**
 * Event called when the player updates (every tick).
 * Can be used for game logic that needs to run consistently.
 */
public class EventUpdate extends Event {

    private final Phase phase;

    public EventUpdate(Phase phase) {
        this.phase = phase;
    }

    public Phase getPhase() {
        return phase;
    }

    public boolean isPre() {
        return phase == Phase.PRE;
    }

    public boolean isPost() {
        return phase == Phase.POST;
    }
}

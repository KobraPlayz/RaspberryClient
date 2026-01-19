package com.raspberry.client.events;

/**
 * Base class for all events in the Raspberry Client.
 * Events can be cancellable, allowing listeners to prevent default behavior.
 */
public abstract class Event {

    private boolean cancelled = false;

    /**
     * Checks if this event has been cancelled.
     * @return true if the event is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancellation state of this event.
     * @param cancelled true to cancel the event, false otherwise
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Enum for event execution phase (PRE or POST).
     */
    public enum Phase {
        PRE,
        POST
    }
}

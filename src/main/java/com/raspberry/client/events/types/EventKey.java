package com.raspberry.client.events.types;

import com.raspberry.client.events.Event;

/**
 * Event called when a key is pressed.
 * @param keyCode the key code of the pressed key
 */
public class EventKey extends Event {

    private final int keyCode;

    public EventKey(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}

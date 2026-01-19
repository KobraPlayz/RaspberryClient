package com.raspberry.client.events.types;

import com.raspberry.client.events.Event;
import net.minecraft.network.Packet;

/**
 * Event called when sending or receiving packets.
 */
public class EventPacket extends Event {

    private final Packet<?> packet;
    private final Type type;

    public EventPacket(Packet<?> packet, Type type) {
        this.packet = packet;
        this.type = type;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public Type getType() {
        return type;
    }

    public boolean isSend() {
        return type == Type.SEND;
    }

    public boolean isReceive() {
        return type == Type.RECEIVE;
    }

    public enum Type {
        SEND,
        RECEIVE
    }
}

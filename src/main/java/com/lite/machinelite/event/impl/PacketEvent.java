package com.lite.machinelite.event.impl;

import com.lite.machinelite.event.Event;
import net.minecraft.network.Packet;

public class PacketEvent extends Event {
    private Packet<?> packet;
    private boolean outgoing;

    public Event preFire(Packet<?> packet) {
        this.packet = packet;
        this.outgoing = true;
        return this;
    }

    public Event postFire(Packet<?> packet) {
        this.packet = packet;
        this.outgoing = false;
        return this;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

    public boolean isOutgoing() {
        return this.outgoing;
    }

    public boolean isIncoming() {
        return !this.outgoing;
    }
}

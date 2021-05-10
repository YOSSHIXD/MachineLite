package com.lite.machinelite.event.impl;

import com.lite.machinelite.event.Event;
import com.lite.machinelite.event.EventType;

public class UpdateEvent extends Event {
    public UpdateEvent fire(EventType type) {
        this.type = type;
        return this;
    }
}

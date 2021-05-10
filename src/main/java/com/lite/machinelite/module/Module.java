package com.lite.machinelite.module;

import com.lite.machinelite.MachineLite;
import com.lite.machinelite.event.Event;
import com.lite.machinelite.event.Listener;
import com.lite.machinelite.utilities.IMC;
import net.minecraftforge.common.MinecraftForge;

public class Module implements IMC, Listener {
    public String name;
    public int keyCode;
    public boolean enabled;

    public Module(String name, int keyCode) {
        this.name = name;
        this.keyCode = keyCode;
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            MinecraftForge.EVENT_BUS.register(this);
            MachineLite.getEventManager().register(this);
            onEnabled();
            MachineLite.WriteChat("\247aEnabled \2477" + this.getName());
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            MachineLite.getEventManager().unregister(this);
            onDisabled();
            MachineLite.WriteChat("\247cDisabled \2477" + this.getName());
        }
    }

    public void onEnabled() {}

    public void onDisabled() {}

    public void onEvent(Event event) {}

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}

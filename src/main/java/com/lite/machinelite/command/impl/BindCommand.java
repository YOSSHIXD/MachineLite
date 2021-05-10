package com.lite.machinelite.command.impl;

import com.lite.machinelite.MachineLite;
import com.lite.machinelite.module.Module;
import com.lite.machinelite.command.Command;
import org.lwjgl.input.Keyboard;

import java.io.FileNotFoundException;

public class BindCommand extends Command {
    public BindCommand(String[] names, String description) {
        super(names, description);
    }

    public void fire(String[] args) {
        if (args != null && args.length > 0) {
            Module module = MachineLite.getModuleManager().getModuleByString(args[0]);

                if (module != null) {
                    if (args.length == 1) {
                        MachineLite.WriteChat(String.format("\2477The current key for \247b%s is \2479%s.", module.getName(), Keyboard.getKeyName(module.getKeyCode())));
                    } else if (args.length == 2) {
                        int key = Keyboard.getKeyIndex(args[1].toUpperCase());
                        module.setKeyCode(key);
                        MachineLite.WriteChat("\2477" + module.getName() + " bind to \247f" + Keyboard.getKeyName(module.getKeyCode()) + "\2477.");
                    }
                } else {
                    MachineLite.WriteChat("\247cModule was not found");
                }
        } else {
            MachineLite.WriteChat("\2477.bind <Module> <Key>");
        }
    }
}

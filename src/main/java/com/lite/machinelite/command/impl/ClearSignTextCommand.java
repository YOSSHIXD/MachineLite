package com.lite.machinelite.command.impl;

import com.lite.machinelite.MachineLite;
import com.lite.machinelite.command.Command;
import com.lite.machinelite.module.impl.AutoSign;

public class ClearSignTextCommand extends Command {
    public ClearSignTextCommand(String[] names, String description) {
        super(names, description);
    }

    @Override
    public void fire(String[] args) {
        if (((AutoSign) MachineLite.getModuleManager().getModuleByString("AutoSign")).getSignText() != null) {
            ((AutoSign) MachineLite.getModuleManager().getModuleByString("AutoSign")).setSignTexts(null);
            MachineLite.WriteChat("\2477SignText has been cleared.");
        } else {
            MachineLite.WriteChat("\247cAlready cleared.");
        }
    }
}

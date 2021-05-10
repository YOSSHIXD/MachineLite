package com.lite.machinelite.module.impl;

import com.lite.machinelite.module.Module;
import net.minecraft.util.text.ITextComponent;

public class AutoSign extends Module {
    private ITextComponent[] signText;

    public AutoSign(String name, int keyCode) {
        super(name, keyCode);
    }

    @Override
    public void onDisabled() {
        this.signText = null;
    }

    public ITextComponent[] getSignText() {
        return this.signText;
    }

    public void setSignText(ITextComponent[] signText) {
        this.signText = signText;
    }

    public void setSignTexts(ITextComponent[] signText) {
        if (isEnabled() && this.signText == null) {
            this.signText = signText;
        }
    }
}

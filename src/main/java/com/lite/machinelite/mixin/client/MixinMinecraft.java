package com.lite.machinelite.mixin.client;

import com.lite.machinelite.event.impl.RightClickMouseEvent;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.lite.machinelite.utilities.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.Proxy;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "rightClickMouse", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;rightClickDelayTimer:I"), cancellable = true)
    private void rightClickMouse(CallbackInfo ci) {
        RightClickMouseEvent rightClickMouseEvent = new RightClickMouseEvent();
        rightClickMouseEvent.call();

        if (rightClickMouseEvent.isCancelled()) {
            ci.cancel();
        }
    }
}

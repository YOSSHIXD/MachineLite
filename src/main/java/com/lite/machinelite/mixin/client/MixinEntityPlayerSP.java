package com.lite.machinelite.mixin.client;

import com.lite.machinelite.event.EventType;
import com.lite.machinelite.event.impl.UpdateEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {
    @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"))
    private void PreUpdateWalkingPlayer(CallbackInfo ci) {
        UpdateEvent updateEvent = new UpdateEvent();
        updateEvent.fire(EventType.PRE).call();
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("RETURN"))
    private void PostUpdateWalkingPlayer(CallbackInfo ci) {
        UpdateEvent updateEvent = new UpdateEvent();
        updateEvent.fire(EventType.POST).call();
    }
}

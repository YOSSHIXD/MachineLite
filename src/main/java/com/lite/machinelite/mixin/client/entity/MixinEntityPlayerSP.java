package com.lite.machinelite.mixin.client.entity;

import com.mojang.authlib.GameProfile;
import com.lite.machinelite.event.EventType;
import com.lite.machinelite.event.impl.UpdateEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends EntityPlayer {
    public MixinEntityPlayerSP(World worldIn, GameProfile gameProfileIn) {
        super(worldIn, gameProfileIn);
    }

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

package com.lite.machinelite.module.impl;

import com.lite.machinelite.event.Event;
import com.lite.machinelite.event.impl.EventBreakBlock;
import com.lite.machinelite.module.Module;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;

public class AntiGhostBlock extends Module {
    public AntiGhostBlock(String name, int keyCode) {
        super(name, keyCode);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventBreakBlock) {
            mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, ((EventBreakBlock) event).getPos(), EnumFacing.UP));
        }
    }
}

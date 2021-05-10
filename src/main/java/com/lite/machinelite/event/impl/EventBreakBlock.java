package com.lite.machinelite.event.impl;

import com.lite.machinelite.event.Event;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

public class EventBreakBlock extends Event {
    private IBlockState state;
    private BlockPos pos;

    public Event fire(BlockPos pos, IBlockState state) {
        this.pos = pos;
        this.state = state;
        return this;
    }

    public BlockPos getPos() {
        return pos;
    }

    public IBlockState getState() {
        return state;
    }
}
package com.lite.machinelite.module.impl;

import com.lite.machinelite.event.Event;
import com.lite.machinelite.event.impl.UpdateEvent;
import com.lite.machinelite.module.Module;
import com.lite.machinelite.utilities.Utils;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class BuildRandom extends Module {
    private final Random random = new Random();
    private float delay;

    public BuildRandom(String name, int keyCode) {
        super(name, keyCode);
    }

    @Override
    public void onEnabled() {
        this.delay = 0.0F;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof UpdateEvent) {
            int range = 5;
            int bound = range * 2;
            BlockPos pos;

            if (!checkHeldItem()) {
                return;
            }

            try {
                do {
                    pos = new BlockPos(mc.player.getPosition()).add(random.nextInt(bound) - range, random.nextInt(bound) - range, random.nextInt(bound) - range);
                } while (--delay < 0 && !tryToPlaceBlock(pos));
            } catch (Exception ignore) {
            }
        }
    }

    private boolean tryToPlaceBlock(BlockPos pos) {
        if (!mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            return false;
        }

        if (Utils.placeBlock(pos)) {
            this.delay = 3.0F;
        }
        return false;
    }

    private boolean checkHeldItem() {
        ItemStack stack = mc.player.inventory.getCurrentItem();
        return !stack.isEmpty() && (stack.getItem() instanceof ItemBlock || stack.getItem() instanceof ItemSkull);
    }
}

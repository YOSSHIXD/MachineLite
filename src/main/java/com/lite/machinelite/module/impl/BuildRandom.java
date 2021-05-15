package com.lite.machinelite.module.impl;

import com.lite.machinelite.event.Event;
import com.lite.machinelite.event.impl.UpdateEvent;
import com.lite.machinelite.module.Module;
import com.lite.machinelite.utilities.TimerUtil;
import com.lite.machinelite.utilities.Utils;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class BuildRandom extends Module {
    private final Random random;
    private final TimerUtil timer;

    public BuildRandom(String name, int keyCode) {
        super(name, keyCode);
        random = new Random();
        timer = new TimerUtil();
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof UpdateEvent) {
            int range = 4;
            int bound = range * 2 + 1;
            int attempts = 0;
            BlockPos pos;

            if (!checkHeldItem()) {
                return;
            }

            try {
                do {
                    pos = new BlockPos(mc.player.getPosition()).add(random.nextInt(bound) - range, random.nextInt(bound) - range, random.nextInt(bound) - range);
                } while (++attempts < 128 && timer.delay(80) && !tryToPlaceBlock(range, pos));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean tryToPlaceBlock(double reach, BlockPos pos) {
        if (pos == null || !mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            return false;
        }

        if (Utils.placeBlock(reach, pos)) {
            timer.reset();
            return true;
        }

        return false;
    }

    private boolean checkHeldItem() {
        ItemStack stack = mc.player.inventory.getCurrentItem();
        return !stack.isEmpty() && (stack.getItem() instanceof ItemBlock || stack.getItem() instanceof ItemSkull);
    }
}

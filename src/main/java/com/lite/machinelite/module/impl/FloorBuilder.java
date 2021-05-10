package com.lite.machinelite.module.impl;

import com.lite.machinelite.event.Event;
import com.lite.machinelite.event.impl.UpdateEvent;
import com.lite.machinelite.module.Module;
import com.lite.machinelite.utilities.Utils;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

/*
 * COPY AND PASTE CODO
 */
public class FloorBuilder extends Module {
    private final Random random = new Random();
    private float delay;

    public FloorBuilder(String name, int keyCode) {
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
                    pos = new BlockPos(mc.player.getPosition()).add(random.nextInt(bound) - range, -1, random.nextInt(bound) - range);
                } while (--delay < 0 && !tryToPlaceBlock(pos));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean tryToPlaceBlock(BlockPos pos) {
        if (pos == null || !mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            return false;
        }

        if (Utils.placeBlock(pos)) {
            this.delay = 1.0F;
            return true;
        }

        return false;
    }

    private boolean checkHeldItem() {
        ItemStack stack = mc.player.inventory.getCurrentItem();
        return !stack.isEmpty() && (stack.getItem() instanceof ItemBlock || stack.getItem() instanceof ItemSkull);
    }
}

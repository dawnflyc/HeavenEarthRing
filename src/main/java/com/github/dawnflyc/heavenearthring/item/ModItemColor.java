package com.github.dawnflyc.heavenearthring.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.awt.*;
import java.util.Random;

public class ModItemColor implements IItemColor {
    @Override
    public int getColor(ItemStack p_getColor_1_, int p_getColor_2_) {
        CompoundNBT nbt=p_getColor_1_.getTag();
        if (nbt!=null){
            int color=nbt.getInt("item_model_color");
            if (color!=0){
                return color;
            }
        }
        return p_getColor_2_;
    }
}

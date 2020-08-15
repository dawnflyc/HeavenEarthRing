package com.github.dawnflyc.heavenearthring.client.model;

import com.github.dawnflyc.heavenearthring.common.nbt.RenderModelNBT;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 模型颜色
 */
public class ModItemColor implements IItemColor {

    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public int getColor(ItemStack itemstack, int color) {
        RenderModelNBT renderModelNBT = new RenderModelNBT(itemstack.getTag());
        return renderModelNBT.getColor();
    }
}

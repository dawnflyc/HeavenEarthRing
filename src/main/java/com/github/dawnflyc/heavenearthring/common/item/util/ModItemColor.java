package com.github.dawnflyc.heavenearthring.common.item.util;

import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.item.model.ModModel;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 模型颜色
 */
public class ModItemColor implements IItemColor {

    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public int getColor(ItemStack itemstack, int color) {
        IModelRenderHandler modelRenderHandler = itemstack.getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(() -> new NullPointerException());
        if (modelRenderHandler.getRenderColor() != null) {
            return modelRenderHandler.getRenderColor();
        }
        return color;
    }
}

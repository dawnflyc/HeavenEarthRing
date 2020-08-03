package com.github.dawnflyc.heavenearthring.common.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * 模型渲染能力
 */
public interface IModelRenderHandler extends INBTSerializable<INBT> {
    /**
     * 渲染资源
     *
     * @return
     */
    ResourceLocation getRenderResourceLocation();

    /**
     * 渲染颜色
     *
     * @return
     */
    Integer getRenderColor();
}

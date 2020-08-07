package com.github.dawnflyc.heavenearthring.common.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * 模型渲染能力
 */
public interface IModelRenderHandler extends INBTSerializable<INBT> {
    /**
     * 获取渲染资源
     *
     * @return
     */
    ResourceLocation getRenderResourceLocation();

    /**
     * 修改渲染资源
     * @param renderResourceLocation
     */
    void setRenderResourceLocation(ResourceLocation renderResourceLocation);

    /**
     * 获取渲染颜色
     *
     * @return
     */
    Integer getRenderColor();

    /**
     * 修改渲染颜色
     * @param color
     */
    void setRenderColor(Integer color);


}

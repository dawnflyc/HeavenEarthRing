package com.github.dawnflyc.heavenearthring.common.item.model;

import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

/**
 * 物品模型接口
 */
public interface IItemModel extends IItemProvider {

    /**
     * 获取注册资源对象
     *
     * @return
     */
    ResourceLocation getResourceLocation();

}
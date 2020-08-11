package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.common.item.ModItem;
import com.github.dawnflyc.processtree.Single;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

/**
 * 物品模型接口
 */
@Single
public interface IItemModel extends IItemProvider, ModItem.ModItemRegistered {

    /**
     * 获取注册资源对象
     * @return
     */
    ResourceLocation getResourceLocation();

}
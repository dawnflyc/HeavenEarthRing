package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.common.item.ModItem;
import com.github.dawnflyc.processtree.Single;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

@Single
public interface IItemModel extends IItemProvider, ModItem.ModItemRegistered {


    ResourceLocation getResourceLocation();

}
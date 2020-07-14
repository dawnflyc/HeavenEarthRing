package com.github.dawnflyc.heavenearthring.item.util;

import com.github.dawnflyc.heavenearthring.event.ModRegistry;
import com.github.dawnflyc.heavenearthring.item.model.ItemModelItem;
import com.github.dawnflyc.heavenearthring.item.ModelMudItem;
import com.github.dawnflyc.heavenearthring.item.RandomDyeItem;
import com.github.dawnflyc.heavenearthring.item.SpaceEssenceItem;

public class ModItem {


    public ModItem() {
        ModRegistry.itemRegister(SpaceEssenceItem.ITEM);
        ModRegistry.itemRegister(ModelMudItem.ITEM);
        ModRegistry.itemRegister(ItemModelItem.ITEM);
        ModRegistry.itemRegister(RandomDyeItem.ITEM);
    }
}

package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class GuiModelItem extends Item implements IItemModel {

    public GuiModelItem() {
        super(new Properties().maxStackSize(1));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_gui_model");
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }
}

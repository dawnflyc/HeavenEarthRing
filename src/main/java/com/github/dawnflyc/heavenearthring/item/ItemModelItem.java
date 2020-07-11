package com.github.dawnflyc.heavenearthring.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class ItemModelItem extends Item {

    public static final ItemModelItem ITEM=new ItemModelItem(new Properties());

    public ItemModelItem(Properties properties) {
        super(properties);
        this.setRegistryName(HeavenEarthRing.MOD_ID,"item_model");
    }

    @Override
    public int getItemEnchantability() {
        return 10;
    }
}

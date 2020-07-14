package com.github.dawnflyc.heavenearthring.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemModelItem extends Item implements IModel {

    public static final ItemModelItem ITEM=new ItemModelItem(new Properties());

    public ItemModelItem(Properties properties) {
        super(properties);
        this.setRegistryName(HeavenEarthRing.MOD_ID,"item_model");
    }


    @Override
    public String getTranslationKey(ItemStack stack) {
        CompoundNBT nbt=stack.getTag();
        if (nbt!=null){
            CompoundNBT model=nbt.getCompound("item_model");
            if (model!=null){
                String key=model.getString("langkey");
                if (key!=null && key.trim().length()>0){
                    return key;
                }
            }
        }
        return super.getTranslationKey(stack);
    }
}

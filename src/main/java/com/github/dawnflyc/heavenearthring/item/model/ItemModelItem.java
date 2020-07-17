package com.github.dawnflyc.heavenearthring.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.event.ModRegistry;
import com.github.dawnflyc.heavenearthring.item.ModItem;
import com.github.dawnflyc.processtree.Single;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;


public class ItemModelItem extends Item implements IModel, ModItem.ModItemRegistered {

    public ItemModelItem() {
        super(new Properties());
        this.setRegistryName(HeavenEarthRing.MOD_ID,"item_model");
    }


  /*  @Override
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
    }*/

    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }
}

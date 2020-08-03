package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;


public class ItemModelItem extends Item implements IItemModel {

    public ItemModelItem() {
        super(new Properties());
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_model");
    }


    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }

/*    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

    }*/
}

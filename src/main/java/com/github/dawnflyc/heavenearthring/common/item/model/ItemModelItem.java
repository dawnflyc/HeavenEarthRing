package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;


public class ItemModelItem extends Item implements IItemModel {

    public ItemModelItem() {
        super(new Properties().maxStackSize(1));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_model");
    }

    public ItemModelItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Minecraft.getInstance().currentScreen instanceof EnchantmentScreen){
            tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.enchantment_gui"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }


}

package com.github.dawnflyc.heavenearthring.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * 随机染料物品
 */
public class RandomDyeItem extends Item {

    protected final Random random = new Random();

    public RandomDyeItem() {
        super(new Properties().group(ItemGroup.MISC));
        this.setRegistryName("random_dye");
    }


    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote) {
            if (entityIn instanceof PlayerEntity) {
                if (stack.getTag() == null) {
                    CompoundNBT compoundNBT = new CompoundNBT();
                    compoundNBT.putInt("color", new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)).getRGB());
                    stack.setTag(compoundNBT);
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT compoundNBT = null;
        tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.random_dye"));
        if ((compoundNBT = stack.getTag()) != null) {
            int color = compoundNBT.getInt("color");
            if (color != -1) {
                tooltip.add(new StringTextComponent("RGB:" + color));
            }
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}

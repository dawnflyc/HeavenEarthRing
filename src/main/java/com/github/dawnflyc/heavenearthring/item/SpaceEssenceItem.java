package com.github.dawnflyc.heavenearthring.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.KeybindTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceEssenceItem extends Item {


    public SpaceEssenceItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote){
            ItemStack main=playerIn.getHeldItem(Hand.MAIN_HAND);
            ItemStack off=playerIn.getHeldItem(Hand.OFF_HAND);
            if (main.getItem() instanceof SpaceEssenceItem && !(off.getItem() instanceof BlockItem)){
                if (main.getCount()>0 && off.getCount()>0){
                    if (!playerIn.isCreative()){
                        main.setCount(main.getCount()-1);
                        off.setCount(main.getCount()-1);
                    }
                    playerIn.addItemStackToInventory(new ItemStack(Items.STONE));
                }

            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new KeybindTextComponent("tooltip.item.space_essence"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}

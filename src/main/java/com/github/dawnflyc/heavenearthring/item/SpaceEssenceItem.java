package com.github.dawnflyc.heavenearthring.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.KeybindTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceEssenceItem extends Item {


    public SpaceEssenceItem(Properties properties) {
        super(properties);
        this.setRegistryName(HeavenEarthRing.MOD_ID,"space_essence");
    }

    public static final Item ITEM=new SpaceEssenceItem(new Item.Properties().group(ItemGroup.MISC));

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && handIn==Hand.MAIN_HAND){
            ItemStack main=playerIn.getHeldItem(Hand.MAIN_HAND);
            ItemStack off=playerIn.getHeldItem(Hand.OFF_HAND);
            if (main.getItem() instanceof SpaceEssenceItem && !(off.getItem() instanceof BlockItem)){
                if (main.getCount()>0 && off.getCount()>0){
                    ItemStack is=new ItemStack(off.getItem());
                   CompoundNBT nbt= off.getTag();
                   if (nbt==null){
                       nbt=new CompoundNBT();
                   }else {
                       nbt=nbt.copy();
                   }
                    ItemStackHelper.saveAllItems(nbt, NonNullList.withSize(27,ItemStack.EMPTY));
                    is.setTag(nbt);
                    if (!playerIn.isCreative()){
                        main.setCount(main.getCount()-1);
                        off.setCount(off.getCount()-1);
                    }
                    playerIn.addItemStackToInventory(is);
                    return ActionResult.resultSuccess(main);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.space_essence"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}

package com.github.dawnflyc.heavenearthring.common.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceEssenceItem extends Item implements ModItem.ModItemRegistered {

    public SpaceEssenceItem() {
        super(new Item.Properties().group(ItemGroup.MISC));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "space_essence");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            ItemStack main = playerIn.getHeldItem(Hand.MAIN_HAND);
            ItemStack off = playerIn.getHeldItem(Hand.OFF_HAND);
            if (main.getItem() instanceof SpaceEssenceItem && off.getItem() instanceof IItemModel) {
                if (main.getCount() > 0 && off.getCount() > 0) {
                    ItemStack is = new ItemStack(off.getItem());
                    if (!playerIn.isCreative()) {
                        main.setCount(main.getCount() - 1);
                        off.setCount(off.getCount() - 1);
                    }
                    playerIn.addItemStackToInventory(is);
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

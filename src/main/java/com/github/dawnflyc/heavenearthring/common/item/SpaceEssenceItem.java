package com.github.dawnflyc.heavenearthring.common.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import com.github.dawnflyc.heavenearthring.common.nbt.RenderModelNBT;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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

/**
 * 空间精髓物品
 */
public class SpaceEssenceItem extends Item {

    private static final Logger LOGGER = LogManager.getLogger();

    public SpaceEssenceItem() {
        super(new Item.Properties().group(ItemGroup.MISC));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "space_essence");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            ItemStack main = playerIn.getHeldItem(Hand.MAIN_HAND);
            ItemStack off = playerIn.getHeldItem(Hand.OFF_HAND);
            if (main.getItem() instanceof SpaceEssenceItem && off.getItem() instanceof IItemModel) {
                if (main.getCount() > 0 && off.getCount() > 0) {
                    ItemStack itemStack = new ItemStack(ModItem.REG_ITEMS.get("item_gui_model"));
                    RenderModelNBT renderModelNBT = new RenderModelNBT(off.getTag());
                    CompoundNBT compoundNBT = new CompoundNBT();
                    renderModelNBT.serializeNBT(compoundNBT);
                    itemStack.setTag(compoundNBT);
                    playerIn.addItemStackToInventory(itemStack);
                    if (!playerIn.isCreative()) {
                        main.shrink(1);
                        off.shrink(1);
                    }
                    return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
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

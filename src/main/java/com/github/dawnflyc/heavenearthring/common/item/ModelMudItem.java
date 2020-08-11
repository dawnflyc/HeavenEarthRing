package com.github.dawnflyc.heavenearthring.common.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.SimpleBakedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
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
 * 用于制作模型的模型泥
 */
public class ModelMudItem extends Item implements ModItem.ModItemRegistered {

    private static final Logger LOGGER = LogManager.getLogger();

    public ModelMudItem() {
        super(new Properties().group(ItemGroup.MISC));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "model_mud");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote) {
            if (context.getItem().getCount() > 0) {
                if (!(context.getHand() == Hand.MAIN_HAND && !context.getPlayer().getHeldItem(Hand.OFF_HAND).isEmpty() && !(context.getPlayer().getHeldItem(Hand.OFF_HAND).getItem() instanceof IItemModel))) {
                    //根据方块制作物品模型线
                    BlockState blockState = context.getWorld().getBlockState(context.getPos());
                    ItemStack stack = createModelByBlockState(context.getWorld(), context.getPos());
                    //减少模型泥数量，给予模型
                    if (stack != null) {
                        if (!context.getPlayer().isCreative()) {
                            context.getItem().setCount(context.getItem().getCount() - 1);
                        }
                        context.getPlayer().addItemStackToInventory(stack);
                        return ActionResultType.SUCCESS;
                    }
                }
            }

        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            if (handIn == Hand.MAIN_HAND && !(playerIn.getHeldItem(Hand.OFF_HAND).getItem() instanceof IItemModel)) {
                ItemStack main = playerIn.getHeldItem(Hand.MAIN_HAND);
                ItemStack off = playerIn.getHeldItem(Hand.OFF_HAND);
                if (main.getCount() > 0 && off.getCount() > 0) {
                    ItemStack itemStack = createModelByItem(off);
                    if (itemStack != null) {
                        if (!playerIn.isCreative()) {
                            main.setCount(main.getCount() - 1);
                        }
                        playerIn.addItemStackToInventory(itemStack);
                        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, main);
                    }
                }
            }
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.model_mud"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    protected ItemStack createModelByItem(ItemStack itemStack) {
        return createModel(itemStack, -1);
    }

    protected ItemStack createModel(ItemStack itemStack, int color) {
        IBakedModel bakedModel = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(itemStack, null, null);
        if (bakedModel instanceof SimpleBakedModel) {
            ItemStack is = new ItemStack(ModItem.REG_ITEMS.get("item_model"), 1);
            IModelRenderHandler modelRenderHandler = is.getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(() -> new NullPointerException());
            modelRenderHandler.setRenderResourceLocation(itemStack.getItem().getRegistryName());
            int itemColor = Minecraft.getInstance().getItemColors().getColor(itemStack, 0);
            modelRenderHandler.setRenderColor(itemColor);
            return is;
        }
        return null;
    }

    protected ItemStack createModelByBlockState(World world, BlockPos pos) {
        Item item = world.getBlockState(pos).getBlock().asItem();
        if (item != null && !item.equals(Items.AIR)) {
            int color = Minecraft.getInstance().getBlockColors().getColor(world.getBlockState(pos), world, pos, 0);
            ItemStack itemStack = createModel(new ItemStack(item), color);
            return itemStack;
        }
        return null;
    }
}
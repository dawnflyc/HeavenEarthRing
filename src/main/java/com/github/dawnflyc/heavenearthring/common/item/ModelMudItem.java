package com.github.dawnflyc.heavenearthring.common.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import com.github.dawnflyc.heavenearthring.common.network.ModelPack;
import com.github.dawnflyc.heavenearthring.common.network.Networking;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
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
public class ModelMudItem extends Item {

    private static final Logger LOGGER = LogManager.getLogger();

    public ModelMudItem() {
        super(new Properties().group(ItemGroup.MISC));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "model_mud");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (context.getWorld().isRemote && context.getItem().getCount() > 0) {
            if (!(context.getHand() == Hand.MAIN_HAND && !context.getPlayer().getHeldItem(Hand.OFF_HAND).isEmpty() && !(context.getPlayer().getHeldItem(Hand.OFF_HAND).getItem() instanceof IItemModel))) {

                //减少模型泥数量，给予模型
/*                        ItemStack itemStack=new ItemStack(ModItem.REG_ITEMS.get("item_model"));
                        CompoundNBT compoundNBT=new CompoundNBT();
                        new RenderModelNBT(context.getWorld().getBlockState(context.getPos()).getBlock().getRegistryName()).serializeNBT(compoundNBT);
                        itemStack.setTag(compoundNBT);
                          if (!context.getPlayer().isCreative()) {
                                context.getItem().setCount(context.getItem().getCount() - 1);
                            }
                            context.getPlayer().addItemStackToInventory(itemStack);*/
                createModel(context.getPlayer(), context.getWorld(), context.getPos());

                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isRemote && handIn == Hand.MAIN_HAND && !(playerIn.getHeldItem(Hand.OFF_HAND).getItem() instanceof IItemModel)) {
            ItemStack main = playerIn.getHeldItem(Hand.MAIN_HAND);
            ItemStack off = playerIn.getHeldItem(Hand.OFF_HAND);
            if (main.getCount() > 0 && off.getCount() > 0) {
                //减少模型泥数量，给予模型
/*
                    ItemStack itemStack=new ItemStack(ModItem.REG_ITEMS.get("item_model"));
                    CompoundNBT nbt=new CompoundNBT();
                    new RenderModelNBT(playerIn.getHeldItem(Hand.OFF_HAND).getItem().getRegistryName()).serializeNBT(nbt);
                    itemStack.setTag(nbt);
                        if (!playerIn.isCreative()) {
                            playerIn.getHeldItem(handIn).setCount( playerIn.getHeldItem(handIn).getCount() - 1);
                        }
                        playerIn.addItemStackToInventory(itemStack);
*/
                createModel(playerIn, off);

                return new ActionResult<ItemStack>(ActionResultType.SUCCESS, main);
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

    @OnlyIn(Dist.CLIENT)
    protected Integer getItemColor(ItemStack itemStack) {
        return Minecraft.getInstance().getItemColors().getColor(itemStack, 0);
    }

    @OnlyIn(Dist.CLIENT)
    protected Integer getBlockColor(World world, BlockPos pos) {
        return Minecraft.getInstance().getBlockColors().getColorOrMaterialColor(world.getBlockState(pos), world, pos);
    }

    @OnlyIn(Dist.CLIENT)
    protected void createModel(PlayerEntity playerEntity, ItemStack itemStack) {
        createModel(playerEntity, itemStack.getItem().getRegistryName(), getItemColor(itemStack));
    }

    @OnlyIn(Dist.CLIENT)
    protected void createModel(PlayerEntity playerEntity, World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        createModel(playerEntity, blockState.getBlock().getRegistryName(), getBlockColor(world, pos));
    }

    @OnlyIn(Dist.CLIENT)
    protected void createModel(PlayerEntity playerEntity, ResourceLocation resourceLocation, int color) {
        Networking.INSTANCE.sendToServer(new ModelPack(playerEntity.getUniqueID(), resourceLocation, color));
    }
}

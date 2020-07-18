package com.github.dawnflyc.heavenearthring.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.event.ModRegistry;
import com.github.dawnflyc.heavenearthring.item.model.IModel;
import com.github.dawnflyc.heavenearthring.item.model.ItemModelItem;
import com.github.dawnflyc.heavenearthring.item.util.ModelMudBakedModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.SimpleBakedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ModelMudItem extends Item implements ModItem.ModItemRegistered{

    private static final Logger LOGGER = LogManager.getLogger();

    public ModelMudItem() {
        super(new Properties().group(ItemGroup.MISC));
        this.setRegistryName(HeavenEarthRing.MOD_ID,"model_mud");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote){
            if (context.getItem().getCount()>0) {
                if (!(context.getHand()==Hand.MAIN_HAND && !context.getPlayer().getHeldItem(Hand.OFF_HAND).isEmpty() && !(context.getPlayer().getHeldItem(Hand.OFF_HAND).getItem() instanceof IModel))) {
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
        if (!worldIn.isRemote){
            if (handIn == Hand.MAIN_HAND && !(playerIn.getHeldItem(Hand.OFF_HAND).getItem() instanceof IModel)){
                ItemStack main=playerIn.getHeldItem(Hand.MAIN_HAND);
                ItemStack off=playerIn.getHeldItem(Hand.OFF_HAND);
                if (main.getCount()>0 && off.getCount()>0){
                   ItemStack itemStack= createModelByItem(off);
                   if (itemStack!=null){
                       if (!playerIn.isCreative()){
                           main.setCount(main.getCount()-1);
                       }
                       playerIn.addItemStackToInventory(itemStack);
                       return new ActionResult<ItemStack>(ActionResultType.SUCCESS,main);
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

    protected ItemStack createModelByItem(ItemStack itemStack){
            return createModel(itemStack,-1);
        }

        protected ItemStack createModel(ItemStack itemStack,int color){
            IBakedModel bakedModel = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(itemStack, null, null);
            if ( bakedModel instanceof SimpleBakedModel){
                ItemStack is=new ItemStack(ModRegistry.REG_ITEMS.get("item_model"),1);
                CompoundNBT nbt= new CompoundNBT();
                nbt.putString("id",itemStack.getItem().getRegistryName().toString());
                nbt.putString("langkey",itemStack.getTranslationKey());
                int itemColor=Minecraft.getInstance().getItemColors().getColor(itemStack,0);
                nbt.putInt("color", color!=-1 ? color : itemColor!=-1 ? itemColor : -1);
                CompoundNBT compoundNBT=new CompoundNBT();
                compoundNBT.put("item_model",nbt);
                is.setTag(compoundNBT);
                is.setDisplayName(itemStack.getDisplayName());
                return is;
            }
            return null;
        }

    protected ItemStack createModelByBlockState(World world,BlockPos pos){
        Item item= world.getBlockState(pos).getBlock().asItem();
        if (item!=null && !item.equals(Items.AIR)){
            int color=Minecraft.getInstance().getBlockColors().getColor(world.getBlockState(pos),world,pos,0);
            ItemStack itemStack=createModel(new ItemStack(item),color);
            return itemStack!=null ? itemStack : null;
        }
        return null;
    }
}

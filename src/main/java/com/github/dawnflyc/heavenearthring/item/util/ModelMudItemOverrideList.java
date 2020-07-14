package com.github.dawnflyc.heavenearthring.item.util;

import com.github.dawnflyc.heavenearthring.item.model.ItemModelItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class ModelMudItemOverrideList extends ItemOverrideList {

    public static ModelProperty<ItemStack> itemStackModelProperty = new ModelProperty<>();
    @Nullable
    @Override
    public IBakedModel getModelWithOverrides(IBakedModel model, ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
        if (stack.getItem() instanceof ItemModelItem) {
            CompoundNBT nbt = stack.getTag();
            CompoundNBT itemModel;
            String id;
            if (nbt != null && (itemModel = (CompoundNBT) nbt.get("item_model")) != null && (id=itemModel.getString("id"))!=null) {
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
                if (item != null) {
                    Minecraft minecraft = Minecraft.getInstance();
                    IBakedModel bakedModel = minecraft.getItemRenderer().getItemModelWithOverrides(new ItemStack(item), worldIn, null);
                    MultipartBakedModel.Builder builder=new MultipartBakedModel.Builder();
                    if (bakedModel instanceof SimpleBakedModel){
                        return bakedModel;
                    }else if (bakedModel instanceof BuiltInModel){
                        //Block block=((BlockItem)item).getBlock();
                        builder.putModel(blockState ->{return true;},bakedModel);
                    }
                }
            }
        }
        return super.getModelWithOverrides(model, stack, worldIn, entityIn);
    }
}

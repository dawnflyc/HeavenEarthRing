package com.github.dawnflyc.heavenearthring.client.model;

import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import com.github.dawnflyc.heavenearthring.common.nbt.RenderModelNBT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.SimpleBakedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class ModelMudItemOverrideList extends ItemOverrideList {


    @Nullable
    @Override
    public IBakedModel getModelWithOverrides(IBakedModel model, ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
        if (stack.getItem() instanceof IItemModel) {
            RenderModelNBT renderModelNBT = new RenderModelNBT(stack.getTag());
            if (renderModelNBT.getResourceLocation() != null && !Items.AIR.getRegistryName().equals(renderModelNBT.getResourceLocation())) {
                SimpleBakedModel simpleBakedModel = findBakedModel(renderModelNBT.getResourceLocation());
                if (simpleBakedModel != null) {
                    return simpleBakedModel;
                }
            }
        }

        //Minecraft.getInstance().getTextureManager().
        //ResourceLocation resourceLocation=new ResourceLocation(HeavenEarthRing.MOD_ID,"error_model");
        //AtlasTexture t=new AtlasTexture(resourceLocation);
        //Minecraft.getInstance().textureManager.loadTexture(resourceLocation,t);
        return model;
        //new SimpleBakedModel.Builder((BlockModel) this.unbakedModel,this,true)
        //.setTexture(t.getSprite(resourceLocation)).build();
        //.getModel(new ModelResourceLocation(new ResourceLocation(HeavenEarthRing.MOD_ID,"error_model"),"inventory"));
    }


    protected SimpleBakedModel findBakedModel(ResourceLocation resourceLocation) {
        Item item = ForgeRegistries.ITEMS.getValue(resourceLocation);
        if (item != null && item != Items.AIR) {
            Minecraft minecraft = Minecraft.getInstance();
            IBakedModel bakedModel = minecraft.getItemRenderer().getItemModelWithOverrides(new ItemStack(item), null, null);
            if (bakedModel instanceof SimpleBakedModel) {
                return (SimpleBakedModel) bakedModel;
            }
        }
        return null;
    }

    protected IBakedModel findBakedModel(ItemStack stack) {
        Minecraft minecraft = Minecraft.getInstance();
        IBakedModel bakedModel = minecraft.getItemRenderer().getItemModelWithOverrides(stack, null, null);
        return bakedModel;
    }
}

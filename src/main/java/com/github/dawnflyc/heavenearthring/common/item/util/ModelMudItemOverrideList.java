package com.github.dawnflyc.heavenearthring.common.item.util;

import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
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
            IModelRenderHandler modelRenderHandler = stack.getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(() -> new NullPointerException());
            if (modelRenderHandler.getRenderResourceLocation() != null) {
                SimpleBakedModel simpleBakedModel = fineBakedModel(modelRenderHandler.getRenderResourceLocation());
                if (simpleBakedModel != null) {
                    return simpleBakedModel;
                }
            }
        }
        return model;
    }

    protected SimpleBakedModel fineBakedModel(ResourceLocation resourceLocation) {
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
}

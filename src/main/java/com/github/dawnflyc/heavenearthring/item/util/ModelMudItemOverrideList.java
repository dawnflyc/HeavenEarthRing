package com.github.dawnflyc.heavenearthring.item.util;

import com.github.dawnflyc.heavenearthring.client.KeyInputListener;
import com.github.dawnflyc.heavenearthring.item.model.ItemModelItem;
import net.java.games.input.Controller;
import net.java.games.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;

public class ModelMudItemOverrideList extends ItemOverrideList {

    protected final KeyInputListener.Key keyShift=KeyInputListener.getKey(GLFW.GLFW_KEY_LEFT_SHIFT);

    @Nullable
    @Override
    public IBakedModel getModelWithOverrides(IBakedModel model, ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
        if (stack.getItem() instanceof ItemModelItem) {
            CompoundNBT nbt=stack.getTag();
            if (nbt!=null){
                CompoundNBT itemmodel=nbt.getCompound("item_model");

                if (itemmodel!=null){
                    String id=itemmodel.getString("id");
                    String soulid=itemmodel.getString("soulid");
                    boolean sneak=keyShift.repeated() || keyShift.pressed();
                    if (sneak){
                        SimpleBakedModel simpleBakedModel=getBakedModelById(soulid);
                        if (simpleBakedModel!=null){
                            return simpleBakedModel;
                        }
                    }else {
                        SimpleBakedModel simpleBakedModel=getBakedModelById(id);
                        if (simpleBakedModel!=null){
                            return simpleBakedModel;
                        }
                    }

                }
            }
        }
        return model;
    }

    protected  SimpleBakedModel getBakedModelById(String id){
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
        if (item!=null && item!= Items.AIR){
            Minecraft minecraft = Minecraft.getInstance();
            IBakedModel bakedModel = minecraft.getItemRenderer().getItemModelWithOverrides(new ItemStack(item), null, null);
            if (bakedModel instanceof SimpleBakedModel){
                return (SimpleBakedModel) bakedModel;
            }
        }
        return null;
    }
}

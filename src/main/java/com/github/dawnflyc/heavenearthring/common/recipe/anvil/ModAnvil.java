package com.github.dawnflyc.heavenearthring.common.recipe.anvil;

import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelSoulHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelSoulHandler;
import com.github.dawnflyc.heavenearthring.common.item.ModItem;
import com.github.dawnflyc.heavenearthring.common.item.RandomDyeItem;
import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.awt.*;

public class ModAnvil {


    public static void init() {
        //模型染色
        AnvilEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof IItemModel) {
                IModelRenderHandler modelRenderHandler = input.getLeft().getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(() -> new NullPointerException());
                int c = modelRenderHandler.getRenderColor();
                if (c != -1) {
                    DyeColor dyeColor = DyeColor.getColor(input.getRight());
                    int color = 0;
                    if (dyeColor != null) {
                        color = dyeColor.getColorValue();
                    } else if (input.getRight().getItem() instanceof RandomDyeItem) {
                        CompoundNBT nbt = null;
                        if ((nbt = input.getRight().getTag()) != null) {
                            int cc = nbt.getInt("color");
                            if (cc != -1) {
                                color = cc;
                            }
                        }
                    } else {
                        return null;
                    }
                    ItemStack itemStack = input.getLeft().copy();
                    IModelRenderHandler render = itemStack.getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(() -> new NullPointerException());
                    render.setRenderColor(color);
                    return new AnvilIO.AnvilOutput(itemStack, 1, 1);
                }
            }
            return null;
        });
        //灵魂
        AnvilEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof IItemModel && !(input.getRight().getItem() instanceof IItemModel) && !(input.getRight().getItem() instanceof RandomDyeItem)
                    && !(input.getRight().getItem() instanceof DyeItem)) {
                ItemStack itemStack = new ItemStack(ModItem.REG_ITEMS.get("item_soul_model"));
                IModelRenderHandler modelRenderHandler = itemStack.getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(NullPointerException::new);
                IModelRenderHandler modelRenderHandler1 = input.getLeft().getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(NullPointerException::new);
                modelRenderHandler.setRenderColor(modelRenderHandler1.getRenderColor());
                modelRenderHandler.setRenderResourceLocation(modelRenderHandler1.getRenderResourceLocation());
                IModelSoulHandler modelSoulHandler = itemStack.getCapability(CapabilityModelSoulHandler.CAPABILITY).orElseThrow(NullPointerException::new);
                modelSoulHandler.setSoulResourceLocation(input.getRight().getItem().getRegistryName());
                itemStack.setTag(input.getRight().getTag());
                return new AnvilIO.AnvilOutput(itemStack, 1, 1);
            }
            return null;
        });
    }
}

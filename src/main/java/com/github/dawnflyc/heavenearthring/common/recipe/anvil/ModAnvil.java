package com.github.dawnflyc.heavenearthring.common.recipe.anvil;

import com.github.dawnflyc.heavenearthring.common.item.ModItem;
import com.github.dawnflyc.heavenearthring.common.item.RandomDyeItem;
import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import com.github.dawnflyc.heavenearthring.common.nbt.FoodModelNBT;
import com.github.dawnflyc.heavenearthring.common.nbt.RenderModelNBT;
import com.github.dawnflyc.heavenearthring.common.nbt.SoulModelNBT;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;

public class ModAnvil {


    public static void init() {
        //模型染色
        AnvilEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof IItemModel) {
                RenderModelNBT renderModelNBT = new RenderModelNBT(input.getLeft().getTag());
                int c = renderModelNBT.getColor();
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
                renderModelNBT.setColor(color);
                CompoundNBT compoundNBT = new CompoundNBT();
                renderModelNBT.serializeNBT(compoundNBT);
                itemStack.setTag(compoundNBT);
                return new AnvilIO.AnvilOutput(itemStack, 1, 1);
            }
            return null;
        });
        //灵魂
        AnvilEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof IItemModel && !(input.getRight().getItem() instanceof IItemModel) && !(input.getRight().getItem() instanceof RandomDyeItem)
                    && !(input.getRight().getItem() instanceof DyeItem)) {
                if (!Items.AIR.equals(input.getRight().getItem()) && !Items.AIR.getRegistryName().equals(input.getRight().getItem().getRegistryName()) && input.getLeft().getCount() > 0 && input.getRight().getCount() > 0) {
                    //声明
                    ItemStack itemStack;
                    CompoundNBT compoundNBT = new CompoundNBT();
                    RenderModelNBT renderModelNBT = new RenderModelNBT(input.getLeft().getTag());
                    renderModelNBT.serializeNBT(compoundNBT);

                    //判断灵魂
                    if (input.getRight().isFood()) {
                        itemStack = new ItemStack(ModItem.REG_ITEMS.get("item_food_model"));
                        FoodModelNBT foodModelNBT = new FoodModelNBT(input.getRight().getItem().getFood());
                        foodModelNBT.serializeNBT(compoundNBT);

                    } else {
                        itemStack = new ItemStack(ModItem.REG_ITEMS.get("item_soul_model"));
                        SoulModelNBT soulModelNBT = new SoulModelNBT(input.getRight().getItem().getRegistryName());
                        soulModelNBT.serializeNBT(compoundNBT);
                    }

                    itemStack.setTag(compoundNBT);
                    return new AnvilIO.AnvilOutput(itemStack, 1, 1);
                }
            }
            return null;
        });
    }
}


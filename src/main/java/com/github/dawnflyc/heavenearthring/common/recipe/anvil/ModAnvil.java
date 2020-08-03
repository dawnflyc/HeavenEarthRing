package com.github.dawnflyc.heavenearthring.common.recipe.anvil;

import com.github.dawnflyc.heavenearthring.common.item.RandomDyeItem;
import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ModAnvil {


    public ModAnvil() {
        //模型染色
        AnvilEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof IItemModel) {
                CompoundNBT compoundNBT = input.getLeft().getTag().copy();
                if (compoundNBT != null) {
                    CompoundNBT model = compoundNBT.getCompound("item_model");
                    if (model != null) {
                        int c = model.getInt("color");
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
                            ItemStack out = input.getLeft().copy();
                            model.putInt("color", color);
                            out.setTag(compoundNBT);
                            return new AnvilIO.AnvilOutput(out, 1, 1);
                        }
                    }
                }
            }
            return null;
        });

        AnvilEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof IItemModel && !(input.getRight().getItem() instanceof IItemModel) && !(input.getRight().getItem() instanceof RandomDyeItem)
                    && !(input.getRight().getItem() instanceof BlockItem)) {
                CompoundNBT compoundNBT = input.getLeft().getTag().copy();
                if (compoundNBT != null) {
                    CompoundNBT modelitem = compoundNBT.getCompound("item_model");
                    if (modelitem != null) {
                        modelitem.putString("soulid", input.getRight().getItem().getRegistryName().toString());
                        ItemStack itemStack = input.getLeft().copy();
                        itemStack.setTag(compoundNBT);
                        return new AnvilIO.AnvilOutput(itemStack, 1, 1);
                    }
                }
            }
            return null;
        });
    }
}

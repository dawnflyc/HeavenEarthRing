package com.github.dawnflyc.heavenearthring.recipe.anvil;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.item.RandomDyeItem;
import com.github.dawnflyc.heavenearthring.item.model.IModel;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class ModAnvil {


    public ModAnvil() {
        //模型染色
        AnvilEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof IModel){
                CompoundNBT compoundNBT=input.getLeft().getTag().copy();
                if (compoundNBT!=null){
                    CompoundNBT model=compoundNBT.getCompound("item_model");
                    if (model!=null){
                        int c=model.getInt("color");
                        if (c!=-1){
                            DyeColor dyeColor= DyeColor.getColor(input.getRight());
                            int color=0;
                            if (dyeColor!=null){
                                color=dyeColor.getColorValue();
                            }else if (input.getRight().getItem() instanceof RandomDyeItem){
                                CompoundNBT nbt=null;
                                if ((nbt=input.getRight().getTag())!=null){
                                    int cc=nbt.getInt("color");
                                    if (cc!=-1){
                                        color=cc;
                                    }
                                }
                            }
                            ItemStack out=input.getLeft().copy();
                            model.putInt("color",color);
                            out.setTag(compoundNBT);
                            return new AnvilIO.AnvilOutput(out,1,1);
                        }
                    }
                }
            }
            return null;
        });
    }
}

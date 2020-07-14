package com.github.dawnflyc.heavenearthring.recipe.anvil;

import com.github.dawnflyc.heavenearthring.item.RandomDyeItem;
import com.github.dawnflyc.heavenearthring.item.model.IModel;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class ModAnvil {


    public ModAnvil() {
        //添加铁砧配方,继承附魔
        AnvilEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof IModel || input.getRight().getItem() instanceof IModel) {
                if (input.getRight().getTag() != null) {
                    ItemStack result = input.getLeft().copy();
                    ListNBT bookNbt = input.getRight().getTag().getList("StoredEnchantments", 10);
                    ListNBT itemNbt = input.getRight().getTag().getList("Enchantments", 10);
                    ListNBT nbt = itemNbt != null && itemNbt.size()>0 ? itemNbt.copy() : bookNbt != null && bookNbt.size()>0 ? bookNbt.copy() : null;
                    if (nbt != null) {
                        CompoundNBT nbt1=result.getTag();
                        if (nbt1==null){
                            nbt1=new CompoundNBT();
                            result.setTag(nbt1);
                        }
                        nbt1.put("Enchantments", nbt);
                        return new AnvilIO.AnvilOutput(result, 1);
                    }
                }
            }
            return null;
        });
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

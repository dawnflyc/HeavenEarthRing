package com.github.dawnflyc.heavenearthring.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.Arrays;

public class ModelContainerProvider implements INamedContainerProvider {

    protected final ItemStack itemStack;

    public ModelContainerProvider(ItemStack itemStack) {
        this.itemStack=itemStack;
    }

    @Override
    public ITextComponent getDisplayName() {
        return itemStack.getDisplayName();
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        CompoundNBT compoundNBT=this.itemStack.getTag();
        if (compoundNBT!=null && compoundNBT.get("Items")!=null){
            NonNullList<ItemStack> itemStacks= NonNullList.withSize(32,ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(compoundNBT,itemStacks);
            return ChestContainer.createGeneric9X3(p_createMenu_1_,p_createMenu_2_, new ModelInventory(itemStacks));
        }
        return ChestContainer.createGeneric9X3(p_createMenu_1_,p_createMenu_2_, new Inventory());
    }




}

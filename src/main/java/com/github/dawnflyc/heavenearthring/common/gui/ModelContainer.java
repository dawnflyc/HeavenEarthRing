package com.github.dawnflyc.heavenearthring.common.gui;

import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModelContainer extends Container {

    protected PlayerInventory playerInventory;

    protected ItemStack itemStack;

    public ModelContainer(int id, PlayerInventory playerInventory, ItemStack itemStack) {
        super(ContainerTypeRegistry.modelContainer.get(), id);
        this.playerInventory = playerInventory;
        this.itemStack = itemStack;
        IItemHandlerModifiable flowerBagInv = (IItemHandlerModifiable) itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        int i = (3 - 4) * 18;
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new SlotItemHandler(flowerBagInv, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 161 + i));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        ItemStack itemstack1=slot.getStack();
        if (slot != null && slot.getHasStack() && !(this.itemStack.isItemEqual(itemstack1))) {
            itemstack = itemstack1.copy();
            if (index < 27) {
                if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 27, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }



    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        if (this.itemStack.isItemEqual(this.inventorySlots.get(slotId).getStack())){
            return ItemStack.EMPTY;
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}

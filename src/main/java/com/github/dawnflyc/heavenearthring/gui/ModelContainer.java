package com.github.dawnflyc.heavenearthring.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.DeferredRegister;

import javax.annotation.Nullable;

public class ModelContainer extends Container {

    protected PlayerInventory playerInventory;

    protected IInventory iInventory;

    protected ModelContainer(int id,PlayerInventory playerInventory,IInventory iInventory) {
        super(ContainerTypeRegistry.modelContainer.get(),id);
        assertInventorySize(iInventory, 3 * 9);
        this.playerInventory=playerInventory;
        this.iInventory=iInventory;
        iInventory.openInventory(playerInventory.player);
        int i = (3 - 4) * 18;
        for(int j = 0; j < 3; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(iInventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 161 + i));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        
    }
}

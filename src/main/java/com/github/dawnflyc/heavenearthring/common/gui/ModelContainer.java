package com.github.dawnflyc.heavenearthring.common.gui;

import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
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

    public static class InvProvider implements ICapabilitySerializable<INBT> {

        private final IItemHandler inv = new ItemStackHandler(27) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                IItemModel model = stack.getItem() instanceof IItemModel ? (IItemModel) stack.getItem() : null;
                return model == null;
            }
        };
        private final LazyOptional<IItemHandler> opt = LazyOptional.of(() -> inv);

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, opt);
        }

        @Override
        public INBT serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
        }

        @Override
        public void deserializeNBT(INBT nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);
        }
    }
}

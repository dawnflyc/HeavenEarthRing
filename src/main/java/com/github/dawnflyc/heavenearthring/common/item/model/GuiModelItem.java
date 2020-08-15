package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.gui.ModelContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * gui模型物品
 */
@Mod.EventBusSubscriber(modid = HeavenEarthRing.MOD_ID)
public class GuiModelItem extends ItemModelItem {

    private static final Logger LOGGER = LogManager.getLogger();


    public GuiModelItem() {
        super(new Properties().maxStackSize(1));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_gui_model");
    }

    public GuiModelItem(Properties properties) {
        super(properties);
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new ItemStackHanlerProvider();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            INamedContainerProvider container = new SimpleNamedContainerProvider((w, p, pl) -> new ModelContainer(w, p, stack), stack.getDisplayName());
            NetworkHooks.openGui((ServerPlayerEntity) playerIn, container, packetBuffer -> packetBuffer.writeItemStack(stack));
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        if (stack != null) {
            ItemStackHandler itemHandler = (ItemStackHandler) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
            if (itemHandler != null) {
                CompoundNBT compoundNBT = stack.getTag();
                if (compoundNBT != null) {
                    CompoundNBT nbt = itemHandler.serializeNBT();
                    if (nbt != null) {
                        compoundNBT.put("inv", nbt);
                        return compoundNBT;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt) {
        ItemStackHandler itemHandler = (ItemStackHandler) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        if (itemHandler != null) {
            CompoundNBT compoundNBT = stack.getTag();
            if (nbt != null) {
                stack.setTag(nbt);
                readTag(itemHandler, nbt);
            } else if (compoundNBT != null) {
                readTag(itemHandler, compoundNBT);
            }
        }
    }

    protected void readTag(ItemStackHandler itemStackHandler, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT1 = nbt.getCompound("inv");
        if (compoundNBT1 != null) {
            itemStackHandler.deserializeNBT(compoundNBT1);
        }
    }


    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void modelInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.modelInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_gui_info"));
        CompoundNBT compoundNBT = stack.getTag();
        if (compoundNBT != null) {
            CompoundNBT nbt = compoundNBT.getCompound("inv");
        }

    }

    private static class ItemStackHanlerProvider implements ICapabilitySerializable<CompoundNBT> {

        private final LazyOptional<IItemHandler> opt;

        private ItemStackHanlerProvider() {
            opt = LazyOptional.of(() -> new ItemStackHandler(27) {
                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                    return super.isItemValid(slot, stack) && !(stack.getItem() instanceof GuiModelItem);
                }
            });
        }

        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction facing) {
            return getCapability(cap);
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, opt);
        }

        @Override
        public CompoundNBT serializeNBT() {
            IItemHandler itemHandler = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, opt).orElse(null);
            if (itemHandler != null) {
                return ((ItemStackHandler) itemHandler).serializeNBT();
            }
            return new CompoundNBT();
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            IItemHandler itemHandler = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, opt).orElse(null);
            if (itemHandler != null) {
                ((ItemStackHandler) itemHandler).deserializeNBT(nbt);
            }
        }
    }
}

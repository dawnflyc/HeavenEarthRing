package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.ModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.gui.ModelContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = HeavenEarthRing.MOD_ID)
public class GuiModelItem extends ItemModelItem {

    public GuiModelItem() {
        super(new Properties().maxStackSize(1));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_gui_model");
    }

    public GuiModelItem(Properties properties) {
        super(properties);
    }

    public static final ResourceLocation GUI = new ResourceLocation(HeavenEarthRing.MOD_ID, "gui");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof GuiModelItem) {
            event.addCapability(GUI, new ItemStackHanlerProvider());
        }
    }


    private static class ItemStackHanlerProvider implements ICapabilitySerializable<CompoundNBT> {
        private final LazyOptional<IItemHandler> opt;

        private ItemStackHanlerProvider() {
            opt = LazyOptional.of(() -> new InvWrapper(new Inventory(27)){
                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                    return super.isItemValid(slot, stack) && !(stack.getItem() instanceof GuiModelItem);
                }
            });
        }

        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(capability, opt);
        }

        @Override
        public CompoundNBT serializeNBT() {
            IItemHandler itemHandler= CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,opt).orElseThrow(NullPointerException::new);
            if (itemHandler instanceof ItemStackHandler){
                return ((ItemStackHandler)(itemHandler)).serializeNBT();
            }
            return new CompoundNBT();
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            IItemHandler itemHandler= CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,opt).orElseThrow(NullPointerException::new);
            if (itemHandler instanceof ItemStackHandler){
                 ((ItemStackHandler)(itemHandler)).deserializeNBT(nbt);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote){
            ItemStack stack = playerIn.getHeldItem(handIn);
            INamedContainerProvider container = new SimpleNamedContainerProvider((w, p, pl) -> new ModelContainer(w, p, stack), stack.getDisplayName());
            NetworkHooks.openGui((ServerPlayerEntity) playerIn, container,packetBuffer -> packetBuffer.writeItemStack(stack));
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }


    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }
}

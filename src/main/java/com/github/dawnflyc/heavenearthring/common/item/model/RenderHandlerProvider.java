package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.ModelRenderHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = HeavenEarthRing.MOD_ID)
public class RenderHandlerProvider implements ICapabilitySerializable {

    public static final ResourceLocation RANDER = new ResourceLocation(HeavenEarthRing.MOD_ID, "render");

    private final LazyOptional<IModelRenderHandler> opt;

    public RenderHandlerProvider() {
        this.opt = LazyOptional.of(ModelRenderHandler::new);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityModelRenderHandler.CAPABILITY.orEmpty(cap, opt);
    }


    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof IItemModel){
            event.addCapability(RANDER,new RenderHandlerProvider());
        }
    }

    @Override
    public INBT serializeNBT() {
       return CapabilityModelRenderHandler.CAPABILITY.orEmpty(CapabilityModelRenderHandler.CAPABILITY, opt).orElseThrow(NullPointerException::new).serializeNBT();
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        CapabilityModelRenderHandler.CAPABILITY.orEmpty(CapabilityModelRenderHandler.CAPABILITY, opt).orElseThrow(NullPointerException::new).deserializeNBT(nbt);
    }
}

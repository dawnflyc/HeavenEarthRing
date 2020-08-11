package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelSoulHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelSoulHandler;
import com.github.dawnflyc.heavenearthring.common.capability.ModelSoulHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 模型灵魂能力提供者
 */
@Mod.EventBusSubscriber(modid = HeavenEarthRing.MOD_ID)
public class SoulHandlerProvider implements ICapabilitySerializable {

    public static final ResourceLocation SOUL = new ResourceLocation(HeavenEarthRing.MOD_ID, "soul");

    private final LazyOptional<IModelSoulHandler> opt;

    public SoulHandlerProvider() {
        this.opt = LazyOptional.of(ModelSoulHandler::new);
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof SoulModelItem) {
            event.addCapability(SOUL, new SoulHandlerProvider());
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return CapabilityModelSoulHandler.CAPABILITY.orEmpty(cap, opt);
    }

    @Override
    public INBT serializeNBT() {
        return CapabilityModelSoulHandler.CAPABILITY.orEmpty(CapabilityModelSoulHandler.CAPABILITY, opt).orElseThrow(NullPointerException::new).serializeNBT();
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        CapabilityModelSoulHandler.CAPABILITY.orEmpty(CapabilityModelSoulHandler.CAPABILITY, opt).orElseThrow(NullPointerException::new).deserializeNBT(nbt);
    }

}

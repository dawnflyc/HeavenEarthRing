package com.github.dawnflyc.heavenearthring.common.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityModelRenderHandler implements ICapability {

    @CapabilityInject(IModelRenderHandler.class)
    public static Capability<IModelRenderHandler> CAPABILITY = null;

    @Override
    public void registry() {
        CapabilityManager.INSTANCE.register(IModelRenderHandler.class, new Capability.IStorage<IModelRenderHandler>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IModelRenderHandler> capability, IModelRenderHandler instance, Direction side) {
                return null;
            }

            @Override
            public void readNBT(Capability<IModelRenderHandler> capability, IModelRenderHandler instance, Direction side, INBT nbt) {
            }
        }, () -> null);
    }
}

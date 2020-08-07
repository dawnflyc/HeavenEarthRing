package com.github.dawnflyc.heavenearthring.common.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityModelSoulHandler implements ICapability {

    @CapabilityInject(IModelSoulHandler.class)
    public static Capability<IModelSoulHandler> CAPABILITY;

    @Override
    public void registry() {
        CapabilityManager.INSTANCE.register(IModelSoulHandler.class, new Capability.IStorage<IModelSoulHandler>() {
            @Nullable
            public INBT writeNBT(Capability<IModelSoulHandler> capability, IModelSoulHandler instance, Direction side) {
                return null;
            }

            @Override
            public void readNBT(Capability<IModelSoulHandler> capability, IModelSoulHandler instance, Direction side, INBT nbt) {
            }
        }, () -> null);
    }
}

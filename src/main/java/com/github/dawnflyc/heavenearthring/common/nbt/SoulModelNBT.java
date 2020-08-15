package com.github.dawnflyc.heavenearthring.common.nbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class SoulModelNBT {

    protected ResourceLocation resourceLocation;


    public SoulModelNBT(CompoundNBT nbt) {
        CompoundNBT compoundNBT = nbt.getCompound("soul_model");
        if (compoundNBT != null) {
            this.resourceLocation = new ResourceLocation(compoundNBT.getString("soul"));
        }
    }

    public SoulModelNBT(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public void serializeNBT(CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putString("soul", resourceLocation.toString());
        nbt.put("soul_model", compoundNBT);
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }
}

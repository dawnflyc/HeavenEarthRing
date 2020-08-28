package com.github.dawnflyc.heavenearthring.common.nbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;


public class RenderModelNBT implements IModelNBT {

    protected ResourceLocation resourceLocation;

    protected int color;

    public RenderModelNBT(CompoundNBT nbt) {
        if (nbt != null) {
            CompoundNBT compoundNBT = nbt.getCompound("render_model");
            if (compoundNBT != null) {
                this.resourceLocation = new ResourceLocation(compoundNBT.getString("render"));
                this.color = compoundNBT.getInt("color");
            }
        }
    }

    public RenderModelNBT(ResourceLocation resourceLocation, int color) {
        this.resourceLocation = resourceLocation;
        this.color = color;
    }

    public RenderModelNBT(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
        this.color = -1;
    }

    public void serializeNBT(CompoundNBT nbt) {
        if (nbt != null) {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString("render", this.resourceLocation.toString());
            compoundNBT.putInt("color", this.color);
            nbt.put("render_model", compoundNBT);
        }
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

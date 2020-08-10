package com.github.dawnflyc.heavenearthring.common.capability;

import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ResourceLocation;

public class ModelRenderHandler implements IModelRenderHandler {

    protected String renderId;

    protected int renderColor;

    public ModelRenderHandler(ResourceLocation renderId, int renderColor) {
        this.renderId = renderId.toString();
        this.renderColor = renderColor;
    }

    public ModelRenderHandler() {
        this(Items.AIR.getRegistryName(), -1);
    }

    @Override
    public ResourceLocation getRenderResourceLocation() {
        return new ResourceLocation(this.renderId);
    }

    @Override
    public void setRenderResourceLocation(ResourceLocation renderResourceLocation) {
        this.renderId = renderResourceLocation.toString();
    }

    @Override
    public Integer getRenderColor() {
        return this.renderColor;
    }

    @Override
    public void setRenderColor(Integer color) {
        this.renderColor = color;
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putString("render_id", this.renderId);
        compoundNBT.putInt("render_color", this.renderColor);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        CompoundNBT compoundNBT = (CompoundNBT) nbt;
        this.renderId = compoundNBT.getString("render_id");
        this.renderColor = compoundNBT.getInt("render_color");
    }
}

package com.github.dawnflyc.heavenearthring.common.capability;

import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ResourceLocation;

public class ModelSoulHandler implements IModelSoulHandler {

    protected String soulId;

    public ModelSoulHandler(ResourceLocation soul) {
        this.soulId = soul.toString();
    }

    public ModelSoulHandler() {
        this(Items.AIR.getRegistryName());
    }

    @Override
    public ResourceLocation getSoulResourceLocation() {
        return new ResourceLocation(this.soulId);
    }

    @Override
    public void getSoulResourceLocation(ResourceLocation resourceLocation) {
        this.soulId=resourceLocation.toString();
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT CompoundNBT = new CompoundNBT();
        CompoundNBT.putString("soul_id", this.soulId);
        return CompoundNBT;
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        CompoundNBT compoundNBT = (CompoundNBT) nbt;
        this.soulId = compoundNBT.getString("soul_id");
    }
}

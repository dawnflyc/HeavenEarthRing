package com.github.dawnflyc.heavenearthring.common.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * 模型灵魂附加能力
 */
public interface IModelSoulHandler extends INBTSerializable<INBT> {
    /**
     * 灵魂资源
     *
     * @return
     */
    ResourceLocation getSoulResourceLocation();
}

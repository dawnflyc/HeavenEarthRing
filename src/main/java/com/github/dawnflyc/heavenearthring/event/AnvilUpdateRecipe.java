package com.github.dawnflyc.heavenearthring.event;

import net.minecraft.item.ItemStack;

/**
 * 铁砧配方
 */
public interface AnvilUpdateRecipe {


    /**
     * 铁砧配方
     * @param input 铁砧输入参数
     * @return 铁砧输出参数
     */
    AnvilIO.AnvilOutput anvilUpdate(AnvilIO.AnvilInput input);

}

package com.github.dawnflyc.heavenearthring.item.util;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.event.ModRegistry;
import com.github.dawnflyc.heavenearthring.item.ItemModelItem;
import com.github.dawnflyc.heavenearthring.item.ModelMudItem;
import com.github.dawnflyc.heavenearthring.item.RandomDyeItem;
import com.github.dawnflyc.heavenearthring.item.SpaceEssenceItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModItem {


    public ModItem() {
        ModRegistry.itemRegister(SpaceEssenceItem.ITEM);
        ModRegistry.itemRegister(ModelMudItem.ITEM);
        ModRegistry.itemRegister(ItemModelItem.ITEM);
        ModRegistry.itemRegister(RandomDyeItem.ITEM);
    }
}

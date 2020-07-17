package com.github.dawnflyc.heavenearthring.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.Random;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemColor implements IItemColor {

    @Override
    public int getColor(ItemStack p_getColor_1_, int p_getColor_2_) {
        CompoundNBT nbt=p_getColor_1_.getTag();
        if (nbt!=null){
            CompoundNBT model=nbt.getCompound("item_model");
            if (model!=null){
                int color=model.getInt("color");
                if (color!=0){
                    return color;
                }
            }
        }
        return p_getColor_2_;
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        IItemProvider[] iItemProviders=new IItemProvider[ModModel.LIST.size()];
        for (int i = 0; i < ModModel.LIST.size(); i++) {
            iItemProviders[i]=ModModel.LIST.get(i);
        }
        event.getItemColors().register(new ModItemColor(),iItemProviders);
    }
}

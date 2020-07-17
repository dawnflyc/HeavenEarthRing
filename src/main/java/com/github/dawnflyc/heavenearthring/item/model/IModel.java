package com.github.dawnflyc.heavenearthring.item.model;

import com.github.dawnflyc.heavenearthring.item.util.ModelMudBakedModel;
import com.github.dawnflyc.processtree.Single;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Single
public interface IModel extends IItemProvider {


     ResourceLocation getResourceLocation();



}
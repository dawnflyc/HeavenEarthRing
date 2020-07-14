package com.github.dawnflyc.heavenearthring.item.model;

import com.github.dawnflyc.heavenearthring.item.util.ModelMudBakedModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public interface IModel extends IItemProvider {

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();
        ModelResourceLocation location = new ModelResourceLocation(ItemModelItem.ITEM.getRegistryName(), "inventory");
        IBakedModel existingModel = modelRegistry.get(location);
        if (existingModel == null) {
            throw new RuntimeException("改物品未注册于注册表中");
        } else if (existingModel instanceof ModelMudBakedModel) {
            throw new RuntimeException("两次尝试！");
        } else {
            ModelMudBakedModel modelMudBakedModel = new ModelMudBakedModel(existingModel);
            event.getModelRegistry().put(location, modelMudBakedModel);
        }
    }
}

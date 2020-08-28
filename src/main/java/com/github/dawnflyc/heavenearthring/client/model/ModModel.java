package com.github.dawnflyc.heavenearthring.client.model;

import com.github.dawnflyc.heavenearthring.common.item.model.IItemModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模型注册器
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModModel {

    public final static List<IItemModel> LIST = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger();

    public static IItemModel registerIModel(IItemModel model) {
        LIST.add(model);
        return model;
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent event) {
        LIST.forEach(iModel -> {
            Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();
            ModelResourceLocation location = new ModelResourceLocation(iModel.getResourceLocation(), "inventory");
            IBakedModel existingModel = modelRegistry.get(location);
            if (existingModel == null) {
                throw new RuntimeException("改物品未注册于注册表中");
            } else if (existingModel instanceof ModelMudBakedModel) {
                throw new RuntimeException("两次尝试！");
            } else {
                ModelMudBakedModel modelMudBakedModel = new ModelMudBakedModel(existingModel);
                event.getModelRegistry().put(location, modelMudBakedModel);
            }
        });
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        IItemProvider[] iItemProviders = new IItemProvider[ModModel.LIST.size()];
        for (int i = 0; i < ModModel.LIST.size(); i++) {
            iItemProviders[i] = ModModel.LIST.get(i);
        }
        event.getItemColors().register(new ModItemColor(), iItemProviders);
    }


}

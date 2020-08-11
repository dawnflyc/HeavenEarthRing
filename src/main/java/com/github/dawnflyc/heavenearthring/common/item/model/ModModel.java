package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.common.item.util.ModelMudBakedModel;
import com.github.dawnflyc.processtree.ITreeHandler;
import com.github.dawnflyc.processtree.Result;
import com.github.dawnflyc.processtree.TreeScan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
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
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@TreeScan(recursive = true, priority = -10, method = IItemModel.class)
public class ModModel implements ITreeHandler<IItemModel> {

    public final static List<IItemModel> LIST = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger();

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

    @Override
    public void handle(Result<IItemModel> result) {
        result.build().forEach(iModel -> {
            LIST.add(iModel);
            LOGGER.info("模型染色注册" + iModel.getClass().getName() + ":" + iModel.hashCode());
        });

    }


}

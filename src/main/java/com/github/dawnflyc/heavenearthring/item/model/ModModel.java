package com.github.dawnflyc.heavenearthring.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.item.ModItem;
import com.github.dawnflyc.heavenearthring.item.util.ModelMudBakedModel;
import com.github.dawnflyc.processtree.ITreeHandler;
import com.github.dawnflyc.processtree.Result;
import com.github.dawnflyc.processtree.TreeScan;
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

import java.util.*;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@TreeScan(packageName = "com.github.dawnflyc.heavenearthring.item.model",recursive = true,priority = -10,method = IModel.class)
public class ModModel implements ITreeHandler<IModel> {

    public final static List<IModel> LIST=new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handle(Result<IModel> result) {
        result.build().forEach(iModel -> {
            LIST.add(iModel);
            LOGGER.info("模型染色注册"+iModel.getClass().getName()+":"+iModel.hashCode());
        });

    }

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


}

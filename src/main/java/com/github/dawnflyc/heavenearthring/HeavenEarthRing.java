package com.github.dawnflyc.heavenearthring;

import com.github.dawnflyc.heavenearthring.client.ClientProxy;
import com.github.dawnflyc.heavenearthring.client.gui.GuiModelContainer;
import com.github.dawnflyc.heavenearthring.common.CommonProxy;
import com.github.dawnflyc.heavenearthring.common.capability.ModCapability;
import com.github.dawnflyc.heavenearthring.common.config.Config;
import com.github.dawnflyc.heavenearthring.common.gui.ContainerTypeRegistry;
import com.github.dawnflyc.heavenearthring.common.gui.ModelContainer;
import com.github.dawnflyc.heavenearthring.common.item.ModItem;
import com.github.dawnflyc.heavenearthring.common.item.model.ModModel;
import com.github.dawnflyc.heavenearthring.common.recipe.anvil.ModAnvil;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 模组入口类
 */
@Mod("heavenearthring")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HeavenEarthRing {
    /**
     * 版本
     */
    public static final String VERSION = "@MOD_VERSION@";
    /**
     * modid
     */
    public static final String MOD_ID = "heavenearthring";
    /**
     * mod名
     */
    public static final String MOD_NAME = "Heaven Earth Ring";
    /**
     * 日志
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * 代理
     */
    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);


    public HeavenEarthRing() {

        ModItem.register();
        ModAnvil.init();
        ContainerTypeRegistry.CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    }

    @SubscribeEvent
    public static void CommonSetup(final FMLCommonSetupEvent event) {
        new ModCapability();
        ScreenManager.registerFactory(ContainerTypeRegistry.modelContainer.get(), (ModelContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) -> {
            return new GuiModelContainer(screenContainer, inv, titleIn);
        });
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {

    }
}

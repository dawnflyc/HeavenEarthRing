package com.github.dawnflyc.heavenearthring.client.gui;

import com.github.dawnflyc.heavenearthring.common.gui.ContainerTypeRegistry;
import com.github.dawnflyc.heavenearthring.common.gui.ModelContainer;
import com.google.common.eventbus.Subscribe;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * 客户端事件
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEventHandler {

    @Subscribe
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ContainerTypeRegistry.modelContainer.get(), (ModelContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) -> {
            return new GuiModelContainer(screenContainer, inv, titleIn);
        });
    }
}

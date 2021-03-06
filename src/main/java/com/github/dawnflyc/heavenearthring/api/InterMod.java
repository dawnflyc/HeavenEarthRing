package com.github.dawnflyc.heavenearthring.api;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 和其他模组交互的类
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InterMod {


    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * 发送信息
     *
     * @param event
     */
    private void enqueueIMC(final InterModEnqueueEvent event) {
        //InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    /**
     * 收信息
     *
     * @param event
     */
    private void processIMC(final InterModProcessEvent event) {
/*        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));*/
    }
}

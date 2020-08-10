package com.github.dawnflyc.heavenearthring.common.capability;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapability {

    public ModCapability() {

        new CapabilityModelRenderHandler().registry();
        new CapabilityModelSoulHandler().registry();
    }
}

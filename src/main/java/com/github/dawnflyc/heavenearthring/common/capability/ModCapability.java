package com.github.dawnflyc.heavenearthring.common.capability;

import com.github.dawnflyc.processtree.ITreeHandler;
import com.github.dawnflyc.processtree.Result;
import com.github.dawnflyc.processtree.TreeScan;
import net.minecraftforge.fml.common.Mod;


//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapability {

    public ModCapability() {

        new CapabilityModelRenderHandler().registry();
        new CapabilityModelSoulHandler().registry();
    }
}

package com.github.dawnflyc.heavenearthring.common.capability;

import com.github.dawnflyc.processtree.ITreeHandler;
import com.github.dawnflyc.processtree.Result;
import com.github.dawnflyc.processtree.TreeScan;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@TreeScan(method = ICapability.class)
public class ModCapability implements ITreeHandler<ICapability> {

    @Override
    public void handle(Result<ICapability> result) {
        result.build().forEach(iCapability -> iCapability.registry());
    }
}

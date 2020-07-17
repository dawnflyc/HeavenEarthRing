package com.github.dawnflyc.heavenearthring.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.event.ModRegistry;
import com.github.dawnflyc.processtree.ITreeHandler;
import com.github.dawnflyc.processtree.Result;
import com.github.dawnflyc.processtree.TreeScan;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@TreeScan(packageName = "com.github.dawnflyc.heavenearthring.item",recursive = true,priority = 0, method = ModItem.ModItemRegistered.class)
public class ModItem implements ITreeHandler<Item> {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handle(Result<Item> result) {
        result.build().forEach(item -> {
            ModRegistry.itemRegister(item);
            LOGGER.info("物品注册"+item.getClass().getName()+":"+item.hashCode());
        });
    }

    public interface ModItemRegistered{}
}

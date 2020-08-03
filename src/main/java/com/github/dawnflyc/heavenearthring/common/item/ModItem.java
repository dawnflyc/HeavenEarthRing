package com.github.dawnflyc.heavenearthring.common.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.processtree.ITreeHandler;
import com.github.dawnflyc.processtree.Result;
import com.github.dawnflyc.processtree.TreeScan;
import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@TreeScan(priority = 0, method = ModItem.ModItemRegistered.class)
public class ModItem implements ITreeHandler<Item> {

    public ModItem() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, HeavenEarthRing.MOD_ID);

    public static final Map<String,Item> REG_ITEMS=new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handle(Result<Item> result) {
        result.build().forEach(item -> {
            ITEMS.register(item.getRegistryName().getPath(), () -> item);
            REG_ITEMS.put(item.getRegistryName().getPath(),item);
            LOGGER.info("物品注册" + item.getClass().getName() + ":" + item.hashCode());
        });
    }

    public interface ModItemRegistered {
    }
}

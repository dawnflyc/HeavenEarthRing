package com.github.dawnflyc.heavenearthring.common.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.processtree.ITreeHandler;
import com.github.dawnflyc.processtree.Result;
import com.github.dawnflyc.processtree.TreeScan;
import com.google.common.eventbus.Subscribe;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@TreeScan(priority = 0, method = ModItem.ModItemRegistered.class)
public class ModItem implements ITreeHandler<Item> {




    public static final Map<String,Item> REG_ITEMS=new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handle(Result<Item> result) {
        result.build().forEach(item -> {
            REG_ITEMS.put(item.getRegistryName().getPath(),item);
            LOGGER.info("物品注册" + item.getClass().getName() + ":" + item.hashCode());
        });
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event){
        REG_ITEMS.forEach((s, item) -> event.getRegistry().register(item));
    }


    public interface ModItemRegistered {

    }
}

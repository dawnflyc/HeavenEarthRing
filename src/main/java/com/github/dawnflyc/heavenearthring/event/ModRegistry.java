package com.github.dawnflyc.heavenearthring.event;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Map<String, Item> REG_ITEMS=new HashMap<>();

    public ModRegistry() {

    }

    /**
     * 注册物品
     * @param itemid
     * @param item
     */
    public static void itemRegister(String itemid,Item item){
        item.setRegistryName(HeavenEarthRing.MOD_ID,itemid);
        REG_ITEMS.put(itemid,item);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        for (Item value : REG_ITEMS.values()) {
            itemRegistryEvent.getRegistry().register(value);
            LOGGER.info("注册物品"+value.getRegistryName().getPath()+"成功！");
        }
    }


}

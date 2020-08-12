package com.github.dawnflyc.heavenearthring.common.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.item.model.GuiModelItem;
import com.github.dawnflyc.heavenearthring.common.item.model.ItemModelItem;
import com.github.dawnflyc.heavenearthring.common.item.model.ModModel;
import com.github.dawnflyc.heavenearthring.common.item.model.SoulModelItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 物品注册器
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItem {


    public static final Map<String, Item> REG_ITEMS = new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        REG_ITEMS.forEach((s, item) -> event.getRegistry().register(item));
    }

    public static Item registerItem(Item item) {
        REG_ITEMS.put(item.getRegistryName().getPath(), item);
        return item;
    }

    public static Item registerItem(String registerName, Item item) {
        REG_ITEMS.put(registerName, item.setRegistryName(HeavenEarthRing.MOD_ID, registerName));
        return item;
    }

    public static Item registerItem(ResourceLocation registerName, Item item) {
        REG_ITEMS.put(registerName.getPath(), item.setRegistryName(registerName));
        return item;
    }

    public static void register(){
        registerItem(new ModelMudItem());
        registerItem(new RandomDyeItem());
        registerItem(new SpaceEssenceItem());

        registerItem((Item) ModModel.registerIModel(new ItemModelItem()));
        registerItem((Item) ModModel.registerIModel(new GuiModelItem()));
        registerItem((Item) ModModel.registerIModel(new SoulModelItem()));


    }

}

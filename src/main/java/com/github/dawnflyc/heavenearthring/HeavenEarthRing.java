package com.github.dawnflyc.heavenearthring;

import com.github.dawnflyc.heavenearthring.client.ClientProxy;
import com.github.dawnflyc.heavenearthring.common.CommonProxy;
import com.github.dawnflyc.heavenearthring.item.util.ModItem;
import com.github.dawnflyc.heavenearthring.recipe.anvil.AnvilIO;
import com.github.dawnflyc.heavenearthring.event.ModEvent;
import com.github.dawnflyc.heavenearthring.event.ModRegistry;
import com.github.dawnflyc.heavenearthring.item.ItemModelItem;
import com.github.dawnflyc.heavenearthring.item.ModelMudItem;
import com.github.dawnflyc.heavenearthring.item.SpaceEssenceItem;
import com.github.dawnflyc.heavenearthring.recipe.anvil.ModAnvil;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("heavenearthring")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HeavenEarthRing {
    /**
     * 代理
     */
    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    /**
     * 日志
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * 版本
     */
    public static final String VERSION = "@MOD_VERSION@";
    /**
     * modid
     */
    public static final String MOD_ID = "heavenearthring";

    /**
     * mod名
     */
    public static final String MOD_NAME = "Heaven Earth Ring";


    public HeavenEarthRing() {
        //注册物品
        new ModItem();
        new  ModAnvil();

    }

    @SubscribeEvent
    public static void CommonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {

    }
}

package com.github.dawnflyc.heavenearthring;

import com.github.dawnflyc.heavenearthring.client.ClientProxy;
import com.github.dawnflyc.heavenearthring.common.CommonProxy;
import com.github.dawnflyc.heavenearthring.event.AnvilIO;
import com.github.dawnflyc.heavenearthring.event.ModEvent;
import com.github.dawnflyc.heavenearthring.event.ModRegistry;
import com.github.dawnflyc.heavenearthring.item.ItemModelItem;
import com.github.dawnflyc.heavenearthring.item.ModelMudItem;
import com.github.dawnflyc.heavenearthring.item.SpaceEssenceItem;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
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
        ModRegistry.itemRegister(SpaceEssenceItem.ITEM);
        ModRegistry.itemRegister(ModelMudItem.ITEM);
        ModRegistry.itemRegister(ItemModelItem.ITEM);
        //添加铁砧配方
        ModEvent.AddAnvilRecipe(input -> {
            if (input.getLeft().getItem() instanceof ItemModelItem || input.getRight().getItem() instanceof ItemModelItem) {
                if (input.getRight().getTag() != null) {
                    ItemStack result = input.getLeft().copy();
                    ListNBT bookNbt = input.getRight().getTag().getList("StoredEnchantments", 10);
                    ListNBT itemNbt = input.getRight().getTag().getList("Enchantments", 10);
                    ListNBT nbt = itemNbt != null && itemNbt.size()>0 ? itemNbt.copy() : bookNbt != null && bookNbt.size()>0 ? bookNbt.copy() : null;
                    if (nbt != null) {
                        CompoundNBT nbt1=result.getTag();
                        if (nbt1==null){
                            nbt1=new CompoundNBT();
                            result.setTag(nbt1);
                        }
                        nbt1.put("Enchantments", nbt);
                        LOGGER.info(nbt);
                        return new AnvilIO.AnvilOutput(result, 1);
                    }
                }
            }
            return null;
        });

    }

    @SubscribeEvent
    public static void CommonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {

    }
}

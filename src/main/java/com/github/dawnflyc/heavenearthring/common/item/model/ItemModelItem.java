package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.client.KeyInputListener;
import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelRenderHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelRenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;


public class ItemModelItem extends Item implements IItemModel {

    KeyInputListener.Key key= KeyInputListener.getKey(GLFW.GLFW_KEY_LEFT_SHIFT);

    public ItemModelItem() {
        super(new Properties().maxStackSize(1));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_model");
    }

    public ItemModelItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Minecraft.getInstance().currentScreen instanceof EnchantmentScreen){
            tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.enchantment_gui"));
        }
        if (key.pressed() || key.repeated()){
            modelInformation(stack,worldIn,tooltip,flagIn);
        }else {
            tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_info_tip").setStyle(new Style().setColor(TextFormatting.GRAY).setItalic(true)));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public void modelInformation(ItemStack stack, @Nullable World worldIn,List<ITextComponent> tooltip,ITooltipFlag flagIn){
        IModelRenderHandler modelRenderHandler= stack.getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(NullPointerException::new);
        Item item = ForgeRegistries.ITEMS.getValue(modelRenderHandler.getRenderResourceLocation());
        tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_info",item.getName().getString(),modelRenderHandler.getRenderColor()));
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        IModelRenderHandler modelRenderHandler=stack.getCapability(CapabilityModelRenderHandler.CAPABILITY).orElseThrow(NullPointerException::new);
        Item item = ForgeRegistries.ITEMS.getValue(modelRenderHandler.getRenderResourceLocation());
        return item.getTranslationKey();
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }


}

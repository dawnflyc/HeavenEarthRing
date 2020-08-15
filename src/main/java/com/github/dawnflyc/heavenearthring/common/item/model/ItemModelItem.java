package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.client.KeyInputListener;
import com.github.dawnflyc.heavenearthring.common.nbt.RenderModelNBT;
import com.github.dawnflyc.heavenearthring.util.ColorUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 模型物品
 */
public class ItemModelItem extends Item implements IItemModel {

    private static final Logger LOGGER = LogManager.getLogger();
    KeyInputListener.Key key = KeyInputListener.getKey(GLFW.GLFW_KEY_LEFT_SHIFT);

    public ItemModelItem() {
        super(new Properties());
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_model");
    }

    public ItemModelItem(Properties properties) {
        super(properties);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (key.pressed() || key.repeated()) {
            modelInformation(stack, worldIn, tooltip, flagIn);
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_info_tip").setStyle(new Style().setColor(TextFormatting.GRAY).setItalic(true)));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void modelInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        RenderModelNBT renderModelNBT = new RenderModelNBT(stack.getTag());
        Item item = ForgeRegistries.ITEMS.getValue(renderModelNBT.getResourceLocation());
        tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_info", item.getName().getString(), ColorUtil.format(renderModelNBT.getColor())));
        if (Items.AIR.equals(item)) {
            tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_error").setStyle(new Style().setColor(TextFormatting.RED)));
        }

    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        RenderModelNBT renderModelNBT = new RenderModelNBT(stack.getTag());
        Item item = ForgeRegistries.ITEMS.getValue(renderModelNBT.getResourceLocation());
        return item.getTranslationKey();
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }


}

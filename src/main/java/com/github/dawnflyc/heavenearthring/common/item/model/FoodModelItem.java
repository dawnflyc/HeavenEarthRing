package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.nbt.FoodModelNBT;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;

public class FoodModelItem extends ItemModelItem {
    public FoodModelItem() {
        super(new Properties().food(new Food.Builder().build()).maxStackSize(64));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_food_model");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entityLiving;
            FoodModelNBT foodModelNBT = new FoodModelNBT(stack.getTag());
            playerEntity.getFoodStats().addStats((int) foodModelNBT.getHunger(), foodModelNBT.getSaturation());
            worldIn.playSound(null, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            if (!worldIn.isRemote) {
                if (playerEntity instanceof ServerPlayerEntity) {
                    CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) playerEntity, stack);
                }
                for (Pair<EffectInstance, Float> pair : foodModelNBT.getEffects()) {
                    if (pair.getLeft() != null && worldIn.rand.nextFloat() < pair.getRight()) {
                        playerEntity.addPotionEffect(new EffectInstance(pair.getLeft()));
                    }
                }
            }
            if (!playerEntity.isCreative()) {
                stack.shrink(1);
            }
        }
        return stack;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void modelInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.modelInformation(stack, worldIn, tooltip, flagIn);
        if (stack != null && stack.getTag() != null) {
            FoodModelNBT foodModelNBT = new FoodModelNBT(stack.getTag());
            tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_food_info", foodModelNBT.getHunger(), foodModelNBT.getSaturation()));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_soul_error").setStyle(new Style().setColor(TextFormatting.RED)));
        }

    }
}

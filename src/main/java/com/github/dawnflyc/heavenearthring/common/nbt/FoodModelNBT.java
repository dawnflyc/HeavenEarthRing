package com.github.dawnflyc.heavenearthring.common.nbt;

import net.minecraft.item.Food;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraftforge.common.util.Constants;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class FoodModelNBT implements IModelNBT {
    /**
     * 饱食度,能看见的
     */
    protected float hunger;

    /**
     * 饱和度，看不见的
     */
    protected float saturation;

    /**
     * 肉类
     */
    protected boolean meat;

    /**
     * 不饿也能吃
     */
    protected boolean alwaysEdible;

    /**
     * 效果
     */
    protected List<Pair<EffectInstance, Float>> effects;


    public FoodModelNBT(CompoundNBT nbt) {
    if (nbt!=null){
        CompoundNBT compoundNBT=nbt.getCompound("food_model");
        if (compoundNBT!=null){
            this.hunger=compoundNBT.getFloat("hunger");
            this.saturation=compoundNBT.getFloat("saturation");
            this.meat=compoundNBT.getBoolean("meat");
            this.alwaysEdible=compoundNBT.getBoolean("always_edible");

            ListNBT listNBT=compoundNBT.getList("effects", Constants.NBT.TAG_COMPOUND);
            List<Pair<EffectInstance, Float>> list= new ArrayList<>();
            if (listNBT!=null){
                for (INBT effectNBT : listNBT) {
                    if (effectNBT instanceof CompoundNBT){
                        CompoundNBT compoundNBTEffect= (CompoundNBT) effectNBT;
                        CompoundNBT effectInstanceNBT=compoundNBTEffect.getCompound("effect");
                        if (effectInstanceNBT!=null){
                            float level=compoundNBTEffect.getFloat("level");
                            EffectInstance effectInstance=EffectInstance.read(effectInstanceNBT);
                            list.add(Pair.of(effectInstance,level));
                        }
                    }
                }
            }
            this.effects=list;

        }
    }

    }

    public FoodModelNBT(float hunger, float saturation, boolean meat, boolean alwaysEdible, List<Pair<EffectInstance, Float>> effects) {
        this.hunger = hunger;
        this.saturation = saturation;
        this.meat = meat;
        this.alwaysEdible = alwaysEdible;
        this.effects = effects;
    }

    public FoodModelNBT(Food food) {
        this.hunger=food.getHealing();
        this.saturation=food.getSaturation();
        this.meat=food.isMeat();
        this.alwaysEdible=food.canEatWhenFull();
        this.effects=food.getEffects();
    }

    public void serializeNBT(CompoundNBT nbt){
        if (nbt!=null){
            CompoundNBT compoundNBT=new CompoundNBT();
            compoundNBT.putFloat("hunger",this.hunger);
            compoundNBT.putFloat("saturation",this.saturation);
            compoundNBT.putBoolean("meat",this.meat);
            compoundNBT.putBoolean("always_edible",this.alwaysEdible);

            ListNBT effectsNBT=new ListNBT();
            for (Pair<EffectInstance, Float> effect : this.effects) {
                CompoundNBT effectNBT=new CompoundNBT();
                CompoundNBT effectInstance=new CompoundNBT();
                effect.getLeft().write(effectInstance);
                effectNBT.put("effect",effectInstance);
                effectNBT.putFloat("level",effect.getRight());
                effectsNBT.add(effectNBT);
            }

            compoundNBT.put("effects",effectsNBT);
            nbt.put("food_model",compoundNBT);

        }
    }

    public float getHunger() {
        return hunger;
    }

    public void setHunger(float hunger) {
        this.hunger = hunger;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public boolean isMeat() {
        return meat;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
    }

    public boolean isAlwaysEdible() {
        return alwaysEdible;
    }

    public void setAlwaysEdible(boolean alwaysEdible) {
        this.alwaysEdible = alwaysEdible;
    }

    public List<Pair<EffectInstance, Float>> getEffects() {
        return effects;
    }

    public void setEffects(List<Pair<EffectInstance, Float>> effects) {
        this.effects = effects;
    }
}

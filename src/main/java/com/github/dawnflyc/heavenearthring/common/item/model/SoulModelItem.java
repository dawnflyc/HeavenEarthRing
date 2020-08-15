package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.nbt.SoulModelNBT;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class SoulModelItem extends ItemModelItem {


    public SoulModelItem() {
        super(new Properties().maxStackSize(1));
        this.setRegistryName(HeavenEarthRing.MOD_ID, "item_soul_model");

    }

    public SoulModelItem(Properties properties) {
        super(properties);
    }


    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        Item item = findItem(stack);
        if (item != null) {
            item.onUse(worldIn, livingEntityIn, stack, count);
        }
        super.onUse(worldIn, livingEntityIn, stack, count);
    }

    /**
     * 玩家右击
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Item item = findItem(playerIn.getHeldItem(handIn));
        if (item != null) {
            item.onItemRightClick(worldIn, playerIn, handIn);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    /**
     * 右击方块
     */
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        Item item = findItem(context.getItem());
        if (item != null) {
            return item.onItemUse(context);
        }
        return super.onItemUse(context);
    }


    /**
     * 使用方式
     */
    @Override
    public UseAction getUseAction(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            item.getUseAction(stack);
        }
        return super.getUseAction(stack);
    }

    /**
     * 修复率
     */
    @Override
    public float getXpRepairRatio(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getXpRepairRatio(stack);
        }
        return super.getXpRepairRatio(stack);
    }

    /**
     * 使用时间，比如弓箭
     */
    @Override
    public int getUseDuration(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getUseDuration(stack);
        }
        return super.getUseDuration(stack);
    }

    /**
     * 是否有附魔效果
     */
    @Override
    public boolean hasEffect(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.hasEffect(stack);
        }
        return super.hasEffect(stack);
    }

    /**
     * 能否修复
     */
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        Item item = findItem(toRepair);
        if (item != null) {
            return item.getIsRepairable(toRepair, repair);
        }
        return super.getIsRepairable(toRepair, repair);
    }

    /**
     * 可修复
     */
    @Override
    public boolean isRepairable(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.isRepairable(stack);
        }
        return super.isRepairable(stack);
    }

    /**
     * 和实体交互
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        Item item = findItem(stack);
        if (item != null) {
            return item.itemInteractionForEntity(stack, playerIn, target, hand);
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    /**
     * 被玩家扔掉后干什么
     */
    @Override
    public boolean onDroppedByPlayer(ItemStack stack, PlayerEntity player) {
        Item item = findItem(stack);
        if (item != null) {
            item.onDroppedByPlayer(stack, player);
        }
        return super.onDroppedByPlayer(stack, player);
    }

    /**
     * 玩家停止使用
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        Item item = findItem(stack);
        if (item != null) {
            item.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        }
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    /**
     * 被创造出来需要做什么
     */
    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        Item item = findItem(stack);
        if (item != null) {
            item.onCreated(stack, worldIn, playerIn);
        }
        super.onCreated(stack, worldIn, playerIn);
    }

    /**
     * 物品栏tick，被不断调用
     */
    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        Item item = findItem(stack);
        if (item != null) {
            item.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        }
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    /**
     * 工具类型
     */
    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getToolTypes(stack);
        }
        return super.getToolTypes(stack);
    }

    /**
     * 使用完成
     */
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        Item item = findItem(stack);
        if (item != null) {
            return item.onItemUseFinish(stack, worldIn, entityLiving);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    /**
     * 获取破坏方块的速度
     */
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getDestroySpeed(stack, state);
        }
        return super.getDestroySpeed(stack, state);
    }

    /**
     * 是否可以破坏方块
     */
    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return true;
    }

    /**
     * 破坏了方块做什么
     */
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F) {
            stack.damageItem(1, entityLiving, (p_220038_0_) -> {
                p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }

    /**
     * 最大耐久
     */
    @Override
    public int getMaxDamage(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getMaxDamage(stack);
        }
        return super.getMaxDamage(stack);
    }

    /**
     * 攻击伤害
     *
     * @param stack
     * @return
     */
    @Override
    public int getDamage(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getDamage(stack);
        }
        return super.getDamage(stack);
    }

    /**
     * 攻击实体
     */
    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Item item = findItem(stack);
        if (item != null) {
            return item.hitEntity(stack, target, attacker);
        }
        return super.hitEntity(stack, target, attacker);
    }

    /**
     * 能否附魔
     */
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        Item item = findItem(stack);
        if (item != null) {
            return item.canApplyAtEnchantingTable(new ItemStack(item), enchantment);
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    /**
     * 附魔好坏的概率
     */
    @Override
    public int getItemEnchantability() {
        return 10;
    }

    /**
     * 稀有度
     */
    @Override
    public Rarity getRarity(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getRarity(stack);
        }
        return super.getRarity(stack);
    }

    /**
     * 能不能堆叠
     */
    @Override
    public boolean isEnchantable(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.isEnchantable(stack);
        }
        return super.isEnchantable(stack);
    }

    /**
     * 破坏等级
     */
    @Override
    public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getHarvestLevel(stack, tool, player, blockState);
        }
        return super.getHarvestLevel(stack, tool, player, blockState);
    }


    /**
     * 查找物品灵魂
     */
    public Item findItem(ItemStack itemStack) {
        SoulModelNBT soulModelNBT = new SoulModelNBT(itemStack.getTag());
        Item item = ForgeRegistries.ITEMS.getValue(soulModelNBT.getResourceLocation());
        if (!(item instanceof AirItem)) {
            return item;
        }
        return null;
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void modelInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.modelInformation(stack, worldIn, tooltip, flagIn);
        SoulModelNBT soulModelNBT = new SoulModelNBT(stack.getTag());
        Item item = ForgeRegistries.ITEMS.getValue(soulModelNBT.getResourceLocation());
        tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.item_model_soul_info", item.getName().getString()));
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }
}

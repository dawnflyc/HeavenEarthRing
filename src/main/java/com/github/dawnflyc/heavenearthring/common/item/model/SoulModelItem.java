package com.github.dawnflyc.heavenearthring.common.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.common.capability.CapabilityModelSoulHandler;
import com.github.dawnflyc.heavenearthring.common.capability.IModelSoulHandler;
import com.github.dawnflyc.heavenearthring.common.gui.ModelContainer;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.AirItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        CompoundNBT nbt = playerIn.getHeldItem(handIn).getTag();
        if (nbt != null) {
            if (!worldIn.isRemote) {
                ItemStack itemStack = playerIn.getHeldItem(handIn);
                IItemHandlerModifiable flowerBagInv = (IItemHandlerModifiable) itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
                if (flowerBagInv != null) {
                    INamedContainerProvider container = new SimpleNamedContainerProvider((w, p, pl) -> new ModelContainer(w, p, itemStack), itemStack.getDisplayName());
                    NetworkHooks.openGui((ServerPlayerEntity) playerIn, container, buf -> {
                        buf.writeBoolean(handIn == Hand.MAIN_HAND);
                    });
                } else {
                    Item item = findItem(playerIn.getHeldItem(handIn));
                    if (item != null) {
                        return item.onItemRightClick(worldIn, playerIn, handIn);
                    }
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        Item item = findItem(stack);
        if (item != null) {
            return item.onItemUseFinish(stack, worldIn, entityLiving);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }


    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getDestroySpeed(stack, state);
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F) {
            stack.damageItem(1, entityLiving, (p_220038_0_) -> {
                p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getMaxDamage(stack);
        }
        return super.getMaxDamage(stack);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Item item = findItem(stack);
        if (item != null) {
            return item.hitEntity(stack, target, attacker);
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        Item item = findItem(stack);
        if (item != null) {
            return item.canApplyAtEnchantingTable(new ItemStack(item), enchantment);
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getItemEnchantability() {
        return 10;
    }


    @Override
    public Rarity getRarity(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getRarity(stack);
        }
        return super.getRarity(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        Item item = findItem(stack);
        if (item != null) {
            return item.isEnchantable(stack);
        }
        return super.isEnchantable(stack);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
        Item item = findItem(stack);
        if (item != null) {
            return item.getHarvestLevel(stack, tool, player, blockState);
        }
        return super.getHarvestLevel(stack, tool, player, blockState);
    }


    public Item findItem(ItemStack itemStack) {
        IModelSoulHandler modelSoulHandler = itemStack.getCapability(CapabilityModelSoulHandler.CAPABILITY).orElseThrow(NullPointerException::new);
        Item item = ForgeRegistries.ITEMS.getValue(modelSoulHandler.getSoulResourceLocation());
        if (!(item instanceof AirItem)) {
            return item;
        }
        return null;
    }


    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }
}

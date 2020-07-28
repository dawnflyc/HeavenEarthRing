package com.github.dawnflyc.heavenearthring.item.model;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import com.github.dawnflyc.heavenearthring.event.ModRegistry;
import com.github.dawnflyc.heavenearthring.gui.ModelContainerProvider;
import com.github.dawnflyc.heavenearthring.item.ModItem;
import com.github.dawnflyc.processtree.Single;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.minecart.ChestMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ItemModelItem extends Item implements IModel, ModItem.ModItemRegistered {

    public ItemModelItem() {
        super(new Properties().maxStackSize(1));
        this.setRegistryName(HeavenEarthRing.MOD_ID,"item_model");
    }


    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        Item item=getItemByNBT(stack);
        if (item!=null){
          item.onUse(worldIn, livingEntityIn, stack, count);
        }
        super.onUse(worldIn, livingEntityIn, stack, count);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        CompoundNBT nbt=playerIn.getHeldItem(handIn).getTag();
        if (nbt!=null){
            if (!worldIn.isRemote && nbt.get("Items")!=null){
            NetworkHooks.openGui((ServerPlayerEntity) playerIn, new ModelContainerProvider(playerIn.getHeldItem(handIn)));
            }else {
                Item item = getItemByNBT(playerIn.getHeldItem(handIn));
                if (item != null) {
                    return item.onItemRightClick(worldIn, playerIn, handIn);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        Item item=getItemByNBT(stack);
        if (item!=null){
            return item.onItemUseFinish(stack, worldIn, entityLiving);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }


    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Item item=getItemByNBT(stack);
        if (item!=null){
            return item.getDestroySpeed(stack,state);
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F){
            stack.damageItem(1, entityLiving, (p_220038_0_) -> {
                p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        Item item=getItemByNBT(stack);
        if (item!=null){
            return item.getMaxDamage(stack);
        }
        return super.getMaxDamage(stack);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Item item=getItemByNBT(stack);
        if (item!=null){
            return item.hitEntity(stack,target,attacker);
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        Item item=getItemByNBT(stack);
        if (item!=null){
            return item.canApplyAtEnchantingTable(new ItemStack(item),enchantment);
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public int getItemEnchantability() {
        return 10;
    }
    

    @Override
    public Rarity getRarity(ItemStack stack) {
        Item item=getItemByNBT(stack);
        if (item!=null){
            return item.getRarity(stack);
        }
        return super.getRarity(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        Item item=getItemByNBT(stack);
        if (item!=null){
            return item.isEnchantable(stack);
        }
        return super.isEnchantable(stack);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
        Item item=getItemByNBT(stack);
        if (item!=null){
            return item.getHarvestLevel(stack,tool,player,blockState);
        }
        return super.getHarvestLevel(stack,tool,player,blockState);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        return true;
    }

    public Item getItemByNBT(ItemStack itemStack){
        CompoundNBT nbt= itemStack.getTag();
        if (nbt!=null){
            CompoundNBT model=nbt.getCompound("item_model");
            if (model!=null){
                String id=model.getString("soulid");
                if (id.trim().length()>0){
                    Item item= ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
                    if (!(item instanceof AirItem)){
                        return item;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return this.getRegistryName();
    }

/*    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

    }*/
}

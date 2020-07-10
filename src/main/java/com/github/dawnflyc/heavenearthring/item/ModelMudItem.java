package com.github.dawnflyc.heavenearthring.item;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BuiltInModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.SimpleBakedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelMudItem extends Item {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final ModelMudItem ITEM=new ModelMudItem(new Properties().group(ItemGroup.MISC));


    public ModelMudItem(Properties properties) {
        super(properties);
        this.setRegistryName(HeavenEarthRing.MOD_ID,"model_mud");
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();
        ModelResourceLocation location = new ModelResourceLocation(ItemModelItem.ITEM.getRegistryName(), "inventory");
        IBakedModel existingModel = modelRegistry.get(location);
        if (existingModel == null) {
            throw new RuntimeException("改物品未注册于注册表中");
        } else if (existingModel instanceof ModelMudBakedModel) {
            throw new RuntimeException("两次尝试！");
        } else {
            ModelMudBakedModel modelMudBakedModel = new ModelMudBakedModel(existingModel);
            event.getModelRegistry().put(location, modelMudBakedModel);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && handIn==Hand.MAIN_HAND){
            ItemStack main=playerIn.getHeldItem(Hand.MAIN_HAND);
            ItemStack off=playerIn.getHeldItem(Hand.OFF_HAND);
            IBakedModel bakedModel = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(off, worldIn, null);
            if (main.getItem() instanceof ModelMudItem && bakedModel instanceof SimpleBakedModel){
                if (main.getCount()>0 && off.getCount()>0){
                    ItemStack is=new ItemStack(ItemModelItem.ITEM,1);
                    CompoundNBT nbt= new CompoundNBT();
                    nbt.putString("item_model_id",off.getItem().getRegistryName().toString());
                    is.setTag(nbt);
                    is.setDisplayName(off.getDisplayName());
                    if (!playerIn.isCreative()){
                        main.setCount(main.getCount()-1);
                    }
                    playerIn.addItemStackToInventory(is);
                    return ActionResult.resultSuccess(main);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.heavenearthring.item.model_mud"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}

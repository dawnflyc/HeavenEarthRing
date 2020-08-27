package com.github.dawnflyc.heavenearthring.common.network;

import com.github.dawnflyc.heavenearthring.common.item.ModItem;
import com.github.dawnflyc.heavenearthring.common.nbt.RenderModelNBT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.UUID;
import java.util.function.Supplier;

public class ModelPack {

    protected final UUID playerUUID;

    protected final int color;

    protected final ResourceLocation resourceLocation;

    public ModelPack(PacketBuffer buffer) {
        this.playerUUID = buffer.readUniqueId();
        this.resourceLocation = buffer.readResourceLocation();
        this.color = buffer.readInt();
    }

    public ModelPack(UUID playerUUID, ResourceLocation resourceLocation, int color) {
        this.playerUUID = playerUUID;
        this.resourceLocation = resourceLocation;
        this.color = color;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeUniqueId(this.playerUUID);
        buf.writeResourceLocation(this.resourceLocation);
        buf.writeInt(this.color);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            MinecraftServer minecraftServer = ServerLifecycleHooks.getCurrentServer();
            PlayerEntity playerEntity = minecraftServer.getPlayerList().getPlayerByUUID(this.playerUUID);
            if (!this.resourceLocation.equals(Items.AIR.getRegistryName()) && ModItem.REG_ITEMS.get("model_mud").equals(playerEntity.getHeldItem(Hand.MAIN_HAND).getItem())) {
                ItemStack itemStack = new ItemStack(ModItem.REG_ITEMS.get("item_model"));
                RenderModelNBT renderModelNBT = new RenderModelNBT(this.resourceLocation, this.color);
                CompoundNBT compoundNBT = new CompoundNBT();
                renderModelNBT.serializeNBT(compoundNBT);
                itemStack.setTag(compoundNBT);
                playerEntity.addItemStackToInventory(itemStack);
                if (!playerEntity.isCreative()) {
                    ItemStack main = playerEntity.getHeldItem(Hand.MAIN_HAND);
                    main.setCount(main.getCount() - 1);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }


}

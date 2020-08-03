package com.github.dawnflyc.heavenearthring.common.gui;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeRegistry {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, HeavenEarthRing.MOD_ID);

    public static RegistryObject<ContainerType<ModelContainer>> modelContainer = CONTAINERS.register("model_container", () -> {
        return IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) -> {
            return new ModelContainer(windowId, inv, data.readItemStack());
        });
    });
}
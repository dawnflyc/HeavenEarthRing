package com.github.dawnflyc.heavenearthring.common.network;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {

    public static final String VERSION = "0.1.0";
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(HeavenEarthRing.MOD_ID, "create_model"),
                () -> {
                    return VERSION;
                },
                (s) -> {
                    return VERSION.equals(s);
                },
                (s) -> {
                    return VERSION.equals(s);
                });
        INSTANCE.registerMessage(
                nextID(),
                ModelPack.class,
                (pack, buffer) -> {
                    pack.toBytes(buffer);
                },
                (buffer) -> {
                    return new ModelPack(buffer);
                },
                (pack, ctx) -> {
                    pack.handler(ctx);
                }
        );
    }
}

package com.github.dawnflyc.heavenearthring.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Config {

    public static ForgeConfigSpec COMMON_CONFIG;

    //public static

    public static ForgeConfigSpec.ConfigValue white;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("基础");
        white = builder.defineList("list",new ArrayList<>(),o -> {return true;});
        builder.pop();
        COMMON_CONFIG = builder.build();
        System.out.println(white.get());
    }
}

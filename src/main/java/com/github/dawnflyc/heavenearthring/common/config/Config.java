package com.github.dawnflyc.heavenearthring.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.*;


public class Config {

    public static ForgeConfigSpec COMMON_CONFIG;

    //public static

    public static ForgeConfigSpec.ConfigValue white;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("基础");
        /*white = builder.defineList("list", new ArrayList<>(), o -> {
            return true;
        });*/
        Map<ConfigEnum, List<String>> map=new LinkedHashMap<>();
        builder.define("list",map);
        builder.pop();
        COMMON_CONFIG = builder.build();
    }
}

package com.github.dawnflyc.heavenearthring.common.config;

public class ConfigEnum {

    public enum ListType{
        //白名单
        WHITE,
        //黑名单
        BLACK
    }

    public enum ListMode{
        //全名
        NAME_FULL,
        //模糊
        NAME_FUZZY,
        //继承
        EXTEND,
        //矿物词典
        ORE_DICT_FULL,
        //模糊矿物词典
        ORE_DICT_FUZZY
    }
}

package com.github.dawnflyc.heavenearthring.common;

import com.github.dawnflyc.heavenearthring.common.item.ModItem;

public class CommonProxy {

    public void init() {
        ModItem.register();
        ModItem.registerModelItem();
    }
}

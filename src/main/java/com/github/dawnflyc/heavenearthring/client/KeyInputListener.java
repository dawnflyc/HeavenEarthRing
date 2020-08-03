package com.github.dawnflyc.heavenearthring.client;

import com.github.dawnflyc.heavenearthring.HeavenEarthRing;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = HeavenEarthRing.MOD_ID)
public class KeyInputListener {

    private static final HashMap<Integer, Key> MAP = new HashMap<>();

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        Key key = MAP.get(event.getKey());
        if (key != null) {
            key.action = event.getAction();
        }
    }

    public static Key getKey(int code) {
        Key key = MAP.get(code);
        if (key == null) {
            key = new Key(code);
            MAP.put(code, key);
        }
        return key;
    }

    public static class Key {

        private final int key;
        private int action;

        private Key(int key) {
            this.key = key;
            this.action = -1;
        }

        /**
         * 按键释放
         *
         * @return
         */
        public boolean released() {
            return action == 0;
        }

        /**
         * 按下
         *
         * @return
         */
        public boolean pressed() {
            return action == 1;
        }

        /**
         * 一直按住
         *
         * @return
         */
        public boolean repeated() {
            return action == 2;

        }

    }
}

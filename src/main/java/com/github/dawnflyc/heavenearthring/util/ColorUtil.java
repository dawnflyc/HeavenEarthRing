package com.github.dawnflyc.heavenearthring.util;

import java.awt.*;
import java.util.Random;
import java.util.regex.Pattern;

public class ColorUtil {

    public static final Random RANDOM=new Random();
    private static final String STRING = "0123456789ABCDEF";

    public static int randomColor(){
        return new Color(RANDOM.nextInt(256),RANDOM.nextInt(256),RANDOM.nextInt(256)).getRGB();
    }

    public static String format(int color){
        Color color1=new Color(color);
        return ConvertRGBToHex(color1.getRed(),color1.getGreen(),color1.getBlue());
    }

    public static String ConvertRGBToHex(int r, int g, int b) {
        String hex = "";
        if (r >= 0 && r < 256 && g >= 0 && g < 256 && b >= 0 && b < 256) {
            int x, y, z;
            x = r % 16;
            r = (r - x) / 16;
            y = g % 16;
            g = (g - y) / 16;
            z = b % 16;
            b = (b - z) / 16;
            hex =  STRING.substring(r, r + 1) + STRING.substring(x, x + 1) + STRING.substring(g, g + 1) + STRING.substring(y, y + 1) + STRING.substring(b, b + 1) + STRING.substring(z, z + 1);
        }
        return hex;
    }
    public static Color ConvertHexToColor(String hex) {
        Color rgb = null;
        String regex = "^[0-9A-F]{3}|[0-9A-F]{6}$";
        if (hex != null) {
            hex = hex.toUpperCase();
            if (hex.substring(0, 1).equals("#")) {
                hex = hex.substring(1);
            }
            if (Pattern.compile(regex).matcher(hex).matches()) {
                String a, c, d;
                String[] str = new String[3];
                for (int i = 0; i < 3; i++) {
                    a = hex.length() == 6 ? hex.substring(i * 2, i * 2 + 2) : hex.substring(i, i + 1) + hex.substring(i, i + 1);
                    c = a.substring(0, 1);
                    d = a.substring(1, 2);
                    str[i] = String.valueOf(STRING.indexOf(c) * 16 + STRING.indexOf(d));
                }
                Integer r=Integer.valueOf(str[0]);
                Integer g=Integer.valueOf(str[1]);
                Integer b=Integer.valueOf(str[2]);
                if (r!=null && r<256 && g!=null && g<256 && b!=null && b<256){
                    Color color=new Color(r,g,b);
                    return color;
                }
            }
        }
        return rgb;
    }

}

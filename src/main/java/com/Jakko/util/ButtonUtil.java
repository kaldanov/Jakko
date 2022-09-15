package com.Jakko.util;

public class ButtonUtil {

    public static String getButtonName(String text) { return getButtonName(text, 100); }

    public static String getButtonName(String text, int length) {
        text = text.replaceAll("\\s+", " ");
        text = text.replaceAll("\\s+$", "");
        if (text.length() > length) text = text.substring(0, length - 1);
        return text;
    }
}

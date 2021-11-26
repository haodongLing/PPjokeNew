package io.github.haodongling.lib.common.util;

import android.util.DisplayMetrics;

import io.github.haodongling.lib.common.App;

public class PixUtils {

    public static int dp2px(int dpValue) {
        DisplayMetrics metrics = App.Companion.getCONTEXT().getResources().getDisplayMetrics();
        return (int) (metrics.density * dpValue + 0.5f);
    }

    public static int getScreenWidth() {
        DisplayMetrics metrics =App.Companion.getCONTEXT().getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics metrics = App.Companion.getCONTEXT().getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}

package io.github.haodongling.lib.common.util

import android.app.Activity
import android.os.Build
import android.view.View
import io.github.haodongling.lib.common.util.RomUtils
import io.github.haodongling.lib.common.util.StatusBarFontUtils
import android.view.WindowManager
import java.lang.Exception

/**
 * Created by Loyea.com on 7月20日.
 */
object StatusBarFontUtils {
    //    /**
    //     * 修改状态栏颜色，支持4.4以上版本
    //     * @param activity
    //     * @param colorId
    //     */
    //    public static void setStatusBarColor(Activity activity, int colorId) {
    //
    //        //Android6.0（API 23）以上，系统方法
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //            Window window = activity.getWindow();
    //            window.setStatusBarColor(activity.getResources().getColor(colorId));
    //        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
    //            setTranslucentStatus(activity);
    //            //设置状态栏颜色
    //            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
    //            tintManager.setStatusBarTintEnabled(true);
    //            tintManager.setStatusBarTintResource(colorId);
    //        }
    //    }
    /**
     * 设置状态栏模式
     * @param activity
     * @param isTextDark 文字、图标是否为黑色 （false为默认的白色）
     * @return
     */
    @JvmStatic
    fun setStatusBarMode(activity: Activity, isTextDark: Boolean) {
        if (!isTextDark) {
            //文字、图标颜色不变，只修改状态栏颜色
            //setStatusBarColor(activity, colorId);
        } else {
            //修改状态栏颜色和文字图标颜色
            //setStatusBarColor(activity, colorId);
            //4.4以上才可以改文字图标颜色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (RomUtils.isMIUI()) {
                    //小米MIUI系统
                    setMIUIStatusBarTextMode(activity, isTextDark)
                } else if (RomUtils.isFlyme()) {
                    //魅族flyme系统
                    setFlymeStatusBarTextMode(activity, isTextDark)
                } else {
                    //4.4以上6.0以下的其他系统，暂时没有修改状态栏的文字图标颜色的方法，有可以加上
                }
            }
        }
    }

    /**
     * 设置Flyme系统状态栏的文字图标颜色
     * @param activity
     * @param isDark 状态栏文字及图标是否为深色
     * @return
     */
    @JvmStatic
    fun setFlymeStatusBarTextMode(activity: Activity, isDark: Boolean): Boolean {
        val window = activity.window
        var result = false
        if (window != null) {
            try {
                val lp = window.attributes
                val darkFlag =
                    WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                value = if (isDark) {
                    value or bit
                } else {
                    value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                window.attributes = lp
                result = true
            } catch (e: Exception) {
            }
        }
        return result
    }

    /**
     * 设置MIUI系统状态栏的文字图标颜色（MIUIV6以上）
     * @param activity
     * @param isDark 状态栏文字及图标是否为深色
     * @return
     */
    @JvmStatic
    fun setMIUIStatusBarTextMode(activity: Activity, isDark: Boolean): Boolean {
        var result = false
        val window = activity.window
        if (window != null) {
            val clazz: Class<*> = window.javaClass
            try {
                var darkModeFlag = 0
                val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField =
                    clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
                if (isDark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag) //状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag) //清除黑色字体
                }
                result = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                    if (isDark) {
                        activity.window.decorView.systemUiVisibility =
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    } else {
                        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    }
                }
            } catch (e: Exception) {
            }
        }
        return result
    }
}
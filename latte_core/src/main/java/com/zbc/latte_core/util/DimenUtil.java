package com.zbc.latte_core.util;

import android.content.res.Resources;

import com.zbc.latte_core.app.Latte;

/**
 * Created by benchengzhou on 2019/8/1 23:11.
 * 作者邮箱： mappstore@163.com
 * 功能描述： 尺寸计算相关工具类
 * 类    名： DimenUtil
 * 备    注：
 */
public class DimenUtil {


    /**
     * 获取手机屏幕的宽度
     * 返回像素点密度
     *
     * @return
     */
    public static int getScreenWidth() {
        Resources resources = Latte.getApplicationContext().getResources();
        return resources.getDisplayMetrics().widthPixels;
    }


    /**
     * 获取手机屏幕的高度
     * 返回像素点密度
     *
     * @return
     */
    public static int getScreenHeight() {
        Resources resources = Latte.getApplicationContext().getResources();
        return resources.getDisplayMetrics().heightPixels;
    }

}

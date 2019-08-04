package com.zbc.latte_core.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by benchengzhou on 2019/7/31 22:04.
 * 作者邮箱： mappstore@163.com
 * 功能描述： loading动画加载器
 * 类    名： LoaderCreator
 * 备    注：
 */
public final class LoaderCreator {

    private static WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();


    /**
     * @param type
     * @param context
     * @return
     */
    static AVLoadingIndicatorView creat(Context context, String type) {
        final AVLoadingIndicatorView indicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        indicatorView.setIndicator(LOADING_MAP.get(type));
        return indicatorView;
    }


    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains("\\.")) {
            final String defaultPackage = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackage)
                    .append("indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            Class<?> indicator = Class.forName(drawableClassName.toString());
            return (Indicator) indicator.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

}

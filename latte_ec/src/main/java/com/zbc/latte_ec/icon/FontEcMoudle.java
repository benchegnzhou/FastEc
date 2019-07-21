package com.zbc.latte_ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by benchengzhou on 2019/7/20 23:55.
 * 作者邮箱：  mappstore@163.com
 * 功能描述： 自定义图标库支持
 * 类    名： FontEcMoudle
 * 备    注：
 */
public class FontEcMoudle implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "big.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}

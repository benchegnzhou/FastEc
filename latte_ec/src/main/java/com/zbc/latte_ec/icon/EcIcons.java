package com.zbc.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by benchengzhou on 2019/7/21 0:02.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： EcIcons
 * 备    注：
 */
public enum EcIcons implements Icon {
    icon_scan('\ue606'),
    icon_ali_pay('\ue606');
    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        //字体名称一般喜欢中划线
        return name().replace("_","-");
    }

    @Override
    public char character() {
        return 0;
    }
}

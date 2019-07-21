package com.zbc.fastec.application;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.zbc.latte_core.app.Latte;
import com.zbc.latte_ec.icon.FontEcMoudle;

/**
 * Created by benchengzhou on 2019/7/21 16:21.
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： MApplication
 * 备    注： 
 */
public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcons(new FontAwesomeModule())
                .withIcons(new FontEcMoudle())
                .withApiHost("http://127.0.0.1")
                .config();
    }


}

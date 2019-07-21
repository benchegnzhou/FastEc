package com.zbc.latte_core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by benchengzhou on 2019/7/20 19:47.
 * 作者邮箱：mappstore@163.com
 * 功能描述： 全局信息统一的管理类
 * 类    名： Latte
 * 备    注：
 */
public class Latte {
    public static Config init(Context context) {
        Config.getInstance().getLatteConfigs().put(ConfigType.APPLICATION_CONTEXT.name(), context);
        return Config.getInstance();
    }

    private static HashMap<String, Object> getConfigs() {
        return Config.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) Config.getInstance().getLatteConfigs().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}

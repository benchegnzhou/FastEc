package com.zbc.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by benchengzhou on 2019/7/20 19:48.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： Config
 * 备    注：
 */
public class Config {
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICON_FONT_DESCRIPTORS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Config() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, false);
    }

    public static Config getInstance() {
        return getInstance2.config;
    }

    private static class getInstance2 {
        private static Config config = new Config();
    }

    /**
     * 完成配置
     */
    public void config() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, true);
        initIcons();
    }


    public HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public Config withApiHost(String apiHost) {
        LATTE_CONFIGS.put(ConfigType.API_HOST, apiHost);
        return this;
    }

    public final Config withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Config withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public void initIcons() {
        if (ICON_FONT_DESCRIPTORS.size() > 0) {
            final Iconify.IconifyInitializer iconifyInitializer = Iconify.with(ICON_FONT_DESCRIPTORS.get(0));
            for (int i = 0; i < ICON_FONT_DESCRIPTORS.size(); i++) {
                iconifyInitializer.with(ICON_FONT_DESCRIPTORS.get(i));
            }
        }
    }


    public Config withIcons(IconFontDescriptor icons) {
        ICON_FONT_DESCRIPTORS.add(icons);
        return this;
    }


    public boolean checkCongig() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("configration is not ready,call config");
        }
        return isReady;
    }

    public <T> T getConfig(Enum<ConfigType> configType) {
        checkCongig();
        return (T) LATTE_CONFIGS.get(configType);
    }
}

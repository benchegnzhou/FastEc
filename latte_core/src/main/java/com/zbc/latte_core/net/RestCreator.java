package com.zbc.latte_core.net;


import com.zbc.latte_core.app.ConfigType;
import com.zbc.latte_core.app.Latte;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestCreator {

    public static RestfulService getInstance() {
        return getInstance2.restfulService;
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.mParams;
    }

    private static class ParamsHolder {
        private static WeakHashMap<String, Object> mParams = new WeakHashMap<>();
    }

    private static class getInstance2 {
        private static RestfulService restfulService = RestCreatorHolder.RETROFIT.create(RestfulService.class);
    }

    private static class RestCreatorHolder {
        private static final String apiHost = (String) Latte.getConfigs().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(apiHost)
                .client(OkhttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private static final class OkhttpHolder {
        private static int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient()
                .newBuilder()
                //这个方法仅在response的时候回调
                // .addInterceptor( )
                //这个方法会在request和response同时回调
                // .addNetworkInterceptor(new)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
}

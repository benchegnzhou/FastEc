package com.zbc.latte_core.net.intercepter;

import java.util.LinkedHashMap;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

public abstract class BaseIntercepter implements Interceptor {



    /**
     * 通过请求地址中获取地址
     *
     * @param chain
     * @return
     */
    public LinkedHashMap<String, String> getUrlParametes(Chain chain) {
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    public String getUrlParametes(Chain chain, String key) {
        final HttpUrl url = chain.request().url();
        return url.queryParameter(key);
    }


    /**
     * post请求获取表单中的参数信息
     *
     * @param chain
     * @return
     */
    public LinkedHashMap<String, String> getUrlBodyParametes(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    public String getUrlBodyParametes(Chain chain, String key) {
        return getUrlBodyParametes(chain).get(key);
    }

}

package com.zbc.latte_core.net;

import com.zbc.latte_core.net.callback.IError;
import com.zbc.latte_core.net.callback.IFailure;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by benchengzhou on 2019/7/21 22:14.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： RestfulClient
 * 备    注：
 */
public class RestfulClient {
    private final String URL;
    private static WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final RequestBody BODY;

    public RestfulClient(String url,
                         WeakHashMap<String, Object> params,
                         ISuccess success,
                         IFailure failure,
                         IError error,
                         IRequest request,
                         RequestBody body) {
        this.URL = url;
        this.PARAMS = params;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
        this.BODY = body;
    }


    /**
     * 构建者模式（目前在搭建框架的使用场景中很流行）
     *
     * @return
     */
    public static RestfulClientBuilder builder() {
        return new RestfulClientBuilder();
    }
}

package com.zbc.latte_core.net;

import com.zbc.latte_core.R;
import com.zbc.latte_core.net.callback.IError;
import com.zbc.latte_core.net.callback.IFailure;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;
import com.zbc.latte_core.net.callback.RequestCallBacks;

import java.util.WeakHashMap;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by benchengzhou on 2019/7/21 22:14.
 * 作者邮箱： mappstore@163.com
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

    /**
     * 发起请求
     *
     * @param httpMethod
     */
    public void request(HttpMethod httpMethod) {
        final RestfulService service = RestCreator.getInstance();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        switch (httpMethod) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }


    public Callback<String> getRequestCallback() {
        return new RequestCallBacks(REQUEST, SUCCESS, FAILURE, ERROR);
    }


    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


}

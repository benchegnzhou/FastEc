package com.zbc.latte_core.net;

import com.zbc.latte_core.R;
import com.zbc.latte_core.net.callback.IError;
import com.zbc.latte_core.net.callback.IFailure;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;
import com.zbc.latte_core.net.callback.RequestCallBacks;
import com.zbc.latte_core.net.download.DownloadHandle;

import java.io.File;
import java.util.WeakHashMap;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


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
    private final String  DOWN_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final RequestBody BODY;
    private final File FILE;

    public RestfulClient(String url,
                         WeakHashMap<String, Object> params,
                         String  downDir,
                         String  extension,
                         String  fileName,
                         ISuccess success,
                         IFailure failure,
                         IError error,
                         IRequest request,
                         RequestBody body,
                         File file) {
        this.URL = url;
        this.PARAMS = params;
        this.DOWN_DIR = downDir;
        this.NAME = fileName;
        this.EXTENSION = extension;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
        this.BODY = body;
        this.FILE = file;
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
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                if (FILE == null) {
                    return;
                }
                RequestBody body = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part file = MultipartBody.Part.createFormData("file", FILE.getName());
                call = RestCreator.getInstance().upload(URL, file);
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
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be empty");
            }
            //使用原始的post请求
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void download() {
        new DownloadHandle(URL,DOWN_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR,REQUEST).handleDownload();
    }


}

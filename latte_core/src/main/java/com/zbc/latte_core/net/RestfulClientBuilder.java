package com.zbc.latte_core.net;

import com.zbc.latte_core.net.callback.IError;
import com.zbc.latte_core.net.callback.IFailure;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by benchengzhou on 2019/7/22 22:46.
 * 作者邮箱： mappstore@163.com
 * 功能描述： 网络请求构建器
 * 类    名： RestfulClientBuilder
 * 备    注：
 */
public class RestfulClientBuilder {
    private String mUrl;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private IRequest mIRequest;
    private RequestBody mBody;
    private File mFile;
    private String mDownDir;
    private String mExtension;
    private String mFileName;

    RestfulClientBuilder() {
    }

    public final RestfulClientBuilder url(String mUrl) {
        PARAMS.clear();
        this.mUrl = mUrl;
        return this;
    }

    public final RestfulClientBuilder params(WeakHashMap<String, Object> mParams) {
        this.PARAMS.putAll(mParams);
        return this;
    }

    public final RestfulClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestfulClientBuilder file(File file) {
        mFile = file;
        return this;
    }

    public final RestfulClientBuilder file(String filePath) {
        mFile = new File(filePath);
        return this;
    }

    /**
     * 文件下载跟目录
     * @param dir
     * @return
     */
    public final RestfulClientBuilder dir(String dir) {
        mDownDir = dir;
        return this;
    }

    /**
     * 文件的后缀名
     * @param extension
     * @return
     */
    public final RestfulClientBuilder extension(String extension) {
        mExtension = extension;
        return this;
    }

    /**
     * 文件下载保存的名称
     * @param name
     * @return
     */
    public final RestfulClientBuilder name(String name) {
        mFileName = name;
        return this;
    }

    public final RestfulClientBuilder success(ISuccess mISuccess) {
        this.mISuccess = mISuccess;
        return this;
    }

    public final RestfulClientBuilder failure(IFailure mIFailure) {
        this.mIFailure = mIFailure;
        return this;
    }

    public final RestfulClientBuilder error(IError mIError) {
        this.mIError = mIError;
        return this;
    }

    public final RestfulClientBuilder onRequest(IRequest mIRequest) {
        this.mIRequest = mIRequest;
        return this;
    }

    /**
     * 指定类型
     *
     * @param raw
     * @return
     */
    public final RestfulClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RestfulClient build() {
        return new RestfulClient(mUrl, PARAMS, mDownDir, mExtension, mFileName, mISuccess, mIFailure, mIError, mIRequest, mBody, mFile);
    }
}

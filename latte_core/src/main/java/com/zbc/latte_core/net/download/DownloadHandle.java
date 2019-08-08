package com.zbc.latte_core.net.download;

import android.os.AsyncTask;

import com.zbc.latte_core.net.RestCreator;
import com.zbc.latte_core.net.callback.IError;
import com.zbc.latte_core.net.callback.IFailure;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;
import java.util.WeakHashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by benchengzhou on 2019/8/4 20:42.
 * 作者邮箱： mappstore@163.com
 * 功能描述： 文件下载
 * 类    名： DownloadHandle
 * 备    注：
 */
public class DownloadHandle {
    private final String URL;
    private static WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String DOWN_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;

    public DownloadHandle(String url, String downDir, String extension, String name, ISuccess success, IFailure failure, IError error, IRequest request) {
        this.URL = url;
        this.DOWN_DIR = downDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
    }


    public void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreator.getInstance().download(URL, PARAMS)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            SaveFileTask saveFileTask = new SaveFileTask(REQUEST, SUCCESS);
                            saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWN_DIR, EXTENSION, NAME, response.body());

                            //这里一定要注意增加判断，否则会出现文件下载不全的现象
                            if (saveFileTask.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestFinish();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFail(call,t);
                        }
                    }
                });
    }


}

package com.zbc.latte_core.net.intercepter;

import androidx.annotation.RawRes;

import com.zbc.latte_core.util.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by benchengzhou on 2019/8/6 23:21.
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： DebugIntercepter
 * 备    注：
 */
public class DebugIntercepter extends BaseIntercepter {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;


    /**
     * @param debugUrl
     * @param debugRawId
     */
    public DebugIntercepter(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
    }


    private Response getResponse(Chain chain, String json) {
        return new Response
                .Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("ok")
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}

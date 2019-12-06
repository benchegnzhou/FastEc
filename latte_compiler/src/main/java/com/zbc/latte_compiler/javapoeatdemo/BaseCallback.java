package com.zbc.latte_compiler.javapoeatdemo;

import java.lang.Object;
import java.lang.String;

/**
 * 网络请求接口
 */
interface BaseCallback {
    String SUCCESS = "msgSuccess";

    String FAIL = "msgFail";

    void onStart();

    void onFinish();

    void onError(int errorCode, String msg);

    void onSuccess(Object o);
}

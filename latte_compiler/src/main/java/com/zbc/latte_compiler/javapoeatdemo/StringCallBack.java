package com.zbc.latte_compiler.javapoeatdemo;


import java.util.logging.Logger;

public class StringCallBack implements BaseCallback {

    @Override
    public void onStart() {
        Logger.getLogger("StringCallBack").info("onStart method");
    }

    @Override
    public void onFinish() {
        Logger.getLogger("StringCallBack").info("onFinish method");
    }

    @Override
    public void onError(int errorCode, String msg) {
        Logger.getLogger("StringCallBack").info("onError method");
        Logger.getLogger("StringCallBack").info(errorCode + msg);
    }

    @Override
    public void onSuccess(Object o) {

    }
}

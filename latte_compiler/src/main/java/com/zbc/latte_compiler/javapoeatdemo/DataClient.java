package com.zbc.latte_compiler.javapoeatdemo;

import java.lang.String;
import java.lang.System;

/**
 * 网络请求器
 */
class DataClient {
    private String tag = "default_tag";

    private String url;

    private long time = System.currentTimeMillis();

    /**
     * build方法
     */
    public DataClient build() {
        time = System.currentTimeMillis();
        return this;
    }

    /**
     * setTag方法
     */
    public DataClient setTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * get方法
     */
    public DataClient get(String url) {
        this.url = url;
        return this;
    }

    /**
     * execute 方法
     */
    public void execute(BaseCallback baseCallback) {
        if (baseCallback != null) {
            baseCallback.onStart();
        }
        if (baseCallback != null) {
            BaseEntity baseEntity = new BaseEntity();
            baseEntity.setCode(200);
            BaseEntity.DataBean dataBean = new BaseEntity.DataBean();
            dataBean.setName("请求到的数据_____data");
            baseEntity.setData(dataBean);
            baseCallback.onSuccess(baseEntity);
        }
        if (baseCallback != null) {
            baseCallback.onFinish();
        }
    }
}

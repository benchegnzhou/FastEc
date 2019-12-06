package com.zbc.latte_compiler.javapoeatdemo;

public class DataT {


    public void loadData() {
        String url = "http://www.baidu.com";
        new DataClient()
                .build()
                .setTag(this.getClass().getCanonicalName())
                .get(url)
                .execute(new BaseCallback() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(int errorCode, String msg) {

                    }

                    @Override
                    public void onSuccess(Object o) {

                    }
                });
    }


}

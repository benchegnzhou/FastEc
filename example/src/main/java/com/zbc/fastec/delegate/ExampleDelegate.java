package com.zbc.fastec.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.zbc.fastec.R;
import com.zbc.latte_core.delegates.LatteDelegate;
import com.zbc.latte_core.net.RestfulClient;
import com.zbc.latte_core.net.callback.IError;
import com.zbc.latte_core.net.callback.IFailure;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(LayoutInflater inflater, Bundle savedInstanceState) {
//        testRestfulClient();
    }

    private void testRestfulClient() {
        RestfulClient.builder()
                .url("")
                .params("", "")
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestFinish() {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFail() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess() {

                    }
                })
                .build();
    }


}

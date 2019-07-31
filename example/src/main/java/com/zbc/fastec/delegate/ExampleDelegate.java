package com.zbc.fastec.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zbc.fastec.R;
import com.zbc.latte_core.delegates.LatteDelegate;
import com.zbc.latte_core.net.RestfulClient;
import com.zbc.latte_core.net.callback.IError;
import com.zbc.latte_core.net.callback.IFailure;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;

import butterknife.OnClick;
import retrofit2.Response;

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
                .url("https://baijiahao.baidu.com/s?id=1623982842682857068&wfr=spider&for=pc")
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
                    public void onSuccess(Response<String> response) {
                        Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

    @OnClick({R.id.icon_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_text:
                break;
            default:
        }
    }


}

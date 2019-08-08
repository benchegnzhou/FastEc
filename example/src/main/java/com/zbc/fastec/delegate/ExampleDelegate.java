package com.zbc.fastec.delegate;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.zbc.fastec.R;
import com.zbc.latte_core.delegates.LatteDelegate;
import com.zbc.latte_core.net.RestfulClient;
import com.zbc.latte_core.net.callback.IError;
import com.zbc.latte_core.net.callback.IFailure;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;

import butterknife.BindView;

import retrofit2.Call;
import retrofit2.Response;

public class ExampleDelegate extends LatteDelegate implements View.OnClickListener {


    @BindView(R.id.icon_text)
    IconTextView iconText;

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(LayoutInflater inflater, Bundle savedInstanceState) {
        //  iconText.setOnClickListener(this);
    }

    @Override
    protected void initListener() {
//        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        testRestfulClient();
    }

    @Override
    protected void initData() {

    }

    private void testRestfulClient() {
        RestfulClient.builder()
//                .url("https://baijiahao.baidu.com/s?id=1623982842682857068&wfr=spider&for=pc")
                .url("http://192.168.1.9:9090?function=getTime")
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
                    public void onFail(Call<String> call, Throwable t) {
                        Log.e("请求失败", "");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.e("code=" + code, "msg=" + msg);
                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(final Response<String> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_text:
                testRestfulClient();
                break;
            default:
        }
    }


}

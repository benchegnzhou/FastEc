package com.zbc.latte_core.net.callback;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String> {

    private final IRequest request;
    private final ISuccess success;
    private final IFailure failure;
    private final IError error;

    public RequestCallBacks(IRequest request, ISuccess success, IFailure failure, IError error) {
        this.request = request;
        this.success = success;
        this.failure = failure;
        this.error = error;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (success != null) {
                    success.onSuccess(response);
                }
            } else {
                if (error != null) {
                    error.onError(response.code(), response.message());
                }
            }
        }
        if (request != null) {
            request.onRequestFinish();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (failure != null) {
            failure.onFail();
        }
        if (request != null) {
            request.onRequestFinish();
        }
    }
}

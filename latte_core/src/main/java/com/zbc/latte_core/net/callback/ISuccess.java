package com.zbc.latte_core.net.callback;

import retrofit2.Response;

public interface ISuccess {
    void onSuccess(Response<String> response);
}

package com.zbc.latte_core.net.callback;

import retrofit2.Call;

public interface IFailure {
    void onFail(Call<String> call, Throwable t);
}

package com.zbc.fastec.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.zbc.fastec.R;
import com.zbc.latte_core.delegates.LatteDelegate;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(LayoutInflater inflater, Bundle savedInstanceState) {

    }
}

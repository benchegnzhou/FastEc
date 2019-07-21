package com.zbc.fastec;

import com.zbc.fastec.delegate.ExampleDelegate;
import com.zbc.latte_core.actities.ProxyActivity;
import com.zbc.latte_core.delegates.BaseDdelegate;


public class MainActivity extends ProxyActivity {

    @Override
    public BaseDdelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}

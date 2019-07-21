package com.zbc.latte_core.actities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ContentFrameLayout;
import android.widget.FrameLayout;


import com.zbc.latte_core.R;
import com.zbc.latte_core.delegates.BaseDdelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by benchengzhou on 2019/7/21 17:49.
 * 作者邮箱： mappstore@163.com
 * 功能描述： fragment容器activity,这个是要我们的业务activity直接继承的
 * 类    名： ProxyActivity
 * 备    注：
 */
public abstract class ProxyActivity extends SupportActivity {
    public abstract BaseDdelegate setRootDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final FrameLayout containerLayout = new FrameLayout(this);
        containerLayout.setId(R.id.delegate_container);
        setContentView(containerLayout);
        //判断首次加载
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    //进行垃圾回收
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}

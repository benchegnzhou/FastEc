package com.zbc.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by benchengzhou on 2019/7/21 17:52.
 * 作者邮箱： mappstore@163.com
 * 功能描述： 可以策划关闭的fragment
 * 类    名： BaseDdelegate
 * 备    注：
 */
public abstract class BaseDdelegate extends SwipeBackFragment {

    public abstract Object setLayout();

    public abstract void onBindView(LayoutInflater inflater, Bundle savedInstanceState);

    private Unbinder mUnbinder = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;
        //获取填充view
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), null);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(inflater, savedInstanceState);
        }
        return  rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}

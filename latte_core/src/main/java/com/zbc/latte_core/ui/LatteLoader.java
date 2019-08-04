package com.zbc.latte_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;
import com.zbc.latte_core.R;
import com.zbc.latte_core.util.DimenUtil;

import java.util.ArrayList;

/**
 * Created by benchengzhou on 2019/8/1 22:58.
 * 作者邮箱： mappstore@163.com
 * 功能描述：
 * 类    名： LatteLoader
 * 备    注：
 */
public class LatteLoader {
    private static final int LOADING_SIZE_SCALE = 8;
    private static final int LOADING_OFFSIT_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADING_DIALOGS = new ArrayList<>();
    private static final String DEFAULT_LOADING = LoaderStyle.BallClipRotateIndicator.name();


    public static void showLoading(Context context, String type) {
        final AppCompatDialog compatDialog = new AppCompatDialog(context, R.style.LoadingDlailg);
        AVLoadingIndicatorView indicatorView = LoaderCreator.creat(context, type);
        compatDialog.setContentView(indicatorView);
        int screenHeight = DimenUtil.getScreenHeight();
        int screenWidth = DimenUtil.getScreenWidth();
        Window window = compatDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = screenWidth / LOADING_SIZE_SCALE;
            params.height = screenHeight / LOADING_SIZE_SCALE;
            params.height = params.height + screenHeight / LOADING_OFFSIT_SCALE;
            params.gravity = Gravity.CENTER;
        }
        LOADING_DIALOGS.add(compatDialog);
        compatDialog.show();
    }


    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADING);
    }

    public static void stopLoading() {
        for (AppCompatDialog compatDialog : LOADING_DIALOGS) {
            if (compatDialog != null) {
                //使用cancle会有相应的cancle回调产生
                compatDialog.cancel();
            }
        }
    }


}

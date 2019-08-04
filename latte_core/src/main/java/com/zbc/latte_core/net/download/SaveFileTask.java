package com.zbc.latte_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import com.zbc.latte_core.app.Latte;
import com.zbc.latte_core.net.callback.IRequest;
import com.zbc.latte_core.net.callback.ISuccess;
import com.zbc.latte_core.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by benchengzhou on 2019/8/4 20:50.
 * 作者邮箱： mappstore@163.com
 * 功能描述： 异步文件下载任务
 * 类    名： SaveFileTask
 * 备    注：
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private static final String TAG = "SaveFileTask";
    private final IRequest iRequest;
    private final ISuccess iSuccess;
    private String packageName;


    public SaveFileTask(IRequest iRequest, ISuccess iSuccess) {
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
    }

    @Override
    protected File doInBackground(Object... objects) {
        if (iRequest != null) {
            iRequest.onRequestStart();
        }
        String downLoadDir = (String) objects[0];
        String extension = (String) objects[1];
        String name = (String) objects[2];
        ResponseBody responseBody = (ResponseBody) objects[3];
        InputStream inputStream = responseBody.byteStream();
        //downLoadDir为空指定默认下载目录
        if (TextUtils.isEmpty(downLoadDir)) {
            downLoadDir = "down_dir";
        }
        //extension为空指定默认文件扩展名
        if (TextUtils.isEmpty(extension)) {
            extension = ".temp";
        }
        //name为空指定默认文件名
        if (TextUtils.isEmpty(name)) {
            return FileUtil.writeToDisk(inputStream, downLoadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(inputStream, downLoadDir, name, extension);
        }
    }


    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (iSuccess != null) {
            iSuccess.onSuccess(null);
        }
        if (iRequest != null) {
            iRequest.onRequestFinish();
        }
        autoInstallApk(file);
    }


    /**
     * 自动安装apk文件
     */
    private void autoInstallApk(File file) {
        if (file != null && file.exists() && "apk".equals(FileUtil.getExtension(file.getAbsolutePath()))) {
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Log.w(TAG, "版本大于 N ，开始使用 fileProvider 进行安装");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(
                        Latte.getApplicationContext()
                        // , "你的包名.fileprovider"
                        , packageName + ".fileprovider"
                        , file);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                Log.w(TAG, "正常进行安装");
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            Latte.getApplicationContext().startActivity(intent);
        }
    }
}

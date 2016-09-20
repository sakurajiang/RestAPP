package com.example.jdk.restapp.CustomizedWidget;

import android.app.Activity;
import android.content.Context;

import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.SnackBarUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by JDK on 2016/8/16.
 */
public abstract class ProgressSubscriber<T> extends Subscriber<T> implements progressCancelListener{

    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public ProgressSubscriber(Context context) {
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            SnackBarUtils.makeLong(((Activity)context).getWindow().getDecorView(), context.getResources().getString(R.string.network_error)).danger();
        } else if (e instanceof ConnectException) {
            SnackBarUtils.makeLong(((Activity) context).getWindow().getDecorView(), context.getResources().getString(R.string.network_error)).danger();
        } else {
            SnackBarUtils.makeLong(((Activity) context).getWindow().getDecorView(), "error:" + e.getMessage()).danger();
        }
        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */


    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}


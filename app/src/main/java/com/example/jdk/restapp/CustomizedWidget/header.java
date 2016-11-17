package com.example.jdk.restapp.CustomizedWidget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by JDK on 2016/10/19.
 */
public class header extends View implements SwipeRefreshTrigger, SwipeTrigger {
    ProgressDialog progressDialog;
    public header(Context context) {
        super(context);
    }

    public header(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressDialog=new ProgressDialog(context);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= 5) {
                progressDialog.show();
            } else {
               progressDialog.dismiss();
            }
        } else {
           progressDialog.dismiss();
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        progressDialog.dismiss();
    }

    @Override
    public void onReset() {

    }
}

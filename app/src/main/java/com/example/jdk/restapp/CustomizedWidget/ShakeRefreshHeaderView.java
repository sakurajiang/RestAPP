package com.example.jdk.restapp.CustomizedWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.example.jdk.restapp.R;

/**
 * Created by JDK on 2016/9/15.
 */
public class ShakeRefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {
    public ShakeRefreshHeaderView(Context context) {
        super(context);
    }

    public ShakeRefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public void onRefresh() {
        setText("");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                setText(getResources().getString(R.string.shake_refresh_header));
            } else {
                setText("");
            }
        } else {
            setText("");
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        setText("");
    }

    @Override
    public void onReset() {
        setText("");
    }

}

package com.example.jdk.restapp.CustomizedWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.example.jdk.restapp.R;

/**
 * Created by JDK on 2016/9/15.
 */
public class ShakeLoadMoreFooterView extends TextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    public ShakeLoadMoreFooterView(Context context) {
        super(context);
    }

    public ShakeLoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        setText("");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                setText(getResources().getString(R.string.shake_loadmore_footer));
            } else {
                setText("");
            }
        } else {
            setText("");
        }
    }

    @Override
    public void onRelease() {
        setText("");
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

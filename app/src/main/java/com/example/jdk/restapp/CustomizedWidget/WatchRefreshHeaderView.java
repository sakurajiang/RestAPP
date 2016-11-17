package com.example.jdk.restapp.CustomizedWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by JDK on 2016/9/14.
 */
public class WatchRefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {
    public WatchRefreshHeaderView(Context context) {
        super(context);
    }

    public WatchRefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public void onRefresh() {
        setText("正在刷新");
    }

    @Override
    public void onPrepare()
    {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
               setText("释放刷新");
            } else {
                setText("刷新");
            }
        } else {
            setText("刷新返回");
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        setText("刷新完成");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
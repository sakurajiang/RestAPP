package com.example.jdk.restapp.CustomizedWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by JDK on 2016/9/15.
 */
public class WatchLoadMoreFooterView extends TextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    public WatchLoadMoreFooterView(Context context) {
        super(context);
    }

    public WatchLoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        setText("正在加载更多");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                setText("释放加载更多");
            } else {
                setText("刷新加载更多");
            }
        } else {
            setText("加载返回");
        }
    }

    @Override
    public void onRelease() {
        setText("加载更多");
    }

    @Override
    public void onComplete() {
        setText("加载完成");
    }

    @Override
    public void onReset() {
        setText("");
    }
}

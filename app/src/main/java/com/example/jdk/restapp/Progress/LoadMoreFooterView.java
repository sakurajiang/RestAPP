package com.example.jdk.restapp.Progress;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by JDK on 2016/8/9.
 */
public class LoadMoreFooterView extends TextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    private boolean isLoad;

    public LoadMoreFooterView(Context context) {
        super(context);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        setText("加载中。。。");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                if (!isLoad) {
                    setText("释放加载更多");
                } else {
                    setText("加载中。。。");
                }
            } else {
                setText("向上滑动加载更多");
            }
        } else {
//            setText("LOAD MORE RETURNING");
            isLoad = false;
        }
    }

    @Override
    public void onRelease() {
        isLoad = true;
        setText("加载中。。。");
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

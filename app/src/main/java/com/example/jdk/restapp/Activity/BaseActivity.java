package com.example.jdk.restapp.Activity;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by JDK on 2016/8/8.
 */
public class BaseActivity extends Activity{
    public void setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

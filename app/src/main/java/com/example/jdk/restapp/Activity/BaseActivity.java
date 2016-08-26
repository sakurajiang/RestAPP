package com.example.jdk.restapp.Activity;

import android.app.Activity;
<<<<<<< HEAD

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;

import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.jdk.restapp.R;
=======
import android.view.Window;
import android.view.WindowManager;
>>>>>>> origin/master

/**
 * Created by JDK on 2016/8/8.
 */
<<<<<<< HEAD
public class BaseActivity extends FragmentActivity {
=======
public class BaseActivity extends Activity{
>>>>>>> origin/master
    public void setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
<<<<<<< HEAD
    public void setWebViewAdaptScreen(int layout_id,int id){
        setContentView(layout_id);
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.webview_linearlayout);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width,height-260);
        linearLayout.setLayoutParams(params);
    }
    public void changeTitle(int title){
        setContentView(R.layout.activity_user);
        TextView textView= (TextView) findViewById(R.id.user_title);
        textView.setText(title);
    }
    public FragmentTransaction getFragmentTransaction(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        return fragmentTransaction;
    }
    public void addFragment(int id,Fragment fragment){
       getFragmentTransaction().add(id, fragment).commit();

    }
    public void replaceFragment(int id,Fragment fragment){
        getFragmentTransaction().replace(id,fragment).addToBackStack(null).commit();
    }
=======
>>>>>>> origin/master
}

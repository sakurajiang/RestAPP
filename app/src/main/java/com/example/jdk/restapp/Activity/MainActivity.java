package com.example.jdk.restapp.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jdk.restapp.Adapter.AdapterInditorViewPage;
import com.example.jdk.restapp.Fragment.AndroidFragment;
import com.example.jdk.restapp.Fragment.MeiziFragment;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.ModelData.MeiziData;
import com.example.jdk.restapp.R;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnPageChange;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends FragmentActivity {
    ImageView imageView;
    Fragment meiziFragment;
    List<Fragment> fragmentList;
    String [] TITLE;
    ViewPager myViewPager;
    TabPageIndicator myTabPageIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitVariable();
        myTabPageIndicator= (TabPageIndicator) findViewById(R.id.myIndicatorViewPage);
        myViewPager= (ViewPager) findViewById(R.id.myViewPager);
        InitListener();
        myViewPager.setAdapter(new AdapterInditorViewPage(getSupportFragmentManager(),fragmentList,TITLE));
        myTabPageIndicator.setViewPager(myViewPager);
    }
    public void InitVariable(){
        meiziFragment=new MeiziFragment(this);
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(meiziFragment);
        TITLE=new String[]{"MEIZI"};
    }
   public void InitListener(){
       myTabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               Toast.makeText(getApplicationContext(), TITLE[position], Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
   }

}

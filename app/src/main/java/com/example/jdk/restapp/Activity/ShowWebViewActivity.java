package com.example.jdk.restapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jdk.restapp.Adapter.AdapterRecyclerViewAndroid;
import com.example.jdk.restapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JDK on 2016/8/8.
 */
public class ShowWebViewActivity extends BaseActivity {
    @Bind(R.id.show_webview_wv)
    WebView showWebView;
    private Intent intent;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.show_webview);
        intent=getIntent();
        url=intent.getStringExtra("WebViewURL");
        ButterKnife.bind(this);
        setMyShowWebView(url);
    }
    public void setMyShowWebView(String url){
        WebSettings webSettings=showWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        showWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        showWebView.loadUrl(url);
    }


}

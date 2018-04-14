package com.example.json.mytouzhisystem;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

//储蓄Activity
public class ChuXuActivity extends BaseActivity {

    @BindView(R.id.wvChuXuActivity)
    WebView wvChuXuActivity;


    //储蓄URL
    private String ChuXuURL = "http://bank.xinhua08.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_xu);
        ButterKnife.bind(this);
        initWebView();
    }

    public void initWebView(){
        wvChuXuActivity.loadUrl(ChuXuURL);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvChuXuActivity.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = wvChuXuActivity.getSettings();
        settings.setJavaScriptEnabled(true);
        //使用缓存
        wvChuXuActivity.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}

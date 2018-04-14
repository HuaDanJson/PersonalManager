package com.example.json.mytouzhisystem;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

//债券Activity
public class ZhaiQuanActivity extends BaseActivity {

    @BindView(R.id.wvZhaiQuanActivity)
    WebView wvZhaiQuanActivity;
    private String ZhaiQuanURL = "http://www.chinabond.com.cn/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhai_quan);
        ButterKnife.bind(this);
    }
    public void initWebView(){
        wvZhaiQuanActivity.loadUrl(ZhaiQuanURL);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvZhaiQuanActivity.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = wvZhaiQuanActivity.getSettings();
        settings.setJavaScriptEnabled(true);
        //使用缓存
        wvZhaiQuanActivity.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}

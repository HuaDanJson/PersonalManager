package com.example.json.mytouzhisystem;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

//保险Activity
public class BaoXianActivity extends BaseActivity {

    @BindView(R.id.wvBaoXianActivity)
    WebView wvBaoXianActivity;

    private String BaoXianURL = "http://www.iachina.cn/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_xian);
        ButterKnife.bind(this);
        initWebView();
    }

    public void initWebView() {
        wvBaoXianActivity.loadUrl(BaoXianURL);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvBaoXianActivity.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = wvBaoXianActivity.getSettings();
        settings.setJavaScriptEnabled(true);
        //使用缓存
        wvBaoXianActivity.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}

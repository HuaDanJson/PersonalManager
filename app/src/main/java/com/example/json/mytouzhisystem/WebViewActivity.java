package com.example.json.mytouzhisystem;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.json.mytouzhisystem.constants.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WebViewActivity extends BaseActivity {

    @BindView(R.id.wv_web_view_activity) WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initWebView();
        initData();
    }

    public void initWebView() {
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //使用缓存
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    private void initData() {
        mWebView.loadUrl(getIntent().getExtras().getString(AppConstant.IntentKey.INTENT_TO_WEBVIEW_ACTIVITY_WITH_URL));
    }
}

package com.example.json.mytouzhisystem;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

//投资知识库
public class KnowledgeActivity extends BaseActivity {
    @BindView(R.id.wvKnowledgeActivity)
    WebView wvKnowledgeActivity;
    //投资知识库URL
    private String knowledgeURL = "http://forex.hexun.com/stepbystep/step1.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        ButterKnife.bind(this);
        initWebView();

    }

    public void initWebView(){
        wvKnowledgeActivity.loadUrl( knowledgeURL);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvKnowledgeActivity.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings =  wvKnowledgeActivity.getSettings();
        settings.setJavaScriptEnabled(true);
        //使用缓存
        wvKnowledgeActivity.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}

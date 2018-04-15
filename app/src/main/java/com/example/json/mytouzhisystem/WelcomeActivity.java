package com.example.json.mytouzhisystem;

import android.os.Bundle;

import cn.bmob.v3.Bmob;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "a6bebd454154723e597c8a5b2848f57c");
        doInUI(new Runnable() {
            @Override
            public void run() {
//                BmobUser bmobUser = BmobUser.getCurrentUser();
//                if (bmobUser != null) {
                    // 允许用户使用应用
                    toActivity(PersonalMainActivity.class);
                    WelcomeActivity.this.finish();
//                } else {
//                    toActivity(LoginActivity.class);
//                    WelcomeActivity.this.finish();
//                }
            }
        }, 50);
    }
}


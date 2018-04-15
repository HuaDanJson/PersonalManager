package com.example.json.mytouzhisystem;

import android.app.Application;

import com.example.json.mytouzhisystem.Utils.DBUserInvestmentUtils;
import com.example.json.mytouzhisystem.Utils.SharePreferenceUtil;

/**
 * Created by Json on 2017/3/29.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBUserInvestmentUtils.Init(getApplicationContext());
        SharePreferenceUtil.initInstance(getApplicationContext(), SharePreferenceUtil.MODE_ENCRYPT_ALL);

    }
}

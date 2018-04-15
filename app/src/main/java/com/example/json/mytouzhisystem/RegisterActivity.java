package com.example.json.mytouzhisystem;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.json.mytouzhisystem.Bean.CurrentUser;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


//用户注册页面
public class RegisterActivity extends BaseActivity {

    private EditText etusername;
    private EditText etpassword;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initialize();
    }

    private void initialize() {
        etusername = (EditText) findViewById(R.id.et_username2);
        etpassword = (EditText) findViewById(R.id.et_password2);
        register = (Button) findViewById(R.id.sign2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerData();
            }
        });
    }

    /**
     * 注册
     */
    private void registerData() {

        CurrentUser currentUser = new CurrentUser();
        final String name = etusername.getText().toString();
        final String password = etpassword.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            currentUser.setUsername(name);
            currentUser.setPassword(password);
            currentUser.signUp(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        RegisterActivity.this.finish();
                    } else {
                        YiLog.D("注册失败 exception = " + e);
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
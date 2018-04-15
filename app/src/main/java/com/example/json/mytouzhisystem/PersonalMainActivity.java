package com.example.json.mytouzhisystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.json.mytouzhisystem.constants.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalMainActivity extends BaseActivity {

    @BindView(R.id.btn_note_personal_main_activity) Button btnNotePersonalMainActivity;
    @BindView(R.id.btn_finance_personal_main_activity) Button btnFinancePersonalMainActivity;
    @BindView(R.id.btn_school_personal_main_activity) Button btnSchoolPersonalMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_note_personal_main_activity)
    public void noteClicked() {
        toActivity(NoteActivity.class);
    }

    @OnClick(R.id.btn_finance_personal_main_activity)
    public void finaceClicked() {
        toActivity(MainActivity.class);
    }

    @OnClick(R.id.btn_school_personal_main_activity)
    public void schoolClicked() {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(AppConstant.IntentKey.INTENT_TO_WEBVIEW_ACTIVITY_WITH_URL, "https://play.google.com/store/apps/details?id=cool.monkey.android");
        startActivity(intent);
    }
}

package com.example.json.mytouzhisystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.json.mytouzhisystem.Bean.DBUserInvestment;
import com.example.json.mytouzhisystem.Bean.DataSaveEvent;
import com.example.json.mytouzhisystem.Utils.DBUserInvestmentUtils;
import com.example.json.mytouzhisystem.Utils.RxBus;
import com.example.json.mytouzhisystem.constants.ConstKey;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

public class DBWaiHuiActivity extends BaseActivity {

    @BindView(R.id.btnDBWaiHuiActivityAdd)
    Button btnDBWaiHuiActivityAdd;
    @BindView(R.id.btnDBWaiHuiActivityZhiXun)
    Button btnDBWaiHuiActivityZhiXun;
    @BindView(R.id.lvDBWaiHuiActivity)
    ListView lvDBWaiHuiActivity;
    private Subscription rxSubscription;
    private List<DBUserInvestment> resultDaoList = new ArrayList<>();
    private MyKindAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbwai_hui);
        ButterKnife.bind(this);
        refreshMediaUpdateEvent();
        getListViewData();
        setListViewClick();
    }

    public void getListViewData(){

        resultDaoList = DBUserInvestmentUtils.getInstance().queryDataDependMedia("外汇");
        if (resultDaoList!=null&&resultDaoList.size()>0){
            myAdapter = new MyKindAdapter(resultDaoList,DBWaiHuiActivity.this);
            lvDBWaiHuiActivity.setAdapter(myAdapter);
        }
    }


    //设置点击事件
    @OnClick({R.id.btnDBWaiHuiActivityAdd, R.id.btnDBWaiHuiActivityZhiXun})
    public void onClick(View view) {
        switch (view.getId()) {
            //处理点击添加点击事件
            case R.id.btnDBWaiHuiActivityAdd:
                Intent intent = new Intent(DBWaiHuiActivity.this, AddActivity.class);
                intent.putExtra("name", "外汇");
                startActivity(intent);
                break;
            //处理行业资讯点击事件
            case R.id.btnDBWaiHuiActivityZhiXun:
                toActivity(WaiHuiActivity.class);
                break;

            default:
                break;
        }
    }

    public void refreshMediaUpdateEvent() {
        rxSubscription = RxBus.getDefault()
                .toObservable(DataSaveEvent.class)
                .subscribe(new Action1<DataSaveEvent>() {
                    @Override
                    public void call(DataSaveEvent dataSaveEvent) {
                        if (ConstKey.SAVE_DATA_SUCCESS.equals(dataSaveEvent.getDataSaveEvent())) {
                            getListViewData();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterRxBus();
    }

    private void unregisterRxBus() {
        if (rxSubscription != null && !rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }
    public void setListViewClick(){
        lvDBWaiHuiActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DBWaiHuiActivity.this,NotXiangQingActivity.class);
                intent.putExtra("NotID",  resultDaoList.get(position).getCreatTimeAsId());
                intent.putExtra("name", "外汇");
                startActivity(intent);
            }
        });
    }
}

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

public class DBGuPiaoActivity extends BaseActivity {

    @BindView(R.id.btnDBGuPiaoActivityAdd)
    Button btnDBGuPiaoActivityAdd;
    @BindView(R.id.btnDBGuPiaoActivityZhiXun)
    Button btnDBGuPiaoActivityZhiXun;
    @BindView(R.id.lvDBGuPiaoActivity)
    ListView lvDBGuPiaoActivity;
    private Subscription rxSubscription;
    private List<DBUserInvestment> resultDaoList = new ArrayList<>();
    private MyKindAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbgu_piao);
        ButterKnife.bind(this);
        refreshMediaUpdateEvent();
        getListViewData();
        setListViewClick();
    }

    public void getListViewData(){

        resultDaoList = DBUserInvestmentUtils.getInstance().queryDataDependMedia("股票");
        if (resultDaoList!=null&&resultDaoList.size()>0){
            myAdapter = new MyKindAdapter(resultDaoList,DBGuPiaoActivity.this);
            lvDBGuPiaoActivity.setAdapter(myAdapter);
        }
    }


    //设置点击事件
    @OnClick({R.id.btnDBGuPiaoActivityAdd, R.id.btnDBGuPiaoActivityZhiXun})
    public void onClick(View view) {
        switch (view.getId()) {
            //处理点击添加点击事件
            case R.id.btnDBGuPiaoActivityAdd:
                Intent intent = new Intent(DBGuPiaoActivity.this, AddActivity.class);
                intent.putExtra("name", "股票");
                startActivity(intent);
                break;
            //处理行业资讯点击事件
            case R.id.btnDBGuPiaoActivityZhiXun:
                toActivity(GuPiaoActivity.class);
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
        lvDBGuPiaoActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DBGuPiaoActivity.this,NotXiangQingActivity.class);
                intent.putExtra("NotID",  resultDaoList.get(position).getCreatTimeAsId());
                intent.putExtra("name", "股票");
                startActivity(intent);
            }
        });
    }
}

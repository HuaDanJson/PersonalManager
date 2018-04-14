package com.example.json.mytouzhisystem;

import android.os.Bundle;
import android.widget.TextView;

import com.example.json.mytouzhisystem.Bean.DBUserInvestment;
import com.example.json.mytouzhisystem.Bean.DataSaveEvent;
import com.example.json.mytouzhisystem.Utils.DBUserInvestmentUtils;
import com.example.json.mytouzhisystem.Utils.RxBus;
import com.example.json.mytouzhisystem.constants.ConstKey;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

//数据统计Activity
public class StatisticsActivity extends BaseActivity {

    @BindView(R.id.tvStatisticsActivityChuXu)
    TextView tvStatisticsActivityChuXu;
    @BindView(R.id.tvStatisticsActivityGuPiao)
    TextView tvStatisticsActivityGuPiao;
    @BindView(R.id.tvStatisticsActivityJiJin)
    TextView tvStatisticsActivityJiJin;
    @BindView(R.id.tvStatisticsActivityZaiQuan)
    TextView tvStatisticsActivityZaiQuan;
    @BindView(R.id.tvStatisticsActivityWaiHui)
    TextView tvStatisticsActivityWaiHui;
    @BindView(R.id.tvStatisticsActivityQiHuo)
    TextView tvStatisticsActivityQiHuo;
    @BindView(R.id.tvStatisticsActivityShouChangPin)
    TextView tvStatisticsActivityShouChangPin;
    @BindView(R.id.tvStatisticsActivityFangDiChan)
    TextView tvStatisticsActivityFangDiChan;
    @BindView(R.id.tvStatisticsActivityBaoXian)
    TextView tvStatisticsActivityBaoXian;
    private Subscription rxSubscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        refreshMediaUpdateEvent();
        setText();

    }
    public void setText(){
        setText(tvStatisticsActivityChuXu,"储蓄");
        setText(tvStatisticsActivityGuPiao,"股票");
        setText(tvStatisticsActivityJiJin,"基金");
        setText(tvStatisticsActivityZaiQuan,"债券");
        setText(tvStatisticsActivityWaiHui,"外汇");
        setText( tvStatisticsActivityQiHuo,"期货");
        setText(tvStatisticsActivityShouChangPin,"收藏品");
        setText(tvStatisticsActivityFangDiChan,"房地产");
        setText(tvStatisticsActivityBaoXian,"保险");

    }
    public void setText(TextView textView,String name){
       List<DBUserInvestment> resultDaoList = DBUserInvestmentUtils.getInstance().queryDataDependMedia(name);
        if (resultDaoList!=null&&resultDaoList.size()>0){
            int count = 0;
            for (int i=0;i<resultDaoList.size();i++){
                count = count+ Integer.parseInt(resultDaoList.get(i).getInvestmentCount());
              }
            textView.setText(name+"的总投资金额："+count+"元");
        }else {
            textView.setText(name+"的总投资金额：0"+"元");
        }
    }
    public void refreshMediaUpdateEvent() {
        rxSubscription = RxBus.getDefault()
                .toObservable(DataSaveEvent.class)
                .subscribe(new Action1<DataSaveEvent>() {
                    @Override
                    public void call(DataSaveEvent dataSaveEvent) {
                        if (ConstKey.SAVE_DATA_SUCCESS.equals(dataSaveEvent.getDataSaveEvent())) {
                            setText();
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
}

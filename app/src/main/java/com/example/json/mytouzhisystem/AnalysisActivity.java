package com.example.json.mytouzhisystem;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.json.mytouzhisystem.Bean.DBUserInvestment;
import com.example.json.mytouzhisystem.Bean.DataSaveEvent;
import com.example.json.mytouzhisystem.Utils.DBUserInvestmentUtils;
import com.example.json.mytouzhisystem.Utils.RxBus;
import com.example.json.mytouzhisystem.constants.ConstKey;
import com.idtk.smallchart.chart.PieChart;
import com.idtk.smallchart.data.PieData;
import com.idtk.smallchart.interfaces.iData.IPieData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

//数据分析Activity
public class AnalysisActivity extends BaseActivity {


    @BindView(R.id.tvAnalysisActivityChuXu)
    TextView tvAnalysisActivityChuXu;
    @BindView(R.id.tvAnalysisActivityGuPiao)
    TextView tvAnalysisActivityGuPiao;
    @BindView(R.id.tvAnalysisActivityJiJin)
    TextView tvAnalysisActivityJiJin;
    @BindView(R.id.tvAnalysisActivityZaiQuan)
    TextView tvAnalysisActivityZaiQuan;
    @BindView(R.id.tvAnalysisActivityWaiHui)
    TextView tvAnalysisActivityWaiHui;
    @BindView(R.id.tvAnalysisActivityQiHuo)
    TextView tvAnalysisActivityQiHuo;
    @BindView(R.id.tvAnalysisActivityChangPin)
    TextView tvAnalysisActivityChangPin;
    @BindView(R.id.tvAnalysisActivityDiChan)
    TextView tvAnalysisActivityDiChan;
    @BindView(R.id.tvAnalysisActivityBaoXian)
    TextView tvAnalysisActivityBaoXian;
    @BindView(R.id.pieChart)
    PieChart pieChart;

    private Subscription rxSubscription;
    private ArrayList<IPieData> mPieDataList = new ArrayList<>();
    private List<DBUserInvestment> resultDaoList = new ArrayList<>();
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;
    private int count6 = 0;
    private int count7 = 0;
    private int count8 = 0;
    private int count9 = 0;
    private int countAll = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        ButterKnife.bind(this);
        getData();
        setText();
        setRound();

    }

    public void setText() {
        tvAnalysisActivityChuXu.setText("储蓄比例：" + (((count1 * 1.0) / countAll) * 100) + "%");
        tvAnalysisActivityGuPiao.setText("股票比例：" + (((count2 * 1.0) / countAll) * 100) + "%");
        tvAnalysisActivityJiJin.setText("基金比例：" + (((count3 * 1.0) / countAll) * 100) + "%");
        tvAnalysisActivityZaiQuan.setText("债券比例：" + (((count4 * 1.0) / countAll) * 100) + "%");
        tvAnalysisActivityWaiHui.setText("外汇比例：" + (((count5 * 1.0) / countAll) * 100) + "%");
        tvAnalysisActivityQiHuo.setText("期货比例：" + (((count6 * 1.0) / countAll) * 100) + "%");
        tvAnalysisActivityChangPin.setText("收藏品比例：" + (((count7 * 1.0) / countAll) * 100) + "%");
        tvAnalysisActivityDiChan.setText("房地产比例：" + (((count8 * 1.0) / countAll) * 100) + "%");
        tvAnalysisActivityBaoXian.setText("保险比例：" + (((count9 * 1.0) / countAll) * 100) + "%");
    }

    public void setRound(){
        initData();
        pieChart.setDataList(mPieDataList);
        pieChart.setAxisColor(Color.WHITE);
        pieChart.setAxisTextSize(pxTodp(20));
    }

    public void getData() {
        count1 = getCount("储蓄");
        count2 = getCount("股票");
        count3 = getCount("基金");
        count4 = getCount("债券");
        count5 = getCount("外汇");
        count6 = getCount("期货");
        count7 = getCount("收藏品");
        count8 = getCount("房地产");
        count9 = getCount("保险");
        countAll = count1 + count2 + count3 + count4 + count5 + count6 + count7 + count8 + count9;
    }

    private void initData() {
        PieData pieData = new PieData();
        pieData.setName("储蓄比例");
        pieData.setValue((float) (((count1 * 1.0) / countAll) * 100));
        pieData.setColor(mColors[0]);
        mPieDataList.add(pieData);

        PieData pieData2 = new PieData();
        pieData2.setName("股票比例");
        pieData2.setValue((float) (((count2 * 1.0) / countAll) * 100));
        pieData2.setColor(mColors[1]);
        mPieDataList.add(pieData2);

        PieData pieData3 = new PieData();
        pieData3.setName("基金比例");
        pieData3.setValue((float) (((count3 * 1.0) / countAll) * 100));
        pieData3.setColor(mColors[2]);
        mPieDataList.add(pieData3);

        PieData pieData4 = new PieData();
        pieData4.setName("债券比例");
        pieData4.setValue((float) (((count4 * 1.0) / countAll) * 100));
        pieData4.setColor(mColors[3]);
        mPieDataList.add(pieData4);

        PieData pieData5 = new PieData();
        pieData5.setName("外汇比例");
        pieData5.setValue((float) (((count5 * 1.0) / countAll) * 100));
        pieData5.setColor(mColors[4]);
        mPieDataList.add(pieData5);

        PieData pieData6 = new PieData();
        pieData6.setName("期货比例");
        pieData6.setValue((float) (((count6 * 1.0) / countAll) * 100));
        pieData6.setColor(mColors[5]);
        mPieDataList.add(pieData6);

        PieData pieData7 = new PieData();
        pieData7.setName("收藏品比例");
        pieData7.setValue((float) (((count7 * 1.0) / countAll) * 100));
        pieData7.setColor(mColors[6]);
        mPieDataList.add(pieData7);

        PieData pieData8 = new PieData();
        pieData8.setName("房地产比例");
        pieData8.setValue((float) (((count8 * 1.0) / countAll) * 100));
        pieData8.setColor(mColors[7]);
        mPieDataList.add(pieData8);

        PieData pieData9 = new PieData();
        pieData9.setName("保险比例");
        pieData9.setValue((float) (((count9 * 1.0) / countAll)) * 100);
        pieData9.setColor(mColors[5]);
        mPieDataList.add(pieData9);
    }

    public int getCount(String name) {
        if (resultDaoList != null && resultDaoList.size() > 0) {
            resultDaoList.clear();
        }
        resultDaoList = DBUserInvestmentUtils.getInstance().queryDataDependMedia(name);
        if (resultDaoList != null && resultDaoList.size() > 0) {
            int count = 0;
            for (int i = 0; i < resultDaoList.size(); i++) {
                count = count + Integer.parseInt(resultDaoList.get(i).getInvestmentCount());
            }
            return count;
        } else {
            return 0;
        }
    }

    public void refreshMediaUpdateEvent() {
        rxSubscription = RxBus.getDefault()
                .toObservable(DataSaveEvent.class)
                .subscribe(new Action1<DataSaveEvent>() {
                    @Override
                    public void call(DataSaveEvent dataSaveEvent) {
                        if (ConstKey.SAVE_DATA_SUCCESS.equals(dataSaveEvent.getDataSaveEvent())) {
                            getData();
                            setText();
                            setRound();
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

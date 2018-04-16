package com.example.json.mytouzhisystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.json.mytouzhisystem.Bean.DBUserInvestment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Json on 2017/3/29.
 */

public class MyKindAdapter extends BaseAdapter {

    private List<DBUserInvestment> dbUserInvestmentList;
    private LayoutInflater inflater;
    private MyVidewHolder myViewHolder;

    public MyKindAdapter(List<DBUserInvestment> dbUserInvestmentList, Context context) {
        this.dbUserInvestmentList = dbUserInvestmentList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return dbUserInvestmentList.size();
    }

    @Override
    public Object getItem(int position) {
        return dbUserInvestmentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_db_chu_xu_activity, null);
            myViewHolder = new MyVidewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyVidewHolder) convertView.getTag();
        }
        SimpleDateFormat sdr1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String CreatedTime1 = sdr1.format(new Date(dbUserInvestmentList.get(position).getCreatTimeAsId()));


        if ("记事本".equals(dbUserInvestmentList.get(position).getName())) {
            myViewHolder.tvItemTitle.setVisibility(View.GONE);
            myViewHolder.tvItemTime.setText("时间:" + CreatedTime1);
            myViewHolder.tvItemCount.setText("标题：" + dbUserInvestmentList.get(position).getInvestmentCount() + "元");
            myViewHolder.tvItemSign.setText("内容：" + dbUserInvestmentList.get(position).getSign());
        } else {
            myViewHolder.tvItemTitle.setVisibility(View.VISIBLE);
            myViewHolder.tvItemTime.setText("投资时间:" + CreatedTime1);
            myViewHolder.tvItemTitle.setText("投资项目" + (position + 1) + "：" + dbUserInvestmentList.get(position).getName());
            myViewHolder.tvItemCount.setText("投资数目：" + dbUserInvestmentList.get(position).getInvestmentCount() + "元");
            myViewHolder.tvItemSign.setText("投资描述内容：" + dbUserInvestmentList.get(position).getSign());
        }
        return convertView;
    }

    class MyVidewHolder {
        @BindView(R.id.tvItemTitle)
        TextView tvItemTitle;
        @BindView(R.id.tvItemCount)
        TextView tvItemCount;
        @BindView(R.id.tvItemTime)
        TextView tvItemTime;
        @BindView(R.id.tvItemSign)
        TextView tvItemSign;

        MyVidewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}



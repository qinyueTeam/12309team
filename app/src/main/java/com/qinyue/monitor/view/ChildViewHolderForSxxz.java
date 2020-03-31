package com.qinyue.monitor.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qinyue.monitor.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public class ChildViewHolderForSxxz extends RecyclerView.ViewHolder {
    private LinearLayout containerLayout;
    private ChooseSxxzActivity mContext;
    private View view;
    private TextView childLeftText;

    public ChildViewHolderForSxxz(ChooseSxxzActivity context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final ParentEntity dataBean, final int pos) {
        containerLayout =view.findViewById(R.id.rl_child_container);
        childLeftText =  view.findViewById(R.id.tv_child);
        switch (dataBean.getLv()){
            case 0:{
                childLeftText.setText(dataBean.getName());
            }break;
            case 1:{
                childLeftText.setText(" "+dataBean.getName());
            }break;
            case 2:{
                childLeftText.setText("     "+dataBean.getName());
            }break;
            case 3:{
                childLeftText.setText("         "+dataBean.getName());
            }break;
        }
//        if (dataBean.getFirstName()!=null){
//            childLeftText.setText("      "+dataBean.getName());
//        }else {
//            childLeftText.setText("   "+dataBean.getName());
//        }
        containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.setClick(dataBean);
            }
        });

    }
}

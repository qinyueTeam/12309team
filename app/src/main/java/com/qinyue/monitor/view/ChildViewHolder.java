package com.qinyue.monitor.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qinyue.monitor.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public class ChildViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout containerLayout;
    private ChooseAfdActivity mContext;
    private View view;
    private TextView childLeftText;

    public ChildViewHolder(ChooseAfdActivity context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final SelectSectionParentEntity dataBean, final int pos) {
        containerLayout =view.findViewById(R.id.rl_child_container);
        childLeftText =  view.findViewById(R.id.tv_child);

        if (dataBean.getFirstName()!=null){
            childLeftText.setText("      "+dataBean.getName());
        }else {
            childLeftText.setText("   "+dataBean.getName());
        }
        containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.setClick(dataBean);
            }
        });

    }
}

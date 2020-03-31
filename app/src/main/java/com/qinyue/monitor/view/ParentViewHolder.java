package com.qinyue.monitor.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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
public class ParentViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private View view;
    private LinearLayout containerLayout;
    private TextView name;
    private ImageView expand;
    private ImageView expand2;

    public ParentViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final SelectSectionParentEntity dataBean, final int pos, final ExpandListClickListener listener) {

        containerLayout = view.findViewById(R.id.container);
        name = view.findViewById(R.id.tv);
        expand = view.findViewById(R.id.img);
        expand2 = view.findViewById(R.id.img2);
        if (dataBean.getFirstName()!=null){
            name.setText("   "+dataBean.getName());
        }else {
            name.setText(dataBean.getName());
        }
        if (!dataBean.isExpand()){
            expand.setVisibility(View.VISIBLE);
            expand2.setVisibility(View.GONE);
        }else {
            expand.setVisibility(View.GONE);
            expand2.setVisibility(View.VISIBLE);
        }
        //父布局OnClick监听
        containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null && dataBean.isHasChild()) {
                    if (dataBean.isExpand()) {
                        listener.onHideChildren(dataBean);
                        dataBean.setExpand(false);
                    } else {
                        listener.onExpandChildren(dataBean);
                        dataBean.setExpand(true);
                    }
                }
            }
        });
    }
}

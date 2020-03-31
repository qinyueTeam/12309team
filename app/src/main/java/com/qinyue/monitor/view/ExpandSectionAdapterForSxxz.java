package com.qinyue.monitor.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qinyue.monitor.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public class ExpandSectionAdapterForSxxz extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ChooseSxxzActivity context;
    private List<ParentEntity> dataBeanList = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnScrollListener mOnScrollListener;

    public ExpandSectionAdapterForSxxz(ChooseSxxzActivity context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setParentData(List<ParentEntity> dataBeanList) {
        this.dataBeanList.addAll(dataBeanList);
        notifyDataSetChanged();
    }

    public void setChildData(List<ParentEntity> dataBeanList, int pos) {
        this.dataBeanList.addAll(pos, dataBeanList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ParentEntity.PARENT_ITEM:
                view = mInflater.inflate(R.layout.item_parent, parent, false);
                return new ParentViewHolderForSxxz(context, view);
            case ParentEntity.CHILD_ITEM:
                view = mInflater.inflate(R.layout.item_child, parent, false);
                return new ChildViewHolderForSxxz(context, view);
            default:
                view = mInflater.inflate(R.layout.item_parent, parent, false);
                return new ParentViewHolderForSxxz(context, view);
        }
    }

    /**
     * 根据不同的类型绑定View
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ParentEntity.PARENT_ITEM:
                ParentViewHolderForSxxz parentViewHolder = (ParentViewHolderForSxxz) holder;
                parentViewHolder.bindView(dataBeanList.get(position), position, itemClickListener);
                break;
            case ParentEntity.CHILD_ITEM:
                ChildViewHolderForSxxz childViewHolder = (ChildViewHolderForSxxz) holder;
                childViewHolder.bindView(dataBeanList.get(position), position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataBeanList.get(position).getType();
    }

    private ExpandListClickListenerForSxxz itemClickListener = new ExpandListClickListenerForSxxz() {
        @Override
        public void onExpandChildren(ParentEntity bean) {
            int position = getCurrentPosition(bean.getCode());//确定当前点击的item位置
            context.getAfdDataForcChild(bean.getCode(), position + 1, bean.getFirstCode(), bean.getSecCode(), bean.getThCode(), bean.getLv());
        }

        @Override
        public void onHideChildren(ParentEntity bean) {
            int position = getCurrentPosition(bean.getCode());//确定当前点击的item位置
            List<ParentEntity> dataBeanList2 = new ArrayList<>();
            for (int i = position; i < dataBeanList.size(); i++) {
                if (dataBeanList.get(i).getFirstCode() != null && bean.getCode().equalsIgnoreCase(dataBeanList.get(i).getFirstCode())) {
                    dataBeanList2.add(dataBeanList.get(i));
                }
                if (dataBeanList.get(i).getSecCode() != null && bean.getCode().equalsIgnoreCase(dataBeanList.get(i).getSecCode())) {
                    dataBeanList2.add(dataBeanList.get(i));
                }
                if (dataBeanList.get(i).getThCode() != null && bean.getCode().equalsIgnoreCase(dataBeanList.get(i).getThCode())) {
                    dataBeanList2.add(dataBeanList.get(i));
                }
            }
            dataBeanList.removeAll(dataBeanList2);
            notifyDataSetChanged();
            if (mOnScrollListener != null) {
                mOnScrollListener.scrollTo(position);
            }
        }
    };

    /**
     * 在父布局下方插入一条数据
     *
     * @param bean
     * @param position
     */
    public void add(ParentEntity bean, int position) {
        dataBeanList.add(position, bean);
        notifyItemInserted(position);
    }

    /**
     * 移除子布局数据
     *
     * @param position
     */
    protected void remove(int position) {
        dataBeanList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 确定当前点击的item位置并返回
     *
     * @param uuid
     * @return
     */
    protected int getCurrentPosition(String uuid) {
        for (int i = 0; i < dataBeanList.size(); i++) {
            if (uuid.equalsIgnoreCase(dataBeanList.get(i).getCode())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 封装子布局数据对象并返回
     * 注意，此处只是重新封装一个DataBean对象，为了标注Type为子布局数据，进而展开，展示数据
     * 要和onHideChildren方法里的getChildBean()区分开来
     *
     * @param bean
     * @return
     */
    private ParentEntity getChildDataBean(ParentEntity bean) {
        ParentEntity child = new ParentEntity();
        child.setType(1);
        return child;
    }

    /**
     * 滚动监听接口
     */
    public interface OnScrollListener {
        void scrollTo(int pos);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

}

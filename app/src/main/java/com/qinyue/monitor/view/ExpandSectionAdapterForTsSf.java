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
public class ExpandSectionAdapterForTsSf extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ChooseTsSfActivity context;
    private List<SelectSectionParentEntity> dataBeanList = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnScrollListener mOnScrollListener;

    public ExpandSectionAdapterForTsSf(ChooseTsSfActivity context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }
    public void setParentData(List<SelectSectionParentEntity> dataBeanList){
        this.dataBeanList.addAll(dataBeanList);
        notifyDataSetChanged();
    }
    public void setChildData(List<SelectSectionParentEntity> dataBeanList,int pos){
        this.dataBeanList.addAll(pos,dataBeanList);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case SelectSectionParentEntity.PARENT_ITEM:
                view = mInflater.inflate(R.layout.item_parent, parent, false);
                return new ParentViewHolder(context, view);
            case SelectSectionParentEntity.CHILD_ITEM:
                view = mInflater.inflate(R.layout.item_child, parent, false);
                return new ChildViewHolderForTsSf(context, view);
            default:
                view = mInflater.inflate(R.layout.item_parent, parent, false);
                return new ParentViewHolder(context, view);
        }
    }

    /**
     * 根据不同的类型绑定View
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case SelectSectionParentEntity.PARENT_ITEM:
                ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
                parentViewHolder.bindView(dataBeanList.get(position), position, itemClickListener);
                break;
            case SelectSectionParentEntity.CHILD_ITEM:
                ChildViewHolderForTsSf childViewHolder = (ChildViewHolderForTsSf) holder;
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

    private ExpandListClickListener itemClickListener = new ExpandListClickListener() {
        @Override
        public void onExpandChildren(SelectSectionParentEntity bean) {
            int position = getCurrentPosition(bean.getCode());//确定当前点击的item位置
            if (bean.getParentCode()!=null&&!bean.getParentCode().isEmpty()){
                context.getAfdDataForcChild(bean.getCode(),position+1,bean.getParentCode(),bean.getFirstName(),bean.getName());
            }else {
                context.getAfdDataForcChild(bean.getCode(),position+1,null,bean.getName(),null);
            }

        }

        @Override
        public void onHideChildren(SelectSectionParentEntity bean) {
            int position = getCurrentPosition(bean.getCode());//确定当前点击的item位置
            List<SelectSectionParentEntity> dataBeanList2 = new ArrayList<>();
            for (int i = position; i < dataBeanList.size(); i++) {
                if (dataBeanList.get(i).getParentCode()!=null&&bean.getCode().equalsIgnoreCase(dataBeanList.get(i).getParentCode())) {
                    dataBeanList2.add(dataBeanList.get(i));
                }
                if (dataBeanList.get(i).getParentCodeForFirst()!=null&&bean.getCode().equalsIgnoreCase(dataBeanList.get(i).getParentCodeForFirst())) {
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
     * @param bean
     * @param position
     */
    public void add(SelectSectionParentEntity bean, int position) {
        dataBeanList.add(position, bean);
        notifyItemInserted(position);
    }

    /**
     *移除子布局数据
     * @param position
     */
    protected void remove(int position) {
        dataBeanList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 确定当前点击的item位置并返回
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
     * @param bean
     * @return
     */
    private SelectSectionParentEntity getChildDataBean(SelectSectionParentEntity bean){
        SelectSectionParentEntity child = new SelectSectionParentEntity();
        child.setType(1);
        return child;
    }

    /**
     * 滚动监听接口
     */
    public interface OnScrollListener{
        void scrollTo(int pos);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.mOnScrollListener = onScrollListener;
    }

}

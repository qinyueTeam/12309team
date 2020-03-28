package com.qinyue.monitor.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.TimeUtils;
import com.google.gson.Gson;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseListBean;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.my.MyXFActivity;
import com.qinyue.monitor.my.XfListBean;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.qinyue.monitor.view.MyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/28
 * 描述:
 **/
public class FirstPagerAdapter extends PagerAdapter {
    MainActivity context;
    String[] strings;
    private int selectItem = 0;
    private String[] types = {"1", "2", "3","4"};
    private int[] page = {0, 0, 0,0};
    List<JcxwdtListBean> jcxwdtListBeans = new ArrayList<>();
    Map<Integer,Object> adapterMap = new HashMap<>();
    public FirstPagerAdapter(MainActivity context, String[] strings){
        this.context = context;
        this.strings = strings;
    }

    public void setJcxwdtListBeans(List<JcxwdtListBean> jcxwdtListBeans,boolean isRefresh){
        if (isRefresh){
            this.jcxwdtListBeans.clear();
        }
        this.jcxwdtListBeans.addAll(jcxwdtListBeans);
        CommonAdapter<JcxwdtListBean> commonAdapter = (CommonAdapter<JcxwdtListBean>)adapterMap.get(0);
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return strings==null?0:strings.length;
    }
    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
//        List<XfListBean> messageBeans = allDataMap.get(selectItem);
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.recyclerview, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_transparent_divider));
        recyclerView.addItemDecoration(divider);
        switch (position){
            case 0:{
                CommonAdapter<JcxwdtListBean> commonAdapter = new CommonAdapter<JcxwdtListBean>(context, R.layout.item_jcxwdt, jcxwdtListBeans) {
                    @Override
                    protected void convert(ViewHolder holder, JcxwdtListBean messageBean, int pos) {
                        MyView myView = holder.getView(R.id.view);
                        if (messageBean.getReleaseTime().indexOf("-")>0){
                            String[] split = messageBean.getReleaseTime().split("-");
                            if (split.length==3){
                                myView.setTime(split[1],split[2]);
                            }
                        }
                        holder.setText(R.id.title,messageBean.getTitle());
                        holder.setText(R.id.content,messageBean.getContent());
                    }
                };
                recyclerView.setAdapter(commonAdapter);
                adapterMap.put(position,commonAdapter);
            }break;
            case 1:{
                CommonAdapter<JcxwdtListBean> commonAdapter = new CommonAdapter<JcxwdtListBean>(context, R.layout.item_myxf, jcxwdtListBeans) {
                    @Override
                    protected void convert(ViewHolder holder, JcxwdtListBean messageBean, int pos) {
                    }
                };
                recyclerView.setAdapter(commonAdapter);
                adapterMap.put(position,commonAdapter);
            }break;
            case 2:{
                CommonAdapter<JcxwdtListBean> commonAdapter = new CommonAdapter<JcxwdtListBean>(context, R.layout.item_myxf, jcxwdtListBeans) {
                    @Override
                    protected void convert(ViewHolder holder, JcxwdtListBean messageBean, int pos) {
                    }
                };
                recyclerView.setAdapter(commonAdapter);
                adapterMap.put(position,commonAdapter);
            }break;
            case 3:{
                CommonAdapter<JcxwdtListBean> commonAdapter = new CommonAdapter<JcxwdtListBean>(context, R.layout.item_myxf, jcxwdtListBeans) {
                    @Override
                    protected void convert(ViewHolder holder, JcxwdtListBean messageBean, int pos) {
                    }
                };
                recyclerView.setAdapter(commonAdapter);
                adapterMap.put(position,commonAdapter);
            }break;
        }
        view.setTag(position);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getItemPosition(Object object) {
        View view = (View) object;
        int position = (int) view.getTag();
        if (position >= 0) {
            return position;
        }
        return POSITION_NONE;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }

}

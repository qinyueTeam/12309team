package com.qinyue.monitor.my;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseListBean;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
public class MyXFPagerAdapter extends PagerAdapter {
    MyXFActivity context;
    String[] strings;
    private int selectItem = 0;
    private String[] types = {"1", "2", "3"};
    private int[] page = {0, 0, 0};
    Map<Integer, SmartRefreshLayout> smartRefreshLayoutMap = new HashMap<>();
    Map<Integer, List<XfListBean>> allDataMap = new HashMap<>();
    private boolean isFirst = true;

    public MyXFPagerAdapter(MyXFActivity context, String[] strings) {
        this.context = context;
        this.strings = strings;
        for (int i = 0; i < types.length; i++) {
            allDataMap.put(i, new ArrayList<>());
        }
    }

    @Override
    public int getCount() {
        return strings == null ? 0 : strings.length;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        SmartRefreshLayout smartRefreshLayout = smartRefreshLayoutMap.get(selectItem);
        List<XfListBean> messageBeans = allDataMap.get(selectItem);
        if (smartRefreshLayout != null && messageBeans != null && messageBeans.size() == 0) {
            smartRefreshLayout.autoRefresh();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_recyclerview, null);
        SmartRefreshLayout smartRefreshLayout = view.findViewById(R.id.refreshLayout);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_transparent_divider));
        recyclerView.addItemDecoration(divider);
        CommonAdapter<XfListBean> commonAdapter = new CommonAdapter<XfListBean>(context, R.layout.item_myxf, allDataMap.get(position) == null ? new ArrayList<>() : allDataMap.get(position)) {
            @Override
            protected void convert(ViewHolder holder, XfListBean messageBean, int pos) {
                pos++;
                holder.setText(R.id.item_num, pos + "");
                if (position == 2) {
                    holder.setText(R.id.item_type, messageBean.getCaseType());
                } else {
                    holder.setText(R.id.item_type, messageBean.getPetitionType());
                }
                holder.setText(R.id.item_where, messageBean.getOrganization());
                holder.setText(R.id.item_time, messageBean.getDateCreated());
                holder.setText(R.id.item_state, messageBean.getStatus());
            }
        };
        recyclerView.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                switch (position) {
                    case 0: {
                        if ("控告".equals(allDataMap.get(position).get(i).getPetitionType())) {
                            Intent intent = new Intent(context, KgDetailsActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            context.startActivity(intent);
                        } else if ("刑事申诉".equals(allDataMap.get(position).get(i).getPetitionType())) {
                            Intent intent = new Intent(context, XsssDetailsActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            context.startActivity(intent);
                        } else if ("国家赔偿".equals(allDataMap.get(position).get(i).getPetitionType())){//
                            Intent intent = new Intent(context, GjpcDetailsActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            context.startActivity(intent);
                        }else if ("民事申诉".equals(allDataMap.get(position).get(i).getPetitionType())){//
                            Intent intent = new Intent(context, MsssDetailsActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            intent.putExtra("title",  "民事申诉详情");
                            context.startActivity(intent);
                        }else if ("行政申诉".equals(allDataMap.get(position).get(i).getPetitionType())){//
                            Intent intent = new Intent(context, MsssDetailsActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            intent.putExtra("title",  "行政申诉详情");
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, QtDetailsActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            context.startActivity(intent);
                        }
                    }
                    break;
                    case 1: {//未成年人
                        if ("控告".equals(allDataMap.get(position).get(i).getPetitionType())) {
                            Intent intent = new Intent(context, WcnKgActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            context.startActivity(intent);
                        } else if ("申请救助".equals(allDataMap.get(position).get(i).getPetitionType())) {
                            Intent intent = new Intent(context, WcnSqjzActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            context.startActivity(intent);
                        } else {//刑事申诉
                            Intent intent = new Intent(context, WcnXsssActivity.class);
                            intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                            context.startActivity(intent);
                        }

                    }
                    break;
                    case 2: {//公益诉讼
                        Intent intent = new Intent(context, GyssDetailsActivity.class);
                        intent.putExtra("id", allDataMap.get(position).get(i).getId() + "");
                        intent.putExtra("title", allDataMap.get(position).get(i).getCaseType() + "");
                        context.startActivity(intent);
                    }
                    break;
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page[position]++;
                getData(position, commonAdapter);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page[position] = 0;
                getData(position, commonAdapter);
            }
        });


        smartRefreshLayoutMap.put(position, smartRefreshLayout);
        view.setTag(position);
        container.addView(view);
        if (isFirst && selectItem == 0 && position == 2) {
            isFirst = false;
            smartRefreshLayoutMap.get(0).autoRefresh();
        }
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

    private void getData(int position, CommonAdapter<XfListBean> commonAdapter) {
        Map<String, String> map = new HashMap<>();
        map.put("type", types[position]);
        map.put("rows", "10");
        map.put("page", page[position] + "");
        map.put("username", UserUtils.getUserName());
        map.put("petitionType", "");
        String s2 = new Gson().toJson(map);
        String aesEncode = Base64Converter.AESEncode(TagConstant.AESKEY, s2);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.petitionList)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aesEncode)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult() == 200) {
                        String a = Base64Converter.AESDncode(TagConstant.AESKEY, s.getData());
                        BaseListBean<XfListBean> xfListBeanBaseListBean = JsonUtils.getInstance().fromJsonArray(a, XfListBean.class);
                        if (page[position] == 0) {
                            allDataMap.get(position).clear();
                        }
                        if (xfListBeanBaseListBean.getTotal() > 0 && xfListBeanBaseListBean.getData() != null && xfListBeanBaseListBean.getData().size() > 0) {
                            List<XfListBean> xfListBeans = allDataMap.get(position);
                            xfListBeans.addAll(xfListBeanBaseListBean.getData());
                            allDataMap.put(selectItem, xfListBeans);
                            smartRefreshLayoutMap.get(position).finishLoadMore(100, true, false);
                        } else {
                            smartRefreshLayoutMap.get(position).finishLoadMore(100, true, true);
                        }
                        smartRefreshLayoutMap.get(position).finishRefresh(true);
                        commonAdapter.notifyDataSetChanged();
                    } else {
                        smartRefreshLayoutMap.get(position).finishRefresh(false);
                        smartRefreshLayoutMap.get(position).finishLoadMore(false);
                    }
                }, throwable -> {
                    smartRefreshLayoutMap.get(position).finishRefresh(false);
                    smartRefreshLayoutMap.get(position).finishLoadMore(false);
                });
    }
}

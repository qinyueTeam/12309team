package com.qinyue.monitor.my;

import android.os.Bundle;

import com.google.gson.Gson;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
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
import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/28
 * 描述:
 **/
public class SpyyActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    int page = 0;
    List<YyjfListbean> qzyjBeans = new ArrayList<>();
    CommonAdapter<YyjfListbean> commonAdapter;
    private String[] title = {"视频预约接访","窗口预约接访","律师预约接访"};
    private String[] types = {"1","2","3"};
    private int where = 0;
    @Override
    public String initTitleText() {
        return title[where];
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initview() {
        where = getIntent().getIntExtra("where",0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_transparent_divider));
        recyclerView.addItemDecoration(divider);
        commonAdapter = new CommonAdapter<YyjfListbean>(this,R.layout.item_yyjf,qzyjBeans) {
            @Override
            protected void convert(ViewHolder holder, YyjfListbean YyjfListbean, int pos) {
                pos++;
                holder.setText(R.id.item_num, pos+"");
                holder.setText(R.id.item_type, YyjfListbean.getType());
                holder.setText(R.id.item_where, YyjfListbean.getOrganization());
                holder.setText(R.id.item_time, YyjfListbean.getReceiveTime());
                holder.setText(R.id.item_state, YyjfListbean.getStatus());
            }
        };
        recyclerView.setAdapter(commonAdapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page= 0;
                getData();
            }
        });
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_qzyj;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }
    private void getData(){
        Map<String,String> map = new HashMap<>();
        map.put("rows","10");
        map.put("page",page+"");
        map.put("type",types[where]);
        map.put("username", UserUtils.getUserName());
        String s2 = new Gson().toJson(map);
        String aesEncode = Base64Converter.AESEncode(TagConstant.AESKEY,s2) ;
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.appointmentList)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aesEncode)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        String a = Base64Converter.AESDncode(TagConstant.AESKEY,s.getData());
                        BaseListBean<YyjfListbean> bean = JsonUtils.getInstance().fromJsonArray(a, YyjfListbean.class);
                        if (page==0){
                            qzyjBeans.clear();
                        }
                        qzyjBeans.addAll(bean.getData());
                        if (bean.getData().size()==0){
                            refreshLayout.finishLoadMore(100,true,true);
                        }else {
                            refreshLayout.finishLoadMore(100,true,false);
                        }
                        refreshLayout.finishRefresh(true);
                    }else {
                        refreshLayout.finishLoadMore(false);
                        refreshLayout.finishRefresh(false);
                    }
                    commonAdapter.notifyDataSetChanged();
                }, throwable -> {
                    refreshLayout.finishLoadMore(false);
                    refreshLayout.finishRefresh(false);
                });
    }
}

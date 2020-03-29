package com.qinyue.monitor.my;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseDataBean;
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
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/28
 * 描述:
 **/
public class JczActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    int page = 0;
    List<JczxxMsgBean> qzyjBeans = new ArrayList<>();
    CommonAdapter<JczxxMsgBean> commonAdapter;
    @Override
    public String initTitleText() {
        return "检察长信箱";
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_transparent_divider));
        recyclerView.addItemDecoration(divider);
        commonAdapter = new CommonAdapter<JczxxMsgBean>(this,R.layout.item_jcz,qzyjBeans) {
            @Override
            protected void convert(ViewHolder holder, JczxxMsgBean qzyjBean, int pos) {
                pos++;
                holder.setText(R.id.item_num, pos+"");
                holder.setText(R.id.item_name, qzyjBean.getWriter());
                if (qzyjBean.getDateCreated().contains(" ")){
                    String[] s = qzyjBean.getDateCreated().split(" ");
                    holder.setText(R.id.item_time, s[0]);
                }else {
                    holder.setText(R.id.item_time, qzyjBean.getDateCreated());
                }

                holder.setText(R.id.item_phone, qzyjBean.getPhone());
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
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL2 + NetConstant.letterShow)
                .add("username", UserUtils.getUserName())
                .add("code","110000")
                .asParser(new SimpleParser<BaseDataBean<String>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.isResult()){
                        String a = Base64Converter.AESDncode(TagConstant.AESKEY,s.getData());
                        List<JczxxMsgBean> bean = JsonUtils.getInstance().gson.fromJson(a, new TypeToken<List<JczxxMsgBean>>(){}.getType());
                        if (page==0){
                            qzyjBeans.clear();
                        }
                        qzyjBeans.addAll(bean);
                        if (bean.size()==0){
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

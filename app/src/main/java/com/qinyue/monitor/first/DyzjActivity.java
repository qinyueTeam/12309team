package com.qinyue.monitor.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.view.MyView;
import com.qinyue.monitor.view.WebViewActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

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
public class DyzjActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    int page = 1;
    List<JcxwdtListBean> jwxzBeans = new ArrayList<>();
    CommonAdapter<JcxwdtListBean> commonAdapter;
    @Override
    public String initTitleText() {
        return "党员之家";
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
        commonAdapter = new CommonAdapter<JcxwdtListBean>(DyzjActivity.this, R.layout.item_jcxwdt, jwxzBeans) {
            @Override
            protected void convert(ViewHolder holder, JcxwdtListBean messageBean, int pos) {
                MyView myView = holder.getView(R.id.view);
                ImageView imageView = holder.getView(R.id.img);
                if (messageBean.getImage()!=null&&!messageBean.getImage().isEmpty()){
                    myView.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    //.transform(new GlideCircleTransform(ConvertUtils.dp2px(2),ContextCompat.getColor(context,R.color.home_color)))
                    Glide.with(DyzjActivity.this).load(messageBean.getImage()).fitCenter().into(imageView);
                }else {
                    myView.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    if (messageBean.getReleaseTime().indexOf("-")>0){
                        String[] split = messageBean.getReleaseTime().split("-");
                        if (split.length==3){
                            myView.setTime(split[1],split[2]);
                        }
                    }
                }
                holder.setText(R.id.title,messageBean.getTitle());
                holder.setText(R.id.content,messageBean.getSummary());
            }
        };
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                Intent intent = new Intent(DyzjActivity.this, WebViewActivity.class);
                intent.putExtra("content",jwxzBeans.get(i).getContent());
                intent.putExtra("title",jwxzBeans.get(i).getTitle());
                intent.putExtra("time",jwxzBeans.get(i).getReleaseTime());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        recyclerView.setAdapter(commonAdapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page= 1;
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
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL2 + NetConstant.newsList)
                .add("orgId", "1")
                .add("page", page)
                .add("type", "dyzj")
                .asParser(new SimpleParser<BaseArrayDataBean<JcxwdtListBean>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.isResult()){
                        if (page==1){
                            jwxzBeans.clear();
                        }
                        jwxzBeans.addAll(s.getData());
                        if (s.getData().size()==0){
                            refreshLayout.finishLoadMore(100,true,true);
                        }else {
                            refreshLayout.finishLoadMore(100,true,false);
                        }
                        refreshLayout.finishRefresh(true);
                    }else {
                        refreshLayout.finishRefresh(false);
                        refreshLayout.finishLoadMore(false);
                    }
                    commonAdapter.notifyDataSetChanged();
                }, throwable -> {
                    refreshLayout.finishRefresh(false);
                    refreshLayout.finishLoadMore(false);
                });
    }
}

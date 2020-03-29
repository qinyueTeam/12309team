package com.qinyue.monitor.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseArrayDataBean;
import com.qinyue.monitor.base.BaseListBean;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.view.ScaleTransitionPagerTitleView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/25
 * 描述:
 **/
public class FirstFragment extends Fragment {
    @BindView(R.id.headimg)
    ImageView headImg;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String[] strings = {"检察新闻动态", "法律法规", "重要案件信息","法律文书公开"};
    private int[] page = {1,1,0,0};
    private boolean[] loadMore = {true,true,true,true};
    CommonNavigator commonNavigator;
    private int checkIndex = 0;
    Unbinder unbinder;
    MainActivity mainActivity;
    FirstPagerAdapter firstPagerAdapter;
    Map<Integer,Object> map = new HashMap<>();
    boolean isFirst = true;
    public FirstFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        mainActivity = (MainActivity) getActivity();
        Glide.with(this).load(TagConstant.BASEBANNER_URL).into(headImg);
        initOnCliclek();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initOther();
    }

    private void initOnCliclek() {

    }

    private void initOther(){
        if (isFirst){
            isFirst = false;
            map.put(0,new ArrayList<JcxwdtListBean>());
            map.put(1,new ArrayList<JcxwdtListBean>());
            map.put(2,new ArrayList<ZyajBean>());
            map.put(3,new ArrayList<ZyajBean>());
            firstPagerAdapter = new FirstPagerAdapter(mainActivity,strings);
            viewPager.setOffscreenPageLimit(4);
            viewPager.setAdapter(firstPagerAdapter);
            commonNavigator = new CommonNavigator(mainActivity);
            commonNavigator.setAdjustMode(true);
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    return strings.length;
                }

                @Override
                public IPagerTitleView getTitleView(Context context, int i) {
                    BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                    SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                    simplePagerTitleView.setText(strings[i]);
                    simplePagerTitleView.setTextSize(12);
                    simplePagerTitleView.setPadding(5, 5, 5, 5);
                    simplePagerTitleView.setNormalColor(ContextCompat.getColor(mainActivity, R.color.text_color));
                    simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mainActivity, R.color.text_color));
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(i);
                        }
                    });
                    badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);
                    return badgePagerTitleView;
                }
                @Override
                public IPagerIndicator getIndicator(Context context) {
                    LinePagerIndicator indicator = new LinePagerIndicator(context);
                    indicator.setLineHeight(UIUtil.dip2px(context, 1));
                    indicator.setColors(ContextCompat.getColor(mainActivity, R.color.home_color));
                    return indicator;
                }

                @Override
                public float getTitleWeight(Context context, int index) {
                    if (index == 0) {
                        return 1.5f;
                    } else if (index == 1) {
                        return 1.0f;
                    } else if (index == 2){
                        return 1.5f;
                    }else {
                        return 1.5f;
                    }
                }
            });
            magicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(magicIndicator, viewPager);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    checkIndex = position;
//                myXFPagerAdapter.setSelectItem(position);
                    switch (checkIndex){
                        case 0:
                        case 1:{
                            List<JcxwdtListBean> listBeans=(List<JcxwdtListBean>)  map.get(checkIndex);
                            if (listBeans!=null&&listBeans.size()==0&&loadMore[checkIndex]){
                                loadMore[checkIndex] = true;
                                page[checkIndex] = 1;
                                if (checkIndex==0){
                                    getData(checkIndex);
                                }else {
                                    getData2(checkIndex);
                                }
                            }
                        }break;
                        case 2:
                        case 3:{
                            List<ZyajBean> listBeans=(List<ZyajBean>)  map.get(checkIndex);
                            if (listBeans!=null&&listBeans.size()==0&&loadMore[checkIndex]){
                                loadMore[checkIndex] = true;
                                page[checkIndex] = 0;
                                if (checkIndex==2){
                                    getData3(checkIndex);
                                }else {
                                    getData4(checkIndex);
                                }
                            }
                        }break;
                    }
                    refreshLayout.setEnableLoadMore(loadMore[checkIndex]);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    page[checkIndex]++;
                    switch (checkIndex){
                        case 0:{
                            getData(checkIndex);
                        }break;
                        case 1:{
                            getData2(checkIndex);
                        }break;
                        case 2:{
                            getData3(checkIndex);
                        }break;
                        case 3:{
                            getData4(checkIndex);
                        }break;
                    }
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    loadMore[checkIndex] = true;
                    switch (checkIndex){
                        case 0:{
                            page[checkIndex] = 1;
                            getData(checkIndex);
                        }break;
                        case 1:{
                            page[checkIndex] = 1;
                            getData2(checkIndex);
                        }break;
                        case 2:{
                            page[checkIndex] = 0;
                            getData3(checkIndex);
                        }break;
                        case 3:{
                            page[checkIndex] = 0;
                            getData4(checkIndex);
                        }break;
                    }
                }
            });
            refreshLayout.autoRefresh();
        }
    }

    @OnClick({R.id.lin_jcyjj,R.id.lin_jwxz,R.id.lin_jwjd,R.id.lin_shce,R.id.lin_jczxx,R.id.lin_jcfc,R.id.lin_dyzj,R.id.lin_flzx})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_jcyjj:{//检察院简介

            }break;
            case R.id.lin_jwxz:{//检务须知

            }break;
            case R.id.lin_jwjd:{//检务监督

            }break;
            case R.id.lin_shce:{//扫黑除恶

            }break;
            case R.id.lin_jczxx:{//检察长信箱

            }break;
            case R.id.lin_jcfc:{//检察风采

            }break;
            case R.id.lin_dyzj:{//党员之家

            }break;
            case R.id.lin_flzx:{//法律咨询

            }break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void getData(int pos){
        refreshLayout.setEnableLoadMore(true);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL2 + NetConstant.newsList)
                .add("orgId", "1")
                .add("page", page[pos])
                .add("type", "jcxwdt")
                .asParser(new SimpleParser<BaseArrayDataBean<JcxwdtListBean>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.isResult()){
                        List<JcxwdtListBean> listBeans =(List<JcxwdtListBean>)map.get(pos);
                        if (page[pos]==1){
                            listBeans.clear();
                        }
                        listBeans.addAll(s.getData());
                        firstPagerAdapter.setJcxwdtListBeans(s.getData(),page[pos]==1?true:false);
                        if (s.getData().size()==0){
                            loadMore[pos] = false;
                        }else {
                            loadMore[pos] = true;
                        }
                        refreshLayout.finishLoadMore(true);
                        refreshLayout.setEnableLoadMore( loadMore[pos]);
                        refreshLayout.finishRefresh(true);
                    }else {
                        refreshLayout.finishRefresh(false);
                        refreshLayout.finishLoadMore(false);
                    }
                }, throwable -> {
                    refreshLayout.finishRefresh(false);
                    refreshLayout.finishLoadMore(false);
                });
    }
    private void getData2(int pos){
        refreshLayout.setEnableLoadMore(true);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL2 + NetConstant.newsList)
                .add("orgId", "1")
                .add("page", page[pos])
                .add("type", "flfg")
                .asParser(new SimpleParser<BaseArrayDataBean<JcxwdtListBean>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.isResult()){
                        List<JcxwdtListBean> listBeans =(List<JcxwdtListBean>)map.get(pos);
                        if (page[pos]==1){
                            listBeans.clear();
                        }
                        listBeans.addAll(s.getData());
                        firstPagerAdapter.setFlfgListBeans(s.getData(),page[pos]==1?true:false);
                        if (s.getData().size()==0){
                            loadMore[pos] = false;
                        }else {
                            loadMore[pos] = true;
                        }
                        refreshLayout.finishLoadMore(true);
                        refreshLayout.setEnableLoadMore( loadMore[pos]);
                        refreshLayout.finishRefresh(true);
                    }else {
                        refreshLayout.finishRefresh(false);
                        refreshLayout.finishLoadMore(false);
                    }
                }, throwable -> {
                    refreshLayout.finishRefresh(false);
                    refreshLayout.finishLoadMore(false);
                });
    }
    private void getData3(int pos){
        refreshLayout.setEnableLoadMore(true);
        Map<String,String> map = new HashMap<>();
        map.put("page",page[pos]+"");
        map.put("row","15");
        String s2 = new Gson().toJson(map);
        s2 = Base64Converter.AESEncode(TagConstant.AESKEY,s2) ;
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.caseList)
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("data",s2)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        String data = Base64Converter.AESDncode(TagConstant.AESKEY,s.getData());
                        BaseListBean<ZyajBean> zyajBeanBaseListBean = JsonUtils.getInstance().fromJsonArray(data, ZyajBean.class);
                        if (zyajBeanBaseListBean.getData()!=null) {
                            List<ZyajBean> listBeans = (List<ZyajBean>) FirstFragment.this.map.get(pos);
                            if (page[pos] == 0) {
                                listBeans.clear();
                            }
                            listBeans.addAll(zyajBeanBaseListBean.getData());
                            firstPagerAdapter.setZyajListBeans(zyajBeanBaseListBean.getData(), page[pos] == 0 ? true : false);
                            if (zyajBeanBaseListBean.getData().size() == 0) {
                                loadMore[pos] = false;
                            } else {
                                loadMore[pos] = true;
                            }
                        }
                        refreshLayout.finishLoadMore(true);
                        refreshLayout.setEnableLoadMore( loadMore[pos]);
                        refreshLayout.finishRefresh(true);
                    }else {
                        refreshLayout.finishRefresh(false);
                        refreshLayout.finishLoadMore(false);
                    }
                }, throwable -> {
                    refreshLayout.finishRefresh(false);
                    refreshLayout.finishLoadMore(false);
                });
    }
    private void getData4(int pos){
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.finishRefresh();
        Map<String,String> map = new HashMap<>();
        map.put("page",page[pos]+"");
        map.put("row","15");
        String s2 = new Gson().toJson(map);
        s2 = Base64Converter.AESEncode(TagConstant.AESKEY,s2) ;
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.lawDocList)
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("data",s2)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        String data = Base64Converter.AESDncode(TagConstant.AESKEY,s.getData());
                        BaseListBean<ZyajBean> zyajBeanBaseListBean = JsonUtils.getInstance().fromJsonArray(data, ZyajBean.class);
                        if (zyajBeanBaseListBean.getData()!=null) {
                            List<ZyajBean> listBeans = (List<ZyajBean>) FirstFragment.this.map.get(pos);
                            if (page[pos] == 0) {
                                listBeans.clear();
                            }
                            listBeans.addAll(zyajBeanBaseListBean.getData());
                            firstPagerAdapter.setFlwsListBeans(zyajBeanBaseListBean.getData(), page[pos] == 0 ? true : false);
                            if (zyajBeanBaseListBean.getData().size() == 0) {
                                loadMore[pos] = false;
                            } else {
                                loadMore[pos] = true;
                            }
                        }
                        refreshLayout.finishLoadMore(true);
                        refreshLayout.setEnableLoadMore( loadMore[pos]);
                        refreshLayout.finishRefresh(true);
                    }else {
                        refreshLayout.finishRefresh(false);
                        refreshLayout.finishLoadMore(false);
                    }
                }, throwable -> {
                    refreshLayout.finishRefresh(false);
                    refreshLayout.finishLoadMore(false);
                });
    }
}

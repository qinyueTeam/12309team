package com.qinyue.monitor.first;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qinyue.monitor.R;
import com.qinyue.monitor.home.MainActivity;
import com.qinyue.monitor.view.MyView;
import com.qinyue.monitor.view.WebViewActivity;
import com.qinyue.monitor.view.WebViewActivity2;
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
    List<JcxwdtListBean> flfgListBeans = new ArrayList<>();
    List<ZyajBean> zyanListBeans = new ArrayList<>();
    List<ZyajBean> flwsListBeans = new ArrayList<>();
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
    public void setFlfgListBeans(List<JcxwdtListBean> jcxwdtListBeans,boolean isRefresh){
        if (isRefresh){
            this.flfgListBeans.clear();
        }
        this.flfgListBeans.addAll(jcxwdtListBeans);
        CommonAdapter<JcxwdtListBean> commonAdapter = (CommonAdapter<JcxwdtListBean>)adapterMap.get(1);
        commonAdapter.notifyDataSetChanged();
    }
    public void setZyajListBeans(List<ZyajBean> jcxwdtListBeans,boolean isRefresh){
        if (isRefresh){
            this.zyanListBeans.clear();
        }
        this.zyanListBeans.addAll(jcxwdtListBeans);
        CommonAdapter<ZyajBean> commonAdapter = (CommonAdapter<ZyajBean>)adapterMap.get(2);
        commonAdapter.notifyDataSetChanged();
    }
    public void setFlwsListBeans(List<ZyajBean> jcxwdtListBeans,boolean isRefresh){
        if (isRefresh){
            this.flwsListBeans.clear();
        }
        this.flwsListBeans.addAll(jcxwdtListBeans);
        CommonAdapter<ZyajBean> commonAdapter = (CommonAdapter<ZyajBean>)adapterMap.get(3);
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
                        ImageView imageView = holder.getView(R.id.img);
                        if (messageBean.getImage()!=null&&!messageBean.getImage().isEmpty()){
                            myView.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                            //.transform(new GlideCircleTransform(ConvertUtils.dp2px(2),ContextCompat.getColor(context,R.color.home_color)))
                            Glide.with(context).load(messageBean.getImage()).fitCenter().into(imageView);
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
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("content",jcxwdtListBeans.get(i).getContent());
                        intent.putExtra("title",jcxwdtListBeans.get(i).getTitle());
                        intent.putExtra("time",jcxwdtListBeans.get(i).getReleaseTime());
                        context.startActivity(intent);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView.setAdapter(commonAdapter);
                adapterMap.put(position,commonAdapter);
            }break;
            case 1:{
                CommonAdapter<JcxwdtListBean> commonAdapter = new CommonAdapter<JcxwdtListBean>(context, R.layout.item_jcxwdt, flfgListBeans) {
                    @Override
                    protected void convert(ViewHolder holder, JcxwdtListBean messageBean, int pos) {
                        MyView myView = holder.getView(R.id.view);
                        ImageView imageView = holder.getView(R.id.img);
                        if (messageBean.getImage()!=null&&!messageBean.getImage().isEmpty()){
                            myView.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                            //.transform(new GlideCircleTransform(ConvertUtils.dp2px(2),ContextCompat.getColor(context,R.color.home_color)))
                            Glide.with(context).load(messageBean.getImage()).fitCenter().into(imageView);
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
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("content",flfgListBeans.get(i).getContent());
                        intent.putExtra("title",flfgListBeans.get(i).getTitle());
                        intent.putExtra("time",flfgListBeans.get(i).getReleaseTime());
                        context.startActivity(intent);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView.setAdapter(commonAdapter);
                adapterMap.put(position,commonAdapter);
            }break;
            case 2:{
                CommonAdapter<ZyajBean> commonAdapter = new CommonAdapter<ZyajBean>(context, R.layout.item_zyaj, zyanListBeans) {
                    @Override
                    protected void convert(ViewHolder holder, ZyajBean messageBean, int pos) {
                        holder.setText(R.id.tv,messageBean.getTitle());
                    }
                };
                commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Intent intent = new Intent(context, WebViewActivity2.class);
                        intent.putExtra("id",zyanListBeans.get(i).getId());
                        intent.putExtra("where",0);
                        context.startActivity(intent);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView.setAdapter(commonAdapter);
                adapterMap.put(position,commonAdapter);
            }break;
            case 3:{
                CommonAdapter<ZyajBean> commonAdapter = new CommonAdapter<ZyajBean>(context, R.layout.item_zyaj, flwsListBeans) {
                    @Override
                    protected void convert(ViewHolder holder, ZyajBean messageBean, int pos) {
                        holder.setText(R.id.tv,messageBean.getTitle());
                    }
                };
                commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Intent intent = new Intent(context, WebViewActivity2.class);
                        intent.putExtra("id",flwsListBeans.get(i).getId());
                        intent.putExtra("where",1);
                        context.startActivity(intent);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
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

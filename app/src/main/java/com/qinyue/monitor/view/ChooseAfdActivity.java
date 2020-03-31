package com.qinyue.monitor.view;

import android.content.Intent;
import android.os.Bundle;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean2;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.xuexiang.xui.widget.toast.XToast;

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
 * 创建日期:2020/3/31
 * 描述:
 **/
public class ChooseAfdActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ExpandSectionAdapter expandSectionAdapter;
    private int index = 0;
    @Override
    public String initTitleText() {
        return "选择案发地";
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        index = getIntent().getIntExtra("index",0);
        miniLoadingDialog.show();
        getAfdData();
    }

    @Override
    protected void initview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_grey_divider));
        recyclerView.addItemDecoration(divider);
        expandSectionAdapter = new ExpandSectionAdapter(this);
        recyclerView.setAdapter(expandSectionAdapter);
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activty_chooseafd;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }
    private void getAfdData(){
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "7")
                .asParser(new SimpleParser<BaseArrayDataBean2<SelectSectionParentEntity>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        for (int i = 0; i < s.getData().size(); i++) {
                            s.getData().get(i).setType(SelectSectionParentEntity.PARENT_ITEM);
                        }
                        expandSectionAdapter.setParentData(s.getData());
                    }else {
                        XToast.error(ChooseAfdActivity.this,s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ChooseAfdActivity.this,throwable.getMessage()).show();
                });
    }
    public void getAfdDataForcChild(String id,int pos,String firstCode,String firstName,String parentName){
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "7")
                .add("id",id)
                .asParser(new SimpleParser<BaseArrayDataBean2<SelectSectionParentEntity>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        for (int i = 0; i < s.getData().size(); i++) {
                            if (s.getData().get(i).isHasChild()){
                                s.getData().get(i).setType(SelectSectionParentEntity.PARENT_ITEM);
                            }else {
                                s.getData().get(i).setType(SelectSectionParentEntity.CHILD_ITEM);
                                if (firstCode!=null) {
                                    s.getData().get(i).setParentCodeForFirst(firstCode);
                                }
                            }
                            if (firstName!=null){
                                s.getData().get(i).setFirstName(firstName);
                            }
                            if (parentName!=null){
                                s.getData().get(i).setParentName(parentName);
                            }
                        }
                        expandSectionAdapter.setChildData(s.getData(),pos);
                    }else {
                        XToast.error(ChooseAfdActivity.this,s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ChooseAfdActivity.this,throwable.getMessage()).show();
                });
    }
    public void setClick(SelectSectionParentEntity bean){
        Intent intent = new Intent();
        intent.putExtra("data",bean);
        intent.putExtra("index",index);
        setResult(123,intent);
        finish();
    }
}

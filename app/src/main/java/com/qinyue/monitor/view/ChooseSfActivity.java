package com.qinyue.monitor.view;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean2;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.first.SfBean;
import com.qinyue.monitor.util.JsonUtils;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
import java.util.List;

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
public class ChooseSfActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ExpandSectionAdapterForSf expandSectionAdapter;
    private int index = 0;
    List<SfBean> sfBeans;
    @Override
    public String initTitleText() {
        return "选择身份";
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        index = getIntent().getIntExtra("index",0);
        getAfdData();
    }

    @Override
    protected void initview() {
        sfBeans = JsonUtils.getInstance().gson.fromJson(getString(R.string.str_sf),new TypeToken<List<SfBean>>(){}.getType());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_grey_divider));
        recyclerView.addItemDecoration(divider);
        expandSectionAdapter = new ExpandSectionAdapterForSf(this);
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
        List<SelectSectionParentEntity> list = new ArrayList<>();
        for (int i = 0; i < sfBeans.size(); i++) {
            SelectSectionParentEntity bean = new SelectSectionParentEntity();
            bean.setCode(sfBeans.get(i).getId());
            bean.setName(sfBeans.get(i).getText());
            if (sfBeans.get(i).getChildren()!=null&&sfBeans.get(i).getChildren().size()!=0){
                bean.setHasChild(true);
                bean.setType(SelectSectionParentEntity.PARENT_ITEM);
            }else {
                bean.setHasChild(false);
                bean.setType(SelectSectionParentEntity.CHILD_ITEM);
            }
            list.add(bean);
        }
        expandSectionAdapter.setParentData(list);
    }
    public void getAfdDataForcChild(String id,int pos,String firstCode,String firstName,String parentName){
        List<SelectSectionParentEntity> list = new ArrayList<>();
        if (firstCode==null) {
            //点的第一级
            for (int i = 0; i < sfBeans.size(); i++) {
                if (sfBeans.get(i).getId().equals(id)) {
                    //确定第一级位置
                    for (int j = 0; j < sfBeans.get(i).getChildren().size(); j++) {
                        SfBean.ChildrenBeanX childrenBeanX = sfBeans.get(i).getChildren().get(j);
                        SelectSectionParentEntity bean = new SelectSectionParentEntity();
                        bean.setCode(childrenBeanX.getId());
                        bean.setName(childrenBeanX.getText());
                        bean.setParentCode(sfBeans.get(i).getId());
                        bean.setFirstName(sfBeans.get(i).getText());
                        if (childrenBeanX.getChildren()!=null&&childrenBeanX.getChildren().size()>0){
                            //有第三级
                            bean.setType(SelectSectionParentEntity.PARENT_ITEM);
                            bean.setHasChild(true);
                        }else {
                            //无第三级
                            bean.setType(SelectSectionParentEntity.CHILD_ITEM);
                            bean.setHasChild(false);
                        }
                        list.add(bean);
                    }
                    expandSectionAdapter.setChildData(list,pos);
                    return;
                }
            }
        }else {
            //点的第二级
            for (int i = 0; i < sfBeans.size(); i++) {
                if (sfBeans.get(i).getId().equals(firstCode)) {
                    //确定第一级位置
                    for (int j = 0; j < sfBeans.get(i).getChildren().size(); j++) {
                        //确定第二级位置
                        if (sfBeans.get(i).getChildren().get(j).getId().equals(id)){
                            for (int k = 0; k < sfBeans.get(i).getChildren().get(j).getChildren().size(); k++) {
                                SfBean.ChildrenBeanX.ChildrenBean childrenBean = sfBeans.get(i).getChildren().get(j).getChildren().get(k);
                                SelectSectionParentEntity bean = new SelectSectionParentEntity();
                                bean.setCode(childrenBean.getId());
                                bean.setName(childrenBean.getText());
                                bean.setParentCode(sfBeans.get(i).getChildren().get(j).getId());
                                bean.setFirstName(sfBeans.get(i).getText());
                                bean.setParentName(sfBeans.get(i).getChildren().get(j).getText());
                                bean.setParentCodeForFirst(sfBeans.get(i).getId());
                                if (childrenBean.getChildren()!=null&&childrenBean.getChildren().size()>0){
                                    //有第三级
                                    bean.setType(SelectSectionParentEntity.PARENT_ITEM);
                                    bean.setHasChild(true);
                                }else {
                                    //无第三级
                                    bean.setType(SelectSectionParentEntity.CHILD_ITEM);
                                    bean.setHasChild(false);
                                }
                                list.add(bean);
                            }
                            expandSectionAdapter.setChildData(list,pos);
                            return;
                        }

                    }
                    expandSectionAdapter.setChildData(list,pos);
                    return;
                }
            }
        }
//        miniLoadingDialog.show();
//        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
//                .add("appId", TagConstant.APPID)
//                .add("code", TagConstant.CODE)
//                .add("type", "7")
//                .add("id",id)
//                .asParser(new SimpleParser<BaseArrayDataBean2<SelectSectionParentEntity>>(){})
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> {
//                    miniLoadingDialog.dismiss();
//                    if (s.getResult()==200){
//                        for (int i = 0; i < s.getData().size(); i++) {
//                            if (s.getData().get(i).isHasChild()){
//                                s.getData().get(i).setType(SelectSectionParentEntity.PARENT_ITEM);
//                            }else {
//                                s.getData().get(i).setType(SelectSectionParentEntity.CHILD_ITEM);
//                                if (firstCode!=null) {
//                                    s.getData().get(i).setParentCodeForFirst(firstCode);
//                                }
//                            }
//                            if (firstName!=null){
//                                s.getData().get(i).setFirstName(firstName);
//                            }
//                            if (parentName!=null){
//                                s.getData().get(i).setParentName(parentName);
//                            }
//                        }
//                        expandSectionAdapter.setChildData(s.getData(),pos);
//                    }else {
//                        XToast.error(ChooseSfActivity.this,s.getMsg()).show();
//                    }
//                }, throwable -> {
//                    miniLoadingDialog.dismiss();
//                    XToast.error(ChooseSfActivity.this,throwable.getMessage()).show();
//                });
    }
    public void setClick(SelectSectionParentEntity bean){
        Intent intent = new Intent();
        intent.putExtra("data",bean);
        intent.putExtra("index",index);
        setResult(124,intent);
        finish();
    }
}

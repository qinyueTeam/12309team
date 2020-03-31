package com.qinyue.monitor.view;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.first.SfBean;
import com.qinyue.monitor.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public class ChooseSxxzActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ExpandSectionAdapterForSxxz expandSectionAdapter;
    private int index = 0;
    List<SfBean> sfBeans;

    @Override
    public String initTitleText() {
        return "选择涉嫌性质";
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        index = getIntent().getIntExtra("index", 0);
        getAfdData();
    }

    @Override
    protected void initview() {
        sfBeans = JsonUtils.getInstance().gson.fromJson(getString(R.string.str_sxxz), new TypeToken<List<SfBean>>() {
        }.getType());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_grey_divider));
        recyclerView.addItemDecoration(divider);
        expandSectionAdapter = new ExpandSectionAdapterForSxxz(this);
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

    private void getAfdData() {
        List<ParentEntity> list = new ArrayList<>();
        for (int i = 0; i < sfBeans.size(); i++) {
            ParentEntity bean = new ParentEntity();
            bean.setCode(sfBeans.get(i).getId());
            bean.setName(sfBeans.get(i).getText());
            bean.setLv(0);
            if (sfBeans.get(i).getChildren() != null && sfBeans.get(i).getChildren().size() != 0) {
                bean.setHasChild(true);
                bean.setType(ParentEntity.PARENT_ITEM);
            } else {
                bean.setHasChild(false);
                bean.setType(ParentEntity.CHILD_ITEM);
            }
            list.add(bean);
        }
        expandSectionAdapter.setParentData(list);
    }

    public void getAfdDataForcChild(String id, int pos, String firstCode, String secCode, String thCode, int lv) {
        List<ParentEntity> list = new ArrayList<>();
        switch (lv) {
            case 0: {
                //点的第一级
                for (int i = 0; i < sfBeans.size(); i++) {
                    if (sfBeans.get(i).getId().equals(id)) {
                        //确定第一级位置
                        for (int j = 0; j < sfBeans.get(i).getChildren().size(); j++) {
                            SfBean.ChildrenBeanX childrenBeanX = sfBeans.get(i).getChildren().get(j);
                            ParentEntity bean = new ParentEntity();
                            bean.setCode(childrenBeanX.getId());
                            bean.setName(childrenBeanX.getText());
                            bean.setLv(1);
                            bean.setFirstCode(sfBeans.get(i).getId());
                            if (childrenBeanX.getChildren() != null && childrenBeanX.getChildren().size() > 0) {
                                //有第三级
                                bean.setType(ParentEntity.PARENT_ITEM);
                                bean.setHasChild(true);
                            } else {
                                //无第三级
                                bean.setType(ParentEntity.CHILD_ITEM);
                                bean.setHasChild(false);
                            }
                            list.add(bean);
                        }
                        expandSectionAdapter.setChildData(list, pos);
                        return;
                    }
                }
            }
            break;
            case 1: {
                //点的第二级
                for (int i = 0; i < sfBeans.size(); i++) {
                    if (sfBeans.get(i).getId().equals(firstCode)) {
                        //确定第一级位置
                        for (int j = 0; j < sfBeans.get(i).getChildren().size(); j++) {
                            //确定第二级位置
                            if (sfBeans.get(i).getChildren().get(j).getId().equals(id)) {
                                for (int k = 0; k < sfBeans.get(i).getChildren().get(j).getChildren().size(); k++) {
                                    SfBean.ChildrenBeanX.ChildrenBean childrenBean = sfBeans.get(i).getChildren().get(j).getChildren().get(k);
                                    ParentEntity bean = new ParentEntity();
                                    bean.setCode(childrenBean.getId());
                                    bean.setName(childrenBean.getText());
                                    bean.setSecCode(sfBeans.get(i).getChildren().get(j).getId());
                                    bean.setLv(2);
                                    bean.setFirstCode(sfBeans.get(i).getId());
                                    if (childrenBean.getChildren() != null && childrenBean.getChildren().size() > 0) {
                                        //有第三级
                                        bean.setType(ParentEntity.PARENT_ITEM);
                                        bean.setHasChild(true);
                                    } else {
                                        //无第三级
                                        bean.setType(ParentEntity.CHILD_ITEM);
                                        bean.setHasChild(false);
                                    }
                                    list.add(bean);
                                }
                                expandSectionAdapter.setChildData(list, pos);
                                return;
                            }

                        }
                    }
                }
            }
            break;
            case 2: {
                //点的第三级
                for (int i = 0; i < sfBeans.size(); i++) {
                    if (sfBeans.get(i).getId().equals(firstCode)) {
                        //确定第一级位置
                        for (int j = 0; j < sfBeans.get(i).getChildren().size(); j++) {
                            //确定第二级位置
                            if (sfBeans.get(i).getChildren().get(j).getId().equals(secCode)) {
                                for (int k = 0; k < sfBeans.get(i).getChildren().get(j).getChildren().size(); k++) {
                                    //确定第三级位置
                                    if (sfBeans.get(i).getChildren().get(j).getChildren().get(k).getId().equals(id)) {
                                        SfBean.ChildrenBeanX.ChildrenBean childrenBean = sfBeans.get(i).getChildren().get(j).getChildren().get(k);
                                        for (int l = 0; l < childrenBean.getChildren().size(); l++) {
                                            ParentEntity bean = new ParentEntity();
                                            bean.setCode(childrenBean.getChildren().get(l).getId());
                                            bean.setName(childrenBean.getChildren().get(l).getText());
                                            bean.setSecCode(sfBeans.get(i).getChildren().get(j).getId());
                                            bean.setThCode(childrenBean.getId());
                                            bean.setLv(3);
                                            bean.setFirstCode(sfBeans.get(i).getId());
                                            if (childrenBean.getChildren().get(l).getChildren() != null && childrenBean.getChildren().get(l).getChildren().size() > 0) {
                                                //有第四级
                                                bean.setType(ParentEntity.PARENT_ITEM);
                                                bean.setHasChild(true);
                                            } else {
                                                //无第四级
                                                bean.setType(ParentEntity.CHILD_ITEM);
                                                bean.setHasChild(false);
                                            }
                                            list.add(bean);
                                        }
                                        expandSectionAdapter.setChildData(list, pos);
                                        return;
                                    }
                                }

                            }

                        }
                    }
                }
            }
            break;
        }
    }

    public void setClick(ParentEntity bean) {
        Intent intent = new Intent();
        intent.putExtra("data", bean);
        intent.putExtra("index", index);
        setResult(126, intent);
        finish();
    }
}

package com.qinyue.monitor.work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.xuexiang.xui.widget.toast.XToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnderageActivity extends BaseActivity {
    @BindView(R.id.img_kg)
    ImageView imgKg;
    @BindView(R.id.img_xsss)
    ImageView imgXsss;
    @BindView(R.id.img_sqjz)
    ImageView imgSqjz;

    @Override
    public String initTitleText() {
        return "未成年司法保护专区";
    }

    @OnClick({R.id.img_kg, R.id.img_xsss, R.id.img_sqjz})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.img_kg:{
                Intent intent = new Intent(this, UnderageAccusationActivity.class);//要替换须知界面
                startActivity(intent);
            }break;
            case R.id.img_xsss:{
                Intent intent = new Intent(this, UnderageComplaintActivity.class);//要替换须知界面
                startActivity(intent);
            }break;
            case R.id.img_sqjz: {

            }break;
        }
    }
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {

    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_underage;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

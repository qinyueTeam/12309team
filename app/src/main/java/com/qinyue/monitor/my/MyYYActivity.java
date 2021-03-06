package com.qinyue.monitor.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;

import butterknife.OnClick;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/28
 * 描述:
 **/
public class MyYYActivity extends BaseActivity {
    @Override
    public String initTitleText() {
        return "我的预约";
    }

    private int where = 0;

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
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
        return R.layout.activity_myyy;
    }

    @OnClick({R.id.view_sp, R.id.view_ck, R.id.view_ls})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_sp://视频
                where = 0;
                break;
            case R.id.view_ck://窗口
                where = 1;
                break;
            case R.id.view_ls://律师
                where = 2;
                break;
        }
        Intent intent = new Intent(this, SpyyActivity.class);
        intent.putExtra("where",where);
        startActivity(intent);
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }
}

package com.qinyue.monitor.work;

import android.os.Bundle;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;

public class UnderageComplaintActivity extends BaseActivity {
    @Override
    public String initTitleText() {
        return "刑事申诉";
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
        return R.layout.activity_underage_complaint;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return null;
    }
}

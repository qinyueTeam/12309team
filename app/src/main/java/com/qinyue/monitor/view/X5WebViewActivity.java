package com.qinyue.monitor.view;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.core.AgentWeb;
import com.just.agentweb.core.client.MiddlewareWebChromeBase;
import com.just.agentweb.widget.AgentWebView;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.constant.TagConstant;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;

import butterknife.BindView;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public class X5WebViewActivity extends BaseActivity {
    @BindView(R.id.agentweb_webview)
    FrameLayout agentWebView;
    AgentWeb mAgentWeb;
    @Override
    public String initTitleText() {
        return "法律咨询";
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
    @Override
    protected void initview() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((FrameLayout) agentWebView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(TagConstant.FLZXURL);
        mAgentWeb.getAgentWebSettings().getWebSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
        mAgentWeb.getAgentWebSettings().getWebSettings().setLoadWithOverviewMode(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!mAgentWeb.back()){
           this.finish();
        }
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_x5web;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return null;
    }
}

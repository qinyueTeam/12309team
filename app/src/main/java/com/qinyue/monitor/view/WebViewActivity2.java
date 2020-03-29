package com.qinyue.monitor.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseListBean;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.home.FirstFragment;
import com.qinyue.monitor.home.ZyajBean;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/29
 * 描述:
 **/
public class WebViewActivity2 extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.time)
    TextView timeTv;
    @BindView(R.id.line)
    View lineView;
    @BindView(R.id.ll_stateful)
    StatefulLayout statefulLayout;
    private String content = "";
    private String id = "";
    private String title = "";
    private String time = "";
    private int where = 0;//0重要案件信息,1法律文书公开
    @Override
    public String initTitleText() {
        return "详情";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        where = getIntent().getIntExtra("where",0);
        if (id==null){
            statefulLayout.showEmpty();
        }else {
            if (where==0){
                getMsg();
            }else {
                titleTv.setVisibility(View.GONE);
                timeTv.setVisibility(View.GONE);
                lineView.setVisibility(View.GONE);
                getMsg2();
            }
        }
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    private void load() {
        timeTv.setText("时间: "+time);
        titleTv.setText(title);
        webview.getSettings().setBlockNetworkImage(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview.addJavascriptInterface(new JavascriptInterface(), "mainActivity");
        String varjs = "<script type='text/javascript'> \nwindow.onload = function()\n{var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%'; $img[p].style.height ='auto'}}</script>";
        String data="<html><body>"+varjs+content+"</html></body>";
//        String data = Html.fromHtml(content).toString();
        //替换img属性

        //点击查看
        String jsimg = "function()\n { var imgs = document.getElementsByTagName(\"img\");for(var i = 0; i < imgs.length; i++){  imgs[i].onclick = function()\n{mainActivity.startPhotoActivity(this.src);}}}";

//        webview.loadData(varjs+data,"text/html", "UTF-8");
        webview.loadDataWithBaseURL(null,data, "text/html", "utf-8",null);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                webview.loadUrl("javascript:(" + jsimg + ")()");
            }
        });

    }
    @Override
    protected void initview() {
        statefulLayout.showLoading();
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activty_webview2;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }
    public class JavascriptInterface {
        @android.webkit.JavascriptInterface
        public void startPhotoActivity(String imageUrl) {
            List<LocalMedia> media = new ArrayList<>();
            LocalMedia localMedia = new LocalMedia();
            localMedia.setPath(imageUrl);
            localMedia.setCompressPath(imageUrl);
            localMedia.setOriginalPath(imageUrl);
            localMedia.setCutPath(imageUrl);
            media.add(localMedia);
            //根据URL查看大图逻辑
            PictureSelector.create(WebViewActivity2.this)
                    .themeStyle(R.style.picture_default_style)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(0, media);
        }
    }
    private void getMsg(){
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        String s2 = new Gson().toJson(map);
        s2 = Base64Converter.AESEncode(TagConstant.AESKEY,s2) ;
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.caseDetail)
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("data",s2)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        String data = Base64Converter.AESDncode(TagConstant.AESKEY,s.getData());
                        ZyajBean zyajBean = JsonUtils.getInstance().gson.fromJson(data,ZyajBean.class);
                        content = zyajBean.getZyajMsgBean().getContent();
                        title = zyajBean.getZyajMsgBean().getTitle();
                        time = TimeUtils.millis2String(zyajBean.getZyajMsgBean().getPublishedTime());
                        load();
                        statefulLayout.showContent();
                    }else {
                        statefulLayout.showError(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                statefulLayout.showLoading();
                                getMsg();
                            }
                        });
                    }
                }, throwable -> {
                    statefulLayout.showError(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            statefulLayout.showLoading();
                            getMsg();
                        }
                    });
                });
    }
    private void getMsg2(){
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        String s2 = new Gson().toJson(map);
        s2 = Base64Converter.AESEncode(TagConstant.AESKEY,s2) ;
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.lawDocDetail)
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("data",s2)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        String data = Base64Converter.AESDncode(TagConstant.AESKEY,s.getData());
                        ZyajBean zyajBean = JsonUtils.getInstance().gson.fromJson(data,ZyajBean.class);
                        content = zyajBean.getZyajMsgBean().getContent();
                        title = zyajBean.getZyajMsgBean().getTitle();
                        time = TimeUtils.millis2String(zyajBean.getZyajMsgBean().getPublishedTime());
                        load();
                        statefulLayout.showContent();
                    }else {
                        statefulLayout.showError(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                statefulLayout.showLoading();
                                getMsg();
                            }
                        });
                    }
                }, throwable -> {
                    statefulLayout.showError(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            statefulLayout.showLoading();
                            getMsg();
                        }
                    });
                });
    }
}

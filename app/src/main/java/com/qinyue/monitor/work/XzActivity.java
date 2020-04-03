package com.qinyue.monitor.work;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.first.JcxwdtListBean;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.view.WebViewActivity2;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/30
 * 描述:
 **/
public class XzActivity extends BaseActivity {
    @BindView(R.id.ll_stateful)
    StatefulLayout statefulLayout;
    @BindView(R.id.webview)
    WebView webview;
    private String[] titles = {"控告须知","刑事申诉须知","国家赔偿申请须知","民事申诉须知","行政申诉须知","其他信访须知","未成年人司法保护须知","公益诉讼线索提供须知","群众意见建议箱须知"};
    private String[] ids = {"2c9fa2dd6e39a37a016e3a81760a0000","402881e76e20a2a9016e20b587e1000a","402881e76e20a2a9016e20b4d3fa0009","402881e76e20a2a9016e20b411130008","402881e76e20a2a9016e20b2b87b0007","402881e76e20a2a9016e20b201280006","2c9fa2dd6e39a37a016e3a871e810003","2c9fa2dd6e39a37a016e3a85fe0c0002","2c9fa2dd6e39a37a016e3a83da950001"};
    private int type = 0;
    @Override
    public String initTitleText() {
        return titles[type];
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        statefulLayout.showLoading();
        getData();
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initview() {
        type = getIntent().getIntExtra("type",0);
    }

    @Override
    protected boolean network() {
        return false;
    }

    private void load(String content) {
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
            PictureSelector.create(XzActivity.this)
                    .themeStyle(R.style.picture_default_style)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(0, media);
        }
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activity_xz;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.but_no,R.id.but_yes})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.but_no:{
                finish();
            }break;
            case R.id.but_yes:{
                switch (type){
                    case 0:{
                        Intent intent = new Intent(this, AccusationActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                    case 1:{
                        Intent intent = new Intent(this, CriminalComPlaintActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                    case 2:{
                        Intent intent = new Intent(this, StateCompensationActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                    case 3:{
                        Intent intent = new Intent(this, CivilActionActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                    case 4:{
                        Intent intent = new Intent(this, AdministrativeLitigationActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                    case 5:{
                        Intent intent = new Intent(this, OtherActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                    case 6:{
                        Intent intent = new Intent(this, UnderageActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                    case 7:{
                        Intent intent = new Intent(this, GyssxsActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                    case 8:{
                        Intent intent = new Intent(this, AddQzyjxActivity.class);
                        startActivity(intent);
                        finish();
                    }break;
                }
            }break;
        }
    }

    @Override
    protected Boolean status() {
        return false;
    }
    private void getData(){
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL2 + NetConstant.newsList)
                .add("orgId", "1")
                .add("page", "1")
                .add("type", "xz")
                .add("id",ids[type])
                .asParser(new SimpleParser<BaseArrayDataBean<JcxwdtListBean>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.isResult()){
                        load(s.getData().get(0).getContent());
                        statefulLayout.showContent();
                    }else {
                        statefulLayout.showError(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                statefulLayout.showLoading();
                                getData();
                            }
                        });
                    }
                }, throwable -> {
                    statefulLayout.showError(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            statefulLayout.showLoading();
                            getData();
                        }
                    });
                });
    }
}

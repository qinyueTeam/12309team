package com.qinyue.monitor.view;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.util.GlideEngine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/29
 * 描述:
 **/
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.time)
    TextView timeTv;
    private String content = "";
    private String title = "";
    private String time = "";
    @Override
    public String initTitleText() {
        return "详情";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        content = getIntent().getStringExtra("content");
        if (content==null){
            content = "";
        }
        title = getIntent().getStringExtra("title");
        if (title!=null){
            titleTv.setText(title);
        }
        time = getIntent().getStringExtra("time");
        if (time!=null){
            timeTv.setText("时间: "+time);
        }
        load();
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    private void load() {
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

    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activty_webview;
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
            PictureSelector.create(WebViewActivity.this)
                    .themeStyle(R.style.picture_default_style)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(0, media);
        }
    }
}

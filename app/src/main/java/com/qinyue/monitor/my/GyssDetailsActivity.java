package com.qinyue.monitor.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;


/**
 * 创建人:qinyue
 * 创建日期:2020/4/3
 * 描述:
 **/
public class GyssDetailsActivity extends BaseActivity {
    @BindView(R.id.dy_tv)
    TextView dyTv;
    @BindView(R.id.sjdy_tv)
    TextView sjdyTv;
    @BindView(R.id.ajlb_tv)
    TextView ajlbTv;
    @BindView(R.id.shly_tv)
    TextView shlyTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.lxdz_tv)
    TextView lxdzTv;
    @BindView(R.id.wflz_tv)
    TextView wflzTv;
    @BindView(R.id.dwmc_tv)
    TextView dwmcTv;
    @BindView(R.id.xzjglb_tv)
    TextView xzjglbTv;
    @BindView(R.id.wfxz_tv)
    TextView wfxzTv;
    @BindView(R.id.jyms_tv)
    TextView jymsTv;
    @BindView(R.id.img_tv)
    TextView imgTv;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.xsname_tv)
    TextView xsnameTv;
    @BindView(R.id.idcard_tv)
    TextView idcardTv;
    @BindView(R.id.dz_tv)
    TextView dzTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;

    private String id;
    private String title = "";
    private String qyStr[] = {"本省内某一区域", "本省内跨区域", "地域跨省"};
    private String ajlbStr[] = {"民事公益诉讼", "行政公益诉讼"};
    private String shlyStr[] = {"生态环境和资源保护", "食品安全", "药品安全", "国有土地使用权出让", "国有财产保护", "英烈保护", "其他"};
    private List<LocalMedia> photoList = new ArrayList<>();
    @Override
    public String initTitleText() {
        return title;
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (id==null){
            llStateful.showEmpty("内容丢失");
        }else {
            llStateful.showLoading();
            getDetails();
        }
    }

    @Override
    protected void initview() {
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_gyssdetails;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return null;
    }

    private void getDetails(){
        Map<String,String> map = new HashMap<>();
        map.put("username", UserUtils.getUserName());
        map.put("id", id);
        map.put("type", "3");
        map.put("petitionType","");
        String json = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY,json);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.petitionDetail)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data",aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        String aa = Base64Converter.AESDncode(TagConstant.AESKEY,s.getData());
                        GyssDetailsBean yyDetailsBean = JsonUtils.getInstance().gson.fromJson(aa, GyssDetailsBean.class);
                        dyTv.setText(yyDetailsBean.getAreaType()==0?"":qyStr[yyDetailsBean.getAreaType()-1]);
                        sjdyTv.setText(yyDetailsBean.getAreaRemark());
                        ajlbTv.setText(yyDetailsBean.getCaseType()==0?"":ajlbStr[yyDetailsBean.getCaseType()-1]);
                        shlyTv.setText(yyDetailsBean.getHarmRange()==0?"":shlyStr[yyDetailsBean.getHarmRange()-1]);
                        nameTv.setText(yyDetailsBean.getDefendantName());
                        lxdzTv.setText(yyDetailsBean.getDefendantAddress());
                        wflzTv.setText(yyDetailsBean.getBreakLawOrg());
                        dwmcTv.setText(yyDetailsBean.getUnitName());
                        xzjglbTv.setText(yyDetailsBean.getInvolveOrg());
                        wfxzTv.setText(yyDetailsBean.getBreakLawProperty());
                        jymsTv.setText(yyDetailsBean.getReflectRemark());
                        if (yyDetailsBean.getImgList()!=null&&yyDetailsBean.getImgList().size()>0){
                            String a = "";
                            for (int i = 0; i <yyDetailsBean.getImgList().size() ; i++) {
                                a =a+yyDetailsBean.getImgList().get(i).getAttachmentFileName()+",";
                                LocalMedia localMedia = new LocalMedia();
                                localMedia.setPath(TagConstant.BASEURL +yyDetailsBean.getImgList().get(i).getAttachmentFileUrl());
                                localMedia.setFileName(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                photoList.add(localMedia);
                            }
                            a = a.substring(0,a.length()-1);
                            imgTv.setText(a);
                            imgTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    PictureSelector.create(GyssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                            .loadImageEngine(GlideEngine.createGlideEngine())
                                            .openExternalPreview(0, photoList);
                                }
                            });
                        }
                        if (yyDetailsBean.getPicCreateTime()!=null&&yyDetailsBean.getPicCreateTime().contains("T")){
                            String[] ts = yyDetailsBean.getPicCreateTime().split("T");
                            if (ts.length>1){
                                timeTv.setText(ts[0]);
                            }
                        }
                        xsnameTv.setText(yyDetailsBean.getPlaintiffName());
                        idcardTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());
                        dzTv.setText(yyDetailsBean.getPlaintiffAddress());
                        phoneTv.setText(yyDetailsBean.getPlaintiffPhone());
                        llStateful.showContent();
                    }else {
                        llStateful.showError(s.getMessage(),new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                llStateful.showLoading();
                                getDetails();
                            }
                        });
                    }
                }, throwable -> {
                    llStateful.showError(throwable.getMessage(),new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            llStateful.showLoading();
                            getDetails();
                        }
                    });
                });
    }

}

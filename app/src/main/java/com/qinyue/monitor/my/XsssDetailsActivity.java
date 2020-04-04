package com.qinyue.monitor.my;

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
 * 创建日期:2020/4/4
 * 描述:
 **/
public class XsssDetailsActivity extends BaseActivity {

    @BindView(R.id.ssr_name_tv)
    TextView ssrNameTv;
    @BindView(R.id.ssr_yyagx_tv)
    TextView ssrYyagxTv;
    @BindView(R.id.ssr_sex_tv)
    TextView ssrSexTv;
    @BindView(R.id.ssr_zjlx_tv)
    TextView ssrZjlxTv;
    @BindView(R.id.ssr_zjh_tv)
    TextView ssrZjhTv;
    @BindView(R.id.ssr_gj_tv)
    TextView ssrGjTv;
    @BindView(R.id.ssr_gzdw_tv)
    TextView ssrGzdwTv;
    @BindView(R.id.ssr_zsd_tv)
    TextView ssrZsdTv;
    @BindView(R.id.ssr_lxdh_tv)
    TextView ssrLxdhTv;
    @BindView(R.id.ssr_email_tv)
    TextView ssrEmailTv;
    @BindView(R.id.bssr_name_tv)
    TextView bssrNameTv;
    @BindView(R.id.bssr_sex_tv)
    TextView bssrSexTv;
    @BindView(R.id.bssr_gj_tv)
    TextView bssrGjTv;
    @BindView(R.id.bssr_dwqc_tv)
    TextView bssrDwqcTv;
    @BindView(R.id.bssr_dwszd_tv)
    TextView bssrDwszdTv;
    @BindView(R.id.bssr_zw_tv)
    TextView bssrZwTv;
    @BindView(R.id.bssr_zj_tv)
    TextView bssrZjTv;
    @BindView(R.id.ss_afd_tv)
    TextView ssAfdTv;
    @BindView(R.id.ss_nr_tv)
    TextView ssNrTv;
    @BindView(R.id.ss_ssz_tv)
    TextView ssSszTv;
    @BindView(R.id.ss_pjws_tv)
    TextView ssPjwsTv;
    @BindView(R.id.ss_fcws_tv)
    TextView ssFcwsTv;
    @BindView(R.id.ss_zjcl_tv)
    TextView ssZjclTv;
    @BindView(R.id.ss_sfz_tv)
    TextView ssSfzTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    private String id;

    @Override
    public String initTitleText() {
        return "刑事申诉详情";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (id == null) {
            llStateful.showEmpty("内容丢失");
        } else {
            llStateful.showLoading();
            getDetails();
        }
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initview() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_xsssdetails;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }

    private void getDetails() {
        Map<String, String> map = new HashMap<>();
        map.put("username", UserUtils.getUserName());
        map.put("id", id);
        map.put("type", "1");
        map.put("petitionType", "");
        String json = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY, json);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.petitionDetail)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult() == 200) {
                        String aa = Base64Converter.AESDncode(TagConstant.AESKEY, s.getData());
                        KgDetailsBean yyDetailsBean = JsonUtils.getInstance().gson.fromJson(aa, KgDetailsBean.class);
                        ssrNameTv.setText(yyDetailsBean.getPlaintiffName());
                        ssrSexTv.setText(yyDetailsBean.getPlaintiffSex());
                        ssrGjTv.setText(yyDetailsBean.getPlaintiffNationality() == null ? "" : yyDetailsBean.getPlaintiffNationality().getMc());
                        ssrGzdwTv.setText(yyDetailsBean.getPlaintiffUnit());
                        ssrZsdTv.setText(yyDetailsBean.getPlaintiffResidence());
                        ssrLxdhTv.setText(yyDetailsBean.getPlaintiffPhone());
                        ssrEmailTv.setText(yyDetailsBean.getPlaintiffEmail());
                        ssrYyagxTv.setText(yyDetailsBean.getRelationship());
                        ssrZjlxTv.setText(yyDetailsBean.getPlaintiffCertificateType()==null?"":yyDetailsBean.getPlaintiffCertificateType().getMc());
                        ssrZjhTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());

                        bssrNameTv.setText(yyDetailsBean.getDefendantName());
                        bssrSexTv.setText(yyDetailsBean.getDefendantSex());
                        bssrGjTv.setText(yyDetailsBean.getDefendantNationality() == null ? "" : yyDetailsBean.getDefendantNationality().getMc());
                        bssrDwqcTv.setText(yyDetailsBean.getDefendantUintFullName());
                        bssrDwszdTv.setText(yyDetailsBean.getDefendantUnitLocation());
                        bssrZwTv.setText(yyDetailsBean.getDefendantDuty());
                        bssrZjTv.setText(yyDetailsBean.getDefendantRank() == null ? "" : yyDetailsBean.getDefendantRank().getMc());


                        ssAfdTv.setText(yyDetailsBean.getVenue()==null?"":yyDetailsBean.getVenue().getMc());
                        ssNrTv.setText(yyDetailsBean.getContent());
                        if (yyDetailsBean.getImgList() != null && yyDetailsBean.getImgList().size() > 0) {
                            for (int i = 0; i < yyDetailsBean.getImgList().size(); i++) {
                                switch (i) {
                                    case 0: {
                                        ssSszTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssSszTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(0).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(0).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(XsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 1: {
                                        ssPjwsTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssPjwsTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(1).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(1).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(XsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 2: {
                                        ssFcwsTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssFcwsTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(2).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(2).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(XsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 3: {
                                        ssZjclTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssZjclTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(3).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(3).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(XsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 4: {
                                        ssSfzTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssSfzTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(4).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(4).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(XsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                }


                            }
                        }
                        llStateful.showContent();
                    } else {
                        llStateful.showError(s.getMessage(), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                llStateful.showLoading();
                                getDetails();
                            }
                        });
                    }
                }, throwable -> {
                    llStateful.showError(throwable.getMessage(), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            llStateful.showLoading();
                            getDetails();
                        }
                    });
                });
    }

}

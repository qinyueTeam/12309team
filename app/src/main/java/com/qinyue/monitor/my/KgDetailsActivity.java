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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/4
 * 描述:
 **/
public class KgDetailsActivity extends BaseActivity {
    @BindView(R.id.kgr_name_tv)
    TextView kgrNameTv;
    @BindView(R.id.kgr_sex_tv)
    TextView kgrSexTv;
    @BindView(R.id.kgr_zjlx_tv)
    TextView kgrZjlxTv;
    @BindView(R.id.kgr_zjh_tv)
    TextView kgrZjhTv;
    @BindView(R.id.kgr_mz_tv)
    TextView kgrMzTv;
    @BindView(R.id.kgr_gj_tv)
    TextView kgrGjTv;
    @BindView(R.id.kgr_gzdw_tv)
    TextView kgrGzdwTv;
    @BindView(R.id.kgr_zsd_tv)
    TextView kgrZsdTv;
    @BindView(R.id.kgr_lxdh_tv)
    TextView kgrLxdhTv;
    @BindView(R.id.kgr_email_tv)
    TextView kgrEmailTv;
    @BindView(R.id.bkgr_name_tv)
    TextView bkgrNameTv;
    @BindView(R.id.bkgr_sex_tv)
    TextView bkgrSexTv;
    @BindView(R.id.bkgr_gj_tv)
    TextView bkgrGjTv;
    @BindView(R.id.bkgr_dwqc_tv)
    TextView bkgrDwqcTv;
    @BindView(R.id.bkgr_dwszd_tv)
    TextView bkgrDwszdTv;
    @BindView(R.id.bkgr_zw_tv)
    TextView bkgrZwTv;
    @BindView(R.id.bkgr_zj_tv)
    TextView bkgrZjTv;
    @BindView(R.id.bkgr_sf_tv)
    TextView bkgrSfTv;
    @BindView(R.id.bkgr_rddb_tv)
    TextView bkgrRddbTv;
    @BindView(R.id.bkgr_zxwy_tv)
    TextView bkgrZxwyTv;
    @BindView(R.id.kg_afd_tv)
    TextView kgAfdTv;
    @BindView(R.id.kg_nr_tv)
    TextView kgNrTv;
    @BindView(R.id.kg_kgcl_tv)
    TextView kgKgclTv;
    @BindView(R.id.kg_zjcl_tv)
    TextView kgZjclTv;
    @BindView(R.id.kg_qtcl_tv)
    TextView kgQtclTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    private String id;

    @Override
    public String initTitleText() {
        return "控告详情";
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
        return R.layout.activity_kgdetails;
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
                        kgrNameTv.setText(yyDetailsBean.getPlaintiffName());
                        kgrSexTv.setText(yyDetailsBean.getPlaintiffSex());
                        kgrZjlxTv.setText(yyDetailsBean.getPlaintiffCertificateType()==null?"":yyDetailsBean.getPlaintiffCertificateType().getMc());
                        kgrZjhTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());
                        kgrMzTv.setText(yyDetailsBean.getPlaintiffNation()==null?"":yyDetailsBean.getPlaintiffNation().getMc());
                        kgrGjTv.setText(yyDetailsBean.getPlaintiffNationality()==null?"":yyDetailsBean.getPlaintiffNationality().getMc());
                        kgrGzdwTv.setText(yyDetailsBean.getPlaintiffUnit());
                        kgrZsdTv.setText(yyDetailsBean.getPlaintiffResidence());
                        kgrLxdhTv.setText(yyDetailsBean.getPlaintiffPhone());
                        kgrEmailTv.setText(yyDetailsBean.getPlaintiffEmail());

                        bkgrNameTv.setText(yyDetailsBean.getDefendantName());
                        bkgrSexTv.setText(yyDetailsBean.getDefendantSex());
                        bkgrGjTv.setText(yyDetailsBean.getDefendantNationality()==null?"":yyDetailsBean.getDefendantNationality().getMc());
                        bkgrDwqcTv.setText(yyDetailsBean.getDefendantUintFullName());
                        bkgrDwszdTv.setText(yyDetailsBean.getDefendantUnitLocation());
                        bkgrZwTv.setText(yyDetailsBean.getDefendantDuty());
                        bkgrZjTv.setText(yyDetailsBean.getDefendantRank()==null?"":yyDetailsBean.getDefendantRank().getMc());
                        bkgrSfTv.setText(yyDetailsBean.getDefendantIdentity()==null?"":yyDetailsBean.getDefendantIdentity().getMc());
                        bkgrRddbTv.setText(yyDetailsBean.getDefendantNPC()==null?"":yyDetailsBean.getDefendantNPC().getMc());
                        bkgrZxwyTv.setText(yyDetailsBean.getDefendantCPPCC());


                        kgAfdTv.setText(yyDetailsBean.getVenue()==null?"":yyDetailsBean.getVenue().getMc());
                        kgNrTv.setText(yyDetailsBean.getContent());
                        if (yyDetailsBean.getImgList() != null && yyDetailsBean.getImgList().size() > 0) {
                            for (int i = 0; i < yyDetailsBean.getImgList().size(); i++) {
                                switch (i) {
                                    case 0: {
                                        kgKgclTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        kgKgclTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(0).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(0).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(KgDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 1: {
                                        kgZjclTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        kgZjclTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(1).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(1).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(KgDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 2: {
                                        kgQtclTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        kgQtclTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(2).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(2).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(KgDetailsActivity.this).themeStyle(R.style.picture_default_style)
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

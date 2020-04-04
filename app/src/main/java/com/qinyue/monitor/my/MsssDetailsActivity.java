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
public class MsssDetailsActivity extends BaseActivity {


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
    @BindView(R.id.ssr_jsd_tv)
    TextView ssrJsdTv;
    @BindView(R.id.ssr_lxdh_tv)
    TextView ssrLxdhTv;
    @BindView(R.id.ssr_email_tv)
    TextView ssrEmailTv;
    @BindView(R.id.ssr_fddbrname_tv)
    TextView ssrFddbrnameTv;
    @BindView(R.id.ssr_fddbrzw_tv)
    TextView ssrFddbrzwTv;
    @BindView(R.id.ssr_dlrname_tv)
    TextView ssrDlrnameTv;
    @BindView(R.id.ssr_dlrzjlx_tv)
    TextView ssrDlrzjlxTv;
    @BindView(R.id.ssr_dlrzjhm_tv)
    TextView ssrDlrzjhmTv;
    @BindView(R.id.ssr_dlrdw_tv)
    TextView ssrDlrdwTv;
    @BindView(R.id.ssr_sddz_tv)
    TextView ssrSddzTv;
    @BindView(R.id.bssr_name_tv)
    TextView bssrNameTv;
    @BindView(R.id.bssr_yyagx_tv)
    TextView bssrYyagxTv;
    @BindView(R.id.bssr_sex_tv)
    TextView bssrSexTv;
    @BindView(R.id.bssr_gj_tv)
    TextView bssrGjTv;
    @BindView(R.id.bssr_dwqc_tv)
    TextView bssrDwqcTv;
    @BindView(R.id.bssr_dwszd_tv)
    TextView bssrDwszdTv;
    @BindView(R.id.bssr_sddz_tv)
    TextView bssrSddzTv;
    @BindView(R.id.ss_afd_tv)
    TextView ssAfdTv;
    @BindView(R.id.ss_yfy_tv)
    TextView ssYfyTv;
    @BindView(R.id.ss_ytime_tv)
    TextView ssYtimeTv;
    @BindView(R.id.ss_ycpry_tv)
    TextView ssYcpryTv;
    @BindView(R.id.ss_yws_tv)
    TextView ssYwsTv;
    @BindView(R.id.ss_erfy_tv)
    TextView ssErfyTv;
    @BindView(R.id.ss_ertime_tv)
    TextView ssErtimeTv;
    @BindView(R.id.ss_ercpry_tv)
    TextView ssErcpryTv;
    @BindView(R.id.ss_erws_tv)
    TextView ssErwsTv;
    @BindView(R.id.ss_zisfy_tv)
    TextView ssZisfyTv;
    @BindView(R.id.ss_zistime_tv)
    TextView ssZistimeTv;
    @BindView(R.id.ss_ziscpry_tv)
    TextView ssZiscpryTv;
    @BindView(R.id.ss_zisws_tv)
    TextView ssZiswsTv;
    @BindView(R.id.ss_zsfy_tv)
    TextView ssZsfyTv;
    @BindView(R.id.ss_zstime_tv)
    TextView ssZstimeTv;
    @BindView(R.id.ss_zscpry_tv)
    TextView ssZscpryTv;
    @BindView(R.id.ss_zsws_tv)
    TextView ssZswsTv;
    @BindView(R.id.ss_fyslzsrq_tv)
    TextView ssFyslzsrqTv;
    @BindView(R.id.ss_fyslzswsh_tv)
    TextView ssFyslzswshTv;
    @BindView(R.id.ss_nr_tv)
    TextView ssNrTv;
    @BindView(R.id.ss_sqs_tv)
    TextView ssSqsTv;
    @BindView(R.id.ss_flws_tv)
    TextView ssFlwsTv;
    @BindView(R.id.ss_sfz_tv)
    TextView ssSfzTv;
    @BindView(R.id.ss_qtcl_tv)
    TextView ssQtclTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    private String id;
    private String title = "";

    @Override
    public String initTitleText() {
        return "title";
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
        title = getIntent().getStringExtra("title");
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_msssdetails;
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
                        ssrJsdTv.setText(yyDetailsBean.getPlaintiffResidence());
                        ssrLxdhTv.setText(yyDetailsBean.getPlaintiffPhone());
                        ssrEmailTv.setText(yyDetailsBean.getPlaintiffEmail());
                        ssrYyagxTv.setText(yyDetailsBean.getRelationship());
                        ssrZjlxTv.setText(yyDetailsBean.getPlaintiffCertificateType() == null ? "" : yyDetailsBean.getPlaintiffCertificateType().getMc());
                        ssrZjhTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());
                        ssrDlrnameTv.setText(yyDetailsBean.getAgentName());
                        ssrDlrzjhmTv.setText(yyDetailsBean.getAgentCertificateNumber());
                        ssrDlrdwTv.setText(yyDetailsBean.getAgentUnit());
                        ssrDlrzjlxTv.setText(yyDetailsBean.getAgentCertificateType() == null ? "" : yyDetailsBean.getAgentCertificateType().getMc());
                        ssrFddbrnameTv.setText(yyDetailsBean.getCorporationName());
                        ssrFddbrzwTv.setText(yyDetailsBean.getCorporationDefendantDuty());
                        ssrSddzTv.setText(yyDetailsBean.getDeliveryAddress());

                        bssrNameTv.setText(yyDetailsBean.getDefendantName());
                        bssrSexTv.setText(yyDetailsBean.getDefendantSex());
                        bssrGjTv.setText(yyDetailsBean.getDefendantNationality() == null ? "" : yyDetailsBean.getDefendantNationality().getMc());
                        bssrDwqcTv.setText(yyDetailsBean.getDefendantUintFullName());
                        bssrDwszdTv.setText(yyDetailsBean.getDefendantUnitLocation());
                        bssrSddzTv.setText(yyDetailsBean.getDeliveryAddress2());
                        bssrYyagxTv.setText(yyDetailsBean.getRelationship2());


                        ssAfdTv.setText(yyDetailsBean.getVenue() == null ? "" : yyDetailsBean.getVenue().getMc());

                        ssYfyTv.setText(yyDetailsBean.getCourt());
                        ssYtimeTv.setText(yyDetailsBean.getRefereeDate());
                        ssYcpryTv.setText(yyDetailsBean.getReferee());
                        ssYwsTv.setText(yyDetailsBean.getDocumentNumber());

                        ssErfyTv.setText(yyDetailsBean.getCourt2());
                        ssErtimeTv.setText(yyDetailsBean.getRefereeDate2());
                        ssErcpryTv.setText(yyDetailsBean.getReferee2());
                        ssErwsTv.setText(yyDetailsBean.getDocumentNumber2());

                        ssZisfyTv.setText(yyDetailsBean.getCourt3());
                        ssZistimeTv.setText(yyDetailsBean.getRefereeDate3());
                        ssZiscpryTv.setText(yyDetailsBean.getReferee3());
                        ssZiswsTv.setText(yyDetailsBean.getDocumentNumber3());

                        ssZsfyTv.setText(yyDetailsBean.getCourt4());
                        ssZstimeTv.setText(yyDetailsBean.getRefereeDate4());
                        ssZscpryTv.setText(yyDetailsBean.getReferee4());
                        ssZswsTv.setText(yyDetailsBean.getDocumentNumber4());

                        ssFyslzsrqTv.setText(yyDetailsBean.getRetrialDate());
                        ssFyslzswshTv.setText(yyDetailsBean.getDocumentNumber5());
                        ssNrTv.setText(yyDetailsBean.getContent());
                        if (yyDetailsBean.getImgList() != null && yyDetailsBean.getImgList().size() > 0) {
                            for (int i = 0; i < yyDetailsBean.getImgList().size(); i++) {
                                switch (i) {
                                    case 0: {
                                        ssSqsTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssSqsTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(0).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(0).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(MsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 1: {
                                        ssFlwsTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssFlwsTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(1).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(1).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(MsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 2: {
                                        ssSfzTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssSfzTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(2).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(2).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(MsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 3: {
                                        ssQtclTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssQtclTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(3).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(3).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(MsssDetailsActivity.this).themeStyle(R.style.picture_default_style)
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

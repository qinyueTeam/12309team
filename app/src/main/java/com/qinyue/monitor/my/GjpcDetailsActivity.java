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
public class GjpcDetailsActivity extends BaseActivity {

    @BindView(R.id.pc_name_tv)
    TextView pcNameTv;
    @BindView(R.id.pc_sex_tv)
    TextView pcSexTv;
    @BindView(R.id.pc_zjlx_tv)
    TextView pcZjlxTv;
    @BindView(R.id.pc_zjh_tv)
    TextView pcZjhTv;
    @BindView(R.id.pc_mz_tv)
    TextView pcMzTv;
    @BindView(R.id.pc_gj_tv)
    TextView pcGjTv;
    @BindView(R.id.pc_gzdw_tv)
    TextView pcGzdwTv;
    @BindView(R.id.pc_zsd_tv)
    TextView pcZsdTv;
    @BindView(R.id.pc_lxdh_tv)
    TextView pcLxdhTv;
    @BindView(R.id.pc_email_tv)
    TextView pcEmailTv;
    @BindView(R.id.pc_dlrname_tv)
    TextView pcDlrnameTv;
    @BindView(R.id.pc_dlrzjlx_tv)
    TextView pcDlrzjlxTv;
    @BindView(R.id.pc_dlrzjhm_tv)
    TextView pcDlrzjhmTv;
    @BindView(R.id.pc_dlrdw_tv)
    TextView pcDlrdwTv;
    @BindView(R.id.sl_afd_tv)
    TextView slAfdTv;
    @BindView(R.id.sl_ywjg_tv)
    TextView slYwjgTv;
    @BindView(R.id.sl_nr_tv)
    TextView slNrTv;
    @BindView(R.id.sl_pcsqs_tv)
    TextView slPcsqsTv;
    @BindView(R.id.sl_flws_tv)
    TextView slFlwsTv;
    @BindView(R.id.sl_sfz_tv)
    TextView slSfzTv;
    @BindView(R.id.sl_qtws_tv)
    TextView slQtwsTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    private String id;

    @Override
    public String initTitleText() {
        return "国家赔偿详情";
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
        return R.layout.activity_gjpcdetails;
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
                        pcNameTv.setText(yyDetailsBean.getPlaintiffName());
                        pcSexTv.setText(yyDetailsBean.getPlaintiffSex());
                        pcMzTv.setText(yyDetailsBean.getPlaintiffNation() == null ? "" : yyDetailsBean.getPlaintiffNation().getMc());
                        pcGjTv.setText(yyDetailsBean.getPlaintiffNationality() == null ? "" : yyDetailsBean.getPlaintiffNationality().getMc());
                        pcGzdwTv.setText(yyDetailsBean.getPlaintiffUnit());
                        pcZsdTv.setText(yyDetailsBean.getPlaintiffResidence());
                        pcLxdhTv.setText(yyDetailsBean.getPlaintiffPhone());
                        pcEmailTv.setText(yyDetailsBean.getPlaintiffEmail());
                        pcZjlxTv.setText(yyDetailsBean.getPlaintiffCertificateType() == null ? "" : yyDetailsBean.getPlaintiffCertificateType().getMc());
                        pcZjhTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());
                        pcDlrnameTv.setText(yyDetailsBean.getAgentName());
                        pcDlrzjhmTv.setText(yyDetailsBean.getAgentCertificateNumber());
                        pcDlrdwTv.setText(yyDetailsBean.getAgentUnit());
                        pcDlrzjlxTv.setText(yyDetailsBean.getAgentCertificateType()==null?"":yyDetailsBean.getAgentCertificateType().getMc());

                        slAfdTv.setText(yyDetailsBean.getVenue()==null?"":yyDetailsBean.getVenue().getMc());
                        slYwjgTv.setText(yyDetailsBean.getOffice());
                        slNrTv.setText(yyDetailsBean.getContent());
                        if (yyDetailsBean.getImgList() != null && yyDetailsBean.getImgList().size() > 0) {
                            for (int i = 0; i < yyDetailsBean.getImgList().size(); i++) {
                                switch (i) {
                                    case 0: {
                                        slPcsqsTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        slPcsqsTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(0).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(0).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(GjpcDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 1: {
                                        slFlwsTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        slFlwsTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(1).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(1).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(GjpcDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 2: {
                                        slSfzTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        slSfzTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(2).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(2).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(GjpcDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 3: {
                                        slQtwsTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        slQtwsTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(3).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(3).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(GjpcDetailsActivity.this).themeStyle(R.style.picture_default_style)
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

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
public class WcnXsssActivity extends BaseActivity {


    @BindView(R.id.wcn_name_tv)
    TextView wcnNameTv;
    @BindView(R.id.wcn_sex_tv)
    TextView wcnSexTv;
    @BindView(R.id.wcn_cym_tv)
    TextView wcnCymTv;
    @BindView(R.id.wcn_ch_tv)
    TextView wcnChTv;
    @BindView(R.id.wcn_zjlx_tv)
    TextView wcnZjlxTv;
    @BindView(R.id.wcn_zjh_tv)
    TextView wcnZjhTv;
    @BindView(R.id.wcn_csrq_tv)
    TextView wcnCsrqTv;
    @BindView(R.id.wcn_mz_tv)
    TextView wcnMzTv;
    @BindView(R.id.wcn_gj_tv)
    TextView wcnGjTv;
    @BindView(R.id.wcn_hjszd_tv)
    TextView wcnHjszdTv;
    @BindView(R.id.wcn_zsd_tv)
    TextView wcnZsdTv;
    @BindView(R.id.wcn_zsdxx_tv)
    TextView wcnZsdxxTv;
    @BindView(R.id.wcn_gzdw_tv)
    TextView wcnGzdwTv;
    @BindView(R.id.wcn_gzdwszd_tv)
    TextView wcnGzdwszdTv;
    @BindView(R.id.wcn_fddlr_tv)
    TextView wcnFddlrTv;
    @BindView(R.id.wcn_jhqk_tv)
    TextView wcnJhqkTv;
    @BindView(R.id.wcn_afnl_tv)
    TextView wcnAfnlTv;
    @BindView(R.id.ssr_name_tv)
    TextView ssrNameTv;
    @BindView(R.id.ssr_gx_tv)
    TextView ssrGxTv;
    @BindView(R.id.ssr_sex_tv)
    TextView ssrSexTv;
    @BindView(R.id.ssr_zjlx_tv)
    TextView ssrZjlxTv;
    @BindView(R.id.ssr_zjh_tv)
    TextView ssrZjhTv;
    @BindView(R.id.ssr_mz_tv)
    TextView ssrMzTv;
    @BindView(R.id.ssr_gj_tv)
    TextView ssrGjTv;
    @BindView(R.id.ssr_zw_tv)
    TextView ssrZwTv;
    @BindView(R.id.ssr_dw_tv)
    TextView ssrDwTv;
    @BindView(R.id.ssr_szdq_tv)
    TextView ssrSzdqTv;
    @BindView(R.id.ssr_lxdh_tv)
    TextView ssrLxdhTv;
    @BindView(R.id.bssr_name_tv)
    TextView bssrNameTv;
    @BindView(R.id.bssr_sex_tv)
    TextView bssrSexTv;
    @BindView(R.id.bssr_zw_tv)
    TextView bssrZwTv;
    @BindView(R.id.bssr_sf_tv)
    TextView bssrSfTv;
    @BindView(R.id.bssr_qtsf_tv)
    TextView bssrQtsfTv;
    @BindView(R.id.bssr_hjszd_tv)
    TextView bssrHjszdTv;
    @BindView(R.id.bssr_zz_tv)
    TextView bssrZzTv;
    @BindView(R.id.bssr_gzdw_tv)
    TextView bssrGzdwTv;
    @BindView(R.id.ss_jglx_tv)
    TextView ssJglxTv;
    @BindView(R.id.ss_jgmc_tv)
    TextView ssJgmcTv;
    @BindView(R.id.ss_lb_tv)
    TextView ssLbTv;
    @BindView(R.id.ss_time_tv)
    TextView ssTimeTv;
    @BindView(R.id.ss_fsdd_tv)
    TextView ssFsddTv;
    @BindView(R.id.ss_yaay_tv)
    TextView ssYaayTv;
    @BindView(R.id.ss_ssnr_tv)
    TextView ssSsnrTv;
    @BindView(R.id.ss_ssz_tv)
    TextView ssSszTv;
    @BindView(R.id.ss_cpws_tv)
    TextView ssCpwsTv;
    @BindView(R.id.ss_fcws_tv)
    TextView ssFcwsTv;
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
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
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
    protected void initview() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_wcnxsss;
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
        map.put("type", "2");
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
                        WcnXsssBean yyDetailsBean = JsonUtils.getInstance().gson.fromJson(aa, WcnXsssBean.class);
                        wcnNameTv.setText(yyDetailsBean.getJuvenileName());
                        wcnSexTv.setText(yyDetailsBean.getJuvenileSex());
                        wcnChTv.setText(yyDetailsBean.getJuvenileNickname());
                        wcnZjlxTv.setText(yyDetailsBean.getJuvenileCertificateType());
                        wcnZjhTv.setText(yyDetailsBean.getJuvenileCertificateNumber());
                        if (yyDetailsBean.getJuvenileBirthday()!=null&&yyDetailsBean.getJuvenileBirthday().contains("T")){
                            String[] ts = yyDetailsBean.getJuvenileBirthday().split("T");
                            if (ts.length>1){
                                wcnCsrqTv.setText(ts[0]);
                            }
                        }
                        wcnMzTv.setText(yyDetailsBean.getJuvenileNation());
                        wcnGjTv.setText(yyDetailsBean.getJuvenileNationality());
                        wcnHjszdTv.setText(yyDetailsBean.getJuvenileDomicile());
                        wcnZsdTv.setText(yyDetailsBean.getJuvenileResidence());
                        wcnZsdxxTv.setText(yyDetailsBean.getJuvenileAddress());
                        wcnGzdwTv.setText(yyDetailsBean.getJuvenileSchool());
                        wcnGzdwszdTv.setText(yyDetailsBean.getJuvenileSchoolAddress());
                        wcnFddlrTv.setText(yyDetailsBean.getJuvenileAgent());
                        wcnJhqkTv.setText(yyDetailsBean.getJuvenileGuardState());
                        wcnAfnlTv.setText(yyDetailsBean.getJuvenileAge());

                        ssrNameTv.setText(yyDetailsBean.getPlaintiffName());
                        ssrGxTv.setText(yyDetailsBean.getRelationship());
                        ssrSexTv.setText(yyDetailsBean.getPlaintiffSex());
                        ssrZjlxTv.setText(yyDetailsBean.getPlaintiffCertificateType());
                        ssrZjhTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());
                        ssrMzTv.setText(yyDetailsBean.getPlaintiffNation());
                        ssrGjTv.setText(yyDetailsBean.getPlaintiffNationality());
                        ssrZwTv.setText(yyDetailsBean.getPlaintiffDuty());
                        ssrSzdqTv.setText(yyDetailsBean.getPlaintiffArea());
                        ssrDwTv.setText(yyDetailsBean.getPlaintiffResidence());
                        ssrLxdhTv.setText(yyDetailsBean.getPlaintiffPhone());

                        bssrNameTv.setText(yyDetailsBean.getDefendantName());
                        bssrSexTv.setText(yyDetailsBean.getDefendantSex());
                        bssrZwTv.setText(yyDetailsBean.getDefendantDuty());
                        bssrSfTv.setText(yyDetailsBean.getDefendantIdentity());
                        bssrQtsfTv.setText(yyDetailsBean.getDefendantOtherIdentity());
                        bssrHjszdTv.setText(yyDetailsBean.getDefendantDomicile());
                        bssrZzTv.setText(yyDetailsBean.getDefendantAddress());
                        bssrGzdwTv.setText(yyDetailsBean.getDefendantUnit());
//
                        ssJglxTv.setText(yyDetailsBean.getCaseOrgType());
                        ssJgmcTv.setText(yyDetailsBean.getCaseOrgName());
                        ssLbTv.setText(yyDetailsBean.getAppealType());
                        if (yyDetailsBean.getCaseDate()!=null&&yyDetailsBean.getCaseDate().contains("T")){
                            String[] ts = yyDetailsBean.getCaseDate().split("T");
                            if (ts.length>1){
                                ssTimeTv.setText(ts[0]);
                            }
                        }
                        ssFsddTv.setText(yyDetailsBean.getCaseArea());
                        ssYaayTv.setText(yyDetailsBean.getCaseReason());
                        ssSsnrTv.setText(yyDetailsBean.getContent());
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
                                                PictureSelector.create(WcnXsssActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 1: {
                                        ssCpwsTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssCpwsTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(1).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(1).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(WcnXsssActivity.this).themeStyle(R.style.picture_default_style)
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
                                                PictureSelector.create(WcnXsssActivity.this).themeStyle(R.style.picture_default_style)
                                                        .loadImageEngine(GlideEngine.createGlideEngine())
                                                        .openExternalPreview(0, photo);
                                            }
                                        });
                                    }
                                    break;
                                    case 3: {
                                        ssSfzTv.setText(yyDetailsBean.getImgList().get(i).getAttachmentFileName());
                                        ssSfzTv.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                List<LocalMedia> photo = new ArrayList<>();
                                                LocalMedia localMedia = new LocalMedia();
                                                localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(3).getAttachmentFileUrl());
                                                localMedia.setFileName(yyDetailsBean.getImgList().get(3).getAttachmentFileName());
                                                photo.add(localMedia);
                                                PictureSelector.create(WcnXsssActivity.this).themeStyle(R.style.picture_default_style)
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

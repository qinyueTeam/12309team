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
public class WcnKgActivity extends BaseActivity {
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
    @BindView(R.id.kgr_name_tv)
    TextView kgrNameTv;
    @BindView(R.id.kgr_gx_tv)
    TextView kgrGxTv;
    @BindView(R.id.kgr_sex_tv)
    TextView kgrSexTv;
    @BindView(R.id.kgr_zjlx_tv)
    TextView kgrZjlxTv;
    @BindView(R.id.kgr_zjh_tv)
    TextView kgrZjhTv;
    @BindView(R.id.kgr_gj_tv)
    TextView kgrGjTv;
    @BindView(R.id.kgr_szdq_tv)
    TextView kgrSzdqTv;
    @BindView(R.id.kgr_dw_tv)
    TextView kgrDwTv;
    @BindView(R.id.kgr_sf_tv)
    TextView kgrSfTv;
    @BindView(R.id.kgr_lxdh_tv)
    TextView kgrLxdhTv;
    @BindView(R.id.bkgr_name_tv)
    TextView bkgrNameTv;
    @BindView(R.id.bkgr_sex_tv)
    TextView bkgrSexTv;
    @BindView(R.id.bkgr_zw_tv)
    TextView bkgrZwTv;
    @BindView(R.id.bkgr_sf_tv)
    TextView bkgrSfTv;
    @BindView(R.id.bkgr_qtsf_tv)
    TextView bkgrQtsfTv;
    @BindView(R.id.bkgr_hjszd_tv)
    TextView bkgrHjszdTv;
    @BindView(R.id.bkgr_zz_tv)
    TextView bkgrZzTv;
    @BindView(R.id.bkgr_gzdw_tv)
    TextView bkgrGzdwTv;
    @BindView(R.id.kg_afd_tv)
    TextView kgAfdTv;
    @BindView(R.id.kg_time_tv)
    TextView kgTimeTv;
    @BindView(R.id.kg_nr_tv)
    TextView kgNrTv;
    @BindView(R.id.kg_kgcl_tv)
    TextView kgKgclTv;
    @BindView(R.id.kg_zjcl_tv)
    TextView kgZjclTv;
    @BindView(R.id.kg_qtcl_tv)
    TextView kgQtclTv;
    @BindView(R.id.kg_xsay_tv)
    TextView kgXsayTv;
    @BindView(R.id.kg_qtxsay_tv)
    TextView kgQtxsayTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;

    private String id;


    @Override
    public String initTitleText() {
        return "控告详情";
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
        return R.layout.activity_wcnkg;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return null;
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
                        WcnKgBean yyDetailsBean = JsonUtils.getInstance().gson.fromJson(aa, WcnKgBean.class);
                        wcnNameTv.setText(yyDetailsBean.getJuvenileName());
                        wcnSexTv.setText(yyDetailsBean.getJuvenileSex());
                        wcnChTv.setText(yyDetailsBean.getJuvenileNickname());
                        wcnZjlxTv.setText(yyDetailsBean.getJuvenileCertificateType());
                        wcnZjhTv.setText(yyDetailsBean.getJuvenileCertificateNumber());
                        wcnCsrqTv.setText(yyDetailsBean.getJuvenileBirthday());
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

                        kgrNameTv.setText(yyDetailsBean.getPlaintiffName());
                        kgrGxTv.setText(yyDetailsBean.getRelationship());
                        kgrSexTv.setText(yyDetailsBean.getPlaintiffSex());
                        kgrZjlxTv.setText(yyDetailsBean.getPlaintiffCertificateType());
                        kgrZjhTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());
                        kgrGjTv.setText(yyDetailsBean.getPlaintiffNationality());
                        kgrSzdqTv.setText(yyDetailsBean.getPlaintiffArea());
                        kgrDwTv.setText(yyDetailsBean.getPlaintiffResidence());
                        kgrSfTv.setText(yyDetailsBean.getPlaintiffIdentity());
                        kgrLxdhTv.setText(yyDetailsBean.getPlaintiffPhone());

                        bkgrNameTv.setText(yyDetailsBean.getDefendantName());
                        bkgrSexTv.setText(yyDetailsBean.getDefendantSex());
                        bkgrZwTv.setText(yyDetailsBean.getDefendantDuty());
                        bkgrSfTv.setText(yyDetailsBean.getDefendantIdentity());
                        bkgrQtsfTv.setText(yyDetailsBean.getDefendantOtherIdentity());
                        bkgrHjszdTv.setText(yyDetailsBean.getDefendantDomicile());
                        bkgrZzTv.setText(yyDetailsBean.getDefendantAddress());
                        bkgrGzdwTv.setText(yyDetailsBean.getDefendantUnit());

                        kgAfdTv.setText(yyDetailsBean.getCaseArea());
                        kgTimeTv.setText(yyDetailsBean.getCaseDate());
                        kgNrTv.setText(yyDetailsBean.getContent());
                        kgXsayTv.setText(yyDetailsBean.getCaseReason());
                        kgQtxsayTv.setText(yyDetailsBean.getAccuseOtherReason());
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
                                                PictureSelector.create(WcnKgActivity.this).themeStyle(R.style.picture_default_style)
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
                                                PictureSelector.create(WcnKgActivity.this).themeStyle(R.style.picture_default_style)
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
                                                PictureSelector.create(WcnKgActivity.this).themeStyle(R.style.picture_default_style)
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

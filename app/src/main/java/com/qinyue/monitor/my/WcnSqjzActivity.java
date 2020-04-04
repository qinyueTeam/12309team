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
public class WcnSqjzActivity extends BaseActivity {

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
    @BindView(R.id.sqr_name_tv)
    TextView sqrNameTv;
    @BindView(R.id.sqr_gx_tv)
    TextView sqrGxTv;
    @BindView(R.id.sqr_sex_tv)
    TextView sqrSexTv;
    @BindView(R.id.sqr_zjlx_tv)
    TextView sqrZjlxTv;
    @BindView(R.id.sqr_zjh_tv)
    TextView sqrZjhTv;
    @BindView(R.id.sqr_gj_tv)
    TextView sqrGjTv;
    @BindView(R.id.sqr_szdq_tv)
    TextView sqrSzdqTv;
    @BindView(R.id.sqr_dw_tv)
    TextView sqrDwTv;
    @BindView(R.id.sqr_zw_tv)
    TextView sqrZwTv;
    @BindView(R.id.sqr_lxdh_tv)
    TextView sqrLxdhTv;
    @BindView(R.id.sq_jzrlb_tv)
    TextView sqJzrlbTv;
    @BindView(R.id.sq_yaay_tv)
    TextView sqYaayTv;
    @BindView(R.id.sq_yaaqzy_tv)
    TextView sqYaaqzyTv;
    @BindView(R.id.sq_yjcjgmc_tv)
    TextView sqYjcjgmcTv;
    @BindView(R.id.sq_ly_tv)
    TextView sqLyTv;
    @BindView(R.id.sq_jzlb_tv)
    TextView sqJzlbTv;
    @BindView(R.id.sq_jznr_tv)
    TextView sqJznrTv;
    @BindView(R.id.sq_xgcl_tv)
    TextView sqXgclTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    private String id;
    @Override
    public String initTitleText() {
        return "申请救助详情";
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

    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_wcnsqjz;
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("id");
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
                        WcnSqjzBean yyDetailsBean = JsonUtils.getInstance().gson.fromJson(aa, WcnSqjzBean.class);
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

                        sqrNameTv.setText(yyDetailsBean.getPlaintiffName());
                        sqrGxTv.setText(yyDetailsBean.getRelationship());
                        sqrSexTv.setText(yyDetailsBean.getPlaintiffSex());
                        sqrZjlxTv.setText(yyDetailsBean.getPlaintiffCertificateType());
                        sqrZjhTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());
                        sqrGjTv.setText(yyDetailsBean.getPlaintiffNationality());
                        sqrSzdqTv.setText(yyDetailsBean.getPlaintiffArea());
                        sqrDwTv.setText(yyDetailsBean.getPlaintiffResidence());
                        sqrZwTv.setText(yyDetailsBean.getPlaintiffDuty());
                        sqrLxdhTv.setText(yyDetailsBean.getPlaintiffPhone());

                        sqJzrlbTv.setText(yyDetailsBean.getSalveeType());
                        sqYaayTv.setText(yyDetailsBean.getCaseReason());
                        sqYaaqzyTv.setText(yyDetailsBean.getCaseRemark());
                        sqYjcjgmcTv.setText(yyDetailsBean.getCaseOrgSrcName());
                        sqLyTv.setText(yyDetailsBean.getDecReason());
                        sqJzlbTv.setText(yyDetailsBean.getHelpType());
                        sqJznrTv.setText(yyDetailsBean.getContent());
                        if (yyDetailsBean.getImgList() != null && yyDetailsBean.getImgList().size() > 0) {
                            sqXgclTv.setText(yyDetailsBean.getImgList().get(0).getAttachmentFileName());
                            sqXgclTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    List<LocalMedia> photo = new ArrayList<>();
                                    LocalMedia localMedia = new LocalMedia();
                                    localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(0).getAttachmentFileUrl());
                                    localMedia.setFileName(yyDetailsBean.getImgList().get(0).getAttachmentFileName());
                                    photo.add(localMedia);
                                    PictureSelector.create(WcnSqjzActivity.this).themeStyle(R.style.picture_default_style)
                                            .loadImageEngine(GlideEngine.createGlideEngine())
                                            .openExternalPreview(0, photo);
                                }
                            });
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

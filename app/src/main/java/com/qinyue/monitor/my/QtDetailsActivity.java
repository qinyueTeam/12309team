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
public class QtDetailsActivity extends BaseActivity {
    @BindView(R.id.xfr_name_tv)
    TextView xfrNameTv;
    @BindView(R.id.xfr_sex_tv)
    TextView xfrSexTv;
    @BindView(R.id.xfr_zjlx_tv)
    TextView xfrZjlxTv;
    @BindView(R.id.xfr_zjh_tv)
    TextView xfrZjhTv;
    @BindView(R.id.xfr_mz_tv)
    TextView xfrMzTv;
    @BindView(R.id.xfr_gj_tv)
    TextView xfrGjTv;
    @BindView(R.id.xfr_lxdh_tv)
    TextView xfrLxdhTv;
    @BindView(R.id.xfr_email_tv)
    TextView xfrEmailTv;
    @BindView(R.id.xfr_gzdw_tv)
    TextView xfrGzdwTv;
    @BindView(R.id.xfr_zsd_tv)
    TextView xfrZsdTv;
    @BindView(R.id.bxfr_name_tv)
    TextView bxfrNameTv;
    @BindView(R.id.bxfr_sex_tv)
    TextView bxfrSexTv;
    @BindView(R.id.bxfr_gj_tv)
    TextView bxfrGjTv;
    @BindView(R.id.bxfr_dwqc_tv)
    TextView bxfrDwqcTv;
    @BindView(R.id.bxfr_dwszd_tv)
    TextView bxfrDwszdTv;
    @BindView(R.id.xf_afd_tv)
    TextView xfAfdTv;
    @BindView(R.id.xf_nr_tv)
    TextView xfNrTv;
    @BindView(R.id.xf_qtcl_tv)
    TextView xfQtclTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    private String id;

    @Override
    public String initTitleText() {
        return "其他信访详情";
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
        return R.layout.activity_qtdetails;
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
                        xfrNameTv.setText(yyDetailsBean.getPlaintiffName());
                        xfrSexTv.setText(yyDetailsBean.getPlaintiffSex());
                        xfrZjlxTv.setText(yyDetailsBean.getPlaintiffCertificateType() == null ? "" : yyDetailsBean.getPlaintiffCertificateType().getMc());
                        xfrZjhTv.setText(yyDetailsBean.getPlaintiffCertificateNumber());
                        xfrMzTv.setText(yyDetailsBean.getPlaintiffNation() == null ? "" : yyDetailsBean.getPlaintiffNation().getMc());
                        xfrGjTv.setText(yyDetailsBean.getPlaintiffNationality() == null ? "" : yyDetailsBean.getPlaintiffNationality().getMc());
                        xfrGzdwTv.setText(yyDetailsBean.getPlaintiffUnit());
                        xfrZsdTv.setText(yyDetailsBean.getPlaintiffResidence());
                        xfrLxdhTv.setText(yyDetailsBean.getPlaintiffPhone());
                        xfrEmailTv.setText(yyDetailsBean.getPlaintiffEmail());

                        bxfrNameTv.setText(yyDetailsBean.getDefendantName());
                        bxfrSexTv.setText(yyDetailsBean.getDefendantSex());
                        bxfrGjTv.setText(yyDetailsBean.getDefendantNationality() == null ? "" : yyDetailsBean.getDefendantNationality().getMc());
                        bxfrDwqcTv.setText(yyDetailsBean.getDefendantUintFullName());
                        bxfrDwszdTv.setText(yyDetailsBean.getDefendantUnitLocation());


                        xfAfdTv.setText(yyDetailsBean.getVenue() == null ? "" : yyDetailsBean.getVenue().getMc());
                        xfNrTv.setText(yyDetailsBean.getContent());
                        if (yyDetailsBean.getImgList() != null && yyDetailsBean.getImgList().size() > 0) {
                            xfQtclTv.setText(yyDetailsBean.getImgList().get(0).getAttachmentFileName());
                            xfQtclTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    List<LocalMedia> photo = new ArrayList<>();
                                    LocalMedia localMedia = new LocalMedia();
                                    localMedia.setPath(TagConstant.BASEURL + yyDetailsBean.getImgList().get(0).getAttachmentFileUrl());
                                    localMedia.setFileName(yyDetailsBean.getImgList().get(0).getAttachmentFileName());
                                    photo.add(localMedia);
                                    PictureSelector.create(QtDetailsActivity.this).themeStyle(R.style.picture_default_style)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.qinyue.monitor.work;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean2;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.first.MzBean;
import com.qinyue.monitor.first.UpDataFileBean;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.qinyue.monitor.view.ChooseAfdActivity;
import com.qinyue.monitor.view.SelectSectionParentEntity;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.data.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

public class ApplyHelpActivity extends BaseActivity {
    @BindView(R.id.txt_xm)
    TextView txtXm;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.txt_wcnrsf)
    TextView txtWcnrsf;
    @BindView(R.id.et_wcnrsf)
    EditText etWcnrsf;
    @BindView(R.id.rl_wcnrsf)
    RelativeLayout rlWcnrsf;
    @BindView(R.id.txt_zym)
    TextView txtZym;
    @BindView(R.id.et_zym)
    EditText etZym;
    @BindView(R.id.rl_zym)
    RelativeLayout rlZym;
    @BindView(R.id.txt_xb)
    TextView txtXb;
    @BindView(R.id.et_xb)
    EditText etXb;
    @BindView(R.id.rl_xb)
    RelativeLayout rlXb;
    @BindView(R.id.txt_ch)
    TextView txtCh;
    @BindView(R.id.et_ch)
    EditText etCh;
    @BindView(R.id.rl_ch)
    RelativeLayout rlCh;
    @BindView(R.id.txt_csrq)
    TextView txtCsrq;
    @BindView(R.id.et_csrq)
    EditText etCsrq;
    @BindView(R.id.rl_csrq)
    RelativeLayout rlCsrq;
    @BindView(R.id.txt_zjlx)
    TextView txtZjlx;
    @BindView(R.id.et_zjlx)
    EditText etZjlx;
    @BindView(R.id.rl_zjlx)
    RelativeLayout rlZjlx;
    @BindView(R.id.txt_zjhm)
    TextView txtZjhm;
    @BindView(R.id.et_zjhm)
    EditText etZjhm;
    @BindView(R.id.rl_zjhm)
    RelativeLayout rlZjhm;
    @BindView(R.id.txt_mz)
    TextView txtMz;
    @BindView(R.id.et_mz)
    EditText etMz;
    @BindView(R.id.rl_mz)
    RelativeLayout rlMz;
    @BindView(R.id.txt_gj)
    TextView txtGj;
    @BindView(R.id.et_gj)
    EditText etGj;
    @BindView(R.id.rl_gj)
    RelativeLayout rlGj;
    @BindView(R.id.txt_sqjz_phone)
    TextView txtSqjzPhone;
    @BindView(R.id.et_sqjz_phone)
    EditText etSqjzPhone;
    @BindView(R.id.rl_sqjz_phone)
    RelativeLayout rlSqjzPhone;
    @BindView(R.id.txt_sqjz_zsd)
    TextView txtSqjzZsd;
    @BindView(R.id.et_sqjz_zsd)
    EditText etSqjzZsd;
    @BindView(R.id.rl_sqjz_zsd)
    RelativeLayout rlSqjzZsd;
    @BindView(R.id.txt_zsddz)
    TextView txtZsddz;
    @BindView(R.id.et_zsddz)
    EditText etZsddz;
    @BindView(R.id.rl_zsddz)
    RelativeLayout rlZsddz;
    @BindView(R.id.txt_sqjz_gzdw)
    TextView txtSqjzGzdw;
    @BindView(R.id.et_sqjz_gzdw)
    EditText etSqjzGzdw;
    @BindView(R.id.rl_sqjz_gzdw)
    RelativeLayout rlSqjzGzdw;
    @BindView(R.id.txt_gzdwxxszd)
    TextView txtGzdwxxszd;
    @BindView(R.id.et_gzdwxxszd)
    EditText etGzdwxxszd;
    @BindView(R.id.rl_gzdwxxszd)
    RelativeLayout rlGzdwxxszd;
    @BindView(R.id.txt_fddlr)
    TextView txtFddlr;
    @BindView(R.id.et_fddlr)
    EditText etFddlr;
    @BindView(R.id.rl_fddlr)
    RelativeLayout rlFddlr;
    @BindView(R.id.txt_jhqk)
    TextView txtJhqk;
    @BindView(R.id.et_jhqk)
    EditText etJhqk;
    @BindView(R.id.rl_jhqk)
    RelativeLayout rlJhqk;
    @BindView(R.id.txt_jsd_sqjz)
    TextView txtJsdSqjz;
    @BindView(R.id.et_jsd_sqjz)
    EditText etJsdSqjz;
    @BindView(R.id.rl_jsd_sqjz)
    RelativeLayout rlJsdSqjz;
    @BindView(R.id.txt_bkgr_xm)
    TextView txtBkgrXm;
    @BindView(R.id.et_bkgr_name)
    EditText etBkgrName;
    @BindView(R.id.rl_bkgr_name)
    RelativeLayout rlBkgrName;
    @BindView(R.id.txt_wcnrgx)
    TextView txtWcnrgx;
    @BindView(R.id.et_wcnrgx)
    EditText etWcnrgx;
    @BindView(R.id.rl_wcnrgx)
    RelativeLayout rlWcnrgx;
    @BindView(R.id.txt_bkgr_xb)
    TextView txtBkgrXb;
    @BindView(R.id.et_bkgr_xb)
    EditText etBkgrXb;
    @BindView(R.id.rl_bkgr_xb)
    RelativeLayout rlBkgrXb;
    @BindView(R.id.txt_wzjlx)
    TextView txtWzjlx;
    @BindView(R.id.et_wzjlx)
    EditText etWzjlx;
    @BindView(R.id.rl_wzjlx)
    RelativeLayout rlWzjlx;
    @BindView(R.id.txt_wzjhm)
    TextView txtWzjhm;
    @BindView(R.id.et_wzjhm)
    EditText etWzjhm;
    @BindView(R.id.rl_wzjhm)
    RelativeLayout rlWzjhm;
    @BindView(R.id.txt_wmz)
    TextView txtWmz;
    @BindView(R.id.et_wmz)
    EditText etWmz;
    @BindView(R.id.rl_wmz)
    RelativeLayout rlWmz;
    @BindView(R.id.txt_bkgr_gj)
    TextView txtBkgrGj;
    @BindView(R.id.et_bkgr_gj)
    EditText etBkgrGj;
    @BindView(R.id.rl_bkgr_gj)
    RelativeLayout rlBkgrGj;
    @BindView(R.id.txt_bkgr_zw)
    TextView txtBkgrZw;
    @BindView(R.id.et_bkgr_zw)
    EditText etBkgrZw;
    @BindView(R.id.rl_bkgr_zw)
    RelativeLayout rlBkgrZw;
    @BindView(R.id.txt_bkgr_zj)
    TextView txtBkgrZj;
    @BindView(R.id.et_bkgr_zj)
    EditText etBkgrZj;
    @BindView(R.id.rl_bkgr_zj)
    RelativeLayout rlBkgrZj;
    @BindView(R.id.txt_bkgr_sf)
    TextView txtBkgrSf;
    @BindView(R.id.et_bkgr_sf)
    EditText etBkgrSf;
    @BindView(R.id.rl_bkgr_sf)
    RelativeLayout rlBkgrSf;
    @BindView(R.id.txt_bkgr_zxwy)
    TextView txtBkgrZxwy;
    @BindView(R.id.et_bkgr_zxwy)
    EditText etBkgrZxwy;
    @BindView(R.id.rl_bkgr_zxwy)
    RelativeLayout rlBkgrZxwy;
    @BindView(R.id.txt_bkgr_jsdjcy)
    TextView txtBkgrJsdjcy;
    @BindView(R.id.et_jsjcy)
    EditText etJsjcy;
    @BindView(R.id.txt_bkgr_afd)
    TextView txtBkgrAfd;
    @BindView(R.id.et_afd)
    EditText etAfd;
    @BindView(R.id.txt_yaay)
    TextView txtYaay;
    @BindView(R.id.et_yaay)
    EditText etYaay;
    @BindView(R.id.txt_ybajc)
    TextView txtYbajc;
    @BindView(R.id.et_ybajc)
    EditText etYbajc;
    @BindView(R.id.txt_sqjzlb)
    TextView txtSqjzlb;
    @BindView(R.id.et_sqjzlb)
    EditText etSqjzlb;
    @BindView(R.id.txt_sqly)
    TextView txtSqly;
    @BindView(R.id.et_sqly)
    EditText etSqly;
    @BindView(R.id.txt_bkgr_nr)
    TextView txtBkgrNr;
    @BindView(R.id.txt_sqjznr)
    TextView txtSqjznr;
    @BindView(R.id.img_qtcl)
    ImageView imgQtcl;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;
    @BindView(R.id.content)
    MultiLineEditText content;
    @BindView(R.id.content_jznr)
    MultiLineEditText content_jznr;

    private int[] checkIndexSex = {-1, -1, -1};
    private TimePickerView mDatePicker;
    private TimePickerView wDatePicker;
    int zjlxIndex = 0;
    int WcnxIndex = 0;//控告人信息
    int mzIndex = 0;
    int WcnmzIndex = 0;//控告人信息
    int gjIndex = 0;
    int WcngjIndex = 0;//控告人信息
    int sfIndex = 0;
    int BkgrSfIndex = 0;
    int QtSfIndex = 0;
    private int[] checkIndexJhqk = {-1, -1, -1};
    private int[] checkIndexNl = {-1, -1, -1};
    private int clIndex = 0;
    SparseArray<LocalMedia> selectPhoto = new SparseArray<>();
    SparseArray<UpDataFileBean> fileIds = new SparseArray<>();
    CertificateTypeBean certificateTypeBean;
    List<String> zjlxStrBeans = new ArrayList<>();
    private int[] mzSelectOption = {-1, -1, -1};
    List<MzBean> mzBeans;
    List<MzBean> wmzBeans;
    List<MzBean> sfBeans;
    List<MzBean> qtSfBeans;
    List<String> mzStrBeans = new ArrayList<>();
    List<String> wmzStrBeans = new ArrayList<>();
    List<String> sfStrBeans = new ArrayList<>();
    List<String> bkgrSfStrBeans = new ArrayList<>();
    List<String> qtSfStrBeans = new ArrayList<>();
    List<MzBean> gjBeans;
    List<MzBean> wgjBeans;
    List<String> gjStrBeans = new ArrayList<>();

    @Override
    public String initTitleText() {
        return "申请救助";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        etBkgrName.setText(UserUtils.getRealName());
//        etWzjlx.setText("居民身份证");
//        etWzjhm.setText(UserUtils.getIdCard());
    }

    @Override
    protected void initview() {

    }

    public void submit(){
        Map<String,String> map = new HashMap<>();
        map.put("source",TagConstant.SOURCE);
        map.put("juvenileName",etName.getText().toString().trim());
        map.put("juvenilePosition",etWcnrsf.getText().toString().trim());
        map.put("juvenileSex",etXb.getText().toString().trim());
        map.put("juvenileNameBefore",etZym.getText().toString().trim());
        map.put("juvenileNickname",etCh.getText().toString().trim());
        map.put("juvenileCertificateNumber",etZjhm.getText().toString().trim());
        if(certificateTypeBean!=null){
            map.put("juvenileCertificateType",certificateTypeBean.getData().get(zjlxIndex).getCode()+"");
        }
        map.put("juvenileBirthday",etCsrq.getText().toString().trim());
        map.put("juvenileAge",etJsdSqjz.getText().toString().trim());
        if(mzBeans!=null){
            map.put("juvenileNation",mzBeans.get(mzIndex).getCode()+"");
        }
        if(gjBeans!=null){
            map.put("juvenileNationality",gjBeans.get(gjIndex).getCode()+"");
        }
        map.put("juvenileDomicile",etSqjzPhone.getText().toString().trim());
        map.put("juvenileResidence",etSqjzZsd.getText().toString().trim());
        map.put("juvenileAddress",etZsddz.getText().toString().trim());
        map.put("juvenileSchool",etSqjzGzdw.getText().toString().trim());
        map.put("juvenileSchoolAddress",etGzdwxxszd.getText().toString().trim());
        map.put("juvenileAgent",etFddlr.getText().toString().trim());
        map.put("juvenileGuardState",etJhqk.getText().toString().trim());

        map.put("plaintiffName",etBkgrName.getText().toString().trim());
        map.put("relationship",etWcnrgx.getText().toString().trim());
        map.put("plaintiffSex",etBkgrXb.getText().toString().trim());
        if(certificateTypeBean!=null){
            map.put("plaintiffCertificateType",certificateTypeBean.getData().get(WcnmzIndex).getCode()+"");
        }
        map.put("plaintiffCertificateNumber",etWzjhm.getText().toString().trim());
        if(wmzBeans!=null){
            map.put("plaintiffNation",wmzBeans.get(WcnmzIndex).getCode()+"");
        }
        if(wgjBeans!=null){
            map.put("plaintiffNationality",wgjBeans.get(WcngjIndex).getCode()+"");
        }
        map.put("plaintiffDuty",etBkgrZw.getText().toString().trim());
        map.put("plaintiffArea",etBkgrZj.getText().toString().trim());
        map.put("plaintiffResidence",etBkgrSf.getText().toString().trim());
//        map.put("plaintiffIdentity",etZsddz.getText().toString().trim());//小程序没有身份
        map.put("plaintiffPhone",etBkgrZxwy.getText().toString().trim());
        map.put("organizationCode",TagConstant.CODE);
        map.put("salveeType",etAfd.getText().toString().trim());
        map.put("caseReason",etYaay.getText().toString().trim());
        map.put("caseOrgSrcName",etYbajc.getText().toString().trim());
        map.put("helpType",etSqjzlb.getText().toString().trim());
        map.put("decReason",etSqly.getText().toString().trim());
        map.put("caseRemark",content.getEditText().getText().toString().trim());
        map.put("content",content_jznr.getEditText().getText().toString().trim());
        map.put("attachmentFile1",fileIds.get(0)==null?"":fileIds.get(0).getFileId());
        map.put("userId",UserUtils.getId());
        map.put("userKeyId",UserUtils.getKeyId());
        map.put("username",UserUtils.getUserName());
        map.put("userRealName",UserUtils.getRealName());
        String ss = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY,ss);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.juvenileRescue)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        XToast.success(ApplyHelpActivity.this,s.getMessage()).show();
                        finish();
                    }else {
                        XToast.error(ApplyHelpActivity.this,s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ApplyHelpActivity.this,throwable.getMessage()).show();
                });

    }

    @OnClick({R.id.et_wcnrsf,R.id.btn_cancel,R.id.et_sqjzlb,R.id.btn_submit,R.id.img_qtcl,R.id.et_yaay,R.id.et_afd,R.id.et_bkgr_zj,R.id.et_bkgr_gj,R.id.et_wmz,R.id.et_wzjlx,R.id.et_bkgr_xb,R.id.et_wcnrgx,R.id.et_jsd_sqjz,R.id.et_jhqk,R.id.et_gzdwxxszd,R.id.et_xb,R.id.et_csrq,R.id.et_zjlx,R.id.et_mz,R.id.et_gj,R.id.et_sqjz_phone,R.id.et_sqjz_zsd})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.et_wcnrsf:{
                showSimpleBottomSheetList(0,etWcnrsf);
            }break;
            case R.id.et_xb:{
                showXbSimpleBottomSheetList(0,etXb);
            }break;
            case R.id.et_csrq:{
                showDatePicker();
            }break;
            case R.id.et_zjlx:{
                getCertificataType();
            }break;
            case R.id.et_mz:{
                if(mzBeans==null){
                    getMzDataForcChild(0,etMz);
                }else {
                    showMzPickerView(etMz, 0);
                }
            }break;
            case R.id.et_gj:{
                if(gjBeans==null){
                    getGjMsg();
                }else {
                    showGjPickerView(etGj, 0);
                }
            }break;
            case R.id.et_sqjz_phone:{
                Intent intent = new Intent(ApplyHelpActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 130);
            }break;
            case R.id.et_sqjz_zsd:{
                Intent intent = new Intent(ApplyHelpActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 140);
            }break;
            case R.id.et_gzdwxxszd:{
                Intent intent = new Intent(ApplyHelpActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 150);
            }break;
            case R.id.et_jhqk:{
                showJhqkSimpleBottomSheetList(0,etJhqk);
            }break;
            case R.id.et_jsd_sqjz:{
                showNlSimpleBottomSheetList(0,etJsdSqjz);
            }break;
            case R.id.et_wcnrgx:{
                showYwcngxSimpleBottomSheetList(0,etWcnrgx);
            }break;
            case R.id.et_bkgr_xb:{
                showXbSimpleBottomSheetList(0,etBkgrXb);
            }break;
            case R.id.et_wzjlx:{
                if(certificateTypeBean==null){
                    getWcnCertificataType();
                }else {
                    showWcnZjlxPickerView(etWzjlx, 0);
                }
            }break;
            case R.id.et_wmz:{
                if(wmzBeans==null){
                    getWcnMzDataForcChild(0,etWmz);
                }else {
                    showWcnMzPickerView(etWmz, 0);
                }
            }break;
            case R.id.et_bkgr_gj:{
                if(wgjBeans==null){
                    getWcnGjMsg();
                }else {
                    showWcnGjPickerView(etBkgrGj, 0);
                }
            }break;
            case R.id.et_bkgr_zj:{
                Intent intent = new Intent(ApplyHelpActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 160);
            }break;
            case R.id.et_afd:{
                showSsjglxSimpleBottomSheetList(0,etAfd);
            }break;
            case R.id.et_yaay:{
                showAySimpleBottomSheetList(0,etYaay);
            }break;
            case R.id.et_sqjzlb:{
                showjzlbSimpleBottomSheetList(0,etSqjzlb);
            }break;
            case R.id.img_qtcl:{
                clIndex = 0;
                showChoosePhotoDialog();
            }break;
            case R.id.btn_submit:{
                if(!etName.getText().toString().isEmpty()||!etWcnrsf.getText().toString().isEmpty()||!etXb.getText().toString().isEmpty()||!etZjlx.getText().toString().isEmpty()||!etZjhm.getText().toString().isEmpty()||!etJsdSqjz.getText().toString().isEmpty()||!etBkgrName.getText().toString().isEmpty()||!etWcnrgx.getText().toString().isEmpty()||!etWzjlx.getText().toString().isEmpty()||!etWzjhm.getText().toString().isEmpty()||!etBkgrZxwy.getText().toString().isEmpty()||!etAfd.getText().toString().isEmpty()||!etYbajc.getText().toString().isEmpty()||!etSqjzlb.getText().toString().isEmpty()||!etSqly.getText().toString().isEmpty()||!content.getEditText().getText().toString().isEmpty()||!content_jznr.getEditText().getText().toString().isEmpty()){
                    miniLoadingDialog.show();
                    if (selectPhoto.size()>0) {
                        fileIds.clear();
                        upLoadFiles(0);
                    }else {
                        submit();
                    }
                }else {

                    XToast.warning(this, "请输入必输内容").show();
                }
            }break;
            case R.id.btn_cancel:{
                finish();
            }break;
        }
    }

    private void showChoosePhotoDialog() {
        new BottomSheet.BottomListSheetBuilder(this)
                .addItem("拍照")
                .addItem("从相册中选择")
                .setTitle("选择文件")
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        if (position == 0) {
                            PictureSelector.create(ApplyHelpActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .maxSelectNum(1)
                                    .loadImageEngine(GlideEngine.createGlideEngine())
                                    .forResult(PictureConfig.REQUEST_CAMERA);
                        } else {
                            PictureSelector.create(ApplyHelpActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .selectionMode(PictureConfig.SINGLE)
                                    .loadImageEngine(GlideEngine.createGlideEngine())
                                    .isCamera(false)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    }
                })
                .build().show();
    }
    private int retry = 0;
    private void upLoadFiles(int index){
        if (selectPhoto.get(index)==null&&index<1){
            upLoadFiles(index+1);
            return;
        }
        if (index>1){
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("fileName",selectPhoto.get(index).getFileName());
        String fromBase64 = Base64Converter.encodeBase64File(selectPhoto.get(index).getPath());
        map.put("data",fromBase64 );
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.fileUpload)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", a)
                .asObject(UpDataFileBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        retry = 0;
                        fileIds.put(index,s);
                        //下一个
                        if (fileIds.size()<selectPhoto.size()){
                            int sss = index+1;
                            upLoadFiles(sss);
                        }else {
                            submit();
                        }
                    }else {
                        //重试
                        retry ++;
                        if (retry==3){
                            miniLoadingDialog.dismiss();
                            XToast.error(ApplyHelpActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                        }else {
                            upLoadFiles(index);
                        }
                    }
                }, throwable -> {
                    retry ++;
                    if (retry==3){
                        miniLoadingDialog.dismiss();
                        XToast.error(ApplyHelpActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                    }else {
                        upLoadFiles(index);
                    }
                });
    }

    private void showjzlbSimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("")
                .addItem("经济救助")
                .addItem("法律救助")
                .addItem("心理救助")
                .addItem("社会救助")
                .setCheckedIndex(checkIndexNl[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexNl[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    private void showAySimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("")
                .addItem("强奸罪")
                .addItem("强制猥琐、侮辱罪")
                .addItem("拐卖妇女、儿童罪")
                .addItem("收买被拐卖的妇女儿童罪")
                .addItem("虐待罪")
                .addItem("遗弃罪")
                .addItem("拐卖儿童罪")
                .addItem("组织残疾儿、儿童乞讨罪")
                .addItem("组织未成年人进行违反治安管理罪")
                .addItem("组织、强迫、引诱、容留、介绍卖淫罪")
                .addItem("传播淫秽物品罪")
                .addItem("组织淫秽表演罪")
                .addItem("虐待被监管人罪")
                .addItem("其他")
                .setCheckedIndex(checkIndexNl[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexNl[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    private void showSsjglxSimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("")
                .addItem("刑事案件被害人")
                .addItem("民事案件被侵权人")
                .addItem("因案件陷入困境未成年人")
                .addItem("其他")
                .setCheckedIndex(checkIndexSex[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexSex[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    /**
     * 未成年国籍
     */
    private void showWcnGjPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                WcngjIndex = options1;
                tv.setText(wgjBeans.get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(WcngjIndex)
                .build();
        pvOptions.setPicker(gjStrBeans);
        pvOptions.show();
    }

    public void getWcnGjMsg(){
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "3")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        wgjBeans = s.getData();
                        for (int i = 0; i < wgjBeans.size(); i++) {
                            gjStrBeans.add(wgjBeans.get(i).getName());
                        }
                        showWcnGjPickerView(etBkgrGj, 0);
                    } else {
                        XToast.error(ApplyHelpActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ApplyHelpActivity.this, throwable.getMessage()).show();
                });
    }

    public void getWcnMzDataForcChild(int pos, EditText textView) {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "2")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        wmzBeans = s.getData();
                        for (int i = 0; i < wmzBeans.size(); i++) {
                            wmzStrBeans.add(wmzBeans.get(i).getName());
                        }
                        showWcnMzPickerView(textView, pos);
                    } else {
                        XToast.error(ApplyHelpActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ApplyHelpActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 未成年民族
     */
    private void showWcnMzPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                WcnmzIndex = options1;
                tv.setText(wmzBeans.get(options1).getName());
                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("民族")
                .setSelectOptions(WcnmzIndex)
                .build();
        pvOptions.setPicker(wmzStrBeans);
        pvOptions.show();
    }

    public void getWcnCertificataType(){
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "1")
                .asParser(new SimpleParser<CertificateTypeBean>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if ("200".equals(s.getResult())) {
                        certificateTypeBean = s;
                        for (int i = 0; i < certificateTypeBean.getData().size(); i++) {
                            zjlxStrBeans.add(certificateTypeBean.getData().get(i).getName());
                        }
                        showWcnZjlxPickerView(etWzjlx, 0);
                    } else {
                        XToast.error(ApplyHelpActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ApplyHelpActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 证件类型
     */
    private void showWcnZjlxPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                WcnxIndex = options1;
                tv.setText(certificateTypeBean.getData().get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(WcnxIndex)
                .build();
        pvOptions.setPicker(zjlxStrBeans);
        pvOptions.show();
    }

    private void showYwcngxSimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("")
                .addItem("未成年人本人")
                .addItem("法定代理人")
                .addItem("其他近亲属")
                .addItem("代理律师")
                .addItem("其他人员")
                .setCheckedIndex(checkIndexNl[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexNl[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    private void showNlSimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("")
                .addItem("不满12周岁")
                .addItem("已满12周岁不满14周岁")
                .addItem("已满14周岁不满16周岁")
                .addItem("已满16周岁不满18周岁")
                .setCheckedIndex(checkIndexNl[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexNl[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    private void showJhqkSimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("")
                .addItem("父母监护")
                .addItem("祖父或外祖父监护")
                .addItem("其他成年家属")
                .addItem("村委会或居委会")
                .addItem("未成年人保护组织")
                .addItem("无人监护")
                .addItem("民政部门")
                .addItem("单亲监护（父/母）")
                .addItem("其他")
                .setCheckedIndex(checkIndexJhqk[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexJhqk[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==130&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etSqjzPhone.setText(sectionParentEntity.getName() + "");
        }else if(requestCode==140&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etSqjzZsd.setText(sectionParentEntity.getName() + "");
        }else if(requestCode==150&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etGzdwxxszd.setText(sectionParentEntity.getName() + "");
        }else if(requestCode==160&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etBkgrZj.setText(sectionParentEntity.getName() + "");
        }else if (requestCode == PictureConfig.CHOOSE_REQUEST||requestCode == PictureConfig.REQUEST_CAMERA) {
            //相册返回
            // 图片选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList!=null&&selectList.size()>0){
                switch (clIndex){
                    case 0:{
                        Glide.with(ApplyHelpActivity.this).load(selectList.get(0).getPath()).into(imgQtcl);
                    }break;
                }
                selectPhoto.put(clIndex,selectList.get(0));
            }
        }
    }

    /**
     * 国籍
     */
    private void showGjPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                gjIndex = options1;
                tv.setText(gjBeans.get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(gjIndex)
                .build();
        pvOptions.setPicker(gjStrBeans);
        pvOptions.show();
    }

    public void getGjMsg(){
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "3")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        gjBeans = s.getData();
                        for (int i = 0; i < gjBeans.size(); i++) {
                            gjStrBeans.add(gjBeans.get(i).getName());
                        }
                        showGjPickerView(etGj, 0);
                    } else {
                        XToast.error(ApplyHelpActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ApplyHelpActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 民族
     */
    private void showMzPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                mzIndex = options1;
                tv.setText(mzBeans.get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("民族")
                .setSelectOptions(mzIndex)
                .build();
        pvOptions.setPicker(mzStrBeans);
        pvOptions.show();
    }

    public void getMzDataForcChild(int pos, EditText textView) {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "2")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        mzBeans = s.getData();
                        for (int i = 0; i < mzBeans.size(); i++) {
                            mzStrBeans.add(mzBeans.get(i).getName());
                        }
                        showMzPickerView(textView, pos);
                    } else {
                        XToast.error(ApplyHelpActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ApplyHelpActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 证件类型
     */
    private void showZjlxPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                zjlxIndex = options1;
                tv.setText(certificateTypeBean.getData().get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(zjlxIndex)
                .build();
        pvOptions.setPicker(zjlxStrBeans);
        pvOptions.show();
    }

    public void getCertificataType(){
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "1")
                .asParser(new SimpleParser<CertificateTypeBean>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if ("200".equals(s.getResult())) {
                        certificateTypeBean = s;
                        for (int i = 0; i < certificateTypeBean.getData().size(); i++) {
                            zjlxStrBeans.add(certificateTypeBean.getData().get(i).getName());
                        }
                        showZjlxPickerView(etZjlx, 0);
                    } else {
                        XToast.error(ApplyHelpActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ApplyHelpActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 日期选择
     */
    private void showDatePicker() {
        if (mDatePicker == null) {
            mDatePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelected(Date date, View v) {
//                    XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
                    etCsrq.setText(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
                }
            })
                    .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {
                            Log.i("pvTime", "onTimeSelectChanged");
                        }
                    })
                    .setTitleText("日期选择")
                    .build();
        }
        mDatePicker.show();
    }

    private void showXbSimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("请选择性别")
                .addItem("未知")
                .addItem("男")
                .addItem("女")
                .setCheckedIndex(checkIndexSex[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexSex[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    private void showSimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("")
                .addItem("被害人")
                .addItem("被告人子女")
                .setCheckedIndex(checkIndexSex[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexSex[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_apply_help;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected Boolean status() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

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

import com.blankj.utilcode.util.RegexUtils;
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
import com.qinyue.monitor.first.JwjdActivty;
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

public class UnderageAccusationActivity extends BaseActivity {
    @BindView(R.id.txt_xm)
    TextView txtXm;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
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
    @BindView(R.id.txt_yddh)
    TextView txtYddh;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.txt_dzyx)
    TextView txtDzyx;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.rl_email)
    RelativeLayout rlEmail;
    @BindView(R.id.txt_zsddz)
    TextView txtZsddz;
    @BindView(R.id.et_zsddz)
    EditText etZsddz;
    @BindView(R.id.rl_zsddz)
    RelativeLayout rlZsddz;
    @BindView(R.id.txt_gzdw)
    TextView txtGzdw;
    @BindView(R.id.et_gzdw)
    EditText etGzdw;
    @BindView(R.id.rl_gzdw)
    RelativeLayout rlGzdw;
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
    @BindView(R.id.txt_jsd)
    TextView txtJsd;
    @BindView(R.id.et_szd)
    EditText etSzd;
    @BindView(R.id.rl_jsd)
    RelativeLayout rlJsd;
    @BindView(R.id.txt_bkgr_xm)
    TextView txtBkgrXm;
    @BindView(R.id.et_bkgr_name)
    EditText etBkgrName;
    @BindView(R.id.rl_bkgr_name)
    RelativeLayout rlBkgrName;
    @BindView(R.id.txt_ywcngz)
    TextView txtYwcngz;
    @BindView(R.id.et_ywcngz)
    EditText etYwcngz;
    @BindView(R.id.rl_ywcngz)
    RelativeLayout rlYwcngz;
    @BindView(R.id.txt_bkgr_xb)
    TextView txtBkgrXb;
    @BindView(R.id.et_bkgr_xb)
    EditText etBkgrXb;
    @BindView(R.id.rl_bkgr_xb)
    RelativeLayout rlBkgrXb;
    @BindView(R.id.txt_bkgr_zjlx)
    TextView txtBkgrZjlx;
    @BindView(R.id.et_bkgr_zjlx)
    EditText etBkgrZjlx;
    @BindView(R.id.rl_bkgr_zjlx)
    RelativeLayout rlBkgrZjlx;
    @BindView(R.id.txt_bkgr_zjhm)
    TextView txtBkgrZjhm;
    @BindView(R.id.et_bkgr_zjhm)
    EditText etBkgrZjhm;
    @BindView(R.id.rl_bkgr_zjhm)
    RelativeLayout rlBkgrZjhm;
    @BindView(R.id.txt_bkgr_mz)
    TextView txtBkgrMz;
    @BindView(R.id.et_bkgr_mz)
    EditText etBkgrMz;
    @BindView(R.id.rl_bkgr_mz)
    RelativeLayout rlBkgrMz;
    @BindView(R.id.txt_bkgr_gj)
    TextView txtBkgrGj;
    @BindView(R.id.et_bkgr_gj)
    EditText etBkgrGj;
    @BindView(R.id.rl_bkgr_gj)
    RelativeLayout rlBkgrGj;
    @BindView(R.id.txt_bkgr_gzdwqc)
    TextView txtBkgrGzdwqc;
    @BindView(R.id.et_bkgr_dwmc)
    EditText etBkgrDwmc;
    @BindView(R.id.rl_bkgr_gzdwqc)
    RelativeLayout rlBkgrGzdwqc;
    @BindView(R.id.txt_bkgr_gzdwszd)
    TextView txtBkgrGzdwszd;
    @BindView(R.id.et_bkgr_dwdz)
    EditText etBkgrDwdz;
    @BindView(R.id.rl_bkgr_dwszd)
    RelativeLayout rlBkgrDwszd;
    @BindView(R.id.txt_bkgr_sf)
    TextView txtBkgrSf;
    @BindView(R.id.et_bkgr_sf)
    EditText etBkgrSf;
    @BindView(R.id.rl_bkgr_sf)
    RelativeLayout rlBkgrSf;
    @BindView(R.id.txt_bkgr_rddb)
    TextView txtBkgrRddb;
    @BindView(R.id.et_bkgr_rddb)
    EditText etBkgrRddb;
    @BindView(R.id.rl_bkgr_rddb)
    RelativeLayout rlBkgrRddb;
    @BindView(R.id.txt_wbkgr_name)
    TextView txtWbkgrName;
    @BindView(R.id.et_wbkgr_name)
    EditText etWbkgrName;
    @BindView(R.id.rl_wbkgr_name)
    RelativeLayout rlWbkgrName;
    @BindView(R.id.txt_wxb)
    TextView txtWxb;
    @BindView(R.id.et_wxb)
    EditText etWxb;
    @BindView(R.id.rl_wxb)
    RelativeLayout rlWxb;
    @BindView(R.id.txt_wzw)
    TextView txtWzw;
    @BindView(R.id.et_wzw)
    EditText etWzw;
    @BindView(R.id.rl_wzw)
    RelativeLayout rlWzw;
    @BindView(R.id.txt_wsf)
    TextView txtWsf;
    @BindView(R.id.et_wsf)
    EditText etWsf;
    @BindView(R.id.rl_wsf)
    RelativeLayout rlWsf;
    @BindView(R.id.txt_wqtgzsf)
    TextView txtWqtgzsf;
    @BindView(R.id.et_wqtgzsf)
    EditText etWqtgzsf;
    @BindView(R.id.rl_wqtgzsf)
    RelativeLayout rlWqtgzsf;
    @BindView(R.id.txt_wdwszd)
    TextView txtWdwszd;
    @BindView(R.id.et_wdwszd)
    EditText etWdwszd;
    @BindView(R.id.rl_wdwszd)
    RelativeLayout rlWdwszd;
    @BindView(R.id.txt_wzz)
    TextView txtWzz;
    @BindView(R.id.et_wzz)
    EditText etWzz;
    @BindView(R.id.rl_wzz)
    RelativeLayout rlWzz;
    @BindView(R.id.txt_wgzdw)
    TextView txtWgzdw;
    @BindView(R.id.et_wgzdw)
    EditText etWgzdw;
    @BindView(R.id.rl_wgzdw)
    RelativeLayout rlWgzdw;
    @BindView(R.id.txt_bkgr_afd)
    TextView txtBkgrAfd;
    @BindView(R.id.et_afd)
    EditText etAfd;
    @BindView(R.id.txt_kgsxfssj)
    TextView txtKgsxfssj;
    @BindView(R.id.et_kgsxfssj)
    EditText etKgsxfssj;
    @BindView(R.id.txt_bkgr_jsdjcy)
    TextView txtBkgrJsdjcy;
    @BindView(R.id.et_jsjcy)
    EditText etJsjcy;
    @BindView(R.id.txt_bkgr_nr)
    TextView txtBkgrNr;
    @BindView(R.id.img_kgcl)
    ImageView imgKgcl;
    @BindView(R.id.img_zjcl)
    ImageView imgZjcl;
    @BindView(R.id.img_qtcl)
    ImageView imgQtcl;
    @BindView(R.id.txt_kgxsay)
    TextView txtKgxsay;
    @BindView(R.id.et_kgxsay)
    EditText etKgxsay;
    @BindView(R.id.rl_kgxsay)
    RelativeLayout rlKgxsay;
    @BindView(R.id.txt_kgqtxsay)
    TextView txtKgqtxsay;
    @BindView(R.id.et_kgqtxsay)
    EditText etKgqtxsay;
    @BindView(R.id.rl_kgqtxsay)
    RelativeLayout rlKgqtxsay;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;
    @BindView(R.id.content)
    MultiLineEditText contentTv;
    @BindView(R.id.et_csrq)
    EditText et_csrq;

    private int[] checkIndexSex = {-1, -1, -1};
    CertificateTypeBean certificateTypeBean;
    List<String> zjlxStrBeans = new ArrayList<>();
    private int[] mzSelectOption = {-1, -1, -1};
    List<MzBean> mzBeans;
    List<MzBean> sfBeans;
    List<MzBean> bsfBeans;
    List<MzBean> qtSfBeans;
    List<String> mzStrBeans = new ArrayList<>();
    List<String> sfStrBeans = new ArrayList<>();
    List<String> bkgrSfStrBeans = new ArrayList<>();
    List<String> qtSfStrBeans = new ArrayList<>();
    List<MzBean> gjBeans;
    List<String> gjStrBeans = new ArrayList<>();
    private TimePickerView mDatePicker;
    private TimePickerView wDatePicker;

    int zjlxIndex = -1;
    int WcnxIndex = -1;//控告人信息
    int sfIndex = -1;
    int BkgrSfIndex = -1;
    int QtSfIndex = -1;
    private int[] checkIndexJhqk = {-1, -1, -1};
    private int[] checkIndexNl = {-1, -1, -1};
    private int clIndex = 0;
    SparseArray<LocalMedia> selectPhoto = new SparseArray<>();
    SparseArray<UpDataFileBean> fileIds = new SparseArray<>();
    private int[] checkIndexGj = {-1, -1};
    private int[] checkIndexMz = {-1, -1};

    @Override
    public String initTitleText() {
        return "控告";//未成年控告
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        etBkgrName.setText(UserUtils.getRealName());
//        etBkgrZjlx.setText("居民身份证");
//        etBkgrZjhm.setText(UserUtils.getIdCard());
    }

    @Override
    protected void initview() {

    }
    @OnClick({R.id.btn_submit,R.id.et_email,R.id.et_xb,R.id.et_gj,R.id.et_mz,R.id.et_zjlx,R.id.et_phone,R.id.et_gzdwxxszd,R.id.et_jhqk,R.id.et_szd,R.id.et_ywcngz,R.id.et_bkgr_xb,R.id.et_bkgr_zjlx,R.id.et_bkgr_mz,R.id.et_bkgr_gj,R.id.et_bkgr_dwmc,R.id.et_bkgr_sf,R.id.et_wxb,R.id.et_wsf,R.id.et_wqtgzsf,R.id.et_wdwszd,R.id.et_afd,R.id.et_kgsxfssj,R.id.img_kgcl,R.id.img_zjcl,R.id.img_qtcl,R.id.et_kgxsay,R.id.et_kgqtxsay,R.id.et_csrq,R.id.btn_cancel})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_submit:{
                if(!etName.getText().toString().trim().isEmpty()||!etXb.getText().toString().isEmpty()||!etZjlx.getText().toString().trim().isEmpty()||!etZjhm.getText().toString().isEmpty()||!etSzd.getText().toString().isEmpty()||!etBkgrName.getText().toString().isEmpty()||!etYwcngz.getText().toString().isEmpty()||!etBkgrZjlx.getText().toString().isEmpty()||!etBkgrZjhm.getText().toString().isEmpty()||!etBkgrRddb.getText().toString().isEmpty()||!etWbkgrName.getText().toString().isEmpty()||!etWdwszd.getText().toString().isEmpty()||!etAfd.getText().toString().isEmpty()||!etKgsxfssj.getText().toString().isEmpty()||!contentTv.getEditText().getText().toString().isEmpty()){
                    if ("居民身份证".equals(etZjlx.getText().toString().trim())&&!RegexUtils.isIDCard18(etZjhm.getText().toString().trim())){
                        XToast.info(this,"未成年人信息证件号码错误").show();
                        break;
                    }
                    if ("居民身份证".equals(etBkgrZjlx.getText().toString().trim())&&!RegexUtils.isIDCard18(etBkgrZjhm.getText().toString().trim())){
                        XToast.info(this,"控告人信息证件号码错误").show();
                        break;
                    }
                    if (!RegexUtils.isMobileSimple(etPhone.getText().toString().trim())){
                        XToast.info(this,"电话号码错误").show();
                        break;
                    }
                    miniLoadingDialog.show();
                    if (selectPhoto.size()>0) {
                        fileIds.clear();
                        upLoadFiles(0);
                    }else {
                        submit();
                    }
                }else {
                    XToast.warning(this, "请输入必填选项").show();
                }
            }break;
            case R.id.et_xb:{
                showSimpleBottomSheetList(0, etXb);
            }break;
            case R.id.et_zjlx:{
                getCertificataType();
            }break;
            case R.id.et_mz:{
                Log.d("index",zjlxIndex+""+"----------"+checkIndexMz[0]+""+"------------=="+checkIndexGj[0]+"");
                if(mzBeans==null){
                    getMzDataForcChild(0,etMz);
                }else {
                    showMzPickerView(etMz, 0);
                }
            }break;
            case R.id.et_gj:{
                Log.d("index",zjlxIndex+""+"----------"+checkIndexMz[0]+""+"------------=="+checkIndexGj[0]+"");
                if(gjBeans==null){
                    getGjDataForcChild(0,etGj);
                }else {
                    showGjPickerView(etGj, 0);
                }
            }break;
            case R.id.et_phone:{
                Intent intent = new Intent(UnderageAccusationActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 100);
            }break;
            case R.id.et_gzdwxxszd:{
                Intent intent = new Intent(UnderageAccusationActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 120);
            }break;
            case R.id.et_jhqk:{
                showJhqkSimpleBottomSheetList(0,etJhqk);
            }break;
            case R.id.et_szd:{
                showNlSimpleBottomSheetList(0,etSzd);
            }break;
            case R.id.et_ywcngz:{
                showYwcngxSimpleBottomSheetList(0,etYwcngz);
            }break;
            case R.id.et_bkgr_xb:{
                showSimpleBottomSheetList(0, etBkgrXb);
            }break;
            case R.id.et_bkgr_zjlx:{
                getWcnCertificataType();
            }break;
            case R.id.et_bkgr_mz:{
                if(mzBeans==null){
                    getMzDataForcChild(1,etBkgrMz);
                }else {
                    showMzPickerView(etBkgrMz, 1);
                }
            }break;
            case R.id.et_bkgr_gj:{
                if(gjBeans==null){
                    getGjDataForcChild(1,etGj);
                }else {
                    showGjPickerView(etGj, 1);
                }
            }break;
            case R.id.et_bkgr_dwmc:{
                Intent intent = new Intent(UnderageAccusationActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 130);
            }break;
            case R.id.et_bkgr_sf:{
                if(sfBeans==null){
                    getSf(0,etBkgrSf);
                }else {
                    showSfPickerView(etBkgrSf, 0);
                }
            }break;
            case R.id.et_wxb:{
                showSimpleBottomSheetList(0, etWxb);
            }break;
            case R.id.et_wsf:{
                if(bsfBeans==null){
                    getBkgrSf(0,etWsf);
                }else {
                    showBkgrSfPickerView(etWsf, 0);
                }
            }break;
            case R.id.et_wqtgzsf:{
                if(qtSfBeans==null){
                    getQtSf(0,etWqtgzsf);
                }else {
                    showqtSfPickerView(etWqtgzsf, 0);
                }
            }break;
            case R.id.et_wdwszd:{
                Intent intent = new Intent(UnderageAccusationActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 140);
            }break;
            case R.id.et_afd:{
                Intent intent = new Intent(UnderageAccusationActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 150);
            }break;
            case R.id.et_kgsxfssj:{
                showDatePicker();
            }break;
            case R.id.img_kgcl:{
                clIndex = 0;
                showChoosePhotoDialog();
            }break;
            case R.id.img_zjcl:{
                clIndex = 1;
                showChoosePhotoDialog();
            }break;
            case R.id.img_qtcl:{
                clIndex = 2;
                showChoosePhotoDialog();
            }break;
            case R.id.et_kgxsay:{
                showAySimpleBottomSheetList(0,etKgxsay);
            }break;
            case R.id.et_kgqtxsay:{
                showAySimpleBottomSheetList(0,etKgqtxsay);
            }break;
            case R.id.et_csrq:{
                showWcnDatePicker();
            }break;
            case R.id.et_email:{
                Intent intent = new Intent(UnderageAccusationActivity.this, ChooseAfdActivity.class);
                startActivityForResult(intent, 160);
            }break;
            case R.id.btn_cancel:{
                finish();
            }break;
        }
    }

    public void submit(){
        Map<String,String> map = new HashMap<>();
        map.put("source",TagConstant.SOURCE);
        map.put("juvenileName",etName.getText().toString().trim());
        map.put("juvenileSex",etXb.getText().toString().trim());
        map.put("juvenileNameBefore",etZym.getText().toString().trim());
        map.put("juvenileNickname",etCh.getText().toString().trim());
        map.put("juvenileCertificateNumber",etZjhm.getText().toString().trim());
        map.put("juvenileCertificateType",certificateTypeBean.getData().get(zjlxIndex).getCode()+"");
        map.put("juvenileBirthday",et_csrq.getText().toString().trim());
        map.put("juvenileNation",checkIndexMz[0]==-1?"":mzBeans.get(checkIndexMz[0]).getCode());
        if(gjBeans.size()>0){
            map.put("juvenileNationality",checkIndexGj[0]==-1?"":gjBeans.get(checkIndexGj[0]).getCode());
        }
        map.put("juvenileDomicile",etPhone.getText().toString().trim());
        map.put("juvenileResidence",etEmail.getText().toString().trim());
        map.put("juvenileAddress",etZsddz.getText().toString().trim());
        map.put("juvenileSchool",etGzdw.getText().toString().trim());
        map.put("juvenileSchoolAddress",etGzdwxxszd.getText().toString().trim());
        map.put("juvenileAgent",etFddlr.getText().toString().trim());
        map.put("juvenileGuardState",etJhqk.getText().toString().trim());
        map.put("juvenileAge",etSzd.getText().toString().trim());
        map.put("plaintiffName",etBkgrName.getText().toString().trim());
        map.put("relationship",etYwcngz.getText().toString().trim());
        map.put("plaintiffSex",etBkgrXb.getText().toString().trim());
        map.put("plaintiffCertificateType",etBkgrZjlx.getText().toString().trim());
        map.put("plaintiffCertificateNumber",etBkgrZjhm.getText().toString().trim());
        map.put("plaintiffNation",checkIndexMz[1]==-1?"":mzBeans.get(checkIndexMz[1]).getCode());
        map.put("plaintiffNationality",checkIndexGj[1]==-1?"":gjBeans.get(checkIndexGj[1]).getCode());
        map.put("plaintiffArea",etBkgrDwmc.getText().toString().trim());
        map.put("plaintiffResidence",etBkgrDwdz.getText().toString().trim());
        map.put("plaintiffIdentity",sfBeans.get(sfIndex).getCode());
        map.put("plaintiffPhone",etBkgrRddb.getText().toString().trim());
        map.put("defendantName",etWbkgrName.getText().toString().trim());
        map.put("defendantSex",etWxb.getText().toString().trim());
        map.put("defendantDuty",etWzw.getText().toString().trim());
        map.put("defendantIdentity",sfBeans.get(BkgrSfIndex).getCode()+"");
        map.put("defendantOtherIdentity",qtSfBeans.get(QtSfIndex).getCode()+"");
        map.put("defendantDomicile",etWdwszd.getText().toString().trim());
        map.put("defendantAddress",etWzz.getText().toString().trim());
        map.put("defendantUnit",etWgzdw.getText().toString().trim());
        map.put("caseArea",etAfd.getText().toString().trim());
        map.put("caseDate",etKgsxfssj.getText().toString().trim());
        map.put("organizationCode",TagConstant.CODE);
        map.put("content",contentTv.getEditText().getText().toString().trim());
        map.put("caseReason",etKgxsay.getText().toString().trim());
        map.put("accuseOtherReason",etKgqtxsay.getText().toString().trim());
        map.put("attachmentFile1",fileIds.get(0)==null?"":fileIds.get(0).getFileId());
        map.put("attachmentFile2",fileIds.get(1)==null?"":fileIds.get(1).getFileId());
        map.put("attachmentFile3",fileIds.get(2)==null?"":fileIds.get(2).getFileId());
        map.put("userId",UserUtils.getId());
        map.put("userKeyId",UserUtils.getKeyId());
        map.put("username",UserUtils.getUserName());
        map.put("userRealName",UserUtils.getRealName());
        String ss = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY,ss);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.juvenileAccuse)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        XToast.success(UnderageAccusationActivity.this,s.getMessage()).show();
                        finish();
                    }else {
                        XToast.error(UnderageAccusationActivity.this,s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(UnderageAccusationActivity.this,throwable.getMessage()).show();
                });


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
                            PictureSelector.create(UnderageAccusationActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .maxSelectNum(1)
                                    .loadImageEngine(GlideEngine.createGlideEngine())
                                    .forResult(PictureConfig.REQUEST_CAMERA);
                        } else {
                            PictureSelector.create(UnderageAccusationActivity.this)
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
        if (selectPhoto.get(index)==null&&index<2){
            upLoadFiles(index+1);
            return;
        }
        if (index>2){
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
                            XToast.error(UnderageAccusationActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                        }else {
                            upLoadFiles(index);
                        }
                    }
                }, throwable -> {
                    retry ++;
                    if (retry==3){
                        miniLoadingDialog.dismiss();
                        XToast.error(UnderageAccusationActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                    }else {
                        upLoadFiles(index);
                    }
                });
    }

    private void showSimpleBottomSheetList(int pos, EditText textView) {
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

    /**
     * 日期选择
     */
    private void showDatePicker() {
        if (mDatePicker == null) {
            mDatePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelected(Date date, View v) {
//                    XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
                    etKgsxfssj.setText(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
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

    /**
     * 日期选择
     */
    private void showWcnDatePicker() {
        if (wDatePicker == null) {
            wDatePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelected(Date date, View v) {
//                    XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
                    et_csrq.setText(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
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
        wDatePicker.show();
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
                        XToast.error(UnderageAccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(UnderageAccusationActivity.this, throwable.getMessage()).show();
                });
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
                        showWcnZjlxPickerView(etBkgrZjlx, 0);
                    } else {
                        XToast.error(UnderageAccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(UnderageAccusationActivity.this, throwable.getMessage()).show();
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
                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(zjlxIndex)
                .build();
        pvOptions.setPicker(zjlxStrBeans);
        pvOptions.show();
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
                        XToast.error(UnderageAccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(UnderageAccusationActivity.this, throwable.getMessage()).show();
                });
    }



    public void getSf(int pos, EditText textView) {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "9")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        sfBeans = s.getData();
                        for (int i = 0; i < sfBeans.size(); i++) {
                            sfStrBeans.add(sfBeans.get(i).getName());
                        }
                        showSfPickerView(textView, pos);
                    } else {
                        XToast.error(UnderageAccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(UnderageAccusationActivity.this, throwable.getMessage()).show();
                });
    }

    public void getBkgrSf(int pos, EditText textView) {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "9")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        bsfBeans = s.getData();
                        for (int i = 0; i < bsfBeans.size(); i++) {
                            bkgrSfStrBeans.add(bsfBeans.get(i).getName());
                        }
                        showBkgrSfPickerView(textView, pos);
                    } else {
                        XToast.error(UnderageAccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(UnderageAccusationActivity.this, throwable.getMessage()).show();
                });
    }

    public void getQtSf(int pos, EditText textView) {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "10")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        qtSfBeans = s.getData();
                        for (int i = 0; i < qtSfBeans.size(); i++) {
                            qtSfStrBeans.add(qtSfBeans.get(i).getName());
                        }
                        showqtSfPickerView(textView, pos);
                    } else {
                        XToast.error(UnderageAccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(UnderageAccusationActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 民族
     */
    private void showMzPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
//                mzIndex = options1;
                tv.setText(mzBeans.get(options1).getName());
                checkIndexMz[where] = options1;
                return false;
            }
        })
                .setTitleText("民族")
                .setSelectOptions(checkIndexMz[where])
                .build();
        pvOptions.setPicker(mzStrBeans);
        pvOptions.show();
    }


    /**
     * 身份
     */
    private void showSfPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                sfIndex = options1;
                tv.setText(sfBeans.get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("身份")
                .setSelectOptions(sfIndex)
                .build();
        pvOptions.setPicker(sfStrBeans);
        pvOptions.show();
    }

    private void showBkgrSfPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                BkgrSfIndex = options1;
                tv.setText(bsfBeans.get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("身份")
                .setSelectOptions(BkgrSfIndex)
                .build();
        pvOptions.setPicker(sfStrBeans);
        pvOptions.show();
    }

    private void showqtSfPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                QtSfIndex = options1;
                tv.setText(qtSfBeans.get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("其他身份")
                .setSelectOptions(QtSfIndex)
                .build();
        pvOptions.setPicker(qtSfStrBeans);
        pvOptions.show();
    }

    public void getGjDataForcChild(int pos,EditText tv) {
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
                        showGjPickerView(tv,pos);
                    } else {
                        XToast.error(UnderageAccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(UnderageAccusationActivity.this, throwable.getMessage()).show();
                });
    }


    /**
     * 国籍
     */
    private void showGjPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
//                gjIndex = options1;
                tv.setText(gjBeans.get(options1).getName());
                checkIndexGj[where] = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(checkIndexGj[where])
                .build();
        pvOptions.setPicker(gjStrBeans);
        pvOptions.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etPhone.setText(sectionParentEntity.getName() + "");
        }else if(requestCode==120&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etGzdwxxszd.setText(sectionParentEntity.getName() + "");
        }else if(requestCode==130&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etBkgrDwmc.setText(sectionParentEntity.getName() + "");
        }else if(requestCode==140&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etWdwszd.setText(sectionParentEntity.getName() + "");
        }else if(requestCode==150&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etAfd.setText(sectionParentEntity.getName() + "");
        }else if (requestCode == PictureConfig.CHOOSE_REQUEST||requestCode == PictureConfig.REQUEST_CAMERA) {
            //相册返回
            // 图片选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList!=null&&selectList.size()>0){
                switch (clIndex){
                    case 0:{//控告
                        Glide.with(UnderageAccusationActivity.this).load(selectList.get(0).getPath()).into(imgKgcl);
                    }break;
                    case 1:{//证据
                        Glide.with(UnderageAccusationActivity.this).load(selectList.get(0).getPath()).into(imgZjcl);
                    }break;
                    case 2:{//其他
                        Glide.with(UnderageAccusationActivity.this).load(selectList.get(0).getPath()).into(imgQtcl);
                    }break;
                }
                selectPhoto.put(clIndex,selectList.get(0));
            }
        }else if(requestCode==160&&resultCode==123){
            SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
            etEmail.setText(sectionParentEntity.getName() + "");
        }
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

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_underage_accusation;
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

package com.qinyue.monitor.work;

import android.content.Intent;
import android.os.Bundle;
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

public class AdministrativeLitigationActivity extends BaseActivity {
    @BindView(R.id.et_yyagx_ms)
    TextView yagxTv;
    @BindView(R.id.et_xb)
    TextView sexTv;
    @BindView(R.id.et_zjlx)
    TextView zjlxTv;
    @BindView(R.id.et_gj)
    TextView gjTv;
    @BindView(R.id.et_dlrzjlx)
    TextView dZjlxTv;
    @BindView(R.id.et_bkgr_yyagx)
    TextView bYagxTv;
    @BindView(R.id.et_bkgr_xb)
    TextView bSexTv;
    @BindView(R.id.et_bkgr_gj)
    TextView bGjTv;
    @BindView(R.id.et_afd)
    TextView afdTv;
    @BindView(R.id.et_yscp)
    TextView yTimeTv;
    @BindView(R.id.et_escp)
    TextView eTimeTv;
    @BindView(R.id.et_scp)
    TextView zTimeTv;
    @BindView(R.id.et_zongscp)
    TextView zsTimeTv;
    @BindView(R.id.et_fyslzsrq)
    TextView fyslzsTimeTv;
    @BindView(R.id.content)
    MultiLineEditText contentTv;

    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.et_zjhm)
    EditText zjhmEdit;
    @BindView(R.id.et_yyagx)
    EditText gzdwNameEdit;
    @BindView(R.id.et_szd)
    EditText szdEdit;
    @BindView(R.id.et_phone)
    EditText phoneEdit;
    @BindView(R.id.et_email)
    EditText emailEdit;
    @BindView(R.id.et_fdname)
    EditText fdNameEdit;
    @BindView(R.id.et_fddbzw)
    EditText fdZwEdit;
    @BindView(R.id.et_dlname)
    EditText dNameEdit;
    @BindView(R.id.et_dlrzjhm)
    EditText dZjhmEdit;
    @BindView(R.id.et_dlrgzdw)
    EditText dGzdwEdit;
    @BindView(R.id.et_sddz)
    EditText dSddzEdit;
    @BindView(R.id.et_bkgr_name)
    EditText bNameEdit;
    @BindView(R.id.et_bkgr_dwmc)
    EditText bGzdwEdit;
    @BindView(R.id.et_bkgr_dwdz)
    EditText bGzdzEdit;
    @BindView(R.id.et_bkgr_zw)
    EditText bSddzEdit;
    @BindView(R.id.et_pcywjg)
    EditText yFyEdit;
    @BindView(R.id.et_yscpry)
    EditText yCpEdit;
    @BindView(R.id.et_yscpsw)
    EditText yWshEdit;
    @BindView(R.id.et_esfy)
    EditText eFyEdit;
    @BindView(R.id.et_escpry)
    EditText eCpEdit;
    @BindView(R.id.et_escpsw)
    EditText eWshEdit;
    @BindView(R.id.et_zsfy)
    EditText zFyEdit;
    @BindView(R.id.et_zscpry)
    EditText zCpEdit;
    @BindView(R.id.et_zscpsw)
    EditText zWshEdit;
    @BindView(R.id.et_zongsfy)
    EditText zsFyEdit;
    @BindView(R.id.et_zongscpry)
    EditText zsCpEdit;
    @BindView(R.id.et_zongscpsw)
    EditText zsWshEdit;
    @BindView(R.id.et_fyslsqswh)
    EditText fyslzsWshEdit;


    @BindView(R.id.img_kgcl)
    ImageView mhjdImg;
    @BindView(R.id.img_xgzjcl)
    ImageView qtImg;
    @BindView(R.id.img_fcws)
    ImageView sfzImg;
    @BindView(R.id.img_zjcl)
    ImageView wsImg;

    private String[] yyagxStrings = {"附带民事诉讼原告人", "附带民事诉讼被告人", "犯罪嫌疑人", "犯罪嫌疑人近亲属", "被告人", "被告人近亲属", "诉讼代理人", "利害关系人", "民事案件原告", "民事案件被告", "第三人", "案外人", "行政案件原告", "行政案件被告", "法定代理人", "被害人", "被害人近亲属", "其他"};

    private int[] checkIndexSex = {-1, -1};
    private int[] checkIndexYyagx = {-1, -1};
    private int[] checkIndexZjlx = {-1, -1};
    private int[] checkIndexGj = {-1, -1};
    private int clIndex = 0;
    SparseArray<LocalMedia> selectPhoto = new SparseArray<>();
    SparseArray<UpDataFileBean> fileIds = new SparseArray<>();
    List<MzBean> zjBeans;
    List<String> zjStrBeans = new ArrayList<>();
    List<MzBean> gjBeans;
    List<String> gjStrBeans = new ArrayList<>();

    SelectSectionParentEntity afdBean;

    @Override
    public String initTitleText() {
        return "行政申诉";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (UserUtils.getSex().equals("男")) {
            checkIndexSex[0] = 0;
        } else {
            checkIndexSex[0] = 1;
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
        return R.layout.activity_civil_action;
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
        return false;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_submit, R.id.img_xgzjcl, R.id.img_fcws, R.id.img_zjcl, R.id.img_kgcl, R.id.rl_fyzs_time, R.id.rl_zs_time, R.id.rl_z_time, R.id.rl_e_time, R.id.re_y_time, R.id.rl_afd, R.id.rl_bkgr_gj, R.id.rl_bkgr_xb, R.id.rl_bkgr_yyagx, R.id.rl_dlrzjlx, R.id.rl_gj, R.id.rl_zjlx, R.id.rl_xb, R.id.rl_yyagx_ms})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit: {//提交
                if (nameEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请输入申诉人姓名或单位名称").show();
                    break;
                }
                if (yagxTv.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请选择申诉人与原案关系").show();
                    break;
                }
                if (zjlxTv.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请选择申诉人证件类型").show();
                    break;
                }
                if (zjhmEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请输入申诉人证件号码").show();
                    break;
                }
                if ("居民身份证".equals(zjlxTv.getText().toString().trim())&&!RegexUtils.isIDCard18(zjhmEdit.getText().toString().trim())){
                    XToast.info(AdministrativeLitigationActivity.this,"证件号码错误").show();
                    break;
                }
                if (szdEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请输入您的居所地").show();
                    break;
                }
                if (phoneEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请输入您的电话号码").show();
                    break;
                }
                if (!RegexUtils.isMobileSimple(phoneEdit.getText().toString().trim())){
                    XToast.info(AdministrativeLitigationActivity.this,"电话号码错误").show();
                    break;
                }
                if (!emailEdit.getText().toString().trim().isEmpty()&&!RegexUtils.isEmail(emailEdit.getText().toString().trim())){
                    XToast.info(this,"电子邮箱格式错误").show();
                    break;
                }
                if (bNameEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请输入被申诉人姓名").show();
                    break;
                }
                if (bYagxTv.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请选择被申诉人与原案关系").show();
                    break;
                }
                if (bGzdwEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请输入被申诉人工作单位全称").show();
                    break;
                }
                if (afdTv.getText().toString().trim().isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请选择案发地").show();
                    break;
                }
                if (contentTv.isEmpty()){
                    XToast.info(AdministrativeLitigationActivity.this,"请输入案情摘要").show();
                    break;
                }
                miniLoadingDialog.show();
                if (selectPhoto.size()>0){
                    fileIds.clear();
                    upLoadFiles(0);
                }else {
                    submit();
                }
            }
            break;
            case R.id.btn_cancel: {//取消
                finish();
            }
            break;
            case R.id.img_kgcl: {//民行监督申请书
                clIndex = 0;
                showChoosePhotoDialog();
            }
            break;
            case R.id.img_zjcl: {//法律文书
                clIndex = 1;
                showChoosePhotoDialog();
            }
            break;
            case R.id.img_fcws: {//身份证
                clIndex = 2;
                showChoosePhotoDialog();
            }
            break;
            case R.id.img_xgzjcl: {//其他
                clIndex = 3;
                showChoosePhotoDialog();
            }
            break;
            case R.id.rl_yyagx_ms: {//申诉人与原案关系
                showYyagxPickerView(0, yagxTv);
            }
            break;
            case R.id.rl_bkgr_yyagx: {//被申诉人与原案关系
                showYyagxPickerView(1, bYagxTv);
            }
            break;
            case R.id.rl_xb: {//申诉人性别
                showSimpleBottomSheetList(0, sexTv);
            }
            break;
            case R.id.rl_bkgr_xb: {//被申诉人性别
                showSimpleBottomSheetList(1, bSexTv);
            }
            break;
            case R.id.rl_zjlx: {//申诉人证件类型
                if (zjBeans == null) {
                    getZjDataForcChild(0, zjlxTv);
                } else {
                    showZjPickerView(0, zjlxTv);
                }
            }
            break;
            case R.id.rl_dlrzjlx: {//代理人证件类型
                if (zjBeans == null) {
                    getZjDataForcChild(1, dZjlxTv);
                } else {
                    showZjPickerView(1, dZjlxTv);
                }
            }
            break;
            case R.id.rl_gj: {//申诉人国籍
                if (gjBeans == null) {
                    getGjDataForcChild(0, gjTv);
                } else {
                    showGjPickerView(0, gjTv);
                }
            }
            break;
            case R.id.rl_bkgr_gj: {//被申诉人国籍
                if (gjBeans == null) {
                    getGjDataForcChild(1, bGjTv);
                } else {
                    showGjPickerView(1, bGjTv);
                }
            }
            break;
            case R.id.rl_afd: {//案发地
                Intent intent = new Intent(AdministrativeLitigationActivity.this, ChooseAfdActivity.class);
                intent.putExtra("index", 0);
                startActivityForResult(intent, 101);
            }
            break;
            case R.id.re_y_time: {//一审日期
                showDatePicker(yTimeTv);
            }
            break;
            case R.id.rl_e_time: {//二审日期
                showDatePicker(eTimeTv);
            }
            break;
            case R.id.rl_z_time: {//再审日期
                showDatePicker(zTimeTv);
            }
            break;
            case R.id.rl_zs_time: {//终审日期
                showDatePicker(zsTimeTv);
            }
            break;
            case R.id.rl_fyzs_time: {//法院受理再审日期
                showDatePicker(fyslzsTimeTv);
            }
            break;
        }
    }

    /**
     * 与原案关系
     */
    private void showYyagxPickerView(int pos, TextView textView) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                textView.setText(yyagxStrings[options1]);
                checkIndexYyagx[pos] = options1;
                return false;
            }
        })
                .setTitleText("与原案关系")
                .setSelectOptions(checkIndexYyagx[pos])
                .build();
        pvOptions.setPicker(yyagxStrings);
        pvOptions.show();
    }

    private void showSimpleBottomSheetList(int pos, TextView textView) {
        if (pos == 0) {
            new BottomSheet.BottomListSheetBuilder(this)
                    .setTitle("请选择性别")
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
        } else {
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

    }

    /**
     * 证件类型
     */
    private void showZjPickerView(int pos, TextView textView) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                textView.setText(zjBeans.get(options1).getName());
                checkIndexZjlx[pos] = options1;
                return false;
            }
        })
                .setTitleText("证件类型")
                .setSelectOptions(checkIndexZjlx[pos])
                .build();
        pvOptions.setPicker(zjStrBeans);
        pvOptions.show();
    }

    public void getZjDataForcChild(int pos, TextView textView) {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "1")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        zjBeans = s.getData();
                        for (int i = 0; i < zjBeans.size(); i++) {
                            zjStrBeans.add(zjBeans.get(i).getName());
                        }
                        showZjPickerView(pos, textView);
                    } else {
                        XToast.error(AdministrativeLitigationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AdministrativeLitigationActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 国籍
     */
    private void showGjPickerView(int pos, TextView tv) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                tv.setText(gjBeans.get(options1).getName());
                checkIndexGj[pos] = options1;
                return false;
            }
        })
                .setTitleText("国籍")
                .setSelectOptions(checkIndexGj[pos])
                .build();
        pvOptions.setPicker(gjStrBeans);
        pvOptions.show();
    }

    public void getGjDataForcChild(int pos, TextView tv) {
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
                        showGjPickerView(pos, tv);
                    } else {
                        XToast.error(AdministrativeLitigationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AdministrativeLitigationActivity.this, throwable.getMessage()).show();
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 123) {//案发地
            afdBean = (SelectSectionParentEntity) data.getSerializableExtra("data");
            afdTv.setText(afdBean.getName());
        }
        if (requestCode == PictureConfig.CHOOSE_REQUEST || requestCode == PictureConfig.REQUEST_CAMERA) {
            //相册返回
            // 图片选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList != null && selectList.size() > 0) {
                switch (clIndex) {
                    case 0: {//民行监督申请书
                        Glide.with(AdministrativeLitigationActivity.this).load(selectList.get(0).getPath()).into(mhjdImg);
                    }
                    break;
                    case 1: {//法律文书
                        Glide.with(AdministrativeLitigationActivity.this).load(selectList.get(0).getPath()).into(wsImg);
                    }
                    break;
                    case 2: {//身份证
                        Glide.with(AdministrativeLitigationActivity.this).load(selectList.get(0).getPath()).into(sfzImg);
                    }
                    break;
                    case 3: {//其他
                        Glide.with(AdministrativeLitigationActivity.this).load(selectList.get(0).getPath()).into(qtImg);
                    }
                    break;
                }
                selectPhoto.put(clIndex, selectList.get(0));
            }
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
                            PictureSelector.create(AdministrativeLitigationActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .maxSelectNum(1)
                                    .loadImageEngine(GlideEngine.createGlideEngine())
                                    .forResult(PictureConfig.REQUEST_CAMERA);
                        } else {
                            PictureSelector.create(AdministrativeLitigationActivity.this)
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

    /**
     * 日期选择
     */
    private void showDatePicker(TextView textView) {
        TimePickerView build = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
                textView.setText(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                    }
                })
                .setTitleText("日期选择")
                .build();
        build.show();
    }
    private int retry = 0;
    private void upLoadFiles(int index){
        if (selectPhoto.get(index)==null&&index<3){
            upLoadFiles(index+1);
            return;
        }
        if (index>3){
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
                            XToast.error(AdministrativeLitigationActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                        }else {
                            upLoadFiles(index);
                        }
                    }
                }, throwable -> {
                    retry ++;
                    if (retry==3){
                        miniLoadingDialog.dismiss();
                        XToast.error(AdministrativeLitigationActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                    }else {
                        upLoadFiles(index);
                    }
                });
    }
    private void submit() {
        Map<String,String> map = new HashMap<>();
        map.put("source",TagConstant.SOURCE);
        map.put("plaintiffName",nameEdit.getText().toString().trim());
        map.put("relationship",checkIndexYyagx[0]==-1?"":yyagxStrings[checkIndexYyagx[0]]);
        map.put("plaintiffSex",sexTv.getText().toString().trim());
        map.put("plaintiffCertificateType",checkIndexZjlx[0]==-1?"":zjBeans.get(checkIndexZjlx[0]).getCode());
        map.put("plaintiffCertificateNumber",zjhmEdit.getText().toString().trim());
        map.put("plaintiffNationality",checkIndexGj[0]==-1?"":gjBeans.get(checkIndexGj[0]).getCode());
        map.put("plaintiffUnit",gzdwNameEdit.getText().toString().trim());
        map.put("plaintiffResidence",szdEdit.getText().toString().trim());
        map.put("plaintiffPhone",phoneEdit.getText().toString().trim());
        map.put("plaintiffEmail",emailEdit.getText().toString().trim());
        map.put("corporationName",fdNameEdit.getText().toString().trim());
        map.put("corporationDefendantDuty",fdZwEdit.getText().toString().trim());
        map.put("agentName",dNameEdit.getText().toString().trim());
        map.put("agentCertificateType",checkIndexZjlx[1]==-1?"":zjBeans.get(checkIndexZjlx[1]).getCode());
        map.put("agentCertificateNumber",dZjhmEdit.getText().toString().trim());
        map.put("agentUnit",dGzdwEdit.getText().toString().trim());
        map.put("deliveryAddress",dSddzEdit.getText().toString().trim());
        map.put("defendantName",bNameEdit.getText().toString().trim());
        map.put("relationship2",checkIndexYyagx[1]==-1?"":yyagxStrings[checkIndexYyagx[1]]);
        map.put("defendantSex",bSexTv.getText().toString().trim());
        map.put("defendantNationality",checkIndexGj[1]==-1?"":gjBeans.get(checkIndexGj[1]).getCode());
        map.put("defendantUintFullName",bGzdwEdit.getText().toString().trim());
        map.put("defendantUnitLocation",bGzdzEdit.getText().toString().trim());
        map.put("deliveryAddress2",bSddzEdit.getText().toString().trim());
        map.put("organizationCode",TagConstant.CODE);
        map.put("venueCode",afdBean==null?"":afdBean.getCode());
        map.put("court",yFyEdit.getText().toString().trim());
        map.put("refereeDate",yTimeTv.getText().toString().trim());
        map.put("referee",yCpEdit.getText().toString().trim());
        map.put("documentNumber",yWshEdit.getText().toString().trim());
        map.put("court2",eFyEdit.getText().toString().trim());
        map.put("refereeDate2",eTimeTv.getText().toString().trim());
        map.put("referee2",eCpEdit.getText().toString().trim());
        map.put("documentNumber2",eWshEdit.getText().toString().trim());
        map.put("court3",zFyEdit.getText().toString().trim());
        map.put("refereeDate3",zTimeTv.getText().toString().trim());
        map.put("referee3",zCpEdit.getText().toString().trim());
        map.put("documentNumber3",zWshEdit.getText().toString().trim());
        map.put("court4",zsFyEdit.getText().toString().trim());
        map.put("refereeDate4",zsTimeTv.getText().toString().trim());
        map.put("referee4",zsCpEdit.getText().toString().trim());
        map.put("documentNumber4",zsWshEdit.getText().toString().trim());
        map.put("retrialDate",fyslzsTimeTv.getText().toString().trim());
        map.put("documentNumber5",fyslzsWshEdit.getText().toString().trim());
        map.put("content",contentTv.getContentText());
        map.put("attachmentFile1",fileIds.get(0)==null?"":fileIds.get(0).getFileId());
        map.put("attachmentFile2",fileIds.get(1)==null?"":fileIds.get(1).getFileId());
        map.put("attachmentFile3",fileIds.get(2)==null?"":fileIds.get(2).getFileId());
        map.put("attachmentFile4",fileIds.get(3)==null?"":fileIds.get(3).getFileId());
        map.put("userId", UserUtils.getId());
        map.put("userKeyId",UserUtils.getKeyId());
        map.put("username",UserUtils.getUserName());
        map.put("userRealName",UserUtils.getRealName());
        String ss = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY,ss);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.admAppeal)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        XToast.success(AdministrativeLitigationActivity.this,s.getMessage()).show();
                        finish();
                    }else {
                        XToast.error(AdministrativeLitigationActivity.this,s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AdministrativeLitigationActivity.this,throwable.getMessage()).show();
                });

    }
}

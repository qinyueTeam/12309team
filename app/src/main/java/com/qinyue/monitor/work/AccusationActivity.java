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
import com.qinyue.monitor.first.AddJczxxActivity;
import com.qinyue.monitor.first.JwjdActivty;
import com.qinyue.monitor.first.MzBean;
import com.qinyue.monitor.first.UpDataFileBean;
import com.qinyue.monitor.login.RegisterActivity;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.qinyue.monitor.view.ChooseAfdActivity;
import com.qinyue.monitor.view.ChooseSfActivity;
import com.qinyue.monitor.view.SelectSectionParentEntity;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
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

public class AccusationActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.et_zjhm)
    EditText zjhmEdit;
    @BindView(R.id.et_bkgr_name)
    EditText bNameEdit;
    @BindView(R.id.et_phone)
    EditText phoneEdit;
    @BindView(R.id.et_email)
    EditText emailEdit;
    @BindView(R.id.et_gzdw)
    EditText gzdwEdit;
    @BindView(R.id.et_bkgr_dwmc)
    EditText bGzdwEdit;
    @BindView(R.id.et_szd)
    EditText jsdEdit;
    @BindView(R.id.et_bkgr_dwdz)
    EditText bDwdzEdit;
    @BindView(R.id.et_bkgr_zw)
    EditText bZwEdit;
    @BindView(R.id.et_xb)
    TextView xbTv;
    @BindView(R.id.et_bkgr_xb)
    TextView bXbTv;
    @BindView(R.id.et_zjlx)
    TextView zjlxTv;
    @BindView(R.id.et_mz)
    TextView mzTv;
    @BindView(R.id.et_gj)
    TextView gjTv;
    @BindView(R.id.et_bkgr_gj)
    TextView bGjTv;
    @BindView(R.id.et_bkgr_zj)
    TextView bZjTv;
    @BindView(R.id.et_bkgr_sf)
    TextView bSfTv;
    @BindView(R.id.et_bkgr_rddb)
    TextView bRddbTv;
    @BindView(R.id.et_afd)
    TextView bAfdTv;
    @BindView(R.id.et_bkgr_zxwy)
    TextView bZxwyTv;
    @BindView(R.id.content)
    MultiLineEditText contentTv;
    @BindView(R.id.img_kgcl)
    ImageView kgclImg;
    @BindView(R.id.img_zjcl)
    ImageView zjclImg;
    @BindView(R.id.img_qtcl)
    ImageView qtlImg;

    private int[] checkIndexSex = {-1, -1};
    private int[] checkIndexGj = {-1, -1};

    List<MzBean> mzBeans;
    List<String> mzStrBeans = new ArrayList<>();
    List<MzBean> zjBeans;
    List<String> zjStrBeans = new ArrayList<>();
    List<MzBean> gjBeans;
    List<String> gjStrBeans = new ArrayList<>();
    List<MzBean> bZjBeans;
    List<String> bZjStrBeans = new ArrayList<>();
    private int mzIndex = -1;
    List<MzBean> rddbBeans;
    List<String> rddbStrBeans = new ArrayList<>();
    private int rddbIndex = -1;
    private int zjIndex = -1;
    private int bZjIndex = -1;
    private int zxwyIndex = -1;
    //身份的对象
    SelectSectionParentEntity sfBean;
    SelectSectionParentEntity afdBean;
    private int clIndex = 0;
    SparseArray<LocalMedia> selectPhoto = new SparseArray<>();
    SparseArray<UpDataFileBean> fileIds = new SparseArray<>();
    @Override
    public String initTitleText() {
        return "控告";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (UserUtils.getSex().equals("男")){
            checkIndexSex[0] = 0;
        }else {
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
        return R.layout.activity_accusation;
    }

    @Override
    protected void init() {

    }
    public void getMzDataForcChild() {
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
                        showMzPickerView();
                    } else {
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }

    public void getZjDataForcChild() {
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
                        showZjPickerView();
                    } else {
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }
    public void getBzjDataForcChild() {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "4")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        bZjBeans = s.getData();
                        for (int i = 0; i < bZjBeans.size(); i++) {
                            bZjStrBeans.add(bZjBeans.get(i).getName());
                        }
                        showBzjPickerView();
                    } else {
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }
    public void getRddbDataForcChild() {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "6")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        rddbBeans = s.getData();
                        for (int i = 0; i < rddbBeans.size(); i++) {
                            rddbStrBeans.add(rddbBeans.get(i).getName());
                        }
                        showRddbPickerView();
                    } else {
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }
    public void getGjDataForcChild(int pos,TextView tv) {
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
                        showGjPickerView(pos,tv);
                    } else {
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }

    @OnClick({R.id.btn_cancel,R.id.btn_submit,R.id.img_qtcl,R.id.img_zjcl,R.id.img_kgcl,R.id.rl_xb,R.id.rl_zjlx,R.id.rl_mz,R.id.rl_gj,R.id.rl_bkgr_xb,R.id.rl_bkgr_gj,R.id.rl_bkgr_zj,R.id.rl_bkgr_sf,R.id.rl_bkgr_rddb,R.id.rl_bkgr_zxwy,R.id.rl_bkgr_afd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_xb: {//性别
                showSimpleBottomSheetList(0,xbTv);
            }
            break;
            case R.id.rl_zjlx: {//证件类型
                if (zjBeans== null) {
                    getZjDataForcChild();
                } else {
                    showZjPickerView();
                }
            }
            break;
            case R.id.rl_mz: {//民族
                if (mzBeans == null) {
                    getMzDataForcChild();
                } else {
                    showMzPickerView();
                }
            }
            break;
            case R.id.rl_gj: {//国籍
                if (gjBeans == null) {
                    getGjDataForcChild(0,gjTv);
                } else {
                    showGjPickerView(0,gjTv);
                }
            }
            break;
            case R.id.rl_bkgr_xb: {//被控告人性别
                showSimpleBottomSheetList(1,bXbTv);
            }
            break;
            case R.id.rl_bkgr_gj: {//被控告人国籍
                if (gjBeans == null) {
                    getGjDataForcChild(1,bGjTv);
                } else {
                    showGjPickerView(1,bGjTv);
                }
            }
            break;
            case R.id.rl_bkgr_zj: {//被控告人职级
                if (bZjBeans== null) {
                    getBzjDataForcChild();
                } else {
                    showBzjPickerView();
                }
            }
            break;
            case R.id.rl_bkgr_sf: {//被控告人身份
                Intent intent = new Intent(AccusationActivity.this, ChooseSfActivity.class);
                intent.putExtra("index", 0);
                startActivityForResult(intent, 102);
            }
            break;
            case R.id.rl_bkgr_rddb: {//被控告人人大代表
                if (rddbBeans == null) {
                    getRddbDataForcChild();
                } else {
                    showRddbPickerView();
                }
            }
            break;
            case R.id.rl_bkgr_zxwy: {//被控告人政协委员
                showZxwyList();
            }
            break;
            case R.id.rl_bkgr_afd: {//被控告人案发地
                Intent intent = new Intent(AccusationActivity.this, ChooseAfdActivity.class);
                intent.putExtra("index", 0);
                startActivityForResult(intent, 101);
            }
            break;
            case R.id.img_kgcl: {//控告材料
                clIndex = 0;
                showChoosePhotoDialog();
            }
            break;
            case R.id.img_zjcl: {//证据材料
                clIndex = 1;
                showChoosePhotoDialog();
            }
            break;
            case R.id.img_qtcl: {//其他材料
                clIndex = 2;
                showChoosePhotoDialog();
            }
            break;
            case R.id.btn_submit: {//提交
                if (nameEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AccusationActivity.this,"请输入控告人姓名或单位名称").show();
                    break;
                }
                if (zjlxTv.getText().toString().trim().isEmpty()){
                    XToast.info(AccusationActivity.this,"请选择证件类型").show();
                    break;
                }
                if (zjhmEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AccusationActivity.this,"请输入证件号码").show();
                    break;
                }
                if ("居民身份证".equals(zjlxTv.getText().toString().trim())&&!RegexUtils.isIDCard18(zjhmEdit.getText().toString().trim())){
                    XToast.info(AccusationActivity.this,"证件号码错误").show();
                    break;
                }
                if (phoneEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AccusationActivity.this,"请输入电话号码").show();
                    break;
                }
                if (!RegexUtils.isMobileSimple(phoneEdit.getText().toString().trim())){
                    XToast.info(AccusationActivity.this,"电话号码错误").show();
                    break;
                }
                if (!emailEdit.getText().toString().trim().isEmpty()&&!RegexUtils.isEmail(emailEdit.getText().toString().trim())){
                    XToast.info(AccusationActivity.this,"电子邮箱格式错误").show();
                    break;
                }
                if (jsdEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AccusationActivity.this,"请输入您的居所地").show();
                    break;
                }
                if (bNameEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AccusationActivity.this,"请输入被控告人姓名").show();
                    break;
                }
                if (bGzdwEdit.getText().toString().trim().isEmpty()){
                    XToast.info(AccusationActivity.this,"请输入被控告人工作单位全称").show();
                    break;
                }
                if (bAfdTv.getText().toString().trim().isEmpty()){
                    XToast.info(AccusationActivity.this,"请选择案发地").show();
                    break;
                }
                if (contentTv.isEmpty()){
                    XToast.info(AccusationActivity.this,"请输入内容").show();
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
        }
    }
    /**
     * 民族
     */
    private void showMzPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                mzTv.setText(mzBeans.get(options1).getName());
                mzIndex = options1;
                return false;
            }
        })
                .setTitleText("民族")
                .setSelectOptions(mzIndex)
                .build();
        pvOptions.setPicker(mzStrBeans);
        pvOptions.show();
    }
    /**
     * 证件类型
     */
    private void showZjPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                zjlxTv.setText(zjBeans.get(options1).getName());
                zjIndex = options1;
                return false;
            }
        })
                .setTitleText("证件类型")
                .setSelectOptions(zjIndex)
                .build();
        pvOptions.setPicker(zjStrBeans);
        pvOptions.show();
    }
    /**
     * 职级
     */
    private void showBzjPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                bZjTv.setText(bZjBeans.get(options1).getName());
                bZjIndex = options1;
                return false;
            }
        })
                .setTitleText("职级")
                .setSelectOptions(bZjIndex)
                .build();
        pvOptions.setPicker(bZjStrBeans);
        pvOptions.show();
    }

    /**
     * 人大代表
     */
    private void showRddbPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                bRddbTv.setText(rddbBeans.get(options1).getName());
                rddbIndex = options1;
                return false;
            }
        })
                .setTitleText("人大代表")
                .setSelectOptions(rddbIndex)
                .build();
        pvOptions.setPicker(rddbStrBeans);
        pvOptions.show();
    }
    /**
     * 国籍
     */
    private void showGjPickerView(int pos,TextView tv) {
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
    private void showSimpleBottomSheetList(int pos, TextView textView) {
        if (pos==0){
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
        }else {
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
     * 政协委员
     */
    private void showZxwyList() {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("政协委员")
                .addItem("县级")
                .addItem("地市")
                .addItem("省级")
                .addItem("全国")
                .setCheckedIndex(zxwyIndex)
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        zxwyIndex = position;
                        bZxwyTv.setText(tag);
                    }
                })
                .build().show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==102&&resultCode==124){//身份
            sfBean = (SelectSectionParentEntity) data.getSerializableExtra("data");
            bSfTv.setText(sfBean.getName());
        }
        if (requestCode==101&&resultCode==123){//案发地
            afdBean = (SelectSectionParentEntity) data.getSerializableExtra("data");
            bAfdTv.setText(afdBean.getName());
        }
        if (requestCode == PictureConfig.CHOOSE_REQUEST||requestCode == PictureConfig.REQUEST_CAMERA) {
            //相册返回
            // 图片选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList!=null&&selectList.size()>0){
                switch (clIndex){
                    case 0:{//控告
                        Glide.with(AccusationActivity.this).load(selectList.get(0).getPath()).into(kgclImg);
                    }break;
                    case 1:{//证据
                        Glide.with(AccusationActivity.this).load(selectList.get(0).getPath()).into(zjclImg);
                    }break;
                    case 2:{//其他
                        Glide.with(AccusationActivity.this).load(selectList.get(0).getPath()).into(qtlImg);
                    }break;
                }
                selectPhoto.put(clIndex,selectList.get(0));
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
                            PictureSelector.create(AccusationActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .maxSelectNum(1)
                                    .loadImageEngine(GlideEngine.createGlideEngine())
                                    .forResult(PictureConfig.REQUEST_CAMERA);
                        } else {
                            PictureSelector.create(AccusationActivity.this)
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
                            XToast.error(AccusationActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                        }else {
                            upLoadFiles(index);
                        }
                    }
                }, throwable -> {
                    retry ++;
                    if (retry==3){
                        miniLoadingDialog.dismiss();
                        XToast.error(AccusationActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                    }else {
                        upLoadFiles(index);
                    }
                });
    }

    private void submit() {
        Map<String,String> map = new HashMap<>();
        map.put("source",TagConstant.SOURCE);
        map.put("plaintiffName",nameEdit.getText().toString().trim());
        map.put("plaintiffSex",xbTv.getText().toString().trim());
        map.put("plaintiffCertificateType",zjIndex==-1?"":zjBeans.get(zjIndex).getCode());
        map.put("plaintiffCertificateNumber",zjhmEdit.getText().toString().trim());
        map.put("plaintiffNation",mzIndex==-1?"":mzBeans.get(mzIndex).getCode());
        map.put("plaintiffNationality",checkIndexGj[0]==-1?"":gjBeans.get(checkIndexGj[0]).getCode());
        map.put("plaintiffPhone",phoneEdit.getText().toString().trim());
        map.put("plaintiffEmail",emailEdit.getText().toString().trim());
        map.put("plaintiffUnit",gzdwEdit.getText().toString().trim());
        map.put("plaintiffResidence",jsdEdit.getText().toString().trim());

        map.put("defendantName",bNameEdit.getText().toString().trim());
        map.put("defendantSex",bXbTv.getText().toString().trim());
        map.put("defendantNationality",checkIndexGj[1]==-1?"":gjBeans.get(checkIndexGj[1]).getCode());
        map.put("defendantUintFullName",bGzdwEdit.getText().toString().trim());
        map.put("defendantUnitLocation",bDwdzEdit.getText().toString().trim());
        map.put("defendantDuty",bZwEdit.getText().toString().trim());
        map.put("defendantRank",bZjIndex==-1?"":bZjBeans.get(bZjIndex).getCode());
        map.put("defendantIdentity",sfBean==null?"":sfBean.getCode());
        map.put("defendantNPC",rddbIndex==-1?"":rddbBeans.get(rddbIndex).getCode());
        map.put("defendantCPPCC",bZxwyTv.getText().toString().trim());
        map.put("organizationCode",TagConstant.CODE);
        map.put("venueCode",afdBean==null?"":afdBean.getCode());
        map.put("content",contentTv.getContentText());
        map.put("attachmentFile1",fileIds.get(0)==null?"":fileIds.get(0).getFileId());
        map.put("attachmentFile2",fileIds.get(1)==null?"":fileIds.get(1).getFileId());
        map.put("attachmentFile3",fileIds.get(2)==null?"":fileIds.get(2).getFileId());
        map.put("userId",UserUtils.getId());
        map.put("userKeyId",UserUtils.getKeyId());
        map.put("username",UserUtils.getUserName());
        map.put("userRealName",UserUtils.getRealName());
        String ss = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY,ss);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.accuse)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        XToast.success(AccusationActivity.this,s.getMessage()).show();
                        finish();
                    }else {
                        XToast.error(AccusationActivity.this,s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this,throwable.getMessage()).show();
                });

    }
}

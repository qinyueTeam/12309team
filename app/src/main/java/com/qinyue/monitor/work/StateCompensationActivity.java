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

public class StateCompensationActivity extends BaseActivity {
    @BindView(R.id.et_zjlx)
    TextView zjlxTv;
    @BindView(R.id.et_xb)
    TextView sexTv;
    @BindView(R.id.et_mz)
    TextView mzTv;
    @BindView(R.id.et_dlrzjlx)
    TextView dZjlxTv;
    @BindView(R.id.et_afd)
    TextView afdTv;
    @BindView(R.id.et_pcywjg)
    TextView pcywTv;
    @BindView(R.id.et_gj)
    TextView gjTv;
    @BindView(R.id.content)
    MultiLineEditText contentEdit;

    @BindView(R.id.et_yyagx)
    EditText gzdwEdit;
    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.et_zjhm)
    EditText zjhmEdit;
    @BindView(R.id.et_szd)
    EditText jsdEdit;
    @BindView(R.id.et_phone)
    EditText phoneEdit;
    @BindView(R.id.et_email)
    EditText emailEdit;
    @BindView(R.id.et_gzdw)
    EditText dNameEdit;
    @BindView(R.id.et_dlrzjhm)
    EditText dZjhmEdit;
    @BindView(R.id.et_dlrgzdw)
    EditText dGzdwEdit;

    @BindView(R.id.img_kgcl)
    ImageView sqsImg;
    @BindView(R.id.img_zjcl)
    ImageView flwsImg;
    @BindView(R.id.img_fcws)
    ImageView sfzImg;
    @BindView(R.id.img_xgzjcl)
    ImageView qtwsImg;

    private String[] pcywStrs = {"公安机关", "检察机关", "审判机关", "其他国家机关"};

    private int[] zjCheckIndex = {-1, -1};
    private int sexIndex = -1;
    private int mzIndex = -1;
    private int gjIndex = -1;
    private int pcywIndex = -1;

    List<MzBean> zjBeans;
    List<String> zjStrBeans = new ArrayList<>();
    List<MzBean> mzBeans;
    List<String> mzStrBeans = new ArrayList<>();
    List<MzBean> gjBeans;
    List<String> gjStrBeans = new ArrayList<>();
    private int clIndex = 0;
    SparseArray<LocalMedia> selectPhoto = new SparseArray<>();
    SparseArray<UpDataFileBean> fileIds = new SparseArray<>();

    SelectSectionParentEntity afdBean;

    @Override
    public String initTitleText() {
        return "国家赔偿";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (UserUtils.getSex().equals("男")) {
            sexIndex = 0;
        } else {
            sexIndex = 1;
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
        return R.layout.activity_state_compensation;
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

    @OnClick({R.id.btn_cancel, R.id.btn_submit, R.id.img_xgzjcl, R.id.img_fcws, R.id.img_zjcl, R.id.img_kgcl, R.id.rl_pcyw, R.id.rl_afd, R.id.rl_dlrzjlx, R.id.rl_gj, R.id.rl_mz, R.id.rl_zjlx, R.id.rl_xb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit: {//提交
                if (nameEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(StateCompensationActivity.this, "请输入赔偿人姓名或单位名称").show();
                    break;
                }
                if (zjlxTv.getText().toString().trim().isEmpty()) {
                    XToast.info(StateCompensationActivity.this, "请选择证件类型").show();
                    break;
                }
                if (zjhmEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(StateCompensationActivity.this, "请输入证件号码").show();
                    break;
                }
                if ("居民身份证".equals(zjlxTv.getText().toString().trim())&&!RegexUtils.isIDCard18(zjhmEdit.getText().toString().trim())){
                    XToast.info(StateCompensationActivity.this,"证件号码错误").show();
                    break;
                }

                if (jsdEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(StateCompensationActivity.this, "请输入您的居所地").show();
                    break;
                }
                if (phoneEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(StateCompensationActivity.this, "请输入电话号码").show();
                    break;
                }
                if (!RegexUtils.isMobileSimple(phoneEdit.getText().toString().trim())){
                    XToast.info(StateCompensationActivity.this,"电话号码错误").show();
                    break;
                }
                if (afdTv.getText().toString().trim().isEmpty()) {
                    XToast.info(StateCompensationActivity.this, "请选择案发地").show();
                    break;
                }
                if (pcywTv.getText().toString().trim().isEmpty()) {
                    XToast.info(StateCompensationActivity.this, "请选择赔偿义务机关").show();
                    break;
                }
                if (contentEdit.isEmpty()) {
                    XToast.info(StateCompensationActivity.this, "请输入案情摘要").show();
                    break;
                }
                miniLoadingDialog.show();
                if (selectPhoto.size() > 0) {
                    fileIds.clear();
                    upLoadFiles(0);
                } else {
                    submit();
                }
            }
            break;
            case R.id.btn_cancel: {//取消
                finish();
            }
            break;
            case R.id.img_kgcl: {//申请书
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
            case R.id.img_xgzjcl: {//其他文书
                clIndex = 3;
                showChoosePhotoDialog();
            }
            break;
            case R.id.rl_pcyw: {//赔偿义务机关
                showYyagxPickerView();
            }
            break;
            case R.id.rl_afd: {//案发地
                Intent intent = new Intent(StateCompensationActivity.this, ChooseAfdActivity.class);
                intent.putExtra("index", 0);
                startActivityForResult(intent, 101);
            }
            break;
            case R.id.rl_zjlx: {//赔偿证件类型
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
            case R.id.rl_xb: {//性别
                showSimpleBottomSheetList();
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
                    getGjDataForcChild();
                } else {
                    showGjPickerView();
                }
            }
            break;
        }
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
                        XToast.error(StateCompensationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(StateCompensationActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 证件类型
     */
    private void showZjPickerView(int pos, TextView textView) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                textView.setText(zjBeans.get(options1).getName());
                zjCheckIndex[pos] = options1;
                return false;
            }
        })
                .setTitleText("证件类型")
                .setSelectOptions(zjCheckIndex[pos])
                .build();
        pvOptions.setPicker(zjStrBeans);
        pvOptions.show();
    }

    private void showSimpleBottomSheetList() {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("请选择性别")
                .addItem("男")
                .addItem("女")
                .setCheckedIndex(sexIndex)
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        sexIndex = position;
                        sexTv.setText(tag);
                    }
                })
                .build().show();


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
                        XToast.error(StateCompensationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(StateCompensationActivity.this, throwable.getMessage()).show();
                });
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

    public void getGjDataForcChild() {
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
                        showGjPickerView();
                    } else {
                        XToast.error(StateCompensationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(StateCompensationActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 国籍
     */
    private void showGjPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                gjTv.setText(gjBeans.get(options1).getName());
                gjIndex = options1;
                return false;
            }
        })
                .setTitleText("国籍")
                .setSelectOptions(gjIndex)
                .build();
        pvOptions.setPicker(gjStrBeans);
        pvOptions.show();
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
                    case 0: {//赔偿申请书
                        Glide.with(StateCompensationActivity.this).load(selectList.get(0).getPath()).into(sqsImg);
                    }
                    break;
                    case 1: {//法律文书
                        Glide.with(StateCompensationActivity.this).load(selectList.get(0).getPath()).into(flwsImg);
                    }
                    break;
                    case 2: {//身份证
                        Glide.with(StateCompensationActivity.this).load(selectList.get(0).getPath()).into(sfzImg);
                    }
                    break;
                    case 3: {//其他文书
                        Glide.with(StateCompensationActivity.this).load(selectList.get(0).getPath()).into(qtwsImg);
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
                            PictureSelector.create(StateCompensationActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .maxSelectNum(1)
                                    .loadImageEngine(GlideEngine.createGlideEngine())
                                    .forResult(PictureConfig.REQUEST_CAMERA);
                        } else {
                            PictureSelector.create(StateCompensationActivity.this)
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

    private void upLoadFiles(int index) {
        if (selectPhoto.get(index) == null && index < 3) {
            upLoadFiles(index + 1);
            return;
        }
        if (index > 3) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("fileName", selectPhoto.get(index).getFileName());
        String fromBase64 = Base64Converter.encodeBase64File(selectPhoto.get(index).getPath());
        map.put("data", fromBase64);
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.fileUpload)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", a)
                .asObject(UpDataFileBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult() == 200) {
                        retry = 0;
                        fileIds.put(index, s);
                        //下一个
                        if (fileIds.size() < selectPhoto.size()) {
                            int sss = index + 1;
                            upLoadFiles(sss);
                        } else {
                            submit();
                        }
                    } else {
                        //重试
                        retry++;
                        if (retry == 3) {
                            miniLoadingDialog.dismiss();
                            XToast.error(StateCompensationActivity.this, "第" + (index + 1) + "张图片上传失败,请检查").show();
                        } else {
                            upLoadFiles(index);
                        }
                    }
                }, throwable -> {
                    retry++;
                    if (retry == 3) {
                        miniLoadingDialog.dismiss();
                        XToast.error(StateCompensationActivity.this, "第" + (index + 1) + "张图片上传失败,请检查").show();
                    } else {
                        upLoadFiles(index);
                    }
                });
    }

    private void submit() {
        Map<String, String> map = new HashMap<>();
        map.put("source", TagConstant.SOURCE);
        map.put("plaintiffName", nameEdit.getText().toString().trim());
        map.put("plaintiffSex", sexTv.getText().toString().trim());
        map.put("plaintiffCertificateType", zjCheckIndex[0] == -1 ? "" : zjBeans.get(zjCheckIndex[0]).getCode());
        map.put("agentCertificateType", zjCheckIndex[1] == -1 ? "" : zjBeans.get(zjCheckIndex[1]).getCode());
        map.put("plaintiffCertificateNumber", zjhmEdit.getText().toString().trim());
        map.put("plaintiffNation", mzIndex == -1 ? "" : mzBeans.get(mzIndex).getCode());
        map.put("plaintiffNationality", gjIndex == -1 ? "" : gjBeans.get(gjIndex).getCode());
        map.put("plaintiffUnit", gzdwEdit.getText().toString().trim());
        map.put("plaintiffResidence", jsdEdit.getText().toString().trim());
        map.put("plaintiffPhone", phoneEdit.getText().toString().trim());
        map.put("plaintiffEmail", emailEdit.getText().toString().trim());
        map.put("agentName", dNameEdit.getText().toString().trim());
        map.put("agentCertificateNumber", dZjhmEdit.getText().toString().trim());
        map.put("agentUnit", dGzdwEdit.getText().toString().trim());
        map.put("organizationCode", TagConstant.CODE);
        map.put("venueCode", afdBean == null ? "" : afdBean.getCode());
        map.put("content", contentEdit.getContentText());
        map.put("attachmentFile1", fileIds.get(0) == null ? "" : fileIds.get(0).getFileId());
        map.put("attachmentFile2", fileIds.get(1) == null ? "" : fileIds.get(1).getFileId());
        map.put("attachmentFile3", fileIds.get(2) == null ? "" : fileIds.get(2).getFileId());
        map.put("attachmentFile4", fileIds.get(3) == null ? "" : fileIds.get(3).getFileId());
        map.put("office", pcywTv.getText().toString().trim());
        map.put("userId", UserUtils.getId());
        map.put("userKeyId", UserUtils.getKeyId());
        map.put("username", UserUtils.getUserName());
        map.put("userRealName", UserUtils.getRealName());
        String ss = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY, ss);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.stateCompensation)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        XToast.success(StateCompensationActivity.this, s.getMessage()).show();
                        finish();
                    } else {
                        XToast.error(StateCompensationActivity.this, s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(StateCompensationActivity.this, throwable.getMessage()).show();
                });

    }

    /**
     * 赔偿义务机关
     */
    private void showYyagxPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                pcywTv.setText(pcywStrs[options1]);
                pcywIndex = options1;
                return false;
            }
        })
                .setTitleText("赔偿义务机关")
                .setSelectOptions(pcywIndex)
                .build();
        pvOptions.setPicker(pcywStrs);
        pvOptions.show();
    }
}

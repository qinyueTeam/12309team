package com.qinyue.monitor.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
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
import com.qinyue.monitor.first.UpDataFileBean;
import com.qinyue.monitor.first.UpDataFileBean2;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.qinyue.monitor.view.ChooseAfdActivity;
import com.qinyue.monitor.view.ImageSelectGridAdapter;
import com.qinyue.monitor.view.SelectSectionParentEntity;
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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/3
 * 描述:
 **/
public class GyssxsActivity extends BaseActivity implements ImageSelectGridAdapter.OnAddPicClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.lin_content)
    LinearLayout contentLin;
    @BindView(R.id.lin_s)
    LinearLayout sLin;
    @BindView(R.id.lin_city)
    LinearLayout cityLin;

    @BindView(R.id.qy_tv)
    TextView qyTv;
    @BindView(R.id.s_tv)
    TextView sTv;
    @BindView(R.id.city_tv)
    TextView cityTv;
    @BindView(R.id.ajlb_tv)
    TextView ajlbTv;
    @BindView(R.id.shly_tv)
    TextView shlyTv;
    @BindView(R.id.time_tv)
    TextView timeTv;

    @BindView(R.id.content_sjdy)
    MultiLineEditText sjdvContentTv;
    @BindView(R.id.content)
    MultiLineEditText wfContentTv;

    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.lxdz_edit)
    EditText lxdzEdit;
    @BindView(R.id.xs_name_edit)
    EditText xsNameEdit;
    @BindView(R.id.idcard_edit)
    EditText idcardEdit;
    @BindView(R.id.xs_dz_edit)
    EditText xsDzEdit;
    @BindView(R.id.xs_phone_edit)
    EditText xsPhoneEdit;

    private ImageSelectGridAdapter mAdapter;

    private List<LocalMedia> mSelectList = new ArrayList<>();
    private SparseArray<UpDataFileBean> fileIds = new SparseArray<>();

    private List<String> sfStr = new ArrayList<>();
    private List<String> cityStr = new ArrayList<>();
    private List<SelectSectionParentEntity> sfBean = new ArrayList<>();
    private List<SelectSectionParentEntity> cityBean = new ArrayList<>();

    private String qyStr[] = {"本省内某一区域", "本省内跨区域", "地域跨省"};
    private String ajlbStr[] = {"民事公益诉讼", "行政公益诉讼"};
    private String shlyStr[] = {"生态环境和资源保护", "食品安全", "药品安全", "国有土地使用权出让", "国有财产保护", "英烈保护", "其他"};
    private int qyIndex = 0;
    private int ajlbIndex = -1;
    private int shlyIndex = -1;
    private int sfIndex = -1;
    private int cityIndex = -1;

    @Override
    public String initTitleText() {
        return "公益诉讼";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initview() {
        GridLayoutManager manager = new GridLayoutManager(this, 4, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter = new ImageSelectGridAdapter(this, this));
        mAdapter.setSelectList(mSelectList);
        mAdapter.setSelectMax(5);
        mAdapter.setOnItemClickListener(new ImageSelectGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureSelector.create(GyssxsActivity.this).themeStyle(R.style.picture_default_style)
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .openExternalPreview(position, mSelectList);
            }
        });
    }

    @OnClick({R.id.btn_cancel,R.id.btn_submit,R.id.time_lin,R.id.lin_shly,R.id.ajlb_lin,R.id.lin_city,R.id.lin_s,R.id.lin_qkqy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_qkqy: {//反映情况所在区域
                showYyagxPickerView();
            }
            break;
            case R.id.time_lin: {//拍摄时间
                showDatePicker(timeTv);
            }
            break;
            case R.id.lin_s: {//省
                if (sfBean.size()==0){
                    getAfdData();
                }else {
                    showSfPickerView();
                }
            }
            break;
            case R.id.lin_city: {//市
                if (sfIndex==-1){
                    XToast.info(GyssxsActivity.this,"请先选择省份").show();
                    break;
                }
                if (cityBean.size()==0){
                    getAfdDataForcChild();
                }else {
                    showCityPickerView();
                }
            }
            break;
            case R.id.ajlb_lin: {//案件类别
                showlbPickerView();
            }
            break;
            case R.id.lin_shly: {//损害领域
                showshlyPickerView();
            }
            break;
            case R.id.btn_submit: {//提交
                if (qyIndex==0){
                    if (sfIndex==-1){
                        XToast.info(GyssxsActivity.this,"请选择省份").show();
                        break;
                    }
                    if (cityIndex==-1){
                        XToast.info(GyssxsActivity.this,"请选择市").show();
                        break;
                    }
                }else if (qyIndex==1){
                    if (sfIndex==-1){
                        XToast.info(GyssxsActivity.this,"请选择省份").show();
                        break;
                    }
                    if (sjdvContentTv.isEmpty()){
                        XToast.info(GyssxsActivity.this,"请输入反映情况涉及地域").show();
                        break;
                    }
                }else {
                    if (sjdvContentTv.isEmpty()){
                        XToast.info(GyssxsActivity.this,"请输入反映情况涉及地域").show();
                        break;
                    }
                }
                if (ajlbIndex==-1){
                    XToast.info(GyssxsActivity.this,"请选择案件类别").show();
                    break;
                }
                if (shlyIndex==-1){
                    XToast.info(GyssxsActivity.this,"请选择公益损害领域").show();
                    break;
                }
                if (wfContentTv.isEmpty()){
                    XToast.info(GyssxsActivity.this,"请输入情况简要描述").show();
                    break;
                }
                if (timeTv.getText().toString().trim().isEmpty()){
                    XToast.info(GyssxsActivity.this,"请选择图片拍摄时间").show();
                    break;
                }
                if (xsNameEdit.getText().toString().trim().isEmpty()){
                    XToast.info(GyssxsActivity.this,"请输入线索提供人姓名").show();
                    break;
                }
                if (!idcardEdit.getText().toString().trim().isEmpty()&&!RegexUtils.isIDCard18(idcardEdit.getText().toString().trim())){
                    XToast.info(this,"证件号码错误").show();
                    break;
                }
                if (xsPhoneEdit.getText().toString().trim().isEmpty()){
                    XToast.info(GyssxsActivity.this,"请输入线索提供人联系方式").show();
                    break;
                }
                if (!RegexUtils.isMobileSimple(xsPhoneEdit.getText().toString().trim())){
                    XToast.info(this,"电话号码错误").show();
                    break;
                }
                miniLoadingDialog.show();
                if (mSelectList.size()>0){
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

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_gyssxs;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }


    @Override
    public void onAddPicClick() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .maxSelectNum(5)
                .minSelectNum(1)
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .isCamera(true)
                .enableCrop(false)
                .compress(true)
                .previewEggs(true)
                .selectionMedia(mSelectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    mSelectList = PictureSelector.obtainMultipleResult(data);
                    mAdapter.setSelectList(mSelectList);
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 反映区域
     */
    private void showYyagxPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                if (options1==qyIndex){
                    return false;
                }
                qyTv.setText(qyStr[options1]);
                qyIndex = options1;
                cityTv.setText("");
                sTv.setText("");
                sjdvContentTv.setContentText("");
                cityStr.clear();
                cityBean.clear();
                cityIndex=-1;
                sfBean.clear();
                sfIndex=-1;
                sfStr.clear();
                switch (options1) {
                    case 0: {
                        contentLin.setVisibility(View.GONE);
                        cityLin.setVisibility(View.VISIBLE);
                        sLin.setVisibility(View.VISIBLE);
                    }
                    break;
                    case 1: {
                        contentLin.setVisibility(View.VISIBLE);
                        cityLin.setVisibility(View.GONE);
                        sLin.setVisibility(View.VISIBLE);

                    }
                    break;
                    case 2: {
                        contentLin.setVisibility(View.VISIBLE);
                        cityLin.setVisibility(View.GONE);
                        sLin.setVisibility(View.GONE);
                    }
                    break;
                }
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(qyIndex)
                .build();
        pvOptions.setPicker(qyStr);
        pvOptions.show();
    }
    /**
     *案件类别
     */
    private void showlbPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                if (options1==ajlbIndex){
                    return false;
                }
                ajlbTv.setText(ajlbStr[options1]);
                ajlbIndex = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(ajlbIndex)
                .build();
        pvOptions.setPicker(ajlbStr);
        pvOptions.show();
    }
    /**
     *省份
     */
    private void showSfPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                if (options1==sfIndex){
                    return false;
                }
                sTv.setText(sfStr.get(options1));
                sfIndex = options1;
                cityTv.setText("");
                cityBean.clear();
                cityIndex = -1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(sfIndex)
                .build();
        pvOptions.setPicker(sfStr);
        pvOptions.show();
    }
    /**
     *城市
     */
    private void showCityPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                if (options1==cityIndex){
                    return false;
                }
                cityTv.setText(cityStr.get(options1));
                cityIndex = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(cityIndex)
                .build();
        pvOptions.setPicker(cityStr);
        pvOptions.show();
    }
    /**
     *公益损害领域
     */
    private void showshlyPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                if (options1==shlyIndex){
                    return false;
                }
                shlyTv.setText(shlyStr[options1]);
                shlyIndex = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(shlyIndex)
                .build();
        pvOptions.setPicker(shlyStr);
        pvOptions.show();
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
    private void getAfdData(){
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "7")
                .asParser(new SimpleParser<BaseArrayDataBean2<SelectSectionParentEntity>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        sfBean.addAll(s.getData());
                        for (int i = 0; i < sfBean.size(); i++) {
                            sfStr.add(sfBean.get(i).getName());
                        }
                        showSfPickerView();
                    }else {
                        XToast.error(GyssxsActivity.this,s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(GyssxsActivity.this,throwable.getMessage()).show();
                });
    }
    public void getAfdDataForcChild(){
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "7")
                .add("id",sfBean.get(sfIndex).getCode())
                .asParser(new SimpleParser<BaseArrayDataBean2<SelectSectionParentEntity>>(){})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        cityBean.addAll(s.getData());
                        for (int i = 0; i < s.getData().size(); i++) {
                           cityStr.add(s.getData().get(i).getName());
                        }
                        showCityPickerView();
                    }else {
                        XToast.error(GyssxsActivity.this,s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(GyssxsActivity.this,throwable.getMessage()).show();
                });
    }
    private int retry = 0;

    private void upLoadFiles(int index) {
        if (mSelectList.get(index) == null && index < 4) {
            upLoadFiles(index + 1);
            return;
        }
        if (index > 4) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("fileName", mSelectList.get(index).getFileName());
        String fromBase64 = Base64Converter.encodeBase64File(mSelectList.get(index).getPath());
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
                        if (fileIds.size() < mSelectList.size()) {
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
                            XToast.error(GyssxsActivity.this, "第" + (index + 1) + "张图片上传失败,请检查").show();
                        } else {
                            upLoadFiles(index);
                        }
                    }
                }, throwable -> {
                    retry++;
                    if (retry == 3) {
                        miniLoadingDialog.dismiss();
                        XToast.error(GyssxsActivity.this, "第" + (index + 1) + "张图片上传失败,请检查").show();
                    } else {
                        upLoadFiles(index);
                    }
                });
    }

    private void submit() {
        Map<String, String> map = new HashMap<>();
        map.put("source", TagConstant.SOURCE);
        map.put("areaType", (qyIndex+1)+"");
        map.put("areaProv", sfIndex==-1?"":sfBean.get(sfIndex).getCode());
        map.put("areaCity", cityIndex==-1?"":cityBean.get(cityIndex).getCode());
        map.put("areaRemark", sjdvContentTv.getContentText());
        map.put("organizationCode", TagConstant.CODE);
        map.put("caseType", (ajlbIndex+1)+"");
        map.put("harmRange", (shlyIndex+1)+"");
        map.put("defendantName", nameEdit.getText().toString().trim());
        map.put("defendantAddress", lxdzEdit.getText().toString().trim());
        map.put("reflectRemark", wfContentTv.getContentText());
        map.put("picCreateTime", timeTv.getText().toString().trim());
        map.put("plaintiffName", xsNameEdit.getText().toString().trim());
        map.put("plaintiffCertificateNumber", idcardEdit.getText().toString().trim());
        map.put("plaintiffAddress", xsDzEdit.getText().toString().trim());
        map.put("plaintiffPhone", xsPhoneEdit.getText().toString().trim());
        map.put("userId", UserUtils.getId());
        map.put("userKeyId", UserUtils.getKeyId());
        map.put("username", UserUtils.getUserName());
        map.put("userRealName", UserUtils.getRealName());
        if (fileIds.size()>0){
            String zz = "";
            for (int i = 0; i < fileIds.size(); i++) {
                zz =zz + fileIds.valueAt(i).getFileId()+",";
            }
            if (zz.endsWith(",")){
                zz= zz.substring(0,zz.length()-1);
            }
            map.put("fileIds", zz);
        }
        String ss = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY, ss);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.publicPetition)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        XToast.success(GyssxsActivity.this, s.getMessage()).show();
                        finish();
                    } else {
                        XToast.error(GyssxsActivity.this, s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(GyssxsActivity.this, throwable.getMessage()).show();
                });

    }
}

package com.qinyue.monitor.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseBean;
import com.qinyue.monitor.base.BaseBean2;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.home.MyFragment;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.FileUtil;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.view.MyCameraActivity;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.data.SPUtils;
import com.xuexiang.xutil.security.EncodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class ChoosePhoneActivity extends BaseActivity {
    private CountDownButtonHelper mCountDownHelper;
    @BindView(R.id.headimg)
    ImageView headImg;
    @BindView(R.id.btn_get_verify_code)
    RoundButton verifyCodeBut;
    @BindView(R.id.edit_phone)
    TextView phoneEdit;
    @BindView(R.id.et_verify_code)
    MaterialEditText verifyEdit;
    String idCard;
    private int index = 0;
    private List<String> strings = new ArrayList<>();
    @Override
    public String initTitleText() {
        return "手机验证码登录";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        idCard = getIntent().getStringExtra("idcard");
        String json = getIntent().getStringExtra("json");
        List<PhoneBean> phoneBeans= JsonUtils.getInstance().gson.fromJson(json,new TypeToken<List<PhoneBean>>(){}.getType());
        for (int i = 0; i < phoneBeans.size(); i++) {
            strings.add(phoneBeans.get(i).getPhone());
        }
    }

    @Override
    protected void initview() {
        Glide.with(this).load(TagConstant.BASEBANNER_URL).into(headImg);
    }
    @OnClick({R.id.choose_phone,R.id.btn_get_verify_code,R.id.but_reg})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.but_reg:{//登录按钮
                if (phoneEdit.getText().toString().isEmpty()){
                    XToast.warning(this,"请选择手机号码").show();
                    break;
                }
                if (verifyEdit.isEmpty()){
                    XToast.warning(this,"请输入短信验证码").show();
                    break;
                }
                doLogin();
            }break;
            case R.id.btn_get_verify_code:{//获取验证码
                if (!phoneEdit.getText().toString().isEmpty()){
                    getVCode();
                }
            }break;
            case R.id.choose_phone:{//选择手机号
                showSxlyPickerView();
//                if (phoneEdit.validate()){
//                    mCountDownHelper.start();
//                }
            }break;
        }
    }
    @Override
    protected boolean network() {
        return false;
    }
    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activty_choosephone;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==101){
            //密码登录返回
            finish();
        }
        if (requestCode==100&&resultCode==102){
            //注册返回
            finish();
        }
    }

    @Override
    protected Boolean status() {
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /**
     * 涉嫌领域
     */
    private void showSxlyPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                phoneEdit.setText(strings.get(options1));
                index = options1;
                return false;
            }
        })
                .setTitleText("选择手机号")
                .setSelectOptions(index)
                .build();
        pvOptions.setPicker(strings);
        pvOptions.show();
    }
    @SuppressLint("CheckResult")
    private void doLogin(){
        miniLoadingDialog.show();
        Map<String,String> map = new HashMap<>();
        map.put("idCard",idCard);
        map.put("phone",phoneEdit.getText().toString());
        map.put("code",verifyEdit.getEditValue());
        String s2 = new Gson().toJson(map);
        String aesEncode = Base64Converter.AESEncode(TagConstant.AESKEY,s2) ;
        RxHttp.postForm(TagConstant.BASEURL+ NetConstant.loginByIdCard)  //发送表单形式的Post请求
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("data",aesEncode)
                .asParser(new SimpleParser<BaseBean<UserBean>>(){})
                .observeOn(AndroidSchedulers.mainThread())//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    BaseBean<UserBean> beanBaseBean = s;
                    if (beanBaseBean.getCode()==101){
                        getUserMsg(beanBaseBean.getData().getId());
                    }else {
                        miniLoadingDialog.dismiss();
                        XToast.error(ChoosePhoneActivity.this,beanBaseBean.getMsg()).show();
                    }
                }, throwable -> {
                    //请求失败
                    miniLoadingDialog.dismiss();
                    XToast.error(ChoosePhoneActivity.this,"登录失败 "+throwable.getMessage()).show();
                });

    }
    @SuppressLint("CheckResult")
    private void getUserMsg(String id){
        Map<String,String> map = new HashMap<>();
        map.put("userId",id);
        String s2 = new Gson().toJson(map);
        id =Base64Converter.AESEncode(TagConstant.AESKEY,s2) ;
        RxHttp.postForm(TagConstant.BASEURL+NetConstant.getMyInfo)
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("data",id)
                .asObject(MyUserBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s ->{
                    miniLoadingDialog.dismiss();
                    MyUserBean myUserBean = s;
                    if (myUserBean.getBaseUser()==null){
                        XToast.error(ChoosePhoneActivity.this,"获取用户信息失败").show();
                    }else {
                        XToast.success(ChoosePhoneActivity.this, "登录成功").show();
                        SPUtils.putObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, myUserBean);
                        MyFragment.logTagChanged.postValue(true);
                        setResult(102);
                        finish();
                    }
                },throwable ->{
                    miniLoadingDialog.dismiss();
                    XToast.error(ChoosePhoneActivity.this,"登录失败 "+throwable.getMessage()).show();
                });
    }
    private void getVCode() {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getCode)
                .add("phone", phoneEdit.getText().toString())
                .add("secretKey", Base64Converter.encrypt32(phoneEdit.getText().toString() + "123456"))
//                .add("source", "applets")
                .asParser(new SimpleParser<BaseBean2<CodeBean>>() {})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getCode()==101){
                        mCountDownHelper = new CountDownButtonHelper(verifyCodeBut, s.getData().getValidTime());
                        mCountDownHelper.start();
                        XToast.success(ChoosePhoneActivity.this,s.getData().getMessage()).show();
                    }else {
                        XToast.error(ChoosePhoneActivity.this,"获取验证码失败").show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(ChoosePhoneActivity.this, throwable.getMessage()).show();
                });
    }
}

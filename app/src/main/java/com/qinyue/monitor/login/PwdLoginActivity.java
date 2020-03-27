package com.qinyue.monitor.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.home.MyFragment;
import com.qinyue.monitor.util.Base64Converter;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.XUtil;
import com.xuexiang.xutil.data.SPUtils;
import com.xuexiang.xutil.net.JSONUtils;
import com.xuexiang.xutil.security.EncodeUtils;
import com.xuexiang.xutil.security.EncryptUtils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class PwdLoginActivity extends BaseActivity {
    @BindView(R.id.headimg)
    ImageView headImg;
    @BindView(R.id.edit_phone)
    MaterialEditText phoneEdit;
    @BindView(R.id.et_pwd)
    MaterialEditText pwdEdit;
    @Override
    public String initTitleText() {
        return "账号密码登录";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        Glide.with(this).load(TagConstant.BASEBANNER_URL).into(headImg);
    }
    @OnClick({R.id.tv_reg,R.id.tv_lsz,R.id.but_reg})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_lsz:{//验证码登录
                finish();
            }break;
            case R.id.tv_reg:{//注册
                startActivityForResult(new Intent(this,RegisterActivity.class),100);
            }break;
            case R.id.but_reg:{//登录按钮
                if (phoneEdit.isEmpty()){
                    XToast.warning(this,"请输入账号").show();
                    break;
                }
                if (pwdEdit.isEmpty()){
                    XToast.warning(this,"请输入密码").show();
                    break;
                }
                doLogin();
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
        return R.layout.activty_pwdlogin;
    }

    @Override
    protected void init() {

    }

    @SuppressLint("CheckResult")
    private void doLogin(){
        miniLoadingDialog.show();
        String phone =new String(EncodeUtils.base64Encode(phoneEdit.getEditValue())) ;
        String pwd =new String(EncodeUtils.base64Encode(pwdEdit.getEditValue())) ;
        String md5 = Base64Converter.encrypt32(phoneEdit.getEditValue()+pwdEdit.getEditValue()+"123456");
        RxHttp.postForm(TagConstant.BASEURL+ NetConstant.loginByPwd)  //发送表单形式的Post请求
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("username",phone )
                .add("password",pwd )
                .add("secretKey",md5 )
                .asParser(new SimpleParser<BaseBean<UserBean>>(){})
                .observeOn(AndroidSchedulers.mainThread())//返回String类型
                .subscribe(s -> {          //订阅观察者，
                    //请求成功
                    BaseBean<UserBean> beanBaseBean = s;
                    if (beanBaseBean.getCode()==101){
                        getUserMsg(beanBaseBean.getData().getId());
                    }else {
                        miniLoadingDialog.dismiss();
                        XToast.error(PwdLoginActivity.this,beanBaseBean.getMsg()).show();
                    }
                }, throwable -> {
                    //请求失败
                    miniLoadingDialog.dismiss();
                    XToast.error(PwdLoginActivity.this,"登录失败 "+throwable.getMessage()).show();
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
                        XToast.error(PwdLoginActivity.this,"获取用户信息失败").show();
                    }else {
                        XToast.success(PwdLoginActivity.this, "登录成功").show();
                        SPUtils.putObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, myUserBean);
                        MyFragment.logTagChanged.postValue(true);
                        setResult(101);
                        finish();
                    }
                },throwable ->{
                    miniLoadingDialog.dismiss();
                    XToast.error(PwdLoginActivity.this,"登录失败 "+throwable.getMessage()).show();
                });
    }
    @Override
    protected Boolean status() {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==102){
            setResult(101);
            finish();
        }
    }
}

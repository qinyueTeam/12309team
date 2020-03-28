package com.qinyue.monitor.my;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseBean2;
import com.qinyue.monitor.base.BaseDataBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.home.MyFragment;
import com.qinyue.monitor.login.CodeBean;
import com.qinyue.monitor.login.MyUserBean;
import com.qinyue.monitor.login.RegisterActivity;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.UserUtils;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.data.SPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/27
 * 描述:
 **/
public class EditMyMsgActivty extends BaseActivity {
    @BindView(R.id.tv_name)
    MaterialEditText nameEdit;
    @BindView(R.id.tv_sex)
    TextView sexTv;
    @BindView(R.id.edit_card)
    MaterialEditText cardEdit;
    @BindView(R.id.edit_phone)
    MaterialEditText phoneEdit;
    @BindView(R.id.et_verify_code)
    MaterialEditText verifyEdit;
    @BindView(R.id.edit_email)
    MaterialEditText emailEdit;
    @BindView(R.id.edit_address)
    MaterialEditText addressEdit;
    @BindView(R.id.btn_get_verify_code)
    RoundButton verifyBut;
    private CountDownButtonHelper mCountDownHelper;
    private int checkIndex = -1;


    @Override
    public String initTitleText() {
        return "修改信息";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (UserUtils.isLogin()){
            nameEdit.setText(UserUtils.getRealName());
            if ("男".equals(UserUtils.getSex())){
                checkIndex = 0;
            }else {
                checkIndex = 1;
            }
            sexTv.setText(UserUtils.getSex());
            phoneEdit.setText(UserUtils.getUserName());
            cardEdit.setText(UserUtils.getIdCard());
            emailEdit.setText(UserUtils.getEmail());
            addressEdit.setText(UserUtils.getAddress());
        }
    }

    @Override
    protected void initview() {

    }
    @OnClick({R.id.btn_get_verify_code,R.id.choose_sex,R.id.but_reg})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_get_verify_code:{//获取验证码
                if (phoneEdit.validate()){
                    getVCode();
                }
            }break;
            case R.id.choose_sex:{//选择性别
                showSimpleBottomSheetList();
            }break;
            case R.id.but_reg:{//修改
                if (nameEdit.isEmpty()) {
                    XToast.warning(this, "请输入姓名").show();
                    break;
                }
                if (checkIndex == -1) {
                    XToast.warning(this, "请选择性别").show();
                    break;
                }
                if (cardEdit.isEmpty()) {
                    XToast.warning(this, "请输入身份证号码").show();
                    break;
                }
                if (phoneEdit.isEmpty()) {
                    XToast.warning(this, "请输入手机号码").show();
                    break;
                }
                if (verifyEdit.isEmpty()) {
                    XToast.warning(this, "请输入短信验证码").show();
                    break;
                }
                updata();
            }break;
        }
    }
    private void showSimpleBottomSheetList() {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("请选择性别")
                .addItem("男")
                .addItem("女")
                .setCheckedIndex(checkIndex)
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndex = position;
                        sexTv.setText(tag);
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
        return R.layout.activty_editmymsg;
    }

    @Override
    protected void init() {

    }
    private void getVCode() {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getCode)
                .add("phone", phoneEdit.getEditValue())
                .add("secretKey", Base64Converter.encrypt32(phoneEdit.getEditValue() + "123456"))
//                .add("source", "applets")
                .asParser(new SimpleParser<BaseBean2<CodeBean>>() {})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getCode()==101){
                        mCountDownHelper = new CountDownButtonHelper(verifyBut, s.getData().getValidTime());
                        mCountDownHelper.start();
                        XToast.success(EditMyMsgActivty.this,s.getData().getMessage()).show();
                    }else {
                        XToast.error(EditMyMsgActivty.this,"获取验证码失败").show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(EditMyMsgActivty.this, throwable.getMessage()).show();
                });
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
    private void updata(){
        miniLoadingDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("realName", nameEdit.getEditValue());
        map.put("userId", UserUtils.getId());
        map.put("gender", (checkIndex + 1) + "");
        map.put("mobile", phoneEdit.getEditValue());
        map.put("idCard", cardEdit.getEditValue());
        map.put("email", emailEdit.getEditValue());
        map.put("address", addressEdit.getEditValue());
        map.put("smsCode", verifyEdit.getEditValue());
        String aes = new Gson().toJson(map);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.saveInfo)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", Base64Converter.AESEncode(TagConstant.AESKEY, aes))
                .asObject(BaseDataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.isResult()){
                        getUserMsg(s.getMsg(),UserUtils.getId());
                    }else {
                        miniLoadingDialog.dismiss();
                        XToast.error(EditMyMsgActivty.this,s.getMsg()).show();
                    }

                    XToast.success(EditMyMsgActivty.this,"修改成功").show();
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(EditMyMsgActivty.this,throwable.getMessage()+"").show();
                });

    }
    @SuppressLint("CheckResult")
    private void getUserMsg(String msg,String id){
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
                        XToast.error(EditMyMsgActivty.this,"获取用户信息失败").show();
                    }else {
                        XToast.success(EditMyMsgActivty.this, msg).show();
                        SPUtils.putObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, myUserBean);
                        MyFragment.logTagChanged.postValue(true);
                        setResult(102);
                        finish();
                    }
                },throwable ->{
                    miniLoadingDialog.dismiss();
                    XToast.error(EditMyMsgActivty.this,msg+throwable.getMessage()).show();
                });
    }
}

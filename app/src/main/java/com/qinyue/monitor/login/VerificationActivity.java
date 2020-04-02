package com.qinyue.monitor.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.bumptech.glide.Glide;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.FileUtil;
import com.qinyue.monitor.view.MyCameraActivity;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.toast.XToast;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class VerificationActivity extends BaseActivity {
    private CountDownButtonHelper mCountDownHelper;
    @BindView(R.id.headimg)
    ImageView headImg;
    @BindView(R.id.btn_get_verify_code)
    RoundButton verifyCodeBut;
    @BindView(R.id.edit_phone)
    MaterialEditText phoneEdit;
    @BindView(R.id.et_verify_code)
    MaterialEditText verifyEdit;
    @Override
    public String initTitleText() {
        return "手机验证码登录";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        Glide.with(this).load(TagConstant.BASEBANNER_URL).into(headImg);
        mCountDownHelper = new CountDownButtonHelper(verifyCodeBut, 60);
    }
    @OnClick({R.id.tv_reg,R.id.tv_lsz,R.id.btn_get_verify_code,R.id.but_reg,R.id.login_idcard})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_lsz:{//账号密码登录
                startActivityForResult(new Intent(this,PwdLoginActivity.class),100);
            }break;
            case R.id.login_idcard:{//身份证登录
                Intent intent = new Intent(VerificationActivity.this, MyCameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
                        true);
                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                // 请手动使用CameraNativeHelper初始化和释放模型
                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
                        true);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, 103);

//                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
//                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
//                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
//                startActivityForResult(intent, 103);
            }break;
            case R.id.tv_reg:{//注册
                startActivityForResult(new Intent(this,RegisterActivity.class),100);
            }break;
            case R.id.but_reg:{//登录按钮
                if (phoneEdit.isEmpty()){
                    XToast.warning(this,"请输入手机号码").show();
                    break;
                }
                if (verifyEdit.isEmpty()){
                    XToast.warning(this,"请输入短信验证码").show();
                    break;
                }
                miniLoadingDialog.show();
            }break;
            case R.id.btn_get_verify_code:{//获取验证码
                if (phoneEdit.validate()){
                    mCountDownHelper.start();
                }
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
        return R.layout.activty_verification;
    }

    @Override
    protected void init() {
        //  初始化本地质量控制模型,释放代码在onDestory中
        //  调用身份证扫描必须加上 intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true); 关闭自动初始化和释放本地模型
        CameraNativeHelper.init(this, OCR.getInstance(this).getLicense(),
                new CameraNativeHelper.CameraNativeInitCallback() {
                    @Override
                    public void onError(int errorCode, Throwable e) {
                        String msg;
                        switch (errorCode) {
                            case CameraView.NATIVE_SOLOAD_FAIL:
                                msg = "加载so失败，请确保apk中存在ui部分的so";
                                break;
                            case CameraView.NATIVE_AUTH_FAIL:
                                msg = "授权本地质量控制token获取失败";
                                break;
                            case CameraView.NATIVE_INIT_FAIL:
                                msg = "本地质量控制";
                                break;
                            default:
                                msg = String.valueOf(errorCode);
                        }
                        Log.i("tah",msg);
                    }
                });
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
        if (requestCode==103&&resultCode==101){
            //身份证直接登录返回
            finish();
        }
    }

    @Override
    protected Boolean status() {
        return false;
    }
    @Override
    protected void onDestroy() {
        // 释放本地质量控制模型
        CameraNativeHelper.release();
        super.onDestroy();
    }
}

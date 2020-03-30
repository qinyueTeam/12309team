package com.qinyue.monitor.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.first.UpDataFileBean;
import com.qinyue.monitor.first.UpDataFileBean2;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.qinyue.monitor.view.ImageSelectGridAdapter;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
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

/**
 * 创建人:qinyue
 * 创建日期:2020/3/30
 * 描述:
 **/
public class AddQzyjxActivity extends BaseActivity {
    @BindView(R.id.content)
    MultiLineEditText contentView;
    @BindView(R.id.phone)
    EditText phoneEdit;
    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.title)
    EditText titleEdit;
    @BindView(R.id.type)
    TextView typeTv;
    private int checkIndex = -1;

    @Override
    public String initTitleText() {
        return "群众意见建议箱";
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        phoneEdit .setText(UserUtils.getUserName());
        nameEdit.setText(UserUtils.getRealName());
    }

    @Override
    protected void initview() {
    }

    @Override
    protected boolean network() {
        return false;
    }

    @OnClick({R.id.choose_type, R.id.but_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_type: {//选择类型
                showSimpleBottomSheetList();
            }
            break;
            case R.id.but_submit: {//提交
                if (nameEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AddQzyjxActivity.this, "请输入姓名").show();
                    break;
                }
                if (phoneEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AddQzyjxActivity.this, "请输入手机号码").show();
                    break;
                }
                if (typeTv.getText().toString().trim().isEmpty()) {
                    XToast.info(AddQzyjxActivity.this, "请选择类型").show();
                    break;
                }
                if (titleEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AddQzyjxActivity.this, "请输入意见标题").show();
                    break;
                }
                if (contentView.getContentText().isEmpty()) {
                    XToast.info(AddQzyjxActivity.this, "请输入内容").show();
                    break;
                }
                miniLoadingDialog.show();
                submit();
            }
            break;
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_addqzyjjy;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }

    private void showSimpleBottomSheetList() {
        //对应code按顺序0-17
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("请选择类型")
                .addItem("立案与侦查活动监督")
                .addItem("公诉与刑事审判监督")
                .addItem("刑事执行检察工作")
                .addItem("民事行政检察工作")
                .addItem("公益诉讼")
                .addItem("控告检察工作")
                .addItem("刑事申诉检察工作")
                .addItem("司法解释工作")
                .addItem("案件管理")
                .addItem("司法交流与合作")
                .addItem("司法改革")
                .addItem("队伍建设")
                .addItem("基层基础建设")
                .addItem("信息化建设")
                .addItem("检察宣传")
                .addItem("检务公开")
                .addItem("接受监督")
                .addItem("其他")
                .setCheckedIndex(checkIndex)
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndex = position;
                        typeTv.setText(tag);
                    }
                })
                .build().show();
    }

    private void submit() {
        Map<String, String> map = new HashMap<>();
        map.put("source", TagConstant.SOURCE);
        map.put("realName", nameEdit.getText().toString().trim());
        map.put("mobile",phoneEdit.getText().toString().trim());
        map.put("organizationCode", TagConstant.CODE);
        map.put("type", typeTv.getText().toString().trim());
        map.put("content", contentView.getContentText());
        map.put("title", titleEdit.getText().toString().trim());
        map.put("userId", UserUtils.getId());
        map.put("userKeyId", UserUtils.getKeyId());
        map.put("username",UserUtils.getUserName());
        map.put("userRealName", UserUtils.getRealName());
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.suggestion)
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("data", a)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200) {
                        XToast.success(AddQzyjxActivity.this, s.getMessage()).show();
                        finish();
                    } else {
                        XToast.error(AddQzyjxActivity.this, s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AddQzyjxActivity.this, throwable.getMessage()).show();
                });
    }
}

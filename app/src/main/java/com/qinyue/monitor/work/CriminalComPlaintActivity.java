package com.qinyue.monitor.work;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean2;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.first.MzBean;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

public class CriminalComPlaintActivity extends BaseActivity {
    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.et_bkgr_name)
    EditText bNameEdit;
    @BindView(R.id.et_zjhm)
    EditText zjhmEdit;
    @BindView(R.id.et_phone)
    EditText phoneEdit;
    @BindView(R.id.et_email)
    EditText emailEdit;
    @BindView(R.id.et_bkgr_dwmc)
    EditText bGzdwNameEdit;
    @BindView(R.id.et_bkgr_dwdz)
    EditText bGzdwDzEdit;
    @BindView(R.id.et_bkgr_zw)
    EditText bZwEdit;
    @BindView(R.id.et_gzdw)
    EditText gzdwEdit;
    @BindView(R.id.et_szd)
    EditText jsdEdit;
    @BindView(R.id.et_yyagx)
    TextView yyagxTv;
    @BindView(R.id.et_xb)
    TextView sexTv;
    @BindView(R.id.et_zjlx)
    TextView zjlxTv;
    @BindView(R.id.et_mz)
    TextView mzTv;
    @BindView(R.id.et_gj)
    TextView gjTv;
    @BindView(R.id.et_bkgr_xb)
    TextView bSexTv;
    @BindView(R.id.et_bkgr_gj)
    TextView bGjTv;
    @BindView(R.id.et_afd)
    TextView afdTv;
    @BindView(R.id.content)
    MultiLineEditText contentTv;

    private int[] checkIndexSex = {-1, -1};
    private int zjIndex = -1;
    private int mzIndex = -1;

    List<MzBean> mzBeans;
    List<String> mzStrBeans = new ArrayList<>();
    List<MzBean> zjBeans;
    List<String> zjStrBeans = new ArrayList<>();
    List<MzBean> gjBeans;
    List<String> gjStrBeans = new ArrayList<>();

    @Override
    public String initTitleText() {
        return "刑事申诉";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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
        return R.layout.activity_criminal_complaint;
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

    @OnClick({R.id.btn_cancel,R.id.btn_submit,R.id.img_sfz,R.id.img_xgzjcl,R.id.img_fcws,R.id.img_zjcl,R.id.img_kgcl,R.id.rl_afd,R.id.rl_bkgr_zj,R.id.rl_bkgr_gj,R.id.rl_bkgr_xb,R.id.rl_gj,R.id.rl_mz,R.id.rl_zjlx,R.id.rl_yyagx,R.id.rl_xb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel: {//取消
                finish();
            }
            break;
            case R.id.btn_submit: {//提交
            }
            break;
            case R.id.img_sfz: {//身份证扫描
            }
            break;
            case R.id.img_xgzjcl: {//证据材料
            }
            break;
            case R.id.img_fcws: {//复查文书
            }
            break;
            case R.id.img_zjcl: {//裁决书
            }
            break;
            case R.id.img_kgcl: {//申诉状
            }
            break;
            case R.id.rl_yyagx: {//与原案的关系
            }
            break;
            case R.id.rl_xb: {//性别
                showSimpleBottomSheetList(0,sexTv);
            }
            break;
            case R.id.rl_bkgr_xb: {//被申诉人性别
                showSimpleBottomSheetList(1,bSexTv);
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
            case R.id.rl_mz: {//名族
                if (mzBeans == null) {
                    getMzDataForcChild();
                } else {
                    showMzPickerView();
                }
            }
            break;
            case R.id.rl_gj: {//国籍
            }
            break;
            case R.id.rl_bkgr_gj: {//被申诉人国籍
            }
            break;
            case R.id.rl_bkgr_zj: {//职级
            }
            break;
            case R.id.rl_afd: {//案发地
            }
            break;
        }
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
                        XToast.error(CriminalComPlaintActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(CriminalComPlaintActivity.this, throwable.getMessage()).show();
                });
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
                        XToast.error(CriminalComPlaintActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(CriminalComPlaintActivity.this, throwable.getMessage()).show();
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
}

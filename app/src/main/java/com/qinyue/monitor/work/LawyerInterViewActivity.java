package com.qinyue.monitor.work;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean2;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.first.MzBean;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

public class LawyerInterViewActivity extends BaseActivity {
    @BindView(R.id.txt_xm)
    TextView txtXm;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.txt_xb)
    TextView txtXb;
    @BindView(R.id.et_xb)
    EditText etXb;
    @BindView(R.id.rl_xb)
    RelativeLayout rlXb;
    @BindView(R.id.txt_zjlx)
    TextView txtZjlx;
    @BindView(R.id.et_zjlx)
    EditText etZjlx;
    @BindView(R.id.rl_zjlx)
    RelativeLayout rlZjlx;
    @BindView(R.id.txt_zjhm)
    TextView txtZjhm;
    @BindView(R.id.et_zjhm)
    EditText etZjhm;
    @BindView(R.id.rl_zjhm)
    RelativeLayout rlZjhm;
    @BindView(R.id.txt_mz)
    TextView txtMz;
    @BindView(R.id.et_mz)
    EditText etMz;
    @BindView(R.id.rl_mz)
    RelativeLayout rlMz;
    @BindView(R.id.txt_gj)
    TextView txtGj;
    @BindView(R.id.et_gj)
    EditText etGj;
    @BindView(R.id.rl_gj)
    RelativeLayout rlGj;
    @BindView(R.id.txt_gzdw)
    TextView txtGzdw;
    @BindView(R.id.et_gzdw)
    EditText etGzdw;
    @BindView(R.id.rl_gzdw)
    RelativeLayout rlGzdw;
    @BindView(R.id.txt_jsd)
    TextView txtJsd;
    @BindView(R.id.et_szd)
    EditText etSzd;
    @BindView(R.id.rl_jsd)
    RelativeLayout rlJsd;
    @BindView(R.id.txt_yddh)
    TextView txtYddh;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.txt_dzyx)
    TextView txtDzyx;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.rl_email)
    RelativeLayout rlEmail;
    @BindView(R.id.txt_ajlb)
    TextView txtAjlb;
    @BindView(R.id.et_ajlb)
    EditText etAjlb;
    @BindView(R.id.rl_ajlb)
    RelativeLayout rlAjlb;
    @BindView(R.id.txt_bkgr_nr)
    TextView txtBkgrNr;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.txt_wdjfsj)
    TextView txtWdjfsj;
    @BindView(R.id.et_wdjfsj)
    EditText etWdjfsj;
    @BindView(R.id.rl_wdjfsj)
    RelativeLayout rlWdjfsj;
    @BindView(R.id.txt_yyjfdw)
    TextView txtYyjfdw;
    @BindView(R.id.et_yyjfdw)
    EditText etYyjfdw;
    @BindView(R.id.rl_yyjfdw)
    RelativeLayout rlYyjfdw;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;
    @BindView(R.id.content)
    MultiLineEditText contentView;

    private int[] checkIndexSex = {-1, -1, -1};
    private int[] checkIndexAjlb = {-1, -1, -1};
    private int checkIndexType = -1;
    CertificateTypeBean certificateTypeBean;
    private List<String> list = new ArrayList<>();
    List<String> zjlxStrBeans = new ArrayList<>();
    private int[] mzSelectOption = {-1, -1, -1};
    List<MzBean> mzBeans;
    List<String> mzStrBeans = new ArrayList<>();
    List<MzBean> gjBeans;
    List<String> gjStrBeans = new ArrayList<>();
    private TimePickerView mDatePicker;
    String cardTypeCode = "";
    int zjlxIndex = 0;
    int mzIndex = 0;
    int gjIndex = 0;


    @Override
    public String initTitleText() {
        return "预约律师接访";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {

    }

    @OnClick({R.id.et_xb,R.id.et_zjlx,R.id.et_mz,R.id.et_gj,R.id.et_ajlb,R.id.et_wdjfsj,R.id.btn_submit,R.id.btn_cancel})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.et_xb:{
                showSimpleBottomSheetList(0, etXb);
            }break;
            case R.id.et_zjlx:{
                getCertificataType();
            }break;
            case R.id.et_mz:{
                getMzDataForcChild(0,etMz);
            }break;
            case R.id.et_gj:{
                getGjMsg();
            }break;
            case R.id.et_ajlb:{
                showajlbSimpleBottomSheetList(0,etAjlb);
            }break;
            case R.id.et_wdjfsj:{
                showDatePicker();
            }break;
            case R.id.btn_submit:{
                if(!etName.getText().toString().trim().isEmpty()||!etZjlx.getText().toString().trim().isEmpty()||!etZjhm.getText().toString().trim().isEmpty()||!etSzd.getText().toString().trim().isEmpty()||!etPhone.getText().toString().trim().isEmpty()||!contentView.getEditText().getText().toString().trim().isEmpty()){
                    submit();
                }else {
                    XToast.info(LawyerInterViewActivity.this,"请输入内容").show();
                }
            }break;
            case R.id.btn_cancel:{
                finish();
            }
        }
    }

    private void showSimpleBottomSheetList(int pos, EditText textView) {
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

    public void getCertificataType(){
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "1")
                .asParser(new SimpleParser<CertificateTypeBean>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if ("200".equals(s.getResult())) {
                        certificateTypeBean = s;
                        for (int i = 0; i < certificateTypeBean.getData().size(); i++) {
                            zjlxStrBeans.add(certificateTypeBean.getData().get(i).getName());
                        }
                        showZjlxPickerView(etZjlx, 0);
                    } else {
                        XToast.error(LawyerInterViewActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(LawyerInterViewActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 证件类型
     */
    private void showZjlxPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                zjlxIndex = options1;
                tv.setText(certificateTypeBean.getData().get(options1).getName());
//                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(zjlxIndex)
                .build();
        pvOptions.setPicker(zjlxStrBeans);
        pvOptions.show();
    }

    public void getMzDataForcChild(int pos, EditText textView) {
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
                        showMzPickerView(textView, pos);
                    } else {
                        XToast.error(LawyerInterViewActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(LawyerInterViewActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 民族
     */
    private void showMzPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                mzIndex = options1;
                tv.setText(mzBeans.get(options1).getName());
                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("民族")
                .setSelectOptions(mzIndex)
                .build();
        pvOptions.setPicker(mzStrBeans);
        pvOptions.show();
    }

    public void getGjMsg(){
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
                        showGjPickerView(etGj, 0);
                    } else {
                        XToast.error(LawyerInterViewActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(LawyerInterViewActivity.this, throwable.getMessage()).show();
                });
    }

    /**
     * 国籍
     */
    private void showGjPickerView(EditText tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                gjIndex = options1;
                tv.setText(gjBeans.get(options1).getName());
                return false;
            }
        })
                .setTitleText("")
                .setSelectOptions(gjIndex)
                .build();
        pvOptions.setPicker(gjStrBeans);
        pvOptions.show();
    }

    private void showajlbSimpleBottomSheetList(int pos, EditText textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("")
                .addItem("举报类")
                .addItem("控告类")
                .addItem("申诉类")
                .addItem("国家司法救助类")
                .addItem("国家赔偿类")
                .addItem("民行类")
                .addItem("其他类")
                .setCheckedIndex(checkIndexAjlb[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexAjlb[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    /**
     * 日期选择
     */
    private void showDatePicker() {
        if (mDatePicker == null) {
            mDatePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelected(Date date, View v) {
//                    XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
                    etWdjfsj.setText(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
                }
            })
                    .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {
                            Log.i("pvTime", "onTimeSelectChanged");
                        }
                    })
                    .setTitleText("日期选择")
                    .build();
        }
        mDatePicker.show();
    }

    private void submit(){
        Map<String,String> map = new HashMap<>();
        map.put("username", UserUtils.getUserName());
//        map.put("orgId", "cbe523dfd3e24b83b95616492c56940d");
//        map.put("content", contentView.getContentText());
        map.put("source","小程序");
        map.put("type","3");
        if(!etZjlx.getText().toString().trim().isEmpty()){
            cardTypeCode = certificateTypeBean.getData().get(zjlxIndex).getCode();
            map.put("cardTypeCode",cardTypeCode);
        }
        if (!etName.getText().toString().trim().isEmpty()){
            map.put("name", etName.getText().toString().trim());
        }
        if (!etZjhm.getText().toString().trim().isEmpty()){
            map.put("cardCode", etZjhm.getText().toString().trim());
        }
        if (!etMz.getText().toString().trim().isEmpty()){
            map.put("nationCode", mzBeans.get(mzIndex).getCode());
        }

        if(!etXb.getText().toString().trim().isEmpty()){
            map.put("gender",etXb.getText().toString().trim());
        }
        if(!etGj.getText().toString().trim().isEmpty()){
            map.put("nationalityCode",gjBeans.get(gjIndex).getCode());
        }
        if(!etGzdw.getText().toString().trim().isEmpty()){
            map.put("orgunit",etGzdw.getText().toString().trim());
        }
        if(!etSzd.getText().toString().trim().isEmpty()){
            map.put("home",etSzd.getText().toString().trim());
        }
        if(!etPhone.getText().toString().trim().isEmpty()){
            map.put("phone",etPhone.getText().toString().trim());
        }
        if(!etEmail.getText().toString().trim().isEmpty()){
            map.put("email",etEmail.getText().toString().trim());
        }
        if(!etAjlb.getText().toString().trim().isEmpty()){
            map.put("caseType",etAjlb.getText().toString().trim());
        }
        if(!contentView.getEditText().getText().toString().trim().isEmpty()){
            map.put("content",contentView.getEditText().getText().toString().trim());
        }
        if(!etWdjfsj.getText().toString().trim().isEmpty()){
            map.put("receiveTime",etWdjfsj.getText().toString().trim());
        }
        map.put("organizationCode",TagConstant.CODE);
        map.put("userId",UserUtils.getId());
        map.put("userKeyId",UserUtils.getKeyId());
        map.put("userRealName",UserUtils.getRealName());
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
//        String a = JsonUtils.getInstance().gson.toJson(map);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.YYSPJF)
                .add("appId",TagConstant.APPID)
                .add("code",TagConstant.CODE)
                .add("data", a)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult()==200){
                        XToast.success(LawyerInterViewActivity.this,s.getMessage()).show();
                        finish();
                    }else {
                        XToast.error(LawyerInterViewActivity.this,s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(LawyerInterViewActivity.this,throwable.getMessage()).show();
                });
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_schedule_video;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

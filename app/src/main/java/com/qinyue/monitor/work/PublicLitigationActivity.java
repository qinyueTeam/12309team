package com.qinyue.monitor.work;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublicLitigationActivity extends BaseActivity {
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
    @BindView(R.id.txt_bkgr_jsdjcy)
    TextView txtBkgrJsdjcy;
    @BindView(R.id.et_jsjcy)
    EditText etJsjcy;
    @BindView(R.id.txt_bkgr_afd)
    TextView txtBkgrAfd;
    @BindView(R.id.et_afd)
    EditText etAfd;
    @BindView(R.id.txt_bkgr_nr)
    TextView txtBkgrNr;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.inputing_tv)
    TextView inputingTv;
    @BindView(R.id.total_tv)
    TextView totalTv;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.grid_view)
    GridView gridView;
    @BindView(R.id.txt_img_time)
    TextView txtImgTime;
    @BindView(R.id.et_img_time)
    EditText etImgTime;
    @BindView(R.id.txt_clue_name)
    TextView txtClueName;
    @BindView(R.id.et_clue_name)
    EditText etClueName;
    @BindView(R.id.txt_identification_number)
    TextView txtIdentificationNumber;
    @BindView(R.id.et_identification_number)
    EditText etIdentificationNumber;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.txt_phone_num)
    TextView txtPhoneNum;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;

    @Override
    public String initTitleText() {
        return "公益诉讼";
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
        return R.layout.activity_public_litigation;
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

package com.qinyue.monitor.work;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StateCompensationActivity extends BaseActivity {
    @BindView(R.id.txt_xm)
    TextView txtXm;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
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
    @BindView(R.id.txt_xb)
    TextView txtXb;
    @BindView(R.id.et_xb)
    EditText etXb;
    @BindView(R.id.rl_xb)
    RelativeLayout rlXb;
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
    @BindView(R.id.et_yyagx)
    EditText etYyagx;
    @BindView(R.id.txt_yyagx)
    TextView txtYyagx;
    @BindView(R.id.rl_yyagx)
    RelativeLayout rlYyagx;
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
    @BindView(R.id.txt_gzdw)
    TextView txtGzdw;
    @BindView(R.id.et_gzdw)
    EditText etGzdw;
    @BindView(R.id.rl_gzdw)
    RelativeLayout rlGzdw;
    @BindView(R.id.txt_dlrzjlx)
    TextView txtDlrzjlx;
    @BindView(R.id.et_dlrzjlx)
    EditText etDlrzjlx;
    @BindView(R.id.rl_dlrzjlx)
    RelativeLayout rlDlrzjlx;
    @BindView(R.id.txt_dlrzjhm)
    TextView txtDlrzjhm;
    @BindView(R.id.et_dlrzjhm)
    EditText etDlrzjhm;
    @BindView(R.id.rl_dlrzjhm)
    RelativeLayout rlDlrzjhm;
    @BindView(R.id.txt_dlrgzdw)
    TextView txtDlrgzdw;
    @BindView(R.id.et_dlrgzdw)
    EditText etDlrgzdw;
    @BindView(R.id.rl_dlrgzdw)
    RelativeLayout rlDlrgzdw;
    @BindView(R.id.txt_bkgr_jsdjcy)
    TextView txtBkgrJsdjcy;
    @BindView(R.id.et_jsjcy)
    EditText etJsjcy;
    @BindView(R.id.txt_bkgr_afd)
    TextView txtBkgrAfd;
    @BindView(R.id.et_afd)
    EditText etAfd;
    @BindView(R.id.txt_pcywjg)
    TextView txtPcywjg;
    @BindView(R.id.et_pcywjg)
    EditText etPcywjg;
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
    @BindView(R.id.img_kgcl)
    ImageView imgKgcl;
    @BindView(R.id.img_zjcl)
    ImageView imgZjcl;
    @BindView(R.id.img_fcws)
    ImageView imgFcws;
    @BindView(R.id.img_xgzjcl)
    ImageView imgXgzjcl;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;

    @Override
    public String initTitleText() {
        return "国家赔偿";
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
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

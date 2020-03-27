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

public class AccusationActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_xb)
    EditText et_xb;
    @BindView(R.id.et_zjlx)
    EditText et_zjlx;
    @BindView(R.id.et_zjhm)
    EditText et_zjhm;
    @BindView(R.id.et_gj)
    EditText et_gj;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_gzdw)
    EditText et_gzdw;
    @BindView(R.id.et_szd)
    EditText et_szd;
    @BindView(R.id.et_bkgr_name)
    EditText et_bkgr_name;
    @BindView(R.id.et_bkgr_xb)
    EditText et_bkgr_xb;
    @BindView(R.id.et_bkgr_gj)
    EditText et_bkgr_gj;
    @BindView(R.id.et_bkgr_dwmc)
    EditText et_bkgr_dwmc;
    @BindView(R.id.et_bkgr_dwdz)
    EditText et_bkgr_dwdz;
    @BindView(R.id.et_bkgr_zw)
    EditText et_bkgr_zw;
    @BindView(R.id.et_bkgr_zj)
    EditText et_bkgr_zj;
    @BindView(R.id.et_bkgr_sf)
    EditText et_bkgr_sf;
    @BindView(R.id.et_bkgr_rddb)
    EditText et_bkgr_rddb;
    @BindView(R.id.et_bkgr_zxwy)
    EditText et_bkgr_zxwy;
    @BindView(R.id.et_jsjcy)
    EditText et_jsjcy;
    @BindView(R.id.et_afd)
    EditText et_afd;
    @BindView(R.id.et_input)
    EditText et_input;
    @BindView(R.id.inputing_tv)
    TextView inputing_tv;
    @BindView(R.id.img_kgcl)
    ImageView img_kgcl;
    @BindView(R.id.img_zjcl)
    ImageView img_zjcl;
    @BindView(R.id.img_qtcl)
    ImageView img_qtcl;
    @BindView(R.id.btn_submit)
    TextView btn_submit;
    @BindView(R.id.btn_cancel)
    TextView btn_cancel;
    @BindView(R.id.rl_name)
    RelativeLayout rl_name;
    @BindView(R.id.rl_xb)
    RelativeLayout rl_xb;
    @BindView(R.id.rl_zjlx)
    RelativeLayout rl_zjlx;
    @BindView(R.id.rl_mz)
    RelativeLayout rl_mz;
    @BindView(R.id.rl_gj)
    RelativeLayout rl_gj;
    @BindView(R.id.rl_phone)
    RelativeLayout rl_phone;
    @BindView(R.id.rl_email)
    RelativeLayout rl_email;
    @BindView(R.id.rl_gzdw)
    RelativeLayout rl_gzdw;
    @BindView(R.id.rl_jsd)
    RelativeLayout rl_jsd;
    @BindView(R.id.rl_bkgr_name)
    RelativeLayout rl_bkgr_name;
    @BindView(R.id.rl_bkgr_xb)
    RelativeLayout rl_bkgr_xb;
    @BindView(R.id.rl_content)
    RelativeLayout rl_content;
    @BindView(R.id.txt_xm)
    TextView txtXm;
    @BindView(R.id.txt_xb)
    TextView txtXb;
    @BindView(R.id.txt_zjlx)
    TextView txtZjlx;
    @BindView(R.id.txt_zjhm)
    TextView txtZjhm;
    @BindView(R.id.rl_zjhm)
    RelativeLayout rlZjhm;
    @BindView(R.id.txt_mz)
    TextView txtMz;
    @BindView(R.id.et_mz)
    EditText etMz;
    @BindView(R.id.txt_gj)
    TextView txtGj;
    @BindView(R.id.txt_yddh)
    TextView txtYddh;
    @BindView(R.id.txt_dzyx)
    TextView txtDzyx;
    @BindView(R.id.txt_gzdw)
    TextView txtGzdw;
    @BindView(R.id.txt_jsd)
    TextView txtJsd;
    @BindView(R.id.txt_bkgr_xm)
    TextView txtBkgrXm;
    @BindView(R.id.txt_bkgr_xb)
    TextView txtBkgrXb;
    @BindView(R.id.txt_bkgr_gj)
    TextView txtBkgrGj;
    @BindView(R.id.rl_bkgr_gj)
    RelativeLayout rlBkgrGj;
    @BindView(R.id.txt_bkgr_gzdwqc)
    TextView txtBkgrGzdwqc;
    @BindView(R.id.rl_bkgr_gzdwqc)
    RelativeLayout rlBkgrGzdwqc;
    @BindView(R.id.txt_bkgr_gzdwszd)
    TextView txtBkgrGzdwszd;
    @BindView(R.id.rl_bkgr_dwszd)
    RelativeLayout rlBkgrDwszd;
    @BindView(R.id.txt_bkgr_zw)
    TextView txtBkgrZw;
    @BindView(R.id.rl_bkgr_zw)
    RelativeLayout rlBkgrZw;
    @BindView(R.id.txt_bkgr_zj)
    TextView txtBkgrZj;
    @BindView(R.id.rl_bkgr_zj)
    RelativeLayout rlBkgrZj;
    @BindView(R.id.txt_bkgr_sf)
    TextView txtBkgrSf;
    @BindView(R.id.rl_bkgr_sf)
    RelativeLayout rlBkgrSf;
    @BindView(R.id.txt_bkgr_rddb)
    TextView txtBkgrRddb;
    @BindView(R.id.rl_bkgr_rddb)
    RelativeLayout rlBkgrRddb;
    @BindView(R.id.txt_bkgr_zxwy)
    TextView txtBkgrZxwy;
    @BindView(R.id.rl_bkgr_zxwy)
    RelativeLayout rlBkgrZxwy;
    @BindView(R.id.txt_bkgr_jsdjcy)
    TextView txtBkgrJsdjcy;
    @BindView(R.id.txt_bkgr_afd)
    TextView txtBkgrAfd;
    @BindView(R.id.txt_bkgr_nr)
    TextView txtBkgrNr;
    @BindView(R.id.total_tv)
    TextView totalTv;

    @Override
    public String initTitleText() {
        return "控告";
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
        return R.layout.activity_accusation;
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

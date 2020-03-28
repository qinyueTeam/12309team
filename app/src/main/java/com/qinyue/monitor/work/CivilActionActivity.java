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

public class CivilActionActivity extends BaseActivity {
    @BindView(R.id.txt_xm)
    TextView txtXm;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.txt_yyagx_ms)
    TextView txtYyagxMs;
    @BindView(R.id.et_yyagx_ms)
    EditText etYyagxMs;
    @BindView(R.id.rl_yyagx_ms)
    RelativeLayout rlYyagxMs;
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
    @BindView(R.id.txt_mz)
    TextView txtMz;
    @BindView(R.id.et_mz)
    EditText etMz;
    @BindView(R.id.rl_mz)
    RelativeLayout rlMz;
    @BindView(R.id.txt_fddbzw)
    TextView txtFddbzw;
    @BindView(R.id.et_fddbzw)
    EditText etFddbzw;
    @BindView(R.id.rl_fddbzw)
    RelativeLayout rlFddbzw;
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
    @BindView(R.id.txt_sddz)
    TextView txtSddz;
    @BindView(R.id.et_sddz)
    EditText etSddz;
    @BindView(R.id.rl_sddz)
    RelativeLayout rlSddz;
    @BindView(R.id.txt_bkgr_xm)
    TextView txtBkgrXm;
    @BindView(R.id.et_bkgr_name)
    EditText etBkgrName;
    @BindView(R.id.rl_bkgr_name)
    RelativeLayout rlBkgrName;
    @BindView(R.id.txt_bkgr_yyagx)
    TextView txtBkgrYyagx;
    @BindView(R.id.et_bkgr_yyagx)
    EditText etBkgrYyagx;
    @BindView(R.id.rl_bkgr_yyagx)
    RelativeLayout rlBkgrYyagx;
    @BindView(R.id.txt_bkgr_xb)
    TextView txtBkgrXb;
    @BindView(R.id.et_bkgr_xb)
    EditText etBkgrXb;
    @BindView(R.id.rl_bkgr_xb)
    RelativeLayout rlBkgrXb;
    @BindView(R.id.txt_bkgr_gj)
    TextView txtBkgrGj;
    @BindView(R.id.et_bkgr_gj)
    EditText etBkgrGj;
    @BindView(R.id.rl_bkgr_gj)
    RelativeLayout rlBkgrGj;
    @BindView(R.id.txt_bkgr_gzdwqc)
    TextView txtBkgrGzdwqc;
    @BindView(R.id.et_bkgr_dwmc)
    EditText etBkgrDwmc;
    @BindView(R.id.rl_bkgr_gzdwqc)
    RelativeLayout rlBkgrGzdwqc;
    @BindView(R.id.txt_bkgr_gzdwszd)
    TextView txtBkgrGzdwszd;
    @BindView(R.id.et_bkgr_dwdz)
    EditText etBkgrDwdz;
    @BindView(R.id.rl_bkgr_dwszd)
    RelativeLayout rlBkgrDwszd;
    @BindView(R.id.txt_bkgr_zw)
    TextView txtBkgrZw;
    @BindView(R.id.et_bkgr_zw)
    EditText etBkgrZw;
    @BindView(R.id.rl_bkgr_zw)
    RelativeLayout rlBkgrZw;
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
    @BindView(R.id.txt_yscp)
    TextView txtYscp;
    @BindView(R.id.et_yscp)
    EditText etYscp;
    @BindView(R.id.txt_yscpry)
    TextView txtYscpry;
    @BindView(R.id.et_yscpry)
    EditText etYscpry;
    @BindView(R.id.txt_yscpsw)
    TextView txtYscpsw;
    @BindView(R.id.et_yscpsw)
    EditText etYscpsw;
    @BindView(R.id.txt_esfy)
    TextView txtEsfy;
    @BindView(R.id.et_esfy)
    EditText etEsfy;
    @BindView(R.id.txt_escp)
    TextView txtEscp;
    @BindView(R.id.et_escp)
    EditText etEscp;
    @BindView(R.id.txt_escpry)
    TextView txtEscpry;
    @BindView(R.id.et_escpry)
    EditText etEscpry;
    @BindView(R.id.txt_escpsw)
    TextView txtEscpsw;
    @BindView(R.id.et_escpsw)
    EditText etEscpsw;
    @BindView(R.id.txt_zsfy)
    TextView txtZsfy;
    @BindView(R.id.et_zsfy)
    EditText etZsfy;
    @BindView(R.id.txt_zscp)
    TextView txtZscp;
    @BindView(R.id.et_scp)
    EditText etScp;
    @BindView(R.id.txt_zscpry)
    TextView txtZscpry;
    @BindView(R.id.et_zscpry)
    EditText etZscpry;
    @BindView(R.id.txt_zscpsw)
    TextView txtZscpsw;
    @BindView(R.id.et_zscpsw)
    EditText etZscpsw;
    @BindView(R.id.txt_zongsfy)
    TextView txtZongsfy;
    @BindView(R.id.et_zongsfy)
    EditText etZongsfy;
    @BindView(R.id.txt_zongscp)
    TextView txtZongscp;
    @BindView(R.id.et_zongscp)
    EditText etZongscp;
    @BindView(R.id.txt_zongscpry)
    TextView txtZongscpry;
    @BindView(R.id.et_zongscpry)
    EditText etZongscpry;
    @BindView(R.id.txt_zongscpsw)
    TextView txtZongscpsw;
    @BindView(R.id.et_zongscpsw)
    EditText etZongscpsw;
    @BindView(R.id.txt_fyslzsrq)
    TextView txtFyslzsrq;
    @BindView(R.id.et_fyslzsrq)
    EditText etFyslzsrq;
    @BindView(R.id.txt_fyslsqswh)
    TextView txtFyslsqswh;
    @BindView(R.id.et_fyslsqswh)
    EditText etFyslsqswh;
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
        return "民事诉讼";
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
        return R.layout.activity_civil_action;
    }

    @Override
    protected void init() {

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

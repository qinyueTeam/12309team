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

public class UnderageAccusationActivity extends BaseActivity {
    @BindView(R.id.txt_xm)
    TextView txtXm;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.txt_zym)
    TextView txtZym;
    @BindView(R.id.et_zym)
    EditText etZym;
    @BindView(R.id.rl_zym)
    RelativeLayout rlZym;
    @BindView(R.id.txt_xb)
    TextView txtXb;
    @BindView(R.id.et_xb)
    EditText etXb;
    @BindView(R.id.rl_xb)
    RelativeLayout rlXb;
    @BindView(R.id.txt_ch)
    TextView txtCh;
    @BindView(R.id.et_ch)
    EditText etCh;
    @BindView(R.id.rl_ch)
    RelativeLayout rlCh;
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
    @BindView(R.id.txt_zsddz)
    TextView txtZsddz;
    @BindView(R.id.et_zsddz)
    EditText etZsddz;
    @BindView(R.id.rl_zsddz)
    RelativeLayout rlZsddz;
    @BindView(R.id.txt_gzdw)
    TextView txtGzdw;
    @BindView(R.id.et_gzdw)
    EditText etGzdw;
    @BindView(R.id.rl_gzdw)
    RelativeLayout rlGzdw;
    @BindView(R.id.txt_gzdwxxszd)
    TextView txtGzdwxxszd;
    @BindView(R.id.et_gzdwxxszd)
    EditText etGzdwxxszd;
    @BindView(R.id.rl_gzdwxxszd)
    RelativeLayout rlGzdwxxszd;
    @BindView(R.id.txt_fddlr)
    TextView txtFddlr;
    @BindView(R.id.et_fddlr)
    EditText etFddlr;
    @BindView(R.id.rl_fddlr)
    RelativeLayout rlFddlr;
    @BindView(R.id.txt_jhqk)
    TextView txtJhqk;
    @BindView(R.id.et_jhqk)
    EditText etJhqk;
    @BindView(R.id.rl_jhqk)
    RelativeLayout rlJhqk;
    @BindView(R.id.txt_jsd)
    TextView txtJsd;
    @BindView(R.id.et_szd)
    EditText etSzd;
    @BindView(R.id.rl_jsd)
    RelativeLayout rlJsd;
    @BindView(R.id.txt_bkgr_xm)
    TextView txtBkgrXm;
    @BindView(R.id.et_bkgr_name)
    EditText etBkgrName;
    @BindView(R.id.rl_bkgr_name)
    RelativeLayout rlBkgrName;
    @BindView(R.id.txt_ywcngz)
    TextView txtYwcngz;
    @BindView(R.id.et_ywcngz)
    EditText etYwcngz;
    @BindView(R.id.rl_ywcngz)
    RelativeLayout rlYwcngz;
    @BindView(R.id.txt_bkgr_xb)
    TextView txtBkgrXb;
    @BindView(R.id.et_bkgr_xb)
    EditText etBkgrXb;
    @BindView(R.id.rl_bkgr_xb)
    RelativeLayout rlBkgrXb;
    @BindView(R.id.txt_bkgr_zjlx)
    TextView txtBkgrZjlx;
    @BindView(R.id.et_bkgr_zjlx)
    EditText etBkgrZjlx;
    @BindView(R.id.rl_bkgr_zjlx)
    RelativeLayout rlBkgrZjlx;
    @BindView(R.id.txt_bkgr_zjhm)
    TextView txtBkgrZjhm;
    @BindView(R.id.et_bkgr_zjhm)
    EditText etBkgrZjhm;
    @BindView(R.id.rl_bkgr_zjhm)
    RelativeLayout rlBkgrZjhm;
    @BindView(R.id.txt_bkgr_mz)
    TextView txtBkgrMz;
    @BindView(R.id.et_bkgr_mz)
    EditText etBkgrMz;
    @BindView(R.id.rl_bkgr_mz)
    RelativeLayout rlBkgrMz;
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
    @BindView(R.id.txt_bkgr_sf)
    TextView txtBkgrSf;
    @BindView(R.id.et_bkgr_sf)
    EditText etBkgrSf;
    @BindView(R.id.rl_bkgr_sf)
    RelativeLayout rlBkgrSf;
    @BindView(R.id.txt_bkgr_rddb)
    TextView txtBkgrRddb;
    @BindView(R.id.et_bkgr_rddb)
    EditText etBkgrRddb;
    @BindView(R.id.rl_bkgr_rddb)
    RelativeLayout rlBkgrRddb;
    @BindView(R.id.txt_wbkgr_name)
    TextView txtWbkgrName;
    @BindView(R.id.et_wbkgr_name)
    EditText etWbkgrName;
    @BindView(R.id.rl_wbkgr_name)
    RelativeLayout rlWbkgrName;
    @BindView(R.id.txt_wxb)
    TextView txtWxb;
    @BindView(R.id.et_wxb)
    EditText etWxb;
    @BindView(R.id.rl_wxb)
    RelativeLayout rlWxb;
    @BindView(R.id.txt_wzw)
    TextView txtWzw;
    @BindView(R.id.et_wzw)
    EditText etWzw;
    @BindView(R.id.rl_wzw)
    RelativeLayout rlWzw;
    @BindView(R.id.txt_wsf)
    TextView txtWsf;
    @BindView(R.id.et_wsf)
    EditText etWsf;
    @BindView(R.id.rl_wsf)
    RelativeLayout rlWsf;
    @BindView(R.id.txt_wqtgzsf)
    TextView txtWqtgzsf;
    @BindView(R.id.et_wqtgzsf)
    EditText etWqtgzsf;
    @BindView(R.id.rl_wqtgzsf)
    RelativeLayout rlWqtgzsf;
    @BindView(R.id.txt_wdwszd)
    TextView txtWdwszd;
    @BindView(R.id.et_wdwszd)
    EditText etWdwszd;
    @BindView(R.id.rl_wdwszd)
    RelativeLayout rlWdwszd;
    @BindView(R.id.txt_wzz)
    TextView txtWzz;
    @BindView(R.id.et_wzz)
    EditText etWzz;
    @BindView(R.id.rl_wzz)
    RelativeLayout rlWzz;
    @BindView(R.id.txt_wgzdw)
    TextView txtWgzdw;
    @BindView(R.id.et_wgzdw)
    EditText etWgzdw;
    @BindView(R.id.rl_wgzdw)
    RelativeLayout rlWgzdw;
    @BindView(R.id.txt_bkgr_afd)
    TextView txtBkgrAfd;
    @BindView(R.id.et_afd)
    EditText etAfd;
    @BindView(R.id.txt_kgsxfssj)
    TextView txtKgsxfssj;
    @BindView(R.id.et_kgsxfssj)
    EditText etKgsxfssj;
    @BindView(R.id.txt_bkgr_jsdjcy)
    TextView txtBkgrJsdjcy;
    @BindView(R.id.et_jsjcy)
    EditText etJsjcy;
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
    @BindView(R.id.img_qtcl)
    ImageView imgQtcl;
    @BindView(R.id.txt_kgxsay)
    TextView txtKgxsay;
    @BindView(R.id.et_kgxsay)
    EditText etKgxsay;
    @BindView(R.id.rl_kgxsay)
    RelativeLayout rlKgxsay;
    @BindView(R.id.txt_kgqtxsay)
    TextView txtKgqtxsay;
    @BindView(R.id.et_kgqtxsay)
    EditText etKgqtxsay;
    @BindView(R.id.rl_kgqtxsay)
    RelativeLayout rlKgqtxsay;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;

    @Override
    public String initTitleText() {
        return "控告";//未成年控告
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
        return R.layout.activity_underage_accusation;
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

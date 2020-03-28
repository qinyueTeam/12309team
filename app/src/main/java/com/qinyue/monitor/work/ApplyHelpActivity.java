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

public class ApplyHelpActivity extends BaseActivity {
    @BindView(R.id.txt_xm)
    TextView txtXm;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.txt_wcnrsf)
    TextView txtWcnrsf;
    @BindView(R.id.et_wcnrsf)
    EditText etWcnrsf;
    @BindView(R.id.rl_wcnrsf)
    RelativeLayout rlWcnrsf;
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
    @BindView(R.id.txt_csrq)
    TextView txtCsrq;
    @BindView(R.id.et_csrq)
    EditText etCsrq;
    @BindView(R.id.rl_csrq)
    RelativeLayout rlCsrq;
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
    @BindView(R.id.txt_sqjz_phone)
    TextView txtSqjzPhone;
    @BindView(R.id.et_sqjz_phone)
    EditText etSqjzPhone;
    @BindView(R.id.rl_sqjz_phone)
    RelativeLayout rlSqjzPhone;
    @BindView(R.id.txt_sqjz_zsd)
    TextView txtSqjzZsd;
    @BindView(R.id.et_sqjz_zsd)
    EditText etSqjzZsd;
    @BindView(R.id.rl_sqjz_zsd)
    RelativeLayout rlSqjzZsd;
    @BindView(R.id.txt_zsddz)
    TextView txtZsddz;
    @BindView(R.id.et_zsddz)
    EditText etZsddz;
    @BindView(R.id.rl_zsddz)
    RelativeLayout rlZsddz;
    @BindView(R.id.txt_sqjz_gzdw)
    TextView txtSqjzGzdw;
    @BindView(R.id.et_sqjz_gzdw)
    EditText etSqjzGzdw;
    @BindView(R.id.rl_sqjz_gzdw)
    RelativeLayout rlSqjzGzdw;
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
    @BindView(R.id.txt_jsd_sqjz)
    TextView txtJsdSqjz;
    @BindView(R.id.et_jsd_sqjz)
    EditText etJsdSqjz;
    @BindView(R.id.rl_jsd_sqjz)
    RelativeLayout rlJsdSqjz;
    @BindView(R.id.txt_bkgr_xm)
    TextView txtBkgrXm;
    @BindView(R.id.et_bkgr_name)
    EditText etBkgrName;
    @BindView(R.id.rl_bkgr_name)
    RelativeLayout rlBkgrName;
    @BindView(R.id.txt_wcnrgx)
    TextView txtWcnrgx;
    @BindView(R.id.et_wcnrgx)
    EditText etWcnrgx;
    @BindView(R.id.rl_wcnrgx)
    RelativeLayout rlWcnrgx;
    @BindView(R.id.txt_bkgr_xb)
    TextView txtBkgrXb;
    @BindView(R.id.et_bkgr_xb)
    EditText etBkgrXb;
    @BindView(R.id.rl_bkgr_xb)
    RelativeLayout rlBkgrXb;
    @BindView(R.id.txt_wzjlx)
    TextView txtWzjlx;
    @BindView(R.id.et_wzjlx)
    EditText etWzjlx;
    @BindView(R.id.rl_wzjlx)
    RelativeLayout rlWzjlx;
    @BindView(R.id.txt_wzjhm)
    TextView txtWzjhm;
    @BindView(R.id.et_wzjhm)
    EditText etWzjhm;
    @BindView(R.id.rl_wzjhm)
    RelativeLayout rlWzjhm;
    @BindView(R.id.txt_wmz)
    TextView txtWmz;
    @BindView(R.id.et_wmz)
    EditText etWmz;
    @BindView(R.id.rl_wmz)
    RelativeLayout rlWmz;
    @BindView(R.id.txt_bkgr_gj)
    TextView txtBkgrGj;
    @BindView(R.id.et_bkgr_gj)
    EditText etBkgrGj;
    @BindView(R.id.rl_bkgr_gj)
    RelativeLayout rlBkgrGj;
    @BindView(R.id.txt_bkgr_zw)
    TextView txtBkgrZw;
    @BindView(R.id.et_bkgr_zw)
    EditText etBkgrZw;
    @BindView(R.id.rl_bkgr_zw)
    RelativeLayout rlBkgrZw;
    @BindView(R.id.txt_bkgr_zj)
    TextView txtBkgrZj;
    @BindView(R.id.et_bkgr_zj)
    EditText etBkgrZj;
    @BindView(R.id.rl_bkgr_zj)
    RelativeLayout rlBkgrZj;
    @BindView(R.id.txt_bkgr_sf)
    TextView txtBkgrSf;
    @BindView(R.id.et_bkgr_sf)
    EditText etBkgrSf;
    @BindView(R.id.rl_bkgr_sf)
    RelativeLayout rlBkgrSf;
    @BindView(R.id.txt_bkgr_zxwy)
    TextView txtBkgrZxwy;
    @BindView(R.id.et_bkgr_zxwy)
    EditText etBkgrZxwy;
    @BindView(R.id.rl_bkgr_zxwy)
    RelativeLayout rlBkgrZxwy;
    @BindView(R.id.txt_bkgr_jsdjcy)
    TextView txtBkgrJsdjcy;
    @BindView(R.id.et_jsjcy)
    EditText etJsjcy;
    @BindView(R.id.txt_bkgr_afd)
    TextView txtBkgrAfd;
    @BindView(R.id.et_afd)
    EditText etAfd;
    @BindView(R.id.txt_yaay)
    TextView txtYaay;
    @BindView(R.id.et_yaay)
    EditText etYaay;
    @BindView(R.id.txt_ybajc)
    TextView txtYbajc;
    @BindView(R.id.et_ybajc)
    EditText etYbajc;
    @BindView(R.id.txt_sqjzlb)
    TextView txtSqjzlb;
    @BindView(R.id.et_sqjzlb)
    EditText etSqjzlb;
    @BindView(R.id.txt_sqly)
    TextView txtSqly;
    @BindView(R.id.et_sqly)
    EditText etSqly;
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
    @BindView(R.id.txt_sqjznr)
    TextView txtSqjznr;
    @BindView(R.id.et_input_sqjznr)
    EditText etInputSqjznr;
    @BindView(R.id.inputing_tv_sqjznr)
    TextView inputingTvSqjznr;
    @BindView(R.id.total_tv_sqjznr)
    TextView totalTvSqjznr;
    @BindView(R.id.rl_content_sqjznr)
    RelativeLayout rlContentSqjznr;
    @BindView(R.id.img_qtcl)
    ImageView imgQtcl;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;

    @Override
    public String initTitleText() {
        return "申请救助";
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
        return R.layout.activity_apply_help;
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

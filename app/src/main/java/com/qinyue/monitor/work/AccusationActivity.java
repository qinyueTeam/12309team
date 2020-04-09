package com.qinyue.monitor.work;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.AnimUtils;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean2;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.first.AddJczxxActivity;
import com.qinyue.monitor.first.JwjdActivty;
import com.qinyue.monitor.first.MzBean;
import com.qinyue.monitor.first.UpDataFileBean;
import com.qinyue.monitor.login.RegisterActivity;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.qinyue.monitor.view.ChooseAfdActivity;
import com.qinyue.monitor.view.ChooseSfActivity;
import com.qinyue.monitor.view.SelectSectionParentEntity;
import com.tencent.aai.AAIClient;
import com.tencent.aai.audio.data.AudioRecordDataSource;
import com.tencent.aai.auth.AbsCredentialProvider;
import com.tencent.aai.auth.LocalCredentialProvider;
import com.tencent.aai.config.ClientConfiguration;
import com.tencent.aai.exception.ClientException;
import com.tencent.aai.exception.ServerException;
import com.tencent.aai.listener.AudioRecognizeResultListener;
import com.tencent.aai.listener.AudioRecognizeStateListener;
import com.tencent.aai.listener.AudioRecognizeTimeoutListener;
import com.tencent.aai.log.AAILogger;
import com.tencent.aai.model.AudioRecognizeRequest;
import com.tencent.aai.model.AudioRecognizeResult;
import com.tencent.aai.model.type.AudioRecognizeConfiguration;
import com.tencent.aai.model.type.AudioRecognizeTemplate;
import com.tencent.aai.model.type.EngineModelType;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.toast.XToast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

public class AccusationActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.et_zjhm)
    EditText zjhmEdit;
    @BindView(R.id.et_bkgr_name)
    EditText bNameEdit;
    @BindView(R.id.et_phone)
    EditText phoneEdit;
    @BindView(R.id.et_email)
    EditText emailEdit;
    @BindView(R.id.et_gzdw)
    EditText gzdwEdit;
    @BindView(R.id.et_bkgr_dwmc)
    EditText bGzdwEdit;
    @BindView(R.id.et_szd)
    EditText jsdEdit;
    @BindView(R.id.et_bkgr_dwdz)
    EditText bDwdzEdit;
    @BindView(R.id.et_bkgr_zw)
    EditText bZwEdit;
    @BindView(R.id.et_xb)
    TextView xbTv;
    @BindView(R.id.et_bkgr_xb)
    TextView bXbTv;
    @BindView(R.id.et_zjlx)
    TextView zjlxTv;
    @BindView(R.id.et_mz)
    TextView mzTv;
    @BindView(R.id.et_gj)
    TextView gjTv;
    @BindView(R.id.et_bkgr_gj)
    TextView bGjTv;
    @BindView(R.id.et_bkgr_zj)
    TextView bZjTv;
    @BindView(R.id.et_bkgr_sf)
    TextView bSfTv;
    @BindView(R.id.et_bkgr_rddb)
    TextView bRddbTv;
    @BindView(R.id.et_afd)
    TextView bAfdTv;
    @BindView(R.id.et_bkgr_zxwy)
    TextView bZxwyTv;
    @BindView(R.id.content)
    MultiLineEditText contentTv;
    @BindView(R.id.img_kgcl)
    ImageView kgclImg;
    @BindView(R.id.img_zjcl)
    ImageView zjclImg;
    @BindView(R.id.img_qtcl)
    ImageView qtlImg;

    private int[] checkIndexSex = {-1, -1};
    private int[] checkIndexGj = {-1, -1};

    List<MzBean> mzBeans;
    List<String> mzStrBeans = new ArrayList<>();
    List<MzBean> zjBeans;
    List<String> zjStrBeans = new ArrayList<>();
    List<MzBean> gjBeans;
    List<String> gjStrBeans = new ArrayList<>();
    List<MzBean> bZjBeans;
    List<String> bZjStrBeans = new ArrayList<>();
    private int mzIndex = -1;
    List<MzBean> rddbBeans;
    List<String> rddbStrBeans = new ArrayList<>();
    private int rddbIndex = -1;
    private int zjIndex = -1;
    private int bZjIndex = -1;
    private int zxwyIndex = -1;
    //身份的对象
    SelectSectionParentEntity sfBean;
    SelectSectionParentEntity afdBean;
    private int clIndex = 0;
    SparseArray<LocalMedia> selectPhoto = new SparseArray<>();
    SparseArray<UpDataFileBean> fileIds = new SparseArray<>();

    AAIClient aaiClient;

    AbsCredentialProvider credentialProvider;

    @Override
    public String initTitleText() {
        return "控告";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (UserUtils.getSex().equals("男")) {
            checkIndexSex[0] = 0;
        } else {
            checkIndexSex[0] = 1;
        }
        // 签名鉴权类，sdk中给出了一个本地的鉴权类，但由于需要用户提供secretKey，这可能会导致一些安全上的问题，
        // 因此，请用户自行实现CredentialProvider接口
        credentialProvider = new LocalCredentialProvider(TagConstant.YSECRETKEY);

        // 用户配置
        ClientConfiguration.setServerProtocolHttps(false); // 是否启用https，默认启用
        ClientConfiguration.setMaxAudioRecognizeConcurrentNumber(2); // 语音识别的请求的最大并发数
        ClientConfiguration.setMaxRecognizeSliceConcurrentNumber(10); // 单个请求的分片最大并发数
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


    // 识别结果回调监听器
    final AudioRecognizeResultListener audioRecognizeResultlistener = new AudioRecognizeResultListener() {

        boolean dontHaveResult = true;

        /**
         * 返回分片的识别结果
         * @param request 相应的请求
         * @param result 识别结果
         * @param seq 该分片所在语音流的序号 (0, 1, 2...)
         */
        @Override
        public void onSliceSuccess(AudioRecognizeRequest request, AudioRecognizeResult result, int seq) {

            if (dontHaveResult && !TextUtils.isEmpty(result.getText())) {
                dontHaveResult = false;
            }
            if (audioEdit != null && !result.getText().isEmpty()) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        audioEdit.setText(result.getText().replace("。", ""));
                        audioEdit = null;
                    }
                });
            } else if (multiLineEditText != null && !result.getText().isEmpty()) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        multiLineEditText.setContentText(result.getText().replace("。", ""));
                        multiLineEditText = null;
                    }
                });
            }

        }

        /**
         * 返回语音流的识别结果
         * @param request 相应的请求
         * @param result 识别结果
         * @param seq 该语音流的序号 (1, 2, 3...)
         */
        @Override
        public void onSegmentSuccess(AudioRecognizeRequest request, AudioRecognizeResult result, int seq) {
            dontHaveResult = true;
        }

        /**
         * 识别结束回调，返回所有的识别结果
         * @param request 相应的请求
         * @param result 识别结果
         */
        @Override
        public void onSuccess(AudioRecognizeRequest request, final String result) {
            Log.i("AUDIO", "返回所有的识别结果:" + result);
            if (audioEdit != null && !result.isEmpty()) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        audioEdit.setText(result.replace("。", ""));
                        audioEdit = null;
                    }
                });
            } else if (multiLineEditText != null && !result.isEmpty()) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        multiLineEditText.setContentText(result.replace("。", ""));
                        multiLineEditText = null;
                    }
                });
            }

        }

        /**
         * 识别失败
         * @param request 相应的请求
         * @param clientException 客户端异常
         * @param serverException 服务端异常
         */
        @Override
        public void onFailure(AudioRecognizeRequest request, final ClientException clientException, final ServerException serverException) {
            Log.i("AUDIO", "识别失败");
        }
    };


    /**
     * 识别状态监听器
     */
    final AudioRecognizeStateListener audioRecognizeStateListener = new AudioRecognizeStateListener() {

        /**
         * 开始录音
         * @param request
         */
        @Override
        public void onStartRecord(AudioRecognizeRequest request) {
            currentRequestId = request.getRequestId();
            Log.i("AUDIO", "真正开始录音");
        }

        /**
         * 结束录音
         * @param request
         */
        @Override
        public void onStopRecord(AudioRecognizeRequest request) {
            Log.i("AUDIO", "真正结束录音:" + request.toString());
        }

        /**
         * 第seq个语音流开始识别
         * @param request
         * @param seq
         */
        @Override
        public void onVoiceFlowStartRecognize(AudioRecognizeRequest request, int seq) {
        }

        /**
         * 第seq个语音流结束识别
         * @param request
         * @param seq
         */
        @Override
        public void onVoiceFlowFinishRecognize(AudioRecognizeRequest request, int seq) {

            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String time = format.format(date);
            String message = String.format("voice flow order = %d, recognize finish in %s", seq, time);
            Log.i("AUDIO", "第seq个语音流结束识别message:" + message);
        }

        /**
         * 第seq个语音流开始
         * @param request
         * @param seq
         */
        @Override
        public void onVoiceFlowStart(AudioRecognizeRequest request, int seq) {
//
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String time = format.format(date);
            String message = String.format("voice flow order = %d, start in %s", seq, time);
            Log.i("AUDIO", "第seq个语音流开始message:" + message);
        }

        /**
         * 第seq个语音流结束
         * @param request
         * @param seq
         */
        @Override
        public void onVoiceFlowFinish(AudioRecognizeRequest request, int seq) {

            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String time = format.format(date);
            String message = String.format("voice flow order = %d, stop in %s", seq, time);
            Log.i("AUDIO", "第seq个语音流结束message:" + message);
        }

        /**
         * 语音音量回调
         * @param request
         * @param volume
         */
        @Override
        public void onVoiceVolume(AudioRecognizeRequest request, final int volume) {
        }

    };
    /**
     * 识别超时监听器
     */
    final AudioRecognizeTimeoutListener audioRecognizeTimeoutListener = new AudioRecognizeTimeoutListener() {

        /**
         * 检测第一个语音流超时
         * @param request
         */
        @Override
        public void onFirstVoiceFlowTimeout(AudioRecognizeRequest request) {
            Log.i("AUDIO", "检测第一个语音流超时");
        }

        /**
         * 检测下一个语音流超时
         * @param request
         */
        @Override
        public void onNextVoiceFlowTimeout(AudioRecognizeRequest request) {
            Log.i("AUDIO", "检测下一个语音流超时");
        }
    };

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
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
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
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }

    public void getBzjDataForcChild() {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "4")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        bZjBeans = s.getData();
                        for (int i = 0; i < bZjBeans.size(); i++) {
                            bZjStrBeans.add(bZjBeans.get(i).getName());
                        }
                        showBzjPickerView();
                    } else {
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }

    public void getRddbDataForcChild() {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "6")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        rddbBeans = s.getData();
                        for (int i = 0; i < rddbBeans.size(); i++) {
                            rddbStrBeans.add(rddbBeans.get(i).getName());
                        }
                        showRddbPickerView();
                    } else {
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }

    public void getGjDataForcChild(int pos, TextView tv) {
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
                        showGjPickerView(pos, tv);
                    } else {
                        XToast.error(AccusationActivity.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });
    }

    int currentRequestId = 0;
    AudioRecognizeRequest.Builder builder = new AudioRecognizeRequest.Builder();
    AudioRecognizeRequest audioRecognizeRequest;
    AudioRecognizeConfiguration audioRecognizeConfiguration;
    EditText audioEdit;
    MultiLineEditText multiLineEditText;
    AlphaAnimation alphaAnimation1;
    private boolean isLongClickModule = false;
    private boolean isAudio = false;
    float startX = 0;
    float startY = 0;
    Timer timer = null;

    @OnTouch({R.id.el_content, R.id.rl_name, R.id.rl_zjhm, R.id.rl_phone, R.id.rl_email, R.id.rl_gzdw, R.id.rl_jsd, R.id.rl_bkgr_name, R.id.rl_bkgr_gzdwqc, R.id.rl_bkgr_dwszd, R.id.rl_bkgr_zw})
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //手指按下

                startX = event.getX();
                startY = event.getY();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isLongClickModule = true;
                    }
                }, 1000);
                break;
            case MotionEvent.ACTION_MOVE: //手指移动（从手指按下到抬起 move多次执行）
                double deltaX = Math.sqrt((event.getX() - startX) * (event.getX() - startX) + (event.getY() - startY) * (event.getY() - startY));
                if (deltaX > 20 && timer != null) { // 移动大于20像素
                    timer.cancel();
                    timer = null;
                }
                if (isLongClickModule&&!isAudio) {
                    isAudio=true;
                    //添加你长按之后的方法
                    audioEdit = null;
                    multiLineEditText = null;
                    if (v.getId() == R.id.rl_name) {
                        audioEdit = nameEdit;
                    } else if (v.getId() == R.id.rl_zjhm) {
                        audioEdit = zjhmEdit;
                    } else if (v.getId() == R.id.rl_phone) {
                        audioEdit = phoneEdit;
                    } else if (v.getId() == R.id.rl_email) {
                        audioEdit = emailEdit;
                    } else if (v.getId() == R.id.rl_gzdw) {
                        audioEdit = gzdwEdit;
                    } else if (v.getId() == R.id.rl_jsd) {
                        audioEdit = jsdEdit;
                    } else if (v.getId() == R.id.rl_bkgr_name) {
                        audioEdit = bNameEdit;
                    } else if (v.getId() == R.id.rl_bkgr_gzdwqc) {
                        audioEdit = bGzdwEdit;
                    } else if (v.getId() == R.id.rl_bkgr_dwszd) {
                        audioEdit = bDwdzEdit;
                    } else if (v.getId() == R.id.rl_bkgr_zw) {
                        audioEdit = bZwEdit;
                    } else if (v.getId() == R.id.el_content) {
                        multiLineEditText = contentTv;
                    }
                    startAudio(v);
                    timer = null;
                }

                break;
            case MotionEvent.ACTION_UP: //手指抬起
                isLongClickModule = false;
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                stopAudio(v);
                break;
            default:
                isLongClickModule = false;
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }

        }
        return true;
    }

    private void startAudio(View view) {
        if (alphaAnimation1 == null) {
            alphaAnimation1 = new AlphaAnimation(0.2f, 1.0f);
            alphaAnimation1.setDuration(2000);
            alphaAnimation1.setInterpolator(new LinearInterpolator());
            alphaAnimation1.setRepeatCount(Animation.INFINITE);
            alphaAnimation1.setRepeatMode(Animation.RESTART);
        }
        view.startAnimation(alphaAnimation1);
        //File file = new File(Environment.getExternalStorageDirectory()+"/tencent_aai____/audio", "1.pcm");
        // 初始化识别请求
        if (audioRecognizeRequest == null) {
            audioRecognizeRequest = builder
                    .pcmAudioDataSource(new AudioRecordDataSource()) // 设置数据源
                    //.templateName(templateName) // 设置模板
                    .template(new AudioRecognizeTemplate(EngineModelType.EngineModelType16K, 0, 0)) // 设置自定义模板
                    .build();

            // 自定义识别配置
            audioRecognizeConfiguration = new AudioRecognizeConfiguration.Builder()
                    .enableAudioStartTimeout(true) // 是否使能起点超时停止录音
                    .enableAudioEndTimeout(true) // 是否使能终点超时停止录音
                    .enableSilentDetect(true) // 是否使能静音检测，true表示不检查静音部分
                    .minAudioFlowSilenceTime(1000) // 语音流识别时的间隔时间
                    .maxAudioFlowSilenceTime(10000) // 语音终点超时时间
                    .maxAudioStartSilenceTime(10000) // 语音起点超时时间
                    .minVolumeCallbackTime(80) // 音量回调时间
                    .sensitive(2)
                    .build();
        }
        if (aaiClient == null) {
            try {
                aaiClient = new AAIClient(AccusationActivity.this, TagConstant.YAPPID, TagConstant.YPROJECTID, TagConstant.YSECRETID, credentialProvider);
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
        if (aaiClient.isAudioRecognizeIdle())
        //currentRequestId = audioRecognizeRequest.getRequestId();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("AUDIO", "开始录音");
                aaiClient.startAudioRecognize(audioRecognizeRequest, audioRecognizeResultlistener,
                        audioRecognizeStateListener, audioRecognizeTimeoutListener,
                        audioRecognizeConfiguration);

            }
        }).start();
    }

    private void stopAudio(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                isAudio=false;
                boolean taskExist = false;
                if (aaiClient != null) {
                    taskExist = aaiClient.stopAudioRecognize(currentRequestId);
                    aaiClient.cancelAudioRecognize(currentRequestId);
                }
                if (!taskExist) {
                    Log.i("AUDIO", "停止录音");
                }
            }
        }).start();
        view.clearAnimation();
    }

    @OnClick({R.id.btn_cancel, R.id.btn_submit, R.id.img_qtcl, R.id.img_zjcl, R.id.img_kgcl, R.id.rl_xb, R.id.rl_zjlx, R.id.rl_mz, R.id.rl_gj, R.id.rl_bkgr_xb, R.id.rl_bkgr_gj, R.id.rl_bkgr_zj, R.id.rl_bkgr_sf, R.id.rl_bkgr_rddb, R.id.rl_bkgr_zxwy, R.id.rl_bkgr_afd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_xb: {//性别
                showSimpleBottomSheetList(0, xbTv);
            }
            break;
            case R.id.rl_zjlx: {//证件类型
                if (zjBeans == null) {
                    getZjDataForcChild();
                } else {
                    showZjPickerView();
                }
            }
            break;
            case R.id.rl_mz: {//民族
                if (mzBeans == null) {
                    getMzDataForcChild();
                } else {
                    showMzPickerView();
                }
            }
            break;
            case R.id.rl_gj: {//国籍
                if (gjBeans == null) {
                    getGjDataForcChild(0, gjTv);
                } else {
                    showGjPickerView(0, gjTv);
                }
            }
            break;
            case R.id.rl_bkgr_xb: {//被控告人性别
                showSimpleBottomSheetList(1, bXbTv);
            }
            break;
            case R.id.rl_bkgr_gj: {//被控告人国籍
                if (gjBeans == null) {
                    getGjDataForcChild(1, bGjTv);
                } else {
                    showGjPickerView(1, bGjTv);
                }
            }
            break;
            case R.id.rl_bkgr_zj: {//被控告人职级
                if (bZjBeans == null) {
                    getBzjDataForcChild();
                } else {
                    showBzjPickerView();
                }
            }
            break;
            case R.id.rl_bkgr_sf: {//被控告人身份
                Intent intent = new Intent(AccusationActivity.this, ChooseSfActivity.class);
                intent.putExtra("index", 0);
                startActivityForResult(intent, 102);
            }
            break;
            case R.id.rl_bkgr_rddb: {//被控告人人大代表
                if (rddbBeans == null) {
                    getRddbDataForcChild();
                } else {
                    showRddbPickerView();
                }
            }
            break;
            case R.id.rl_bkgr_zxwy: {//被控告人政协委员
                showZxwyList();
            }
            break;
            case R.id.rl_bkgr_afd: {//被控告人案发地
                Intent intent = new Intent(AccusationActivity.this, ChooseAfdActivity.class);
                intent.putExtra("index", 0);
                startActivityForResult(intent, 101);
            }
            break;
            case R.id.img_kgcl: {//控告材料
                clIndex = 0;
                showChoosePhotoDialog();
            }
            break;
            case R.id.img_zjcl: {//证据材料
                clIndex = 1;
                showChoosePhotoDialog();
            }
            break;
            case R.id.img_qtcl: {//其他材料
                clIndex = 2;
                showChoosePhotoDialog();
            }
            break;
            case R.id.btn_submit: {//提交
                if (nameEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AccusationActivity.this, "请输入控告人姓名或单位名称").show();
                    break;
                }
                if (zjlxTv.getText().toString().trim().isEmpty()) {
                    XToast.info(AccusationActivity.this, "请选择证件类型").show();
                    break;
                }
                if (zjhmEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AccusationActivity.this, "请输入证件号码").show();
                    break;
                }
                if ("居民身份证".equals(zjlxTv.getText().toString().trim()) && !RegexUtils.isIDCard18(zjhmEdit.getText().toString().trim())) {
                    XToast.info(AccusationActivity.this, "证件号码错误").show();
                    break;
                }
                if (phoneEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AccusationActivity.this, "请输入电话号码").show();
                    break;
                }
                if (!RegexUtils.isMobileSimple(phoneEdit.getText().toString().trim())) {
                    XToast.info(AccusationActivity.this, "电话号码错误").show();
                    break;
                }
                if (!emailEdit.getText().toString().trim().isEmpty() && !RegexUtils.isEmail(emailEdit.getText().toString().trim())) {
                    XToast.info(AccusationActivity.this, "电子邮箱格式错误").show();
                    break;
                }
                if (jsdEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AccusationActivity.this, "请输入您的居所地").show();
                    break;
                }
                if (bNameEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AccusationActivity.this, "请输入被控告人姓名").show();
                    break;
                }
                if (bGzdwEdit.getText().toString().trim().isEmpty()) {
                    XToast.info(AccusationActivity.this, "请输入被控告人工作单位全称").show();
                    break;
                }
                if (bAfdTv.getText().toString().trim().isEmpty()) {
                    XToast.info(AccusationActivity.this, "请选择案发地").show();
                    break;
                }
                if (contentTv.isEmpty()) {
                    XToast.info(AccusationActivity.this, "请输入内容").show();
                    break;
                }
                miniLoadingDialog.show();
                if (selectPhoto.size() > 0) {
                    fileIds.clear();
                    upLoadFiles(0);
                } else {
                    submit();
                }
            }
            break;
            case R.id.btn_cancel: {//取消
                finish();
            }
            break;
        }
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

    /**
     * 职级
     */
    private void showBzjPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                bZjTv.setText(bZjBeans.get(options1).getName());
                bZjIndex = options1;
                return false;
            }
        })
                .setTitleText("职级")
                .setSelectOptions(bZjIndex)
                .build();
        pvOptions.setPicker(bZjStrBeans);
        pvOptions.show();
    }

    /**
     * 人大代表
     */
    private void showRddbPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                bRddbTv.setText(rddbBeans.get(options1).getName());
                rddbIndex = options1;
                return false;
            }
        })
                .setTitleText("人大代表")
                .setSelectOptions(rddbIndex)
                .build();
        pvOptions.setPicker(rddbStrBeans);
        pvOptions.show();
    }

    /**
     * 国籍
     */
    private void showGjPickerView(int pos, TextView tv) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                tv.setText(gjBeans.get(options1).getName());
                checkIndexGj[pos] = options1;
                return false;
            }
        })
                .setTitleText("国籍")
                .setSelectOptions(checkIndexGj[pos])
                .build();
        pvOptions.setPicker(gjStrBeans);
        pvOptions.show();
    }

    private void showSimpleBottomSheetList(int pos, TextView textView) {
        if (pos == 0) {
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
        } else {
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

    /**
     * 政协委员
     */
    private void showZxwyList() {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("政协委员")
                .addItem("县级")
                .addItem("地市")
                .addItem("省级")
                .addItem("全国")
                .setCheckedIndex(zxwyIndex)
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        zxwyIndex = position;
                        bZxwyTv.setText(tag);
                    }
                })
                .build().show();
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected Boolean status() {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == 124) {//身份
            sfBean = (SelectSectionParentEntity) data.getSerializableExtra("data");
            bSfTv.setText(sfBean.getName());
        }
        if (requestCode == 101 && resultCode == 123) {//案发地
            afdBean = (SelectSectionParentEntity) data.getSerializableExtra("data");
            bAfdTv.setText(afdBean.getName());
        }
        if (requestCode == PictureConfig.CHOOSE_REQUEST || requestCode == PictureConfig.REQUEST_CAMERA) {
            //相册返回
            // 图片选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            if (selectList != null && selectList.size() > 0) {
                switch (clIndex) {
                    case 0: {//控告
                        Glide.with(AccusationActivity.this).load(selectList.get(0).getPath()).into(kgclImg);
                    }
                    break;
                    case 1: {//证据
                        Glide.with(AccusationActivity.this).load(selectList.get(0).getPath()).into(zjclImg);
                    }
                    break;
                    case 2: {//其他
                        Glide.with(AccusationActivity.this).load(selectList.get(0).getPath()).into(qtlImg);
                    }
                    break;
                }
                selectPhoto.put(clIndex, selectList.get(0));
            }
        }
    }

    private void showChoosePhotoDialog() {
        new BottomSheet.BottomListSheetBuilder(this)
                .addItem("拍照")
                .addItem("从相册中选择")
                .setTitle("选择文件")
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        if (position == 0) {
                            PictureSelector.create(AccusationActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .maxSelectNum(1)
                                    .loadImageEngine(GlideEngine.createGlideEngine())
                                    .forResult(PictureConfig.REQUEST_CAMERA);
                        } else {
                            PictureSelector.create(AccusationActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .selectionMode(PictureConfig.SINGLE)
                                    .loadImageEngine(GlideEngine.createGlideEngine())
                                    .isCamera(false)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    }
                })
                .build().show();
    }

    private int retry = 0;

    private void upLoadFiles(int index) {
        if (selectPhoto.get(index) == null && index < 2) {
            upLoadFiles(index + 1);
            return;
        }
        if (index > 2) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("fileName", selectPhoto.get(index).getFileName());
        String fromBase64 = Base64Converter.encodeBase64File(selectPhoto.get(index).getPath());
        map.put("data", fromBase64);
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.fileUpload)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", a)
                .asObject(UpDataFileBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult() == 200) {
                        retry = 0;
                        fileIds.put(index, s);
                        //下一个
                        if (fileIds.size() < selectPhoto.size()) {
                            int sss = index + 1;
                            upLoadFiles(sss);
                        } else {
                            submit();
                        }
                    } else {
                        //重试
                        retry++;
                        if (retry == 3) {
                            miniLoadingDialog.dismiss();
                            XToast.error(AccusationActivity.this, "第" + (index + 1) + "张图片上传失败,请检查").show();
                        } else {
                            upLoadFiles(index);
                        }
                    }
                }, throwable -> {
                    retry++;
                    if (retry == 3) {
                        miniLoadingDialog.dismiss();
                        XToast.error(AccusationActivity.this, "第" + (index + 1) + "张图片上传失败,请检查").show();
                    } else {
                        upLoadFiles(index);
                    }
                });
    }

    private void submit() {
        Map<String, String> map = new HashMap<>();
        map.put("source", TagConstant.SOURCE);
        map.put("plaintiffName", nameEdit.getText().toString().trim());
        map.put("plaintiffSex", xbTv.getText().toString().trim());
        map.put("plaintiffCertificateType", zjIndex == -1 ? "" : zjBeans.get(zjIndex).getCode());
        map.put("plaintiffCertificateNumber", zjhmEdit.getText().toString().trim());
        map.put("plaintiffNation", mzIndex == -1 ? "" : mzBeans.get(mzIndex).getCode());
        map.put("plaintiffNationality", checkIndexGj[0] == -1 ? "" : gjBeans.get(checkIndexGj[0]).getCode());
        map.put("plaintiffPhone", phoneEdit.getText().toString().trim());
        map.put("plaintiffEmail", emailEdit.getText().toString().trim());
        map.put("plaintiffUnit", gzdwEdit.getText().toString().trim());
        map.put("plaintiffResidence", jsdEdit.getText().toString().trim());

        map.put("defendantName", bNameEdit.getText().toString().trim());
        map.put("defendantSex", bXbTv.getText().toString().trim());
        map.put("defendantNationality", checkIndexGj[1] == -1 ? "" : gjBeans.get(checkIndexGj[1]).getCode());
        map.put("defendantUintFullName", bGzdwEdit.getText().toString().trim());
        map.put("defendantUnitLocation", bDwdzEdit.getText().toString().trim());
        map.put("defendantDuty", bZwEdit.getText().toString().trim());
        map.put("defendantRank", bZjIndex == -1 ? "" : bZjBeans.get(bZjIndex).getCode());
        map.put("defendantIdentity", sfBean == null ? "" : sfBean.getCode());
        map.put("defendantNPC", rddbIndex == -1 ? "" : rddbBeans.get(rddbIndex).getCode());
        map.put("defendantCPPCC", bZxwyTv.getText().toString().trim());
        map.put("organizationCode", TagConstant.CODE);
        map.put("venueCode", afdBean == null ? "" : afdBean.getCode());
        map.put("content", contentTv.getContentText());
        map.put("attachmentFile1", fileIds.get(0) == null ? "" : fileIds.get(0).getFileId());
        map.put("attachmentFile2", fileIds.get(1) == null ? "" : fileIds.get(1).getFileId());
        map.put("attachmentFile3", fileIds.get(2) == null ? "" : fileIds.get(2).getFileId());
        map.put("userId", UserUtils.getId());
        map.put("userKeyId", UserUtils.getKeyId());
        map.put("username", UserUtils.getUserName());
        map.put("userRealName", UserUtils.getRealName());
        String ss = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY, ss);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.accuse)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        XToast.success(AccusationActivity.this, s.getMessage()).show();
                        finish();
                    } else {
                        XToast.error(AccusationActivity.this, s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AccusationActivity.this, throwable.getMessage()).show();
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (aaiClient != null) {
            aaiClient.release();
        }
    }
}

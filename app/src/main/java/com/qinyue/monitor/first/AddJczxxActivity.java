package com.qinyue.monitor.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.qinyue.monitor.view.ImageSelectGridAdapter;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.net.JSONUtils;

import java.io.File;
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
public class AddJczxxActivity extends BaseActivity implements ImageSelectGridAdapter.OnAddPicClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.content)
    MultiLineEditText contentView;
    @BindView(R.id.phone)
    EditText phoneEdit;
    @BindView(R.id.et_name)
    EditText nameEdit;
    @BindView(R.id.sex)
    TextView sexTv;
    private ImageSelectGridAdapter mAdapter;
    private int checkIndex = -1;
    private List<LocalMedia> mSelectList = new ArrayList<>();
    private SparseArray<UpDataFileBean2> fileIds = new SparseArray<>();
    @Override
    public String initTitleText() {
        return "检察长信箱";
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        GridLayoutManager manager = new GridLayoutManager(this, 4, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter = new ImageSelectGridAdapter(this, this));
        mAdapter.setSelectList(mSelectList);
        mAdapter.setSelectMax(5);
        mAdapter.setOnItemClickListener(new ImageSelectGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureSelector.create(AddJczxxActivity.this).themeStyle(R.style.picture_default_style)
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .openExternalPreview(position, mSelectList);
            }
        });
    }

    @Override
    protected boolean network() {
        return false;
    }
    @OnClick({R.id.choose_sex,R.id.but_submit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.choose_sex:{//选择性别
                showSimpleBottomSheetList();
            }break;
            case R.id.but_submit:{//提交
                if (contentView.getContentText().isEmpty()){
                    XToast.info(AddJczxxActivity.this,"请输入内容").show();
                    break;
                }
                miniLoadingDialog.show();
                if (mSelectList.size()>0){
                    fileIds.clear();
                    upLoadFiles2(mSelectList.get(0),0);
                }else {
                    submit();
                }

            }break;
        }
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activity_addjczxx;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }
    private void showSimpleBottomSheetList() {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("请选择性别")
                .addItem("男")
                .addItem("女")
                .setCheckedIndex(checkIndex)
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndex = position;
                        sexTv.setText(tag);
                    }
                })
                .build().show();
    }
    @Override
    public void onAddPicClick() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .maxSelectNum(5)
                .minSelectNum(1)
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .isCamera(true)
                .enableCrop(false)
                .compress(true)
                .previewEggs(true)
                .selectionMedia(mSelectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    mSelectList = PictureSelector.obtainMultipleResult(data);
                    mAdapter.setSelectList(mSelectList);
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }
    private int retry = 0;
    private void upLoadFiles(LocalMedia localMedia,int index){
        Map<String,String> map = new HashMap<>();
        map.put("fileName",localMedia.getFileName());
        String fromBase64 = Base64Converter.encodeBase64File(localMedia.getPath());
        map.put("data",fromBase64 );
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.fileUpload)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", a)
                .asObject(UpDataFileBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        retry = 0;
//                        fileIds.put(index,s);
                        //下一个
                        if (fileIds.size()<mSelectList.size()){
                            int sss = index+1;
                            upLoadFiles(mSelectList.get(sss),sss);
                        }else {
                            submit();
                        }
                    }else {
                        //重试
                        retry ++;
                        if (retry==3){
                            miniLoadingDialog.dismiss();
                            XToast.error(AddJczxxActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                        }else {
                            upLoadFiles(localMedia,index);
                        }
                    }
                }, throwable -> {
                    retry ++;
                    if (retry==3){
                        miniLoadingDialog.dismiss();
                        XToast.error(AddJczxxActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                    }else {
                        upLoadFiles(localMedia,index);
                    }
                });
    }
    private void upLoadFiles2(LocalMedia localMedia,int index){
        Map<String,String> map = new HashMap<>();
        map.put("fileName",localMedia.getFileName());
        String[] split = localMedia.getPath().split("\\.");
        map.put("category",split[1]);
        String fromBase64 = Base64Converter.encodeBase64File(localMedia.getPath());
        map.put("file",fromBase64 );
        map.put("filetype","1" );
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL2 + NetConstant.savePicture)
                .add("data", a)
                .asObject(UpDataFileBean2.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.isResult()){
                        retry = 0;
                        fileIds.put(index,s);
                        //下一个
                        if (fileIds.size()<mSelectList.size()){
                            int sss = index+1;
                            upLoadFiles2(mSelectList.get(sss),sss);
                        }else {
                            submit();
                        }
                    }else {
                        //重试
                        retry ++;
                        if (retry==3){
                            miniLoadingDialog.dismiss();
                            XToast.error(AddJczxxActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                        }else {
                            upLoadFiles2(localMedia,index);
                        }
                    }
                }, throwable -> {
                    retry ++;
                    if (retry==3){
                        miniLoadingDialog.dismiss();
                        XToast.error(AddJczxxActivity.this,"第"+(index+1)+"张图片上传失败,请检查").show();
                    }else {
                        upLoadFiles2(localMedia,index);
                    }
                });
    }
    private void submit(){
        Map<String,String> map = new HashMap<>();
        map.put("username", UserUtils.getUserName());
        map.put("orgId", "cbe523dfd3e24b83b95616492c56940d");
        map.put("content", contentView.getContentText());
        if (!nameEdit.getText().toString().trim().isEmpty()){
            map.put("writer", nameEdit.getText().toString().trim());
        }
        if (!sexTv.getText().toString().trim().isEmpty()){
            map.put("sex", sexTv.getText().toString().trim());
        }
        if (!phoneEdit.getText().toString().trim().isEmpty()){
            map.put("phone", phoneEdit.getText().toString().trim());
        }
        if (fileIds.size()>0){
            String zz = "";
            for (int i = 0; i < fileIds.size(); i++) {
                zz =zz + fileIds.valueAt(i).getFileId()+",";
            }
            if (zz.endsWith(",")){
                zz= zz.substring(0,zz.length()-1);
            }
            map.put("fileIds", zz);
        }
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL2 + NetConstant.saveLetter)
                .add("data", a)
                .asObject(UpDataFileBean2.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.isResult()){
                        XToast.success(AddJczxxActivity.this,s.getMessage()).show();
                        finish();
                    }else {
                        XToast.error(AddJczxxActivity.this,s.getMessage()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(AddJczxxActivity.this,throwable.getMessage()).show();
                });
    }
}

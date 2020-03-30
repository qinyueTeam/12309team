package com.qinyue.monitor.first;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/30
 * 描述:
 **/
public class UpDataFileBean implements Serializable {

    /**
     * result : 200
     * message : 接口调用成功
     * fileId : 20200330_8aab4d15c5a84f00a1419da10cf857fe@20200330_b482f3df984e46fcbfab08cfef818188
     * fileName : yuba1582618588403.jpg
     * fileType : 1
     * fileExt : jpg
     */

    private int result;
    private String message;
    private String fileId;
    private String fileName;
    private int fileType;//1：图片，2：视频，3：文档，4：压缩包
    //文件扩展名
    private String fileExt;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }
}

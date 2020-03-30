package com.qinyue.monitor.first;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/30
 * 描述:
 **/
public class UpDataFileBean2 implements Serializable {

    /**
     * fileId : 247
     * result : true
     * message : 上传成功
     */

    private int fileId;
    private boolean result;
    private String message;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

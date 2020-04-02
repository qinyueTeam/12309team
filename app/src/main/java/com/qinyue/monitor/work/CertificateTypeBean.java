package com.qinyue.monitor.work;

import java.io.Serializable;
import java.util.List;

public class CertificateTypeBean implements Serializable {


    /**
     * result : 200
     * msg : 接口调用成功
     * data : [{"code":"9910180300001","name":"居民身份证"},{"code":"9910180300002","name":"军官证"},{"code":"9910180300003","name":"士兵证"},{"code":"9910180300005","name":"警官证"},{"code":"9910180300006","name":"工作证"},{"code":"9910180300007","name":"护照"},{"code":"9910180300008","name":"户口簿"},{"code":"9910180300009","name":"其他证件"},{"code":"9910180300010","name":"律师执业证"},{"code":"9910180300011","name":"无证件"}]
     */

    private String result;
    private String msg;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 9910180300001
         * name : 居民身份证
         */

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

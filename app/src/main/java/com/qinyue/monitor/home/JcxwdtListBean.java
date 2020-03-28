package com.qinyue.monitor.home;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/29
 * 描述:
 **/
public class JcxwdtListBean implements Serializable {
    private String title;
    private String summary;
    private String content;
    private String image;
    private String releaseTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}

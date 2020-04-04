package com.qinyue.monitor.my;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/4
 * 描述:
 **/
public class DeRankBean implements Serializable {

    /**
     * class : com.bjrxkj.petition.PetitionLetterDict
     * id : 11062
     * dm : 9914180800001
     * fdm : null
     * lbbm : 9914
     * mc : 无职级
     * sfsc : N
     * sm : null
     */

    private int id;
    private String dm;
    private String fdm;
    private String lbbm;
    private String mc;
    private String sfsc;
    private String sm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getFdm() {
        return fdm;
    }

    public void setFdm(String fdm) {
        this.fdm = fdm;
    }

    public String getLbbm() {
        return lbbm;
    }

    public void setLbbm(String lbbm) {
        this.lbbm = lbbm;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }
}

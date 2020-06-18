package com.zydcc.zrdc.model.dic;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * =======================================
 * 地类图斑
 * Create by ningsikai 2020/6/18:2:04 PM
 * ========================================
 */
@Entity(createInDb = false)
public class DLTB {
    @Id
    private String id;
    private String bz;
    private int sfxs;
    private int xh;
    private String xsws;
    private int ystj;
    private String zddm;
    private String zdlx;
    private String zdmc;
    @Generated(hash = 2050778958)
    public DLTB(String id, String bz, int sfxs, int xh, String xsws, int ystj,
            String zddm, String zdlx, String zdmc) {
        this.id = id;
        this.bz = bz;
        this.sfxs = sfxs;
        this.xh = xh;
        this.xsws = xsws;
        this.ystj = ystj;
        this.zddm = zddm;
        this.zdlx = zdlx;
        this.zdmc = zdmc;
    }
    @Generated(hash = 44115711)
    public DLTB() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public int getSfxs() {
        return this.sfxs;
    }
    public void setSfxs(int sfxs) {
        this.sfxs = sfxs;
    }
    public int getXh() {
        return this.xh;
    }
    public void setXh(int xh) {
        this.xh = xh;
    }
    public String getXsws() {
        return this.xsws;
    }
    public void setXsws(String xsws) {
        this.xsws = xsws;
    }
    public int getYstj() {
        return this.ystj;
    }
    public void setYstj(int ystj) {
        this.ystj = ystj;
    }
    public String getZddm() {
        return this.zddm;
    }
    public void setZddm(String zddm) {
        this.zddm = zddm;
    }
    public String getZdlx() {
        return this.zdlx;
    }
    public void setZdlx(String zdlx) {
        this.zdlx = zdlx;
    }
    public String getZdmc() {
        return this.zdmc;
    }
    public void setZdmc(String zdmc) {
        this.zdmc = zdmc;
    }
}

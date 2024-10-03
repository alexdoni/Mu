package com.oversea.ab_firstarea.net.model;

/* loaded from: classes.dex */
public class RewardItemBean {
    private String areaCode;
    private String countryCode;
    private double money;
    private String netWorkName;
    private int precision;

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String str) {
        this.areaCode = str;
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double d) {
        this.money = d;
    }

    public String getNetWorkName() {
        return this.netWorkName;
    }

    public void setNetWorkName(String str) {
        this.netWorkName = str;
    }

    public int getPrecision() {
        return this.precision;
    }

    public void setPrecision(int i) {
        this.precision = i;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String toString() {
        return "RewardItemBean{money=" + this.money + ", netWorkName='" + this.netWorkName + "', precision=" + this.precision + ", countryCode='" + this.countryCode + "', areaCode='" + this.areaCode + "'}";
    }
}

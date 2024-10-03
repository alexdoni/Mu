package com.oversea.ab_firstarea.net.domainN;

/* loaded from: classes.dex */
public class DomainPingModle {
    private String url;
    private int responseTime = 10000;
    private int order = 100;
    private boolean isUse = false;

    public DomainPingModle(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(int i) {
        this.responseTime = i;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int i) {
        this.order = i;
    }

    public boolean isUse() {
        return this.isUse;
    }

    public void setUse(boolean z) {
        this.isUse = z;
    }
}

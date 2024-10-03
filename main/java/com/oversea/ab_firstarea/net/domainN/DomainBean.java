package com.oversea.ab_firstarea.net.domainN;

import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.xsdk.ab_firstbase.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class DomainBean {

    @SerializedName("api")
    private String api_api;

    @SerializedName("api-logs")
    private String api_logs;

    @SerializedName("api-pay")
    private String api_pay;

    @SerializedName("api-sdk")
    private String api_sdk;

    public String getApi_sdk() {
        return AreaBaseService.HTTPS + this.api_sdk;
    }

    public void setApi_sdk(String str) {
        this.api_sdk = str;
    }

    public String getApi_pay() {
        return AreaBaseService.HTTPS + this.api_pay;
    }

    public void setApi_pay(String str) {
        this.api_pay = str;
    }

    public String getApi_logs() {
        return AreaBaseService.HTTPS + this.api_logs;
    }

    public void setApi_logs(String str) {
        this.api_logs = str;
    }

    public String getApi_api() {
        return this.api_api;
    }

    public void setApi_api(String str) {
        this.api_api = str;
    }

    public String toString() {
        return "DomainBean{api_sdk='" + getApi_sdk() + "', api_pay='" + getApi_pay() + "', api_logs='" + getApi_logs() + "', api='" + getApi_api() + "'}";
    }
}

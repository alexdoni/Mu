package com.xsdk.ab_firstbase.net.http;

import android.app.Activity;
import java.util.Map;

/* loaded from: classes3.dex */
public class ParamsBean {
    private String diaMsg;
    private Map<String, String> headers;
    private boolean isShowprogressDia = false;
    private Activity mActivity;
    private String mParameters;
    private Map<String, Object> params;
    private Object tag;
    private String url;

    public String getDiaMsg() {
        return this.diaMsg;
    }

    public void setDiaMsg(String str) {
        this.diaMsg = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Object getTag() {
        return this.tag;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> map) {
        this.params = map;
    }

    public String getmParameters() {
        return this.mParameters;
    }

    public void setmParameters(String str) {
        this.mParameters = str;
    }

    public Activity getmActivity() {
        return this.mActivity;
    }

    public void setmActivity(Activity activity) {
        this.mActivity = activity;
    }

    public boolean isShowprogressDia() {
        return this.isShowprogressDia;
    }

    public void setShowprogressDia(boolean z) {
        this.isShowprogressDia = z;
    }
}

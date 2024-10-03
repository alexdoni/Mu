package com.xsdk.ab_firstbase.net.http;

import android.app.Activity;
import com.xsdk.ab_firstbase.net.okhttp3.OkHttpManger;
import java.util.IdentityHashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class GetBuilder extends HttpRequestBuilder {
    private void initParams() {
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public /* bridge */ /* synthetic */ HttpRequestBuilder headers(Map map) {
        return headers((Map<String, String>) map);
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public /* bridge */ /* synthetic */ HttpRequestBuilder params(Map map) {
        return params((Map<String, Object>) map);
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public GetBuilder url(String str) {
        this.url = str;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public GetBuilder tag(Object obj) {
        this.tag = obj;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public GetBuilder params(Map<String, Object> map) {
        this.params = map;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public HttpRequestBuilder addDo(String str) {
        if (this.params == null) {
            initParams();
        }
        this.params.put("do", str);
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public GetBuilder addParams(String str, Object obj) {
        if (this.params == null) {
            initParams();
        }
        this.params.put(str, obj);
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public HttpRequestBuilder addMapParams(Map<String, Object> map) {
        if (this.params == null) {
            initParams();
        }
        this.params.putAll(map);
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public GetBuilder headers(Map<String, String> map) {
        this.headers = map;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public GetBuilder addHeader(String str, String str2) {
        if (this.headers == null) {
            this.headers = new IdentityHashMap();
        }
        this.headers.put(str, str2);
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public RequestCall build() {
        return new GetRequest(this.url, this.tag, this.params, this.params != null ? appendParams(this.params) : "", this.headers).setMethod(OkHttpManger.GetMethod).build();
    }

    private String appendParams(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        if (map != null && !map.isEmpty()) {
            for (String str : map.keySet()) {
                sb.append(str);
                sb.append("=");
                sb.append(map.get(str));
                sb.append("&");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public HttpRequestBuilder isShowprogressDia(boolean z, Activity activity) {
        this.mActivity = activity;
        this.isShowprogressDia = z;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public HttpRequestBuilder isShowprogressDia(boolean z, Activity activity, String str) {
        this.mActivity = activity;
        this.isShowprogressDia = z;
        this.diaMsg = str;
        return this;
    }
}

package com.xsdk.ab_firstbase.net.http;

import android.app.Activity;
import android.text.TextUtils;
import com.xsdk.ab_firstbase.net.okhttp3.OkHttpManger;
import java.io.File;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PostFormBuilder extends HttpRequestBuilder {
    private String mParameters;
    private ParamsBean paramsBean;
    private List<FileInput> files = new ArrayList();
    private String msg = "loading...";

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public /* bridge */ /* synthetic */ HttpRequestBuilder headers(Map map) {
        return headers((Map<String, String>) map);
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public /* bridge */ /* synthetic */ HttpRequestBuilder params(Map map) {
        return params((Map<String, Object>) map);
    }

    public PostFormBuilder(String str) {
        this.url = str;
    }

    public PostFormBuilder() {
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public PostFormBuilder url(String str) {
        this.url = str;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public PostFormBuilder tag(Object obj) {
        this.tag = obj;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public PostFormBuilder params(Map<String, Object> map) {
        this.params = map;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public HttpRequestBuilder addDo(String str) {
        if (this.params == null) {
            initParams();
        }
        if (!TextUtils.isEmpty(str)) {
            this.params.put("do", str);
        }
        return this;
    }

    private void initParams() {
        this.params = new IdentityHashMap();
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public PostFormBuilder addParams(String str, Object obj) {
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
    public PostFormBuilder headers(Map<String, String> map) {
        this.headers = map;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public PostFormBuilder addHeader(String str, String str2) {
        if (this.headers == null) {
            this.headers = new IdentityHashMap();
        }
        this.headers.put(str, str2);
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public RequestCall build() {
        if (this.params != null) {
            this.mParameters = appendParams(this.url, this.params);
        }
        ParamsBean paramsBean = new ParamsBean();
        this.paramsBean = paramsBean;
        paramsBean.setUrl(this.url);
        this.paramsBean.setmActivity(this.mActivity);
        this.paramsBean.setTag(this.tag);
        this.paramsBean.setParams(this.params);
        this.paramsBean.setHeaders(this.headers);
        this.paramsBean.setmParameters(this.mParameters);
        this.paramsBean.setShowprogressDia(this.isShowprogressDia);
        this.paramsBean.setDiaMsg(this.msg);
        return new PostFormRequest(this.paramsBean, this.files).setMethod(OkHttpManger.PostMethod).build();
    }

    private String appendParams(String str, Map<String, Object> map) {
        return new JSONObject(map).toString();
    }

    /* loaded from: classes3.dex */
    public static class FileInput {
        public File file;
        public String filename;
        public String key;

        public FileInput(String str, String str2, File file) {
            this.key = str;
            this.filename = str2;
            this.file = file;
        }
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public HttpRequestBuilder isShowprogressDia(boolean z, Activity activity) {
        this.isShowprogressDia = z;
        this.mActivity = activity;
        return this;
    }

    @Override // com.xsdk.ab_firstbase.net.http.HttpRequestBuilder
    public HttpRequestBuilder isShowprogressDia(boolean z, Activity activity, String str) {
        this.isShowprogressDia = z;
        this.mActivity = activity;
        this.msg = str;
        return this;
    }
}

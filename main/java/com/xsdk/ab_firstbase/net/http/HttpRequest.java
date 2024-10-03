package com.xsdk.ab_firstbase.net.http;

import com.xsdk.ab_firstbase.net.http.utils.Exceptions;
import java.io.File;
import java.util.Map;

/* loaded from: classes3.dex */
public class HttpRequest {
    protected File file;
    protected Map<String, String> headers;
    private int httpMethod;
    protected boolean isShowProDia;
    protected String mParameters;
    protected Map<String, Object> params;
    protected ParamsBean paramsBean;
    protected Object tag;
    protected String url;

    public HttpRequest setMethod(int i) {
        this.httpMethod = i;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpRequest(ParamsBean paramsBean) {
        this.isShowProDia = false;
        this.paramsBean = paramsBean;
        this.url = paramsBean.getUrl();
        this.tag = paramsBean.getTag();
        this.params = paramsBean.getParams();
        this.headers = paramsBean.getHeaders();
        this.mParameters = paramsBean.getmParameters();
        if (paramsBean.getUrl() == null) {
            Exceptions.illegalArgument("url can not be null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpRequest(String str, Object obj, Map<String, Object> map, String str2, Map<String, String> map2) {
        this.isShowProDia = false;
        this.url = str;
        this.tag = obj;
        this.params = map;
        this.headers = map2;
        this.mParameters = str2;
        if (str == null) {
            Exceptions.illegalArgument("url can not be null.");
        }
    }

    public HttpRequest(String str, Object obj, Map<String, Object> map, String str2, Map<String, String> map2, boolean z) {
        this.url = str;
        this.tag = obj;
        this.params = map;
        this.headers = map2;
        this.mParameters = str2;
        this.isShowProDia = z;
        if (str == null) {
            Exceptions.illegalArgument("url can not be null.");
        }
    }

    protected HttpRequest(String str, Object obj, Map<String, Object> map, Map<String, String> map2) {
        this.isShowProDia = false;
        this.url = str;
        this.tag = obj;
        this.params = map;
        this.headers = map2;
        if (str == null) {
            Exceptions.illegalArgument("url can not be null.");
        }
    }

    public HttpRequest setFile(File file) {
        try {
            this.file = file;
        } catch (Throwable unused) {
        }
        return this;
    }

    public RequestCall build() {
        return new RequestCall(this, this.httpMethod);
    }
}

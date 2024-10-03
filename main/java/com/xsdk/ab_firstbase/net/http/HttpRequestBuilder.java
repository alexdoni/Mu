package com.xsdk.ab_firstbase.net.http;

import android.app.Activity;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class HttpRequestBuilder {
    protected Map<String, String> headers;
    protected Activity mActivity;
    protected String mParameters;
    protected Map<String, Object> params;
    protected Object tag;
    protected String url;
    protected boolean isShowprogressDia = false;
    protected String diaMsg = "加载中...";

    public abstract HttpRequestBuilder addDo(String str);

    public abstract HttpRequestBuilder addHeader(String str, String str2);

    public abstract HttpRequestBuilder addMapParams(Map<String, Object> map);

    public abstract HttpRequestBuilder addParams(String str, Object obj);

    public abstract RequestCall build();

    public abstract HttpRequestBuilder headers(Map<String, String> map);

    public abstract HttpRequestBuilder isShowprogressDia(boolean z, Activity activity);

    public abstract HttpRequestBuilder isShowprogressDia(boolean z, Activity activity, String str);

    public abstract HttpRequestBuilder params(Map<String, Object> map);

    public abstract HttpRequestBuilder tag(Object obj);

    public abstract HttpRequestBuilder url(String str);
}

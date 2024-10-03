package com.xsdk.ab_firstbase.net.http;

/* loaded from: classes3.dex */
public class HttpUtils {
    public static HttpUtils mInstance;

    public static HttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils();
                }
            }
        }
        return mInstance;
    }

    public GetBuilder get() {
        return new GetBuilder();
    }

    public PostFormBuilder post() {
        return new PostFormBuilder();
    }
}

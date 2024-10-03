package com.xsdk.ab_firstbase.net.http;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public abstract class CommomCallBack {
    public void onErrorReportCode(int i, String str) {
    }

    public abstract void onFailure(int i, String str);

    public abstract void onSucceed(String str, String str2, HttpRequest httpRequest);

    public void onAfter(String str, String str2, HttpRequest httpRequest) {
        if (str != null && !TextUtils.isEmpty(str)) {
            onSucceed(str, str2, httpRequest);
        } else {
            onFailure(10010, "数据异常:json is null");
        }
    }
}

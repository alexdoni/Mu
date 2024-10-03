package com.p008ld.sdk.internal;

/* loaded from: classes2.dex */
public interface LDPayCallback {
    void onCancel();

    void onError(LDException lDException);

    void onSuccess(String str, String str2);
}

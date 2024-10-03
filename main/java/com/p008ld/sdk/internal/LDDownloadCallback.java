package com.p008ld.sdk.internal;

/* compiled from: LDDownloadCallback.kt */
/* loaded from: classes2.dex */
public interface LDDownloadCallback {
    void onError(Throwable th);

    void onProgress(long j, long j2);

    void onSuccess(boolean z, String str);
}

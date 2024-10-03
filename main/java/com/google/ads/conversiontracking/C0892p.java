package com.google.ads.conversiontracking;

import android.os.Looper;

/* renamed from: com.google.ads.conversiontracking.p */
/* loaded from: classes.dex */
public final class C0892p {
    /* renamed from: a */
    public static void m244a(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(str);
        }
    }
}

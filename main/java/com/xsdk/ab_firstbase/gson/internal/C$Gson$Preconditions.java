package com.xsdk.ab_firstbase.gson.internal;

/* renamed from: com.xsdk.ab_firstbase.gson.internal.$Gson$Preconditions, reason: invalid class name */
/* loaded from: classes3.dex */
public final class C$Gson$Preconditions {
    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }
}

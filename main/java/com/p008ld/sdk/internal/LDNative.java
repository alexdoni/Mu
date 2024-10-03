package com.p008ld.sdk.internal;

/* compiled from: LDNative.kt */
/* loaded from: classes2.dex */
public final class LDNative {
    public static final LDNative INSTANCE = new LDNative();

    public final native String encrypt(String str);

    public final native String encrypt5(String str, String str2, String str3, String str4, String str5);

    public final native String genSalt();

    public final native boolean init(String str);

    private LDNative() {
    }

    static {
        System.loadLibrary("ld-sdk");
    }
}

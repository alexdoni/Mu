package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.f */
/* loaded from: classes3.dex */
public final class C2618f {

    /* renamed from: b */
    private static final char[] f1442b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static final byte[] f1441a = new byte[0];

    /* renamed from: a */
    public static String m1007a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = f1442b;
            cArr[i2 + 1] = cArr2[b & Ascii.f555SI];
            cArr[i2 + 0] = cArr2[((byte) (b >>> 4)) & Ascii.f555SI];
        }
        return new String(cArr);
    }
}

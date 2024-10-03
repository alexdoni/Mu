package com.google.ads.conversiontracking;

import com.google.common.base.Ascii;
import kotlin.io.encoding.Base64;
import okio.Utf8;

/* renamed from: com.google.ads.conversiontracking.s */
/* loaded from: classes.dex */
public final class C0894s {

    /* renamed from: a */
    private static final char[] f502a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    /* renamed from: b */
    private static final char[] f503b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();

    /* renamed from: c */
    private static final byte[] f504c = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, 59, 60, Base64.padSymbol, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, Ascii.f548FF, Ascii.f546CR, Ascii.f556SO, Ascii.f555SI, 16, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f547EM, -9, -9, -9, -9, -9, -9, Ascii.SUB, Ascii.ESC, Ascii.f549FS, Ascii.f550GS, Ascii.f554RS, Ascii.f558US, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};

    /* renamed from: d */
    private static final byte[] f505d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, Base64.padSymbol, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, Ascii.f548FF, Ascii.f546CR, Ascii.f556SO, Ascii.f555SI, 16, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.f547EM, -9, -9, -9, -9, Utf8.REPLACEMENT_BYTE, -9, Ascii.SUB, Ascii.ESC, Ascii.f549FS, Ascii.f550GS, Ascii.f554RS, Ascii.f558US, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};

    /* renamed from: a */
    private static char[] m253a(byte[] bArr, int i, int i2, char[] cArr, int i3, char[] cArr2) {
        int i4 = (i2 > 0 ? (bArr[i] << Ascii.CAN) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << Ascii.CAN) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << Ascii.CAN) >>> 24 : 0);
        if (i2 == 1) {
            cArr[i3] = cArr2[i4 >>> 18];
            cArr[i3 + 1] = cArr2[(i4 >>> 12) & 63];
            cArr[i3 + 2] = '=';
            cArr[i3 + 3] = '=';
            return cArr;
        }
        if (i2 == 2) {
            cArr[i3] = cArr2[i4 >>> 18];
            cArr[i3 + 1] = cArr2[(i4 >>> 12) & 63];
            cArr[i3 + 2] = cArr2[(i4 >>> 6) & 63];
            cArr[i3 + 3] = '=';
            return cArr;
        }
        if (i2 != 3) {
            return cArr;
        }
        cArr[i3] = cArr2[i4 >>> 18];
        cArr[i3 + 1] = cArr2[(i4 >>> 12) & 63];
        cArr[i3 + 2] = cArr2[(i4 >>> 6) & 63];
        cArr[i3 + 3] = cArr2[i4 & 63];
        return cArr;
    }

    @Deprecated
    /* renamed from: a */
    public static String m251a(byte[] bArr, boolean z) {
        return m250a(bArr, 0, bArr.length, f503b, z);
    }

    /* renamed from: a */
    public static String m250a(byte[] bArr, int i, int i2, char[] cArr, boolean z) {
        char[] m252a = m252a(bArr, i, i2, cArr, Integer.MAX_VALUE);
        int length = m252a.length;
        while (!z && length > 0 && m252a[length - 1] == '=') {
            length--;
        }
        return new String(m252a, 0, length);
    }

    /* renamed from: a */
    static char[] m252a(byte[] bArr, int i, int i2, char[] cArr, int i3) {
        int i4 = ((i2 + 2) / 3) * 4;
        char[] cArr2 = new char[i4 + (i4 / i3)];
        int i5 = i2 - 2;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < i5) {
            int i9 = ((bArr[i6 + i] << Ascii.CAN) >>> 8) | ((bArr[(i6 + 1) + i] << Ascii.CAN) >>> 16) | ((bArr[(i6 + 2) + i] << Ascii.CAN) >>> 24);
            cArr2[i7] = cArr[i9 >>> 18];
            int i10 = i7 + 1;
            cArr2[i10] = cArr[(i9 >>> 12) & 63];
            cArr2[i7 + 2] = cArr[(i9 >>> 6) & 63];
            cArr2[i7 + 3] = cArr[i9 & 63];
            i8 += 4;
            if (i8 == i3) {
                cArr2[i7 + 4] = '\n';
                i8 = 0;
                i7 = i10;
            }
            i6 += 3;
            i7 += 4;
        }
        if (i6 < i2) {
            m253a(bArr, i6 + i, i2 - i6, cArr2, i7, cArr);
            if (i8 + 4 == i3) {
                cArr2[i7 + 4] = '\n';
            }
        }
        return cArr2;
    }
}

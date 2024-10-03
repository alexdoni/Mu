package com.tencent.p014av.signature;

import android.util.Base64;

/* loaded from: classes3.dex */
public class Base64Url {
    public static byte[] base64EncodeUrl(byte[] bArr) {
        byte[] encode = Base64.encode(bArr, 0);
        for (int i = 0; i < encode.length; i++) {
            byte b = encode[i];
            if (b == 43) {
                encode[i] = 42;
            } else if (b == 47) {
                encode[i] = 45;
            } else if (b == 61) {
                encode[i] = 95;
            }
        }
        return encode;
    }

    public static byte[] base64DecodeUrl(byte[] bArr) {
        byte[] bArr2 = (byte[]) bArr.clone();
        for (int i = 0; i < bArr2.length; i++) {
            byte b = bArr2[i];
            if (b == 42) {
                bArr2[i] = 43;
            } else if (b == 45) {
                bArr2[i] = 47;
            } else if (b == 95) {
                bArr2[i] = kotlin.io.encoding.Base64.padSymbol;
            }
        }
        return Base64.decode(bArr2, 2);
    }
}

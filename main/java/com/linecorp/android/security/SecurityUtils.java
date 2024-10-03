package com.linecorp.android.security;

import android.util.Base64;
import java.security.SecureRandom;

/* loaded from: classes2.dex */
public final class SecurityUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static Object hideIfNotDebug(Object obj) {
        return "#####";
    }

    private SecurityUtils() {
    }

    public static String createRandomString(int i) {
        byte[] bArr = new byte[i];
        secureRandom.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }
}

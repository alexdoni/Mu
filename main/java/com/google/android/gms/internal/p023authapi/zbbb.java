package com.google.android.gms.internal.p023authapi;

import android.util.Base64;
import java.security.SecureRandom;

/* compiled from: com.google.android.gms:play-services-auth@@20.6.0 */
/* loaded from: classes2.dex */
public final class zbbb {
    private static final SecureRandom zba = new SecureRandom();

    public static String zba() {
        byte[] bArr = new byte[16];
        zba.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }
}

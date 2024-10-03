package com.google.android.gms.internal.recaptcha;

import java.nio.ByteOrder;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzlv {
    private static final zzls zza;

    static {
        zzls zzlsVar = zzlr.INSTANCE;
        try {
            if ("amd64".equals(System.getProperty("os.arch"))) {
                if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
                    zzlsVar = zzlu.UNSAFE_LITTLE_ENDIAN;
                } else {
                    zzlsVar = zzlu.UNSAFE_BIG_ENDIAN;
                }
            }
        } catch (Throwable unused) {
        }
        zza = zzlsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzb(byte[] bArr, int i) {
        return zza.zza(bArr, i);
    }
}

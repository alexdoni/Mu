package com.google.android.gms.internal.recaptcha;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
enum zzlu implements zzls {
    UNSAFE_LITTLE_ENDIAN,
    UNSAFE_BIG_ENDIAN;

    private static final Unsafe zzc;
    private static final int zzd;

    static {
        Unsafe zzd2 = zzd();
        zzc = zzd2;
        zzd = zzd2.arrayBaseOffset(byte[].class);
        if (zzd2.arrayIndexScale(byte[].class) != 1) {
            throw new AssertionError();
        }
    }

    public static final long zzb(byte[] bArr, int i) {
        return zzc.getLong(bArr, i + zzd);
    }

    public static final long zzc(byte[] bArr, int i) {
        return Long.reverseBytes(zzc.getLong(bArr, i + zzd));
    }

    private static Unsafe zzd() {
        try {
            try {
                return Unsafe.getUnsafe();
            } catch (SecurityException unused) {
                return (Unsafe) AccessController.doPrivileged(new zzlt());
            }
        } catch (PrivilegedActionException e) {
            throw new RuntimeException("Could not initialize intrinsics", e.getCause());
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzls
    public final /* synthetic */ long zza(byte[] bArr, int i) {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return zzb(bArr, i);
        }
        if (ordinal == 1) {
            return zzc(bArr, i);
        }
        throw null;
    }
}

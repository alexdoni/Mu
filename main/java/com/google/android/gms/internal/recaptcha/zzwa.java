package com.google.android.gms.internal.recaptcha;

import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public enum zzwa implements zzri {
    ARM7(2),
    X86(4),
    ARM64(5),
    X86_64(6);

    private static final zzrj<zzwa> zze = new zzrj<zzwa>() { // from class: com.google.android.gms.internal.recaptcha.zzvy
    };
    private final int zzg;

    zzwa(int i) {
        this.zzg = i;
    }

    public static zzrk zzb() {
        return zzvz.zza;
    }

    public static zzwa zzc(int i) {
        if (i == 2) {
            return ARM7;
        }
        if (i == 4) {
            return X86;
        }
        if (i == 5) {
            return ARM64;
        }
        if (i != 6) {
            return null;
        }
        return X86_64;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzri
    public final int zza() {
        return this.zzg;
    }
}

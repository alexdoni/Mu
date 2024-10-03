package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zztp extends zzrg<zztp, zzto> implements zzso {
    private static final zztp zzb;
    private static volatile zzsu<zztp> zze;
    private long zzf;
    private int zzg;

    static {
        zztp zztpVar = new zztp();
        zzb = zztpVar;
        zzrg.zzD(zztp.class, zztpVar);
    }

    private zztp() {
    }

    public static zzto zzf() {
        return zzb.zzu();
    }

    public static zztp zzi() {
        return zzb;
    }

    public final int zzd() {
        return this.zzg;
    }

    public final long zze() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return new zzsy(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zztp();
        }
        zztn zztnVar = null;
        if (i2 == 4) {
            return new zzto(zztnVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zztp> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zztp.class) {
                zzsuVar = zze;
                if (zzsuVar == null) {
                    zzsuVar = new zzrc<>(zzb);
                    zze = zzsuVar;
                }
            }
        }
        return zzsuVar;
    }
}

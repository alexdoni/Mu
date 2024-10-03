package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvu extends zzrg<zzvu, zzvt> implements zzso {
    private static final zzvu zzb;
    private static volatile zzsu<zzvu> zze;
    private int zzg;
    private long zzi;
    private zzmb zzj;
    private int zzk;
    private String zzf = "";
    private String zzh = "";

    static {
        zzvu zzvuVar = new zzvu();
        zzb = zzvuVar;
        zzrg.zzD(zzvu.class, zzvuVar);
    }

    private zzvu() {
    }

    public static zzvt zzf() {
        return zzb.zzu();
    }

    public static zzvu zzi() {
        return zzb;
    }

    public final int zzd() {
        return this.zzg;
    }

    public final long zze() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0006\u0000\u0000\u0001\u0007\u0006\u0000\u0000\u0000\u0001Ȉ\u0002\u0004\u0003Ȉ\u0004\u0002\u0005\t\u0007\f", new Object[]{"zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzvu();
        }
        if (i2 == 4) {
            return new zzvt(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvu> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvu.class) {
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

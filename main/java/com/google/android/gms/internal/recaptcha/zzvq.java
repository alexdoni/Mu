package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvq extends zzrg<zzvq, zzvp> implements zzso {
    private static final zzvq zzb;
    private static volatile zzsu<zzvq> zze;
    private String zzf = "";
    private String zzg = "";

    static {
        zzvq zzvqVar = new zzvq();
        zzb = zzvqVar;
        zzrg.zzD(zzvq.class, zzvqVar);
    }

    private zzvq() {
    }

    public static zzvp zzd() {
        return zzb.zzu();
    }

    public static /* synthetic */ void zzf(zzvq zzvqVar, String str) {
        str.getClass();
        zzvqVar.zzf = str;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzvq();
        }
        if (i2 == 4) {
            return new zzvp(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvq> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvq.class) {
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

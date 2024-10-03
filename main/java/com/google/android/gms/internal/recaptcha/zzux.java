package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzux extends zzrg<zzux, zzuw> implements zzso {
    private static final zzux zzb;
    private static volatile zzsu<zzux> zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";

    static {
        zzux zzuxVar = new zzux();
        zzb = zzuxVar;
        zzrg.zzD(zzux.class, zzuxVar);
    }

    private zzux() {
    }

    public static zzuw zzd() {
        return zzb.zzu();
    }

    public static /* synthetic */ void zzf(zzux zzuxVar, String str) {
        str.getClass();
        zzuxVar.zzf = str;
    }

    public static /* synthetic */ void zzg(zzux zzuxVar, String str) {
        str.getClass();
        zzuxVar.zzg = str;
    }

    public static /* synthetic */ void zzi(zzux zzuxVar, String str) {
        str.getClass();
        zzuxVar.zzh = str;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzux();
        }
        if (i2 == 4) {
            return new zzuw(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzux> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzux.class) {
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

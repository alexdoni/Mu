package com.google.android.gms.internal.recaptcha;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvb extends zzrg<zzvb, zzuz> implements zzso {
    private static final zzvb zzb;
    private static volatile zzsu<zzvb> zze;
    private long zzh;
    private boolean zzl;
    private zzvh zzo;
    private zzsh<String, String> zzj = zzsh.zza();
    private zzsh<Integer, zzpy> zzm = zzsh.zza();
    private String zzf = "";
    private String zzg = "";
    private String zzi = "";
    private String zzk = "";
    private String zzn = "";

    static {
        zzvb zzvbVar = new zzvb();
        zzb = zzvbVar;
        zzrg.zzD(zzvb.class, zzvbVar);
    }

    private zzvb() {
    }

    public static /* synthetic */ void zzE(zzvb zzvbVar, String str) {
        str.getClass();
        zzvbVar.zzg = str;
    }

    public static zzuz zzd() {
        return zzb.zzu();
    }

    public static /* synthetic */ Map zzf(zzvb zzvbVar) {
        if (!zzvbVar.zzj.zze()) {
            zzvbVar.zzj = zzvbVar.zzj.zzb();
        }
        return zzvbVar.zzj;
    }

    public static /* synthetic */ Map zzg(zzvb zzvbVar) {
        if (!zzvbVar.zzm.zze()) {
            zzvbVar.zzm = zzvbVar.zzm.zzb();
        }
        return zzvbVar.zzm;
    }

    public static /* synthetic */ void zzi(zzvb zzvbVar, String str) {
        str.getClass();
        zzvbVar.zzk = str;
    }

    public static /* synthetic */ void zzk(zzvb zzvbVar, String str) {
        str.getClass();
        zzvbVar.zzn = str;
    }

    public static /* synthetic */ void zzm(zzvb zzvbVar, String str) {
        str.getClass();
        zzvbVar.zzf = str;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\n\u0000\u0000\u0001\n\n\u0002\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0002\u0004Ȉ\u00052\u0006Ȉ\u0007\u0007\b2\tȈ\n\t", new Object[]{"zzf", "zzg", "zzh", "zzi", "zzj", zzuy.zza, "zzk", "zzl", "zzm", zzva.zza, "zzn", "zzo"});
        }
        if (i2 == 3) {
            return new zzvb();
        }
        if (i2 == 4) {
            return new zzuz(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvb> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvb.class) {
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

package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzdp extends zzrg<zzdp, zzdo> implements zzso {
    private static final zzdp zzb;
    private static volatile zzsu<zzdp> zze;
    private String zzf = "";
    private zztp zzg;

    static {
        zzdp zzdpVar = new zzdp();
        zzb = zzdpVar;
        zzrg.zzD(zzdp.class, zzdpVar);
    }

    private zzdp() {
    }

    public static zzdo zza() {
        return zzb.zzu();
    }

    public static zzdp zzc() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzdp zzdpVar, String str) {
        str.getClass();
        zzdpVar.zzf = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzdp zzdpVar, zztp zztpVar) {
        zztpVar.getClass();
        zzdpVar.zzg = zztpVar;
    }

    public final String zzd() {
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
            return zzC(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzdp();
        }
        zzdg zzdgVar = null;
        if (i2 == 4) {
            return new zzdo(zzdgVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzdp> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzdp.class) {
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

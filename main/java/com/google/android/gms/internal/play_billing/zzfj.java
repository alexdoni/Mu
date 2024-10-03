package com.google.android.gms.internal.play_billing;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
public final class zzfj extends zzcb implements zzdg {
    private static final zzfj zzb;
    private int zzd;
    private int zze;
    private int zzg;
    private String zzf = "";
    private String zzh = "";

    static {
        zzfj zzfjVar = new zzfj();
        zzb = zzfjVar;
        zzcb.zzo(zzfj.class, zzfjVar);
    }

    private zzfj() {
    }

    public static zzfh zzu() {
        return (zzfh) zzb.zze();
    }

    public static /* synthetic */ void zzw(zzfj zzfjVar, int i) {
        zzfjVar.zzd |= 1;
        zzfjVar.zze = i;
    }

    public static /* synthetic */ void zzx(zzfj zzfjVar, String str) {
        str.getClass();
        zzfjVar.zzd |= 2;
        zzfjVar.zzf = str;
    }

    public static /* synthetic */ void zzy(zzfj zzfjVar, int i) {
        zzfjVar.zzg = i - 1;
        zzfjVar.zzd |= 4;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzt(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzl(zzb, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0004ဌ\u0002\u0005ဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", zzfi.zza, "zzh"});
        }
        if (i2 == 3) {
            return new zzfj();
        }
        if (i2 == 4) {
            return new zzfh(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

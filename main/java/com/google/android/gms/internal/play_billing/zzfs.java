package com.google.android.gms.internal.play_billing;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
public final class zzfs extends zzcb implements zzdg {
    private static final zzfs zzb;
    private int zzd;
    private int zze;
    private String zzf = "";

    static {
        zzfs zzfsVar = new zzfs();
        zzb = zzfsVar;
        zzcb.zzo(zzfs.class, zzfsVar);
    }

    private zzfs() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzt(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzl(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", zzfo.zza, "zzf"});
        }
        if (i2 == 3) {
            return new zzfs();
        }
        if (i2 == 4) {
            return new zzfr(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

package com.google.android.gms.internal.play_billing;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
public final class zzfb extends zzcb implements zzdg {
    private static final zzfb zzb;
    private int zzd;
    private int zze;
    private zzfj zzf;
    private zzfs zzg;

    static {
        zzfb zzfbVar = new zzfb();
        zzb = zzfbVar;
        zzcb.zzo(zzfb.class, zzfbVar);
    }

    private zzfb() {
    }

    public static zzfa zzu() {
        return (zzfa) zzb.zze();
    }

    public static zzfb zzw(byte[] bArr, zzbn zzbnVar) throws zzci {
        return (zzfb) zzcb.zzi(zzb, bArr, zzbnVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzx(zzfb zzfbVar, zzfj zzfjVar) {
        zzfjVar.getClass();
        zzfbVar.zzf = zzfjVar;
        zzfbVar.zzd |= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzy(zzfb zzfbVar, int i) {
        zzfbVar.zze = i - 1;
        zzfbVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    protected final Object zzt(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzl(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zzd", "zze", zzfc.zza, "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzfb();
        }
        zzez zzezVar = null;
        if (i2 == 4) {
            return new zzfa(zzezVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

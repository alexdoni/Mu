package com.google.android.gms.internal.recaptcha;

import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzdk extends zzrg<zzdk, zzdh> implements zzso {
    private static final zzdk zzb;
    private static volatile zzsu<zzdk> zze;
    private zzsh<String, zzdn> zzf = zzsh.zza();
    private zzsh<String, zzdp> zzg = zzsh.zza();

    static {
        zzdk zzdkVar = new zzdk();
        zzb = zzdkVar;
        zzrg.zzD(zzdk.class, zzdkVar);
    }

    private zzdk() {
    }

    public static zzdk zzb() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map zzc(zzdk zzdkVar) {
        if (!zzdkVar.zzf.zze()) {
            zzdkVar.zzf = zzdkVar.zzf.zzb();
        }
        return zzdkVar.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map zzd(zzdk zzdkVar) {
        if (!zzdkVar.zzg.zze()) {
            zzdkVar.zzg = zzdkVar.zzg.zzb();
        }
        return zzdkVar.zzg;
    }

    public final Map<String, zzdp> zze() {
        return Collections.unmodifiableMap(this.zzg);
    }

    public final Map<String, zzdn> zzf() {
        return Collections.unmodifiableMap(this.zzf);
    }

    public final boolean zzg(String str) {
        str.getClass();
        return this.zzf.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0002\u0000\u0000\u00012\u00022", new Object[]{"zzf", zzdj.zza, "zzg", zzdi.zza});
        }
        if (i2 == 3) {
            return new zzdk();
        }
        zzdg zzdgVar = null;
        if (i2 == 4) {
            return new zzdh(zzdgVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzdk> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzdk.class) {
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

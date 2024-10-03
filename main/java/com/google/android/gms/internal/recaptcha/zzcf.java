package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import com.facebook.internal.security.CertificateUtil;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcf {
    private final zzfx<zzdk> zza;
    private final zzcs zzb;

    protected zzcf(zzfx<zzdk> zzfxVar, zzcs zzcsVar) {
        this.zza = zzfxVar;
        this.zzb = zzcsVar;
    }

    public static zzcf zza(Context context) {
        return new zzcf(zzcb.zza(context, zzca.zza(context)), zzct.zza());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String zze(String str, String str2, zzdk zzdkVar) {
        zzdp zzdpVar = zzdkVar.zze().get(zzf(str, str2));
        return zzdpVar == null ? "" : zzdpVar.zzd();
    }

    private static String zzf(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(CertificateUtil.DELIMITER);
        sb.append(str2);
        return sb.toString();
    }

    public final zzop<String> zzb(final String str, final String str2) {
        return zzof.zzj(this.zza.zzd(), new zzjc() { // from class: com.google.android.gms.internal.recaptcha.zzcc
            @Override // com.google.android.gms.internal.recaptcha.zzjc
            public final Object zza(Object obj) {
                return zzcf.zze(str, str2, (zzdk) obj);
            }
        }, zzow.zzb());
    }

    public final /* synthetic */ zzop zzc(String str, String str2, String str3, zzdk zzdkVar) throws Exception {
        zzdh zzv = zzdkVar.zzv();
        String zzf = zzf(str, str2);
        zzdo zza = zzdp.zza();
        zza.zzb(str3);
        zza.zza(this.zzb.zza());
        zzv.zza(zzf, zza.zzk());
        return zzof.zzf(zzv.zzk());
    }

    public final zzop<Void> zzd(final String str, final String str2, final String str3, String str4) {
        zzop<Void> zzi = this.zza.zzi(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzcd
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj) {
                return zzcf.this.zzc(str, str2, str3, (zzdk) obj);
            }
        }, zzow.zzb());
        zzof.zzm(zzi, new zzce(this, str4, str3, str, str2), zzbz.zza);
        return zzi;
    }
}

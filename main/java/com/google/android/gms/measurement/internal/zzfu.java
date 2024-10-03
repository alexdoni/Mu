package com.google.android.gms.measurement.internal;

import com.facebook.internal.security.CertificateUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzfu implements Runnable {
    private final /* synthetic */ int zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Object zzc;
    private final /* synthetic */ Object zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ zzfr zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfu(zzfr zzfrVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzfrVar;
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        char c;
        long j;
        char c2;
        long j2;
        zzgd zzn = this.zzf.zzu.zzn();
        if (!zzn.zzae()) {
            this.zzf.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        c = this.zzf.zza;
        if (c == 0) {
            if (this.zzf.zze().zzx()) {
                this.zzf.zza = 'C';
            } else {
                this.zzf.zza = 'c';
            }
        }
        j = this.zzf.zzb;
        if (j < 0) {
            this.zzf.zzb = 82001L;
        }
        char charAt = "01VDIWEA?".charAt(this.zza);
        c2 = this.zzf.zza;
        j2 = this.zzf.zzb;
        String str = "2" + charAt + c2 + j2 + CertificateUtil.DELIMITER + zzfr.zza(true, this.zzb, this.zzc, this.zzd, this.zze);
        if (str.length() > 1024) {
            str = this.zzb.substring(0, 1024);
        }
        if (zzn.zzb != null) {
            zzn.zzb.zza(str, 1L);
        }
    }
}

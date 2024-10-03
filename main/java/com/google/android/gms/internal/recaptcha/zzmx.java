package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzmx implements Runnable {
    final /* synthetic */ zzop zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ zzmz zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmx(zzmz zzmzVar, zzop zzopVar, int i) {
        this.zzc = zzmzVar;
        this.zza = zzopVar;
        this.zzb = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.zza.isCancelled()) {
                zzmz.zzd(this.zzc, null);
                this.zzc.cancel(false);
            } else {
                zzmz.zze(this.zzc, this.zzb, this.zza);
            }
        } finally {
            zzmz.zzw(this.zzc, null);
        }
    }
}

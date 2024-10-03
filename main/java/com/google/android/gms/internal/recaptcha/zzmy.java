package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzmy implements Runnable {
    final /* synthetic */ zzmz zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmy(zzmz zzmzVar, zzke zzkeVar) {
        this.zza = zzmzVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzmz.zzw(this.zza, null);
    }
}

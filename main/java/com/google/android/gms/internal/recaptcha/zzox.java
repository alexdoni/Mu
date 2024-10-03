package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzox implements Runnable {
    final /* synthetic */ Runnable zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzox(zzoz zzozVar, Runnable runnable) {
        this.zza = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.run();
    }

    public final String toString() {
        return this.zza.toString();
    }
}

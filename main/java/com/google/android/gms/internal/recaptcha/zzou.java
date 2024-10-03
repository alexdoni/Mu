package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzms;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzou extends zzms.zzi<Void> implements Runnable {
    private final Runnable zza;

    public zzou(Runnable runnable) {
        runnable.getClass();
        this.zza = runnable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzms
    public final String zza() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 7);
        sb.append("task=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.run();
        } catch (Throwable th) {
            zzu(th);
            zzjw.zza(th);
            throw new RuntimeException(th);
        }
    }
}

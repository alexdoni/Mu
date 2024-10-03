package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.internal.recaptcha.zzms;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzoe<V> extends zzms.zzi<V> implements Runnable {
    private zzop<V> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzoe(zzop<V> zzopVar) {
        this.zza = zzopVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzop<V> zzopVar = this.zza;
        if (zzopVar != null) {
            zzc(zzopVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzms
    public final String zza() {
        zzop<V> zzopVar = this.zza;
        if (zzopVar == null) {
            return null;
        }
        String valueOf = String.valueOf(zzopVar);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 11);
        sb.append("delegate=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzms
    protected final void zzb() {
        this.zza = null;
    }
}

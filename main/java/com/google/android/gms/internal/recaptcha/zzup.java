package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzup extends zzrb<zzuq, zzup> implements zzso {
    private zzup() {
        super(zzuq.zze());
    }

    public final zzup zza(zzux zzuxVar) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzuq.zzf((zzuq) this.zza, zzuxVar);
        return this;
    }

    public final zzup zzb(boolean z) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzuq.zzg((zzuq) this.zza, true);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzup(zzuo zzuoVar) {
        super(zzuq.zze());
    }
}

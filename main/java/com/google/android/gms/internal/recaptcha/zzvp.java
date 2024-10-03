package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvp extends zzrb<zzvq, zzvp> implements zzso {
    private zzvp() {
        super(zzvq.zze());
    }

    public final zzvp zza(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvq.zzg((zzvq) this.zza, str);
        return this;
    }

    public final zzvp zzb(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvq.zzf((zzvq) this.zza, str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzvp(zzvk zzvkVar) {
        super(zzvq.zze());
    }
}

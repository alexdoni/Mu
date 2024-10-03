package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzuw extends zzrb<zzux, zzuw> implements zzso {
    private zzuw() {
        super(zzux.zze());
    }

    public final zzuw zza(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzux.zzi((zzux) this.zza, str);
        return this;
    }

    public final zzuw zzb(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzux.zzg((zzux) this.zza, str);
        return this;
    }

    public final zzuw zzc(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzux.zzf((zzux) this.zza, str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzuw(zzuo zzuoVar) {
        super(zzux.zze());
    }
}

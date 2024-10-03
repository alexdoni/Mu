package com.google.android.gms.internal.recaptcha;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzuz extends zzrb<zzvb, zzuz> implements zzso {
    private zzuz() {
        super(zzvb.zze());
    }

    public final zzuz zza(Map<String, String> map) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzf((zzvb) this.zza).putAll(map);
        return this;
    }

    public final zzuz zzb(Map<Integer, zzpy> map) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzg((zzvb) this.zza).putAll(map);
        return this;
    }

    public final zzuz zzc(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzE((zzvb) this.zza, str);
        return this;
    }

    public final zzuz zzd(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzm((zzvb) this.zza, str);
        return this;
    }

    public final zzuz zze(zzvh zzvhVar) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzl((zzvb) this.zza, zzvhVar);
        return this;
    }

    public final zzuz zzq(boolean z) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzj((zzvb) this.zza, true);
        return this;
    }

    public final zzuz zzr(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzk((zzvb) this.zza, str);
        return this;
    }

    public final zzuz zzs(String str) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzi((zzvb) this.zza, str);
        return this;
    }

    public final zzuz zzt(long j) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zzvb.zzF((zzvb) this.zza, j);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzuz(zzuo zzuoVar) {
        super(zzvb.zze());
    }
}

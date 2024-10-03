package com.google.android.gms.internal.recaptcha;

import java.util.HashMap;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzgc {
    private Executor zza;
    private zzed zzb;
    private final zzhh zzd = zzhh.zza;
    private final HashMap<String, zzhd> zzc = new HashMap<>();

    public final zzgb zza() {
        return new zzgb(this.zza, this.zzb, this.zzd, this.zzc, null, null);
    }

    public final zzgc zzb(zzhd zzhdVar) {
        zzjn.zzg(!this.zzc.containsKey("singleproc"), "There is already a factory registered for the ID %s", "singleproc");
        this.zzc.put("singleproc", zzhdVar);
        return this;
    }

    public final zzgc zzc(Executor executor) {
        this.zza = executor;
        return this;
    }

    public final zzgc zzd(zzed zzedVar) {
        this.zzb = zzedVar;
        return this;
    }
}

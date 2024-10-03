package com.google.android.gms.internal.recaptcha;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzsg<K, V> {
    private final zzsf<K, V> zza;
    private final K zzb;
    private final V zzc;

    private zzsg(zzuh zzuhVar, K k, zzuh zzuhVar2, V v) {
        this.zza = new zzsf<>(zzuhVar, k, zzuhVar2, v);
        this.zzb = k;
        this.zzc = v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> int zzb(zzsf<K, V> zzsfVar, K k, V v) {
        return zzqw.zza(zzsfVar.zza, 1, k) + zzqw.zza(zzsfVar.zzc, 2, v);
    }

    public static <K, V> zzsg<K, V> zzd(zzuh zzuhVar, K k, zzuh zzuhVar2, V v) {
        return new zzsg<>(zzuhVar, k, zzuhVar2, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> void zze(zzqj zzqjVar, zzsf<K, V> zzsfVar, K k, V v) throws IOException {
        zzqw.zze(zzqjVar, zzsfVar.zza, 1, k);
        zzqw.zze(zzqjVar, zzsfVar.zzc, 2, v);
    }

    public final int zza(int i, K k, V v) {
        int zzJ = zzqj.zzJ(i);
        int zzb = zzb(this.zza, k, v);
        return zzJ + zzqj.zzK(zzb) + zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzsf<K, V> zzc() {
        return this.zza;
    }
}

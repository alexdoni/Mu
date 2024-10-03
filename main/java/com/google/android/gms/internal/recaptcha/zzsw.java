package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzsw {
    private static final zzsw zza = new zzsw();
    private final ConcurrentMap<Class<?>, zzta<?>> zzc = new ConcurrentHashMap();
    private final zztb zzb = new zzse();

    private zzsw() {
    }

    public static zzsw zza() {
        return zza;
    }

    public final <T> zzta<T> zzb(Class<T> cls) {
        zzrp.zzf(cls, "messageType");
        zzta<T> zztaVar = (zzta) this.zzc.get(cls);
        if (zztaVar == null) {
            zztaVar = this.zzb.zza(cls);
            zzrp.zzf(cls, "messageType");
            zzrp.zzf(zztaVar, "schema");
            zzta<T> zztaVar2 = (zzta) this.zzc.putIfAbsent(cls, zztaVar);
            if (zztaVar2 != null) {
                return zztaVar2;
            }
        }
        return zztaVar;
    }
}

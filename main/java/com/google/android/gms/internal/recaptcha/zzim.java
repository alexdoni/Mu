package com.google.android.gms.internal.recaptcha;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.CheckReturnValue;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
@CheckReturnValue
/* loaded from: classes2.dex */
public final class zzim {
    static final Map<Long, zzii> zza;

    static {
        Math.abs(new Random().nextInt());
        zza = new HashMap();
    }

    @CheckReturnValue
    public static <I, O> zzjc<I, O> zza(zzjc<I, O> zzjcVar) {
        return new zzik(zziq.zzb(), zzjcVar);
    }

    @CheckReturnValue
    public static <I, O> zzng<I, O> zzc(zzng<I, O> zzngVar) {
        return new zzil(zziq.zzb(), zzngVar);
    }

    @CheckReturnValue
    public static <V> zznf<V> zzb(zznf<V> zznfVar) {
        zznfVar.getClass();
        return new zzij(zziq.zzb(), zznfVar);
    }
}

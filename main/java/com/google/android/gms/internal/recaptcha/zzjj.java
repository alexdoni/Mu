package com.google.android.gms.internal.recaptcha;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzjj<T> implements Serializable {
    public static <T> zzjj<T> zzd() {
        return zzit.zza;
    }

    public static <T> zzjj<T> zze(@CheckForNull T t) {
        return t == null ? zzit.zza : new zzjo(t);
    }

    public static <T> zzjj<T> zzf(T t) {
        return new zzjo(t);
    }

    public abstract T zza();

    @CheckForNull
    public abstract T zzb();

    public abstract boolean zzc();
}

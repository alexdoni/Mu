package com.google.android.gms.internal.recaptcha;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzit<T> extends zzjj<T> {
    static final zzit<Object> zza = new zzit<>();

    private zzit() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjj
    public final T zza() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjj
    @CheckForNull
    public final T zzb() {
        return null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjj
    public final boolean zzc() {
        return false;
    }
}

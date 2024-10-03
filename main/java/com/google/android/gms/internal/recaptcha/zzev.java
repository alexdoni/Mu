package com.google.android.gms.internal.recaptcha;

import java.io.Closeable;
import java.io.IOException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzev<T extends Closeable> implements Closeable {

    @Nullable
    private final T zza;

    private zzev(T t) {
        this.zza = t;
    }

    public static <T extends Closeable> zzev<T> zza(T t) {
        return new zzev<>(t);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        T t = this.zza;
        if (t != null) {
            t.close();
        }
    }

    @Nullable
    public final T zzb() {
        return this.zza;
    }
}

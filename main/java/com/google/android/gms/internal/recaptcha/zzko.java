package com.google.android.gms.internal.recaptcha;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzko {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T zza(Iterable<T> iterable) {
        if (!iterable.isEmpty()) {
            return (T) iterable.get(iterable.size() - 1);
        }
        throw new NoSuchElementException();
    }
}

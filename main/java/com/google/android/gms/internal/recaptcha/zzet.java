package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzet {

    @Nullable
    private final ConcurrentMap<String, Semaphore> zza = new ConcurrentHashMap();

    @Deprecated
    public zzet() {
    }
}

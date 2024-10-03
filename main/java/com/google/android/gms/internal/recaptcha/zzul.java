package com.google.android.gms.internal.recaptcha;

import java.time.Instant;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzul {
    public static zztp zza(zzmp zzmpVar) {
        Instant now = Instant.now();
        return zzun.zzd(now.getEpochSecond(), now.getNano());
    }
}

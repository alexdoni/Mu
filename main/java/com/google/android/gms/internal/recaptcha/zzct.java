package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzct {
    public static final zzcs zza() {
        if (!PlatformVersion.isAtLeastO()) {
            return new zzcr();
        }
        return new zzcu();
    }
}

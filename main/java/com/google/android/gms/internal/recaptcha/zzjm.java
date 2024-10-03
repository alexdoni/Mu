package com.google.android.gms.internal.recaptcha;

import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzjm {
    private static final Logger zza = Logger.getLogger(zzjm.class.getName());
    private static final zzjl zzb = new zzjl(null);

    private zzjm() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckForNull
    public static String zza(@CheckForNull String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzb(@CheckForNull String str) {
        return str == null ? "" : str;
    }
}

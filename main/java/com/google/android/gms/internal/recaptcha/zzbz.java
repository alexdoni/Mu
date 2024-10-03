package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzbz {
    public static final ExecutorService zza;
    private static final String zzb = "https://www.recaptcha.net";

    static {
        zzq.zza();
        zza = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
    }

    public static final String zza() {
        String str = zzb;
        StringBuilder sb = new StringBuilder(str.length() + 18);
        sb.append(str);
        sb.append("/recaptcha/api3/ae");
        return sb.toString();
    }

    public static final String zzb() {
        String str = zzb;
        StringBuilder sb = new StringBuilder(str.length() + 18);
        sb.append(str);
        sb.append("/recaptcha/api3/ac");
        return sb.toString();
    }
}

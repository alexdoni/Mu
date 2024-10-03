package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcz {
    public static final ExecutorService zza;
    private static final String zzb = "https://www.recaptcha.net";

    static {
        zzq.zza();
        zza = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
    }

    public static final String zza() {
        String str = zzb;
        StringBuilder sb = new StringBuilder(str.length() + 32);
        sb.append(str);
        sb.append("/recaptcha/api3/accountchallenge");
        return sb.toString();
    }

    public static final String zzb() {
        String str = zzb;
        StringBuilder sb = new StringBuilder(str.length() + 29);
        sb.append(str);
        sb.append("/recaptcha/api3/accountverify");
        return sb.toString();
    }
}

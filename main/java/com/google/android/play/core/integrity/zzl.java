package com.google.android.play.core.integrity;

import android.content.Context;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
final class zzl {
    private static zzj zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized zzj zza(Context context) {
        zzj zzjVar;
        synchronized (zzl.class) {
            if (zza == null) {
                zzh zzhVar = new zzh(null);
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                zzhVar.zza(context);
                zza = zzhVar.zzb();
            }
            zzjVar = zza;
        }
        return zzjVar;
    }
}

package com.google.android.gms.internal.recaptcha;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzsi {
    public static final int zza(int i, Object obj, Object obj2) {
        zzsh zzshVar = (zzsh) obj;
        zzsg zzsgVar = (zzsg) obj2;
        int i2 = 0;
        if (!zzshVar.isEmpty()) {
            for (Map.Entry entry : zzshVar.entrySet()) {
                i2 += zzsgVar.zza(i, entry.getKey(), entry.getValue());
            }
        }
        return i2;
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzsh zzshVar = (zzsh) obj;
        zzsh zzshVar2 = (zzsh) obj2;
        if (!zzshVar2.isEmpty()) {
            if (!zzshVar.zze()) {
                zzshVar = zzshVar.zzb();
            }
            zzshVar.zzd(zzshVar2);
        }
        return zzshVar;
    }
}

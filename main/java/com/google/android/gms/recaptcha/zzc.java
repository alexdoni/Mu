package com.google.android.gms.recaptcha;

import com.google.android.gms.common.Feature;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzc {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature zzc;
    public static final Feature zzd;
    public static final Feature[] zze;

    static {
        Feature feature = new Feature("verify_with_recaptcha_v2_internal", 1L);
        zza = feature;
        Feature feature2 = new Feature("init", 3L);
        zzb = feature2;
        Feature feature3 = new Feature("execute", 5L);
        zzc = feature3;
        Feature feature4 = new Feature("close", 2L);
        zzd = feature4;
        zze = new Feature[]{feature, feature2, feature3, feature4};
    }
}

package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
abstract class zzjs {
    private static final zzjs zza = new zzjr();
    private static final zzjs zzb = new zzjt();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjs zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> List<L> zza(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> void zza(Object obj, Object obj2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjs zzb() {
        return zzb;
    }

    private zzjs() {
    }
}

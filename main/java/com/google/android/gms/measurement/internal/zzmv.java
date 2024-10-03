package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
final class zzmv implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzmw zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmv(zzmw zzmwVar, String str, String str2, Bundle bundle) {
        this.zzd = zzmwVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzd.zza.zza((zzbg) Preconditions.checkNotNull(this.zzd.zza.zzq().zza(this.zza, this.zzb, this.zzc, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, this.zzd.zza.zzb().currentTimeMillis(), false, true)), this.zza);
    }
}

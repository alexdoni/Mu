package com.google.firebase.analytics;

import com.google.android.gms.internal.measurement.zzdf;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-api@@21.5.0 */
/* loaded from: classes2.dex */
public final class zza implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zza;

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        zzdf zzdfVar;
        zzdfVar = this.zza.zzb;
        return zzdfVar.zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }
}

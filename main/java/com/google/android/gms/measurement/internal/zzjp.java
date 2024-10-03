package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzjp implements zznf {
    private final /* synthetic */ zziq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjp(zziq zziqVar) {
        this.zza = zziqVar;
    }

    @Override // com.google.android.gms.measurement.internal.zznf
    public final void zza(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str2, bundle, str);
        } else {
            this.zza.zzb(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str2, bundle);
        }
    }
}

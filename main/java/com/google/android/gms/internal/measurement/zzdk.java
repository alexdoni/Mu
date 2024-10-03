package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzdk extends zzdf.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Bundle zze;
    private final /* synthetic */ zzdf zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdk(zzdf zzdfVar, String str, String str2, Bundle bundle) {
        super(zzdfVar);
        this.zzf = zzdfVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzdf.zza
    final void zza() throws RemoteException {
        zzcu zzcuVar;
        zzcuVar = this.zzf.zzj;
        ((zzcu) Preconditions.checkNotNull(zzcuVar)).clearConditionalUserProperty(this.zzc, this.zzd, this.zze);
    }
}

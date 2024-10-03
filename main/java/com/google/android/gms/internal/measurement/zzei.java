package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzei extends zzdf.zza {
    private final /* synthetic */ zzcs zzc;
    private final /* synthetic */ int zzd;
    private final /* synthetic */ zzdf zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzei(zzdf zzdfVar, zzcs zzcsVar, int i) {
        super(zzdfVar);
        this.zze = zzdfVar;
        this.zzc = zzcsVar;
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.measurement.zzdf.zza
    protected final void zzb() {
        this.zzc.zza((Bundle) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdf.zza
    final void zza() throws RemoteException {
        zzcu zzcuVar;
        zzcuVar = this.zze.zzj;
        ((zzcu) Preconditions.checkNotNull(zzcuVar)).getTestFlag(this.zzc, this.zzd);
    }
}

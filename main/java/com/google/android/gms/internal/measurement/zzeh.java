package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzeh extends zzdf.zza {
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzdf zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzeh(zzdf zzdfVar, boolean z) {
        super(zzdfVar);
        this.zzd = zzdfVar;
        this.zzc = z;
    }

    @Override // com.google.android.gms.internal.measurement.zzdf.zza
    final void zza() throws RemoteException {
        zzcu zzcuVar;
        zzcuVar = this.zzd.zzj;
        ((zzcu) Preconditions.checkNotNull(zzcuVar)).setDataCollectionEnabled(this.zzc);
    }
}

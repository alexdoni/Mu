package com.google.android.play.integrity.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public final class zzd extends zza implements zzf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzd(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.integrity.protocol.IIntegrityService");
    }

    @Override // com.google.android.play.integrity.internal.zzf
    public final void zzc(Bundle bundle, zzh zzhVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, bundle);
        zzc.zzd(zza, zzhVar);
        zzb(2, zza);
    }
}

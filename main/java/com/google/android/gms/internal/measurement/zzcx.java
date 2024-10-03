package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzcx extends zzbu implements zzcv {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override // com.google.android.gms.internal.measurement.zzcv
    public final void zza(Bundle bundle) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, bundle);
        zzb(1, m318a_);
    }
}

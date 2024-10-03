package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzca extends zzbu implements zzby {
    @Override // com.google.android.gms.internal.measurement.zzby
    public final Bundle zza(Bundle bundle) throws RemoteException {
        Parcel m318a_ = m318a_();
        zzbw.zza(m318a_, bundle);
        Parcel zza = zza(1, m318a_);
        Bundle bundle2 = (Bundle) zzbw.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzca(IBinder iBinder) {
        super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }
}

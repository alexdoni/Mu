package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzdc extends zzbu implements zzda {
    @Override // com.google.android.gms.internal.measurement.zzda
    public final int zza() throws RemoteException {
        Parcel zza = zza(2, m318a_());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override // com.google.android.gms.internal.measurement.zzda
    public final void zza(String str, String str2, Bundle bundle, long j) throws RemoteException {
        Parcel m318a_ = m318a_();
        m318a_.writeString(str);
        m318a_.writeString(str2);
        zzbw.zza(m318a_, bundle);
        m318a_.writeLong(j);
        zzb(1, m318a_);
    }
}

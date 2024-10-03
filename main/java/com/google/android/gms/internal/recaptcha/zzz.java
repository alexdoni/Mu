package com.google.android.gms.internal.recaptcha;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzz extends zzb implements zzaa {
    public zzz() {
        super("com.google.android.gms.recaptcha.internal.ICloseCallback");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzb((Status) zzc.zza(parcel, Status.CREATOR), zzc.zzd(parcel));
        return true;
    }
}

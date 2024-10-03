package com.google.android.gms.internal.recaptcha;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaResultData;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzab extends zzb implements zzac {
    public zzab() {
        super("com.google.android.gms.recaptcha.internal.IExecuteCallback");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzb((Status) zzc.zza(parcel, Status.CREATOR), (RecaptchaResultData) zzc.zza(parcel, RecaptchaResultData.CREATOR));
        } else {
            if (i != 2) {
                return false;
            }
            zzc((Status) zzc.zza(parcel, Status.CREATOR), (zzx) zzc.zza(parcel, zzx.CREATOR));
        }
        return true;
    }
}

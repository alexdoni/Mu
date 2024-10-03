package com.google.android.gms.internal.recaptcha;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaHandle;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzad extends zzb implements zzae {
    public zzad() {
        super("com.google.android.gms.recaptcha.internal.IInitCallback");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzb((Status) zzc.zza(parcel, Status.CREATOR), (RecaptchaHandle) zzc.zza(parcel, RecaptchaHandle.CREATOR));
        } else {
            if (i != 2) {
                return false;
            }
            zzc((Status) zzc.zza(parcel, Status.CREATOR), (zzai) zzc.zza(parcel, zzai.CREATOR));
        }
        return true;
    }
}

package com.google.android.gms.internal.recaptcha;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.recaptcha.RecaptchaHandle;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzaf extends zza implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.recaptcha.internal.IRecaptchaService");
    }

    public final void zzc(zzaa zzaaVar, RecaptchaHandle recaptchaHandle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzaaVar);
        zzc.zzb(zza, recaptchaHandle);
        zzb(4, zza);
    }

    public final void zzd(zzac zzacVar, zzv zzvVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzacVar);
        zzc.zzb(zza, zzvVar);
        zzb(6, zza);
    }

    public final void zze(zzae zzaeVar, zzag zzagVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzaeVar);
        zzc.zzb(zza, zzagVar);
        zzb(5, zza);
    }
}

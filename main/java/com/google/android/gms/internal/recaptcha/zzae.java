package com.google.android.gms.internal.recaptcha;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaHandle;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public interface zzae extends IInterface {
    @Deprecated
    void zzb(Status status, RecaptchaHandle recaptchaHandle) throws RemoteException;

    void zzc(Status status, zzai zzaiVar) throws RemoteException;
}

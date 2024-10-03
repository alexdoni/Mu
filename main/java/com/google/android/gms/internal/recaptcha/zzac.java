package com.google.android.gms.internal.recaptcha;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaResultData;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public interface zzac extends IInterface {
    void zzb(Status status, RecaptchaResultData recaptchaResultData) throws RemoteException;

    void zzc(Status status, zzx zzxVar) throws RemoteException;
}

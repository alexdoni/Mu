package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzbr {
    private final zzbw zza;

    public zzbr(Context context) {
        this.zza = zzbw.zza(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(zzae zzaeVar, Status status) {
        try {
            zzaeVar.zzc(status, null);
        } catch (RemoteException e) {
            zzak.zza("RecaptchaOPInit", e);
        }
    }

    public final void zzb(zzae zzaeVar, String str, String str2, zzcp zzcpVar) {
        zzof.zzm(this.zza.zzc(str, str2, zzcpVar), new zzbq(this, zzaeVar, str, str2), zzbz.zza);
    }
}

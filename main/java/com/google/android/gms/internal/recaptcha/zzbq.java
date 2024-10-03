package com.google.android.gms.internal.recaptcha;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.HttpStatusException;
import com.google.android.gms.recaptcha.RecaptchaHandle;
import com.google.android.gms.recaptcha.RecaptchaNetworkException;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzbq implements zzoa<zzdn> {
    final /* synthetic */ zzae zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(zzbr zzbrVar, zzae zzaeVar, String str, String str2) {
        this.zza = zzaeVar;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoa
    public final void zza(Throwable th) {
        th.getMessage();
        if (th instanceof RecaptchaNetworkException) {
            zzbr.zza(this.zza, new Status(7, th.getMessage()));
            return;
        }
        if (th instanceof HttpStatusException) {
            zzae zzaeVar = this.zza;
            int httpErrorStatus = ((HttpStatusException) th).getHttpErrorStatus();
            StringBuilder sb = new StringBuilder(58);
            sb.append("Failed to fetch data for local cache - status: ");
            sb.append(httpErrorStatus);
            zzbr.zza(zzaeVar, new Status(13, sb.toString()));
            return;
        }
        if (th instanceof IOException) {
            zzbr.zza(this.zza, new Status(8, "Failed to read/write local cache"));
        } else {
            zzbr.zza(this.zza, new Status(8, "Internal error during init"));
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoa
    public final /* bridge */ /* synthetic */ void zzb(zzdn zzdnVar) {
        try {
            this.zza.zzc(new Status(0), new zzai(new RecaptchaHandle(this.zzb, this.zzc, zzdnVar.zzg())));
        } catch (RemoteException e) {
            zzak.zza("RecaptchaOPInit", e);
        }
    }
}

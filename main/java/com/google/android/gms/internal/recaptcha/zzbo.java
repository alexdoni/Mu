package com.google.android.gms.internal.recaptcha;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.HttpStatusException;
import com.google.android.gms.recaptcha.RecaptchaNetworkException;
import com.google.android.gms.recaptcha.RecaptchaResultData;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzbo implements zzoa<String> {
    final /* synthetic */ zzac zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbo(zzbp zzbpVar, zzac zzacVar) {
        this.zza = zzacVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoa
    public final void zza(Throwable th) {
        th.getMessage();
        if (th instanceof zzby) {
            zzbp.zzd(this.zza, new Status(8, "Error during initialization step - read/write local cache failed"));
            return;
        }
        if (th instanceof zzbx) {
            Exception exc = (Exception) th.getCause();
            if (exc instanceof RecaptchaNetworkException) {
                zzbp.zzd(this.zza, new Status(7, "Error during initialization step - server connection failed"));
                return;
            }
            if (exc instanceof HttpStatusException) {
                zzac zzacVar = this.zza;
                int httpErrorStatus = ((HttpStatusException) exc).getHttpErrorStatus();
                StringBuilder sb = new StringBuilder(92);
                sb.append("Error during initialization step - failed to fetch initialization data - status: ");
                sb.append(httpErrorStatus);
                zzbp.zzd(zzacVar, new Status(13, sb.toString()));
                return;
            }
            return;
        }
        if (th instanceof zzbi) {
            zzbp.zzd(this.zza, new Status(13, ((zzbi) th).getMessage()));
            return;
        }
        if (th instanceof RecaptchaNetworkException) {
            zzbp.zzd(this.zza, new Status(7, ((RecaptchaNetworkException) th).getMessage()));
        } else if (th instanceof HttpStatusException) {
            zzbp.zzd(this.zza, new Status(13, ((HttpStatusException) th).getMessage()));
        } else {
            zzbp.zzd(this.zza, new Status(8, "Internal error during execution"));
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoa
    public final /* bridge */ /* synthetic */ void zzb(String str) {
        String str2 = str;
        zzac zzacVar = this.zza;
        Status status = new Status(0);
        int i = zzbp.zza;
        try {
            zzacVar.zzc(status, new zzx(new RecaptchaResultData(str2)));
        } catch (RemoteException e) {
            zzak.zza("RecaptchaOPExecute", e);
        }
    }
}

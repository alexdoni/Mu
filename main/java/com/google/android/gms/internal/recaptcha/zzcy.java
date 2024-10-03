package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaHandle;
import com.google.android.gms.recaptcha.VerificationHandle;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcy {
    private final zzcs zza;
    private final ExecutorService zzb;
    private final zzbh<zzvo> zzc;

    public zzcy(zzcs zzcsVar) {
        this.zza = zzcsVar;
        ExecutorService executorService = zzcz.zza;
        this.zzb = executorService;
        this.zzc = new zzbh<>(zzcz.zza(), executorService, zzvo.zze());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzcy zzcyVar, zzcv zzcvVar, zzvo zzvoVar, RecaptchaHandle recaptchaHandle) {
        zzvw zzg = zzvoVar.zzg();
        zzvw zzvwVar = zzvw.NO_ERROR;
        int ordinal = zzvoVar.zzg().ordinal();
        if (ordinal == 2 || ordinal == 3) {
            zzf(zzcvVar, zzda.zza(zzg));
        } else if (ordinal == 7) {
            zzg(zzcvVar, VerificationHandle.zzf(zzvoVar.zzj(), zzcyVar.zza, recaptchaHandle.getSiteKey()));
        } else {
            zzg.zza();
            zzf(zzcvVar, new Status(13, "Internal Error."));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzf(zzcv zzcvVar, Status status) {
        try {
            zzcvVar.zza(status, null);
        } catch (zzs e) {
            zzak.zza("RecaptchaCAOperation", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zzg(zzcv zzcvVar, VerificationHandle verificationHandle) {
        try {
            zzcvVar.zza(new Status(0), verificationHandle);
        } catch (zzs e) {
            zzak.zza("RecaptchaCAOperation", e);
        }
    }

    public final void zze(zzcv zzcvVar, RecaptchaHandle recaptchaHandle, String str) {
        zzvl zzd = zzvm.zzd();
        zzd.zza(str);
        this.zzc.zza(zzd.zzk()).addOnSuccessListener(new zzcx(this, zzcvVar, recaptchaHandle, null)).addOnFailureListener(new zzcw(this, zzcvVar, null));
    }
}

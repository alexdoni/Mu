package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.VerificationHandle;
import com.google.android.gms.recaptcha.VerificationResult;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzdf {
    private final Context zza;
    private final zzcs zzb;
    private final ExecutorService zzc;
    private final zzbh<zzvs> zzd;

    public zzdf(Context context, zzcs zzcsVar) {
        this.zza = context;
        this.zzb = zzcsVar;
        ExecutorService executorService = zzcz.zza;
        this.zzc = executorService;
        this.zzd = new zzbh<>(zzcz.zzb(), executorService, zzvs.zze());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzdf zzdfVar, zzdc zzdcVar, zzvs zzvsVar, String str) {
        Status zza = zzda.zza(zzvsVar.zzg());
        zzvw zzvwVar = zzvw.NO_ERROR;
        switch (zzvsVar.zzg().ordinal()) {
            case 2:
            case 3:
            case 4:
            case 6:
                zzf(zzdcVar, zza);
                return;
            case 5:
                zzg(zzdcVar, VerificationResult.zza(VerificationHandle.zze(zzvsVar, zzdfVar.zzb, str), zza));
                return;
            case 7:
                zzg(zzdcVar, VerificationResult.zzb(zzvsVar.zzk(), zza));
                return;
            default:
                zzvsVar.zzg().zza();
                zzf(zzdcVar, new Status(13, "Internal Error."));
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzf(zzdc zzdcVar, Status status) {
        try {
            zzdcVar.zza(status, null);
        } catch (zzs e) {
            zzak.zza("RecaptchaVAOperation", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzg(zzdc zzdcVar, VerificationResult verificationResult) {
        try {
            zzdcVar.zza(new Status(0), verificationResult);
        } catch (zzs e) {
            zzak.zza("RecaptchaVAOperation", e);
        }
    }

    public final void zze(zzdc zzdcVar, String str, VerificationHandle verificationHandle) {
        if (!verificationHandle.isValid()) {
            zzf(zzdcVar, zzda.zza(zzvw.CHALLENGE_EXPIRED));
            return;
        }
        if (str.length() == verificationHandle.getCodeLength()) {
            for (char c : str.toCharArray()) {
                if (Character.isDigit(c)) {
                }
            }
            String verificationToken = verificationHandle.getVerificationToken();
            zzvp zzd = zzvq.zzd();
            zzd.zzb(verificationToken);
            zzd.zza(str);
            this.zzd.zza(zzd.zzk()).addOnSuccessListener(new zzde(this, verificationHandle, zzdcVar, null)).addOnFailureListener(new zzdd(this, zzdcVar, null));
            return;
        }
        zzf(zzdcVar, zzda.zza(zzvw.INVALID_PIN));
    }
}

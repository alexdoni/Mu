package com.google.android.gms.internal.recaptcha;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaAction;
import com.google.android.gms.recaptcha.RecaptchaClient;
import com.google.android.gms.recaptcha.RecaptchaHandle;
import com.google.android.gms.recaptcha.RecaptchaResultData;
import com.google.android.gms.recaptcha.VerificationHandle;
import com.google.android.gms.recaptcha.VerificationResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzaq implements RecaptchaClient {
    private static final zzcs zza = zzct.zza();
    private final Context zzb;
    private final zzbr zzc;
    private final zzbp zzd;
    private final zzbj zze;
    private final zzcy zzf;
    private final zzdf zzg;
    private final zzcp zzh;

    zzaq(Context context, zzbr zzbrVar, zzbp zzbpVar, zzbj zzbjVar, zzcy zzcyVar, zzdf zzdfVar, zzcp zzcpVar) {
        this.zzb = context;
        this.zzc = zzbrVar;
        this.zzd = zzbpVar;
        this.zze = zzbjVar;
        this.zzf = zzcyVar;
        this.zzg = zzdfVar;
        this.zzh = zzcpVar;
    }

    public static zzaq zza(Activity activity) {
        zzbr zzbrVar = new zzbr(activity);
        zzbp zzbpVar = new zzbp(activity);
        zzbj zzbjVar = new zzbj();
        zzcs zzcsVar = zza;
        return new zzaq(activity, zzbrVar, zzbpVar, zzbjVar, new zzcy(zzcsVar), new zzdf(activity, zzcsVar), zzcp.zzb());
    }

    public static zzaq zzb(Context context) {
        zzbr zzbrVar = new zzbr(context);
        zzbp zzbpVar = new zzbp(context);
        zzbj zzbjVar = new zzbj();
        zzcs zzcsVar = zza;
        return new zzaq(context, zzbrVar, zzbpVar, zzbjVar, new zzcy(zzcsVar), new zzdf(context, zzcsVar), zzcp.zzb());
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<RecaptchaHandle> init(String str) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (str == null) {
            throw new NullPointerException("Cannot call init with a null site key.");
        }
        this.zzc.zzb(new zzal(this, taskCompletionSource), str, this.zzb.getPackageName(), this.zzh);
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<VerificationHandle> challengeAccount(RecaptchaHandle recaptchaHandle, String str) {
        if (recaptchaHandle == null || str == null) {
            throw new NullPointerException("Cannot call challengeAccount with a null RecaptchaHandle or a null challenge request token.");
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzf.zze(new zzao(this, taskCompletionSource), recaptchaHandle, str);
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<Boolean> close(RecaptchaHandle recaptchaHandle) {
        if (recaptchaHandle == null) {
            throw new NullPointerException("Cannot call close with a null RecaptchaHandle.");
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            new zzan(this, taskCompletionSource).zzb(new Status(0), false);
        } catch (RemoteException e) {
            zzak.zza("RecaptchaOPClose", e);
        }
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<RecaptchaResultData> execute(RecaptchaHandle recaptchaHandle, RecaptchaAction recaptchaAction) {
        if (recaptchaHandle == null || recaptchaAction == null) {
            throw new NullPointerException("Cannot call execute with a null RecaptchaHandle or a null RecaptchaAction. Make sure to call init first.");
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzd.zze(new zzam(this, taskCompletionSource), recaptchaHandle, new RecaptchaAction(recaptchaAction, zzdb.zza(this.zzb, recaptchaHandle.getSiteKey())), this.zzh);
        return taskCompletionSource.getTask();
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<VerificationResult> verifyAccount(String str, VerificationHandle verificationHandle) {
        if (str == null || verificationHandle == null) {
            throw new NullPointerException("Cannot call verifyAccount with a null pin or a null VerificationHandle.");
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzg.zze(new zzap(this, taskCompletionSource), str, verificationHandle);
        return taskCompletionSource.getTask();
    }
}

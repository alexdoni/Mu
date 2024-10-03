package com.google.android.gms.internal.recaptcha;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
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
public final class zzbc extends GoogleApi<Api.ApiOptions.NoOptions> implements RecaptchaClient {
    private static final Api.ClientKey<zzbd> zza;
    private static final zzcs zzb;
    private static final Api.AbstractClientBuilder<zzbd, Api.ApiOptions.NoOptions> zzc;
    private static final Api<Api.ApiOptions.NoOptions> zzd;
    private final Context zze;
    private final zzcy zzf;
    private final zzdf zzg;

    static {
        Api.ClientKey<zzbd> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        zzb = zzct.zza();
        zzaw zzawVar = new zzaw();
        zzc = zzawVar;
        zzd = new Api<>("Recaptcha.API", zzawVar, clientKey);
    }

    public zzbc(Activity activity) {
        super(activity, zzd, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zze = activity;
        zzcs zzcsVar = zzb;
        this.zzf = new zzcy(zzcsVar);
        this.zzg = new zzdf(activity, zzcsVar);
    }

    public final /* synthetic */ void zza(RecaptchaHandle recaptchaHandle, String str, zzbd zzbdVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzf.zze(new zzba(this, taskCompletionSource), recaptchaHandle, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzb(RecaptchaHandle recaptchaHandle, RecaptchaAction recaptchaAction, zzbd zzbdVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzaf) zzbdVar.getService()).zzd(new zzay(this, taskCompletionSource), new zzv(recaptchaHandle, new RecaptchaAction(recaptchaAction, zzdb.zza(this.zze, recaptchaHandle.getSiteKey())), zzt.zza()));
    }

    public final /* synthetic */ void zzc(String str, VerificationHandle verificationHandle, zzbd zzbdVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzg.zze(new zzbb(this, taskCompletionSource), str, verificationHandle);
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<VerificationHandle> challengeAccount(final RecaptchaHandle recaptchaHandle, final String str) {
        if (recaptchaHandle == null || str == null) {
            throw new NullPointerException("Cannot call challengeAccount with a null RecaptchaHandle or a null challenge request token.");
        }
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.recaptcha.zzat
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzbc.this.zza(recaptchaHandle, str, (zzbd) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(19805).build());
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<Boolean> close(final RecaptchaHandle recaptchaHandle) {
        if (recaptchaHandle == null) {
            throw new NullPointerException("Cannot call close with a null RecaptchaHandle.");
        }
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.recaptcha.zzar
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzbc zzbcVar = zzbc.this;
                RecaptchaHandle recaptchaHandle2 = recaptchaHandle;
                ((zzaf) ((zzbd) obj).getService()).zzc(new zzaz(zzbcVar, (TaskCompletionSource) obj2), recaptchaHandle2);
            }
        }).setFeatures(com.google.android.gms.recaptcha.zzc.zzd).setMethodKey(19804).build());
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<RecaptchaResultData> execute(final RecaptchaHandle recaptchaHandle, final RecaptchaAction recaptchaAction) {
        if (recaptchaHandle == null || recaptchaAction == null) {
            throw new NullPointerException("Cannot call execute with a null RecaptchaHandle or a null RecaptchaAction. Make sure to call init first.");
        }
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.recaptcha.zzas
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzbc.this.zzb(recaptchaHandle, recaptchaAction, (zzbd) obj, (TaskCompletionSource) obj2);
            }
        }).setFeatures(com.google.android.gms.recaptcha.zzc.zzc).setMethodKey(19803).build());
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<RecaptchaHandle> init(final String str) {
        if (str == null) {
            throw new NullPointerException("Cannot call init with a null site key.");
        }
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.recaptcha.zzau
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzbc zzbcVar = zzbc.this;
                String str2 = str;
                ((zzaf) ((zzbd) obj).getService()).zze(new zzax(zzbcVar, (TaskCompletionSource) obj2), new zzag(str2, zzt.zza()));
            }
        }).setFeatures(com.google.android.gms.recaptcha.zzc.zzb).setMethodKey(19802).build());
    }

    @Override // com.google.android.gms.recaptcha.RecaptchaClient
    public final Task<VerificationResult> verifyAccount(final String str, final VerificationHandle verificationHandle) {
        if (str == null || verificationHandle == null) {
            throw new NullPointerException("Cannot call verifyAccount with a null pin or a null VerificationHandle.");
        }
        return doRead(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.recaptcha.zzav
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzbc.this.zzc(str, verificationHandle, (zzbd) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(19806).build());
    }

    public zzbc(Context context) {
        super(context, zzd, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zze = context;
        zzcs zzcsVar = zzb;
        this.zzf = new zzcy(zzcsVar);
        this.zzg = new zzdf(context, zzcsVar);
    }
}

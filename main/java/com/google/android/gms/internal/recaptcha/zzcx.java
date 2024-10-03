package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.recaptcha.RecaptchaHandle;
import com.google.android.gms.recaptcha.VerificationHandle;
import com.google.android.gms.tasks.OnSuccessListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcx implements OnSuccessListener<zzvo> {
    final /* synthetic */ RecaptchaHandle zza;
    final /* synthetic */ zzcy zzb;
    final /* synthetic */ zzcv zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcx(zzcy zzcyVar, zzcv zzcvVar, RecaptchaHandle recaptchaHandle, byte[] bArr) {
        this.zzb = zzcyVar;
        this.zzc = zzcvVar;
        this.zza = recaptchaHandle;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* bridge */ /* synthetic */ void onSuccess(zzvo zzvoVar) {
        zzcs zzcsVar;
        zzvo zzvoVar2 = zzvoVar;
        if (zzvoVar2.zzg().equals(zzvw.NO_ERROR)) {
            zzcy zzcyVar = this.zzb;
            zzcv zzcvVar = this.zzc;
            zzcsVar = zzcyVar.zza;
            zzcy.zzg(zzcvVar, VerificationHandle.zzd(zzvoVar2, zzcsVar, this.zza.getSiteKey()));
            return;
        }
        zzcy.zzd(this.zzb, this.zzc, zzvoVar2, this.zza);
    }
}

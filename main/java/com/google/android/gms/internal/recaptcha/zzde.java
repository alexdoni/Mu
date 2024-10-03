package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.VerificationHandle;
import com.google.android.gms.recaptcha.VerificationResult;
import com.google.android.gms.tasks.OnSuccessListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzde implements OnSuccessListener<zzvs> {
    final /* synthetic */ VerificationHandle zza;
    final /* synthetic */ zzdf zzb;
    final /* synthetic */ zzdc zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzde(zzdf zzdfVar, VerificationHandle verificationHandle, zzdc zzdcVar, byte[] bArr) {
        this.zzb = zzdfVar;
        this.zza = verificationHandle;
        this.zzc = zzdcVar;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* bridge */ /* synthetic */ void onSuccess(zzvs zzvsVar) {
        Context context;
        zzvs zzvsVar2 = zzvsVar;
        if (zzvsVar2.zzg() == zzvw.NO_ERROR) {
            context = this.zzb.zza;
            zzdb.zzb(context, zzvsVar2.zzi(), this.zza.getSiteKey());
            zzdf.zzg(this.zzc, VerificationResult.zzb(zzvsVar2.zzk(), new Status(0)));
            return;
        }
        zzdf.zzd(this.zzb, this.zzc, zzvsVar2, this.zza.getSiteKey());
    }
}

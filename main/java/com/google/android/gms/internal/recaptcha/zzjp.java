package com.google.android.gms.internal.recaptcha;

import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzjp extends zzjs {
    final /* synthetic */ zzjq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzjp(zzjq zzjqVar, zzjt zzjtVar, CharSequence charSequence) {
        super(zzjtVar, charSequence);
        this.zza = zzjqVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjs
    final int zzc(int i) {
        return i + 1;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzjs
    final int zzd(int i) {
        zzja zzjaVar = this.zza.zza;
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzjn.zzb(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (zzjaVar.zza(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}

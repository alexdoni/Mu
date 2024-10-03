package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzgy implements zzfq {
    final /* synthetic */ zzgz zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgy(zzgz zzgzVar, zzgu zzguVar) {
        this.zza = zzgzVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfq
    public final zzop<Void> zza(final zzng zzngVar, final Executor executor) {
        zzia zziaVar;
        zzhs zzhsVar;
        zziaVar = this.zza.zzh;
        zziaVar.zza();
        zzhsVar = this.zza.zze;
        return zzof.zzk(zzof.zzh(zzhsVar.zzb()), zzim.zzc(new zzng() { // from class: com.google.android.gms.internal.recaptcha.zzgx
            @Override // com.google.android.gms.internal.recaptcha.zzng
            public final zzop zza(Object obj) {
                zzhc zzhcVar;
                zzgy zzgyVar = zzgy.this;
                zzng zzngVar2 = zzngVar;
                Executor executor2 = executor;
                zzhcVar = zzgyVar.zza.zzc;
                return zzhcVar.zzj(zzngVar2, executor2, null);
            }
        }), zzow.zzb());
    }
}

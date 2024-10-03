package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.zzaa;
import com.google.android.play.integrity.internal.zzx;
import com.google.android.play.integrity.internal.zzy;
import com.google.android.play.integrity.internal.zzz;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
final class zzj {
    private final zzj zza = this;
    private final zzaa zzb;
    private final zzaa zzc;
    private final zzaa zzd;
    private final zzaa zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzj(Context context, zzi zziVar) {
        zzp zzpVar;
        zzy zzb = zzz.zzb(context);
        this.zzb = zzb;
        zzpVar = zzo.zza;
        zzaa zzb2 = zzx.zzb(zzpVar);
        this.zzc = zzb2;
        zzaa zzb3 = zzx.zzb(new zzv(zzb, zzb2));
        this.zzd = zzb3;
        this.zze = zzx.zzb(new zzn(zzb3));
    }

    public final IntegrityManager zza() {
        return (IntegrityManager) this.zze.zza();
    }
}

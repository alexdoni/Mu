package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.zzaa;
import com.google.android.play.integrity.internal.zzy;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public final class zzv implements zzy {
    private final zzaa zza;
    private final zzaa zzb;

    public zzv(zzaa zzaaVar, zzaa zzaaVar2) {
        this.zza = zzaaVar;
        this.zzb = zzaaVar2;
    }

    @Override // com.google.android.play.integrity.internal.zzaa
    public final /* bridge */ /* synthetic */ Object zza() {
        return new zzt((Context) this.zza.zza(), (com.google.android.play.integrity.internal.zzi) this.zzb.zza());
    }
}

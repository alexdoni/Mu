package com.google.android.play.core.integrity;

import android.content.Context;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
final class zzh implements zzk {
    private Context zza;

    private zzh() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzh(zzg zzgVar) {
    }

    public final zzh zza(Context context) {
        context.getClass();
        this.zza = context;
        return this;
    }

    @Override // com.google.android.play.core.integrity.zzk
    public final zzj zzb() {
        Context context = this.zza;
        if (context != null) {
            return new zzj(context, null);
        }
        throw new IllegalStateException(String.valueOf(Context.class.getCanonicalName()).concat(" must be set"));
    }
}

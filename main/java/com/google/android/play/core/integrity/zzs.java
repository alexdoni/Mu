package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
final class zzs extends com.google.android.play.integrity.internal.zzg {
    final /* synthetic */ zzt zza;
    private final com.google.android.play.integrity.internal.zzi zzb = new com.google.android.play.integrity.internal.zzi("OnRequestIntegrityTokenCallback");
    private final TaskCompletionSource zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(zzt zztVar, TaskCompletionSource taskCompletionSource) {
        this.zza = zztVar;
        this.zzc = taskCompletionSource;
    }

    @Override // com.google.android.play.integrity.internal.zzh
    public final void zzb(Bundle bundle) {
        this.zza.zza.zzr(this.zzc);
        this.zzb.zzd("onRequestIntegrityToken", new Object[0]);
        int i = bundle.getInt("error");
        if (i != 0) {
            this.zzc.trySetException(new IntegrityServiceException(i, null));
            return;
        }
        String string = bundle.getString("token");
        if (string == null) {
            this.zzc.trySetException(new IntegrityServiceException(-100, null));
            return;
        }
        TaskCompletionSource taskCompletionSource = this.zzc;
        zzd zzdVar = new zzd();
        zzdVar.zza(string);
        taskCompletionSource.trySetResult(zzdVar.zzb());
    }
}

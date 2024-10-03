package com.google.android.play.core.integrity;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public final class zzr extends com.google.android.play.integrity.internal.zzj {
    final /* synthetic */ byte[] zza;
    final /* synthetic */ Long zzb;
    final /* synthetic */ TaskCompletionSource zzc;
    final /* synthetic */ IntegrityTokenRequest zzd;
    final /* synthetic */ zzt zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzr(zzt zztVar, TaskCompletionSource taskCompletionSource, byte[] bArr, Long l, TaskCompletionSource taskCompletionSource2, IntegrityTokenRequest integrityTokenRequest) {
        super(taskCompletionSource);
        this.zze = zztVar;
        this.zza = bArr;
        this.zzb = l;
        this.zzc = taskCompletionSource2;
        this.zzd = integrityTokenRequest;
    }

    @Override // com.google.android.play.integrity.internal.zzj
    public final void zza(Exception exc) {
        if (exc instanceof com.google.android.play.integrity.internal.zzu) {
            super.zza(new IntegrityServiceException(-9, exc));
        } else {
            super.zza(exc);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.play.integrity.internal.zzf, android.os.IInterface] */
    @Override // com.google.android.play.integrity.internal.zzj
    protected final void zzb() {
        com.google.android.play.integrity.internal.zzi zziVar;
        try {
            this.zze.zza.zze().zzc(zzt.zza(this.zze, this.zza, this.zzb), new zzs(this.zze, this.zzc));
        } catch (RemoteException e) {
            zziVar = this.zze.zzb;
            zziVar.zzc(e, "requestIntegrityToken(%s)", this.zzd);
            this.zzc.trySetException(new IntegrityServiceException(-100, e));
        }
    }
}

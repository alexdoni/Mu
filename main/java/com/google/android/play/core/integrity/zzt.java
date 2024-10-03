package com.google.android.play.core.integrity;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
final class zzt {
    final com.google.android.play.integrity.internal.zzt zza;
    private final com.google.android.play.integrity.internal.zzi zzb;
    private final String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(Context context, com.google.android.play.integrity.internal.zzi zziVar) {
        this.zzc = context.getPackageName();
        this.zzb = zziVar;
        if (!com.google.android.play.integrity.internal.zzw.zza(context)) {
            zziVar.zzb("Phonesky is not installed.", new Object[0]);
            this.zza = null;
        } else {
            this.zza = new com.google.android.play.integrity.internal.zzt(context, zziVar, "IntegrityService", zzu.zza, zzq.zza, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ Bundle zza(zzt zztVar, byte[] bArr, Long l) {
        Bundle bundle = new Bundle();
        bundle.putString("package.name", zztVar.zzc);
        bundle.putByteArray("nonce", bArr);
        if (l != null) {
            bundle.putLong("cloud.prj", l.longValue());
        }
        return bundle;
    }

    public final Task zzb(IntegrityTokenRequest integrityTokenRequest) {
        if (this.zza == null) {
            return Tasks.forException(new IntegrityServiceException(-2, null));
        }
        try {
            byte[] decode = Base64.decode(integrityTokenRequest.nonce(), 10);
            Long cloudProjectNumber = integrityTokenRequest.cloudProjectNumber();
            this.zzb.zzd("requestIntegrityToken(%s)", integrityTokenRequest);
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            this.zza.zzp(new zzr(this, taskCompletionSource, decode, cloudProjectNumber, taskCompletionSource, integrityTokenRequest), taskCompletionSource);
            return taskCompletionSource.getTask();
        } catch (IllegalArgumentException e) {
            return Tasks.forException(new IntegrityServiceException(-13, e));
        }
    }
}

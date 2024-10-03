package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.1.0 */
/* loaded from: classes2.dex */
public abstract class zzr {
    final int zza;
    final TaskCompletionSource zzb = new TaskCompletionSource();
    final int zzc;
    final Bundle zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(int i, int i2, Bundle bundle) {
        this.zza = i;
        this.zzc = i2;
        this.zzd = bundle;
    }

    public final String toString() {
        return "Request { what=" + this.zzc + " id=" + this.zza + " oneWay=" + zzb() + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(Bundle bundle);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzb();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(zzs zzsVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", "Failing " + toString() + " with " + zzsVar.toString());
        }
        this.zzb.setException(zzsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(Object obj) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", "Finishing " + toString() + " with " + String.valueOf(obj));
        }
        this.zzb.setResult(obj);
    }
}

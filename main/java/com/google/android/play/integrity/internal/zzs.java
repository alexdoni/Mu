package com.google.android.play.integrity.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public final class zzs implements ServiceConnection {
    final /* synthetic */ zzt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzs(zzt zztVar, zzr zzrVar) {
        this.zza = zztVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzt.zzf(this.zza).zzd("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.zza.zzc().post(new zzp(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzt.zzf(this.zza).zzd("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.zza.zzc().post(new zzq(this));
    }
}

package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzoh;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzmd {
    protected long zza;
    final /* synthetic */ zzlx zzb;
    private long zzc;
    private final zzaw zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zza(long j) {
        long j2 = j - this.zza;
        this.zza = j;
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(zzmd zzmdVar) {
        zzmdVar.zzb.zzt();
        zzmdVar.zza(false, false, zzmdVar.zzb.zzb().elapsedRealtime());
        zzmdVar.zzb.zzc().zza(zzmdVar.zzb.zzb().elapsedRealtime());
    }

    public zzmd(zzlx zzlxVar) {
        this.zzb = zzlxVar;
        this.zzd = new zzmg(this, zzlxVar.zzu);
        long elapsedRealtime = zzlxVar.zzb().elapsedRealtime();
        this.zzc = elapsedRealtime;
        this.zza = elapsedRealtime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        this.zzd.zza();
        this.zzc = 0L;
        this.zza = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(long j) {
        this.zzd.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(long j) {
        this.zzb.zzt();
        this.zzd.zza();
        this.zzc = j;
        this.zza = j;
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        this.zzb.zzt();
        this.zzb.zzu();
        if (!zzoh.zza() || !this.zzb.zze().zza(zzbi.zzbn) || this.zzb.zzu.zzac()) {
            this.zzb.zzk().zzk.zza(this.zzb.zzb().currentTimeMillis());
        }
        long j2 = j - this.zzc;
        if (!z && j2 < 1000) {
            this.zzb.zzj().zzp().zza("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
            return false;
        }
        if (!z2) {
            j2 = zza(j);
        }
        this.zzb.zzj().zzp().zza("Recording user engagement, ms", Long.valueOf(j2));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", j2);
        zznd.zza(this.zzb.zzn().zza(!this.zzb.zze().zzu()), bundle, true);
        if (!z2) {
            this.zzb.zzm().zzc(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_e", bundle);
        }
        this.zzc = j;
        this.zzd.zza();
        this.zzd.zza(3600000L);
        return true;
    }
}

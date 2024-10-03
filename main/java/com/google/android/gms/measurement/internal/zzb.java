package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzb extends zzf {
    private final Map<String, Long> zza;
    private final Map<String, Integer> zzb;
    private long zzc;

    @Override // com.google.android.gms.measurement.internal.zzid, com.google.android.gms.measurement.internal.zzif
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzid, com.google.android.gms.measurement.internal.zzif
    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzid, com.google.android.gms.measurement.internal.zzif
    @Pure
    public final /* bridge */ /* synthetic */ zzae zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzid
    @Pure
    public final /* bridge */ /* synthetic */ zzaf zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzid
    @Pure
    public final /* bridge */ /* synthetic */ zzba zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzfl zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzfo zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzid
    @Pure
    public final /* bridge */ /* synthetic */ zzfq zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzid, com.google.android.gms.measurement.internal.zzif
    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzid
    @Pure
    public final /* bridge */ /* synthetic */ zzgd zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzid, com.google.android.gms.measurement.internal.zzif
    @Pure
    public final /* bridge */ /* synthetic */ zzgy zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zziq zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzkh zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzkp zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzlx zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzid
    @Pure
    public final /* bridge */ /* synthetic */ zznd zzq() {
        return super.zzq();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(zzb zzbVar, String str, long j) {
        zzbVar.zzt();
        Preconditions.checkNotEmpty(str);
        if (zzbVar.zzb.isEmpty()) {
            zzbVar.zzc = j;
        }
        Integer num = zzbVar.zzb.get(str);
        if (num != null) {
            zzbVar.zzb.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (zzbVar.zzb.size() >= 100) {
            zzbVar.zzj().zzu().zza("Too many ads visible");
        } else {
            zzbVar.zzb.put(str, 1);
            zzbVar.zza.put(str, Long.valueOf(j));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzb(zzb zzbVar, String str, long j) {
        zzbVar.zzt();
        Preconditions.checkNotEmpty(str);
        Integer num = zzbVar.zzb.get(str);
        if (num != null) {
            zzki zza = zzbVar.zzn().zza(false);
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                zzbVar.zzb.remove(str);
                Long l = zzbVar.zza.get(str);
                if (l == null) {
                    zzbVar.zzj().zzg().zza("First ad unit exposure time was never set");
                } else {
                    long longValue = j - l.longValue();
                    zzbVar.zza.remove(str);
                    zzbVar.zza(str, longValue, zza);
                }
                if (zzbVar.zzb.isEmpty()) {
                    long j2 = zzbVar.zzc;
                    if (j2 == 0) {
                        zzbVar.zzj().zzg().zza("First ad exposure time was never set");
                        return;
                    } else {
                        zzbVar.zza(j - j2, zza);
                        zzbVar.zzc = 0L;
                        return;
                    }
                }
                return;
            }
            zzbVar.zzb.put(str, Integer.valueOf(intValue));
            return;
        }
        zzbVar.zzj().zzg().zza("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    public zzb(zzhf zzhfVar) {
        super(zzhfVar);
        this.zzb = new ArrayMap();
        this.zza = new ArrayMap();
    }

    public final void zza(String str, long j) {
        if (str == null || str.length() == 0) {
            zzj().zzg().zza("Ad unit id must be a non-empty string");
        } else {
            zzl().zzb(new zza(this, str, j));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzid
    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzid
    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzid
    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final void zzb(String str, long j) {
        if (str == null || str.length() == 0) {
            zzj().zzg().zza("Ad unit id must be a non-empty string");
        } else {
            zzl().zzb(new zzd(this, str, j));
        }
    }

    private final void zza(long j, zzki zzkiVar) {
        if (zzkiVar == null) {
            zzj().zzp().zza("Not logging ad exposure. No active activity");
            return;
        }
        if (j < 1000) {
            zzj().zzp().zza("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("_xt", j);
        zznd.zza(zzkiVar, bundle, true);
        zzm().zzc("am", "_xa", bundle);
    }

    private final void zza(String str, long j, zzki zzkiVar) {
        if (zzkiVar == null) {
            zzj().zzp().zza("Not logging ad unit exposure. No active activity");
            return;
        }
        if (j < 1000) {
            zzj().zzp().zza("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("_ai", str);
        bundle.putLong("_xt", j);
        zznd.zza(zzkiVar, bundle, true);
        zzm().zzc("am", "_xu", bundle);
    }

    public final void zza(long j) {
        zzki zza = zzn().zza(false);
        for (String str : this.zza.keySet()) {
            zza(str, j - this.zza.get(str).longValue(), zza);
        }
        if (!this.zza.isEmpty()) {
            zza(j - this.zzc, zza);
        }
        zzb(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(long j) {
        Iterator<String> it = this.zza.keySet().iterator();
        while (it.hasNext()) {
            this.zza.put(it.next(), Long.valueOf(j));
        }
        if (this.zza.isEmpty()) {
            return;
        }
        this.zzc = j;
    }
}

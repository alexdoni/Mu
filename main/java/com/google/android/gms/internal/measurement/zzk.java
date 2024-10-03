package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzk extends zzal {
    private final zzac zzk;

    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzaq zza(zzh zzhVar, List<zzaq> list) {
        Map<String, Object> hashMap;
        zzg.zza(this.zza, 3, list);
        String zzf = zzhVar.zza(list.get(0)).zzf();
        long zza = (long) zzg.zza(zzhVar.zza(list.get(1)).zze().doubleValue());
        zzaq zza2 = zzhVar.zza(list.get(2));
        if (zza2 instanceof zzap) {
            hashMap = zzg.zza((zzap) zza2);
        } else {
            hashMap = new HashMap<>();
        }
        this.zzk.zza(zzf, zza, hashMap);
        return zzaq.zzc;
    }

    public zzk(zzac zzacVar) {
        super("internal.eventLogger");
        this.zzk = zzacVar;
    }
}

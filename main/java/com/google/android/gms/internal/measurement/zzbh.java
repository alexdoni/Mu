package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzbh implements Comparator<zzaq> {
    private final /* synthetic */ zzal zza;
    private final /* synthetic */ zzh zzb;

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzaq zzaqVar, zzaq zzaqVar2) {
        zzaq zzaqVar3 = zzaqVar;
        zzaq zzaqVar4 = zzaqVar2;
        zzal zzalVar = this.zza;
        zzh zzhVar = this.zzb;
        if (zzaqVar3 instanceof zzax) {
            return !(zzaqVar4 instanceof zzax) ? 1 : 0;
        }
        if (zzaqVar4 instanceof zzax) {
            return -1;
        }
        if (zzalVar == null) {
            return zzaqVar3.zzf().compareTo(zzaqVar4.zzf());
        }
        return (int) zzg.zza(zzalVar.zza(zzhVar, Arrays.asList(zzaqVar3, zzaqVar4)).zze().doubleValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbh(zzal zzalVar, zzh zzhVar) {
        this.zza = zzalVar;
        this.zzb = zzhVar;
    }
}

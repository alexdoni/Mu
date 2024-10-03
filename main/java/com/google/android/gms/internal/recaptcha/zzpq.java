package com.google.android.gms.internal.recaptcha;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzpq implements Comparator<zzpy> {
    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(zzpy zzpyVar, zzpy zzpyVar2) {
        zzpy zzpyVar3 = zzpyVar;
        zzpy zzpyVar4 = zzpyVar2;
        zzpo zzpoVar = new zzpo(zzpyVar3);
        zzpo zzpoVar2 = new zzpo(zzpyVar4);
        while (zzpoVar.hasNext() && zzpoVar2.hasNext()) {
            int zza = zzpp.zza(zzpoVar.zza() & 255, zzpoVar2.zza() & 255);
            if (zza != 0) {
                return zza;
            }
        }
        return zzpp.zza(zzpyVar3.zzd(), zzpyVar4.zzd());
    }
}

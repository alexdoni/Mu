package com.google.android.gms.internal.measurement;

import com.facebook.share.internal.ShareConstants;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzw extends zzal {
    private zzaa zzk;

    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzaq zza(zzh zzhVar, List<zzaq> list) {
        zzg.zza(this.zza, 3, list);
        String zzf = zzhVar.zza(list.get(0)).zzf();
        zzaq zza = zzhVar.zza(list.get(1));
        if (!(zza instanceof zzar)) {
            throw new IllegalArgumentException("Invalid callback type");
        }
        zzaq zza2 = zzhVar.zza(list.get(2));
        if (!(zza2 instanceof zzap)) {
            throw new IllegalArgumentException("Invalid callback params");
        }
        zzap zzapVar = (zzap) zza2;
        if (!zzapVar.zzc(ShareConstants.MEDIA_TYPE)) {
            throw new IllegalArgumentException("Undefined rule type");
        }
        this.zzk.zza(zzf, zzapVar.zzc("priority") ? zzg.zzb(zzapVar.zza("priority").zze().doubleValue()) : 1000, (zzar) zza, zzapVar.zza(ShareConstants.MEDIA_TYPE).zzf());
        return zzaq.zzc;
    }

    public zzw(zzaa zzaaVar) {
        super("internal.registerCallback");
        this.zzk = zzaaVar;
    }
}

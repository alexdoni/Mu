package com.google.android.gms.internal.recaptcha;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzrz extends zzsa {
    private zzrz() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzrz(zzrx zzrxVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzsa
    public final <L> List<L> zza(Object obj, long j) {
        zzro zzroVar = (zzro) zzub.zzf(obj, j);
        if (zzroVar.zzc()) {
            return zzroVar;
        }
        int size = zzroVar.size();
        zzro zzd = zzroVar.zzd(size == 0 ? 10 : size + size);
        zzub.zzs(obj, j, zzd);
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzsa
    public final void zzb(Object obj, long j) {
        ((zzro) zzub.zzf(obj, j)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzsa
    public final <E> void zzc(Object obj, Object obj2, long j) {
        zzro zzroVar = (zzro) zzub.zzf(obj, j);
        zzro zzroVar2 = (zzro) zzub.zzf(obj2, j);
        int size = zzroVar.size();
        int size2 = zzroVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzroVar.zzc()) {
                zzroVar = zzroVar.zzd(size2 + size);
            }
            zzroVar.addAll(zzroVar2);
        }
        if (size > 0) {
            zzroVar2 = zzroVar;
        }
        zzub.zzs(obj, j, zzroVar2);
    }
}

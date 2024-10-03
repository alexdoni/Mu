package com.google.android.gms.internal.recaptcha;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zznd extends zznb {
    private zznd() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zznd(zzna zznaVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zznb
    public final int zza(zzne<?> zzneVar) {
        int zzB;
        synchronized (zzneVar) {
            zzB = zzne.zzB(zzneVar);
        }
        return zzB;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zznb
    public final void zzb(zzne<?> zzneVar, @CheckForNull Set<Throwable> set, Set<Throwable> set2) {
        Set set3;
        synchronized (zzneVar) {
            set3 = ((zzne) zzneVar).seenExceptions;
            if (set3 == null) {
                ((zzne) zzneVar).seenExceptions = set2;
            }
        }
    }
}

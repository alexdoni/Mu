package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
final class zzm implements IntegrityManager {
    private final zzt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(zzt zztVar) {
        this.zza = zztVar;
    }

    @Override // com.google.android.play.core.integrity.IntegrityManager
    public final Task<IntegrityTokenResponse> requestIntegrityToken(IntegrityTokenRequest integrityTokenRequest) {
        return this.zza.zzb(integrityTokenRequest);
    }
}

package com.google.android.play.core.integrity;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
final class zzd extends zzw {
    private String zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.integrity.zzw
    public final zzw zza(String str) {
        this.zza = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.integrity.zzw
    public final IntegrityTokenResponse zzb() {
        String str = this.zza;
        if (str != null) {
            return new zzf(str, null);
        }
        throw new IllegalStateException("Missing required properties: token");
    }
}

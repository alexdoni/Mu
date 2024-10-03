package com.google.android.gms.internal.recaptcha;

import java.io.Serializable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzll extends zzlm implements Serializable {
    final long zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzll(long j) {
        this.zza = j;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final int zza() {
        return (int) this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final int zzb() {
        return 64;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final long zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    final boolean zzd(zzlm zzlmVar) {
        return this.zza == zzlmVar.zzc();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final byte[] zze() {
        return new byte[]{(byte) this.zza, (byte) (r2 >> 8), (byte) (r2 >> 16), (byte) (r2 >> 24), (byte) (r2 >> 32), (byte) (r2 >> 40), (byte) (r2 >> 48), (byte) (r2 >> 56)};
    }
}

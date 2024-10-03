package com.google.android.gms.internal.recaptcha;

import java.io.Serializable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzlk extends zzlm implements Serializable {
    final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlk(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final int zza() {
        int length = this.zza.length;
        zzjn.zzk(length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", length);
        byte[] bArr = this.zza;
        return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final int zzb() {
        return this.zza.length * 8;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final long zzc() {
        int length = this.zza.length;
        zzjn.zzk(length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", length);
        long j = this.zza[0] & 255;
        for (int i = 1; i < Math.min(this.zza.length, 8); i++) {
            j |= (this.zza[i] & 255) << (i * 8);
        }
        return j;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    final boolean zzd(zzlm zzlmVar) {
        if (this.zza.length != zzlmVar.zzf().length) {
            return false;
        }
        boolean z = true;
        int i = 0;
        while (true) {
            byte[] bArr = this.zza;
            if (i >= bArr.length) {
                return z;
            }
            z &= bArr[i] == zzlmVar.zzf()[i];
            i++;
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final byte[] zze() {
        return (byte[]) this.zza.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.recaptcha.zzlm
    public final byte[] zzf() {
        return this.zza;
    }
}

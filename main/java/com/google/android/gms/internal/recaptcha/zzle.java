package com.google.android.gms.internal.recaptcha;

import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
abstract class zzle implements zzln {
    @Override // com.google.android.gms.internal.recaptcha.zzln
    public final zzlm zza(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public zzlm zzb(byte[] bArr, int i, int i2) {
        zzjn.zzh(0, i2, bArr.length);
        zzlo zzd = zzd(i2);
        zzd.zza(bArr, 0, i2);
        return zzd.zzd();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzln
    public zzlm zzc(CharSequence charSequence, Charset charset) {
        throw null;
    }

    public zzlo zzd(int i) {
        return zze();
    }
}

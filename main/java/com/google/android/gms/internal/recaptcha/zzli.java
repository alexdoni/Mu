package com.google.android.gms.internal.recaptcha;

import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
abstract class zzli extends zzle {
    @Override // com.google.android.gms.internal.recaptcha.zzle, com.google.android.gms.internal.recaptcha.zzln
    public final zzlm zzc(CharSequence charSequence, Charset charset) {
        byte[] bytes = charSequence.toString().getBytes(charset);
        return zzb(bytes, 0, bytes.length);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzle
    public final zzlo zzd(int i) {
        return new zzlg(this, i);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzln
    public final zzlo zze() {
        return new zzlg(this, 32);
    }
}

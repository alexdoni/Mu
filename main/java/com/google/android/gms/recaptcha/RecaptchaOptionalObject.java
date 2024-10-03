package com.google.android.gms.recaptcha;

import com.google.android.gms.internal.recaptcha.zzjj;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public class RecaptchaOptionalObject<T> {
    private final zzjj<T> zza;

    private RecaptchaOptionalObject(zzjj<T> zzjjVar) {
        this.zza = zzjjVar;
    }

    public static <T> RecaptchaOptionalObject<T> ofNullable(T t) {
        return new RecaptchaOptionalObject<>(zzjj.zze(t));
    }

    public T orNull() {
        return this.zza.zzb();
    }
}

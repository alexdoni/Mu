package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzfe implements zzec<InputStream> {
    private zzfe() {
    }

    public static zzfe zzb() {
        return new zzfe();
    }

    public static final InputStream zzc(zzeb zzebVar) throws IOException {
        return zzebVar.zzc(zzebVar.zzb().zze(zzebVar.zza())).get(0);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzec
    public final /* bridge */ /* synthetic */ InputStream zza(zzeb zzebVar) throws IOException {
        return zzc(zzebVar);
    }
}

package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.net.URLConnection;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzbg implements zzl {
    final /* synthetic */ zzbh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbg(zzbh zzbhVar) {
        this.zza = zzbhVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzl
    public final /* bridge */ /* synthetic */ URLConnection zza() throws IOException {
        return zzbh.zzd(this.zza);
    }
}

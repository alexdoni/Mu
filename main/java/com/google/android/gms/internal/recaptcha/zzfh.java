package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzfh implements zzfg {
    /* JADX INFO: Access modifiers changed from: protected */
    public Uri zza(Uri uri) throws IOException {
        throw null;
    }

    protected abstract zzfg zzc();

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public /* synthetic */ File zzd(Uri uri) {
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public InputStream zze(Uri uri) throws IOException {
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public boolean zzg(Uri uri) throws IOException {
        throw null;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final OutputStream zzj(Uri uri) throws IOException {
        return zzc().zzj(zza(uri));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final void zzk(Uri uri) throws IOException {
        zzc().zzk(zza(uri));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final void zzl(Uri uri, Uri uri2) throws IOException {
        zzc().zzl(zza(uri), zza(uri2));
    }
}

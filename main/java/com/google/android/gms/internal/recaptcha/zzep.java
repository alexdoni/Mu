package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import com.facebook.share.internal.ShareInternalUtility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzep implements zzfg {
    private final zzet zza;

    public zzep() {
        this.zza = new zzet();
    }

    public zzep(zzet zzetVar) {
        this.zza = zzetVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final File zzd(Uri uri) throws IOException {
        return zzeo.zza(uri);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final InputStream zze(Uri uri) throws IOException {
        return zzex.zzb(zzeo.zza(uri));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final String zzf() {
        return ShareInternalUtility.STAGING_PARAM;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final boolean zzg(Uri uri) throws IOException {
        return zzeo.zza(uri).exists();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final OutputStream zzj(Uri uri) throws IOException {
        File zza = zzeo.zza(uri);
        zzmi.zza(zza);
        return new zzey(new FileOutputStream(zza), zza);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final void zzk(Uri uri) throws IOException {
        File zza = zzeo.zza(uri);
        if (zza.isDirectory()) {
            throw new FileNotFoundException(String.format("%s is a directory", uri));
        }
        if (zza.delete()) {
            return;
        }
        if (!zza.exists()) {
            throw new FileNotFoundException(String.format("%s does not exist", uri));
        }
        throw new IOException(String.format("%s could not be deleted", uri));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzfg
    public final void zzl(Uri uri, Uri uri2) throws IOException {
        File zza = zzeo.zza(uri);
        File zza2 = zzeo.zza(uri2);
        zzmi.zza(zza2);
        if (!zza.renameTo(zza2)) {
            throw new IOException(String.format("%s could not be renamed to %s", uri, uri2));
        }
    }
}

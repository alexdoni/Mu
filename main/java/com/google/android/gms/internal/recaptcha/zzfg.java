package com.google.android.gms.internal.recaptcha;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public interface zzfg {
    File zzd(Uri uri) throws IOException;

    InputStream zze(Uri uri) throws IOException;

    String zzf();

    boolean zzg(Uri uri) throws IOException;

    OutputStream zzj(Uri uri) throws IOException;

    void zzk(Uri uri) throws IOException;

    void zzl(Uri uri, Uri uri2) throws IOException;
}

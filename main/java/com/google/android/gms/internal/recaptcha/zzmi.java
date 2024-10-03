package com.google.android.gms.internal.recaptcha;

import java.io.File;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzmi {
    private static final zzlc<File> zza = new zzmh();

    public static void zza(File file) throws IOException {
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile == null) {
            return;
        }
        parentFile.mkdirs();
        if (parentFile.isDirectory()) {
            return;
        }
        String valueOf = String.valueOf(file);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39);
        sb.append("Unable to create parent directories of ");
        sb.append(valueOf);
        throw new IOException(sb.toString());
    }
}
